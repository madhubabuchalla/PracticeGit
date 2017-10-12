package com.schneider.windchillaccessrightsvalidation.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;


/**
 * @author MADHUBABU
 * 13-Oct-2016
 */
public class ExcelUtil extends ReportGenerator{
	
	private static Logger log = Logger.getLogger(ExcelUtil.class);
		
	ExtentTest extentReport = null;	
	public String filePath=null;
	public FileInputStream fis = null;
	private HSSFWorkbook wb =null;
	private HSSFSheet ws=null;
	
	public ExcelUtil(String filePath ) {
		this.filePath = filePath;
		
		try{
			fis = new FileInputStream(new File(filePath));
		wb = new HSSFWorkbook(fis);
		getExtentInstance();
		log.info("Invoked "+filePath +" Excel File");
		
		}catch(Exception e){
			e.printStackTrace();
			log.info("Probelm in Invoking "+filePath +" Excel File", e);
		}
	}
	
	
	
	
	public int getRowCount(String wsName){
		int rowCount=0;
		int sheetIndex= wb.getSheetIndex(wsName);
		if (sheetIndex== -1){
			log.info("There is NO WorkSheet with the "+wsName);
			return 0;
			
		}else{
			ws = wb.getSheetAt(sheetIndex);
			 rowCount=ws.getLastRowNum();
			 log.info("There are "+ rowCount +" rows in " +wsName + " of "+filePath);
		}
		return rowCount;
	}
	
	public int getColumnCount(String wsName){
		int colCount=0;
		int sheetIndex= wb.getSheetIndex(wsName);
		if (sheetIndex== -1){
			return 0;
		}else{
			colCount=ws.getRow(0).getLastCellNum();
	//		 log.info("There are "+ colCount +" Columns in " +wsName + " of "+filePath);
		}
		return colCount;
	}
	
	public String getUserID(String wsName, String Role, String Template){
		String userID="";
		
		int noOfRows = getRowCount(wsName)+1;
		//int noOfColumns = getColumnCount(wsName);
		
		int sheetIndex= wb.getSheetIndex(wsName);
		
		if(sheetIndex==-1){
			return "";
		}
		else{	
		for(int i=0;i<=noOfRows;i++){
							
			String template =	ws.getRow(i).getCell(3).getStringCellValue();
			
			log.info("Template Name is : "+template);
			String role = ws.getRow(i).getCell(2).getStringCellValue().trim();
			log.info("Role is : " +role);
			if(template.equalsIgnoreCase(Template)&&role.equalsIgnoreCase(Role)){
				userID = ws.getRow(i).getCell(0).getStringCellValue().trim();
			}		
					
			}		
		
		return userID;
		}	
		
	}
	
	public String getData(String wsName, int Rownumber, String colName){
		String data = "";
		int rowNumber = Rownumber;
		int colNumber = 0;
		//int noOfRows = getRowCount(wsName)+1;
		int noOfColumns = getColumnCount(wsName);
		
		int sheetIndex= wb.getSheetIndex(wsName);
		
		if(sheetIndex==-1){
			return "";
		}
		else{	
			
		for(int j=0;j<noOfColumns;j++){
			
			//log.info("GET DATA :::: column num : "+j+" and value is :"+ ws.getRow(0).getCell(j).getStringCellValue());
			if(ws.getRow(0).getCell(j).getStringCellValue().equalsIgnoreCase(colName.trim())){
			colNumber= j;
			
			//log.info(colName+" is at : "+colNumber +" th Column in " +wsName);
			break;
		}
			
		}
		
		 HSSFRow row = ws.getRow(rowNumber);
		 HSSFCell cell = row.getCell(colNumber);
		 data = cellToString(cell);
		   
		//data=ws.getRow(rowNumber).getCell(colNumber).getStringCellValue();
		 
		return data;	
	}

}
	public int getColumnNumber(String wsName, String colName ){
		int colNumber = 0;
		
		int noOfColumns = getColumnCount(wsName);
		
		int sheetIndex= wb.getSheetIndex(wsName);
		
		if(sheetIndex==-1){
			return 0;
		}
		else{
			
			for(int j=0;j<noOfColumns;j++){
				
			//	log.info("WRITE DATA :: column num : "+j+" and value is :"+ ws.getRow(0).getCell(j).getStringCellValue());
				if(ws.getRow(0).getCell(j).getStringCellValue().equalsIgnoreCase(colName.trim())){
				colNumber= j;
			
				break;
				
				}
				
			}
		
		return colNumber;
		}
		
	}
	
