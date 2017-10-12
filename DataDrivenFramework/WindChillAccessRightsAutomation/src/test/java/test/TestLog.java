package test;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

public class TestLog {

	static final Logger debugLog = Logger.getLogger("file");
	static final Logger reportLog = Logger.getLogger("reportsLogger");
	
	
	@Test
	public void logTest(){
		
		
		debugLog.info("This is Debug Log");
		
		reportLog.info("this is report log");
	}
}