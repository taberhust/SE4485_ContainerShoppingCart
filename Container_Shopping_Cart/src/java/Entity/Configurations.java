/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author matt
 */
public class Configurations {
    private Long containerID;
    private Long configurationID;
    
    public void setContainerID(Long containerID) {
        this.containerID = containerID;
    }
    
    public void setConfigurationID(Long configurationID) {
        this.configurationID = configurationID;
    }    
    
    public Long getContainerID() {
        return containerID;
    }
        
    public Long getConfigurationID() {
        return configurationID;
    }
    
}