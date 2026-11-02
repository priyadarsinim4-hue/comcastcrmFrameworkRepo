package com.crm.comcast.practiceconfigannotation.orgTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriver.utility.JavaUtility;
import com.comcast.crm.generic.webdriver.utility.UtilityClassObject;
import com.comcast.crm.objectrepository.CreateNewOrganizationPage;
import com.comcast.crm.objectrepository.HomePage;
import com.comcast.crm.objectrepository.OrgPage;

import com.comcast.crm.objectrepository.OrganizationNewInfoPage;
import com.crm.generic.baseutility.BaseClassTest;
import com.crm.generic.baseutility.ProjectBaseClassTest;

public class CreateOrgTest extends ProjectBaseClassTest {

	@Test
	public void createOrgTest() throws EncryptedDocumentException, IOException {
		// reading testscriptdata from excel
		UtilityClassObject.getTest().log(Status.INFO,"read the data from excel");
		String orgname = elib.getDataFromExcel("organization", 1, 2) + jlib.getRandomNumber();

		// st:1 login to app

		wlib.waitForPageToLoad(driver);

		// step:2 Navigate to organization maodule:
		UtilityClassObject.getTest().log(Status.INFO,"navigate to org module");
		HomePage hp = new HomePage(driver);
		hp.getOrglink().click();

		// step:3 click on "create organization" button
		OrgPage op = new OrgPage(driver);
		op.getNewOrgicon().click();

		// step:4 Enter all the details and create new organization
		UtilityClassObject.getTest().log(Status.INFO,"create a new org");
		CreateNewOrganizationPage cop = new CreateNewOrganizationPage(driver);
		cop.CreateNewOrganization(orgname);

		// verify the header message expected result

		OrganizationNewInfoPage cnip = new OrganizationNewInfoPage(driver);
		String headerInfo = cnip.getHeaderinfo().getText();
		org.testng.Assert.assertEquals(true,headerInfo.contains(orgname));
//		if (headerInfo.contains(orgname)) {
//			System.out.println(orgname + " is created=== PASS");
//		} else {
//			System.out.println(orgname + " is not  created=== FAIL");
//		}
	}

	@Test
	public void createOrgTestWithIndustry() throws EncryptedDocumentException, IOException {
		// create object

		// reading testscript data from excel file
		String orgname = elib.getDataFromExcel("contact", 4, 2) + jlib.getRandomNumber();
		String industry = elib.getDataFromExcel("organization", 4, 3);
		String type = elib.getDataFromExcel("organization", 4, 4);

		HomePage hp = new HomePage(driver);
		hp.getOrglink().click();

		// step:3 click on "create organization" button
		OrgPage op = new OrgPage(driver);
		op.getNewOrgicon().click();

		// step:4 Enter all the details and create new organization
		CreateNewOrganizationPage cop = new CreateNewOrganizationPage(driver);
		// cop.CreateNewOrganization(orgname, industry);
		cop.CreateNewOrganization(orgname, industry, type);

		// verify the industry and type dropdown
		OrganizationNewInfoPage cnip = new OrganizationNewInfoPage(driver);
		String actindustry = cnip.getActindustry().getText();

		if (actindustry.equals(industry)) {
			System.out.println(industry + "is created=== PASS");
		} else {
			System.out.println(industry + "is not  created=== FAIL");
		}

		String acttype = cnip.getActtype().getText();

		if (acttype.equals(type)) {
			System.out.println(type + " is created=== PASS");
		} else {
			System.out.println(type + " is not  created=== FAIL");
		}

	}

	@Test
	public void createOrgWithPhone() throws EncryptedDocumentException, IOException {

		// getting testscriptdata from excel

		String orgname = elib.getDataFromExcel("organization", 4, 2) + jlib.getRandomNumber();
		String ph_no = elib.getDataFromExcel("organization", 7, 3);

		// navigate to organization module

		HomePage hp = new HomePage(driver);
		hp.getOrglink().click();

		// step:3 click on "create organization" button
		OrgPage op = new OrgPage(driver);
		op.getNewOrgicon().click();

		// step:4 Enter all the details and create new organization
		CreateNewOrganizationPage cop = new CreateNewOrganizationPage(driver);
		cop.CreateNewOrgWithPhone(orgname, ph_no);
		// cop.CreateNewOrganization(orgname);

		// verifying phone number is created or not
		OrganizationNewInfoPage cnip = new OrganizationNewInfoPage(driver);
		String actph_no = cnip.getActph_no().getText();

		if (actph_no.equals(ph_no)) {
			System.out.println(ph_no + " is created=== PASS");
		} else {
			System.out.println(ph_no + " is not created=== FAIL");
		}

	}

}
