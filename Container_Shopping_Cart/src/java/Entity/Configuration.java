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
public class Configuration {
    private Long configurationID;
    private String displayName;
    private String defaultType;
    private String defaultArg1;
    private String defaultArg2;
    
    public void setConfigurationID(Long configurationID){
        this.configurationID = configurationID;
    }
    
    public void setDisplayName(String displayName){
        this.displayName = displayName;
    }
    
    public void setDefaultType(String defaultType){
        this.defaultType = defaultType;
    }
    
    public void setDefaultArg1(String defaultArg1){
        this.defaultArg1 = defaultArg1;
    }
        
    public void setDefaultArg2(String defaultArg2){
        this.defaultArg2 = defaultArg2;
    }
    
    public Long getConfigurationID(){
        return configurationID;
    }
        
    public String getDisplayName(){
        return displayName;
    }
    
    public String getDefaultType(){
        return defaultType;
    }
    
    public String getDefaultArg1(){
        return defaultArg1;
    }
    
    public String getDefaultArg2(){
        return defaultArg2;
    }
    
}