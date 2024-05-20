package com.TOS.Stepdefinition.Products;

import org.openqa.selenium.WebDriver;

import com.TOS.pages.ProductsPage;
import com.TOS.utils.DriverManager;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProductSteps {
	WebDriver driver = DriverManager.getDriver();
	ProductsPage productPage =  new ProductsPage(driver);

	@When("user clicks on {string} icon")
	public void user_clicks_on_icon(String icon) {
		if(icon.equalsIgnoreCase("Add Product")) {
			productPage.clickOnAddProductPlusIcon();
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
	
	@When("user uploads the image file")
	public void user_uploads_the_image_file() {
	    productPage.uploadImage();
	}
	
	@When("user clicks on the submit button")
	public void user_clicks_on_the_submit_button_to_add_the_product() {
	    productPage.submitProductDetails();
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
		productPage.validateAddedProductMessage();
	}
	
	@When("verify added {string} should display in the product table with proper details")
	public void verify_added_should_display_in_the_product_table_with_proper_details(String productName) throws InterruptedException {
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
		productPage.validateUpdatedProductMessage();
	}
	
	@Then("user should be able to update the data in product profile page")
	public void the_user_should_be_able_to_update_the_data_in_product_profile_page() {
		productPage.validateUpdatedDetails();
	}

	
}

