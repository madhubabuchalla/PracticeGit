package com.schneider.windchillaccessrightsvalidation.folder;

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
import com.schneider.windchillaccessrightsvalidation.utilities.WindChillAccessRightsValidationUtil;
import com.schneider.windchillaccessrightsvalidation.utilities.ReportGenerator;

public class FolderAccessRightsValidationDriverScript extends ReportGenerator {

	
	static Logger log = Logger.getLogger("FolderAccessRightsValidationDriverScript");
	private static ExtentTest report = null;
	private String objectType = Config.FOLDER;	
	private String sheetName = Config.SSO_USER_ROLE;	
	String objectName = "";
	FolderAccessRightsValidationSuite FolderSuite = new FolderAccessRightsValidationSuite();
	String status = null;
	String ADVANCE_SEARCH_BY_NAME = Config.FOLDER_NAME;
	String currentRole = null;
	String currentTemplate = null;
	
	//String status = null;
	Authentication auth = new Authentication();
	
	ExcelUtil excel = null;
	
	String excelInput = Config.SSO_NEXTGEN_ACCESS_RIGHTS_MANAGEMENT_RESULT_FILE_PATH_FOLDER;

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
public void TestAllAccessRightsAutomationForFolder() throws Exception {
	log.info("####################### "+objectType+ "Driver Script execution started  ##################################"  );
	
	int folderInirialRowNumber = Config.folderSuiteInitialRowNumber-1;
	
	for (int i = folderInirialRowNumber; i <= excel.getRowCount(sheetName); i++) {
		
		 report = extent.createTest(objectType + " : Row Number : "+ (i+1) , " Role is : "+ Config.SSO_USER_ROLE );
		
		log.info("####################### Execution Started for the Row Number "+ (i+1) +" in " +objectType+" Sheet ##################################"  );
		
		currentRole = excel.getData(sheetName, i, "Role").trim();
		currentTemplate = excel.getData(sheetName, i, "Product / Library Template").trim();
		
		status = "No status for "+ objectType;
		
		String loggedInUserRole = auth.getUserRole(currentTemplate);
 		log.info("Current Logged in user : "+ loggedInUserRole);
 		
		if (loggedInUserRole.equalsIgnoreCase(Config.SSO_USER_ROLE)){
			
			GenericFunctionLibrary.refreshWindow();
			Thread.sleep(2000);
			GenericFunctionLibrary.refreshWindow();
			Thread.sleep(2000);
			
			if(WindChillAccessRightsValidationUtil.navigateToRequiredLocation(currentTemplate, Config.fetchContainerOrLibrary(currentTemplate))){
			
				log.info("User is Navigated to " + currentTemplate +" and "+ Config.fetchContainerOrLibrary(currentTemplate));
				
			log.info("Current Role is : "+loggedInUserRole + " and Current Template is : " + currentTemplate + " and Status of the "+ objectName   + " is : "+ status);
			
			String rowInfo = " Current Role is : "+loggedInUserRole + ";  Current Template is : " + currentTemplate + " --> "+ Config.fetchContainerOrLibrary(currentTemplate) +";  Status of the "+ objectName   + " is : "+ status +";" ;
			
			log.info(rowInfo);
			
			// Expected Access Rights mapped in the Input Excel 
			
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
		
					// Create Folder Access Validation Script will execute

					if (createAccess.equalsIgnoreCase("NA")) {

						String comment = "Create :  The Create access right is marked with NA Hence 'CREATE' Script has been Skipped ";
						excel.writeResult(report, createAccess, "NA", sheetName, i, "Create", rowInfoAndExpectedAccessRights, comment);

						log.info(comment);

						extent.flush();
					} else {

						String[] validationResult = FolderSuite.verifyCreateAccessRightForFolder(currentTemplate);

						excel.writeResult(report, createAccess, validationResult[0], sheetName, i, "Create",rowInfoAndExpectedAccessRights, validationResult[1]);
						extent.flush();
					}

					Thread.sleep(3000);
					GenericFunctionLibrary.refreshWindow();
					Thread.sleep(3000);
					GenericFunctionLibrary.refreshWindow();
					Thread.sleep(3000);

					// Modify Folder Access Validation Script will execute

					if (modifyAccess.equalsIgnoreCase("NA")) {
						String comment = " Modify : The Modify access right is mentioned with NA Hence 'MODIFY' Script has been Skipped ";
						excel.writeResult(report, modifyAccess, "NA", sheetName, i, "Modify",
								rowInfoAndExpectedAccessRights, comment);
						log.info(comment);
						extent.flush();

					} else {

						String[] validationResult = FolderSuite.verifyModifyAccessRightForFolder(currentTemplate);
						excel.writeResult(report, modifyAccess, validationResult[0], sheetName, i, "Modify",
								rowInfoAndExpectedAccessRights, validationResult[1]);

						extent.flush();
					}

				Thread.sleep(3000);
				GenericFunctionLibrary.refreshWindow();
				Thread.sleep(3000);
				GenericFunctionLibrary.refreshWindow();
				Thread.sleep(3000);

			// Delete Access Validation Script will execute
			 

			
				if (deleteAccess.equalsIgnoreCase("NA")) {
					String comment = " Delete : The Delete access right is mentioned with NA Hence 'DELETE' Script has been Skipped ";						
					excel.writeResult(report, deleteAccess, "NA", sheetName, i, "Delete", rowInfoAndExpectedAccessRights, comment);
					log.info(comment);	
					
					extent.flush();			
					}else{
												
					String[] validationResult  = FolderSuite.verifyDeleteAccessRightForFolder(currentTemplate);
					excel.writeResult(report, deleteAccess, validationResult[0], sheetName, i, "Delete", rowInfoAndExpectedAccessRights, validationResult[1]);
					extent.flush();
			}
	
// Read Access Permission is Not Applicable for Folder
				
			String readComment =" Read access right is NA for for "+objectType;
			excel.writeResult(report, readAccess, "NA", sheetName, i, "Read", rowInfoAndExpectedAccessRights, readComment);
			
			log.info(readComment);
			extent.flush();
			
// Create by move Access Permission is Not Applicable for Folder
			
			String createByMoveComment = "Create by move access right is NA for all object types. You can't test it directly and it is internal access right for Change Domaian and Change Context";
			excel.writeResult(report, createByMoveAccess, "NA", sheetName, i, "Create by move", rowInfoAndExpectedAccessRights,createByMoveComment);
			log.info(createByMoveComment);
			
			extent.flush();
			
// Download Access Permission is Not Applicable for Folder
			
			String downloadComment = "Download access right is NA for " + objectType+ " Hence 'DOWNLOAD' Script has been Skipped";
			excel.writeResult(report, downloadAccess, "NA", sheetName, i, "Download", rowInfoAndExpectedAccessRights, downloadComment);
			log.info(downloadComment);
			
			extent.flush();
			
//Modify Content Access Permission is Not Applicable for Folder
			
			String modifyContentComment = "Modify Content access right is NA for " + objectType + " Hence 'MODIFY CONTENT' Script has been Skipped";
			excel.writeResult(report, modifyContentAccess, "NA", sheetName, i, "Modify Content", rowInfoAndExpectedAccessRights, modifyContentComment);
			log.info(modifyContentComment);		
			extent.flush();
			
	// Modify Identity Access Permission is Not Applicable for Folder
			String ModifyIdentityComment = "Modify Identity access right is NA for "+objectType+" Hence 'MODIFY IDENTITY' Script has been Skipped";
			excel.writeResult(report, modifyIdentityAccess, "NA", sheetName, i, "Modify Identity", rowInfoAndExpectedAccessRights, ModifyIdentityComment);
			
			log.info(ModifyIdentityComment);
			extent.flush();
			
	// revise Access Permission is Not Applicable for Folder
			
			String reviseComment = "Revise access right is NA for "+objectType+" Hence 'REVISE' Script has been Skipped";
			excel.writeResult(report, reviseAccess, "NA", sheetName, i, "Revise", rowInfoAndExpectedAccessRights, reviseComment);
				
			log.info(reviseComment);	
			extent.flush();
			
	// Set State Access Permission is Not Applicable for Folder
			
			String setStateComment = "Set State access right is NA for " + objectType + " Hence 'SET STATE' Script has been Skipped";
			excel.writeResult(report, setStateAccess, "NA", sheetName, i, "Set State",	rowInfoAndExpectedAccessRights, setStateComment);
			log.info(setStateComment);
			extent.flush();	
	
	// Change Context Access Permission is Not Applicable Folder
			
			String changeContextcomment = " Change Context access right is NA for "+objectType+" Hence 'CHANGE CONTEXT' Script has been Skipped";
			excel.writeResult(report, changeContextAccess, "NA", sheetName, i, "Change Context",	rowInfoAndExpectedAccessRights, changeContextcomment);
						
			log.info(changeContextcomment);
			extent.flush();	
			
			
	//  	Change Domain Access Permission is Not Applicable for Folder	
			
			String changeDoamincomment = " Change Domain access right is NA for "+objectType+" Hence 'CHANGE DOMAIN' Script has been Skipped";
			excel.writeResult(report, changeDomainAccess, "NA", sheetName, i, "Change Domain",	rowInfoAndExpectedAccessRights, changeDoamincomment);
			
			log.info(changeDoamincomment);
			extent.flush();
			
			
			}else{
				String comment = "User is unable to navigated to "+ Config.fetchContainerOrLibrary(currentTemplate);
				  log.info(comment);
			       
			}
			
		}else{
			String comment = "User is not Valid since this User ID is not mapped in Authentication Details.xls";
			log.info(comment);
			
			report.log(Status.WARNING, comment);
			extent.flush();
			
		}
		
			log.info("####################### Execution Completed for the Row Number "+ (i+1) +" in " +objectType+" Sheet ##################################"  );
			
			
			extent.flush();
		} // for loop end
			
		}

	
}

