package test;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.schneider.windchillaccessrightsvalidation.genericlibraries.Authentication;
import com.schneider.windchillaccessrightsvalidation.genericlibraries.Config;
import com.schneider.windchillaccessrightsvalidation.utilities.WindChillAccessRightsValidationUtil;

public class ChangeRolewithAdmin {
Authentication auth = new Authentication();
private static Logger log= Logger.getLogger("ChangeRolewithAdmin");

@Test
public void test(){
	changeRole("Schneider Product Template");
}

public void changeRole(String currentTemplate){
		
		try{
			if (auth.getUserRole(currentTemplate).equalsIgnoreCase(Config.SSO_USER_ROLE)){
			
			//String UserName = auth.getUserName(currentTemplate);
			String UserName = "Unrestricted Viewer";
			//GenericFunctionLibrary.refreshWindow();
			Thread.sleep(2000);
			
			
			if(WindChillAccessRightsValidationUtil.navigateToSubfoldersfromRecentContext(currentTemplate, Config.fetchContainerOrLibrary(currentTemplate),"Team")){
				Thread.sleep(5000);
				
				if(WindChillAccessRightsValidationUtil.selectCheckboxforRequiredUser(UserName.trim())){
					
					
					System.out.println(UserName +" is available and checked checkbox for it");
					
					
				}

			}

			}else{
				log.info("User is not Valid. This User ID is not mapped in Authentication Details SSO.xls");
			}
		}catch(Exception e){
			System.out.println(e);
		}
	}
}
