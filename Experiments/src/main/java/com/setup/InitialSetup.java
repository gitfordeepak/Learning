package com.setup;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class InitialSetup {

	public static WebDriver driver;
	public static String browser;
	public static Properties prop;
	private static FileInputStream input = null;

	@SuppressWarnings("deprecation")
	public static WebDriver intiateDriver() throws IOException {

		browser = InitialSetup.readProp("Browser");
		switch (browser.toLowerCase()) {
		case "chrome": {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		}
		case "firefox": {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		}
		case "ie": {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			break;
		}
		case "edge": {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + browser+" is not defined correctly in Property file, Please check and correct");
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

		return driver;




	}

	//////////////////////////Read Property File/////////////////////////
	public static String readProp(String key) throws IOException {

		prop = new Properties();
		File file = new File("C:\\Users\\deepa\\git\\Learning\\Experiments\\src\\main\\resources\\ConfigFiles\\Properties.properties");
		//file = new File(".\\Experiments\\src\\main\\resources\\ConfigFiles\\Properties.properties");

		try {
			input = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		prop.load(input);
		String propValue = prop.getProperty(key);
		return propValue;	

	}

}
