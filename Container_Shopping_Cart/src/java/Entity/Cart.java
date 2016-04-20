/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.ArrayList;

/**
 *
 * @author matt & kevin
 */
public class Cart {
    private Long userID;
    private ArrayList<Container> containers;
    private Long cartContainerID;

    public void setUserID(Long userID) {
        this.userID = userID;
    }
    
    public void setCartContainerID(Long cartContainerID) {
        this.cartContainerID = cartContainerID;
    }
    
    public void addContainer(Container container){
        this.containers.add(container);
    }
    
    public Long getUserID() {
        return userID;
    }
    
    public Long getCartContainerID() {
        return cartContainerID;
    }
   
    public ArrayList<Container> getContainer(){
        return containers;
    }
}