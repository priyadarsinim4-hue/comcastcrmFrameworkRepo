package com.comcast.crm.orgtest;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriver.utility.JavaUtility;
import com.comcast.crm.generic.webdriver.utility.WebDriverUtility;
import com.comcast.crm.objectrepository.CreateNewOrganizationPage;
import com.comcast.crm.objectrepository.HomePage;
import com.comcast.crm.objectrepository.LoginPage;
import com.comcast.crm.objectrepository.OrgPage;
import com.comcast.crm.objectrepository.OrganizationNewInfoPage;
import com.crm.generic.baseutility.ProjectBaseClassTest;

public class CreateorgTestWithConfigAnottationImplementation extends ProjectBaseClassTest {

	@Test
	public void createOrgTest() throws EncryptedDocumentException, IOException {
		// reading testscriptdata from excel
		String orgname = elib.getDataFromExcel("organization", 1, 2) + jlib.getRandomNumber();

		// st:1 login to app

		wlib.waitForPageToLoad(driver);

		// step:2 Navigate to organization maodule:
		HomePage hp = new HomePage(driver);
		hp.getOrglink().click();

		// step:3 click on "create organization" button
		OrgPage op = new OrgPage(driver);
		op.getNewOrgicon().click();

		// step:4 Enter all the details and create new organization
		CreateNewOrganizationPage cop = new CreateNewOrganizationPage(driver);
		cop.CreateNewOrganization(orgname);

		// verify the header message expected result

		OrganizationNewInfoPage cnip = new OrganizationNewInfoPage(driver);
		String headerInfo = cnip.getHeaderinfo().getText();
		if (headerInfo.contains(orgname)) {
			System.out.println(orgname + " is created=== PASS");
		} else {
			System.out.println(orgname + " is not  created=== FAIL");
		}
	}
	
	

}
