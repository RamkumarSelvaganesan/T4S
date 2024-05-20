package com.TOS.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesRead {
	
	 private static Properties properties;
	 public static String propertyFilePath = "src/main/resources/Config.properties";
	
	    public static String getProperties(String key) {
	    	 properties = new Properties();
		        try {
		        	
		            FileInputStream fis = new FileInputStream(propertyFilePath);
		            properties.load(fis);
		            fis.close();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
	        return properties.getProperty(key);
	    }

}
