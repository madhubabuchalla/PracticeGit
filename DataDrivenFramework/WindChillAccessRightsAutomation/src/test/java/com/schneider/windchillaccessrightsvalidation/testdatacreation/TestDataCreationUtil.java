package com.schneider.windchillaccessrightsvalidation.testdatacreation;

import java.awt.AWTException;
import java.io.IOException;
import org.apache.log4j.Logger;

import com.schneider.windchillaccessrightsvalidation.genericlibraries.Config;
import com.schneider.windchillaccessrightsvalidation.genericlibraries.GenericFunctionLibrary;
import com.schneider.windchillaccessrightsvalidation.repositories.ContentRepository;
import com.schneider.windchillaccessrightsvalidation.repositories.ObjectRepository;
import com.schneider.windchillaccessrightsvalidation.utilities.WindChillAccessRightsValidationUtil;

public class TestDataCreationUtil {

	static Logger log = Logger.getLogger(CatalogPartTestDataCreation.class);
	private static final Logger reportLog = Logger.getLogger("reportsLogger");

	WindChillAccessRightsValidationUtil WindChillUtil = new WindChillAccessRightsValidationUtil();
	
	//public static WebDriver driver = null;
	//

//  ************************** Create CAD Document ************************************

public String createCADDocument(String currentTemplate, String DocName){
		
	
	String documentName= null;
	String workSpaceName = null;
		try{
			if(currentTemplate.contains("Restricted")){
				workSpaceName = "";
			}else{
				workSpaceName = Config.ADMIN_CADDOC_WORKSPACE_NAME;
			}
			if(WindChillAccessRightsValidationUtil.navigateToWorkSpace(currentTemplate, Config.fetchContainerOrLibrary(currentTemplate), "Workspaces", workSpaceName)){
			
				
					GenericFunctionLibrary.SwitchToFrame(ObjectRepository.CAD_IFRAME);
					GenericFunctionLibrary.WaitForElementToBeVisible("xpath", ObjectRepository.CAD_CREATE_ICON, 20);
					if(GenericFunctionLibrary.isElementPresent(ObjectRepository.CAD_CREATE_ICON)){
				
				String parentWindow = GenericFunctionLibrary.switchToChildWindow(ObjectRepository.CAD_CREATE_ICON);
				Thread.sleep(3000);
					GenericFunctionLibrary.WaitForElementToBeVisible("xpath", ObjectRepository.CADDOCUMENT_TEMPLATE_DROPDOWN, 10);
					GenericFunctionLibrary.selectDropdownByVisibleText("xpath", ObjectRepository.CADDOCUMENT_TEMPLATE_DROPDOWN, ContentRepository.CAD_Template);
					GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.CADDOCUMENT_NAME_FIELD_XPATH, 10);
					GenericFunctionLibrary.setText("xpath", ObjectRepository.CADDOCUMENT_NAME_FIELD_XPATH, DocName+System.currentTimeMillis());
					
					Thread.sleep(2000);
				documentName = GenericFunctionLibrary.getAttributeValue("xpath", ObjectRepository.CADDOCUMENT_NAME_FIELD_XPATH, "value");
					
					log.info("Created Document Name is : "  + documentName);
					
									
					GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.MODIFY_OK_BUTTON);
					
					/*if(GenericFunctionLibrary.isAleretPresent(10)){
						GenericFunctionLibrary.getAlert();
						
						GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CREATEWINDOW_CANCEL_BUTTON_XPATH);
						GenericFunctionLibrary.switchToWindow(parentWindow);
					}*/
					
					GenericFunctionLibrary.switchToWindow(parentWindow);
					
					
					}
					
			
			}
			
		}catch(Exception e){
			log.info(e);
		}
		return documentName;
	}

public boolean checkInCADDcoument()throws Exception{
	boolean isCheckedIn = false;
	
	
	GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.ACTION_BUTTON);
	
	if(GenericFunctionLibrary.isElementPresent(ObjectRepository.ACTION_OPTION_CHECK_IN)){
	String parentWindow = GenericFunctionLibrary.switchToChildWindow(ObjectRepository.ACTION_OPTION_CHECK_IN);
	log.info("user clicked on Checkin option from the action dropdown");
	Thread.sleep(3000);
	GenericFunctionLibrary.SwitchToFrame("lbContentIframe");
	
	GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.OK_BUTTON_PARENT_WINDOW);
	log.info("User clicked on OK Button");
	// goto parent window
	GenericFunctionLibrary.switchToWindow(parentWindow);

	Thread.sleep(3000);
	
	isCheckedIn = true;
	}else{
		log.info("CheckIn option is NOT available");
		reportLog.error("CheckIn option is NOT available");
		isCheckedIn = false;
	}
	
	
	return isCheckedIn;
}



public boolean CADSetState(String filename,  String ObjectType, String template, String ContextName, String state) throws Exception {

	boolean isStateChanged = false;

	// searching expected content

	//if (isContentAvailableAndClick(filename)) {
	
	if(AdvSearchwithContext(filename, ObjectType, template, ContextName)){

		Thread.sleep(3000);
		
		
		
		if(checkInCADDcoument()){
		
		// goto content detail page, click on actions

		// clicking on 'actions' button
		GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.ACTION_BUTTON);

		// verifying whether 'setstate' option is available in action tab or not
		Thread.sleep(5000);
		
		if(GenericFunctionLibrary.isElementPresent(ObjectRepository.ACTION_OPTION_SETSTATE)){		
		
		// navigating to 'setstate' window
	GenericFunctionLibrary.switchToChildWindow(ObjectRepository.ACTION_OPTION_SETSTATE);

		//driver.switchTo().frame("lbContentIframe");
		GenericFunctionLibrary.SwitchToFrame("lbContentIframe");

	//	String previousState = GenericFunctionLibrary.getText("xpath",ObjectRepository.CURRENT_STATE_VALUE);

		// select the state value from dropdown by state
		Thread.sleep(4000);
		GenericFunctionLibrary.selectDropdownByVisibleText("xpath", ObjectRepository.STATE_DROP_DOWN, state);

		Thread.sleep(2000);

		String updatedState = GenericFunctionLibrary.getAttributeValue("xpath", ObjectRepository.STATE_DROP_DOWN, "value");

		// click on save
		GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.OK_BUTTON);
		
		log.info("object is changed to "+ updatedState );
		
		isStateChanged = true;
		
		Thread.sleep(5000);
		GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.TESTDATA_FOLDER_LANDING_PAGE);
		Thread.sleep(2000);

		}else{
			log.info("Set State option not available");
			reportLog.error("Set State option not available");
			GenericFunctionLibrary.refreshWindow();
			Thread.sleep(4000);
			
			GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.TESTDATA_FOLDER_LANDING_PAGE);
		}
		
	}else{
		
		log.info("CheckIn Failed");
		reportLog.error("CheckIn Failed");
		
	}
	}	else{
		
		log.info("Content not displayed in search result. Either content not available in the library or logged-in user is un-authorized");
		reportLog.error("Content not displayed in search result. Either content not available in the library or logged-in user is un-authorized");
		
		//GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CONTAINER_LANDING_PAGE);
		
	}
		

	return isStateChanged;

}

	
public String createDocumentAccessRightValidation(String ObjectType, String filename) throws IOException, InterruptedException {
		
		
	 	
	 	String documentName=null;
	 	
		Thread.sleep(5000);
		
		
		String parentWindow = GenericFunctionLibrary.switchToChildWindow(ObjectRepository.CREATE_NEW_DOCUMENT_BUTTON_XPATH);
		Thread.sleep(3000);
		
	//	String expetedAlertMessage = "ATTENTION: Action Unavailable.";
		
			
			// Selecting content source from drop down
		Thread.sleep(3000);
		GenericFunctionLibrary.selectDropdownByVisibleText("id", ObjectRepository.ELEMENT_DROPDOWN_ID, ObjectType);
		
		GenericFunctionLibrary.selectDropdownByVisibleText("id", ObjectRepository.SELECT_CONTENT_SOURCE_ID,ContentRepository.primaryContentSource);

		GenericFunctionLibrary.uploadFile(ObjectRepository.FILE_UPLOAD_BUTTON,Config.AUTOIT_SCRIPTS_PATH);
		
	
		Thread.sleep(10000);
		GenericFunctionLibrary.clearTextBox("xpath", ObjectRepository.FILE_NAME_FILED_XPATH);
		Thread.sleep(3000);
		GenericFunctionLibrary.setText("xpath", ObjectRepository.FILE_NAME_FILED_XPATH, filename+System.currentTimeMillis());

		documentName = GenericFunctionLibrary.getAttributeValue("xpath", ObjectRepository.FILE_NAME_FILED_XPATH, "value");
		
		log.info("Created Document Name is : "  + documentName);
		
		GenericFunctionLibrary.selectDropdownByIndex("id", ObjectRepository.DOCUMENTTYPE_DROPDOWN_ID, 1);
		Thread.sleep(3000);
		// Clicking on Finish button
		GenericFunctionLibrary.clickOnElement("id", ObjectRepository.FINISH_BUTTON_ID);
		Thread.sleep(3000);

		/*
		 if(GenericFunctionLibrary.isAleretPresent(3000)){
			String alertMessage = GenericFunctionLibrary.getAlert();
			log.info("Thrown Alert after clicking on Finingh Button and alert is "+ alertMessage);
		}
		*/
		
		// Moving on parent window
		GenericFunctionLibrary.switchToWindow(parentWindow);
		
		return documentName;
		

	}


//Object List Part Creation

