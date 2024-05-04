*****************************************************************************************************
File name: NimGameTest.java                                                  Authors: Anh, Emely, Thy

Purpose: Represents the main entry point for the Nim game. It handles user interaction, game setup, 
and gameplay flow.

Functionality:

- Main Method: Initializes the game loop and controls its flow. It displays the welcome prompt, main 
menu options, and executes the selected option.

- Game Loop: Continuously prompts the user to choose an option until the user chooses to quit. It includes 
options for starting a new game, viewing the leaderboard, and quitting the game.

- New Game Option: Allows the user to start a new game, create players, select the first player, and 
initiate the game. It updates player scores and the leaderboard based on game results.

- Leaderboard Option: Displays the leaderboard menu, allowing users to view all players or top players. 
It provides options to specify the number of top players to display.

- Quit Option: Terminates the game loop and exits the program.

- End Prompt: Displays a thank you message and credits for the project upon quitting the game.

Features Beyond Project 1 Expectations:

1. Dynamic Player Creation: Allows users to create new players and select the first player for each game,
enhancing customization and player experience.

2. Leaderboard Functionality: Provides options to view all players or specify the number of top players 
to display on the leaderboard, offering insights into player rankings.

3. Score Tracking: Tracks player scores across multiple games and updates the leaderboard accordingly, 
adding depth to the gameplay and fostering competition among players.

4. User Input Validation: Implements robust input validation to ensure that users provide valid input when
selecting game options, enhancing the reliability and user-friendliness of the application.

5. Interactive User Experience: Offers an interactive and engaging user experience with clear prompts, 
game rules, and menu options, enhancing overall gameplay enjoyment.

*****************************************************************************************************
File name: Game.java                                                         Authors: Anh, Emely, Thy

Purpose: Manages the overall gameplay flow of the Nim game. It orchestrates player interactions, 
game setup, and gameplay progression.

Functionality:

- Constructor: Initializes a new instance of the Game class.
- startGame(): Initiates the game by setting the starting player.
- createNewPlayers(): Allows the creation of new players, including human and AI players.
- signUp(): Registers a new human player by prompting for a name and assigning a unique player ID.
- logIn(): Logs in an existing player by prompting for a name and player ID.
- play(): Initiates the gameplay loop, where players take turns making moves.
- lookForPlayer(): Searches for a player by name.
- nextTurn(): Determines the next player's turn.
- displayGameInfo(): Displays game information such as player names, first turn, and heap size.
- findIndex(): Searches for a player's index in the players array.

Features Beyond Project 1 Expectations:

1. Player Authentication: Implemented sign-up and login functionality for human players.
2. Game Logging: Logged each turn's details, including the player's name, removed objects, and heap 
index.
3. Flexible Player Creation: Supports the creation of human and AI players, providing flexibility in 
gameplay options.

*****************************************************************************************************
File name: Leaderboard.java                                                  Authors: Anh, Emely, Thy

Purpose: Manages the leaderboard, which stores information about players' scores and rankings. 
It includes methods for adding scores, retrieving high scores, and updating rankings.

Functionality:

- Constructor: Initializes a new leaderboard with an empty list of players.
- updatePlayer(Player player): Updates the leaderboard with the given player's score.
- getTopPlayer(int numPlayers): Retrieves the top players based on their scores.
- sortHumanPlayersDescending(ArrayList<Player> HumanPlayers): Sorts the list of human players in 
descending order.
- removePlayersAfterIndex(ArrayList<Player> HumanPlayers, int index): Removes players from the list.
- display(int numHumanPlayers): Displays the leaderboard with the top players and their ranks.
- lookForOldPlayer(String name): Checks if a player with the given name exists in the leaderboard.
- lookForOldGameID(int id): Checks if a game with the given ID exists in the leaderboard.

Features Beyond Project 1 Expectations:

1. Score Management: Tracks and updates player scores during gameplay.
2. Ranking System: Ranks players based on their scores, highlighting top performers.
3. Player Identification: Ensures unique player identification by names and IDs.
4. User Interface: Provides a clear interface for viewing the leaderboard, enhancing user experience.

*****************************************************************************************************
File name: Move.java                                                                  Author: Anh Bui

Purpose: Provides a structured way to represent and manipulate moves within the Nim game.

