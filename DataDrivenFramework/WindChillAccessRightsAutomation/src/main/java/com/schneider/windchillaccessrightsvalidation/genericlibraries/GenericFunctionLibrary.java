package com.schneider.windchillaccessrightsvalidation.genericlibraries;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
//import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.schneider.windchillaccessrightsvalidation.repositories.ObjectRepository;


/**
 * @author SESA439753
 *
 */

public class GenericFunctionLibrary extends Driver {
	
private	static Logger log = Logger.getLogger(GenericFunctionLibrary.class);

	/// *************** Open Browser  *************************************

	public static void launchServer() throws IOException, InterruptedException {
		
		try{

			driverManager();
			
			FileUtils.writeStringToFile(new File(Config.UPLOAD_FILE_PATH), Config.UPLOAD_FILE, "UTF-8",false);
			log.info(Config.UPLOAD_FILE+" has been passed to "+ Config.UPLOAD_FILE_PATH );
			
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				driver.manage().window().maximize();
				String URL = Config.SERVER_URL;
				
				log.info("URL is : "+ URL);
								
				driver.get(URL);
				log.info(URL+ " is Opened");
				Thread.sleep(5000);
		
		}catch(Exception e){
			log.info(e);
		}
		
	}			
	
	//// *************** Click Methods*************************************
	

	public static void clickOnElement(String locatorType, String locatorValue) {

		try {
			By element = byLocator(locatorType, locatorValue);

			driver.findElement(element).click();
			log.info("User clicked on given "+ locatorValue);
		} catch (NoSuchElementException e) {
			log.error("No such given locator value in the page "+ locatorValue, e);
			//e.printStackTrace();
		} catch (NullPointerException e) {
			log.error("Locator value is missed and exception is "+ locatorValue, e);
			//e.printStackTrace();
		} catch (Exception e) {
			log.error("user unable to click on given locator"+ locatorValue,e);
			
			//e.printStackTrace();
		}

	}
	
	/*public static void rightClickonElement()
	js.executeScript("function contextMenuClick(element){"
			+ "var evt = element.ownerDocument.createEvent('MouseEvents');"
					+ "var RIGHT_CLICK_BUTTON_CODE = 2;"
			+ "evt.initMouseEvent('contextmenu', true, true,"
			+ "element.ownerDocument.defaultView, 1, 0, 0, 0, 0, false, false, false, false, RIGHT_CLICK_BUTTON_CODE, null);"
			+ " if (document.createEventObject){"
			+ "return element.fireEvent('onclick', evt)"
			+ "} else{"
			+ " return !element.dispatchEvent(evt);"
			+ "}"
			+ "}; contextMenuClick();", objAgendaPage.anyAgendaLine);
	}*/



public static void scrollDown() throws AWTException {
		//JavascriptExecutor js = (JavascriptExecutor) driver;
		//js.executeScript("window.scrollBy(0,2000)", "");
		//js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_PAGE_DOWN);
		robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
	}

	/// **************** Clear Methods ********************************** 

	public static void clearTextBox(String locatorType, String locatorValue) {
		try{
		
		By element = byLocator(locatorType, locatorValue);
		driver.findElement(element).clear();
		log.info("cleared Text box for given locator "+ locatorValue);
		}catch(Exception e){
			log.error("No such given locator value in the page"+ locatorValue, e);
		}
		
	}

	/// *************************** Setter Methods  ********************** 

	public static void KeyboardActionEnter() throws Exception{
		
		 Robot rb = new Robot();
		rb.keyPress(KeyEvent.VK_DOWN);
        rb.keyRelease(KeyEvent.VK_DOWN);
        Thread.sleep(2000);
        rb.keyPress(KeyEvent.VK_ENTER);
        rb.keyRelease(KeyEvent.VK_ENTER); 

        Thread.sleep(3000);
	}
	
	
	public static void setText(String locatorType, String locatorValue, String textValue) {
		try {
			By element = byLocator(locatorType, locatorValue);
			driver.findElement(element).sendKeys(textValue);
			log.info("entered "+ textValue +" in "+ locatorValue);
		} catch (ElementNotFoundException e) {
			e.printStackTrace();

		} catch (Exception e) {
			
			e.printStackTrace();
		}

	}

	/// *************************** Getter Methods ********************* 

