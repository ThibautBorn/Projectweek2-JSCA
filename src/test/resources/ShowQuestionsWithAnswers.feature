Feature: Story 02 - Show questions with their Answers

  #Narrative:
  #As a student
  #I want to see all answers for each question per tent
  #In order to choose an appropriate answer

  #Acceptance Criteria:
  #For every tent, the following is shown:
  #The tent the questions belong to
  #All the questions from that tent
  #The possible answers for each question
  #The possible answers are shown in a random order

  Scenario outline: Show questions with their answers per tent
    Given there are questions for <tent> and the user has started a test
    When the a tent is openened
    Then the questions are shown from that <tent>
    And the possible answers are shown in a certain order

    Examples:
    | tent |

