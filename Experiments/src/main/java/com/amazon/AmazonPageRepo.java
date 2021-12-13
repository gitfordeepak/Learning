package com.amazon;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AmazonPageRepo {

	//*****************Login Page*********************//
	@FindBy(xpath=".//span[contains(text(),'Account & Lists')]") WebElement accountList;
	@FindBy(xpath=".//div[@id='nav-al-signin']//span[text()='Sign in']") WebElement siginHome;
	@FindBy(xpath=".//label[contains(text(),'Email or mobile phone number')]") WebElement signinPageLabel;
	@FindBy(name="email") WebElement emailId;
	@FindBy(name="password") WebElement password;
	@FindBy(id="continue") WebElement continu;
	@FindBy(id="signInSubmit") WebElement signIn;
	@FindBy(linkText="Sign Out") WebElement signOut;
	
	//************Search Page***************************//
	@FindBy(id="nav-search-submit-button") WebElement searchBtn;
	@FindBy(id="twotabsearchtextbox") WebElement searchBar;
	
	
	
}
