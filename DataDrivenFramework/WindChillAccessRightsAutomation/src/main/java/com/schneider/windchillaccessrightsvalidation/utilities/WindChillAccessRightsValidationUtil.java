package com.schneider.windchillaccessrightsvalidation.utilities;

import java.awt.AWTException;
import java.io.IOException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import com.schneider.windchillaccessrightsvalidation.genericlibraries.Authentication;
import com.schneider.windchillaccessrightsvalidation.genericlibraries.Config;
import com.schneider.windchillaccessrightsvalidation.genericlibraries.GenericFunctionLibrary;
import com.schneider.windchillaccessrightsvalidation.repositories.ContentRepository;
import com.schneider.windchillaccessrightsvalidation.repositories.ObjectRepository;

/**
 * @author SESA439753
 *
 */


public class WindChillAccessRightsValidationUtil extends GenericFunctionLibrary {

	private static Logger log = Logger.getLogger(WindChillAccessRightsValidationUtil.class);

	Authentication auth = new Authentication();
	
		
	public static void selectDomain(String domainName) throws InterruptedException {

		WaitForElementToBeClickable("xpath", ObjectRepository.DOMAIN_LIST, 5);
		GenericFunctionLibrary.selectIdentityFromList(ObjectRepository.DOMAIN_LIST, domainName);
	}

	// This method will select product container by taking the container name

	public static boolean selectContainer(String containerName) throws InterruptedException {
		boolean isSelected = false;
		 WaitForElementToBeClickable("xpath", ObjectRepository.PRODUCTS_LIST, 5);
		if(GenericFunctionLibrary.selectIdentityFromList(ObjectRepository.PRODUCTS_LIST, containerName)){
			isSelected = true;
		}else{
			log.info("Given "+ containerName + " is not available in the Product Containers list Mignt be not accessible");
		}
		return isSelected;
	}

	public static boolean selectSubFolders(String subfolderName) throws InterruptedException {
		boolean isSelected = false;
		 WaitForElementToBeClickable("xpath", ObjectRepository.SUBFOLDER_XPATH, 5);
		if(GenericFunctionLibrary.selectIdentityFromList(ObjectRepository.SUBFOLDER_XPATH, subfolderName)){
			isSelected = true;
		}else{
			log.info( subfolderName + " is not available in sub folders list ");
		}
		return isSelected;
	}

	// This method will select Library by taking the Library name

	public static boolean selectLibrary(String LibraryName) throws InterruptedException {
		boolean isSelected = false;
 
		WaitForElementToBeClickable("xpath", ObjectRepository.LIBRARIES_LIST, 5);
		if(GenericFunctionLibrary.selectMatchedFoundInList(ObjectRepository.LIBRARIES_LIST, LibraryName)){
			isSelected = true;
		}else{
			log.info("Given "+ LibraryName + " is not available in the Libraries list Might be not accessible");
		}
		return isSelected;
	}
	
	public static boolean selectWorkspace(String workspaceName) throws InterruptedException {
		boolean isSelected = false;
 
		WaitForElementToBeClickable("xpath", ObjectRepository.WORKSPACES_LIST, 5);
		if(GenericFunctionLibrary.selectMatchedFoundInList(ObjectRepository.WORKSPACES_LIST, workspaceName)){
			isSelected = true;
		}else{
			log.info("Given "+ workspaceName + " is not available in the Workspaces list Might be not accessible");
		}
		return isSelected;
	}
public static boolean selectCheckboxforRequiredUser(String ExpectedName) throws InterruptedException, AWTException{
	boolean isSelected = false;
	Thread.sleep(4000);
	//GenericFunctionLibrary.clickOnElement("xpath", "//button[contains(text(),'Actions')]/ancestor::td[@class='x-toolbar-cell']/preceding-sibling::td[2]//button");
	WaitForElementPresence("xpath", "//div[@class='x-grid3-body']/div//tbody/tr/td[4]/div/div[2]", 5);
	if(GenericFunctionLibrary.selectCheckBoxforMatchedStringInTable("//div[@class='x-grid3-body']/div//tbody/tr/td[4]/div/div[2]", ExpectedName)){
		isSelected = true;
	}else{
		log.info(ExpectedName + " is not available in list");
		GenericFunctionLibrary.scrollDown();
		Thread.sleep(4000);
		GenericFunctionLibrary.clickOnElement("xpath", "//button[contains(text(),'Actions')]/ancestor::td[@class='x-toolbar-cell']/preceding-sibling::td[2]//button");
		
		selectCheckboxforRequiredUser(ExpectedName);
	}
	
	return isSelected;
}
		
	
	public static boolean navigateToRequiredLocationNew(String Template, String Container_Library) throws InterruptedException {
		boolean executionFlag = false;

			// Clicking on Browser button

			clickOnElement("id", ObjectRepository.BROWSE_BUTTON_ID);

			if (Template.contains("Product")) {
				// Navigating to recent product list
				clickOnElement("xpath", ObjectRepository.PRODUCT_LIST_ICON);

				log.info("User Clicked on Products List Icon from Browse");

				// Navigating to viewAll section
				clickOnElement("linktext", "View All");

				executionFlag = true;
				log.info("user clicked on View ALL link");

				// Select Specified container
				selectContainer(Container_Library);

				// Selecting domains based on the Template Name
				executionFlag = true;

				if (Template.contains("Restricted")) {
					selectDomain(Config.RESTRICTED_DOMAIN);
					executionFlag = true;

				} else if (Template.contains("Confidential Area")) {
					selectDomain(Config.CONFIDENTIAL_DOMAIN);
					executionFlag = true;
				}

			} else if (Template.contains("Library")) {

				// Navigating to recent Libraris list
				clickOnElement("xpath", ObjectRepository.LIBRARY_LIST_ICON);
				log.info("User Clicked on libraries List Icon from Browse");
				// Navigating to viewAll section
				clickOnElement("linktext", "View All");

				executionFlag = true;
				log.info("User clicked on View ALL link");

				// Select Specified container
				selectLibrary(Container_Library);

				// Selecting domains based on the Template Name

				if (Template.contains("Restricted")) {
					selectDomain(Config.RESTRICTED_DOMAIN);

				} else if (Template.contains("Confidential Area")) {
					selectDomain(Config.CONFIDENTIAL_DOMAIN);
				}
			} else {
				log.info("Unknown Container or Library");
			}

		
		return executionFlag;
	}
	
	
	public static boolean navigateToRequiredLocation(String Template, String Container_Library) throws InterruptedException {
		boolean executionFlag = false;

			// Clicking on Browser button

			clickOnElement("id", ObjectRepository.BROWSE_BUTTON_ID);
			
			log.info(" User Clicked on Browse Button from the home page");

			if (Template.contains("Product")) {
				
				log.info(" Access Rights Excel sheet is having "+ Template );
				// Navigating to recent product list
				clickOnElement("xpath", ObjectRepository.PRODUCT_LIST_ICON);
				
				log.info(" User Clicked on 'PRODUCT LIST ICON' from the Browse page");
				
				// Navigating to viewAll section
				clickOnElement("linktext", "View All");
				log.info(" User Clicked on 'View ALL' link from the Browse page");
				
			//	executionFlag = true;
			log.info("user clicked on View ALL link");

				// Select Specified container
				if(selectContainer(Container_Library)){

				log.info("In Access Rights Excel sheet is having Container "+ Container_Library +" So navigated to " + Container_Library);
				
				
				// Selecting domains based on the Template Name
				executionFlag = true;

				 if (Template.contains("Restricted")) {
					selectDomain(Config.RESTRICTED_DOMAIN);
					
					
					log.info("In Access Rights Excel sheet Template is having 'Restricted Documents Area' So navigated to  'Restricted Documents Area' inside" + Container_Library);
					executionFlag = true;

				} else if (Template.contains("Confidential Area")) {
					selectDomain(Config.CONFIDENTIAL_DOMAIN);
					
					log.info("In Access Rights Excel sheet Template is having 'Confidential Documents Area' So navigated to  'Confidential Documents Area' inside" + Container_Library);
					executionFlag = true;
				}
				
				
				}else{
					
					log.info("given "+ Container_Library  +" is not available in the list");
				}
				

			} else if (Template.contains("Library")) {
				
				log.info(" Access Rights Excel sheet is having "+ Template );
				// Navigating to recent Libraris list
				
				clickOnElement("xpath", ObjectRepository.LIBRARY_LIST_ICON);
				log.info(" User Clicked on 'LIBRARY LIST ICON' from the Browse page");
				
				log.info("User Clicked on libraries List Icon from Browse");
				
				// Navigating to viewAll section
				clickOnElement("linktext", "View All");
				
				log.info(" User Clicked on 'View ALL' link from the Browse page");
				
				//executionFlag = true;
				
				log.info("user clicked on View ALL link");

				// Select Specified container
				if(selectLibrary(Container_Library)){

				log.info("In Access Rights Excel sheet is having Container "+ Container_Library +" So navigated to " + Container_Library);
				// Selecting domains based on the Template Name
				executionFlag = true;
				if (Template.contains("Restricted")) {
					selectDomain(Config.RESTRICTED_DOMAIN);
					log.info("In Access Rights Excel sheet Template is having 'Restricted Documents Area' So navigated to  'Restricted Documents Area' inside" + Container_Library);
					executionFlag = true;
				} else if (Template.contains("Confidential Area")) {
					selectDomain(Config.CONFIDENTIAL_DOMAIN);
					log.info("In Access Rights Excel sheet Template is having 'Confidential Documents Area' So navigated to  'Confidential Documents Area' inside" + Container_Library);
					executionFlag = true;
				}
				}else{
					
					log.info("given "+ Container_Library  +" is not available in the list");
				}
			} else {
				log.info("Unknown Container or Library");
				log.fatal("Unknown Container or Library So Stoping Execution");
			}

		
		return executionFlag;
	}
	
	public static boolean navigateToSubfoldersfromRecentContext(String Template, String Container_Library, String subFolderName) throws InterruptedException {
		boolean executionFlag = false;

			// Clicking on Browser button

			clickOnElement("id", ObjectRepository.BROWSE_BUTTON_ID);
			
			log.info(" User Clicked on Browse Button from the home page");

			if (Template.contains("Product")) {
				
				log.info(" Access Rights Excel sheet is having "+ Template );
				// Navigating to recent product list
				clickOnElement("xpath", ObjectRepository.PRODUCT_LIST_ICON);
				
				log.info(" User Clicked on 'PRODUCT LIST ICON' from the Browse page");
				
				// Clicking on context in recent products list
				if(GenericFunctionLibrary.isMatchFoundInList(ObjectRepository.PRODUCTS_LIST_XPATH, Container_Library)){
				clickOnElement("linktext", Container_Library);
				log.info(" User Clicked on "+ Container_Library);
				} else{
					log.info(Container_Library + " is not available in recent product list");
				}
				// Select Specified container
				if(selectSubFolders(subFolderName)){

				log.info(subFolderName+ " is available and navigated to "+ subFolderName);
				executionFlag = true;
				
								
				}else{
					
					log.info(subFolderName+ "is not available in the sub folders");
				}
				

			} else if (Template.contains("Library")) {
				
				log.info(" Access Rights Excel sheet is having "+ Template );
				// Navigating to recent Libraris list
				
				clickOnElement("xpath", ObjectRepository.LIBRARY_LIST_ICON);
				log.info(" User Clicked on 'LIBRARY LIST ICON' from the Browse page");
				
				if(GenericFunctionLibrary.isMatchFoundInList(ObjectRepository.LIBRARIES_LIST_XPATH, Container_Library)){
					clickOnElement("linktext", Container_Library);
					log.info(" User Clicked on "+ Container_Library);
					
					} else{
						log.info(Container_Library + " is not available in recent product list");
					}
				// Select Specified subfolder
				if(selectSubFolders(subFolderName)){

				log.info(subFolderName+ " is available and navigated to "+ subFolderName);
				
				executionFlag = true;
								
				}else{
					
					log.info(subFolderName +" is not available in the sub folders");
				}
			} else {
				log.info("Unknown Container or Library");
				log.fatal("Unknown Container or Library So Stoping Execution");
			}

		
		return executionFlag;
	}

	public static boolean navigateToWorkSpace(String Template, String Container_Library, String subFolderName,String workspaceName) throws InterruptedException{
		boolean isSelected = false;
		if(navigateToSubfoldersfromRecentContext(Template, Container_Library, subFolderName)){
			if(selectWorkspace(workspaceName)){
				isSelected =true;
			}else{
				log.info(workspaceName+" is not available in workspace list");
			}
		}else{
			log.info(subFolderName + " is not available");
		}

		
		return isSelected;
	}
	public boolean navigateToAllProducts() throws InterruptedException {
		
		boolean executionFlag = false;

		try {
			// Clicking on Browser button

			clickOnElement("id", ObjectRepository.BROWSE_BUTTON_ID);

			// Navigating to recent product list

			clickOnElement("xpath", ObjectRepository.PRODUCT_LIST_ICON);

			// Navigating to viewAll section
			clickOnElement("linktext", "View All");

			// driver.findElement(By.linkText("View All")).click();

			executionFlag = true;
			log.info("user clicked on View ALL link");

		} catch (NoSuchElementException e) {
			//e.printStackTrace();

		}
		return executionFlag;
	}

	public boolean navigateToAllLibraries() throws InterruptedException {

		boolean executionFlag = false;

		// Clicking on Browser button

		clickOnElement("id", ObjectRepository.BROWSE_BUTTON_ID);

		// Navigating to recent Libraris list

		clickOnElement("xpath", ObjectRepository.LIBRARY_LIST_ICON);

		// Navigating to viewAll section
		clickOnElement("linktext", "View All");

		// driver.findElement(By.linkText("View All")).click();

		executionFlag = true;
		log.info("user clicked on View ALL link");

		return executionFlag;
	}
	
