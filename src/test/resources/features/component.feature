Feature: component Categories

  Background: 
    Given the user is on the T0S Application home page

  @Tc10 @new
  Scenario: Verify user can add a component 
    When user clicks on "Components" Tab
    And Get component details from "<TestCaseNo>"
    And user selects "OnBehalfOf"
    And user fills details of Component Category
    And user uploads the "image" file
    And user clicks on the submit button
    And verify the added component message should display
    And search for component name "Name"
    And search for company "OnBehalfOf"
    Then verify added "Component" should display in the components table with proper details
      Examples: 
      | TestCaseNo |
      | TC10       |
      
    Scenario Outline: Verify that user can add Breakdown for the component
	    When user clicks on "Components" Tab
	    And Get component details from "<TestCaseNo>"
	    And user selects "OnBehalfOf"
	    And user fills details of Component Category
	    And user uploads the "image" file
	    And user clicks on the submit button
	    And verify the added component message should display
	    And search for component name "Name"
	    And search for company "OnBehalfOf"
	    And user opens the Component details for "ComponentName" 
	    And user clicks on "Breakdown" Tab 
	    And user  clicks on "AddNew" to adds new breakdown
	    Then user should be able to add breakdown with details
	      Examples: 
      | TestCaseNo |
      | TC11       |
	    
	    
	   Scenario Outline: Verify that user can Edit Breakdown for the component
	    When user clicks on "Components" Tab
	    And Get component details from "<TestCaseNo>"
	    And user selects "OnBehalfOf"
	    And user fills details of Component Category
	    And user uploads the "image" file
	    And user clicks on the submit button
	    And verify the added component message should display
	    And search for component name "Name"
	    And search for company "OnBehalfOf"
	    And user opens the Component details for "ComponentName" 
	    And user clicks on "Breakdown" Tab 
	    When user  clicks on "Delete" Icon to Delete component
	    Then user should be able to delete breakdown
      	 Examples: 
      | TestCaseNo |
      | TC12       |
      
   #Repeat @Tc13
  Scenario Outline: Verify user can update the added Component
    When user clicks on "components" Tab
    And Get component details from "<TestCaseNo>"
    When search for component name "ComponentName"
    And  search for company "OnBehalfOf"
    And user opens the Component details for "ComponentName"
    And user clicks on the "Edit" icon
    And user modifies the data
    And user clicks on the submit button
    And verify the upadted component message should display
    Then user should be able to update the data in component profile page
      
      
  
   
  #Repeat @TC13
  Scenario Outline: Verify the user can add a Component by adding the template
    When user clicks on "Components" Tab
    And user clicks on "Import" icon
    And user clicks on Download Template button
    And verify file "materials_template.xlsx" present in downloads.
    And user proceeds to next step to upload the file
    And user uploads the "materials_template.xlsx" file
    And user clicks on the submit button
    Then the component should be added successfully message display
    
    #Repeat @Tc14 
   Scenario Outline: Verify the user can Export Components Table
      When user clicks on "Components" Tab
      And user clicks on "Export" icon
      And verify file "materials_template.xlsx" present in downloads.
      
     
     
     
