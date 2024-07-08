package model.piece;

import java.util.Random;

public class PieceImp extends User implements Piece{
	
	private final Colors color;
	private int lastPosition;
	private int position;
	private int canThrow;
	private int priority;
	private int privilege;
	private int boostThrow;
	private int boostMalus;
	private Random rnd;
	
	public PieceImp(final String name,final Colors color,final int priority) {
		super(name);
		this.color=color;
		this.position=0;
		this.lastPosition=0;
		this.canThrow=0;
		this.priority=priority;
		this.privilege=0;
		this.boostThrow =0;
		this.boostMalus =0;
		this.rnd = new Random();
	}
	public int getLastPosition() {
		return this.lastPosition;
	}
	public int getPosition() {
		return this.position;
	}
	public int getCanThrow() {
		return this.canThrow;
	}
	public Colors getColor() {
		return color;
	}
	public int getPriority() {
		return priority;
	}
	public int getPrivilege() {
		return this.privilege;
	}
	public int getPlusThrow() {
		return this.rnd.nextInt(1,5);
	}
	public int getBoostMalus() {
		return boostMalus;
	}
	public int getBoostThrow() {
		return this.boostThrow;
	}
	// La pedina si muove in avanti di una posizione 
	public void moveForwards() {
		this.position++;
	}
	// La pedina si muove all'indietro di una posizione 
	public void moveBackwards() {
		this.position--;
	}
	// La pedina aumenta i permessi di gioco di uno
	public void canThrowInc() {
		this.canThrow++;
	}
	// La pedina diminuisce i permessi di gioco di uno
	public void canThrowDec() {
		this.canThrow--;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public void setLastPosition(int lastPosition) {
		this.lastPosition = lastPosition;
	}
	public void setPrivilege(int n) {
		this.privilege=n;
	}
	public void setBoostThrow(int n) {
		this.boostThrow = n;
	}
	public void resetPrivilege() {
		this.privilege = 0;
	}
	public void resetBoostThrow() {
		this.boostThrow = 0;
	}
	public void resetBoostMalus() {
		this.boostMalus = 0;
	}
	public void addBoostMalus(int boostMalus) {
		this.boostMalus += boostMalus;
	}
	public void privilegeDec() {
		if(this.privilege != 0)
			this.privilege--;
	}
	public void boostThrowDec() {
		if(this.boostThrow != 0) 
			this.boostThrow--;
	}
	public void boostMalusDec() {
		if(this.boostMalus != 0) 
			this.boostMalus--;
	}
}
