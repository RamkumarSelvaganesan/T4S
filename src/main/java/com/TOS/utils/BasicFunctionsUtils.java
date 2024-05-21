package com.TOS.utils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasicFunctionsUtils {

	public WebDriver driver;

	public BasicFunctionsUtils(WebDriver driver) {
		this.driver = driver;
	}

	public void javaScriptClick(WebElement ele) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", ele);
	}

	public void actionClick(WebElement ele) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);
		Actions action = new Actions(driver);
		action.moveToElement(ele).click().perform();
	}

	public void waitUntilElementVisiable(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}

	public void waitUntilElementToBeClicked(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	}

	public void waitUntilLoadComplete() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {

		}
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(webDriver -> ((JavascriptExecutor) webDriver)
				.executeScript("return document.readyState").equals("complete"));
	}

	public void click(WebElement element) {
		// waitUntilElementToBeClicked(element);
		element.click();
		waitUntilLoadComplete();
	}

	public void type(WebElement element, String value) {
		waitUntilElementToBeClicked(element);
		element.sendKeys(value);
		waitUntilLoadComplete();
	}

	public void enter(WebElement element, String value) {
		element.sendKeys(value);
		waitUntilLoadComplete();
	}

	public void upload(WebElement element, String locationPath) {
		element.sendKeys(locationPath);
		waitUntilLoadComplete();
	}

	public void typeIfDataPresent(WebElement element, String value) {
		if (value != null) {
			waitUntilElementToBeClicked(element);
			element.sendKeys(value);
			waitUntilLoadComplete();
		}
	}
	
	public void updateIfDataPresent(WebElement element, String value) {
		if (value != null) {
			waitUntilElementToBeClicked(element);
			clearText(element, 25);
			waitUntilLoadComplete();
			element.sendKeys(value);
			waitUntilLoadComplete();
		}
	}

	public void pressEnter(WebElement element) {
		element.sendKeys(Keys.ENTER);
		waitUntilLoadComplete();
	}

	public boolean isElementVisible(WebElement element) {
		return element.isDisplayed();
	}

	public String getText(WebElement element) {
		return element.getText();
	}

	public void setRadioButton(WebElement element, String value) {
		if (value != null) {
			String currentStatus = String.valueOf(element.isSelected());
			if (!currentStatus.equalsIgnoreCase(value)) {
				click(element);
			}
		}
	}

	public void setActiveRadioButton(WebElement element, String value) {
		if (value != null) {
			value = value.equalsIgnoreCase("Active") ? "true" : "false";
			String currentStatus = String.valueOf(element.isSelected());
			if (!currentStatus.equalsIgnoreCase(value)) {
				click(element);
			}
		}
	}

	public void clearText(WebElement element, int num) {
		for (int i = 0; i < num; i++) {
			element.sendKeys(Keys.BACK_SPACE);
		}
	}

	public void deleteFolder(String folderPath) {
		File folder = new File(folderPath);
		try {
			FileUtils.cleanDirectory(folder);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static boolean isFileInFolder(String folderPath, String fileName) {
		if (folderPath == null || fileName == null) {
			throw new IllegalArgumentException("Folder path or file name cannot be null.");
		}

		// Create a File object for the folder
		File folder = new File(folderPath);

		// Check if the folder exists and is a directory
		if (folder.exists() && folder.isDirectory()) {
			// Create a File object for the file
			File file = new File(folder, fileName);

			// Check if the file exists and is indeed a file
			return file.exists() && file.isFile();
		} else {
			System.out.println("The specified folder does not exist or is not a directory.");
			return false;
		}
	}

}
