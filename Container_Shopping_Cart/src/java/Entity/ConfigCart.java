/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 * ConfigCart object class to store user configurations
 * 
 * @author matt & kevin
 */
public class ConfigCart {
    private Long userID;
    private Long cartContainerID;
    private String displayName;
    private String userType;
    private String userArg1;
    private String userArg2;

    /**
     * Set user ID
     * 
     * @param userID User ID
     */
    public void setUserID(Long userID){
        this.userID = userID;
    }
    
    /**
     * Set container ID
     * 
     * @param cartContainerID Container ID
     */
    public void setCartContainerID(Long cartContainerID){
        this.cartContainerID = cartContainerID;
    }
    
    /**
     * Set display name
     * 
     * @param displayName Display name
     */
    public void setDisplayName(String displayName){
        this.displayName = displayName;
    }
    
    /**
     * Set user type
     * 
     * @param userType User type 
     */
    public void setUserType(String userType){
        this.userType = userType;
    }
    
    /**
     * Set user arg1
     * 
     * @param userArg1 user arg1
     */
    public void setUserArg1(String userArg1){
        this.userArg1 = userArg1;
    }
    
    /**
     * Set user arg2
     * 
     * @param userArg2 User arg2
     */
    public void setUserArg2(String userArg2){
        this.userArg2 = userArg2;
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
     * Get cart container ID
     * 
     * @return Cart container ID
     */
    public Long getCartContainerID(){
        return cartContainerID;
    }

    /**
     * Get display name
     * 
     * @return Display name
     */
    public String getDisplayName(){
        return displayName;
    }
    
    /**
     * Get user type
     * 
     * @return User type
     */
    public String getUserType(){
        return userType;
    }

    /**
     * Get user arg1
     * 
     * @return User arg1
     */
    public String getUserArg1(){
        return userArg1;
    }

    /**
     * Get user arg2
     * 
     * @return User arg2
     */
    public String getUserArg2(){
        return userArg2;
    }    
    
    
}
