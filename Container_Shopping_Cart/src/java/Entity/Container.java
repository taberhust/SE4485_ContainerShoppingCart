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
    private String id;
    private String name;
    private String version;
    private Image icon;
    private String category;
    private String productName;
    private ArrayList<Configuration> configs;
    private ArrayList<Component> comps;
    
    public void setID(String ID){
        this.id = ID;
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
        return id;
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
    
    }
    
    public void editConfig(int ID, Configuration configuration){
    
    }
    
//    public void editConfig(int Index, Configuration configuration){
//    
//    }
    
    public void removeConfig(int index){
    
    }
    
}
