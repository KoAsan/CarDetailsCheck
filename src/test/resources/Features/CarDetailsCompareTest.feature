Feature: Compare car details with given output file
  I read the given input file: car_input.txt and extract vehichle registration numbers
  Perform the registration number check and compare details with output file
  

  Scenario: Compare car valuation report
    Given I have all car registration numbers from the text file
    Then I perform car details search for each car registration number and compare details with output file
