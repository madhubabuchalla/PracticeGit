package com.schneider.windchillaccessrightsvalidation.utilities;

import java.net.InetAddress;
import java.net.UnknownHostException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.schneider.windchillaccessrightsvalidation.genericlibraries.Config;

public class ReportGenerator {
	
	public static ExtentReports extent = null;
	private static ExtentHtmlReporter htmlReporter;
	private static String filePath = Config.ADVANCED_HTML_REPORT_FOLDER_PATH+"\\AdvancedHTMLreport.html";
	
	public static ExtentReports getExtentInstance() throws UnknownHostException{
		
	      // start reporters
		htmlReporter = new ExtentHtmlReporter(filePath);
        htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setDocumentTitle("Schneider Automation Report");
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName("WindChill Access Rights Validation Report");
        //htmlReporter.setAppendExisting(true);
       // htmlReporter.loadXMLConfig("./config.xml");
        
        // create ExtentReports and attach reporter(s)
        
        if (extent != null){
        	return extent;
       
        }else{
        	 extent = new ExtentReports();
        	 InetAddress IP=InetAddress.getLocalHost();
        	 extent.setSystemInfo("OS", System.getProperty("os.name"));
        	 extent.setSystemInfo("IP", IP.toString()); 
        	// extent.setSystemInfo("HOST NAME", IP.getHostName());
        	 extent.setSystemInfo("USER NAME", System.getProperty("user.name"));
        	 extent.setSystemInfo("ENVIRONMENT", Config.SERVER_URL);
        	 extent.setSystemInfo("ROLE", Config.SSO_USER_ROLE);
        	 extent.attachReporter(htmlReporter); 
        	        	 
        }
        return extent;
	}
	
}
