/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.sql.Date;
import java.util.ArrayList;

/**
 * Purchase object class
 * 
 * @author matt & kevin
 */
public class Purchase {
    private Long purchaseID;
    private Long userID;
    private Date timeOfPurchase;
    private ArrayList<Container> items;
    
    /**
     * Set purchase ID
     * 
     * @param purchaseID Purchase ID
     */
    public void setPurchaseID(Long purchaseID){
        this.purchaseID = purchaseID;
    }
    
    /**
     * Set user ID
     * 
     * @param userID User ID
     */
    public void setUserID(Long userID){
        this.userID = userID;
    }
    
    /**
     * Set time of purchase
     * 
     * @param timeOfPurchase Time of purchase
     */
    public void setTimeOfPurchase(Date timeOfPurchase){
        this.timeOfPurchase = timeOfPurchase;
    }
    
    /**
     * Set ArrayList of purchase items
     * 
     * @param items ArrayList of purchase items
     */
    public void setItems(ArrayList<Container> items){
        this.items = items;
    }
    
    /**
     * Get purchase ID
     * 
     * @return Purchase ID
     */
    public Long getPurchaseID(){
        return purchaseID;
    }
    
    /**
     * Get user ID
     * 
     * @return User ID
     */
    public Long getUserID(){
        return userID;
    }
    
    /**
     * Get time of purchase
     * 
     * @return Time of purchase
     */
    public Date getTimeOfPurchase(){
        return timeOfPurchase;
    }
    
    /**
     * Get ArrayList of items in the purchase
     * 
     * @return ArrayList of purchase items
     */
    public ArrayList<Container> getItems(){
        return items;
    }
    
    /**
     * Add container as an item in the purchase
     * 
     * @param container Container to add to the purchase item list
     */
    public void addItem(Container container){
        this.items.add(container);
    }
    
    /**
     * Remove container as an item from the purchase
     * 
     * @param containerID Container to remove from the purchase item list
     */
    public void removeItem(Long containerID) {
        for (int i = 0; i < this.items.size(); i++) {
            if(containerID == items.get(i).getContainerID()) {
                items.remove(i);
            }
        }
    }
}