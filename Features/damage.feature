Feature:  Damage
          As a gamer,
          In order to enjoy this game,
          I need to play a challenging game.
          I want that the hindrances that hit the character
          damage it.
          The hindrances that hit the character
          damage it at most of one Heart Point.
          
          Scenario: the game starts
                    Given that I am on the main menu
		                When I click on the "New Story" button
                    or on the "Continue Story" button
                    Then the game session starts
                    And the map is generated
                    And some hindrances are put in it
                    And the character is put in it.
                    If the charater is hit by a hindrance
                    il will lose an amount of Life Points,
                    at most one Heart Point.
