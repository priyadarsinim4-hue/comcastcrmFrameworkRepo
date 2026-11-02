package com.comcast.crm.orgtest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriver.utility.JavaUtility;

public class CreateOrganizationTest {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		
		// create object
		
		FileUtility flib = new FileUtility();
		ExcelUtility elib = new ExcelUtility();
		JavaUtility jlib = new JavaUtility();
		
		//read common data from property file
		
			String BROWSER = flib.getDataFromPropertiesFile("browser");
			String URL = flib.getDataFromPropertiesFile("url");
			String UN = flib.getDataFromPropertiesFile("username");
			String PWD = flib.getDataFromPropertiesFile("password");
	
	//reading testscript data from excel file
	
    String orgname  = elib.getDataFromExcel("organization",1,2)+jlib.getRandomNumber() ;
	
//			
//			//reading testscript data from excel file
//			FileInputStream fis1 = new  FileInputStream("./testdata/tesetscriptdataexcel.xlsx");	
//					 Workbook wb = WorkbookFactory.create(fis1);
//					  Sheet s= wb.getSheet("organization");
//					  Row r = s.getRow(1);
//					  String orgname = r.getCell(2).toString() +randomInt;
//					  wb.close();
			
	    // we should not hard code the data achieving cross browser testing
			WebDriver driver =null ;
			
			if(BROWSER.equals("chrome")) {
				driver= new ChromeDriver();
			}else if (BROWSER.equals("edge")) {
				driver= new EdgeDriver();
			}else if (BROWSER.equals("firefox")) {
				driver= new FirefoxDriver();
			}
			
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.get(URL);
			driver.findElement(By.name("user_name")).sendKeys(UN);
			driver.findElement(By.name("user_password")).sendKeys(PWD);
			driver.findElement(By.id("submitButton")).click();
			
			//navigate to organization module
			driver.findElement(By.linkText("Organizations")).click();
			//st:3 click on create organization button
			driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
			
			//st:4 enter all the details and create new organization
			driver.findElement(By.name("accountname")).sendKeys(orgname);
			driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();
			
			//verify the header message expected result
	    	String headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
	    	if (headerInfo.contains(orgname)) {
				System.out.println(orgname + " is created=== PASS");
			}else {
				System.out.println(orgname + " is not  created=== FAIL");
				}
	    	
	    	//verify header orgname info expected result
	    	String actorgname = driver.findElement(By.id("dtlview_Organization Name")).getText();
	    	if (actorgname.equals(orgname)) {
				System.out.println(orgname + " is created=== PASS");
			}else {
				System.out.println(orgname + " is not  created=== FAIL");
				}
	    	
			Thread.sleep(1000);
			driver.quit();
	    
	}

}
