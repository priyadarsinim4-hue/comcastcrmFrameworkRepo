package praticeSampleTest;


import org.junit.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class TestngConfigAnnotationPracticeTest {

	
	@BeforeMethod
	public void configBm() {
		
		System.out.println("execute before method 1");
		
	}
	
	@BeforeClass
	public void configBc1() {
		
		System.out.println("execute before class 1");
	}
	
	@BeforeClass
	public void configBc2() {
		
		System.out.println("execute before class 2");
	}
	
	@BeforeMethod
	public void configBm2() {
		
		System.out.println("execute before method 2");
		
	}
	
	@Test
	public void createContactTest() {
		
		System.out.println("executing create contqct test");
	}
	
	@Test
	public void createContactWithDate() {
		
		System.out.println("executing create contqct test with date ");
	}
	
	@BeforeSuite
	public void configBs() {
		
		System.out.println("execute before suite");
	}
	 
	@AfterSuite
	public void configAs() {
		
		System.out.println("execute after suite");
	}
	
	@AfterClass
	public void configAc1() {
		
		System.out.println("execute after class 1");
	}
	
	@AfterClass
	public void configAc2() {
		
		System.out.println("execute after class 2");
	}
	
	@AfterMethod
	public void configAm() {
		
		System.out.println("execute after method");
	}
	
	@AfterMethod
	public void configAm2() {
		
		System.out.println("execute after method 2");
	}
	
	
	
	
}

