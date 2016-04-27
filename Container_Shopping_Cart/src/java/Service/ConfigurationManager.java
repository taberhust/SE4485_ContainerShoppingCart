package Service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.Locale;

import Entity.Container;
import Entity.Configuration;


/**
 *
 * @author taber
 */
public class ConfigurationManager {
    
    private static final String dir = "/" + System.getProperty("user.home") + "/ContainerShoppingCart";
    private static final String scriptDir = dir + "/Script";
    
    long userID;
    
    public ConfigurationManager()
    {
        userID = 0;
    }
    
    public ConfigurationManager(long userID)
    {
        this.userID = userID;
    }
    /**
     * Volume - a directory on the container that is shared with the host machine via a local dir.
     * 
     * @param containerDir
     * @param hostDir
     * @return 
     */
    public Configuration addVolume(String containerDir, String hostDir)
    {
        Configuration volume = new Configuration();
        volume.setDefaultType("VOLUME");
        volume.setDisplayName("");
        volume.setDefaultArg1(containerDir);
        volume.setDefaultArg2(hostDir);
        
        return volume;
    }
    
    /**
     * Command - simple command to be run in container
     * 
     * @param command
     * @return 
     */
    public Configuration addCommand(String command)
    {
        Configuration commandConfig = new Configuration();
        commandConfig.setDefaultType("CMD");
        commandConfig.setDisplayName("");
        commandConfig.setDefaultArg1(command);
        commandConfig.setDefaultArg2("");
        
        return commandConfig;
    }
    
    /**
     * EnvironmentVar - Environment Variable to be set within the container
     * 
     * @param var
     * @param value
     * @return 
     */
    public Configuration addEnvironmentVar(String var, String value)
    {
        Configuration environmentVar = new Configuration();
        environmentVar.setDefaultType("ENV");
        environmentVar.setDisplayName("");
        environmentVar.setDefaultArg1(var);
        environmentVar.setDefaultArg2(value);
        
        return environmentVar;
    }
    
    /**
     * Detached Mode - container will run in background
     * 
     * @return 
     */
    public Configuration setDetached()
    {
        Configuration mode = new Configuration();
        mode.setDefaultType("MODE");
        mode.setDisplayName("");
        mode.setDefaultArg1("-d");
        mode.setDefaultArg2("");
        
        return mode;
    }
    
    /**
     * Interactive Mode - Container will be run with a shell console to pop up
     * with which the user can interact
     * 
     * @return 
     */
    public Configuration setInteractive()
    {
        Configuration mode = new Configuration();
        mode.setDefaultType("MODE");
        mode.setDisplayName("");
        mode.setDefaultArg1("-i");
        mode.setDefaultArg2("");
        
        return mode;
    }
    
    /**
     * Expose Port - maps a container port to a host machine port
     * @param containerPort
     * @param hostPort
     * @return 
     */
    public Configuration exposePort(long configID, String containerPort, String hostPort)
    {
        Configuration port = new Configuration();
        port.setConfigurationID(configID);
        port.setDefaultType("EXPOSE");
        port.setDisplayName("");
        port.setDefaultArg1(containerPort);
        port.setDefaultArg2(hostPort);
        
        return port;
    }
    
    /**
     * Entry Point - The starting command for the container
     * ***This option should rarely be enabled for user configuration
     * 
     * @param command
     * @return 
     */
    public Configuration setEntryPoint(String command)
    {
        Configuration entryPoint = new Configuration();
        entryPoint.setDefaultType("ENTRYPOINT");
        entryPoint.setDisplayName("");
        entryPoint.setDefaultArg1(command);
        entryPoint.setDefaultArg2(null);
        
        return entryPoint;
    }
    
    /**
     * Work Dir - working directory of the container
     * 
     * @param dir
     * @return 
     */
    public Configuration setWorkDir(String dir)
    {
        Configuration workDir = new Configuration();
        workDir.setDefaultType("WORKDIR");
        workDir.setDisplayName("");
        workDir.setDefaultArg1(dir);
        workDir.setDefaultArg2(null);
        
        return workDir;
    }
    
    /**
     * User - the user that is set to run the commands within the container
     * 
     * @param user
     * @return 
     */
    public Configuration setUser(String user)
    {
        Configuration userConfig = new Configuration();
        userConfig.setDefaultType("USER");
        userConfig.setDisplayName("");
        userConfig.setDefaultArg1(user);
        userConfig.setDefaultArg2(null);
        
        return userConfig;
    }
    
