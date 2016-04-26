/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.ArrayList;

/**
 * Container object class
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
    private String productFamily;
    private String version;
    private ArrayList<Component> components;
    private ArrayList<Configuration> configurations;
    
    /**
     * Set container ID
     * 
     * @param id Container ID
     */
    public void setContainerID(Long id){
        this.containerID = id;
    }
    
    /**
     * Set docker ID
     * 
     * @param dockerID Docker ID
     */
    public void setDockerID(String dockerID){
        this.dockerID = dockerID;
    }

    /**
     * Set docker name
     * 
     * @param dockerName Docker name
     */
    public void setDockerName(String dockerName){
        this.dockerName = dockerName;
    }
    
    /**
     * Set container name
     * 
     * @param containerName Container name
     */
    public void setContainerName(String containerName){
        this.containerName = containerName;
    }
    
    /**
     * Set version
     * 
     * @param Version Version
     */
    public void setVersion(String Version){
        this.version = Version;
    }
    
    /**
     * Set the path to the icon
     * 
     * @param pathToIcon Path to the icon
     */
    public void setPathToIcon(String pathToIcon){
        this.pathToIcon = pathToIcon;
    }

    /**
     * Set category
     * 
     * @param category Category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Set product family
     * 
     * @param productFamily Product family
     */
    public void setProductFamily(String productFamily) {
        this.productFamily = productFamily;
    }
    
    /**
     * Set ArrayList of components
     * 
     * @param components ArrayList of components
     */
    public void setComponents(ArrayList<Component> components){
        this.components = components;
    }
    
    /**
     * Set ArrayList of configurations
     * 
     * @param configurations ArrayList of configurations
     */
    public void setConfigurations(ArrayList<Configuration> configurations) {
        this.configurations = configurations;
    }            
    
    /**
     * Add configuration
     * 
     * @param configuration Configuration
     */
    public void addConfiguration(Configuration configuration){
        this.configurations.add(configuration);
    }
    
    /**
     * Get container ID
     * 
     * @return Container ID
     */
    public Long getContainerID(){
        return containerID;
    }
    
    /**
     * Get docker ID
     * 
     * @return Docker ID
     */
    public String getDockerID(){
        return dockerID;
    }
    
    /**
     * Get docker name
     * 
     * @return Docker name
     */
    public String getDockerName(){
        return dockerName;
    }
    
    /**
     * Get container name
     * 
     * @return Container name
     */
    public String getContainerName(){
        return containerName;
    }
    
    /**
     * Get version
     * 
     * @return Version
     */
    public String getVersion(){
        return version;
    }
    
    /**
     * Get path to the icon
     * 
     * @return Path to the icon
     */
    public String getPathToIcon(){
        return pathToIcon;
    }

    /**
     * Get category
     * 
     * @return Category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Get product family
     * 
     * @return Product family
     */
    public String getProductFamily() {
        return productFamily;
    }
    
    /**
     * Get ArrayList of components
     * 
     * @return ArrayList of components
     */
    public ArrayList<Component> getComponents() {
        return components;
    }

    /**
     * Get ArrayList of configurations
     * 
     * @return ArrayList of configurations
     */
    public ArrayList<Configuration> getConfigurations() {
        return configurations;
    }
        
}