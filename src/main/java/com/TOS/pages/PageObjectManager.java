package com.TOS.pages;

import org.openqa.selenium.WebDriver;

public class PageObjectManager {

	public ProductsPage productPage;
	public ComponentsPage componentsPage;
	public WebDriver driver;

	public PageObjectManager(WebDriver driver) {
		this.driver = driver;
	}

	public ProductsPage getProductsPage() {
		return new ProductsPage(driver);
	}

	public ComponentsPage getComponentsPage() {
		return new ComponentsPage(driver);
	}

	public DocumentRulesetsPage getDocumentRulesetsPage() {
		return new DocumentRulesetsPage(driver);
	}

}
