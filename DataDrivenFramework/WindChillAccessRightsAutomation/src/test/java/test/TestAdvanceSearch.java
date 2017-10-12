package test;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.schneider.windchillaccessrightsvalidation.genericlibraries.Config;
import com.schneider.windchillaccessrightsvalidation.repositories.ContentRepository;
import com.schneider.windchillaccessrightsvalidation.utilities.WindChillAccessRightsValidationUtil;

/**
 * @author MADHUBABU
 * 14-Nov-2016
 */
public class TestAdvanceSearch {
	WindChillAccessRightsValidationUtil windChillUtil = new WindChillAccessRightsValidationUtil();
	
	//String Container = Autentication.container;
String Template = "Schneider Product Template";

String CREATED_BY_USER = "SESA439756_DM";
	@BeforeTest

	public void BeforeClass() throws Exception {

	//	windChillUtil.login();


	}
	 
	@Test(priority=0)
	 
	public void Advancesearch() throws InterruptedException 
	{
		//SESA439756_DM,Describe Document,Verif_A_Demo,Product & In Creation
		//boolean Advancesearchstatus = windChillUtil.advanceSearchForCAD(CREATED_BY_USER, ContentRepository.ADV_SEARCH_OBJECT_TYPE,Config.containerOrLibrary(Template),Template,ContentRepository.ADV_SEARCH_STATE_STATUS,ContentRepository.ADVANCE_SEARCH_BY_NAME);
		
		boolean Advancesearchstatus = windChillUtil.advanceSearchModified(ContentRepository.ADV_SEARCH_OBJECT_TYPE,Config.fetchContainerOrLibrary(Template),Template,ContentRepository.ADV_SEARCH_STATE_STATUS,ContentRepository.ADVANCE_SEARCH_BY_NAME);
		
		System.out.println("Status is : "+ Advancesearchstatus);
	
	}
}