	public static boolean globalSearch(String ExpectedDocument) throws InterruptedException{
		boolean isFound=true;
		
		String RECORD_Number="";
	GenericFunctionLibrary.setText("id", "gloabalSearchField", ExpectedDocument);
	Thread.sleep(3000);
	GenericFunctionLibrary.clickOnElement("xpath", "//div[@id='globalSearch']//span/img[@class='x-form-trigger global-search-trigger']");
	Thread.sleep(3000);
	
	if(GenericFunctionLibrary.isElementPresent(ObjectRepository.ADV_SEARCH_LIST)){
			
		//Get the document number from the list
		RECORD_Number=GenericFunctionLibrary.getText("xpath", ObjectRepository.ADV_SEARCH_LIST);
	 log.info("Record NUMBER is : "+RECORD_Number);
	 GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.ADV_SEARCH_LIST);
	 isFound = true;
	}else {
		isFound = false;
		log.info("Content is not available in search");
	}
	return isFound;
	}
	

	public boolean isContentAvailableAndClick(String searchDocument) throws InterruptedException {
		boolean flag = false;
		log.info("Searching for given file "+searchDocument);
		
		refreshWindow();
		Thread.sleep(6000);
		// clear textBox
		
		GenericFunctionLibrary.clearTextBox("xpath", ObjectRepository.SEARCH_BOX_PATH);
		
		log.info("Clearing the Search feild before start searching");
		
		Thread.sleep(5000);
		// input value in search text box
		GenericFunctionLibrary.setText("xpath", ObjectRepository.SEARCH_BOX_PATH, searchDocument);
		
		log.info("Entered file name in Search box");
		
		Thread.sleep(3000);
		// clicking on search button
		if(GenericFunctionLibrary.isElementPresent(ObjectRepository.SEARCH_BUTTON_PATH)){
		GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.SEARCH_BUTTON_PATH);
		
		log.info("Clicked on Search Button after Entered file name in Search box");
		
		log.info("clicked on Search Button");
		Thread.sleep(5000);

		String text = GenericFunctionLibrary.getText("id", ObjectRepository.VALIDATION_MESSAGE_ID);
		if(text.equalsIgnoreCase("Searching...")){
			Thread.sleep(10000);
		}
		if (text.equalsIgnoreCase("No matches found")) {
			flag = false;
			log.info("No Search content found");
			
			log.warn("Showing 'No matches found' No Content available with the name "+ searchDocument);
		} else {
			// storing search result value in 'actual search result'
			// variable
			if(GenericFunctionLibrary.isMatchFoundInList(ObjectRepository.SEARCH_RESULTS_XPATH, searchDocument)){
				GenericFunctionLibrary.selectMatchedFoundInList(ObjectRepository.SEARCH_RESULTS_XPATH, searchDocument);
				flag = true;
				log.info("Search string found");
				log.info(searchDocument +" is Available and Clicked on it");
				
				}else{
					flag = false;
				}

		}
		
		}
		return flag;
	}
	public boolean isFolderAvailable(String searchDocument) throws InterruptedException {
		boolean flag = false;
		log.info("Searching for given file "+searchDocument);
		//refreshWindow();
		Thread.sleep(4000);
		// clear textBox
		GenericFunctionLibrary.clearTextBox("id", ObjectRepository.SEARCHBOX_ID);
		log.info("Clearing the Search feild before start searching");
		Thread.sleep(4000);
		// input value in search text box
		GenericFunctionLibrary.setText("id", ObjectRepository.SEARCHBOX_ID, searchDocument);
		log.info("Entered file name in Search box");
		Thread.sleep(3000);
		
		// clicking on search button
		GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.SEARCH_BUTTON_PATH);
		log.info("Clicked on Search Button after Entered file name in Search box");
		Thread.sleep(5000);

		String text = GenericFunctionLibrary.getText("id", ObjectRepository.VALIDATION_MESSAGE_ID);
		log.info("Search results text is : "+ text);
		if(text.equalsIgnoreCase("Searching...")){
			Thread.sleep(10000);
		}
		if (text.equalsIgnoreCase("No matches found")) {
			flag = false;
			log.info("No Search content found");
			
			log.warn("Showing 'No matches found' No Content available with the name "+ searchDocument);
			
		} else {
			
							
				if(GenericFunctionLibrary.isElementPresent(ObjectRepository.SEARCH_RESULTS_XPATH)){
			flag = true;
			//log.info("Search string found");
			}else{
				flag = false;
			}
		}

		return flag;
	}
	
	public boolean isContentAvailable(String searchDocument) throws InterruptedException {
		boolean flag = false;
		log.info("Searching for given file "+searchDocument);
		//refreshWindow();
		GenericFunctionLibrary.WaitForElementPresence("id", ObjectRepository.SEARCHBOX_ID, 5);
		// clear textBox
		GenericFunctionLibrary.clearTextBox("id", ObjectRepository.SEARCHBOX_ID);
		log.info("Clearing the Search feild before start searching");
		Thread.sleep(4000);
		// input value in search text box
		GenericFunctionLibrary.setText("id", ObjectRepository.SEARCHBOX_ID, searchDocument);
		log.info("Entered file name in Search box");
		Thread.sleep(3000);
		
		// clicking on search button
		GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.SEARCH_BUTTON_PATH);
		log.info("Clicked on Search Button after Entered file name in Search box");
		Thread.sleep(5000);

		String text = GenericFunctionLibrary.getText("id", ObjectRepository.VALIDATION_MESSAGE_ID);
		log.info("Search results text is : "+ text);
		if(text.equalsIgnoreCase("Searching...")){
			Thread.sleep(10000);
		}
		if (text.equalsIgnoreCase("No matches found")) {
			flag = false;
			log.info("No Search content found");
			
			log.warn("Showing 'No matches found' No Content available with the name "+ searchDocument);
			
		} else {
							
				if(GenericFunctionLibrary.isMatchFoundInList(ObjectRepository.SEARCH_RESULTS_XPATH, searchDocument)){
			flag = true;
		log.info("Search string found");
			}else{
				flag = false;
			}
		}

		return flag;
	}
	
	// below method is same as '' method but we have used 'xpath' inplace of
		// 'id' because in part(engineering etc), ID locator is not working

		public boolean isContentAvailableXpath(String searchDocument)throws InterruptedException {
			
			boolean flag = false;
			refreshWindow();
			Thread.sleep(5000);
			// clear textBox
			GenericFunctionLibrary.clearTextBox("xpath", ObjectRepository.SEARCH_BOX_PATH);
			Thread.sleep(5000);
			// input value in search text box
			GenericFunctionLibrary.setText("xpath", ObjectRepository.SEARCH_BOX_PATH, searchDocument);
			
			// clicking on search button
			GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.SEARCH_BUTTON_PATH);

			Thread.sleep(5000);

			String text = GenericFunctionLibrary.getText("xpath",
					ObjectRepository.VALIDATION_MESSAGE);
			if(text.equalsIgnoreCase("Searching...")){
				Thread.sleep(10000);
			}
			if (text.equalsIgnoreCase("No matches found")) {
				flag = false;
				log.info("No Search content found");
			} else {
				
				
				if(GenericFunctionLibrary.isMatchFoundInList(ObjectRepository.SEARCH_RESULTS_XPATH, searchDocument)){
				flag = true;
				//log.info("Search string found");

			}else{
				flag = false;
			}
			}

			return flag;
		}
		
		public boolean verifyAndClickOptionFromActionsDropDown(String DropDowmOption) throws InterruptedException {
			boolean option = false;
			Thread.sleep(3000);
			GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ACTION_BUTTON, 10);
			GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ACTION_BUTTON);
			Thread.sleep(3000);
			// matching the value from the list and clicking on that link if found
			GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ACTION_MENU_OPTIONS, 10);
			if(GenericFunctionLibrary.selectMatchedFoundInList(ObjectRepository.ACTION_MENU_OPTIONS, DropDowmOption)){
				
				option = true;
				log.info("User Clicked on" + DropDowmOption + " from the dropdown ");
			}else{
				log.info( DropDowmOption + " is NOT available inside the dropdown ");
			}
			return option;
		}
		
		public boolean verifyOptionFromActionsDropDown(String DropDowmOption) throws InterruptedException {
			boolean option = false;
			GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ACTION_BUTTON, 10);
			GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ACTION_BUTTON);
			
			// matching the value from the list and clicking on that link if found
			GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ACTION_MENU_OPTIONS, 10);
			if(GenericFunctionLibrary.isMatchFoundInList(ObjectRepository.ACTION_MENU_OPTIONS, DropDowmOption)){
				option = true;
				log.info( DropDowmOption + " is Available inside the dropdown ");
			
			}else{
				log.info( DropDowmOption + "is NOT available inside the dropdown ");
			}
			
			return option;
		}
		
		

	// method for selecting value from 'Actions' tab

	public static boolean selectOptionFromActionsDropDown(String DropDowmOption) throws InterruptedException {
		boolean isSelected = false;
		
		GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ACTION_MENU_BUTTON);
		// matching the value from the list and clicking on that link if found
		if(GenericFunctionLibrary.selectIdentityFromList(ObjectRepository.ACTION_MENU_OPTIONS, DropDowmOption)){
			isSelected = true;
			log.info("User Clicked on" + DropDowmOption + " from the dropdown ");
		}else{
			log.info( DropDowmOption + "is NOT available inside the dropdown ");
			
		}

		return isSelected;
	}

	public String[] createDocumentAccessRightValidation(String objectType, String objectName) throws IOException, InterruptedException {
		
		String result[] = new String[2];  
		result[0] = "";
		result[1] = "";
		
		Thread.sleep(5000);
		String parentWindow ="";
		parentWindow = GenericFunctionLibrary.switchToChildWindow(ObjectRepository.CREATE_NEW_DOCUMENT_BUTTON_XPATH);
		log.info(GenericFunctionLibrary.getPageTitle());
	
		Thread.sleep(5000);
		String expetedAlertMessage = "ATTENTION";
		   
		   if(GenericFunctionLibrary.isAleretPresent(3)){
			   log.info("Alert is Present");
			   
			   String ActualErrormessage = GenericFunctionLibrary.getAlert();
			   
			   if (ActualErrormessage.contains(expetedAlertMessage)) {  
				   log.info(" Alert Message is : " + ActualErrormessage);
				   result[0] = "Un-Authorized Successful";
					  String  Comment = "Throwing an alert "+ ActualErrormessage +" hence user is not Authorized to create "+ objectType;
					    result[1] =  Comment;
					    
					    GenericFunctionLibrary.switchToWindow(parentWindow);  
				   }
				
		}else{
	
			if(GenericFunctionLibrary.isMatchFoundInColumn(ObjectRepository.ELEMENT_DROPDOWN_ID, objectType)){
				
		// Selecting content source from drop down
		Thread.sleep(3000);
		GenericFunctionLibrary.selectDropdownByVisibleText("id", ObjectRepository.ELEMENT_DROPDOWN_ID, objectType);
		
		GenericFunctionLibrary.selectDropdownByVisibleText("id", ObjectRepository.SELECT_CONTENT_SOURCE_ID,
				ContentRepository.primaryContentSource);

		if(GenericFunctionLibrary.uploadFile(ObjectRepository.FILE_UPLOAD_BUTTON,Config.AUTOIT_SCRIPTS_PATH)){
			log.info(Config.AUTOIT_SCRIPTS_PATH +" executed successfully");
			
			Thread.sleep(4000);
		}else{
			log.warn("Upload Filed for Creation of "+objectType);
		}
		
		/*GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.FILE_UPLOAD_BUTTON);

		// calling autoIT script to attach the document file
		Runtime.getRuntime().exec(ContentRepository.AUTOIT_SCRIPTS_PATH);
*/
		Thread.sleep(5000);
		GenericFunctionLibrary.clearTextBox("xpath", ObjectRepository.FILE_NAME_FILED_XPATH);
		Thread.sleep(3000);
		GenericFunctionLibrary.setText("xpath", ObjectRepository.FILE_NAME_FILED_XPATH, objectName + System.currentTimeMillis());

		String actualDocumentName = GenericFunctionLibrary.getAttributeValue("xpath",
				ObjectRepository.FILE_NAME_FILED_XPATH, "value");

		GenericFunctionLibrary.selectDropdownByIndex("id", ObjectRepository.DOCUMENTTYPE_DROPDOWN_ID, 1);
		Thread.sleep(3000);
		// Clicking on Finish button
		GenericFunctionLibrary.clickOnElement("id", ObjectRepository.FINISH_BUTTON_ID);
		Thread.sleep(3000);

		
		GenericFunctionLibrary.switchToWindow(parentWindow);
		Thread.sleep(3000);
		if (isContentAvailable(actualDocumentName)) {
			log.info("content available in search");
			String Comment = objectType+" is Created Successfully and the dataset number is : "+ actualDocumentName;
			 result[1] =  Comment;
			
			result[0] = "Authorized Successful";

		} else {
			log.info("Content is not available in search");
		
		}

		}else{
			
			String Comment = objectType+" option is Not available in the list";
			result[1] =  Comment;
			log.info(Comment);
							
			// clicking on 'Finish' button to create content
			GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CREATEWINDOW_CANCEL_BUTTON_XPATH);
			
			log.info("Clicked on Cancel button in create window");
			
			result[0] = "Un-Authorized Successful";
			// Moving on parent window
			GenericFunctionLibrary.switchToWindow(parentWindow);

			Thread.sleep(5000);
		}
			
		}
		
		return result;

	}



	public String[] reviseAccessRightValidation() throws InterruptedException {
	
	String[] result = new String[2];
	
	// Entering text in Search Field and Click on search button
		
			String fileName = GenericFunctionLibrary.getFileName(ObjectRepository.VERSION_NUMBER_FILENAME);
	
			Thread.sleep(3000);
			String versionBeforeRevise = GenericFunctionLibrary.getVersion(ObjectRepository.VERSION_NUMBER_FILENAME);
			
			log.info("Vesrion before revise : " + versionBeforeRevise);
			
			log.info("Vesrion before revise : " + versionBeforeRevise);
			
			if(verifyAndClickOptionFromActionsDropDown("Revise")){
				log.info("User clicked on Revise Option and navigate to Revise page");
			
				GenericFunctionLibrary.SwitchToFrame("lbContentIframe");
				
				Thread.sleep(5000);
				
				GenericFunctionLibrary.WaitForElementToBeClickable("xpath", ObjectRepository.OK_BUTTON, 5);

				GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.OK_BUTTON);
				log.info("user clicked on Ok button in revise screen");
				
				/*GenericFunctionLibrary.WaitForElementToBeClickable("xapth", ObjectRepository.SEARCH_BUTTON_PATH, 5);

				GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.SEARCH_BUTTON_PATH);
				Thread.sleep(5000);*/

				String versionAfterRevise = GenericFunctionLibrary.getVersion(ObjectRepository.VERSION_NUMBER_FILENAME);
				log.info("Vesrion After revise : " +versionAfterRevise);
				log.info(versionAfterRevise);
				
				if(!versionAfterRevise.equals(versionBeforeRevise)){
				
				log.info("versionBeforeRevise and versionAfterRevise are not equal So User is able to Revise ");
				
				log.info("   User is able to Revise Properly");
		
				result[0] = "Authorized Successful";
				log.info("Execution status is : "+result[0]);
				
				String comment = "Revise done on dataset number : "+fileName+". Revise option is Available in side the Action Menu and version gets changed from "+versionBeforeRevise+" to "+versionAfterRevise;
				
				log.info(comment);				
				result[1] = comment;
				
}
			//	GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.CONTAINER_LANDING_PAGE);
				
				Thread.sleep(3000);
				
			} else {
				String comment = " Revise done on dataset number : "+fileName+". Revise option is not Available in side the Action Menu Hence is User is not Authorized to Revise";
				log.info(comment);
				result[1] = comment;
				//GenericFunctionLibrary.refreshWindow();
				Thread.sleep(3000);
				
			//	GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.CONTAINER_LANDING_PAGE);
				Thread.sleep(3000);
								
				result[0] = "Un-Authorized Successful";
				log.info("Execution status is : "+result[0].toString());
											
			}

	return result;
	
	

}
	public String[] reviseAccessRightValidationforCAD() throws InterruptedException {
		
		String[] result = new String[2];
		
		// Entering text in Search Field and Click on search button
			
		String fileName = GenericFunctionLibrary.getFileName(ObjectRepository.VERSION_NUMBER_FILENAME);
				
				Thread.sleep(3000);
				String versionBeforeRevise = GenericFunctionLibrary.getFileName(ObjectRepository.VERSION_NUMBER_FILENAME);
				
				log.info("Vesrion before revise : " + versionBeforeRevise);
				
				if(verifyAndClickOptionFromActionsDropDown("Revise")){
					log.info("User clicked on Revise Option and navigate to Revise page");
				
					GenericFunctionLibrary.SwitchToFrame("lbContentIframe");
					
					Thread.sleep(5000);
					
					GenericFunctionLibrary.WaitForElementToBeClickable("xpath", ObjectRepository.OK_BUTTON, 5);

					GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.OK_BUTTON);
					log.info("user clicked on Ok button in revise screen");
					
					/*GenericFunctionLibrary.WaitForElementToBeClickable("xapth", ObjectRepository.SEARCH_BUTTON_PATH, 5);

					GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.SEARCH_BUTTON_PATH);
					Thread.sleep(5000);*/

					String versionAfterRevise = GenericFunctionLibrary.getFileName(ObjectRepository.VERSION_NUMBER_FILENAME);
					log.info("Vesrion After revise : " +versionAfterRevise);
					log.info(versionAfterRevise);

					if(!versionAfterRevise.equals(versionBeforeRevise)){
					String comment = "Revise is done on "+fileName+" Revise option is Available in side the Action Menu and version gets changed from "+versionBeforeRevise+" to "+versionAfterRevise;
					log.info(comment);
					
					result[0] = "Authorized Successful";
					result[1] = comment;
					log.info("Execution status is : "+ result[0]);
						
					}
				} else {
					String comment = "Revise is done on "+fileName+" Revise option is not Available in side the Action Menu Hence is User is not Authorized to Revise";
					log.info(comment);
					result[0] = "Un-Authorized Successful";
					log.info("Execution status is : "+result[0]);
								
				}
			
		return result;
		
		

	}

	public String readAccessRightValidation(String fileName) throws InterruptedException {
		
		// Read document and click on expected search content
		
		String StatusMessage = null;
		if (isContentAvailable(fileName)) {
			log.info(fileName+" is available hence User is Authorized to Read");
			StatusMessage = "Authorized Successful";
			log.info("   User is Authorized to Read Access");
		
		} else {
			log.info(fileName+" is NOT available hence User is NOT Authorized to Read");
			StatusMessage = "Un-Authorized Successful";
			log.info("   Either Content is NOT Availble inside the Container OR User is UnAuthorized to Read Access");
			
		
		}
		return StatusMessage;
	}

	public String[] modifyAccessRightValidation(String fileName) throws InterruptedException {

		String result[] = new String[2];  
		result[0] = "";
		result[1] = "";

		//Fetching FileName to perform modify action
		
		String fileNameBeforeModify = GenericFunctionLibrary.getFileName(ObjectRepository.VERSION_NUMBER_FILENAME);
		Thread.sleep(2000);

			// clicking on 'actions' button
		//	GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.ACTION_BUTTON);
		//	Thread.sleep(2000);
			//if(GenericFunctionLibrary.isElementPresent(ObjectRepository.ACTION_OPTION)){
			if(verifyAndClickOptionFromActionsDropDown("Rename")){
				log.info("Rename option is available inside Action DropDown");
				String parentWindow = GenericFunctionLibrary.switchToChildWindow();
				
				
			// navigating to 'rename' window
			/*String parentWindow = GenericFunctionLibrary.switchToChildWindow(ObjectRepository.ACTION_OPTION);
			Thread.sleep(3000);*/
			log.info("user clicked on Rename option and navigated to modify screen");
			log.info("User Navigated to : "+GenericFunctionLibrary.getPageTitle()+" Page ");
			GenericFunctionLibrary.setText("xpath",ObjectRepository.DOCUMENT_MODIFY_SEARCH_FIELD , "Name");
		
			Thread.sleep(3000);
			GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.DOCUMENT_MODIFY_SEARCH_BUTTON);
			
			Thread.sleep(3000);
			GenericFunctionLibrary.WaitForElementToBeVisible("xpath", ObjectRepository.DOCUMENT_RENAME_INPUT_TEXT_PATH, 30);
			// getting existing content name in the variable
		//	String currentContentName = GenericFunctionLibrary.getAttributeValue("xPath",ObjectRepository.DOCUMENT_RENAME_INPUT_TEXT_PATH,"value");

			GenericFunctionLibrary.clearTextBox("xPath",ObjectRepository.DOCUMENT_RENAME_INPUT_TEXT_PATH);

			// insert new value
			GenericFunctionLibrary.setText("xPath",ObjectRepository.DOCUMENT_RENAME_INPUT_TEXT_PATH,fileName+"Updt"+System.currentTimeMillis());
			Thread.sleep(3000);
		//	String modifiedContentName = GenericFunctionLibrary.getAttributeValue("xPath",ObjectRepository.DOCUMENT_RENAME_INPUT_TEXT_PATH,"value");

			// clicking on 'ok' button
			GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.MODIFY_OK_BUTTON);
			log.info("Clicked on OK Button in Modify Screen");
			Thread.sleep(4000);
			
		
			GenericFunctionLibrary.switchToWindow(parentWindow);
			
			String fileNameAfterModify = GenericFunctionLibrary.getFileName(ObjectRepository.VERSION_NUMBER_FILENAME);
			
			if(!fileNameAfterModify.equals(fileNameBeforeModify)){
				String comment ="Modify is done on"+ fileNameBeforeModify+" File name is changed after modify hence the user is authorized. file changed from "+fileNameBeforeModify+" to "+ fileNameAfterModify;
				log.info(comment);
				result[0] = "Authorized Successful";
				result[1] = comment;	
			}else if(fileNameAfterModify.equals(fileNameBeforeModify)) {
				String comment = "Modify is done on "+fileNameBeforeModify+" File name is not changed,  File name is same before and after modification. file name before modify is "+ fileNameBeforeModify+" after modify "+ fileNameAfterModify;
				log.info(comment);
				result[0] ="Un-Authorized Successful";
				result[1] = comment;
			}
			
			}else {
				String comment ="Modify is done on "+fileNameBeforeModify+". Rename option is not Available in side the Action Menu Hence is User is not Authorized to Rename";
				log.info(comment);
				
				Thread.sleep(3000);
				
			//	GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.CONTAINER_LANDING_PAGE);
				Thread.sleep(3000);
								
				result[0] = "Un-Authorized Successful";
				log.info("Execution status is : "+ result[0]);
				
			}

		return result;	
		
	}

	public String[] modifyIdentityAccessRightValidation() throws InterruptedException {
		String result[] = new String[2];  
		result[0] = "";
		result[1] = "";
		
		Thread.sleep(6000);

		String actualFileName = GenericFunctionLibrary.getFileName(ObjectRepository.VERSION_NUMBER_FILENAME);
		
		log.info("File Name before modify :" + actualFileName);

		// clicking on 'actions' button
		GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.ACTION_BUTTON);
		Thread.sleep(2000);
		log.info("user clicked on action button");
		
		
		//if(GenericFunctionLibrary.isElementPresent(ObjectRepository.ACTION_OPTION)){
		if(verifyAndClickOptionFromActionsDropDown("Rename")){
			log.info("Rename option is available inside Action DropDown");

		// navigating to 'rename' window
		String parentWindow = GenericFunctionLibrary.switchToChildWindow(ObjectRepository.ACTION_OPTION);
		log.info("user clicked on Rename option and navigated to modify screen");
		
		//verifying the textbox is enabled or disabled
		if(GenericFunctionLibrary.isElementPresent(ObjectRepository.DOCUMENT_NUMBER_PATH)){
		log.info("Document Number field is Editable");
			String ActualdocumentNumber = GenericFunctionLibrary.getAttributeValue("xpath", ObjectRepository.DOCUMENT_NUMBER_PATH, "value");
			 log.info("Previous Document number is :" + ActualdocumentNumber);
			
			GenericFunctionLibrary.clearTextBox("xpath",ObjectRepository.DOCUMENT_NUMBER_PATH);

		// insert new number value

		// insert new value
		GenericFunctionLibrary.setText("xpath",ObjectRepository.DOCUMENT_NUMBER_PATH, "D" + System.currentTimeMillis());

		String UpdateddocumentNumber = GenericFunctionLibrary.getAttributeValue("xpath",ObjectRepository.DOCUMENT_NUMBER_PATH, "value");

		/*
		 * log.info("Updated Document number is :" +
		 * UpdateddocumentNumber);
		 */

		GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.MODIFY_OK_BUTTON);
		
		log.info("Clicked on OK button in Modify Identity Screen");

		// compare modified content number with insert document
		// number(UpdateddocumentNumber)

		GenericFunctionLibrary.switchToWindow(parentWindow);

		Thread.sleep(3000);
		String modifiedContentNumber = GenericFunctionLibrary.getText("xpath",ObjectRepository.MODIFIED_CONTENT_NUMBER);
		
		
		/*
		 * log.info("Modified content number :" +
		 * modifiedContentNumber);
		 */

				if (modifiedContentNumber.equalsIgnoreCase(UpdateddocumentNumber)) {
						log.info("   Logged-in user is authorized to update content number");
						String comment = "Modify Identity is done on "+ actualFileName +". New number textbox is enabled and Document Number is updated successfully and the new document number is : "+UpdateddocumentNumber+" hence user authorized to Modify Identity";
						
						result[0] = "Authorized Successful";
						result[1] = comment;
						log.info("both modifiedContentNumber  and UpdateddocumentNumber are equal hence user is authorized to Modify Identity");
					} 
						
			}else{
				String comment = "Modify Identity is done on "+ actualFileName +". New number textbox is disabled and loggedin user can not change the content number";
				log.info(comment);
				
				result[0] = "Un-Authorized Successful";
				result[1] = comment;

				GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.MODIFY_OK_BUTTON);
				GenericFunctionLibrary.switchToWindow(parentWindow);
				
				Thread.sleep(5000);
			//	GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CONTAINER_LANDING_PAGE);
				
			}
			
		}else{
			
			String comment = "Modify Identity is done on "+ actualFileName +". Rename Option is NOT Available hence Logged-in user don't have modify permission ";
			log.info(comment);
			
			
			result[0] = "Un-Authorized Successful";
			result[1] = comment;
			
		}
	
		return result;
	}

	public String[] modifyContentAccessRightValidation()   {
		String[] result = new String[2];

try{
	Thread.sleep(3000);
		// get the current version and store in variable
	
	String actualFileName = GenericFunctionLibrary.getFileName(ObjectRepository.VERSION_NUMBER_FILENAME);
		
		String documentVersionBeforeCheckIN = GenericFunctionLibrary.getVersion(ObjectRepository.VERSION_NUMBER_FILENAME);
		log.info("version before check-in : "+ documentVersionBeforeCheckIN);
		// goto content detail page, click on actions

		Thread.sleep(6000);

		// clicking on 'actions' button
		GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.ACTION_BUTTON);
		Thread.sleep(3000);
		log.info("User clicked on Action Button");
		String expectedLinkColor = "rgba(128, 128, 128, 1)";
		String actualLinkColor = GenericFunctionLibrary.getCSSValue(ObjectRepository.ACTION_OPTION_CHECK_OUT);
		if (actualLinkColor.equals(expectedLinkColor)) {
			String comment = "CheckOut option is grayout hence user is not Authorized to Modify Content";
		log.info(comment);	
		
			//GenericFunctionLibrary.refreshWindow();
			log.info("   User is un-authorzied to perform modify content action");
			result[0] = "Un-Authorized Successful";
			result[1]=comment;
		//	GenericFunctionLibrary.refreshWindow();
			Thread.sleep(4000);
			
		//	GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.CONTAINER_LANDING_PAGE);
		} else {
			//log.info("Fail : Checkout link color not matched with expected condition");
		
		
		
		// clicking on check-out option
		GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.ACTION_OPTION_CHECK_OUT);
		log.info("user clicked on Checkout option from the action dropdown");
		Thread.sleep(5000);
		
		//  Upload the file after checkin
		
		
		
		// clicking on action tab to perform check-in activity
		GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.DOCUMENT_ACTION_BUTTON_PATH);
		
		// switch to child window and clicking on check-in option
		String parentWindow = GenericFunctionLibrary.switchToChildWindow(ObjectRepository.ACTION_OPTION_CHECK_IN);
		log.info("user clicked on Checkin option from the action dropdown");
		Thread.sleep(3000);
		
		if(GenericFunctionLibrary.uploadFile(ObjectRepository.FILE_UPLOAD_BUTTON, Config.AUTOIT_SCRIPTS_PATH)){
			Thread.sleep(5000);
			log.info(Config.AUTOIT_SCRIPTS_PATH +" File Uploaded successfully");
		}else{
			log.warn("Upload Failed for Creation of Object");
			
		}
		// click on ok button
		GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.MODIFY_OK_BUTTON);
		log.info("User clicked on OK Button");
		// goto parent window
		GenericFunctionLibrary.switchToWindow(parentWindow);

		Thread.sleep(3000);

		// get the updated version and store in variable
		String documentVersionAfterCheckIN = GenericFunctionLibrary.getVersion("infoPageIdentityObjectIdentifier");
		log.info("version after check-in : " + documentVersionAfterCheckIN);

		if (documentVersionAfterCheckIN.equals(documentVersionBeforeCheckIN)) {
			String comment = "Modify content is done on "+ actualFileName +"No Change observed in Document Version after Checkout and Checkin hence user is NOT authorized to Modify Content. version before check-in " + documentVersionBeforeCheckIN+ "version after check-in :" + documentVersionAfterCheckIN;
			log.info(comment);
			log.info(" Document version not changed after check-in");
			result[0] = "Un-Authorized Successful";
			result[1] = comment;
			
		} else {
			String comment = "Modify content is done on "+ actualFileName +"Document version changed after check-in and user is authorized to perform modify content action. version before check-in " + documentVersionBeforeCheckIN+ "version after check-in : " + documentVersionAfterCheckIN ;
			log.info(comment);
			result[0] = "Authorized Successful";
			result[1] = comment;
			Thread.sleep(3000);
		}
		}
}catch(Exception e){
	
	log.info(e);
	
}
		return result;

	}
		
	

	public String[] downloadAccessRightValidation() throws InterruptedException {

		String[] result = new String[2];
		
		Thread.sleep(3000);
		
		String actualFileName = GenericFunctionLibrary.getFileName(ObjectRepository.VERSION_NUMBER_FILENAME);
		
			// clicking on content menu
			GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CONTENT_MENU);
			log.info("user is navigated to content tab");
			
			Thread.sleep(2000);
			// verifying whether file is clickable or not
			if (GenericFunctionLibrary.isElementPresent(ObjectRepository.DOWNLOAD_FILE_LOCATION_XPATH)) {

				String comment = " Dowload option is clickable and Logged-in user is able to download content";
				log.info(comment);
				result[0] = "Authorized Successful";
				result[1] = comment;				

				Thread.sleep(3000);				
				}else{
					String comment = "Download is done on"+ actualFileName +" Dowload option is NOT clickable and logged-in user is not Authorized to download content";
					log.info(comment);
					result[0] = "Un-Authorized Successful";
					result[1] = comment;
			}
			
			GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.DETAILS_MENU);

		return result;
	}
	

	public String[] deleteAccessRightValidation() throws InterruptedException{
		String[] result = new String[2];
		
		// Verify content is available or Not
		Thread.sleep(3000);
		
		String fileName = GenericFunctionLibrary.getFileName(ObjectRepository.VERSION_NUMBER_FILENAME);
		Thread.sleep(2000);
		
			// clicking on 'actions' button
			GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ACTION_BUTTON);
			Thread.sleep(3000);
			log.info("User Clicked on Action Button");
			String expectedLinkColor = "rgba(128, 128, 128, 1)";
			String actualLinkColor = GenericFunctionLibrary.getCSSValue(ObjectRepository.DELETE_ACTION);
			
			if (actualLinkColor.equals(expectedLinkColor)) {
				String comment = " Delete action done on dataset : "+fileName+". Delete Option is Greyout in Action Menu So User doesn't have access to Delete";
				log.info(comment);	
				
				
			GenericFunctionLibrary.refreshWindow();
				
				result[0] = "Un-Authorized Successful";
				result[1]= comment;
				GenericFunctionLibrary.refreshWindow();
				
				
				Thread.sleep(4000);
			//	GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.CONTAINER_LANDING_PAGE);
			} else {
				
			// navigating to 'Delete' window
			
			String parentWindow = GenericFunctionLibrary.switchToChildWindow(ObjectRepository.DELETE_ACTION);
			log.info("User clicked on Delete option");
				Thread.sleep(5000);
		//		GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.OK_BUTTON_PARENT_WINDOW);
				if(GenericFunctionLibrary.isItOpenedChildWindow(ObjectRepository.OK_BUTTON_PARENT_WINDOW, parentWindow)){
					
					String parentWindow2 =	GenericFunctionLibrary.switchToChildWindow();		
				
				log.info("User clicked on Ok Button on Delete screen");
				
				Thread.sleep(5000);
				
				//String parentWindow2 =	GenericFunctionLibrary.switchToChildWindow();		
				
				
				// verifying 'authorized and un-authorized' conditions
				if (GenericFunctionLibrary.isMatchFoundInList(ObjectRepository.ERROR_MESSAGE,"Failed with overridable conflicts")) {
					String comment = "Delete action done on dataset : "+fileName+". user find the Conflict messages as Failed with overridable conflicts hence use is NOT Authorized to Delete";
					log.info(comment);
					GenericFunctionLibrary.closeChildWindow();
					GenericFunctionLibrary.switchToWindow(parentWindow2);
					GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CANCEL_BUTTON_CHANGE_CONTEXT);
					GenericFunctionLibrary.switchToWindow(parentWindow);
					result[0] = "Un-Authorized Successful";
					result[1]= comment;
				//	GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CONTAINER_LANDING_PAGE);
					Thread.sleep(3000);
				} else if(GenericFunctionLibrary.isMatchFoundInList(ObjectRepository.ERROR_MESSAGE,"Done with Conflicts")){
					String comment = "Delete action done on dataset : "+fileName+". user find the Conflict messages as Done with Conflicts hence use is NOT Authorized to Delete";
					log.info(comment);
					GenericFunctionLibrary.closeChildWindow();
					GenericFunctionLibrary.switchToWindow(parentWindow2);
					GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CANCEL_BUTTON_CHANGE_CONTEXT);
					GenericFunctionLibrary.switchToWindow(parentWindow);
					result[0] = "Un-Authorized Successful";
					result[1]= comment;
				}else if(GenericFunctionLibrary.isMatchFoundInList(ObjectRepository.ERROR_MESSAGE,"Failed")){
					String comment = "Delete action done on dataset : "+fileName+". user find the Conflict messages as Failed hence use is NOT Authorized to Delete";
							
					log.info(comment);
					GenericFunctionLibrary.closeChildWindow();
					GenericFunctionLibrary.switchToWindow(parentWindow2);
					GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CANCEL_BUTTON_CHANGE_CONTEXT);
					GenericFunctionLibrary.switchToWindow(parentWindow);
					result[0] = "Un-Authorized Successful";
					result[1]= comment;
				}
				}else{
						
				GenericFunctionLibrary.switchToWindow(parentWindow);
					log.info("After");
					GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.CONTAINER_LANDING_PAGE);
					selectDomain("CignexTestData");
				
					if (isContentAvailable(fileName)) {
					log.info("Content is NOT Deleted");
					
					String comment =" Delete action done on dataset : "+fileName+". and is NOT deleted. it could be issue with the Functionality";
					log.info(comment);
					result[0] = "Un-Authorized Successful";
					result[1]= comment;
					
				}else{
					
					String comment = "Delete action done on dataset : "+fileName+". Content Deleted Successfully and User is Authorised to Delete Action";
					log.info(comment);
					
					result[0] = "Authorized Successful";
					result[1]= comment;
				}
			}
			}
		
		return result;
		
	}
	
	
	
public String[] setStateAccessRightValidation() throws InterruptedException{
	
	String[] result = new String[2]; 
	
	
	String fileName = GenericFunctionLibrary.getFileName(ObjectRepository.VERSION_NUMBER_FILENAME);
	
	String parentWindow = "";
	String currentStatus = GenericFunctionLibrary.getText("xpath", ObjectRepository.SEARCH_RESULT_STATUS);
	log.info("current content status :" + currentStatus);

		// clicking on 'actions' button
		Thread.sleep(6000);
	///	GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.ACTION_BUTTON);
	//	log.info("user clicked on Action Button");
		// verifying whether 'setstate' option is available in action tab or not
		Thread.sleep(3000);
		//if(GenericFunctionLibrary.isElementPresent(ObjectRepository.ACTION_OPTION_SETSTATE)){		
		if(verifyAndClickOptionFromActionsDropDown("Set State")){
		log.info("SetState Optio is Available inside Action dropdown");
		Thread.sleep(3000);
		// navigating to 'setstate' window
		//String parentWindow = GenericFunctionLibrary.switchToChildWindow(ObjectRepository.ACTION_OPTION_SETSTATE);
		Thread.sleep(3000);
		log.info("User clicked on SetState option and navigated to Set State page");
		//driver.switchTo().frame("lbContentIframe");
		GenericFunctionLibrary.SwitchToFrame("lbContentIframe");

		String previousState = GenericFunctionLibrary.getText("xpath",ObjectRepository.CURRENT_STATE_VALUE);

		// select the state value from dropdown by using index

	//	GenericFunctionLibrary.optimizedselectDropdown("xpath", ObjectRepository.STATE_DROP_DOWN, ObjectRepository.STATE_DROP_DOWN+"/options", previousState);
		GenericFunctionLibrary.selectDropdownByIndex("xpath", ObjectRepository.STATE_DROP_DOWN, 1);
	
		
		Thread.sleep(2000);
	//	String updatedState = GenericFunctionLibrary.getText("xpath",ObjectRepository.CURRENT_STATE_VALUE);
		String updatedState = GenericFunctionLibrary.getAttributeValue("xpath", ObjectRepository.STATE_DROP_DOWN, "value");
		log.info("Selected other state and the Selected state is : "+ updatedState);
		// click on save
		//GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.OK_BUTTON_SET_STATE_Frame1);
		log.info("user is clicked on OK Button in SetState screen");
		Thread.sleep(2000);
		if(GenericFunctionLibrary.isOpenedChildWindow(ObjectRepository.OK_BUTTON_SET_STATE_Frame1)){
			
			 parentWindow =	GenericFunctionLibrary.switchToChildWindow();	
			
			log.info("user is clicked on OK Button in SetState screen");
			
		// compare previous and updated state, should not same

		 if (GenericFunctionLibrary.isMatchFoundInList(ObjectRepository.ERROR_MESSAGE,"Failed with overridable conflicts")) {
				
				String comment = "Set state done on dataset : "+ fileName +". user find the Conflict messages as Failed with overridable conflicts hence use is NOT Authorized to Set State";
				log.info(comment);
				
				GenericFunctionLibrary.closeChildWindow();
				GenericFunctionLibrary.switchToWindow(parentWindow);
				Thread.sleep(2000);
				GenericFunctionLibrary.SwitchToFrame("lbContentIframe");
				GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CANCEL_BUTTON);
				
				result[0] = "Un-Authorized Successful";
				result[1] = comment;
			//	GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CONTAINER_LANDING_PAGE);
				Thread.sleep(3000);
				} else if(GenericFunctionLibrary.isMatchFoundInList(ObjectRepository.ERROR_MESSAGE,"Done with Conflicts")){

				String comment = "Set state done on dataset : "+ fileName +". user find the Conflict messages as Done with Conflicts hence use is NOT Authorized to Set State";
				log.info(comment);
				
				GenericFunctionLibrary.closeChildWindow();
				GenericFunctionLibrary.switchToWindow(parentWindow);
				Thread.sleep(2000);
				GenericFunctionLibrary.SwitchToFrame("lbContentIframe");
				GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CANCEL_BUTTON);
				//GenericFunctionLibrary.switchToWindow(parentWindow);
				result[0] = "Un-Authorized Successful";
				result[1] = comment;
				}else if(GenericFunctionLibrary.isMatchFoundInList(ObjectRepository.ERROR_MESSAGE,"Failed")){
			
				String comment = "Set state done on dataset : "+ fileName +". user find the Conflict messages as Failed hence use is NOT Authorized to Set State";
				
				log.info(comment);
				
//				GenericFunctionLibrary.clickOnElement("xpath", "//tbody[@class='x-btn-small x-btn-icon-small-left']//button[Contains(text(),'Close')]");
				GenericFunctionLibrary.closeChildWindow();
				GenericFunctionLibrary.switchToWindow(parentWindow);
			
				Thread.sleep(2000);
				GenericFunctionLibrary.SwitchToFrame("lbContentIframe");
				GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CANCEL_BUTTON);
				//GenericFunctionLibrary.switchToWindow(parentWindow);
				result[0] = "Un-Authorized Successful";
				result[1] = comment;
			}
			}else{

			GenericFunctionLibrary.switchToWindow(parentWindow);
			String updatedStatus = GenericFunctionLibrary.getText("xpath", ObjectRepository.SEARCH_RESULT_STATUS);
			log.info("current content status :" + updatedStatus);
			if (updatedStatus.equals(previousState)) {
				
				String comment = "Set state done on dataset : "+ fileName +". State did not Changed after set state. could be the issue with application. State before the Set State action is "+previousState +" and State after Set State action is "+ updatedStatus ;
				log.info(comment);
				
				result[0] = "Un-Authorized Successful";
				result[1] = comment;
				// verifying 'authorized and un-authorized' conditions
			
			}else{
			
			String comment = "Set state done on dataset : "+ fileName +". State has chaged from "+ previousState +" to "+ updatedStatus +"after setState hence User is Athorized to SetState";
			log.info(comment);
			log.info(" State changed and logged-in user has set state permission");

			result[0] = "Authorized Successful";
			result[1] = comment;
			
		//	GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CONTAINER_LANDING_PAGE);
		}
			}
		
		}else{
			
			String comment = "Set state done on dataset : "+ fileName +". SetState option is NOT available inside action dropdown Hence user is not Authorized to Set Stete";
			log.info(comment);
			
			log.info("Logged-in user don't have set state permission ");
			result[0] = "Un-Authorized Successful";
			result[1] = comment;
		
			Thread.sleep(4000);
			
		
		}
	
	return result;

}

public String[] changeDomainAccessRightValidation(String templateName) throws InterruptedException {
	String[] result = new String[2];

	// searching expected content in the folder to change domain
		
	String fileName = GenericFunctionLibrary.getFileName(ObjectRepository.VERSION_NUMBER_FILENAME);
	log.info("File Name : "+ fileName);
	Thread.sleep(2000);
	
	GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.DETAILS_MENU);
		Thread.sleep(3000);
			GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ACTION_BUTTON);
			log.info("user clicked on Action Button");
			Thread.sleep(3000);
			
			if(GenericFunctionLibrary.isElementPresent(ObjectRepository.MOVE_ACTION)){
				
				String expectedLinkColor = "rgba(128, 128, 128, 1)";
				String actualLinkColor = GenericFunctionLibrary.getCSSValue(ObjectRepository.MOVE_ACTION);
				
				if (actualLinkColor.equals(expectedLinkColor)) {
					log.info("Move option is grayout hence user is not Authorized to Move Content");	
					
				GenericFunctionLibrary.refreshWindow();
				
				String comment = "Change Domain done on dataset : "+ fileName +". Move Option is Greyout in Action Menu So User doesn't have access to Change Domain/Context";
					log.info(comment);
					
					result[0] = "Un-Authorized Successful";
					result[1] = comment;
					GenericFunctionLibrary.refreshWindow();
					Thread.sleep(4000);
				
				
				}else{
				
				
			// verify 'move' option in Action tab and if exist the process further actions (click on move option)	
			//if(verifyAndClickOptionFromActionsDropDown("Move")){
			//if(GenericFunctionLibrary.isElementPresent(ObjectRepository.MOVE_ACTION)){
					GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.MOVE_ACTION);

				log.info("User clicked on Action Button Move Option Available inside Action dropdown");
				
						log.info("User clicked on Move option and navigated to Change Domain page");	
				
			String parentWindow = GenericFunctionLibrary.switchToChildWindow();
			Thread.sleep(2000);
			
			// Checking the Object to Move
			GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.CHECK_MOVE_OBJECT);	
	
			Thread.sleep(3000);
			
			String targetLocation = null;
			if(templateName.contains("Restricted")){
				 
				targetLocation = Config.TESTDATA_FOLDER;
				
				  } else{

					  targetLocation = Config.RESTRICTED_DOMAIN;
				  }
			
			log.info("location to be moved : "+ targetLocation);
			GenericFunctionLibrary.WaitForElementTobeSelected("xpath", ObjectRepository.SET_NEW_LOCATION_ICON, 10);
			// Clicking on the Folder Icon
			GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.SET_NEW_LOCATION_ICON);
			log.info("Clicked on Change Location Icon in Change domain screen");
			
			String parentWindow1 = GenericFunctionLibrary.switchToChildWindow();
			
			Thread.sleep(3000);
			
			//GenericFunctionLibrary.WaitForElementTobeSelected("xpath", ObjectRepository.TARGET_FOLDER_XPATH, 15);
			
			selectMatchedFoundInList(ObjectRepository.DOMAINS_LIST, targetLocation);
		
			//	GenericFunctionLibrary.clickOnElement("xapth", "//option[@id='__00. Restricted Area']");
			//GenericFunctionLibrary.selectDropdownByVisibleText("id", ObjectRepository.TARGET_FOLDER_XPATH, Config.RESTRICTED_DOMAIN);
			
			log.info("Selected "+ targetLocation +" from the list box");
			
			GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.OK_BUTTON);
			log.info("Clicked on Ok Button after seleting domain");
			Thread.sleep(5000);
			
			// Moving on parent window1
			GenericFunctionLibrary.switchToWindow(parentWindow1);
			
			Thread.sleep(5000);
			//Click on ok button in parent window
			
						
			if(GenericFunctionLibrary.isItOpenedChildWindow(ObjectRepository.OK_BUTTON_PARENT_WINDOW, parentWindow)){
				
				String parentWindow2 =	GenericFunctionLibrary.switchToChildWindow();		
			
			
			// verifying 'authorized and un-authorized' conditions
			if (GenericFunctionLibrary.isMatchFoundInList(ObjectRepository.ERROR_MESSAGE,"Failed with overridable conflicts")) {
				String comment = "Change Domain done on dataset : "+ fileName +". user find the Conflict messages Failed with overridable conflicts hence user is NOT Authorized to Change Domain";
				
				log.info(comment);
				
				GenericFunctionLibrary.closeChildWindow();
				GenericFunctionLibrary.switchToWindow(parentWindow2);
				GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CANCEL_BUTTON_CHANGE_CONTEXT);
				GenericFunctionLibrary.switchToWindow(parentWindow);
				result[0] = "Un-Authorized Successful";
				result[1]= comment;
			//	GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CONTAINER_LANDING_PAGE);
				Thread.sleep(3000);
			} else if(GenericFunctionLibrary.isMatchFoundInList(ObjectRepository.ERROR_MESSAGE,"Done with Conflicts")){

				String comment = "Change Domain done on dataset : "+ fileName +". user find the Conflict messages as Done with Conflicts hence user is NOT Authorized to Change Domain";

				log.info(comment);
				GenericFunctionLibrary.closeChildWindow();
				GenericFunctionLibrary.switchToWindow(parentWindow2);
				GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CANCEL_BUTTON_CHANGE_CONTEXT);
				GenericFunctionLibrary.switchToWindow(parentWindow);
				result[0] = "Un-Authorized Successful";
				result[1] = comment;
			}else if(GenericFunctionLibrary.isMatchFoundInList(ObjectRepository.ERROR_MESSAGE,"Failed")){
				String comment = "Change Domain done on dataset : "+ fileName +". user find the Conflict messages as Failed hence user is NOT Authorized to Change Domain";
				log.info(comment);
				GenericFunctionLibrary.closeChildWindow();
				GenericFunctionLibrary.switchToWindow(parentWindow2);
				GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CANCEL_BUTTON_CHANGE_CONTEXT);
				GenericFunctionLibrary.switchToWindow(parentWindow);
				result[0] = "Un-Authorized Successful";
				result[1] = comment;
			}
			}else{

				GenericFunctionLibrary.switchToWindow(parentWindow);
				GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.DETAILS_MENU);
				
				GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.TARGET_DOMAIN, 3);
				
				if(GenericFunctionLibrary.isElementPresent(ObjectRepository.TARGET_DOMAIN)){
				String domain	= GenericFunctionLibrary.getText("xpath", ObjectRepository.TARGET_DOMAIN);
				
				log.info("Document Moved to "+ domain);
				
				//GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.CONTAINER_LANDING_PAGE);
				
				Thread.sleep(5000);
				
				// Navigating to 'Restricted Documents Area' folder
				
				//selectDomain(Config.RESTRICTED_DOMAIN);
			
				//	GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.RESTRICT_PART_FOLDER_NEW);

					log.info("User navigated to Selcted Domain ie., Restricted Documents Area in the same context");
					Thread.sleep(2000);
					
					if (domain.equals(targetLocation)) {
						String comment = "Change Domain done on dataset : "+ fileName +". content is available in search and logged-in user has permission to perform change domain action";
						log.info(comment);
						result[0] = "Authorized Successful";
						result[1] = comment;
						//GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CONTAINER_LANDING_PAGE);

					} else {
						String comment = "Change Domain done on dataset : "+ fileName +". File is NOT Available in Target Domain. could be some issue with Application";
						log.info(comment);
						result[0] = "Un-Authorized Successful";
						result[1] = comment;
					}
					
				}else{
					
					result[0] = "Authorized Successful";
				}
			}
			
				}
								 
} else{
	String comment = "Change Domain done on dataset : "+ fileName +". Move Option is NOT available in Action Dropdown Hence user is NOT authorized to Change Domain";
		log.info(comment);		
			log.info("   Logged-in user don't have change domain permission ");
			result[0] = "Un-Authorized Successful";
			result[1] = comment;
		
		}
	 return result;
}


