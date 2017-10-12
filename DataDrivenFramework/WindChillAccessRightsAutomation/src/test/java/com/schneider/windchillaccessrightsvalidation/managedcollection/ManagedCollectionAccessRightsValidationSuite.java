/**
 * 
 */
package com.schneider.windchillaccessrightsvalidation.managedcollection;

import java.awt.AWTException;
import org.apache.log4j.Logger;
import com.schneider.windchillaccessrightsvalidation.genericlibraries.Config;
import com.schneider.windchillaccessrightsvalidation.genericlibraries.GenericFunctionLibrary;
import com.schneider.windchillaccessrightsvalidation.utilities.WindChillAccessRightsValidationUtil;

/**
 * @author MADHUBABU
 * 08-Dec-2016
 */
public class ManagedCollectionAccessRightsValidationSuite {
static Logger log = Logger.getLogger("ManagedCollectionAccessRightsValidationSuite");
public static String objectType = Config.MANAGED_COLLECTION;
public static String objectName = Config.MANAGED_COLLECTION_NAME;
String[] createAccessRight = new String[2];
String createOption = "";


WindChillAccessRightsValidationUtil windChillUtil = new WindChillAccessRightsValidationUtil();

	public String[] verifyCreateAccessRightForManagedCollection() throws InterruptedException, AWTException {
		log.info("###################  Create validation Script Execution Started for "+objectType+" ###############################  ");
		String[] access = new String[2];
		access[0]= "";
		access[1] = "";
		windChillUtil.isContentAvailableAndClick(Config.TESTDATA_FOLDER);
		log.info("  -------- "+ objectType+" : Calling Create method  -------- ");
		createAccessRight = windChillUtil.createManagedCollectionAccessRightValidation();
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

	public String[] verifyReadAccessRightForManagedCollection(String ADV_OBJECT_TYPE, String ADV_SEARCH_CONTAINER_NAME, String currentTemplate, String ADV_SEARCH_BY_NAME) throws InterruptedException {
		
		log.info("###################  Read validation Script Execution Started for "+objectType+" ###############################  ");
		
		String[] accessRight= new String[2];
		if(createOption.equalsIgnoreCase("No Option")){
			accessRight[0] = "Un-Authorized";
			log.info(accessRight[0]);
			log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+ " : "+accessRight[0]);
			String comment = "User Dont have create access hence the actual result for Read is : "+ accessRight[0];
			log.info(comment);
			
			accessRight[1]= comment;
		} else if (windChillUtil.advanceSearchByName(ADV_OBJECT_TYPE, ADV_SEARCH_CONTAINER_NAME, currentTemplate, ADV_SEARCH_BY_NAME)) {

			log.info("  ***************  "+ objectType+" : Calling Read method ************* ");
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

	public String[] verifyModifyAccessRightForManagedCollection(String ADV_OBJECT_TYPE, String ADV_SEARCH_CONTAINER_NAME,	String currentTemplate, String ADV_SEARCH_BY_NAME) throws InterruptedException {
		log.info("###################  Modify validation Script Execution Started for "+objectType+" ###############################  ");
		
		String[] access= new String[2];
		String[] accessRight = new String[2];
			
		if(createOption.equalsIgnoreCase("No Option")){
			access[0] = "Un-Authorized";
			log.info(access[0]);
			log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+ " : "+access[0]);
			String comment = "User Dont have create access hence the actual result for Modify is : "+ access[0];
			log.info(comment);
		} else if (windChillUtil.advanceSearchByName(ADV_OBJECT_TYPE, ADV_SEARCH_CONTAINER_NAME, currentTemplate,ADV_SEARCH_BY_NAME)) {

			log.info("  -------- "+ objectType+" : Calling Modify method  -------- ");
			Thread.sleep(3000);
			
			
			String fileName = null;
			if(currentTemplate.contains("Restricted")){
				 fileName = Config.RESTRICTED+ ADV_SEARCH_BY_NAME;
				  } else{
					  fileName = ADV_SEARCH_BY_NAME;
				  }
			
			log.info("fileName for modify : "+ fileName);
			accessRight = windChillUtil.modifyAccessRightValidationForManagedCollection(fileName);

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

	// Delete : AUTHORIZED & UN-AUTHORIZED

	public String[] verifyDeleteAccessRightForManagedCollection(String ADV_OBJECT_TYPE, String ADV_SEARCH_CONTAINER_NAME,
			String currentTemplate, String ADV_SEARCH_BY_NAME) throws InterruptedException {
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

		} else if (windChillUtil.advanceSearchByName(ADV_OBJECT_TYPE, ADV_SEARCH_CONTAINER_NAME, currentTemplate,
				ADV_SEARCH_BY_NAME)) {

			log.info("  -------- "+ objectType+" : Calling Modify method  -------- ");
			
			accessRight = windChillUtil.deleteAccessRightValidationForManagedCollection();

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
