package praticeSampleTest;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.crm.generic.baseutility.ProjectBaseClassTest;

public class InvoiceTest extends ProjectBaseClassTest {
	
	@Test
	public void createInvoicetest() {
	
		System.out.println("execute createInvoicetest "); 
		String actTitle = driver.getTitle();
		Assert.assertEquals(actTitle,"Login");
		System.out.println("step-1");
		System.out.println("step-2");
		System.out.println("step-3");
		System.out.println("step-4");
	}
	
	@Test
	public void createinvoiceWithContacttest() {
		
		System.out.println("execute createinvoiceWithContacttest ");
		System.out.println("step-1");
		System.out.println("step-2");
		System.out.println("step-3");
		System.out.println("step-4");
	}
	

}
