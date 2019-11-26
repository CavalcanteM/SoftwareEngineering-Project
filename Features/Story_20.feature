Feature:  storytelling
          As a gamer,
          in order to make the game more interesting
          I want a comic to explain me the character's story
        
          Scenario: the game starts for the first time
                    Given that I am on the main menu
		                When I click on the "New Story" button
                    Then the game session starts
                    And a comic that explains the character's story is shown.
