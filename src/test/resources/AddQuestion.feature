Feature: Story 07 - add Question
#Narrative:
#As a admin
#I want to add a Question
#In order to have students answer these questions

#Acceptance Criteria:
#If the admin makes a question for a tent and grade, that question is added to the tent
#New questions are stored
#invalid questions are rejected

  Scenario: Question is added
    Given the admin filled in all fields
    When he submits his question
    Then the question is stored
    And the answers are stored

  Scenario: warning if no grade is filled in
    Given the admin filled in all fields except grade
    When he submits his question
    Then the message "Please fill in grade for the question" is shown

  Scenario: warning if no question is filled in
    Given the admin filled in all fields except grade
    When he submits his question
    Then the message "Please fill in string for the question" is shown

  Scenario: warning if no tent is filled in
    Given the admin filled in all fields except grade
    When he submits his question
    Then the message "Please fill in test for the question" is shown

  Scenario Outline: The right paramaters are saved
     Given a question with a <question>,<grade> and <tent>
     And answers with value <antwoord 1>,<point 1>, <antwoord 2>, <point 2>, <antwoord 3>, <point 3>
     When he submits the question
     Then the question is stored with <question>,<grade>,<tent> and questions value <antwoord 1>,<point 1>, <antwoord 2>, <point 2>, <antwoord 3>, <point 3>

    Examples:
     | question | grade | tent | antwoord 1 | point 1 | antwoord 2 | point 2 | antwoord 3 | point 3
     |"kan je goed samenwerken" |  1 | "samenwerken"| "perfect" |6 | "zeer goed" | 4 | "matig" | 2
     |"kan je met het internet omgaan" |  2 | "ICT" | "perfect" |6 | "zeer goed" | 4 | "matig" | 2

