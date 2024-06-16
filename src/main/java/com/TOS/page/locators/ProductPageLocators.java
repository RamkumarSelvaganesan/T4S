package com.TOS.page.locators;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPageLocators {
	WebDriver driver;
	@FindBy(xpath = "//button//span[text()='Add Product']")
	public WebElement addProductButton;

	@FindBy(xpath = "//button//span[text()='Import']")
	public WebElement importButton;

	@FindBy(id = "onBehalfOf")
	public WebElement onBehalfOf;

	@FindBy(xpath = "//button//span[text()='Next']")
	public WebElement nextButton;

	@FindBy(xpath = "//button//span[text()='Previous']")
	public WebElement previousButton;

	@FindBy(xpath = "//button//span[text()='Close']")
	public WebElement closeButton;

	@FindBy(xpath = "//button//span[text()='Clear']")
	public WebElement clearButton;

	@FindBy(xpath = "//button//span[text()='Submit']")
	public WebElement submitButton;

	@FindBy(id = "name")
	public WebElement productName;

	@FindBy(id = "cost")
	public WebElement unitCost;

	@FindBy(id = "baseCategory")
	public WebElement category;

	@FindBy(id = "category")
	public WebElement subCategory;

	@FindBy(id = "unit")
	public WebElement quantity;

	@FindBy(id = "measureValue")
	public WebElement measureValue;

	@FindBy(id = "isActive")
	public WebElement active;

	@FindBy(id = "weight")
	public WebElement weight;

	@FindBy(id = "color")
	public WebElement colorCode;

	@FindBy(id = "externalDataId")
	public WebElement lfItemNo;

	@FindBy(id = "hsCode")
	public WebElement hsCode;

	@FindBy(id = "sku")
	public WebElement sku;

	@FindBy(id = "upc")
	public WebElement upc;

	@FindBy(id = "code")
	public WebElement refNo;

	@FindBy(id = "collection")
	public WebElement collection;

	@FindBy(id = "remark")
	public WebElement remark;

	@FindBy(xpath = "//div[@class='ant-select-selection-overflow']")
	public WebElement tags;

	@FindBy(xpath = "//span[@role='button']//input[@type='file']/..")
	public WebElement imageUpload;

	@FindBy(xpath = "//span[@role='button']//input[@type='file']")
	public WebElement uploadButton;

	@FindBy(xpath = "//span[contains(@class,'ant-input-group')]//input")
	public WebElement searchProduct;

	@FindBy(xpath = "//div[@class=\"ant-select custom-select  css-184gkg7 ant-select-single ant-select-allow-clear ant-select-show-arrow ant-select-show-search\"]")
	public WebElement forCompany;

	@FindBy(xpath = "//div[@class='ant-alert-message']")
	public WebElement messageTitle;

	@FindBy(xpath = "//div[@class='ant-alert-description']")
	public WebElement messageDiscription;

	@FindBy(css = "thead.ant-table-thead th")
	public List<WebElement> tableTitle;

	public ProductPageLocators(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getProductHyperLine(String productName) {
		return driver.findElement(By.linkText(productName));
	}

	public WebElement getProductProfileButton(String buttonName) {
		return driver.findElement(By.xpath("//span[text()='" + buttonName + "']"));
	}

	@FindBy(xpath = "//table//span[@class='ant-descriptions-item-content']/..//span[@class='ant-descriptions-item-label']")
	public List<WebElement> profileDetailsLabel;

	@FindBy(xpath = "//table//span[@class='ant-descriptions-item-content']")
	public List<WebElement> profileDetailsValues;

	@FindBy(xpath = "//a[@download='products_template.xlsx']")
	public WebElement downloadTemplateButton;

	@FindBy(xpath = "//tbody//tr[@data-row-key]//td")
	public List<WebElement> columnData;

	public List<WebElement> getAllTableCellValue(int columnIndex) {
		return driver.findElements(By.xpath("//tbody//tr[@data-row-key]//td[" + columnIndex + "]"));
	}

	@FindBy(id = "column")
	public WebElement filterBy;

	@FindBy(id = "condition")
	public WebElement filterCondition;

	@FindBy(id = "value")
	public WebElement filterValue;

	public WebElement getFilterOption(String optionName) {
		return driver.findElement(By.xpath("//div[@title='" + optionName + "']"));
	}

	public WebElement getAddLinkMaterialElement(String name) {
		return driver.findElement(By.xpath("//input[contains(@id,'" + name + "')][last()]"));
	}

}