//CREATE : AUTHORIZED & UN-AUTHORIZED

		public String createObjectListPartAccessRightValidation(String filename,String objectType) throws InterruptedException,
		AWTException {
			
			
			
			String DocumentName=null;
			
			
			try{
			
			/*
			 * calling 'SwitchToWindow' method that will handle multile windows and
			 * will perform 'click event' to move on next page
			 */
				Thread.sleep(5000);
			String parentWindow = GenericFunctionLibrary.switchToChildWindow(ObjectRepository.CREATE_PART_ICON);		
			Thread.sleep(3000);
			String expetedAlertMessage = "ATTENTION: Action Unavailable.";
			
			if (GenericFunctionLibrary.getAlert().contains(expetedAlertMessage)) {				
				log.info("Alert message is " + expetedAlertMessage);
				log.info("Logged-in user is not able to create object list content");
				
					
			}else{
				
				if(GenericFunctionLibrary.isMatchFoundInColumn(ObjectRepository.PART_OPTONS_XPATH, objectType)){
				Thread.sleep(2000);
				// selecting values from dropdown
				GenericFunctionLibrary.selectDropdownByVisibleText("xpath",ObjectRepository.PART_OBJECT_TYPE, objectType);

				GenericFunctionLibrary.selectDropdownByVisibleText("xpath",ObjectRepository.PART_END_ITEM, "No");

				GenericFunctionLibrary.setText("xpath", ObjectRepository.NAMEFIELD,filename+System.currentTimeMillis());

				 DocumentName = GenericFunctionLibrary.getAttributeValue("xpath", ObjectRepository.NAMEFIELD, "value");

				GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.LOCATION1);

				// clicking on 'Finish' button to create content
				GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.FINISH_BUTTON);

				// Moving on parent window
				GenericFunctionLibrary.switchToWindow(parentWindow);

				Thread.sleep(5000);

				log.info("Created Object Name is : "+ DocumentName);
				reportLog.debug("Created Object Name is : "+ DocumentName);

				// Perform search action to verify created document in 'folder content'
				// page
				Thread.sleep(2000);
				
			}else{
				log.info("Object List option is Not available in the list of Create Part drop down");
				reportLog.error("Object List option is Not available in the list of Create Part drop down");
				
				// clicking on 'Finish' button to create content
				GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CREATEWINDOW_CANCEL_BUTTON_XPATH);
				
				log.info("Clicked on Cancel button in create window");
				
				//StatusMessage = "Un-Authorized Successful";
				// Moving on parent window
				GenericFunctionLibrary.switchToWindow(parentWindow);

				Thread.sleep(5000);
			}
			
		}
			
		}catch(Exception e){
			e.printStackTrace();
		}
			
			return DocumentName;
		}

		
		//Raw material part creation
		


		public String createRawMaterialPartAccessRightValidation(String filename,String objectType) throws Exception {

			
			String DocumentName=null;
			
			/*
			 * calling 'SwitchToWindow' method that will handle multiple windows and
			 * will perform 'click event' to move on next page
			 */
			Thread.sleep(4000);
			GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.CREATE_PART_ICON, 10);
			String parentWindow = GenericFunctionLibrary.switchToChildWindow(ObjectRepository.CREATE_PART_ICON);
			
            Thread.sleep(3000);
			GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.PART_OPTONS_XPATH, 10);
				if (GenericFunctionLibrary.isMatchFoundInColumn(ObjectRepository.PART_OPTONS_XPATH, objectType)) {
					// selecting values from dropdown
					GenericFunctionLibrary.selectDropdownByVisibleText("xpath", ObjectRepository.PART_OBJECT_TYPE,
							objectType);
					Thread.sleep(2000);
					GenericFunctionLibrary.selectDropdownByVisibleText("xpath", ObjectRepository.PART_END_ITEM, "No");
					Thread.sleep(2000);
					GenericFunctionLibrary.setText("xpath", ObjectRepository.NAMEFIELD, filename+System.currentTimeMillis());
					Thread.sleep(2000);
					DocumentName = GenericFunctionLibrary.getAttributeValue("xpath", ObjectRepository.NAMEFIELD, "value");
					log.info("Value in Name Field : " + DocumentName);
					Thread.sleep(2000);
	
	// *************  Management Type Attributes *************************************	
					
					// Select Part Sensitivity as No
					GenericFunctionLibrary.selectDropdownByIndex("xpath",  ObjectRepository.SENSITIVITY,1);
					Thread.sleep(2000);
					
	// ************* Business Attributes  *****************************************************
					
					// Send value to OwnerOrganization editable text box
					GenericFunctionLibrary.selectDropdownByVisibleText("xpath", ObjectRepository.OWNER_ORGANIZATION, ContentRepository.OwnerOrganization);
					Thread.sleep(2000);
					
					// Select Approved option from CorpPreference drop down
					GenericFunctionLibrary.selectDropdownByVisibleText("xpath", ObjectRepository.CORP_PREFERENCE,	"Approved");
					Thread.sleep(2000);

					
					
					

					// **************  Technical Attributes ******************************************************
					
					
					GenericFunctionLibrary.setText("xpath", ObjectRepository.TECHNICAL_CLASSIFICATION, ContentRepository.raw_technical_classification_value);
					Thread.sleep(2000);
					GenericFunctionLibrary.KeyboardActionEnter();
					     
					//String mainWindow = GenericFunctionLibrary.switchToChildWindow("//td[@attrid='seiTechnicalClassification']/button[1]/img[@title='Find...']");
					
					GenericFunctionLibrary.setText("xpath", ObjectRepository.DEFAULT_UNIT, ContentRepository.defaultUnit);
					
					GenericFunctionLibrary.selectDropdownByVisibleText("xpath", ObjectRepository.ENVIRONEMENT_PROFILE, ContentRepository.environmental_profile_value);
					
					
					
					/*
					// ********************  Technical Attributes *********************************************
					
					GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.TECHNICAL_SEARCHBUTTON);
					
					// get the current window handle
					String parentHandle1 = GenericFunctionLibrary.switchToChildWindow();

					System.out.print("name of current ..." + parentHandle1);

					GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.RAW_SEARCH_TEXT);

					// Select ok button in window handle screen
					GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.FINISH_BUTTON);

					// Moving on parent window
					GenericFunctionLibrary.switchToWindow(parentHandle1);
					*/
					
					// Select kilograms option from defaultUnit drop down
					GenericFunctionLibrary.selectDropdownByVisibleText("xpath", ObjectRepository.RAW_DEFAULT_UNIT,ContentRepository.defaultUnit);
					
					// select Environmental profile
					
					GenericFunctionLibrary.selectDropdownByIndex("xpath", ObjectRepository.ENVIRONEMENT_PROFILE, 1);
				
				/*	// Select yes option from KeytoPerformance drop down
					GenericFunctionLibrary.setText("xpath", ObjectRepository.KEY_TO_PERFORMANCE, ContentRepository.KeytoPerformance);

					

					// Send value to CorpPreferenceComment editable text box
					GenericFunctionLibrary.setText("xpath", ObjectRepository.CORPPREFERENCE_COMMENT,
							ContentRepository.corpPreferenceComment);
				 */
					
	//********************** Environment Foor Print 	*******************************************
					
					GenericFunctionLibrary.selectDropdownByIndex("xpath", ObjectRepository.EIME_BASE_MATERIAL, 1);
					
					
					
	// *************************  Part Attributes *******************************************************				
					
					// Select Inseparable option from AssemblyMode drop down
					GenericFunctionLibrary.selectDropdownByVisibleText("xpath", ObjectRepository.ASSEEMBLY_MODE, "Inseparable");

					// Select Buy option from Source drop down
					GenericFunctionLibrary.selectDropdownByVisibleText("xpath", ObjectRepository.SOURCE, ContentRepository.source_value);

					// Select Serial Number option from DefaultTraceCode drop down
					GenericFunctionLibrary.selectDropdownByVisibleText("xpath", ObjectRepository.DEFAULT_TRACE_CODE, "Serial Number");

					GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.GATHERING_PART);
					Thread.sleep(2000);
					GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.LOCATION);

					// clicking on 'Next' button to create 'Raw material Part' document
					GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.NEXTBTN);

					GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.TYPEOFADDITIVES_DROPDOWN, 20);
					
					GenericFunctionLibrary.selectDropdownByVisibleText("xpath", ObjectRepository.TYPEOFADDITIVES_DROPDOWN, "Blowing agent");
					
					GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.HALOGENFREE_RADIOBUTTON);
					
					GenericFunctionLibrary.selectDropdownByVisibleText("xpath", ObjectRepository.COLOR2, "Black");
					
					GenericFunctionLibrary.setText("xpath", ObjectRepository.TYPICALLOADING_INPUT, "60");
					
					GenericFunctionLibrary.setText("xpath", ObjectRepository.MAXPROCESSTEMP_INPUT, "60");
					
					GenericFunctionLibrary.selectDropdownByIndex("xpath", ObjectRepository.LEGACY_GENERIC_MATERIAL_IDENTIFICATION, 1);
					
					// clicking on 'Finish' button to create 'Raw material Part' document
					GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.FINISH_BUTTON);

					Thread.sleep(3000);
					// Moving on parent window
					GenericFunctionLibrary.switchToWindow(parentWindow);

					log.info("Created Object name is : "+ DocumentName);
										
					// Perform search action to verify created document in 'folder
					// content'
					// page

					} else {
					log.info("Raw Material Part option is Not available in the list of Create Part drop down");
					reportLog.error("Raw Material Part option is Not available in the list of Create Part drop down");
					// clicking on 'Finish' button to create content
					GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CREATEWINDOW_CANCEL_BUTTON_XPATH);

					log.info("Clicked on Cancel button in create window");

					
					// Moving on parent window
					GenericFunctionLibrary.switchToWindow(parentWindow);

					Thread.sleep(5000);

				}
			
			return DocumentName;
		}
		
		
//Raw material part creation
		

	/*	public String createRawMaterialPartAccessRightValidation(String filename,String objectType) throws InterruptedException {

			
			String DocumentName=null;
			
			 * calling 'SwitchToWindow' method that will handle multiple windows and
			 * will perform 'click event' to move on next page
			 
			
			Thread.sleep(5000);
			String parentWindow = GenericFunctionLibrary.switchToChildWindow(ObjectRepository.CREATE_PART_ICON);
			Thread.sleep(3000);
			String expetedAlertMessage = "ATTENTION";

			boolean Flag = GenericFunctionLibrary.isAleretPresent(3);
			if (Flag == true) {
				GenericFunctionLibrary.getAlert().contains(expetedAlertMessage);
				log.info("Pass : " + expetedAlertMessage);
				
			} else {

				Thread.sleep(2000);

				if (GenericFunctionLibrary.isMatchFoundInColumn(ObjectRepository.PART_OPTONS_XPATH, objectType)) {
					// selecting values from dropdown
					GenericFunctionLibrary.selectDropdownByVisibleText("xpath", ObjectRepository.PART_OBJECT_TYPE,
							objectType);

					GenericFunctionLibrary.selectDropdownByVisibleText("xpath", ObjectRepository.PART_END_ITEM, "No");

					GenericFunctionLibrary.setText("xpath", ObjectRepository.RAW_NAME, filename+System.currentTimeMillis());

					DocumentName = GenericFunctionLibrary.getAttributeValue("xpath",
							ObjectRepository.RAW_NAME, "value");
					log.info("Value in Name Field : " + DocumentName);

					// Select kilograms option from defaultUnit drop down
					GenericFunctionLibrary.selectDropdownByVisibleText("xpath", ObjectRepository.RAW_DEFAULT_UNIT,
							"kilograms");

					GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.RAW_SEARCHBUTTON);
					// get the current window handle
					String parentHandle1 = GenericFunctionLibrary.switchToChildWindow();

					System.out.print("name of current ..." + parentHandle1);

					GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.RAW_SEARCH_TEXT);

					// Select ok button in window handle screen
					GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.RAW_OKBUTTON);

					// Moving on parent window
					GenericFunctionLibrary.switchToWindow(parentHandle1);
					// Send value to OwnerOrganization editable text box
					GenericFunctionLibrary.setText("xpath", ObjectRepository.OWNER_ORGANIZATION,
							ContentRepository.OwnerOrganization);

					// Select yes option from KeytoPerformance drop down
					GenericFunctionLibrary.setText("xpath", ObjectRepository.KEY_TO_PERFORMANCE,
							ContentRepository.KeytoPerformance);

					// Select Approved option from CorpPreference drop down
					GenericFunctionLibrary.selectDropdownByVisibleText("xpath", ObjectRepository.CORP_PREFERENCE,
							"Approved");

					// Send value to CorpPreferenceComment editable text box
					GenericFunctionLibrary.setText("xpath", ObjectRepository.CORPPREFERENCE_COMMENT,
							ContentRepository.corpPreferenceComment);

					// Select Inseparable option from AssemblyMode drop down
					GenericFunctionLibrary.selectDropdownByVisibleText("xpath", ObjectRepository.ASSEEMBLY_MODE,
							"Inseparable");

					// Select Buy option from Source drop down
					GenericFunctionLibrary.selectDropdownByVisibleText("xpath", ObjectRepository.SOURCE, "Buy");

					// Select Serial Number option from DefaultTraceCode drop down
					GenericFunctionLibrary.selectDropdownByVisibleText("xpath", ObjectRepository.DEFAULT_TRACE_CODE,
							"Serial Number");

					GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.GATHERING_PART);
					GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.RAW_LOCATION);

					// clicking on 'Next' button to create 'Raw material Part'
					// document
					GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.RAW_NEXTBTN);

					Thread.sleep(4000);

					// clicking on 'Finish' button to create 'Raw material Part'
					// document
					GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.FINISH_BUTTON_FOLDER);

					// Moving on parent window
					GenericFunctionLibrary.switchToWindow(parentWindow);

					// Perform search action to verify created document in 'folder
					// content'
					// page

									} else {
					log.info("Option is Not available in the list");

					// clicking on 'Finish' button to create content
					GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CREATEWINDOW_CANCEL_BUTTON_XPATH);

					log.info("Clicked on Cancel button in create window");

					
					// Moving on parent window
					GenericFunctionLibrary.switchToWindow(parentWindow);

					Thread.sleep(5000);

				}
			}
			return DocumentName;
		}
		*/
		
