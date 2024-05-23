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

    #Then verify added "ProductName" should display in the product table with proper details
    Examples: 
      | TestCaseNo |
      | TC1        |

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
      | TC2        |

  @TC3
  Scenario: Verify the user can add a product by adding the template
    When user clicks on "Products" Tab
    And user clicks on "Import" icon
    And user clicks on Download Template button
    And verify file "products_template.xlsx" present in downloads.
    And user proceeds to next step to upload the file
    And user uploads the "products_template.xlsx" file
    And user clicks on the submit button
    Then the product should be added successfully message display

   @new @Tc4
  Scenario: Verify the user can check the Product details based on Filter type
    When user clicks on the "Products" tab
    And user clicks on the "Filter" icon
    And user selects the "FilterType" as "Category" with condition "contains" and value "filterValue"
    And user clears the search filter
    And user modifies the search input to "modifiedValue"
    And user clicks the "Submit" button
    Then Product details matching the filter criteria should be displayed
    Examples: 
      | TestCaseNo |
      | TC4       |
    

   @new @Tc5
    Scenario: Verify the user can Link Materials for the Product
		  When user clicks on "Products" Tab
		  And Get product details from "<TestCaseNo>"
	    When search for product name "ProductName"
	    And search for company "OnBehalfOf"
	    And user opens the Product details for "ProductName"
	    When user clicks "Material" tab 
	    And  user clicks on "Export Data" button
	    Then verify file "Materials.xlsx" present in downloads.
	    When user clicks on "Link Material" Tab 
	    And user clicks on "Add Item" icon
	    And user clicks on the submit button
	    Then the materials should be added successfully
    Examples: 
      | TestCaseNo |
      | TC5        |
  
  
  
  
  
  #Repeat @Tc6
  Scenario: Verify the user can delete the added Product
    When user clicks on "Products" Tab
    And user clicks on "Add Product" icon
    And Get product details from "<TestCaseNo>"
    And user selects "OnBehalfOf"
    And user fills details of Product Category
    #And user uploads the "image" file
    And user clicks on the submit button
    And verify the added product message should display
    And search for product name "ProductName"
    When user clicks on delete icon
    #And user clicks on confirm button
    #Then the product should be deleted
    
    Examples: 
      | TestCaseNo |
      | TC5       |
