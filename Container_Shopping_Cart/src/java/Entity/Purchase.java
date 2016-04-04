/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author matt
 */
public class Purchase {
    private Long purchaseID;
    private Long userID;
    private Date timeOfPurchase;
    private ArrayList<Container> items;
    
    public void setPurchaseID(Long purchaseID){
        this.purchaseID = purchaseID;
    }
    
    public void setUserID(Long userID){
        this.userID = userID;
    }
    
    public void setTimeOfPurchase(Date timeOfPurchase){
        this.timeOfPurchase = timeOfPurchase;
    }
    
    public void setItems(ArrayList<Container> items){
        this.items = items;
    }
    
    public Long getPurchaseID(){
        return purchaseID;
    }
    
    public Long getUserID(){
        return userID;
    }
    
    public Date getTimeOfPurchase(){
        return timeOfPurchase;
    }
    
    public ArrayList<Container> getItems(){
        return items;
    }
    
}