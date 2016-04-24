/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 * TESTING ENVIRONMENT OBJECT ONLY
 * Configurations class to work with weak entity of same name
 * 
 * @author matt & kevin
 */
public class Configurations {
    private Long containerID;
    private Long configurationID;
    
    /**
     * Set container ID
     * 
     * @param containerID Container ID
     */
    public void setContainerID(Long containerID) {
        this.containerID = containerID;
    }
    
    /**
     * Set configuration ID
     * 
     * @param configurationID Configuration ID
     */
    public void setConfigurationID(Long configurationID) {
        this.configurationID = configurationID;
    }    
    
    /**
     * Get container ID
     * 
     * @return Container ID
     */
    public Long getContainerID() {
        return containerID;
    }
        
    /**
     * Get configuration ID
     * 
     * @return Configuration ID
     */
    public Long getConfigurationID() {
        return configurationID;
    }
    
}