*****************************************************************************************************
File name: NimGameTest.java                                         Authors: Anh, Emely, Thy

Purpose: The NimGameTest class serves as the main entry point for the Nim game. 
It handles user interaction, game setup, and gameplay flow.

Functionality:

* Main Method:
The main method initializes the game and controls its flow.
It creates a Scanner object to read user input.
The main game loop continues until the user chooses to quit.

* Game Loop:
Displays the welcome prompt and game rules.
Presents the main menu options: New Game, Leaderboard, and Quit.
Validates user input and executes the corresponding option.

* New Game Option:
Starts a new game by creating an instance of the Game class.
Creates players and allows the user to choose the first player.
Initiates the game and handles gameplay.
Updates player scores and the leaderboard based on game results.

* Leaderboard Option:
Displays the leaderboard menu, allowing users to view all players or top players.
Provides options to view all players or specify the number of top players to display.
Quit Option:
Exits the game loop and terminates the program.

* End Prompt:
Displays a thank you message and credits for the project.