//Change context : permission
	/*
	 * This method will use in all the content type, just will call and pass the
	 * content value as parameter in respective script
	 */

	public String[] changeContextAccessRightValidation()throws InterruptedException {

		String[] result = new String[2];
		
		String fileName = GenericFunctionLibrary.getFileName(ObjectRepository.VERSION_NUMBER_FILENAME);
		Thread.sleep(3000);
		GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.DETAILS_MENU);
				
			Thread.sleep(2000);
			
			// Store context value before perform 'change context' action

			String previousContainerName = GenericFunctionLibrary.getText("xpath", ObjectRepository.CONTAINER_NAME_VALUE);

			log.info("previous container name :"+ previousContainerName);

			Thread.sleep(3000);

			// clicking on 'actions' button
			GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.ACTION_BUTTON);
			Thread.sleep(2000);
			// verify 'move' option in Action tab and if exist the process further actions	
			
			if(GenericFunctionLibrary.isElementPresent(ObjectRepository.MOVE_ACTION)){
			String expectedLinkColor = "rgba(128, 128, 128, 1)";
			String actualLinkColor = GenericFunctionLibrary.getCSSValue(ObjectRepository.MOVE_ACTION);
			
			if (actualLinkColor.equals(expectedLinkColor)) {
				String comment = "Change Context done on dataset : "+ fileName +". Move Option is Greyout in Action Menu So User doesn't have access to Change Domain/Context";
				log.info(comment);		
			GenericFunctionLibrary.refreshWindow();
			
				result[0] = "Un-Authorized Successful";
				result[1]= comment;
				GenericFunctionLibrary.refreshWindow();
				Thread.sleep(4000);
			
			
			}else{
			
			// verify 'move' option in Action tab and if exist the process further actions				
			//if(GenericFunctionLibrary.isElementPresent(ObjectRepository.MOVE_ACTION)){
				
				
				
				
				/*if(verifyAndClickOptionFromActionsDropDown("Move")){
					
			log.info("Move Optio is Available inside Action dropdown");
			String parentWindow =	GenericFunctionLibrary.switchToChildWindow();*/
			// navigating to 'Move' window
			String parentWindow = GenericFunctionLibrary.switchToChildWindow(ObjectRepository.MOVE_ACTION);
			log.info("User clicked on Move option and navigated to Change Context page");
			// select checkbox
			GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.CHECK_MOVE_OBJECT);

			/*String number = GenericFunctionLibrary.getText("xpath",ObjectRepository.OBJECT_PART_NUMBER);

			log.info(number);
			Thread.sleep(5000);
			String newLocationOption = ;
			 */
			Thread.sleep(3000);
            GenericFunctionLibrary.WaitForElementTobeSelected("xpath", ObjectRepository.SET_NEW_LOCATION_ICON, 10);
			GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.SET_NEW_LOCATION_ICON);
			String parentWindow1 = GenericFunctionLibrary.switchToChildWindow();
			log.info(" Page name is : "+ GenericFunctionLibrary.getPageTitle());
			Thread.sleep(5000);
			
			 GenericFunctionLibrary.WaitForElementTobeSelected("xpath", ObjectRepository.TARGET_LOCATION, 10);

			// Selecting a New Folder to Move the Object
			GenericFunctionLibrary.selectDropdownByVisibleText("xpath", ObjectRepository.TARGET_LOCATION, Config.CHANGE_CONTEXT_TO);
			
			/* 
			GenericFunctionLibrary.WaitForElementTobeSelected("xpath", ObjectRepository.TARGET_FOLDER_XPATH, 10);
			Thread.sleep(3000);
			GenericFunctionLibrary.selectDropdownByVisibleText("xpath", ObjectRepository.TARGET_FOLDER_XPATH, Config.TESTDATA_FOLDER);
			*/
			
			//GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.TARGET_LOCATION);
			
			log.info("Selected Target Context inside Change Context Page");
			Thread.sleep(2000);
			//GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.TARGET_FOLDER);

			log.info("Selected Target Folder inside Change Context Page");
			//GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.TARGET_CHILD_FOLDER);
			Thread.sleep(2000);
			//log.info("Selected Target Child Folder inside Change Context Page");
			
		//	GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.OPENBUTTON);
			//log.info("Clicked on Open Button");
			
			Thread.sleep(5000);

			GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.OK_BUTTON);
			log.info("Clicked on OK Button in Change Context after selected target Context and folders");

			Thread.sleep(2000);
			// Moving on to Parent Window

			GenericFunctionLibrary.switchToWindow(parentWindow1);

			//GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.OK_BUTTON_PARENT_WINDOW);

		//	String parentWindow2 = GenericFunctionLibrary.switchToChildWindow(ObjectRepository.OK_BUTTON_PARENT_WINDOW);
			log.info("user clicked on OK Button Window 1 of Change Context ");
			//Thread.sleep(5000);
			
		if(GenericFunctionLibrary.isItOpenedChildWindow(ObjectRepository.OK_BUTTON_PARENT_WINDOW, parentWindow)){
			
		String parentWindow2 =	GenericFunctionLibrary.switchToChildWindow();
			
		// compare checkStatusMessage with expectedMessage
			if (GenericFunctionLibrary.isMatchFoundInList(ObjectRepository.ERROR_MESSAGE,"Failed with overridable conflicts")) {
				String comment = "Change Context done on dataset : "+ fileName +". user find the Conflict messages as Failed with overridable conflicts hence user is NOT Authorized to Change Context";
				log.info(comment);
				GenericFunctionLibrary.closeChildWindow();
				//GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CLOSE_BUTTON_CHANGE_CONTEXT);
				
				GenericFunctionLibrary.switchToWindow(parentWindow2);
				GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CANCEL_BUTTON_CHANGE_CONTEXT);
				GenericFunctionLibrary.switchToWindow(parentWindow);
								
				result[0] = "Un-Authorized Successful";
				result[1] = comment;
				
				Thread.sleep(3000);
			//	GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CONTAINER_LANDING_PAGE);
				Thread.sleep(3000);
			}else if(GenericFunctionLibrary.isMatchFoundInList(ObjectRepository.ERROR_MESSAGE,"Done with Conflicts")){
				String comment = "Change Context done on dataset : "+ fileName +". user find the Conflict messages as Done with Conflicts hence user is NOT Authorized to Change Context";
				log.info(comment);
				GenericFunctionLibrary.closeChildWindow();
				GenericFunctionLibrary.switchToWindow(parentWindow2);
				GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CANCEL_BUTTON_CHANGE_CONTEXT);
				GenericFunctionLibrary.switchToWindow(parentWindow);
				result[0] = "Un-Authorized Successful";
				result[1] = comment;
			
			}else if(GenericFunctionLibrary.isMatchFoundInList(ObjectRepository.ERROR_MESSAGE,"Failed")){
				
				String comment = "Change Context done on dataset : "+ fileName +". user find the Conflict messages as Failed hence user is NOT Authorized to Change Context";
				
				log.info(comment);
				GenericFunctionLibrary.closeChildWindow();
				GenericFunctionLibrary.switchToWindow(parentWindow2);
				GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CANCEL_BUTTON_CHANGE_CONTEXT);
				GenericFunctionLibrary.switchToWindow(parentWindow);
				result[0] = "Un-Authorized Successful";
				result[1] = comment;
			}
			}else {
							
			log.info("Before switching to Parent Window");
			
			// switch back to previous windnow
			GenericFunctionLibrary.switchToWindow(parentWindow);
			
			log.info("After switching to Parent Window");
			// Get modified container name and verify with previous container
			// name
			GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.DETAILS_MENU);
			Thread.sleep(3000);

			String modifiedContainerName = GenericFunctionLibrary.getText("xpath", ObjectRepository.CONTAINER_NAME_VALUE);

			log.info("modified container name :"+ modifiedContainerName);

			if (modifiedContainerName.equals(previousContainerName)) {

				String comment = "Change Context done on dataset : "+ fileName +". Context is not Changed. could be some issue in application";
				log.info(comment);
				result[0] = "Un-Authorized Successful";
				result[1] = comment;
				
			} else {
				String comment = " Change Context done on dataset : "+ fileName +". Content moved to different container and user is authorized to perform 'change context' action";
				
				log.info(comment);
				Thread.sleep(3000);
			//	GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CONTAINER_LANDING_PAGE);
				Thread.sleep(3000);
				
				result[0] = "Authorized Successful";
				result[1] = comment;
			}

		}
			}		
	
		}else{
			
			String comment = "Change Context done on dataset : "+ fileName +". Move Option is NOT available in Action Dropdown Hence user is NOT authorized to Change Context";
			log.info(comment);
				result[0] = "Un-Authorized Successful";
				result[1] = comment;
				Thread.sleep(4000);
			
			}
		
	
	return result;
}

	
	public boolean advanceSearchModified(String ADV_OBJECT_TYPE, String ADV_SEARCH_CONTAINER_NAME, String Template, String ADV_SEARCH_STATE_STATUS, String ADVANCE_SEARCH_BY_NAME) throws InterruptedException {

		boolean isPassed = true;
		String ADV_SEARCH_DROPDOWN_STATE_VALUE = "State";
	//	String ADV_SEARCH_CREATEDBY = "Created By";
		String ADV_SEARCH_DROPDOWN_NAME_VALUE = "Name";
		String	RECORD_NUMBER = "";

		log.info("Advance search starts.....");

		Thread.sleep(2000);
		
		
		// Click on advance search button/icon
		GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_BUTTON_XPATH, 30);
		GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH_BUTTON_XPATH);
		log.info("Clicked on Search Link ");
		//Thread.sleep(2000);

		// Clicking on Advanced Search Tab
		GenericFunctionLibrary.WaitForElementPresence("xpath",ObjectRepository.ADV_SEARCH_TAB_XPATH, 20);
		GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH_TAB_XPATH);
		Thread.sleep(3000);

		// clicking on New Search Criteria Link
		if (GenericFunctionLibrary.isElementPresent(ObjectRepository.ADV_SEARCH_NEW_CRITERIA)) {
			GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH_NEW_CRITERIA);
			Thread.sleep(2000);
		
		if (GenericFunctionLibrary.isMatchFoundInList(ObjectRepository.ADV_SEARCH_SEARCHTEXT_VALUE, ADV_OBJECT_TYPE)) {
			GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_ALL, 20);
			GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH_ALL);
			Thread.sleep(1000);
			// Select check box based on ADV_SEARCH_VALUE variable
			GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_SEARCHTEXT_VALUE, 20);
			GenericFunctionLibrary.selectMatchedFoundInList(ObjectRepository.ADV_SEARCH_SEARCHTEXT_VALUE, ADV_OBJECT_TYPE);
		} else {
			// Click on Add link and Switch to child window
			String parentWindow = GenericFunctionLibrary.switchToChildWindow(ObjectRepository.ADV_SEARCH_ADD);
			Thread.sleep(2000);

			// Enter search text in window
			GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_WINDOW, 20);
			GenericFunctionLibrary.setText("xpath", ObjectRepository.ADV_SEARCH_WINDOW, ADV_OBJECT_TYPE);
			Thread.sleep(1000);

			// search text box
			GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH_WINDOW_CLICK);
			Thread.sleep(2000);

			// Add searched check box
			GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_CHECKBOX, 20);
			GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH_CHECKBOX);
			//Thread.sleep(2000);
			GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH_OK);
			Thread.sleep(2000);

			// Switch to main window
			GenericFunctionLibrary.switchToWindow(parentWindow);

			
			Thread.sleep(2000);
			// click on ALL item check box
			GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_ALL, 20);
			GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH_ALL);
			Thread.sleep(1000);
			// Select check box based on ADV_SEARCH_VALUE variable
			GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_SEARCHTEXT_VALUE, 20);
			GenericFunctionLibrary.selectMatchedFoundInList(ObjectRepository.ADV_SEARCH_SEARCHTEXT_VALUE, ADV_OBJECT_TYPE);
			
		}
		// Select the add on context
		Thread.sleep(3000);

		if (GenericFunctionLibrary.isMatchFoundInList(ObjectRepository.ADV_SEARCH_SEARCHTEXT_VALUE,ADV_SEARCH_CONTAINER_NAME)) {

			GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_ALL_CONTEXTS, 20);
			GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH_ALL_CONTEXTS);

			// Select check box based on ADV_SEARCH_Container_VALUE variable
			GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_SEARCHTEXT_VALUE, 20);
			GenericFunctionLibrary.selectMatchedFoundInList(ObjectRepository.ADV_SEARCH_SEARCHTEXT_VALUE,
					ADV_SEARCH_CONTAINER_NAME);
		} else {

			// Switch to child window String parentWindow_context

			String parentWindow = GenericFunctionLibrary.switchToChildWindow(ObjectRepository.ADV_SEARCH_CONTEXT_ADD);
			//Thread.sleep(2000);

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
			GenericFunctionLibrary.selectMatchedFoundInList(ObjectRepository.ADV_SEARCH_SEARCHTEXT_VALUE,ADV_SEARCH_CONTAINER_NAME);
		}

		// clicking search state drop down
		GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_SEARCHDROPDOWN, 20);
		GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH_SEARCHDROPDOWN);
		Thread.sleep(2000);

		// ******************** clicking search state // ********************************************
	//	if(GenericFunctionLibrary.isElementPresent("//input[@id='combo-qBAttributeTD-1'")){
		GenericFunctionLibrary.selectMatchedFoundInList(ObjectRepository.ADV_SEARCH_SEARCHDROPCLICK, ADV_SEARCH_DROPDOWN_STATE_VALUE);
		// clicking in creation drop down
		Thread.sleep(2000);

		GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_SEARCHDROP_VALUE, 20);

		GenericFunctionLibrary.selectDropdownByVisibleText("xpath", ObjectRepository.ADV_SEARCH_SEARCHDROP_VALUE, ADV_SEARCH_STATE_STATUS);

		Thread.sleep(2000);
		GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH_ADD_ICON);
		Thread.sleep(2000);


		Thread.sleep(2000);
		GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH_ADD_ICON);
		Thread.sleep(2000);

		// clicking search Name drop down*********************************
		GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_SEARCHDROPDOWN_NAME, 20);
		GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH_SEARCHDROPDOWN_NAME);
		Thread.sleep(3000);
		// clicking search Name
		GenericFunctionLibrary.selectMatchedFoundInList(ObjectRepository.ADV_SEARCH_SEARCHDROPCLICK_NAME, ADV_SEARCH_DROPDOWN_NAME_VALUE);
		Thread.sleep(3000);
		GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_BY_NAME, 20);
		if (Template.contains("Restricted")) {
			GenericFunctionLibrary.clearTextBox("xpath", ObjectRepository.ADV_SEARCH_BY_NAME);
			Thread.sleep(3000);
			GenericFunctionLibrary.setText("xpath", ObjectRepository.ADV_SEARCH_BY_NAME,Config.RESTRICTED + ADVANCE_SEARCH_BY_NAME+"*");
		
		} else {
			GenericFunctionLibrary.setText("xpath", ObjectRepository.ADV_SEARCH_BY_NAME, ADVANCE_SEARCH_BY_NAME +"*");
		}
		
