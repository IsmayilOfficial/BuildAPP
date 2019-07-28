Feature: reject

  @single
  Scenario: rejecting the plant hire request
    Given I open firefox browser for reject PHR
    When I navigate to http://localhost:8080/#/about page for reject
    And I click on reject button
    Then Quit


