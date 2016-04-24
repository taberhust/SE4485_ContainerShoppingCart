/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 * Component object class
 * 
 * @author matt & kevin
 */
public class Component {
    private Long componentID;
    private String imageID;
    private String componentName;
    private String componentType;
    private String version;
    
    /**
     * Set component ID
     * 
     * @param componentID Component ID
     */
    public void setComponentID(Long componentID){
        this.componentID = componentID;
    }
    
    /**
     * Set image ID
     * 
     * @param imageID Image ID
     */
    public void setImageID(String imageID){
        this.imageID = imageID;
    }
    
    /**
     * Set component name
     * 
     * @param componentName Component Name
     */
    public void setComponentName(String componentName){
        this.componentName = componentName;
    }
    
    /**
     * Set component type
     * 
     * @param componentType Component type
     */
    public void setComponentType(String componentType){
        this.componentType = componentType;
    }
    
    /**
     * Set version
     * 
     * @param version Version
     */
    public void setVersion(String version){
        this.version = version;
    }
    
    /**
     * Get component ID
     * 
     * @return Component ID
     */
    public Long getComponentID(){
        return componentID;
    }
    
    /**
     * Get image ID
     * 
     * @return Image ID 
     */
    public String getImageID(){
        return imageID;
    }
    
    /**
     * Get component name
     * 
     * @return Component name
     */
    public String getComponentName(){
        return componentName;
    }
    
    /**
     * Get component type
     * 
     * @return Component type
     */
    public String getComponentType(){
        return componentType;
    }
    
    /**
     * Get version
     * 
     * @return Version
     */
    public String getVersion(){
        return version;
    } 
    
}