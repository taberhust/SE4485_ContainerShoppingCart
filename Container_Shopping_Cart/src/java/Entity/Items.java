/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 * Purchase items object class
 * 
 * @author matt & kevin
 */
public class Items {
    private Long purchaseID;
    private Long containerID;    

    /**
     * Set purchase ID
     * 
     * @param purchaseID Purchase ID
     */
    public void setPurchaseID(Long purchaseID) {
        this.purchaseID = purchaseID;
    }

    /**
     * Set container ID
     * 
     * @param containerID Container ID
     */
    public void setContainerID(Long containerID) {
        this.containerID = containerID;
    }
    
    /**
     * Get purchase ID
     * 
     * @return Purchase ID
     */
    public Long getPurchaseID() {
        return purchaseID;
    }

    /**
     * Get container ID
     * 
     * @return Container ID
     */
    public Long getContainerID() {
        return containerID;
    }
    
}