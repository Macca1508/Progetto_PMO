package controller;

import java.awt.Color;
import java.util.List;
import java.util.Set;

import model.piece.PieceImp;

public interface ViewController {
	void startTurn();
	void createPieces(List<String> players);
	void reset();
	void play();
	void doActions();
	void setPlayerForBigDuck();
	int valueDice1();
	int valueDice2();
	int valueDiceTot();
	int getPositionByCurrentPlayer();
	int getLastPositionByCurrentPlayer();
	int numberOfPlayers();
	boolean ifIsAction();
	boolean isWin();
	boolean thereArePlayer(int position);	
	boolean getDirection();
	boolean isStopOrReroll();
	Set<Integer> pieceInTheSlots(int position);
	PieceImp getCurrentPlayer();
	PieceImp getPlayerForRank(int number);
	PieceImp getPlayer(int number);
	String getMessages(PieceImp piece);
	String getMessages();
	Color colorConverter(int i);
}
