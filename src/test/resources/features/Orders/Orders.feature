Feature: Purchase Orders categories

  Background: 
    Given the user is on the T0S Application home page

  @newlyAddedOnJun19
  Scenario: Verify user can add a purchase Order 
    When user clicks on "Orders" Tab
    And user clicks on the "Purchase Orders" icon
    And user clicks on the "Add PO" icon
    And Get Orders details from "<TestCaseNo>"
    And user selects "OnBehalfOf"
    And user fills Basic information of PO
    And user fills Product details of PO
    And user fills Document details of PO
    And user clicks on the submit button
    And verify the added purchase Order message should display
    
      Examples: 
      | TestCaseNo |
      | TC1       |
      
    