package com.comcast.crm.practicepomclass;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriver.utility.JavaUtility;
import com.comcast.crm.generic.webdriver.utility.WebDriverUtility;

public class SampleTestWithPom {
		
		@FindBy(name="user_name")
		private WebElement ele1;
		
		@FindBy(name="user_password")
		private  WebElement ele2;
		
		@FindBy(id="submitButton")
		private WebElement ele3;
		
		//implementation using autohealing
		@FindAll({@FindBy(id = "submit"),@FindBy(xpath="//input[@type='submit']")})
		private WebElement login;
		
		@Test
		public void sampleTest() throws IOException{
			
			FileUtility flib = new FileUtility();
			ExcelUtility elib = new ExcelUtility();
			JavaUtility jlib = new JavaUtility();
			WebDriverUtility wlib = new WebDriverUtility();
			
			//read common data from property file
			
				String BROWSER = flib.getDataFromPropertiesFile("browser");
				String URL = flib.getDataFromPropertiesFile("url");
				String UN = flib.getDataFromPropertiesFile("username");
				String PWD = flib.getDataFromPropertiesFile("password");
				
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
							SampleTestWithPom s = PageFactory.initElements(driver,SampleTestWithPom.class );
							s.ele1.sendKeys(UN);				
							s.ele2.sendKeys(PWD);
							//driver.navigate().refresh(); // output: stale element reference exception
							
							s.login.click();
		}
		
}



