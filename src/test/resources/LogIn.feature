Feature: Story 06 - Log in

  #Narrative
  #As a visitor
  #i want to select a role
  #in order to gain appropriate privilegies to use the site

  #Acceptance Criteria:
  #All the available roles are shown on the home screen
  #When a user selects a role, they can start to navigate the site with the right privilegies

  Scenario: no Username given
    Given A loginform with no username
    When a user attempts to login
    Then an error message is shown
    And the login page is shown again

  Scenario: no password given
    Given A loginform with no password
    When a user attempts to login
    Then an error message is shown
    And the login page is shown again

  Scenario: wrong password given
    Given A loginform filled in login form
    When a user attempts to login with the wrong password
    Then an error message is shown
    And the login page is shown again

  Scenario: unknown username given
    Given A loginform filled in login form
    When a user attempts to login with the wrong password
    Then an error message is shown
    And the login page is shown again

  Scenario: Student login
    Given A correct filled in student login form
    When attempts to login
    Then he is logged in as student from his grade.

  Scenario: teacher login
    Given A correct filled in teacher login form
    When attempts to login
    Then he is logged in as teacher.

  Scenario: admin login
    Given A correct filled in teacher login form
    When attempts to login
    Then he is logged in as teacher.