package com.comcast.crm.objectrepository;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

	//onject identification
	@FindBy(linkText = "Organizations")
	private WebElement orglink; //--> storing elements
	
	@FindBy(linkText = "Contacts")
	private WebElement contactlink;
	
	public void clickOnContactLink() {

	    WebDriverWait wait =
	            new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(contactlink)).click();

//	    wait.until(ExpectedConditions.elementToBeClickable(
//	            By.linkText("Contacts"))).click();
	}
	
	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement adminImg;

	@FindBy(linkText ="Sign Out" )
	private WebElement signoutLink;
	
	//object intialization
	
	WebDriver driver;
	

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	//object encapsulation
	public WebElement getOrglink() {
		return orglink;
	}
	
	public WebElement getAdminImg() {
		return adminImg;
	}

	public WebElement getSignoutLink() {
		return signoutLink;
	}
	
	public WebElement getContactlink() {
		return contactlink;
	}
	
	
	// this is business library no need bcz of only one action whenever we have multiple actions
     public void clickOnOrglink(){     //---> this is business library which is specific to business we can't use it for all business
		orglink.click();
}
     public void logOut(){     //---> this is business library which is specific to business we can't use it for all business
 		Actions action = new Actions(driver);
 		action.moveToElement(adminImg).perform();
 		signoutLink.click();
 }
	
	
}
