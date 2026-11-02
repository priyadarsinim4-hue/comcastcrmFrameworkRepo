package com.crm.comcast.practiceconfigannotation.Contacttest;



import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.IAssert;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.util.Assert;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriver.utility.JavaUtility;
import com.comcast.crm.generic.webdriver.utility.UtilityClassObject;
import com.comcast.crm.generic.webdriver.utility.WebDriverUtility;
import com.comcast.crm.objectrepository.ContactInfoPage;
import com.comcast.crm.objectrepository.ContactPage;
import com.comcast.crm.objectrepository.ContactWithOrgChildTab;
import com.comcast.crm.objectrepository.ContactWithOrgPage;
import com.comcast.crm.objectrepository.CreateNewOrganizationPage;
import com.comcast.crm.objectrepository.HomePage;
import com.comcast.crm.objectrepository.OrgPage;
import com.crm.generic.baseutility.BaseClassTest;
import com.crm.generic.baseutility.ProjectBaseClassTest;

public class CreateContactTest extends ProjectBaseClassTest {

	@Test(groups="rt")
	public void createContactTest() throws EncryptedDocumentException, IOException {

		UtilityClassObject.getTest().log(Status.INFO,"read the data from excel");
		String LastName = elib.getDataFromExcel("contact", 1, 2) + jlib.getRandomNumber();

		// click on contact
		UtilityClassObject.getTest().log(Status.INFO,"navigate to contact module");
		HomePage hp = new HomePage(driver);
		hp.getContactlink().click();

		wlib.waitForPageToLoad(driver);

		// click on create new contact icon
		UtilityClassObject.getTest().log(Status.INFO,"create a new contact");
		ContactPage cp = new ContactPage(driver);
		cp.getNewcontacticon().click();

		// filling last name mandatory text field
		cp.getLastname().sendKeys(LastName);

		// click on save button
		cp.getSavebtn().click();

		// verify the header message expected result;
		ContactInfoPage cinfo = new ContactInfoPage(driver);
		String headerInfo = cinfo.getHeaderinfo().getText();
		//org.testng.Assert.assertEquals(headerInfo, contains(LastName));
		org.testng.Assert.assertEquals(true,headerInfo.contains(LastName));
//		if (headerInfo.contains(LastName)) {
//			System.out.println(LastName + " is created=== PASS");
//		} else {
//			
//			System.out.println(LastName + " is not  created=== FAIL");
//		}
//
	}

	private Object contains(String lastName) {
		// TODO Auto-generated method stub
		return null;
	}

	// ------------------------------------------------------------------------------

	@Test(groups="rt")
	public void createContctWithSupportdatedate() throws EncryptedDocumentException, IOException, InterruptedException {

		String LastName = elib.getDataFromExcel("contact", 4, 2) + jlib.getRandomNumber();

		// click on contact
		HomePage hp = new HomePage(driver);
		hp.getContactlink().click();

		// click on create new contact icon
		ContactPage cp = new ContactPage(driver);
		cp.getNewcontacticon().click();

		// filling last name mandatory text field
		cp.getLastname().sendKeys(LastName);

		// support start date

		String startdate = jlib.getSystemDateYYYYDDMM();
		String enddate = jlib.getRequiredDateYYYYDDMM(30);

		cp.getSupportstartdatedate().clear();
		cp.getSupportstartdatedate().sendKeys(startdate);

		cp.getSupportenddate().clear();
		cp.getSupportenddate().sendKeys(enddate);
		System.out.println(enddate);

		// click on save button
		cp.getSavebtn().click();

		// verify the header message expected result

		ContactInfoPage cinfo = new ContactInfoPage(driver);
		String headerInfo = cinfo.getHeaderinfo().getText();
		if (headerInfo.contains(LastName)) {
			System.out.println(LastName + "  header is created=== PASS");
		} else {
			System.out.println(LastName + " header is not  created=== FAIL");
		}

//				//verifying start date and enddate  has been created or not 
//		    	
		String actstartdate = cinfo.getActsupportstartdate().getText();
		if (actstartdate.equals(startdate)) {
			System.out.println(startdate + " has been created sucessfully === PASS ");
		} else {
			System.out.println(startdate + "hasn't created === FAIL");
		}

		String actenddate = cinfo.getActsupportenddate().getText();
		if (actenddate.equals(enddate)) {
			System.out.println(enddate + " has been created sucessfully === PASS ");
		} else {
			System.out.println(enddate + "hasn't created === FAIL");
		}

	}

	// -----------------------------------------------------------------------------
	@Test(groups="st")
	public void contactWithOrgTest() throws EncryptedDocumentException, IOException, InterruptedException {

		// reading testscript data from excel file
		String orgname = elib.getDataFromExcel("contact", 7, 2) + jlib.getRandomNumber();
		String ContactLastName = elib.getDataFromExcel("contact", 7, 3) + jlib.getRandomNumber();
		wlib.waitForPageToLoad(driver);

		// navigate to organization module
		HomePage hp = new HomePage(driver);
		hp.getOrglink().click();

		// st:3 click on create organization button
		OrgPage op = new OrgPage(driver);
		op.getNewOrgicon().click();

		// st:4 enter all the details and create new organization

		CreateNewOrganizationPage cop = new CreateNewOrganizationPage(driver);
		cop.CreateNewOrganization(orgname);

		Thread.sleep(2000);
		//hp.clickOnContactLink();--- used explicit wait
		
		// click on contact
        hp.getContactlink().click();

		// click on create new contact icon
		ContactPage cp = new ContactPage(driver);
		cp.getNewcontacticon().click();

		// filling last name mandatory text field
		cp.getLastname().sendKeys(ContactLastName);

		// click on created organization icon
		
		ContactWithOrgPage co = new ContactWithOrgPage(driver);
		co.getNeworgicon().click();
		

		// switch to child window

		wlib.switchToTabOnURL(driver, "module=Accounts");

		// searching the latest created orgname for that we have to use dynamic xpath
		
		ContactWithOrgChildTab coc = new ContactWithOrgChildTab(driver);
		coc.getSearchtxtbox().sendKeys(orgname);
		coc.getSerchbtn().click();
		
		driver.findElement(By.xpath("//a[text()='" + orgname + "']")).click();

		// switch to parent window

		wlib.switchToTabOnTitle(driver, "module=Contacts");

		// click on save button
		cp.getSavebtn().click();

		// verify the contact header message expected result
		
		ContactInfoPage cinfo = new ContactInfoPage(driver);
		String headerInfo = cinfo.getHeaderinfo().getText();
		//String headerInfo1 = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (headerInfo.contains(ContactLastName)) {
			System.out.println(ContactLastName + " is created=== PASS");
		} else {
			System.out.println(ContactLastName + " is not  created=== FAIL");
		}

		// verify header orgname info expected result
		String actorgname = cinfo.getActorgname().getText();
		//String actorgname = driver.findElement(By.id("mouseArea_Organization Name")).getText();
		System.out.println(actorgname);//
		if (actorgname.trim().equals(orgname)) {
			System.out.println(orgname + " is created=== PASS integration flow happend");
		} else {
			System.out.println(orgname + " is not created=== FAIL");
		}

	}

}
