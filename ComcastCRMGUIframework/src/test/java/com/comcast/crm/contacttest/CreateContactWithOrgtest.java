package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.commons.collections4.bag.SynchronizedSortedBag;
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
import com.comcast.crm.generic.webdriver.utility.WebDriverUtility;

public class CreateContactWithOrgtest {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
    
	// create object
		
		FileUtility flib = new FileUtility();
		ExcelUtility elib = new ExcelUtility();
		JavaUtility jlib = new JavaUtility();
		WebDriverUtility wlib = new WebDriverUtility();
		
		//read common data from property file
		
		
			String BROWSER = flib.getDataFromPropertiesFile("browser");
			String URL = flib.getDataFromPropertiesFile("url");
			String UN = flib.getDataFromPropertiesFile("username");
			String PWD = flib.getDataFromPropertiesFile("password");
		
			
			//reading testscript data from excel file
					  String orgname  = elib.getDataFromExcel("contact",7,2)+jlib.getRandomNumber() ;
					  String ContactLastName  = elib.getDataFromExcel("contact",7,3)+jlib.getRandomNumber();
			
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
			wlib.waitForPageToLoad(driver);
			//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
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
			driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				Thread.sleep(3000);
			// navigate to contact module
			driver.findElement(By.linkText("Contacts")).click();
			
			//click on create new contact icon 
			driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
			
	    	//Thread.sleep(1000);
			//filling last name mandatory text field
			driver.findElement(By.name("lastname")).sendKeys(ContactLastName);
			
			//click on created organization icon
			driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
			
			//switch to child window
			
			wlib.switchToTabOnURL(driver, "module=Accounts");
						
			//searching the latest created orgname for that we have to use dynamic xpath
				driver.findElement(By.id("search_txt")).sendKeys(orgname);
				driver.findElement(By.name("search")).click();
			    driver.findElement(By.xpath("//a[text()='"+orgname+"']")).click();
			
			//switch to parent window
			    
			    wlib.switchToTabOnTitle(driver, "module=Contacts");
			
			//click on save button 
			driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
			
			//verify the contact header message expected result
	    	String headerInfo1 = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
	    	if (headerInfo1.contains(ContactLastName)) {
				System.out.println(ContactLastName + " is created=== PASS");
			}else {
				System.out.println(ContactLastName + " is not  created=== FAIL");
				}
			
			//verify header orgname info expected result
	    	String actorgname = driver.findElement(By.id("mouseArea_Organization Name")).getText();
	    	System.out.println(actorgname);// 
	    	if (actorgname.trim().equals(orgname)) {
				System.out.println(orgname + " is created=== PASS integration flow happend");
			}else {
				System.out.println(orgname + " is not created=== FAIL");
				}
	
	    	Thread.sleep(1000);
			driver.quit();

	}
	}
