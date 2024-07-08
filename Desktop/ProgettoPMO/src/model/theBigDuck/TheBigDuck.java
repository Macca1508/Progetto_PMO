package model.theBigDuck;

import java.util.Set;

import model.piece.PieceImp;

public interface TheBigDuck {
	void start();
	void increseTurn();
	void setPlayer(Set<PieceImp> player);
	void doTheMagic();
	String getMessage();
}
