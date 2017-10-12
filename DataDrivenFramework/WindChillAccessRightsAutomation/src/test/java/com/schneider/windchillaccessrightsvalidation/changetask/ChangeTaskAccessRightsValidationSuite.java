package com.schneider.windchillaccessrightsvalidation.changetask;

import org.apache.log4j.Logger;

import com.schneider.windchillaccessrightsvalidation.genericlibraries.Config;
import com.schneider.windchillaccessrightsvalidation.genericlibraries.GenericFunctionLibrary;
import com.schneider.windchillaccessrightsvalidation.utilities.WindChillAccessRightsValidationUtil;

public class ChangeTaskAccessRightsValidationSuite {

	static Logger log = Logger.getLogger("ChangeTaskAccessRightsValidationSuite");
	
	String objectType = Config.CHANGE_TASK;
	String[] createAccessRight = new String[2];
	String createOption = "";
	
	
	public WindChillAccessRightsValidationUtil windChillUtil = new WindChillAccessRightsValidationUtil();

		
  		public String[] verifyCreateAccessRightValidationforChangeTask(String ADV_OBJECT_TYPE,String ADV_SEARCH_CONTAINER_NAME, String currentTemplate, String ADV_SEARCH_STATE_STATUS, String ADV_SEARCH_BY_NAME) throws InterruptedException 
  		{
  			log.info("###################  Create validation Script Execution Started for "+objectType+" ###############################  ");
  			String[] access = new String[3];
  			access[0]= "";
  			access[1] = "";
  			 if(windChillUtil.advanceSearchModified(ADV_OBJECT_TYPE, ADV_SEARCH_CONTAINER_NAME, currentTemplate, ADV_SEARCH_STATE_STATUS, ADV_SEARCH_BY_NAME)){
  			
  			Thread.sleep(2000);
  			createAccessRight = windChillUtil.createChangeTaskAccessRightValidation();
  			access[1] = createAccessRight[1];
			log.info("AccessRight is : " + access[1]);
	
	if (createAccessRight[0].equalsIgnoreCase("Authorized Successful")) {
		access[0] = "Authorized";
		log.info(access);
		log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+ " : "+access);
		String comment = "Create validation Script Executed and returned the result as : "+ createAccessRight;
		log.info(comment);
		
	} else if (createAccessRight[0].equalsIgnoreCase("Un-Authorized Successful")) {

		access[0] = "Un-Authorized";
		log.info(access);
		log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+ " : "+access);
		String comment ="Create validation Script Executed and returned the result as : "+ createAccessRight;
		log.info(comment);
		
	} else if (createAccessRight[0].equalsIgnoreCase("No Option")) {
		createOption = "No Option";
		access[0] = "Un-Authorized";
		log.info(access);
		log.info(GenericFunctionLibrary.getCurrentClassAndMethodNames()+" : No Create Option");
		String comment = "Create Option is not there in Create part dropdown";
		log.info(comment);
		
	}
  			}else{
  				String comment  = ADV_SEARCH_BY_NAME+" is not exist in system. Create a change notice for describe document with "+ Config.CHANGENOTICE_FOR_CHANGETASK+ " and try";
  				log.info(comment);
  				access[0] = "No Base Document";
  				access[2] = comment;
  			}
  				
  				log.info("###################  Create validation Script Execution Completed for "+objectType+" ###############################  ");
  				return access;
  			}
	
 
		
	 	public String[] verifyReadChangeTaskAccessRightValidation(String ADV_OBJECT_TYPE,String ADV_SEARCH_CONTAINER_NAME, String currentTemplate, String ADV_SEARCH_STATE_STATUS, String ADV_SEARCH_BY_NAME, String CHNGTSKSTATE) throws InterruptedException {
	 		log.info("###################  Read validation Script Execution Started for "+objectType+" ###############################  ");
	 		String[] access = new String[3];
	 		access[0]= "";
	 		access[1] = "";
	 		String[] readAccessRight = new String[2];
	 		if(createOption.equalsIgnoreCase("No Option")){
	 			access[0] = "Un-Authorized";
				log.info(access[0]);
				log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+ " : "+access[0]);
				String comment = "User Dont have create access hence the actual result for Read is : "+ access[0];
				log.info(comment);
				
				access[1]= comment;
	 		}else {
				 readAccessRight = windChillUtil.readChangeTask(CHNGTSKSTATE);
			
	 		if(readAccessRight[0].equalsIgnoreCase("Authorized Successful")){
          			
				access[0] = "Authorized";
				access[1] = readAccessRight[1];
					log.info(access[0]);
					log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+ " : "+access[0]);
					String comment = "Read validation Script Executed and returned the result as : "+ access[0];
					
					access[1]= comment;
					}else{
						access[0] = "Un-Authorized";
						log.info(access[0]);
						log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+ " : "+access[0]);
						String comment = "No data available in the search : May be user  is NOT authorized or Data is not available in the system";
						log.info(comment);
						
						access[1]= comment;
					}
	 		}
				
				log.info("###################  Read validation Script Execution Completed for "+objectType+" ###############################  ");
			return access;
				
			} 
	 	
	 	
	public String[] verifyModifyChangeTaskAccessRightValidation(String ADV_OBJECT_TYPE,String ADV_SEARCH_CONTAINER_NAME, String currentTemplate, String ADV_SEARCH_STATE_STATUS, String ADV_SEARCH_BY_NAME, String CHNGTSKSTATE) throws InterruptedException{
		String[] access= new String[2];
		String[] accessRight = new String[2];
			
		if(createOption.equalsIgnoreCase("No Option")){
			access[0] = "Un-Authorized";
			log.info(access[0]);
			log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+ " : "+access[0]);
			String comment = "User Dont have create access hence the actual result for Modify is : "+ access[0];
			log.info(comment);
			
		}else if(windChillUtil.advanceSearchModified(ADV_OBJECT_TYPE, ADV_SEARCH_CONTAINER_NAME, currentTemplate, ADV_SEARCH_STATE_STATUS, ADV_SEARCH_BY_NAME)){
           
			   String fileName = null;
				if(currentTemplate.contains("Restricted")){
					 fileName = Config.RESTRICTED+ Config.CHANGE_TASK_NAME;
					  } else{
						  fileName =  Config.CHANGE_TASK_NAME;
					  }
				
				log.info("File Name for modify : "+ fileName);
			   
			   // Change Task Name
      	   accessRight =windChillUtil.modifyChangeTask(fileName, CHNGTSKSTATE);
         			
      	 log.info("AccessRight is : "+ accessRight[0]);
			access[1] = accessRight[1];
			if(accessRight[0].equalsIgnoreCase("Authorized Successful")){
				access[0] = "Authorized";
				log.info(access[0]);
				log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+ " : "+access[0]);
				String comment = "Modify validation Script Executed and returned the result as : "+ access[0];
				log.info(comment);
				
			}else if (accessRight[0].equalsIgnoreCase("Un-Authorized Successful")) {
				
				access[0] = "Un-Authorized";
				log.info(access[0]);
				log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+ " : "+access[0]);
				String comment = "Modify validation Script Executed and returned the result as : "+ access[0];
				log.info(comment);
				
			}
			}else{
				access[0] = "No Data";
				log.info("No Access Returned");
				log.info(GenericFunctionLibrary.getCurrentClassAndMethodNames()+" : Not Validated");
				String comment= "No data available in the search : May be user  is NOT authorized or Data is not available in the system";
				log.info(comment);
				
			}
			log.info("###################  Modify validation Script Execution completed for "+objectType+" ###############################  ");
			return access;


		}
		
	 	public String[] setStateChangeTaskAccessRightValidation(String ADV_OBJECT_TYPE,String ADV_SEARCH_CONTAINER_NAME, String currentTemplate, String ADV_SEARCH_STATE_STATUS, String ADV_SEARCH_BY_NAME, String CHNGTSKSTATE) throws InterruptedException{
	 		log.info(" ###################  Set State validation Script Execution Started for "+objectType+" ############################### ");
			
			String[] access  = new String[2];
			String[] accessRight = new String[2];
				
			Thread.sleep(2000);
			if(createOption.equalsIgnoreCase("No Option")){
				access[0] = "Un-Authorized";
			log.info(access[0] );
			log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+ " : "+access[0] );
			String comment = "User Dont have create access hence the actual result for Set State is : "+ access[0] ;
			log.info(comment);
					
			}else if(windChillUtil.advanceSearchModified(ADV_OBJECT_TYPE, ADV_SEARCH_CONTAINER_NAME, currentTemplate, ADV_SEARCH_STATE_STATUS, ADV_SEARCH_BY_NAME)){
	           // Change Task set state
	      	 accessRight =windChillUtil.setStateChangeTask(CHNGTSKSTATE);
    			
	      	log.info("AccessRight is : "+ accessRight[0]);
			access[1]=accessRight[1];
			
			if( accessRight[0].equalsIgnoreCase("Authorized Successful")){
				access[0] = "Authorized";
				log.info(access[0]);
				log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+ " : "+access[0]);
				String comment = "Set State validation Script Executed and returned the result as : "+ access[0];
				log.info(comment);
				
			}else if (accessRight[0].equalsIgnoreCase("Un-Authorized Successful")) {
				
				access[0] = "Un-Authorized";
				log.info(access[0]);
				log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+ " : "+access[0]);
				String comment ="Set State validation Script Executed and returned the result as : "+ access[0]; 
				log.info(comment);
				
			}
			}else{
				access[0] = "No Data";
				log.info("No Access Returned");
				String comment = "No data available in the search : May be user  is NOT authorized or Data is not available in the system";
				log.info(comment);
							
			}
			log.info(" ###################  Set State validation Script Execution completed for "+objectType+" ############################### ");
			
			return access;
		}

	
}
