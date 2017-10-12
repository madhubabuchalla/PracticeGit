/**
 * 
 */
package com.schneider.windchillaccessrightsvalidation.folder;

import java.io.IOException;
import java.util.logging.Logger;

import com.schneider.windchillaccessrightsvalidation.genericlibraries.Config;
import com.schneider.windchillaccessrightsvalidation.utilities.WindChillAccessRightsValidationUtil;

/**
 * @author MADHUBABU
 * 04-Jan-2017
 */
public class FolderAccessRightsValidationSuite {
	static Logger log = Logger.getLogger("FolderAccessRightsValidationSuite");
	String folderName = Config.FOLDER_NAME;
	String objectType =Config.FOLDER;
	WindChillAccessRightsValidationUtil WindChillUtil = new WindChillAccessRightsValidationUtil();

	@org.testng.annotations.BeforeClass
	public void BeforeClass() throws Exception {

	//	WindChillUtil.login();

	
	}
	// CREATE FOLDER : AUTHORIZED & UN-AUTHORIZED
		// Calling CREATE method : AUTHORIZED

		
	
		public String[] verifyCreateAccessRightForFolder(String template) throws InterruptedException, IOException{
			log.info("###################  Create validation Script Execution Started for "+objectType+" ###############################  ");
			String[] access = new String[2];
			access[0]= "";
			access[1] = "";
			
			String folderName = null;
			
			if(template.contains("Restricted")){
				folderName = Config.RESTRICTED+ Config.FOLDER_NAME;
				  } else{
					  folderName = Config.FOLDER_NAME;
				  }
			
			log.info("  -------- Calling Create method for "+ objectType +" -------- ");
			WindChillUtil.isContentAvailableAndClick(Config.TESTDATA_FOLDER);
			
			Thread.sleep(2000);
			
			String[] accessRight = WindChillUtil.createFolderAccessRightValidation(folderName);
			
			log.info("AccessRight is : "+ accessRight[0]);
			access[1] = accessRight[1];
			if(accessRight[0].equalsIgnoreCase("Authorized Successful")){
				access[0] = "Authorized";
				
				log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+ " : "+access[0]);
			}else if (accessRight[0].equalsIgnoreCase("Un-Authorized Successful")) {
				
				access[0] = "Un-Authorized";
				
				log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+ " : "+access[0]);
			}else{
				log.info("No Access Returned");
			}
			log.info("###################  Create validation Script Execution Completed for "+objectType+" ###############################  ");
			return access;
			
		}
		
		
		public void verifyReadAccessRightForFolder(String Template) throws InterruptedException{
			
			
			
		}


		
		public String[] verifyModifyAccessRightForFolder(String Template) throws InterruptedException {
			log.info("###################  Modify validation Script Execution Started for "+objectType+" ###############################  ");
			String[] access = new String[2];
			access[0]= "";
			access[1] = "";
			log.info("  -------- Calling modify method for "+ objectType +"   -------- ");
//			WindChillUtil.isContentAvailableAndClick(Config.TESTDATA_FOLDER);
//			Thread.sleep(3000);
			String[] accessRight = WindChillUtil.modifyFolderAccessRightValidation(Template);
			
			log.info("AccessRight is : "+ accessRight[0]);
			access[1] = accessRight[1];
			if( accessRight[0].equalsIgnoreCase("Authorized Successful")){
				access[0] = "Authorized";
				log.info(access[0]);
				log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+ " : "+access[0]);
			}else if ( accessRight[0].equalsIgnoreCase("Un-Authorized Successful")) {
				
				access[0] = "Un-Authorized";
				log.info(access[0]);
				log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+ " : "+access[0]);
			}else{
				log.info("No Access Returned");
			}
			log.info("###################  Modify validation Script Execution Completed for "+objectType+" ###############################  ");
			return access;
		}

		

		public String[] verifyDeleteAccessRightForFolder(String Template) throws InterruptedException {
			log.info(" ###################  Delete validation Script Execution Started for "+objectType+" ############################### ");
			String[] access = new String[2];
			access[0]= "";
			access[1] = "";
			log.info("  -------- Calling delete method  for "+ objectType +" -------- ");
//			WindChillUtil.isContentAvailableAndClick(Config.TESTDATA_FOLDER);
//			Thread.sleep(3000);
			String[] accessRight = WindChillUtil.deleteFolderAccessRightValidation(Template);
			
			log.info("AccessRight is : "+ accessRight[0]);
			access[1] = accessRight[1];
			if(accessRight[0].equalsIgnoreCase("Authorized Successful")){
				access[0] = "Authorized";
				log.info(access[0]);
				log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+ " : "+access[0]);
			}else if (accessRight[0].equalsIgnoreCase("Un-Authorized Successful")) {
				
				access[0] = "Un-Authorized";
				log.info(access[0]);
				log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+ " : "+access[0]);
			}else{
				log.info("No Access Returned");
			}
			log.info(" ###################  Delete validation Script Execution Completed for "+objectType+" ############################### ");
			return access;
		}

}
