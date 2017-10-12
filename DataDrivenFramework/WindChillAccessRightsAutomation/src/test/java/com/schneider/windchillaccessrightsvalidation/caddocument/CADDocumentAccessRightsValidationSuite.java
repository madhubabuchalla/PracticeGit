package com.schneider.windchillaccessrightsvalidation.caddocument;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.schneider.windchillaccessrightsvalidation.genericlibraries.Config;
import com.schneider.windchillaccessrightsvalidation.genericlibraries.GenericFunctionLibrary;
import com.schneider.windchillaccessrightsvalidation.utilities.WindChillAccessRightsValidationUtil;

public class CADDocumentAccessRightsValidationSuite {
	String objectType = Config.CAD_PART;
	String objectName = "CADPART";
	String state = "In Creation";
	String[] createAccessRight = new String[2];
	String createOption = "";
	static Logger log = Logger.getLogger("CADDocumentAccessRightsValidationSuite");
	
	WindChillAccessRightsValidationUtil windChillUtil = new WindChillAccessRightsValidationUtil();

	public String[] verifyCreateAccessRightForCADDocument(String ObjectType, String template, String ContextName) throws IOException, InterruptedException {
			
		log.info("###################  Create validation Script Execution Started for "+objectType+" ###############################  ");
		String[] accessRight = new String[2];
		accessRight[0]= "";
		accessRight[1] = "";

		createAccessRight = windChillUtil.createCADDocumentAccessRightsValidation(objectType, template, ContextName);
		log.info("AccessRight is : "+ createAccessRight[0]);
		accessRight[1]= createAccessRight[1];
		log.info("Create validation Script Executed and returned the result as : "+ createAccessRight[0]);
		if(createAccessRight[0].equalsIgnoreCase("Authorized Successful")){
			accessRight[0] = "Authorized";
			
			log.info(accessRight[0]);
			log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+ " : "+accessRight[0]);
			
		}else if (createAccessRight[0].equalsIgnoreCase("Un-Authorized Successful")) {
			
			accessRight[0] = "Un-Authorized";
			log.info(accessRight[0]);
			log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+ " : "+accessRight[0]);
			
		} if (createAccessRight[0].equalsIgnoreCase("No Option")){
			accessRight[0] = "Un-Authorized";
			log.info(accessRight[0]);
			log.info(GenericFunctionLibrary.getCurrentClassAndMethodNames()+" : No Create Option");
			
			log.info("No Create Option Available");
		}
	
	log.info("###################  Create validation Script Execution Completed for "+objectType+" ###############################  ");
		
		return accessRight;
	}

		// calling read method : Authorized & Un-Authorized

public String[] verifyReadAccessRightForCADDocument(String ADV_OBJECT_TYPE,String ADV_SEARCH_CONTAINER_NAME, String currentTemplate, String ADV_SEARCH_STATE_STATUS, String ADV_SEARCH_BY_NAME) throws InterruptedException {
		
		
log.info("###################  Read validation Script Execution Started for "+objectType+" ###############################  ");
String[] accessRight= new String[2];
	Thread.sleep(2000);

	if(createOption.equalsIgnoreCase("No Option")){
	accessRight[0] = "Un-Authorized";
	log.info(accessRight[0]);
	log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+ " : "+accessRight[0]);
	log.info("Read validation Script Executed and returned the result as : "+ accessRight[0]);
	}else if(windChillUtil.advanceSearchModified(ADV_OBJECT_TYPE, ADV_SEARCH_CONTAINER_NAME, currentTemplate, ADV_SEARCH_STATE_STATUS, ADV_SEARCH_BY_NAME)){
	log.info("  -------- CAD Document Part : Calling Read method  -------- ");
	
	accessRight[0] = "Authorized";
	log.info(accessRight[0]);
	
	log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+ " : "+accessRight[0]);
	}
	else{
		accessRight[0] = "Un-Authorized";
		log.info(accessRight[0]);
		log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+ " : "+accessRight[0]);
		log.info("No data available in the search : May be user  is NOT authorized or Data is not available in the system");
	}

log.info("###################  Read validation Script Execution Completed for "+objectType+" ###############################  ");
return accessRight;

}


