Feature:  story
          As a gamer,
          in order to make the game more exciting,
          I want that the game has a story mode.
          The story focus on a robot that 
          has to collect pieces of its girlfriend 
          scattered around the space, the final one is her CPU.
          
          Scenario: the game starts for the first time
                    Given that I am on the main menu
		                When I click on the "New Story" button
                    Then the game session starts
                    And a comic that explains the character's story is shown.
	
	        Scenario: the game starts
		                Given that I am on the main menu
  		              When I click on "Resume Story" button
                    Then the game session starts
                    When I finish a level
                    Then a comic show me the transition to the next level.