//Engineered Part Creation
public String createEngineeredPartAccessRightValidation(String documentName, String ObjectType) throws InterruptedException,
AWTException {

	String EngineeringPartDocument="";
	//String documentName=Config.ENGINEERED_PART_NAME+System.currentTimeMillis();
	/*
	 * calling 'SwitchToWindow' method that will handle multile windows and
	 * will perform 'click event' to move on next page
	 */
	
	
try{
	Thread.sleep(6000);
	
	GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.CREATE_PART_ICON, 10);
	String parentWindow = GenericFunctionLibrary.switchToChildWindow(ObjectRepository.CREATE_PART_ICON);		
	Thread.sleep(2000);
	String expetedAlertMessage = "ATTENTION: Action Unavailable.";
    
	if (GenericFunctionLibrary.getAlert().contains(expetedAlertMessage)) {		
		log.info("Pass : " + expetedAlertMessage);
		
		
	}else{
		
		GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.PART_OPTONS_XPATH, 10);
		// selecting values from dropdown
		
		if(GenericFunctionLibrary.isMatchFoundInColumn(ObjectRepository.PART_OPTONS_XPATH, ObjectType)){
		GenericFunctionLibrary.selectDropdownByVisibleText("xpath", ObjectRepository.PART_OBJECT_TYPE, ObjectType);

		GenericFunctionLibrary.selectDropdownByVisibleText("xpath",ObjectRepository.PART_END_ITEM, "No");
		
		Thread.sleep(4000);
		/*GenericFunctionLibrary.setText("xpath", ObjectRepository.NAME,
				ContentRepository.engPartDocumentName);*/
		GenericFunctionLibrary.setText("xpath", ObjectRepository.NAMEFIELD,documentName+System.currentTimeMillis());

		EngineeringPartDocument = GenericFunctionLibrary.getAttributeValue("xpath", ObjectRepository.NAMEFIELD, "value");
		log.info("Created data set name is : " + EngineeringPartDocument);

		GenericFunctionLibrary.selectDropdownByVisibleText("xpath",ObjectRepository.BOM_LEVEL, "Component");
		
		GenericFunctionLibrary.selectDropdownByVisibleText("xpath",ObjectRepository.DEFAULT_UNIT, "each");
		
		GenericFunctionLibrary.selectDropdownByVisibleText("xpath",ObjectRepository.PART_TYPE, "Separable");
		
		GenericFunctionLibrary.selectDropdownByVisibleText("xpath",ObjectRepository.SOURCE, "Make");
		
		GenericFunctionLibrary.selectDropdownByVisibleText("xpath",ObjectRepository.DEFAULT_TRACE_CODE, "Serial Number");

		GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.GATHERING_PART);
		GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.LOCATION);

		// clicking on 'Finish' button to create 'Enginering Part' document
		GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.FINISH_BUTTON);
		Thread.sleep(3000);
		
		// Moving on parent window
		GenericFunctionLibrary.switchToWindow(parentWindow);

		// Perform search action to verify created document in 'folder content'
		// page
		Thread.sleep(2000);
			
	}else{
		
		log.info("Engineered Part option is Not available in the list of Create Part drop down");
		reportLog.error("Engineered Part option is Not available in the list of Create Part drop down");
		
		// clicking on 'Finish' button to create content
		GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CREATEWINDOW_CANCEL_BUTTON_XPATH);
		
		log.info("Clicked on Cancel button in create window");
		
		// Moving on parent window
		GenericFunctionLibrary.switchToWindow(parentWindow);

		Thread.sleep(5000);
	}
	
}
	
}catch(Exception e){
	e.printStackTrace();
}
return EngineeringPartDocument;
	
	
}

//


//CREATE Content : AUTHORIZED & UN-AUTHORIZED [ CATALOG PART ]

		 public String createCatalogPartAccessRightValidation(String DocumentName,String ObjectType) throws Exception {

		  
		  String catalogPartName=null;
		  /*
		   * calling 'SwitchToWindow' method that will handle multiple windows and
		   * will perform 'click event' to move on next page
		   */
		  GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.CREATE_PART_ICON, 10);
		  String parentWindow = GenericFunctionLibrary.switchToChildWindow(ObjectRepository.CREATE_PART_ICON);
		  Thread.sleep(3000);
		  // String expetedAlertMessage = "ATTENTION: Action Unavailable.";
		  String expetedAlertMessage = "ATTENTION";

		  if (GenericFunctionLibrary.getAlert().contains(expetedAlertMessage)) {
		   log.info("Alert Message is  : " + expetedAlertMessage);
		  

		  } else {
			  GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.PART_OBJECT_TYPE, 10);
		   // selecting values from dropdown
		   if (GenericFunctionLibrary.isMatchFoundInColumn(ObjectRepository.PART_OBJECT_TYPE, ObjectType)) {
		    GenericFunctionLibrary.selectDropdownByVisibleText("xpath", ObjectRepository.PART_OBJECT_TYPE, ObjectType);

		    GenericFunctionLibrary.selectDropdownByVisibleText("xpath", ObjectRepository.PART_END_ITEM, "No");

		    GenericFunctionLibrary.setText("xpath", ObjectRepository.NAMEFIELD, DocumentName+System.currentTimeMillis());

		    catalogPartName = GenericFunctionLibrary.getAttributeValue("xpath", ObjectRepository.NAMEFIELD, "value");
		   

		   
		 // *************  Management Type Attributes *************************************		
			
			// Select Part Sensitivity as No
			GenericFunctionLibrary.selectDropdownByIndex("xpath", ObjectRepository.SENSITIVITY,1);
			
			
			
		// ********************** Business Attributes *************			
			
			// Select value to OwnerOrganization editable text box
		//	GenericFunctionLibrary.setText("xpath", ObjectRepository.OWNER_ORGANIZATION, ContentRepository.OwnerOrganization);
			
			GenericFunctionLibrary.selectDropdownByVisibleText("xpath", ObjectRepository.OWNER_ORGANIZATION, ContentRepository.OwnerOrganization);

			
			// Select Approved option from CorpPreference drop down
			GenericFunctionLibrary.selectDropdownByVisibleText("xpath", ObjectRepository.CORP_PREFERENCE,ContentRepository.corporatePrefDropDown);

			// Send value to CorpPreferenceComment editable text box
			GenericFunctionLibrary.setText("xpath", ObjectRepository.CORPPREFERENCE_COMMENT, ContentRepository.corpPreferenceComment);
			
		 // *********************************  Technical Attributes ******************************************************
			
			
			GenericFunctionLibrary.setText("xpath", ObjectRepository.TECHNICAL_CLASSIFICATION, ContentRepository.catalog_technical_classification_value);
			Thread.sleep(2000);
			GenericFunctionLibrary.KeyboardActionEnter();
			     
			//String mainWindow = GenericFunctionLibrary.switchToChildWindow("//td[@attrid='seiTechnicalClassification']/button[1]/img[@title='Find...']");
			
			 // Select kilograms option from default Unit drop down
		    GenericFunctionLibrary.selectDropdownByVisibleText("xpath", ObjectRepository.RAW_DEFAULT_UNIT, ContentRepository.defaultUnit);

			
			GenericFunctionLibrary.selectDropdownByVisibleText("xpath", ObjectRepository.ENVIRONEMENT_PROFILE, ContentRepository.environmental_profile_value);
			

			// **************  Environmental Foot Attributes ****************************************************
			
			GenericFunctionLibrary.selectDropdownByIndex("xpath", ObjectRepository.EIME_BASE_MATERIAL, 1);
			
			//******  Part Attributes **************************************************

			// Select Inseparable option from AssemblyMode drop down
			GenericFunctionLibrary.selectDropdownByVisibleText("xpath", ObjectRepository.ASSEEMBLY_MODE, ContentRepository.assemblymode_value);

			// Select Buy option from Source drop down
			GenericFunctionLibrary.selectDropdownByVisibleText("xpath", ObjectRepository.SOURCE, ContentRepository.source_value);

			// Default trace code
			GenericFunctionLibrary.selectDropdownByVisibleText("xpath", ObjectRepository.DEFAULT_TRACE_CODE, ContentRepository.default_tracecode_Value);

		    // gathering part
		    GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.GATHERING_PART);

		    // Life Cycle Template

		    // location
		    GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.LOCATION);

			// clicking on 'next' button
			GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.NEXTBTN);
			
			// selecting casing style in Next Page
			GenericFunctionLibrary.selectDropdownByIndex("xpath", ObjectRepository.CASINGSTYLE , 1);
			
			// clicking on finish button				
			GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.FINISH_BUTTON);
		
			 log.info("Created data set is  : " + catalogPartName);
			 
		    Thread.sleep(4000);
		 
		  }else {
		   
		   log.info("Catalog Part option is Not available in the list of Create Part drop down");
		   reportLog.error("Catalog Part option is Not available in the list of Create Part drop down");

		   // clicking on 'Finish' button to create content
		   GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CREATEWINDOW_CANCEL_BUTTON_XPATH);

		   log.info("Clicked on Cancel button in create window");

		   
		   // Moving on parent window
		   GenericFunctionLibrary.switchToWindow(parentWindow);

		   Thread.sleep(5000);

		  }
		   /*if(GenericFunctionLibrary.isAleretPresent(5)){
			     GenericFunctionLibrary.getAlert();
			     Thread.sleep(3000);
			     GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.CREATEWINDOW_CANCEL_BUTTON_XPATH);
			     GenericFunctionLibrary.switchToWindow(parentWindow);
			     
			    }*/
		  
		 }
		  GenericFunctionLibrary.switchToWindow(parentWindow);
		  return catalogPartName;
		 
		 }

//Manufacturer Part Creation
		 
		 public String createManufacturerPartAccessRightValidation(String DocumentName, String ObjectName) throws InterruptedException {
				
				String filename=null;
			
				/*
				 * calling 'SwitchToWindow' method that will handle multiple windows and
				 * will perform 'click event' to move on next page
				 */
				GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.CREATE_PART_ICON, 10);
				String parentWindow = GenericFunctionLibrary.switchToChildWindow(ObjectRepository.CREATE_PART_ICON);
				Thread.sleep(5000);
				//String expetedAlertMessage = "ATTENTION";

				/*boolean Flag=GenericFunctionLibrary.isAleretPresent(3);
				if (Flag==true) {
					GenericFunctionLibrary.getAlert().contains(expetedAlertMessage);
					log.info("Pass : " + expetedAlertMessage);
					StatusMessage = "Un-Authorized Successful";
					
				
				}*/String expetedAlertMessage = "ATTENTION:";
				   
				   
				   if (GenericFunctionLibrary.getAlert().contains(expetedAlertMessage)) {  
				    log.info("Alert message is " + expetedAlertMessage);
				  
				    }
				   
				else{
					
					GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.PART_OPTONS_XPATH, 10);
					if(GenericFunctionLibrary.isMatchFoundInColumn(ObjectRepository.PART_OPTONS_XPATH, ContentRepository.PRODUCTTYPE_MANUFATURER)){
					Thread.sleep(2000);
					// selecting values from dropdown
					GenericFunctionLibrary.selectDropdownByVisibleText("xpath",ObjectRepository.PART_OBJECT_TYPE, "Manufacturer Part");
					
					
					Thread.sleep(5000);
					//Click on manufacturerID search 
					String parentWindow1 = GenericFunctionLibrary.switchToChildWindow(ObjectRepository.MANUFACTURE_SEARCH);
					
					//GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.MANUFACTURE_SEARCH);
					
					Thread.sleep(5000);
					//Search Organization Name with constant value 'V*'
					GenericFunctionLibrary.setText("xpath", ObjectRepository.MANUFACTURE_SEARCHTEXT,ContentRepository.manufsearchText);
					
					
					//Search the Manufacture Search Submit button
					GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.MANUFACTURE_SEARCHSUBMIT);
					
					Thread.sleep(5000);
					//Select Radio button from the list of product
					GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.MANUFACTURE_SEARCHRADIO); 
					
						  
			        //Search the ManufactureSearch ok button 
					GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.MANUFACTURE_SEARCHOK); 

					// Moving on parent window
					GenericFunctionLibrary.switchToWindow(parentWindow1);

					GenericFunctionLibrary.selectDropdownByVisibleText("xpath",ObjectRepository.MANUF_PART_END_ITEM, "Yes");
					
					  Thread.sleep(5000);
					//Send value to manufacturer Number editable text box
				     GenericFunctionLibrary.setText("xpath", ObjectRepository.MANUF_NUMBER, "MP"+System.currentTimeMillis());
					
					//Enter name of manufacture part
				   GenericFunctionLibrary.setText("xpath", ObjectRepository.NAMEFIELD, DocumentName+System.currentTimeMillis());

				   filename = GenericFunctionLibrary.getAttributeValue("xpath", ObjectRepository.NAMEFIELD, "value");
					

					
					
					  //Select the Manufacture location part
				    	GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.LOCATION);

									
					//Select Commercialized  option from ManufactureAvailability drop down
						GenericFunctionLibrary.selectDropdownByVisibleText("xpath",ObjectRepository.MANUF_AVAILABILITY, "Commercialized");
						
					  
					//Select Inseparable option from AssemblyMode drop down
						GenericFunctionLibrary.selectDropdownByVisibleText("xpath",ObjectRepository.ASSEEMBLY_MODE, "Inseparable");
						
					//Select Serial Number option from DefaultTraceCode drop down
					    GenericFunctionLibrary.selectDropdownByVisibleText("xpath",ObjectRepository.DEFAULT_TRACE_CODE, "Serial Number");
									
					 //select the RawGatheringPart
						GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.GATHERING_PART);
								
							
					// clicking on 'Finish' button to create 'Manufacturer  Part' document
					GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.FINISH_BUTTON);
					
					log.info("created data set is  : " + filename);
					
					Thread.sleep(2000);
					
					// Moving on parent window
					GenericFunctionLibrary.switchToWindow(parentWindow);

					// Perform search action to verify created document in 'folder content'
					// page
					Thread.sleep(2000);
					/*if (isContentAvailable(ManufacturerPartDocument)) {
						log.info("content available in search");
						StatusMessage = "Authorized Successful";

					} else {
						log.info("Content is not available in search");
					
					}
*/
				}else{
					log.info("Manufacturer part option is Not available in the list of Create Part drop down");
					reportLog.error("Manufacturer part option is Not available in the list of Create Part drop down");
					// clicking on 'Finish' button to create content
					GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CREATEWINDOW_CANCEL_BUTTON_XPATH);
					
					log.info("Clicked on Cancel button in create window");
					
					
					// Moving on parent window
					GenericFunctionLibrary.switchToWindow(parentWindow);

					Thread.sleep(5000);
				}
				
			}
				return filename;
	}
		 
