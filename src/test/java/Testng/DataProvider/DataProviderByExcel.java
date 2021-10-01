package Testng.DataProvider;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderByExcel {
	public String baseUrl = "https://en-gb.facebook.com/";
	String driverPath = "/Users/kalpe/eclipse-workspace/TestNGSeleniumDemo/Driver/chromedriver.exe";
	public WebDriver driver ;

	@Test(priority = 0)
	public void launchBrowser() {
	    System.out.println("launching chrome browser"); 
	    System.setProperty("webdriver.chrome.driver", driverPath);
	    driver = new ChromeDriver();
	    driver.get(baseUrl);
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	  @Test(dataProvider = "excel-data", priority = 1)
	  public void firstName_lastName(String username, String password) throws InterruptedException {
		  driver.findElement(By.name("email")).clear();
		  driver.findElement(By.name("email")).sendKeys(username);
		  System.out.println("fname Executed");
		  driver.findElement(By.name("pass")).clear();
		  driver.findElement(By.name("pass")).sendKeys(password);
		  System.out.println("lname Executed");
		  Thread.sleep(2000);
		  //driver.findElement(By.name("websubmit")).click();
		  }
	  
	  @DataProvider(name ="excel-data")
	  public Object[][] excelDP() throws IOException{
      	//We are creating an object from the excel sheet data by calling a method that reads data from the excel stored locally in our system
      	Object[][] arrObj = getExcelData("C:\\Users\\kalpe\\eclipse-workspace\\DataProvider\\DataFiles\\loginData.xlsx","Sheet1");
      	return arrObj;
	}
	  
	//This method handles the excel - opens it and reads the data from the respective cells using a for-loop & returns it in the form of a string array
	public String[][] getExcelData(String fileName, String sheetName) throws IOException{
      	String[][] data = null;   	
	  	
	   	FileInputStream fis = new FileInputStream(fileName);
	   	XSSFWorkbook workbook = new XSSFWorkbook(fis);
	   	XSSFSheet sheet = workbook.getSheet(sheetName);
	   	System.out.println("sheetname:"+sheetName);
	   	XSSFRow row = sheet.getRow(0);
	   	int noOfRows = sheet.getPhysicalNumberOfRows();
	   	int noOfCols = row.getLastCellNum();
	   	System.out.println("No of Rows: " +noOfRows);
	   	System.out.println("No of cols: " +noOfCols);
	   	Cell cell;
	   	data = new String[noOfRows-1][noOfCols];
	   	
	   	for(int i =1; i<noOfRows;i++){
		     for(int j=0;j<noOfCols;j++){
		    	   row = sheet.getRow(i);
		    	   cell= row.getCell(j);
		    	   data[i-1][j] = cell.getStringCellValue();
	   	 	   }
	   	}
	   	workbook.close();
      	return data;
	}
}