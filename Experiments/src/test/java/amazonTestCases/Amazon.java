package amazonTestCases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.amazon.Search;
import com.amazon.SignIn;
import com.setup.AppDetails;
import com.setup.InitialSetup;
import com.utility.Utilities;

public class Amazon extends InitialSetup {// Here i used extends to read details from by base class InitialSetup

	WebDriver driver;
	AppDetails details = new AppDetails("amazon");
	Utilities util;
	SignIn amznSignin;
	Search search;

	@BeforeClass
	public void beforeClass() throws IOException {

		driver =InitialSetup.intiateDriver(); 
		details.initiateValues();
		util = new Utilities();
		search = new Search();
		amznSignin = new SignIn();
	}
	@Test(priority=1)
	public void signIn() throws InterruptedException, IOException {
		try {
			boolean flag= amznSignin.signin();
			Assert.assertTrue(flag,"Signin not happened");
		}
		catch(Exception e) {System.out.println(e.getMessage());}
	}
	@DataProvider 
	public Object[][] dataFromSheet() throws IOException{
		
		Object [][] data = Utilities.getTestData("amazonsearch");
		return data;
	}

	@Test(priority=2,dataProvider ="dataFromSheet")
	public void searchProduct(String SearchWords, String Suggestion) throws InterruptedException, IOException {
		//amznSignin.signin();
		search.searchWithString(SearchWords);

	}
	@Test(priority=2,dataProvider ="dataFromSheet")
	public void searchProductwithSuggestion(String SearchWords, String Suggestion) throws InterruptedException, IOException {

		//amznSignin.signin();
		search.searchWithString(SearchWords, Suggestion);

	}


//	@AfterTest
//	public void afterTets() throws InterruptedException {
//		Thread.sleep(5000);
//		amznSignin.signOut();
//
//	}

	@AfterClass
	public void afterClass() throws InterruptedException {
		Thread.sleep(5000);
		amznSignin.signOut();
		driver.close();

	}

}