/*}else{
	GenericFunctionLibrary.refreshWindow();
	Thread.sleep(3000);
	GenericFunctionLibrary.refreshWindow();
	advanceSearchModified(ADV_OBJECT_TYPE, ADV_SEARCH_CONTAINER_NAME, Template, ADV_SEARCH_STATE_STATUS, ADVANCE_SEARCH_BY_NAME);
}*/
		Thread.sleep(3000);
		// click on Search button
		GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH, 20);
		GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH);
		
		if(GenericFunctionLibrary.isAleretPresent(3)){
			GenericFunctionLibrary.getAlert();
			GenericFunctionLibrary.refreshWindow();
			Thread.sleep(3000);
			GenericFunctionLibrary.refreshWindow();
			advanceSearchModified(ADV_OBJECT_TYPE, ADV_SEARCH_CONTAINER_NAME, Template, ADV_SEARCH_STATE_STATUS, ADVANCE_SEARCH_BY_NAME);
		}
		}else{
		
		GenericFunctionLibrary.refreshWindow();
		Thread.sleep(3000);
		GenericFunctionLibrary.refreshWindow();
		advanceSearchModified(ADV_OBJECT_TYPE, ADV_SEARCH_CONTAINER_NAME, Template, ADV_SEARCH_STATE_STATUS, ADVANCE_SEARCH_BY_NAME);

		

}
		
		Thread.sleep(3000);

		// GenericFunctionLibrary.selectFirstElementInList(ObjectRepository.ADV_SEARCH_LIST);

		Boolean ADV_SEARCH_LIST_Element = GenericFunctionLibrary.isElementPresent(ObjectRepository.ADV_SEARCH_LIST);
		if (ADV_SEARCH_LIST_Element == true) {

			// Get the document number from the list
		RECORD_NUMBER = GenericFunctionLibrary.getText("xpath", ObjectRepository.ADV_SEARCH_LIST);
			log.info("Data Set used is  : " + RECORD_NUMBER);
			
		}

		if (RECORD_NUMBER != "") {

			// Perform search action to verify created document in 'folder
			// content'
			Thread.sleep(2000);
			String ADV_SEARCH_FIRST_LIST = ObjectRepository.ADV_SEARCH_FIRST_LIST+ RECORD_NUMBER + "')]";
			log.info("XPATH for ObjectRepository.ADV_SEARCH_FIRST_LIST is : " + ADV_SEARCH_FIRST_LIST);

			log.info("content available in search");
			// Click on the document number in the list
			// GenericFunctionLibrary.selectMatchedFoundInList(ObjectRepository.ADV_SEARCH_LIST,RECORD_VALUE.trim());
			GenericFunctionLibrary.WaitForElementPresence("xpath", ADV_SEARCH_FIRST_LIST, 20);
			GenericFunctionLibrary.clickOnElement("xpath", ADV_SEARCH_FIRST_LIST);
			

			Thread.sleep(2000);
			isPassed = true;

		} else {
			log.info("Content is not available in system");
			
			isPassed = false;
		}
		log.info("search status is : "+isPassed);
		return isPassed;

	}

	public boolean advanceSearchModifiedPR(String ADV_OBJECT_TYPE, String ADV_SEARCH_CONTAINER_NAME, String Template, String ADV_SEARCH_STATE_STATUS, String ADVANCE_SEARCH_BY_NAME) throws InterruptedException {

		boolean isPassed = true;
		String ADV_SEARCH_DROPDOWN_STATE_VALUE = "State";
	//	String ADV_SEARCH_CREATEDBY = "Created By";
		String ADV_SEARCH_DROPDOWN_NAME_VALUE = "Name";
		String	RECORD_NUMBER = "";

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

		// clicking on New Search Criteria Link
		if (GenericFunctionLibrary.isElementPresent(ObjectRepository.ADV_SEARCH_NEW_CRITERIA)) {
			GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH_NEW_CRITERIA);
			Thread.sleep(2000);
		
		if (GenericFunctionLibrary.isMatchFoundInList(ObjectRepository.ADV_SEARCH_SEARCHTEXT_VALUE, ADV_OBJECT_TYPE)) {
			GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_ALL, 20);
			GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH_ALL);
			Thread.sleep(1000);
			// Select check box based on ADV_SEARCH_VALUE variable
			GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_SEARCHTEXT_VALUE, 20);
			GenericFunctionLibrary.selectMatchedFoundInList(ObjectRepository.ADV_SEARCH_SEARCHTEXT_VALUE,
					ADV_OBJECT_TYPE);
		} else {
			// Click on Add link and Switch to child window
			String parentWindow = GenericFunctionLibrary.switchToChildWindow(ObjectRepository.ADV_SEARCH_ADD);
			Thread.sleep(2000);

			// Enter search text in window
			GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_WINDOW, 20);
			GenericFunctionLibrary.setText("xpath", ObjectRepository.ADV_SEARCH_WINDOW, ADV_OBJECT_TYPE);
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

			
			Thread.sleep(2000);
			// click on ALL item check box
			GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_ALL, 20);
			GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH_ALL);
			Thread.sleep(1000);
			// Select check box based on ADV_SEARCH_VALUE variable
			GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_SEARCHTEXT_VALUE, 20);
			GenericFunctionLibrary.selectMatchedFoundInList(ObjectRepository.ADV_SEARCH_SEARCHTEXT_VALUE, ADV_OBJECT_TYPE);
			
		}
		// Select the add on context
		Thread.sleep(3000);

		if (GenericFunctionLibrary.isMatchFoundInList(ObjectRepository.ADV_SEARCH_SEARCHTEXT_VALUE,ADV_SEARCH_CONTAINER_NAME)) {

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

		// clicking search state drop down
		GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_SEARCHDROPDOWN, 20);
		GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH_SEARCHDROPDOWN);
		Thread.sleep(2000);

		// ******************** clicking search state
		// ********************************************

		GenericFunctionLibrary.selectMatchedFoundInList(ObjectRepository.ADV_SEARCH_SEARCHDROPCLICK, ADV_SEARCH_DROPDOWN_STATE_VALUE);
		// clicking in creation drop down
		Thread.sleep(2000);

		GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_SEARCHDROP_VALUE, 20);

		GenericFunctionLibrary.selectDropdownByVisibleText("xpath", ObjectRepository.ADV_SEARCH_SEARCHDROP_VALUE, ADV_SEARCH_STATE_STATUS);

		Thread.sleep(2000);
		GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH_ADD_ICON);
		Thread.sleep(2000);


		Thread.sleep(2000);
		GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH_ADD_ICON);
		Thread.sleep(2000);

		// clicking search Name drop down*********************************
		GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_SEARCHDROPDOWN_NAME, 20);
		GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH_SEARCHDROPDOWN_NAME);
		Thread.sleep(3000);
		// clicking search Name
		GenericFunctionLibrary.selectMatchedFoundInList(ObjectRepository.ADV_SEARCH_SEARCHDROPCLICK_NAME,
				ADV_SEARCH_DROPDOWN_NAME_VALUE);
		Thread.sleep(3000);
		GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_BY_NAME, 20);
		
		/*if (Template.contains("Restricted")) {
			GenericFunctionLibrary.clearTextBox("xpath", ObjectRepository.ADV_SEARCH_BY_NAME);
			Thread.sleep(3000);
			GenericFunctionLibrary.setText("xpath", ObjectRepository.ADV_SEARCH_BY_NAME,Config.RESTRICTED + ADVANCE_SEARCH_BY_NAME+"*");
		
		} else {
			GenericFunctionLibrary.setText("xpath", ObjectRepository.ADV_SEARCH_BY_NAME, ADVANCE_SEARCH_BY_NAME +"*");
		}*/
		
		GenericFunctionLibrary.setText("xpath", ObjectRepository.ADV_SEARCH_BY_NAME, ADVANCE_SEARCH_BY_NAME +"*");
		
		Thread.sleep(3000);
		// click on Search button
		GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH);
		
		if(GenericFunctionLibrary.isAleretPresent(3)){
			GenericFunctionLibrary.getAlert();
			GenericFunctionLibrary.refreshWindow();
			Thread.sleep(3000);
			GenericFunctionLibrary.refreshWindow();
			advanceSearchModifiedPR(ADV_OBJECT_TYPE, ADV_SEARCH_CONTAINER_NAME, Template, ADV_SEARCH_STATE_STATUS, ADVANCE_SEARCH_BY_NAME);
		}
		}else{
		
		GenericFunctionLibrary.refreshWindow();
		Thread.sleep(3000);
		GenericFunctionLibrary.refreshWindow();
		advanceSearchModifiedPR(ADV_OBJECT_TYPE, ADV_SEARCH_CONTAINER_NAME, Template, ADV_SEARCH_STATE_STATUS, ADVANCE_SEARCH_BY_NAME);

		

}
		
		Thread.sleep(3000);

		// GenericFunctionLibrary.selectFirstElementInList(ObjectRepository.ADV_SEARCH_LIST);

		Boolean ADV_SEARCH_LIST_Element = GenericFunctionLibrary.isElementPresent(ObjectRepository.ADV_SEARCH_LIST_PR);
		if (ADV_SEARCH_LIST_Element == true) {

			// Get the document number from the list
		RECORD_NUMBER = GenericFunctionLibrary.getText("xpath", ObjectRepository.ADV_SEARCH_LIST_PR);
			log.info("Used Data Set is : " + RECORD_NUMBER);
			
		}

		if (RECORD_NUMBER != "") {

			// Perform search action to verify created document in 'folder
			// content'
			Thread.sleep(5000);
			String ADV_SEARCH_FIRST_LIST = ObjectRepository.ADV_SEARCH_FIRST_LIST+ RECORD_NUMBER + "')]";
			log.info("XPATH for ObjectRepository.ADV_SEARCH_FIRST_LIST is : " + ADV_SEARCH_FIRST_LIST);

			log.info("content available in search");
			// Click on the document number in the list
			// GenericFunctionLibrary.selectMatchedFoundInList(ObjectRepository.ADV_SEARCH_LIST,RECORD_VALUE.trim());
			GenericFunctionLibrary.WaitForElementPresence("xpath", ADV_SEARCH_FIRST_LIST, 20);
			GenericFunctionLibrary.clickOnElement("xpath", ADV_SEARCH_FIRST_LIST);
			

			Thread.sleep(2000);
			isPassed = true;

		} else {
			log.info("Content is not available in search");
			
			isPassed = false;
		}
		return isPassed;

	}

	public boolean advanceSearchForCAD(String ADVANCE_SEARCH_USER_NAME, String ADV_OBJECT_TYPE, String ADV_SEARCH_CONTAINER_NAME, String Template, String ADV_SEARCH_STATE_STATUS, String ADVANCE_SEARCH_BY_NAME) throws InterruptedException {

		boolean isPassed = true;
		String ADV_SEARCH_DROPDOWN_STATE_VALUE = "State";
		String ADV_SEARCH_CREATEDBY = "Created By";
		String ADV_SEARCH_DROPDOWN_NAME_VALUE = "Name";
		String	RECORD_NUMBER = "";

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
		if (GenericFunctionLibrary.isMatchFoundInList(ObjectRepository.ADV_SEARCH_SEARCHTEXT_VALUE, ADV_OBJECT_TYPE)) {
			GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_ALL, 20);
			GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH_ALL);
			Thread.sleep(1000);
			// Select check box based on ADV_SEARCH_VALUE variable
			GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_SEARCHTEXT_VALUE, 20);
			GenericFunctionLibrary.selectMatchedFoundInList(ObjectRepository.ADV_SEARCH_SEARCHTEXT_VALUE,
					ADV_OBJECT_TYPE);
		} else {
			// Click on Add link and Switch to child window
			String parentWindow = GenericFunctionLibrary.switchToChildWindow(ObjectRepository.ADV_SEARCH_ADD);
			Thread.sleep(2000);

			// Enter search text in window
			GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_WINDOW, 20);
			GenericFunctionLibrary.setText("xpath", ObjectRepository.ADV_SEARCH_WINDOW, ADV_OBJECT_TYPE);
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
			GenericFunctionLibrary.selectMatchedFoundInList(ObjectRepository.ADV_SEARCH_SEARCHTEXT_VALUE, ADV_OBJECT_TYPE);
			
		}
		// Select the add on context
		Thread.sleep(3000);

		if (GenericFunctionLibrary.isMatchFoundInList(ObjectRepository.ADV_SEARCH_SEARCHTEXT_VALUE,ADV_SEARCH_CONTAINER_NAME)) {

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

		// clicking search state drop down
		GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_SEARCHDROPDOWN, 20);
		GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH_SEARCHDROPDOWN);
		Thread.sleep(2000);

		// ******************** clicking search state ********************************************

		GenericFunctionLibrary.selectMatchedFoundInList(ObjectRepository.ADV_SEARCH_SEARCHDROPCLICK, ADV_SEARCH_DROPDOWN_STATE_VALUE);
		// clicking in creation drop down
		Thread.sleep(2000);

		GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_SEARCHDROP_VALUE, 20);

		GenericFunctionLibrary.selectDropdownByVisibleText("xpath", ObjectRepository.ADV_SEARCH_SEARCHDROP_VALUE, ADV_SEARCH_STATE_STATUS);

		Thread.sleep(2000);
		GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH_ADD_ICON);
		Thread.sleep(2000);

		
		  //clicking search CREATEDBY dropdown*********************************
		  
		 GenericFunctionLibrary.WaitForElementPresence("xpath",
		  ObjectRepository.ADV_SEARCH_SEARCHDROPDOWN_3, 30);GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.
		  ADV_SEARCH_SEARCHDROPDOWN_3); Thread.sleep(3000);
		  
		  //clicking search CREATEDBY
		  //GenericFunctionLibrary.selectMatchedFoundInList(ObjectRepository.ADV_SEARCH_SEARCHDROPDOWN_1,ADV_SEARCH_CREATEDBY); 
		  //clicking in creation drop down
		  GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_SEARCHDROPCLICK_3, 30);
		  GenericFunctionLibrary.selectMatchedFoundInList(ObjectRepository.ADV_SEARCH_SEARCHDROPCLICK_3, ADV_SEARCH_CREATEDBY);
		  Thread.sleep(3000);
		  GenericFunctionLibrary.WaitForElementPresence("id", ObjectRepository.ADV_SEARCH_FIND_BUTTON, 30);
		  GenericFunctionLibrary.clickOnElement("id",ObjectRepository.  ADV_SEARCH_FIND_BUTTON);
		  
		  String parentwindow = GenericFunctionLibrary.switchToChildWindow();
		  GenericFunctionLibrary.WaitForElementPresence("id", ObjectRepository.ADV_SEARCH_WIN_NAME, 30);
		  GenericFunctionLibrary.setText("id",ObjectRepository.ADV_SEARCH_WIN_NAME, ADVANCE_SEARCH_USER_NAME);
		  
		  Thread.sleep(3000);
		  GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_WIN_SEARCH, 30);
		  GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.ADV_SEARCH_WIN_SEARCH); Thread.sleep(3000);
		  GenericFunctionLibrary.WaitForElementPresence("xpath",ObjectRepository.ADV_SEARCH_CONTEXT_OK, 30);
		  GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.ADV_SEARCH_CONTEXT_OK);
		  
		  
		  //Switch to main window
		  GenericFunctionLibrary.switchToWindow(parentwindow);
		  Thread.sleep(3000);
		 
		Thread.sleep(2000);
		GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH_ADD_ICON);
		Thread.sleep(2000);

		// clicking search Name drop down*********************************
		GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_SEARCHDROPDOWN_NAME, 20);
		GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH_SEARCHDROPDOWN_NAME);
		Thread.sleep(3000);
		// clicking search Name
		GenericFunctionLibrary.selectMatchedFoundInList(ObjectRepository.ADV_SEARCH_SEARCHDROPCLICK_NAME,
				ADV_SEARCH_DROPDOWN_NAME_VALUE);
		Thread.sleep(3000);
		GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_BY_NAME, 20);
		
		if (Template.contains("Restricted")) {
			GenericFunctionLibrary.clearTextBox("xpath", ObjectRepository.ADV_SEARCH_BY_NAME);
			Thread.sleep(3000);
			GenericFunctionLibrary.setText("xpath", ObjectRepository.ADV_SEARCH_BY_NAME, Config.RESTRICTED + ADVANCE_SEARCH_BY_NAME+"*");
		} else {
			GenericFunctionLibrary.setText("xpath", ObjectRepository.ADV_SEARCH_BY_NAME, ADVANCE_SEARCH_BY_NAME +"*");
		}
		
		
		
		Thread.sleep(3000);
		// click on Search button
		GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH);
		
		if(GenericFunctionLibrary.isAleretPresent(3)){
			GenericFunctionLibrary.getAlert();
			GenericFunctionLibrary.refreshWindow();
			Thread.sleep(3000);
			GenericFunctionLibrary.refreshWindow();
			advanceSearch(ADV_OBJECT_TYPE, ADV_SEARCH_CONTAINER_NAME, Template, ADV_SEARCH_STATE_STATUS, ADVANCE_SEARCH_BY_NAME);
		}

		/*
		 * Thread.sleep(2000); //Search based on container
		 * GenericFunctionLibrary.setText("xpath",ObjectRepository.
		 * ADV_SEARCH_CONTAINER,ADV_SEARCH_CONTAINER_NAME); //Click on Search
		 * based on container
		 * GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.
		 * ADV_SEARCH_CONTAINER_BUTTON);
		 */
		Thread.sleep(3000);

		// GenericFunctionLibrary.selectFirstElementInList(ObjectRepository.ADV_SEARCH_LIST);

		Boolean ADV_SEARCH_LIST_Element = GenericFunctionLibrary.isElementPresent(ObjectRepository.ADV_SEARCH_LIST);
		if (ADV_SEARCH_LIST_Element == true) {

			// Get the document number from the list
		RECORD_NUMBER = GenericFunctionLibrary.getText("xpath", ObjectRepository.ADV_SEARCH_LIST);
			log.info("Record NUMBER is : " + RECORD_NUMBER);
			
		}

		if (RECORD_NUMBER != "") {

			// Perform search action to verify created document in 'folder
			// content'
			Thread.sleep(5000);
			String ADV_SEARCH_FIRST_LIST = ObjectRepository.ADV_SEARCH_FIRST_LIST+ RECORD_NUMBER + "')]";
			log.info("XPATH for ObjectRepository.ADV_SEARCH_FIRST_LIST is : " + ADV_SEARCH_FIRST_LIST);

			log.info("content available in search");
			// Click on the document number in the list
			// GenericFunctionLibrary.selectMatchedFoundInList(ObjectRepository.ADV_SEARCH_LIST,RECORD_VALUE.trim());
			GenericFunctionLibrary.WaitForElementPresence("xpath", ADV_SEARCH_FIRST_LIST, 20);
			GenericFunctionLibrary.clickOnElement("xpath", ADV_SEARCH_FIRST_LIST);
			//String versionNumber = GenericFunctionLibrary.getFileName(ObjectRepository.VERSION_NUMBER_FILENAME);
			

			Thread.sleep(2000);
			isPassed = true;

		} else {
			log.info("Content is not available in search");
			isPassed = false;
		}
		return isPassed;

	}
	public void advanceSearch(String ADV_OBJECT_TYPE, String ADV_SEARCH_CONTAINER_NAME, String Template, String ADV_SEARCH_STATE_STATUS, String ADVANCE_SEARCH_BY_NAME) throws InterruptedException{
		
		String ADV_SEARCH_DROPDOWN_STATE_VALUE = "State";
	//	String ADV_SEARCH_CREATEDBY = "Created By";
		String ADV_SEARCH_DROPDOWN_NAME_VALUE = "Name";
		

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
		if (GenericFunctionLibrary.isElementPresent(ObjectRepository.ADV_SEARCH_EDIT_CRITERIA)) {
			GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH_EDIT_CRITERIA);
			Thread.sleep(2000);
		}
		if (GenericFunctionLibrary.isMatchFoundInList(ObjectRepository.ADV_SEARCH_SEARCHTEXT_VALUE, ADV_OBJECT_TYPE)) {
			GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_ALL, 20);
			GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH_ALL);
			Thread.sleep(1000);
			// Select check box based on ADV_SEARCH_VALUE variable
			GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_SEARCHTEXT_VALUE, 20);
			GenericFunctionLibrary.selectMatchedFoundInList(ObjectRepository.ADV_SEARCH_SEARCHTEXT_VALUE, ADV_OBJECT_TYPE);
		} else {
			// Click on Add link and Switch to child window
			String parentWindow = GenericFunctionLibrary.switchToChildWindow(ObjectRepository.ADV_SEARCH_ADD);
			Thread.sleep(2000);

			// Enter search text in window
			GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_WINDOW, 20);
			GenericFunctionLibrary.setText("xpath", ObjectRepository.ADV_SEARCH_WINDOW, ADV_OBJECT_TYPE);
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
			GenericFunctionLibrary.selectMatchedFoundInList(ObjectRepository.ADV_SEARCH_SEARCHTEXT_VALUE,
					ADV_OBJECT_TYPE);
		}
		// Select the add on context
		Thread.sleep(3000);

		if (GenericFunctionLibrary.isMatchFoundInList(ObjectRepository.ADV_SEARCH_SEARCHTEXT_VALUE,ADV_SEARCH_CONTAINER_NAME)) {

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

		/*	if (Template.contains("Library")) {

				GenericFunctionLibrary.selectDropdownByVisibleText("id", ObjectRepository.ADV_SEARCH_CONTEXT_DROPDOWN,
						"Library");
			} else if (Template.contains("Product")) {
				GenericFunctionLibrary.selectDropdownByVisibleText("id", ObjectRepository.ADV_SEARCH_CONTEXT_DROPDOWN,
						"Product");
			}*/

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

		// clicking search state drop down
		GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_SEARCHDROPDOWN, 20);
		GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH_SEARCHDROPDOWN);
		Thread.sleep(2000);

		// ******************** clicking search state
		// ********************************************

		GenericFunctionLibrary.selectMatchedFoundInList(ObjectRepository.ADV_SEARCH_SEARCHDROPCLICK, ADV_SEARCH_DROPDOWN_STATE_VALUE);
		// clicking in creation drop down
		Thread.sleep(2000);

		GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_SEARCHDROP_VALUE, 20);

		GenericFunctionLibrary.selectDropdownByVisibleText("xpath", ObjectRepository.ADV_SEARCH_SEARCHDROP_VALUE,
				ADV_SEARCH_STATE_STATUS);

		Thread.sleep(2000);
		GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH_ADD_ICON);
		Thread.sleep(2000);

		/*
		 * //clicking search CREATEDBY dropdown*********************************
		 * 
		 * GenericFunctionLibrary.WaitForElementPresence("xpath",
		 * ObjectRepository.ADV_SEARCH_SEARCHDROPDOWN_3, 30);
		 * GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.
		 * ADV_SEARCH_SEARCHDROPDOWN_3); Thread.sleep(3000);
		 * 
		 * //clicking search CREATEDBY
		 * //GenericFunctionLibrary.selectMatchedFoundInList(ObjectRepository.
		 * ADV_SEARCH_SEARCHDROPDOWN_1,ADV_SEARCH_CREATEDBY); //clicking in
		 * creation drop down
		 * GenericFunctionLibrary.WaitForElementPresence("xpath",
		 * ObjectRepository.ADV_SEARCH_SEARCHDROPCLICK_3, 30);
		 * GenericFunctionLibrary.selectMatchedFoundInList(ObjectRepository.
		 * ADV_SEARCH_SEARCHDROPCLICK_3, ADV_SEARCH_CREATEDBY);
		 * Thread.sleep(3000);
		 * GenericFunctionLibrary.WaitForElementPresence("id",
		 * ObjectRepository.ADV_SEARCH_FIND_BUTTON, 30);
		 * GenericFunctionLibrary.clickOnElement("id",ObjectRepository.
		 * ADV_SEARCH_FIND_BUTTON);
		 * 
		 * GenericFunctionLibrary.switchToChildWindow();
		 * GenericFunctionLibrary.WaitForElementPresence("id",
		 * ObjectRepository.ADV_SEARCH_WIN_NAME, 30);
		 * GenericFunctionLibrary.setText("id",ObjectRepository.
		 * ADV_SEARCH_WIN_NAME, ADVANCE_SEARCH_WIN_NAME);
		 * 
		 * Thread.sleep(3000);
		 * GenericFunctionLibrary.WaitForElementPresence("xpath",
		 * ObjectRepository.ADV_SEARCH_WIN_SEARCH, 30);
		 * GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.
		 * ADV_SEARCH_WIN_SEARCH); Thread.sleep(3000);
		 * GenericFunctionLibrary.WaitForElementPresence("xpath",
		 * ObjectRepository.ADV_SEARCH_CONTEXT_OK, 30);
		 * GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.
		 * ADV_SEARCH_CONTEXT_OK);
		 * 
		 * 
		 * //Switch to main window
		 * GenericFunctionLibrary.switchToWindow(parentWindow);
		 * Thread.sleep(3000);
		 */

		Thread.sleep(2000);
		GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH_ADD_ICON);
		Thread.sleep(2000);

		// clicking search Name drop down*********************************
		GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_SEARCHDROPDOWN_NAME, 20);
		GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH_SEARCHDROPDOWN_NAME);
		Thread.sleep(3000);
		// clicking search Name
		GenericFunctionLibrary.selectMatchedFoundInList(ObjectRepository.ADV_SEARCH_SEARCHDROPCLICK_NAME,
				ADV_SEARCH_DROPDOWN_NAME_VALUE);
		Thread.sleep(3000);
		GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_BY_NAME, 20);
		if (Template.contains("Restricted")) {

			//GenericFunctionLibrary.setText("xpath", ObjectRepository.ADV_SEARCH_BY_NAME, Config.RESTRICTED + ADVANCE_SEARCH_BY_NAME+"*");
			GenericFunctionLibrary.setText("xpath", ObjectRepository.ADV_SEARCH_BY_NAME, Config.RESTRICTED + "*");
		} else {
			GenericFunctionLibrary.setText("xpath", ObjectRepository.ADV_SEARCH_BY_NAME, ADVANCE_SEARCH_BY_NAME+"*");
		}
		Thread.sleep(3000);
		// click on Search button
		GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH);
	
	}
	
	public boolean advanceSearchByName(String ADV_OBJECT_TYPE, String ADV_SEARCH_CONTAINER_NAME, String Template, String ADVANCE_SEARCH_BY_NAME) throws InterruptedException {

		boolean isPassed = true;
		
		String RECORD_VALUE = "searchabc";

		log.info("Advance search starts");

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
		if (GenericFunctionLibrary.isMatchFoundInList(ObjectRepository.ADV_SEARCH_SEARCHTEXT_VALUE, ADV_OBJECT_TYPE)) {
			GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_ALL, 20);
			GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH_ALL);
			Thread.sleep(1000);
			// Select check box based on ADV_SEARCH_VALUE variable
			GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_SEARCHTEXT_VALUE, 20);
			GenericFunctionLibrary.selectMatchedFoundInList(ObjectRepository.ADV_SEARCH_SEARCHTEXT_VALUE,
					ADV_OBJECT_TYPE);
		} else {
			// Click on Add link and Switch to child window
			String parentWindow = GenericFunctionLibrary.switchToChildWindow(ObjectRepository.ADV_SEARCH_ADD);
			Thread.sleep(2000);

			// Enter search text in window
			GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_WINDOW, 20);
			GenericFunctionLibrary.setText("xpath", ObjectRepository.ADV_SEARCH_WINDOW, ADV_OBJECT_TYPE);
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
			GenericFunctionLibrary.selectMatchedFoundInList(ObjectRepository.ADV_SEARCH_SEARCHTEXT_VALUE,
					ADV_OBJECT_TYPE);

		}

		// Select the add on context
		Thread.sleep(3000);

		if (GenericFunctionLibrary.isMatchFoundInList(ObjectRepository.ADV_SEARCH_SEARCHTEXT_VALUE,
				ADV_SEARCH_CONTAINER_NAME)) {

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
		/*
		// clicking search state drop down
		GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_SEARCHDROPDOWN, 30);
		GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH_SEARCHDROPDOWN);
		Thread.sleep(2000);
		
		// clicking search state ********************************************
		
		GenericFunctionLibrary.selectMatchedFoundInList(ObjectRepository.ADV_SEARCH_SEARCHDROPCLICK, ADV_SEARCH_STATE);
		// clicking in creation drop down
		Thread.sleep(2000);
		//if(!ADV_SEARCH_STATE_STATUS.equals("")){
		GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_SEARCHDROP_VALUE, 30);
		
		GenericFunctionLibrary.selectDropdownByVisibleText("xpath", ObjectRepository.ADV_SEARCH_SEARCHDROP_VALUE, ADV_SEARCH_STATE_STATUS);
		// GenericFunctionLibrary.selectDropdownByIndex("xpath", ObjectRepository.ADV_SEARCH_SEARCHDROP_VALUE, 3);
	//}
		Thread.sleep(2000);
		GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH_ADD_ICON);
		Thread.sleep(2000);
		
	*/
	
		/*
		 * //clicking search CREATEDBY dropdown*********************************
		 * 
		 * GenericFunctionLibrary.WaitForElementPresence("xpath",
		 * ObjectRepository.ADV_SEARCH_SEARCHDROPDOWN_3, 30);
		 * GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.
		 * ADV_SEARCH_SEARCHDROPDOWN_3); Thread.sleep(3000);
		 * 
		 * //clicking search CREATEDBY
		 * //GenericFunctionLibrary.selectMatchedFoundInList(ObjectRepository.
		 * ADV_SEARCH_SEARCHDROPDOWN_1,ADV_SEARCH_CREATEDBY); //clicking in
		 * creation drop down
		 * GenericFunctionLibrary.WaitForElementPresence("xpath",
		 * ObjectRepository.ADV_SEARCH_SEARCHDROPCLICK_3, 30);
		 * GenericFunctionLibrary.selectMatchedFoundInList(ObjectRepository.
		 * ADV_SEARCH_SEARCHDROPCLICK_3, ADV_SEARCH_CREATEDBY);
		 * Thread.sleep(3000);
		 * GenericFunctionLibrary.WaitForElementPresence("id",
		 * ObjectRepository.ADV_SEARCH_FIND_BUTTON, 30);
		 * GenericFunctionLibrary.clickOnElement("id",ObjectRepository.
		 * ADV_SEARCH_FIND_BUTTON);
		 * 
		 * GenericFunctionLibrary.switchToChildWindow();
		 * GenericFunctionLibrary.WaitForElementPresence("id",
		 * ObjectRepository.ADV_SEARCH_WIN_NAME, 30);
		 * GenericFunctionLibrary.setText("id",ObjectRepository.
		 * ADV_SEARCH_WIN_NAME, ADVANCE_SEARCH_WIN_NAME);
		 * 
		 * Thread.sleep(3000);
		 * GenericFunctionLibrary.WaitForElementPresence("xpath",
		 * ObjectRepository.ADV_SEARCH_WIN_SEARCH, 30);
		 * GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.
		 * ADV_SEARCH_WIN_SEARCH); Thread.sleep(3000);
		 * GenericFunctionLibrary.WaitForElementPresence("xpath",
		 * ObjectRepository.ADV_SEARCH_CONTEXT_OK, 30);
		 * GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.
		 * ADV_SEARCH_CONTEXT_OK);
		 * 
		 * 
		 * //Switch to main window
		 * GenericFunctionLibrary.switchToWindow(parentWindow);
		 * Thread.sleep(3000);
	
 */
		/*Thread.sleep(2000);
		GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH_ADD_ICON);
		Thread.sleep(2000);
	*/
		Thread.sleep(3000);
		if(GenericFunctionLibrary.isElementPresent(ObjectRepository.CN_ADVANCE_SEARCH_TEXT)){
		GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.CN_ADVANCE_SEARCH_TEXT, 20);
		GenericFunctionLibrary.clearTextBox("xpath", ObjectRepository.CN_ADVANCE_SEARCH_TEXT);
		Thread.sleep(3000);
		
		if (Template.contains("Restricted")) {

			GenericFunctionLibrary.setText("xpath", ObjectRepository.CN_ADVANCE_SEARCH_TEXT,
					Config.RESTRICTED + ADVANCE_SEARCH_BY_NAME+"*");
		} else {
			GenericFunctionLibrary.setText("xpath", ObjectRepository.CN_ADVANCE_SEARCH_TEXT, ADVANCE_SEARCH_BY_NAME +"*");
		}
		}else{
			GenericFunctionLibrary.getAlert();
			GenericFunctionLibrary.refreshWindow();
			Thread.sleep(3000);
			GenericFunctionLibrary.refreshWindow();
			advanceSearchByName(ADV_OBJECT_TYPE, ADV_SEARCH_CONTAINER_NAME, Template, ADVANCE_SEARCH_BY_NAME);
		}
		
		//GenericFunctionLibrary.setText("xpath",ObjectRepository.CN_ADVANCE_SEARCH_TEXT, ADVANCE_SEARCH_BY_NAME+"*"); 

		/*
		// clicking search Name drop down*********************************
		GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_SEARCHDROPDOWN_NAME, 30);
		GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH_SEARCHDROPDOWN_NAME);
		Thread.sleep(3000);
		// clicking search Name
		GenericFunctionLibrary.selectMatchedFoundInList(ObjectRepository.ADV_SEARCH_SEARCHDROPCLICK_NAME,
				ADV_SEARCH_NAME);
		Thread.sleep(3000);
		GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_BY_NAME, 30);
		GenericFunctionLibrary.setText("xpath", ObjectRepository.ADV_SEARCH_BY_NAME, ADVANCE_SEARCH_BY_NAME);
*/
		Thread.sleep(3000);
		// click on Search button
		GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH);

		
		if(GenericFunctionLibrary.isAleretPresent(3)){
			GenericFunctionLibrary.getAlert();
			GenericFunctionLibrary.refreshWindow();
			Thread.sleep(3000);
			GenericFunctionLibrary.refreshWindow();
			advanceSearchByName(ADV_OBJECT_TYPE, ADV_SEARCH_CONTAINER_NAME, Template, ADVANCE_SEARCH_BY_NAME);
		}
		
		
		/*
		 * Thread.sleep(2000); //Search based on container
		 * GenericFunctionLibrary.setText("xpath",ObjectRepository.
		 * ADV_SEARCH_CONTAINER,ADV_SEARCH_CONTAINER_NAME); //Click on Search
		 * based on container
		 * GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository. ADV_SEARCH_CONTAINER_BUTTON);
		 */
		Thread.sleep(3000);

		// GenericFunctionLibrary.selectFirstElementInList(ObjectRepository.ADV_SEARCH_LIST);

		Boolean ADV_SEARCH_LIST_Element = GenericFunctionLibrary.isElementPresent(ObjectRepository.ADV_SEARCH_LIST);
		if (ADV_SEARCH_LIST_Element == true) {

			// Get the document number from the list
			RECORD_VALUE = GenericFunctionLibrary.getText("xpath", ObjectRepository.ADV_SEARCH_LIST);
			log.info("Record NUMBER is : " + RECORD_VALUE);
		}

		if (RECORD_VALUE != "searchabc") {

			// Perform search action to verify created document in 'folder
			// content'
			Thread.sleep(5000);
			String ADV_SEARCH_FIRST_LIST = "//div[@class='x-grid3-cell-inner x-grid3-col-number']/a[contains(text(),'"
					+ RECORD_VALUE + "')]";
		//	log.info("XPATH for ObjectRepository.ADV_SEARCH_FIRST_LIST is : " + ADV_SEARCH_FIRST_LIST);

			log.info("content available in search");
			// Click on the document number in the list
			// GenericFunctionLibrary.selectMatchedFoundInList(ObjectRepository.ADV_SEARCH_LIST,RECORD_VALUE.trim());
			GenericFunctionLibrary.WaitForElementPresence("xpath", ADV_SEARCH_FIRST_LIST, 20);
			GenericFunctionLibrary.clickOnElement("xpath", ADV_SEARCH_FIRST_LIST);

			Thread.sleep(2000);
			isPassed = true;

		} else {
			log.info("Content is not available in search");
			
			isPassed = false;
		}
		return isPassed;

	}
	
	
	/*public boolean advanceSearchByNamenew(String ADV_OBJECT_TYPE, String ADV_SEARCH_CONTAINER_NAME, String Template, String ADVANCE_SEARCH_BY_NAME) throws InterruptedException {

		boolean isPassed = true;
	
		//String RECORD_VALUE = "searchabc";

		log.info("Advance search starts");

		Thread.sleep(2000);
		// searching expected content

		GenericFunctionLibrary.WaitForElementPresence("id", ObjectRepository.ADV_SEARCH_BUTTON_XPATH, 30);
		GenericFunctionLibrary.clickOnElement("id", ObjectRepository.ADV_SEARCH_BUTTON_XPATH);
		log.info("Clicked on Search Link from Home page");
		Thread.sleep(2000);
		
		// Clicking on Advanced Search Tab

		GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH_TAB_XPATH);
		Thread.sleep(3000);
		
		//clicking on Edit Search Criteria Link
		if(GenericFunctionLibrary.isElementPresent("//div[@id='editSearchCriteriaLink']/a")){
		GenericFunctionLibrary.clickOnElement("xpath", "//div[@id='editSearchCriteriaLink']/a");
		Thread.sleep(2000);
		}

		// Switch to child window
		String parentWindow = GenericFunctionLibrary.switchToChildWindow(ObjectRepository.ADV_SEARCH_ADD);
		Thread.sleep(2000);

		// Enter search text in window
		GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_WINDOW, 30);
		GenericFunctionLibrary.setText("xpath", ObjectRepository.ADV_SEARCH_WINDOW, ObjectType);
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
				GenericFunctionLibrary.selectMatchedFoundInList(ObjectRepository.ADV_SEARCH_SEARCHTEXT_VALUE, ObjectType);
			
				Thread.sleep(3000);
				GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.CN_ADVANCE_SEARCH_TEXT, 30);
				GenericFunctionLibrary.clearTextBox("xpath", ObjectRepository.CN_ADVANCE_SEARCH_TEXT);
				Thread.sleep(3000);
				GenericFunctionLibrary.setText("xpath",ObjectRepository.CN_ADVANCE_SEARCH_TEXT, ADVANCE_SEARCH_BY_NAME); 
				Thread.sleep(3000);
				
				Thread.sleep(3000);
				// click on Search button
				GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH);
				Thread.sleep(3000);

		// GenericFunctionLibrary.selectFirstElementInList(ObjectRepository.ADV_SEARCH_LIST);
		String RECORD_NUMBER = "";
		Boolean ADV_SEARCH_LIST_Element = GenericFunctionLibrary.isElementPresent(ObjectRepository.ADV_SEARCH_LIST);
		if (ADV_SEARCH_LIST_Element == true) {

			// Get the document number from the list
		RECORD_NUMBER = GenericFunctionLibrary.getText("xpath", ObjectRepository.ADV_SEARCH_LIST);
			log.info("Record NUMBER is : " + RECORD_NUMBER);
		}

		if (RECORD_NUMBER != "") {

			// Perform search action to verify created document in 'folder
			// content'
			Thread.sleep(5000);
			String ADV_SEARCH_FIRST_LIST = ObjectRepository.ADV_SEARCH_FIRST_LIST+ RECORD_NUMBER + "')]";
			log.info("XPATH for ObjectRepository.ADV_SEARCH_FIRST_LIST is : " + ADV_SEARCH_FIRST_LIST);

			log.info("content available in search");
			// Click on the document number in the list
			// GenericFunctionLibrary.selectMatchedFoundInList(ObjectRepository.ADV_SEARCH_LIST,RECORD_VALUE.trim());
			GenericFunctionLibrary.WaitForElementPresence("xpath", ADV_SEARCH_FIRST_LIST, 20);
			GenericFunctionLibrary.clickOnElement("xpath", ADV_SEARCH_FIRST_LIST);
			

			Thread.sleep(2000);
			isPassed = true;

		} else {
			log.info("Content is not available in search");
			isPassed = false;
		}
		return isPassed;

	}*/
	
	
	
	public boolean AdvSearchwithContext(String filename, String ObjectType, String template, String ContextName) throws InterruptedException{
		
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
				
				GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.CN_ADVANCE_SEARCH_TEXT, 30);
				Thread.sleep(2000);
				GenericFunctionLibrary.clearTextBox("xpath", ObjectRepository.CN_ADVANCE_SEARCH_TEXT);
				Thread.sleep(3000);
				GenericFunctionLibrary.setText("xpath",ObjectRepository.CN_ADVANCE_SEARCH_TEXT, filename); 
				Thread.sleep(3000);
				
				
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
					Thread.sleep(2000);
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

	
	

	//Advance search method
	//Change Notice, Name-Change notice
	public String changeNoticeAdvancesearch(String CN_ADV_SEARCH_VALUE, String ChangeNoticeName)throws InterruptedException {
	String CN_NUMBER_VALUE="EMPTY";

	log.info("Change Notice Advance search starts...."); 
	Thread.sleep(2000); 
	//Click on advance search button/icon
	
	GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_BUTTON_XPATH, 30);
	GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH_BUTTON_XPATH);
	