//
		// ------------------------------------  Below Methods for MOUNTING CONTEXT PART --------------------------------
			
			
			public String createMountingContextPartAccessRightValidation(String filename, String ObjectType) throws InterruptedException,
			AWTException {
				
				
				String PartName=null;
				/*
				  calling 'SwitchToWindow' method that will handle multile windows and
				  will perform 'click event' to move on next page
				 */
				GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.CREATE_PART_ICON, 10);	
				String parentWindow = GenericFunctionLibrary.switchToChildWindow(ObjectRepository.CREATE_PART_ICON);		
				Thread.sleep(3000);
				
				String expetedAlertMessage = "ATTENTION: Action Unavailable.";
				
				if (GenericFunctionLibrary.getAlert().contains(expetedAlertMessage)) {		
					log.info("Throwing an Alert : " + expetedAlertMessage);
					
				}else{		
					
					GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.PART_OPTONS_XPATH, 10);	
					if(GenericFunctionLibrary.isMatchFoundInColumn(ObjectRepository.PART_OPTONS_XPATH, ContentRepository.productType_MCP)){
					// selecting values from dropdown
					GenericFunctionLibrary.selectDropdownByVisibleText("xpath",ObjectRepository.PART_OBJECT_TYPE,ContentRepository.productType_MCP);

					GenericFunctionLibrary.selectDropdownByVisibleText("xpath", ObjectRepository.PART_END_ITEM, "No");

					/*// fill number value, however number field is auto generated but for
					// automation we are using own number
					GenericFunctionLibrary.clearTextBox("xpath",ObjectRepository.NUMBERFIELD);
					GenericFunctionLibrary.setText("xpath", ObjectRepository.NUMBERFIELD, "MCP_CD" + System.currentTimeMillis());
	*/
					Thread.sleep(2000);

					// fill name value
					GenericFunctionLibrary.setText("xpath", ObjectRepository.NAMEFIELD, filename+System.currentTimeMillis());
					// get insert value and store in variable
					 PartName = GenericFunctionLibrary.getAttributeValue("xpath", ObjectRepository.NAMEFIELD, "value");
					
					// set Location
					GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.LOCATION1);

					// clicking on 'Finish' button to create content
					GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.FINISH_BUTTON);
					 log.info("Created document name is : "+ PartName);
					// Moving on parent window
					GenericFunctionLibrary.switchToWindow(parentWindow);

					Thread.sleep(5000);

					
					// Perform search action to verify created document in 'folder content'
					// page
					Thread.sleep(2000);
						}else{
						log.info("Mounting Context Part option is Not available in the list of Create Part drop down");
						reportLog.info("Mounting Context Part option is Not available in the list of Create Part drop down");
						// clicking on 'Finish' button to create content
						GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CREATEWINDOW_CANCEL_BUTTON_XPATH);
						
						log.info("Clicked on Cancel button in create window");
						
						// Moving on parent window
						GenericFunctionLibrary.switchToWindow(parentWindow);

						Thread.sleep(5000);
				
					}			
				}
				return PartName;
			}
			




			//Engineered Part Creation
			public String createTreatmentPartAccessRightValidation(String fileName) throws InterruptedException,
			AWTException {
				
				
				String documentName=null;
				/*
				 * calling 'SwitchToWindow' method that will handle multile windows and
				 * will perform 'click event' to move on next page
				 */
			Thread.sleep(3000);
			try{
				GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.CREATE_PART_ICON, 10);
				String parentWindow = GenericFunctionLibrary.switchToChildWindow(ObjectRepository.CREATE_PART_ICON);		
				Thread.sleep(5000);
				String expetedAlertMessage = "ATTENTION: Action Unavailable.";

				if (GenericFunctionLibrary.getAlert().contains(expetedAlertMessage)) {		
					log.info("Alert Message is " + expetedAlertMessage);
					
					
				
				}else{
					
					Thread.sleep(3000);
					GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.PART_OBJECT_TYPE, 10);
					// selecting values from dropdown
					
					if(GenericFunctionLibrary.isMatchFoundInColumn(ObjectRepository.PART_OPTONS_XPATH, Config.TREATMENT_PART)){
					GenericFunctionLibrary.selectDropdownByVisibleText("xpath", ObjectRepository.PART_OBJECT_TYPE, Config.TREATMENT_PART);

					GenericFunctionLibrary.selectDropdownByVisibleText("xpath", ObjectRepository.PART_END_ITEM, "No");
					
					Thread.sleep(4000);
					
					GenericFunctionLibrary.setText("xpath", ObjectRepository.NAMEFIELD, fileName+System.currentTimeMillis() );

					documentName = GenericFunctionLibrary.getAttributeValue("xpath", ObjectRepository.NAMEFIELD, "value");
					

					// *************  Management Type Attributes *************************************		
					
					// Select Part Sensitivity as No
					GenericFunctionLibrary.selectDropdownByIndex("xpath",ObjectRepository.SENSITIVITY,1);
					
					// ****************** Business Attributes ******************************************
					
					///
					Thread.sleep(5000);
					// Select value to OwnerOrganization editable text box
					GenericFunctionLibrary.selectDropdownByVisibleText("xpath", ObjectRepository.OWNER_ORGANIZATION, ContentRepository.OwnerOrganization);

					// Select yes option from KeytoPerformance drop down
					
				//	GenericFunctionLibrary.selectDropdownByVisibleText("xpath", ObjectRepository.KEY_TO_PERFORMANCE,ContentRepository.KeytoPerformance);
					
					//GenericFunctionLibrary.setText("xpath", ObjectRepository.KEY_TO_PERFORMANCE,ContentRepository.KeytoPerformance);

					// Select Approved option from CorpPreference drop down
					GenericFunctionLibrary.selectDropdownByVisibleText("xpath", ObjectRepository.CORP_PREFERENCE,"Approved");

					// Send value to CorpPreferenceComment editable text box
					GenericFunctionLibrary.setText("xpath", ObjectRepository.CORPPREFERENCE_COMMENT, ContentRepository.corpPreferenceComment);
					
					
					
			//**************  Technical Attributes ******************************************************
					
					
					GenericFunctionLibrary.setText("xpath", ObjectRepository.TECHNICAL_CLASSIFICATION, ContentRepository.treatment_technical_classification_value);
					Thread.sleep(2000);
					GenericFunctionLibrary.KeyboardActionEnter();
					     
					//String mainWindow = GenericFunctionLibrary.switchToChildWindow("//td[@attrid='seiTechnicalClassification']/button[1]/img[@title='Find...']");
					
					GenericFunctionLibrary.setText("xpath", ObjectRepository.DEFAULT_UNIT, ContentRepository.defaultUnit);
					
					GenericFunctionLibrary.selectDropdownByVisibleText("xpath", ObjectRepository.ENVIRONEMENT_PROFILE, ContentRepository.environmental_profile_value);
					
					
					/*// ****************  Technical Attributes **********************************
					
					
					// Select Technical classification

					GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.TECHNICAL_SEARCHBUTTON);
					
					// get the current window handle
					
					Thread.sleep(3000);
					String parentHandle1 = GenericFunctionLibrary.switchToChildWindow();
					System.out.print("name of current ..." + parentHandle1);
					Thread.sleep(3000);
					GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.TP_SEARCH_TEXT);

					// Select ok button in window handle screen
					GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.FINISH_BUTTON);

					// Moving on parent window
					GenericFunctionLibrary.switchToWindow(parentHandle1);
			*/
					
			//**************  Environmental Foot Attributes ****************************************************
					
					GenericFunctionLibrary.selectDropdownByIndex("xpath", ObjectRepository.EIME_BASE_MATERIAL, 1);
					
					
					//  ******************************* Part Attributes **********************************		
				

					// Select Inseparable option from AssemblyMode drop down
					GenericFunctionLibrary.selectDropdownByVisibleText("xpath", ObjectRepository.ASSEEMBLY_MODE,"Inseparable");

					// Select Buy option from Source drop down
					GenericFunctionLibrary.selectDropdownByVisibleText("xpath", ObjectRepository.SOURCE, "Buy");

					// Default trace code
					GenericFunctionLibrary.selectDropdownByVisibleText("xpath", ObjectRepository.DEFAULT_TRACE_CODE, "Serial Number");

					// gathering part
					GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.GATHERING_PART);

					// Life Cycle Template

					// location
					GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.LOCATION);

					// clicking on 'Next' button
					GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.NEXTBTN);

					Thread.sleep(3000);
					
					// clicking on 'Finish' button
					GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.FINISH_BUTTON);

					log.info("created data set is : " + documentName);

					Thread.sleep(3000);
					
					// Moving on parent window
					GenericFunctionLibrary.switchToWindow(parentWindow);

					// Perform search action to verify created document in 'folder content'
					// page
					Thread.sleep(2000);
					}
			/*	}else{
					log.info("Option is Not available in the list");
					
					// clicking on 'Finish' button to create content
					GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CREATEWINDOW_CANCEL_BUTTON_XPATH);
					
					log.info("Clicked on Cancel button in create window");
					
				
					// Moving on parent window
					GenericFunctionLibrary.switchToWindow(parentWindow);

					Thread.sleep(5000);
				}*/
				
			}
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
				return documentName;
			}




//ObjectList Creation


