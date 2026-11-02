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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriver.utility.JavaUtility;

public class CreateOrgWithPhoneNumberTest {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub

		// create object
		
		FileUtility flib = new FileUtility();
		ExcelUtility elib = new ExcelUtility();
		JavaUtility jlib = new JavaUtility();
		
		//read common data from property file
		 FileInputStream fis = new FileInputStream("./configAppData/commondataproperty.properties");
			Properties pobj = new Properties();
			pobj.load(fis);
			String BROWSER = flib.getDataFromPropertiesFile("browser");
			String URL = flib.getDataFromPropertiesFile("url");
			String UN = flib.getDataFromPropertiesFile("username");
			String PWD = flib.getDataFromPropertiesFile("password");

			//getting testscriptdata from excel 
			
					  String orgname  = elib.getDataFromExcel("organization",4,2)+jlib.getRandomNumber() ;
					  String industry  = elib.getDataFromExcel("organization",4,3) ;
					  String type  = elib.getDataFromExcel("organization",4,4) ;
					  String ph_no  = elib.getDataFromExcel("organization",7,3) ;
					 
			
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
			
			//selecting industry and type
			 WebElement weindus =driver.findElement(By.name("industry"));
			 Select sel = new Select(weindus);
			 sel.selectByVisibleText(industry);
			 
			 
			 WebElement wetype =driver.findElement(By.name("accounttype"));
			 Select sel1 = new Select(wetype);
			 sel1.selectByVisibleText(type);
			 
			 //entering phone number
			 driver.findElement(By.id("phone")).sendKeys(ph_no);
			 
			driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();			
	    	
	    	//verify the industry and type dropdown
	    	String actindustry = driver.findElement(By.id("dtlview_Industry")).getText();
	    	if (actindustry.equals(industry)) {
				System.out.println(industry + "is created=== PASS");
			}else {
				System.out.println(industry + "is not  created=== FAIL");
				}
	    	
	    	
	    	String acttype = driver.findElement(By.id("dtlview_Type")).getText();
	    	if (acttype.equals(type)) {
				System.out.println(type + " is created=== PASS");
			}else {
				System.out.println(type + " is not  created=== FAIL");
				}
	    	
	    	//verifying phone number is created or not
	    	String actph_no = driver.findElement(By.id("dtlview_Phone")).getText();
	    	if (actph_no.equals(ph_no)) {
				System.out.println(ph_no + " is created=== PASS");
			}else {
				System.out.println(ph_no + " is not  created=== FAIL");
				}
	    	
			Thread.sleep(1000);
			driver.quit();
	}

}
