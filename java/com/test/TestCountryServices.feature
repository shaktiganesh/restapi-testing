Feature: Webservice Testing of countries
  
  Scenario: User calls web service to get all countries and validate that certain
    countries are present in it
    
     Given the user makes a call to the all country service
     When a user searches the countries by code
      | Fields   | Values |
      | country1 | US     |
      | country2 | DE     |
      | country3 | GB     |
  
    Then the status code is Http Ok

  