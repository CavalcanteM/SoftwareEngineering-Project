Feature: winning
        As a gamer,
        in order to pass a level
        I want to collect N items
        
        Scenario: I collect the nth item
            When I collect the nth item
            Then a winning screen is displayed
            And a checkpoint is set
