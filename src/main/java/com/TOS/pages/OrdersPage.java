package com.TOS.pages;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.TOS.page.locators.OrdersPageLocators;
import com.TOS.utils.BasicFunctionsUtils;
import com.TOS.utils.ExcelDataReader;

public class OrdersPage extends BasicFunctionsUtils {
	public PageObjectManager pageManager;
	public ProductsPage productsPage;
	private OrdersPageLocators OrdersPageLocators;
	public static Map<String, String> orderTestData;

	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		pageManager = new PageObjectManager(driver);
		this.OrdersPageLocators = new OrdersPageLocators(driver);
		productsPage = pageManager.getProductsPage();
		PageFactory.initElements(driver, this.OrdersPageLocators);
	}

	public void readTestCase(String testCaseId) {
		try {
			orderTestData = ExcelDataReader.getData(testCaseId);
			// we reusing many component from Product page. so we need to pass the testdata
			productsPage.excelDetails = orderTestData;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void fillBasicDetails() {
		type(OrdersPageLocators.currency, orderTestData.get("Currency"));
		pressEnter(OrdersPageLocators.currency);
		type(OrdersPageLocators.supplier, orderTestData.get("Supplier"));
		pressEnter(OrdersPageLocators.supplier);
		click(OrdersPageLocators.purchaseProcessesDropdown);
		click(OrdersPageLocators.getOptionElement(orderTestData.get("Purchase Processes")));
		typeIfDataPresent(OrdersPageLocators.refNo, orderTestData.get("Ref No."));
		typeIfDataPresent(OrdersPageLocators.secondarySupplier, orderTestData.get("Secondary Supplier"));
		typeIfDataPresent(OrdersPageLocators.orderNature, orderTestData.get("Order Nature"));
		pressEnter(OrdersPageLocators.orderNature);
		typeIfDataPresent(OrdersPageLocators.externalCustomerReference,
				orderTestData.get("External Customer Reference"));
		click(productsPage.productPageLocators.nextButton);

	}

	public void fillProductDetails() {
		productsPage.clickOnIcon("Add Item");
		typeIfDataPresent(OrdersPageLocators.getProductElement("productName"), orderTestData.get("Product Name"));
		pressEnter(OrdersPageLocators.getProductElement("productName"));
		typeIfDataPresent(OrdersPageLocators.getProductElement("quantity"), orderTestData.get("Quantity"));
		typeIfDataPresent(OrdersPageLocators.getProductElement("unit"), orderTestData.get("Unit"));
		pressEnter(OrdersPageLocators.getProductElement("unit"));
		typeIfDataPresent(OrdersPageLocators.getProductElement("ppu"), orderTestData.get("Unit Price"));
		typeIfDataPresent(OrdersPageLocators.getProductElement("comment"), orderTestData.get("Comment"));
		click(productsPage.productPageLocators.nextButton);
	}

	public void fillDocumentDetails() {
		typeIfDataPresent(OrdersPageLocators.tracingTemplate, orderTestData.get("Tracing Template"));
		pressEnter(OrdersPageLocators.tracingTemplate);
	}

}