public String[] verifyDownloadAccessRightForCADDocument(String ADV_OBJECT_TYPE,String ADV_SEARCH_CONTAINER_NAME, String currentTemplate, String ADV_SEARCH_STATE_STATUS, String ADV_SEARCH_BY_NAME) throws InterruptedException {
	log.info("###################  Download validation Script Execution Started for "+objectType+" ###############################  ");
	String[] access= new String[2];
	String[] accessRight = new String[2];
	
	if(createOption.equalsIgnoreCase("No Option")){
		access[0] = "Un-Authorized";
		log.info(access[0]);
		String comment = "User Dont have create access hence the actual result for download is : "+ access[0];
		log.info(comment);
	}else if(windChillUtil.advanceSearchModified(ADV_OBJECT_TYPE, ADV_SEARCH_CONTAINER_NAME, currentTemplate, ADV_SEARCH_STATE_STATUS, ADV_SEARCH_BY_NAME)){
	Thread.sleep(2000);
	log.info("  -------- "+ objectType+" : Calling Download method  -------- ");

	accessRight =windChillUtil.downloadAccessRightValidation();
	log.info("AccessRight is : "+ accessRight[0]);
	access[1] = accessRight[1];
	log.info("Download validation Script Executed and returned the result as : "+ accessRight[0]);
	if(accessRight[0].equalsIgnoreCase("Authorized Successful")){
		access[0] = "Authorized";
		log.info(access[0]);
		log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+ " : "+access[0]);
	}else if (accessRight[0].equalsIgnoreCase("Un-Authorized Successful")) {
		
		access[0] = "Un-Authorized";
		log.info(access[0]);
		log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+ " : "+access[0]);
	}
	}else{
		access[0] = "No Data";
		log.info("No Access Returned");
		log.info(GenericFunctionLibrary.getCurrentClassAndMethodNames()+" : Not Validated");
	}
	log.info("###################  Download validation Script Execution Completed for "+objectType+" ###############################  ");
	return access;

}


		
		// Calling setstate : Authorized & Un-Authorized

