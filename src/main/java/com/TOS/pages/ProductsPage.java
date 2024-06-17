package com.TOS.pages;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.TOS.page.locators.ProductPageLocators;
import com.TOS.utils.BasicFunctionsUtils;
import com.TOS.utils.DriverManager;
import com.TOS.utils.ExcelDataReader;

public class ProductsPage extends BasicFunctionsUtils {

	public WebDriver driver;
	ProductPageLocators productPageLocators;
	public static Map<String, String> productDetails;
	Map<String, String> displayProductDetailsInUI;
	Map<String, String> displayProductProfileDetails;
	Map<String, String> expectedMap;
	Map<String, Integer> tableHeaderWithIndex;
	SoftAssert sa;
	public PageObjectManager pageManager;

	public ProductsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		pageManager = new PageObjectManager(driver);
		this.productPageLocators = new ProductPageLocators(driver);
		PageFactory.initElements(driver, this.productPageLocators);
		productDetails = new HashMap<>();
		displayProductDetailsInUI = new HashMap<>();
		displayProductProfileDetails = new HashMap<>();
		tableHeaderWithIndex = new HashMap<>();
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

	public void clickOnImportIcon() {
		click(productPageLocators.importButton);
	}

	public void clickOnDownloadTemplate() {
		click(productPageLocators.downloadTemplateButton);
	}

	public void clearDownloadFolder() {
		deleteFolder(DriverManager.downloadFolder);
	}

	public void clickOnIcon(String iconName) {
		try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			click(productPageLocators.getProductProfileButton(iconName));
		} catch (NoSuchElementException e) {
			click(driver.findElement(By.xpath("//*[text()='" + iconName + "']")));
		} finally {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		}
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

	public void clickOnNextButton() {
		click(productPageLocators.nextButton);
	}

