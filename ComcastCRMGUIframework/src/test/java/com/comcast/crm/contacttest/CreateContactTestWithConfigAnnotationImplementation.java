package com.comcast.crm.contacttest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.webdriver.utility.JavaUtility;
import com.comcast.crm.objectrepository.ContactInfoPage;
import com.comcast.crm.objectrepository.ContactPage;
import com.comcast.crm.objectrepository.HomePage;
import com.crm.generic.baseutility.ProjectBaseClassTest;

public class CreateContactTestWithConfigAnnotationImplementation extends ProjectBaseClassTest{

	@Test
	public void createContactTest() throws EncryptedDocumentException, IOException {
		
	   
	
		String LastName = elib.getDataFromExcel("contact",1,2)+jlib.getRandomNumber() ;
		
		//click on contact 
		HomePage hp =new HomePage(driver);
		hp.getContactlink().click();
		
		wlib.waitForPageToLoad(driver);
		
		//click on create new contact icon 
		ContactPage cp = new ContactPage(driver);
		cp.getNewcontacticon().click();
				
		//filling last name mandatory text field
		cp.getLastname().sendKeys(LastName);
				
		//click on save button 
		cp.getSavebtn().click();
				
		//verify the header message expected result;
		ContactInfoPage cinfo = new ContactInfoPage(driver);
    	String headerInfo = cinfo.getHeaderinfo().getText();
    	if (headerInfo.contains(LastName)) {
			System.out.println(LastName + " is created=== PASS");
		}else {
			System.out.println(LastName + " is not  created=== FAIL");
			}
    	
    	
	}
}
