package test;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.schneider.windchillaccessrightsvalidation.genericlibraries.Authentication;
import com.schneider.windchillaccessrightsvalidation.genericlibraries.Config;
import com.schneider.windchillaccessrightsvalidation.genericlibraries.GenericFunctionLibrary;
import com.schneider.windchillaccessrightsvalidation.repositories.ContentRepository;
import com.schneider.windchillaccessrightsvalidation.repositories.ObjectRepository;
import com.schneider.windchillaccessrightsvalidation.utilities.WindChillAccessRightsValidationUtil;

public class CreateCADDocument {
Authentication auth = new Authentication();
private static Logger log= Logger.getLogger("ChangeRolewithAdmin");

@Test
public void test(){
	System.out.println(createCADDocument("Schneider Product Template"));
	}

public String createCADDocument(String currentTemplate){
		
	
	String documentName= null;
		try{
			if (auth.getUserRole(currentTemplate).equalsIgnoreCase(Config.SSO_USER_ROLE)){
			
			
			
			if(WindChillAccessRightsValidationUtil.navigateToWorkSpace(currentTemplate, Config.fetchContainerOrLibrary(currentTemplate), "Workspaces","test1")){
			
				
					GenericFunctionLibrary.SwitchToFrame(ObjectRepository.CAD_IFRAME);
					GenericFunctionLibrary.WaitForElementToBeVisible("xpath", ObjectRepository.CAD_CREATE_ICON, 20);
					if(GenericFunctionLibrary.isElementPresent(ObjectRepository.CAD_CREATE_ICON)){
				
				String parentWindow = GenericFunctionLibrary.switchToChildWindow(ObjectRepository.CAD_CREATE_ICON);
				
					GenericFunctionLibrary.WaitForElementToBeVisible("xpath", ObjectRepository.CADDOCUMENT_TEMPLATE_DROPDOWN, 10);
					GenericFunctionLibrary.selectDropdownByVisibleText("xpath", ObjectRepository.CADDOCUMENT_TEMPLATE_DROPDOWN, ContentRepository.CAD_Template);
					GenericFunctionLibrary.WaitForElementPresence("xpath", ObjectRepository.CADDOCUMENT_NAME_FIELD_XPATH, 10);
					GenericFunctionLibrary.setText("xpath", ObjectRepository.CADDOCUMENT_NAME_FIELD_XPATH, Config.CAD_PART_NAME+System.currentTimeMillis());
					
					Thread.sleep(2000);
				documentName = GenericFunctionLibrary.getAttributeValue("xpath", ObjectRepository.CADDOCUMENT_NAME_FIELD_XPATH, "value");
					
					System.out.println("Created Document Name is : "  + documentName);
					
					
					GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.MODIFY_OK_BUTTON);
					
					/*if(GenericFunctionLibrary.isAleretPresent(10)){
						GenericFunctionLibrary.getAlert();
						
						GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CREATEWINDOW_CANCEL_BUTTON_XPATH);
						GenericFunctionLibrary.switchToWindow(parentWindow);
					}*/
					
					GenericFunctionLibrary.switchToWindow(parentWindow);
					
					GenericFunctionLibrary.clickOnElement("xpath", ObjectRepository.CAD_CHECKIN_ICON);
					}
					
			
			}
			}else{
				log.info("User is not Valid. This User ID is not mapped in Authentication Details SSO.xls");
			}
		}catch(Exception e){
			System.out.println(e);
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
	GenericFunctionLibrary.clickOnElement("xpath",ObjectRepository.MODIFY_OK_BUTTON);
	log.info("User clicked on OK Button");
	// goto parent window
	GenericFunctionLibrary.switchToWindow(parentWindow);

	Thread.sleep(3000);
	
	isCheckedIn = true;
	}else{
		log.info("CheckIn option is NOT available it couldbe checked in already");
		
		isCheckedIn = true;
	}
	
	
	return isCheckedIn;
}

}
