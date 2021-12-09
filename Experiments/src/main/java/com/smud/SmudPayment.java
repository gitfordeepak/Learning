package com.smud;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import com.setup.InitialSetup;
import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import com.utility.Utilities;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;

@SuppressWarnings("deprecation")
public class SmudPayment {

	WebDriver driver = InitialSetup.driver;
	Utilities util = new Utilities();
	Actions action;
	Screenshot screenshot;
	static float smudBill,amountPaying;
	SmudPageFactory page = new SmudPageFactory();


	public SmudPayment() {//Constructor
		PageFactory.initElements(driver, page);
		action = new Actions(driver);
		screenshot = new AShot().takeScreenshot(driver);

	}


	public void signIn() {
		try {

			driver.get(InitialSetup.readProp("smudURL"));
			page.userID.sendKeys(decodeString(InitialSetup.readProp("Smuduserid")));
			page.password.sendKeys(decodeString(InitialSetup.readProp("Smudpassword")));
			page.signIn.click();
			if(page.accName.isDisplayed()) {
				System.out.println("Login success");
			}

		} catch (IOException | Base64DecodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String checkBill() {

		String payornot =null;
		String DueAmnt = page.dueAmount.getText().replace("$", "");
		smudBill = Float.parseFloat(DueAmnt);
		while(smudBill==0.0) {
			System.out.println("The bill has not generarated yet");
			payornot = "no";
			break;
		}

		while(smudBill>100) {
			System.out.println("The bill generarated and amout is more than $100:  $"+ smudBill+"\r\n"+page.dueDate.getText());
			payornot = "yes";
			break;
		}
		while(smudBill>150) {
			System.out.println("The bill generarated and amout is more than $150:  $"+ smudBill+"\r\n"+page.dueDate.getText());
			payornot = "yes";
			break;
		}
		return payornot;

	}

	public void payBill() throws InterruptedException, IOException  {
		SmudPayment payment = new SmudPayment();
		if (payment.checkBill()=="yes") {
			page.payButton.click();
			Thread.sleep(5000);
			//driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			page.amountDue.click();
			page.payNow.click();
			page.payNext.click();
			Thread.sleep(5000);
			amountPaying = Float.parseFloat(page.finalAmount.getText().replace("$", ""));
			//System.out.println(amountPaying);
			Utilities.screenshot(InitialSetup.readProp("screenShots"+"Invoice.jpg"), driver);
			page.Submit.click();

			//		if(amountPaying==smudBill) {	
			//			//buttonclick(Submit);
			//			try{Submit.click();}
			//			catch (Exception e) {
			//				// TODO Auto-generated catch block
			//				e.printStackTrace();
			//			}
			//			ImageIO.write(screenshot.getImage(), "jpg", new File(prop.readProp("screenShots"+"fullimage.jpg")));
			//			System.out.println("Payment of: "+smudBill+" completed");
			//		}
			//		else {
			//			buttonclick(Cancel);
			//		}

			Thread.sleep(5000);
		}
		else {
			System.out.println("The bill has not generarated yet");
		}

	}

	public void EndTest() {

		driver.close();
	}


	private String decodeString(String str) throws Base64DecodingException {
		byte[] decoded = Base64.decode(str.getBytes()); String decodedString = new
				String(decoded); return decodedString; }

	private void buttonclick(WebElement element) {//private function to button click
		action.moveToElement(element); action.click(); }


}
