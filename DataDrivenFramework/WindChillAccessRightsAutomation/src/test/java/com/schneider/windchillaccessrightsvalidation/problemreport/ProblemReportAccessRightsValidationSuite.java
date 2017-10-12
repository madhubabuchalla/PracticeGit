package com.schneider.windchillaccessrightsvalidation.problemreport;

import java.io.IOException;
import org.apache.log4j.Logger;
import com.schneider.windchillaccessrightsvalidation.genericlibraries.Config;
import com.schneider.windchillaccessrightsvalidation.genericlibraries.GenericFunctionLibrary;
import com.schneider.windchillaccessrightsvalidation.utilities.WindChillAccessRightsValidationUtil;

public class ProblemReportAccessRightsValidationSuite {

	static Logger log = Logger.getLogger("ProblemReportAccessRightsValidationSuite");
	
	String objectType = Config.PROBLEM_REPORT;
	
	String objectName = Config.PROBLEM_REPORT_NAME;
	String[] createAccessRight = new String[2];
	String createOption = "";
	
	public WindChillAccessRightsValidationUtil windChillUtil = new WindChillAccessRightsValidationUtil();
	
	public String[] verifyCreateAccessRightForProblemReport() throws IOException, InterruptedException {
		
		log.info("###################  Create validation Script Execution Started for "+objectType+" ###############################  ");
		String[] access = new String[2];
		access[0]= "";
		access[1] = "";
		windChillUtil.isContentAvailableAndClick(Config.TESTDATA_FOLDER);
		log.info("  -------- "+ objectType+" : Calling Create method  -------- ");
		createAccessRight =windChillUtil.createProblemReportAccessRightValidation();
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
		
		log.info("###################  Create validation Script Execution Completed for "+objectType+" ###############################  ");
		return access;
	}
	
	
	// Read : AUTHORIZED & UN-AUTHORIZED

	public String[] verifyReadAccessRightForProblemReport(String ADV_OBJECT_TYPE,String ADV_SEARCH_CONTAINER_NAME, String currentTemplate, String ADV_SEARCH_STATE_STATUS, String ADV_SEARCH_BY_NAME) throws InterruptedException {

		log.info("###################  Read validation Script Execution Started for "+objectType+" ###############################  ");
		
		String[] accessRight= new String[2];
		if(createOption.equalsIgnoreCase("No Option")){
			accessRight[0] = "Un-Authorized";
			log.info(accessRight[0]);
			log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+ " : "+accessRight[0]);
			String comment = "User Dont have create access hence the actual result for Read is : "+ accessRight[0];
			log.info(comment);
			
			accessRight[1]= comment;
		}else if(windChillUtil.advanceSearchModified(ADV_OBJECT_TYPE, ADV_SEARCH_CONTAINER_NAME, currentTemplate, ADV_SEARCH_STATE_STATUS, ADV_SEARCH_BY_NAME)){
			accessRight[0] = "Authorized";
			log.info(accessRight[0]);
			log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+ " : "+accessRight[0]);
			String comment = "Read validation Script Executed and returned the result as : "+ accessRight[0];
			
