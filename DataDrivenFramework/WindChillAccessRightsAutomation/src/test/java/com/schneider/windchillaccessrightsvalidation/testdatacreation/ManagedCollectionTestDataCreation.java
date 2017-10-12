/**
 * 
 */
package com.schneider.windchillaccessrightsvalidation.testdatacreation;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.schneider.windchillaccessrightsvalidation.genericlibraries.Authentication;
import com.schneider.windchillaccessrightsvalidation.genericlibraries.Config;
import com.schneider.windchillaccessrightsvalidation.genericlibraries.GenericFunctionLibrary;
import com.schneider.windchillaccessrightsvalidation.utilities.ExcelUtil;
import com.schneider.windchillaccessrightsvalidation.utilities.WindChillAccessRightsValidationUtil;

/**
 * @author MADHUBABU
 * 22-Dec-2016
 */
public class ManagedCollectionTestDataCreation {
	
static Logger log = Logger.getLogger(ManagedCollectionTestDataCreation.class);
private static final Logger reportLog = Logger.getLogger("reportsLogger");
private String ObjectType = Config.MANAGED_COLLECTION;

String fileName= null;

TestDataCreationUtil testdataUtil = new TestDataCreationUtil();

String currentRole = null;
String currentTemplate = null;
String status = null;
Authentication auth = new Authentication();

ExcelUtil excel = null;

String ExcelInput = Config.NEXTGEN_ACCESS_RIGHTS_MANAGEMENT_FILEPATH;

@BeforeClass
public void init() {
	excel = new ExcelUtil(ExcelInput);
	log.info(ExcelInput + " is initiated");
}
@Test
public void testDataCreationForManagedCollection() throws Exception {
	log.info("####################### "+ObjectType+ " Test Data Creation Script Execution started   ##################################"  );
	reportLog.info("####################### "+ObjectType+ " Test Data Creation Script Execution started  ##################################"  );

	int initialRowNumber = Config.managedCollectionSuiteTestDataInitialRowNumber-1;
	for (int i = initialRowNumber; i <= excel.getRowCount(ObjectType); i++) {

		log.info("####################### Execution Started for the Row Number "+ (i+1) +" in " +ObjectType+" Sheet ##################################"  );
		reportLog.info("####################### Execution Started for the Row Number "+ (i+1) +" in " +ObjectType+" Sheet ##################################"  );
				
		currentRole = excel.getData(ObjectType, i, "Role").trim();
		currentTemplate = excel.getData(ObjectType, i, "Product / Library Template").trim();
		status = excel.getData(ObjectType, i, "Status").trim();
		
		log.info("Object Type is : "+ObjectType + " and Current Template is : " + currentTemplate + " and Status of the "+ ObjectType   + " is : "+ status);
		reportLog.info("Object Type is : "+ObjectType + " and Current Template is : " + currentTemplate + " and Status of the "+ ObjectType   + " is : "+ status);
		
		
		String deleteAccess = excel.getData(ObjectType, i, "Delete").trim();
		
			if(currentTemplate.contains("Product")){
				   currentRole = "Product Manager";
				  } else if(currentTemplate.contains("Library")){
				   currentRole = "Library Manager";
				  }
			
			if(currentTemplate.contains("Restricted")){
				fileName = Config.RESTRICTED+ Config.MANAGED_COLLECTION_NAME;
				  } else{
					  fileName = Config.MANAGED_COLLECTION_NAME;
				  }
			
			
			
			 String loggedInUserRole = auth.getUserRole(currentTemplate);
			 
			log.info("Logged in user role : "+loggedInUserRole);
			
			
			
			if (loggedInUserRole.equalsIgnoreCase(Config.PRODUCT_MANAGER)||loggedInUserRole.equalsIgnoreCase(Config.LIBRARY_MANAGER)){
				
				if(WindChillAccessRightsValidationUtil.navigateToRequiredLocation(currentTemplate, Config.fetchContainerOrLibrary(currentTemplate))){
				
				log.info("User is Navigated to " + currentTemplate +" and "+ Config.fetchContainerOrLibrary(currentTemplate));
			
			GenericFunctionLibrary.refreshWindow();
			Thread.sleep(2000);
				
				
				testdataUtil.isContentAvailableAndClick(Config.TESTDATA_FOLDER);
				
				int k=1;
				
				if(deleteAccess.equals("P")){
					k=k+1;
				}
				
				log.info("No of Records required for " +(i+1)+" row is "+k);
				
					for(int j=1;j<=k;j++)	{
					String documentName=testdataUtil.createManagedCollectionAccessRightValidation(fileName);
					
					log.info("Created data set Name is : "+documentName);
					 
					 if (!documentName.equals(null)) {
						
							reportLog.debug("Created data set Name is : "+documentName);
							
						}else{
							log.info("data set is not created ");
							reportLog.info("data set is not created");
						}
						
					} // for j loop end
					
					}else{
						  log.info("User is not navigated to required location");
					        reportLog.warn("User is not navigated to required location");
					}
					
				}else {
					
					log.info("User is not Valid. This User ID is not mapped in Authentication Details SSO.xls");
					reportLog.warn("User is not Valid. This User ID is not mapped in Authentication Details SSO.xls");
				}
				
				log.info("####################### Execution Completed for the Row Number "+ (i+1) +" in " +ObjectType+" Sheet ##################################"  );
				reportLog.info("####################### Execution Completed for the Row Number "+ (i+1) +" in " +ObjectType+" Sheet ##################################"  );
				
					
							
	}// for i loop end

	}

	}

