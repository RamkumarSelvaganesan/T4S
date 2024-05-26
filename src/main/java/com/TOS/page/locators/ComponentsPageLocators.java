package com.TOS.page.locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ComponentsPageLocators {
	WebDriver driver;

	@FindBy(id = "name")
	public WebElement name;
	
	@FindBy(id = "description")
	public WebElement description;
	
	@FindBy(id = "unit")
	public WebElement unit;
	
	@FindBy(id = "material")
	public WebElement material;
	
	public ComponentsPageLocators(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getOptionsElement(String optionName) {
		return driver.findElement(By.xpath("//div[text()='"+optionName+"']"));
	}
}