			accessRight[1]= comment;
			}
			else{
				accessRight[0] = "Un-Authorized";
				log.info(accessRight[0]);
				log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+ " : "+accessRight[0]);
				String comment = "No data available in the search : May be user  is NOT authorized or Data is not available in the system";
				log.info(comment);
				
				accessRight[1]= comment;
			}
		
		log.info("###################  Read validation Script Execution Completed for "+objectType+" ###############################  ");
	return accessRight;
		
	} 
	
	// Modify : AUTHORIZED & UN-AUTHORIZED

	public String[] verifyModifyAccessDocumentValidationProblemReport(String ADV_OBJECT_TYPE,String ADV_SEARCH_CONTAINER_NAME, String currentTemplate, String ADV_SEARCH_STATE_STATUS, String ADV_SEARCH_BY_NAME) throws InterruptedException 
	{
		
		log.info("###################  Modify validation Script Execution Started for "+objectType+" ###############################  ");
		
		String[] access= new String[2];
		String[] accessRight = new String[2];
			
		if(createOption.equalsIgnoreCase("No Option")){
			access[0] = "Un-Authorized";
			log.info(access[0]);
			log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+ " : "+access[0]);
			String comment = "User Dont have create access hence the actual result for Modify is : "+ access[0];
			log.info(comment);
		}else if(windChillUtil.advanceSearchModified(ADV_OBJECT_TYPE, ADV_SEARCH_CONTAINER_NAME, currentTemplate, ADV_SEARCH_STATE_STATUS, ADV_SEARCH_BY_NAME)){
		
		Thread.sleep(2000);
		
		String fileName = null;
		if(currentTemplate.contains("Restricted")){
			 fileName = Config.RESTRICTED+ ADV_SEARCH_BY_NAME;
			  } else{
				  fileName = ADV_SEARCH_BY_NAME;
			  }
		
		log.info("fileName for modify : "+ fileName);
		accessRight =windChillUtil.modifyAccessRightValidationForProblemReport(fileName);
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
					
	// Set State : AUTHORIZED & UN-AUTHORIZED

	public String[] verifySetStateAccessDocumentValidationProblemReport(String ADV_OBJECT_TYPE,String ADV_SEARCH_CONTAINER_NAME, String currentTemplate, String ADV_SEARCH_STATE_STATUS, String ADV_SEARCH_BY_NAME) throws InterruptedException {
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
		log.info("  -------- "+ objectType+" : Calling SetState method  -------- ");
		Thread.sleep(3000);
		accessRight = windChillUtil.setstateAccessRightValidationForProblemReport();
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
	
	// Delete : AUTHORIZED & UN-AUTHORIZED

	public String[] verfiyDeleteAccessDocumentValidationProblemReport(String ADV_OBJECT_TYPE,String ADV_SEARCH_CONTAINER_NAME, String currentTemplate, String ADV_SEARCH_STATE_STATUS, String ADV_SEARCH_BY_NAME) throws InterruptedException {
		log.info(" ###################  Delete validation Script Execution Started for "+objectType+" ############################### ");
		
		String[] access = new String[2];
		String[] accessRight = new String[2];

	Thread.sleep(3000);
	if(createOption.equalsIgnoreCase("No Option")){
		access[0] = "Un-Authorized";
	log.info(access[0]);
	log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+ " : "+access[0]);
	String comment = "User Dont have create access hence the actual result for Delete is : "+ access[0];
	log.info(comment);
	
	}else if(windChillUtil.advanceSearchModified(ADV_OBJECT_TYPE, ADV_SEARCH_CONTAINER_NAME, currentTemplate, ADV_SEARCH_STATE_STATUS, ADV_SEARCH_BY_NAME)){
	
		log.info("  -------- "+ objectType+" : Calling Delete method  -------- ");
	Thread.sleep(3000);
		accessRight =windChillUtil.deleteAccessRightValidationForProblemReport();
		access[1]=accessRight[1];
		log.info("AccessRight is : "+ accessRight[0]);
		
		if(accessRight[0].equalsIgnoreCase("Authorized Successful")){
			access[0] = "Authorized";
			log.info(access[0]);
			log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+ " : "+access[0]);
			String comment = "Delete validation Script Executed and returned the result as : "+ access[0];
			log.info(comment);
			
		}else if (accessRight[0].equalsIgnoreCase("Un-Authorized Successful")) {
			
			access[0] = "Un-Authorized";
			log.info(access[0]);
			log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+ " : "+access[0]);
			String comment = "Delete validation Script Executed and returned the result as : "+ access[0];
			log.info(comment);
			
		}
		}else{
			access[0] = "No Data";
			log.info("No Access Returned");
			log.info(GenericFunctionLibrary.getCurrentClassAndMethodNames()+" : Not Validated");
			String comment = "No data available in the search : May be user  is NOT authorized or Data is not available in the system";
			log.info(comment);
			
		}
		log.info(" ###################  Delete validation Script Execution Completed for "+objectType+" ############################### ");
		return access;
	}
	
	}
	