package com.schneider.windchillaccessrightsvalidation.objectlist;

import java.awt.AWTException;

import org.apache.log4j.Logger;

import com.schneider.windchillaccessrightsvalidation.genericlibraries.Config;
import com.schneider.windchillaccessrightsvalidation.genericlibraries.GenericFunctionLibrary;
import com.schneider.windchillaccessrightsvalidation.utilities.WindChillAccessRightsValidationUtil;

/**
 * @author MADHUBABU 25-Nov-2016
 */
public class ObjectListPartAccessRightsValidationSuite {
	static Logger log = Logger.getLogger("ObjectListPartAccessRightsValidationSuite");

	WindChillAccessRightsValidationUtil windChillUtil = new WindChillAccessRightsValidationUtil();

	public static String objectType = Config.OBJECT_LIST_PART;

	public static String objectName = Config.OBJECT_LIST_PART_NAME;
	String[] createAccessRight = new String[2];
	String createOption = "";

	public String[] verifyCreateAccessRightForObjectListPart() throws InterruptedException, AWTException {
		log.info("###################  Create validation Script Execution Started for " + objectType
				+ " ###############################  ");
		String[] access = new String[2];
		access[0] = "";
		access[1] = "";
		windChillUtil.isContentAvailableAndClick(Config.TESTDATA_FOLDER);
		log.info("  -------- " + objectType + " : Calling Create method  -------- ");

		createAccessRight = windChillUtil.createObjectListPartAccessRightValidation(objectType);
		access[1] = createAccessRight[1];
		log.info("AccessRight is : " + access[1]);

		if (createAccessRight[0].equalsIgnoreCase("Authorized Successful")) {
			access[0] = "Authorized";
			log.info(access);
			log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " : " + access);
			String comment = "Create validation Script Executed and returned the result as : " + createAccessRight;
			log.info(comment);

		} else if (createAccessRight[0].equalsIgnoreCase("Un-Authorized Successful")) {

			access[0] = "Un-Authorized";
			log.info(access);
			log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " : " + access);
			String comment = "Create validation Script Executed and returned the result as : " + createAccessRight;
			log.info(comment);

		} else if (createAccessRight[0].equalsIgnoreCase("No Option")) {
			createOption = "No Option";
			access[0] = "Un-Authorized";
			log.info(access);
			log.info(GenericFunctionLibrary.getCurrentClassAndMethodNames() + " : No Create Option");
			String comment = "Create Option is not there in Create part dropdown";
			log.info(comment);

		}

		log.info("###################  Create validation Script Execution Completed for " + objectType
				+ " ###############################  ");
		return access;
	}

	// Read : AUTHORIZED & UN-AUTHORIZED

	public String[] verifyReadAccessRightForObjectListPart(String ADV_OBJECT_TYPE, String ADV_SEARCH_CONTAINER_NAME,
			String currentTemplate, String ADV_SEARCH_STATE_STATUS, String ADV_SEARCH_BY_NAME)
			throws InterruptedException {
		log.info("###################  Read validation Script Execution Started for " + objectType
				+ " ###############################  ");

		String[] accessRight = new String[2];
		if (createOption.equalsIgnoreCase("No Option")) {
			accessRight[0] = "Un-Authorized";
			log.info(accessRight[0]);
			log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " : " + accessRight[0]);
			String comment = "User Dont have create access hence the actual result for Read is : " + accessRight[0];
			log.info(comment);

			accessRight[1] = comment;

		} else if (windChillUtil.advanceSearchModified(ADV_OBJECT_TYPE, ADV_SEARCH_CONTAINER_NAME, currentTemplate,
				ADV_SEARCH_STATE_STATUS, ADV_SEARCH_BY_NAME)) {
			log.info("  ***************  " + objectType + " : Calling Read method ************* ");
			accessRight[0] = "Authorized";
			log.info(accessRight[0]);
			log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " : " + accessRight[0]);
			String comment = "Read validation Script Executed and returned the result as : " + accessRight[0];

			accessRight[1] = comment;
		} else {
			accessRight[0] = "Un-Authorized";
			log.info(accessRight[0]);
			log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " : " + accessRight[0]);
			String comment = "No data available in the search : May be user  is NOT authorized or Data is not available in the system";
			log.info(comment);

			accessRight[1] = comment;
		}

		log.info("###################  Read validation Script Execution Completed for " + objectType
				+ " ###############################  ");
		return accessRight;

	}

	// Modify : AUTHORIZED & UN-AUTHORIZED

	public String[] verifyModifyAccessRightForObjectListPart(String ADV_OBJECT_TYPE, String ADV_SEARCH_CONTAINER_NAME,
			String currentTemplate, String ADV_SEARCH_STATE_STATUS, String ADV_SEARCH_BY_NAME)
			throws InterruptedException {
		log.info("###################  Modify validation Script Execution Started for " + objectType
				+ " ###############################  ");

		String[] access = new String[2];
		String[] accessRight = new String[2];

		if (createOption.equalsIgnoreCase("No Option")) {
			access[0] = "Un-Authorized";
			log.info(access[0]);
			log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " : " + access[0]);
			String comment = "User Dont have create access hence the actual result for Modify is : " + access[0];
			log.info(comment);

		} else if (windChillUtil.advanceSearchModified(ADV_OBJECT_TYPE, ADV_SEARCH_CONTAINER_NAME, currentTemplate,
				ADV_SEARCH_STATE_STATUS, ADV_SEARCH_BY_NAME)) {

			log.info("  -------- " + objectType + " : Calling Modify method  -------- ");
			Thread.sleep(3000);

			String fileName = null;
			if (currentTemplate.contains("Restricted")) {
				fileName = Config.RESTRICTED + ADV_SEARCH_BY_NAME;
			} else {
				fileName = ADV_SEARCH_BY_NAME;
			}

			log.info("fileName for modify : " + fileName);
			accessRight = windChillUtil.partModifyAccessValidation(fileName);

			log.info("AccessRight is : " + accessRight[0]);
			access[1] = accessRight[1];
			if (accessRight[0].equalsIgnoreCase("Authorized Successful")) {
				access[0] = "Authorized";
				log.info(access[0]);
				log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " : " + access[0]);
				String comment = "Modify validation Script Executed and returned the result as : " + access[0];
				log.info(comment);

			} else if (accessRight[0].equalsIgnoreCase("Un-Authorized Successful")) {

				access[0] = "Un-Authorized";
				log.info(access[0]);
				log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " : " + access[0]);
				String comment = "Modify validation Script Executed and returned the result as : " + access[0];
				log.info(comment);

			}
		} else {
			access[0] = "No Data";
			log.info("No Access Returned");
			log.info(GenericFunctionLibrary.getCurrentClassAndMethodNames() + " : Not Validated");
			String comment = "No data available in the search : May be user  is NOT authorized or Data is not available in the system";
			log.info(comment);

		}
		log.info("###################  Modify validation Script Execution completed for " + objectType
				+ " ###############################  ");
		return access;

	}

	// Modify Identity : AUTHORIZED & UN-AUTHORIZED

	public String[] verifyModifyIdentityAccessRightForObjectListPart(String ADV_OBJECT_TYPE,
			String ADV_SEARCH_CONTAINER_NAME, String currentTemplate, String ADV_SEARCH_STATE_STATUS,
			String ADV_SEARCH_BY_NAME) throws InterruptedException {
		String[] access = new String[2];
		String[] accessRight = new String[2];

		log.info("###################  Modify Identity validation Script Execution started for " + objectType
				+ " ###############################  ");

		if (createOption.equalsIgnoreCase("No Option")) {
			access[0] = "Un-Authorized";
			log.info(access);
			log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " : " + access);
			String comment = "User Dont have create access hence the actual result for Modify Identity is : " + access;
			log.info(comment);

		} else if (windChillUtil.advanceSearchModified(ADV_OBJECT_TYPE, ADV_SEARCH_CONTAINER_NAME, currentTemplate,
				ADV_SEARCH_STATE_STATUS, ADV_SEARCH_BY_NAME)) {
			log.info("  -------- " + objectType + " : Calling Modify Identity method  -------- ");
			Thread.sleep(3000);
			accessRight = windChillUtil.partModifyIdentityAccessValidation(objectName);
			log.info("AccessRight is : " + accessRight[0]);
			access[1] = accessRight[1];

			if (accessRight[0].equalsIgnoreCase("Authorized Successful")) {
				access[0] = "Authorized";
				log.info(access[0]);
				log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " : " + access[0]);
				String comment = "Modify Identity validation Script Executed and returned the result as : " + access[0];
				log.info(comment);

			} else if (accessRight[0].equalsIgnoreCase("Un-Authorized Successful")) {

				access[0] = "Un-Authorized";
				log.info(access[0]);
				log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " : " + access[0]);
				String comment = "Modify Identity validation Script Executed and returned the result as : " + access[0];
				log.info(comment);

			}
		} else {
			access[0] = "No Data";
			log.info("No Access Returned");
			log.info(GenericFunctionLibrary.getCurrentClassAndMethodNames() + " : Not Validated");
			String comment = "No data available in the search : May be user  is NOT authorized or Data is not available in the system";
			log.info(comment);

		}
		log.info("###################  Modify Identity validation Script Execution completed for " + objectType
				+ " ###############################  ");
		return access;

	}

	// Set State : AUTHORIZED & UN-AUTHORIZED

	public String[] verifySetStateAccessRightForObjectListPart(String ADV_OBJECT_TYPE, String ADV_SEARCH_CONTAINER_NAME,
			String currentTemplate, String ADV_SEARCH_STATE_STATUS, String ADV_SEARCH_BY_NAME)
			throws InterruptedException {
		String[] access = new String[2];
		String[] accessRight = new String[2];

		log.info(" ###################  Set State validation Script Execution Started for " + objectType
				+ " ############################### ");

		Thread.sleep(2000);
		if (createOption.equalsIgnoreCase("No Option")) {
			access[0] = "Un-Authorized";
			log.info(access[0]);
			log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " : " + access[0]);
			String comment = "User Dont have create access hence the actual result for Set State is : " + access[0];
			log.info(comment);

		} else if (windChillUtil.advanceSearchModified(ADV_OBJECT_TYPE, ADV_SEARCH_CONTAINER_NAME, currentTemplate,
				ADV_SEARCH_STATE_STATUS, ADV_SEARCH_BY_NAME)) {
			log.info("  -------- " + objectType + " : Calling SetState method  -------- ");
			Thread.sleep(3000);
			accessRight = windChillUtil.setStateAccessRightValidation();

			log.info("AccessRight is : " + accessRight[0]);
			access[1] = accessRight[1];

			if (accessRight[0].equalsIgnoreCase("Authorized Successful")) {
				access[0] = "Authorized";
				log.info(access[0]);
				log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " : " + access[0]);
				String comment = "Set State validation Script Executed and returned the result as : " + access[0];
				log.info(comment);

			} else if (accessRight[0].equalsIgnoreCase("Un-Authorized Successful")) {

				access[0] = "Un-Authorized";
				log.info(access[0]);
				log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " : " + access[0]);
				String comment = "Set State validation Script Executed and returned the result as : " + access[0];
				log.info(comment);

			}
		} else {
			access[0] = "No Data";
			log.info("No Access Returned");
			String comment = "No data available in the search : May be user  is NOT authorized or Data is not available in the system";
			log.info(comment);

		}
		log.info(" ###################  Set State validation Script Execution completed for " + objectType
				+ " ############################### ");
		return access;
	}
	// Change context : AUTHORIZED & UN-AUTHORIZED

	public String[] verifyChangeContextAccessRightForObjectListPart(String ADV_OBJECT_TYPE,
			String ADV_SEARCH_CONTAINER_NAME, String currentTemplate, String ADV_SEARCH_STATE_STATUS,
			String ADV_SEARCH_BY_NAME) throws InterruptedException {
		String[] access = new String[2];
		String[] accessRight = new String[2];

		log.info(" ###################  Change Context validation Script Execution Started for " + objectType
				+ " ############################### ");

		if (createOption.equalsIgnoreCase("No Option")) {
			access[0] = "Un-Authorized";
			log.info(access[0]);
			log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " : " + access[0]);
			String comment = "User Dont have create access hence the actual result for Change Context is : "
					+ access[0];
			log.info(comment);

		} else if (windChillUtil.advanceSearchModified(ADV_OBJECT_TYPE, ADV_SEARCH_CONTAINER_NAME, currentTemplate,
				ADV_SEARCH_STATE_STATUS, ADV_SEARCH_BY_NAME)) {
			log.info("  -------- " + objectType + " : Calling change context method  -------- ");
			Thread.sleep(3000);
			accessRight = windChillUtil.changeContextAccessRightValidation();
			access[1] = accessRight[1];
			log.info("AccessRight is : " + accessRight[0]);

			if (accessRight[0].equalsIgnoreCase("Authorized Successful")) {
				access[0] = "Authorized";
				log.info(access[0]);
				log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " : " + access[0]);
				String comment = "Change Domain validation Script Executed and returned the result as : " + access[0];
				log.info(comment);

			} else if (accessRight[0].equalsIgnoreCase("Un-Authorized Successful")) {

				access[0] = "Un-Authorized";
				log.info(access[0]);
				log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " : " + access[0]);
				String comment = "Change Domain validation Script Executed and returned the result as : " + access[0];
				log.info(comment);

			}
		} else {
			access[0] = "No Data";
			log.info("No Access Returned");
			String comment = "No data available in the search : May be user  is NOT authorized or Data is not available in the system";
			log.info(comment);

		}
		log.info(" ###################  Change Context validation Script Execution completed for " + objectType
				+ " ############################### ");
		return access;

	}

	// Change domain : AUTHORIZED & UN-AUTHORIZED

	public String[] verifyChangeDomainAccessRightForObjectListPart(String ADV_OBJECT_TYPE,
			String ADV_SEARCH_CONTAINER_NAME, String currentTemplate, String ADV_SEARCH_STATE_STATUS,
			String ADV_SEARCH_BY_NAME) throws InterruptedException {
		String[] access = new String[2];
		String[] accessRight = new String[2];

		log.info(" ###################  Change Domain validation Script Execution Started for " + objectType
				+ " ############################### ");

		if (createOption.equalsIgnoreCase("No Option")) {
			access[0] = "Un-Authorized";
			log.info(access[0]);
			log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " : " + access[0]);
			String comment = "User Dont have create access hence the actual result is : " + access[0];
			log.info(comment);

		} else if (windChillUtil.advanceSearchModified(ADV_OBJECT_TYPE, ADV_SEARCH_CONTAINER_NAME, currentTemplate,
				ADV_SEARCH_STATE_STATUS, ADV_SEARCH_BY_NAME)) {
			log.info("  -------- " + objectType + " : Calling change domain method  -------- ");
			Thread.sleep(3000);
			accessRight = windChillUtil.changeDomainAccessRightValidation(currentTemplate);

			log.info("AccessRight is : " + accessRight[0]);
			access[1] = accessRight[1];
			if (accessRight[0].equalsIgnoreCase("Authorized Successful")) {
				access[0] = "Authorized";
				log.info(access[0]);
				log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " : " + access[0]);
				String comment = "Change Domain validation Script Executed and returned the result for Change Domain as : "
						+ access[0];
				log.info(comment);

			} else if (accessRight[0].equalsIgnoreCase("Un-Authorized Successful")) {

				access[0] = "Un-Authorized";
				log.info(access[0]);
				log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " : " + access[0]);
				String comment = "Change Domain validation Script Executed and returned the result as : " + access[0];
				log.info(comment);

			}
		} else {
			access[0] = "No Data";
			log.info("No Access Returned");
			log.info(GenericFunctionLibrary.getCurrentClassAndMethodNames() + " : Not Validated");
			String comment = "No data available in the search : May be user  is NOT authorized or Data is not available in the system";
			log.info(comment);

		}
		log.info(" ###################  Change Domain validation Script Execution completed for " + objectType
				+ " ############################### ");
		return access;

	}
	// Revise : AUTHORIZED & UN-AUTHORIZED

	public String[] verifyReviseAccessRightForObjectListPart(String ADV_OBJECT_TYPE, String ADV_SEARCH_CONTAINER_NAME,
			String currentTemplate, String ADV_SEARCH_STATE_STATUS, String ADV_SEARCH_BY_NAME)
			throws InterruptedException {
		log.info("###################  Revise validation Script Execution Started for " + objectType
				+ " ###############################  ");

		String[] access = new String[2];
		String[] accessRight = new String[2];

		Thread.sleep(2000);
		if (createOption.equalsIgnoreCase("No Option")) {
			access[0] = "Un-Authorized";
			log.info(access[0]);
			log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " : " + access[0]);
			String comment = "User Dont have create access hence the actual result for Revise is : " + access[0];
			log.info(comment);

		} else if (windChillUtil.advanceSearchModified(ADV_OBJECT_TYPE, ADV_SEARCH_CONTAINER_NAME, currentTemplate,
				ADV_SEARCH_STATE_STATUS, ADV_SEARCH_BY_NAME)) {
			log.info("  -------- " + objectType + " : Calling revise method  -------- ");
			Thread.sleep(3000);
			accessRight = windChillUtil.reviseAccessRightValidation();
			log.info("AccessRight is : " + accessRight[0]);

			access[1] = accessRight[1];

			if (accessRight[0].equalsIgnoreCase("Authorized Successful")) {
				access[0] = "Authorized";
				log.info(access[0]);
				log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " : " + access[0]);
				String comment = "Revise validation Script Executed and returned the result as : " + access[0];
				log.info(comment);

			} else if (accessRight[0].equalsIgnoreCase("Un-Authorized Successful")) {

				access[0] = "Un-Authorized";
				log.info(accessRight[0]);
				log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " : " + access[0]);
				String comment = "Revise validation Script Executed and returned the result as : " + access[0];
				log.info(comment);

			}
		} else {
			access[0] = "No Data";
			log.info("No Access Returned");
			log.info(GenericFunctionLibrary.getCurrentClassAndMethodNames() + " : Not Validated");
			String comment = "No data available in the search : May be user  is NOT authorized or Data is not available in the system";
			log.info(comment);

		}
		log.info("###################  Revise validation Script Execution Completed for " + objectType
				+ " ###############################  ");
		return access;
	}

	// Delete : AUTHORIZED & UN-AUTHORIZED

	public String[] verifyDeleteAccessRightForObjectListPart(String ADV_OBJECT_TYPE, String ADV_SEARCH_CONTAINER_NAME,
			String currentTemplate, String ADV_SEARCH_STATE_STATUS, String ADV_SEARCH_BY_NAME)
			throws InterruptedException {
		String[] access = new String[2];
		String[] accessRight = new String[2];

		log.info(" ###################  Delete validation Script Execution Started for " + objectType
				+ " ############################### ");

		Thread.sleep(3000);
		if (createOption.equalsIgnoreCase("No Option")) {
			access[0] = "Un-Authorized";
			log.info(access[0]);
			log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " : " + access[0]);
			String comment = "User Dont have create access hence the actual result for Delete is : " + access[0];
			log.info(comment);

		} else if (windChillUtil.advanceSearchModified(ADV_OBJECT_TYPE, ADV_SEARCH_CONTAINER_NAME, currentTemplate,
				ADV_SEARCH_STATE_STATUS, ADV_SEARCH_BY_NAME)) {
			log.info("  -------- " + objectType + " : Calling Delete method  -------- ");
			Thread.sleep(3000);
			accessRight = windChillUtil.deleteAccessRightValidation();
			access[1] = accessRight[1];
			log.info("AccessRight is : " + accessRight[0]);

			if (accessRight[0].equalsIgnoreCase("Authorized Successful")) {
				access[0] = "Authorized";
				log.info(access[0]);
				log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " : " + access[0]);
				String comment = "Delete validation Script Executed and returned the result as : " + access[0];
				log.info(comment);

			} else if (accessRight[0].equalsIgnoreCase("Un-Authorized Successful")) {

				access[0] = "Un-Authorized";
				log.info(access[0]);
				log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " : " + access[0]);
				String comment = "Delete validation Script Executed and returned the result as : " + access[0];
				log.info(comment);

			}
		} else {
			access[0] = "No Data";
			log.info("No Access Returned");
			log.info(GenericFunctionLibrary.getCurrentClassAndMethodNames() + " : Not Validated");
			String comment = "No data available in the search : May be user  is NOT authorized or Data is not available in the system";
			log.info(comment);

		}
		log.info(" ###################  Delete validation Script Execution Completed for " + objectType
				+ " ############################### ");
		return access;
	}
}
