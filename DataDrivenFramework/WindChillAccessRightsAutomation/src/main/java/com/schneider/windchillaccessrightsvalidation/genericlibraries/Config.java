package com.schneider.windchillaccessrightsvalidation.genericlibraries;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * @author MADHUBABU
 * 20-Dec-2016
 */

public class Config {
	
	private static final String CONFIGURATION_FILE = "C:\\Users\\SESA439753\\Desktop\\Runtime Parameters.properties";

    public static String runTimeURL = null;
	public static String runTimeBrowser  = null;
	public static String runTimeRole  = null;
	public static String runTimeRow = null;
	
	static {
		 
		 try{
			 FileInputStream fis = new FileInputStream(CONFIGURATION_FILE);
			Properties Environment_property = new Properties();
			Environment_property.load(fis);
			
			runTimeURL= Environment_property.getProperty("URL");
			runTimeBrowser = Environment_property.getProperty("Browser");
			runTimeRole = Environment_property.getProperty("Role");
			runTimeRow= Environment_property.getProperty("RowNumber");
			
		 }catch(Exception e){
				System.out.println(e);
			}
			}
			
			
	// ************************** File Paths  ************************************************************************ 
	
	
public static final String BASE_FOLDER_PATH = System.getProperty("user.dir");
public static final String DRIVERS_FOLDER_PATH = BASE_FOLDER_PATH+"\\Drivers";
public static final String AUTHENTICATION_DETAILS_FILE_PATH = BASE_FOLDER_PATH + "\\SourceFiles\\Athentication Details.xls";
public static final String SSO_AUTHENTICATION_DETAILS_FILE_PATH = BASE_FOLDER_PATH + "\\SourceFiles\\Athentication Details SSO.xls";
public static final String AUTHENTICATION_DETAILS_NEXTGEN_FILE_PATH = BASE_FOLDER_PATH + "\\SourceFiles\\AuthenticationDetails for NEXTGEN Env.xls";
public static final String CLEAR_CACHE_BATCHFILE_PATH = BASE_FOLDER_PATH+ "\\BatchFiles\\clearcachecmd.bat";
public static final String NEXTGEN_ACCESS_RIGHTS_MANAGEMENT_RESULT_FILE_PATH = "C:\\Users\\SESA439753\\Desktop\\Schneider WindChill Document Types and Access Rights.xls";
public static final String HTML_REPORT_FOLDER_PATH = "C:\\Automation Logs and Reports\\HTML Reports\\";
public static final String LOGS_FOLDER_PATH = "C:\\Automation Logs and Reports\\logs\\";
public static final String EXCEL_REPORT_FOLDER_PATH = "C:\\Automation Logs and Reports\\Excel Reports\\";
public static final String ADVANCED_HTML_REPORT_FOLDER_PATH = "C:\\Automation Logs and Reports\\Advanced HTML Reports\\";
//public static final String NEXTGEN_ACCESS_RIGHTS_MANAGEMENT_DESKTOP_FILE_PATH = "";

public static final String NEXTGEN_ACCESS_RIGHTS_MANAGEMENT_FILEPATH = BASE_FOLDER_PATH + "\\SourceFiles\\Schneider WindChill Document Types and Access Rights.xls";
public static final String LOG4J_PROPERTIES = System.getProperty("user.dir")+ File.separator+"logConfiguration\\log4j.properties";
public static final String AUTOIT_SCRIPTS_PATH = BASE_FOLDER_PATH+"\\AutoITFiles\\FileUpload.exe";
public static final String IE_LOGIN_EXE_AUTOIT_FILE_PATH = BASE_FOLDER_PATH+"\\AutoITFiles\\IELogin.exe";
public static final String IE_USERNAME_FILE = BASE_FOLDER_PATH+"\\Drivers\\User_Name.txt";
public static final String RESULT_FILE_PATH = BASE_FOLDER_PATH+"\\SourceFiles\\ResultFilePath.txt";
public static final String UPLOAD_FILE_PATH = BASE_FOLDER_PATH+"\\temp\\uploadfilepath.txt";
public static final String UPLOAD_FILE =BASE_FOLDER_PATH+"\\temp\\FileForUpload.txt";
public static final String PROMOTIONREQUESTNAME_FILE_PATH = BASE_FOLDER_PATH+"SourceFiles\\PromotionRequestName.txt";


// *************************** SSO FILE PATHs ************************************************

public static final String SSO_NEXTGEN_ACCESS_RIGHTS_MANAGEMENT_RESULT_FILE_PATH_CATALOG_PART = BASE_FOLDER_PATH + "\\SourceFiles\\Schneider WindChill Document Types and Access Rights_Catalog Part.xls";
public static final String SSO_NEXTGEN_ACCESS_RIGHTS_MANAGEMENT_RESULT_FILE_PATH_DESCRIBE_DOCUMENT = BASE_FOLDER_PATH + "\\SourceFiles\\Schneider WindChill Document Types and Access Rights_Describe Document.xls";
public static final String SSO_NEXTGEN_ACCESS_RIGHTS_MANAGEMENT_RESULT_FILE_PATH_REFERENCE_DOCUMENT = BASE_FOLDER_PATH + "\\SourceFiles\\Schneider WindChill Document Types and Access Rights_Reference Document.xls";
public static final String SSO_NEXTGEN_ACCESS_RIGHTS_MANAGEMENT_RESULT_FILE_PATH_OBJECT_LIST_PART = BASE_FOLDER_PATH + "\\SourceFiles\\Schneider WindChill Document Types and Access Rights_Object List.xls";
public static final String SSO_NEXTGEN_ACCESS_RIGHTS_MANAGEMENT_RESULT_FILE_PATH_MOUNTING_CONTEXT_PART = BASE_FOLDER_PATH + "\\SourceFiles\\Schneider WindChill Document Types and Access Rights_Mounting Context Part.xls";
public static final String SSO_NEXTGEN_ACCESS_RIGHTS_MANAGEMENT_RESULT_FILE_PATH_MANUFACTURER_PART = BASE_FOLDER_PATH + "\\SourceFiles\\Schneider WindChill Document Types and Access Rights_Manufacturer Part.xls";
public static final String SSO_NEXTGEN_ACCESS_RIGHTS_MANAGEMENT_RESULT_FILE_PATH_MANAGED_COLLECTION = BASE_FOLDER_PATH + "\\SourceFiles\\Schneider WindChill Document Types and Access Rights_Managed Collection.xls";
public static final String SSO_NEXTGEN_ACCESS_RIGHTS_MANAGEMENT_RESULT_FILE_PATH_RAWMATERIAL_PART = BASE_FOLDER_PATH + "\\SourceFiles\\Schneider WindChill Document Types and Access Rights_Raw Materail Part.xls";
public static final String SSO_NEXTGEN_ACCESS_RIGHTS_MANAGEMENT_RESULT_FILE_PATH_TREATMENT_PART = BASE_FOLDER_PATH + "\\SourceFiles\\Schneider WindChill Document Types and Access Rights_Treatment Part.xls";
public static final String SSO_NEXTGEN_ACCESS_RIGHTS_MANAGEMENT_RESULT_FILE_PATH_CAD_DOCUMENT_PART = BASE_FOLDER_PATH + "\\SourceFiles\\Schneider WindChill Document Types and Access Rights_CAD Document.xls";
public static final String SSO_NEXTGEN_ACCESS_RIGHTS_MANAGEMENT_RESULT_FILE_PATH_ENGINEERED_PART = BASE_FOLDER_PATH + "\\SourceFiles\\Schneider WindChill Document Types and Access Rights_Engineered Part.xls";
public static final String SSO_NEXTGEN_ACCESS_RIGHTS_MANAGEMENT_RESULT_FILE_PATH_PROBLEM_REPORT = BASE_FOLDER_PATH + "\\SourceFiles\\Schneider WindChill Document Types and Access Rights_Problem Report.xls";
public static final String SSO_NEXTGEN_ACCESS_RIGHTS_MANAGEMENT_RESULT_FILE_PATH_PROMOTION_REQUEST = BASE_FOLDER_PATH + "\\SourceFiles\\Schneider WindChill Document Types and Access Rights_Promotion Request.xls";
public static final String SSO_NEXTGEN_ACCESS_RIGHTS_MANAGEMENT_RESULT_FILE_PATH_CHANGE_NOTICE = BASE_FOLDER_PATH + "\\SourceFiles\\Schneider WindChill Document Types and Access Rights_Change Notice.xls";
public static final String SSO_NEXTGEN_ACCESS_RIGHTS_MANAGEMENT_RESULT_FILE_PATH_CHANGE_TASK = BASE_FOLDER_PATH + "\\SourceFiles\\Schneider WindChill Document Types and Access Rights_Change Task.xls";
public static final String SSO_NEXTGEN_ACCESS_RIGHTS_MANAGEMENT_RESULT_FILE_PATH_FOLDER = BASE_FOLDER_PATH + "\\SourceFiles\\Schneider WindChill Document Types and Access Rights_Folder.xls";
public static final String SSO_NEXTGEN_ACCESS_RIGHTS_MANAGEMENT_RESULT_FILE_DEBUG = BASE_FOLDER_PATH + "\\SourceFiles\\Schneider WindChill Document Types and Access Rights_Debug.xls";


//****************  parameter for User Roles  **************************************************

public static final String APPROVER = "Approver";
public static final String AUTHOR = "Author";
public static final String DATA_MANAGER = "Data Manager";
public static final String PRODUCTION_VIEWER = "Production Viewer";
public static final String INDUSTRILIZATION_VIEWER = "Industrilization Viewer";
public static final String INDUSTRILIZATION_AND_PRODUCTION_VIEWER = "Industr And Production Viewer";
public static final String VIEWER ="Viewer";
public static final String OWNER = "Owner";
public static final String UNRESTRICTED_VIEWER = "Unrestricted Viewer";
public static final String FOLDER_MANAGER = "Folder Manager";
public static final String TEAM_MANAGER = "Team Manager";
public static final String CN_CREATOR = "CN Creator";
public static final String CN_AUDITOR = "CN Auditor";
public static final String CIB_REVIEWER ="CIB Reviewer";
public static final String EXPORT_USER = "Export User";
public static final String PRODUCT_MANAGER = "Product Manager";
public static final String LIBRARY_MANAGER = "Library Manager";

//****************  parameter for User Role Assignment  **************************************************

public static final String SSO_USER_ROLE = runTimeRole;

//public static final String SSO_USER_ROLE_FOR_CHANGE_NOTICE_AND_CHANGE_TASK = CN_CREATOR;

public static final String PASSWORD = "nextgen";

//****************  Browser Variable    ************************************************ /////// 

// This will help you to execute your scripts on required browsers, all you need to is change value for browser parameter as below.

//  ********* "chrome" for execution on CHROME browser *************
//  ******** "firefox" for execution on FIREFOX browser ************
// ******** "ie" for execution on INTERNET EXPLORER browser ********

public static final String BROWSER = runTimeBrowser;


//  **************************  Parameter for Server URL ****************************************************//

// for the Firefox and Chrome browsers we need to update the URL as bewlow.
//Ex:  if the Server Url is” https://nextgen-demoa.schneider-electric.com:4430/Windchill/app/ “
//then we need to remove “ https:// “ and assign the rest of the URL to SERVER_URL variable as shown below. 

public static final String SERVER_URL_FOR_IE = "https://nextgen-demoa.schneider-electric.com:4430/Windchill/app/";

public static final String DEMOA_URL = "nextgen-demoa.schneider-electric.com:4430/Windchill/app/";

public static final String PRE_PROD_URL = "https://nextgen-preprod.schneider-electric.com:4430/Windchill/";

public static final String PRE_PROD2_URL = "https://nextgen-preprod2.schneider-electric.com:4430/Windchill/";


public static final String SERVER_URL = runTimeURL;


// TO connect to Creo MDM Server
public static final String CREO_ENV_URL ="https://nextgen-demoa.schneider-electric.com:4430/Windchill";

// ***********************  Parameters for Templates ************************************

public static final String PRODUCT_TEMPLATE = "Schneider Product Template";
public static final String PRODUCT_TEMPLATE_RESTRICTED = "Schneider Product Template (__00. Restricted Area)";
public static final String COMPONENTS_LIBRARY_TEMPLATE = "Components Library Template";
public static final String COMPONENTS_LIBRARY_TEMPLATE_RESTRICTED = "Components Library Template (__00. Restricted Area)";
public static final String RAWMATERIAL_AND_TREATMENTS = "Raw Materials and Treatments Library Template";
public static final String RAWMATERIAL_AND_TREATMENTS_RESTRICTED = "Raw Materials and Treatments Library Template (__00. Restricted Area)";
public static final String ADMIN_LIBRARY_TEMPLATE ="Admin Library Template";


//  ******************** Parameters for Containers ****************************************

public static final String COMPONENTS_LIBRARY = "Verification_Comp_Library";
public static final String RAWMATERIALS_LIBRARY ="Verification_Raw_Library";
public static final String ADMIN_LIBRARY = "Verification_Admin_library";
public static final String PRODUCT_CONTAINER ="Verif_Test_Automation";

// *******************************  Parameters for Domains  ******************

public static final String RESTRICTED_DOMAIN = "__00. Restricted Area";
public static final String CONFIDENTIAL_DOMAIN = "Confidential Area";

public static final String TESTDATA_FOLDER = "AutomationTestData";
public static final String CHANGE_CONTEXT_TO = "Verification_Product_1";


// ************************* Parameters for Workspaces ****************************


public static final String COMP_RESTRICTED_CADDOC_WORKSPACE_NAME = "Test_Automation_Restricted";
public static final String COMP_CADDOC_WORKSPACE_NAME = "Test_Automation";
public static final String RAW_RESTRICTED_CADDOC_WORKSPACE_NAME = "RAW_Test_Automation_Restricted";
public static final String RAW_CADDOC_WORKSPACE_NAME = "RAW_Test_Automation";
public static final String PROD_RESTRICTED_CADDOC_WORKSPACE_NAME = "Prod_Test_Automation_Restricted";
public static final String PROD_CADDOC_WORKSPACE_NAME = "Prod_Test_Automation";
public static final String ADMIN_CADDOC_WORKSPACE_NAME = "Admin_Test_Automation";


// ++++++++++++++++++ Parameters Row Number initiation for the Execution ************************************

// ############## Keep the row number for the respective document type initial row number variable from which row you want to start execution so that it will start execution from that number of Row #####################################

public static int describeDocumentSuiteInitialRowNumber = Integer.parseInt(runTimeRow);
public static int referenceDocumentSuiteInitialRowNumber = Integer.parseInt(runTimeRow);
public static int engineeredPartSuiteInitialRowNumber = Integer.parseInt(runTimeRow);
public static int manufacturerPartSuiteInitialRowNumber = Integer.parseInt(runTimeRow);
public static int objectListPartSuiteInitialRowNumber = Integer.parseInt(runTimeRow);
public static int mountingContextPartSuiteInitialRowNumber = Integer.parseInt(runTimeRow);
public static int CADPartSuiteInitialRowNumber = Integer.parseInt(runTimeRow);
public static int treatmentPartSuiteInitialRowNumber = Integer.parseInt(runTimeRow);
public static int catalogPartSuiteInitialRowNumber = Integer.parseInt(runTimeRow);
public static int rawMaterialPartSuiteInitialRowNumber = Integer.parseInt(runTimeRow);  
public static int managedCollectionSuiteInitialRowNumber = Integer.parseInt(runTimeRow);
public static int promotionRequestSuiteInitialRowNumber = Integer.parseInt(runTimeRow);
public static int changeNoticeSuiteInitialRowNumber = Integer.parseInt(runTimeRow);
public static int changeTaskSuiteInitialRowNumber = Integer.parseInt(runTimeRow);
public static int problemReportSuiteInitialRowNumber = Integer.parseInt(runTimeRow);
public static int folderSuiteInitialRowNumber = Integer.parseInt(runTimeRow);
public static int allSuiteInitialRowNumber = Integer.parseInt(runTimeRow);

////  ********************  Parameters for Object Types for Control Execution *************************************************************

public static final String DESCRIBE_DOCUMENT = "Describe Document";
public static final String REFERENCE_DOCUMENT = "Reference Document";
public static final String CAD_PART = "CAD Document";
public static final String ENGINEERED_PART = "Engineered Part";
public static final String MANUFACTURER_PART = "Manufacturer Part";
public static final String MOUNTING_CONTEXT_PART = "Mounting Context Part";
public static final String RAWMATERIAL_PART = "Raw Material Part";
public static final String OBJECT_LIST_PART = "Object List";
public static final String CATALOG_PART = "Catalog Part";
public static final String TREATMENT_PART = "Treatment Part";
public static final String MANAGED_COLLECTION = "Managed Collection";
public static final String PROMOTION_REQUEST = "Promotion Request";
public static final String CHANGE_NOTICE = "Change Notice";
public static final String CHANGE_TASK = "Change Task";
public static final String PROBLEM_REPORT = "Problem Report";
public static final String FOLDER = "Folder";
public static final String ALL = "All";
public static final String DEBUG = "Debug";


// ################################  All Scenarios Parameters ###############################################

public static final String OBJECT_NAME_ALL = ENGINEERED_PART;
public static final String STATUS_ALL = "In Creation";
public static final String ROLE_ALL = "Product Manager";


public static final String OBJECT_NAME = DESCRIBE_DOCUMENT;
//public static final String STATUS = "In Creation";


/// ******************************* Parameters for Object Names for Test Data Creation and Searching test data ******************* ///////////

public static final String RESTRICTED ="Restrict_";

public static final String DESCRIBE_DOCUMENT_NAME = "DescribeDocument";
public static final String REFERENCE_DOCUMENT_NAME = "ReferenceDocument";
public static final String CAD_PART_NAME = "CAD_CD";
public static final String ENGINEERED_PART_NAME = "EngPART_CD";
public static final String MANUFACTURER_PART_NAME = "Manfctr_Part_CD";
public static final String MOUNTING_CONTEXT_PART_NAME = "MC_PART_CD";
public static final String RAWMATERIAL_PART_NAME = "RMP_CD";
public static final String OBJECT_LIST_PART_NAME = "ObjList_Part_CD";
public static final String CATALOG_PART_NAME = "Ctlg_Part_CD";
public static final String TREATMENT_PART_NAME = "Trtmt_Part_CD";
public static final String MANAGED_COLLECTION_NAME = "MngCol_CD";
public static final String PROMOTION_REQUEST_NAME = "Prm_Req_CD";
public static final String CHANGE_NOTICE_NAME = "Chng_Ntc_CD";
public static final String CHANGE_TASK_NAME = "Chng_Tsk_CD";
public static final String PROBLEM_REPORT_NAME = "PrbReport_CD";
public static final String FOLDER_NAME = "Folder";

public static final String DESCRIBEDOCUMENT_FOR_CHANGE_NOTICE = "DONOTDELETE_DSCRBEDOC_TSTNG_FOR_CHNGNTC";
public static final String CHANGENOTICE_FOR_CHANGETASK  = "DONOTDELETE_CHNGNOTICE_TSTNG_FOR_CHNGTSK";

public static final String getSearchObjectName(String objectName){
	
	String ObjectName = "";
	
	if(objectName.equalsIgnoreCase(DESCRIBE_DOCUMENT)){
		ObjectName =DESCRIBE_DOCUMENT_NAME;
	} else if(objectName.equalsIgnoreCase(REFERENCE_DOCUMENT)){
		ObjectName = REFERENCE_DOCUMENT_NAME;
	} else if(objectName.equalsIgnoreCase(CAD_PART)){
		ObjectName = CAD_PART_NAME;
	} else if(objectName.equalsIgnoreCase(ENGINEERED_PART)){
		ObjectName = ENGINEERED_PART_NAME;
	} else if(objectName.equalsIgnoreCase(MANUFACTURER_PART)){
		ObjectName = MANUFACTURER_PART_NAME;
	} else if(objectName.equalsIgnoreCase(MOUNTING_CONTEXT_PART)){
		ObjectName = MOUNTING_CONTEXT_PART_NAME;
	} else if(objectName.equalsIgnoreCase(RAWMATERIAL_PART)){
		ObjectName = RAWMATERIAL_PART_NAME;
	} else if(objectName.equalsIgnoreCase(OBJECT_LIST_PART)){
		ObjectName = OBJECT_LIST_PART_NAME;
	} else if(objectName.equalsIgnoreCase(CATALOG_PART)){
		ObjectName = CATALOG_PART_NAME;
	} else if(objectName.equalsIgnoreCase(TREATMENT_PART)){
		ObjectName = TREATMENT_PART_NAME;
	} else if(objectName.equalsIgnoreCase(MANAGED_COLLECTION)){
		ObjectName = MANAGED_COLLECTION_NAME;
	} else if(objectName.equalsIgnoreCase(PROMOTION_REQUEST)){
		ObjectName = PROMOTION_REQUEST_NAME;
	} else if(objectName.equalsIgnoreCase(CHANGE_NOTICE)){
		ObjectName = CHANGE_NOTICE_NAME;
	} else if(objectName.equalsIgnoreCase(CHANGE_TASK)){
		ObjectName = CHANGE_TASK_NAME;
	} else if(objectName.equalsIgnoreCase(PROBLEM_REPORT)){
		ObjectName = PROBLEM_REPORT_NAME;
	} else if(objectName.equalsIgnoreCase(FOLDER_NAME)){
		ObjectName = FOLDER;
	} 
	
	return ObjectName;
}

public static final String fetchContainerOrLibrary(String template){
	
	String container = "";
	
	if(template.trim().equalsIgnoreCase(COMPONENTS_LIBRARY_TEMPLATE)||template.trim().contains(COMPONENTS_LIBRARY_TEMPLATE_RESTRICTED)){
	container = COMPONENTS_LIBRARY.trim();
	}else if(template.trim().equalsIgnoreCase(RAWMATERIAL_AND_TREATMENTS)||template.trim().contains(RAWMATERIAL_AND_TREATMENTS_RESTRICTED) ){
	container = RAWMATERIALS_LIBRARY.trim();
	} else if(template.trim().equalsIgnoreCase(ADMIN_LIBRARY_TEMPLATE)){
	container = ADMIN_LIBRARY.trim();
	} else if(template.trim().equalsIgnoreCase(PRODUCT_TEMPLATE)||template.trim().contains(PRODUCT_TEMPLATE_RESTRICTED)){
	container = PRODUCT_CONTAINER.trim();
	} 
	
	
	return container;
	
}

// ***************** Parameters Row Number initiation for Test Data Creation ************************************

public static int describeDocumentTestDataSuiteInitialRowNumber = 2;
public static int referenceDocumentTestDataSuiteInitialRowNumber = 2;
public static int engineeredPartTestDataSuiteInitialRowNumber = 2;
public static int manufacturerPartTestDataSuiteInitialRowNumber = 2;
public static int objectListPartTestDataSuiteInitialRowNumber = 2;
public static int mountingContextPartTestDataSuiteInitialRowNumber = 2;
public static int CADPartTestDataSuiteInitialRowNumber = 2;
public static int treatmentPartTestDataSuiteInitialRowNumber = 2;
public static int catalogPartTestDataSuiteInitialRowNumber = 2;
public static int rawMaterialPartTestDataSuiteInitialRowNumber = 2;
public static int managedCollectionSuiteTestDataInitialRowNumber = 2;
public static int promotionRequestSuiteTestDataInitialRowNumber = 2;
public static int changeNoticeTestDataSuiteInitialRowNumber = 2;
public static int changeTaskTestDataSuiteInitialRowNumber = 2;
public static int problemReportTestDataSuiteInitialRowNumber = 2;
public static int folderTestDataSuiteInitialRowNumber = 2;


public static void main(String args[]) throws Exception{
		
	/*System.out.println("AUTOIT_SCRIPTS_PATH  : "+ AUTOIT_SCRIPTS_PATH);
	System.out.println("LOG4J_PROPERTIES : "+ LOG4J_PROPERTIES);
	System.out.println("AUTHENTICATION_DETAILS_FILE_PATH : "+ AUTHENTICATION_DETAILS_FILE_PATH);
	System.out.println("NEXTGEN_ACCESS_RIGHTS_MANAGEMENT : "+ NEXTGEN_ACCESS_RIGHTS_MANAGEMENT_FILEPATH);*/
	//System.out.println("Base Folder Path : "+ BASE_FOLDER_PATH);
	//System.out.println("SSO NEXTGEN_ACCESS_RIGHTS_MANAGEMENT : "+SSO_NEXTGEN_ACCESS_RIGHTS_MANAGEMENT_RESULT_FILE_PATH_CATALOG_PART);

	System.out.println( " URL is :"+ runTimeURL);
	System.out.println( " Browser is :"+ runTimeBrowser);
	System.out.println( " ROLE is :"+ runTimeRole);
	System.out.println( " ROW is :"+ Integer.parseInt(runTimeRow));
	
}



}
