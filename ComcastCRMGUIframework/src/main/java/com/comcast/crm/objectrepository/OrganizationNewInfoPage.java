package com.comcast.crm.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationNewInfoPage {

	@FindBy(xpath= "//span[@class='dvHeaderText']")
	 private WebElement headerinfo ;
	
	@FindBy(id="dtlview_Industry")
	private WebElement actindustry;
	
	@FindBy(id="dtlview_Phone")
	private WebElement actph_no;
	
	public WebElement getActph_no() {
		return actph_no;
	}

	public WebElement getActindustry() {
		return actindustry;
	}

	public WebElement getActtype() {
		return acttype;
	}

	@FindBy(id="dtlview_Type")
	private WebElement acttype ;
	
	//object intialization
	
			WebDriver driver;
			public OrganizationNewInfoPage(WebDriver driver) {
				this.driver = driver;
				PageFactory.initElements(driver,this);
			}
			
			public WebElement getHeaderinfo() {
				return headerinfo;
			}
			
			
}
