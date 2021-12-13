package com.amazon;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.setup.InitialSetup;
import com.utility.Utilities;


public class Search {

	WebDriver driver = InitialSetup.driver;
	AmazonPageRepo repo;
	Utilities util;

	public Search() {
		repo = new AmazonPageRepo();
		PageFactory.initElements(driver,repo);
		util = new Utilities();
	}

	public void searchWithString(String searchString, String suggestion) throws InterruptedException, IOException {

		if(!(searchString=="")) {
			Thread.sleep(2000);
			repo.searchBar.clear();
			repo.searchBar.sendKeys(searchString);
			
			Thread.sleep(2000);
			if(!(suggestion=="")) {
				List<WebElement> list = driver.findElements(By.xpath(".//div[@id='nav-flyout-searchAjax']//div/descendant::div[@class='s-suggestion']"));
				for(WebElement w : list) {
					//System.out.println(w.getText());
					if(w.getText().toLowerCase().contains(suggestion))
					{
						w.click();
						Utilities.screenshot(suggestion,driver);
						//System.out.println("took Screenshot");
						break;
					}
					else {
						System.out.println(suggestion+" not found in suggestion");
					}
				}
			}
			else {
				repo.searchBtn.click();	
				Utilities.screenshot(searchString,driver);
				//System.out.println("took Screenshot");
				Thread.sleep(2000);

			}
		}

	}

	public void searchWithString(String searchString) throws InterruptedException, IOException {

		if(!(searchString=="")) {
			repo.searchBar.clear();
			repo.searchBar.sendKeys(searchString);
			Thread.sleep(2000);
			repo.searchBtn.click();	
			Utilities.screenshot("AmzonSearch",driver);
		}
	}
}
