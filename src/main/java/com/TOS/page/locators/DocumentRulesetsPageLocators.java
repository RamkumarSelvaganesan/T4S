package com.TOS.page.locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DocumentRulesetsPageLocators {
	WebDriver driver;

	public DocumentRulesetsPageLocators(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(id = "name")
	public WebElement name;
	@FindBy(id = "code")
	public WebElement code;
	@FindBy(id = "description")
	public WebElement description;

	public WebElement getAddRulesElement(String name) {
		return driver.findElement(By.xpath("//input[contains(@id,'" + name + "')][last()]"));
	}
}
