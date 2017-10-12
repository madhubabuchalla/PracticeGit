package test;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.schneider.windchillaccessrightsvalidation.genericlibraries.Authentication;
import com.schneider.windchillaccessrightsvalidation.genericlibraries.Config;
import com.schneider.windchillaccessrightsvalidation.genericlibraries.Driver;
import com.schneider.windchillaccessrightsvalidation.genericlibraries.GenericFunctionLibrary;
import com.schneider.windchillaccessrightsvalidation.repositories.ObjectRepository;
import com.schneider.windchillaccessrightsvalidation.utilities.ExcelUtil;
import com.schneider.windchillaccessrightsvalidation.utilities.WindChillAccessRightsValidationUtil;

public class UserValidation extends Driver{
	
	
	private static Logger log = Logger.getLogger(Authentication.class);
	public String Expected_userID;
	String ActualTemplateInExcel;
	String container;
	String templateAndContainer;

	
	@Test
	public void test() throws Exception{
		GenericFunctionLibrary.launchServer();
		validateUser("Schneider Product Template","Author");
	}
	
	
	
public boolean validateUser( String Template, String Role) throws Exception {
		
		/*
		  THIS METHOD WILL TAKE THE ARGUMENT FROM THAT IS USER NAME FROM THE
		 SUB CLASS AND WILL LOGIN TO SYSTEM
		*/  
		 
	boolean isValid = false;	
	
	
		ActualTemplateInExcel = Template;
	//ExcelUtil excel = new ExcelUtil(baseFolder+authenticationFile);
		ExcelUtil excel = new ExcelUtil(Config.AUTHENTICATION_DETAILS_FILE_PATH);
		
		int rowcount= excel.getRowCount("userIDs");
		
		for (int i=1; i<=rowcount; i++){
			
		//	System.out.println("Row Count in Login details page: " + rowcount);
			
		String role = excel.getData("userIDs", i, "Role");
		String template = excel.getData("userIDs", i, "Template");
		container = excel.getData("userIDs", i, "Container / Library");
		
		System.out.println(role + " : " + template +" : "+ container);
		
		if(Template.contains(template)&&role.equalsIgnoreCase(Role)){
			Expected_userID = excel.getData("userIDs", i, "User ID");
			System.out.println("Role is : "+role+" : "+Expected_userID +" : is going to login to the system" );
			log.info("User Loggedin with the Role : "+role + " and the usedId is :"+ Expected_userID);
			break;
		}
		}
			
		GenericFunctionLibrary.WaitForElementToBeClickable("xpath", ObjectRepository.GLOBAL_USER_ID, 10);				
		String Actual_UserID = GenericFunctionLibrary.getText("id", ObjectRepository.GLOBAL_USER_ID);
		System.out.println(Actual_UserID + " is LoggedIn");
		if (Actual_UserID.equalsIgnoreCase(Expected_userID)) {
			
			isValid = true;
			WindChillAccessRightsValidationUtil.navigateToRequiredLocation(Template, container);
			
			System.out.println("User is Navigated to " + Template +" and "+ container);

			}else{
			System.out.println("User is not mapped in Authentication Details.xls");
			}
		//}		
		
		return isValid;
		

	}
	
	
	
}
