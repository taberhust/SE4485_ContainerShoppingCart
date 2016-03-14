/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.awt.Image;
import java.util.ArrayList;

/**
 *
 * @author matt
 */
public class Container {
    private String containerID;
    private String name;
    private String version;
    private Image icon;
    private String category;
    private String productName;
    private ArrayList<Configuration> configs;
    private ArrayList<Component> comps;
    
    public void setID(String ID){
        this.containerID = ID;
    }
    
    public void setName(String Name){
        this.name = Name;
    }
    
    public void setVersion(String Version){
        this.version = Version;
    }
    
    public void setIcon(Image Icon){
        this.icon = Icon;
    }
    
    public String getID(){
        return containerID;
    }
    
    public String getName(){
        return name;
    }
    
    public String getVersion(){
        return version;
    }
    
    public Image getIcon(){
        return icon;
    }
    
    //--------------Below are the configuration and component methods
    public void addConfig(Configuration configuration){
        this.configs.add(configuration);
    }
    
    public void editConfig(int ID, Configuration configuration){
        //Is there a way to replace the old configuration with the new configuration
    }
    
//    public void editConfig(int Index, Configuration configuration){
//    
//    }
    
    public void removeConfig(int index){
        
    }
    
}
