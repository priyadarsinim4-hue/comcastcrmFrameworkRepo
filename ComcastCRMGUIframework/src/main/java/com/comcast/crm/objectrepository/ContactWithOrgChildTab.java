package com.comcast.crm.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactWithOrgChildTab {

	@FindBy(id = "search_txt")
	private WebElement searchtxtbox;
	
	@FindBy(name ="search")
	private WebElement serchbtn;
	
	

	public WebElement getSerchbtn() {
		return serchbtn;
	}

	WebDriver driver;
	
	public ContactWithOrgChildTab (WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
		
		
	}

	public WebElement getSearchtxtbox() {
		return searchtxtbox;
	}
}
