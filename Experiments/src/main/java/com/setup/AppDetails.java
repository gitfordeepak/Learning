package com.setup;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

public class AppDetails {
	
	public static String app,inputExcel;
	WebDriver driver;
	InitialSetup setup = new InitialSetup();
	
	public AppDetails(String application) {
		
		AppDetails.app=application;
		
	}
	
	public void initiateValues() throws IOException {
		
		inputExcel = InitialSetup.readProp(app+"inputExcel");
		
		
	}
	

}
