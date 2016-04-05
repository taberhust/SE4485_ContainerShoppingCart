/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author matt & kevin
 */
public class Items {
    private Long purchaseID;
    private Long containerID;    

    public void setPurchaseID(Long purchaseID) {
        this.purchaseID = purchaseID;
    }

    public void setContainerID(Long containerID) {
        this.containerID = containerID;
    }
    
    public Long getPurchaseID() {
        return purchaseID;
    }

    public Long getContainerID() {
        return containerID;
    }
    
}