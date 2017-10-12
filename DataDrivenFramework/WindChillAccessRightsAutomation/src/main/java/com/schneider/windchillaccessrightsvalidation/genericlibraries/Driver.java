package com.schneider.windchillaccessrightsvalidation.genericlibraries;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

/**
 * @author SESA439753
 *
 */

public class Driver  {

	public static WebDriver driver = null;
	public static WebDriver ExistingchromeBrowser;
	public static WebDriver ExistingmozillaBrowser;
	public static WebDriver ExistingIEBrowser;
	
	private static Logger log = Logger.getLogger(Driver.class);
	
	
	public static void driverManager() throws Exception {
		
		try {

			if (Config.BROWSER == null) {

				log.warn("Driver is NOT Specified in Config.java file for the BROWSER variable");

			} else {
				// Check If any previous webdriver browser Instance Is exist
				// then run new test in that existing webdriver browser
				// Instance.

				if (Config.BROWSER.equalsIgnoreCase("firefox") && ExistingmozillaBrowser != null) {
					driver = ExistingmozillaBrowser;
					return;
				} else if (Config.BROWSER.equalsIgnoreCase("chrome") && ExistingchromeBrowser != null) {
					driver = ExistingchromeBrowser;
					log.info("taken the existing chrome instance");
					return;
				} else if (Config.BROWSER.equalsIgnoreCase("IE") && ExistingIEBrowser != null) {
					driver = ExistingIEBrowser;
					return;
				}

				if (Config.BROWSER.equalsIgnoreCase("firefox")) {
					// Load Firefox driver Instance.
					driver = new FirefoxDriver();
					ExistingmozillaBrowser = driver;
					log.info("Firefox Driver Instance loaded successfully.");

				} else if (Config.BROWSER.equalsIgnoreCase("Chrome")) {
					// Load Chrome driver Instance.
					System.setProperty("webdriver.chrome.driver",
							System.getProperty("user.dir") + "//Drivers//chromedriver.exe");
					driver = new ChromeDriver();
					ExistingchromeBrowser = driver;
					log.info("Chrome Driver Instance loaded successfully.");

				} else if (Config.BROWSER.equalsIgnoreCase("IE")) {
					// Load IE driver Instance.
					System.setProperty("webdriver.ie.driver",
							System.getProperty("user.dir") + "//Drivers//IEDriverServer.exe");
					driver = new InternetExplorerDriver();
					ExistingIEBrowser = driver;
					log.info("IE Driver Instance loaded successfully.");

				}
			}

		} catch (Exception e) {
			log.info(e);
		}
	}
					
				
		
}