//	GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.CN_ADV_SEARCH_BUTTON, 30);
//	GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.CN_ADV_SEARCH_BUTTON);
			
	Thread.sleep(2000); 

	Thread.sleep(2000); 
	//click on ALL item check box
	GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_ALL, 30);
	GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.ADV_SEARCH_ALL);
	Thread.sleep(1000); 
	//Select check box based on ADV_SEARCH_VALUE variable
	GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_SEARCHTEXT_VALUE, 30);
	GenericFunctionLibrary.selectMatchedFoundInList(ObjectRepository.ADV_SEARCH_SEARCHTEXT_VALUE,CN_ADV_SEARCH_VALUE);
	Thread.sleep(3000);
	GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.CN_ADVANCE_SEARCH_TEXT, 30);
	GenericFunctionLibrary.setText("xpath",ObjectRepository.CN_ADVANCE_SEARCH_TEXT, ChangeNoticeName);

		Thread.sleep(3000);
		// click on Search button
		GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH);

		Thread.sleep(3000);

		Boolean ADV_SEARCH_LIST_Element = GenericFunctionLibrary.isElementPresent(ObjectRepository.ADV_SEARCH_LIST);
		if (ADV_SEARCH_LIST_Element == true) {
			// Get the document number from the list
			CN_NUMBER_VALUE = GenericFunctionLibrary.getText("xpath", ObjectRepository.ADV_SEARCH_LIST);
			log.info("Change notice NUMBER is : " + CN_NUMBER_VALUE);
		}

		return CN_NUMBER_VALUE;
	}
	//Advance search method
	 //SESA439756_DM,Describe Document,Verif_A_Demo,Product & In Creation
	public boolean advancesearch(String ADVANCE_SEARCH_WIN_NAME,String ADV_SEARCH_VALUE,String ADV_SEARCH_CONTAINER_NAME,String ADV_SEARCH_CONTENT_DROPDOWN_VALUE,String ADV_SEARCH_STATE_STATUS,String ADVANCE_SEARCH_BY_NAME)throws InterruptedException {

	boolean isPassed = true;
	String ADV_SEARCH_STATE="State";
	String ADV_SEARCH_CREATEDBY="Created By";
	String ADV_SEARCH_NAME="Name";
	String RECORD_VALUE="searchabc";

	log.info("Advance search starts............"); 

	Thread.sleep(2000); 
	//Click on advance search button/icon
	GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_BUTTON, 30);
	GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.ADV_SEARCH_BUTTON);
			
	Thread.sleep(2000); 
	//Switch to child window
	String parentWindow = GenericFunctionLibrary.switchToChildWindow(ObjectRepository.ADV_SEARCH_ADD);
	Thread.sleep(2000);
	//Enter search text in window 
	GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_WINDOW, 30);
	GenericFunctionLibrary.setText("xpath", ObjectRepository.ADV_SEARCH_WINDOW, ADV_SEARCH_VALUE);
	Thread.sleep(1000);
	//search text box
	GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.ADV_SEARCH_WINDOW_CLICK);
	Thread.sleep(2000);
	//Add searched check box 
	GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_CHECKBOX, 30);
	GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.ADV_SEARCH_CHECKBOX);
	Thread.sleep(2000);
	GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.ADV_SEARCH_OK);
			
	Thread.sleep(2000);
	//Switch to main window
	GenericFunctionLibrary.switchToWindow(parentWindow);
			
	/*Thread.sleep(2000); 
	//Enter the keyword
	GenericFunctionLibrary.setText("id", ObjectRepository.ADV_SEARCH_SEARCHTEXTBOX_ID, "*");
	*/
	Thread.sleep(2000); 
	//click on ALL item check box
	GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_ALL, 30);
	GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.ADV_SEARCH_ALL);
	Thread.sleep(1000); 
	//Select check box based on ADV_SEARCH_VALUE variable
	GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_SEARCHTEXT_VALUE, 30);
	GenericFunctionLibrary.selectMatchedFoundInList(ObjectRepository.ADV_SEARCH_SEARCHTEXT_VALUE,ADV_SEARCH_VALUE);
			
	//Select the add on context
	Thread.sleep(3000); 
	//Switch to child window   String parentWindow_context 
	GenericFunctionLibrary.switchToChildWindow(ObjectRepository.ADV_SEARCH_CONTEXT_ADD);
	Thread.sleep(2000); 
	GenericFunctionLibrary.WaitForElementPresence("id", ObjectRepository.ADV_SEARCH_CONTEXT_DROPDOWN, 30);
	GenericFunctionLibrary.selectDropdownByVisibleText("id", ObjectRepository.ADV_SEARCH_CONTEXT_DROPDOWN, ADV_SEARCH_CONTENT_DROPDOWN_VALUE);
	Thread.sleep(2000);
	//Enter container name in context search  
	GenericFunctionLibrary.setText("xpath", ObjectRepository.ADV_SEARCH_CONTEXT_NAME,
					ADV_SEARCH_CONTAINER_NAME);
	Thread.sleep(1000);
	//click on search button
	GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.ADV_SEARCH_CONTEXT_SEARCH);
	Thread.sleep(2000);
	GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_CONTEXT_CHECKBOX, 30);
	GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.ADV_SEARCH_CONTEXT_CHECKBOX);
	Thread.sleep(2000);		

	GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.ADV_SEARCH_CONTEXT_OK);
	Thread.sleep(2000);	
	//Switch to main window
	GenericFunctionLibrary.switchToWindow(parentWindow);
	Thread.sleep(3000);	
	//clicking search state drop down
	GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_SEARCHDROPDOWN, 30);
	GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.ADV_SEARCH_SEARCHDROPDOWN);
	Thread.sleep(2000);
	   //clicking search state ********************************************
	GenericFunctionLibrary.selectMatchedFoundInList(ObjectRepository.ADV_SEARCH_SEARCHDROPCLICK, ADV_SEARCH_STATE);
		//clicking in creation drop down 
	Thread.sleep(2000);
	GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_SEARCHDROP_VALUE, 30);
	GenericFunctionLibrary.selectDropdownByVisibleText("xpath", ObjectRepository.ADV_SEARCH_SEARCHDROP_VALUE, ADV_SEARCH_STATE_STATUS);
		//GenericFunctionLibrary.selectDropdownByIndex("xpath", ObjectRepository.ADV_SEARCH_SEARCHDROP_VALUE, 3);
		
	Thread.sleep(2000);
	GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.ADV_SEARCH_ADD_ICON);
	Thread.sleep(2000);
			
			
		//clicking search CREATEDBY drop down*********************************
	GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_SEARCHDROPDOWN_3, 30);
	GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.ADV_SEARCH_SEARCHDROPDOWN_3);
	Thread.sleep(3000);
	
		 //clicking search CREATEDBY 
			//GenericFunctionLibrary.selectMatchedFoundInList(ObjectRepository.ADV_SEARCH_SEARCHDROPDOWN_1,ADV_SEARCH_CREATEDBY);
			//clicking in creation drop down 
	
	GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_SEARCHDROPCLICK_3, 30);
	GenericFunctionLibrary.selectMatchedFoundInList(ObjectRepository.ADV_SEARCH_SEARCHDROPCLICK_3, ADV_SEARCH_CREATEDBY);
	Thread.sleep(3000);
	GenericFunctionLibrary.WaitForElementPresence("id", ObjectRepository.ADV_SEARCH_FIND_BUTTON, 30);
	GenericFunctionLibrary.clickOnElement("id",ObjectRepository.ADV_SEARCH_FIND_BUTTON);
	
	GenericFunctionLibrary.switchToChildWindow();
	GenericFunctionLibrary.WaitForElementPresence("id", ObjectRepository.ADV_SEARCH_WIN_NAME, 30);
	GenericFunctionLibrary.setText("id",ObjectRepository.ADV_SEARCH_WIN_NAME, ADVANCE_SEARCH_WIN_NAME);

	Thread.sleep(3000);
	GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_WIN_SEARCH, 30);
	GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.ADV_SEARCH_WIN_SEARCH);
	Thread.sleep(3000);
	GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_CONTEXT_OK, 30);
	GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.ADV_SEARCH_CONTEXT_OK);

		
		//Switch to main window
	GenericFunctionLibrary.switchToWindow(parentWindow);
	Thread.sleep(3000);
		
			
	Thread.sleep(2000);
	GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.ADV_SEARCH_ADD_ICON);
	Thread.sleep(2000);
		
		//clicking search Name drop down*********************************
	GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_SEARCHDROPDOWN_NAME, 30);
	GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.ADV_SEARCH_SEARCHDROPDOWN_NAME);
	Thread.sleep(3000);
		 //clicking search Name 
	GenericFunctionLibrary.selectMatchedFoundInList(ObjectRepository.ADV_SEARCH_SEARCHDROPCLICK_NAME, ADV_SEARCH_NAME);
	Thread.sleep(3000);
	GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_BY_NAME, 30);
	GenericFunctionLibrary.setText("xpath",ObjectRepository.ADV_SEARCH_BY_NAME, ADVANCE_SEARCH_BY_NAME);
		
	Thread.sleep(3000);
	//click on Search button
	GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.ADV_SEARCH);
		
		/*Thread.sleep(2000);
		//Search based on container
		GenericFunctionLibrary.setText("xpath",ObjectRepository.ADV_SEARCH_CONTAINER,ADV_SEARCH_CONTAINER_NAME);
		//Click on Search based on container
		GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.ADV_SEARCH_CONTAINER_BUTTON);
		*/
	Thread.sleep(3000);		
		
	//	GenericFunctionLibrary.selectFirstElementInList(ObjectRepository.ADV_SEARCH_LIST);
		
	Boolean ADV_SEARCH_LIST_Element=GenericFunctionLibrary.isElementPresent(ObjectRepository.ADV_SEARCH_LIST);
	if(ADV_SEARCH_LIST_Element==true){
			
		//Get the document number from the list
	RECORD_VALUE=GenericFunctionLibrary.getText("xpath", ObjectRepository.ADV_SEARCH_LIST);
	 log.info("Record NUMBER is : "+RECORD_VALUE);
	 
	}
	   
	if (RECORD_VALUE!="searchabc") {
		
		// Perform search action to verify created document in 'folder content'
		Thread.sleep(5000);
		String ADV_SEARCH_FIRST_LIST= ObjectRepository.ADV_SEARCH_FIRST_LIST+ RECORD_VALUE + "')]"; 
		log.info("XPATH for ObjectRepository.ADV_SEARCH_FIRST_LIST is : "+ADV_SEARCH_FIRST_LIST);
		
		
		log.info("content available in search");
		 //Click on the document number in the list
		//GenericFunctionLibrary.selectMatchedFoundInList(ObjectRepository.ADV_SEARCH_LIST,RECORD_VALUE.trim());
		GenericFunctionLibrary.WaitForElementPresence("xpath", ADV_SEARCH_FIRST_LIST, 30);
		GenericFunctionLibrary.clickOnElement("xpath",ADV_SEARCH_FIRST_LIST);
		
		Thread.sleep(2000);	
		isPassed = true;

	} else {
		log.info("Content is not available in search");
		isPassed = false;
		}
	return isPassed;

	}
	
	public void advancesearch() throws InterruptedException 
	{
		//SESA439756_DM,Describe Document,Verif_A_Demo,Product, Increation and File Name Restricted*
		boolean Advancesearchstatus=advancesearch(ContentRepository.ADVANCE_SEARCH_BY_USER,ContentRepository.ADV_SEARCH_OBJECT_TYPE,ContentRepository.ADV_SEARCH_CONTAINER_NAME,ContentRepository.ADV_SEARCH_CONTEXT_TYPE_DROPDOWN_VALUE,ContentRepository.ADV_SEARCH_STATE_STATUS,ContentRepository.ADVANCE_SEARCH_BY_NAME);
		log.info("Status is : "+ Advancesearchstatus);
	
	}
	
	/**************************************Manufacturer Part*************************
	 * 
	 * */	
	
	
	
	
	public String[] createManufacturerPartAccessRightValidation(String objectType) throws InterruptedException {

		String result[] = new String[2];
		result[0] = "";
		result[1] = "";

		Thread.sleep(6000);

		String parentWindow = "";
		parentWindow = GenericFunctionLibrary.switchToChildWindow(ObjectRepository.CREATE_PART_ICON);
		log.info(GenericFunctionLibrary.getPageTitle());

		Thread.sleep(5000);
		String expetedAlertMessage = "ATTENTION";

		if (GenericFunctionLibrary.isAleretPresent(2)) {
			log.info("Alert is Present");

			String ActualErrormessage = GenericFunctionLibrary.getAlert();

			if (ActualErrormessage.contains(expetedAlertMessage)) {
				log.info(" Alert Message is : " + ActualErrormessage);
				result[0] = "Un-Authorized Successful";
				String Comment = "Throwing an alert " + ActualErrormessage + " hence user is not Authorized to create "
						+ objectType;
				result[1] = Comment;

				GenericFunctionLibrary.switchToWindow(parentWindow);
			}

		} else {

			if (GenericFunctionLibrary.isMatchFoundInColumn(ObjectRepository.PART_OPTONS_XPATH, objectType)) {
				Thread.sleep(2000);
				// selecting values from dropdown
				GenericFunctionLibrary.selectDropdownByVisibleText("xpath", ObjectRepository.PART_OBJECT_TYPE,
						objectType);

				Thread.sleep(5000);
				// Click on manufacturerID search

				String parentWindow1 = GenericFunctionLibrary.switchToChildWindow(ObjectRepository.MANUFACTURE_SEARCH);

				// GenericFunctionLibrary.clickOnElement("xpath",
				// ObjectRepository.MANUFACTURE_SEARCH);

				Thread.sleep(5000);
				// Search Organization Name with constant value 'V*'
				GenericFunctionLibrary.setText("xpath", ObjectRepository.MANUFACTURE_SEARCHTEXT,
						ContentRepository.manufsearchText);

				// Search the Manufacture Search Submit button
				GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.MANUFACTURE_SEARCHSUBMIT);

				Thread.sleep(5000);
				// Select Radio button from the list of product
				GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.MANUFACTURE_SEARCHRADIO);

				// Search the ManufactureSearch ok button
				GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.MANUFACTURE_SEARCHOK);

				// Moving on parent window
				GenericFunctionLibrary.switchToWindow(parentWindow1);

				GenericFunctionLibrary.selectDropdownByVisibleText("xpath", ObjectRepository.MANUF_PART_END_ITEM,
						"Yes");

				Thread.sleep(5000);
				// Send value to manufacturer Number editable text box
				GenericFunctionLibrary.setText("xpath", ObjectRepository.MANUF_NUMBER,
						"MP" + System.currentTimeMillis());

				// Enter name of manufacture part
				GenericFunctionLibrary.setText("xpath", ObjectRepository.NAMEFIELD,
						Config.MANUFACTURER_PART_NAME + System.currentTimeMillis());

				String ManufacturerPartDocument = GenericFunctionLibrary.getAttributeValue("xpath",
						ObjectRepository.NAMEFIELD, "value");
				log.info("Value in Name Field : " + ManufacturerPartDocument);

				// Select the Manufacture location part
				GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.LOCATION);

				
				// clicking on 'Finish' button to create 'Manufacturer Part' document
				GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.FINISH_BUTTON);

				Thread.sleep(2000);

				// Moving on parent window
				GenericFunctionLibrary.switchToWindow(parentWindow);

				// Perform search action to verify created document in 'folder content'
				// page
				Thread.sleep(2000);
				if (isContentAvailable(ManufacturerPartDocument)) {
					log.info("content available in search");
					String Comment = objectType + " is Created Successfully and the dataset number is : "+ ManufacturerPartDocument;
					result[1] = Comment;

					result[0] = "Authorized Successful";

				} else {
					String comment = ManufacturerPartDocument +" created and content is not available in search";
					log.info(comment);
					result[0] = "Un-Authorized Successful";
					result[1] = comment;
				}

			} else {

				String Comment = objectType+" option is not available in Create Part drop down hence user dont have access to create "+ objectType;
				result[1] = Comment;
				log.info(Comment);

				// clicking on 'Finish' button to create content
				GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CREATEWINDOW_CANCEL_BUTTON_XPATH);

				log.info("Clicked on Cancel button in create window");

				result[0] = "Un-Authorized Successful";
				// Moving on parent window
				GenericFunctionLibrary.switchToWindow(parentWindow);

				Thread.sleep(5000);
			}

		}

		return result;
	}
	 
