package com.comcast.crm.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactPage {

	
	@FindBy(xpath= "//img[@alt='Create Contact...']")
	 private WebElement newcontacticon ;
	WebDriver driver ;
	
	@FindBy(name="lastname")
	private WebElement lastname ;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement savebtn ;

	@FindBy(name = "support_start_date")
	private WebElement supportstartdatedate;
	
	@FindBy(name= "support_end_date")
	private WebElement supportenddate ;

	public ContactPage(WebDriver driver ) {
		this.driver = driver;
		PageFactory.initElements(driver,this);	
	}
	
	public WebElement getNewcontacticon() {
		return newcontacticon;
	}

	public WebElement getLastname() {
		return lastname;
	}
	
	public WebElement getSavebtn() {
		return savebtn;
	}

	public WebElement getSupportstartdatedate() {
		return supportstartdatedate;
	}

	public WebElement getSupportenddate() {
		return supportenddate;
	}
	
	
}
