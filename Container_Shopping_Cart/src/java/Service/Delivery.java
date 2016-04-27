package Service;


import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.PrintStream;

import org.xeustechnologies.jtar.TarOutputStream;
import org.xeustechnologies.jtar.TarEntry;

import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Properties;

import java.io.IOException;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelShell;

import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Image;
import com.github.dockerjava.core.command.PullImageResultCallback;

import Entity.Container;

/**
 *
 * @author taber
 */
public class Delivery {
    
    private static final String dir = System.getProperty("user.home") + "/ContainerShoppingCart";
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
    
    public boolean deliverViaSSH(long userID, ArrayList<Container> cart, String hostAddress, int port, String userName, String password, String destinationDir){
        boolean status = false;
        
        Properties sshConfig = new Properties();
        
        sshConfig.put("StrictHostKeyChecking","no");
        
        JSch javaSSH = new JSch();
        
        try{
            Session sshSession = javaSSH.getSession(userName, hostAddress, port);

            sshSession.setPassword(password);
            sshSession.setConfig(sshConfig);
            
            sshSession.connect(10000);
            
            System.out.println("Code successfully connected to ssh port");
            
            /*if(deployContainerViaSSH(sshSession, destinationDir, userID, cart))
            {
                status = true;
            }*/
            if(uploadPackageViaSSH(sshSession, destinationDir, userID, cart) != null)
            {
                status = true;
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex.toString());
        }
        
        return status;
    }
    
    public boolean deliverViaFTP(){
        boolean status = false;
        
        //ftp logic
        
        return status;
    }
    
    private String uploadPackageViaSSH(Session sshSession, String destinationDir, long userID, ArrayList<Container> cart) throws Exception
    {
        String fileName = null;
        
        Channel channel = sshSession.openChannel("sftp");
        channel.connect();

        ChannelSftp sftpChannel = (ChannelSftp)channel;

        File deliveryPckg = generateDeliveryPackage(userID, cart);
        sftpChannel.cd(destinationDir);
        
        System.out.println("Completed deliveryPckg generation.");
        System.out.println("File name is " + deliveryPckg.getName());

        sftpChannel.put(new FileInputStream(deliveryPckg),deliveryPckg.getName());
        
        /*InputStream response = sftpChannel.getInputStream();
        byte[] data = new byte[100];
        int count;
        System.out.println("The system has begun to read a response.");
        while((count = response.read(data)) != -1)
        {
            System.out.print(new String(data));
        }
        */
        //If code reaches this point without exception being thrown, succeeded
        sftpChannel.disconnect();
        sshSession.disconnect();
        fileName = deliveryPckg.getName();
        
        return fileName;
    }
    
    private boolean deployContainerViaSSH(Session sshSession, String destinationDir, long userID, ArrayList<Container> cart) throws Exception
    {
        boolean status = false;
        String fileName;
        
        if((fileName = uploadPackageViaSSH(sshSession, destinationDir, userID, cart)) != null)
        {
            Channel channel = sshSession.openChannel("shell");
                        
            ChannelShell shellChannel = (ChannelShell)channel;
            
            OutputStream shellOut = shellChannel.getOutputStream();
            PrintStream ps = new PrintStream(shellOut, true);
            
            channel.connect();
            InputStream shellResponse = shellChannel.getInputStream();
            
            ps.println("tar -xvf " + fileName);
            
        }
        
        return status;
    }
    
    private File generateDeliveryPackage(long userID, ArrayList<Container> items)
    {
        String scriptUniqueFileName;
        ArrayList<File> pckgContents = new ArrayList<>();
        ConfigurationManager configMgr = new ConfigurationManager(userID);
        Calendar date = Calendar.getInstance();
        File deliveryPckg = new File(deliveryDir + "/" + userID + "_" + date.get(Calendar.YEAR) + "-" + date.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.US) + "-" + date.get(Calendar.DAY_OF_MONTH) + date.get(Calendar.HOUR_OF_DAY) + "-" + date.get(Calendar.MINUTE) + "-" + date.get(Calendar.SECOND) + ".tar");
        
        int currIndex = 0;
        
        for(int i = 0; i < items.size(); i++)
        {
            //Add image tar and script to pckgContents
            pckgContents.add(new File(imgDir + "/" + items.get(i).getDockerName() + ".tar"));
            if(!pckgContents.get(i).exists())
            {
                if(!hasImage(items.get(i)))
                {
                    try{
                        pullImage(items.get(i));
                    }
                    catch(Exception ex)
                    {
                        System.out.println("Unable to pull image for " + items.get(i).getDockerName());
                        System.out.println(ex.toString());
                    }
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
                if(item.getName().contains(".sh"))
                {
                    item.setExecutable(true);
                    scriptUniqueFileName = item.getName();
                    tarOut.putNextEntry(new TarEntry(item, /*item.getName()*/scriptUniqueFileName.substring(0, scriptUniqueFileName.indexOf("_UID_")) + ".sh"));
                }
                else
                {
                    tarOut.putNextEntry(new TarEntry(item, item.getName()));
                }
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
    
    private void pullImage(Container content) throws Exception
    {
        String name = content.getDockerName();
        String repository = name.substring(0, name.indexOf(":"));
        String tag = name.substring(name.indexOf(":") + 1);
        
        //Execute pull for image respository:tag and await success before returning
        PullImageResultCallback pullResult = dockerClient.pullImageCmd(repository).withTag(tag).exec(new PullImageResultCallback());
        pullResult.awaitSuccess();
    }
    
    private File saveImage(Container content)
    {
        String name = content.getDockerName().substring(0, content.getDockerName().indexOf(":"));
        String tag = content.getDockerName().substring(content.getDockerName().indexOf(":") + 1);
        
        File imgTar = new File (imgDir + "/" + content.getDockerName() + ".tar");
        //byte[] buffer = new byte[100];
        //int bytesRead = 0;
        
        try{
            //Set "docker save" command to the process
            
            //*****************************************************************
            //FUTUTE DEVELOPERS OF THIS CODE READ THIS
            //After days of attempting to figure out an error with the java-docker client's SaveImage function
            //that causes a leak of data into the .tar file that impedes the ability to load the image,
            //I was forced to simply execute command line execution of the docker save command
            
            //The code for implementing docker-java functions for the save are below
            //Feel free to attempt where I failed
            /*
            InputStream imgByteStream = this.dockerClient.saveImageCmd(name).withTag(tag).exec();
            FileOutputStream tarFileWriter = new FileOutputStream(imgTar);
            
            int count;
            byte[] data = new byte[100];
            
            while((count = imgByteStream.read(data)) != -1)
            {
                tarFileWriter.write(data);
            }
            
            tarFileWriter.flush();
            tarFileWriter.close();
            */
            
            //System.out.println("Command to be executed: " + cmd);
            String cmd = "docker save --output=" + imgDir + "/" + content.getDockerName() + ".tar " + content.getDockerName();
            Process saveProcess = Runtime.getRuntime().exec(cmd);
            /*
            InputStream procOut = saveProcess.getErrorStream();
            int procCount = 0;
            byte[] procOutByte = new byte[100];
            while((procCount = procOut.read(procOutByte)) != -1)
            {
                System.out.print(new String(procOutByte));
            }
            */
            try{
                int returnVal = saveProcess.waitFor();
                if(returnVal != 0)
                {
                    System.out.println("Saving process failed for image.");
                }
            }
            catch(Exception ex)
            {
                System.out.println("FATAL: Saving process was interrupted.");
                System.out.println(ex.toString());
            }
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
