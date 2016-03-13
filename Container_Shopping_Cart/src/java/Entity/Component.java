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
public class Component {
    private String imageID;
    private String name;
    private String type;
    private String version;
    
    public void setImage(String ImageID){
        this.imageID = ImageID;
    }
    
    public void setName(String Name){
        this.name = Name;
    }
    
    public void setType(String Type){
        this.type = Type;
    }
    
    public void setVersion(String Version){
        this.version = Version;
    }
    
    public String getImage(){
        return imageID;
    }
    
    public String getName(){
        return name;
    }
    
    public String getType(){
        return type;
    }
    
    public String getVersion(){
        return version;
    }       
}
