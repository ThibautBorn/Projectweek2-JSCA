# ﻿Cheatsheet writing specifications
Try to find bugs before you actually write the code: uncover all kinds of assumptions, misunderstandings and gaps between you and your customer’s understanding of the desired result.
1. Doesn’t mention the user interface
Focus on the WHAT, not on the HOW. 


Too much UI references:
Scenario: Log in with email and password
Given a user is registered
When he enters his email address in the username field
And his password in the password field
And he clicks on the login button
Then the homepage is shown
	

Better:
Scenario: Log in with email and password
Given a user is registered
When he logs in with his email and password
Then he is granted access to the webshop
	

1. Avoid technical details
Your customer must understand the specifications.


Too technical:
Scenario: Background colour can be red or yellow
Given the css of the webshop is yellow.css
And the user clicks on the button to change the css
Then the css of the webshop is red.css
	

Better:
Scenario: Background colour can be red or yellow
Given the background colour of the webshop is yellow
And the user chooses to change the colour
Then the background colour of the webshop is red


1. Provide enough details
Too general:
Scenario: New product can be added for sale.
Given you want to start selling shoes
When you enter all required product details
Then shoes will be available to buy in the webshop
	

Better:
Scenario: New product can be added for sale.
Given you want to start selling shoes
When you register the shoes giving the product name and description
And giving the colour, the size and the unit price
Then shoes will be available to buy in the webshop
	

1. Use concrete examples
Too general:
Scenario: 10 % discount is given when bought for 50,00 euro.
Given the user has goods in his shopping basket
When he asks to calculate the price
Then the total amount is shown
And the discount is already deducted
	

Better:
Scenario: 10 % discount is given when bought for 50,00 euro.
Given the user has 2 sweaters of 26,00 in his shopping basket
When he asks to calculate the price
Then the total amount of 46,80 is shown
	________________


1. Provide enough examples (without overdoing it).
Provide examples for the happy path, edge cases, etc... (think of the testing design techniques: Equivalence Partitioning, Boundary Value Analysis, …)


Scenario: 10 % discount is given when bought for more than 50,00 euro.
Given the user has 6 T-shirts of 10,00 in his shopping basket
When he asks to calculate the price
Then the total amount of 54,00 is shown


Scenario: 10 % discount is given when bought for 50,00 euro.
Given the user has 5 T-shirts of 10,00 in his shopping basket
When he asks to calculate the price
Then the total amount of 45,00 is shown


Scenario: No discount is given when bought for less than 50,00 euro.
Given the user has 4 T-shirts of 10,00 in his shopping basket
When he asks to calculate the price
Then the total amount of 40,00 is shown
	



1. DRY
Use example tables.


Too much reading:
Scenario: 10 % discount is given when bought for more than 50,00 euro.
Given the user has 6 T-shirts of 10,00 in his shopping basket
When he asks to calculate the price
Then the total amount of 54,00 is shown


Scenario: 10 % discount is given when bought for 50,00 euro.
Given the user has 5 T-shirts of 10,00 in his shopping basket
When he asks to calculate the price
Then the total amount of 45,00 is shown


Scenario: No discount is given when bought for less than 50,00 euro.
Given the user has 4 T-shirts of 10,00 in his shopping basket
When he asks to calculate the price
Then the total amount of 40,00 is shown
	



Better:
Scenario: 10 % discount is given when bought for 50,00 euro.
Given the user has a <number> of T-shirts of 10,00 in his shopping basket
When he asks to calculate the price
Then the total amount <total> is shown


Examples:
number
	total
	6
	54
	5
	45
	4
	40
	

	

1. Write scenarios, not scripts
Focus on the WHAT, not on the HOW. 


Too long, too much focus on the how:
Scenario: Sign up with email and password
Given a user wants to register
When he enters his email address in the username field
And he enters his password in the password field
And the password is minimum 8 characters
And the password is maximum 15 characters
And the password contains at least 1 digit
And the password contains at least 1 uppercase character
And the password contains at least 1 lowercase character
And the password contains at least 1 special character
And he clicks on the register button
Then an email is sent to the user to confirm his email address
And an account is created
And the account is marked unverified
And the user can click on the link in the email
And then he is granted access to the webshop
And the account is marked verified
	________________




Better:
Scenario: Sign up with email and password
Given a user wants to register
When registers with  his email address and password
Then an email is sent to the user to confirm his email address
And an unverified account is created


Scenario: The email needs to be confirmed
Given an unverified account was created for the user
And he was asked to confirm his email
When he confirms his email
Then the account is marked verified


Scenario: The password must be valid
Given a user wants to register
When he chooses a <password> that breaks a <rule>
Then the password is refused


Examples:
password
	rule
	$h0rt
	minimum 8 characters
	ThisPassw0rdI$Amazing
	maximum 15 characters
	myAmazingPa$$ 
	at least 1 digit
	amazingpa$$4me
	contains at least 1 uppercase character 
	AMAZINGPA$$4ME
	contains at least 1 lowercase character 
	amazingPass4me
	contains at least 1 special character 
	 
	

1. The scenario title should tell what is different
Reading the scenario title should tell you what the what the scenario is about, what is different from other scenarios.