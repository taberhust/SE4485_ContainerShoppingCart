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
    private String fileName;
    private Long lineNumber;
    private String displayName;
    private String defaultValue;
    private String configValue;
    
    public void setConfigurationID(Long configurationID){
        this.configurationID = configurationID;
    }
    
    public void setFileName(String fileName){
        this.fileName = fileName;
    }
    
    public void setLineNumber(Long lineNumber){
        this.lineNumber = lineNumber;
    }
    
    public void setDisplayName(String displayName){
        this.displayName = displayName;
    }
    
    public void setDefaultValue(String defaultName){
        this.defaultValue = defaultName;
    }
    
    public void setConfigValue(String configValue){
        this.configValue = configValue;
    }
    
    public Long getConfigurationID(){
        return configurationID;
    }
    
    public String getFileName(){
        return fileName;
    }
    
    public Long getLineNumber(){
        return lineNumber;
    }
    
    public String getDisplayName(){
        return displayName;
    }
    
    public String getDefaultValue(){
        return defaultValue;
    }
    
    public String getConfigValue(){
        return configValue;
    }
    
}