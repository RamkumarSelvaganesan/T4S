package com.TOS.Stepdefinition.DocumentRulesets;

import org.openqa.selenium.WebDriver;

import com.TOS.pages.DocumentRulesetsPage;
import com.TOS.utils.DriverManager;

import io.cucumber.java.en.When;

public class DocumentRulesetSteps {
	WebDriver driver = DriverManager.getDriver();
	private DocumentRulesetsPage documentRuleset = new DocumentRulesetsPage(driver);

	@When("user fills Required details of ruleset")
	public void user_fills_details_of_ruleset_category() {
		documentRuleset.fillRequiredFields();
	}

	@When("user fills Optional Ruleset details")
	public void user_fills_optional_Ruleset_details() {
		documentRuleset.fillOptionalFields();
	}

	@When("Get DocumentRulesets details from {string}")
	public void get_document_rulesets_details_from(String testCaseId) {
		documentRuleset.readTestCase(testCaseId);
	}

	@When("verify the added Ruleset message should display")
	public void verify_the_added_ruleset_message_should_display() {
		documentRuleset.productsPage.validatePOPMessage("Add Title", "Add description");
	}
}