	public int getCellNumber(String wsName, int rownumber, String colName ) throws IOException{
		//String outPutFilePath = "C:\\Users\\SESA439753\\Desktop\\Schneider Requirement Documents\\sample.xls";
		//	String data = "";
			
			int colNumber = 0;
			
			int noOfColumns = getColumnCount(wsName);
			
			int sheetIndex= wb.getSheetIndex(wsName);
			
			if(sheetIndex==-1){
				return 0;
			}
			else{
				
				for(int j=0;j<noOfColumns;j++){
					
					//log.info("WRITE DATA :: column num : "+j+" and value is :"+ ws.getRow(0).getCell(j).getStringCellValue());
					if(ws.getRow(0).getCell(j).getStringCellValue().equalsIgnoreCase(colName.trim())){
					
						
					colNumber= j;
					log.info(colName+" is at : "+(colNumber+1) +" th Column in " +wsName);
				//	log.info(colName +" cell is in "+j +" column" );
					break;
					
					}
					
				}
			
			
				return colNumber;
				}
	}
	
	
	
	/*public boolean WriteData(String wsName, int rownumber, String colName , String result) throws IOException{
		//String outPutFilePath = "C:\\Users\\SESA439753\\Desktop\\Schneider Requirement Documents\\sample.xls";
		//	String data = "";
			int rowNumber = rownumber;
			int colNumber = 0;
			
			int noOfColumns = getColumnCount(wsName);
			
			int sheetIndex= wb.getSheetIndex(wsName);
			
			if(sheetIndex==-1){
				return false;
			}
			else{
				
				for(int j=0;j<noOfColumns;j++){
					
				//	log.info("WRITE DATA :: column num : "+j+" and value is :"+ ws.getRow(0).getCell(j).getStringCellValue());
					if(ws.getRow(0).getCell(j).getStringCellValue().equalsIgnoreCase(colName.trim())){
					colNumber= j;
					//log.info(colName +" cell is in "+j +" column" );
					break;
					
					}
					
				}
			
				
				HSSFCell cell = ws.getRow(rowNumber).getCell(colNumber);
				if (cell == null){
					
					createRow(rowNumber);
					cell = ws.getRow(rowNumber).createCell(colNumber);
					cell.setCellValue(result);
					
					log.info("Written " +result +" in excel");
					
					fos = new FileOutputStream(filePath);
					wb.write(fos);
				//	wb.close();
					 
					
					}else{
					cell.setCellValue(result);
					
					log.info("Written "+result +" in excel");
										
					fos = new FileOutputStream(filePath);
					
					wb.write(fos);
				//	wb.close();
					
				}
				return true;
				}
	}*/
	

	/*public boolean WriteData(String wsName, String rowName, String colName , String result) throws IOException{
	
	//	String data = "";
		int rowNumber = 0;
		int colNumber = 0;
		int noOfRows = getRowCount(wsName)+1;
		int noOfColumns = getColumnCount(wsName);
		
		int sheetIndex= wb.getSheetIndex(wsName);
		
		if(sheetIndex==-1){
			return false;
		}
		else{
			ws = wb.getSheet(wsName);
			for(int i = 0; i<noOfRows; i++){
				//log.info(" row num : "+i+" and row value is :"+ws.getRow(i).getCell(0).getStringCellValue());
				if(ws.getRow(i).getCell(0).getStringCellValue().equalsIgnoreCase(rowName.trim())){
					rowNumber = i;
					break;
				} 
			}
			for(int j=0;j<noOfColumns;j++){
				
				//log.info(" column num : "+j+" and value is :"+ ws.getRow(0).getCell(j).getStringCellValue());
				if(ws.getRow(0).getCell(j).getStringCellValue().equalsIgnoreCase(colName.trim())){
				colNumber= j;
				break;
			}
				
			}	
		
			HSSFCell cell = ws.getRow(rowNumber).getCell(colNumber);
			if (cell == null){
				cell = ws.getRow(rowNumber).createCell(colNumber);
				cell.setCellValue(result);
				
				fos = new FileOutputStream(filePath);
				
				wb.write(fos);
			//	wb.close();
			}
			
			return true;
	}
}*/
	
	
	@SuppressWarnings("unchecked")
	public HSSFRow createRow( int index) {
		
		HSSFRow row  = ws.getRow(index);
        if(row==null) return ws.createRow(index);

        Iterator<Cell> it = row.iterator();
        while(it.hasNext()) {
            it.next();
            it.remove();
        }
        return row;
    }
	
