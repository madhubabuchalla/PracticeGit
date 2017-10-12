package com.schneider.windchillaccessrightsvalidation.repositories;

public class ContentRepository {
	
	public static String  productType_ObjectList = "Object List"; 
	public static String productTypeDD = "Describe Document";
	public static String productTypeEng = "Engineered Part";
	public static String productTypeCatPart = "Catalog Part";
	public static String productTypeMCP = "Mounting Context Part";
	
	public static String PRODUCTTYPE_RAWMATERIAL = "Raw Material Part";
	
	public static String PRODUCTTYPE_MANUFATURER = "Manufacturer Part";

	public static String failedScreenshotPath = "C:\\Shobhit\\windchillWorkspace\\schneiderwindchillR&Dsystem\\CommonUtility\\FailedScreenshot\\";

	public static String screenshotName = "windchill";

	public static String elementDropDown = "//select[@id='createType']"; 
	
	 // Create document : Describe document
	 
	public static String objectTypeCreateDocument;// = "Describe Document";
	
	public static String  primaryContentSource= "Local File";
	
	public static String documentTypeValue = "3D MODEL - ASSEMBLY";
	
	
	// ************ CAD Document Creation *******************
	
	public static String CAD_Template = "org_se_in_basic.prt";

	/*
	 * Element location of 'Read' Elements for Authorized and UnAuthorized
	 * methods
	 */

	public static String GlobalUser = "globalUser";

	/*
	 * ModifyIdentity
	 */
	
	//public static String ModifuDocumentName = "Modify" + sysTime;

	// Set the document number
	public static final String SET_THE_DOCUMENT_NUMBER = "D0000101024";

	// Change the document number
	public static final String UPDATE_THE_DOCUMENT_NUMBER = "D0000202033";

	/*
	 * 'Describe document' : Set State (with and without permission)
	 */
	
	public static String setStateSearchContent = "SetState";
	
	public static String actualReadDocument = "ReadAction";
	
///	Content for Download Access Right
	
	
	public static String downloadContent = "TestData26oct";
	public static String downloadContentUnAuthorized = "TestData26oct";
	/*
	 * Engineering Part variables
	 */

	public static String expectedContainer = "Verif_Test_Automation";
	public static String objectType = "Engineered Part";
	public static String searchString = "EngineeringPartDocument1476790010818";
	public static String expectedSearchString = "EngineeringPartDocument1476790010818";
	
	public static String expectedSearchValidationText = "No matches found";
	
	// Need to change variable name after run 'Modify' script

	String modifyVariable = "EngPartModify1476938995042"; 
	
	
	
	/*******************Manufacturer part******************/
	
	//common library
	public static String RAW_TREAT_CONTAINER_VALUE = "Raw Materials and Treatments";
	
	public static String MANUF_CONTAINER_VALUE = "Manufacturer Parts";
	public static String manufexpectedValue = "Manufacturer Parts";
	public static String manufsearchText ="v*";
	
	

	/*****************************Change domain*****************/
	public static String changeContentDocument = "Changedomain";
	public static String changeexpectedStatus = "Failed with overridable conflicts";
	public static String changeTargetFolder ="Restricted Documents Area";
	
	

	/**********************************Advance search************/
	//SESA439756_DM,Describe Document,Verif_A_Demo,Product & In Creation
	// Engineered Part , Describe Document
	 public static String ADV_SEARCH_OBJECT_TYPE="CAD Document";
	 //In Creation 
	 public static String ADV_SEARCH_STATE_STATUS="In Creation";
	 //Verif_A_Demo, Verif_Test_Automation
	 public static String ADV_SEARCH_CONTAINER_NAME="Verif_A_Demo";
	 //context value is Product , Library 
	 public static String ADV_SEARCH_CONTEXT_TYPE_DROPDOWN_VALUE="Product";
	 
    public static String ADVANCE_SEARCH_BY_USER="SESA439756";
   
    public static String ADVANCE_SEARCH_BY_NAME="c";
	
    
	
	
	//  Mounting Context Part and Object LIst
	
	public static String  productType_MCP = "Mounting Context Part"; 

	public static String MCPnameVariable = "MCP";
	public static String MCPnumber = "EMC";
	public static String ObjectListnameVariable = "ojectList";
	public static String ObjectListnumber = "OL";
	
	

	
	
	//----------- Object Type : Folder --------------------------------
	
	public static String currentFolderName;
	public static String folderNameRead="TestFolder1477478155681";
	
	
	
	/********************Reference document************************************/
	/*
	 * Variable which we have used in 'Reference  document' : Create document (with and without permission)
	 */
	public static String refdropDownValue = "Reference Document"; // for create document [describe document]
	//public static String refdocumentName = "RefTestDocument" + sysTime;
	public static String refdocumentTypeValue = "C08-INTERNAL SUPPLIER CONTRACT REVIEW";
	public static String refactualDocumentName;	
	public static String refobjectTypeCreateDocument;// = "Reference Document";
	

