package com.schneider.windchillaccessrightsvalidation.promotionrequest;

import java.awt.AWTException;
import java.io.IOException;
import org.apache.log4j.Logger;

import com.schneider.windchillaccessrightsvalidation.genericlibraries.Config;
import com.schneider.windchillaccessrightsvalidation.genericlibraries.GenericFunctionLibrary;
import com.schneider.windchillaccessrightsvalidation.utilities.WindChillAccessRightsValidationUtil;

/**
 * @author MADHUBABU 09-Dec-2016
 */
public class PromotionRequestAccessRightsValidationSuite {
	
	static Logger log = Logger.getLogger("PromotionRequestAccessRightsValidationSuite");
	public static String objectType = Config.PROMOTION_REQUEST;
	String[] createAccessRight = new String[2];
	String createOption = "";
	String ADV_OBJECT_TYPE = Config.DESCRIBE_DOCUMENT;
	String ADV_SEARCH_CONTAINER_NAME =  Config.COMPONENTS_LIBRARY;
	String currentTemplate = Config.COMPONENTS_LIBRARY_TEMPLATE; 
	String ADV_SEARCH_STATE_STATUS = "In Creation";
	String ADV_SEARCH_BY_NAME  = Config.DESCRIBE_DOCUMENT_NAME;
	
	
	WindChillAccessRightsValidationUtil windChillUtil = new WindChillAccessRightsValidationUtil();

	
	public String[] verifyCreateAccessRightForPromotionRequest(String ADV_OBJECT_TYPE, String ADV_SEARCH_CONTAINER_NAME,
			String currentTemplate, String ADV_SEARCH_STATE_STATUS, String ADV_SEARCH_BY_NAME)
			throws InterruptedException, AWTException, IOException {
		log.info("###################  Create validation Script Execution Started for "+objectType+" ###############################  ");
		String[] access = new String[2];
		access[0]= "";
		access[1] = "";
		windChillUtil.isContentAvailableAndClick(Config.TESTDATA_FOLDER);
		
		log.info("  -------- "+ objectType+" : Calling Create method  -------- ");
		
			createAccessRight = windChillUtil.createPromotionRequestAccessRightValidation( ADV_SEARCH_CONTAINER_NAME, currentTemplate);
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
		
	
	public String[] verifyReadAccessRightForPromotionRequest(String ADV_OBJECT_TYPE, String ADV_SEARCH_CONTAINER_NAME, String currentTemplate, String ADV_SEARCH_STATE_STATUS, String ADV_SEARCH_BY_NAME)
			throws InterruptedException {
		log.info("###################  Read validation Script Execution Started for "+objectType+" ###############################  ");
		
		String[] accessRight= new String[2];
		if(createOption.equalsIgnoreCase("No Option")){
			accessRight[0] = "Un-Authorized";
			log.info(accessRight[0]);
			log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+ " : "+accessRight[0]);
			String comment = "User Dont have create access hence the actual result for Read is : "+ accessRight[0];
			log.info(comment);
			
			accessRight[1]= comment;
		}else if(windChillUtil.advanceSearchModifiedPR(ADV_OBJECT_TYPE, ADV_SEARCH_CONTAINER_NAME, currentTemplate, ADV_SEARCH_STATE_STATUS, ADV_SEARCH_BY_NAME)){
		
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

}
