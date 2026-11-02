 package com.comcast.crm.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreateNewOrganizationPage {

	@FindBy(name = "accountname")
	 private WebElement orgnameedt ;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	 private WebElement savebtn;
	
	@FindBy(name = "industry")
	 private WebElement industrydb ;
	
	@FindBy(name="accounttype")
	private WebElement typedd;
	
	@FindBy(id="phone")
    private WebElement ph_no;
	

	WebDriver driver;
	public CreateNewOrganizationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	public WebElement getOrgname() {
		return orgnameedt;
	}
	
	public WebElement getSavebtn() {
		return savebtn;
	}
	
	public WebElement getPh_no() {
		return ph_no;
	}
	
	public WebElement getTypedd() {
		return typedd;
	}
	
	
	public void CreateNewOrganization(String neworgname) {	
		orgnameedt.sendKeys(neworgname);
		savebtn.click();	
	}
	
	
	
	//create organization with industry and type ---> method same but arguments are different this is nothing but method overloaded
	public void CreateNewOrganization(String neworgname , String industry) {	
		orgnameedt.sendKeys(neworgname);
		Select sel = new Select(industrydb);
		sel.selectByVisibleText(industry);//---> industry should come from excel
		savebtn.click();
		
	}
	
	public void CreateNewOrganization(String neworgname , String industry ,String type) {	
		orgnameedt.sendKeys(neworgname);
		Select sel = new Select(industrydb);
		sel.selectByVisibleText(industry);//---> industry should come from excel
		
		Select sel1 = new Select(typedd);
		sel1.selectByVisibleText(type);
		savebtn.click();
		
	}
	
	public void CreateNewOrgWithPhone(String neworgname , String phonenum) {
		orgnameedt.sendKeys(neworgname);
		ph_no.sendKeys(phonenum);
		savebtn.click();
	}
	
	
	
}
