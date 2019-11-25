Feature:  Hindrance
          As a gamer,
	        in order to enjoy this game,
          I need to play a challenging game.
          I want that hindrances are generated
          and placed in the map.
          The set of hindrances includes:
          - Lasers
          - Flame throwers
          - Three-shot lasers
          
          Scenario: the game starts
                    Given that I am on the main menu
		                When I click on the "New Story" button
                    or the "Continue Story" buttom
                    Then the game session starts
                    And the map is generated
                    And the hindrances are put in it in random positions
                    chosen from a set of pre-defined positions
                    And the character is put in it
