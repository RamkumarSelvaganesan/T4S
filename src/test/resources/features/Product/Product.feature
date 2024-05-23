Feature: Product Categories

  Background: 
    Given the user is on the T0S Application home page
	@new
	Scenario Outline: Verify that user can add a Product Category
	When user clicks on "Products" Tab
	And user clicks on "Add Product" icon
	And Get product details from "<TestCaseNo>"
	And user selects "OnBehalfOf"
	And user fills details of Product Category
	#And user uploads the "image" file
	And user clicks on the submit button
	And verify the added product message should display
	And search for product name "ProductName"
	And search for company "OnBehalfOf"
	Then verify added "ProductName" should display in the product table with proper details

	Examples:
	| TestCaseNo |
	| TC1 |
	
	@new
	Scenario Outline: Verify user can update the added Product
	When user clicks on "Products" Tab
	And Get product details from "<TestCaseNo>"
	When search for product name "ProductName"
	And search for company "OnBehalfOf"
	And user opens the Product details for "ProductName"
	And user clicks on the "Edit" icon
	And user modifies the data
	And user clicks on the submit button
	And verify the upadted product message should display
	Then user should be able to update the data in product profile page

	Examples:
	| TestCaseNo |
	| TC2 |
	
	@new
	Scenario: Verify the user can add a product by adding the template
	When user clicks on "Products" Tab
	And user clicks on "Import" icon
	And user clicks on Download Template button
	And verify file "products_template.xlsx" present in downloads.
	And user proceeds to next step to upload the file
	And user uploads the "products_template.xlsx" file
	And user clicks on the submit button
	Then the product should be added successfully message display
	
	@new
	Scenario: Verify the user can check the Product details based on Filter type
  When user clicks on "Products" Tab
  And user clicks on "Filters" icon
  And user selects FilterType as "Product Name" with condition "Contains" and value "TestProduct4"
  And user clicks on the submit button
  Then validate the Product details table "Product Name" column has a value "TestProduct4"
  And user clicks on the clear button
  And user selects FilterType as "Active" with condition "Contains" and value "Active"
  And user clicks on the submit button
  Then validate the Product details table "Active" column has a value "Active"
  
	
		