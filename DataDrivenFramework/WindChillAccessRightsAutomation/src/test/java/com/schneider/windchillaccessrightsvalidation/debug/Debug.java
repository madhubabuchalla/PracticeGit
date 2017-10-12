package com.schneider.windchillaccessrightsvalidation.debug;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.schneider.windchillaccessrightsvalidation.caddocument.CADDocumentAccessRightsValidationSuite;
import com.schneider.windchillaccessrightsvalidation.catalogpart.CatalogPartAccessRightsValidationSuite;
import com.schneider.windchillaccessrightsvalidation.changenotice.ChangeNoticeAccessRightsValidationSuite;
import com.schneider.windchillaccessrightsvalidation.changetask.ChangeTaskAccessRightsValidationSuite;
import com.schneider.windchillaccessrightsvalidation.describedocument.DescribeDocumentAccessRightsValidationSuite;
import com.schneider.windchillaccessrightsvalidation.engineeredpart.EngineeredPartAccessRightsValidationSuite;
import com.schneider.windchillaccessrightsvalidation.folder.FolderAccessRightsValidationSuite;
import com.schneider.windchillaccessrightsvalidation.genericlibraries.Authentication;
import com.schneider.windchillaccessrightsvalidation.genericlibraries.ClearCache;
import com.schneider.windchillaccessrightsvalidation.genericlibraries.Config;
import com.schneider.windchillaccessrightsvalidation.genericlibraries.GenericFunctionLibrary;
import com.schneider.windchillaccessrightsvalidation.managedcollection.ManagedCollectionAccessRightsValidationSuite;
import com.schneider.windchillaccessrightsvalidation.manufacturerpart.ManufacturerPartAccessRightsValidationSuite;
import com.schneider.windchillaccessrightsvalidation.mountingcontextpart.MountingContextPartAccessRightValidationSuite;
import com.schneider.windchillaccessrightsvalidation.objectlist.ObjectListPartAccessRightsValidationSuite;
import com.schneider.windchillaccessrightsvalidation.problemreport.ProblemReportAccessRightsValidationSuite;
import com.schneider.windchillaccessrightsvalidation.promotionrequest.PromotionRequestAccessRightsValidationSuite;
import com.schneider.windchillaccessrightsvalidation.rawmaterialpart.RawMaterialPartAccessRightsValidationSuite;
import com.schneider.windchillaccessrightsvalidation.referencedocument.ReferenceDocumentAccessRightsValidationSuite;
import com.schneider.windchillaccessrightsvalidation.treatmentpart.TreatmentPartAccessRightsValidationSuite;
import com.schneider.windchillaccessrightsvalidation.utilities.ExcelUtil;
import com.schneider.windchillaccessrightsvalidation.utilities.FileUtil;
import com.schneider.windchillaccessrightsvalidation.utilities.WindChillAccessRightsValidationUtil;
import com.schneider.windchillaccessrightsvalidation.utilities.ReportGenerator;

/**
 * @author MADHUBABU 
 * 03-Jan-2017
 */

public class Debug extends ReportGenerator {
private static Logger log = Logger.getLogger("Debug");
private static ExtentTest report = null;
private String objectType = Config.DEBUG;
private String sheetName = Config.DEBUG;
String ADVANCE_SEARCH_BY_NAME = "";
String currentRole = "";
String currentTemplate = "";
String status = "";
String objectName = "";
Authentication auth = new Authentication();

ExcelUtil excel = null;

