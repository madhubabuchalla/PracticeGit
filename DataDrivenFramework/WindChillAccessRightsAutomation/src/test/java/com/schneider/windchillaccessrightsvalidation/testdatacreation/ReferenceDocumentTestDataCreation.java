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

public class ReferenceDocumentTestDataCreation {
	
	static Logger log = Logger.getLogger(ReferenceDocumentTestDataCreation.class);
	private static final Logger reportLog = Logger.getLogger("reportsLogger");
	String ObjectType = Config.REFERENCE_DOCUMENT;	
	
	String documentName=null;
	String currentRole = null;
	String currentTemplate = null;
	String status = null;
	Authentication auth = new Authentication();
	String filename = null;
	TestDataCreationUtil testdataUtil = new TestDataCreationUtil();
	
	ExcelUtil excel = null;
	
	
	String ExcelInput = Config.NEXTGEN_ACCESS_RIGHTS_MANAGEMENT_FILEPATH;

	@BeforeClass
	public void init() {
		excel = new ExcelUtil(ExcelInput);
		log.info(ExcelInput+ " is initiated");
	}

@Test
public void testDataCreationForReferenceDocument() throws Exception {
	log.info("####################### "+ObjectType+ " Test Data Creation Script Execution started   ##################################"  );
	reportLog.info("####################### "+ObjectType+ " Test Data Creation Script Execution started  ##################################"  );

	int initialRowNumber = Config.referenceDocumentTestDataSuiteInitialRowNumber-1;
	for (int i = initialRowNumber; i <= excel.getRowCount(ObjectType); i++) {
		
		log.info("####################### Execution Started for the Row Number "+ (i+1) +" in " +ObjectType+" Sheet ##################################"  );
		reportLog.info("####################### Execution Started for the Row Number "+ (i+1) +" in " +ObjectType+" Sheet ##################################"  );
		
		
		currentRole = excel.getData(ObjectType, i, "Role").trim();
		currentTemplate = excel.getData(ObjectType, i, "Product / Library Template").trim();
		status = excel.getData(ObjectType, i, "Status").trim();
		
		log.info("Object Type is : "+ObjectType + " and Current Template is : " + currentTemplate + " and Status of the "+ ObjectType   + " is : "+ status);
		reportLog.info("Object Type is : "+ObjectType + " and Current Template is : " + currentTemplate + " and Status of the "+ ObjectType   + " is : "+ status);
		
		String deleteAccess = excel.getData(ObjectType, i, "Delete").trim();
		String reviseAccess = excel.getData(ObjectType, i, "Revise").trim();
		String setStateAccess = excel.getData(ObjectType, i, "Set State").trim();
		String changeDomainAccess = excel.getData(ObjectType, i, "Change Domain").trim();
		String changeContextAccess = excel.getData(ObjectType, i, "Change Context").trim();
		
			if(currentTemplate.contains("Product")){
				   currentRole = "Product Manager";
				  } else if(currentTemplate.contains("Library")){
				   currentRole = "Library Manager";
				  }
			
			if(currentTemplate.contains("Restricted")){
				filename = Config.RESTRICTED+Config.REFERENCE_DOCUMENT_NAME;
				 
				  }else{
					  
					  filename = Config.REFERENCE_DOCUMENT_NAME;
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
				if(reviseAccess.equals("P")){
					k=k+1;
				}
				if(setStateAccess.equals("P")){
					k=k+1;
				}
				if(changeDomainAccess.equals("P")){
					k=k+1;
				}
				if(changeContextAccess.equals("P")){
					k=k+1;
				}
				
				log.info("No of Records required for " +(i+1)+" row is "+k);
				
				for( int j=1;j<=k;j++)	{	
										
					documentName = testdataUtil.createDocumentAccessRightValidation(ObjectType,filename);
					log.info("Created data set Name is : "+documentName);
									
						if (!documentName.equals(null)) {
							
							reportLog.debug("Created data set Name is : "+documentName);
							
					if(!status.equals("In Creation")){
						
						if(testdataUtil.setStateRightAccessValidation(documentName, ObjectType,currentTemplate,Config.fetchContainerOrLibrary(currentTemplate), status)){
							
							reportLog.debug("data set has changed to : " +status + " status ");
							
						}else{
							
							reportLog.error("data set has not changed to : " +status + " status ");
							
						}
					}else{
						
						reportLog.info("expected state is " +status + " status hence No need to change the state");
						
					}
					
				}else{
					log.info("data set is not created. Set State action cannot be executed");
					reportLog.info("data set is not created. Set State action cannot be executed");
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