	// Download action : Reference document
	public static String refdownloadContent = "RefModifyContentPermission";
	public static String refdownloadContentUnAuthorized = "RefTestData2Nov";
	
    //Reference document' : Modify Content (with permission)
    public static String refmodifyContentSearchContent = "RefModifyContentPermission";
   
    //'Reference document' : Modify Identity (with and without permission)
	public static String refmodifyIdentitySearchContent = "RefModifyIdentity";
	
	// Modify Identity : Reference document
	
	public static String refnewContentNumber;
	public static String refexpectedSearchContent;
	public static String refSearchValidationText = "No matches found";
		
	// Modify permission : Reference document
	public static String refModifyDocumentName = "RefModifyIdentity";											
	public static String refcurrentContentName;
	public static String refmodifiedContentName;
		
	  // Variable which we have used in 'Reference document' : Read document (with and without permission)
	public static String refactualReadDocument = "refReadAction";
	
 
	//reference  document' : Set State (with and without permission)
	
	public static String refsetStateSearchContent = "refdoc123";
			//  "RefSetState";
	
	
	/*******************raw material part******************/
	
	/*
	 * Variable which we have used in 'raw material part' : Create document (with and without permission)
	 */
	
//	public static String rawdocumentName = "RawTestDocument" + sysTime;
	public static String OwnerOrganization = "Buildings Product Management";
	public static String KeytoPerformance = "Yes - Key to Performance, restricted Sourcing Change possibilities";
	public static String corpPreferenceComment = "Corporate Preference Comment";
	public static String defaultUnit = "Foot";
	public static String corporatePrefDropDown = "Approved";
	public static String catalog_technical_classification_value = "Ecad Objects";
	public static String raw_technical_classification_value = "Plastic Additives";
	public static String treatment_technical_classification_value = "Thermal Treatments";
	
	public static String environmental_profile_value ="No Requirement";
	public static String assemblymode_value = "Inseparable";
	public static String source_value = "Buy";
	public static String default_tracecode_Value = "Serial Number";
	public static String part_end_item_value = "No";
	
	public static String TARGET_FOLDER_ID = "Restricted Documents Area";
	
	
	//--------------- Promotional Request ---------------
		public static String targetPromotionState = "In Industrialization";
		public static String participantOption = "Members By Role";
		
		
	// **************************Change Task**************

	// Change Task send change notice
	public static String CN_TASK_ADV_SEARCH_VALUE = "Change Notice";

	// CN1479899502840, *.*
	// Change notice option by change notice name eg:CN148007688025353 , CN*
	public static String CN_TASK_ADVANCE_SEARCH_BY_NAME = "DONOTDELETE_TESTING_FOR_CHANGETASK";
	public static String CN_TASK_ADV_SEARCH_STATE = "Open";

	// Modify change notice
	public static String ChangeTaskAttributeName = "CNTaskModify" + System.currentTimeMillis();

	// Read action "(Default task) Modify affected objects" , "CNTask*"
	public static String ChangeTaskName = "(Default task) Modify affected objects";

	// Modify action "(Default task) Modify affected objects" , "CNTask*"
	public static String ChangeTaskStateSearch = "Open";
	//public static String ChangeTaskModifyName = "CNTModify" + sysTime;

	// Set state change task (old state--ChangeTaskStateSearch="Open")
	public static String ChangeTaskNewStateSet = "Completed";

	/********************************** ChangeNoticeAdvancesearch ************/
	// Change Notice, Name-Change notice
	// Change Notice
	public static String CN_ADV_SEARCH_VALUE = "Change Notice";

	public static String CN_ADVANCE_SEARCH_BY_NAME = "T*";
	public static String CN_DescribeDocument_FileName = "DONOTDELETE_TESTING_FOR_CHANGENOTICE";
	// **************************Change notice**************
	// Change notice name with system time
	public static String CHANGENOTICENAME = "CN" + System.currentTimeMillis();
	// Full Track (COMPLEX) Fast Track (SIMPLE)
	public static String CHANGENOTICECOMPLEXITY = "Fast Track (SIMPLE)";
		
	//Modify change notice
	 public static String ChangeNoticeAttributeName = "CNModify" + System.currentTimeMillis();	
	 
	 //Set state
	 public static String CN_LIFECYCLE="Canceled";
		
		
	 /********************************Problem Report*******************************/
	    
	    public static String PR_NAME="PR_CD_"+System.currentTimeMillis();
	    public static String PR_ADVANCE_SEARCH_BY_NAME="PR*";
	    public static String PR_ADV_SEARCH_VALUE="Problem Report";
	    
		
		
		
		
}