//------------------------------------  Below Methods for ENGINEERING PART --------------------------------

	// CREATE : AUTHORIZED & UN-AUTHORIZED
		
		public String[] createEngineeredPartAccessRightValidation(String objectType) throws InterruptedException,
		AWTException {
			
			String result[] = new String[2];
			result[0] = "";
			result[1] = "";
	try{
			Thread.sleep(6000);
			String parentWindow = GenericFunctionLibrary.switchToChildWindow(ObjectRepository.CREATE_PART_ICON);		
			log.info(GenericFunctionLibrary.getPageTitle());
			
			Thread.sleep(5000);
			String expetedAlertMessage = "ATTENTION: Action Unavailable.";
			
			if (GenericFunctionLibrary.isAleretPresent(2)) {
				log.info("Alert is Present");

				String ActualErrormessage = GenericFunctionLibrary.getAlert();

				if (ActualErrormessage.contains(expetedAlertMessage)) {
					log.info(" Alert Message is : " + ActualErrormessage);
					result[0] = "Un-Authorized Successful";
					String Comment = "Throwing an alert " + ActualErrormessage + " hence user is not Authorized to create "
							+ objectType;
					result[1] = Comment;

					GenericFunctionLibrary.switchToWindow(parentWindow);
				}
			   
			}else{
				Thread.sleep(2000);
				// selecting values from dropdown
				
				if(GenericFunctionLibrary.isMatchFoundInColumn(ObjectRepository.PART_OPTONS_XPATH, objectType)){
				GenericFunctionLibrary.selectDropdownByVisibleText("xpath", ObjectRepository.PART_OBJECT_TYPE, objectType);

				GenericFunctionLibrary.selectDropdownByVisibleText("xpath",ObjectRepository.PART_END_ITEM, "No");
				
				Thread.sleep(4000);
				
				GenericFunctionLibrary.setText("xpath", ObjectRepository.NAMEFIELD, Config.ENGINEERED_PART_NAME+System.currentTimeMillis());

				String EngineeringPartDocument = GenericFunctionLibrary.getAttributeValue("xpath", ObjectRepository.NAMEFIELD, "value");
				log.info("Value in Name Field : " + EngineeringPartDocument);

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

				// Perform search action to verify created document in 'folder content' page
				Thread.sleep(2000);
				if (isContentAvailableXpath(EngineeringPartDocument)) {
					String comment = EngineeringPartDocument + "is available in search and logged-in user is able to create "+ objectType;
					log.info(comment);
					
					result[0] = "Authorized Successful";
					result[1] = comment;
					
				} else {
					String comment = EngineeringPartDocument + " created and content is not available in search";
					log.info(comment);
					result[0]= "Un-Authorized Successful";
					result[1] = comment;
					
				}
				
			}else{
				String comment = objectType+" option is not available in Create Part drop down hence user dont have access to create "+ objectType;
				log.info(comment);
				
				// clicking on 'Finish' button to create content
				GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CREATEWINDOW_CANCEL_BUTTON_XPATH);
				
				log.info("Clicked on Cancel button in create window");
				
				result[0] = "No Option";
				result[1] = comment;
				// Moving on parent window
				GenericFunctionLibrary.switchToWindow(parentWindow);

				Thread.sleep(5000);
			}
			
		}
			
		}catch(Exception e){
			e.printStackTrace();
		}
			
			return result;
		}
		
		
		// MODIFY : AUTHORIZED & UN-AUTHORIZED
		
		public String[] partModifyAccessValidation(String fileName) throws InterruptedException {

			String[] result = new String[2];

					
			Thread.sleep(3000);
			
			String ActualFileName = GenericFunctionLibrary.getFileName(ObjectRepository.VERSION_NUMBER_FILENAME);
			
			log.info("File Name before modify :" + ActualFileName);
			
			Thread.sleep(4000);
			// clicking on 'actions' button
					
					
			GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ACTION_BUTTON);

			// verify 'Rename' action

			Thread.sleep(3000);
			if(GenericFunctionLibrary.isElementPresent(ObjectRepository.ACTION_OPTION)){
				Thread.sleep(2000);
				
				String parentWindow = GenericFunctionLibrary.switchToChildWindow(ObjectRepository.ACTION_OPTION);

				
				GenericFunctionLibrary.SwitchToFrame("lbContentIframe");
				Thread.sleep(2000);
			
				GenericFunctionLibrary.setText("xpath",ObjectRepository.NEW_NAME_ENGPART_DOC, fileName+ "Updt"+System.currentTimeMillis());

				//String updatedEngPartDocName1 = GenericFunctionLibrary.getAttributeValue("xpath", ObjectRepository.NEW_NAME_ENGPART_DOC, "value");

	if(GenericFunctionLibrary.isItOpenedChildWindow(ObjectRepository.OkBtn, parentWindow)){

		String parentWindow2 =	GenericFunctionLibrary.switchToChildWindow();
		log.info("parnt window 2 :" + parentWindow2);
		log.info("parnt window  :" + parentWindow);
		Thread.sleep(3000);
			// compare checkStatusMessage with expectedMessage
			if (GenericFunctionLibrary.isMatchFoundInList(ObjectRepository.ERROR_MESSAGE,"Failed with overridable conflicts")) {
				
				String comment = "Modify done on dataset number : "+ ActualFileName+" user find the Conflict messages hence use is NOT Authorized to Modify";
				
				log.info(comment);
				Thread.sleep(2000);

				GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CANCLE_BUTTON_FAILED_WINDOW);

				Thread.sleep(3000);			

				GenericFunctionLibrary.switchToWindow(parentWindow2);
				GenericFunctionLibrary.SwitchToFrame("lbContentIframe");
				GenericFunctionLibrary.WaitForElementToBeClickable("xpath", ObjectRepository.CANCEL_BUTTON, 10);
				GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CANCEL_BUTTON);
				Thread.sleep(3000);
				GenericFunctionLibrary.switchToWindow(parentWindow);

				result[0] = "Un-Authorized Successful";

				
			}
			} else {		

				GenericFunctionLibrary.switchToWindow(parentWindow);
				Thread.sleep(3000);
				//  part name after modified
				String updatedEngPartDocName = GenericFunctionLibrary.getText("xpath", ObjectRepository.UPDATED_PART_NAME);
				//String updatedPartDocName = GenericFunctionLibrary.getFileName(ObjectRepository.VERSION_NUMBER_FILENAME);

				String comment =  "Modify done on dataset number : "+ ActualFileName+" updated Part Name from "+ ActualFileName +" to "+  updatedEngPartDocName;
				log.info(comment);
				
				// Compare previous and updated name values

				if (updatedEngPartDocName.equals(ActualFileName)) {

					log.info("Fail : Name not changed");

					// redirecting to container landing page
					Thread.sleep(3000);
				//	GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.CONTAINER_LANDING_PAGE);
					result[0] = "Un-Authorized Successful";

				} else {
					
					log.info(comment);
					
					result[0] = "Authorized Successful";

					result[1] = comment + " name changed to "+ updatedEngPartDocName;
					log.info("new name : " + updatedEngPartDocName);

					// redirecting to container landing page
					Thread.sleep(3000);
					
				//	GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.CONTAINER_LANDING_PAGE);

				}
				}	

			}else{
				log.info(" Logged-in user don't have modify permission ");
				String comment = "Modify done on dataset number : "+ ActualFileName+" Rename Option is Not available in action drop dpwn hence Logged-in user don't have modify permission ";
				
				result[0] = "Un-Authorized Successful";
				result[1] = comment;
				
				//GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CONTAINER_LANDING_PAGE);
			}
			
			/*}else{
				statusMessage = "No Data Available";
				log.info("No Search Result Found. Either content is not available or User don't have permission");
				Thread.sleep(3000);
				GenericFunctionLibrary.clickOnElement("xpath",
						ObjectRepository.CONTAINER_LANDING_PAGE);
			}*/

			return result;

	}		
		
		// MODIFY IDENTITY : AUTHORIZED & UN-AUTHORIZED
		
	public String[] CADpartModifyIdentityAccessValidation(String fileName) throws InterruptedException {

		String result[] = new String[2];
		result[0] = "";
		result[1] = "";
		
		Thread.sleep(4000);
		
		String ActualFileName = GenericFunctionLibrary.getFileName(ObjectRepository.VERSION_NUMBER_FILENAME);
		
		// clicking on 'actions' button
		GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ACTION_BUTTON);
		Thread.sleep(4000);
		// verify 'Rename' action
		if (GenericFunctionLibrary.isElementPresent(ObjectRepository.ACTION_OPTION)) {

			GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ACTION_OPTION);

			GenericFunctionLibrary.SwitchToFrame("lbContentIframe");

			Thread.sleep(5000);

			if (GenericFunctionLibrary.isElementEnabled("xpath", ObjectRepository.NEW_NUMBER_TEXT_BOX)) {

				GenericFunctionLibrary.setText("xpath", ObjectRepository.CAD_NEW_FILENAME_TEXT_BOX,
						fileName + System.currentTimeMillis() + ".prt");

				String UpdatedFileName = GenericFunctionLibrary.getAttributeValue("xpath",
						ObjectRepository.CAD_NEW_FILENAME_TEXT_BOX, "value");
				Thread.sleep(3000);
				GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.NEW_NUMBER_TEXT_BOX);
				Thread.sleep(2000);
				GenericFunctionLibrary.setText("xpath", ObjectRepository.NEW_NUMBER_TEXT_BOX,
						"CD" + System.currentTimeMillis() + ".PRT");
				
				log.info("New number textbox is enabled and loggedin user can change the content number");

				String UpdateddocumentNumber = GenericFunctionLibrary.getAttributeValue("xpath",
						ObjectRepository.NEW_NUMBER_TEXT_BOX, "value");
				String comment = "CAD part modify identity is done on "+ ActualFileName +" New number textbox is enabled. Updated Document number is :" + UpdateddocumentNumber + " & " + "Updated File Name is :"
		+ UpdatedFileName;
				log.info(comment);

				// clicking on OK button
				GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.OkBtn);
				
				result[0] = "Authorized Successful";
				result[1] = comment;
				// redirecting to container landing page
				Thread.sleep(3000);
				GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CONTAINER_LANDING_PAGE);

			} else {
				// Verify Un-Authorized case

				String comment = "CAD part modify identity is done on "+ ActualFileName +" New number textbox is disabled and loggedin user can not change the content number";
				log.info(comment);
				
				result[0] = "Un-Authorized Successful";
				result[1] = comment;
				// clicking on cancel button
				GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CANCEL_BUTTON);
				// redirecting to container landing page
				Thread.sleep(3000);
				// GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.CONTAINER_LANDING_PAGE);
			}

		} else {
			String comment = "CAD part modify identity is done on "+ ActualFileName +" Rename Option is NOT Available hence Logged-in user don't have modify permission ";
			log.info(comment);
			
			result[0] = "Un-Authorized Successful";
			result[1] =comment;

			// GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CONTAINER_LANDING_PAGE);
		}
	
		return result;
	}

		// MODIFY IDENTITY : AUTHORIZED & UN-AUTHORIZED
		
		
		public String[] partModifyIdentityAccessValidation(String documentNumber) throws InterruptedException {

			String result[] = new String[2];  
			result[0] = "";
			result[1] = "";
			
				Thread.sleep(4000);
				
				String ActualFileName = GenericFunctionLibrary.getFileName(ObjectRepository.VERSION_NUMBER_FILENAME);
				
				log.info("File Name before modify Identity :" + ActualFileName);
			// clicking on 'actions' button
			GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.ACTION_BUTTON);
			Thread.sleep(4000);
			// verify 'Rename' action		
			if(GenericFunctionLibrary.isElementPresent(ObjectRepository.ACTION_OPTION)){
				
				GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.ACTION_OPTION);

				GenericFunctionLibrary.SwitchToFrame("lbContentIframe");
				
				Thread.sleep(5000);
				
				if(GenericFunctionLibrary.isElementEnabled("xpath", ObjectRepository.NEW_NUMBER_TEXT_BOX)){
					
					GenericFunctionLibrary.setText("xpath",ObjectRepository.NEW_NUMBER_TEXT_BOX, documentNumber + System.currentTimeMillis());
					log.info("Number entered in New Number Filed");
					log.info("New number textbox is enabled and loggedin user can change the content number");
					
					String UpdateddocumentNumber = GenericFunctionLibrary.getAttributeValue("xpath",ObjectRepository.NEW_NUMBER_TEXT_BOX, "value");

					
					log.info("Updated Document number is :" + UpdateddocumentNumber);
					 

					// clicking on OK button
					GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.OkBtn);
					
					String comment = "Modify Identity is done on "+ ActualFileName +". New number textbox is enabled and Document Number is updated successfully and the new document number is : "+UpdateddocumentNumber+" hence user authorized to Modify Identity";
					log.info(comment);
					result[0] = "Authorized Successful";
					result[1] = comment;
					// redirecting to container landing page
					Thread.sleep(3000);
					GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.CONTAINER_LANDING_PAGE);
					
				}else{
					
					// Verify Un-Authorized case
					String comment = "Modify Identity is done on "+ ActualFileName +". New number textbox is disabled and loggedin user can not change the content number";
					log.info(comment);
					
					result[0] = "Un-Authorized Successful";
					result[1] = comment;
					// clicking on cancel button
					GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CANCEL_BUTTON);
					// redirecting to container landing page
					Thread.sleep(3000);
					//GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.CONTAINER_LANDING_PAGE);
				}
				
			}else{
				String comment = "Modify Identity is done on "+ ActualFileName +". Rename Option is NOT Available hence Logged-in user don't have modify permission ";
				log.info(comment);
				
				result[0] = "Un-Authorized Successful";
				result[1] = comment;
				
				//GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CONTAINER_LANDING_PAGE);
			}
			
				return result;
		}
		
		
		// ------------------------------------  Below methods for OBJECT LIST --------------------------------	
		
		// CREATE : AUTHORIZED & UN-AUTHORIZED
		
		public String[] createObjectListPartAccessRightValidation(String objectType) throws InterruptedException, AWTException {
			
			String[] result = new String[2];
			String parentWindow = null;
			
			try{
			
				Thread.sleep(5000);
			parentWindow = GenericFunctionLibrary.switchToChildWindow(ObjectRepository.CREATE_PART_ICON);		
			
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.focus();");		
			
			log.info("Child window is on Focus");
			
			log.info(GenericFunctionLibrary.getPageTitle());
			
			Thread.sleep(5000);
			String expetedAlertMessage = "ATTENTION: Action Unavailable.";
			
			
			if (GenericFunctionLibrary.isAleretPresent(2)) {
				log.info("Alert is Present");

				String ActualErrormessage = GenericFunctionLibrary.getAlert();

				if (ActualErrormessage.contains(expetedAlertMessage)) {
					log.info(" Alert Message is : " + ActualErrormessage);
					result[0] = "Un-Authorized Successful";
					String Comment = "Throwing an alert " + ActualErrormessage + " hence user is not Authorized to create "	+ objectType;
					result[1] = Comment;

					GenericFunctionLibrary.switchToWindow(parentWindow);
				}
			   
			}else{
				
				if(GenericFunctionLibrary.isMatchFoundInColumn(ObjectRepository.PART_OPTONS_XPATH, objectType)){
				Thread.sleep(2000);
				// selecting values from dropdown
				GenericFunctionLibrary.selectDropdownByVisibleText("xpath",ObjectRepository.PART_OBJECT_TYPE, objectType);

				GenericFunctionLibrary.selectDropdownByVisibleText("xpath",ObjectRepository.PART_END_ITEM, "No");

				GenericFunctionLibrary.setText("xpath", ObjectRepository.NAMEFIELD,Config.OBJECT_LIST_PART_NAME+System.currentTimeMillis());

				String objectListPartName = GenericFunctionLibrary.getAttributeValue("xpath", ObjectRepository.NAMEFIELD, "value");
				log.info("Object List name is : "+objectListPartName);
				GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.LOCATION1);

				// clicking on 'Finish' button to create content
				GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.FINISH_BUTTON);	

				
				// Moving on parent window
				GenericFunctionLibrary.switchToWindow(parentWindow);
				// Perform search action to verify created document in 'folder content' page

				Thread.sleep(3000);
				if (isContentAvailable(objectListPartName)) {
					String comment = objectListPartName+ " is available in search hence logged-in user is able to create "+ objectType;
					log.info(comment);
					
					result[0] = "Authorized Successful";
					result[1] = comment;

				}else{
					
					String comment = objectListPartName+ "Content is not available in search";
					log.info(comment);
					
					result[0]  = "Un-Authorized Successful";
					result[1] = comment;
 					
				}
							
			}else{
				
				String comment = objectType+" option is not available in Create Part drop down hence user dont have access to create "+ objectType;
				log.info(comment);
				
				// clicking on 'Finish' button to create content
				GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CREATEWINDOW_CANCEL_BUTTON_XPATH);
				
				log.info("Clicked on Cancel button in create window");
				
				result[0]  =  "No Option";
				// Moving on parent window
				GenericFunctionLibrary.switchToWindow(parentWindow);

				Thread.sleep(5000);
			}
			
		}
			}catch(UnhandledAlertException un){
				GenericFunctionLibrary.getAlert();
				result[0]  = "Un-Authorized Successful";
				GenericFunctionLibrary.switchToWindow(parentWindow);
		}catch(Exception e){
			e.printStackTrace();
		}
			
			return result;
		}
		// ------------------------------------  Below Methods for MOUNTING CONTEXT PART --------------------------------
		
		
		public String[] createMountingContextPartAccessRightValidation(String objectType) throws InterruptedException,
		AWTException {
			
			String[] result = new String[2];
						
			Thread.sleep(5000);		
			String parentWindow = GenericFunctionLibrary.switchToChildWindow(ObjectRepository.CREATE_PART_ICON);		
			log.info(GenericFunctionLibrary.getPageTitle());
			
			Thread.sleep(5000);
			String expetedAlertMessage = "ATTENTION: Action Unavailable.";
			
			if (GenericFunctionLibrary.isAleretPresent(2)) {
				log.info("Alert is Present");

				String ActualErrormessage = GenericFunctionLibrary.getAlert();

				if (ActualErrormessage.contains(expetedAlertMessage)) {
					log.info(" Alert Message is : " + ActualErrormessage);
					result[0] = "Un-Authorized Successful";
					String Comment = "Throwing an alert " + ActualErrormessage + " hence user is not Authorized to create "	+ objectType;
					result[1] = Comment;

					GenericFunctionLibrary.switchToWindow(parentWindow);
				}
			   
			}else{	
				
				
				if(GenericFunctionLibrary.isMatchFoundInColumn(ObjectRepository.PART_OPTONS_XPATH,objectType)){
				// selecting values from dropdown
				GenericFunctionLibrary.selectDropdownByVisibleText("xpath",ObjectRepository.PART_OBJECT_TYPE, objectType);

				GenericFunctionLibrary.selectDropdownByVisibleText("xpath", ObjectRepository.PART_END_ITEM, "No");

							
				Thread.sleep(2000);

				// fill name value
				GenericFunctionLibrary.setText("xpath", ObjectRepository.NAMEFIELD, Config.MOUNTING_CONTEXT_PART_NAME + System.currentTimeMillis());
				// get insert value and store in variable
				String mountingContextPartName = GenericFunctionLibrary.getAttributeValue("xpath", ObjectRepository.NAMEFIELD, "value");

				// set Location
				GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.LOCATION1);

				// clicking on 'Finish' button to create content
				GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.FINISH_BUTTON);
				
				
				// Moving on parent window
				GenericFunctionLibrary.switchToWindow(parentWindow);
				
				Thread.sleep(2000);
				if (isContentAvailable(mountingContextPartName)) {
					
					String comment = mountingContextPartName+ "is available in search and logged-in user is able to create Mounting Context part content";
					log.info(comment);
                 
					result[0] = "Authorized Successful";
					result[1] = comment;

				}else{
					
					String comment =mountingContextPartName+" is not available in search";
					log.info(comment);
					
					result[0] = "Un-Authorized Successful";
					result[1] = comment;
				}
				
				}else{
					
					String comment = objectType+" option is not available in Create Part drop down hence user dont have access to create "+ objectType;
					log.info(comment);
					// clicking on 'Cancel' button to create content
					GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CREATEWINDOW_CANCEL_BUTTON_XPATH);
					
					log.info("Clicked on Cancel button in create window");
					
					result[0] = "No Option";
					result[1] = comment;
					// Moving on parent window
					GenericFunctionLibrary.switchToWindow(parentWindow);

					Thread.sleep(5000);
			
				}			
			}
			return result;
		}
		
		
		
		//-------------------------------------------------------
				// RAW MATERIAL PART
				//-------------------------------------------------------
				
				// CREATE : AUTHORIZED & UN-AUTHORIZED

	public String[] createRawMaterialPartAccessRightValidation(String objectType) throws Exception {

		String result[] = new String[2];
		result[0] = "";
		result[1] = "";
		
		Thread.sleep(5000);
		String parentWindow = GenericFunctionLibrary.switchToChildWindow(ObjectRepository.CREATE_PART_ICON);
		log.info(GenericFunctionLibrary.getPageTitle());
		
		Thread.sleep(5000);
		String expetedAlertMessage = "ATTENTION: Action Unavailable.";
		
		if (GenericFunctionLibrary.isAleretPresent(2)) {
			log.info("Alert is Present");

			String ActualErrormessage = GenericFunctionLibrary.getAlert();

			if (ActualErrormessage.contains(expetedAlertMessage)) {
				log.info(" Alert Message is : " + ActualErrormessage);
				result[0] = "Un-Authorized Successful";
				String Comment = "Throwing an alert " + ActualErrormessage + " hence user is not Authorized to create "
						+ objectType;
				result[1] = Comment;

				GenericFunctionLibrary.switchToWindow(parentWindow);
			}
		   
		}else{

			Thread.sleep(2000);

			if (GenericFunctionLibrary.isMatchFoundInColumn(ObjectRepository.PART_OPTONS_XPATH, objectType)) {
				// selecting values from dropdown
				GenericFunctionLibrary.selectDropdownByVisibleText("xpath", ObjectRepository.PART_OBJECT_TYPE,
						objectType);

				GenericFunctionLibrary.selectDropdownByVisibleText("xpath", ObjectRepository.PART_END_ITEM, "No");

				GenericFunctionLibrary.setText("xpath", ObjectRepository.NAMEFIELD, Config.RAWMATERIAL_PART_NAME+System.currentTimeMillis());

				String RawmaterialPartDocument = GenericFunctionLibrary.getAttributeValue("xpath",	ObjectRepository.NAMEFIELD, "value");
				log.info("Value in Name Field : " + RawmaterialPartDocument);

				// *************  Management Type Attributes *************************************	
				
				// Select Part Sensitivity as No
				GenericFunctionLibrary.selectDropdownByIndex("xpath",  ObjectRepository.SENSITIVITY,1);
				
				// ************* Business Attributes  *****************************************************
				
				// Send value to OwnerOrganization editable text box
				GenericFunctionLibrary.selectDropdownByVisibleText("xpath", ObjectRepository.OWNER_ORGANIZATION, ContentRepository.OwnerOrganization);
				
				
				// Select Approved option from CorpPreference drop down
				GenericFunctionLibrary.selectDropdownByVisibleText("xpath", ObjectRepository.CORP_PREFERENCE, "Approved");

				
				
				// **************  Technical Attributes ******************************************************
				
				
				GenericFunctionLibrary.setText("xpath", ObjectRepository.TECHNICAL_CLASSIFICATION, ContentRepository.raw_technical_classification_value);
				Thread.sleep(2000);
				GenericFunctionLibrary.KeyboardActionEnter();
				     
				//String mainWindow = GenericFunctionLibrary.switchToChildWindow("//td[@attrid='seiTechnicalClassification']/button[1]/img[@title='Find...']");
				
				GenericFunctionLibrary.setText("xpath", ObjectRepository.DEFAULT_UNIT, ContentRepository.defaultUnit);
				
				GenericFunctionLibrary.selectDropdownByVisibleText("xpath", ObjectRepository.ENVIRONEMENT_PROFILE, ContentRepository.environmental_profile_value);
				
								
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
				
				
				// Moving on parent window
				GenericFunctionLibrary.switchToWindow(parentWindow);

				// Perform search action to verify created document in 'folder
				// content'
				// page

				Thread.sleep(2000);
				if (isContentAvailable(RawmaterialPartDocument)) {
				String comment = 	objectType + " is Created Successfully and the dataset number is : "+ RawmaterialPartDocument;
					log.info(comment);
					result[0] = "Authorized Successful";
					result[1] = comment;

				} else {
					String comment = RawmaterialPartDocument+ " created  and Content is not available in search";
					log.info(comment);
					
					result[0] = "Un-Authorized Successful";
					result[1] = comment;


				}
			
			} else {
				
				String comment = objectType+" option is not available in Create Part drop down hence user dont have access to create "+ objectType;
				
				log.info(comment);
				
				// clicking on 'Finish' button to create content
				GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CREATEWINDOW_CANCEL_BUTTON_XPATH);

				log.info("Clicked on Cancel button in create window");

				result[0] = "No Option";
				result[1]= comment;
				// Moving on parent window
				GenericFunctionLibrary.switchToWindow(parentWindow);

				Thread.sleep(5000);

			}
		}
		return result;
	}
	
	
	// CREATE Content : AUTHORIZED & UN-AUTHORIZED [ CATALOG PART ]

	public String[] createCatalogPartAccessRightValidation(String objectType) throws Exception {

		String[] result = new String[2];
		GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.CREATE_PART_ICON, 10);
		String parentWindow = GenericFunctionLibrary.switchToChildWindow(ObjectRepository.CREATE_PART_ICON);
		log.info(GenericFunctionLibrary.getPageTitle());
		
		Thread.sleep(5000);
		String expetedAlertMessage = "ATTENTION: Action Unavailable.";
		
		
		if (GenericFunctionLibrary.isAleretPresent(2)) {
			log.info("Alert is Present");

			String ActualErrormessage = GenericFunctionLibrary.getAlert();

			if (ActualErrormessage.contains(expetedAlertMessage)) {
				log.info(" Alert Message is : " + ActualErrormessage);
				result[0] = "Un-Authorized Successful";
				String Comment = "Throwing an alert " + ActualErrormessage + " hence user is not Authorized to create "
						+ objectType;
				result[1] = Comment;

				GenericFunctionLibrary.switchToWindow(parentWindow);
			}
		   
		}else{
			
			GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.PART_OBJECT_TYPE, 10);
			// selecting values from dropdown
			if (GenericFunctionLibrary.isMatchFoundInColumn(ObjectRepository.PART_OBJECT_TYPE, objectType)) {
				GenericFunctionLibrary.selectDropdownByVisibleText("xpath", ObjectRepository.PART_OBJECT_TYPE, objectType);

				GenericFunctionLibrary.selectDropdownByVisibleText("xpath", ObjectRepository.PART_END_ITEM, ContentRepository.part_end_item_value);

				GenericFunctionLibrary.setText("xpath", ObjectRepository.NAMEFIELD, Config.CATALOG_PART_NAME + System.currentTimeMillis());

				String actualCatalogPartDocument = GenericFunctionLibrary.getAttributeValue("xpath", ObjectRepository.NAMEFIELD, "value");
				log.info("Value in Name Field : " + actualCatalogPartDocument);
              
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
				
				
		// **************  Technical Attributes ******************************************************
				
				
				GenericFunctionLibrary.setText("xpath", ObjectRepository.TECHNICAL_CLASSIFICATION, ContentRepository.catalog_technical_classification_value);
				Thread.sleep(2000);
				GenericFunctionLibrary.KeyboardActionEnter();
				     
				//String mainWindow = GenericFunctionLibrary.switchToChildWindow("//td[@attrid='seiTechnicalClassification']/button[1]/img[@title='Find...']");
				
				GenericFunctionLibrary.setText("xpath", ObjectRepository.DEFAULT_UNIT, ContentRepository.defaultUnit);
				
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
				
				
				// Moving on parent window
				GenericFunctionLibrary.switchToWindow(parentWindow);

				Thread.sleep(2000);
				if (isContentAvailable(actualCatalogPartDocument)) {
					String comment = actualCatalogPartDocument+ " available in search and logged-in user is able to create Catalog part content";
					log.info(comment);
					
					result[0] = "Authorized Successful";
					result[1] = comment;

				} else {
					
					String comment =actualCatalogPartDocument+ "Created. and is NOT available in Search";
					log.info(comment);
					
					result[0] = "Un-Authorized Successful";
					result[1] = comment;
				}

			
		}else {
			
			String comment = "Catalog Part Option is Not available in the create part drop down list hence user is Un-Authorized to Create Catalog Part";

			log.info(comment);
			// clicking on 'Finish' button to create content
			GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CREATEWINDOW_CANCEL_BUTTON_XPATH);

			log.info("Clicked on Cancel button in create window");

			result[0] = "Un-Authorized Successful";
			result[1]=comment;
			// Moving on parent window
			GenericFunctionLibrary.switchToWindow(parentWindow);

			Thread.sleep(5000);

		}
		
	}
		return result;
	
	}
	
	// CREATE Content : AUTHORIZED & UN-AUTHORIZED [ TREATMENT PART ]
	
	public String[] createTreatmentPartAccessRightValidation(String objectType) throws Exception {

		String[] result = new String[2];
		Thread.sleep(5000);
		String parentWindow = GenericFunctionLibrary.switchToChildWindow(ObjectRepository.CREATE_PART_ICON);
		Thread.sleep(4000);
		log.info(GenericFunctionLibrary.getPageTitle());
		
		Thread.sleep(5000);
		String expetedAlertMessage = "ATTENTION: Action Unavailable.";
		
		
		if (GenericFunctionLibrary.isAleretPresent(2)) {
			log.info("Alert is Present");

			String ActualErrormessage = GenericFunctionLibrary.getAlert();

			if (ActualErrormessage.contains(expetedAlertMessage)) {
				log.info(" Alert Message is : " + ActualErrormessage);
				result[0] = "Un-Authorized Successful";
				String Comment = "Throwing an alert " + ActualErrormessage + " hence user is not Authorized to create "	+ objectType;
				result[1] = Comment;

				GenericFunctionLibrary.switchToWindow(parentWindow);
			}
		   
		}else{
			Thread.sleep(2000);
			// selecting values from dropdown
			if (GenericFunctionLibrary.isMatchFoundInColumn(ObjectRepository.PART_OBJECT_TYPE, objectType)) {
				GenericFunctionLibrary.selectDropdownByVisibleText("xpath", ObjectRepository.PART_OBJECT_TYPE, objectType);
				Thread.sleep(3000);
				GenericFunctionLibrary.selectDropdownByVisibleText("xpath", ObjectRepository.ENG_PART_END_ITEM, "No");

				GenericFunctionLibrary.setText("xpath", ObjectRepository.NAMEFIELD, Config.TREATMENT_PART_NAME+ System.currentTimeMillis());

				String actualCatalogPartDocument = GenericFunctionLibrary.getAttributeValue("xpath",ObjectRepository.NAMEFIELD, "value");
				log.info("Value in Name Field : " + actualCatalogPartDocument);

				
				// *************  Management Type Attributes *************************************		
				
				// Select Part Sensitivity as No
				GenericFunctionLibrary.selectDropdownByIndex("xpath",ObjectRepository.SENSITIVITY,1);
				
				// ****************** Business Attributes ******************************************
				///
				Thread.sleep(5000);
				// Select value to OwnerOrganization editable text box
				GenericFunctionLibrary.selectDropdownByVisibleText("xpath", ObjectRepository.OWNER_ORGANIZATION, ContentRepository.OwnerOrganization);
			
				// Select Approved option from CorpPreference drop down
				GenericFunctionLibrary.selectDropdownByVisibleText("xpath", ObjectRepository.CORP_PREFERENCE,"Approved");

				// Send value to CorpPreferenceComment editable text box
				GenericFunctionLibrary.setText("xpath", ObjectRepository.CORPPREFERENCE_COMMENT, ContentRepository.corpPreferenceComment);
				
				
				
				// **************  Technical Attributes ******************************************************
				
				
				GenericFunctionLibrary.setText("xpath", ObjectRepository.TECHNICAL_CLASSIFICATION, ContentRepository.treatment_technical_classification_value);
				Thread.sleep(2000);
				GenericFunctionLibrary.KeyboardActionEnter();
				     
						
				GenericFunctionLibrary.setText("xpath", ObjectRepository.DEFAULT_UNIT, ContentRepository.defaultUnit);
				
				GenericFunctionLibrary.selectDropdownByVisibleText("xpath", ObjectRepository.ENVIRONEMENT_PROFILE, ContentRepository.environmental_profile_value);
				
				
				
				// **************  Environmental Foot Attributes ****************************************************
				
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

				
				// Moving on parent window
				GenericFunctionLibrary.switchToWindow(parentWindow);
				// Perform search action to verify created document in 'folder content' page

				Thread.sleep(2000);
				if (isContentAvailable(actualCatalogPartDocument)) {
					String comment = actualCatalogPartDocument+ "content available in search and logged-in user is able to create Treatment part content";
					log.info(comment);
					
					result[0] = "Authorized Successful";
					result[1] = comment;

				}else{
					
					String comment = actualCatalogPartDocument + " create and content is not available in search";
					log.info(comment);
					
					result[0] = "Un-Authorized Successful";
					result[1]= comment;
				}
		 
			}
			else {
				String comment = objectType+" option is not available in Create Part drop down hence user dont have access to create "+ objectType;
			
				log.info(comment);
				GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CREATEWINDOW_CANCEL_BUTTON_XPATH);

				log.info("Clicked on Cancel button in create window");

				result[0] = "No Option";
				result[1] = comment;
				// Moving on parent window
				GenericFunctionLibrary.switchToWindow(parentWindow);

				Thread.sleep(5000);

			}

		}

		return result;
	
	}
	
//  ************************** Create CAD Document ************************************

	public String[] createCADDocumentAccessRightsValidation(String ObjectType, String template, String ContextName) {
		String result[] = new String[2];
		result[0] = "";
		result[1] = "";

		String documentName = null;
		String workSpaceName = null;
		try {

			if (template.contains("Restricted")) {
				workSpaceName = Config.PROD_RESTRICTED_CADDOC_WORKSPACE_NAME;
			} else {
				workSpaceName = Config.PROD_CADDOC_WORKSPACE_NAME;
			}

			if (WindChillAccessRightsValidationUtil.navigateToWorkSpace(template,
					Config.fetchContainerOrLibrary(template), "Workspaces", workSpaceName)) {

				GenericFunctionLibrary.SwitchToFrame(ObjectRepository.CAD_IFRAME);
				GenericFunctionLibrary.WaitForElementToBeVisible("xpath", ObjectRepository.CAD_CREATE_ICON, 20);
				if (GenericFunctionLibrary.isElementPresent(ObjectRepository.CAD_CREATE_ICON)) {

					String parentWindow = GenericFunctionLibrary.switchToChildWindow(ObjectRepository.CAD_CREATE_ICON);

					Thread.sleep(3000);

					GenericFunctionLibrary.WaitForElementToBeVisible("xpath",
							ObjectRepository.CADDOCUMENT_TEMPLATE_DROPDOWN, 10);
					GenericFunctionLibrary.selectDropdownByVisibleText("xpath",
							ObjectRepository.CADDOCUMENT_TEMPLATE_DROPDOWN, ContentRepository.CAD_Template);
					GenericFunctionLibrary.WaitForElementPresence("xpath",
							ObjectRepository.CADDOCUMENT_NAME_FIELD_XPATH, 10);
					GenericFunctionLibrary.setText("xpath", ObjectRepository.CADDOCUMENT_NAME_FIELD_XPATH,
							Config.CAD_PART_NAME + System.currentTimeMillis());

					Thread.sleep(2000);
					documentName = GenericFunctionLibrary.getAttributeValue("xpath",
							ObjectRepository.CADDOCUMENT_NAME_FIELD_XPATH, "value");

					log.info("Created Document Name is : " + documentName);

					GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.MODIFY_OK_BUTTON);

					GenericFunctionLibrary.switchToWindow(parentWindow);

					if (AdvSearchwithContext(documentName, ObjectType, template, ContextName)) {

						log.info(documentName + "is available in search");

						if (checkInCADDcoument()) {
							String comment = documentName
									+ " is created and checkedIn successfully Hence user is authorized to create CAD document";
							log.info(comment);
							
							result[0] = "Authorized Successful";
							result[1] = comment;

						} else {

							String comment = documentName + "CheckIn Failed hence user is Un-authorized";
							log.info(comment);
							
							result[0] = "Un-Authorized Successful";
							result[1] = comment;
						}
					} else {
						String comment = documentName
								+ " not displayed in search result. Either content not available in the library or logged-in user is un-authorized";
						log.info(comment);
						result[0] = "Un-Authorized Successful";
						result[1] = comment;
					}
				}
			}

		} catch (Exception e) {
			log.info(e);
		}
		return result;
	}
	

