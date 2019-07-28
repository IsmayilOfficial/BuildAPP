Feature: query

  @single
  Scenario: (1) querying RentIt's plant catalog ,(2) selecting one plant for creating a Plant hire request
    Given I open firefox browser
    When I navigate to http://localhost:8080/#/ page
    And I provide name as "mini" and startDate as "23.04.2019" and endDate "30.04.2019"
    And I click on submit button
    And I click on Select plant button
    And I click on Create Plant hire request button

