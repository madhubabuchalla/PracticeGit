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
 * 30-Oct-2016
 */
public class ProblemReportTestDataCreation {
	
	static Logger log = Logger.getLogger(ProblemReportTestDataCreation.class);
	private static final Logger reportLog = Logger.getLogger("reportsLogger");
	String ObjectType = Config.PROBLEM_REPORT;
	String documentName = null;
	String currentRole = null;
	String currentTemplate = null;
	String status = null;
	Authentication auth = new Authentication();
	String filename=Config.DESCRIBE_DOCUMENT_NAME;
	TestDataCreationUtil testdataUtil = new TestDataCreationUtil();

	ExcelUtil excel = null;
	

	String ExcelInput = Config.NEXTGEN_ACCESS_RIGHTS_MANAGEMENT_FILEPATH;

	@BeforeClass
	public void init() {
		excel = new ExcelUtil(ExcelInput);
		log.info(ExcelInput+ " is initiated");
	}

@Test
public void testDataCreationForProblemReport() throws Exception {
	log.info("####################### "+ObjectType+ " Test Data Creation Script Execution started  ##################################"  );
	reportLog.info("####################### "+ObjectType+ " Test Data Creation Script Execution started  ##################################"  );
	
	int initialRowNumber = Config.problemReportTestDataSuiteInitialRowNumber-1;
	for (int i = initialRowNumber; i <= excel.getRowCount(ObjectType); i++) {
	
		log.info("####################### Execution Started for the Row Number "+ (i+1) +" in " +ObjectType+" Sheet ##################################"  );
		reportLog.info("####################### Execution Started for the Row Number "+ (i+1) +" in " +ObjectType+" Sheet ##################################"  );
		
		
		currentRole = excel.getData(ObjectType, i, "Role").trim();
		currentTemplate = excel.getData(ObjectType, i, "Product / Library Template").trim();
		status = excel.getData(ObjectType, i, "Status").trim();
		
		log.info("Object Type is : "+ObjectType + " and Current Template is : " + currentTemplate + " and Status of the "+ ObjectType   + " is : "+ status);
		reportLog.info("Object Type is : "+ObjectType + " and Current Template is : " + currentTemplate + " and Status of the "+ ObjectType   + " is : "+ status);
		
		String setStateAccess = excel.getData(ObjectType, i, "Set State").trim();
		String deleteAccess  = excel.getData(ObjectType, i, "Delete").trim();

	
			if(currentTemplate.contains("Product")){
				   currentRole = "Product Manager";
				  } else if(currentTemplate.contains("Library")){
				   currentRole = "Library Manager";
				  }
			
			if(currentTemplate.contains("Restricted")){
				documentName = Config.RESTRICTED + Config.PROBLEM_REPORT_NAME;
				  }else{
					  documentName = Config.PROBLEM_REPORT_NAME; 
				  }
			
			
			
			 String loggedInUserRole = auth.getUserRole(currentTemplate);
			 
			System.out.println("Logged in user role : "+loggedInUserRole);
			
			if (loggedInUserRole.equalsIgnoreCase(Config.PRODUCT_MANAGER)||loggedInUserRole.equalsIgnoreCase(Config.LIBRARY_MANAGER)){
				
				if(WindChillAccessRightsValidationUtil.navigateToRequiredLocation(currentTemplate, Config.fetchContainerOrLibrary(currentTemplate))){
				
				log.info("User is Navigated to " + currentTemplate +" and "+ Config.fetchContainerOrLibrary(currentTemplate));
			
			GenericFunctionLibrary.refreshWindow();
			Thread.sleep(2000);
			GenericFunctionLibrary.refreshWindow();
			Thread.sleep(3000);
			
			
		int k=1;
		if(deleteAccess.equals("P")){
			k=k+1;
		}
		if(setStateAccess.equals("P")){
			k=k+1;
		}
				
						
				for(int j=1;j<=k;j++) {		
					
					log.info("Required objects required is : "+ k);
							
					 String fileName=testdataUtil.createProblemReportAccessRightValidation(documentName);
					 log.info("Created data set Name is : "+filename);
						
						
							if (!filename.equals(null)) {
								
								reportLog.debug("Created data set Name is : "+filename);
								
					if(!status.equals("Under Review"))	{	
						
						GenericFunctionLibrary.refreshWindow();
						Thread.sleep(2000);
						GenericFunctionLibrary.refreshWindow();
						Thread.sleep(3000);
						
						if(testdataUtil.setStateRightAccessValidationWithAdvSearchExeptPartandDoc(fileName, status, ObjectType, currentTemplate, Config.fetchContainerOrLibrary(currentTemplate))){
							
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


