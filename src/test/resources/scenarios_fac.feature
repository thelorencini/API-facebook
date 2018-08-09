Feature: Test API Facebook, Authentication, post and alteration

  Scenario: The user must perform the authentication on facebook.
    Given The user who has the facebook authentication token
    When The user will made a request to "https://graph.facebook.com/me"
    And The user sent a get request with his token
    Then The response code of system should be 200

  Scenario: The user must post a message on his on page
      Given The user who has the facebook authentication token 
      When The user will made a request to "https://graph.facebook.com/me/feed"
      And User will pass the body message "TESTEAPI123"
      And The user sent a post request 
      Then The response code of system should be 200
   And The ID of his post will be save

  Scenario: The user needs make a change in his post
    Given The user who has the facebook authentication token
    When The user will made a request to "https://graph.facebook.com/" 
    And Passing the body message "123APITEST"
    And The user sent a post request 
 Then The response code of system should be 200