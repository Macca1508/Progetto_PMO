package model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.pieces.*;
import model.slots.*;

public class FieldImp implements Field{
	private final static int nSlot = 63;
	private Set<PieceImp> pieces = new HashSet<>();
	private Set<ActionSlot> actionSlots = new HashSet<>();
	private Set<Slot> slots = new HashSet<>();
	private DiceImp dice1,dice2;
	private PieceImp currentPlayer;
	private int diceTot;
	private boolean direction;
	private static FieldImp fieldImp;
	
	private FieldImp() {
		
		createActionSlot();
		createSlot();
		this.dice1 = DiceImp.createDice();
		this.dice2 = DiceImp.createDice();
		this.direction = true;
	}
	
	public static FieldImp createField() {
		if(fieldImp == null)
			fieldImp = new FieldImp();
		return fieldImp;
	}
	public Set<PieceImp> getPieces() {
		return this.pieces;
	}
	public Set<ActionSlot> getActionSlots() {
		return this.actionSlots;
	}
	public Set<Slot> getSlots() {
		return this.slots;
	}
	public DiceImp getDice1() {
		return this.dice1;
	}
	public DiceImp getDice2() {
		return this.dice2;
	}
	public int getDiceTot() {
		return this.diceTot;
	}
	public PieceImp getCurrentPlayer() {
		return this.currentPlayer;
	}
	public boolean getDirection() {
		return this.direction;
	}
	private void setDiceTot(int currentValue1, int currentValue2) {
		this.diceTot= currentValue1+currentValue2;
	}
	// Imposta il giocatore che deve fare il turno
	public void setCurrentPlayer() {
		this.currentPlayer = this.getPieces()
				  				 .stream()
				  				 .filter(p -> p.getCanThrow()>0)
				  				 .sorted((p1,p2)-> (p1.getPriority()< p2.getPriority())? -1:1)
				  				 .findFirst()
				  				 .get();
	}
	// Aggiunge un permesso di gioco ad ogni giocatore
	public void goToPlay() {
		if(this.countPeoplePlayTurn() <= 0)
			while(this.getPieces().stream().filter(piece -> piece.getCanThrow()>0).count()< 1) {
				this.pieces.forEach(piece -> piece.canThrowInc());		
		}	
	}
	// Restituisce il totale dei permessi dei giocatori
	public int countPeoplePlayTurn() {
		return  this.pieces.stream()
						   .filter(piece-> piece.getCanThrow()>0)
						   .map(PieceImp::getCanThrow)
						   .reduce(0,Integer::sum);
	}
	// Simula il lancio dei dadi
	public void throwDices() {
		this.dice1.throwDice();
		this.dice2.throwDice();
		setDiceTot(this.dice1.getCurrentValue(),this.dice2.getCurrentValue());
	}
	
	// Simula lo spostamento delle pedine 
	public void play() {
		PieceImp piece = getCurrentPlayer();
		this.direction = true;
		piece.setLastPosition(piece.getPosition());
		for(int i=0;i<this.getDiceTot();i++) {
			if(piece.getPosition() == FieldImp.nSlot)
				this.direction = false;
			if(direction)
				piece.moveForwards();
			else
				piece.moveBackwards();
		}
		piece.canThrowDec();
	}
	// Controlla se la casella in cui il giocatore è arrivato sia una casella azione
	public boolean ifIsAction() {
		return this.actionSlots.stream()
				               .filter(actionSlot-> actionSlot.getSlotName()== this.currentPlayer.getPosition())
				               .findFirst()
				               .isPresent();
	}
	// Esegue l'azione sul giocatore se si è possizionato su una casella azione
	public void doActions() {
		if(ifIsAction()) {
			this.actionSlots.stream()
			   .filter(actionSlot-> actionSlot.getSlotName()== this.currentPlayer.getPosition())
			   .findFirst()
			   .get()
			   .action(currentPlayer);
		}
	}
	// Instanza tutte le caselle azione  
	private void createActionSlot(){
		this.actionSlots.add(new RerollActionSlot(5));
		this.actionSlots.add(new RerollActionSlot(41));
		this.actionSlots.add(new RerollActionSlot(50));
		this.actionSlots.add(new RerollActionSlot(53));
		this.actionSlots.add(new RerollActionSlot(59));
		this.actionSlots.add(new GoToActionSlot(6,12));
		this.actionSlots.add(new GoToActionSlot(9,1));
		this.actionSlots.add(new GoToActionSlot(23,28));
		this.actionSlots.add(new GoToActionSlot(42,35));
		this.actionSlots.add(new GoToActionSlot(45,40));
		this.actionSlots.add(new GoToActionSlot(54,57));
		this.actionSlots.add(new GoToActionSlot(58,1));
		this.actionSlots.add(new StopActionSlot(18,1));
		this.actionSlots.add(new StopActionSlot(19,1));
		this.actionSlots.add(new StopActionSlot(26,1));
		this.actionSlots.add(new StopActionSlot(31,2));
		this.actionSlots.add(new StopActionSlot(36,1));
		this.actionSlots.add(new StopActionSlot(52,2));
		this.actionSlots.add(new DoubleResultActionSlot(14));
		this.actionSlots.add(new DoubleResultActionSlot(32));
	}
	// Instanza tutte le caselle normali 
	private void createSlot(){
		for(int i=0;i<FieldImp.nSlot;i++) {
			if(this.actionSlotPresent(i+1))
				this.slots.add(new SlotImp(i+1));
		}
	}
	// Controlla se la casella con un determinato nome esiste già 
	public boolean actionSlotPresent(final int i) {
		return this.getActionSlots().stream()
									.map(ActionSlot::getSlotName)
									.filter(n -> n.equals(i))
									.findFirst()
									.isEmpty();
	}
	// Controlla se il giocatore che sta giocando ha vinto
	public boolean isWin() {
		if(this.getCurrentPlayer().getPosition()== FieldImp.nSlot)
			return true;
		else
			return false;
	}
	// Cancella HashSet dei giocatori cosi da poter fare un altra partita con dei nuovi giocatori
	public void reset() {
		this.pieces = new HashSet<PieceImp>();
	}
	// Restituisce una classifica momentanea
	public List<PieceImp> leaderBoard(){
		return this.pieces.stream()
					 .sorted((o1,o2) -> (o1.getPosition() == o2.getPosition())? 0 :(o1.getPosition() > o2.getPosition())? -1:1)
					 .toList();
	}
	// Instanza tutti giocatori per la partita
	public void createPiece(List<String> pleyers) {
		for(int i=0;i<pleyers.size();i++) {
			this.pieces.add(new PieceImp(pleyers.get(i),Colors.valueOf(pleyers.get(i+1)),i));
			i++;
		}
	}
	
}
