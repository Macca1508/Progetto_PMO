package model;

import java.util.List;

import model.pieces.PieceImp;

public interface Field {
	void setCurrentPlayer();
	PieceImp getCurrentPlayer();
	void goToPlay();
	void throwDices();
	void play();
	void doActions();
	boolean isWin();
	void reset();
	List<PieceImp> leaderBoard();
}