public String[] verifySetStateAccessRightForCADDocument(String ADV_OBJECT_TYPE,String ADV_SEARCH_CONTAINER_NAME, String currentTemplate, String ADV_SEARCH_STATE_STATUS, String ADV_SEARCH_BY_NAME) throws InterruptedException{
	
	log.info(" ###################  Set State validation Script Execution Started for "+objectType+" ############################### ");
	String[] access= new String[2];
	String[] accessRight = new String[2];
	
	if(createOption.equalsIgnoreCase("No Option")){
		access[0] = "Un-Authorized";
		log.info(access[0]);
		log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+ " : "+access[0]);
		log.info("Set State validation Script Executed and returned the result as : "+ access[0]);
		
	}else if(windChillUtil.advanceSearchModified(ADV_OBJECT_TYPE, ADV_SEARCH_CONTAINER_NAME, currentTemplate, ADV_SEARCH_STATE_STATUS, ADV_SEARCH_BY_NAME)){
	Thread.sleep(2000);
	 accessRight =windChillUtil.setStateAccessRightValidation();
	log.info("AccessRight is : "+ accessRight[0]);
	access[1]=accessRight[1];
	log.info("Set State validation Script Executed and returned the result as : "+ accessRight[0]);
	if(accessRight[0].equalsIgnoreCase("Authorized Successful")){
		access[0] = "Authorized";
		log.info(access[0]);
		log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+ " : "+access[0]);
	}else if (accessRight[0].equalsIgnoreCase("Un-Authorized Successful")) {
		
		access[0] = "Un-Authorized";
		log.info(access[0]);
		log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+ " : "+access[0]);
	}
	}else{
		access[0] = "No Data";
		log.info("No Access Returned");
		log.info(GenericFunctionLibrary.getCurrentClassAndMethodNames()+" : Not Validated");
	}
	log.info(" ###################  Set State validation Script Execution completed for "+objectType+" ############################### ");
	return access;
}


		// Calling change context : un-Authorized & authorized

	public String[] verifyChangeContextAccessRightForCADDocument(String ADV_OBJECT_TYPE, String ADV_SEARCH_CONTAINER_NAME,
			String currentTemplate, String ADV_SEARCH_STATE_STATUS, String ADV_SEARCH_BY_NAME)
			throws InterruptedException {
		log.info(" ###################  Change Context validation Script Execution Started for " + objectType
				+ " ############################### ");
		String[] access= new String[2];
		String[] accessRight = new String[2];
		if (createOption.equalsIgnoreCase("No Option")) {
			access[0] = "Un-Authorized";
			log.info(access[0]);
			log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+ " : "+access[0]);
			log.info("Change Context validation Script Executed and returned the result as : "+ access[0]);
			
		} else if (windChillUtil.advanceSearchModified(ADV_OBJECT_TYPE, ADV_SEARCH_CONTAINER_NAME, currentTemplate,
				ADV_SEARCH_STATE_STATUS, ADV_SEARCH_BY_NAME)) {
			Thread.sleep(2000);
			accessRight = windChillUtil.changeContextAccessRightValidation();
			log.info("AccessRight is : " + accessRight[0]);
			access[1]=accessRight[1];
			log.info("Change Context validation Script Executed and returned the result as : " + accessRight[0]);
			if (accessRight[0].equalsIgnoreCase("Authorized Successful")) {
				access[0] = "Authorized";
				log.info(access[0]);
				log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " : " + access[0]);
			} else if (accessRight[0].equalsIgnoreCase("Un-Authorized Successful")) {

				access[0] = "Un-Authorized";
				log.info(access[0]);
				log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " : " + access[0]);
			}
		} else {
			access[0] = "No Data";
			log.info("No Access Returned");
			log.info(GenericFunctionLibrary.getCurrentClassAndMethodNames() + " : Not Validated");
		}
		log.info(" ###################  Change Context validation Script Execution Completed for " + objectType
				+ " ############################### ");
		return access;

	}

	
	// Change Domain Access Right Validation
	public String[] verifyChangeDomainAccessRightForCADDocument(String ADV_OBJECT_TYPE, String ADV_SEARCH_CONTAINER_NAME,
			String currentTemplate, String ADV_SEARCH_STATE_STATUS, String ADV_SEARCH_BY_NAME)
			throws InterruptedException {
		log.info(" ###################  Change Domain validation Script Execution Started for " + objectType
				+ " ############################### ");
		String[] access= new String[2];
		String[] accessRight = new String[2];
		
		if (createOption.equalsIgnoreCase("No Option")) {
			access[0] = "Un-Authorized";
			log.info(access[0]);
			log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+ " : "+access[0]);
			log.info("Change Domain validation Script Executed and returned the result as : "+ access[0]);
			
		} else if (windChillUtil.advanceSearchModified(ADV_OBJECT_TYPE, ADV_SEARCH_CONTAINER_NAME, currentTemplate,
				ADV_SEARCH_STATE_STATUS, ADV_SEARCH_BY_NAME)) {
			Thread.sleep(2000);
			accessRight = windChillUtil.changeDomainAccessRightValidation(currentTemplate);
			log.info("AccessRight is : " + accessRight[0]);
			access[1]=accessRight[1];
			log.info("Change Domain validation Script Executed and returned the result as : " + accessRight[0]);
			if (accessRight[0].equalsIgnoreCase("Authorized Successful")) {
				access[0] = "Authorized";
				log.info(access[0]);
				log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " : " + access[0]);
			} else if (accessRight[0].equalsIgnoreCase("Un-Authorized Successful")) {

				access[0] = "Un-Authorized";
				log.info(access[0]);
				log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " : " + access[0]);
			}
		} else {
			access[0] = "No Data";
			log.info("No Access Returned");
			log.info(GenericFunctionLibrary.getCurrentClassAndMethodNames() + " : Not Validated");
		}
		log.info(" ###################  Change Domain validation Script Execution completed for " + objectType
				+ " ############################### ");
		return access;

	}
				
	
		// Calling revise method : Authorized and UnAuthorized

	public String[] verifyReviseAccessRightForCADDocument(String ADV_OBJECT_TYPE, String ADV_SEARCH_CONTAINER_NAME,
			String currentTemplate, String ADV_SEARCH_STATE_STATUS, String ADV_SEARCH_BY_NAME)
			throws InterruptedException {

		log.info("###################  Revise validation Script Execution Started for " + objectType + " ###############################  ");
		String[] access= new String[2];
		String[] accessRight = new String[2];
		if (createOption.equalsIgnoreCase("No Option")) {
			access[0] = "Un-Authorized";
			log.info(access[0]);
			log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+ " : "+access[0]);
			log.info("Revise validation Script Executed and returned the result as : "+ access[0]);
			
		} else if (windChillUtil.advanceSearchModified(ADV_OBJECT_TYPE, ADV_SEARCH_CONTAINER_NAME, currentTemplate,
				ADV_SEARCH_STATE_STATUS, ADV_SEARCH_BY_NAME)) {
			Thread.sleep(2000);
			accessRight = windChillUtil.reviseAccessRightValidationforCAD();
			log.info("AccessRight is : " + accessRight[0]);
			access[1]=accessRight[1];
			log.info("Revise validation Script Executed and returned the result as : " + accessRight[0]);
			if (accessRight[0].equalsIgnoreCase("Authorized Successful")) {
				access[0] = "Authorized";
				log.info(access[0]);
				log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " : " + access[0]);
			} else if (accessRight[0].equalsIgnoreCase("Un-Authorized Successful")) {

				access[0] = "Un-Authorized";
				log.info(access[0]);
				log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " : " + access[0]);
			}
		} else {
			access[0] = "No Data";
			log.info("No Access Returned");
			log.info(GenericFunctionLibrary.getCurrentClassAndMethodNames() + " : Not Validated");
		}
		log.info("###################  Revise validation Script Execution Completed for " + objectType
				+ " ###############################  ");
		return access;

	}

	// Calling delete method : Authorized and UnAuthorized

	public String[] verifyDeleteAccessRightForCADDocument(String ADV_OBJECT_TYPE, String ADV_SEARCH_CONTAINER_NAME,
			String currentTemplate, String ADV_SEARCH_STATE_STATUS, String ADV_SEARCH_BY_NAME)
			throws InterruptedException {
		log.info(" ###################  Delete validation Script Execution Started for " + objectType
				+ " ############################### ");
		String[] access = new String[2];
		String[] accessRight = new String[2];

		if (createOption.equalsIgnoreCase("No Option")) {
			access[0] = "Un-Authorized";
			log.info(access[0]);
			log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " : " + access[0]);
			log.info("Delete validation Script Executed and returned the result as : " + access[0]);
		} else if (windChillUtil.advanceSearchModified(ADV_OBJECT_TYPE, ADV_SEARCH_CONTAINER_NAME, currentTemplate,
				ADV_SEARCH_STATE_STATUS, ADV_SEARCH_BY_NAME)) {

			Thread.sleep(2000);
			accessRight = windChillUtil.deleteAccessRightValidation();
			log.info("AccessRight is : " + accessRight[0]);
			access[1]=accessRight[1];
			log.info("Delete validation Script Executed and returned the result as : " + accessRight[0]);
			if (accessRight[0].equalsIgnoreCase("Authorized Successful")) {
				access[0] = "Authorized";
				log.info(access[0]);
				log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " : " + access[0]);
			} else if (accessRight[0].equalsIgnoreCase("Un-Authorized Successful")) {

				access[0] = "Un-Authorized";
				log.info(access[0]);
				log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " : " + access[0]);
			}
		} else {
			access[0] = "No Data";
			log.info("No Access Returned");
			log.info(GenericFunctionLibrary.getCurrentClassAndMethodNames() + " : Not Validated");
		}
		log.info(" ###################  Delete validation Script Execution Completed for " + objectType
				+ " ############################### ");
		return access;
	}		

	// Modify : AUTHORIZED & UN-AUTHORIZED

	public String[] verifyModifyAccessRightForCADDocument(String ADV_OBJECT_TYPE, String ADV_SEARCH_CONTAINER_NAME,
			String templateName, String ADV_SEARCH_STATE_STATUS, String ADV_SEARCH_BY_NAME)
			throws InterruptedException {

		log.info("###################  Modify validation Script Execution Started for " + objectType
				+ " ###############################  ");
		String[] access = new String[2];
		String[] accessRight = new String[2];
		if (createOption.equalsIgnoreCase("No Option")) {
			access[0] = "Un-Authorized";
			log.info(access[0]);
			log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " : " + access[0]);
			log.info("Modify validation Script Executed and returned the result as : " + access[0]);
		} else if (windChillUtil.advanceSearchModified(ADV_OBJECT_TYPE, ADV_SEARCH_CONTAINER_NAME, templateName,
				ADV_SEARCH_STATE_STATUS, ADV_SEARCH_BY_NAME)) {

			log.info("  -------- Catalog Part : Calling Modify method  -------- ");
			Thread.sleep(3000);

			String fileName = null;
			if (templateName.contains("Restricted")) {
				fileName = Config.RESTRICTED + ADV_SEARCH_BY_NAME;
			} else {
				fileName = ADV_SEARCH_BY_NAME;
			}

			log.info("fileName for modify : " + fileName);
			accessRight = windChillUtil.partModifyAccessValidation(fileName);

			log.info("AccessRight is : " + accessRight[0]);
			access[1]=accessRight[1];
			log.info("Modify validation Script Executed and returned the result as : " + accessRight[0]);
			if (accessRight[0].equalsIgnoreCase("Authorized Successful")) {
				access[0] = "Authorized";
				log.info(access[0]);
				log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " : " + access[0]);
			} else if (accessRight[0].equalsIgnoreCase("Un-Authorized Successful")) {

				access[0] = "Un-Authorized";
				log.info(access[0]);
				log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " : " + access[0]);
			}
		} else {
			access[0] = "No Data";
			log.info("No Access Returned");
			log.info(GenericFunctionLibrary.getCurrentClassAndMethodNames() + " : Not Validated");
		}
		log.info("###################  Modify validation Script Execution completed for " + objectType
				+ " ###############################  ");
		return access;

	}

	// Modify Identity : AUTHORIZED & UN-AUTHORIZED

	public String[] verifyModifyIdentityAccessRightForCADDocument(String ADV_OBJECT_TYPE,
			String ADV_SEARCH_CONTAINER_NAME, String templateName, String ADV_SEARCH_STATE_STATUS,
			String ADV_SEARCH_BY_NAME) throws InterruptedException {

		log.info("###################  Modify Identity validation Script Execution started for " + objectType
				+ " ###############################  ");
		String[] access = new String[2];
		String[] accessRight = new String[2];
		if (createOption.equalsIgnoreCase("No Option")) {
			access[0] = "Un-Authorized";
			log.info(access[0]);
			log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " : " + access[0]);
			log.info("Modify Identity validation Script Executed and returned the result as : " + access[0]);

		} else if (windChillUtil.advanceSearchModified(ADV_OBJECT_TYPE, ADV_SEARCH_CONTAINER_NAME, templateName,
				ADV_SEARCH_STATE_STATUS, ADV_SEARCH_BY_NAME)) {
			log.info("  -------- Catalog Part : Calling Modify Identity method  -------- ");
			Thread.sleep(3000);

			String fileName = null;
			if (templateName.contains("Restricted")) {
				fileName = Config.RESTRICTED + ADV_SEARCH_BY_NAME;
			} else {
				fileName = ADV_SEARCH_BY_NAME;
			}

			log.info("fileName for modify : " + fileName);
			accessRight = windChillUtil.CADpartModifyIdentityAccessValidation(fileName);
			log.info("AccessRight is : " + accessRight[0]);
			access[1]=accessRight[1];
			log.info("Modify Identity validation Script Executed and returned the result as : " + accessRight[0]);

			if (accessRight[0].equalsIgnoreCase("Authorized Successful")) {
				access[0] = "Authorized";
				log.info(access[0]);
				log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " : " + access[0]);

			} else if (accessRight[0].equalsIgnoreCase("Un-Authorized Successful")) {

				access[0] = "Un-Authorized";
				log.info(access[0]);
				log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " : " + access[0]);

			}
		} else {
			access[0] = "No Data";
			log.info("No Access Returned");
			log.info(GenericFunctionLibrary.getCurrentClassAndMethodNames() + " : Not Validated");
		}
		log.info("###################  Modify Identity validation Script Execution completed for " + objectType
				+ " ###############################  ");
		return access;

	}	
							
}
