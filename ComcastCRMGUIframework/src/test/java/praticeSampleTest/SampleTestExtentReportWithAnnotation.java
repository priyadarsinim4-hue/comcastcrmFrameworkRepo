package praticeSampleTest;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class SampleTestExtentReportWithAnnotation {
	
	// in real time @bs and @as should be there in base class
	
	ExtentReports report ;
	@BeforeSuite
	public void beforeSuiteConfig() {
		
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
	
	@AfterSuite
	public void afterSuiteConfig() {
	
		report.flush(); 
		
	}
	
	@Test
	public void createContactTest() {
		
	    WebDriver driver = new ChromeDriver();
	    driver.get(" http://49.249.29.4:8888/");
	    
	    //attaching screenshot in extent report:
	    
	    TakesScreenshot ts = (TakesScreenshot)driver ;
	    String filepath = ts.getScreenshotAs(OutputType.BASE64);// remember sc should keep in listner implementation class
	    
		ExtentTest test = report.createTest("Create Contact Test");
		test.log(Status.INFO, "login to app");
		test.log(Status.INFO, "navigate to contact page");
		test.log(Status.INFO, "create contact");
		if ("HDFC".equals("HDFgC")) {
			test.log(Status.PASS, "contact is created ");	
		} else {
			test.log(Status.FAIL, "contact is not created ");
			test.addScreenCaptureFromBase64String(filepath, "error file");
		}
		
		driver.quit();
	}
}
	
	
