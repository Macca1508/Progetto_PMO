package model;

import java.util.List;

import model.piece.PieceImp;

public interface Field {
	void setCurrentPlayer();
	void goToPlay();
	void throwDices();
	void play();
	void doActions();
	void reset();
	boolean isWin();
	boolean getDirection();
	PieceImp getCurrentPlayer();	
	List<PieceImp> leaderBoard();
	
}
