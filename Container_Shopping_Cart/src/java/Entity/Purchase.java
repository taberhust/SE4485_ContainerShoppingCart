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
    private int purchaseID;
    private String userName;
    private Date timeOfPurchase;
    private ArrayList<Container> items;
    
    public void setPurchaseID(int PurchaseID){
        this.purchaseID = PurchaseID;
    }
    
    public void setUserName(String UserName){
        this.userName = UserName;
    }
    
    public void setTimeOfPurchase(Date TimeOfPurchase){
        this.timeOfPurchase = TimeOfPurchase;
    }
    
    public void setItems(ArrayList Items){
        this.items = Items;
    }
    
    //*****************************
    //NEEDS WORKED ON
    public void addItem(){}
    
    public int getPurchaseID(){
        return purchaseID;
    }
    
    public String getUserName(){
        return userName;
    }
    
    public Date getTimeOfPurchase(){
        return timeOfPurchase;
    }
    
    public ArrayList<Container> getItems(){
        return items;
    }
}
