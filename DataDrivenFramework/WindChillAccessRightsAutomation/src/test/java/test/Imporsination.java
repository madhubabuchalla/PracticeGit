package test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import com.schneider.windchillaccessrightsvalidation.genericlibraries.Config;
import com.schneider.windchillaccessrightsvalidation.genericlibraries.GenericFunctionLibrary;

public class Imporsination {
	public static WebDriver driver = null;
	public static WebDriver ExistingchromeBrowser;
	public static WebDriver ExistingmozillaBrowser;
	public static WebDriver ExistingIEBrowser;
	
	private static Logger log = Logger.getLogger("Imporsination");
	
	@Test
	public void test() throws IOException, InterruptedException{
		openBrowser();
	}
	
public static void openBrowser() throws IOException, InterruptedException {
		
		try{
	
	
	if (Config.BROWSER.equalsIgnoreCase("firefox")){
		//System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
		
		FirefoxProfile firefoxProfile = new FirefoxProfile();
        firefoxProfile.setPreference("browser.privatebrowsing.autostart", true);
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		String URL = Config.PRE_PROD_URL;
		
		System.out.println("URL is "+ URL);
		

		log.info("URL is "+ URL);
		
		GenericFunctionLibrary.launchServer();
		log.info("Mozilla Firefox Driver Initiated");

	} else if (Config.BROWSER.equalsIgnoreCase("chrome")) {

		System.setProperty("webdriver.chrome.driver", Config.BASE_FOLDER_PATH+"\\Drivers\\chromedriver.exe");
		 DesiredCapabilities capabilities = DesiredCapabilities.chrome();
	        ChromeOptions options = new ChromeOptions();
	        options.addArguments("incognito");
	        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
	        driver = new ChromeDriver(capabilities);
	        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			String URL = Config.PRE_PROD_URL;
			
			System.out.println("URL is "+ URL);
			
	
			log.info("URL is "+ URL);
			
			GenericFunctionLibrary.launchServer();
	        Thread.sleep(5000);
	        log.info("Chrome Driver Initiated");
	        
	} else if (Config.BROWSER.equalsIgnoreCase("ie")){
		System.setProperty("webdriver.ie.driver", Config.BASE_FOLDER_PATH+"\\Drivers\\IEDriverServer.exe");
		DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
		capabilities.setCapability(InternetExplorerDriver.FORCE_CREATE_PROCESS, true); 
		capabilities.setCapability(InternetExplorerDriver.IE_SWITCHES, "-private");

        
        // if you get this exception "org.openqa.selenium.remote.SessionNotFoundException: " . uncomment the below line and comment the above line
 //capabilities.setCapability(InternetExplorerDriver.FORCE_CREATE_PROCESS, true);
        
		
		log.info("IE Driver Initiated");
		 driver = new InternetExplorerDriver(capabilities);
		 String URL = Config.PRE_PROD_URL;
					
		System.out.println("URL is "+ URL);
		
		log.info("URL is "+ URL);
					
		driver.get(URL);
	
	}
	

}catch(WebDriverException e){
	System.out.println(GenericFunctionLibrary.getCurrentClassAndMethodNames() +" Driver NOT initiated"+e);
}

}
}
