package com.TOS.Stepdefinition.Products;

import java.io.File;
import java.util.ArrayList;

import org.openqa.selenium.WebDriver;

import com.TOS.pages.ProductsPage;
import com.TOS.utils.BasicFunctionsUtils;
import com.TOS.utils.DriverManager;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProductSteps {
	WebDriver driver = DriverManager.getDriver();
	ProductsPage productPage = new ProductsPage(driver);

	@When("user clicks on {string} icon")
	public void user_clicks_on_icon(String icon) {
		if (icon.equalsIgnoreCase("Add Product")) {
			productPage.clickOnAddProductPlusIcon();
		} else if (icon.equalsIgnoreCase("Import")) {
			productPage.clickOnImportIcon();
		} else {
			productPage.clickOnIcon(icon);
		}
	}

	@When("Get product details from {string}")
	public void get_product_details_from(String testCaseID) {
		productPage.getProductDetails(testCaseID);
	}

	@When("user selects {string}")
	public void user_selects(String behalfName) {
		productPage.selectOnBehalfOf(behalfName);
	}

	@When("user fills details of Product Category")
	public void user_fills_details_of_product_category() {
		productPage.goToRequiredFieldsPage();
		productPage.fillRequiredFields();
		productPage.fillOptionFields();
	}

	@When("user uploads the {string} file")
	public void user_uploads_the_file(String fileType) {
		productPage.upload(fileType);
	}

	@When("user clicks on the submit button")
	public void user_clicks_on_the_submit_button_to_add_the_product() {
		productPage.submitProductDetails();
	}

	@When("user clicks on the clear button")
	public void user_clicks_on_the_clear_button() {
		productPage.clickOnClearButton();
	}

	@When("search for product name {string}")
	public void search_for_product_name(String string) {
		
		productPage.searchProduct(string);
	}

	@When("search for company {string}")
	public void search_for_company(String string) {
		productPage.searchForCompany(string);
	}

	@When("verify the added product message should display")
	public void verify_the_added_product_message_should_display() {
		productPage.validatePOPMessage("Create Product", "Product created successfully");
	}

	@When("verify added {string} should display in the product table with proper details")
	public void verify_added_should_display_in_the_product_table_with_proper_details(String productName)
			throws InterruptedException {
		if(BasicFunctionsUtils.itemUniqueName!=null) {
			productName=BasicFunctionsUtils.itemUniqueName;
		}
		productPage.validateProductDetailsDisplayInTable(productName);

	}

	@When("user opens the Product details for {string}")
	public void user_opens_the_product_details_for(String productName) {
		productPage.openProductDetails(productName);
	}

	@When("user clicks on the {string} icon")
	public void user_clicks_on_the_icon(String buttonName) {
		productPage.clickButtonInProductProfile(buttonName);
	}

	@When("user modifies the data")
	public void user_modifies_the_data() {
		productPage.editsProductDetails();
	}

	@When("verify the upadted product message should display")
	public void verify_the_upadted_product_message_should_display() {
		productPage.validatePOPMessage("Update Product", "Product updated successfully");
	}

	@Then("user should be able to update the data in product profile page")
	public void the_user_should_be_able_to_update_the_data_in_product_profile_page() {
		productPage.validateUpdatedDetails();
	}

	@When("user clicks on Download Template button")
	public void user_clicks_on_button() {
		productPage.clearDownloadFolder();
		productPage.clickOnDownloadTemplate();
	}

	@When("verify file {string} present in downloads.")
	public void verify_file_present_in_downloads(String filePath) {
		productPage.verifyFileInDownloads(filePath);
	}

	@When("user proceeds to next step to upload the file")
	public void user_proceeds_to_next_step_to_upload_the_file() {
		productPage.clickOnNextButton();
	}

	@Then("the product should be added successfully message display")
	public void the_product_should_be_added_successfully() {
		productPage.validatePOPMessage("Need to add Title", "Need to add discription");
	}

	@When("user selects FilterType as {string} with condition {string} and value {string}")
	public void user_selects_the_as_with_condition_and_value(String filterType, String filterCondition,
			String filterValue) {
		productPage.enterFilterType(filterType);
		productPage.enterFilterCondition(filterCondition);
		productPage.enterFilterValue(filterValue);
	}

	@Then("validate the Product details table {string} column has a value {string}")
	public void validate_the_product_details_table_column_has_a_value(String columnName, String expectedValue)
			throws Exception {
		ArrayList<String> displayData = productPage.readProductDetailsForColumn(columnName);
		if (displayData.size() == 0) {
			throw new Exception("\"" + expectedValue + "\" is not matched with any result in the \'" + columnName
					+ "\". please update the search value");
		}
		productPage.isContainValues(displayData, expectedValue);

	}

}
