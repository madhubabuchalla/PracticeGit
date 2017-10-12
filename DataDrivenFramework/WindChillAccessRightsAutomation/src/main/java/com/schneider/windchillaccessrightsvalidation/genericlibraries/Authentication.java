package com.schneider.windchillaccessrightsvalidation.genericlibraries;

import java.io.IOException;
import org.apache.log4j.Logger;

import com.schneider.windchillaccessrightsvalidation.repositories.ObjectRepository;
import com.schneider.windchillaccessrightsvalidation.utilities.ExcelUtil;

/**
 * @author SESA439753
 *
 */
public class Authentication extends Driver{
	
	private static Logger log = Logger.getLogger(Authentication.class);
	public String userID;
	String ActualTemplateInExcel;
	String container;
	String templateAndContainer;
	public String Expected_userID;
	
		
/*	public String loginWith( String Template, String browser, String Role) throws Exception {
		
		
		  THIS METHOD WILL TAKE THE ARGUMENT FROM THAT IS USER NAME FROM THE
		 SUB CLASS AND WILL LOGIN TO SYSTEM
		  
		 
		
		ActualTemplateInExcel = Template;
	//ExcelUtil excel = new ExcelUtil(baseFolder+authenticationFile);
		ExcelUtil excel = new ExcelUtil(Config.SSO_AUTHENTICATION_DETAILS_FILE_PATH);
		
		int rowcount= excel.getRowCount("userIDs");
		
		for (int i=1; i<=rowcount; i++){
		//	log.info("Row Count in Login details page: " + rowcount);
			
		String role = excel.getData("userIDs", i, "Role");
		String template = excel.getData("userIDs", i, "Template");
		container = excel.getData("userIDs", i, "Container / Library");
		
		log.info(role + " : " + template +" : "+ container);
		
		if(Template.contains(template)&&role.equalsIgnoreCase(Role)){
			userID = excel.getData("userIDs", i, "User ID");
			if(browser.equalsIgnoreCase("Chrome")||browser.equalsIgnoreCase("firefox")){
			log.info("Role is : "+role+" : "+userID +" : is going to login to the system" );
			
			String URL = "https://"+userID+":"+Config.PASSWORD+"@"+Config.SERVER_URL;
			log.info("URL is "+ URL);
			log.info("User Loggedin with the Role : "+role + " and the usedId is :"+ userID);
	
			log.info("URL is "+ URL);
			//FileUtils.writeStringToFile(new File(Config.IE_USERNAME_FILE), userID, "UTF-8",false);
										 
			GenericFunctionLibrary.launchServer();
		
			Thread.sleep(5000);
			
			WindChillAccessRightsValidationUtil.navigateToRequiredLocation(Template, container);
			
			log.info("User is Navigated to " + Template +" and "+ container);
			break;
		}	else if (browser.equalsIgnoreCase("ie")){
			
		//	FileUtils.writeStringToFile(new File(Config.IE_USERNAME_FILE), userID, "UTF-8",false);
			GenericFunctionLibrary.launchServer();
			Thread.sleep(2000);
			//Runtime.getRuntime().exec(Config.IE_LOGIN_EXE_AUTOIT_FILE_PATH);
			
			Process process= Runtime.getRuntime().exec(Config.BASE_FOLDER_PATH+"\\AutoITFiles\\IELogin.exe");
            
            int exitValue = process.waitFor();
            			
			log.info("Waiting for Autoit script to Login to IE Browser : "+ exitValue);
			WindChillAccessRightsValidationUtil.navigateToRequiredLocation(Template, container);
			Thread.sleep(2000);
			WindChillAccessRightsValidationUtil.selectDomain(Config.TESTDATA_FOLDER);
			log.info("User is Navigated to " + Template +" and "+ container);
			
			break;
		}
		
				
		}
		}
		
		return userID;
	}
	*/

	
public String getUserRole(String Template) throws IOException, InterruptedException{
	String userRole = "No Role";
	
	GenericFunctionLibrary.launchServer();
	GenericFunctionLibrary.WaitForElementToBeClickable("xpath", ObjectRepository.GLOBAL_USER_XPATH, 10);				
	String Actual_UserID = GenericFunctionLibrary.getText("xpath", ObjectRepository.GLOBAL_USER_XPATH);
	log.info(Actual_UserID + " is LoggedIn");
	
	ActualTemplateInExcel = Template;
	
	ExcelUtil excel = new ExcelUtil(Config.SSO_AUTHENTICATION_DETAILS_FILE_PATH);
	
	int rowcount= excel.getRowCount("userIDs");
	
	for (int i=1; i<=rowcount; i++){
	
	String template = excel.getData("userIDs", i, "Template");
	container = Config.fetchContainerOrLibrary(template);
	String ExpectedUserID = excel.getData("userIDs", i, "User ID");
	
	log.info(ExpectedUserID + " : " + template +" : "+ container);
	
	if(Template.contains(template)&&ExpectedUserID.equalsIgnoreCase(Actual_UserID)){
		userRole = excel.getData("userIDs", i, "Role");
		log.info("Role is : "+userRole+" and User ID is : "+ExpectedUserID +"  is going to login to the system" );
		log.info("User Loggedin with the Role : "+userRole + " and the usedId is :"+ ExpectedUserID);
		break;
	
	}else{
		log.info("User Role is not Maped with UserID in Authentication Details SSO.xls");
	}

	}
	
	return userRole;
}


}