	public static String cellToString(HSSFCell cell) {
		
		Object result=null;
		try{
		switch(cell.getCellType()) {
		case Cell.CELL_TYPE_BOOLEAN:
			result = cell.getBooleanCellValue();
			break;
		case Cell.CELL_TYPE_NUMERIC:
			result = cell.getNumericCellValue();
			break;
		case Cell.CELL_TYPE_STRING:
			result = cell.getStringCellValue();
			break;
			// throw new RuntimeException("There are no support for this type of cell");
		}
		}
		catch(NullPointerException e){
			log.info("Null pointer exception");
		}
		
			  return result.toString();
		 }
	
	    
	private CellStyle setStyle(String Result){
		
		CellStyle style =	wb.createCellStyle();
		
		try{
		if(Result.equalsIgnoreCase("Pass")){ 
		
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		
		}else if(Result.equalsIgnoreCase("Fail")){
			
			style.setFillForegroundColor(IndexedColors.RED.getIndex());
		
		}else if(Result.equalsIgnoreCase("NoData")){ 
			
			style.setFillForegroundColor(IndexedColors.BLUE.getIndex()); 
			
		}else if(Result.equalsIgnoreCase("Skip")){ 
			
			style.setFillForegroundColor(IndexedColors.BROWN.getIndex()); 
		}
		
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setAlignment(CellStyle.ALIGN_CENTER_SELECTION);
		style.setBorderBottom(CellStyle.BORDER_THIN);
	    style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
	    style.setBorderLeft(CellStyle.BORDER_THIN);
	    style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
	    style.setBorderRight(CellStyle.BORDER_THIN);
	    style.setRightBorderColor(IndexedColors.BLACK.getIndex());
	    style.setBorderTop(CellStyle.BORDER_THIN);
	    style.setTopBorderColor(IndexedColors.BLACK.getIndex());
		style.setFont(setFont());
		}catch(Exception e){
			e.printStackTrace();
		}
		return style;
		}
	
		
	
	public Font setFont(){
		
		Font font =null;
		try{
		font = wb.createFont();
		font.setFontName("Wingdings 2");
		font.setColor(IndexedColors.YELLOW.getIndex());
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		}catch(Exception e){
			e.printStackTrace();
		}
		return font;
	}
	
	public boolean WriteResultWithComment(String sheetName, int rowNumber, String colName, String accessPermission,
			String Result, String comment) throws IOException 		 {
		
			boolean flag = false;
			Row row = ws.getRow(rowNumber);
			Cell cel = row.getCell(getCellNumber(sheetName, rowNumber, colName));
			cel.setCellType(Cell.CELL_TYPE_STRING);
			cel.setCellStyle(setStyle(Result));
			cel.setCellValue(accessPermission);
			
			setCommnet(sheetName, rowNumber, colName, comment);
			
			FileOutputStream fos = new FileOutputStream(filePath);
			wb.write(fos);
			flag = true;

				return flag;
		}

	
	public boolean writeResult(String sheetName, int rowNumber, String colName, String accessPermission,
		String Result) throws IOException {
	
		boolean flag = false;
		Row row = ws.getRow(rowNumber);
		Cell cel = row.getCell(getCellNumber(sheetName, rowNumber, colName));
		cel.setCellType(Cell.CELL_TYPE_STRING);
		cel.setCellStyle(setStyle(Result));
		cel.setCellValue(accessPermission);
		FileOutputStream fos = new FileOutputStream(filePath);
		wb.write(fos);
		flag = true;

			return flag;
	}

