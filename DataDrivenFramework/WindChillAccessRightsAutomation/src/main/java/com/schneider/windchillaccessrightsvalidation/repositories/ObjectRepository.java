package com.schneider.windchillaccessrightsvalidation.repositories;

/**
 * @author MADHUBABU 20-Oct-2016
 */

public class ObjectRepository {

	// **************************** Common Object Locators for All scenarios
	// ************************************************************************************//

	public static final String PRODUCTS_LIST = "//div[@id='netmarkets.product.list']//table[@class='x-grid3-row-table']/tbody/tr/td[@class='x-grid3-col x-grid3-cell x-grid3-td-name ']/div/a";

	public static final String LIBRARIES_LIST = "//div[@id='netmarkets.library.list']//table[@class='x-grid3-row-table']/tbody/tr/td[@class='x-grid3-col x-grid3-cell x-grid3-td-name ']/div/a";

	public static final String WORKSPACES_LIST = "//div[@id='ExtWorkspacesTable']//table/tbody/tr/td[3]/div/a";
	
	public static final String PRODUCT_LIST_ICON = "//span[@class='x-tab-strip-text productNavigation-icon']";

	public static final String LIBRARY_LIST_ICON = "//span[@class='x-tab-strip-text libraryNavigation-icon']";
	
	public static final String LIBRARIES_LIST_XPATH = "//div[@id='libraryNavigation']//div[@class='x-tree-root-node']//a[@class='x-tree-node-anchor']/span";
	
	public static final String PRODUCTS_LIST_XPATH = "//div[@id='productNavigation']//div[@class='x-tree-root-node']//a[@class='x-tree-node-anchor']/span";


	public static final String GLOBAL_USER_ID = "globalUser";
	public static final String GLOBAL_USER_XPATH = "//span[@id='globalUser']";

	public static final String BROWSE_BUTTON_ID = "object_main_navigation_nav";

	public static final String SEARCHBOX_ID = "folderbrowser_PDM.searchInListTextBox";

	public static final String SEARCH_BOX_PATH = "//input[@id='folderbrowser_PDM.searchInListTextBox']";

	public static final String SEARCH_BUTTON_PATH = "//input[@id='folderbrowser_PDM.searchInListTextBox']/following-sibling::span/img[@class='x-form-trigger x-form-search-trigger']";

	public static final String CHECK_BOX_PATH = "//table[@class='x-grid3-row-table']//div[@class='x-grid3-row-checker']";

	public static final String ACTION_MENU_BUTTON = "//table[@id='object_folderbrowser_toolbar_actions__folderbrowser_PDM__folderbrowser_PDM']//button[contains(text(),'Actions')]";

	public static final String ACTION_MENU_OPTIONS = "//span[@class='x-menu-item-text']";

	public static final String REVISE_OPTION = "//a/span[@class='x-menu-item-text'][contains(text(),'Revise')]";

	public static final String FAILURE_ICON = "//table[@class='x-grid3-row-table']/tbody/tr/td[@class='x-grid3-col x-grid3-cell x-grid3-td-message_status ']/div/img";

	public static final String TOOLTIP_ATTRIBUTE = "ext:qtip";

	public static final String OK_BUTTON = "//table[@id='okbutton']";

	public static final String DOMAIN_LIST = "//div[@id='folderbrowser_tree']//table[@class='x-grid3-row-table']//div[@class='x-grid3-cell-inner x-grid3-col-folderName']/a";

	public static final String VALIDATION_MESSAGE_ID = "folderbrowser_PDM.searchStatusLabel";

	public static final String SEARCH_FOR_FOLDERNAME_XPATH = "//table[@class='x-grid3-row-table']/tbody//tr/td//div[@class='x-grid3-cell-inner x-grid3-col-folderName']/a[contains(text(),'Restricted Documents Area')]";

	public static final String SEARCH_RESULTS_XPATH = "//div[@class='x-grid3-cell-inner x-grid3-col-name']/a";
	
	public static final String SEARCH_RESULT = "//div[@class='x-grid3-viewport']/div[@class='x-grid3-scroller']//div[@class='x-grid3-cell-inner x-grid3-col-name']/a";
	
	public static final String VERSION_NUMBER_FILENAME = "infoPageIdentityObjectIdentifier";
	public static final String SEARCH_CLEAR_PATH="//input[@id='folderbrowser_PDM.searchInListTextBox']/following-sibling::span/img[@class='x-form-trigger x-form-clear-trigger']";
	
	// **************************** Create document specific object locators
	// ************************************************************************************//

	public static final String CREATE_NEW_DOCUMENT_BUTTON_XPATH  = "//button[contains(text(),'Actions')]/ancestor::tr[@class='x-toolbar-left-row']/td//button[@style='background-image: url(\"netmarkets/images/newdoc.gif\");']";

	public static final String ELEMENT_DROPDOWN_ID = "createType";

	public static final String SELECT_CONTENT_SOURCE_ID = "primary0contentSourceList";

	public static final String DOCUMENTTYPE_DROPDOWN_ID = "seiDocumentType";

	public static final String SELECT_CONTENT_SOURCE = "//select[@id='primary0contentSourceList']";

	public static final String FILE_UPLOAD_BUTTON = "//div[@id='fiWrapper']/span[@class='fileinput-wrapper']";

	public static final String FILE_NAME_FILED_XPATH = "//table[@class='attributePanel-group-panel']/tbody/tr[2]//td[@class='attributePanel-value']/input[@type='text']";

	
	
	// **************************** CAD Document Creation ***********************************************************************
	
	public static final String CADDOCUMENT_NAME_FIELD_XPATH = "//input[@id='newCadDocNameTextFieldID']";
	
	public static final String CADDOCUMENT_TEMPLATE_DROPDOWN = "//select[@id='DocTemplate']";
	
	public static final String CAD_IFRAME = "the_tpIFrame";
	
	public static final String CAD_CREATE_ICON= "//div[@id='WsJSTable.toolBar']//table[@class='x-toolbar-ct']//td[@class='x-toolbar-left']//td[10]//button";
	
	public static final String CAD_CHECKIN_ICON ="//div[@id='WsJSTable.toolBar']//table[@class='x-toolbar-ct']//td[@class='x-toolbar-left']//td[2]//button";
	
	public static final String SUBFOLDER_XPATH  = "//div[@class='x-tree-root-node']/li/ul/li/div/a/span";
	
	// **************************** Element locations of 'Modify Content'
	// ************************************************************************************//

	public static final String DOCUMENT_PATH_FOR_CLICK = "//div[@class='x-grid3-cell-inner x-grid3-col-name']/a";

	public static final String DOCUMENT_VERSION_PATH = "//div[@class='x-grid3-cell-inner x-grid3-col-version']";

	public static final String DOCUMENT_ACTION_BUTTON_PATH = "//button[contains(text(),'Actions')]";

	public static final String ACTION_CHECKOUT_OPTION_PATH = "//span[contains(text(), 'Check In')]/ancestor::li/following-sibling::li//img[@src='netmarkets/images/checkout.gif']";

	public static final String ACTION_CHECKIN_OPTION_PATH = "//span[contains(text(), 'Check In')]";

	public static final String SELECT_DONOT_UPLOAD_FILE_PATH = "//input[@id='keep_existing_primary_file']";

	//public static final String SELECT_OK_BUTTON_PATH = "//table[@id='PJL_wizard_ok']/tbody/tr/td[@class='x-btn-mc']";

	public static final String SELECT_HISTORY_OPTION_PATH = "//span[contains(text(), 'History')]";

	public static final String IN_HISTORY_PAGE_DOCUMENT_VERSION_PATH = "//div[@class='x-grid3-row x-grid3-row-first ptc-grid-row-focus']/table/tbody/tr[1]/td[@class='x-grid3-col x-grid3-cell x-grid3-td-version']/div";

	public static final String READ_DOCUMENT_NAME_PATH = "//div[@class='x-grid3-viewport']/div[@class='x-grid3-scroller']//div[@class='x-grid3-cell-inner x-grid3-col-name']/a";

