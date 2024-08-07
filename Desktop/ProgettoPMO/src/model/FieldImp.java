package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import model.dice.DiceImp;
import model.piece.*;
import model.slot.*;
import model.theBigDuck.TheBigDuckImp;

public class FieldImp implements Field{
	private final static int nSlot = 99;
	private Set<PieceImp> pieces = new HashSet<>();
	private Set<ActionSlot> actionSlots = new HashSet<>();
	private Set<Slot> slots = new HashSet<>();
	private DiceImp dice1,dice2;
	private PieceImp currentPlayer;
	private int diceTot;
	private boolean direction;
	private static FieldImp fieldImp;
	private String nomefile="ActionSlots.txt";
	private TheBigDuckImp bigDuck;
	
	private FieldImp() {
		createActionSlot();
		createSlot();
		this.dice1 = DiceImp.createDice();
		this.dice2 = DiceImp.createDice();
		this.bigDuck = TheBigDuckImp.createTheBigDuck();
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
	public TheBigDuckImp getBigDuck() {
		return bigDuck;
	}
	public void setPlayerForBigDuck() {
		this.bigDuck.setPlayer(this.pieces);
	}
	// il metodo è publicco solo per i test senno andrebbe messo privato 
	public void setDiceTot(int currentValue1, int currentValue2) {
		if(this.getCurrentPlayer().getBoostThrow()>0) {
			this.diceTot= currentValue1+currentValue2+this.getCurrentPlayer().getPlusThrow();
			this.currentPlayer.boostThrowDec();
		}else
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
		if(this.countPeoplePlayTurn() <= 0) {
			this.bigDuck.start();
			this.bigDuck.increseTurn();
			this.bigDuck.doTheMagic();
			while(this.getPieces().stream().filter(piece -> piece.getCanThrow()>0).count()< 1) {
				this.pieces.forEach(piece -> piece.canThrowInc());		
			}	
		}
	}
	// Restituisce il totale dei permessi dei giocatori
	public int countPeoplePlayTurn() {
		return  this.pieces.stream()
						   .filter(piece-> piece.getCanThrow()>0)
						   .map(PieceImp::getCanThrow)
						   .reduce(0, Integer::sum);
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
			if(this.actionSlots.stream()
				               .filter(actionSlot-> actionSlot.getSlotName()== this.currentPlayer.getPosition())
				               .findFirst()
				               .isPresent()) {
				if(this.getActionSlot() instanceof SwapActionSlot) {
					SwapActionSlot tmp = (SwapActionSlot) this.getActionSlot();
					PieceImp p = null;
					do {
						Random r = new Random();
						p = this.getPieces().stream().collect(Collectors.toList()).get(r.nextInt(2));
					}while(this.getCurrentPlayer().equals(p));
					tmp.setTarget(p);
				}
				return true;
			}else 
				return false;
	}
	// Esegue l'azione sul giocatore se si è possizionato su una casella azione
	public void doActions() {
		if(ifIsAction()) {
			this.getActionSlot().action(currentPlayer);
		}
	}
	// Restituisce la casella azione su cui è il player che sta giocando
	private ActionSlot getActionSlot() {
		return this.actionSlots.stream()
				   .filter(actionSlot-> actionSlot.getSlotName()== this.currentPlayer.getPosition())
				   .findFirst()
				   .get();
	} 
	// Instanza tutte le caselle azione  
	private void createActionSlot(){
		try {
			BufferedReader leggi = new BufferedReader(new FileReader(nomefile));
			String sTmp[];
			boolean stato= true;
			String tmp;
			while(stato)
			{
				tmp = leggi.readLine();
				if(tmp==null)
					stato=false;   
				else
				{
					sTmp = tmp.split(";");
					ActionSlotsType c = ActionSlotsType.parseToActionSlotType(sTmp[0]);
					int d = Integer.parseInt(sTmp[1]);
					if(sTmp.length == 2) 
						this.actionSlots.add(FactorySlots.createActionSlots(c,d));
					else {
						int e = Integer.parseInt(sTmp[2]);
						this.actionSlots.add(FactorySlots.createActionSlots(c,d,e));
					}
					sTmp = null;
				}
			}
			leggi.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	// Instanza tutte le caselle normali 
	private void createSlot(){
		for(int i=1;i<=FieldImp.nSlot;i++) {
			if(this.actionSlotPresent(i))
				this.slots.add(new SlotImp(i));
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
		this.bigDuck.reset();
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