public String createObjectListPartAccessRightValidation(String objectType) throws InterruptedException,
AWTException {
	
	
	String filename=null;
	
	try{
	
	/*
	 * calling 'SwitchToWindow' method that will handle multile windows and
	 * will perform 'click event' to move on next page
	 */
		
	GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.CREATE_PART_ICON, 10);
	String parentWindow = GenericFunctionLibrary.switchToChildWindow(ObjectRepository.CREATE_PART_ICON);		
	Thread.sleep(3000);
	String expetedAlertMessage = "ATTENTION: Action Unavailable.";
	
	if (GenericFunctionLibrary.getAlert().contains(expetedAlertMessage)) {				
		log.info("alert message is " + expetedAlertMessage);
		log.info("Logged-in user is not able to create object list content");
	
			
	}else{
		GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.PART_OPTONS_XPATH, 10);
		if(GenericFunctionLibrary.isMatchFoundInColumn(ObjectRepository.PART_OPTONS_XPATH, objectType)){
		Thread.sleep(2000);
		// selecting values from dropdown
		GenericFunctionLibrary.selectDropdownByVisibleText("xpath",ObjectRepository.PART_OBJECT_TYPE, objectType);

		GenericFunctionLibrary.selectDropdownByVisibleText("xpath",ObjectRepository.PART_END_ITEM, "No");

		GenericFunctionLibrary.setText("xpath", ObjectRepository.NAMEFIELD,Config.OBJECT_LIST_PART_NAME+System.currentTimeMillis());

		filename = GenericFunctionLibrary.getAttributeValue("xpath", ObjectRepository.NAMEFIELD, "value");

		
		GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.LOCATION1);

		// clicking on 'Finish' button to create content
		GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.FINISH_BUTTON);


		log.info("Created File name is : "+ filename);
		
		// Moving on parent window
		GenericFunctionLibrary.switchToWindow(parentWindow);

		Thread.sleep(5000);

		
		// Perform search action to verify created document in 'folder content'
		// page
		Thread.sleep(2000);
		/*if (isContentAvailableXpath(expectedSearchDocument)) {
			log.info("content available in search and logged-in user is able to create object list content");
			StatusMessage = "Authorized Successful";
		} else {
			log.info("Content is not available in search");
			
		}*/
	}else{
		log.info("Object List option is Not available in the list of Create part drop down");
		reportLog.info("Object List option is Not available in the list of Create part drop down");
		
		// clicking on 'Finish' button to create content
		GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CREATEWINDOW_CANCEL_BUTTON_XPATH);
		
		log.info("Clicked on Cancel button in create window");
		
		//StatusMessage = "Un-Authorized Successful";
		// Moving on parent window
		GenericFunctionLibrary.switchToWindow(parentWindow);

		Thread.sleep(5000);
	}
	
	}
	
	}catch(Exception e){
	e.printStackTrace();
	}
	
	return filename;
	}

//Create Problem Report


public String createProblemReportAccessRightValidation(String fileName) throws InterruptedException {

	String filename=null;
	
	GenericFunctionLibrary.refreshWindow();
	Thread.sleep(2000);
	GenericFunctionLibrary.refreshWindow();
	Thread.sleep(2000);
	
	// clicking on 'actions' button
	GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.ACTION_OPTION_PROBLEM_REPORT);
	Thread.sleep(5000);

	// verifying whether 'New' option is available in action tab or not
	if(GenericFunctionLibrary.isElementPresent(ObjectRepository.NEW_ACTION_OPTION_PROMOTIONAL_REQUEST))
	{			
		GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.NEW_ACTION_OPTION_PROMOTIONAL_REQUEST);
		
		Thread.sleep(3000);
		
		// verify 'new problem report option in 'new' menu
		if(GenericFunctionLibrary.isElementPresent(ObjectRepository.NEW_PROBLEM_REPORT_MENU)){
			
		log.info("clicked on 'new problem report' option");
		Thread.sleep(2000);
		String parentWindow = GenericFunctionLibrary.switchToChildWindow(ObjectRepository.NEW_PROBLEM_REPORT_MENU);
		
		Thread.sleep(3000);
		// Input name
		GenericFunctionLibrary.setText("xpath", ObjectRepository.FILE_NAME_FILED_XPATH, fileName+System.currentTimeMillis());
		
		Thread.sleep(3000);
		filename = GenericFunctionLibrary.getAttributeValue("xpath", ObjectRepository.FILE_NAME_FILED_XPATH, "value");
		
		// click finish					
		GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.MODIFY_OK_BUTTON);
						
		Thread.sleep(5000);
						
		GenericFunctionLibrary.switchToChildWindow();
			
			
		String ConfirmationMessage=	GenericFunctionLibrary.getText("xpath", ObjectRepository.CONFIRMATIONMESSAGE);
		log.info(ConfirmationMessage);
		// clicking on 'submit now' button				
		
		GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.SUBMITNOWBUTTON);
		
		log.info("Created Problem report name :" + filename);
		
		Thread.sleep(5000);
		
		// switch on parent window
		GenericFunctionLibrary.switchToWindow(parentWindow);
		
		GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CONTAINER_LANDING_PAGE);
		
		}else{
			
			log.info("  New Problem Report Option is not available in sub menu of NEW ");
			reportLog.error(" New Problem Report Option is not available in sub menu of NEW");
			
			GenericFunctionLibrary.refreshWindow();
			Thread.sleep(4000);
			
			GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CONTAINER_LANDING_PAGE);
			
		}
	
				
	}
			
return filename;

}

//Create Managed Collection

public String createManagedCollectionAccessRightValidation(String fileName) throws InterruptedException{
	
	Thread.sleep(3000);
	String filename=null;
	GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ACTION_BUTTON_LANDING_PAGE, 10);
	// click on 'Actions' button			
	GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ACTION_BUTTON_LANDING_PAGE);
	
	// verify 'New' option in action menu
	GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.NEW_OPTION, 10);
	if(GenericFunctionLibrary.isElementPresent(ObjectRepository.NEW_OPTION)){
		
		// Click on 'NEW'				
		GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.NEW_OPTION);
		
		//if(GenericFunctionLibrary.isElementPresent(ObjectRepository.NEW_MANAGED_COLLECTION_OPTION)){
		GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.MENU_UNDER_NEW_OPTION, 10);
			if(GenericFunctionLibrary.isMatchFoundInColumn(ObjectRepository.MENU_UNDER_NEW_OPTION, "New Managed Collection")){
			// Click on 'NEW MANAGED COLLECTION' option				
			//GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.NEWOPTION_NEW_MANAGED_COLLECTION);
			String parentWindow = GenericFunctionLibrary.switchToChildWindow(ObjectRepository.NEW_MANAGED_COLLECTION_OPTION);
		
			GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.FILE_NAME_FILED_XPATH, 10);
			
			// Enter name
			GenericFunctionLibrary.setText("xpath", ObjectRepository.FILE_NAME_FILED_XPATH, fileName + System.currentTimeMillis() );
			Thread.sleep(3000);
			
			filename = GenericFunctionLibrary.getAttributeValue("xpath", ObjectRepository.FILE_NAME_FILED_XPATH, "value");
			
			// Click Finsh
			GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.MODIFY_OK_BUTTON);
			log.debug("Created Managed collection name is : " + filename);
			Thread.sleep(3000);
			
			// Moving on parent window
			GenericFunctionLibrary.switchToWindow(parentWindow);
			/*GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.CONTAINER_LANDING_PAGE, 10);
			GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.CONTAINER_LANDING_PAGE);
			*/// Verify whether content create or not
			
			/*if (isContentAvailableXpath(ExpectedManagedCollectionValue)) {
				log.info("content available in search and logged-in user is able to create manage collection content");						
				statusMessage = "Authorized Successful";
			} 
			else {
				log.info("Content is not available in search");
				statusMessage = "Un-Authorized Successful";				
				
			}*/				
		}
		
		else{
			log.info(" 'New Managed Collection' option is not available in sub menu of NEW");
			log.error(" 'New Managed Collection' option is not available in sub menu of NEW");
			
		
			/*	GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.CONTAINER_LANDING_PAGE, 10);
			GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.CONTAINER_LANDING_PAGE);
		*/
			
		}
		
	}else{
		log.info("'New' option is not available in Action Menu");
		log.error(" 'New' option is not available in Action Menu");
		
	/*	GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.CONTAINER_LANDING_PAGE, 10);
		GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.CONTAINER_LANDING_PAGE);
	*/
	}
	
	return filename;
	}

public String createFolderAccessRightValidation(String folderName) throws InterruptedException, IOException {

	
	String FolderName = "";
	//Thread.sleep(5000);
	
	GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.CREATE_FOLDER_ICON, 10);
	String ParentWindow = GenericFunctionLibrary.switchToChildWindow(ObjectRepository.CREATE_FOLDER_ICON);

	//Thread.sleep(5000);
	GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.NAME_ATTRIBUTE, 10);
	GenericFunctionLibrary.setText("xpath", ObjectRepository.NAME_ATTRIBUTE, folderName+System.currentTimeMillis());

	 FolderName = GenericFunctionLibrary.getAttributeValue("xpath", ObjectRepository.NAME_ATTRIBUTE,"value");
	
	log.info("Created folder name is : "+ FolderName);
	
	//Thread.sleep(2000);

	//GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.FINISH_BUTTON_FOLDER);

	//Thread.sleep(5000);
	GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.FINISH_BUTTON, 10);
	if(GenericFunctionLibrary.isWindowClosed(ObjectRepository.FINISH_BUTTON)){
		
		GenericFunctionLibrary.switchToWindow(ParentWindow);
	
		
	//if(GenericFunctionLibrary.switchToWindow(ParentWindow)){
	// * Handling alert popup
	}else if(GenericFunctionLibrary.isAleretPresent(5)){
	
	String actualAlertMessage = GenericFunctionLibrary.getAlert();

	String expetedAlertMessage = "ATTENTION:";


	if (actualAlertMessage.contains(expetedAlertMessage)) {
		log.info(" throwing "+ actualAlertMessage +" alert hence Folder cant be created ");
		reportLog.info(" throwing "+ actualAlertMessage +" alert hence Folder cant be created");
		GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CANCEL_BUTTON_FOLDER);
		
	} else {
		log.info(" Due to some expectable behavior");
	}
	} 
	
	GenericFunctionLibrary.switchToWindow(ParentWindow);
	return FolderName;

}



public boolean setStateRightAccessValidation(String filename,  String ObjectType, String template, String ContextName, String state) throws InterruptedException {

	boolean isStateChanged = false;

	// searching expected content

	//if (isContentAvailableAndClick(filename)) {
	
	if(AdvSearchwithContext(filename, ObjectType, template, ContextName)){

		Thread.sleep(3000);
		
		// goto content detail page, click on actions

		// clicking on 'actions' button
		GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.ACTION_BUTTON);

		// verifying whether 'setstate' option is available in action tab or not
		Thread.sleep(5000);
		
		if(GenericFunctionLibrary.isElementPresent(ObjectRepository.ACTION_OPTION_SETSTATE)){		
		
		// navigating to 'setstate' window
		 GenericFunctionLibrary.switchToChildWindow(ObjectRepository.ACTION_OPTION_SETSTATE);

		//driver.switchTo().frame("lbContentIframe");
		GenericFunctionLibrary.SwitchToFrame("lbContentIframe");

		//String previousState = GenericFunctionLibrary.getText("xpath",ObjectRepository.CURRENT_STATE_VALUE);

		// select the state value from dropdown by state
		Thread.sleep(4000);
		GenericFunctionLibrary.selectDropdownByVisibleText("xpath", ObjectRepository.STATE_DROP_DOWN, state);

		Thread.sleep(2000);

		String updatedState = GenericFunctionLibrary.getAttributeValue("xpath", ObjectRepository.STATE_DROP_DOWN, "value");

		// click on save
		GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.OK_BUTTON);
		
		log.info("Document is change to "+updatedState +" state");
		
		isStateChanged = true;
		
		Thread.sleep(5000);
		GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.TESTDATA_FOLDER_LANDING_PAGE);
		Thread.sleep(2000);

		// compare previous and updated state, should not same

		/*if (updatedState.equals(previousState)) {

			log.info("Fail : State doesn't change");
		} else {
			log.info("Pass : State changed and logged-in user has set state permission");
			log.info("previous state was : " + previousState);
			log.info("updated state is : " + updatedState);
			StatusMessage = "Authorized Successful";
			GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CONTAINER_LANDING_PAGE);
		}*/
		}else{
			log.info("Set State option not available");
			reportLog.error("Set State option not available");
			
			GenericFunctionLibrary.refreshWindow();
			Thread.sleep(4000);
			
			GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.TESTDATA_FOLDER_LANDING_PAGE);
		}
	}
	
	else{
		
		log.info("Content not displayed in search result. Either content not available in the library or logged-in user is un-authorized");
		//GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CONTAINER_LANDING_PAGE);
	}
		

	return isStateChanged;

}

//AdvanceSearch for Testdata Creation

