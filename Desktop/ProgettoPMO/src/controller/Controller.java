package controller;

import java.awt.Color;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import model.FieldImp;
import model.pieces.PieceImp;
import view.Menu;
import view.MyFrame;

public class Controller implements ViewController{
	
	private FieldImp field;
	private MyFrame frame;
	
	public Controller() {
		field = FieldImp.createField();
		frame = new MyFrame();
	}
	
	public void start() {
		Menu menu = new Menu(this.getFrame().getContentPane(),this);
		this.getFrame().getContentPane().add(menu);
		this.getFrame().setVisible(true);
		
	}
	
	public FieldImp getField() {
		return this.field;
	}
	
	public MyFrame getFrame() {
		return this.frame;
	}


	public void startTurn() {
		field.goToPlay();
		field.setCurrentPlayer();
		field.throwDices();
	}

	public void createPieces(List<String> players) {
		field.createPiece(players);
	}
	
	public void reset() {
		field.reset();
	}
	
	public String setMessages() {
		return field.getActionSlots().stream()
				    .filter(actionSlot-> actionSlot.getSlotName()== this.getPositionByCurrentPlayer())
				    .findFirst()
				    .get()
				    .message();
	}
	
	public PieceImp getCurrentPlayer() {
		return field.getCurrentPlayer();
	}
	
	public int valueDice1() {
		return field.getDice1().getCurrentValue();
	}
	
	public int valueDice2() {
		return field.getDice2().getCurrentValue();
	}

	public void play() {
		field.play();
	}

	public boolean ifIsAction() {
		return field.ifIsAction();
	}

	public void doActions() {
		field.doActions();
	}

	public boolean isWin() {
		return field.isWin();
	}

	public Set<Integer> pieceInTheSlots(int position) {
		return field.getPieces().stream()
          	   .filter(piece -> piece.getPosition()== position)
               .map(PieceImp::getColor)
               .map(color -> color.getNumber())
               .collect(Collectors.toSet());
	}

	public int getPositionByCurrentPlayer() {
		return field.getCurrentPlayer().getPosition();
	}

	public int getLastPositionByCurrentPlayer() {
		return field.getCurrentPlayer().getLastPosition();
	}
	
	public boolean thereArePlayer(int position){
		if(field.getPieces()
				.stream()
				.filter(piece -> piece.getPosition()== position)
				.count() != 0)
			return true;
		else
			return false;
	}

	public int numberOfPlayers() {
		return field.getPieces().size();
	}

	public PieceImp getPlayerForRank(int number) {
		return field.leaderBoard().get(number);
	}

	public int valueDiceTot() {
		return field.getDiceTot();
	}

	public boolean getDirection() {
		return field.getDirection();
	}
	
	public Color colorConverter(int i) {
		if(field.leaderBoard().get(i).getColor().getNumber() == 0)
			return Color.ORANGE;
		else if (field.leaderBoard().get(i).getColor().getNumber() == 1)
			return Color.BLUE;
		else if (field.leaderBoard().get(i).getColor().getNumber() == 2)
			return Color.YELLOW;
		else if (field.leaderBoard().get(i).getColor().getNumber() == 3)
			return Color.RED;
		else if (field.leaderBoard().get(i).getColor().getNumber() == 4)
			return Color.GREEN;
		else
			return new java.awt.Color(255, 0, 255);
	}
} 
