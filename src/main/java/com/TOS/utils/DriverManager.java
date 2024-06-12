package com.TOS.utils;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

public class DriverManager {
	private static WebDriver driver;
	public static String downloadFolder;

	public enum BrowserList {
		CHROME, FIREFOX
	}

	public static WebDriver getDriver() {
		String browser = PropertiesRead.getProperties("Browser");
		downloadFolder = System.getProperty("user.dir") + File.separator + "Downloads";
		if (driver == null) {

			switch (BrowserList.valueOf(browser.toUpperCase())) {
			case CHROME:
				System.setProperty("webdriver.chrome.driver", "C:\\Users\\v-NandaniNisha\\Selenium\\chromedriver.exe");
				driver = new ChromeDriver(setupChromeOptions());
				break;
			case FIREFOX:
				driver = new FirefoxDriver(setupFirefoxOptions());
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

	public static ChromeOptions setupChromeOptions() {
		ChromeOptions options = new ChromeOptions();
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("download.default_directory", downloadFolder);
		options.setExperimentalOption("prefs", prefs);
		options.addArguments("--remote-allow-origins=*");
		return options;
	}

	public static FirefoxOptions setupFirefoxOptions() {
		FirefoxOptions options = new FirefoxOptions();
		FirefoxProfile profile = new FirefoxProfile();

		// Set the download directory
		profile.setPreference("browser.download.folderList", 2); // Use for custom location
		profile.setPreference("browser.download.dir", downloadFolder);
		profile.setPreference("browser.download.useDownloadDir", true);

		// Set preferences to avoid download prompts for known file types
		profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
				"application/pdf, application/x-pdf, application/octet-stream");

		options.setProfile(profile);

		return options;
	}

}
