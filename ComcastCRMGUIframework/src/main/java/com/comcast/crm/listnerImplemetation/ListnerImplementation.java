package com.comcast.crm.listnerImplemetation;

import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.generic.webdriver.utility.UtilityClassObject;
import com.crm.generic.baseutility.ProjectBaseClassTest;

public class ListnerImplementation implements ITestListener, ISuiteListener {
	
	public static ExtentReports report ;
	public ExtentSparkReporter spark ;
	ExtentTest test;
	
	
	@Override
	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub
		System.out.println("report configuration");
		
		String time = new Date().toString().replace(" ","_").replace(":", "_");
		ExtentSparkReporter spark = new ExtentSparkReporter("./AdvanceReport/report1"+time+".html");
		spark.config().setDocumentTitle("CRM Test Suite Results ");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);
		
		//add env information and create test
		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "windows -10");
		report.setSystemInfo("browser", " chrome");
	}

	@Override
	public void onFinish(ISuite suite) {
		// TODO Auto-generated method stub
		System.out.println("report backup");
		report.flush(); 
	}

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("====="+result.getMethod().getMethodName()+"====start===");
		UtilityClassObject.setTest(test);
		 test = report.createTest(result.getMethod().getMethodName());

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("====="+result.getMethod().getMethodName()+"====END===");
		test.log(Status.PASS,result.getMethod().getMethodName()+"===PASS====" );
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		String testName = result.getMethod().getMethodName();
		
		TakesScreenshot edriver = (TakesScreenshot)ProjectBaseClassTest.sdriver ;
		String filepath = edriver.getScreenshotAs(OutputType.BASE64);
		String time = new Date().toString().replace(" ","_").replace(":", "_");
		test.addScreenCaptureFromBase64String(filepath, testName+"_"+time);
		test.log(Status.FAIL,result.getMethod().getMethodName()+"===FAIL====" );
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		report.flush();
	}

	
}
