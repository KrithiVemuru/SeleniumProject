@PetStore
Feature: Automation Assignment
    
    @Type=UI
  Scenario: Dynamic Table
    When User clicks on "Dynamic Table" link
    And User reads the Dynamic Table Data
    Then Verify Dynamic Table Data

  @Type=UI
  Scenario: MouseOver
    When User clicks on "Mouse Over" link
    And User clicks on "Click me" link
    And User clicks on "Click me" link
    Then Link should be clicked "2" times
    
  @Type=UI
  @Data=AssignmentTestData.json
  Scenario: Ovelapped Element
    When User clicks on "Overlapped Element" link
    And User enters "Customer Details" data
    Then Details should be entered successfully
    
     @Type=API
    @Data=AddNewPet.json
  Scenario: Add New Pet to the Store and Create an Order for the added Pet
    When User Adds a new Pet "New Pet" to the Store via API
    Then Pet should be added successfully
    When User get the Pet by Status "available"
    Then Pet data should be displayed successfully with status "available"
    When User places an Order "Place Order" for the Pet
    Then Pet Order should be placed successfully
