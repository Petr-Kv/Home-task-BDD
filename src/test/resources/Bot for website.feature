Feature: Bot for klavogonki website

  Background: User is on the main page of the site
    Given Opening a website "https://klavogonki.ru/go?type=normal"

    Scenario: Bot starts the game
      When Let's start the game
      And We are waiting for the start of the game
      And Enter the highlighted word in a loop
      Then We fix that the game is over and there are more than 1000 symbols per minute