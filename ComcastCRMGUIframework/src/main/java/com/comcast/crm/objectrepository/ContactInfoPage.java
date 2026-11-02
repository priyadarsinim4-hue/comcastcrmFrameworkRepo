package com.comcast.crm.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInfoPage {

	
	@FindBy(xpath="//span[@class='dvHeaderText']")
	 private WebElement headerinfo;
	
	@FindBy(id="dtlview_Support Start Date")
	private WebElement actsupportstartdate;	

	@FindBy(id="dtlview_Support End Date")
	private WebElement actsupportenddate ;
	
	@FindBy(id="mouseArea_Organization Name")
	private WebElement actorgname ;
	
	
	
	public WebElement getActorgname() {
		return actorgname;
	}

	WebDriver driver;
	
	public ContactInfoPage(WebDriver driver) {
		
		this.driver=  driver;
		PageFactory.initElements( driver,this);
	}

	public WebElement getHeaderinfo() {
		return headerinfo;
	}
	
	public WebElement getActsupportstartdate() {
		return actsupportstartdate;
	}

	public WebElement getActsupportenddate() {
		return actsupportenddate;
	}
	
	
}
