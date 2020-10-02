/*
* @author Iffat Nusaiba Mahmood
* <p> Die
* <p> Project 3
* <p> 
* 
*/

import java.util.Random;

//
// class to manage the value of a single simulated die
//
public class Die
{
	private int _pips = 1;
	private final int _MAX_PIPS = 6;
	private Random _randNum;
	private int _dieValue;
	
	// constructor that will create a Random class and generate a random start value.
	public Die()
	{
		_randNum = new Random();
		_dieValue = _randNum.nextInt(_MAX_PIPS) + _pips;
	}

	//
	// constructor that will create a Random class, set the seed of the RNG,
	// and generate a random start value.
	//
	public Die(int seed)
	{
		_randNum = new Random(seed);
		_dieValue = _randNum.nextInt(_MAX_PIPS) + _pips;
	}
	
	//
	// accessor to return the current value of the die.
	//
	public int faceValue()
	{
		return _dieValue;
	}

	//
	// mutator to randomly change the value of the die.
	//
	public int roll()
	{
		_dieValue = _randNum.nextInt(_MAX_PIPS) + _pips;
		return _dieValue;
	}
}
