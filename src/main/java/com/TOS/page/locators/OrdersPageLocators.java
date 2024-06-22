package com.TOS.page.locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OrdersPageLocators {
	WebDriver driver;

	public OrdersPageLocators(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(id = "currency")
	public WebElement currency;

	@FindBy(id = "supplier")
	public WebElement supplier;

	@FindBy(xpath = "//label[@title='Purchase Processes']/../..//div[contains(@class,'ant-select-show-search')]")
	public WebElement purchaseProcessesDropdown;

	@FindBy(id = "externalDataId")
	public WebElement refNo;

	@FindBy(xpath = "//label[@title='Secondary Supplier']/../..//input")
	public WebElement secondarySupplier;

	@FindBy(id = "orderNature")
	public WebElement orderNature;

	@FindBy(id = "externalCustomerReference")
	public WebElement externalCustomerReference;

	public WebElement getProductElement(String name) {
		return driver.findElement(By.xpath("//input[contains(@id,'" + name + "')][last()]"));
	}

	public WebElement getOptionElement(String option) {
		return driver.findElement(By.xpath("//*[@title='" + option + "']"));
	}

	@FindBy(id = "tracingTemplate")
	public WebElement tracingTemplate;

}
