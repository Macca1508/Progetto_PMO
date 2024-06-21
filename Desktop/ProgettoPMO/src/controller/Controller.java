package controller;

import java.awt.Color;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import model.FieldImp;
import model.piece.PieceImp;
import view.Menu;
import view.MyFrame;

public class Controller implements ViewController{
	
	private Optional<FieldImp> field;
	private MyFrame frame;
	
	public Controller() {
		field = Optional.empty();
		frame = new MyFrame();
	}
	
	public void start() throws IOException {
		if(field.isPresent()) {
			Menu menu = new Menu(this.getFrame().getContentPane(),this);
			this.getFrame().getContentPane().add(menu);
			this.getFrame().setVisible(true);
		}else {
			throw new IOException("Problema nella creazione del campo");
		}
	}
	// Crea il campo da gioco
	public void createFiled() {
		field = Optional.of(FieldImp.createField());
	}
	
	public FieldImp getField() {
		return this.field.get();
	}
	
	public MyFrame getFrame() {
		return this.frame;
	}

	// Predispone l'inizio del turno
	public void startTurn() {
		this.getField().goToPlay();
		this.getField().setCurrentPlayer();
		this.getField().throwDices();
	}
	// Crea i giocatori 
	public void createPieces(List<String> players) {
		this.getField().createPiece(players);
	}
	// Cancella i giocatori 
	public void reset() {
		this.getField().reset();
	}
	// Restituisce il messagio in base alla casella azione in si è fermata la pedina 
	public String setMessages() {
		return this.getField().getActionSlots().stream()
				    .filter(actionSlot-> actionSlot.getSlotName()== this.getPositionByCurrentPlayer())
				    .findFirst()
				    .get()
				    .message();
	}
	// Restituisce il giocatore che sta facendo il turno
	public PieceImp getCurrentPlayer() {
		return this.getField().getCurrentPlayer();
	}
	// Restituisce il valore del primo dado
	public int valueDice1() {
		return this.getField().getDice1().getCurrentValue();
	}
	// Restituisce il valore del secondo dado
	public int valueDice2() {
		return this.getField().getDice2().getCurrentValue();
	}
	// Fa avanzare le dedina in baso al lancio effetuato 
	public void play() {
		this.getField().play();
	}
	// Controlla se la pedina è finita su una casella azione
	public boolean ifIsAction() {
		return this.getField().ifIsAction();
	}
	// Esegue l'azione della casella in cui si è finiti sopra
	public void doActions() {
		this.getField().doActions();
	}
	// Controlla se qualcuno ha vinto
	public boolean isWin() {
		return this.getField().isWin();
	}
	// Crea un set con all'interno il numero dei colori delle pedine presenti nella casella designata 
	public Set<Integer> pieceInTheSlots(int position) {
		return this.getField().getPieces().stream()
          	   .filter(piece -> piece.getPosition()== position)
               .map(PieceImp::getColor)
               .map(color -> color.getNumber())
               .collect(Collectors.toSet());
	}
	// Restituisce la posizione attuale del giocatore che sta facendo il turno
	public int getPositionByCurrentPlayer() {
		return this.getField().getCurrentPlayer().getPosition();
	}
	// Restituisce la posizione precendente del giocatore che sta facendo il turno
	public int getLastPositionByCurrentPlayer() {
		return this.getField().getCurrentPlayer().getLastPosition();
	}
	// Controlla se ci sono pedine presenti sulla casella designata 
	public boolean thereArePlayer(int position){
		if(this.getField().getPieces()
				.stream()
				.filter(piece -> piece.getPosition()== position)
				.count() != 0)
			return true;
		else
			return false;
	}
	// Restituisce il numero di giocatori di questa partita
	public int numberOfPlayers() {
		return this.getField().getPieces().size();
	}
	// Restituisce la pedina in base alla posizione in classifica
	public PieceImp getPlayerForRank(int number) {
		return this.getField().leaderBoard().get(number);
	}
	public PieceImp getPlayer(int number) {
		return this.getField().getPieces().stream().collect(Collectors.toList()).get(number);
	}
	// Restituisce la somma dei dadi lanciati
	public int valueDiceTot() {
		return this.getField().getDiceTot();
	}
	// Restuituisce la direzione di movimento della pedina: true-Avanti false-In Dietro
	public boolean getDirection() {
		return this.getField().getDirection();
	}
	// Converte il colore delle pedine(enum) in un colore di java.awt.Color
	public Color colorConverter(int i) {
		if(this.getField().leaderBoard().get(i).getColor().getNumber() == 0)
			return Color.ORANGE;
		else if (this.getField().leaderBoard().get(i).getColor().getNumber() == 1)
			return Color.BLUE;
		else if (this.getField().leaderBoard().get(i).getColor().getNumber() == 2)
			return Color.YELLOW;
		else if (this.getField().leaderBoard().get(i).getColor().getNumber() == 3)
			return Color.RED;
		else if (this.getField().leaderBoard().get(i).getColor().getNumber() == 4)
			return Color.GREEN;
		else
			return new java.awt.Color(255, 0, 255);
	}
	public void createThebigDuck() {
		this.getField().createTheBigDuck();
	}
} 