public boolean AdvSearch( String filename, String ObjectType) throws InterruptedException{
	
	boolean isAvailable = false;

	log.info("Advance search starts.....");

	Thread.sleep(2000);
	
	// Click on advance search button/icon
	GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_BUTTON_XPATH, 30);
	GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH_BUTTON_XPATH);
	log.info("Clicked on Search Link from Home page");
	Thread.sleep(2000);

	// Clicking on Advanced Search Tab

	GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH_TAB_XPATH);
	Thread.sleep(3000);

	// clicking on Edit Search Criteria Link
	if (GenericFunctionLibrary.isElementPresent(ObjectRepository.ADV_SEARCH_NEW_CRITERIA)) {
		GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH_NEW_CRITERIA);
		Thread.sleep(2000);
	}
	
	if (GenericFunctionLibrary.isElementPresent("//input[@id='keywordkeywordField_SearchTextBox']")){
		log.info("Search is working");
	}else{
		GenericFunctionLibrary.refreshWindow();
		Thread.sleep(3000);
		GenericFunctionLibrary.refreshWindow();
		Thread.sleep(3000);
		AdvSearch(filename,ObjectType);
	}
	
	if (GenericFunctionLibrary.isMatchFoundInList(ObjectRepository.ADV_SEARCH_SEARCHTEXT_VALUE, ObjectType)) {
		GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_ALL, 20);
		GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH_ALL);
		Thread.sleep(1000);
		// Select check box based on ADV_SEARCH_VALUE variable
		GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_SEARCHTEXT_VALUE, 20);
		GenericFunctionLibrary.selectMatchedFoundInList(ObjectRepository.ADV_SEARCH_SEARCHTEXT_VALUE,ObjectType);
	} else {
		// Click on Add link and Switch to child window
		String parentWindow = GenericFunctionLibrary.switchToChildWindow(ObjectRepository.ADV_SEARCH_ADD);
		Thread.sleep(2000);

		// Enter search text in window
		GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_WINDOW, 20);
		GenericFunctionLibrary.setText("xpath", ObjectRepository.ADV_SEARCH_WINDOW, ObjectType);
		Thread.sleep(1000);

		// search text box
		GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH_WINDOW_CLICK);
		Thread.sleep(2000);

		// Add searched check box
		GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_CHECKBOX, 20);
		GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH_CHECKBOX);
		Thread.sleep(2000);
		GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH_OK);
		Thread.sleep(2000);

		// Switch to main window
		GenericFunctionLibrary.switchToWindow(parentWindow);

		/*
		 * Thread.sleep(2000); //Enter the keyword
		 * GenericFunctionLibrary.setText("id",
		 * ObjectRepository.ADV_SEARCH_SEARCHTEXTBOX_ID, "*");
		 */

		Thread.sleep(2000);
		// click on ALL item check box
		GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_ALL, 20);
		GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH_ALL);
		Thread.sleep(1000);
		// Select check box based on ADV_SEARCH_VALUE variable
		GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_SEARCHTEXT_VALUE, 20);
		GenericFunctionLibrary.selectMatchedFoundInList(ObjectRepository.ADV_SEARCH_SEARCHTEXT_VALUE, ObjectType);
		
	}
	
	
	/*if (GenericFunctionLibrary.isMatchFoundInList(ObjectRepository.ADV_SEARCH_SEARCHTEXT_VALUE,ADV_SEARCH_CONTAINER_NAME)) {

		GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_ALL_CONTEXTS, 20);
		GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH_ALL_CONTEXTS);

		Thread.sleep(1000);
		// Select check box based on ADV_SEARCH_Container_VALUE variable
		GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_SEARCHTEXT_VALUE, 20);
		GenericFunctionLibrary.selectMatchedFoundInList(ObjectRepository.ADV_SEARCH_SEARCHTEXT_VALUE,
				ADV_SEARCH_CONTAINER_NAME);
	} else {

		// Switch to child window String parentWindow_context

		String parentWindow = GenericFunctionLibrary.switchToChildWindow(ObjectRepository.ADV_SEARCH_CONTEXT_ADD);
		Thread.sleep(6000);

		GenericFunctionLibrary.WaitForElementPresence("id", ObjectRepository.ADV_SEARCH_CONTEXT_DROPDOWN, 20);

		if (Template.contains("Library")) {

			GenericFunctionLibrary.selectDropdownByVisibleText("id", ObjectRepository.ADV_SEARCH_CONTEXT_DROPDOWN,
					"Library");
		} else if (Template.contains("Product")) {
			GenericFunctionLibrary.selectDropdownByVisibleText("id", ObjectRepository.ADV_SEARCH_CONTEXT_DROPDOWN,
					"Product");
		}

		// Thread.sleep(2000);
		// Enter container name in context search
		GenericFunctionLibrary.WaitForElementPresence("id", ObjectRepository.ADV_SEARCH_CONTEXT_NAME, 20);
		GenericFunctionLibrary.setText("xpath", ObjectRepository.ADV_SEARCH_CONTEXT_NAME,
				ADV_SEARCH_CONTAINER_NAME);
		Thread.sleep(1000);
		// click on search button
		GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH_CONTEXT_SEARCH);
		Thread.sleep(2000);
		GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_CONTEXT_CHECKBOX, 20);
		GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH_CONTEXT_CHECKBOX);
		Thread.sleep(2000);

		GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH_CONTEXT_OK);
		Thread.sleep(2000);
		// Switch to main window
		GenericFunctionLibrary.switchToWindow(parentWindow);
		Thread.sleep(3000);

		GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_ALL_CONTEXTS, 20);
		GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH_ALL_CONTEXTS);

		Thread.sleep(1000);
		// Select check box based on ADV_SEARCH_Container_VALUE variable
		GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_SEARCHTEXT_VALUE, 20);
		GenericFunctionLibrary.selectMatchedFoundInList(ObjectRepository.ADV_SEARCH_SEARCHTEXT_VALUE,
				ADV_SEARCH_CONTAINER_NAME);
	}
*/
			
			GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.CN_ADVANCE_SEARCH_TEXT, 30);
			Thread.sleep(2000);
			GenericFunctionLibrary.clearTextBox("xpath", ObjectRepository.CN_ADVANCE_SEARCH_TEXT);
			Thread.sleep(3000);
			GenericFunctionLibrary.setText("xpath",ObjectRepository.CN_ADVANCE_SEARCH_TEXT, filename); 
			Thread.sleep(3000);
			
			//Thread.sleep(3000);
			// click on Search button
			GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH);
			Thread.sleep(3000);
			if(GenericFunctionLibrary.isAleretPresent(3)){
				GenericFunctionLibrary.getAlert();
				GenericFunctionLibrary.refreshWindow();
				Thread.sleep(3000);
				GenericFunctionLibrary.refreshWindow();
				Thread.sleep(3000);
				GenericFunctionLibrary.refreshWindow();
				Thread.sleep(2000);
				AdvSearch(filename, ObjectType );
			}
			
			if (GenericFunctionLibrary.isElementPresent(ObjectRepository.ADV_SEARCH_LIST)){
				isAvailable = true;
		    String	RECORD_VALUE = GenericFunctionLibrary.getText("xpath", ObjectRepository.ADV_SEARCH_LIST);
			String ADV_SEARCH_FIRST_LIST = "//div[@class='x-grid3-cell-inner x-grid3-col-number']/a[contains(text(),'"+ RECORD_VALUE + "')]";
			GenericFunctionLibrary.WaitForElementPresence("xpath", ADV_SEARCH_FIRST_LIST, 30);
			GenericFunctionLibrary.clickOnElement("xpath", ADV_SEARCH_FIRST_LIST);
			
			}
	
	return isAvailable;
	
}

