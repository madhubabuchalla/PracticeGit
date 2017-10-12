package test;

import java.io.IOException;

import com.schneider.windchillaccessrightsvalidation.genericlibraries.Config;

public class ClearCache {

	
	public static int cleanTemp() throws IOException, InterruptedException {
		int value =2;	
		value = Runtime.getRuntime().exec("cmd /c start "+Config.CLEAR_CACHE_BATCHFILE_PATH).waitFor();
		return value;
	}
	
	
	public static void main(String [] args) throws IOException, InterruptedException{
		
		if(cleanTemp()==0){
			System.out.println("returned 0");
		}else{
			System.out.println("Not returned 0");
		}
	}
}
