/**
 * 
 */
package com.schneider.windchillaccessrightsvalidation.utilities;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;
import com.google.common.io.Files;
import com.schneider.windchillaccessrightsvalidation.genericlibraries.Config;

/**
 * @author MADHUBABU
 * 10-Jan-2017
 */

public class FileUtil {
	
	private static final Logger log = Logger.getLogger(FileUtil.class);
	
	 public static String copyFile(String objectType, String role, String excelInput) throws IOException{
		 String resultFilePath = null;
		try{
		
	File source = new File(excelInput);
	Long s= System.currentTimeMillis();
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss a");
	System.setProperty("current.date.time", dateFormat.format(new Date()));
	resultFilePath = Config.EXCEL_REPORT_FOLDER_PATH+objectType+"_"+role+"_"+System.setProperty("current.date.time", dateFormat.format(new Date()))+"_"+s+".xls";
	 File newdir = new File(resultFilePath);
	    
     Files.copy(source, newdir);
     
     log.info("Result File path is : "+resultFilePath);
     
       
}catch (IOException e){
	log.info(e);
}
	
	return resultFilePath;
	}
	
	
}