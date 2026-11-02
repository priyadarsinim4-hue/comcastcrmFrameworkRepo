package com.comcast.crm.orgtest;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriver.utility.JavaUtility;
import com.comcast.crm.generic.webdriver.utility.WebDriverUtility;
import com.comcast.crm.objectrepository.CreateNewOrganizationPage;
import com.comcast.crm.objectrepository.HomePage;
import com.comcast.crm.objectrepository.LoginPage;
import com.comcast.crm.objectrepository.OrgPage;
import com.comcast.crm.objectrepository.OrganizationNewInfoPage;
import com.comcast.crm.practicepomclass.SampleTestWithPom;

public class CreateOrgTestWithPom {

	public static void main(String[] args) throws IOException, InterruptedException {
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
			
			//reading testscriptdata from excel
			String orgname  = elib.getDataFromExcel("organization",1,2)+jlib.getRandomNumber() ;
			
						WebDriver driver =null ;
						
						if(BROWSER.equals("chrome")) {
							driver= new ChromeDriver();
						}else if (BROWSER.equals("edge")) {
							driver= new EdgeDriver();
						}else if (BROWSER.equals("firefox")) {
							driver= new FirefoxDriver();
						}
						
						// st:1 login to app 
						
						wlib.waitForPageToLoad(driver);
						//driver.get(URL);
						driver.manage().window().maximize();
						
						// now instead of intializing pagefactory again and again i can call the pagefactory constructor from pom class
						// way-2:
						
						LoginPage lp = new LoginPage(driver);
						
						
						// way-1 :LoginPage lp = PageFactory.initElements( driver, LoginPage.class); // OBJECT INTIALIZATION ---> POM CLASS 
					    
//						lp.getUseredt().sendKeys(UN); ---> SINGLE ACCESS ELEMENT USING GETTERS --> ENCAPSULATION
//						lp.getPwdedt().sendKeys(PWD);
//						lp.getLoginbtn().click();
						
						// fetching the data using actions or business library
						lp.loginToApp(URL,UN, PWD); //---> multi-acess element
						
						// step:2 Navigate to organization maodule:
						HomePage hp =new HomePage(driver);
						hp.getOrglink().click(); // --> by using getter method performing action
						//hp.clickOnOrglink(); // --> usage for business library
						
						//step:3 click on "create organization" button
						OrgPage op = new OrgPage(driver);
						op.getNewOrgicon().click();
						
						//step:4 Enter all the details and create new organization 
						CreateNewOrganizationPage cop =new CreateNewOrganizationPage(driver);
						cop.CreateNewOrganization(orgname);
						
						//verify the header message expected result
						
						OrganizationNewInfoPage cnip = new OrganizationNewInfoPage(driver);
						String headerInfo =cnip.getHeaderinfo().getText();
				    	if (headerInfo.contains(orgname)) {
							System.out.println(orgname + " is created=== PASS");
						}else {
							System.out.println(orgname + " is not  created=== FAIL");
							}
				    	
				    	//step:6 logout
				    	hp.logOut();
						Thread.sleep(2000);
						
						driver.quit();
				
	}

}