 String excelInput = Config.SSO_NEXTGEN_ACCESS_RIGHTS_MANAGEMENT_RESULT_FILE_DEBUG;
 

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
public void TestAllAccessRightsAutomationForSpecificDocumentType() throws Exception {
	log.info("####################### "+objectType+ "Driver Script execution started  ##################################"  );
	
for (int i = 2; i <= excel.getRowCount(sheetName); i++) {
	
	 report = extent.createTest(objectType + " : Row Number : "+ (i+1) , " Role is : "+ Config.SSO_USER_ROLE );
	 
	log.info("####################### Execution Started for the Row Number "+ (i+1) +" in " +objectType+" Sheet ##################################"  );
	
	objectName = excel.getData(objectType, i, "Object Name");
	status = excel.getData(objectType, i, "Status").trim();
	currentRole = excel.getData(sheetName, i, "Role").trim();
	currentTemplate = excel.getData(sheetName, i, "Product / Library Template").trim();
	
	String loggedInUserRole = auth.getUserRole(currentTemplate);
		log.info("Current Logged in user : "+ loggedInUserRole);
		
	if (loggedInUserRole.equalsIgnoreCase(Config.SSO_USER_ROLE)){
		
			Thread.sleep(2000);
			GenericFunctionLibrary.refreshWindow();
			 Thread.sleep(2000);
			 GenericFunctionLibrary.refreshWindow();
			 Thread.sleep(2000);
		
		if(WindChillAccessRightsValidationUtil.navigateToRequiredLocation(currentTemplate, Config.fetchContainerOrLibrary(currentTemplate))){
		
			log.info("User is Navigated to " + currentTemplate +" and "+ Config.fetchContainerOrLibrary(currentTemplate));
	
			log.info("Current Role is : "+currentRole + " and Current Template is : " + currentTemplate + " and Status of the document is : " + status);
			
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

	//	String AdministrativeAccess = excel.getData(Object, i, "Administrative").trim();

	String expectedAccessRights = "Expected Access Rights are ::  Create is : " + createAccess + "; " 
			+ "  Read is : " + readAccess + "; " + "  Download is : " + downloadAccess + "; " + "  Modify is : "
			+ modifyAccess + "; " + "  Modify Content is : " + modifyContentAccess + "; "
			+ "  Modify Identity is : " + modifyIdentityAccess + "; " + "  Delete is :" + deleteAccess + "; "
			+ "  Revise is : " + reviseAccess + "; " + "  Set State is : " + setStateAccess + "; "
			+ "  Change By Domain : "+changeDomainAccess +"; " + "  Change By Context : " + changeContextAccess+"; ";

	log.info(expectedAccessRights);
	
			
	String rowInfoAndExpectedAccessRights = rowInfo +"<br> <br>"+ expectedAccessRights +"<br> <br>";
	
	//  Create Access Validation Script will execute 
	
	if(!status.equalsIgnoreCase("In Creation")||createAccess.equalsIgnoreCase("NA")||!status.equalsIgnoreCase("Open")){
		String comment = "Create : the Create access right is mentioned with "+ createAccess+"or Status is "+status+" Hence 'CREATE' Script has been Skipped";
		excel.writeResult(report, createAccess, "NA", sheetName, i, "Create", rowInfoAndExpectedAccessRights,  comment);

		log.info(comment);
	}else{
		
		String[] validationResult = new String[2];
		
			if(objectName.equalsIgnoreCase(Config.DESCRIBE_DOCUMENT)){
				DescribeDocumentAccessRightsValidationSuite describeDocumentSuite = new DescribeDocumentAccessRightsValidationSuite(); 
				validationResult =	describeDocumentSuite.verifyCreateAccessRightForDescribeDocument();
					
				}else if(objectName.equalsIgnoreCase(Config.REFERENCE_DOCUMENT)){
					ReferenceDocumentAccessRightsValidationSuite referenceDocumentSuite = new ReferenceDocumentAccessRightsValidationSuite();
					validationResult =	referenceDocumentSuite.verifyCreateAccessRightForReferenceDocument();
					
				}else if(objectName.equalsIgnoreCase(Config.CAD_PART)){
					CADDocumentAccessRightsValidationSuite CADDocSuite = new CADDocumentAccessRightsValidationSuite();
					validationResult =	CADDocSuite.verifyCreateAccessRightForCADDocument(objectType, currentTemplate, Config.fetchContainerOrLibrary(currentTemplate));

				}else if(objectName.equalsIgnoreCase(Config.CATALOG_PART)){
					CatalogPartAccessRightsValidationSuite CatalogPartSuite = new CatalogPartAccessRightsValidationSuite();
					validationResult =	CatalogPartSuite.verifyCreateAccessRightForCatalogPart();

				}else if(objectName.equalsIgnoreCase(Config.ENGINEERED_PART)){
					EngineeredPartAccessRightsValidationSuite engineeredPartSuite = new EngineeredPartAccessRightsValidationSuite();
					validationResult =	engineeredPartSuite.verifyCreateAccessRightForEngineeredPart();

				}else if(objectName.equalsIgnoreCase(Config.MANUFACTURER_PART)){
					ManufacturerPartAccessRightsValidationSuite manufaturerPartSuite = new ManufacturerPartAccessRightsValidationSuite();
					validationResult =	manufaturerPartSuite.verifyCreateAccessRightForManufacturerPart(objectType);

				}else if(objectName.equalsIgnoreCase(Config.MOUNTING_CONTEXT_PART)){
					MountingContextPartAccessRightValidationSuite MountingContextPartSuite = new MountingContextPartAccessRightValidationSuite();
					validationResult =	MountingContextPartSuite.verifyCreateAccessRightForMountingContextPart();

				}else if(objectName.equalsIgnoreCase(Config.OBJECT_LIST_PART)){
					ObjectListPartAccessRightsValidationSuite ObjectListPartSuite = new ObjectListPartAccessRightsValidationSuite();
					validationResult =	ObjectListPartSuite.verifyCreateAccessRightForObjectListPart();

				}else if(objectName.equalsIgnoreCase(Config.RAWMATERIAL_PART)){
					RawMaterialPartAccessRightsValidationSuite RawMaterialPartSuite = new RawMaterialPartAccessRightsValidationSuite();
					validationResult =	RawMaterialPartSuite.verifyCreateAccessRightForRawMaterialPart();

				}else if(objectName.equalsIgnoreCase(Config.TREATMENT_PART)){
					TreatmentPartAccessRightsValidationSuite treatmentPartSuite = new TreatmentPartAccessRightsValidationSuite();
					validationResult =	treatmentPartSuite.verifyCreateAccessRightForTreatmentPart();
					
				}else if(objectName.equalsIgnoreCase(Config.MANAGED_COLLECTION)){
					ManagedCollectionAccessRightsValidationSuite managedCollectionSuite = new ManagedCollectionAccessRightsValidationSuite();
					validationResult =	managedCollectionSuite.verifyCreateAccessRightForManagedCollection();

				}else if(objectName.equalsIgnoreCase(Config.CHANGE_NOTICE)){
					ChangeNoticeAccessRightsValidationSuite changeNoticeSuite = new ChangeNoticeAccessRightsValidationSuite();
					validationResult = changeNoticeSuite.verifyCreateAccessRightValidationforChangeNotice(Config.fetchContainerOrLibrary(currentTemplate), currentTemplate);
					
               }else if(objectName.equalsIgnoreCase(Config.CHANGE_TASK)){
            	   ChangeTaskAccessRightsValidationSuite changeTaskSuite = new ChangeTaskAccessRightsValidationSuite();
            	   validationResult = changeTaskSuite.verifyCreateAccessRightValidationforChangeTask(Config.CHANGE_NOTICE, Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, "Open", Config.CHANGENOTICE_FOR_CHANGETASK);
				
               }else if(objectName.equalsIgnoreCase(Config.PROMOTION_REQUEST)){
					PromotionRequestAccessRightsValidationSuite promotionRequestSuite = new PromotionRequestAccessRightsValidationSuite();
					validationResult =	promotionRequestSuite.verifyCreateAccessRightForPromotionRequest(Config.DESCRIBE_DOCUMENT, Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, "In Creation", Config.DESCRIBE_DOCUMENT_NAME);

				}else if(objectName.equalsIgnoreCase(Config.PROBLEM_REPORT)){
					ProblemReportAccessRightsValidationSuite problemReportSuite = new ProblemReportAccessRightsValidationSuite();
					validationResult = problemReportSuite.verifyCreateAccessRightForProblemReport();
										
				}else if(objectName.equalsIgnoreCase(Config.FOLDER)){
					FolderAccessRightsValidationSuite folderSuite = new FolderAccessRightsValidationSuite();
					validationResult = folderSuite.verifyCreateAccessRightForFolder(currentTemplate);
					
				}
					
		

		// ********** Writing the results into excel after execution completed ***************************
			
			excel.writeResult(report, createAccess, validationResult[0], sheetName, i, "Create", rowInfoAndExpectedAccessRights,  validationResult[1]);		
			extent.flush();
	}
	 
	GenericFunctionLibrary.refreshWindow();
	Thread.sleep(2000);
	GenericFunctionLibrary.refreshWindow();
	Thread.sleep(2000);
	
	
	// Read Access Validation Script will execute based on the object type mentioned in the "Debug excel workbook"

	if (readAccess.equalsIgnoreCase("NA")) {

		String comment = "Read : The Read access right is mentioned with NA Hence 'READ' Script has been Skipped";
		excel.writeResult(report,readAccess, "NA", sheetName, i, "Read", rowInfoAndExpectedAccessRights, comment);
		log.info(comment);
		
		extent.flush();
	}else{
				
				
			String[] validationResult = new String[2];
				
					if(objectName.equalsIgnoreCase(Config.DESCRIBE_DOCUMENT)){
						DescribeDocumentAccessRightsValidationSuite describeDocumentSuite = new DescribeDocumentAccessRightsValidationSuite(); 
						validationResult =	describeDocumentSuite.verifyReadAccessRightForDescribeDocument(objectName, Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status, ADVANCE_SEARCH_BY_NAME);
							
						}else if(objectName.equalsIgnoreCase(Config.REFERENCE_DOCUMENT)){
							ReferenceDocumentAccessRightsValidationSuite referenceDocumentSuite = new ReferenceDocumentAccessRightsValidationSuite();
							validationResult =	referenceDocumentSuite.verifyReadAccessRightForReferenceDocument(objectName, Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status, ADVANCE_SEARCH_BY_NAME);
						
						}else if(objectName.equalsIgnoreCase(Config.CAD_PART)){
							CADDocumentAccessRightsValidationSuite CADDocSuite = new CADDocumentAccessRightsValidationSuite();
							validationResult =	CADDocSuite.verifyReadAccessRightForCADDocument(objectName, Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status, Config.CAD_PART_NAME);

						}else if(objectName.equalsIgnoreCase(Config.CATALOG_PART)){
							CatalogPartAccessRightsValidationSuite CatalogPartSuite = new CatalogPartAccessRightsValidationSuite();
							validationResult =	CatalogPartSuite.verifyReadAccessRightForCatalogPart(objectName, Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status, ADVANCE_SEARCH_BY_NAME);

						}else if(objectName.equalsIgnoreCase(Config.ENGINEERED_PART)){
							EngineeredPartAccessRightsValidationSuite engineeredPartSuite = new EngineeredPartAccessRightsValidationSuite();
							validationResult =	engineeredPartSuite.verifyReadAccessRightForEngineeredPart(objectName, Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status, ADVANCE_SEARCH_BY_NAME);

						}else if(objectName.equalsIgnoreCase(Config.MANUFACTURER_PART)){
							ManufacturerPartAccessRightsValidationSuite manufaturerPartSuite = new ManufacturerPartAccessRightsValidationSuite();
							validationResult =	manufaturerPartSuite.verifyReadAccessRightForManufacturerPart(objectName, Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status, ADVANCE_SEARCH_BY_NAME);

						}else if(objectName.equalsIgnoreCase(Config.MOUNTING_CONTEXT_PART)){
							MountingContextPartAccessRightValidationSuite MountingContextPartSuite = new MountingContextPartAccessRightValidationSuite();
							validationResult =	MountingContextPartSuite.verifyReadAccessRightForMountingContextPart(objectName, Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status, ADVANCE_SEARCH_BY_NAME);

						}else if(objectName.equalsIgnoreCase(Config.OBJECT_LIST_PART)){
							ObjectListPartAccessRightsValidationSuite ObjectListPartSuite = new ObjectListPartAccessRightsValidationSuite();
							validationResult =	ObjectListPartSuite.verifyReadAccessRightForObjectListPart(objectName, Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status, ADVANCE_SEARCH_BY_NAME);

						}else if(objectName.equalsIgnoreCase(Config.RAWMATERIAL_PART)){
							RawMaterialPartAccessRightsValidationSuite RawMaterialPartSuite = new RawMaterialPartAccessRightsValidationSuite();
							validationResult =	RawMaterialPartSuite.verifyReadAccessRightForRawMaterialPart(objectName, Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status, ADVANCE_SEARCH_BY_NAME);

						}else if(objectName.equalsIgnoreCase(Config.TREATMENT_PART)){
							TreatmentPartAccessRightsValidationSuite treatmentPartSuite = new TreatmentPartAccessRightsValidationSuite();
							validationResult =	treatmentPartSuite.verifyReadAccessRightForTreatmentPart(objectName, Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status, ADVANCE_SEARCH_BY_NAME);
						
						}else if(objectName.equalsIgnoreCase(Config.MANAGED_COLLECTION)){
							ManagedCollectionAccessRightsValidationSuite ManagedCollectionSuite = new ManagedCollectionAccessRightsValidationSuite();
							validationResult =	ManagedCollectionSuite.verifyReadAccessRightForManagedCollection(objectName, Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, ADVANCE_SEARCH_BY_NAME);

						}else if(objectName.equalsIgnoreCase(Config.CHANGE_NOTICE)){
							ChangeNoticeAccessRightsValidationSuite changeNoticeSuite = new ChangeNoticeAccessRightsValidationSuite();
							validationResult = changeNoticeSuite.readChangeNoticeAccessRightValidation(objectName, Config.fetchContainerOrLibrary(currentTemplate), currentTemplate,status, Config.CHANGE_NOTICE_NAME);
							
		               }else if(objectName.equalsIgnoreCase(Config.CHANGE_TASK)){
		            	   ChangeTaskAccessRightsValidationSuite changeTaskSuite = new ChangeTaskAccessRightsValidationSuite();
		            	   validationResult = changeTaskSuite.verifyReadChangeTaskAccessRightValidation(Config.CHANGE_NOTICE, Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, "Open", Config.CHANGENOTICE_FOR_CHANGETASK, status);
						
		               }else if(objectName.equalsIgnoreCase(Config.PROMOTION_REQUEST)){
							PromotionRequestAccessRightsValidationSuite promotionRequestSuite = new PromotionRequestAccessRightsValidationSuite();
							validationResult =	promotionRequestSuite.verifyReadAccessRightForPromotionRequest(objectName,
									Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status,
									Config.DESCRIBE_DOCUMENT_NAME);

						}else if(objectName.equalsIgnoreCase(Config.PROBLEM_REPORT)){
							ProblemReportAccessRightsValidationSuite problemReportSuite = new ProblemReportAccessRightsValidationSuite();
							validationResult = problemReportSuite.verifyReadAccessRightForProblemReport(objectName, Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status, ADVANCE_SEARCH_BY_NAME);
												
						}else if(objectName.equalsIgnoreCase(Config.FOLDER)){
							
							validationResult[0] = "NA";
							
						}
				
		// ********** Writing the results into excel after execution completed ***************************
					
					excel.writeResult(report,readAccess, validationResult[0], sheetName, i, "Read", rowInfoAndExpectedAccessRights, validationResult[1]);		
					extent.flush();		
}
	

	 	 Thread.sleep(2000);
		 GenericFunctionLibrary.refreshWindow();
		 Thread.sleep(2000);
		 GenericFunctionLibrary.refreshWindow();
		 Thread.sleep(2000);
	
	
	// Download Access Validation Script will execute based on the object type mentioned in the "Debug excel workbook"
	 

	
	if (downloadAccess.equalsIgnoreCase("NA")) {
		String comment = "Download :  the Downlaod access right is mentioned with " + downloadAccess+ " Hence 'DOWNLOAD' Script has been Skipped";
		excel.writeResult(report,downloadAccess, "NA", sheetName, i, "Download", rowInfoAndExpectedAccessRights, comment);
		log.info(comment);
		extent.flush();
	}else{
				
		String[] validationResult = new String[2];
		
			if(objectName.equalsIgnoreCase(Config.DESCRIBE_DOCUMENT)){
				DescribeDocumentAccessRightsValidationSuite describeDocumentSuite = new DescribeDocumentAccessRightsValidationSuite(); 
				validationResult =	describeDocumentSuite.verifyDownloadAccessRightForDescribeDocument(objectName, Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status, ADVANCE_SEARCH_BY_NAME);
					
				}else if(objectName.equalsIgnoreCase(Config.REFERENCE_DOCUMENT)){
					ReferenceDocumentAccessRightsValidationSuite referenceDocumentSuite = new ReferenceDocumentAccessRightsValidationSuite();
					validationResult =	referenceDocumentSuite.verifyDownloadAccessRightForReferenceDocument(objectName, Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status, ADVANCE_SEARCH_BY_NAME);

				}else if(objectName.equalsIgnoreCase(Config.CAD_PART)){
					
					validationResult[0]  = "NA";

				}else if(objectName.equalsIgnoreCase(Config.CATALOG_PART)){
					
					validationResult[0]  = "NA";

				}else if(objectName.equalsIgnoreCase(Config.ENGINEERED_PART)){
					
					validationResult[0]  = "NA";

				}else if(objectName.equalsIgnoreCase(Config.MANUFACTURER_PART)){
					
					validationResult[0]  = "NA";

				}else if(objectName.equalsIgnoreCase(Config.MOUNTING_CONTEXT_PART)){
					
					validationResult[0]  = "NA";

				}else if(objectName.equalsIgnoreCase(Config.OBJECT_LIST_PART)){
					
					validationResult[0]  = "NA";

				}else if(objectName.equalsIgnoreCase(Config.RAWMATERIAL_PART)){
					
					validationResult[0]  = "NA";

				}else if(objectName.equalsIgnoreCase(Config.TREATMENT_PART)){
					
					validationResult[0]  = "NA";

				}else if(objectName.equalsIgnoreCase(Config.MANAGED_COLLECTION)){
					
					validationResult[0]  = "NA";

				}else if(objectName.equalsIgnoreCase(Config.CHANGE_NOTICE)){
					
					validationResult[0]  = "NA";
					
               }else if(objectName.equalsIgnoreCase(Config.CHANGE_TASK)){
            	   
            	   validationResult[0]  = "NA";
				
               }else if(objectName.equalsIgnoreCase(Config.PROMOTION_REQUEST)){
				
            	   validationResult[0]  = "NA";

				}else if(objectName.equalsIgnoreCase(Config.PROBLEM_REPORT)){
					
					validationResult[0]  = "NA";
										
				}else if(objectName.equalsIgnoreCase(Config.FOLDER)){
					
					validationResult[0]  = "NA";
					
				}
			
			// ********** Writing the results into excel after execution completed ***************************
			
			excel.writeResult(report,downloadAccess, validationResult[0], sheetName, i, "Download", rowInfoAndExpectedAccessRights, validationResult[1]);		
			extent.flush();	
}

	 Thread.sleep(2000);
		GenericFunctionLibrary.refreshWindow();
		 Thread.sleep(2000);
		 GenericFunctionLibrary.refreshWindow();
		 Thread.sleep(2000);
	
	// Modify Content Access Validation Script will execute based on the object type mentioned in the "Debug excel workbook"

	if (modifyContentAccess.equalsIgnoreCase("NA")) {
		String comment = "Modify Content : the Modify Content access right is mentioned with "+modifyContentAccess +" Hence 'MODIFY CONTENT' Script has been Skipped";
		excel.writeResult(report,modifyContentAccess, "NA", sheetName, i, "Modify Content", rowInfoAndExpectedAccessRights, comment);
		log.info(comment);
		extent.flush();
	}else{	
		
		String[] validationResult = new String[2];

				if (objectName.equalsIgnoreCase(Config.DESCRIBE_DOCUMENT)) {
					DescribeDocumentAccessRightsValidationSuite describeDocumentSuite = new DescribeDocumentAccessRightsValidationSuite();
					validationResult = describeDocumentSuite.verifyModifyContentAccessRightForDescribeDocument(
							objectName, Config.fetchContainerOrLibrary(currentTemplate), currentTemplate,
							status, ADVANCE_SEARCH_BY_NAME);

				} else if (objectName.equalsIgnoreCase(Config.REFERENCE_DOCUMENT)) {
					ReferenceDocumentAccessRightsValidationSuite referenceDocumentSuite = new ReferenceDocumentAccessRightsValidationSuite();
					validationResult = referenceDocumentSuite.verifyModifyContentAccessRightForReferenceDocument(
							objectName, Config.fetchContainerOrLibrary(currentTemplate), currentTemplate,
							status, ADVANCE_SEARCH_BY_NAME);
					
				}else if(objectName.equalsIgnoreCase(Config.CAD_PART)){
					validationResult[0]  = "NA";
					
					
				} else if (objectName.equalsIgnoreCase(Config.CATALOG_PART)) {
					validationResult[0]  = "NA";

				} else if (objectName.equalsIgnoreCase(Config.ENGINEERED_PART)) {
					validationResult[0]  = "NA";
				
				} else if (objectName.equalsIgnoreCase(Config.MANUFACTURER_PART)) {
					validationResult[0]  = "NA";

				} else if (objectName.equalsIgnoreCase(Config.MOUNTING_CONTEXT_PART)) {
					validationResult[0]  = "NA";

				} else if (objectName.equalsIgnoreCase(Config.OBJECT_LIST_PART)) {
					validationResult[0]  = "NA";

				} else if (objectName.equalsIgnoreCase(Config.RAWMATERIAL_PART)) {
					validationResult[0]  = "NA";

				} else if (objectName.equalsIgnoreCase(Config.TREATMENT_PART)) {
					validationResult[0]  = "NA";

				} else if (objectName.equalsIgnoreCase(Config.MANAGED_COLLECTION)) {
					validationResult[0]  = "NA";

				}else if(objectName.equalsIgnoreCase(Config.CHANGE_NOTICE)){
					validationResult[0]  = "NA";
					
               }else if(objectName.equalsIgnoreCase(Config.CHANGE_TASK)){
            	   validationResult[0]  = "NA";
				
               }else if(objectName.equalsIgnoreCase(Config.PROMOTION_REQUEST)){
            	   validationResult[0]  = "NA";

				}else if(objectName.equalsIgnoreCase(Config.PROBLEM_REPORT)){
					validationResult[0]  = "NA";
										
				}else if(objectName.equalsIgnoreCase(Config.FOLDER)){
					validationResult[0]  = "NA";
					
				}
				
				// ********** Writing the results into excel after execution completed ***************************
				
				excel.writeResult(report,modifyContentAccess, validationResult[0], sheetName, i, "Modify Content", rowInfoAndExpectedAccessRights, validationResult[1]);		
				extent.flush();			
}
		
	
	 Thread.sleep(2000);
		GenericFunctionLibrary.refreshWindow();
		 Thread.sleep(2000);
		 GenericFunctionLibrary.refreshWindow();
		 Thread.sleep(2000);
	
	
	// Modify Identity Access Validation Script will execute based on the object type mentioned in the "Debug excel workbook"
	
	if (modifyIdentityAccess.equalsIgnoreCase("NA")) {
		
		String comment = "Modify Identity : The Modify Identity access right is mentioned with NA Hence 'MODIFY IDENTITY' Script has been Skipped";
		excel.writeResult(report, modifyIdentityAccess, "NA", sheetName, i, "Modify Identity", rowInfoAndExpectedAccessRights, comment);
		log.info(comment);
		
		extent.flush();
	}else{
				
		String[] validationResult = new String[2];
		
			if(objectName.equalsIgnoreCase(Config.DESCRIBE_DOCUMENT)){
				DescribeDocumentAccessRightsValidationSuite describeDocumentSuite = new DescribeDocumentAccessRightsValidationSuite(); 
				validationResult =	describeDocumentSuite.verifyModifyIdentityAccessRightForDescribeDocument(objectName, Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status, ADVANCE_SEARCH_BY_NAME);
					
				}else if(objectName.equalsIgnoreCase(Config.REFERENCE_DOCUMENT)){
					ReferenceDocumentAccessRightsValidationSuite referenceDocumentSuite = new ReferenceDocumentAccessRightsValidationSuite();
					validationResult =	referenceDocumentSuite.verifyModifyIdentityAccessRightForReferenceDocument(objectName, Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status, ADVANCE_SEARCH_BY_NAME);

				}else if(objectName.equalsIgnoreCase(Config.CAD_PART)){
					CADDocumentAccessRightsValidationSuite CADDocSuite = new CADDocumentAccessRightsValidationSuite();
					validationResult =	CADDocSuite.verifyModifyIdentityAccessRightForCADDocument(objectName, Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status, Config.CAD_PART_NAME);

				}else if(objectName.equalsIgnoreCase(Config.CATALOG_PART)){
					CatalogPartAccessRightsValidationSuite CatalogPartSuite = new CatalogPartAccessRightsValidationSuite();
					validationResult =	CatalogPartSuite.verifyModifyIdentityAccessRightForCatalogPart(objectName, Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status, ADVANCE_SEARCH_BY_NAME);

				}else if(objectName.equalsIgnoreCase(Config.ENGINEERED_PART)){
					EngineeredPartAccessRightsValidationSuite engineeredPartSuite = new EngineeredPartAccessRightsValidationSuite();
					validationResult =	engineeredPartSuite.verifyModifyIdentityAccessRightForEngineeredPart(objectName, Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status, ADVANCE_SEARCH_BY_NAME);

				}else if(objectName.equalsIgnoreCase(Config.MANUFACTURER_PART)){
					ManufacturerPartAccessRightsValidationSuite manufaturerPartSuite = new ManufacturerPartAccessRightsValidationSuite();
				validationResult =	manufaturerPartSuite.verifyModifyIdentityAccessRightForManufacturerPart(objectName, Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status, ADVANCE_SEARCH_BY_NAME);

				}else if(objectName.equalsIgnoreCase(Config.MOUNTING_CONTEXT_PART)){
					MountingContextPartAccessRightValidationSuite MountingContextPartSuite = new MountingContextPartAccessRightValidationSuite();
					validationResult =	MountingContextPartSuite.verifyModifyIdentityAccessRightForMountingContextPart(objectName, Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status, ADVANCE_SEARCH_BY_NAME);

				}else if(objectName.equalsIgnoreCase(Config.OBJECT_LIST_PART)){
					ObjectListPartAccessRightsValidationSuite ObjectListPartSuite = new ObjectListPartAccessRightsValidationSuite();
				validationResult =	ObjectListPartSuite.verifyModifyIdentityAccessRightForObjectListPart(objectName, Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status, ADVANCE_SEARCH_BY_NAME);

				}else if(objectName.equalsIgnoreCase(Config.RAWMATERIAL_PART)){
					RawMaterialPartAccessRightsValidationSuite RawMaterialPartSuite = new RawMaterialPartAccessRightsValidationSuite();
					validationResult =	RawMaterialPartSuite.verifyModifyIdentityAccessRightForRawMaterialPart(objectName, Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status, ADVANCE_SEARCH_BY_NAME);

				}else if(objectName.equalsIgnoreCase(Config.TREATMENT_PART)){
					TreatmentPartAccessRightsValidationSuite treatmentPartSuite = new TreatmentPartAccessRightsValidationSuite();
					validationResult =	treatmentPartSuite.verifyModifyIdentityAccessRightForTreatmentPart(objectName, Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status, ADVANCE_SEARCH_BY_NAME);

				}else if(objectName.equalsIgnoreCase(Config.MANAGED_COLLECTION)){
					validationResult[0]  = "NA";
					
				}else if(objectName.equalsIgnoreCase(Config.CHANGE_NOTICE)){
					validationResult[0]  = "NA";
					
               }else if(objectName.equalsIgnoreCase(Config.CHANGE_TASK)){
            	   validationResult[0]  = "NA";
				
               }else if(objectName.equalsIgnoreCase(Config.PROMOTION_REQUEST)){
            	   validationResult[0]  = "NA";

				}else if(objectName.equalsIgnoreCase(Config.PROBLEM_REPORT)){
					validationResult[0]  = "NA";
										
				}else if(objectName.equalsIgnoreCase(Config.FOLDER)){
					validationResult[0]  = "NA";
					
				}
			
			// ********** Writing the results into excel after execution completed ***************************
			
			excel.writeResult(report, modifyIdentityAccess, validationResult[0], sheetName, i, "Modify Identity", rowInfoAndExpectedAccessRights, validationResult[1]);
			extent.flush();	
		
}

	 Thread.sleep(2000);
		GenericFunctionLibrary.refreshWindow();
		 Thread.sleep(2000);
		 GenericFunctionLibrary.refreshWindow();
		 Thread.sleep(2000);


//Modify Access Validation Script will execute based on the object type mentioned in the "Debug excel workbook"

if (modifyAccess.equalsIgnoreCase("NA")) {
	String comment = " Modify : The Modify access right is mentioned with NA Hence 'MODIFY' Script has been Skipped ";
	excel.writeResult(report, modifyAccess, "NA", sheetName, i, "Modify", rowInfoAndExpectedAccessRights, comment);
	log.info(comment);
	extent.flush();
}else{
		
	String[] validationResult = new String[2];

				if (objectName.equalsIgnoreCase(Config.DESCRIBE_DOCUMENT)) {
					DescribeDocumentAccessRightsValidationSuite describeDocumentSuite = new DescribeDocumentAccessRightsValidationSuite();
					validationResult = describeDocumentSuite.verifyModifyAccessRightForDescribeDocument(objectName,
							Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status,
							ADVANCE_SEARCH_BY_NAME);

				} else if (objectName.equalsIgnoreCase(Config.REFERENCE_DOCUMENT)) {
					ReferenceDocumentAccessRightsValidationSuite referenceDocumentSuite = new ReferenceDocumentAccessRightsValidationSuite();
					validationResult = referenceDocumentSuite.verifyModifyAccessRightForReferenceDocument(objectName,
							Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status,
							ADVANCE_SEARCH_BY_NAME);
					
				}else if(objectName.equalsIgnoreCase(Config.CAD_PART)){
					CADDocumentAccessRightsValidationSuite CADDocSuite = new CADDocumentAccessRightsValidationSuite();
					validationResult =	CADDocSuite.verifyModifyAccessRightForCADDocument(objectName,Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status, Config.CAD_PART_NAME);

				} else if (objectName.equalsIgnoreCase(Config.CATALOG_PART)) {
					CatalogPartAccessRightsValidationSuite CatalogPartSuite = new CatalogPartAccessRightsValidationSuite();
					validationResult = CatalogPartSuite.verifyModifyAccessRightForCatalogPart(objectName,Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status,
							ADVANCE_SEARCH_BY_NAME);

				} else if (objectName.equalsIgnoreCase(Config.ENGINEERED_PART)) {
					EngineeredPartAccessRightsValidationSuite engineeredPartSuite = new EngineeredPartAccessRightsValidationSuite();
					validationResult = engineeredPartSuite.verifyModifyAccessRightForEngineeredPart(objectName,	Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status,
							ADVANCE_SEARCH_BY_NAME);

				} else if (objectName.equalsIgnoreCase(Config.MANUFACTURER_PART)) {
					ManufacturerPartAccessRightsValidationSuite manufaturerPartSuite = new ManufacturerPartAccessRightsValidationSuite();
					validationResult = manufaturerPartSuite.verifyModifyAccessRightForManufacturerPart(objectName,	Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status,
							ADVANCE_SEARCH_BY_NAME);

				} else if (objectName.equalsIgnoreCase(Config.MOUNTING_CONTEXT_PART)) {
					MountingContextPartAccessRightValidationSuite MountingContextPartSuite = new MountingContextPartAccessRightValidationSuite();
					validationResult = MountingContextPartSuite.verifyModifyAccessRightForMountingContextPart(objectName, Config.fetchContainerOrLibrary(currentTemplate), currentTemplate,
							status, ADVANCE_SEARCH_BY_NAME);

				} else if (objectName.equalsIgnoreCase(Config.OBJECT_LIST_PART)) {
					ObjectListPartAccessRightsValidationSuite ObjectListPartSuite = new ObjectListPartAccessRightsValidationSuite();
					validationResult = ObjectListPartSuite.verifyModifyAccessRightForObjectListPart(objectName,
							Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status,
							ADVANCE_SEARCH_BY_NAME);

				} else if (objectName.equalsIgnoreCase(Config.RAWMATERIAL_PART)) {
					RawMaterialPartAccessRightsValidationSuite RawMaterialPartSuite = new RawMaterialPartAccessRightsValidationSuite();
					validationResult = RawMaterialPartSuite.verifyModifyAccessRightForRawMaterialPart(objectName,
							Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status,
							ADVANCE_SEARCH_BY_NAME);

				} else if (objectName.equalsIgnoreCase(Config.TREATMENT_PART)) {
					TreatmentPartAccessRightsValidationSuite treatmentPartSuite = new TreatmentPartAccessRightsValidationSuite();
					validationResult = treatmentPartSuite.verifyModifyAccessRightForTreatmentPart(objectName,
							Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status,
							ADVANCE_SEARCH_BY_NAME);

				} else if (objectName.equalsIgnoreCase(Config.MANAGED_COLLECTION)) {
					ManagedCollectionAccessRightsValidationSuite ManagedCollectionSuite = new ManagedCollectionAccessRightsValidationSuite();
					validationResult = ManagedCollectionSuite.verifyModifyAccessRightForManagedCollection(objectName,
							Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, ADVANCE_SEARCH_BY_NAME);

				}else if(objectName.equalsIgnoreCase(Config.CHANGE_NOTICE)){
					ChangeNoticeAccessRightsValidationSuite changeNoticeSuite = new ChangeNoticeAccessRightsValidationSuite();
					validationResult = changeNoticeSuite.modifyChangeNoticeAccessRightValidation(objectName, Config.fetchContainerOrLibrary(currentTemplate), currentTemplate,status, Config.CHANGE_NOTICE_NAME);
					
               }else if(objectName.equalsIgnoreCase(Config.CHANGE_TASK)){
            	   ChangeTaskAccessRightsValidationSuite changeTaskSuite = new ChangeTaskAccessRightsValidationSuite();
            	   validationResult = changeTaskSuite.verifyModifyChangeTaskAccessRightValidation(Config.CHANGE_NOTICE, Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, "Open", Config.CHANGENOTICE_FOR_CHANGETASK, status);
				
               }else if(objectName.equalsIgnoreCase(Config.PROMOTION_REQUEST)){
					
            	   validationResult[0]  = "NA";
            	   
				}else if(objectName.equalsIgnoreCase(Config.PROBLEM_REPORT)){
					ProblemReportAccessRightsValidationSuite problemReportSuite = new ProblemReportAccessRightsValidationSuite();
					validationResult = problemReportSuite.verifyModifyAccessDocumentValidationProblemReport(objectName,Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status, ADVANCE_SEARCH_BY_NAME);
										
				}else if(objectName.equalsIgnoreCase(Config.FOLDER)){
					FolderAccessRightsValidationSuite folderSuite = new FolderAccessRightsValidationSuite();
					validationResult =	folderSuite.verifyModifyAccessRightForFolder(currentTemplate);
					
				}

				// ********** Writing the results into excel after execution completed ***************************
				
				excel.writeResult(report, modifyAccess, validationResult[0], sheetName, i, "Modify", rowInfoAndExpectedAccessRights, validationResult[1]);
				
				extent.flush();
			}

Thread.sleep(2000);
GenericFunctionLibrary.refreshWindow();
 Thread.sleep(2000);
 GenericFunctionLibrary.refreshWindow();
 Thread.sleep(2000);
	
	
//***********************************************************************************************************************************************************//		
	
	// Revise Access Validation Script will execute based on the object type mentioned in the "Debug excel workbook"
			
	if (reviseAccess.equalsIgnoreCase("NA")) {
		String comment = " Revise : The Revise access right is mentioned with NA Hence 'REVISE' Script has been Skipped ";
		excel.writeResult(report, reviseAccess, "NA", sheetName, i, "Revise", rowInfoAndExpectedAccessRights, comment);
		log.info(comment);	
			
		extent.flush();
}else{
				
	String[] validationResult = new String[2];
	
		if(objectName.equalsIgnoreCase(Config.DESCRIBE_DOCUMENT)){
			DescribeDocumentAccessRightsValidationSuite describeDocumentSuite = new DescribeDocumentAccessRightsValidationSuite(); 
			validationResult =	describeDocumentSuite.verifyReviseAccessRightForDescribeDocument(objectName, Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status, ADVANCE_SEARCH_BY_NAME);
				
			}else if(objectName.equalsIgnoreCase(Config.REFERENCE_DOCUMENT)){
				ReferenceDocumentAccessRightsValidationSuite referenceDocumentSuite = new ReferenceDocumentAccessRightsValidationSuite();
				validationResult =	referenceDocumentSuite.verifyReviseAccessRightForReferenceDocument(objectName, Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status, ADVANCE_SEARCH_BY_NAME);

			}else if(objectName.equalsIgnoreCase(Config.CAD_PART)){
				CADDocumentAccessRightsValidationSuite CADDocSuite = new CADDocumentAccessRightsValidationSuite();
				validationResult =	CADDocSuite.verifyReviseAccessRightForCADDocument(objectName, Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status, Config.CAD_PART_NAME);

			}else if(objectName.equalsIgnoreCase(Config.CATALOG_PART)){
				CatalogPartAccessRightsValidationSuite CatalogPartSuite = new CatalogPartAccessRightsValidationSuite();
				validationResult =	CatalogPartSuite.verifyReviseAccessRightForCatalogPart(objectName, Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status, ADVANCE_SEARCH_BY_NAME);

			}else if(objectName.equalsIgnoreCase(Config.ENGINEERED_PART)){
				EngineeredPartAccessRightsValidationSuite engineeredPartSuite = new EngineeredPartAccessRightsValidationSuite();
				validationResult =	engineeredPartSuite.verifyReviseAccessRightForEngineeredPart(objectName, Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status, ADVANCE_SEARCH_BY_NAME);

			}else if(objectName.equalsIgnoreCase(Config.MANUFACTURER_PART)){
				ManufacturerPartAccessRightsValidationSuite manufaturerPartSuite = new ManufacturerPartAccessRightsValidationSuite();
			validationResult =	manufaturerPartSuite.verifyReviseAccessRightForManufacturerPart(objectName, Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status, ADVANCE_SEARCH_BY_NAME);

			}else if(objectName.equalsIgnoreCase(Config.MOUNTING_CONTEXT_PART)){
				MountingContextPartAccessRightValidationSuite MountingContextPartSuite = new MountingContextPartAccessRightValidationSuite();
				validationResult =	MountingContextPartSuite.verifyReviseAccessRightForMountingContextPart(objectName, Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status, ADVANCE_SEARCH_BY_NAME);

			}else if(objectName.equalsIgnoreCase(Config.OBJECT_LIST_PART)){
				ObjectListPartAccessRightsValidationSuite ObjectListPartSuite = new ObjectListPartAccessRightsValidationSuite();
			validationResult =	ObjectListPartSuite.verifyReviseAccessRightForObjectListPart(objectName, Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status, ADVANCE_SEARCH_BY_NAME);

			}else if(objectName.equalsIgnoreCase(Config.RAWMATERIAL_PART)){
				RawMaterialPartAccessRightsValidationSuite RawMaterialPartSuite = new RawMaterialPartAccessRightsValidationSuite();
				validationResult =	RawMaterialPartSuite.verifyReviseAccessRightForRawMaterialPart(objectName, Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status, ADVANCE_SEARCH_BY_NAME);

			}else if(objectName.equalsIgnoreCase(Config.TREATMENT_PART)){
				TreatmentPartAccessRightsValidationSuite treatmentPartSuite = new TreatmentPartAccessRightsValidationSuite();
				validationResult =	treatmentPartSuite.verifyReviseAccessRightForTreatmentPart(objectName, Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status, ADVANCE_SEARCH_BY_NAME);

			}else if(objectName.equalsIgnoreCase(Config.MANAGED_COLLECTION)){
				validationResult[0]  = "NA";
			}else if(objectName.equalsIgnoreCase(Config.CHANGE_NOTICE)){
				validationResult[0]  = "NA";
				
           }else if(objectName.equalsIgnoreCase(Config.CHANGE_TASK)){
        	   validationResult[0]  = "NA";
			
           }else if(objectName.equalsIgnoreCase(Config.PROMOTION_REQUEST)){
        	   validationResult[0]  = "NA";

			}else if(objectName.equalsIgnoreCase(Config.PROBLEM_REPORT)){
				validationResult[0]  = "NA";
				
			}else if(objectName.equalsIgnoreCase(Config.FOLDER)){
				validationResult[0]  = "NA";
				
			}
		
		// ********** Writing the results into excel after execution completed ***************************
		
		excel.writeResult(report, reviseAccess, validationResult[0], sheetName, i, "Revise", rowInfoAndExpectedAccessRights, validationResult[1]);
		extent.flush();

}
	
	 Thread.sleep(2000);
		GenericFunctionLibrary.refreshWindow();
		 Thread.sleep(2000);
		 GenericFunctionLibrary.refreshWindow();
		 Thread.sleep(2000);
	

//***********************************************************************************************************************************************************//

	// Set State Access Validation Script will execute based on the object type mentioned in the "Debug excel workbook"
	
	if (setStateAccess.equalsIgnoreCase("NA")) {
		String comment  = "Set State : The Set State access right is mentioned with NA Hence 'SET STATE' Script has been Skipped";							
		excel.writeResult(report, setStateAccess, "NA", sheetName, i, "Set State", rowInfoAndExpectedAccessRights, comment);
		log.info(comment);	
		
		extent.flush();	
	}else{
					
		String[] validationResult = new String[2];
		
			if(objectName.equalsIgnoreCase(Config.DESCRIBE_DOCUMENT)){
				DescribeDocumentAccessRightsValidationSuite describeDocumentSuite = new DescribeDocumentAccessRightsValidationSuite(); 
				validationResult =	describeDocumentSuite.verifySetStateAccessRightForDescribeDocument(objectName, Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status, ADVANCE_SEARCH_BY_NAME);
					
				}else if(objectName.equalsIgnoreCase(Config.REFERENCE_DOCUMENT)){
					ReferenceDocumentAccessRightsValidationSuite referenceDocumentSuite = new ReferenceDocumentAccessRightsValidationSuite();
					validationResult =	referenceDocumentSuite.verifySetStateAccessRightForReferenceDocument(objectName, Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status, ADVANCE_SEARCH_BY_NAME);

				}else if(objectName.equalsIgnoreCase(Config.CAD_PART)){
					CADDocumentAccessRightsValidationSuite CADDocSuite = new CADDocumentAccessRightsValidationSuite();
					validationResult =	CADDocSuite.verifySetStateAccessRightForCADDocument(objectName,Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status,	Config.CAD_PART_NAME);

				}else if(objectName.equalsIgnoreCase(Config.CATALOG_PART)){
					CatalogPartAccessRightsValidationSuite CatalogPartSuite = new CatalogPartAccessRightsValidationSuite();
					validationResult =	CatalogPartSuite.verifySetStateAccessRightForCatalogPart(objectName, Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status, ADVANCE_SEARCH_BY_NAME);

				}else if(objectName.equalsIgnoreCase(Config.ENGINEERED_PART)){
					EngineeredPartAccessRightsValidationSuite engineeredPartSuite = new EngineeredPartAccessRightsValidationSuite();
					validationResult =	engineeredPartSuite.verifySetStateAccessRightForEngineeredPart(objectName, Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status, ADVANCE_SEARCH_BY_NAME);

				}else if(objectName.equalsIgnoreCase(Config.MANUFACTURER_PART)){
					ManufacturerPartAccessRightsValidationSuite manufaturerPartSuite = new ManufacturerPartAccessRightsValidationSuite();
				validationResult =	manufaturerPartSuite.verifySetStateAccessRightForManufacturerPart(objectName, Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status, ADVANCE_SEARCH_BY_NAME);

				}else if(objectName.equalsIgnoreCase(Config.MOUNTING_CONTEXT_PART)){
					MountingContextPartAccessRightValidationSuite MountingContextPartSuite = new MountingContextPartAccessRightValidationSuite();
					validationResult =	MountingContextPartSuite.verifySetStateAccessRightForMountingContextPart(objectName, Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status, ADVANCE_SEARCH_BY_NAME);

				}else if(objectName.equalsIgnoreCase(Config.OBJECT_LIST_PART)){
					ObjectListPartAccessRightsValidationSuite ObjectListPartSuite = new ObjectListPartAccessRightsValidationSuite();
				validationResult =	ObjectListPartSuite.verifySetStateAccessRightForObjectListPart(objectName, Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status, ADVANCE_SEARCH_BY_NAME);

				}else if(objectName.equalsIgnoreCase(Config.RAWMATERIAL_PART)){
					RawMaterialPartAccessRightsValidationSuite RawMaterialPartSuite = new RawMaterialPartAccessRightsValidationSuite();
					validationResult =	RawMaterialPartSuite.verifySetStateAccessRightForRawMaterialPart(objectName, Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status, ADVANCE_SEARCH_BY_NAME);

				}else if(objectName.equalsIgnoreCase(Config.TREATMENT_PART)){
					TreatmentPartAccessRightsValidationSuite treatmentPartSuite = new TreatmentPartAccessRightsValidationSuite();
					validationResult =	treatmentPartSuite.verifySetStateAccessRightForTreatmentPart(objectName, Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status, ADVANCE_SEARCH_BY_NAME);

				}else if(objectName.equalsIgnoreCase(Config.MANAGED_COLLECTION)){
					validationResult[0]  = "NA";
					
				}else if(objectName.equalsIgnoreCase(Config.CHANGE_NOTICE)){
					
					ChangeNoticeAccessRightsValidationSuite ChangeNoticeSuite = new ChangeNoticeAccessRightsValidationSuite();
					validationResult =	ChangeNoticeSuite.verifySetStateChangeNoticeAccessRightValidation(objectName, Config.fetchContainerOrLibrary(currentTemplate), currentTemplate,status, Config.CHANGE_NOTICE_NAME);
										
               }else if(objectName.equalsIgnoreCase(Config.CHANGE_TASK)){
            	   ChangeTaskAccessRightsValidationSuite changeTaskSuite = new ChangeTaskAccessRightsValidationSuite();
            	   validationResult = changeTaskSuite.setStateChangeTaskAccessRightValidation(Config.CHANGE_NOTICE, Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, "Open", Config.CHANGENOTICE_FOR_CHANGETASK, status);
				
               }else if(objectName.equalsIgnoreCase(Config.PROMOTION_REQUEST)){
					
            	   validationResult[0]  = "NA";

				}else if(objectName.equalsIgnoreCase(Config.PROBLEM_REPORT)){
					ProblemReportAccessRightsValidationSuite problemReportSuite = new ProblemReportAccessRightsValidationSuite();
					validationResult = problemReportSuite.verifySetStateAccessDocumentValidationProblemReport(objectName,Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status, ADVANCE_SEARCH_BY_NAME);
										
				}else if(objectName.equalsIgnoreCase(Config.FOLDER)){
					validationResult[0]  = "NA";
					
				}
			
			// ********** Writing the results into excel after execution completed ***************************
			excel.writeResult(report, setStateAccess, validationResult[0], sheetName, i, "Set State", rowInfoAndExpectedAccessRights, validationResult[1]);
			extent.flush();
}


	 Thread.sleep(2000);
		GenericFunctionLibrary.refreshWindow();
		 Thread.sleep(2000);
		 GenericFunctionLibrary.refreshWindow();
		 Thread.sleep(2000);
	
	
	// Delete Access Validation Script will execute based on the object type mentioned in the "Debug excel workbook"
	
	if (deleteAccess.equalsIgnoreCase("NA")) {
		String comment = " Delete : The Delete access right is mentioned with NA Hence 'DELETE' Script has been Skipped ";						
		excel.writeResult(report, deleteAccess, "NA", sheetName, i, "Delete", rowInfoAndExpectedAccessRights, comment);
		log.info(comment);	
		
		extent.flush();	
	}else{
					
		String[] validationResult = new String[2];

				if (objectName.equalsIgnoreCase(Config.DESCRIBE_DOCUMENT)) {
					DescribeDocumentAccessRightsValidationSuite describeDocumentSuite = new DescribeDocumentAccessRightsValidationSuite();
					validationResult = describeDocumentSuite.verifyDeleteAccessRightForDescribeDocument(objectName,	Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status,
							ADVANCE_SEARCH_BY_NAME);

				} else if (objectName.equalsIgnoreCase(Config.REFERENCE_DOCUMENT)) {
					ReferenceDocumentAccessRightsValidationSuite referenceDocumentSuite = new ReferenceDocumentAccessRightsValidationSuite();
					validationResult = referenceDocumentSuite.verifyDeleteAccessRightForReferenceDocument(objectName,	Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status,
							ADVANCE_SEARCH_BY_NAME);

				}else if(objectName.equalsIgnoreCase(Config.CAD_PART)){
					CADDocumentAccessRightsValidationSuite CADDocSuite = new CADDocumentAccessRightsValidationSuite();
					validationResult =	CADDocSuite.verifyDeleteAccessRightForCADDocument(objectName, Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status, Config.CAD_PART_NAME);


				} else if (objectName.equalsIgnoreCase(Config.CATALOG_PART)) {
					CatalogPartAccessRightsValidationSuite CatalogPartSuite = new CatalogPartAccessRightsValidationSuite();
					validationResult = CatalogPartSuite.verifyDeleteAccessRightForCatalogPart(objectName,Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status,
							ADVANCE_SEARCH_BY_NAME);

				} else if (objectName.equalsIgnoreCase(Config.ENGINEERED_PART)) {
					EngineeredPartAccessRightsValidationSuite engineeredPartSuite = new EngineeredPartAccessRightsValidationSuite();
					validationResult = engineeredPartSuite.verifyDeleteAccessRightForEngineeredPart(objectName,	Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status,
							ADVANCE_SEARCH_BY_NAME);

				} else if (objectName.equalsIgnoreCase(Config.MANUFACTURER_PART)) {
					ManufacturerPartAccessRightsValidationSuite manufaturerPartSuite = new ManufacturerPartAccessRightsValidationSuite();
					validationResult = manufaturerPartSuite.verifyDeleteAccessRightForManufacturerPart(objectName,Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status,
							ADVANCE_SEARCH_BY_NAME);

				} else if (objectName.equalsIgnoreCase(Config.MOUNTING_CONTEXT_PART)) {
					MountingContextPartAccessRightValidationSuite MountingContextPartSuite = new MountingContextPartAccessRightValidationSuite();
					validationResult = MountingContextPartSuite.verifyDeleteAccessRightForMountingContextPart(objectName, Config.fetchContainerOrLibrary(currentTemplate), currentTemplate,
							status, ADVANCE_SEARCH_BY_NAME);

				} else if (objectName.equalsIgnoreCase(Config.OBJECT_LIST_PART)) {
					ObjectListPartAccessRightsValidationSuite ObjectListPartSuite = new ObjectListPartAccessRightsValidationSuite();
					validationResult = ObjectListPartSuite.verifyDeleteAccessRightForObjectListPart(objectName,	Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status,
							ADVANCE_SEARCH_BY_NAME);

				} else if (objectName.equalsIgnoreCase(Config.RAWMATERIAL_PART)) {
					RawMaterialPartAccessRightsValidationSuite RawMaterialPartSuite = new RawMaterialPartAccessRightsValidationSuite();
					validationResult = RawMaterialPartSuite.verifyDeleteAccessRightForRawMaterialPart(objectName,Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status,
							ADVANCE_SEARCH_BY_NAME);

				} else if (objectName.equalsIgnoreCase(Config.TREATMENT_PART)) {
					TreatmentPartAccessRightsValidationSuite treatmentPartSuite = new TreatmentPartAccessRightsValidationSuite();
					validationResult = treatmentPartSuite.verifyDeleteAccessRightForTreatmentPart(objectName,Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status,
							ADVANCE_SEARCH_BY_NAME);

				} else if (objectName.equalsIgnoreCase(Config.MANAGED_COLLECTION)) {
					ManagedCollectionAccessRightsValidationSuite ManagedCollectionSuite = new ManagedCollectionAccessRightsValidationSuite();
					validationResult = ManagedCollectionSuite.verifyDeleteAccessRightForManagedCollection(objectName,Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, ADVANCE_SEARCH_BY_NAME);

				}else if(objectName.equalsIgnoreCase(Config.CHANGE_NOTICE)){
					validationResult[0]  = "NA";
					
               }else if(objectName.equalsIgnoreCase(Config.CHANGE_TASK)){
            	   validationResult[0]  = "NA";
				
               }else if(objectName.equalsIgnoreCase(Config.PROMOTION_REQUEST)){
            	   validationResult[0]  = "NA";

				}else if(objectName.equalsIgnoreCase(Config.PROBLEM_REPORT)){
					validationResult[0]  = "NA";
										
				}else if(objectName.equalsIgnoreCase(Config.FOLDER)){
					FolderAccessRightsValidationSuite folderSuite = new FolderAccessRightsValidationSuite();
					validationResult = folderSuite.verifyDeleteAccessRightForFolder(currentTemplate);
					
				}
				
				// ********** Writing the results into excel after execution completed ***************************
				excel.writeResult(report, deleteAccess, validationResult[0], sheetName, i, "Delete", rowInfoAndExpectedAccessRights, validationResult[1]);
				extent.flush();
}
	
	 Thread.sleep(2000);
		GenericFunctionLibrary.refreshWindow();
		 Thread.sleep(2000);
		 GenericFunctionLibrary.refreshWindow();
		 Thread.sleep(2000);

	
	// Change Domain Access Validation Script will execute based on the object type mentioned in the "Debug excel workbook"
	
	if (changeDomainAccess.equalsIgnoreCase("NA")) {
		String comment = "Change Domain : The Change Domain access right is mentioned with NA Hence 'CHANGE DOMAIN' Script has been Skipped";
		excel.writeResult(report, changeDomainAccess, "NA", sheetName, i, "Change Domain", rowInfoAndExpectedAccessRights, comment);
					
		log.info(comment);	
							
		extent.flush();				
	}else{
					
		String[] validationResult = new String[2];
		
			if(objectName.equalsIgnoreCase(Config.DESCRIBE_DOCUMENT)){
				DescribeDocumentAccessRightsValidationSuite describeDocumentSuite = new DescribeDocumentAccessRightsValidationSuite(); 
				validationResult =	describeDocumentSuite.verifyChangeDomainAccessRightForDescribeDocument(objectName, Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status, ADVANCE_SEARCH_BY_NAME);
					
				}else if(objectName.equalsIgnoreCase(Config.REFERENCE_DOCUMENT)){
					ReferenceDocumentAccessRightsValidationSuite referenceDocumentSuite = new ReferenceDocumentAccessRightsValidationSuite();
					validationResult =	referenceDocumentSuite.verifyChangeDomainAccessRightForReferenceDocument(objectName, Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status, ADVANCE_SEARCH_BY_NAME);


				}else if(objectName.equalsIgnoreCase(Config.CAD_PART)){
					CADDocumentAccessRightsValidationSuite CADDocSuite = new CADDocumentAccessRightsValidationSuite();
					validationResult =	CADDocSuite.verifyChangeDomainAccessRightForCADDocument(objectName,Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status, Config.CAD_PART_NAME);

				}else if(objectName.equalsIgnoreCase(Config.CATALOG_PART)){
					CatalogPartAccessRightsValidationSuite CatalogPartSuite = new CatalogPartAccessRightsValidationSuite();
					validationResult =	CatalogPartSuite.verifyChangeDomainAccessRightForCatalogPart(objectName, Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status, ADVANCE_SEARCH_BY_NAME);

				}else if(objectName.equalsIgnoreCase(Config.ENGINEERED_PART)){
					EngineeredPartAccessRightsValidationSuite engineeredPartSuite = new EngineeredPartAccessRightsValidationSuite();
					validationResult =	engineeredPartSuite.verifyChangeDomainAccessRightForEngineeredPart(objectName, Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status, ADVANCE_SEARCH_BY_NAME);

				}else if(objectName.equalsIgnoreCase(Config.MANUFACTURER_PART)){
					ManufacturerPartAccessRightsValidationSuite manufaturerPartSuite = new ManufacturerPartAccessRightsValidationSuite();
				validationResult =	manufaturerPartSuite.verifyChangeDomainAccessRightForManufacturerPart(objectName, Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status, ADVANCE_SEARCH_BY_NAME);

				}else if(objectName.equalsIgnoreCase(Config.MOUNTING_CONTEXT_PART)){
					MountingContextPartAccessRightValidationSuite MountingContextPartSuite = new MountingContextPartAccessRightValidationSuite();
					validationResult =	MountingContextPartSuite.verifyChangeDomainAccessRightForMountingContextPart(objectName, Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status, ADVANCE_SEARCH_BY_NAME);

				}else if(objectName.equalsIgnoreCase(Config.OBJECT_LIST_PART)){
					ObjectListPartAccessRightsValidationSuite ObjectListPartSuite = new ObjectListPartAccessRightsValidationSuite();
				validationResult =	ObjectListPartSuite.verifyChangeDomainAccessRightForObjectListPart(objectName, Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status, ADVANCE_SEARCH_BY_NAME);

				}else if(objectName.equalsIgnoreCase(Config.RAWMATERIAL_PART)){
					RawMaterialPartAccessRightsValidationSuite RawMaterialPartSuite = new RawMaterialPartAccessRightsValidationSuite();
					validationResult =	RawMaterialPartSuite.verifyChangeContextAccessRightForRawMaterialPart(objectName, Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status, ADVANCE_SEARCH_BY_NAME);

				}else if(objectName.equalsIgnoreCase(Config.TREATMENT_PART)){
					TreatmentPartAccessRightsValidationSuite treatmentPartSuite = new TreatmentPartAccessRightsValidationSuite();
					validationResult =	treatmentPartSuite.verifyChangeContextAccessRightForTreatmentPart(objectName, Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status, ADVANCE_SEARCH_BY_NAME);

				}else if(objectName.equalsIgnoreCase(Config.MANAGED_COLLECTION)){
					validationResult[0]  = "NA";
					
				}else if(objectName.equalsIgnoreCase(Config.CHANGE_NOTICE)){
					validationResult[0]  = "NA";
					
               }else if(objectName.equalsIgnoreCase(Config.CHANGE_TASK)){
            	   validationResult[0]  = "NA";
				
               }else if(objectName.equalsIgnoreCase(Config.PROMOTION_REQUEST)){
            	   validationResult[0]  = "NA";

				}else if(objectName.equalsIgnoreCase(Config.PROBLEM_REPORT)){
					validationResult[0]  = "NA";
										
				}else if(objectName.equalsIgnoreCase(Config.FOLDER)){
					validationResult[0]  = "NA";
					
				}
		
			
			// ********** Writing the results into excel after execution completed ***************************
			
			excel.writeResult(report, changeDomainAccess, validationResult[0], sheetName, i, "Change Domain", rowInfoAndExpectedAccessRights, validationResult[1]);	
			 extent.flush();
	
			}
	
	 Thread.sleep(2000);
		GenericFunctionLibrary.refreshWindow();
		 Thread.sleep(2000);
		 GenericFunctionLibrary.refreshWindow();
		 Thread.sleep(2000);
	
	// Change Context Access Validation Script will execute based on the object type mentioned in the "Debug excel workbook"
	 

		if (changeContextAccess.equalsIgnoreCase("NA")) {
			
			String comment = "Change Context : The Change Context access right is mentioned with NA Hence 'CHANGE CONTEXT' Script has been Skipped";
			excel.writeResult(report, changeContextAccess, "NA", sheetName, i, "Change Context", rowInfoAndExpectedAccessRights, comment);
			log.info(comment);	
			
			extent.flush();	
		}else{
						
			String[] validationResult = new String[2];
			
				if(objectName.equalsIgnoreCase(Config.DESCRIBE_DOCUMENT)){
					DescribeDocumentAccessRightsValidationSuite describeDocumentSuite = new DescribeDocumentAccessRightsValidationSuite(); 
					validationResult =	describeDocumentSuite.verifyChangeContextAccessRightForDescribeDocument(objectName, Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status, ADVANCE_SEARCH_BY_NAME);
						
					}else if(objectName.equalsIgnoreCase(Config.REFERENCE_DOCUMENT)){
						ReferenceDocumentAccessRightsValidationSuite referenceDocumentSuite = new ReferenceDocumentAccessRightsValidationSuite();
						validationResult =	referenceDocumentSuite.verifyChangeContextAccessRightForReferenceDocument(objectName, Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status, ADVANCE_SEARCH_BY_NAME);

					}else if(objectName.equalsIgnoreCase(Config.CAD_PART)){
						CADDocumentAccessRightsValidationSuite CADDocSuite = new CADDocumentAccessRightsValidationSuite();
						validationResult =	CADDocSuite.verifyChangeContextAccessRightForCADDocument(objectName,Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status, Config.CAD_PART_NAME);

					}else if(objectName.equalsIgnoreCase(Config.CATALOG_PART)){
						CatalogPartAccessRightsValidationSuite CatalogPartSuite = new CatalogPartAccessRightsValidationSuite();
						validationResult =	CatalogPartSuite.verifyChangeContextAccessRightForCatalogPart(objectName, Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status, ADVANCE_SEARCH_BY_NAME);

					}else if(objectName.equalsIgnoreCase(Config.ENGINEERED_PART)){
						EngineeredPartAccessRightsValidationSuite engineeredPartSuite = new EngineeredPartAccessRightsValidationSuite();
						validationResult =	engineeredPartSuite.verifyChangeContextAccessRightForEngineeredPart(objectName, Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status, ADVANCE_SEARCH_BY_NAME);

					}else if(objectName.equalsIgnoreCase(Config.MANUFACTURER_PART)){
						ManufacturerPartAccessRightsValidationSuite manufaturerPartSuite = new ManufacturerPartAccessRightsValidationSuite();
					validationResult =	manufaturerPartSuite.verifyChangeContextAccessRightForManufacturerPart(objectName, Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status, ADVANCE_SEARCH_BY_NAME);

					}else if(objectName.equalsIgnoreCase(Config.MOUNTING_CONTEXT_PART)){
						MountingContextPartAccessRightValidationSuite MountingContextPartSuite = new MountingContextPartAccessRightValidationSuite();
						validationResult =	MountingContextPartSuite.verifyChangeContextAccessRightForMountingContextPart(objectName, Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status, ADVANCE_SEARCH_BY_NAME);

					}else if(objectName.equalsIgnoreCase(Config.OBJECT_LIST_PART)){
						ObjectListPartAccessRightsValidationSuite ObjectListPartSuite = new ObjectListPartAccessRightsValidationSuite();
					validationResult =	ObjectListPartSuite.verifyChangeContextAccessRightForObjectListPart(objectName, Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status, ADVANCE_SEARCH_BY_NAME);

					}else if(objectName.equalsIgnoreCase(Config.RAWMATERIAL_PART)){
						RawMaterialPartAccessRightsValidationSuite RawMaterialPartSuite = new RawMaterialPartAccessRightsValidationSuite();
						validationResult =	RawMaterialPartSuite.verifyChangeContextAccessRightForRawMaterialPart(objectName, Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status, ADVANCE_SEARCH_BY_NAME);

					}else if(objectName.equalsIgnoreCase(Config.TREATMENT_PART)){
						TreatmentPartAccessRightsValidationSuite treatmentPartSuite = new TreatmentPartAccessRightsValidationSuite();
						validationResult =	treatmentPartSuite.verifyChangeContextAccessRightForTreatmentPart(objectName, Config.fetchContainerOrLibrary(currentTemplate), currentTemplate, status, ADVANCE_SEARCH_BY_NAME);

					}else if(objectName.equalsIgnoreCase(Config.MANAGED_COLLECTION)){
						validationResult[0]  = "NA";
						
					}else if(objectName.equalsIgnoreCase(Config.CHANGE_NOTICE)){
						validationResult[0]  = "NA";
						
	               }else if(objectName.equalsIgnoreCase(Config.CHANGE_TASK)){
	            	   validationResult[0]  = "NA";
					
	               }else if(objectName.equalsIgnoreCase(Config.PROMOTION_REQUEST)){
	            	   validationResult[0]  = "NA";

					}else if(objectName.equalsIgnoreCase(Config.PROBLEM_REPORT)){
						validationResult[0]  = "NA";
											
					}else if(objectName.equalsIgnoreCase(Config.FOLDER)){
						validationResult[0] = "NA";
						
					}

				
				// ********** Writing the results into excel after execution completed ***************************
				
				excel.writeResult(report, changeContextAccess, validationResult[0], sheetName, i, "Change Context", rowInfoAndExpectedAccessRights, validationResult[1]);
				extent.flush();
				
	}
		// Create by move Access Permission is Not Applicable for All Object Types
		
		String createByMoveComment = "Create by move access right is NA for all object types. You can't test it directly and it is internal access right for Change Domaian and Change Context";
		excel.writeResult(report, createByMoveAccess, "NA", sheetName, i, "Create by move", rowInfoAndExpectedAccessRights,createByMoveComment);
		log.info(createByMoveComment);
		
		extent.flush();

		}else{
			
			log.info("User is not navigated to required location");
	        
	        report.log(Status.WARNING, "User is failed to navigat "+ Config.fetchContainerOrLibrary(currentTemplate));
	        extent.flush();
		}
	
	}else{
		String comment = "User is not Valid since this User ID is not mapped in Authentication Details.xls";
		log.info(comment);
		
		report.log(Status.WARNING, comment);
		extent.flush();
	}	
	
		log.info("####################### Execution Completed for the Row Number "+ (i+1) +" in " +objectType+" Sheet ##################################"  );
		
	} // for loop end	

}
}