	public static final String ACTION_BUTTON_PATH = "//table[@class='x-btn actions-menu x-btn-noicon']//button[contains(text(),'Actions')]";

	public static final String CHECKOUT_OPTION_ON_ACTION_PATH = "//span[contains(text(), 'Check Out')]";
	
	public static final String MODIFYCONTENT_FILEUPLOAD_BUTTON= "//div[@id='fiWrapper']/span";

	// **************************** Element location of 'Read' Elements for
	// Authorized and UnAuthorized methods
	// *************************************************************//

	public static final String RECENT_PRODUCT_LIST = "//span[@class='x-tab-strip-text productNavigation-icon']";

	public static final String VIEWALL = "View All";

	public static final String GETTABLE = "//div[@id='netmarkets.product.list']//table[@class='x-grid3-row-table']/tbody/tr[1]/td[2]/div/a";

	public static final String lINK_NAME = "LinkName";

	public static final String READ_SEARCH_TEXTBOX = "//input[@id='folderbrowser_PDM.searchInListTextBox']";

	public static final String READ_SEARCH_BUTTON = "//input[@id='folderbrowser_PDM.searchInListTextBox']/following-sibling::span/img[@class='x-form-trigger x-form-search-trigger']";

	public static final String VALIDATION_MESSAGE = ".//*[@id='folderbrowser_PDM.searchStatusLabel']";

	public static final String ACTION_RENAME_OPTION = "//a//span[@class='x-menu-item-text'][contains(text(),'Rename')]";

	public static final String RENAME_TEXTBOX = "//div[@class='x-grid3-cell-inner x-grid3-col-NEWNAME_JSID']//input[@value='']";

	public static final String UPDATED_PART_NAME = "//table[@class='attributePanel-group-panel']/tbody//td[@attrid='name']";
													

	// **************************** Modify Identity specific object locators
	// **********************************************************************************************//

	public static final String RENAME_OPTION_ON_ACTION_PATH = "//span[contains(text(), 'Rename')]";

	public static final String DOCUMENT_ORIGINAL_NAME_PATH = "//div[@class='x-grid3-cell-inner x-grid3-col-name']/a";

	public static final String DOCUMENT_RENAME_INPUT_TEXT_PATH = "//div[@id='edit.renameAttributes']//table[@class='x-grid3-row-table']/tbody/tr/td[3]/div/input[1]";
	
	public static final String _RENAME_INPUT_TEXT_PATH = "//div[@class='x-grid3-body']//div[3]//table[@class='x-grid3-row-table']//tbody/tr//td/div/input[@type='text']";

	public static final String DOCUMENT_UPDATED_NAME_PATH = "//div[@id='dataStoreGeneral']//table[@class='attributePanel-group-panel']/tbody/tr[1]/td[@class='attributePanel-value']";
	
	public static final String DOCUMENT_MODIFY_SEARCH_FIELD = "//div[@id='edit.renameAttributes']//input[@id='edit.renameAttributes.searchInListTextBox']";
	
	public static final String DOCUMENT_MODIFY_SEARCH_BUTTON = "//div[@id='edit.renameAttributes']//input[@id='edit.renameAttributes.searchInListTextBox']/following-sibling::span[@class='x-form-twin-triggers']/img[@class='x-form-trigger x-form-search-trigger']";
	
	// Set the document number
//	public static final String SET_THE_DOCUMENT_NUMBER = "D0000101024";

	public static final String DROP_DOWN_OPTION = "//a//span[@class='x-menu-item-text'][contains(text(),'";

	public static final String DROP_DOWN_VALUE = "Rename')]";

	public static final String ACTION_OPTION = DROP_DOWN_OPTION + DROP_DOWN_VALUE;

	public static final String MODIFIED_CONTENT_NUMBER = "//div[@id='infoPageinfoPageIdentityDisplayPanel']//div/span[@id='infoPageIdentityDisplayText']//span[@id='infoPageIdentityObjectIdentifier']";

	// Change the document number

	public static final String UPDATE_THE_DOCUMENT_NUMBER = "D0000202033";
	
	//public static final String TEXT_BOX_XPATH = "//div[@class='x-grid3-cell-inner x-grid3-col-attributesTableValue']/input[@id='number']";

	public static final String DOCUMENT_NUMBER_PATH = "//input[@id='number']";

	public static final String DOCUMENT_NUMBER_NOT_EDITABLE_PATH = "//div[4]//table[@class='x-grid3-row-table']/tbody/tr/td[3]//div[@class='x-grid3-cell-inner x-grid3-col-attributesTableValue']";

	// **************************** Change Content specific object locators
	// *****************************************************************************************//

	public static final String CHECK_SEARCH_RESULT = ".//*[@id='FolderPageRightPane']//table[@class='x-grid3-row-table']//td[1]";

	public static final String ACTION_DROPDOWN = "//table[@class='x-btn actions-menu x-btn-noicon']//button[contains(text(),'Actions')]";

	public static final String MOVE_ACTION = "//a//span[@class='x-menu-item-text'][contains(text(),'Move')]";

	public static final String CHECK_MOVE_OBJECT = ".//table[@class='x-grid3-row-table']//td";

	public static final String OBJECT_PART_NUMBER = "//div[@class='x-grid3-cell-inner x-grid3-col-objNumber']";

//	public static final String TARGET_LOCATION = "//select[@id='location_contextId']";
	
	public static final String TARGET_LOCATION = "//div[@id='newContent']//select[@id='location_contextId'][@name='contextId']";
	
	public static final String TARGET_FOLDER = ".//*[@id='Verif_A_Demo']";

	public static final String TARGET_CHILD_FOLDER = ".//*[@id='Baselines']";

	public static final String OPEN_BUTTON_ID = "openbutton";

	public static final String OK_BUTTON_PARENT_WINDOW = "//table[@id='btnOkId']";

	public static final String CLEAR_SEARCH_TEXT = "//input[@id='folderbrowser_PDM.searchInListTextBox']/following-sibling::span/img[@class='x-form-trigger x-form-clear-trigger']";

	public static final String SEARCH_TEXT_FIELD = ".//*[@id='folderbrowser_PDM.searchInListTextBox']";

	public static final String SEARCHED_FOLDER = "//table[@class='x-grid3-row-table']/tbody//tr/td//div[@class='x-grid3-cell-inner x-grid3-col-folderName']/a[contains(text(),'Baselines')]";

	public static final String GETTING_CELLDATA = "//div[@class='x-grid3-cell-inner x-grid3-col-name']";

	public static final String CONTAINER_NAME = ".//*[@id='dataStoreSystem']/table/tbody/tr[14]/td[3]";

	public static final String FINAL_LOCATION = ".//*[@id='dataStoreSystem']/table/tbody/tr[14]/td[6]";

	public static final String TARGET_FOLDER_NO_PER = ".//*[@id='Energy_A_Demo']";

	public static final String ERROR_MESSAGE_CONTENT = "//div[contains(text(),'Done with Conflicts')]";

	public static final String INFORMATION_ICON = "//div[@class='x-grid3-cell-inner x-grid3-col-objActions']";

	public static final String CAPTURE_DESCRIPTION_TEXT = "//table[@class='x-grid3-row-table']//div[@class='x-grid3-cell-inner x-grid3-col-description']";

	public static final String SET_NEW_LOCATION_ICON = "//table[@id='MoveEditTableMOVEEDITSETLOCATION_label_shortcutbar']/tbody/tr[2]/td[@class='x-btn-mc']/em/button";
	
	public static final String DOMAINS_LIST ="//select[@id='location_LocationSelect']/option";
	
	 
	public static final String TARGET_FOLDER_XPATH = "//select[@id='location_LocationSelect']";

	// ***************************** Change Domain specific object locators
	// *******************************************************************************************
	// //
	public static final String TARGET_DOMAIN ="//div[@id='dataStoreSystem']/table[@class='attributePanel-group-panel']/tbody//td[@class='attributePanel-value']/a[2]";
	
