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
public class Account {
    private Long userID;
    private String userName;
    private String firstName;
    private String lastName;
    private String privilege;
            
    public void setUserID(Long userID){
        this.userID = userID;
    }
    
    public void setUserName(String username){
        this.userName = username;
    }
    
    public void setFirstName(String firstname){
        this.firstName = firstname;
    }
    
    public void setLastName(String lastname){
        this.lastName = lastname;
    }
    
    public void setPrivilege(String privilege){
        this.privilege = privilege;
    }
    
    public Long getUserID(){
        return userID;
    }
    
    public String getUsername(){
        return userName;
    }
    
    public String getFirstName(){
        return firstName;
    }
    
    public String getLastName(){
        return lastName;
    }
    
    public String getPrivilege(){
        return privilege;
    }
    
}