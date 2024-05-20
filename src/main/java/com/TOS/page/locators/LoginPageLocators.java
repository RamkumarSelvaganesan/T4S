package com.TOS.page.locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPageLocators {

	@FindBy(name = "username")
	public WebElement emailTxtBox;

	@FindBy(name = "password")
	public WebElement passwordTxtBox;

	@FindBy(xpath = "//button[@type='submit']")
	public WebElement continueButton;

}