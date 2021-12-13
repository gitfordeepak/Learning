package smudTestCases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.setup.InitialSetup;
import com.smud.SmudPayment;
import com.utility.Utilities;



public class Smud {

	WebDriver driver;
	InitialSetup setup;
	SmudPayment smudpay;
	Utilities utils;

	@BeforeClass public void startDriver() throws IOException { 
		setup = new InitialSetup();
		driver =InitialSetup.intiateDriver(); 
		smudpay = new SmudPayment();


	}

	@Test(priority = 1)
	public void CheckBill() throws IOException, InterruptedException {

		smudpay.signIn();
		smudpay.checkBill();

	}
	@Test(priority = 2)
	public void Payment() throws IOException, InterruptedException {
		try {
			smudpay.payBill();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	//	@Test(priority = 0)
	//	public void ExceldataUpdate() throws IOException {
	//
	//		utils.addStringinputSheet("Header", utils.getTotalRows()+1, 0);
	//		System.out.println("Ran first");
	//
	//	}

	@AfterClass
	public void closeDriver() {

		driver.close();
		driver.quit();
	}

}
