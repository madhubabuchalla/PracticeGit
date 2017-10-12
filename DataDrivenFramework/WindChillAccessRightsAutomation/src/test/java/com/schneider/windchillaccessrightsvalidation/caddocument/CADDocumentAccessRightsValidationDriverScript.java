package com.schneider.windchillaccessrightsvalidation.caddocument;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.schneider.windchillaccessrightsvalidation.genericlibraries.Authentication;
import com.schneider.windchillaccessrightsvalidation.genericlibraries.ClearCache;
import com.schneider.windchillaccessrightsvalidation.genericlibraries.Config;
import com.schneider.windchillaccessrightsvalidation.genericlibraries.GenericFunctionLibrary;
import com.schneider.windchillaccessrightsvalidation.utilities.ExcelUtil;
import com.schneider.windchillaccessrightsvalidation.utilities.FileUtil;
import com.schneider.windchillaccessrightsvalidation.utilities.ReportGenerator;

public class CADDocumentAccessRightsValidationDriverScript extends ReportGenerator {

static Logger log = Logger.getLogger(CADDocumentAccessRightsValidationDriverScript.class);
private static ExtentTest reporter = null;
	private String objectType = Config.CAD_PART;
	String objectName = null;
	
	private String sheetName = Config.SSO_USER_ROLE;	
	CADDocumentAccessRightsValidationSuite CADDocumentSuite = new CADDocumentAccessRightsValidationSuite();
	
	String currentRole = null;
	String currentTemplate = null;
	String status = null;
	Authentication auth = new Authentication();
	
	ExcelUtil excel = null;
	
	String excelInput = Config.SSO_NEXTGEN_ACCESS_RIGHTS_MANAGEMENT_RESULT_FILE_PATH_CAD_DOCUMENT_PART;

