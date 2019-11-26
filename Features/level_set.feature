Feature:  Level Set
	  As a gamer,
	  in order to enjoy this game,
          I need to play different levels
          with ad hoc designed environments.
          The level set includes:
          Different levels are playable,
          one for each planet of a given set
          of planets in the Centaurus constellation.
          
          Scenario: the game starts for the first time
		                Given that I am on the main menu
		                When I click on the "New Story" button
		                Then the game session starts 
		                And the map is generated
		                And the character is put in it
                    
          Scenario: the game starts
		                Given that I am on the main menu
		                When I click on the "Continue Story" button
		                Then the game session starts 
		                The checkpoint is loaded
                    		And the map of the last level played is generated
		                And the character is put in it
