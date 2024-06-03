package com.TOS.Stepdefinition.Components;

import org.openqa.selenium.WebDriver;

import com.TOS.pages.ComponentsPage;
import com.TOS.pages.ProductsPage;
import com.TOS.utils.BasicFunctionsUtils;
import com.TOS.utils.DriverManager;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ComponentSteps {
	WebDriver driver = DriverManager.getDriver();
	ComponentsPage componentsPage = new ComponentsPage(driver);
	ProductsPage productPage = new ProductsPage(driver);

	@When("Get component details from {string}")
	public void get_product_details_from(String testCaseID) {
		componentsPage.getProductDetails(testCaseID);
	}

	@When("user fills details of Component Category")
	public void user_fills_details_of_component_category() {
		componentsPage.fillRequiredDetails();
	}

	@When("verify the added component message should display")
	public void verify_the_added_component_message_should_display() {
		componentsPage.validatePOPMessage("Create Material", "Material created successfully");
	}

	@When("search for component name {string}")
	public void search_for_component_name(String string) {
		if (BasicFunctionsUtils.itemUniqueName != null) {
			string = BasicFunctionsUtils.itemUniqueName;
		}
		productPage.searchProduct(string);
	}

	@Then("verify added {string} should display in the components table with proper details")
	public void verify_added_should_display_in_the_components_table_with_proper_details(String componentName) {
		if (BasicFunctionsUtils.itemUniqueName != null) {
			componentName = BasicFunctionsUtils.itemUniqueName;
		}
		componentsPage.validateProductDetailsDisplayInTable(componentName);
	}

	@When("user opens the Component details for {string}")
	public void user_opens_the_component_details_for(String componetName) {
		productPage.openProductDetails(componetName);
	}

	@When("user fills details of Breakdown")
	public void user_fills_details_of_breakdown() {
		componentsPage.fillBreakdownDetails();
	}

	@When("verify the update component message should display")
	public void verify_the_upadted_component_message_should_display() {
		productPage.validatePOPMessage("Update Material", "Material updated successfully");
	}

	@Then("user should be able to add breakdown with details")
	public void user_should_be_able_to_add_breakdown_with_details() {
		componentsPage.verifyAddedBreakdown();
	}
}
