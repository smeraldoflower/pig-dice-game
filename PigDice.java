/*
 * @author Iffat Nusaiba Mahmood
 * <p> PigDice
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

// this class manages the state of the dice and the scoring
public class PigDice
{
	// keep track of total and round scores as well as the two dice.
	private int _totalScore = 0;
	private int _roundScore = 0;
	private Die _die1;
	private Die _die2;

	//Constructor; instantiates dice for use by players.
	public PigDice()
	{
		_die1 = new Die();
		_die2 = new Die();
	}

	// accessor for total score
	public int currentTotal()
	{
		return _totalScore;
	}

	// accessor for this round score
	public int currentRound()
	{
		return _roundScore;
	}

	// accessor to see if the user has rolled a single "1" and loses turn
	public boolean piggedOut()
	{
        return singleOneRolled();
	}

	// mutator that simulates rolling two dice and evaluating the resulting score
	public void rollDice()
	{
		// Roll the die
		_die1.roll();
		_die2.roll();
	}

	// accessor for a formatted string of what the last roll looked like
	public String lastRoll()
	{
		return "D1 (" + _die1.faceValue() + "), D2 (" + _die2.faceValue() + ")";
	}

	//accessor; computes rolled score and adds to round score. Returns rolled score.
	public int evaluate()
	{
		int rolledScore;
		
		//Pigged out score
		if (singleOneRolled() == true)
		{
			_roundScore = 0;
			rolledScore = 0;
		}
		//Computes double ones as a rolled score of 25
		else if (doubleOnesRolled() == true)
        {
        	rolledScore = 25;
        }
		//Otherwise adds the face values of the dice to compute rolled score.
        else
        {
        	rolledScore = _die1.faceValue() + _die2.faceValue(); 
        }
        
		//Adds rolled score to the current round score.
		_roundScore += rolledScore;
        
        return rolledScore;
	}

	//Returns true if exactly one die has a faceValue == 1
	private boolean singleOneRolled()
	{
        int die1 = _die1.faceValue();
        int die2 = _die2.faceValue();
        boolean singleOne;
        if((die1 == 1 && die2 != 1) || (die1 != 1 && die2 == 1))
        {
        	singleOne = true;
        }
        else
        {
        	singleOne = false;
        }
        return singleOne;
	}
	
	//Returns true if both dice have a faceValue == 1
	private boolean doubleOnesRolled()
	{
        int die1 = _die1.faceValue();
        int die2 = _die2.faceValue();
        boolean doubleOnes;
        if(die1 == 1 && die2 == 1)
        {
        	doubleOnes = true;
        }
        else
        {
        	doubleOnes = false;
        }
        return doubleOnes;
	}

	//
	// mutator to end a round and keep the add this round to the total
	// also returns the total value of the round and resets the round total for next time
	//
	public int save()
	{
		int thisRoundScore = _roundScore;
		_totalScore += _roundScore;
		_roundScore = 0;
		
		return thisRoundScore;
        
	}
	
	//mutator that clears scores for a new game.
	public void clear()
	{
		_totalScore = 0;
		_roundScore = 0;
	}
}
