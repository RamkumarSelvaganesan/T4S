package com.TOS.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.TOS.page.locators.LoginPageLocators;
import com.TOS.utils.BasicFunctionsUtils;
import com.TOS.utils.PropertiesRead;

public class LoginPage extends BasicFunctionsUtils {
	
	public WebDriver driver;

	LoginPageLocators loginPageLocators;
	
	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		this.loginPageLocators = new LoginPageLocators();
		PageFactory.initElements(driver, loginPageLocators);
	}
	public void loginIntoApplication() {
		driver.get(PropertiesRead.getProperties("hostName"));
		type(loginPageLocators.emailTxtBox,PropertiesRead.getProperties("userName"));
		click(loginPageLocators.continueButton);
		type(loginPageLocators.passwordTxtBox,PropertiesRead.getProperties("passWord"));
		click(loginPageLocators.continueButton);
	}

}
