package com.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.maven.surefire.shared.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.setup.AppDetails;
import com.setup.InitialSetup;

@SuppressWarnings({ })
public class Utilities {
	
	
	//static File file;
	static String inputFromExcel;
	static String projectPath = System.getProperty("user.dir");
	private static FileInputStream input = null;
	private FileOutputStream output = null;
	private static XSSFWorkbook wb = null;
	private static XSSFSheet sh = null;
	private XSSFRow row = null;
	private XSSFCell cell =null;
	static Properties prop;
	WebDriver driver;
	static SimpleDateFormat format;
	static Date date;
	
	
	public Utilities() { //constructor
	
	}
	
	
	//******************************************System date***************************************************///
	public static String getSystemDate() {
		
		format = new SimpleDateFormat("MM/DD/YYYY_HHmmss");
		date = new Date();
		String dt = format.format(date);
		return dt.replace("/","");
	}
	
	
	////////////////////////////Take Screenshots////////////////////////////////
	public static void screenshot (String Filename, WebDriver driver)
	{
		File file=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(file, new File(projectPath+InitialSetup.readProp("screenShots")+Filename+"_"+getSystemDate()+".jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	///***************************************Excel Functions********************************************************************// 
	private static void connectExcel(String sheetname) throws IOException {	///*****This function should be called to use any excel functions*******
		
		String filename = AppDetails.inputExcel;
		File file= new File(projectPath+filename);
		//File file= new File(projectPath+Utilities.readProp(AppDetails.inputExcel));
		input = new FileInputStream(file);
		wb = new XSSFWorkbook(input);
		sh =  wb.getSheet(sheetname);
		
	}

	public String getCellValue(int rownum, int cellnum) throws IOException {//get the String value of a cell
		row = sh.getRow(rownum);
		cell = row.getCell(cellnum);
		inputFromExcel = cell.getStringCellValue();
		return inputFromExcel;
	}
	
	public static Object[][] getTestData(String sheetname) throws IOException{ /// reading data from excel to an Object Array
		connectExcel(sheetname);
		Object [][] testData = new Object [getTotalRows()][getTotalCells()];
		
		for(int i=0;i<getTotalRows();i++)
		{
			for(int j=0;j<getTotalCells();j++)
			{
				testData[i][j] = sh.getRow(i+1).getCell(j).toString(); 
			}
		}
		return testData;
		
	}
	

	private static int getTotalRows() throws IOException {//get the current number of rows
		int totalRows = sh.getLastRowNum();
		//System.out.println("totalrows = "+totalRows);
		return totalRows;
	}
	private static int getTotalCells() throws IOException {//get the current number of rows
		int totalcells = sh.getRow(0).getLastCellNum();
		//System.out.println("totalrows = "+totalRows);
		return totalcells;
	}
	
	public void createSheet(String newSheetName) {//Create new sheet
		sh =  wb.createSheet(newSheetName);
	}
	
	public void addStringinputSheet(String valueToBeEntered,int rownum, int cellnum) throws IOException {///adding a string value to excel
		row = sh.createRow(rownum);
		cell = row.createCell(cellnum);
		cell.setCellValue(valueToBeEntered);
		output = new FileOutputStream(new File(projectPath+InitialSetup.readProp(AppDetails.inputExcel)));
		try {
			wb.write(output);
			wb.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	///***************************************Password encryption Functions********************************************************************// 
	/*
	 * private String encodeString(String str) throws Base64DecodingException {
	 * byte[] decoded = Base64.decode(str.getBytes()); String decodedString = new
	 * String(decoded); return decodedString; }
	 * 
	 * private String decodeString(String str) throws Base64DecodingException {
	 * byte[] decoded = Base64.decode(str.getBytes()); String decodedString = new
	 * String(decoded); return decodedString; }
	 */
	
	

}
