/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.ArrayList;

/**
 *
 * @author matt
 */
public class Cart {
    private String userName;
    private ArrayList<Container> containers;
    
    public void setUserName(String UserName){
        this.userName = UserName;
    }
    
    public void addContainer(Container container){
      this.containers.add(container);
    }
    
    public String getUserName(){
        return userName;
    }
    
    public ArrayList retrieveContainers(){
        return containers;
    }
}
