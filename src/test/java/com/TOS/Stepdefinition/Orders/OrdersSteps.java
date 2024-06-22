package com.TOS.Stepdefinition.Orders;

import org.openqa.selenium.WebDriver;

import com.TOS.pages.OrdersPage;
import com.TOS.utils.DriverManager;

import io.cucumber.java.en.When;

public class OrdersSteps {
	WebDriver driver = DriverManager.getDriver();
	private OrdersPage ordersPage = new OrdersPage(driver);

	@When("Get Orders details from {string}")
	public void get_orders_details_from(String string) {
		ordersPage.readTestCase(string);
	}

	@When("user fills Basic information of PO")
	public void user_fills_basic_information_of_po() {
		ordersPage.fillBasicDetails();
	}

	@When("user fills Product details of PO")
	public void user_fills_product_details_of_po() {
		ordersPage.fillProductDetails();
	}

	@When("user fills Document details of PO")
	public void user_fills_document_details_of_po() {
		ordersPage.fillDocumentDetails();
	}

	@When("verify the added purchase Order message should display")
	public void verify_the_added_purchase_order_message_should_display() {
		ordersPage.productsPage.validatePOPMessage("Add title", "Need to description");
	}

}
