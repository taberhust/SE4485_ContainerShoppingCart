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
    private Long componentID;
    private String imageID;
    private String componentName;
    private String componentType;
    private String version;
    
    public void setComponentID(Long componentID){
        this.componentID = componentID;
    }
    
    public void setImageID(String imageID){
        this.imageID = imageID;
    }
    
    public void setComponentName(String componentName){
        this.componentName = componentName;
    }
    
    public void setComponentType(String componentType){
        this.componentType = componentType;
    }
    
    public void setVersion(String version){
        this.version = version;
    }
    
    public Long getComponentID(){
        return componentID;
    }
    
    public String getImageID(){
        return imageID;
    }
    
    public String getComponentName(){
        return componentName;
    }
    
    public String getComponentType(){
        return componentType;
    }
    
    public String getVersion(){
        return version;
    } 
    
}