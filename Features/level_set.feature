Feature:  Level Set
	  As a customer,
	  in order to improve variety and longevity of the game,
          I want a set of different levels 
          with ad hoc designed environments.
          The level set includes:
          Different playable levels
          located on different planets in the Centaurus constellation.
          
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
