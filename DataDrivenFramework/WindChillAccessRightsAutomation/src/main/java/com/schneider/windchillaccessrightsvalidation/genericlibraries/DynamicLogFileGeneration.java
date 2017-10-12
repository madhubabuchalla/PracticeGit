/**
 * 
 */
package com.schneider.windchillaccessrightsvalidation.genericlibraries;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.FileAppender;


/**
 * @author MADHUBABU
 * 17-Nov-2016
 */
public class DynamicLogFileGeneration extends FileAppender {
/*static{
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
        System.setProperty("current.date.time", dateFormat.format(new Date()));
    }*/
   /* public DynamicLogFileGeneration() {
    }*/

    @Override
    public void setFile(String file) {
     //   super.setFile(System.getProperty("user.dir")+"\\logs\\"+prependDate(file));
    	super.setFile(Config.LOGS_FOLDER_PATH+prependDate(file));
    	/* super.setFile(Config.HTML_REPORT_FOLDER_PATH+prependDate(file));
    	 super.setFile(Config.HTML_FAILED_REPORT_FOLDER_PATH+prependDate(file));*/
    }

    private static String prependDate(String filename) {
    	
    	 SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss a");
         System.setProperty("current.date.time", dateFormat.format(new Date()));
    	
        return System.setProperty("current.date.time", dateFormat.format(new Date()))+"_"+ System.currentTimeMillis() + "_" + filename;
       
      //  return System.currentTimeMillis() + "_" + filename;
  
    }
}