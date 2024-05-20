package com.TOS.utils;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverManager {
	private static WebDriver driver;

	public enum BrowserList {
		CHROME, FIREFOX
	}

	public static WebDriver getDriver() {
		String browser = PropertiesRead.getProperties("Browser");
		if (driver == null) {

			switch (BrowserList.valueOf(browser.toUpperCase())) {
			case CHROME:
				driver = new ChromeDriver();
				break;
			case FIREFOX:
				driver = new FirefoxDriver();
				driver.manage().deleteAllCookies();
				break;
			default:
				System.out.println("No Browser is specified");
			}
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
		}
		return driver;
	}

	public static void quitDriver() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}

}
