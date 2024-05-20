package com.TOS.Stepdefinition;

import org.openqa.selenium.WebDriver;

import com.TOS.pages.LoginPage;
import com.TOS.utils.DriverManager;

import io.cucumber.java.en.Given;

public class commonSteps {

	WebDriver driver = DriverManager.getDriver();

	private LoginPage loginPage = new LoginPage(driver);

	@Given("the user is on the T0S Application home page")
	public void userIsOnGoogleSearchPage() {
		loginPage.loginIntoApplication();
	}

}
