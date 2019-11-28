Feature: upgrades
        As a gamer,
        in order to have fun
	i want that some upgrades spawn during the run
	The upgrades consist in:
	- Shield
	- Speed Up
	- Life (+1 Heart)
        
        Scenario: During the level
            When the game is on
            Then at certain moments the upgrade spawn
		in the map
	    And the player can collect them
	    Then the player can decide when to activate the power 
		depending on the situation
