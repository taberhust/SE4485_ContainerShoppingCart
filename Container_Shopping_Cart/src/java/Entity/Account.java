/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 * Account object class
 * 
 * @author matt & kevin
 */
public class Account {
    private Long userID;
    private String userName;
    private String firstName;
    private String lastName;
    private String privilege;
            
    /**
     * Set user ID
     * 
     * @param userID ID to be set
     */
    public void setUserID(Long userID){
        this.userID = userID;
    }
    
    /**
     * Set username
     * 
     * @param username Username to set
     */
    public void setUserName(String username){
        this.userName = username;
    }
    
    /**
     * Set first name
     * 
     * @param firstname First name to set
     */
    public void setFirstName(String firstname){
        this.firstName = firstname;
    }
    
    /**
     * Set last name
     * 
     * @param lastname Last name to set
     */
    public void setLastName(String lastname){
        this.lastName = lastname;
    }
    
    /**
     * Set privilege
     * 
     * @param privilege Privilege to set
     */
    public void setPrivilege(String privilege){
        this.privilege = privilege;
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
     * Get username
     * 
     * @return Username
     */
    public String getUsername(){
        return userName;
    }
    
    /**
     * Get first name
     * 
     * @return First name
     */
    public String getFirstName(){
        return firstName;
    }
    
    /**
     * Get last name
     * 
     * @return Last name
     */
    public String getLastName(){
        return lastName;
    }
    
    /**
     * Get privilege
     * 
     * @return Privilege
     */
    public String getPrivilege(){
        return privilege;
    }
    
}