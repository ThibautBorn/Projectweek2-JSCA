Feature: Story 01 - Overview Tents

  # Narrative:
  # As a student
  # I want to open the app
  # In order see an overview of all the tents

  # Acceptance Criteria:
  # All tents are shown on the overview
  # For each tent name and the description is shown

  Scenario: Warning if no tents are registered
    Given no tents are registered in the app
    When the student opens the app
    Then an error message is shown

  Scenario Outline: The name and description of each tent is shown for students/teachers
    Given the tent with the title <title> is registered in the app
    When the student/teacher opens the app
    Then he can see the tent <title> with the description <description>

    Examples:
    | title           | description |
    | Problem Solving | How do you deal with unexpected situations |
    | Perseverance    | How do you react when things get though |
    | Team Spirit     | How do you function in a team |


  Scenario: The name and description of each tent is shown for students and teachers
    Given the 10 tents are registered in the system
    When the  student/teacher opens the app
    Then he can see the all ten tents in the overview


Scenario: all the tents have an add question option for admin
    Given the 10 tents are registered in the system
    When the admin opens the app
    Then he can see the all ten tents in the overview and each tent has an add question option


Scenario: Warning if no tents are registered
Given no tents are registered in the app
When the student opens the app
Then an error message is shown
