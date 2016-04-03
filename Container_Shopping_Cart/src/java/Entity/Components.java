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
public class Components {
    private Long containerID;
    private Long componentID;

    public void setContainerID(Long containerID) {
        this.containerID = containerID;
    }
    
    public void setComponentID(Long componentID) {
        this.componentID = componentID;
    }
    
    public Long getContainerID() {
        return containerID;
    }
    
    public Long getComponentID() {
        return componentID;
    }
    
}