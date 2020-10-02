/*
 * @author Iffat Nusaiba Mahmood
 * <p> GameController
 * <p> Project 3
 * <p> 
 */
 
//
// The rules to the dice game Pig
//
// Number of Players: 2 + 
// Game Duration: 30 mins
// Players Aged: 6 +
//
// You will need: 2 dice and paper to score on.
//
// To Play: The players take turns to roll both dice, 
// they can roll as many times as they want in one turn.
//
// A player scores the sum of the two dice thrown and 
// gradually reaches a higher score as they continue to roll.
//
// If a single number 1 is thrown on either die, the score 
// for that whole turn is lost. However a double 1 counts as 25.
// The first player to 100 wins unless a player scores more 
// subsequently in the same round. This means that everyone in 
// the game must have the same number of turns.
//

import java.util.Scanner;

//Class manages flow of 2-player game play.
public class GameController
{

	// central method to start and manage game play
	public void play()
	{
        //new scanner to accept user input.
		Scanner kb = new Scanner(System.in);
        
		//Variable to hold user entered score goal.
		int targetScore;
        
        //Two players; two instances of PigDice
        PigDice P1 = new PigDice();
    	PigDice P2 = new PigDice();

    	//Do while loop for new game (y/n)
    	do
    	{
    		targetScore = getInitialMax (kb);
    		
        	//do while loop for player turns and game rounds (y/n)
    		do
        	{
        		System.out.println("\nPLAYER 1");
            	takeTurn (kb, P1);
            	
            	System.out.println("\nPLAYER 2");
            	takeTurn (kb, P2);
            	
            	System.out.println("\nPlayer 1: " + P1.currentTotal() + " -- Player 2: " + P2.currentTotal());
        	}
         	while(P1.currentTotal() < targetScore && P2.currentTotal() < targetScore);
        	
    		//if else statements for game results and conclusion
        	if (P1.currentTotal() == P2.currentTotal() && P1.currentTotal() >= targetScore && P2.currentTotal() >= targetScore)
        	{
        		System.out.println("\nThe result is a tie.");
        	}
        	else if (P1.currentTotal() >= targetScore && P2.currentTotal() < targetScore)
        	{
        		System.out.println("\nPlayer 1 wins!");
        	}
        	else if (P2.currentTotal() >= targetScore && P1.currentTotal() < targetScore)
        	{
        		System.out.println("\nPlayer 2 wins!");
        	}
        	else if (P1.currentTotal() > P2.currentTotal())
        	{
        		System.out.println("\nPlayer 1 wins!");
        	}
        	else
        	{
        		System.out.println("\nPlayer 2 wins!");
        	}
        	
        	//Clears scores for new game.
        	P1.clear();
        	P2.clear();
        	System.out.println("\nWould you like to play again? Respond (Y/N) only.");	
    	}
    	while (yesResponse(kb));
    	
    	//End game message
    	System.out.println("Goodbye.");
    	
	}

	//
	// Returns the initial max score (loops until a value between 1 <= score <= 100 is entered) 
	//
	private int getInitialMax(Scanner kb)
	{
		int score;
        do
        {
        	System.out.println("What score would you like to play? (100 max)");
        	score = kb.nextInt();        	
        }
        while (score < 1 || score > 100);
        kb.nextLine();
        return score;
	}
	
	//
	// method for managing a single session of rolling dice
	//
	private void takeTurn(Scanner kb, PigDice pd)
	{
		boolean keepRolling = true;

		do
		{
			// Roll the dice
			pd.rollDice();

			// Report the result
			System.out.println(pd.lastRoll() + " scored " + pd.evaluate() + " points.");

			// Did the player pig out?
			if (pd.piggedOut() == true)
			{	
				System.out.println("You pigged out this turn.");
			}
			else
			{
				//
				// Roll again; see if the user wants to roll again to add to total or pass and keep current points
				//
				System.out.println("Your current roll is " + pd.currentRound() + " points. Keep rolling? Respond (Y/N) only.");
				
				if (!yesResponse(kb))
				{
					keepRolling = false;
					int roundScore = pd.save();
					System.out.printf("Your total for the round was %d and your total score is %d.\r\n", roundScore, pd.currentTotal());
				}
			} 

		} while (!pd.piggedOut() && keepRolling);
	}
	
	//
	// Returns true if the user enters a 'y' or 'Y'
	//
	final String _YES = "Y";
	public boolean yesResponse(Scanner kb)
	{
		return kb.nextLine().substring(0, 1).toUpperCase().equals(_YES);
	}
}