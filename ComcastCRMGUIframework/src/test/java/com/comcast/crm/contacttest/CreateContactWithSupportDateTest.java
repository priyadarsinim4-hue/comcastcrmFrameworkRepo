package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
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

public class CreateContactWithSupportDateTest {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
    
		// create object
		
		FileUtility flib = new FileUtility();
		ExcelUtility elib = new ExcelUtility();
		JavaUtility jlib = new JavaUtility();
		
		
		//read common data from property file:
		
			String BROWSER = flib.getDataFromPropertiesFile("browser");
			String URL = flib.getDataFromPropertiesFile("url");
			String UN = flib.getDataFromPropertiesFile("username");
			String PWD = flib.getDataFromPropertiesFile("password");


	     String LastName = elib.getDataFromExcel("contact",4,2)+jlib.getRandomNumber() ;
					
			
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
			
			//click on contact 
			driver.findElement(By.linkText("Contacts")).click();
			
			//click on create new contact icon 
			driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
			
			//filling last name mandatory text field
			driver.findElement(By.name("lastname")).sendKeys(LastName);
			
			//support start date
			
			String startdate= jlib.getSystemDateYYYYDDMM();
			String enddate = jlib.getRequiredDateYYYYDDMM(-30);
			
			driver.findElement(By.name("support_start_date")).clear();
			driver.findElement(By.name("support_start_date")).sendKeys(startdate);
			
			//supportenddate support_end_date
			driver.findElement(By.name("support_end_date")).clear();
			driver.findElement(By.name("support_end_date")).sendKeys(enddate);
			
			//getting error because end date is not capturing properly
			
			//click on save button 
			driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
			
			Thread.sleep(2000);
			
			//verify the header message expected result
	    	String headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
	    	if (headerInfo.contains(LastName)) {
				System.out.println(LastName + "  header is created=== PASS");
			}else {
				System.out.println(LastName + " header is not  created=== FAIL");
				}
			
			//verifying start date and enddate  has been created or not 
			String actstartdate = driver.findElement(By.id("dtlview_Support Start Date")).getText();
			if(actstartdate.equals(startdate)) {
				System.out.println(startdate+ " has been created sucessfully === PASS ");
			}else {
				System.out.println(startdate+ "hasn't created === FAIL");
			}
			
			String actenddate = driver.findElement(By.id("dtlview_Support End Date")).getText();
			if(actenddate.equals(enddate)) {
				System.out.println(enddate+ " has been created sucessfully === PASS ");
			}else {
				System.out.println(enddate+ "hasn't created === FAIL");
			}
			
			
			Thread.sleep(2000);
			driver.quit();
			
			
	}

}