	public void fillRequiredFields() {
		uniqueProductName(productDetails.get("ProductName"));
		type(productPageLocators.productName, itemUniqueName);
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

	public void upload(String fileType) {
		String uploadFileLocation = null;
		if (fileType.equalsIgnoreCase("Image")) {
			uploadFileLocation = System.getProperty("user.dir") + "/src/test/resources/TestData/Image-1.png";
		} else if (fileType.contains(".xlsx")) {
			uploadFileLocation = System.getProperty("user.dir") + File.separator + "Downloads" + File.separator
					+ fileType;
		}
		if (productDetails.get("UploadFileLocation") != null) {
			uploadFileLocation = productDetails.get("UploadFileLocation");
		}
		upload(productPageLocators.uploadButton, uploadFileLocation);
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

	public void clickOnClearButton() {
		click(productPageLocators.clearButton);
	}

	public void searchProduct(String productName) {
		if (BasicFunctionsUtils.itemUniqueName != null) {
			productName = BasicFunctionsUtils.itemUniqueName;
		} else {
			productName = productDetails.get(productName);
		}
		type(productPageLocators.searchProduct, productName);
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

	public void validatePOPMessage(String expectedMessageTitle, String expectedMessageDiscription) {
		waitUntilElementVisiable(productPageLocators.messageTitle);
		String actualMessageTitle = getText(productPageLocators.messageTitle);
		String actualMessageDiscription = getText(productPageLocators.messageDiscription);
		sa = new SoftAssert();
		sa.assertEquals(actualMessageTitle, expectedMessageTitle, "Popup Message is incorrect");
		sa.assertEquals(actualMessageDiscription, expectedMessageDiscription, "Popup Message is incorrect");
		sa.assertAll();
	}

	public Map<String, String> readProductDetailsInTable(String productName) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (BasicFunctionsUtils.itemUniqueName != null) {
			productName = BasicFunctionsUtils.itemUniqueName;
		} else {
			productName = productDetails.get(productName);
		}
		List<WebElement> tableDataForProduct = driver
				.findElements(By.xpath("//a[text()='" + productName + "']/ancestor::tr/td"));
		for (int i = 0; i < productPageLocators.tableTitle.size(); i++) {

			String data = tableDataForProduct.get(i).getText();
			if (data != null) {
				if (!(data.isEmpty() || data.isBlank())) {
					displayProductDetailsInUI.put(productPageLocators.tableTitle.get(i).getText(),
							tableDataForProduct.get(i).getText());
				}
			}
		}
		return displayProductDetailsInUI;
	}

	public void readProductDetailsInProfilePage() {

		for (int i = 0; i < productPageLocators.profileDetailsLabel.size(); i++) {
			String data = productPageLocators.profileDetailsValues.get(i).getText();
			if (data != null) {
				if (!(data.isEmpty() || data.isBlank())) {
					displayProductProfileDetails.put(productPageLocators.profileDetailsLabel.get(i).getText(),
							productPageLocators.profileDetailsValues.get(i).getText());
				}
			}
		}

	}

	public ArrayList<String> readProductDetailsForColumn(String columnName) {
		readTableHeaderDetails();
		ArrayList<String> columnData = new ArrayList<String>();
		List<WebElement> allValues = productPageLocators.getAllTableCellValue(tableHeaderWithIndex.get(columnName));
		for (WebElement value : allValues) {
			columnData.add(value.getText());
		}
		return columnData;
	}

	public void readTableHeaderDetails() {
		for (int i = 0; i < productPageLocators.tableTitle.size(); i++) {
			tableHeaderWithIndex.put(productPageLocators.tableTitle.get(i).getText(), i + 1);
		}
	}

	public void validateProductDetailsDisplayInTable(String productName) {
		readProductDetailsInTable(productName);
		setExpectedMapForCompare(displayProductDetailsInUI);
		compareMapUniqueValues(productName, "Product Name", "for Product Name details");
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

	public void setExpectedMapForCompare(Map<String, String> map) {
		expectedMap = map;
		sa = new SoftAssert();
	}

	public void compareMapValues(String testDataKeyName, String uiKeyName, String messageNote) {
		String testData = productDetails.get(testDataKeyName);
		sa.assertEquals(testData, expectedMap.get(uiKeyName), messageNote);
	}

	public void compareMapUniqueValues(String string, String uiKeyName, String messageNote) {
		sa.assertEquals(string, expectedMap.get(uiKeyName), messageNote);
	}

	public void openProductDetails(String productName) {
		if (BasicFunctionsUtils.itemUniqueName != null) {
			productName = BasicFunctionsUtils.itemUniqueName;
		} else {
			productName = productDetails.get(productName);
		}
		click(productPageLocators.getProductHyperLine(productName));
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

	public void verifyFileInDownloads(String filePath) {
		Assert.assertTrue(isFileInFolder(DriverManager.downloadFolder, filePath),
				" Downloaded File:" + filePath + " is not present in the Downloads folder");
	}

	public void enterFilterType(String filterName) {
		click(productPageLocators.filterBy);
		click(productPageLocators.getFilterOption(filterName));
	}

	public void enterFilterCondition(String filterCondition) {
		click(productPageLocators.filterCondition);
		click(productPageLocators.getFilterOption(filterCondition));
	}

	public void enterFilterValue(String filterValue) {
		type(productPageLocators.filterValue, filterValue);
	}

	public void isContainValues(ArrayList<String> inputs, String expectedValue) {
		sa = new SoftAssert();
		for (String input : inputs) {
			Assert.assertTrue(input.contains(expectedValue),
					expectedValue + " is not present in the  filtered results: " + inputs);
		}
		sa.assertAll();
	}

	public void addLinkMaterialDetails() {
		type(productPageLocators.getAddLinkMaterialElement("material"), productDetails.get("LM-MaterialName"));
		pressEnter(productPageLocators.getAddLinkMaterialElement("material"));
		type(productPageLocators.getAddLinkMaterialElement("consumption"), productDetails.get("LM-Wastage"));
		type(productPageLocators.getAddLinkMaterialElement("unit"), productDetails.get("LM-Unit"));
		pressEnter(productPageLocators.getAddLinkMaterialElement("unit"));
		type(productPageLocators.getAddLinkMaterialElement("weight"), productDetails.get("LM-Weight"));
	}
}
