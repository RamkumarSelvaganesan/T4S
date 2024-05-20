package com.TOS.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.TOS.page.locators.DashboardPageLocators;
import com.TOS.utils.BasicFunctionsUtils;

public class DashboardPage extends BasicFunctionsUtils{
	
	public WebDriver driver;

	DashboardPageLocators dashboardPageLocators;
	
	public DashboardPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		this.dashboardPageLocators = new DashboardPageLocators();
		PageFactory.initElements(driver, dashboardPageLocators);
	}
	
	public void goToTab(String tabName) {
		WebElement tabLocater =  driver.findElement(By.xpath("//ul[@role='menu']//span[text()='"+tabName+"']"));
		click(tabLocater);
	}
}