public boolean AdvSearchwithContext(String filename, String ObjectType, String template, String ContextName) throws InterruptedException{
	
	boolean isAvailable = false;

	log.info("Advance search starts.....");

	Thread.sleep(2000);
	GenericFunctionLibrary.refreshWindow();
	Thread.sleep(2000);
	GenericFunctionLibrary.refreshWindow();
	Thread.sleep(5000);
	
	// Click on advance search button/icon
	GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_BUTTON_XPATH, 30);
	GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH_BUTTON_XPATH);
	log.info("Clicked on Search Link from Home page");
	Thread.sleep(2000);

	// Clicking on Advanced Search Tab

	GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH_TAB_XPATH);
	Thread.sleep(3000);

	// clicking on Edit Search Criteria Link
	if (GenericFunctionLibrary.isElementPresent(ObjectRepository.ADV_SEARCH_NEW_CRITERIA)) {
		GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH_NEW_CRITERIA);
		Thread.sleep(2000);
	}else{
		GenericFunctionLibrary.refreshWindow();
		Thread.sleep(2000);
		GenericFunctionLibrary.refreshWindow();
		Thread.sleep(5000);
		AdvSearchwithContext(filename, ObjectType, template, ContextName);
	}
	if (GenericFunctionLibrary.isMatchFoundInList(ObjectRepository.ADV_SEARCH_SEARCHTEXT_VALUE, ObjectType)) {
		GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_ALL, 20);
		GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH_ALL);
		Thread.sleep(1000);
		// Select check box based on ADV_SEARCH_VALUE variable
		GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_SEARCHTEXT_VALUE, 20);
		GenericFunctionLibrary.selectMatchedFoundInList(ObjectRepository.ADV_SEARCH_SEARCHTEXT_VALUE,
				ObjectType);
	} else {
		// Click on Add link and Switch to child window
		String parentWindow = GenericFunctionLibrary.switchToChildWindow(ObjectRepository.ADV_SEARCH_ADD);
		Thread.sleep(2000);

		// Enter search text in window
		GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_WINDOW, 20);
		GenericFunctionLibrary.setText("xpath", ObjectRepository.ADV_SEARCH_WINDOW, ObjectType);
		Thread.sleep(1000);

		// search text box
		GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH_WINDOW_CLICK);
		Thread.sleep(2000);

		// Add searched check box
		GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_CHECKBOX, 20);
		GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH_CHECKBOX);
		Thread.sleep(2000);
		GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH_OK);
		Thread.sleep(2000);

		// Switch to main window
		GenericFunctionLibrary.switchToWindow(parentWindow);

		/*
		 * Thread.sleep(2000); //Enter the keyword
		 * GenericFunctionLibrary.setText("id",
		 * ObjectRepository.ADV_SEARCH_SEARCHTEXTBOX_ID, "*");
		 */

		Thread.sleep(2000);
		// click on ALL item check box
		GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_ALL, 20);
		GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH_ALL);
		Thread.sleep(1000);
		// Select check box based on ADV_SEARCH_VALUE variable
		GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_SEARCHTEXT_VALUE, 20);
		GenericFunctionLibrary.selectMatchedFoundInList(ObjectRepository.ADV_SEARCH_SEARCHTEXT_VALUE, ObjectType);
		
	}
	
	
	// Select the add on context
			Thread.sleep(3000);

			if (GenericFunctionLibrary.isMatchFoundInList(ObjectRepository.ADV_SEARCH_SEARCHTEXT_VALUE,ContextName)) {

				GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_ALL_CONTEXTS, 20);
				GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH_ALL_CONTEXTS);

				Thread.sleep(1000);
				// Select check box based on ADV_SEARCH_Container_VALUE variable
				GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_SEARCHTEXT_VALUE, 20);
				GenericFunctionLibrary.selectMatchedFoundInList(ObjectRepository.ADV_SEARCH_SEARCHTEXT_VALUE,
						ContextName);
			} else {

				// Switch to child window String parentWindow_context

				String parentWindow = GenericFunctionLibrary.switchToChildWindow(ObjectRepository.ADV_SEARCH_CONTEXT_ADD);
				Thread.sleep(6000);

				GenericFunctionLibrary.WaitForElementPresence("id", ObjectRepository.ADV_SEARCH_CONTEXT_DROPDOWN, 20);

				if (template.contains("Library")) {

					GenericFunctionLibrary.selectDropdownByVisibleText("id", ObjectRepository.ADV_SEARCH_CONTEXT_DROPDOWN,
							"Library");
				} else if (template.contains("Product")) {
					GenericFunctionLibrary.selectDropdownByVisibleText("id", ObjectRepository.ADV_SEARCH_CONTEXT_DROPDOWN,
							"Product");
				}

				// Thread.sleep(2000);
				// Enter container name in context search
				GenericFunctionLibrary.WaitForElementPresence("id", ObjectRepository.ADV_SEARCH_CONTEXT_NAME, 20);
				GenericFunctionLibrary.setText("xpath", ObjectRepository.ADV_SEARCH_CONTEXT_NAME,
						ContextName);
				Thread.sleep(1000);
				// click on search button
				GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH_CONTEXT_SEARCH);
				Thread.sleep(2000);
				GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_CONTEXT_CHECKBOX, 20);
				GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH_CONTEXT_CHECKBOX);
				Thread.sleep(2000);

				GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH_CONTEXT_OK);
				Thread.sleep(2000);
				// Switch to main window
				GenericFunctionLibrary.switchToWindow(parentWindow);
				Thread.sleep(3000);

				GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_ALL_CONTEXTS, 20);
				GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH_ALL_CONTEXTS);

				Thread.sleep(1000);
				// Select check box based on ADV_SEARCH_Container_VALUE variable
				GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_SEARCHTEXT_VALUE, 20);
				GenericFunctionLibrary.selectMatchedFoundInList(ObjectRepository.ADV_SEARCH_SEARCHTEXT_VALUE,
						ContextName);
			}
			
			GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.CN_ADVANCE_SEARCH_TEXT, 20);
			if(GenericFunctionLibrary.isElementPresent(ObjectRepository.CN_ADVANCE_SEARCH_TEXT)){
			Thread.sleep(2000);
			GenericFunctionLibrary.clearTextBox("xpath", ObjectRepository.CN_ADVANCE_SEARCH_TEXT);
			Thread.sleep(3000);
			GenericFunctionLibrary.setText("xpath",ObjectRepository.CN_ADVANCE_SEARCH_TEXT, filename); 
			Thread.sleep(3000);
			}else{
				GenericFunctionLibrary.refreshWindow();
				Thread.sleep(3000);
				GenericFunctionLibrary.refreshWindow();
				Thread.sleep(3000);
				GenericFunctionLibrary.refreshWindow();
				Thread.sleep(5000);
				AdvSearchwithContext(filename, ObjectType, template, ContextName);
			}
			
			Thread.sleep(3000);
			// click on Search button
			GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH);
			Thread.sleep(3000);
			if(GenericFunctionLibrary.isAleretPresent(3)){
				GenericFunctionLibrary.getAlert();
				GenericFunctionLibrary.refreshWindow();
				Thread.sleep(3000);
				GenericFunctionLibrary.refreshWindow();
				Thread.sleep(3000);
				GenericFunctionLibrary.refreshWindow();
				Thread.sleep(5000);
				AdvSearchwithContext(filename, ObjectType, template, ContextName);
			}
			
			if (GenericFunctionLibrary.isElementPresent(ObjectRepository.ADV_SEARCH_LIST)){
				isAvailable = true;
		    String	RECORD_VALUE = GenericFunctionLibrary.getText("xpath", ObjectRepository.ADV_SEARCH_LIST);
			String ADV_SEARCH_FIRST_LIST = "//div[@class='x-grid3-cell-inner x-grid3-col-number']/a[contains(text(),'"+ RECORD_VALUE + "')]";
			GenericFunctionLibrary.WaitForElementPresence("xpath", ADV_SEARCH_FIRST_LIST, 30);
			GenericFunctionLibrary.clickOnElement("xpath", ADV_SEARCH_FIRST_LIST);
			
			
			
			}
	
	return isAvailable;
	
}
public boolean setStateRightAccessValidationWithAdvSearchExeptPartandDoc(String filename, String state, String objectType, String Template, String context)
		throws InterruptedException {
	
	boolean isStateChanged = false;

		// goto content detail page, click on actions

			if(AdvSearchwithContext(filename,objectType, Template, context )){
				
			
			Thread.sleep(7000);
			
			//clicking on 'actions' button
			GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.ACTION_BUTTON);
			Thread.sleep(5000);
			//verifying whether 'setstate' option is available in action tab or not
			
			if(GenericFunctionLibrary.isElementPresent(ObjectRepository.ACTION_OPTION_SETSTATE)){
				Thread.sleep(4000);
			// navigating to 'setstate' window
			String parentWindow1 = GenericFunctionLibrary.switchToChildWindow(ObjectRepository.ACTION_OPTION_SETSTATE);
			log.info("Found Set State Option");
			
			Thread.sleep(3000);
			String previousState = GenericFunctionLibrary.getAttributeValue("xpath", ObjectRepository.CURRENT_STATE_VALUE_PR, "value");
			
			//String previousState = GenericFunctionLibrary.getText("xpath", ObjectRepository.CURRENT_STATE_VALUE_PR);
			log.info("previous state is :" + previousState);
			// Verify whether value present in dropdown or not
			if(GenericFunctionLibrary.isMatchFoundInColumn(ObjectRepository.CURRENT_STATE_VALUE_PR, state)){

			//select state value from dropdown
			GenericFunctionLibrary.selectDropdownByVisibleText("xpath", ObjectRepository.SELECT_STATE_VALUE_PR, state);

			Thread.sleep(5000);

			String updatedState = GenericFunctionLibrary.getAttributeValue("xpath", ObjectRepository.CURRENT_STATE_VALUE_PR, "value");
			Thread.sleep(5000);
			
			log.info("document changed to " + updatedState+ " state");
			
			// click on save
			GenericFunctionLibrary.clickOnElement("id",ObjectRepository.FINISH_BUTTON_ID);
			
			isStateChanged = true;
			Thread.sleep(2000);

			GenericFunctionLibrary.switchToWindow(parentWindow1);
			Thread.sleep(3000);
			GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CONTAINER_LANDING_PAGE);

			}else{
				
				log.info("Option is not available in dropdown");
				GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.TESTDATA_FOLDER_LANDING_PAGE);
			
			}
			}else{
				
				log.info(" Set State option is not available in Action menu");
				reportLog.error(" Set State option is not available in Action menu");
				//GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.TESTDATA_FOLDER_LANDING_PAGE);
				GenericFunctionLibrary.refreshWindow();
				Thread.sleep(4000);
				GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.TESTDATA_FOLDER_LANDING_PAGE);				
				
			}
			}else{
				
				log.info("No Content available with : "+ filename);
				GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.TESTDATA_FOLDER_LANDING_PAGE);				
				
			}
			
		return isStateChanged;
	}

//SetState with advance search for Problem Report

public boolean setStateRightAccessValidationWithAdvSearch(String filename, String state, String ObjectType)
		throws InterruptedException {
boolean isStateChanged = false;
		// goto content detail page, click on actions

			if(AdvSearch(filename,ObjectType)){
				
			
			Thread.sleep(7000);
			
			//clicking on 'actions' button
			GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.ACTION_BUTTON);
			Thread.sleep(5000);
			//verifying whether 'setstate' option is available in action tab or not
			
			if(GenericFunctionLibrary.isElementPresent(ObjectRepository.ACTION_OPTION_SETSTATE)){
				Thread.sleep(4000);
			// navigating to 'setstate' window
			String parentWindow1 = GenericFunctionLibrary.switchToChildWindow(ObjectRepository.ACTION_OPTION_SETSTATE);
			log.info("Found Set State Option");
			
			Thread.sleep(3000);
			String previousState = GenericFunctionLibrary.getAttributeValue("xpath", ObjectRepository.CURRENT_STATE_VALUE_PR, "value");
			
			//String previousState = GenericFunctionLibrary.getText("xpath", ObjectRepository.CURRENT_STATE_VALUE_PR);
			log.info("previous state is :" + previousState);
			// Verify whether value present in dropdown or not
			if(GenericFunctionLibrary.isMatchFoundInColumn(ObjectRepository.CURRENT_STATE_VALUE_PR, state)){

			//select state value from dropdown
			GenericFunctionLibrary.selectDropdownByVisibleText("xpath", ObjectRepository.SELECT_STATE_VALUE_PR, state);

			Thread.sleep(5000);

			String updatedState = GenericFunctionLibrary.getAttributeValue("xpath", ObjectRepository.CURRENT_STATE_VALUE_PR, "value");
			Thread.sleep(5000);
			log.info("updated state is :" + updatedState);
			// click on save
			GenericFunctionLibrary.clickOnElement("id",ObjectRepository.FINISH_BUTTON_ID);
			
			log.info("document changed to " + updatedState+ " state");
			
			
			 			 
			Thread.sleep(2000);

			GenericFunctionLibrary.switchToWindow(parentWindow1);
			Thread.sleep(3000);
			GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.TESTDATA_FOLDER_LANDING_PAGE);

			isStateChanged = true;
			
			}
			else{
				
				log.info("Option is not available in dropdown");
				GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.TESTDATA_FOLDER_LANDING_PAGE);
			
			}
			}else{
				
				log.info(" Set State option is not available in Action menu");
				reportLog.error(" Set State option is not available in Action menu");
				//GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.TESTDATA_FOLDER_LANDING_PAGE);
				GenericFunctionLibrary.refreshWindow();
				Thread.sleep(4000);
				GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.TESTDATA_FOLDER_LANDING_PAGE);				
				
			}
			}else{
				
				log.info("No Content available with : "+ filename);
				GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.TESTDATA_FOLDER_LANDING_PAGE);				
				
			}
			
			//GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CONTAINER_LANDING_PAGE);
			
			return isStateChanged;
	}


public boolean setStateChangeTask(String fileName,String Newstate,String ObjectType) throws InterruptedException {
	
	boolean isStateChanged = false;

//Change Notice Advance search
	
/*AdvSearch("DONOTDELETE_TESTING_FOR_CHANGETASK", "Change Notice");

  		Thread.sleep(5000);	
		GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.CN_TASK_IMPLEMENT_TAB, 30);
		GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.CN_TASK_IMPLEMENT_TAB);
		
		log.info("Change Task starts...");
		Thread.sleep(2000);
		*/
	
		 //Search the Change task name in search box
		log.info(fileName);
       // boolean Value=changeTaskIsContentAvailable(fileName);
        
        if (changeTaskIsContentAvailable(fileName)) {
        	       //log.info("Search content found : " + Value);
    				Thread.sleep(2000);
    				String SearchDocumentName = GenericFunctionLibrary.getText("xpath", ObjectRepository.CN_TASK_SEARCH_LIST_NAME);

    				log.info("Change Task Name:"+SearchDocumentName);
    				log.info("Clicking on Search content....... ");
    				//Click on the task 
    				GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.CN_TASK_SEARCH_LIST_CLICK,30);
    				GenericFunctionLibrary.changeTaskSelectIdentityFromList(ObjectRepository.CN_TASK_SEARCH_LIST_CLICK);
    				GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CN_TASK_SEARCH_LIST_CLICK);
    				Thread.sleep(2000);	
    				// rename for change task 
    			   	// clicking on 'actions' button
        				GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.ACTION_BUTTON);
        				Thread.sleep(2000);	
        				
        				// verifying whether 'setstate' option is available in action tab or not
        				
        				if(GenericFunctionLibrary.isElementPresent(ObjectRepository.ACTION_OPTION_SETSTATE)){		
        				
        				// navigating to 'setstate' window
        				String parentWindow = GenericFunctionLibrary.switchToChildWindow(ObjectRepository.ACTION_OPTION_SETSTATE);

        				// select the state value from drop down by using value
        				Thread.sleep(2000);
        				GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.CN_TASK_NEW_SETSTATE_DROPDOWN,30);
        				GenericFunctionLibrary.selectDropdownByVisibleText("xpath", ObjectRepository.CN_TASK_NEW_SETSTATE_DROPDOWN,Newstate);

        				Thread.sleep(2000);
        				
        				// clicking on 'ok' button
    					GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.MODIFY_OK_BUTTON);
    					
    					log.info("document changed to " + Newstate+ " state");
    					
    					isStateChanged = true;
    				  	Thread.sleep(2000);
    				  	GenericFunctionLibrary.switchToWindow(parentWindow);
    				  	Thread.sleep(2000);
    				  	GenericFunctionLibrary.clickOnElement("xpath", "//span[@class='attributePanel-fieldset-title'][contains(text(),'Attributes')]/ancestor::legend/following-sibling::div//td[@class='attributePanel-value']/a");
    				  	GenericFunctionLibrary.WaitForElementToBeClickable("xpath", ObjectRepository.DETAILS_MENU, 30);
    				  	GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.DETAILS_MENU);
    				  	//Thread.sleep(5000);
    				  	//GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CONTAINER_LANDING_PAGE);				
    					
        				// Verify the  state
    				  	
    				   /*String CN_TASK_PRESENT_STATE="//span[contains(text(),'"+Newstate+"')]";
    				   log.info("Xpath : "+CN_TASK_PRESENT_STATE );
    				   String presentstate=GenericFunctionLibrary.getText("xpath", CN_TASK_PRESENT_STATE);
    				   log.info("Present state: "+presentstate);
        				if (presentstate.equalsIgnoreCase(Newstate)) {
        					log.info("Pass : State changed and logged-in user has set state permission");
        					StatusMessage = "Authorized Successful";
        					GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CONTAINER_LANDING_PAGE);
            			
        				} else
        				  log.info("Fail : State doesn't change");
        					*/
        				
    				  	/*log.info("Pass : State changed and logged-in user has set state permission");
    					StatusMessage = "Authorized Successful";
    					 */   					
        				}else{
        					log.info(" Set State option is not available in Action menu");
        					reportLog.error(" Set State option is not available in Action menu");
        					
        					GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CONTAINER_LANDING_PAGE);				
        					
        					}
        }
        return isStateChanged;	
        }	
        				
     