	public static String getAttributeValue(String locatorType, String locatorValue, String AttributeName) {

		String attributeValue = null;
		try {
			By element = byLocator(locatorType, locatorValue);
			attributeValue = driver.findElement(element).getAttribute(AttributeName);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

		return attributeValue;

	}

	public static String getText(String locatorType, String locatorValue) {
		String Text = "";
		
		try{
		By element = byLocator(locatorType, locatorValue);

		Text = driver.findElement(element).getText();

		
	}catch(Exception e){
	
		e.printStackTrace();
		log.error("No such given locator "+locatorValue, e);
	}
		return Text;
	}

	public static String getPageTitle() {
		String pageTitle="";
		try{
	

		pageTitle = driver.getTitle();
		
		}catch (Exception e){
			//e.printStackTrace();
			log.error("Page Title is not Available ", e);
		}
return pageTitle;
	}
	
	
	public static String getVersion(String locatorValue){
		
		String version = "";
		try{
		String rawvalue = getText("id", locatorValue);
		String[] subString = rawvalue.split(",");
		version = subString[2];
		}catch (Exception e){
			//e.printStackTrace();
			log.error("No such locator "+locatorValue ,e);
		}
		return version;
	}
	
public static String getFileName(String locatorValue){
		
		String version = "";
		try{
		String rawvalue = getText("id", locatorValue);
		String[] subString = rawvalue.split(",");
		version = subString[1];
		}catch (Exception e){
			//e.printStackTrace();
			log.error("No such locator "+locatorValue ,e);
		}
		return version;
	}
	
public static String getFileNameMngC(String locatorValue){
	
	String version = "";
	try{
	String rawvalue = getText("id", locatorValue);
	String[] subString = rawvalue.split("-");
	version = subString[1];
	}catch (Exception e){
		//e.printStackTrace();
		log.error("No such locator "+locatorValue ,e);
	}
	return version;
}
	// get cssvalue

			public static String getCSSValue(String locatorValue){
				String color="";
				try{
			color = driver.findElement(By.xpath(locatorValue)).getCssValue("color");
				log.info("link color is " + color);
				log.info("link color is " + color);
				}catch(Exception e){
					//e.printStackTrace();
					log.error("No Such Locator "+ locatorValue,e);
				}
				return color;
			}


	/// *************************** Select Methods ********************** 

	// Selected Drop down By Value

	public static void selectDropdownByValue(String locatorType, String locatorValue, String optionName) {
		try {
			By element = byLocator(locatorType, locatorValue);
			Select dropdown = new Select(driver.findElement(element));
			dropdown.selectByValue(optionName);
			log.info("Seleted "+optionName+ " from the drop down");
		} catch (NoSuchElementException e) {
			//e.printStackTrace();
			log.error("No Such dropdown or Option available "+locatorValue, e);
		} catch (Exception e) {
			//e.printStackTrace();
			log.error("No Such dropdown or Option available "+locatorValue, e);
		}

	}
	
	// Selected Drop down By Visible Text
		public static String  getSelectedValueFromDropDown(String locatorType, String locatorValue, String optionName) {
			String selectedValue = null;
			try {
			
				
				By element = byLocator(locatorType, locatorValue);
				Select dropdown = new Select(driver.findElement(element));
							
				selectedValue = dropdown.getFirstSelectedOption().getText();
				 
				  log.info(selectedValue);
								
				log.info("Seleted "+optionName+ " from the drop down is : "+ selectedValue );
			} catch (NoSuchElementException e) {
				//e.printStackTrace();
				log.error("No Such dropdown or Option available "+locatorValue, e);
			} catch (Exception e) {
				//e.printStackTrace();
				log.error("No Such dropdown or Option available "+locatorValue, e);
			}
	return optionName;

		}
		
	public static boolean optimizedselectDropdown(String locatorType, String locatorValue, String optionsXpath, String PreiousState ){
		boolean isSelected = false;
		
		try {
			By element = byLocator(locatorType, locatorValue);
			Select dropdown = new Select(driver.findElement(element));
			
			
			log.info("Actual Selected dropdown is : "+ dropdown.getFirstSelectedOption());
			
			log.info("Seleted "+PreiousState+ " from the drop down");
		

			
				List<WebElement> Options = driver.findElements(By.xpath(optionsXpath));

				// log.info("Container count : " + Links.size());

				for (WebElement option : Options) {
					
					String optionName = option.getText();

					log.info("option Name " + optionName);

					if (!optionName.equalsIgnoreCase(PreiousState)) {
			
						dropdown.selectByVisibleText(optionName);
						isSelected = true;
						
						log.info(optionName +" is Selected");
						break;
					}
				}
			} catch (StaleElementReferenceException e) {
				log.info("stale element reference: element is not attached to the page document");
				log.error("objects are loading slowly", e);
			} catch (Exception e) {
				e.printStackTrace();
				log.error("some issue ", e);
			}
			
			
		return isSelected;
	}
		
		
	// Selected Drop down By Visible Text
	public static void selectDropdownByVisibleText(String locatorType, String locatorValue, String optionName) {
		try {
			By element = byLocator(locatorType, locatorValue);
			Select dropdown = new Select(driver.findElement(element));
			dropdown.selectByVisibleText(optionName);
			log.info("Seleted "+optionName+ " from the drop down");
		} catch (NoSuchElementException e) {
			//e.printStackTrace();
			log.error("No Such dropdown or Option available "+locatorValue, e);
		} catch (Exception e) {
			//e.printStackTrace();
			log.error("No Such dropdown or Option available "+locatorValue, e);
		}

	}

	// Selected Drop down By Index
	public static void selectDropdownByIndex(String locatorType, String locatorValue, int indexOfOption) {
		try {
			By element = byLocator(locatorType, locatorValue);
			Select dropdown = new Select(driver.findElement(element));
			dropdown.selectByIndex(indexOfOption);
			log.info("Seleted "+indexOfOption+ " from the drop down");
		} catch (NoSuchElementException e) {
			//e.printStackTrace();
			log.error("No Such dropdown or Option available "+locatorValue, e);
		} catch (Exception e) {
			//e.printStackTrace();
			log.error("No Such dropdown or Option available "+locatorValue, e);
		}

	}

	public static void checkingCheckboxes(String checkBoxName) {
		try {
			List<WebElement> checkBoxes = driver.findElements(
					By.xpath(ObjectRepository.ADV_SEARCH_ALL_CONTEXTS));
			for (WebElement checkBox : checkBoxes) {
				String checkbox_Name = checkBox.getText();
				// log.info(checkbox_Name);
				if (checkbox_Name.trim().equalsIgnoreCase(checkBoxName.trim())) {
					log.info(checkBoxName + " Checkbox name exist in the list and clicked on it");
					if(checkBox.isSelected()){
						
						log.info(" Checkbox is already selected");
						break;
					}else{
					checkBox.click();

					break;
					}
				} else {
					continue;
				}
			}
		} catch (StaleElementReferenceException e) {
			log.info("Element with " + "checkbox_Name" + "is not attached to the page document" + "e.getStackTrace()");
		} catch (NoSuchElementException e) {
			log.info("Element " + "checkbox_Name" + " was not found in DOM" + "e.getStackTrace()");
		} catch (Exception e) {
			//e.printStackTrace();
			log.info("Error occurred while hovering" +" e.getStackTrace()");
		}

	}

	public static WebElement selectCheckboxUsingName(String checkBoxName) {
		WebElement chkbox = null;
		try {
			List<WebElement> checkBoxes = driver.findElements(
					By.xpath("//div[@class='filter-section ']/descendant::div[@data-name='Capacity_s']/div"));
			for (int i = 1; i < checkBoxes.size(); i++) {

				WebElement checkBox = driver.findElement(
						By.xpath("//div[@class='filter-section ']/descendant::div[@data-name='Capacity_s']/div[" + i
								+ "]/label/a"));

				String checkbox_Name = checkBox.getText();
				// log.info(checkbox_Name);
				if (checkbox_Name.trim().equalsIgnoreCase(checkBoxName.trim())) {
					log.info(checkBoxName + " Checkbox exist in the list");
					chkbox = checkBox;
					break;

				} else {
					continue;
				}
			}
		} catch (StaleElementReferenceException e) {
			log.info("Element with " + checkBoxName + "is not attached to the page document" +" e.getStackTrace()");
		} catch (NoSuchElementException e) {
			log.info("Element " + checkBoxName + " was not found in DOM" + "e.getStackTrace()");
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error occurred while hovering" + e.getStackTrace());
		}
		return chkbox;

	}

	public void selectMultipleCheckBoxes(int waitTime, String... checkboxNames) throws Exception {

		WebElement checkElement = null;

		try {
			if (checkboxNames.length > 0) {
				for (int i = 0; i < checkboxNames.length; i++) {
					checkElement = selectCheckboxUsingName(checkboxNames[i]);
					log.info("Check box " + i + " is " + checkElement);
					WebDriverWait wait = new WebDriverWait(driver, waitTime);
					wait.until(ExpectedConditions.elementToBeClickable(checkElement));

					WebElement checkBox = checkElement;
					if (checkBox.isSelected()) {
						log.info("CheckBox " + checkElement + " is already selected");
					} else {
						checkBox.click();
						log.info("clicked on " + checkBox);

						Thread.sleep(4000);
					}
				}
			} else {
				log.info("Expected atleast one element as argument to function");
			}
		} catch (StaleElementReferenceException e) {
			log.info("Element - " + checkElement + " is not attached to the page document " + "e.getStackTrace()");
		} catch (NoSuchElementException e) {
			log.info("Element " + checkElement + " was not found in DOM" + "e.getStackTrace()");
			/*
			 * } catch (Exception e) {
			 * takeScreenShoot("D:\\Sample Programs\\Selenium_Framework\\ScreenShots\\" + getClass().getName() + "
			 * : " + new Exception().getStackTrace()[0].getMethodName() + " : "
			 * + getTime() + ".png");
			 */
			log.info("Unable to select checkbox " + e.getStackTrace());
		}

	}
	
	
	
	
	
	
	

	/// *************************** Switch and Navigating methods   *************************

	/*
	 * Switching to frame
	 */

	public static void SwitchToFrame(String locatorValue) {

		try {
			driver.switchTo().frame(locatorValue);
			log.info("Driver switched to Frame "+ locatorValue);
		} catch (NoSuchFrameException e) {
			//e.printStackTrace();
			log.error("No Such Frame witht the Name or ID as "+locatorValue, e);
		} catch (Exception e) {
			//e.printStackTrace();
			log.error("No Such Frame witht the Name or ID as "+locatorValue, e);
		}
	}

	/*
	 * Handling child windows
	 * 
	 * This Method will handle child window and return Parent windowID so that
	 * after handling child window, it will help to switch back to parent window
	 * 
	 */

	public static String switchToChildWindow() {

		String winHandleBefore = driver.getWindowHandle();
		try {
			  	
			// Switch to new window opened
			for (String winHandle : driver.getWindowHandles()) {
				driver.switchTo().window(winHandle);
			}

			String winHandleAfter = driver.getWindowHandle();

			log.info("Child Window Id is " + winHandleAfter);
			log.info("Driver switched to " + winHandleAfter);
			
			
		} catch (NoSuchWindowException e) {
		///	e.printStackTrace();
			log.error("No such window ", e);
		}catch(Exception e){
			log.error("No such window ", e);
		}
		
		return winHandleBefore;
	}

	public static String switchToChildWindow(String xPath) {

		String winHandleBefore = driver.getWindowHandle();
		log.info("Window Before click : "+winHandleBefore);
		
		try {
			clickOnElement("xpath", xPath);

			// Switch to new window opened
			for (String winHandle : driver.getWindowHandles()) {
				driver.switchTo().window(winHandle);
			}

			String winHandleAfter = driver.getWindowHandle();
			
			log.info("Window Id is " + winHandleAfter);
			
					
			Thread.sleep(2000);
	
			log.info("Driver switched to " + winHandleAfter+" after clicking on "+ xPath);
		} catch (NoSuchWindowException e) {
			///e.printStackTrace();
			log.error("No such window ", e);
		}catch(Exception e){
			log.error("No such window ", e);
		}

		return winHandleBefore;
	}

	// Switching to Window using Window ID

	public static boolean switchToWindow(String WindowID) {
		boolean executionStatus = false;
		try {
			driver.switchTo().window(WindowID);
			executionStatus = true;
			log.info("Driver switched to " +WindowID);
		} catch (NoSuchWindowException e) {
		//	e.printStackTrace();
			log.error("No such window ", e);
		}catch(Exception e){
			//e.printStackTrace();
			log.error("No such window ", e);
		}
		return executionStatus;
	}

	public static boolean isOpenedChildWindow(String locatorValue){
		boolean isChildwindow = false;
		String winHandleBefore = driver.getWindowHandle();
		//driver.findElement(By.xpath(locatorValue)).click();
		clickOnElement("xpath", locatorValue);
		Set<String> s=driver.getWindowHandles();
		//this method will gives you the handles of all opened windows

		Iterator<String> ite=s.iterator();

		while(ite.hasNext())
		{
		    String popupHandle=ite.next().toString();
		    if(!popupHandle.contains(winHandleBefore)) {
		    	
		             //   driver.switchTo().window(popupHandle);
		                isChildwindow = true;
		    }  else {
		                	 isChildwindow = false;
		              
		    }
		}
		return isChildwindow;
		}
	public static boolean isOpenedChildWindowNew(String locatorValue){
		boolean isChildwindow = false;
		String winHandleBefore = driver.getWindowHandle();
		//driver.findElement(By.xpath(locatorValue)).click();
		clickOnElement("xpath", locatorValue);
		Set<String> s=driver.getWindowHandles();
		//this method will gives you the handles of all opened windows

		Iterator<String> ite=s.iterator();

		while(ite.hasNext())
		{
		    String popupHandle=ite.next().toString();
		    if(!popupHandle.contains(winHandleBefore)) {
		    	
		               driver.switchTo().window(popupHandle);
		               
		              
		                isChildwindow = true;
		                log.info(" Driver switched to "+ popupHandle);
		    }  else {
		                	 isChildwindow = false;
		              
		    }
		}
		return isChildwindow;
		}
	
	public static boolean isItOpenedChildWindow(String locatorValue, String parentWindow){
		boolean isChildwindow = false;
		String winHandleBefore = driver.getWindowHandle();
		//driver.findElement(By.xpath(locatorValue)).click();
		clickOnElement("xpath", locatorValue);
		Set<String> s=driver.getWindowHandles();
		//this method will gives you the handles of all opened windows

		Iterator<String> ite=s.iterator();

		while(ite.hasNext())
		{
		    String popupHandle=ite.next().toString();
		    if(!popupHandle.contains(winHandleBefore)&& (!popupHandle.contains(parentWindow))) {
		    	
		               // driver.switchTo().window(popupHandle);
		                isChildwindow = true;
		    }  else if(popupHandle.contains(parentWindow)){
		                //	 driver.switchTo().window(parentWindow);
		                	 isChildwindow = false;
		                }
		               // /**/here you can perform operation in pop-up window**/
		                //After finished your operation in pop-up just select the main window again
		               
		    }
		return isChildwindow;
		}
	
	public static boolean isWindowClosed(String locatorValue) throws InterruptedException{
		boolean flag = false;
		String winHandleBefore = driver.getWindowHandle();
		
		log.info("Parent window is : "+ winHandleBefore);
		//driver.findElement(By.xpath(locatorValue)).click();
		clickOnElement("xpath", locatorValue);
		
		Thread.sleep(5000);
		Set<String> s=driver.getWindowHandles();
		//this method will gives you the handles of all opened windows
		int windowsize =s.size();
		log.info("No of windows opened is : "+ windowsize);
		
		if(s.size()==1){
			flag = true;
		} else{
			flag = false;
		}
		return flag;
		}
	
	
	
	
	// Handle alert popup
	public static String getAlert() throws InterruptedException {
		String actualAlertMessage = "";
		try {

			WaitForAleretPresent(6);

			//
			Alert alert = driver.switchTo().alert();
			log.info("driver switched to Alert");
			
			Thread.sleep(5000);
			actualAlertMessage = alert.getText();
			log.info("Alert message :" + actualAlertMessage);
			log.info("driver switched to Alert and alert message is : "+ actualAlertMessage);
			alert.accept();
		} catch (NoAlertPresentException e) {
			e.printStackTrace();
			log.error("No Alert present", e);
		}catch(Exception e){
			log.error("No Alert present", e);
		}
		return actualAlertMessage;

	}
	
	/*
	 * Navigating to product container detail page
	 */

	public static boolean selectIdentityFromList(String locatorvalue, String expectedvalue) throws InterruptedException {
		boolean isSelected = false;
		// Get the table size

		Thread.sleep(10000);

		try {
			List<WebElement> Links = driver.findElements(By.xpath(locatorvalue));

			// log.info("Container count : " + Links.size());

			for (WebElement link : Links) {
				String LinkName = link.getText();

			//	log.info("containerName " + LinkName);

				if (LinkName.equalsIgnoreCase(expectedvalue)) {
		
					link.click();
					log.info("Clicked on : " + LinkName);
					isSelected = true;
					
					log.info(LinkName +" is identified and Clicked on it");
					break;
				}
			}
		} catch (StaleElementReferenceException e) {
			log.info("stale element reference: element is not attached to the page document");
			log.error("objects are loading slowly", e);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("some issue ", e);
		}
return isSelected;
	}

	/// *************************** Wait Methods ********************** 

	public static void WaitForElementPresence(String locatorType, String locatorValue, int timeInSeconds) {
		try {
			By element = byLocator(locatorType, locatorValue);
			WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
			wait.until(ExpectedConditions.presenceOfElementLocated(element));
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		} catch (ElementNotFoundException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void WaitForElementToBeClickable(String locatorType, String locatorValue, int timeInSeconds) {
		
		try {
			By element = byLocator(locatorType, locatorValue);
			WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
			wait.until(ExpectedConditions.elementToBeClickable(element));
		} catch (ElementNotVisibleException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void WaitForElementToBeVisible(String locatorType, String locatorValue, int timeInSeconds) {
		try {
			By element = byLocator(locatorType, locatorValue);
			WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
			wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		} catch (ElementNotVisibleException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void WaitForElementTobeSelected(String locatorType, String locatorValue, int timeInSeconds) {
		try {
			By element = byLocator(locatorType, locatorValue);
			WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
			wait.until(ExpectedConditions.elementToBeSelected(element));
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		} catch (ElementNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void WaitForFrameToBeSwitchToIt(String locatorValue, int timeInSeconds) {
		try {

			WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locatorValue));
		} catch (NoSuchFrameException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void WaitForAleretPresent(int timeInSeconds) {

		WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
		try {

			wait.until(ExpectedConditions.alertIsPresent());

		} catch (NoAlertPresentException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// ??????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
	/*
	 * Compare two strings
	 */

	public void compareStrings(String expectedValue, String actualValue) {

		try {
			if (actualValue.equalsIgnoreCase(expectedValue)) {
				log.info("Pass : Case pass for logged-in user");
			} else {
				log.info("Fail : Case fail for logged-in user");
			}

		} catch (Exception e) {
			log.info("oops!...some problem to comparing the strings");
		}

	}

	public static By byLocator(String locatorType, String locatorvalue) {
		By by = null;
		try {

			if (locatorType.equalsIgnoreCase("id")) {
				by = By.id(locatorvalue);
			}
			if (locatorType.equalsIgnoreCase("name")) {

				by = By.name(locatorvalue);
			}

			if (locatorType.equalsIgnoreCase("linktext")) {

				by = By.linkText(locatorvalue);
			}
			if (locatorType.equalsIgnoreCase("partiallinktext")) {

				by = By.partialLinkText(locatorvalue);
			}
			if (locatorType.equalsIgnoreCase("xpath")) {

				by = By.xpath(locatorvalue);
			}
			if (locatorType.equalsIgnoreCase("css")) {

				by = By.cssSelector(locatorvalue);
			}
			if (locatorType.equalsIgnoreCase("classname")) {

				by = By.className(locatorvalue);
			}
			if (locatorType.equalsIgnoreCase("tagname")) {

				by = By.tagName(locatorvalue);
			}

		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return by;
	}

	//// ******************************** isPresent methods *************************

	
	public static boolean isElementPresentUpdated(String locatorValue) {
		boolean flag = true;
	try{
		WaitForElementPresence("xpath", locatorValue, 6);
		int elements = driver.findElements(By.xpath(locatorValue)).size();
		
		log.info("Elements Size = "+ elements);
		if (elements == 0) {
			flag = false;
			log.info("No elements found");
			log.info("Elements Size = "+ elements+" for given xpath "+ locatorValue);
		}else{
			log.info("Element is available");
			
		}
		
	}catch(Exception e){
			log.error(" No matchings found for given xpath "+ locatorValue,e);
		}
		return flag;
	}
	
	public static boolean isElementPresent(String locatorValue) {
		boolean flag = false;
	try{
		WaitForElementPresence("xpath", locatorValue, 6);
		int elements = driver.findElements(By.xpath(locatorValue)).size();
		
		
		if (elements == 0) {
			flag = false;
			log.info("No elements found"); 
			log.info("Elements Size = "+ elements+" for given xpath "+ locatorValue);
		}else{
			flag = true;
			log.info("Elements Size = "+ elements);
			log.info("Element is available");
		}
		
	}catch(Exception e){
			log.error(" No matchings found for given xpath "+ locatorValue,e);
		}
		return flag;
	}

	public static boolean isElementEnabled(WebElement element) {
		boolean flag = false;
		try {
			if (element.isDisplayed() || element.isEnabled())
				flag = true;
			
		} catch (NoSuchElementException e) {
			flag = false;
			log.error("No such element as "+ element, e);
		} catch (StaleElementReferenceException e) {
			flag = false;
			log.error("Element loading slowly "+ element, e);
		}
		return flag;
	}
	public static boolean isElementEnabled(String locatorType,  String locatorValue) {
		boolean flag = false;
		try {
			By Byelement =byLocator(locatorType, locatorValue);
					WebElement element = driver.findElement(Byelement);
			if (element.isEnabled())
				flag = true;
		} catch (NoSuchElementException e) {
			flag = false;
		} catch (StaleElementReferenceException e) {
			flag = false;
		}
		return flag;
	}
	public static boolean isAleretPresent(int timeInSeconds) {

		boolean foundAlert = false;
		WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
		try {

			wait.until(ExpectedConditions.alertIsPresent());

			foundAlert = true;
			log.info("Alert Present ");
			
		} catch (NoAlertPresentException e) {
			foundAlert = false;
			log.error(" No Alert present ", e);
		} catch (Exception e) {
			foundAlert = false;
			log.error(" No Alert present ", e);
		}

		return foundAlert;
	}

	public static void closeChildWindow() {
		try{
		driver.close();
		log.info("Child Window closed");
		}catch(Exception e){
			log.error("some issue in closing window", e);
		}
		
	}
	public static void closeDriver() {
		try{
		driver.quit();
		log.info("Child Window closed");
		}catch(Exception e){
			log.error("some issue in closing window", e);
		}
		
	}
	

	public static boolean isMatchFoundInColumn(String locatorForColumn, String ExpectedStatus)	throws InterruptedException {

		boolean isfound = false;

		try {
			List<WebElement> status = driver.findElements(By.xpath(locatorForColumn));

			log.info("Options size : " + status.size());

			for (WebElement state : status) {
				String StatusName = state.getText();

		//	log.info("Option Name : " + StatusName);

				if (StatusName.contains(ExpectedStatus)) {

					log.info("Print actual Link name : " + StatusName);
					isfound = true;
					break;
				}
			}
		} catch (StaleElementReferenceException e) {
			log.info("stale element reference: element is not attached to the page document");
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return isfound;
	}
	
	
	public static boolean selectFirstElementInList(String locatorForColumn)
			throws InterruptedException {

		
		boolean isfound = false;

		try {
			List<WebElement> Names = driver.findElements(By.xpath(locatorForColumn));

			//log.info("Container count : " + Names.size());

			for (WebElement Name : Names) {
				String name = Name.getText();
				log.info("Print actual Link name : " + name);
				Name.click();
				break;
				//String name = Name.getText();

			//	log.info("Status : " + name);

				/*if (name.equalsIgnoreCase(ExpectedName)) {

				//	log.info("Print actual Link name : " + name);
					
					Name.click();
					
					isfound = true;
					log.info(Names.size()+" Matches found in given xpath "+locatorForColumn +"and "+ExpectedName+" is found in the List and clicked on it");
					break;
				}else{
					isfound = false;
				}*/
			}
		} catch (StaleElementReferenceException e) {
			log.info("stale element reference: element is not attached to the page document");
			log.error("Elements are loading slowly", e);
		} catch (Exception e) {
			log.info("Exception Occured");
			log.error("some issue in selecting", e);
		}
		return isfound;
	}
	
	///  User want to click and open the search List Item then Use this method
		public static boolean selectMatchedFoundInList(String locatorForColumn, String ExpectedName)
				throws InterruptedException {

			boolean isfound = false;

			try {
				List<WebElement> Names = driver.findElements(By.xpath(locatorForColumn));

				//log.info("Container count : " + Names.size());

				for (WebElement Name : Names) {
					String name = Name.getText().trim();

					//log.info("Status : " + name);

					if (name.equalsIgnoreCase(ExpectedName.trim())) {

					//	log.info("Print actual Link name : " + name);
						
						Name.click();
						
						isfound = true;
						log.info(Names.size()+" Matches found in given xpath "+locatorForColumn +" and "+ExpectedName+" is found in the List and clicked on it");
						break;
					}
				}
				
				/*log.info(" No matches found in the list");
				isfound = false;*/
				
			} catch (StaleElementReferenceException e) {
				log.info("stale element reference: element is not attached to the page document");
				log.error("Elements are loading slowly", e);
			} catch (Exception e) {
				//e.printStackTrace();
				log.error("some issue in selecting", e);
			}
			return isfound;
		}

		
		///  User want to click and open the search List Item then Use this method
		public static boolean selectCheckBoxforMatchedStringInTable(String locatorForColumn, String ExpectedName)
				throws InterruptedException {

			log.info("Given xpath is : "+ locatorForColumn);

			
			boolean isfound = false;

			try {
				List<WebElement> Names = driver.findElements(By.xpath(locatorForColumn));

				log.info("Container count : " + Names.size());
				int i=1;
				for (WebElement Name : Names) {
					String name = Name.getText().trim();
					
					log.info("user name : " + name);

					if (name.equalsIgnoreCase(ExpectedName.trim())) {

						log.info(" matched user name : " + name);
						
						clickOnElement("xpath", "//div[@class='x-grid3-body']/div["+i+"]//tbody/tr/td[1]/div/div[@class='x-grid3-row-checker']");
						log.info("Checkbox Xpath is : "+ "//div[@class='x-grid3-body']/div["+i+"]//tbody/tr/td[1]/div/div[@class='x-grid3-row-checker']");
						isfound = true;
					
						log.info(Names.size()+" Matches found in given xpath "+locatorForColumn +"and check box checked for the "+ ExpectedName);
						break;
					}
					log.info("i value is :"+ i);
					i=i+1;
				}
				
				/*log.info(" No matches found in the list");
				isfound = false;*/
				
			} catch (StaleElementReferenceException e) {
				log.info("stale element reference: element is not attached to the page document");
				log.error("Elements are loading slowly", e);
			} catch (Exception e) {
				//e.printStackTrace();
				log.error("some issue in selecting", e);
			}
			return isfound;
		}
		
		
		
		
		public static boolean isMatchFoundInListupdate(String locatorForColumn, String ExpectedName)
				throws InterruptedException {

			
			boolean isfound = false;

			try {
				List<WebElement> Names = driver.findElements(By.xpath(locatorForColumn));

				log.info("List count : " + Names.size());
				
				

				for (WebElement Name : Names) {
					String name = Name.getText();

					//log.info("Link : " + name);

					if (name.equalsIgnoreCase(ExpectedName)) {
						log.info("Print actual Link name : " + name);
						isfound = true;
						log.info(Names.size()+" Matches found in given xpath "+locatorForColumn +"and "+ExpectedName+" is found in the List ");
						break;
					}
					
				}
			
			} catch (StaleElementReferenceException e) {
				log.info("stale element reference: element is not attached to the page document");
				log.error("Elements are loading slowly", e);
			} catch (Exception e) {
				//e.printStackTrace();
				log.error("some issue in selecting", e);
			}
			return isfound;
		}


	
		
		/// When user wants to identify the matching file from the search List
		public static boolean isMatchFoundInList(String locatorForColumn, String ExpectedName)
				throws InterruptedException {

			boolean isfound = false;

			try {
				List<WebElement> Names = driver.findElements(By.xpath(locatorForColumn));

				//log.info("List count : " + Names.size());
				
				if(Names.size()==0){
					isfound = false;
				}else{

				for (WebElement Name : Names) {
					String name = Name.getText();

				//	log.info("Link : " + name);

					if (name.equalsIgnoreCase(ExpectedName)) {

					//	log.info("Print actual Link name : " + name);
						isfound = true;
						log.info(Names.size()+" Matches found in given xpath "+locatorForColumn +"and "+ExpectedName+" is found in the List ");
						break;
					}
					
				}
				
				}
			} catch (StaleElementReferenceException e) {
				log.info("stale element reference: element is not attached to the page document");
				log.error("Elements are loading slowly", e);
			} catch (Exception e) {
				//e.printStackTrace();
				log.error("some issue in selecting", e);
			}
			return isfound;
		}
		
		public static void refreshWindow(){
			try{
				driver.navigate().refresh();
			}catch (Exception e){
				e.printStackTrace();
			}
			
		}
		
		
		public static String getMethodNames() {
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			return methodName;
		}
		
		public static String getCurrentClassAndMethodNames() {
		    final StackTraceElement e = Thread.currentThread().getStackTrace()[2];
		    final String s = e.getClassName();
		    return s.substring(s.lastIndexOf('.') + 1, s.length()) + "." + e.getMethodName();
		}
		
		public static String getCurrentClass() {
		    final StackTraceElement e = Thread.currentThread().getStackTrace()[2];
		    final String s = e.getClassName();
		    return s.substring(s.lastIndexOf('.') + 1, s.length());
		}
		
		
public static boolean uploadFile(String uploadButtonPath, String filePath) throws IOException, InterruptedException{
	boolean isUploaded = false;
	try{
	GenericFunctionLibrary.clickOnElement("xpath", uploadButtonPath);
	Thread.sleep(2000);
	// calling autoIT script to attach the document file
	int value = Runtime.getRuntime().exec(filePath).waitFor();
	if(value==0){
	isUploaded = true;
	}else{
		isUploaded = true;
	}
		
	log.info(filePath+" Uploaded successfully");
	}catch(Exception e){
		//e.printStackTrace();
		log.warn("Upload Failed ");
	}
	return isUploaded;
}
	
	// capture screenshot  ( Class name as screenshot Name)
	public void screenshotCapture(String screenshotName) throws Exception {

		try {
			String filePath = System.getProperty("user.dir");
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(filePath + screenshotName + System.currentTimeMillis() + ".png"));

		} catch (IOException e1) {
			// e1.printStackTrace();
		}

	}
		
	// Change task click on element for second(third) item in the list due development constrain
		
	public static void changeTaskSelectIdentityFromList(String locatorvalue) throws InterruptedException {

		try {
			List<WebElement> Links = driver.findElements(By.xpath(locatorvalue));

			// log.info("Container count : " + Links.size());
			int i = 0;
			for (WebElement link : Links) {
				String LinkName = link.getText();
				i = i + 1;
				if (i == 2) {
					log.info("containerName ......" + LinkName);
					log.info("Print actual Link name : " + LinkName);
					link.click();
					break;
				}
			}
		} catch (StaleElementReferenceException e) {
			log.info("stale element reference: element is not attached to the page document");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

