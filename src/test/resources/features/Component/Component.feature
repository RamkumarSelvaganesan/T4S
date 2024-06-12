Feature: Component Categories

  Background: 
    Given the user is on the T0S Application home page

  @Tc10 @new
  Scenario: Verify user can add a component 
    When user clicks on "Components" Tab
    And user clicks on the "Add New" icon
    And Get component details from "<TestCaseNo>"
    And user selects "OnBehalfOf"
    And user fills details of Component Category
    #And user uploads the "image" file
    And user clicks on the submit button
    And verify the added component message should display
    And search for component name "ComponentName"
    And search for company "OnBehalfOf"
    Then verify added "ComponentName" should display in the components table with proper details
      Examples: 
      | TestCaseNo |
      | TC1       |
      
    @new @component
    Scenario Outline: Verify that user can add Breakdown for the component
	  When user clicks on "Components" Tab
    And user clicks on the "Add New" icon
    And Get component details from "<TestCaseNo>"
    And user selects "OnBehalfOf"
    And user fills details of Component Category
    #And user uploads the "image" file
    And user clicks on the submit button
    And verify the added component message should display
    And search for component name "ComponentName"
    And search for company "OnBehalfOf"
	  And user opens the Component details for "ComponentName" 
	  And user clicks on the "Breakdown" icon
	  And user clicks on the "Add New" icon
	  And user fills details of Breakdown
	  And user clicks on the submit button
	  And verify the update component message should display
	  Then user should be able to add breakdown with details
	      Examples: 
      | TestCaseNo |
      | TC2       |
	    
	    
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
      
 
     
     
     
