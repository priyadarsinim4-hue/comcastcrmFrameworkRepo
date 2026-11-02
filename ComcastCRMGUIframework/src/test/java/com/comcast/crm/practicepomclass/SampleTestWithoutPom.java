package com.comcast.crm.practicepomclass;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriver.utility.JavaUtility;
import com.comcast.crm.generic.webdriver.utility.WebDriverUtility;

public class SampleTestWithoutPom {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub


		FileUtility flib = new FileUtility();
		ExcelUtility elib = new ExcelUtility();
		JavaUtility jlib = new JavaUtility();
		WebDriverUtility wlib = new WebDriverUtility();
		
		//read common data from property file
		
			String BROWSER = flib.getDataFromPropertiesFile("browser");
			String URL = flib.getDataFromPropertiesFile("url");
			String UN = flib.getDataFromPropertiesFile("username");
			String PWD = flib.getDataFromPropertiesFile("password");
		
		// we should not hard code the data achieving cross browser testing
						WebDriver driver =null ;
						
						if(BROWSER.equals("chrome")) {
							driver= new ChromeDriver();
						}else if (BROWSER.equals("edge")) {
							driver= new EdgeDriver();
						}else if (BROWSER.equals("firefox")) {
							driver= new FirefoxDriver();
						}
						wlib.waitForPageToLoad(driver);
						
						driver.get(URL);
						
						WebElement ele1=driver.findElement(By.name("user_name"));
						ele1.sendKeys(UN);
						
						WebElement ele2 = driver.findElement(By.name("user_password"));
						ele2.sendKeys(PWD);
						
						WebElement ele3 = driver.findElement(By.id("submitButton"));
						
						driver.navigate().refresh(); // output: stale element reference exception
						
						ele3.click();
	}

}
