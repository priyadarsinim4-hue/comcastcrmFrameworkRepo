package praticeSampleTest;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;

public class SampleTestWithAmazon {

	@Test(dataProvider = "getData")
	public void getProductInfoTest(String brandname,String productName) {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.amazon.in");
		
		// search product 
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(brandname,Keys.ENTER);
		
		// get the product info
		// below one hardcoding of data
		
	//String x= driver.findElement(By.xpath("//span[text()='iPhone 18 Pro (256 GB) - Glacier']/../../../../div[3]/div[1]/div[1]/div[1]/div/div/a/span/span[2]/span[2]")).getText();
		
		String x= driver.findElement(By.xpath("//span[text()='"+productName+"']/../../../../div[3]/div[1]/div[1]/div[1]/div/div/a/span/span[2]/span[2]")).getText();	
		
		System.out.println(x);
		//driver.close();
		
		
	}
	
	
	//getting data -- excel
	@DataProvider
	public Object[][] getData() throws EncryptedDocumentException, IOException{
		
		ExcelUtility elib = new ExcelUtility();
		int rowcount = elib.getRowcount("products");
		
		
		Object [][] objarr = new Object[rowcount][2];
		
		for(int i =0 ; i<rowcount;i++) {
			objarr[i][0]= elib.getDataFromExcel("products", i+1, 0);
			objarr[i][1]= elib.getDataFromExcel("products", i+1, 1);
			
		}
		
		return objarr;
	
	// getfing data -- hardcoding
//	@DataProvider
//	public Object[][] getData(){
//		
//		Object [][] objarr = new Object[2][2];
//		objarr[0][0]= "iphone";
//		objarr[0][1]= "iPhone 18 Pro (256 GB) - Glacier" ;
//		
//		objarr[1][0]= "iphone";
//		objarr[1][1]= "iPhone 18 Pro (256 GB) - Burgundy" ;
//		
//		return objarr;
	}
}
