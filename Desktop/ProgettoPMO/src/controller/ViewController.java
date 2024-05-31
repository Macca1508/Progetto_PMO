package controller;

import java.awt.Color;
import java.util.List;
import java.util.Set;

import model.pieces.PieceImp;

public interface ViewController {
	void startTurn();
	void createPiece(List<String> players);
	void reset();
	void play();
	void doActions();
	int valueDice1();
	int valueDice2();
	int valueDiceTot();
	int getPositionByCurrentPlayer();
	int getLastPositionByCurrentPlayer();
	int numberOfPlayers();
	boolean ifIsAction();
	boolean isWin();
	boolean thereArePlayer(int position);	
	boolean isCurrentPlayer(int number);
	boolean getDirection();
	Set<Integer> pieceInTheSlots(int position);
	PieceImp getCurrentPlayer();
	PieceImp getPlayerForRank(int number);
	String setMessages();
	Color convertiColore(int i);
	
}