	@BeforeClass
	public void init() throws Exception{
		if(ClearCache.cleanTemp()==0){
		String resultfile = FileUtil.copyFile(objectType, sheetName, excelInput);
		Thread.sleep(5000);
		
		excel = new ExcelUtil(resultfile);
		log.info(resultfile+ " is initiated");
		
		}
	}

@Test    
public void TestAllAccessRightsAutomationForCADDocument() throws Exception {
	log.info("####################### "+objectType+ "Driver Script execution started  ##################################"  );
	
	int initialRowNumber = Config.CADPartSuiteInitialRowNumber-1;
	for (int i = initialRowNumber; i <= excel.getRowCount(sheetName); i++) {
		
		reporter = extent.createTest(objectType + " : Row Number : "+ (i+1) , " Role is : "+ Config.SSO_USER_ROLE );
					
		log.info("####################### Execution Started for the Row Number "+ (i+1) +" in " +objectType+" Sheet ##################################"  );
				
		objectName = excel.getData(sheetName, i, "Object Name").trim();
		currentRole = excel.getData(sheetName, i, "Role").trim();
		currentTemplate = excel.getData(sheetName, i, "Product / Library Template").trim();
		status = excel.getData(sheetName, i, "Status").trim();
		
		String loggedInUserRole = auth.getUserRole(currentTemplate);
 		log.info("Current Logged in user : "+ loggedInUserRole);
 		
		if (loggedInUserRole.equalsIgnoreCase(Config.SSO_USER_ROLE)){
		
			GenericFunctionLibrary.refreshWindow();
			Thread.sleep(2000);
			GenericFunctionLibrary.refreshWindow();
			Thread.sleep(2000);
			
			String rowInfo = " Current Role is : "+loggedInUserRole + ";  Current Template is : " + currentTemplate + " --> "+ Config.fetchContainerOrLibrary(currentTemplate) +";  Status of the "+ objectName   + " is : "+ status +";" ;
			
			log.info(rowInfo);

					
		String createAccess = excel.getData(sheetName, i, "Create").trim();
		String createByMoveAccess = excel.getData(sheetName, i, "Create by move").trim();
		String readAccess = excel.getData(sheetName, i, "Read").trim();
		String downloadAccess = excel.getData(sheetName, i, "Download").trim();
		String modifyAccess = excel.getData(sheetName, i, "Modify").trim();
		String modifyContentAccess = excel.getData(sheetName, i, "Modify Content").trim();
		String modifyIdentityAccess = excel.getData(sheetName, i, "Modify Identity").trim();
		String deleteAccess = excel.getData(sheetName, i, "Delete").trim();
		String reviseAccess = excel.getData(sheetName, i, "Revise").trim();
		String setStateAccess = excel.getData(sheetName, i, "Set State").trim();
		String changeDomainAccess = excel.getData(sheetName, i, "Change Domain").trim();
		String changeContextAccess = excel.getData(sheetName, i, "Change Context").trim();
	

		String expectedAccessRights = "Expected Access Rights are ::  Create is : " + createAccess + "; " 
				+ "  Read is : " + readAccess + "; " + "  Download is : " + downloadAccess + "; " + "  Modify is : "
				+ modifyAccess + "; " + "  Modify Content is : " + modifyContentAccess + "; "
				+ "  Modify Identity is : " + modifyIdentityAccess + "; " + "  Delete is :" + deleteAccess + "; "
				+ "  Revise is : " + reviseAccess + "; " + "  Set State is : " + setStateAccess + "; "
				+ "  Change By Domain : "+changeDomainAccess +"; " + "  Change By Context : " + changeContextAccess+"; ";

		log.info(expectedAccessRights);
		
				
		String rowInfoAndExpectedAccessRights = rowInfo +"<br> <br>"+ expectedAccessRights +"<br> <br>";

		
	//  Create Access Validation Script will execute 
		 
		if(createAccess.equalsIgnoreCase("NA")){
			String comment = "Create :  The Create access right is marked with NA Hence 'CREATE' Script has been Skipped ";
			excel.writeResult(reporter, createAccess, "NA", sheetName, i, "Create", rowInfoAndExpectedAccessRights,  comment);

			log.info(comment);
			
			extent.flush();
		}else if(!status.equalsIgnoreCase("In Creation")){
			String comment = " Create : Create Action is NOT Applicable for the "+status+" State of "+ objectType+". Create script will execute for only 'In Creation State' of the "+objectName+". Hence 'CREATE' Script has been Skipped ";
			excel.writeResult(reporter, createAccess, "NA", sheetName, i, "Create", rowInfoAndExpectedAccessRights,  comment);

			log.info(comment);
			
			extent.flush();
			}else{
		
			String validationResult[]  = CADDocumentSuite.verifyCreateAccessRightForCADDocument(objectType, currentTemplate, Config.fetchContainerOrLibrary(currentTemplate));

			excel.writeResult(reporter, createAccess, validationResult[0], sheetName, i, "Create", rowInfoAndExpectedAccessRights,  validationResult[1]);		
			extent.flush();
					
	}
 
		 
		Thread.sleep(3000);
		GenericFunctionLibrary.refreshWindow();
		Thread.sleep(3000);
		GenericFunctionLibrary.refreshWindow();
		Thread.sleep(3000);

		
	//  Read Access Validation Script will execute 
	
		if (readAccess.equalsIgnoreCase("NA")) {
			String comment = "Read : The Read access right is mentioned with NA Hence 'READ' Script has been Skipped";
			excel.writeResult(reporter,readAccess, "NA", sheetName, i, "Read", rowInfoAndExpectedAccessRights, comment);
			log.info(comment);
			extent.flush();

		}else{
					
					
		String validationResult[]  = CADDocumentSuite.verifyReadAccessRightForCADDocument(objectType, Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status, Config.CAD_PART_NAME);
		excel.writeResult(reporter,readAccess, validationResult[0], sheetName, i, "Read", rowInfoAndExpectedAccessRights, validationResult[1]);		
		extent.flush();
	}
		

		 Thread.sleep(2000);
		GenericFunctionLibrary.refreshWindow();
		 Thread.sleep(2000);
		 GenericFunctionLibrary.refreshWindow();
		 Thread.sleep(2000);
		
		
		 
		// Modify Access Validation Script will execute 

			if (modifyAccess.equalsIgnoreCase("NA")) {
				
				String comment = " Modify : The Modify access right is mentioned with NA Hence 'MODIFY' Script has been Skipped ";
				excel.writeResult(reporter, modifyAccess, "NA", sheetName, i, "Modify", rowInfoAndExpectedAccessRights, comment);
				log.info(comment);
				
				extent.flush();
			} else {

				String validationResult[] = CADDocumentSuite.verifyModifyAccessRightForCADDocument(objectType,Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status, Config.CAD_PART_NAME);
				excel.writeResult(reporter, modifyAccess, validationResult[0], sheetName, i, "Modify", rowInfoAndExpectedAccessRights, validationResult[1]);
				
				extent.flush();				
						
					}

					 Thread.sleep(2000);
						GenericFunctionLibrary.refreshWindow();
						 Thread.sleep(2000);
						 GenericFunctionLibrary.refreshWindow();
						 Thread.sleep(2000);

    // Modify Identity Access Validation Script will execute based on the flag mentioned in the "Access Rights Excel"

			if (modifyIdentityAccess.equalsIgnoreCase("NA")) {
				String comment = "Modify Identity : The Modify Identity access right is mentioned with NA Hence 'MODIFY IDENTITY' Script has been Skipped";
				excel.writeResult(reporter, modifyIdentityAccess, "NA", sheetName, i, "Modify Identity",
						rowInfoAndExpectedAccessRights, comment);
				log.info(comment);

				extent.flush();
				
			} else {

				String validationResult[] = CADDocumentSuite.verifyModifyIdentityAccessRightForCADDocument(objectType, Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status, Config.CAD_PART_NAME);
				excel.writeResult(reporter, modifyIdentityAccess, validationResult[0], sheetName, i,
						"Modify Identity", rowInfoAndExpectedAccessRights, validationResult[1]);
				extent.flush();	
			}

				 Thread.sleep(2000);
					GenericFunctionLibrary.refreshWindow();
					 Thread.sleep(2000);
					 GenericFunctionLibrary.refreshWindow();
					 Thread.sleep(2000);

		// Revise Access Validation Script will execute 
						
			if (reviseAccess.equalsIgnoreCase("NA")) {
				String comment = " Revise : The Revise access right is mentioned with NA Hence 'REVISE' Script has been Skipped ";
				excel.writeResult(reporter, reviseAccess, "NA", sheetName, i, "Revise", rowInfoAndExpectedAccessRights, comment);
				log.info(comment);	
					
				extent.flush();
			}else{
									
								
			String validationResult[]  = CADDocumentSuite.verifyReviseAccessRightForCADDocument(objectType, Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status, Config.CAD_PART_NAME);
			excel.writeResult(reporter, reviseAccess, validationResult[0], sheetName, i, "Revise", rowInfoAndExpectedAccessRights, validationResult[1]);
			extent.flush();				
	}
			 Thread.sleep(2000);
				GenericFunctionLibrary.refreshWindow();
				 Thread.sleep(2000);
				 GenericFunctionLibrary.refreshWindow();
				 Thread.sleep(2000);
			
										 
			// Set State Access Validation Script will execute 
			 
			if (setStateAccess.equalsIgnoreCase("NA")) {
				String comment  = "Set State : The Set State access right is mentioned with NA Hence 'SET STATE' Script has been Skipped";							
				excel.writeResult(reporter, setStateAccess, "NA", sheetName, i, "Set State", rowInfoAndExpectedAccessRights, comment);
				log.info(comment);	
				
				extent.flush();	
			} else {

				String validationResult[] = CADDocumentSuite.verifySetStateAccessRightForCADDocument(objectType,Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status,	Config.CAD_PART_NAME);
				excel.writeResult(reporter, setStateAccess, validationResult[0], sheetName, i, "Set State", rowInfoAndExpectedAccessRights, validationResult[1]);
				extent.flush();				
		}

		
			 Thread.sleep(2000);
				GenericFunctionLibrary.refreshWindow();
				 Thread.sleep(2000);
				 GenericFunctionLibrary.refreshWindow();
				 Thread.sleep(2000);
		
			
				
				
				//Delete Access Validation Script will execute
		
				if (deleteAccess.equalsIgnoreCase("NA")) {
					String comment = " Delete : The Delete access right is mentioned with NA Hence 'DELETE' Script has been Skipped ";						
					excel.writeResult(reporter, deleteAccess, "NA", sheetName, i, "Delete", rowInfoAndExpectedAccessRights, comment);
					log.info(comment);	
					
					extent.flush();
				}else{
								
								
					String validationResult[]  = CADDocumentSuite.verifyDeleteAccessRightForCADDocument(objectType, Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status, Config.CAD_PART_NAME);
					 
					excel.writeResult(reporter, deleteAccess, validationResult[0], sheetName, i, "Delete", rowInfoAndExpectedAccessRights, validationResult[1]);
					extent.flush();
            	}
				
				 	Thread.sleep(2000);
					GenericFunctionLibrary.refreshWindow();
					 Thread.sleep(2000);
					 GenericFunctionLibrary.refreshWindow();
					 Thread.sleep(2000);

// Change Domain Access Validation Script will execute based on the flag mentioned in the "Access Rights Excel"

				if (changeDomainAccess.equalsIgnoreCase("NA")) {

					String comment = "Change Domain : The Change Domain access right is mentioned with NA Hence 'CHANGE DOMAIN' Script has been Skipped";
					excel.writeResult(reporter, changeDomainAccess, "NA", sheetName, i, "Change Domain", rowInfoAndExpectedAccessRights, comment);
					log.info(comment);		
					extent.flush();
					
				} else {

					String validationResult[] = CADDocumentSuite.verifyChangeDomainAccessRightForCADDocument(objectType,
							Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status,
							Config.CAD_PART_NAME);
					 excel.writeResult(reporter, changeDomainAccess, validationResult[0], sheetName, i, "Change Domain", rowInfoAndExpectedAccessRights, validationResult[1]);	
					 extent.flush();
				}

				Thread.sleep(2000);
				GenericFunctionLibrary.refreshWindow();
				Thread.sleep(2000);
				GenericFunctionLibrary.refreshWindow();
				Thread.sleep(2000);
						
				// Change Context Access Validation Script will execute based on the flag mentioned in the "Access Rights Excel"
				 
			if (changeContextAccess.equalsIgnoreCase("NA")) {

				String comment = "Change Context : The Change Context access right is mentioned with NA Hence 'CHANGE CONTEXT' Script has been Skipped";
				excel.writeResult(reporter, changeContextAccess, "NA", sheetName, i, "Change Context", rowInfoAndExpectedAccessRights, comment);
				log.info(comment);		
				extent.flush();	
				
			} else {

				String validationResult[] = CADDocumentSuite.verifyChangeContextAccessRightForCADDocument(objectType,	Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status, Config.CAD_PART_NAME);
				excel.writeResult(reporter, changeContextAccess, validationResult[0], sheetName, i, "Change Context", rowInfoAndExpectedAccessRights, validationResult[1]);
				extent.flush();				
		}
			
				

			// Create by move Access Permission is Not Applicable for CADDocument Part
			String createByMoveComment = "Create by move access right is NA for all object types. You can't test it directly and it is internal access right for Change Domaian and Change Context";
				excel.writeResult(reporter, createByMoveAccess, "NA", sheetName, i, "Create by move", rowInfoAndExpectedAccessRights,createByMoveComment);
			    log.info(createByMoveComment);
				extent.flush();
				
			// Download Access Permission is Not Applicable for CADDocument Part
			String downloadComment = "Download access right is NA for " + objectType+ " Hence 'DOWNLOAD' Script has been Skipped";
					excel.writeResult(reporter, downloadAccess, "NA", sheetName, i, "Download", rowInfoAndExpectedAccessRights, downloadComment);
					log.info(downloadComment);
					extent.flush();
					
			//Modify Content Access Permission is Not Applicable for CADDocument Part
			String modifyContentComment = "Modify Content access right is NA for " + objectType + " Hence 'MODIFY CONTENT' Script has been Skipped";
					excel.writeResult(reporter, modifyContentAccess, "NA", sheetName, i, "Modify Content", rowInfoAndExpectedAccessRights, modifyContentComment);
					log.info(modifyContentComment);	
					extent.flush();
		
				
						
			}else{
				
				String comment = "User is not Valid since this User ID is not mapped in Authentication Details.xls";
				log.info(comment);
				
				reporter.log(Status.WARNING, comment);
				extent.flush();
				
			}
			
				log.info("####################### Execution Completed for the Row Number "+ (i+1) +" in " +objectType+" Sheet ##################################"  );
				
				
				extent.flush();
			} // for loop end
				
			}

		
	}