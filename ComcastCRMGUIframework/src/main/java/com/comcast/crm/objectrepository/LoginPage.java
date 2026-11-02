package com.comcast.crm.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriver.utility.WebDriverUtility;

public class LoginPage extends WebDriverUtility { //---> rule:1 create java class for each web page
	
	
	// creation of constructor it simplifybthe intialization of oagefactory in each script
	WebDriver driver;
	public LoginPage(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements( driver, this); // this refers to current class object reference
	}
	
	//rule:2 Object Creation
	
	@FindBy(name="user_name")
    private  WebElement useredt ; //here by default "default" access specifier will be assigned and scope of the default is within the class 
	//so convert into public to use in script
	//and but it should be private inorder to stop other test engineers editing the locators
	
	@FindBy(name="user_password")
	private WebElement pwdedt ;
	
	@FindBy(id="submitButton")// here by default 
	private WebElement loginbtn ;

	//rule:3 Object Intialization:  which is done in test script
	//rule : 4 object encapsulation
	public WebElement getUseredt() {
		return useredt;
	}

	public WebElement getPwdedt() {
		return pwdedt;
	}

	public WebElement getLoginbtn() {
		return loginbtn;
	}
	
	
	
	public void loginToApp(String url,String username , String password){     //---> this is business library which is specific to business we can't use it for all business
		
		//rule:5 provide action
		//driver.manage().window().maximize(); like this i can use implicit explict wait everything
		waitForPageToLoad(driver);
		driver.get(url);
		useredt.sendKeys(username);
		pwdedt.sendKeys(password);
		loginbtn.click();
		
	}
	
	
	
	
}
