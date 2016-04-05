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
 * @author matt & kevin
 */
public class Container {
    private Long containerID;
    private String dockerID;
    private String dockerName;
    private String containerName;
    private String pathToIcon;
    private String category;
    private String productName;
    private String version;
    private ArrayList<Component> components;
    private ArrayList<Configuration> configurations;
    
    public void setContainerID(Long id){
        this.containerID = id;
    }
    
    public void setDockerID(String dockerID){
        this.dockerID = dockerID;
    }

    public void setDockerName(String dockerName){
        this.dockerName = dockerName;
    }
    
    public void setContainerName(String containerName){
        this.containerName = containerName;
    }
    
    public void setVersion(String Version){
        this.version = Version;
    }
    
    public void setPathToIcon(String pathToIcon){
        this.pathToIcon = pathToIcon;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    public void setComponents(ArrayList<Component> components){
        this.components = components;
    }
            
    public void setConfigurations(ArrayList<Configuration> configurations) {
        this.configurations = configurations;
    }            
    
    public Long getContainerID(){
        return containerID;
    }
    
    public String getDockerID(){
        return dockerID;
    }
    
    public String getDockerName(){
        return dockerName;
    }
    
    public String getContainerName(){
        return containerName;
    }
    
    public String getVersion(){
        return version;
    }
    
    public String getPathToIcon(){
        return pathToIcon;
    }

    public String getCategory() {
        return category;
    }

    public String getProductName() {
        return productName;
    }
    
    public ArrayList<Component> getComponents() {
        return components;
    }

    public ArrayList<Configuration> getConfigurations() {
        return configurations;
    }
        
}