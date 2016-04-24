package Service;


import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;

import org.xeustechnologies.jtar.TarOutputStream;
import org.xeustechnologies.jtar.TarEntry;

import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;

import java.io.IOException;
import java.util.Locale;

import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Image;

import Entity.Container;

/**
 *
 * @author taber
 */
public class Delivery {
    
    private static final String dir = "/" + System.getProperty("user.home") + "/ContainerShoppingCart";
    private static final String imgDir = dir + "/Images";
    private static final String scriptDir = dir + "/Script";
    private static final String deliveryDir = dir + "/Tar";
    
    DockerClient dockerClient;
    
    public Delivery(){
        File appDir = new File(dir);
        File imgDirFile = new File(imgDir);
        File scriptDirFile = new File(scriptDir);
        File deliveryDirFile = new File(deliveryDir);
        
        dockerClient = DockerClientBuilder.getInstance().build();
        
        if(!appDir.exists())
        {
            try{
                boolean succeed = appDir.mkdir();
                if (succeed)
                        System.out.println("success");
                else
                    System.out.println("failure");
                //System.out.println(appDir.getAbsolutePath());
                imgDirFile.mkdir();
                //System.out.println(imgDirFile.getAbsolutePath());
                scriptDirFile.mkdir();
                //System.out.println(scriptDirFile.getAbsolutePath());
                deliveryDirFile.mkdir();
                //System.out.println(deliveryDirFile.getAbsolutePath());
            }
            catch(Exception ex)
            {
                System.out.println("Error creating /home/$user/ContainerShoppingCart/ and all children directories.");
                System.out.println(ex.toString());
            }
        }
    }
    
    /**
     * Used for packing all contents to be transferred via HTTP Response
     * 
     * @param userID
     * @param items
     * @return File object pointing to the TAR file containing all of the customers shopping cart contents
     */
    public File getLocalDeliveryPackage(long userID, ArrayList<Container> items)
    {
        File deliveryPckg = generateDeliveryPackage(userID, items);
        
        return deliveryPckg;
    }
    
    private File generateDeliveryPackage(long userID, ArrayList<Container> items)
    {
        ArrayList<File> pckgContents = new ArrayList<>();
        ConfigurationManager configMgr = new ConfigurationManager(userID);
        File deliveryPckg = new File(deliveryDir + "/" + userID + "-" + Calendar.getInstance().getDisplayName(Calendar.DATE, Calendar.LONG, Locale.getDefault()) + ".tar");
        
        int currIndex = 0;
        
        for(int i = 0; i < items.size(); i++)
        {
            //Add image tar and script to pckgContents
            pckgContents.add(new File(imgDir + "/" + items.get(i).getDockerID() + ".tar"));
            if(!pckgContents.get(i).exists())
            {
                if(!hasImage(items.get(i)))
                {
                    //pullImage(items.get(i));
                }
                
                pckgContents.set(currIndex, saveImage(items.get(i)));
            }
            
            currIndex++;
            
            pckgContents.add(configMgr.generateStartScript(0, items.get(i)));
            
            currIndex++;
        }
        
        try{
            TarOutputStream tarOut = new TarOutputStream( new BufferedOutputStream( new FileOutputStream(deliveryPckg)));
            BufferedInputStream tarIn;
            
            for(File item: pckgContents)
            {
                tarOut.putNextEntry(new TarEntry(item, item.getName()));
                tarIn = new BufferedInputStream(new FileInputStream(item));
                
                int count;
                byte[] data = new byte[1024];
                while((count = tarIn.read(data)) != -1)
                {
                    tarOut.write(data, 0, count);
                }
            }
            
            tarOut.flush();
            tarOut.close();
        }
        catch(Exception ex)
        {
            System.out.println("Error adding items to shipping container.");
            System.out.println(ex.toString());
        }
        
        return deliveryPckg;
    }
    
    private File saveImage(Container content)
    {
        File imgTar = new File (imgDir + "/" + content.getDockerName() + ".tar");
        byte[] buffer = new byte[100];
        int bytesRead = 0;
        
        try{
            //Set "docker save" command to the process
            InputStream imgByteStream = this.dockerClient.saveImageCmd(content.getDockerID()).exec();
            OutputStream tarFileWriter = new FileOutputStream(imgTar);
            
            int count;
            byte[] data = new byte[100];
            
            while((count = imgByteStream.read(data)) != -1)
            {
                tarFileWriter.write(data);
            }
            
            tarFileWriter.flush();
            tarFileWriter.close();
        }
        catch(IOException ex)
        {
            System.out.println("Error saving image to .tar file");
            System.out.println(ex.toString());
            return null;
        }
        
        return imgTar;
    }
    
    /**
     * hasImage(imgName) uses the "docker images" command and examines the output to determine
     * if image exists within the systems local docker inventory.
     * 
     * @param imgName
     * @return true if image lies within local docker image repository and false if not
     */
    public boolean hasImage(Container content)
    {
        boolean foundImg = false;
        
        List<Image> images = dockerClient.listImagesCmd().withShowAll(Boolean.TRUE).exec();
        for(Image currImg: images)
        {
            if(currImg.getId().equals(content.getDockerID()))
            {
                foundImg = true;
            }
        }
        
        return foundImg;
    }
    
}
