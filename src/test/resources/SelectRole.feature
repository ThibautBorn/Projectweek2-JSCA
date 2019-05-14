Feature: Story 06 - Select a role

  #Narrative
  #As a visitor
  #i want to select a role
  #in order to gain appropriate privilegies to use the site

  #Acceptance Criteria:
  #All the available roles are shown on the home screen
  #When a user selects a role, they can start to navigate the site with the right privilegies

  Scenario: register as a admin
    Given A visitor on the app
    When the visitor selects the admin role
    Then he is logged in as a admin

  Scenario Outline: register as a student
    Given a visitor on the app
    When the  visitor selects the student role from grade <grade>
    Then he is logged in as a student from <grade>

    Examples:
    | grade |
    | 1 |
    | 2 |
    | 3 |

   Scenario: register as teacher from grade
    Given a visitor on the app
    When the visitor registers as a student from grade
    Then the he is logged in as teacher from that grade
