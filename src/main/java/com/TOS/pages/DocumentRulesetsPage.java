package com.TOS.pages;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.TOS.page.locators.DocumentRulesetsPageLocators;
import com.TOS.utils.BasicFunctionsUtils;
import com.TOS.utils.ExcelDataReader;

public class DocumentRulesetsPage extends BasicFunctionsUtils {

	public DocumentRulesetsPageLocators DocumentRulesetsPageLocators;
	public PageObjectManager pageManager;
	public ProductsPage productsPage;
	public static Map<String, String> documentTestData;

	public DocumentRulesetsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		pageManager = new PageObjectManager(driver);
		this.DocumentRulesetsPageLocators = new DocumentRulesetsPageLocators(driver);
		productsPage = pageManager.getProductsPage();
		PageFactory.initElements(driver, this.DocumentRulesetsPageLocators);
	}

	public void fillRequiredFields() {
		type(DocumentRulesetsPageLocators.name, documentTestData.get("Name"));
		type(DocumentRulesetsPageLocators.code, documentTestData.get("Code"));
		type(DocumentRulesetsPageLocators.description, documentTestData.get("Description"));
		click(productsPage.productPageLocators.nextButton);
	}

	public void readTestCase(String testCaseId) {
		try {
			documentTestData = ExcelDataReader.getData(testCaseId);
			// we reusing many component from Product page. so we need to pass the testdata
			productsPage.excelDetails = documentTestData;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void fillOptionalFields() {
		productsPage.clickOnIcon("Add Item");
		typeIfDataPresent(DocumentRulesetsPageLocators.getAddRulesElement("document"),
				documentTestData.get("Document Type"));
		pressEnter(DocumentRulesetsPageLocators.getAddRulesElement("document"));
		typeIfDataPresent(DocumentRulesetsPageLocators.getAddRulesElement("appliesTo"),
				documentTestData.get("Applies To"));
		pressEnter(DocumentRulesetsPageLocators.getAddRulesElement("appliesTo"));
	}
}
