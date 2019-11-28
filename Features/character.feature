Feature: character
         As a gamer,
	 in order to enjoy this game,
         I want to play a retro game in which the player moves in a 2D space.
                  
         Scenario: the game starts
		   Given that I am on the main menu
		   When I click on the "New Story" button
                   or the "Continue Story" button
		   Then the game session starts
                   And the character is put in 2D world.
