Feature: Story 08 - see student results
#Narrative:
#As a teacher
#I want to see the questions of the students of my class
#In order to have an overview of my students test

#Acceptance Criteria:
#If the teacher wants to selects to view he results, he gets to see the results from his students


  Scenario: View class score
    Given the teacher is logged in
    When he selects the option to view his class
    Then a list of results from his students is shown


  Scenario: No class
    Given the teacher has no classes
    When he selects the option to view his class
    Then an error message is shown