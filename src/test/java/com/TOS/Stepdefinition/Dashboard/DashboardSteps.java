package com.TOS.Stepdefinition.Dashboard;

import org.openqa.selenium.WebDriver;

import com.TOS.pages.DashboardPage;
import com.TOS.utils.DriverManager;

import io.cucumber.java.en.When;

public class DashboardSteps {
	
	WebDriver driver = DriverManager.getDriver();
	
	private DashboardPage dashboardPage = new DashboardPage(driver);
	
	@When("user clicks on {string} Tab")
	public void user_clicks_on_tab(String tabName) {
	    dashboardPage.goToTab(tabName);
	}
	

}
