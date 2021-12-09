package com.amazon;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import com.setup.InitialSetup;
import com.utility.Utilities;

public class SignIn {


	WebDriver driver = InitialSetup.driver;
	AmazonPageRepo repo = new AmazonPageRepo();
	Utilities util;
	Actions action; 

	public SignIn() {

		PageFactory.initElements(driver, repo);	
		util = new Utilities();
		action= new Actions(driver);
	}
	
	public boolean signin() throws IOException, InterruptedException {

		boolean flag = false;
		driver.get(InitialSetup.readProp("amazonURL"));
		repo.accountList.click();
		//repo.siginHome.click();
		if(repo.signinPageLabel.isDisplayed())
		{
			repo.emailId.sendKeys("deepakradhakrishnan7@gmail.com");
			repo.continu.click();
			repo.password.sendKeys("Password@1");
			repo.signIn.click();
			Thread.sleep(5000);
			action.moveToElement(repo.accountList).perform();
			Thread.sleep(5000);
			if(repo.signOut.isDisplayed())
			{
				flag =true;
				System.out.println("Amazon Signin success");
			}
			
		}
		else if (repo.signOut.isDisplayed())
		{
			flag =true;
			System.out.println("Already signed in");
		}
		else {System.out.println("Failed to Signin");}
		return flag;
	}

	public void signOut() throws InterruptedException {
		try {
		action.moveToElement(repo.accountList).perform();
		Thread.sleep(1000);
		repo.signOut.click();
		Thread.sleep(5000);
		}
		catch(Exception e) {System.out.println(e.getMessage());}

	}
	
	

}
