/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 * Configuration object class
 * 
 * @author matt & kevin
 */
public class Configuration {
    private Long configurationID;
    private String displayName;
    private String defaultType;
    private String defaultArg1;
    private String defaultArg2;
    
    /**
     * Set configuration ID
     * 
     * @param configurationID Configuration ID
     */
    public void setConfigurationID(Long configurationID){
        this.configurationID = configurationID;
    }
    
    /**
     * Set display name
     * 
     * @param displayName Display name
     */
    public void setDisplayName(String displayName){
        this.displayName = displayName;
    }
    
    /**
     * Set default type
     * 
     * @param defaultType Default type
     */
    public void setDefaultType(String defaultType){
        this.defaultType = defaultType;
    }
    
    /**
     * Set default arg1
     * 
     * @param defaultArg1 Default arg1 
     */
    public void setDefaultArg1(String defaultArg1){
        this.defaultArg1 = defaultArg1;
    }
        
    /**
     * Set default arg2
     * 
     * @param defaultArg2 Default arg2
     */
    public void setDefaultArg2(String defaultArg2){
        this.defaultArg2 = defaultArg2;
    }
    
    /**
     * Get configuration ID
     * 
     * @return Configuratoin ID
     */
    public Long getConfigurationID(){
        return configurationID;
    }
     
    /**
     * Get display name
     * 
     * @return Display name
     */
    public String getDisplayName(){
        return displayName;
    }
    
    /**
     * Get default type
     * 
     * @return Default type
     */
    public String getDefaultType(){
        return defaultType;
    }
    
    /**
     * Get default arg1
     * 
     * @return Default arg1
     */
    public String getDefaultArg1(){
        return defaultArg1;
    }
    
    /**
     * Get default arg2
     * 
     * @return Default arg2
     */
    public String getDefaultArg2(){
        return defaultArg2;
    }
    
}