	//public static final String TARGET_FOLDER_ID = "Restricted Documents Area";

	//public static final String TARGET_FOLDER_CONTAINER = "//table[@class='x-grid3-row-table']/tbody//tr/td//div[@class='x-grid3-cell-inner x-grid3-col-folderName']/a[contains(text(),'Restricted Documents Area')]";

	public static final String ERROR_TEXT_CONTENT = "//div[@class='x-grid3-cell-inner x-grid3-col-event_status']";

	// ***************************** Delete specific object locators
	// **************************************************************************************************
	// //

	public static final String DELETE_ACTION = "//a//span[@class='x-menu-item-text'][contains(text(),'Delete')]";

	public static final String DELETE_CLOSE_BUTTON = "//button[@id='ext-gen18']";

	// ***************************** Set State specific object locators *******************************************

	public static final String OBJECT_CHECK = ".//div[@class ='x-grid3-cell-inner x-grid3-col-checker']";

	public static final String OBJECT_NUMBER = "//div[@class='x-grid3-cell-inner x-grid3-col-objNumber']";

	public static final String IBCONTENT_FRAME1 = ".//*[@id='okbutton']/tbody/tr[2]/td[2]";

	public static final String CURRENT_STATE = ".//div[@class='x-grid3-cell-inner x-grid3-col-state']";

	public static final String CHECK_CURRENT_STATE = ".//div[@ class='x-grid3-cell-inner x-grid3-col-state']";

	public static final String SET_STATE_ACTION = "//a//span[@class='x-menu-item-text'][contains(text(),'Set State')]";

	public static final String TOOLTIP_MESSAGE = "//table[@class='x-grid3-row-table']//td[4]/div/img";

	public static final String IBCONTENT_FRAME = "//table[@id='okbutton']/tbody/tr//td[@class='x-btn-mc']";

	// ***************************** Create Folder specific object locators  ***************************************

	public static final String CREATE_FOLDER_ICON = "//button[contains(text(),'Actions')]/ancestor::tr[@class='x-toolbar-left-row']/td//button[@style='background-image: url(\"netmarkets/images/newfoldertl.gif\");']";

	public static final String NAME_ATTRIBUTE = "//table[@class='attributePanel-group-panel']//input[@type='text']";

	public static final String FINISH_BUTTON_ID = "PJL_wizard_ok";

	public static final String MODIFY_OK_BUTTON = "//table[@id='PJL_wizard_ok']";
	
	public static final String PART_MODIFY_OK_BUTTON = "//table[@id='okbutton']";
	

	// ***************************** Locator details of 'Download' action for [Authorized and un-authorized users] ********

	public static final String CONTENT_MENU = "//span[@class='x-tab-strip-inner']/span[contains(text(),'Content')]";

	public static final String DOWNLOAD_FILE_LOCATION_XPATH = "//div[@class='x-grid3-cell-inner x-grid3-col-attachmentsName']/a";

	// ***************************** Engineering Part specific object locators
	// ***************************************************************************************
	// //

	public static final String PART_OBJECT_TYPE = "//select[@id='!~objectHandle~partHandle~!createType']"; // In
																											// 'new
																											// cart'
																											// child
																											// window

	public static final String ENG_PART_END_ITEM = ".//*[@id='endItem']";

	//public static final String NAME="//table[@class='attributePanel-group-panel']//tr/td[@attrid='name']/input[@type='text']";

	public static final String BOM_LEVEL = "//table[@class='attributePanel-group-panel']//tr//td[@class='attributePanel-value']/select[@id='seiBOMLevel']";

	public static final String DEFAULT_UNIT = "//table[@class='attributePanel-group-panel']//tr//td[@class='attributePanel-value']/select[@id='defaultUnit']";

	public static final String PART_TYPE = "//table[@class='attributePanel-group-panel']//tr//td[@class='attributePanel-value']/select[@id='partType']";

	public static final String SOURCE = "//table[@class='attributePanel-group-panel']//tr//td[@class='attributePanel-value']/select[@id='source']";

	public static final String DEFAULT_TRACE_CODE = "//table[@class='attributePanel-group-panel']//tr//td[@class='attributePanel-value']/select[@id='defaultTraceCode']";

	public static final String GATHERING_PART = "//table[@class='attributePanel-group-panel']//tr//td[@class='attributePanel-value']/span[@id='hidePartInStructure']//input[@value='True']";

	public static final String LOCATION = "//table[@class='attributePanel-group-panel']//tr//td[@class='attributePanel-value']//input[@onchange='PTC.selectFolderTextboxValueIsRequired (true);']";

	public static final String CREATE_PART_ICON = "//button[contains(text(),'Actions')]/ancestor::tr[@class='x-toolbar-left-row']/td//button[@style='background-image: url(\"netmarkets/images/newpart.gif\");']";

	public static final String ACTION_BUTTON = "//table[@id='infoPagedetailsPageActionsMenu']//button[contains(text(),'Actions')]";
	// engineering part folder
	public static final String ENG_PART_FOLDER = "//table[@class='x-grid3-row-table']/tbody//tr/td//div[@class='x-grid3-cell-inner x-grid3-col-folderName']/a[contains(text(),'Engineered Part')]";

	// ***************************************** Revise specific Object Locators
	// ****************************************************************************************
	// //

	public static final String VERSION = "//div[@class='x-grid3-cell-inner x-grid3-col-version']";
	/*
	 * SetStateNoPermission
	 */
	public static final String CheckCurrentState = ".//div[@ class='x-grid3-cell-inner x-grid3-col-state']";

	public static final String SetStateAction = "//a//span[@class='x-menu-item-text'][contains(text(),'Set State')]";

	public static final String TOOL_TIP_MESSAGE = "//table[@class='x-grid3-row-table']//td[4]/div/img";

	public static final String OK_BUTTON_SETSTATE = "//table[@id='okbutton']/tbody/tr//td[@class='x-btn-mc']";

	
	/*
	 * SetState
	 */
	public static final String CURRENT_STATE_VALUE = "//div[@class='x-grid3-cell-inner x-grid3-col-current_lifecycle_state']";
	
	public static final String ObjectCheck = ".//div[@class ='x-grid3-cell-inner x-grid3-col-checker']";

	public static final String ObjectNumber = "//div[@class='x-grid3-cell-inner x-grid3-col-objNumber']";

	public static final String OK_BUTTON_SET_STATE_Frame1 = ".//*[@id='okbutton']/tbody/tr[2]/td[2]";

	public static final String CurrentState = ".//div[@class='x-grid3-cell-inner x-grid3-col-state']";

	public static final String STATE_DROP_DOWN = "//div[@class='x-grid3-cell-inner x-grid3-col-target_state']/select";

	public static final String STATE_VALUE = "//div[@class='x-grid3-viewport']/div[@class='x-grid3-scroller']//div[@class='x-grid3-cell-inner x-grid3-col-state']";

	/*
	 * SetState action [Authorized and un-authorized users]
	 */
	public static final String ACTION_BUTTON_SETSTATE = "//button[contains(text(),'Actions')]";

	public static final String CHECK_BOX = "//div[@class='x-grid3-viewport']/div[@class='x-grid3-scroller']//div[@class='x-grid3-cell-inner x-grid3-col-checker']/div[@class='x-grid3-row-checker']";

	public static final String DROP_DOWN_VALUE_SETSTATE = "Set State')]";

	public static final String ACTION_OPTION_SETSTATE = DROP_DOWN_OPTION + DROP_DOWN_VALUE_SETSTATE;

	public static final String CHECK_OUT = "Check Out')]";

	public static final String CHECK_IN = "Check In')]";

	public static final String ACTION_OPTION_CHECK_OUT = DROP_DOWN_OPTION + CHECK_OUT;

	public static final String ACTION_OPTION_CHECK_IN = DROP_DOWN_OPTION + CHECK_IN;

	public static final String CHECK_OUT_OPTION_ON_ACTION_PATH = "//span[contains(text(), 'Check Out')]";

