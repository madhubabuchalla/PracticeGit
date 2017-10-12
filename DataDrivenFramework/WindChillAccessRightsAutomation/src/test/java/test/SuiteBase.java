package test;

import java.io.IOException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.schneider.windchillaccessrightsvalidation.genericlibraries.GenericFunctionLibrary;

/**
 * @author MADHUBABU
 * 30-Nov-2016
 */
public class SuiteBase {
@AfterSuite

public void quitBrowser(){
	GenericFunctionLibrary.closeDriver();
}


@BeforeSuite
public void launchBrowser() throws IOException, InterruptedException{
	GenericFunctionLibrary.launchServer();
}


}
