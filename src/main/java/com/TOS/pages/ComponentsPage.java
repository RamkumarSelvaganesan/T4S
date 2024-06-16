package com.TOS.pages;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import com.TOS.page.locators.ComponentsPageLocators;
import com.TOS.utils.BasicFunctionsUtils;
import com.TOS.utils.ExcelDataReader;

public class ComponentsPage extends BasicFunctionsUtils {

	public static Map<String, String> componentTestData;
	public ComponentsPageLocators componentsPageLocator;
	public PageObjectManager pageManager;
	public ProductsPage productsPage;

	SoftAssert sa;

	public ComponentsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		pageManager = new PageObjectManager(driver);
		this.componentsPageLocator = new ComponentsPageLocators(driver);
		componentTestData = new HashMap<>();
		productsPage = pageManager.getProductsPage();
		PageFactory.initElements(driver, this.componentsPageLocator);
		sa = new SoftAssert();
	}

	public void getProductDetails(String testCaseId) {
		try {
			componentTestData = ExcelDataReader.getData(testCaseId);
			// we reusing many component from Product page. so we need to pass the testdata
			productsPage.productDetails = componentTestData;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void fillRequiredDetails() {
		uniqueProductName(componentTestData.get("ComponentName"));
		type(componentsPageLocator.name, itemUniqueName);
		type(componentsPageLocator.description, componentTestData.get("Description"));
		click(componentsPageLocator.unit);
		click(componentsPageLocator.getOptionsElement(componentTestData.get("Unit")));
		click(componentsPageLocator.material);
		click(componentsPageLocator.getOptionsElement(componentTestData.get("Material")));
		if (componentTestData.get("SubMaterial") != null) {
			click(componentsPageLocator.getOptionsElement(componentTestData.get("SubMaterial")));
		}
	}

	public void validatePOPMessage(String expectedMessageTitle, String expectedMessageDiscription) {
		productsPage.validatePOPMessage(expectedMessageTitle, expectedMessageDiscription);
	}

	public void validateProductDetailsDisplayInTable(String componentName) {
		Map<String, String> displayProductProfileDetails = productsPage.readProductDetailsInTable(componentName);
		System.out.println("displayProductProfileDetails" + displayProductProfileDetails);
		productsPage.setExpectedMapForCompare(displayProductProfileDetails);
		productsPage.compareMapUniqueValues(itemUniqueName, "Name", "for Name details");
		productsPage.compareMapValues("Description", "Description", "for Description details");
		productsPage.compareMapValues("Unit", "Unit", "for Unit details");
		sa.assertAll();
	}

	public void fillBreakdownDetails() {
		click(componentsPageLocator.material);
		click(componentsPageLocator.getOptionsElement(componentTestData.get("BreakdownMaterial")));
		if (componentTestData.get("BreakdownSubMaterial") != null) {
			click(componentsPageLocator.getOptionsElement(componentTestData.get("BreakdownSubMaterial")));
		}
		click(componentsPageLocator.origin);
		click(componentsPageLocator.getOptionsElement(componentTestData.get("Origin")));
		type(componentsPageLocator.content, componentTestData.get("Content"));
	}

	public void verifyAddedBreakdown() {
		Map<String, String> displayRowData = null;
		if (componentTestData.get("BreakdownSubMaterial") != null) {
			displayRowData = readBreakDownDetailsFor(componentTestData.get("BreakdownSubMaterial"));
		}

		sa = new SoftAssert();
		sa.assertEquals(displayRowData.get("Component Name"), componentTestData.get("BreakdownSubMaterial"));
		sa.assertEquals(displayRowData.get("Origin"), componentTestData.get("Origin"));
		sa.assertEquals((displayRowData.get("Content %")).replaceAll("%", ""), componentTestData.get("Content"));
		sa.assertAll();
	}

	public Map<String, String> readBreakDownDetailsFor(String componentName) {
		Map<String, String> displayRowData = new HashMap<String, String>();
		for (int i = 0; i < componentsPageLocator.tableHeaders.size(); i++) {
			displayRowData.put(componentsPageLocator.tableHeaders.get(i).getText(),
					componentsPageLocator.getRowValues(componentName).get(i).getText());
		}
		return displayRowData;
	}

	public void clickOnDeleteIcon() {
		click(componentsPageLocator.deleteIcon);
		productsPage.clickOnIcon("OK");
	}

}