	/*
	 * ChangeContextNoPermission
	 */
	public static final String TARGET_FOLDER_NOPER = "//*[@id='Energy_A_Demo']";

	public static final String CaptureDescriptionText = "//table[@class='x-grid3-row-table']//div[@class='x-grid3-cell-inner x-grid3-col-description']";

	public static final String OK_BUTTON_CHANGE_CONTEXT = "//table[@id='btnOkId']";
	
	public static final String CANCEL_BUTTON_CHANGE_CONTEXT = "//table[@id='btnCancelId']";
	
	//public static final String CLOSE_BUTTON_CHANGE_CONTEXT = "//table[@class='x-btn x-btn-noicon']//td[@class='x-btn-mc']";
	
	// ************************Change domain****************************/
	public static final String CHANGE_ACTION_BUTTON = "//table[@id='infoPagedetailsPageActionsMenu']//button[contains(text(),'Actions')]";

	public static String ChangeDomainDocumentName;

	public static String RESTRICT_PART_FOLDER = "//table[@class='x-grid3-row-table']/tbody//tr/td//div[@class='x-grid3-cell-inner x-grid3-col-folderName']/a[contains(text(),'Restricted Documents Area')]";
	
	public static String RESTRICT_PART_FOLDER_NEW = "//div[@id='folderbrowser_tree']//div[@class='x-grid3-cell-inner x-grid3-col-folderName']/a[contains(text(),'Restricted Documents Area')]";
	// public static String CHANGE_LOCATION =
	// "//a[@id='MoveEditTable_nonepmdoc_" + ChangeDomainDocumentName +
	// "_newlocation_loc_img']/img";

	public static String CHANGE_LOCATION_ICON = "//button[@id='ext-gen124']";

	public static String CHANGE_DOMAIN_TEXT = "//a[contains(text(), 'Verif_Test_Automation')]";

	public static final String CHANGE_DOMAIN_TARGET = "location_LocationSelect";

	public static final String CC_ERROR_TEXT_CONTENT = "//div[contains(text(),'Failed with overridable conflicts')]";

	// 'CONTAINER_NAME_VALUE' will use in 'change context' action
	public static final String CONTAINER_NAME_VALUE = "//table[@class='attributePanel-group-panel']/tbody//tr//td[@attrid='containerReference']/a";

	public static final String ERROR_MESSAGE = "//div[@class='x-grid3-cell-inner x-grid3-col-event_status']";

	public static final String OPENBUTTON = ".//table[@id='openbutton']";
	
	public static final String CONTAINER_LANDING_PAGE = "//div[@id='contentPanel_crumbs']//span[2]/a";
	
	public static final String TESTDATA_FOLDER_LANDING_PAGE="//div[@id='contentPanel_crumbs']//span[@class='breadcrumb']/a[contains(text(),'AutomationTestData')]";
	
	public static final String TESTDATA_CONTAINER_LANDING_PAGE="//div[@id='contentPanel_crumbs']//span[3]/a";
	//********************** Modify **************************************************************************************//
	public static final String DETAILS_MENU = "//span[@class='x-tab-strip-inner']/span[contains(text(),'Details')]";
	
	public static final String CHANGENOTICE_LINK_IN_CHNAGETASK = "//span[@class='attributePanel-fieldset-title'][contains(text(),'Attributes')]/ancestor::legend/following-sibling::div//td[@class='attributePanel-value']/a";
	
	public static final String History_MENU = "//span[@class='x-tab-strip-inner']/span[contains(text(),'History')]";
	
	public static final String TEXT_BOX_XPATH = "//div[@class='x-grid3-cell-inner x-grid3-col-attributesTableValue']/input[@id='number']";

/*********************************Advance Search***********************************/
	
	//	  public static String ADV_SEARCH_BUTTON="//div[@id='globalSearch']//span[@class='x-form-twin-triggers']/img[1]";
		  
	 public static String ADV_SEARCH_BUTTON=  "//div[@id='globalSearch']//span/img[@class='x-form-trigger global-search-trigger']";
		  
	 public static String ADV_SEARCH_BUTTON_XPATH = "//a[@id='object_search_navigation_nav']";
	 
	 public static String ADV_SEARCH_EDIT_CRITERIA = "//div[@id='editSearchCriteriaLink']/a";
	 
	 public static String ADV_SEARCH_NEW_CRITERIA = "//div[@id='startNewSearchLink']/a";
	 
	 public static String ADV_SEARCH_ALL_CONTEXTS = "//input[@id='all_contexts']";
		  
		  //public static String ADV_SEARCH_TAB_XPATH ="//span[@class='x-tab-strip-text'][contains(text(),'Advanced Search')]";
		  
		  public static String BACK_TO_SEARCH_CRITERIA_LINK = "//div[@id='searchResultTableLB']//div[@id='editSearchCriteriaLink']/a";
		  
		  public static String ADV_SEARCH_TAB_XPATH ="//li[@id='object_search_navigation__advancedNavigation']/a[@class='x-tab-right']";
		  public static String ADV_SEARCH_ADD="//a[@href='javascript:launchTypePicker();']";
		  public static String ADV_SEARCH_WINDOW="//input[@id='typePicker.tree.fitTextField']";
		  public static String ADV_SEARCH_WINDOW_CLICK=".//*[@id='ext-gen67']";
		  
		  public static String ADV_SEARCH_CHECKBOX="//div[@class='x-grid3-hd-checker']";
		  public static String ADV_SEARCH_OK=".//*[@id='PJL_wizard_ok']/tbody/tr[2]/td[2]";
		  public static String ADV_SEARCH_MYFAVORITE=".//*[@id='myFavTypes']";
		  public static String ADV_SEARCH_SEARCHTEXTBOX_ID="keywordkeywordField_SearchTextBox";
		  public static String ADV_SEARCH_ALL=".//*[@id='wt.fc.Persistable']";
		  public static String ADV_SEARCH_SEARCHTEXT_VALUE="//div[@class='removableCheckBoxLabel']";
		 
		  public static String ADV_SEARCH_CONTENT_STATE="//div[@class='ng-isolate-scope']/div[@id='attSelection']//input[@seleniumvalue='State']";
	      //public static String ADV_SEARCH_SEARCHDROPDOWN="//div[@class='ng-isolate-scope']/div[@id='attSelection']//div[@id='comboIdDivqBAttributeTD-1']//img[@class='x-form-trigger x-form-arrow-trigger']";
		  public static String ADV_SEARCH_SEARCHDROPDOWN="//div[@id='comboIdDivqBAttributeTD-1']//img[@class='x-form-trigger x-form-arrow-trigger']";
		  public static String ADV_SEARCH_SEARCHDROPCLICK="//div[@class='x-combo-list-inner']/div[@class='x-combo-list-item']";
		  public static String ADV_SEARCH_SEARCHDROP_VALUE="//span[@class='ng-isolate-scope']/div[@class='ng-scope']/select[@name='enumValue']";

		  public static String ADV_SEARCH_ADD_ICON="//td/fieldset[@class='qbFieldset']//td/a/img[@src='netmarkets/images/search_add_condition.gif']";
		
		  public static String ADV_SEARCH_SEARCHDROPDOWN_3="//div[@id='comboIdDivqBAttributeTD-3']//img[@class='x-form-trigger x-form-arrow-trigger']";
		  public static String ADV_SEARCH_SEARCHDROPCLICK_3="//div[@class='x-combo-list-inner']/div[@class='x-combo-list-item']";
		  //public static String ADV_SEARCH_SEARCHDROP_VALUE_1="//span[@class='ng-isolate-scope']/div[@class='ng-scope']/select[@name='enumValue']";

		  public static String ADV_SEARCH_FIND_BUTTON="iterationInfo__DOT__creator2_SearchPickerID_FIND_BTN";
		
		  //In window
		  public static String ADV_SEARCH_WIN_NAME="name1_SearchTextBox";
		
		  public static String ADV_SEARCH_WIN_SEARCH="//tr/td[2]/font[@class='wizardbuttonfont']/button[contains(text(),'Search')]";
		 
		 
		  public static String ADV_SEARCH_SEARCH_TYPE="//div[@class='removableCheckBoxLabel']";
		  public static String ADV_SEARCH="//tr/td/em/button[contains(text(),'Search')]";

