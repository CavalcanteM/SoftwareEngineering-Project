Feature: options
        As a developer,
        in order to make tha game more customizable
        i want the possibility to choose between two sets of commands 
        
        Scenario: From the main main
            When the user is in the main menu
	    And click on the Options button 
	    Then he goes to the Options panel
	    And can change the buttons used in game
            
        Scenario: the player begins the game
            When the player starts a new run
	    Then he can choose between two configuration
            (one with WASD - movement and arrows for gravity; the other the inverse) 