	public void setCommnet(String sheetName, int rowNumber,String  colName, String note) throws IOException{
		Row row = ws.getRow(rowNumber);
		Cell cell = row.getCell(getCellNumber(sheetName, rowNumber, colName));
		HSSFPatriarch patr= ws.createDrawingPatriarch();
		
		//anchor defines size and position of the comment in worksheet
		HSSFComment comment = patr.createComment(new HSSFClientAnchor(0, 0, 0, 0, (short)4, 8, (short) 6, 11));
        //modify background color of the comment
        comment.setFillColor(204, 236, 255);

     // set text in the comment
        comment.setString(new HSSFRichTextString("We can set comments in POI"));	
      //set comment author.
        //you can see it in the status bar when moving mouse over the commented cell
        comment.setAuthor("Apache Software Foundation");
        cell.setCellComment(comment);
		
		
	}
	public void deColourCells(String SheetName){
		
		        log.info("Started");
		        
		        CellStyle style = wb.createCellStyle();
		        style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		        Sheet sheet = wb.getSheet(SheetName);

		        Iterator<Row> rowIterator = sheet.rowIterator();
		        while(rowIterator.hasNext()){
		             Row row = rowIterator.next();
		             Iterator<Cell> cellIterator = row.cellIterator();
		             while(cellIterator.hasNext()) {
		                 Cell cell = cellIterator.next();
		                 cell.setCellStyle(style);
		                
		                 log.info(cell.getStringCellValue());
		             }
	}
	}

	
	public boolean writeResult(ExtentTest reporter, String expectedAccessRight,String validationResult, String sheetName, int rowNumber, String columnName,String rowInfo, String comment ) throws IOException{
	boolean iswritten=false;
	
	if(expectedAccessRight.equalsIgnoreCase("P")&&(validationResult.equalsIgnoreCase("Authorized"))){
		String consolidatedResult = columnName +" Access Right : "+ "<br> " +  rowInfo +"  Expected Result is : Authorized and the Actual Result is : " +validationResult+" Hence "+ columnName +" "+" is Passed"+"<br> <br>" + comment;
		writeResult(sheetName, rowNumber, columnName, expectedAccessRight,"Pass");
		log.info("******* "+ columnName +" : Expected Result is : Authorized and the Actual Result is : " +validationResult+" Hence "+ columnName +" "+" is Passed" + comment);
		reporter.log(Status.PASS, consolidatedResult);
		iswritten =true;
		
	}else if(expectedAccessRight.equalsIgnoreCase("P")&&(validationResult.equalsIgnoreCase("Un-Authorized"))){
		String consolidatedResult = columnName +" Access Right : "+ "<br> " + rowInfo + "  Expected Result is : Authorized and the Actual Result is : " +validationResult+" Hence "+ columnName +" "+" is Failed"+"<br> <br>" + comment;
		writeResult(sheetName, rowNumber, columnName, expectedAccessRight,"Fail");
		log.info("********** "+ columnName +" : Expected Result is : Authorized and the Actual Result is : " +validationResult+" Hence "+ columnName +" "+" is Failed"+"<br>" + comment);
		reporter.log(Status.FAIL, consolidatedResult);
		
		iswritten =true;
		
	}else if(expectedAccessRight.equalsIgnoreCase("O")&&(validationResult.equalsIgnoreCase("Un-Authorized"))){
		String consolidatedResult = columnName +" Access Right : "+ "<br> " + rowInfo +"  Expected Result is : Un-Authorized and the Actual Result is : " +validationResult+" Hence "+ columnName +" "+" is Passed"+"<br> <br>" + comment;
		writeResult(sheetName, rowNumber, columnName, expectedAccessRight,"Pass");
		log.info("******** "+ columnName +" : Expected Result is : Un-Authorized and the Actual Result is : " +validationResult+" Hence  "+ columnName +" "+" is Passed"+"<br>" + comment);
		reporter.log(Status.PASS, consolidatedResult);
		iswritten =true;
		
	}else if(expectedAccessRight.equalsIgnoreCase("O")&&(validationResult.equalsIgnoreCase("Authorized"))){
		String consolidatedResult = columnName +" Access Right : "+ "<br> " + rowInfo +" Expected Result is : Un-Authorized and the Actual Result is : " +validationResult+" Hence "+ columnName +" "+" is Failed"+"<br> <br>" + comment;
		writeResult(sheetName, rowNumber, columnName, expectedAccessRight,"Fail");
		log.info("********** "+ columnName +" : Expected Result is : Un-Authorized and the Actual Result is : " +validationResult+" Hence "+ columnName +" "+" is Failed"+"<br> <br>" + comment);
		reporter.log(Status.FAIL, consolidatedResult);
		
		iswritten =true;
	
	} else if(validationResult.equalsIgnoreCase("No Data")){
		String consolidatedResult = columnName +" Access Right : "+ "<br> " + rowInfo +" Data set is not available in Search Hence "+ columnName +" is not validated";
		writeResult(sheetName, rowNumber, columnName, expectedAccessRight,"NoData");	
		log.info(consolidatedResult);
	   reporter.log(Status.WARNING, consolidatedResult);
	   iswritten =true;
	   
	}else if(validationResult.equalsIgnoreCase("NA")){
		String consolidatedResult = columnName +" Access Right : "+ "<br> " + rowInfo +columnName+"  is marked with " +validationResult+" Hence "+ columnName +" is not validated"+"<br> <br>" + comment;
		writeResult(sheetName, rowNumber, columnName, expectedAccessRight,"Skip");	
		log.info(consolidatedResult);
	   reporter.log(Status.WARNING, consolidatedResult);
	   iswritten =true;
	   
	}
	
	return iswritten;
	}
	
	
			
}