		  public static String ADV_SEARCH_CONTAINER="//input[@id='wt.doc.WTDocument.defaultSearchView.searchInListTextBox']";
		  public static String ADV_SEARCH_CONTAINER_BUTTON="//tr[@class='x-toolbar-right-row']//td/div/span[@class='x-form-twin-triggers']";
		  public static String ADV_SEARCH_LIST="//div[@class='x-grid3-cell-inner x-grid3-col-number']/a";
		  
		  public static String ADV_SEARCH_LIST_PR = "//div[@id='wt.maturity.PromotionNotice.defaultSearchView']//div[@class='x-grid3-cell-inner x-grid3-col-number']/a";
		  
		  public static String ADV_SEARCH_FIRST_LIST="//div[@class='x-grid3-cell-inner x-grid3-col-number']/a[contains(text(),'";
		  
		  public static String ADV_SEARCH_FIRST_LIST_PR="//div[@id='wt.maturity.PromotionNotice.defaultSearchView']//div[@class='x-grid3-cell-inner x-grid3-col-number']/a[contains(text(),'";
          //Context selection
		  public static String ADV_SEARCH_CONTEXT_ADD=".//*[@id='contextOptionsMenuAnchor']";
		  public static String ADV_SEARCH_CONTEXT_DROPDOWN="searchType_display";
		  public static String ADV_SEARCH_CONTEXT_NAME=".//*[@id='containerInfo__DOT__name0_SearchTextBox']";
		  public static String ADV_SEARCH_CONTEXT_SEARCH="//tr[@class='basefont']//td/font[@class='wizardbuttonfont']/button[contains(text(),'Search')]";
		  public static String ADV_SEARCH_CONTEXT_CHECKBOX="//div[@class='x-grid3-hd-inner x-grid3-hd-checker']/div[@class='x-grid3-hd-checker']";
		  public static String ADV_SEARCH_CONTEXT_OK=".//*[@id='PJL_wizard_ok']/tbody/tr[2]/td[2]";

		  public static final String ADV_SEARCH_SEARCHDROPDOWN_NAME="//div[@id='comboIdDivqBAttributeTD-4']//img[@class='x-form-trigger x-form-arrow-trigger']";
		  public static final String ADV_SEARCH_SEARCHDROPCLICK_NAME="//div[@class='x-combo-list-inner']/div[@class='x-combo-list-item']";
		  public static final String ADV_SEARCH_SEARCHDROP_VALUE_NAME="//span[@class='ng-isolate-scope']/div[@class='ng-scope']/select[@name='enumValue']";
		  public static final String ADV_SEARCH_BY_NAME="//tbody[5]/tr/td/table/tbody/tr/td[4]/span/span[1]/div/input[@type='text']";
	      public static final String ADV_SEARCH_WINDHILL_ERROR="//div[@id='reportTemplateMVCDiv'][@class='partialContents']/title[contains(text(),'Windchill Error')]";  
		 
	      
	      public static final String WINDCHILL_ERROR =  "//h1[Contains(text(),'Windchill Error')]";
	      
	      /*******************************Manufacturer part*******************************************************************************
			 * 
			 * 
			 * */
			
			/************************ Manufacture *******************************************************************************************/
			
		    public static final String MANUF_PART_OBJECT_TYPE = ".//*[@id='!~objectHandle~partHandle~!createType']"; // In 'new cart' child window
			
		    public static final String MANUF_NAME_LIST="//a[contains(text(),'Manufacturer Parts')]";
		    
		    public static final String MANUFACTURE_SEARCH = "//table[@class='pp']/tbody/tr//td[@class='ppdata']//button/img[@title='Find...']";
			//public static String MANUFACTURE_SEARCHWINDOW = "//div/table/tbody/tr/td[2]//button[@class='pickerInputComponentFindButton']";
			public static final String MANUFACTURE_SEARCHTEXT ="//input[@id='name0_SearchTextBox']";
			public static final String MANUFACTURE_SEARCHSUBMIT="//div/table/tbody/tr//td/font//button[@name='pickerSearch']";
			public static final String MANUFACTURE_SEARCHRADIO="//input[@name='radio_manufacturer_view']";
			public static final String MANUFACTURE_SEARCHOK=".//*[@id='PJL_wizard_ok']/tbody/tr[2]/td[2]";
			
			 public static final String MANUF_PART_END_ITEM = ".//*[@id='endItem']";
			 
			 public static final String MANUF_NUMBER = "//table[@class='attributePanel-group-panel']//tr//td[@class='attributePanel-value']/input[@id='number']";

	       //  public static final String MANUF_NAME="//table[@class='attributePanel-group-panel']//tr/td[@attrid='name']/input[@type='text']";
			   		
	         public static final String MANUF_AVAILABILITY = "//table[@class='attributePanel-group-panel']//tr/td/select[@id='seiAvailability']";
	         public static final String CANCEL_BUTTON = "//table[@id='cancelbutton']"; 
	         public static final String NEW_NUMBER_TEXT_BOX = "//div[@class='x-grid3-cell-inner x-grid3-col-NEWNUMBER_JSID']/input[@type='text']";
	         public static final String CAD_NEW_FILENAME_TEXT_BOX = "//div[@class='x-grid3-cell-inner x-grid3-col-NEWFILENAME_JSID']/input[@type='text']";
	         
	       //  public static final String CAD_NEW_NUMBER_TEXT_BOX = "//div[@class='x-grid3-cell-inner x-grid3-col-NEWNUMBER_JSID']/input";
	         //
	        
	         /***********************************************
				 * Raw Material Part objects
				 ************************************************/
				
	         	public static final String RAW_PART_OBJECT_TYPE = ".//*[@id='!~objectHandle~partHandle~!createType']"; // In 'new cart' child window
				
				
			    public static final String PART_END_ITEM = ".//*[@id='endItem']";
			    
			    public static final String RAW_DEFAULT_UNIT = "//table[@class='attributePanel-group-panel']//tr//td[@class='attributePanel-value']/select[@id='defaultUnit']";
				
			   // public static final String RAW_NAME="//table[@class='attributePanel-group-panel']//tr/td[@attrid='name']/input[@type='text']";
			   		
				public static final String TECHNICAL_SEARCHBUTTON = "//table[@class='attributePanel-group-panel']//tr//td[@class='attributePanel-value']/button/img[@title='Find...']";
				
				//public static final String RAW_OKBUTTON=".//*[@id='PJL_wizard_ok']/tbody/tr[2]/td[2]";
			  
				// public static final String RAW_SEARCH_TEXT="//table[@class='x-grid3-row-table']//div[@class='x-grid3-row-radio']/input";
			    
			    public static final String RAW_SEARCH_TEXT ="//div[@id='ext-gen84']/table/tbody/tr/td[1]/div/div/input";
				public static final String SENSITIVITY = "//select[@id='seiPartSensitivity']"; 
			    public static final String ENVIRONEMENT_PROFILE = "//select[@id='seiEnvironmentalProfile']";
			    public static final String EIME_BASE_MATERIAL = "//select[@id='seiEIMEBaseMaterialName']";
			    public static final String OWNER_ORGANIZATION = "//select[@id='seiOwnerOrganization']";
				public static final String KEY_TO_PERFORMANCE="//table[@class='attributePanel-group-panel']//tr//td[@class='attributePanel-value']/select[@id='seiKeytoPerformance']";
				public static final String CORP_PREFERENCE="//table[@class='attributePanel-group-panel']//tr/td/select[@id='seiCorporatePreference']";
				public static final String CORPPREFERENCE_COMMENT="//table[@class='attributePanel-group-panel']//tr/td/textarea[@id='seiCorporatePreferenceComment']";
				public static final String ASSEEMBLY_MODE="//table[@class='attributePanel-group-panel']//tr/td/select[@id='partType']";
				public static final String TECHNICAL_CLASSIFICATION = "//td[contains(text(),'Technical Classification:')]/following-sibling::td/span/div/input[2]";
				public static final String TYPEOFADDITIVES_DROPDOWN = "//td[contains(text(),'Type of additive:')]/following-sibling::td/select"; 
				//public static final String LOCATION = "//table[@class='attributePanel-group-panel']//tr//td[@class='attributePanel-value']//input[@onchange='PTC.selectFolderTextboxValueIsRequired (true);']";
				
