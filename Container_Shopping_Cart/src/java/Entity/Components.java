/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 * TESTING ENVIRONMENT OBJECT ONLY
 * Components object class to work with weak entity table of same name
 * 
 * @author matt & kevin
 */
public class Components {
    private Long containerID;
    private Long componentID;

    /**
     * Set container ID
     * 
     * @param containerID Container ID
     */
    public void setContainerID(Long containerID) {
        this.containerID = containerID;
    }
    
    /**
     * Set component ID
     * 
     * @param componentID Component ID
     */
    public void setComponentID(Long componentID) {
        this.componentID = componentID;
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
     * Get component ID
     * 
     * @return Component ID
     */
    public Long getComponentID() {
        return componentID;
    }
    
}