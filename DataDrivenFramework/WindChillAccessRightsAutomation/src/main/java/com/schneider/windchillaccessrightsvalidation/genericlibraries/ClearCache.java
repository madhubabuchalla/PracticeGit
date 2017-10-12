package com.schneider.windchillaccessrightsvalidation.genericlibraries;

import java.io.IOException;

public class ClearCache {

	public static int cleanTemp() throws InterruptedException, IOException {
		int value =1;	
		value = Runtime.getRuntime().exec("cmd /c start "+Config.CLEAR_CACHE_BATCHFILE_PATH).waitFor();
		return value;
	}
	
}
