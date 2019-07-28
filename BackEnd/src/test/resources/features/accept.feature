Feature: query

  @single
  Scenario: accepting the plant hire request
    Given I open firefox browser for accept PHR
    When I navigate to http://localhost:8080/#/about page
    And I click on accept button
    Then Quit


