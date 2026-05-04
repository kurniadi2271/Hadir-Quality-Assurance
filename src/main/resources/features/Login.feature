@SIT @Login
# login.feature
Feature: Login Page
  As an application user,
  I want to be able to login, logout, and validate the form,
  So that I can access the system securely.

  Background:
    Given User is on the login page

  # TC_LOG_001 - Login Valid (Positive)
  Scenario: TC_LOG_001 - Valid login with correct credentials
    When User enters valid email and password
    And The user clicks the login button
    Then The user is successfully redirected to the dashboard
    
  # TC_LOG_002 - Logout Function
  Scenario: TC_LOG_002 - Logout successfully ends the session
    Given User is already logged in to the Dashboard
    When User clicks the profile name in the top right corner
    And User clicks the Logout button
    And User is redirected back to the login page

  # TC_LOG_003 - Login Invalid Password
  Scenario: TC_LOG_003 - Login fails due to incorrect password
    When User enters valid email and invalid password
    And The user clicks the login button
    Then An error message is displayed

  # TC_LOG_004 - Login Invalid Email
  Scenario: TC_LOG_004 - Login fails due to unregistered email
    When User enters invalid email and valid password
    And The user clicks the login button
    Then An error message 'Akun tidak ditemukan' is displayed

  # TC_LOG_005 - Empty Field Validation
  Scenario: TC_LOG_005 - Validation message appears when fields are empty
    When User leaves email and password fields empty
    And The user clicks the login button
    Then An error message 'Akun tidak ditemukan' is displayed

  # TC_LOG_006 - Email Format Check
  Scenario: TC_LOG_006 - Validation appears for email without '@' character
    When User enters invalid format email without '@' character
    And The user clicks the login button
    Then An invalid email format validation message is displayed

  # TC_LOG_007 - Password visible toggle check
  Scenario: TC_LOG_007 - Password visibility toggle works correctly
    When User enters a password in the password field
    And User clicks the password visibility toggle icon
    Then The password should become visible

    
 

  
