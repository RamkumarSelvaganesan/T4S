package com.TOS.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.TOS.page.locators.ProductPageLocators;
import com.TOS.utils.BasicFunctionsUtils;
import com.TOS.utils.ExcelDataReader;

public class ProductsPage extends BasicFunctionsUtils {

	public WebDriver driver;
	ProductPageLocators productPageLocators;
	Map<String, String> productDetails;
	Map<String, String> displayProductDetailsInUI;
	Map<String, String> displayProductProfileDetails;
	Map expectedMap;
	SoftAssert sa;

	public ProductsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		this.productPageLocators = new ProductPageLocators(driver);
		PageFactory.initElements(driver, this.productPageLocators);
		productDetails = new HashMap<>();
		displayProductDetailsInUI = new HashMap<>();
		displayProductProfileDetails = new HashMap<>();
	}

	// new code

	public void getProductDetails(String testCaseId) {
		try {
			productDetails = ExcelDataReader.getData(testCaseId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void clickOnAddProductPlusIcon() {
		click(productPageLocators.addProductButton);
	}

	public void selectOnBehalfOf(String behalfName) {
		if (productDetails.get(behalfName) != null) {
			type(productPageLocators.onBehalfOf, productDetails.get(behalfName));
			pressEnter(productPageLocators.onBehalfOf);
			click(productPageLocators.nextButton);
		}
	}

	public void goToRequiredFieldsPage() {
		if (!(isElementVisible(productPageLocators.previousButton)
				&& (isElementVisible(productPageLocators.productName)))) {
			click(productPageLocators.nextButton);
		}
	}

	public void fillRequiredFields() {
		type(productPageLocators.productName, productDetails.get("ProductName"));
		type(productPageLocators.unitCost, productDetails.get("Cost"));
		type(productPageLocators.category, productDetails.get("Category"));
		pressEnter(productPageLocators.category);
		type(productPageLocators.subCategory, productDetails.get("SubCategory"));
		pressEnter(productPageLocators.subCategory);
		type(productPageLocators.quantity, productDetails.get("Quantity"));
		pressEnter(productPageLocators.quantity);
		type(productPageLocators.measureValue, productDetails.get("MeasureValue"));
		click(productPageLocators.nextButton);
	}

	public void fillOptionFields() {
		setActiveRadioButton(productPageLocators.active, productDetails.get("Active"));
		typeIfDataPresent(productPageLocators.weight, productDetails.get("Weight"));
		typeIfDataPresent(productPageLocators.colorCode, productDetails.get("ColorCode"));
		typeIfDataPresent(productPageLocators.lfItemNo, productDetails.get("LF"));
		typeIfDataPresent(productPageLocators.hsCode, productDetails.get("HSCode"));
		typeIfDataPresent(productPageLocators.sku, productDetails.get("SKU"));
		typeIfDataPresent(productPageLocators.upc, productDetails.get("UPC"));
		typeIfDataPresent(productPageLocators.refNo, productDetails.get("RefNo"));
		typeIfDataPresent(productPageLocators.collection, productDetails.get("Collection"));
	}

	public void uploadImage() {
		String imageLocation = System.getProperty("user.dir") + "/src/test/resources/TestData/Image-1.png";
		upload(productPageLocators.imageUploadinput, imageLocation);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void submitProductDetails() {
		click(productPageLocators.submitButton);
	}

	public void searchProduct(String productName) {
		type(productPageLocators.searchProduct, productDetails.get(productName));
	}

	public void searchForCompany(String companyName) {
		if (productDetails.get(companyName) != null) {
			click(productPageLocators.forCompany);
			WebElement companyOption = driver
					.findElement(By.xpath("//div[@class='ant-select-item-option-content' and contains(text(),'"
							+ productDetails.get(companyName) + "')]"));
			click(companyOption);
		}
	}

	public void validateAddedProductMessage() {
		waitUntilElementVisiable(productPageLocators.messageTitle);
		String actualMessageTitle = getText(productPageLocators.messageTitle);
		String actualMessageDiscription = getText(productPageLocators.messageDiscription);
		String expectedMessageTitle = "Create Product";
		String expectedMessageDiscription = "Product created successfully";
		Assert.assertEquals(actualMessageTitle, expectedMessageTitle,"Popup Message is incorrect");
		Assert.assertEquals(actualMessageDiscription, expectedMessageDiscription,"Popup Message is incorrect");
	}

	public void validateUpdatedProductMessage() {
		waitUntilElementVisiable(productPageLocators.messageTitle);
		String actualMessageTitle = getText(productPageLocators.messageTitle);
		String actualMessageDiscription = getText(productPageLocators.messageDiscription);
		String expectedMessageTitle = "Update Product";
		String expectedMessageDiscription = "Product updated successfully";
		Assert.assertEquals(actualMessageTitle, expectedMessageTitle,"Popup Message is incorrect");
		Assert.assertEquals(actualMessageDiscription, expectedMessageDiscription,"Popup Message is incorrect");
	}

	public void readProductDetailsInTable(String productName) {
		List<WebElement> tableDataForProduct = driver
				.findElements(By.xpath("//a[text()='" + productDetails.get(productName) + "']/ancestor::tr/td"));
		for (int i = 0; i < productPageLocators.tableTitle.size(); i++) {
			
			String data = tableDataForProduct.get(i).getText();
			if(data  != null) {
				if(!(data.isEmpty()|| data.isBlank())) {
			displayProductDetailsInUI.put(productPageLocators.tableTitle.get(i).getText(),
					tableDataForProduct.get(i).getText());
		}}}
	}

	public void readProductDetailsInProfilePage() {
		for (int i = 0; i < productPageLocators.profileDetailsLabel.size(); i++) {
			String data = productPageLocators.profileDetailsValues.get(i).getText();
			if(data  != null) {
				if(!(data.isEmpty()|| data.isBlank())) {
			displayProductProfileDetails.put(productPageLocators.profileDetailsLabel.get(i).getText(),
					productPageLocators.profileDetailsValues.get(i).getText());
		}}}
	}

	public void validateProductDetailsDisplayInTable(String productName) {
		readProductDetailsInTable(productName);
		setExpectedMapForCompare(displayProductDetailsInUI);
		compareMapValues("ProductName", "Product Name", "for Product Name details");
		compareMapValues("Active", "Active", "for Active details");
		compareMapValues("Ref No", "Product Code", "for Product code details");
		compareMapValues("HSCode", "HS Code", "for HS code details");
		compareMapValues("Category", "Category", "for Category details");
		compareMapValues("SubCategory", "Sub Category", "for Sub Category details");
		compareMapValues("Weight", "Weight/pc (kg)", "for Weight/pc (kg) details");
		compareMapValues("MeasureValue", "Measure Value", "for Measure Value details");
		sa.assertAll();

	}
	

	public boolean isEqual(String actual, String expected) {
		return actual.equalsIgnoreCase(expected);
	}
	
	public void setExpectedMapForCompare(Map map) {
		expectedMap =map;
		sa = new SoftAssert();
	}

	public void compareMapValues(String testDataKeyName, String uiKeyName, String messageNote) {
		String testData = productDetails.get(testDataKeyName);
		sa.assertEquals(testData, expectedMap.get(uiKeyName), messageNote);
	}

	public void openProductDetails(String productName) {
		click(productPageLocators.getProductHyperLine(productDetails.get(productName)));
	}

	public void clickButtonInProductProfile(String buttonName) {
		click(productPageLocators.getProductProfileButton(buttonName));
	}

	public void editsProductDetails() {
		updateIfDataPresent(productPageLocators.weight, productDetails.get("EditWeight"));
		updateIfDataPresent(productPageLocators.unitCost, productDetails.get("EditItemcost"));
		updateIfDataPresent(productPageLocators.sku, productDetails.get("EditSku"));
		updateIfDataPresent(productPageLocators.collection, productDetails.get("EditSeason"));
		updateIfDataPresent(productPageLocators.remark, productDetails.get("EditRemarks"));
		updateIfDataPresent(productPageLocators.tags, productDetails.get("EditTags"));
	
	}

	public void validateUpdatedDetails() {
		readProductDetailsInProfilePage();
		setExpectedMapForCompare(displayProductProfileDetails);
		compareMapValues("EditWeight", "Weight (kg) / Unit", "for Weight (kg) / Unit");
		compareMapValues("EditItemcost", "Item Cost (USD)", "for Item Cost (USD)");
		compareMapValues("EditSku", "SKU", "for SKU details");
		compareMapValues("EditSeason", "Collection / Season", "for Collection / Season");
		compareMapValues("EditRemarks", "Remarks", "for Remarks");
		compareMapValues("EditTags", "Tags", "for Tags");
		sa.assertAll();
	}

	public void test() {
		
	}
}
