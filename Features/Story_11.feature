Feature: main menu
        As a gamer,
        in order to start a new game or continue my previous run
        i want a main manu.
        Main menu is composed by:
        - the game title
        - a New Story button
        - a Continue Story button
        - an exit button (X)
        - a credit button
        - a volume button
        
        Scenario: I open the game
            When the game is opened
            Then the main menu is displayed.
            
        Scenario: the player wants to exit the game
            Given that I am playing
            When I want to exit
            Then the main menu is displayed.
