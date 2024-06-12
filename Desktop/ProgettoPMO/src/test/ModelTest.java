package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import model.FieldImp;

public class ModelTest {
		
	ArrayList<String> piece = new ArrayList<>();
	
	private void pieces(){
		piece.add("Tommaso");
		piece.add("ORANGE");
		piece.add("Angelo");
		piece.add("BLUE");
	}

	@org.junit.Test
    public void testPiecesMove() {
		// Creazione campo da gioco
		FieldImp field = FieldImp.createField();
		field.reset();
		pieces();
		field.createPiece(piece);
		field.goToPlay();
		assertEquals(2,field.countPeoplePlayTurn());
		// Designazione del primo giocatore giocatore
		field.setCurrentPlayer();
		assertEquals("Tommaso",field.getCurrentPlayer().getName());
		assertEquals(0,field.getCurrentPlayer().getPriority());
		// Primo turno
		field.throwDices();
		field.play();
		assertEquals(field.getDiceTot(),field.getCurrentPlayer().getPosition());
		assertEquals(0,field.getCurrentPlayer().getCanThrow());
		// Designazione del secondo giocatore giocatore
		field.setCurrentPlayer();
		assertEquals("Angelo",field.getCurrentPlayer().getName());
		assertEquals(2,field.getCurrentPlayer().getPriority());
		// Primo turno
		field.throwDices();
		field.play();
		assertEquals(field.getDiceTot(),field.getCurrentPlayer().getPosition());
		assertEquals(0,field.getCurrentPlayer().getCanThrow());
		// Conclusione del turno
		assertEquals(0,field.countPeoplePlayTurn());
    }
	
	@org.junit.Test
	public void testPiecesActionSLot(){
		FieldImp field = FieldImp.createField();
		field.reset();
		pieces();
		field.createPiece(piece);
		field.goToPlay();
		field.setCurrentPlayer();
		assertEquals("Tommaso",field.getCurrentPlayer().getName());
		assertEquals(0,field.getCurrentPlayer().getPriority());
		// Primo turno
		field.setDiceTot(4, 5);
		field.play();
		assertTrue(field.ifIsAction());
		field.doActions();
		assertEquals(1,field.getCurrentPlayer().getPosition());
	}
	
	@org.junit.Test
	public void testPiecesLiderBoard(){
		// Creazione campo da gioco
		FieldImp field = FieldImp.createField();
		field.reset();
		pieces();
		field.createPiece(piece);
		field.goToPlay();
		assertEquals(2,field.countPeoplePlayTurn());
		// Designazione del primo giocatore giocatore
		field.setCurrentPlayer();
		field.throwDices();
		field.play();
		assertEquals("Tommaso",field.leaderBoard().get(0).getName());
	}
	
	@org.junit.Test
	public void testWin(){
		// Creazione campo da gioco
		FieldImp field = FieldImp.createField();
		field.reset();
		pieces();
		field.createPiece(piece);
		field.goToPlay();
		field.setCurrentPlayer();
		field.setDiceTot(32, 31);
		field.play();
		field.setCurrentPlayer();
		field.setDiceTot(2, 1);
		field.play();
		field.goToPlay();
		field.setCurrentPlayer();
		assertEquals(63,field.getCurrentPlayer().getPosition());
		assertTrue(field.isWin());
	}
	
}
