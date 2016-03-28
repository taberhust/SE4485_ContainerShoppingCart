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
    private int id;
    private String fileName;
    private int lineNumber;
    private String displayName;
    private String defaultValue;
    private String value;
    
    public void setID(int ID){
        this.id = ID;
    }
    
    public void setFileName(String FileName){
        this.fileName = FileName;
    }
    
    public void setLineNumber(int LineNumber){
        this.lineNumber = LineNumber;
    }
    
    public void setDisplayName(String DisplayName){
        this.displayName = DisplayName;
    }
    
    public void setDefaultValue(String DefaultName){
        this.defaultValue = DefaultName;
    }
    
    public void setValue(String Value){
        this.value = Value;
    }
    
    public int getID(){
        return id;
    }
    
    public String getFileName(){
        return fileName;
    }
    
    public int getLineNumber(){
        return lineNumber;
    }
    
    public String getDisplayName(){
        return displayName;
    }
    
    public String getDefaultValue(){
        return defaultValue;
    }
    
    public String getValue(){
        return value;
    }
    
}
