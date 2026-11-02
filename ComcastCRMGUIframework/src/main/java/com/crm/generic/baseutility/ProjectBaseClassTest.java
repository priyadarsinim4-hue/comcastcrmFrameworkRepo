package com.crm.generic.baseutility;

import java.io.IOException;

import org.junit.AfterClass;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriver.utility.JavaUtility;
import com.comcast.crm.generic.webdriver.utility.UtilityClassObject;
import com.comcast.crm.generic.webdriver.utility.WebDriverUtility;
import com.comcast.crm.objectrepository.HomePage;
import com.comcast.crm.objectrepository.LoginPage;

public class ProjectBaseClassTest {
	
	public  WebDriver driver =null ;
	public static WebDriver sdriver =null;
	
	public FileUtility flib = new FileUtility();
	public ExcelUtility elib = new ExcelUtility();
	public JavaUtility jlib = new JavaUtility();
	public WebDriverUtility wlib = new WebDriverUtility();
	
	public ExtentReports report ;
	public ExtentSparkReporter spark ;
	
	@BeforeSuite
	public void configBs() {
		
		System.out.println(" --- Connect to db and report the config ---- ");
	
		//spark report config
				ExtentSparkReporter spark = new ExtentSparkReporter("./AdvanceReport/report.html");
				spark.config().setDocumentTitle("CRM Test Suite Results ");
				spark.config().setReportName("CRM Report");
				spark.config().setTheme(Theme.DARK);
				
				//add env information and create test
				report = new ExtentReports();
				report.attachReporter(spark);
				report.setSystemInfo("OS", "windows -10");
				report.setSystemInfo("browser", " chrome");
	}
	
	
	//@Parameters("browser")
	@BeforeClass
	public void configBc( ) throws IOException {
		
		System.out.println("execute before class -- here we're going to push luch the browser part ---  ");
		
		String BROWSER =flib.getDataFromPropertiesFile("browser");
		
		if(BROWSER.equals("chrome")) {
			driver= new ChromeDriver();
		}else if (BROWSER.equals("edge")) { 
			driver= new EdgeDriver();
		}else if (BROWSER.equals("firefox")) {
			driver= new FirefoxDriver();
		}
		sdriver=driver;
		UtilityClassObject.setDriver(driver);
	}
	
	@BeforeMethod
	public void configBm() throws IOException {
		
		System.out.println("execute before method -- here we're going to login to appln part --- ");
		
		LoginPage lp = new LoginPage(driver);
		String URL = flib.getDataFromPropertiesFile("url");
		String UN = flib.getDataFromPropertiesFile("username");
		String PWD = flib.getDataFromPropertiesFile("password");
		lp.loginToApp(URL,UN, PWD);
		
	}
	
	
	@AfterMethod
	public void configAm() {
		
		System.out.println("execute after method   -- here we're going to logout from the appln part ---");
		
		HomePage hp =new HomePage(driver);
		hp.logOut();
	
	}
	
	@AfterClass
	public void configAc() {
		
		System.out.println("execute after class --- here we're going to push the close the browser part ---- ");
		driver.quit();
	}
	
	
	@AfterSuite
	public void configAs() {
		
		System.out.println(" ---- close the db and report the backup ---- ");
		
		report.flush(); 
	}
}
