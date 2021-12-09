package com.amazon;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.setup.InitialSetup;
import com.utility.Utilities;

public class Home {
	
	WebDriver driver = InitialSetup.driver;
	AmazonPageRepo repo;
	Utilities util;
	
	public Home() {
		
		repo = new AmazonPageRepo();
		util = new Utilities();
		PageFactory.initElements(driver, repo);		
	}
	
	
	
	
	

}
