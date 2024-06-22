Feature: Document Rulesets Categories

  Background: 
    Given the user is on the T0S Application home page

  @newlyAddedOnJun19
  Scenario: Verify user can add a Ruleset 
    When user clicks on "Document Rulesets" Tab
    And user clicks on the "Add Ruleset" icon
    And Get DocumentRulesets details from "<TestCaseNo>"
    And user selects "OnBehalfOf"
    And user fills Required details of ruleset
    And user fills Optional Ruleset details
    And user clicks on the submit button
    And verify the added Ruleset message should display
    
      Examples: 
      | TestCaseNo |
      | TC1       |
      
    