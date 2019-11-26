Feature:  Challenging increases
          As a gamer,
	  in order to enjoy this game,
          I need to play a challenging game.
          I want that the challenging increases at each level.
          The challenging increasing provide:
          - Hindrance putted in random positions chosen from a set of pre-defined positions;
          - The shots becomes faster;
          - The number of hindrances increases.
            
          Scenario: the game starts for the first time
                    Given that I am on the main menu
		                When I click on the "New Story" button
                    Then the game session starts
                    And the map is generated
                    And the hindrances are put in it in pre-defined positions
                    And the character is put in it.
                    
          Scenario: the game starts
                    Given that I am on the main menu
		                When I click on the "Continue Story" button
                    Then the game session starts
                    And the map is generated
                    And some hindrances are put in it in pre-defined positions
                    and others in in random positions chosen from a set of pre-defined positions
                    And the character is put in it.
