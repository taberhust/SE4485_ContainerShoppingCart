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
    
    private String userName;
    private String firstName;
    private String lastName;
    private String privilege;
            
    //Is this the same as email?  Should we only call it one thing to avoid confusion?
    public void setUserName(String username){
        this.userName = username;
    }
    
    public void setFirstName(String firstname){
        this.firstName = firstname;
    }
    
    public void setLastName(String lastname){
        this.lastName = lastname;
    }
    
    //Probable need a setter and getter for privilege
    //Also wouldn't this be better as an int, or is it just admin/user
    public void setPrivilege(String Privilege){
        this.privilege = Privilege;
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
    
    //Here is the getter for privilege mentioned earlier
    public String getPrivilege(){
        return privilege;
    }
}
