package Service;

import java.io.File;

import Entity.Configuration;
import Entity.Container;

/**
 *
 * @author taber
 */
public class ConfigurationManager {
    
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
        commandConfig.setDefaultArg2(command);
        
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
        mode.setDefaultArg1("-d");
        
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
        mode.setDefaultArg1("-i");
        
        return mode;
    }
    
    /**
     * Expose Port - maps a container port to a host machine port
     * @param containerPort
     * @param hostPort
     * @return 
     */
    public Configuration exposePort(String containerPort, String hostPort)
    {
        Configuration port = new Configuration();
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
        userConfig.setDefaultArg1(user);
        userConfig.setDefaultArg2(null);
        
        return userConfig;
    }
    
    /**
     * Start-up Script - customized startup script for container with all its configuration flags set
     * 
     * This both works to create the container if it does not exist and to start container if it does exist
     * 
     * @param myContainer
     * @return 
     */
    public String generateStartScript(Container myContainer)
    {
        boolean cmdSet = false;
        String cmd = "";
        
        Configuration currentConfig;
        
        String startupScript = "#!/bin/bash\n\n" +
                                "echo off\n" +
                                "containerExists=0\n" +
                                "commd='docker ps'\n" +
                                "indexSet=0\n" +
                                "index=0\n" +
                                "containerName='" + myContainer.getContainerName() + "_" + myContainer.getVersion() + "\n" +
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
                                "       docker load < " + myContainer.getDockerName() + ".tar" +
                                "	docker run --name=$containerName ";
                                for(int i = 0; i < myContainer.getConfigurations().size(); i++)
                                {
                                    currentConfig = myContainer.getConfigurations().get(i);
                                    switch(currentConfig.getDefaultType())
                                    {
                                        case "EXPOSE":
                                            startupScript = startupScript.concat("-p " + currentConfig.getDefaultArg1() + ":" + currentConfig.getDefaultArg2() + " ");
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
                                    startupScript = startupScript.concat(" " + cmd + "\n");
                                }
                                else
                                    startupScript = startupScript.concat("\n");
                                startupScript = startupScript.concat("fi");
        return startupScript;
    }
}
