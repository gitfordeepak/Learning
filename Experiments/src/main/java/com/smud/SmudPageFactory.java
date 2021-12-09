package com.smud;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SmudPageFactory {
	
		
	@FindBy(id="UserId") WebElement userID;
	@FindBy(id="Password") WebElement password;
	@FindBy(xpath=".//button[contains(text(),'Sign in')]") WebElement signIn;
	@FindBy(linkText="Sign Out") WebElement signOut;
	@FindBy(linkText="Profile") WebElement profile;
	@FindBy(id="account-name") WebElement accName;
	@FindBy(id="account-identifier") WebElement accNumber;
	@FindBy(xpath=".//div/h2[contains(text() ,'Billing')]//parent::div//span[@class='cost-item']") WebElement dueAmount;
	@FindBy(xpath=".//div/h2[contains(text() ,'Billing')]//parent::div//span[@class='cost-item']") WebElement currentCharges;
	@FindBy(xpath=".//div[contains(text(),'Current charges')]/div") WebElement dueDate;
	@FindBy(linkText="Pay now") WebElement payButton;
	@FindBy(linkText="Next") WebElement payNext;
	@FindBy(xpath=".//div[@id='divPaymentAmount']//span[text()='Total amount due: ']") WebElement amountDue;
	@FindBy(xpath=".//div[@id='divPaymentDate']//span[text()='Payment Date']") WebElement payNow;
	@FindBy(xpath=".//div[@class='padded']/span[3]") WebElement finalAmount;
	@FindBy(xpath=".//div[@class='visible-lg visible-md visible-sm']//a[text()='Submit Payment']") WebElement Submit;
	@FindBy(linkText="Cancel") WebElement Cancel;

}
