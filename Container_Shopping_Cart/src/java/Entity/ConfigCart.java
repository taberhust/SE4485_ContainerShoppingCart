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
public class ConfigCart {
    private Long userID;
    private Long cartContainerID;
    private String userType;
    private String userArg1;
    private String userArg2;

    public void setUserID(Long userID){
        this.userID = userID;
    }
    
    public void setCartContainerID(Long cartContainerID){
        this.cartContainerID = cartContainerID;
    }
    
    public void setUserType(String userType){
        this.userType = userType;
    }
    
    public void setUserArg1(String userArg1){
        this.userArg1 = userArg1;
    }
    
    public void setUserArg2(String userArg2){
        this.userArg2 = userArg2;
    }
    
    public Long getUserID(){
        return userID;
    }

    public Long getCartContainerID(){
        return cartContainerID;
    }

    public String getUserType(){
        return userType;
    }

    public String getUserArg1(){
        return userArg1;
    }

    public String getUserArg2(){
        return userArg2;
    }    
    
    
}
