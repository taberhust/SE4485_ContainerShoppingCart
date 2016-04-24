/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DaoImpl;

import DBConnection.DBConnection;
import Entity.Component;
import Entity.Components;
import Entity.ConfigCart;
import Entity.Configuration;
import Entity.Configurations;
import Entity.Container;
import java.sql.Connection;
import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author matt
 */
public class ConfigCartDaoImplTest {
    
    @Test
    public void testAddToConfigCart() throws Exception {
        Connection connection = DBConnection.getDataSource().getConnection();
        connection.setAutoCommit(false);
        
        ConfigCart configcart = new ConfigCart();
        configcart.setUserID(1234567L);
        configcart.setCartContainerID(999L);
        configcart.setDisplayName("testDisplay");
        configcart.setUserType("testType");
        configcart.setUserArg1("testArg1");
        configcart.setUserArg2("testArg2");
        
        ArrayList<Component> testArrayComponents = new ArrayList<>();
        Component componentOne = new Component();
        componentOne.setComponentID(2222L);
        componentOne.setImageID("imageOne");
        componentOne.setComponentName("compNameOne");
        componentOne.setComponentType("CompTypeOne");
        componentOne.setVersion("versionOne");
        testArrayComponents.add(componentOne);
        
        Component componentTwo = new Component();
        componentTwo.setComponentID(33333L);
        componentTwo.setImageID("imageTwo");
        componentTwo.setComponentName("compNameTwo");
        componentTwo.setComponentType("compTypeTwo");        
        componentTwo.setVersion("versionTwo");      
        testArrayComponents.add(componentTwo);
        
        Component componentThree = new Component();
        componentThree.setComponentID(4444L);
        componentThree.setImageID("imageThree");
        componentThree.setComponentName("compNameThree");
        componentThree.setComponentType("compTypeThree");
        componentThree.setVersion("versionThree");
        testArrayComponents.add(componentThree);
        
        ArrayList<Configuration> testArrayConfigurations = new ArrayList<>();
        Configuration configOne = new Configuration();
        configOne.setConfigurationID(5555L);
        configOne.setDisplayName("displayOne");      
        configOne.setDefaultType("ctONe");
        configOne.setDefaultArg1("OneArg1");
        configOne.setDefaultArg2("OneArg2");
        
        Configuration configTwo = new Configuration();
        testArrayConfigurations.add(configOne);
        configTwo.setConfigurationID(66666L);
        configTwo.setDisplayName("displaytwo");      
        configTwo.setDefaultType("ctTwo");
        configTwo.setDefaultArg1("TwoArg1");
        configTwo.setDefaultArg2("TwoArg2");
        testArrayConfigurations.add(configTwo);
        
        Configuration configThree = new Configuration();
        configThree.setConfigurationID(77777L);
        configThree.setDisplayName("displayThree");      
        configThree.setDefaultType("ctThree");
        configThree.setDefaultArg1("ThreeArg1");
        configThree.setDefaultArg2("ThreeArg2");         
        testArrayConfigurations.add(configThree);
        
        Container container = new Container();
        container.setContainerID(112L);
        container.setDockerID("TestDockerID");
        container.setContainerName("TestContainerName");
        container.setVersion("TestVersion");
        container.setPathToIcon("TestPath");
        container.setCategory("TestCatory");
        container.setProductFamily("TestProductFamily");
        container.setComponents(testArrayComponents);
        container.setConfigurations(testArrayConfigurations);
        
        Long id = 876L;
        ConfigCartDaoImpl instance = new ConfigCartDaoImpl();
        boolean result = instance.createConfigCart(connection, id, container);
        assertTrue(result);
                
        connection.rollback();
        connection.setAutoCommit(true);
    }
}
