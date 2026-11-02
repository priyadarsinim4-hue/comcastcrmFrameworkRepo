package com.comcast.crm.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrgPage {

	@FindBy(xpath = "//img[@alt='Create Organization...']")
	 private WebElement newOrgicon ;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement savebtn;
	
	//object intialization
	
		public WebElement getSavebtn() {
		return savebtn;
	}

		WebDriver driver;
		public OrgPage(WebDriver driver) {
			this.driver = driver;
			PageFactory.initElements(driver,this);
		}
		
		public WebElement getNewOrgicon() {
			return newOrgicon;
		}
}
