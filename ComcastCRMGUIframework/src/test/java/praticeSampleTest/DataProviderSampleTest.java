package praticeSampleTest;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderSampleTest {
@Test(dataProvider="getData")
public void CreateContactTest(String fn , String ln) {
	
	System.out.println("first name : "+fn +", lastname : "+ln);
}

@DataProvider
public Object[][] getData(){
	
	Object [][] objarr = new Object[3][2];
	objarr[0][0]= "priya";
	objarr[0][1]= "mani" ;
	
	objarr[1][0]= "ramya";
	objarr[1][1]= "sree" ;
	
	objarr[2][0]= "reddy";
	objarr[2][1]= "sekhar" ;
	
	return objarr;
}
}
