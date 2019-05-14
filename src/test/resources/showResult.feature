Feature: story 05 - show result
#Narrative:
#As a student
#I want to see the result for each tent and a total result
#In order to finish the test

#Acceptance Criteria:
#All tents are shown on the result page
#for each tent the achieved result is shown


Scenario: The result of each tent is shown
Given the student completed the last tent of the test
When the student finishes the test
Then the result of all the completed tents are shown

Scenario Outline: The total result of test is shown
Given the student completed the last tent of the test with a total <score>
When the student finishes the test
Then the right total <score> is shown as a progressbar
  Examples:
  |score |
  |70%   |
  |35%   |
  |12%   |

Scenario Outline:  score 0 for unanswered tests
  Given the unaswered tent <title>
  When the the student finished the test without answering a tent
  Then he can see the tent <title> with score 0

  Examples:
  | title           |
  | Problem Solving |
  | Perseverance    |