				public static final String NEXTBTN = "//table[@id='PJL_wizard_next']/tbody/tr[2]/td[2]";
				public static final String FINISH_BUTTON = "//table[@id='PJL_wizard_ok']/tbody/tr[2]/td[2]";
				public static final String CASINGSTYLE = "//td[contains(text(),'Casing style:')]//following-sibling::td[@class='attributePanel-value']/select";
				
			    // *********** Raw Material Part folder ***************************************
				
				public static final String RAW_PART_FOLDER = "//table[@class='x-grid3-row-table']/tbody//tr/td//div[@class='x-grid3-cell-inner x-grid3-col-folderName']/a[contains(text(),'Raw Material Part')]";
				//public static final String ELASTOMER_FAMILY = "//td[contains(text(),'Elastomer Family:')]/following-sibling::td[@class='attributePanel-value']/select";
				public static final String MAXPROCESSTEMP_INPUT = "//td[contains(text(),'Max processing temperature:')]/following-sibling::td/input[1]";
				public static final String TYPICALLOADING_INPUT = "//td[contains(text(),'Typical loading:')]/following-sibling::td/input[1]";
				public static final String COLOR = "//td[contains(text(),'Color:')]/following-sibling::td[@class='attributePanel-value']/select";
				public static final String COLOR2 = "//td[contains(text(),'Nearest Color:')]/parent::tr/preceding-sibling::tr//td[contains(text(),'Color:')]/following-sibling::td[@class='attributePanel-value']/select";
				//public static final String UL94_FLAMMABILITY = "//td[contains(text(),'UL94 Flammablilty at 1.6mm:')]/following-sibling::td[@class='attributePanel-value']/select";
				public static final String LEGACY_GENERIC_MATERIAL_IDENTIFICATION = "//td[contains(text(),'Legacy Generic Material Identification:')]/following-sibling::td[@class='attributePanel-value']/select";
				public static final String HALOGENFREE_RADIOBUTTON = "//td[contains(text(),'Halogen-free:')]/following-sibling::td/span/label[contains(text(),'Yes')]";
				
				/*
				 Modify Identity 
				 */

				public static final String OLD_NAME_ENGPART_DOC = "//div[@class='x-grid3-cell-inner x-grid3-col-objName']";
				public static final String NEW_NAME_PART_DOC = "//div[@class='x-grid3-cell-inner x-grid3-col-NEWNAME_JSID']/input[@type='text']";
				public static final String OLDNUMBER = "//*[@id='FolderPageRightPane']//table[@class='x-grid3-row-table']//td[7]";
				public static final String OLD_NUMBER = "//div[@class='x-grid3-cell-inner x-grid3-col-objNumber']";
				public static final String NEW_NUMBER = ".//*[@id='infoPageIdentityObjectIdentifier']";
				
				public static final String OKBUTTON_MI = ".//*[@id='okbutton']/tbody/tr[2]/td[2]";
				
				
				
				
				/*
				 * Engineering Part objects
				 */

				public static final String EngPartObjectType = ".//*[@id='!~objectHandle~partHandle~!createType']"; // In
				public static final String NEW_NAME_ENGPART_DOC = "//div[@class='x-grid3-cell-inner x-grid3-col-NEWNAME_JSID']/input[@type='text']";																								// 'new
																												// cart'
																												// child
																												// window
				public static final String PARTENDITEM = ".//*[@id='endItem']";
				public static final String Name = "//table[@class='attributePanel-group-panel']//tr/td[@class='attributePanel-value']/input[@name='tcomp$attributesTable$OR:wt.folder.SubFolder:11913265$___null!~objectHandle~partHandle~!_col_name___textbox']";
				public static final String BOM_Level = "//table[@class='attributePanel-group-panel']//tr//td[@class='attributePanel-value']/select[@id='seiBOMLevel']";
				public static final String DefaultUnit = "//table[@class='attributePanel-group-panel']//tr//td[@class='attributePanel-value']/select[@id='defaultUnit']";
				public static final String PartType = "//table[@class='attributePanel-group-panel']//tr//td[@class='attributePanel-value']/select[@id='partType']";
				public static final String Source = "//table[@class='attributePanel-group-panel']//tr//td[@class='attributePanel-value']/select[@id='source']";
				public static final String DefaultTraceCode = "//table[@class='attributePanel-group-panel']//tr//td[@class='attributePanel-value']/select[@id='defaultTraceCode']";
				public static final String GatheringPart = "//table[@class='attributePanel-group-panel']//tr//td[@class='attributePanel-value']/span[@id='hidePartInStructure']//input[@value='True']";
				public static final String LOCATION1 = "//table[@class='attributePanel-group-panel']//tr//td[@class='attributePanel-value']//input[@onchange='PTC.selectFolderTextboxValueIsRequired (true);']";
			//	public static final String FINISHBTN = "//table[@id='PJL_wizard_ok']/tbody/tr[2]/td[2]";
				public static final String CREATEWINDOW_CANCEL_BUTTON_XPATH = "//table[@id='PJL_wizard_cancel']";
				public static final String ActionsBtn = "//table[@id='infoPagedetailsPageActionsMenu']//button[contains(text(),'Actions')]";
				// engineering part folder
				public static final String EngPartFolder = "//table[@class='x-grid3-row-table']/tbody//tr/td//div[@class='x-grid3-cell-inner x-grid3-col-folderName']/a[contains(text(),'Engineered Part')]";
				public static final String OkBtn = "//table[@id='okbutton']"; 
				//public static final String UPDATED_PART_NAME = "//div[@id='dataStoreVisualization_and_Attributes']/table/tbody/tr/td[@attrid='name']";
				public static final String UPDATED_PART_NAME_Eng ="//table[@class='attributePanel-group-panel']/tbody//td[@attrid='name']";
				public static final String CANCLE_BUTTON_FAILED_WINDOW = "//div[@id='footerPanel']//td[@class='x-btn-mc']/em/button[contains(text(),'Close')]";
	         
				public static final String PART_OPTONS_XPATH = "//select[@id='!~objectHandle~partHandle~!createType']/option";
				/*
				 * Mounting context part
				 */

				//public static final String NAMEFIELD = "//table[@class='attributePanel-group-panel']//tr/td[@class='attributePanel-value']/input[@name='tcomp$attributesTable$OR:wt.pdmlink.PDMLinkProduct:11470004$___null!~objectHandle~partHandle~!_col_name___textbox']";
				public static final String NAMEFIELD="//table[@class='attributePanel-group-panel']//tr/td[@attrid='name']/input[@type='text']";
				//public static final String NUMBERFIELD = "//table[@class='attributePanel-group-panel']//tr/td[@class='attributePanel-value']/input[@name='tcomp$attributesTable$OR:wt.pdmlink.PDMLinkProduct:11470004$___null!~objectHandle~partHandle~!_col_number___textbox']";
				public static final String NUMBERFIELD ="//table[@class='attributePanel-group-panel']//tr/td[@attrid='number']/input[@type='text']";
				
				// **************************************** Treatment part ***********************************
				public static final String TP_SEARCHBUTTON = "//table[@class='attributePanel-group-panel']//tr//td[@class='attributePanel-value']/button/img[@title='Find...']";
				//public static final String TP_SEARCH_TEXT="//table[@class='x-grid3-row-table']//div[@class='x-grid3-row-radio']";
				public static final String TP_SEARCH_TEXT = "//div[@class='x-grid3-cell-inner x-grid3-col-displayIdentifierWithLabels'][contains(text(),'Raw Materials and Treatments')]/../../preceding-sibling::td//input";
			//	public static final String TP_OKBUTTON=".//*[@id='PJL_wizard_ok']/tbody/tr[2]/td[2]";
			//	public static final String TP_NEXT="//*[@id='PJL_wizard_next']/tbody/tr[2]/td[2]";
				public static final String TP_GATHERING_PART = "//table[@class='attributePanel-group-panel']//tr//td[@class='attributePanel-value']/span[@id='hidePartInStructure']//input[@value='False']";