//

@SuppressWarnings("static-access")
public boolean changeTaskIsContentAvailable(String searchDocument) throws InterruptedException {
	boolean flag = false;

	// clear textBox
	GenericFunctionLibrary.clearTextBox("xpath", ObjectRepository.CN_TASK_SEARCH_EDIT);
	Thread.sleep(3000);
	// input value in search text box
	GenericFunctionLibrary.setText("xpath", ObjectRepository.CN_TASK_SEARCH_EDIT, searchDocument);
	Thread.sleep(3000);
	// clicking on search button
	GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CN_TASK_SEARCH_BUTTON);
	log.info("clicked on Search Button");
	Thread.sleep(2000);

	
	//old item CN_TASK_SEARCH_LIST
	//boolean text = ;
	
			//GenericFunctionLibrary.getText("xpath", ObjectRepository.VALIDATION_MESSAGE_ID);
	
	if (WindChillUtil.isElementPresent(ObjectRepository.CN_TASK_SEARCH_LIST_CLICK)==false) {
		flag = false;
		log.info("No Search content found");
	} else {
		// storing search result value in 'actual search result'
		// variable
		/*Thread.sleep(2000);
		String SearchDocumentNumber = GenericFunctionLibrary.getText("xpath", ObjectRepository.CN_TASK_SEARCH_LIST);

		log.info("Change Taks Number:"+SearchDocumentNumber);
		Thread.sleep(2000);
		String SearchDocumentName = GenericFunctionLibrary.getText("xpath", ObjectRepository.CN_TASK_SEARCH_LIST_NAME);

		log.info("Change Task Name:"+SearchDocumentName);
*/		
			
		flag = true;
		
		log.info("Search string found");

	}

	return flag;
}



//Create ChangeTask testdata creation
public String createchangeTaskAccessRightValidation(String CN_fileName, String template, String ContextName, String CT_fileName) throws InterruptedException {
	
   
	//Change Notice Advance search
	//Change Notice, Change notice Name & state
	

	// Change Notice advance search expected content
	
	if(AdvSearchwithContext( CN_fileName, Config.CHANGE_NOTICE, template, ContextName)){


/*	GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_BUTTON_XPATH, 30);
	GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH_BUTTON_XPATH);
	log.info("Clicked on Search Link from Home page");
	Thread.sleep(2000);
	
	// Clicking on Advanced Search Tab

	GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH_TAB_XPATH);
	Thread.sleep(3000);
	
	//clicking on Edit Search Criteria Link
	if(GenericFunctionLibrary.isElementPresent(ObjectRepository.ADV_SEARCH_NEW_CRITERIA)){
	GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH_NEW_CRITERIA);
	Thread.sleep(2000);
	}

	// Switch to child window
	String parentWindow = GenericFunctionLibrary.switchToChildWindow(ObjectRepository.ADV_SEARCH_ADD);
	Thread.sleep(2000);

	// Enter search text in window
	GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_WINDOW, 30);
	GenericFunctionLibrary.setText("xpath", ObjectRepository.ADV_SEARCH_WINDOW, "Change Notice");
	Thread.sleep(1000);
	
	// search text box
	GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH_WINDOW_CLICK);
	Thread.sleep(2000);
	
	// Add searched check box
			GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_CHECKBOX, 30);
			GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH_CHECKBOX);
			Thread.sleep(2000);
			GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH_OK);
			Thread.sleep(2000);
			
			// Switch to main window
			GenericFunctionLibrary.switchToWindow(parentWindow);
			
			Thread.sleep(2000);
			// click on ALL item check box
			GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_ALL, 30);
			GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH_ALL);
			Thread.sleep(1000);
			
			// Select check box based on ADV_SEARCH_VALUE variable
			GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_SEARCHTEXT_VALUE, 30);
			GenericFunctionLibrary.selectMatchedFoundInList(ObjectRepository.ADV_SEARCH_SEARCHTEXT_VALUE, "Change Notice");
		
			Thread.sleep(3000);
			GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.CN_ADVANCE_SEARCH_TEXT, 30);
			GenericFunctionLibrary.clearTextBox("xpath", ObjectRepository.CN_ADVANCE_SEARCH_TEXT);
			Thread.sleep(3000);
			GenericFunctionLibrary.setText("xpath",ObjectRepository.CN_ADVANCE_SEARCH_TEXT, filename); 
			Thread.sleep(3000);
			
			Thread.sleep(3000);
			// click on Search button
			GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH);
			Thread.sleep(3000);
				
			//Click on document
			String	RECORD_VALUE = GenericFunctionLibrary.getText("xpath", ObjectRepository.ADV_SEARCH_LIST);
			String ADV_SEARCH_FIRST_LIST = "//div[@class='x-grid3-cell-inner x-grid3-col-number']/a[contains(text(),'"+ RECORD_VALUE + "')]";
			GenericFunctionLibrary.WaitForElementPresence("xpath", ADV_SEARCH_FIRST_LIST, 30);
			GenericFunctionLibrary.clickOnElement("xpath", ADV_SEARCH_FIRST_LIST);*/

		// goto content detail page, click on actions

	
			//log.info("Change notice number :"+filename);
	}		 
	   //Click on document
	   		
			// Perform search action to verify created document in 'folder content'
			Thread.sleep(5000);
					
			GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.CN_TASK_IMPLEMENT_TAB, 30);
			GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.CN_TASK_IMPLEMENT_TAB);
			
			//click on change task button
			Thread.sleep(5000);	
			GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.CN_TASK_BUTTON, 30);
		//	GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.CN_TASK_BUTTON);
			log.info("Change Task starts...");
			
			//Start the change notice operation
			String ParentCN=GenericFunctionLibrary.switchToChildWindow(ObjectRepository.CN_TASK_BUTTON);
			Thread.sleep(5000);	
			
				//Set Name for change notice
				Thread.sleep(2000);
				
				GenericFunctionLibrary.setText("xpath",ObjectRepository.NAMEFIELD, CT_fileName+System.currentTimeMillis());
				Thread.sleep(2000);
				
				String CNTNAME = GenericFunctionLibrary.getAttributeValue("xpath", ObjectRepository.NAMEFIELD, "value");
				
				GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CHANGE_NOTICE_FINISH);
				
				log.info("Created chang Task name is : "+ CNTNAME);
	        	Thread.sleep(5000);
	            GenericFunctionLibrary.switchToWindow(ParentCN);
	            Thread.sleep(5000);
	           // GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CONTAINER_LANDING_PAGE);
	            
	return CNTNAME;

}


/***************************************Change notice*****************************************/

public String createChangeNoticeAccessRightValidation(String fileName, String template, String Context, String CN_fileName) throws InterruptedException {
		
			
  	
		//Check the file is existing
		
		String searchFile;
		String CNNAME=null;
		
		
	if(AdvSearchwithContext(fileName, Config.DESCRIBE_DOCUMENT, template, Context)){
		//if (isContentAvailableAndClick(fileName)) {
			searchFile = "File found Successful";
			log.info(searchFile);
			Thread.sleep(2000);
			GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ACTION_BUTTON);
			Thread.sleep(3000);
			// verify 'New' option in action menu

			if(GenericFunctionLibrary.isElementPresent(ObjectRepository.NEW_OPTION)){
				
				// Click on 'NEW'				
				GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.NEW_OPTION);
				Thread.sleep(3000);
				if(GenericFunctionLibrary.isElementPresent(ObjectRepository.CHANGE_NOTICE_LIST)){
				
	
			//Start the change notice operation
			String ParentCN=GenericFunctionLibrary.switchToChildWindow(ObjectRepository.CHANGE_NOTICE_LIST);
			    
			//Set Name for change notice
			Thread.sleep(5000);
	    	
			GenericFunctionLibrary.setText("xpath",ObjectRepository.NAMEFIELD,CN_fileName+System.currentTimeMillis());
			Thread.sleep(2000);
			CNNAME=GenericFunctionLibrary.getAttributeValue("xpath", ObjectRepository.NAMEFIELD, "value");
			
			GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.CHANGE_NOTICE_COMPLEXITY,30);
			 GenericFunctionLibrary.selectDropdownByVisibleText("xpath",ObjectRepository.CHANGE_NOTICE_COMPLEXITY,ContentRepository.CHANGENOTICECOMPLEXITY);
            Thread.sleep(2000);
          
            GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository. CHANGE_NOTICE_NEXT);
            Thread.sleep(2000);
            GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository. CHANGE_NOTICE_CHECKBOX);
            Thread.sleep(2000);
            GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository. CHANGE_NOTICE_NEXT);
            Thread.sleep(2000);
            GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CHANGE_NOTICE_FINISH);
        	Thread.sleep(5000);
            GenericFunctionLibrary.switchToWindow(ParentCN);
            Thread.sleep(3000);
          
            /* GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.CN_ADVANCE_VERIFY,30);
			GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CN_ADVANCE_VERIFY);
        	Thread.sleep(2000);
            */
           
        	log.info("Change notice Name : "+CNNAME);
        	    
		}else{
			
			log.info("New Change Notice option is not available");
			reportLog.info("New Change Notice option is not available");
			
		}
			
			}else{
				
				log.info("New Option is NOT available");
				reportLog.info("New Option is NOT available");
			}
			
	}else {
			searchFile = "File not found ";
			log.info(searchFile);
			
		}
		GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CONTAINER_LANDING_PAGE);
		Thread.sleep(4000);
		GenericFunctionLibrary.clearTextBox("xpath",ObjectRepository.SEARCH_BOX_PATH);
		Thread.sleep(5000);
		GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.SEARCH_BUTTON_PATH);
		return CNNAME;
}


public boolean isContentAvailableAndClick(String searchDocument) throws InterruptedException {
	
	boolean flag = false;

	
	GenericFunctionLibrary.refreshWindow();
	Thread.sleep(3000);
	// clear textBox
	GenericFunctionLibrary.clearTextBox("id", ObjectRepository.SEARCHBOX_ID);

	// input value in search text box
	GenericFunctionLibrary.setText("id", ObjectRepository.SEARCHBOX_ID,
			searchDocument);

	// clicking on search button
	GenericFunctionLibrary.clickOnElement("xpath",	ObjectRepository.SEARCH_BUTTON_PATH);

	Thread.sleep(5000);

	String text = GenericFunctionLibrary.getText("id",	ObjectRepository.VALIDATION_MESSAGE_ID);

	if (text.equalsIgnoreCase("No matches found")) {
		flag = false;
		log.info("No Search content found");
	} else {
		// storing search result value in 'actual search result'
		// variable

		GenericFunctionLibrary.selectMatchedFoundInList(ObjectRepository.SEARCH_RESULTS_XPATH, searchDocument);
		/*
		 * String actualSearchDocument = GenericFunctionLibrary.getText("xpath", ObjectRepository.SEARCH_RESULT);
		 * 
		 * String expectedSearchDocument = searchDocument;
		 * 
		 * // comparing search result value from expected value
		 * Assert.assertEquals(actualSearchDocument, expectedSearchDocument);
		 */
		flag = true;

		log.info("Search string found");

	}

	return flag;
}

public static void selectDomain(String domainName) throws InterruptedException {

	GenericFunctionLibrary.WaitForElementToBeClickable("xpath", ObjectRepository.DOMAIN_LIST, 5);
	GenericFunctionLibrary.selectIdentityFromList(ObjectRepository.DOMAIN_LIST, domainName);
}

	
}
