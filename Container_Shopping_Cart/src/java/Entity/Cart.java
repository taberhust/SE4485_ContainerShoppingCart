/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.ArrayList;

/**
 * Cart object class
 * 
 * @author matt & kevin
 */
public class Cart {
    private Long userID;
    private ArrayList<Container> containers;
    private Long cartContainerID;

    /**
     * Set user ID
     * 
     * @param userID User ID to be set
     */
    public void setUserID(Long userID) {
        this.userID = userID;
    }
    
    /**
     * Set cart container ID
     * 
     * @param cartContainerID Cart container ID to set
     */
    public void setCartContainerID(Long cartContainerID) {
        this.cartContainerID = cartContainerID;
    }
    
    /**
     * Add container to ArrayList
     * 
     * @param container Container to add
     */
    public void addContainer(Container container){
        this.containers.add(container);
    }
    
    /**
     * Get User ID
     * 
     * @return user ID
     */
    public Long getUserID() {
        return userID;
    }
    
    /**
     * Get cart container ID
     * 
     * @return Cart container ID
     */
    public Long getCartContainerID() {
        return cartContainerID;
    }
   
    /**
     * Get container ArrayList
     * 
     * @return ArrayList of containers
     */
    public ArrayList<Container> getContainer(){
        return containers;
    }
}