public boolean checkInCADDcoument()throws Exception{
	boolean isCheckedIn = false;
	
	Thread.sleep(3000);
	
	GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.ACTION_BUTTON);
	String parentWindow = null;
	if(GenericFunctionLibrary.isElementPresent(ObjectRepository.ACTION_OPTION_CHECK_IN)){
	parentWindow = GenericFunctionLibrary.switchToChildWindow(ObjectRepository.ACTION_OPTION_CHECK_IN);
	log.info("user clicked on Checkin option from the action dropdown");
	Thread.sleep(3000);
	GenericFunctionLibrary.SwitchToFrame("lbContentIframe");
	

	if(GenericFunctionLibrary.isItOpenedChildWindow(ObjectRepository.OK_BUTTON_PARENT_WINDOW, parentWindow)){
	log.info("User clicked on OK Button");
		Thread.sleep(3000);
		String parentWindow2 =	GenericFunctionLibrary.switchToChildWindow();		
	
	
	// verifying 'authorized and un-authorized' conditions
	if (GenericFunctionLibrary.isMatchFoundInList(ObjectRepository.ERROR_MESSAGE,"Failed with overridable conflicts")) {
		String comment = "user find the Conflict messages hence user is NOT Authorized to Create CAD Document";
		log.info(comment);
		GenericFunctionLibrary.closeChildWindow();
		GenericFunctionLibrary.switchToWindow(parentWindow2);
		GenericFunctionLibrary.SwitchToFrame("lbContentIframe");
		GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CANCEL_BUTTON_CHANGE_CONTEXT);
		GenericFunctionLibrary.switchToWindow(parentWindow);
		isCheckedIn = false;
	//	GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CONTAINER_LANDING_PAGE);
		Thread.sleep(3000);
		
	} else if(GenericFunctionLibrary.isMatchFoundInList(ObjectRepository.ERROR_MESSAGE,"Done with Conflicts")){
		String comment = "user find the Conflict messages as Done with Conflicts hence user is NOT Authorized to Create CAD Document";
		log.info(comment);
		GenericFunctionLibrary.closeChildWindow();
		GenericFunctionLibrary.switchToWindow(parentWindow2);
		GenericFunctionLibrary.SwitchToFrame("lbContentIframe");
		GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CANCEL_BUTTON_CHANGE_CONTEXT);
		GenericFunctionLibrary.switchToWindow(parentWindow);
		isCheckedIn = false;
		
	}else if(GenericFunctionLibrary.isMatchFoundInList(ObjectRepository.ERROR_MESSAGE,"Failed")){
		String comment = "user find the Conflict messages as Failed hence user is NOT Authorized to Create CAD Document";
		log.info(comment);
		GenericFunctionLibrary.closeChildWindow();
		GenericFunctionLibrary.switchToWindow(parentWindow2);
		GenericFunctionLibrary.SwitchToFrame("lbContentIframe");
		GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CANCEL_BUTTON_CHANGE_CONTEXT);
		GenericFunctionLibrary.switchToWindow(parentWindow);
		isCheckedIn = false;
	}
	}else{
	
	// goto parent window
			GenericFunctionLibrary.switchToWindow(parentWindow);
			isCheckedIn = true;
	}
	
	}else{
		log.info("CheckIn option is NOT available it could be checkedIn already");
		
		isCheckedIn = true;
	}
	
		
	return isCheckedIn;
}
	//---------------------------- Below Method for Manage Collection ----------------------------
	
			public String[] createManagedCollectionAccessRightValidation() throws InterruptedException{
				String[] result =  new String[2];
				GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ACTION_BUTTON_LANDING_PAGE, 10);
				// click on 'Actions' button			
				GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ACTION_BUTTON_LANDING_PAGE);
				
				Thread.sleep(3000);
				GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.NEW_OPTION, 10);
				// verify 'New' option in action menu

				if(GenericFunctionLibrary.isElementPresent(ObjectRepository.NEW_OPTION)){
					
					// Click on 'NEW'				
					GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.NEW_OPTION);
					
					//if(GenericFunctionLibrary.isElementPresent(ObjectRepository.NEW_MANAGED_COLLECTION_OPTION)){
					GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.MENU_UNDER_NEW_OPTION, 10);
						if(GenericFunctionLibrary.isMatchFoundInColumn(ObjectRepository.MENU_UNDER_NEW_OPTION, "New Managed Collection")){
							
						
						// Click on 'NEW MANAGED COLLECTION' option				
						//GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.NEWOPTION_NEW_MANAGED_COLLECTION);
						String parentWindow = GenericFunctionLibrary.switchToChildWindow(ObjectRepository.NEW_MANAGED_COLLECTION_OPTION);
						
						Thread.sleep(3000);
						GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.FILE_NAME_FILED_XPATH, 10);
						// Enter name
						GenericFunctionLibrary.setText("xpath", ObjectRepository.FILE_NAME_FILED_XPATH, Config.MANAGED_COLLECTION_NAME+System.currentTimeMillis());
						Thread.sleep(3000);
						
						String managedCollectionName = GenericFunctionLibrary.getAttributeValue("xpath", ObjectRepository.FILE_NAME_FILED_XPATH, "value");
						log.info("Managed collection name is : " + managedCollectionName);
						
						// Click Finsh
						GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.MODIFY_OK_BUTTON);
						
						Thread.sleep(3000);
						
						// Moving on parent window
						GenericFunctionLibrary.switchToWindow(parentWindow);
						
						// Verify whether content create or not
						
						if (isContentAvailableXpath(managedCollectionName)) {
							
							String comment = managedCollectionName+ " is available in search and logged-in user is able to create manage collection content";
							log.info(comment);
							
							result[0] = "Authorized Successful";
							result[1] = comment;
						} 
						else {
							String comment = managedCollectionName+ " is not available in search";
							log.info(comment);
							
							result[0] = "Un-Authorized Successful";	
							result[1] = comment;
							
						}				
					}
			
					
					else{
						String comment = "User is not-authorized because 'New Managed Collection' option is not available";
						log.info(comment);
						result[0] = "Un-Authorized Successful";
						Thread.sleep(2000);
						result[1] = comment;
						//GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.CONTAINER_LANDING_PAGE);
					}
					
				}else{
					String comment = "User is not-authorized because 'New' option is not available";
					log.info(comment);
					
					result[0] = "Un-Authorized Successful";
					result[1] = comment;
					Thread.sleep(2000);
				//	GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.CONTAINER_LANDING_PAGE);
				}
				
				return result;
			}
					
					
			// ---------------------------------------------------
			// Delete : Un-Authorized And Authorized users
			// ---------------------------------------------------
			
			public String[] deleteAccessRightValidationForManagedCollection() throws InterruptedException{

				String[] result =  new String[2];
				
				Thread.sleep(3000);

				String fileName = GenericFunctionLibrary.getFileNameMngC(ObjectRepository.VERSION_NUMBER_FILENAME);
				log.info("File name is : "+ fileName);
					GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ACTION_BUTTON, 10);

					// clicking on 'actions' button
					GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ACTION_BUTTON);

					String expectedLinkColor = "rgba(128, 128, 128, 1)";
					String actualLinkColor = GenericFunctionLibrary.getCSSValue(ObjectRepository.DELETE_ACTION);

					if (actualLinkColor.equals(expectedLinkColor)) {
					//GenericFunctionLibrary.refreshWindow();
						
						String comment = " Delete action is done on "+fileName +". Delete Option is Greyout in Action Menu that's the reason User doesn't have access to Delete"; 
						log.info("Actual Delete Link Color : " + actualLinkColor);
					log.info(comment);
					
					result[0] = "Un-Authorized Successful";
					result[1] = comment;
						
						GenericFunctionLibrary.refreshWindow();
						Thread.sleep(4000);
					//	
						
					} else {
						log.info("Actual Delete Link Color : " + actualLinkColor);
						GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.DELETE_ACTION, 10);
						GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.DELETE_ACTION);
				
						if(GenericFunctionLibrary.isAleretPresent(3000)){
							GenericFunctionLibrary.getAlert();
						
							if(isContentAvailableAndClick(fileName)){
								
								String comment = " Deleted content is still availble in system ";
							
								log.info(comment);
								
								result[0] = "Un-Authorized Successful";
								result[1] = comment;
						}else{
							String comment = fileName+ " is deleted from the system hence user is authorized to delete";
							log.info(comment);
							
							result[0] = "Authorized Successful";
						}
									
					}
					}

				

				return result;

			}				
			
			// ---------------------------------------------------
			// Modify : Un-Authorized And Authorized users
			// ---------------------------------------------------
			
			public String[] modifyAccessRightValidationForManagedCollection(String fileName) throws InterruptedException {
				
				String[] result =  new String[2];
				
				// verify 'Rename' action

				String actualFileName = GenericFunctionLibrary.getFileNameMngC(ObjectRepository.VERSION_NUMBER_FILENAME);
				log.info("File name is : "+ fileName);
				
				if(verifyAndClickOptionFromActionsDropDown("Rename")){
					
				String parentWindow = GenericFunctionLibrary.switchToChildWindow();
				log.info(GenericFunctionLibrary.getPageTitle());
				GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.RENAME_MANAGE_COLLECTION_FIELD, 5);
				GenericFunctionLibrary.clearTextBox("xpath", ObjectRepository.RENAME_MANAGE_COLLECTION_FIELD);
				GenericFunctionLibrary.setText("xpath", ObjectRepository.RENAME_MANAGE_COLLECTION_FIELD, fileName +System.currentTimeMillis());
				String fileNameAfterModify = GenericFunctionLibrary.getAttributeValue("xpath", ObjectRepository.RENAME_MANAGE_COLLECTION_FIELD, "value");	
				
				log.info("Updated File Name is : "+ fileNameAfterModify);
				GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.OK_RENAME_MANAGE_COLLECTION, 10);
				GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.OK_RENAME_MANAGE_COLLECTION);
					Thread.sleep(3000);
				GenericFunctionLibrary.switchToWindow(parentWindow);
				log.info(" Logged-in is Authorized user and have modify permission ");
				
				if(!fileNameAfterModify.endsWith(actualFileName)){
				String comment = "Modify action is done on "+ actualFileName+". File name renamed from "+ actualFileName +" to "+ fileNameAfterModify+". hence user is authorized to modify ";
				result[0] = "Authorized Successful";
				result[1] = comment;
				}else{
					
					String comment = "Modify action is done on "+ actualFileName+". File name is not renamed. file name before modify is "+ actualFileName +" and file name after modify is : "+ fileNameAfterModify+". hence user is not authorized to modify ";
					result[0] = "Un-Authorized Successful";
					result[1] = comment;
					
				}
				
				}else{
					String comment = "Modify action is done on "+ actualFileName+". Rename Option is NOT available hence Logged-in is Un-Authorized user and don't have modify permission ";
					log.info(comment);
					
					result[0] = "Un-Authorized Successful";
					result[1] = comment;
				}
								
				return result;
				
			}
			
			// Below method will use to read the content from 'HOME' page
			// Using in Promotion Request and Problem Report content types
			
			public boolean isContentAvailablePR(String searchDocument) throws InterruptedException {
				boolean flag = false;
				
				// Click on HOME link
				
				GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.HOME_LINK);
				Thread.sleep(5000);
				
				// clear textBox
				GenericFunctionLibrary.clearTextBox("xpath", ObjectRepository.SEARCH_TEXT_BOX);

				// input value in search text box
				GenericFunctionLibrary.setText("xpath", ObjectRepository.SEARCH_TEXT_BOX,searchDocument);

				// clicking on search button
				GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.SEARCH_BUTTON_PATH_PR);

				Thread.sleep(5000);

				String text = GenericFunctionLibrary.getText("xpath", ObjectRepository.VALIDATION_MESSAGE_ID_PR);
				
				

				Thread.sleep(5000);
				
				if (text.equalsIgnoreCase("No matches found")) {
					flag = false;
					log.info("No Search content found");
				} else {
					// storing search result value in 'actual search result'
					// variable
					String actualSearchDocument = GenericFunctionLibrary.getText("xpath", ObjectRepository.SEARCH_RESULT_PR);

					String expectedSearchDocument = searchDocument;

					// comparing search result value from expected value
					if(expectedSearchDocument.equals(actualSearchDocument)){
					
					flag = true;
					log.info("Search string found");
					}
				}

				return flag;
			}
			
			// Search Problem report for created content
			
			public boolean isCreatedContentAvailablePR(String searchDocument) throws InterruptedException {
				boolean flag = false;
				
			
				Thread.sleep(5000);
				
				// clear textBox
				GenericFunctionLibrary.clearTextBox("xpath", ObjectRepository.SEARCH_TEXT_BOX);

				// input value in search text box
				GenericFunctionLibrary.setText("xpath", ObjectRepository.SEARCH_TEXT_BOX, searchDocument);

				// clicking on search button
				GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.SEARCH_BUTTON_PATH_PR);

				Thread.sleep(5000);

				String text = GenericFunctionLibrary.getText("xpath", ObjectRepository.VALIDATION_MESSAGE_ID_PR);
				
				Thread.sleep(5000);
				
				if (text.equalsIgnoreCase("No matches found")) {
					flag = false;
					log.info("No Search content found");
				} else {
					// storing search result value in 'actual search result'
					// variable
					String actualSearchDocument = GenericFunctionLibrary.getText("xpath", ObjectRepository.SEARCH_RESULT_PR);

					String expectedSearchDocument = searchDocument;

					// comparing search result value from expected value
					if(expectedSearchDocument.equals(actualSearchDocument)){
					
					flag = true;
					log.info("Search string found");

					}
				}

				return flag;
			}
			
			//------------------------------ Below method for Problem report ------------------------------------
			
			// Below method will use to read the content from 'HOME' page
					// Using in Promotion Request and Problem Report content types
					
					public boolean isContentAvailableAndClickPReport(String searchDocument) throws InterruptedException {
						boolean flag = false;
						
						// Click on HOME link
						
						GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.HOME_LINK);
						Thread.sleep(5000);
						
						// clear textBox
						GenericFunctionLibrary.clearTextBox("xpath", ObjectRepository.SEARCH_TEXT_BOX);

						// input value in search text box
						GenericFunctionLibrary.setText("xpath", ObjectRepository.SEARCH_TEXT_BOX,searchDocument);

						// clicking on search button
						GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.SEARCH_BUTTON_PATH_PR);

						Thread.sleep(5000);

						String text = GenericFunctionLibrary.getText("xpath",ObjectRepository.VALIDATION_MESSAGE_ID_PR);
						
						

						Thread.sleep(5000);
						
						if (text.equalsIgnoreCase("No matches found")) {
							flag = false;
							log.info("No Search content found");
						} else {
							
							//clinking on search item
							GenericFunctionLibrary.selectMatchedFoundInList(ObjectRepository.SEARCH_RESULT_XPATH_PROBLEMREPORT, searchDocument);
							
							flag = true;
							log.info("Search string found");

						}

						return flag;
					}
			
	// ------------------------------------------------
	// Modify : AUTHORIZED & UN-AUTHORIZED
	// ------------------------------------------------

	public String[] modifyAccessRightValidationForProblemReport(String fileName) throws InterruptedException {

		String[] result = new String[2];

		// Taking the name of the problem report before modification

		String actualProblemReportName = GenericFunctionLibrary.getText("xpath",ObjectRepository.PROBLEM_REPORT_NAME);
		log.info("Previous Problem Report Name :" + actualProblemReportName);

			if (verifyAndClickOptionFromActionsDropDown("Rename")) {
				
				
				log.info("rename option found");

				// click on rename option
				Thread.sleep(4000);

				String parentWidnow = GenericFunctionLibrary.switchToChildWindow();
				log.info("parent window id :" + parentWidnow);
				Thread.sleep(4000);
				// get existing number

				
				GenericFunctionLibrary.clearTextBox("xpath", ObjectRepository.PROBLEM_REPORT_NAME_FIELD);
				Thread.sleep(2000);

				// set new problem report number
				GenericFunctionLibrary.setText("xpath", ObjectRepository.PROBLEM_REPORT_NAME_FIELD, fileName+"upd" + System.currentTimeMillis());
				Thread.sleep(2000);

				// clicking on 'ok' button
				GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.MODIFY_OK_BUTTON);
				
				GenericFunctionLibrary.switchToWindow(parentWidnow);
				
				Thread.sleep(5000);
				String modifiedProblemReportName = GenericFunctionLibrary.getText("xpath",ObjectRepository.PROBLEM_REPORT_NAME);
				log.info("Modified report Number :" + modifiedProblemReportName);

				if (actualProblemReportName.equalsIgnoreCase(modifiedProblemReportName)) {
					String comment = "Modify action is done on "+actualProblemReportName+". Problem report name doesn't update. Name before modify is : "+actualProblemReportName+ " and Name after modify is : "+ modifiedProblemReportName;
					log.info(comment);
					
					result[0] = "Un-Authorized Successful";
					result[1] = comment;

				} else {
					String comment = "Modify action is done on "+actualProblemReportName+". Problem report name changed! logged-in user has modify permission. Name before modify is : "+actualProblemReportName+ " and Name after modify is : "+ modifiedProblemReportName;
					log.info(comment);
					
					result[0] = "Authorized Successful";
					result[1] = comment;
					Thread.sleep(3000);
				
					 GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.CONTAINER_LANDING_PAGE);

				}

				
			} else {
				String comment = "Modify action is done on "+actualProblemReportName+". No Rename Option : Logged-in user don't have modify permission";
				log.info(comment);
				result[0] = "Un-Authorized Successful";
				result[1] = comment;
				// GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CONTAINER_LANDING_PAGE);

			}
	
		return result;
	}

	// ------------------------------------------------
	// SETSTATE : AUTHORIZED & UN-AUTHORIZED
	// ------------------------------------------------

	public String[] setstateAccessRightValidationForProblemReport() throws InterruptedException {

		String[] result = new String[2];

		        Thread.sleep(4000);
				
		        String actualProblemReportName = GenericFunctionLibrary.getText("xpath",ObjectRepository.PROBLEM_REPORT_NAME);
				log.info("Previous Problem Report Name :" + actualProblemReportName);
				
				//clicking on 'actions' button
				GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.ACTION_BUTTON);
				Thread.sleep(5000);
				//verifying whether 'setstate' option is available in action tab or not
				
				if(GenericFunctionLibrary.isElementPresent(ObjectRepository.ACTION_OPTION_SETSTATE)){
					Thread.sleep(4000);
				// navigating to 'setstate' window
				String parentWindow = GenericFunctionLibrary.switchToChildWindow(ObjectRepository.ACTION_OPTION_SETSTATE);
				log.info("Found Set State Option");
				
				Thread.sleep(3000);
				String previousState = GenericFunctionLibrary.getAttributeValue("xpath", ObjectRepository.CURRENT_STATE_VALUE_PR, "value");
				
				log.info("previous state was : " + previousState);
				
				//select state value from dropdown
				GenericFunctionLibrary.selectDropdownByIndex("xpath", ObjectRepository.SELECT_STATE_VALUE_PR, 1);

				Thread.sleep(5000);

				String updatedState = GenericFunctionLibrary.getAttributeValue("xpath", ObjectRepository.CURRENT_STATE_VALUE_PR, "value");

				log.info("updated state is : " + updatedState);		
				
				// click on save
				GenericFunctionLibrary.clickOnElement("id",ObjectRepository.FINISH_BUTTON_ID);


							Thread.sleep(2000);

							// compare previous and updated state, should not same

							if (updatedState.equals(previousState)) {
								String comment = "Set State action is done on "+ actualProblemReportName +". State has not been change after Set State action. State before set State action is : "+previousState +" and state after Set State is : "+ updatedState;
								log.info(comment);
								result[0] = "Authorized Successful";
								result[1] = comment;
								GenericFunctionLibrary.switchToWindow(parentWindow);
								GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CONTAINER_LANDING_PAGE);
							} else {
								String comment = "Set State action is done on "+ actualProblemReportName +". State has been changed hence logged-in user has set state permission. State before set State action is : "+previousState +" and state after Set State is : "+ updatedState;
								log.info(comment);
								
								result[0] = "Authorized Successful";
								result[1] = comment;
								GenericFunctionLibrary.switchToWindow(parentWindow);
								GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CONTAINER_LANDING_PAGE);
							}
					
					} else {
						String comment = "Set State action is done on "+ actualProblemReportName +". Set-State option is not available Hence Logged-in user don't have set state permission ";
						log.info(comment);
						
						result[0] = "Un-Authorized Successful";
						result[1] = comment;
						GenericFunctionLibrary.refreshWindow();
						Thread.sleep(4000);

						GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CONTAINER_LANDING_PAGE);

					}

				return result;

	}
	
	// ------------------------------------------------
	// DELETE : AUTHORIZED & UN-AUTHORIZED
	// ------------------------------------------------
					
	public String[] deleteAccessRightValidationForProblemReport()throws InterruptedException {

		String[] result = new String[2];
			
		String problemReportName = GenericFunctionLibrary.getText("xpath",ObjectRepository.PROBLEM_REPORT_NAME);
		log.info(" Problem Report Name to be deleted " + problemReportName);

		
			// clicking on 'actions' button
			GenericFunctionLibrary.clickOnElement("xpath",	ObjectRepository.ACTION_BUTTON);

			// verifying whether 'delete' option is available in action tab or not
			
			if(GenericFunctionLibrary.isElementPresent(ObjectRepository.DELETE_ACTION)){
				
			// clicking on 'Delete' option
			GenericFunctionLibrary.switchToChildWindow(ObjectRepository.DELETE_ACTION);
			
			
			Thread.sleep(3000);
			
			//handle delete alert popup
			String actualAlert = GenericFunctionLibrary.getAlert();
			String comment = "Delete action is done on "+problemReportName+". Throwing "+ actualAlert +" and deleted Problem Report Successfully after clicking on OK button in alert";
			log.info(comment);
			
			Thread.sleep(3000);
			result[0] = "Authorized Successful";
			result[1] = comment;
			log.info(result[0]);
			// Goto library 
			
			//navigateToRequiredLocation("Components Library Template",ContentRepository.LIBRARY_VALUE);			

			}	else{
				
				String comment = "Delete action is done on "+problemReportName+". Delete option is not available in Action menu and Logged-in user don't have Delete permission ";
				log.info(comment);
				result[0] = "Un-Authorized Successful";
				log.info(result[0]);
				result[1] = comment;
				GenericFunctionLibrary.refreshWindow();
				Thread.sleep(4000);					
				GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CONTAINER_LANDING_PAGE);	
			}
			
			return result;
	}


	// CREATE Content : AUTHORIZED & UN-AUTHORIZED
	
	public String[] createProblemReportAccessRightValidation() throws InterruptedException {

		String[] result = new String[2];

		// searching expected content
		
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
			Thread.sleep(3000);
			String parentWindow = GenericFunctionLibrary.switchToChildWindow(ObjectRepository.NEW_PROBLEM_REPORT_MENU);
			
			Thread.sleep(5000);
			// Input name
			GenericFunctionLibrary.setText("xpath", ObjectRepository.FILE_NAME_FILED_XPATH, Config.PROBLEM_REPORT_NAME+System.currentTimeMillis());
			
			Thread.sleep(3000);
			String inputProblemReportName = GenericFunctionLibrary.getAttributeValue("xpath", ObjectRepository.FILE_NAME_FILED_XPATH, "value");
			log.info("Problem report name :" + inputProblemReportName);
			
			// click finish					
			GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.MODIFY_OK_BUTTON);
							
			Thread.sleep(5000);
							
			GenericFunctionLibrary.switchToChildWindow();			
			
			
			Thread.sleep(3000);
			
			String ConfirmationMessage=	GenericFunctionLibrary.getText("xpath", ObjectRepository.CONFIRMATIONMESSAGE);
			log.info(ConfirmationMessage);
			// clicking on 'submit now' button				
			
			GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.SUBMITNOWBUTTON);
			Thread.sleep(5000);
			
			// switch on parent window
			GenericFunctionLibrary.switchToWindow(parentWindow);
			
			Thread.sleep(7000);
			// clicking on home link
		//	GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.HOME_LINK);
			// Verification	
			
			Thread.sleep(7000);
			
			if (isContentAvailable(inputProblemReportName)) {
				
				String comment = inputProblemReportName+" available in table search and logged-in user is able to create engineering part content";
				log.info(comment);
				
				result[0] = "Authorized Successful";
				result[1] = comment;
			
			}else {
				String comment = inputProblemReportName+" is not available in table search";
				log.info(comment);
				
				GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CANCEL_BUTTON);
				result[0] = "Un-Authorized Successful";
				result[1] = comment;
			}
		
			
		
			Thread.sleep(3000);

			// Goto library 
			//navigateToRequiredLocation("Components Library Template",ContentRepository.LIBRARY_VALUE);
			
	}
			else{
				String comment = " Logged-in user don't have New Problem Report Option in NEW Menu ";
				log.info(comment);
				result[0] = "no Option";
				result[1] = comment;
				GenericFunctionLibrary.refreshWindow();
				Thread.sleep(4000);
				
				GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CONTAINER_LANDING_PAGE);
				
			}
		
					
		}
				
	return result;

}
	
	// CREATE Content : AUTHORIZED & UN-AUTHORIZED [ PROMOTIONAL REQUEST ]

		public String[] createPromotionRequestAccessRightValidation( String ADV_SEARCH_CONTAINER_NAME, String Template) throws InterruptedException, IOException {

			String[] result = new String[2];

			Thread.sleep(3000);
          //String PromotionRequestName = GenericFunctionLibrary.getFileName(ObjectRepository.VERSION_NUMBER_FILENAME);
			String currentStatus = GenericFunctionLibrary.getText("xpath", ObjectRepository.SEARCH_RESULT_STATUS);
		log.info("current content status :" + currentStatus);
			if (currentStatus.equalsIgnoreCase("In Creation")) {

				// goto content detail page, and capturing the content status
				String PromotionRequestName = GenericFunctionLibrary.getFileName(ObjectRepository.VERSION_NUMBER_FILENAME);
				
				//FileUtils.writeStringToFile(new File(Config.PROMOTIONREQUESTNAME_FILE_PATH), PromotionRequestName);
				
				String previousStatus = GenericFunctionLibrary.getText("xpath", ObjectRepository.P_R_STATUS);
				log.info("Status before perform promotion request :" + previousStatus);

				// clicking on 'actions' button
				GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ACTION_BUTTON);

				// verifying whether 'New' option is available in action tab or not

				//if (GenericFunctionLibrary.isElementPresent(ObjectRepository.ACTION_OPTION_PROMOTIONAL_REQUEST)) {
					
				if(verifyAndClickOptionFromActionsDropDown("New")){

					log.info("clicked on new option");

					// Thread.sleep(2000);
//					GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.CHANGE_NOTICE_DROPDOWN, 30);
//					GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CHANGE_NOTICE_DROPDOWN);
					Thread.sleep(1000);
					GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.NEW_PROMOTION_MENU, 30);
					Thread.sleep(1000);
					GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.NEW_PROMOTION_MENU);
					Thread.sleep(1000);

					// Start the New Promotion request action
					String ParentWindow = GenericFunctionLibrary.switchToChildWindow();
					
					// Clicking on 'next' button
					GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.PROMOTION_REQUEST_NEXT_BUTTON, 30);
					GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.PROMOTION_REQUEST_NEXT_BUTTON);

					Thread.sleep(3000);

					if (GenericFunctionLibrary.isMatchFoundInColumn(ObjectRepository.TARGET_PROMOTION_STATE, ContentRepository.targetPromotionState)) {
						
						Thread.sleep(1000);
						
						// selecting values from dropdown
						GenericFunctionLibrary.selectDropdownByVisibleText("xpath", ObjectRepository.TARGET_PROMOTION_STATE, ContentRepository.targetPromotionState);

						Thread.sleep(1000);
						// selecting checkbox
						GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CHANGE_NOTICE_CHECKBOX);

						Thread.sleep(1000);

						// click on promotion request option

						GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.PROMOTION_SYMBLE);
						Thread.sleep(1000);

						// click on next
						GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.PROMOTION_REQUEST_NEXT_BUTTON);

						Thread.sleep(2000);

						// click finish
						GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.MODIFY_OK_BUTTON);

						Thread.sleep(5000);
						// VERIFY STATE CHANGE FROM IN-CREATION TO IN-CREATION REVIEW : VALIDATION CONDITION (IF CONDITION)

						GenericFunctionLibrary.switchToWindow(ParentWindow);

						String ModifiedStatus = GenericFunctionLibrary.getText("xpath", ObjectRepository.P_R_STATUS);
						
						log.info("Status after perform promotion request :" + ModifiedStatus);
						
						//GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CONTAINER_LANDING_PAGE);

						if (ModifiedStatus.equals(previousStatus)) {
							
							String comment = "Content Status doesn't update. file status before "+ previousStatus+" and file status after is "+  ModifiedStatus;
							log.info(comment);
							
							result[0] = "Un-Authorized Successful";
							result[1] = comment;
							Thread.sleep(8000);
							GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CONTAINER_LANDING_PAGE);
							Thread.sleep(3000);

						} else {
							GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CONTAINER_LANDING_PAGE);
						if(advanceSearchModifiedPR(Config.PROMOTION_REQUEST, ADV_SEARCH_CONTAINER_NAME, Template,"Under Review", PromotionRequestName)){
							
							String comment = PromotionRequestName+ " Promotion Request is created. hence user is authorized to create Promotion request";
							log.info(comment);
							result[0] = "Authorized Successful";
							result[1] = comment;
							GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CONTAINER_LANDING_PAGE);
							
							}else{
							log.info("Promotion Request Created and NOT available in Advance Search");
							
							result[0] = "Authorized Successful";
							}
							 
							Thread.sleep(3000);
						}
						
					}

				} else {
					
					String comment = "Logged-in user don't have New Promotion Request permission ";
					log.info(comment);
					
					result[0] = "No Option";
					result[1] = comment;

					Thread.sleep(4000);

					// GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CONTAINER_LANDING_PAGE);
				}
			} else {
				log.info("Content status is not valid to create promotion request");
				
				// GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CONTAINER_LANDING_PAGE);
			}
			
			return result;

		}



	// READ Content : AUTHORIZED & UN-AUTHORIZED [ PROMOTIONAL REQUEST ]

	public String readRightAccessValidationForPromotionRequest(String readObjectName) throws InterruptedException {

		String StatusMessage = null;
		if (isContentAvailablePR(readObjectName)) {
			StatusMessage = "Authorized Successful";
			log.info(" User is Authorized to Read Access permission");

			// redirecting to container landing page
			Thread.sleep(3000);

			// navigateToRequiredLocation(template, containerORLibrary);

		} else {

			StatusMessage = "Un-Authorized Successful";
			log.info(" Either Content is Not Availble inside the Container OR User is UnAuthorized to Read Access Permission");

			// Goto library
			Thread.sleep(3000);

			// navigateToRequiredLocation(template,containerORLibrary);

		}
		return StatusMessage;
	}

	// ******************Change
	// Task*****************************************************

	public String[] createChangeTaskAccessRightValidation() throws InterruptedException {


		String[] result = new String[2];
     
		String expetedAlertMessage = "Nothing selected.";
	
				Thread.sleep(2000);	
				GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.CN_TASK_IMPLEMENT_TAB, 30);
				GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.CN_TASK_IMPLEMENT_TAB);
				
				//click on change task button
				Thread.sleep(5000);	
				GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.CN_TASK_BUTTON, 30);
				//GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.CN_TASK_BUTTON);
				log.info("Change Task starts...");
				Thread.sleep(4000);
				//Start the change notice operation
				String ParentCN=GenericFunctionLibrary.switchToChildWindow(ObjectRepository.CN_TASK_BUTTON);
				Thread.sleep(5000);	
				// String ParentCN=GenericFunctionLibrary.switchToChildWindow(ObjectRepository.CHANGE_NOTICE_LIST);
				if (GenericFunctionLibrary.isAleretPresent(2)) {
					log.info("Alert is Present");

					String ActualErrormessage = GenericFunctionLibrary.getAlert();

					if (ActualErrormessage.contains(expetedAlertMessage)) {
						log.info(" Alert Message is : " + ActualErrormessage);
						result[0] = "Un-Authorized Successful";
						String Comment = "Throwing an alert " + ActualErrormessage + " hence user is not Authorized to create ";
						result[1] = Comment;

					}
				}else {
					
					//Set Name for change notice
					Thread.sleep(2000);
					//String CNTNAME=ContentRepository.ChangeTaskAttributeName;
					GenericFunctionLibrary.setText("xpath",ObjectRepository.FILE_NAME_FILED_XPATH,Config.CHANGE_TASK_NAME+System.currentTimeMillis());
					Thread.sleep(2000);
				    String ChngTskName = GenericFunctionLibrary.getAttributeValue("xpath", ObjectRepository.FILE_NAME_FILED_XPATH, "value");
					GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CHANGE_NOTICE_FINISH);
		        	Thread.sleep(5000);
		            GenericFunctionLibrary.switchToWindow(ParentCN);
		            Thread.sleep(3000);
		            //Search the Change task name in search box
		         //   boolean Value=;
		            if (changeTaskIsContentAvailable(ChngTskName)) {
		            	
		            	String comment = "Search content found : hence user is authorized to create Change Task";
		            	log.info(comment);
		            	result[0] = "Authorized Successful";
		            	result[1] = comment;
		        		log.info("Status Message is : "+result[0]);
		        		Thread.sleep(3000);
		        		//Click on the task 
		        		GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.CN_TASK_SEARCH_LIST,30);
		        		GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CN_TASK_SEARCH_LIST);
		        		Thread.sleep(2000);
		    			GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CHANGENOTICE_LINK_IN_CHNAGETASK);
		    			GenericFunctionLibrary.WaitForElementToBeClickable("xpath", ObjectRepository.DETAILS_MENU, 30);
		    			GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.DETAILS_MENU);
		        				
		        	} else {
		        		String comment = "Created Change Task is not found in table search";
		        		log.info(comment);
		        		result[0] = "Un-Authorized Successful";
		        		result[1] = comment;
		        		Thread.sleep(2000);
		        		GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CHANGENOTICE_LINK_IN_CHNAGETASK);
    				  	GenericFunctionLibrary.WaitForElementToBeClickable("xpath", ObjectRepository.DETAILS_MENU, 30);
    				  	GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.DETAILS_MENU);
		        	}
		           
				}

		   
		return result;
	
}
	

	// Read Change task name
	public String[] readChangeTask(String changeTaskNamevalue) throws InterruptedException {

		String[] result = new String[2];
			Thread.sleep(2000);
			GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.CN_TASK_IMPLEMENT_TAB, 30);
			GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CN_TASK_IMPLEMENT_TAB);

			log.info("Change Task starts...");
			Thread.sleep(2000);

			// Search the Change task name in search box
		
			if (changeTaskIsContentAvailable(changeTaskNamevalue)) {
			
				Thread.sleep(3000);
				String comment = "Change tasks are available in table search hence user is authorized to Read change task";
				result[0] = "Authorized Successful";
				result[1] = comment;
				log.info("Status Message is : " + result[0]);
				Thread.sleep(2000);
				GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CHANGENOTICE_LINK_IN_CHNAGETASK);
			  	GenericFunctionLibrary.WaitForElementToBeClickable("xpath", ObjectRepository.DETAILS_MENU, 30);
			  	GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.DETAILS_MENU);

			} else {

				String comment = "Change Task is not found in table search with "+ changeTaskNamevalue;
        		log.info(comment);
        		
        		result[0] = "Un-Authorized Successful";
        		result[1] = comment;
				log.info("Status Message is : " + result[0]);
				Thread.sleep(2000);
			  	GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CHANGENOTICE_LINK_IN_CHNAGETASK);
			  	GenericFunctionLibrary.WaitForElementToBeClickable("xpath", ObjectRepository.DETAILS_MENU, 30);
			  	GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.DETAILS_MENU);

			}

		
		return result;
	}

	// Modify Change Task name
	public String[] modifyChangeTask(String fileName, String changeTaskState) throws InterruptedException {

		String[] result = new String[2];

			Thread.sleep(2000);
			GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.CN_TASK_IMPLEMENT_TAB, 30);
			GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CN_TASK_IMPLEMENT_TAB);

			log.info("Change Task starts...");
			Thread.sleep(2000);

			// Search the Change task name in search box
			
			if (ischangeTaskAvailableAndClick(changeTaskState)) {
				
				Thread.sleep(2000);
				log.info("Clicking on Search content....... ");
				// Click on the task
				GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.CN_TASK_SEARCH_LIST_CLICK, 30);
				GenericFunctionLibrary.changeTaskSelectIdentityFromList(ObjectRepository.CN_TASK_SEARCH_LIST_CLICK);
				Thread.sleep(2000);
				// rename for change task
				// clicking on 'actions' button
				GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ACTION_BUTTON);
				Thread.sleep(2000);

				if (GenericFunctionLibrary.isElementPresent(ObjectRepository.ACTION_OPTION)) {

					// navigating to 'rename' window
					String parentWindow = GenericFunctionLibrary.switchToChildWindow(ObjectRepository.ACTION_OPTION);

					GenericFunctionLibrary.WaitForElementPresence("xPath",
							ObjectRepository.DOCUMENT_RENAME_INPUT_TEXT_PATH, 30);
					// getting existing content name in the variable
					String currentContentName = GenericFunctionLibrary.getAttributeValue("xPath",
							ObjectRepository.DOCUMENT_RENAME_INPUT_TEXT_PATH, "value");

					GenericFunctionLibrary.clearTextBox("xPath", ObjectRepository.DOCUMENT_RENAME_INPUT_TEXT_PATH);

					// insert new value
					GenericFunctionLibrary.setText("xPath", ObjectRepository.DOCUMENT_RENAME_INPUT_TEXT_PATH,
							fileName +"updt"+System.currentTimeMillis());

					String modifiedContentName = GenericFunctionLibrary.getAttributeValue("xPath",
							ObjectRepository.DOCUMENT_RENAME_INPUT_TEXT_PATH, "value");

					// clicking on 'ok' button
					GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.MODIFY_OK_BUTTON);
					Thread.sleep(4000);
					GenericFunctionLibrary.switchToWindow(parentWindow);
					// comparing the result
					if (modifiedContentName.equals(currentContentName)) {
						String comment = "Change Task Name doesn't update. Name before modify is : "+currentContentName + " and Name after Modify is : "+ modifiedContentName;
						log.info(comment);
						result[0] = "Un-Authorized Successful";
						result[1] = comment;
						log.info("Status Message is : " + result[0]);
						Thread.sleep(2000);
    				  	GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CHANGENOTICE_LINK_IN_CHNAGETASK);
    				  	GenericFunctionLibrary.WaitForElementToBeClickable("xpath", ObjectRepository.DETAILS_MENU, 30);
    				  	GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.DETAILS_MENU);
					} else {
						String comment = " Name changed! logged-in user has modify permissionName before modify is : "+currentContentName + " and Name after Modify is : "+ modifiedContentName;
						log.info(comment);
						log.info("Updated Change Task Name is : " + modifiedContentName);
						Thread.sleep(3000);
						GenericFunctionLibrary.switchToWindow(parentWindow);
						Thread.sleep(2000);
    				  	GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CHANGENOTICE_LINK_IN_CHNAGETASK);
    				  	GenericFunctionLibrary.WaitForElementToBeClickable("xpath", ObjectRepository.DETAILS_MENU, 30);
    				  	GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.DETAILS_MENU);
						GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CONTAINER_LANDING_PAGE);
						log.info("Status Message is : " + result[0]);
						result[0] = "Authorized Successful";
						result[1] = comment;

					}

				}else {
					String comment = "No Rename option in Action Menu";
					log.info(comment);
					result[0] = "Un-Authorized Successful";
					result[1] = comment;
					log.info("Status Message is : " + result[0]);
					Thread.sleep(2000);
				  	GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CHANGENOTICE_LINK_IN_CHNAGETASK);
				  	GenericFunctionLibrary.WaitForElementToBeClickable("xpath", ObjectRepository.DETAILS_MENU, 30);
				  	GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.DETAILS_MENU);
				}

		} else {
			
			String comment = "Change Task record does not exits ";
			log.info(comment);
			result[0] = "Un-Authorized Successful";
			result[1] = comment;
			Thread.sleep(2000);
		  	GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CHANGENOTICE_LINK_IN_CHNAGETASK);
		  	GenericFunctionLibrary.WaitForElementToBeClickable("xpath", ObjectRepository.DETAILS_MENU, 30);
		  	GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.DETAILS_MENU);
			
		}
		return result;
	}

	public String[] setStateChangeTask( String changeTaskState) throws InterruptedException {

		String result[] = new String[2];
		
			Thread.sleep(2000);
			GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.CN_TASK_IMPLEMENT_TAB, 30);
			GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CN_TASK_IMPLEMENT_TAB);

			log.info("Change Task starts...");
			Thread.sleep(2000);

			
			if(ischangeTaskAvailableAndClick(changeTaskState)){
				Thread.sleep(2000);
			// Search the Change task name in search box click on the task
				GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.CN_TASK_SEARCH_LIST_CLICK, 30);
				GenericFunctionLibrary.changeTaskSelectIdentityFromList(ObjectRepository.CN_TASK_SEARCH_LIST_CLICK);
				Thread.sleep(2000);
				// rename for change task clicking on 'actions' button
				GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ACTION_BUTTON);
				Thread.sleep(2000);

				// verifying whether 'setstate' option is available in action tab or not

				if (GenericFunctionLibrary.isElementPresent(ObjectRepository.ACTION_OPTION_SETSTATE)) {

					// navigating to 'setstate' window
					String parentWindow = GenericFunctionLibrary.switchToChildWindow(ObjectRepository.ACTION_OPTION_SETSTATE);

					// select the state value from drop down by using value
					Thread.sleep(2000);
					GenericFunctionLibrary.WaitForElementPresence("xpath",	ObjectRepository.CN_TASK_NEW_SETSTATE_DROPDOWN, 30);
					GenericFunctionLibrary.selectDropdownByIndex("xpath",	ObjectRepository.CN_TASK_NEW_SETSTATE_DROPDOWN, 1);

					Thread.sleep(2000);

					// clicking on 'ok' button
					GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.MODIFY_OK_BUTTON);
					GenericFunctionLibrary.switchToWindow(parentWindow);
					Thread.sleep(2000);
				  	GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CHANGENOTICE_LINK_IN_CHNAGETASK);
				  	GenericFunctionLibrary.WaitForElementToBeClickable("xpath", ObjectRepository.DETAILS_MENU, 30);
				  	GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.DETAILS_MENU);

					String comment = " State changed after set state action performed hence logged-in user has set state permission";
				  	log.info(comment);
				   	result[0] = "Authorized Successful";
				  	result[1] = comment;

				} else {
					String comment = "Logged-in user don't have set state permission ";
					log.info(comment);
					
					result[0] = "Un-Authorized Successful";
					result[1]= comment;
					Thread.sleep(2000);
				  	GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CHANGENOTICE_LINK_IN_CHNAGETASK);
				  	GenericFunctionLibrary.WaitForElementToBeClickable("xpath", ObjectRepository.DETAILS_MENU, 30);
				  	GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.DETAILS_MENU);

				}

			} else {
				String comment = "Change Task record is empty with the searched state, record doesnot exits :" + changeTaskState;
				log.info(comment);
				result[0] = "Un-Authorized Successful";
				result[1] = comment;
				Thread.sleep(2000);
			  	GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CHANGENOTICE_LINK_IN_CHNAGETASK);
			  	GenericFunctionLibrary.WaitForElementToBeClickable("xpath", ObjectRepository.DETAILS_MENU, 30);
			  	GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.DETAILS_MENU);
			}

		
		return result;
	}

	
	// change Task search method
	public boolean ischangeTaskAvailableAndClick(String searchDocument) throws InterruptedException {
		boolean flag = false;
		GenericFunctionLibrary.refreshWindow();
		Thread.sleep(3000);
		// clear textBox
		GenericFunctionLibrary.clearTextBox("xpath", ObjectRepository.CN_TASK_SEARCH_EDIT);
		Thread.sleep(3000);
		// input value in search text box
		GenericFunctionLibrary.setText("xpath", ObjectRepository.CN_TASK_SEARCH_EDIT, searchDocument);
		Thread.sleep(3000);
		// clicking on search button
		GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CN_TASK_SEARCH_BUTTON);
		log.info("clicked on Search Button");
		Thread.sleep(5000);

		// old item CN_TASK_SEARCH_LIST
		//boolean text = isElementPresent("ObjectRepository.CN_TASK_SEARCH_LIST_CLICK");
		
		if (isElementPresent(ObjectRepository.CN_TASK_SEARCH_LIST_CLICK)) {
			flag = true;
			String number = GenericFunctionLibrary.getText("xpath", ObjectRepository.CN_TASK_SEARCH_LIST_CLICK);
			
			log.info("Change Task number is : "+ number);
			
			GenericFunctionLibrary.selectMatchedFoundInList(ObjectRepository.CN_TASK_SEARCH_LIST_CLICK, number);
			log.info("Search content found");
		} else {


			flag = false;

			log.info(" No Search string found");

		}

		return flag;
	}
	// change Task search method
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
		Thread.sleep(5000);

		// old item CN_TASK_SEARCH_LIST
		//boolean text = isElementPresent("ObjectRepository.CN_TASK_SEARCH_LIST_CLICK");
		
		if (isElementPresent(ObjectRepository.CN_TASK_SEARCH_LIST_CLICK)) {
			flag = true;
			String number = GenericFunctionLibrary.getText("xpath", ObjectRepository.CN_TASK_SEARCH_LIST_CLICK);
			
			log.info("Change Task number is : "+ number);
			
			GenericFunctionLibrary.selectMatchedFoundInList(ObjectRepository.CN_TASK_SEARCH_LIST_CLICK, number);
			log.info("Search content found");
		} else {


			flag = false;

			log.info(" No Search string found");

		}

		return flag;
	}

	// Change notice Advance search method
	// Change Notice, Name-Change notice and state
	public String changeNoticeAdvancesearchWithState(String CN_ADV_SEARCH_VALUE, String ChangeNoticeName,
			String Changenoticestatevalue) throws InterruptedException {
		String CN_NUMBER_VALUE="EMPTY";
		String ADV_SEARCH_STATE="State";

		log.info("Change Notice Advance search starts...."); 
		Thread.sleep(2000); 
		/*//Click on advance search button/icon
		GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.CN_ADV_SEARCH_BUTTON, 30);
		GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.CN_ADV_SEARCH_BUTTON);
*/
		
		GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_BUTTON_XPATH, 30);
		GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH_BUTTON_XPATH);
		log.info("Clicked on Search Link from Home page");
		Thread.sleep(2000);
		GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH_TAB_XPATH);
		
		//click on edit advance search
		if(GenericFunctionLibrary.isElementPresent("//div[@id='editSearchCriteriaLink']/a")){
			GenericFunctionLibrary.clickOnElement("xpath", "//div[@id='editSearchCriteriaLink']/a");
			Thread.sleep(2000);
			}
		
		
		Thread.sleep(3000); 
		//click on ALL item check box
		GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_ALL, 30);
		Thread.sleep(4000);
		GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.ADV_SEARCH_ALL);
		Thread.sleep(1000); 
		
		// click on ALL item check box
		GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_ALL, 30);
		GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH_ALL);
		Thread.sleep(1000);
		
		//Select check box based on ADV_SEARCH_VALUE variable
		GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_SEARCHTEXT_VALUE, 30);
		GenericFunctionLibrary.selectMatchedFoundInList(ObjectRepository.ADV_SEARCH_SEARCHTEXT_VALUE,CN_ADV_SEARCH_VALUE);
		Thread.sleep(3000);
		GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.CN_ADVANCE_SEARCH_TEXT, 30);
		GenericFunctionLibrary.clearTextBox("xpath", ObjectRepository.CN_ADVANCE_SEARCH_TEXT);
		GenericFunctionLibrary.setText("xpath",ObjectRepository.CN_ADVANCE_SEARCH_TEXT, ChangeNoticeName );

		Thread.sleep(2000);
		GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.ADV_SEARCH_ADD_ICON);
		Thread.sleep(2000);

		//clicking search STATE drop down*********************************
			GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_SEARCHDROPDOWN_3, 30);
			GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.ADV_SEARCH_SEARCHDROPDOWN_3);
			Thread.sleep(2000);
			 //clicking search STATE 
				//GenericFunctionLibrary.selectMatchedFoundInList(ObjectRepository.ADV_SEARCH_SEARCHDROPDOWN_1,ADV_SEARCH_CREATEDBY);
				//clicking in state drop down 
			GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_SEARCHDROPCLICK_3, 30);
			GenericFunctionLibrary.selectMatchedFoundInList(ObjectRepository.ADV_SEARCH_SEARCHDROPCLICK_3, ADV_SEARCH_STATE);
			Thread.sleep(2000);

			//clicking open drop down 
				GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_SEARCHDROP_VALUE, 30);
				GenericFunctionLibrary.selectDropdownByVisibleText("xpath", ObjectRepository.ADV_SEARCH_SEARCHDROP_VALUE,Changenoticestatevalue);
				//GenericFunctionLibrary.selectDropdownByIndex("xpath", ObjectRepository.ADV_SEARCH_SEARCHDROP_VALUE, 3);
			
			
			Thread.sleep(3000);
			//click on Search button
			GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.ADV_SEARCH);
			
			
			Thread.sleep(3000);
			GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_LIST, 30);
			Thread.sleep(2000);
			if(GenericFunctionLibrary.isElementPresent(ObjectRepository.ADV_SEARCH_LIST))
			{
			//Get the change notice number from the list
				Thread.sleep(2000);
			GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_LIST, 30);
			CN_NUMBER_VALUE=GenericFunctionLibrary.getText("xpath", ObjectRepository.ADV_SEARCH_LIST);
		log.info("Change notice NUMBER is : "+ CN_NUMBER_VALUE);
			}

		return CN_NUMBER_VALUE;
		}


	/***************************************
	 * Change notice
	 *****************************************/
	
	//Read Change notice name
	public String readChangeNotice(String readChangeNoticeName, String readChangeNoticeStatus)throws InterruptedException
	{
	String AccessRight= "";

	String ChangeNoticeValue="EMPTY";

	//Change Notice Advance search
	//Change Notice, Change notice Name

	ChangeNoticeValue=changeNoticeAdvancesearchWithState(ContentRepository.CN_ADV_SEARCH_VALUE,readChangeNoticeName,readChangeNoticeStatus);
		//ChangeNoticeAdvancesearchWithState(ContentRepository.CN_ADV_SEARCH_VALUE,readChangeNoticeName,ContentRepository.CN_ADV_SEARCH_STATE);
		 if(ChangeNoticeValue!="EMPTY")
		 {
			log.info("Change notice number :"+ChangeNoticeValue);
	        log.info("Authorized Successful");
	        AccessRight ="Authorized Successful";
	 
		 }
		 else 
		 {
			AccessRight ="Un-Authorized Successful";
		 }
		 
			return AccessRight;
	 }	
	
	
	

	
	/***************************************Change notice*****************************************/

	public String[] createChangeNoticeAccessRightValidation() throws InterruptedException {

		String[] result = new String[2];
		
		String CNNAME=null;
	
		GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ACTION_BUTTON);
		Thread.sleep(3000);
		// verify 'New' option in action menu

		if(GenericFunctionLibrary.isElementPresent(ObjectRepository.NEW_OPTION)){
			
			// Click on 'NEW'				
			GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.NEW_OPTION);
			Thread.sleep(3000);
			if(GenericFunctionLibrary.isElementPresent(ObjectRepository.CHANGE_NOTICE_LIST)){
			

		//Start the change notice operation
				GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CHANGE_NOTICE_LIST);
			//	Thread.sleep(5000);
		String parentWindow=GenericFunctionLibrary.switchToChildWindow();
			
		Thread.sleep(5000);
		String expetedAlertMessage = "ATTENTION: Action Unavailable.";
		
		
		if (GenericFunctionLibrary.isAleretPresent(2)) {
			log.info("Alert is Present");

			String ActualErrormessage = GenericFunctionLibrary.getAlert();

			if (ActualErrormessage.contains(expetedAlertMessage)) {
				log.info(" Alert Message is : " + ActualErrormessage);
				result[0] = "Un-Authorized Successful";
				String Comment = "Throwing an alert " + ActualErrormessage + " hence user is not Authorized to create ";
				result[1] = Comment;

				GenericFunctionLibrary.switchToWindow(parentWindow);
			}
		
		}else{
	
		
		    
		//Set Name for change notice
		Thread.sleep(5000);
    	
		GenericFunctionLibrary.setText("xpath",ObjectRepository.NAMEFIELD,Config.CHANGE_NOTICE_NAME+System.currentTimeMillis());
		Thread.sleep(2000);
		CNNAME=GenericFunctionLibrary.getAttributeValue("xpath", ObjectRepository.NAMEFIELD, "value");
		log.info("Change notice Name is "+CNNAME);
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
        GenericFunctionLibrary.switchToWindow(parentWindow);
        Thread.sleep(3000);
        
       /* GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.CN_ADVANCE_VERIFY,30);
		GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CN_ADVANCE_VERIFY);
    	Thread.sleep(2000);
       
        */
       
    	log.info("Change notice Name : "+CNNAME);
    	GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CONTAINER_LANDING_PAGE);
    	
    	
    	if(isContentAvailable(CNNAME)) {
    		String comment = CNNAME+" created. hence user is Authoriezed to create Change Notice";
    		log.info(comment);
    		
    		result[0]="Authorized Successful";
    		result[1] = comment;
    		
    	}else{
    		String comment = CNNAME+" created. and is not available in table serach ";
    		log.info(comment);
    		
    		result[0]="Un-Authorized Successful";
    		result[1] = comment;
    	}
    	
		}
    	
	}else{
		String comment = "New Change Notice option is not available";
		log.info(comment);
		
		result[0]="Un-Authorized Successful";
		result[1] = comment;
		
	}
		
		}else{
			
			String comment = "New Option is NOT available";
			log.info(comment);
			
			result[0]="Un-Authorized Successful";
			result[1] = comment;
		}
			return result;
}


	//Modify Change notice name
	public String[] modifyAccessRightValidationForChangeNotice(String fileName)throws InterruptedException{
		
		String[] result = new String[2];
		
				// clicking on 'actions' button
				GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.ACTION_BUTTON);
							
				if(GenericFunctionLibrary.isElementPresent(ObjectRepository.ACTION_OPTION)){
								
				// navigating to 'rename' window
		    	String parentWindow = GenericFunctionLibrary.switchToChildWindow(ObjectRepository.ACTION_OPTION);
							
	    		GenericFunctionLibrary.WaitForElementPresence("xPath",ObjectRepository._RENAME_INPUT_TEXT_PATH,30);
	    		// getting existing content name in the variable
				String currentContentName = GenericFunctionLibrary.getAttributeValue("xPath",ObjectRepository._RENAME_INPUT_TEXT_PATH, "value");

				GenericFunctionLibrary.clearTextBox("xPath",ObjectRepository._RENAME_INPUT_TEXT_PATH);

				// insert new value
				GenericFunctionLibrary.setText("xPath",ObjectRepository._RENAME_INPUT_TEXT_PATH,fileName+"updt"+System.currentTimeMillis());

				String modifiedContentName = GenericFunctionLibrary.getAttributeValue("xPath",ObjectRepository._RENAME_INPUT_TEXT_PATH,	"value");
							
				// clicking on 'ok' button
				GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.MODIFY_OK_BUTTON);

				Thread.sleep(4000);
				// comparing the result
				if (modifiedContentName.equals(currentContentName)) {
								
					String comment = "Modify action is done on"+ currentContentName +". Name doesn't update file name before modify is "+ currentContentName + " file name after modify is "+ modifiedContentName;
					result[0] = "Un-Authorized Successful";
					log.info(comment);
					result[1]  = comment;
					
					} else {
						String comment = "Modify action is done on"+ currentContentName +". Name changed. File updated from "+ currentContentName+" to "+ modifiedContentName;
						log.info(comment);
						
						log.info(comment);
						Thread.sleep(3000);
						GenericFunctionLibrary.switchToWindow(parentWindow);
						GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CONTAINER_LANDING_PAGE);
								
						result[0] = "Authorized Successful";
						result[1]  = comment;
						log.info("Status Message is : "+result[0]);
							
							}
				
							
				}else {
					
					String comment = "Rename optioin is not available in Action drop down hence user is not authorized to Modify Change Notice";
					
					log.info(comment);
					
					result[0] = "Un-Authorized Successful";
					result[1]  = comment;
					log.info("Status Message is : "+result[0]);
					
				}

		return result;

		}
	

	public String[] setStateAccessRightValodationForChangeNotice() throws InterruptedException{
		
		String[] result = new String[2];

				
		String previousState = GenericFunctionLibrary.getAttributeValue("xpath", ObjectRepository.CURRENT_STATE_VALUE_PR, "value");
					Thread.sleep(2000);	
					
					// clicking on 'actions' button
					GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.ACTION_BUTTON);
				
					Thread.sleep(2000);	
				// verifying whether 'setstate' option is available in action tab or not
							
			  	if(GenericFunctionLibrary.isElementPresent(ObjectRepository.ACTION_OPTION_SETSTATE)){		
			  		Thread.sleep(2000);	
				// navigating to 'setstate' window
				String parentWindow = GenericFunctionLibrary.switchToChildWindow(ObjectRepository.ACTION_OPTION_SETSTATE);
				
				Thread.sleep(2000);
				// select the state value from dropdown by using value cancel
				GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.CN_STATE_LIFECYCLE, 30);
				GenericFunctionLibrary.selectDropdownByIndex("xpath", ObjectRepository.CN_STATE_LIFECYCLE,1);

				Thread.sleep(2000);

				// click on save
				GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.CN_STATE_OK);
				
				Thread.sleep(2000);
				
				//Switch to parent window
				GenericFunctionLibrary.switchToWindow(parentWindow);
				Thread.sleep(2000);
				//get Change state after set state
				String FINAL_State=GenericFunctionLibrary.getText("xpath", ObjectRepository.CN_GET_PRESENT_STATE);
				log.info("Present state after set state is : "+FINAL_State);
				
				     if(FINAL_State.equalsIgnoreCase(previousState)){
				    	 result[0] = "Un-Authorized Successful";
				    	 String comment = "State of Change Notice doesnot Change hence user is not authorized to Set State action";
				     log.info(comment);
				    result[1] = comment;
							
				}	else {
					String comment = "State of Change Notice Changed from "+ previousState + " to "+ FINAL_State +"hence user is authorized to Set State action";
					 log.info(comment);
					 result[0] = "Authorized Successful";
					 result[1] = comment;
					log.info("Status Message is : "+result[0]);
				  }
			  	}else {
			  		String comment = "Set State option is not available in actoin menu hence user is not authorized to Set State action";
			  		 log.info(comment);
			  		result[0] = "Authorized Successful";
			  		 result[1] = comment;
					
			  	}
		
		   return result;
		   }	
					
	// CREATE FOLDER : AUTHORIZED & UN-AUTHORIZED

	public String[] createFolderAccessRightValidation(String fileName) throws InterruptedException, IOException {

	
	String[] result = new String[2];
	
	GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.CREATE_FOLDER_ICON, 10);
	
	String ParentWindow = GenericFunctionLibrary.switchToChildWindow(ObjectRepository.CREATE_FOLDER_ICON);

	GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.NAME_ATTRIBUTE, 10);

	GenericFunctionLibrary.setText("xpath", ObjectRepository.NAME_ATTRIBUTE, fileName+System.currentTimeMillis());

	String folderName = GenericFunctionLibrary.getAttributeValue("xpath", ObjectRepository.NAME_ATTRIBUTE,"value");
	
	GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.FINISH_BUTTON, 10);

	if(GenericFunctionLibrary.isWindowClosed(ObjectRepository.FINISH_BUTTON)){
		
		GenericFunctionLibrary.switchToWindow(ParentWindow);
		String comment = folderName+ " Folder created successfully hence user is authorized to create Folder";
		log.info(comment);
		
		result[0] = "Authorized Successful";
		
		log.info(result[0]);
		result[1] = comment;
		
	
	// * Handling alert popup
	}else if(GenericFunctionLibrary.isAleretPresent(5)){
	
	String actualAlertMessage = GenericFunctionLibrary.getAlert();

	String expetedAlertMessage = "ATTENTION:";
	
	if (actualAlertMessage.contains(expetedAlertMessage)) {
		String comment =  " Throwing "+actualAlertMessage+" alert hence Logged-in user don't have create folder permission ";
		log.info(comment);
		
		GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CANCEL_BUTTON_FOLDER);
		result[0] = "Un-Authorized Successful";
		result[1] = comment;
		log.info(result[0]);
	} else {
		String comment = "Some issue other than expected behavior";
		log.info(comment);
		
		result[0] = "Un-Authorized Successful";
		log.info(result[0]);
		result[1] = comment;
	}
	}
	
	GenericFunctionLibrary.switchToWindow(ParentWindow);
	return result;

}
 
	// MODIFY FOLDER : AUTHORIZED
	
	public String[] modifyFolderAccessRightValidation(String Template) throws InterruptedException {

		// Modify : Permission

		String[] result = new String[2];

		String folderName = null;
		if(Template.contains("Restricted")){
			folderName = Config.RESTRICTED+Config.FOLDER_NAME;
			
		} else{
				  folderName = Config.FOLDER_NAME;  
			  }

		if(isFolderAvailable(folderName)){				
			
			WebElement searchedContent = driver.findElement(By.xpath(ObjectRepository.FOLDERSLIST));

			// Initialize Actions class object
			Actions builder = new Actions(driver);

			// Perform Right Click on searched item
			builder.contextClick(searchedContent).perform();
			
			// click on 'edit link'

			WebElement editOption = driver.findElement(By.xpath(ObjectRepository.EDITOPTION));
			builder.moveToElement(editOption,0,0).click().perform();
			
			Thread.sleep(3000);
			String ParentWindow = GenericFunctionLibrary.switchToChildWindow();
			
			String folderNameBeforeModify = GenericFunctionLibrary.getAttributeValue("xpath", ObjectRepository.FOLDERNAME, "value");
			log.info("Prevous folder name :" + folderNameBeforeModify);
			GenericFunctionLibrary.clearTextBox("xpath", ObjectRepository.FOLDERNAME);
			GenericFunctionLibrary.setText("xpath", ObjectRepository.FOLDERNAME,folderName + System.currentTimeMillis());
			String folderNameAfterModify = GenericFunctionLibrary.getAttributeValue("xpath", ObjectRepository.FOLDERNAME, "value");
			log.info("Updated folder name :" + folderNameAfterModify);
						
			if(GenericFunctionLibrary.isWindowClosed(ObjectRepository.FINISH_BUTTON)){
				
				GenericFunctionLibrary.switchToWindow(ParentWindow);
				
				if(folderNameAfterModify.equals(folderNameBeforeModify)){
					String comment = "Folder name is not Changed. Hence user is not Authorized to modify. Folder name before modify "+ folderNameBeforeModify +" folder name after modify is "+ folderNameAfterModify;
					log.info(comment);
					
					result[0] = "Un-Authorized Successful";
					log.info(result[0]);
					result[1] = comment;
				}else{
					String comment = "Folder name changed from "+ folderNameBeforeModify + " to "+ folderNameAfterModify +" Hence user Authorized to modify";
					log.info(comment);
					
					result[0] = "Authorized Successful";
					log.info(result[0]);
					result[1]= comment;
				}
				
		//if(GenericFunctionLibrary.switchToWindow(ParentWindow)){
			// * Handling alert popup
			}else if(GenericFunctionLibrary.isAleretPresent(5)){
			
			String actualAlertMessage = GenericFunctionLibrary.getAlert();
		
			String expetedAlertMessage = "ATTENTION:";
		
		
			if (actualAlertMessage.contains(expetedAlertMessage)) {
				String comment = "Throwing "+ actualAlertMessage +" hence Logged-in user don't have modify folder permission ";
				log.info(comment);
				
				GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CANCEL_BUTTON_FOLDER);
				GenericFunctionLibrary.switchToWindow(ParentWindow);
				result[0] = "Un-Authorized Successful";
				result[1] = comment;
			} else {
				log.info("Fail : Due to some expectable behavior");
			}
			}
		}
			return result;
	}
	
	public String[] deleteFolderAccessRightValidation(String Template) throws InterruptedException{
		
		String[] result = new String[2];
		GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.SEARCH_CLEAR_PATH);
		Thread.sleep(5000);
		
		String folderName=null;
		if(Template.contains("Restricted")){
			folderName = Config.RESTRICTED+Config.FOLDER_NAME;
			  } else{
				  folderName = Config.FOLDER_NAME;  
			  }
		
		// searching folder and click on it
		if(isFolderAvailable(folderName)){
				
		String parentWindow= GenericFunctionLibrary.driver.getWindowHandle();
		GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.CHECK_BOX_PATH, 10);
		GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CHECK_BOX_PATH);
		GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ACTION_MENU_BUTTON);
		GenericFunctionLibrary.switchToChildWindow(ObjectRepository.DELETE_ACTION);
		log.info("parent window id is"+parentWindow);
		Thread.sleep(5000);
		if(GenericFunctionLibrary.isWindowClosed(GenericFunctionLibrary.getAlert())){
			
			String comment = " Folder deleted successfully hence user is authorized to Delete Folder";
			log.info(comment);
			result[0] = "Authorized Successful";
			result[1] = comment;
			
			log.info(result[0]);
			
		}else{
			
				GenericFunctionLibrary.switchToChildWindow();

				// compare checkStatusMessage with expectedMessage
				if (GenericFunctionLibrary.isMatchFoundInList(ObjectRepository.ERROR_MESSAGE, "Failed")) {
					String comment = " Event Management Window opened and showing Failed. Logged-in user do not have permission to perform Delete action";
					log.info(comment);

					// GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CLOSE_BUTTON_FOLDER_DELETE);

					GenericFunctionLibrary.closeChildWindow();
					GenericFunctionLibrary.switchToWindow(parentWindow);
					result[0] = "Un-Authorized Successful";
					log.info(result[0]);
					result[1] = comment;

				}
			}
		
		}else{
			
			String comment = "Folders are not available with the name : "+ folderName; 
			log.info(comment);
			result[0] = "No Data";
			log.info(result[0]);
			result[1] = comment;
	}
		
		return result;
	}
	
	
				
	// CREATE Content : AUTHORIZED & UN-AUTHORIZED

		
			public String problemReportAdvancesearchWithState(String PR_ADV_SEARCH_VALUE,String problemreportName,String problemreportValue)throws InterruptedException {
			
				String PR_NUMBER_VALUE="EMPTY";
				String ADV_SEARCH_STATE="State";

				log.info("Change Notice Advance search starts...."); 
				Thread.sleep(2000); 
				//Click on advance search button/icon
				GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.CN_ADV_SEARCH_BUTTON, 30);
				GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.CN_ADV_SEARCH_BUTTON);

				Thread.sleep(3000); 
				//click on ALL item check box
				GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_ALL, 30);
				Thread.sleep(4000);
				GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.ADV_SEARCH_ALL);
				Thread.sleep(1000); 
				//Select check box based on ADV_SEARCH_VALUE variable
				GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_SEARCHTEXT_VALUE, 30);
				GenericFunctionLibrary.selectMatchedFoundInList(ObjectRepository.ADV_SEARCH_SEARCHTEXT_VALUE,PR_ADV_SEARCH_VALUE);
				Thread.sleep(3000);
				GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.CN_ADVANCE_SEARCH_TEXT, 30);
				GenericFunctionLibrary.setText("xpath",ObjectRepository.CN_ADVANCE_SEARCH_TEXT, problemreportName);

				Thread.sleep(2000);
				GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.ADV_SEARCH_ADD_ICON);
				Thread.sleep(2000);

				//clicking search STATE drop down*********************************
					GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_SEARCHDROPDOWN_3, 30);
					GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.ADV_SEARCH_SEARCHDROPDOWN_3);
					Thread.sleep(2000);
					 //clicking search STATE 
						//GenericFunctionLibrary.selectMatchedFoundInList(ObjectRepository.ADV_SEARCH_SEARCHDROPDOWN_1,ADV_SEARCH_CREATEDBY);
						//clicking in state drop down 
					GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_SEARCHDROPCLICK_3, 30);
					GenericFunctionLibrary.selectMatchedFoundInList(ObjectRepository.ADV_SEARCH_SEARCHDROPCLICK_3, ADV_SEARCH_STATE);
					Thread.sleep(2000);

					//clicking open drop down 
						GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_SEARCHDROP_VALUE, 30);
						GenericFunctionLibrary.selectDropdownByVisibleText("xpath", ObjectRepository.ADV_SEARCH_SEARCHDROP_VALUE,problemreportValue);
						//GenericFunctionLibrary.selectDropdownByIndex("xpath", ObjectRepository.ADV_SEARCH_SEARCHDROP_VALUE, 3);
					
					
					Thread.sleep(3000);
					//click on Search button
					GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.ADV_SEARCH);
					
					
					Thread.sleep(3000);
					GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_LIST, 30);
					Thread.sleep(2000);
					if(GenericFunctionLibrary.isElementPresent(ObjectRepository.ADV_SEARCH_LIST))
					{
					//Get the change notice number from the list
						Thread.sleep(2000);
					GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_LIST, 30);
					PR_NUMBER_VALUE=GenericFunctionLibrary.getText("xpath", ObjectRepository.ADV_SEARCH_LIST);
				log.info("Change notice NUMBER is : "+ PR_NUMBER_VALUE);
					}

				return PR_NUMBER_VALUE;
			}
			
			
			//Advance search method Change Notice, Name-Change notice
			public String Advancesearch(String objectType, String docName)throws InterruptedException {
			String DOC_NUMBER="EMPTY";

			log.info("Change Notice Advance search starts...."); 
			Thread.sleep(2000); 
				
			
			GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_BUTTON_XPATH, 30);
			GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH_BUTTON_XPATH);
			log.info("Clicked on Search Link from Home page");
			Thread.sleep(2000);
			
			// Clicking on Advanced Search Tab

			GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.ADV_SEARCH_TAB_XPATH);
			Thread.sleep(3000);
			Thread.sleep(2000); 

			Thread.sleep(2000); 
			//click on ALL item check box
			GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_ALL, 30);
			GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.ADV_SEARCH_ALL);
			Thread.sleep(1000); 
			//Select check box based on ADV_SEARCH_VALUE variable
			GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.ADV_SEARCH_SEARCHTEXT_VALUE, 30);
			GenericFunctionLibrary.selectMatchedFoundInList(ObjectRepository.ADV_SEARCH_SEARCHTEXT_VALUE,objectType);
			Thread.sleep(3000);
			GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.CN_ADVANCE_SEARCH_TEXT, 30);
			GenericFunctionLibrary.setText("xpath",ObjectRepository.CN_ADVANCE_SEARCH_TEXT, docName);

				Thread.sleep(3000);
				//click on Search button
				GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.ADV_SEARCH);
				
				
				Thread.sleep(3000);
				
				Boolean ADV_SEARCH_LIST_Element=GenericFunctionLibrary.isElementPresent(ObjectRepository.ADV_SEARCH_LIST);
				if(ADV_SEARCH_LIST_Element==true)
				
				{
					
				//Get the document number from the list
				DOC_NUMBER=GenericFunctionLibrary.getText("xpath", ObjectRepository.ADV_SEARCH_LIST);
			 log.info("Change notice NUMBER is : "+ DOC_NUMBER);
				}
			 
			return DOC_NUMBER;
			}

}
