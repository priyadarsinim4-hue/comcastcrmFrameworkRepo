package com.crm.generic.baseutility;

import org.junit.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseClassTest {


	@BeforeSuite
	public void configBs() {
		
		System.out.println(" --- Connect to db and report the config ---- ");
	}
	
	@BeforeClass
	public void configBc() {
		
		System.out.println("execute before class -- here we're going to push luch the browser part ---  ");
	}
	
	@BeforeMethod
	public void configBm() {
		
		System.out.println("execute before method -- here we're going to login to appln part --- ");
		
	}
	
	
	@AfterMethod
	public void configAm() {
		
		System.out.println("execute after method   -- here we're going to logout from the appln part ---");
	}
	
	@AfterClass
	public void configAc() {
		
		System.out.println("execute after class --- here we're going to push the close the browser part ---- ");
	}
	
	
	@AfterSuite
	public void configAs() {
		
		System.out.println(" ---- close the db and report the backup ---- ");
	}
	
}
