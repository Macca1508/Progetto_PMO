package model;

import java.util.Random;

public class DiceImp {
	private Random rnd;
	private int currentValue;
	private static DiceImp diceImp;
	
	private DiceImp() {
		currentValue = -1;
		this.rnd = new Random();
	}
	// Simula il lancio dei dadi 
	public int throwDice() {
		int value;
		value=rnd.nextInt(6)+1;
		currentValue= value;
		return value;
	}
	
	public int getCurrentValue() {
		return currentValue;
	}
	
	public static DiceImp createDice() {
		if(diceImp == null)
			diceImp = new DiceImp();
		return diceImp;
		
	}
}