Functionality:

- Constructor: Initializes a Move object with a specified heap index and number of objects removed.
- getHeapIndex(): Getter method to retrieve the heap index of the move.
- setHeapIndex(int heapIndex): Setter method to set the heap index of the move.
- getNumObjectsRemoved(): Getter method to retrieve the number of objects removed in the move.
- setNumObjectsRemoved(int numObjectsRemoved): Setter method to set the number of objects removed in
the move.

*****************************************************************************************************
File name: Score.java                                                                 Author: Anh Bui

Purpose: Encapsulates information about a player's score in a game.

Functionality:

- Constructors: Initializes a Score object with the player's name, score value, and optionally a game ID.
- Getter and Setter Methods: Accesses and modifies player information such as name, score, and game ID.
- toString() Method: Returns a formatted string representation of the Score object.

*****************************************************************************************************
File name: Board.java                                                  Author: Emely Cortez Hernandez

Purpose: Represents the game board, which consists of heaps or piles of objects. 
Includes methods for board initialization, display, object removal, and game over detection.

Functionality:

- Constructor: Initializes a new board with the specified number of objects per heap.
- display(): Displays the current state of the board, showing the number of objects in each heap.
- removeObjects(int heapIndex, int numberOfObjects): Removes objects from a heap.
- isGameOver(): Checks if the game is over.
- getHeaps(): Retrieves the ArrayList containing the number of objects in each heap.

Features Beyond Project 1 Expectations:

1. Dynamic Display: Adjusts the visual representation of heaps based on the number of objects, 
enhancing clarity and user experience.
2. Error Handling: Validates moves to ensure they conform to game rules, providing clear error 
messages for invalid moves.
3. Game Over Detection: Efficiently detects game over conditions by checking heap states, 
facilitating game termination.
4. Getter Method: Provides access to heap information, allowing other classes to retrieve and 
analyze board state.

*****************************************************************************************************
  File name: Player.java                                                       Author: Uyen Thy Duong

Purpose: Represents a player in the Nim game. Each player has a name, a unique identifier, and a score.

Functionality:

- Constructor: Initializes a Player object with the specified name, generating a random player ID and 
setting the score to zero.
- Getter and Setter methods: Access and modify player attributes such as name, player ID, and score.
- win(int game_id): Updates player score upon winning a game.
- loose(int game_id): Updates player score upon losing a game.
- makeMove(Board board): Abstract method for making a move.

Explanation:
The Player class encapsulates information about a player in the Nim game. It provides methods for 
managing player data and interactions with the game board. Each player instance can participate in 
multiple games and has a score associated with their performance.

Features Beyond Project 1 Expectations:
- Flexible player system supporting both human and AI-controlled players.
- Game logic for updating player scores based on game outcomes, enhancing gameplay experience.

*****************************************************************************************************
File name: HumanPlayer.java                                                    Author: Uyen Thy Duong

Purpose: Represents a human player in the Nim game. Facilitates interaction with the player to input 
moves during gameplay.

Functionality:

- Constructor: Initializes a new HumanPlayer instance with the provided player name.
- makeMove(Board board): Allows human players to input moves during gameplay.

Features Beyond Project 1 Expectations:

- User Input Validation: Implements robust input validation to ensure the player's moves adhere to 
the game rules.
- Interactive Gameplay: Enables interactive gameplay by providing prompts and instructions for the
player to input their moves.
- Exception Handling: Handles various edge cases and invalid input scenarios gracefully, guiding the
player to provide correct input.

*****************************************************************************************************
File name: AIPlayer.java                                                       Author: Uyen Thy Duong

Purpose: Represents an AI-controlled player in the Nim game. Implements AI logic to make optimal moves.

Functionality:

- Constructor: Initializes an AI player with default attributes.
- makeMove(Board board): Calculates optimal moves based on the current game state.

Explanation:
The AIPlayer class implements advanced AI logic based on Nim sum calculations. It strategically selects 
moves to force opponents into losing positions, enhancing the gameplay experience with dynamic and 
challenging gameplay.

Features Beyond Project 1 Expectations:
- Advanced AI logic based on Nim sum calculations.
- Strategically selects moves to force opponents into losing positions.

*****************************************************************************************************