				//**************************************** Manage Collection ******************************************
				
				//public static final String EDIT_MANAGECOLLECTION = "//div[@id='infoPagedetailsPageActionsMenu_menu']/ul[@class='x-menu-list']/li[1]//span[@class='x-menu-item-text']";
				public static final String RENAME_MANAGECOLLECTION = "//div[@id='infoPagedetailsPageActionsMenu_menu']//span[@class='x-menu-item-text'][contains(text(),'Rename')]";
				public static final String RENAME_MANAGE_COLLECTION = "//div[@id='infoPagedetailsPageActionsMenu_menu']//span[@class='x-menu-item-text']";
				public static final String NEW_OPTION = "//a//span[@class='x-menu-item-text'][contains(text(),'New')]";
				public static final String NEW_MANAGED_COLLECTION_OPTION = "//a//span[@class='x-menu-item-text'][contains(text(),'New Managed Collection')]";
			//	public static final String RENAME_MANAGE_COLLECTION_FIELD = "//div[@class='x-grid3-cell-inner x-grid3-col-attributesTableLabel']/label[contains(text(),'Name')]/../../following-sibling::td[@class='x-grid3-col x-grid3-cell x-grid3-td-attributesTableValue']/div/input[1]";
				
				public static final String RENAME_MANAGE_COLLECTION_FIELD= "//div[@class='frame_outer']//div[@class='x-grid3-cell-inner x-grid3-col-attributesTableValue']/input[starts-with(@id,'NameInput')][@class='required']";
				public static final String MENU_UNDER_NEW_OPTION = "//a//span[@class='x-menu-item-text'][contains(text(),'New Managed Collection')]";
				public static final String ACTION_BUTTON_LANDING_PAGE = "//table[@id='object_folderbrowser_toolbar_actions__folderbrowser_PDM__folderbrowser_PDM']//tbody[@class='x-btn-small x-btn-icon-small-left']//button[contains(text(),'Actions')]";
				public static final String OK_RENAME_MANAGE_COLLECTION = "//table[@id='PJL_wizard_ok']";
				public static final String MENU_UNDER_NEW_OPTION_XPATH = "//a//span[@class='x-menu-item-text']";
				//**************************************** Promotional Request ****************************************
				
				public static final String DROP_DOWN_VALUE_PROMOTIONAL_REQUEST = "New')]";
				public static final String ACTION_OPTION_PROMOTIONAL_REQUEST = DROP_DOWN_OPTION+DROP_DOWN_VALUE_PROMOTIONAL_REQUEST;
				public static final String NEW_ACTION_OPTION_PROMOTIONAL_REQUEST = DROP_DOWN_OPTION+DROP_DOWN_VALUE_PROMOTIONAL_REQUEST;
				//public static final String CHANGE_NOTICE_BUTTON = "//button[contains(text(),'Actions')]";
		 		//public static final String NEW_OPTION = "//a[@id='object_more parts toolbar actions new_infoPagedetailsPageActionsMenu_menu']";
		 		public static final String NEW_PROMOTION_MENU = "//span[@class='x-menu-item-text'][contains(text(),'New Promotion Request')]";
		 		//public static final String CHANGE_NOTICE_DROPDOWN = "//a[@id='object_more parts toolbar actions new_infoPagedetailsPageActionsMenu_menu']";
		 		public static final String CHANGE_NOTICE_DROPDOWN = "//a[@id='object_docs row actions new_infoPagedetailsPageActionsMenu_menu']";
		 		public static final String PROMOTION_REQUEST_NEXT_BUTTON = "//table[@id='PJL_wizard_next']";
		 		public static final String TARGET_PROMOTION_STATE = "//select[@id='maturityState']";
		 		public static final String PROMOTION_REQUEST_CHECK_BOX = "//table[@class='x-grid3-row-table ']//div[@class='x-grid3-row-checker']";
		 		public static final String CHANGE_NOTICE_CHECKBOX= "//div[@class='x-grid3-hd-checker']";
		 		public static final String PROMOTION_SYMBLE = "//div[@id='promotionRequest.promotionObjects.toolBar']//table[@id='promotionRequest.promotionObjectspromoteObjects_shortcutbar']";
		 		public static final String APPROVER = "//td[@class='x-grid3-col x-grid3-cell x-grid3-td-APPROVER ']//div[@class='x-grid3-cell-inner x-grid3-col-APPROVER']/input[@type='checkbox']";
		 		public static final String PARTICIPANT_LIST = "//div[@id='wt.maturity.PromotionNotice.CREATE.wizardParticipant.wfParticipants']//div[@class='x-form-field-wrap x-form-field-trigger-wrap']/img[@class='x-form-trigger x-form-arrow-trigger']";
		 		public static final String APROVER_CHECK_BOX = "//table[@class='x-grid3-row-table']//td[3]/div/input";
		 		//public static final String PARTICIPANT_LIST_VALUE = "//div[@class='x-combo-list-inner']";////div[contains(text(),'Members By Role')]";
		 		public static final String PARTICIPANT_LIST_VALUE = "//div[@class='x-combo-list-inner']//div[contains(text(),'Selected Participants')]";
		 		public static final String P_R_STATUS = "//div[@id='infoPageinfoPageTitleBarLifeCycleState']//div[@class='x-panel-body x-panel-body-noheader x-panel-body-noborder']";
		 		public static final String HOME_LINK = "//div[@id='homePageIconID']";
		 		public static final String SEARCH_TEXT_BOX = "//input[@name='recent.updates.table.searchInListTextBox']";
		 		public static final String SEARCHBOX_ID_PR = "//div[@id='recent.updates.table']//table[@class='x-toolbar-right-ct']//input[@name='recent.updates.table.searchInListTextBox']";
		 		public static final String SEARCH_BUTTON_PATH_PR = "//div[@id='recent.updates.table']//div[@id='recent.updates.table.toolBar']//span[@class='x-form-twin-triggers']//img[2]";
		 		public static final String VALIDATION_MESSAGE_ID_PR = "//div[@id='recent.updates.table']//div[@id='recent.updates.table.toolBar']//label[@id='recent.updates.table.searchStatusLabel']";
		 		public static final String ADVANCE_SEARCH_BOX_PR = "//div[@id='globalSearch']//input[@id='gloabalSearchField']";
		 		public static final String ADVANCE_SEARCH_BOX_BUTTON = "//div[@id='globalSearch']//span[@class='x-form-twin-triggers']/img[1]";
		 		public static final String ADVANCE_SEARCH_PAGE_COLLAPSE = "//div[@id='navigatorPanel-xcollapsed']/div[1]";
		 		public static final String ADVANCE_SEARCH_RESULT_PR = "//table[@class='x-grid3-row-table']/tbody/tr/td[9]";
		 		public static final String SEARCH_RESULT_PR = "//div[@id='recent.updates.table']//table[@class='x-grid3-row-table']/tbody/tr//td[3]/div[@class='x-grid3-cell-inner x-grid3-col-name']";
		 		public static final String SEARCH_RESULT_STATUS = "//div[@id='infoPageinfoPageTitleBarLifeCycleState']";
		 		
		 		//**************************************** Problem Report ********************************************************
		 		