    /**
     * Start-up Script - customized startup script for container with all its configuration flags set
     * 
     * This both works to create the container if it does not exist and to start container if it does exist
     * 
     * @param userID
     * @param myContainer
     * @return 
     */
    public File generateStartScript(long userID, Container myContainer)
    {
        Calendar date = Calendar.getInstance();
        File scriptFile = new File(scriptDir + "/start_" + myContainer.getContainerName() + "_UID_" + userID + "_PD_" + date.get(Calendar.YEAR) + "-" + date.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.US) + "-" + date.get(Calendar.DAY_OF_MONTH) + ".sh");
        
        boolean cmdSet = false;
        String cmd = "";
        
        Configuration currentConfig;
        
        String startupScript = "#!/bin/bash\n\n" +
                                "echo off\n" +
                                "containerExists=0\n" +
                                "commd='docker ps'\n" +
                                "indexSet=0\n" +
                                "index=0\n" +
                                "containerName='" + myContainer.getContainerName() + "_" + userID + "'\n" +
                                "count=0\n\n" +
                                "for output in $(docker ps -a)\n" +
                                "do\n" +
                                "	if [ indexSet = 0 ]; then\n" +
                                "		if [ \"$output\" = \"NAMES\" ]; then\n" +
                                "			indexSet=1	\n" +
                                "			index=$count\n" +
                                "			count=-1\n" +
                                "	 	fi\n" +
                                "	else\n" +
                                "		if [ $count = $index ]; then\n" +
                                "			if [ $output = $containerName ]; then\n" +
                                "				docker start $containerName\n" +
                                "				containerExists=1\n" +
                                "				\n" +
                                "			fi\n" +
                                "			count=-1\n" +
                                "		fi\n" +
                                "	fi	\n" +
                                "	count=$[$count +1]\n" +
                                "done\n" +
                                "\n" +
                                "if [ $containerExists = 0 ]; then\n" +
                                "       docker load < " + myContainer.getDockerName() + ".tar\n" +
                                "	docker run --name=$containerName ";
                                for(int i = 0; i < myContainer.getConfigurations().size(); i++)
                                {
                                    currentConfig = myContainer.getConfigurations().get(i);
                                    switch(currentConfig.getDefaultType())
                                    {
                                        case "EXPOSE":
                                            startupScript = startupScript.concat("-p " + currentConfig.getDefaultArg1() + ":" + currentConfig.getDefaultArg2() + " ");
                                            break;
                                        case "MODE":
                                            startupScript = startupScript.concat(currentConfig.getDefaultArg1() + " ");
                                            break;
                                        case "ENTRYPOINT":
                                            startupScript = startupScript.concat("--entrypoint=\"" + currentConfig.getDefaultArg1() + "\" ");
                                            break;
                                        case "CMD":
                                            cmdSet = true;
                                            cmd = currentConfig.getDefaultArg1();
                                            break;
                                        case "ENV":
                                            startupScript = startupScript.concat("-e " + currentConfig.getDefaultArg1() + "=" + currentConfig.getDefaultArg2() + " ");
                                            break;
                                        case "VOLUME":
                                            startupScript = startupScript.concat("-v " + currentConfig.getDefaultArg1() + ":" + currentConfig.getDefaultArg2() + " ");
                                            break;
                                        case "USER":
                                            startupScript = startupScript.concat("-u=\"" + currentConfig.getDefaultArg1() + "\" ");
                                            break;
                                        case "WORKDIR":
                                            startupScript = startupScript.concat("-w=\"" + currentConfig.getDefaultArg1() + "\" ");
                                            break;
                                        default:
                                    }
                                }
                                startupScript = startupScript.concat(myContainer.getDockerName());
                                if(cmdSet)
                                {
                                    startupScript = startupScript.concat(" \"" + cmd + "\"\n");
                                }
                                else
                                    startupScript = startupScript.concat("\n");
                                startupScript = startupScript.concat("fi");
        
        try{
            scriptFile.createNewFile();
            FileOutputStream writeFile = new FileOutputStream(scriptFile);
            writeFile.write(startupScript.getBytes());
        }
        catch(Exception ex)
        {
            System.out.println("ERROR: Error writing script file.");
            System.out.println(ex.toString());
        }
                                
        return scriptFile;
    }
}
