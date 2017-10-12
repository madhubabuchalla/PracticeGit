package com.schneider.windchillaccessrightsvalidation.utilities;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertyUtil {
	
	public static String foderPath = "C:\\Users\\SESA439753\\Desktop\\";
	
	public String runTimeURL = null;
	public String runTimeBrowser  = null;
	public String runTimeRole  = null;
	public String runTimeRow = null;
	
	
	public PropertyUtil() {
		try{
		FileInputStream fis = new FileInputStream(foderPath +"Runtime Parameters.properties");
		Properties Environment_property = new Properties();
		Environment_property.load(fis);
		
		runTimeURL= Environment_property.getProperty("URL");
		runTimeBrowser = Environment_property.getProperty("Browser");
		runTimeRole = Environment_property.getProperty("Role");
		runTimeRow= Environment_property.getProperty("RowNumber");
		
	}catch(Exception e){
		System.out.println(e);
	}
	}
	
	public String getURL(){
		
		return runTimeURL;
		
	}
	
public String getBrowser(){
		
		return runTimeBrowser;
		
	}
public String getRole(){
	
	return runTimeRole;
	
}
public String getRow(){
	
	return runTimeRow;
	
}
	
	/*public static void propertyInitialization()throws Exception{
		
		FileInputStream fis = new FileInputStream(foderPath+"Runtime Parameters.properties");
		Environment_property = new Properties();
		Environment_property.load(fis);
		
	}*/
}
