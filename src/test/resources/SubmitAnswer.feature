Feature: Story 04 - submit answer
  #Narrative:
  #As a student
  #I want to click a submit button for each tent
  #In order to submit these answers

  #Acceptance Criteria:
  #If there are more tents to answer, the next tent is shown
  #If there are no more tents to answer, the result is shown
  #The selected answers are stored
  #If no answer is selected, the user cannot submit

  Scenario: Next tent is shown if their are tents left
    Given the student answered all questions
    And there are more tents left to answers
    When he submits his answers
    Then the answers are stored
    And the next tent is shown

  Scenario: Results are shown if no tents left
    Given the student answered all questions
    And there is not a tent left to answer
    When he submits his answers
    Then the answers are stored
    And the total result is shown

Scenario: not all questions were answered
  Given a student has not answered all questions
  When he submits his answers
  Then all unanswered questions will get score 0







