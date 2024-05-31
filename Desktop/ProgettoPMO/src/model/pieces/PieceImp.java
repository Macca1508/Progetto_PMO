package model.pieces;


public class PieceImp extends User implements Piece{
	private final Colors c;
	private int lastPosition;
	private int position;
	private int canThrow;
	private int priority;
	
	public PieceImp(final String name,final Colors c,final int priority) {
		super(name);
		this.c=c;
		this.position=0;
		this.lastPosition=0;
		this.canThrow=0;
		this.priority=priority;
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
	public Colors getC() {
		return c;
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
	public int getPriority() {
		return priority;
	}
}