		 		public static final String SEARCH_RESULT_XPATH_PROBLEMREPORT = "//div[@class='x-grid3-cell-inner x-grid3-col-number']/a";
		 		//public static final String PROBLEM_REPORT_NAME_FIELD= "//div[@class='x-grid3-row']/table[@class='x-grid3-row-table']//tbody/tr//td/div/input[@type='text']";
		 		public static final String PROBLEM_REPORT_NAME_FIELD="//div[@class='x-grid3-cell-inner x-grid3-col-attributesTableValue']/input[contains(@id,'NameInputId')][@type='text']";
		 		public static final String PROBLEM_REPORT_NAME = "//div[@id='dataStoreGeneral']/table//td[@attrid='name']";
		 		//public static final String CURRENT_STATE_VALUE_PR = "//table[@class='pp']//td[@class='ppdata']/input";
		 		public static final String CURRENT_STATE_VALUE_PR = "//table[@class='pp']//td[@class='ppdata']/select";
		 		//table[@class='pp']//td[@class='ppdata']/select[@class='ppdata']
		 		public static final String SELECT_STATE_VALUE_PR = "//table[@class='pp']//td[@class='ppdata']/select";
		 		public static final String NAME_PROBLEM_REPORT = "//table[@class='attributePanel-group-panel']//td[@class='attributePanel-value']/input[@class='required']";
		 		public static final String NEW_PROBLEM_REPORT_MENU = "//span[@class='x-menu-item-text'][contains(text(),'New Problem Report')]";
		 		public static final String ACTION_OPTION_PROBLEM_REPORT = "//table[@id='object_folderbrowser_toolbar_actions__folderbrowser_PDM__folderbrowser_PDM']//button[contains(text(),'Actions')]";
		 		public static final String PROBLEM_REPORT_SUBMIT_NOW_BUTTON = "//div[@class=' x-window']//table[@id='submitNowBtn']//button[@class='x-btn-text']";
		 		public static final String CONFIRMATIONMESSAGE = "//div[@class='x-window-plain-body x-window-plain-body-noheader x-window-plain-body-noborder']";
		 		public static final String SUBMITNOWBUTTON = "//table[@id='submitNowBtn']/tbody/tr[2]/td[2]/em";
		 		

		 		//**************************************** Advance search for change notice ****************************************
		  		
		 		public static final String CN_ADV_SEARCH_BUTTON="//div[@class='x-form-field-wrap x-form-field-trigger-wrap']/span/img[@id='ext-gen29']";

		  		public static final String CN_ADVANCE_SEARCH_TEXT= "//tbody[2]/tr/td/table/tbody/tr/td[4]/span/span[1]/div/input[@type='text']";

		  		public static final String CN_ADVANCE_VERIFY= "//a[contains(text(),'Verif_Test_Automation')]"; 		
		  	


			  	// ****************************************** FOLDER ****************************************
					
					public static final String EDITOPTION = "//div[@id='gridRowContextMenu']/ul[@class='x-menu-list']/li[@class='x-menu-list-item']/a/span[contains(text(),'Edit')]";
					public static final String EDITOPTION1 = "//div[@id='gridRowContextMenu']/ul[@class='x-menu-list']/li[11]/a";
					public static final String FOLDERNAME = "//td[@class='attributePanel-value']/input[@type='text']";
					public static final String CANCEL_BUTTON_FOLDER=".//*[@id='PJL_wizard_cancel']/tbody/tr[2]/td[2]";
					public static final String FOLDERSLIST = "//div[@class='x-grid3-cell-inner x-grid3-col-name']/a";
					//public static final String EDITOPTION            = "//div[@id='gridRowContextMenu']/ul[@class='x-menu-list']/li[11]/a"

					 //Change Notice ********************************

			 		public static final String CHANGE_NOTICE_BUTTON = "//button[contains(text(),'Actions')]";
			 		//public static final String CHANGE_NOTICE_DROPDOWN = "//a[@id='object_more parts toolbar actions new_infoPagedetailsPageActionsMenu_menu']";
			 		public static final String CHANGE_NOTICE_LIST = "//span[@class='x-menu-item-text'][contains(text(),'New Change Notice')]";
			 	//	public static final String CHANGE_NOTICE_NAME = "//table[@class='attributePanel-group-panel']//tr/td[@attrid='name']/input[@type='text']";
			  		public static final String CHANGE_NOTICE_COMPLEXITY = "//div[@id='dataStoreSetAttributesGroup']/table/tbody//tr/td[@class='attributePanel-value']/select";
			  		public static final String CHANGE_NOTICE_NEXT= ".//*[@id='PJL_wizard_next']/tbody/tr[2]/td[2]";
			  	//	public static final String CHANGE_NOTICE_CHECKBOX= "//div[@class='x-grid3-hd-checker']";
			  		public static final String CHANGE_NOTICE_FINISH= ".//*[@id='PJL_wizard_ok']/tbody/tr[2]/td[2]";
			  		//public static final String CHANGE_NOTICE_CHANGES_TAB="//span[@class='x-tab-strip-text'][contains(text(), 'Changes')]";
			  		//public static final String CHANGE_NOTICE_SEARCH=".//*[@id='related.changes.affectedByChangeNotices.searchInListTextBox']";
			 		
			  				  		 
			  		
			  		public static final String CN_ADVANCE_STATE= "//div[@id='comboIdDivqBAttributeTD-2']//img[@class='x-form-trigger x-form-arrow-trigger']";
			  		
			  		//Change Notice Set state
			  		
			  		public static final String CN_PRESENT_STATE= "//tr/td[8]/div[@class='x-grid3-cell-inner x-grid3-col-state']";
			  		public static final String CN_STATE_LIFECYCLE="//select[@id='lifecyclestate']";
			  		 public static final String CN_STATE_OK="//table[@id='PJL_wizard_ok']//td[@class='x-btn-mc']";
			  		
			  		 
			  		 //public static final String CN_GET_PRESENT_STATE="//div[@id='infoPageinfoPageTitleBarLifeCycleState']//div[@class='x-panel-body x-panel-body-noheader x-panel-body-noborder']/span";
			  		public static final String CN_GET_PRESENT_STATE="//span[contains(text(),'Canceled')]";
			  		 
			  		
			  		//*************************Change Task **************************************************
			  		
			  		public static final String CN_TASK_IMPLEMENT_TAB="//div[@class='x-tab-strip-wrap']//li[@id='infoPageinfoPanelID__infoPage_myTab_object_implementationPlanTab' ]";
			  		//public static final String CN_TASK_BUTTON="//button[@class='x-btn-text blist'][@style='background-image: url("+"netmarkets/images/task_create.gif"+");']";
			  		public static final String CN_TASK_BUTTON="//div[@class='x-panel-body x-panel-body-noheader x-panel-body-noborder x-border-layout-ct']//div[@id='changeNotice_implementationPlan_table.toolBar']//td[@class='x-toolbar-left']/table/tbody/tr/td[1]//button";

			  		//Change task search
			  		public static final String CN_TASK_SEARCH_EDIT="//input[@id='changeNotice_implementationPlan_table.searchInListTextBox']";
			  		public static final String CN_TASK_SEARCH_BUTTON="//div[@class='x-panel-body x-panel-body-noheader x-panel-body-noborder x-border-layout-ct']//div[@id='changeNotice_implementationPlan_table.toolBar']//td[@class='x-toolbar-right']/table[@class='x-toolbar-right-ct']//tr[@class='x-toolbar-right-row']/td[3]/div/span/img[2]";
			  		
			  		//Two search 
			  		public static final String CN_TASK_SEARCH_LIST="//div[@class='x-grid3-cell-inner x-grid3-col-number']/a";
			  		//public static final String CN_TASK_SEARCH_LIST_CLICK="//div[@class='x-grid3-row x-grid3-row-first']//div[@class='x-grid3-cell-inner x-grid3-col-number']/a";
			  	    public static final String CN_TASK_SEARCH_LIST_CLICK="//table[@class='x-grid3-row-table']//tbody//tr[1]//td//div[@class='x-grid3-cell-inner x-grid3-col-number']//a";
			  		
			  		public static final String CN_TASK_SEARCH_LIST_NAME="//div[@class='x-grid3-cell-inner x-grid3-col-name']";
	                
			  		public static final String CN_TASK_NEW_SETSTATE_DROPDOWN="//select[@id='lifecyclestate']";
			  		

				
}