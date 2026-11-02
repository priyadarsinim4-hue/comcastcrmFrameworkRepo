package com.comcast.crm.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactWithOrgPage {
	
	WebDriver driver;
	
	public ContactWithOrgPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath ="//input[@name='account_name']/following-sibling::img")
	private WebElement neworgicon ;

	public WebElement getNeworgicon() {
		return neworgicon;
	}

}
