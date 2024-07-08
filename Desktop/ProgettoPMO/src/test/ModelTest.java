package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;

import model.FieldImp;

public class ModelTest {
		
	ArrayList<String> piece = new ArrayList<>();
	FieldImp field;
	
	private void pieces(){
		piece.add("Tommaso");
		piece.add("ORANGE");
		piece.add("Angelo");
		piece.add("BLUE");
	}
	
	@Before
	public void createData() {
		field = FieldImp.createField();
		field.reset();
		pieces();
		field.createPiece(piece);
		field.setPlayerForBigDuck();
		field.getBigDuck().kill();
	}
	
	@org.junit.Test
    public void testGoToPlay() {
		field.goToPlay();
		assertEquals(2,field.countPeoplePlayTurn());
	}
	
	@org.junit.Test
	public void testSetCurrentPlayer() {
		field.goToPlay();
		field.setCurrentPlayer();
		assertEquals("Tommaso",field.getCurrentPlayer().getName());
		assertEquals(0,field.getCurrentPlayer().getPriority());
	}
	
	@org.junit.Test // Flaky test
	public void testThrowDices() {
		field.throwDices();
		assertEquals(2,field.getDiceTot());
	}

	@org.junit.Test
    public void testPlay() {
		field.goToPlay();
		field.setCurrentPlayer();
		field.setDiceTot(5, 6);
		field.play();
		assertEquals(11,field.getCurrentPlayer().getPosition());
		assertEquals(0,field.getCurrentPlayer().getCanThrow());
    }
	
	@org.junit.Test
	public void testIfIsAction() {
		field.goToPlay();
		field.setCurrentPlayer();
		field.setDiceTot(6, 3);
		field.play();
		assertTrue(field.ifIsAction());
	}
	
	@org.junit.Test
	public void doActionsStop(){
		field.goToPlay();
		field.setCurrentPlayer();
		field.setDiceTot(6, 3);
		field.play();
		field.doActions();
		assertEquals(1,field.getCurrentPlayer().getPosition());
	}
	
	@org.junit.Test
	public void doActionsReroll(){
		field.goToPlay();
		field.setCurrentPlayer();
		field.setDiceTot(4, 1);
		field.play();
		field.doActions();
		assertEquals("Tommaso",field.getCurrentPlayer().getName());
	}
	
	@org.junit.Test
	public void doActionsGoTo(){
		field.goToPlay();
		field.setCurrentPlayer();
		field.setDiceTot(3, 3);
		field.play();
		field.doActions();
		field.setCurrentPlayer();
		field.setDiceTot(2, 1);
		field.play();
		field.goToPlay();
		field.setCurrentPlayer();
		assertEquals(12,field.getCurrentPlayer().getPosition());
	}
	
	@org.junit.Test
	public void doActionsDoubleResault(){
		field.goToPlay();
		field.setCurrentPlayer();
		field.setDiceTot(7, 7);
		field.play();
		field.doActions();
		field.setCurrentPlayer();
		field.setDiceTot(2, 1);
		field.play();
		field.goToPlay();
		field.setCurrentPlayer();
		assertEquals(28,field.getCurrentPlayer().getPosition());
	}
	
	@org.junit.Test
	public void doActionsSwap(){
		field.goToPlay();
		field.setCurrentPlayer();
		field.setDiceTot(14, 15);
		field.play();
		field.ifIsAction();
		assertTrue(field.ifIsAction());
		field.doActions();
		assertEquals(0,field.getCurrentPlayer().getPosition());
	}
	
	@org.junit.Test
	public void testPiecesLiderBoard(){
		field.goToPlay();
		field.setCurrentPlayer();
		field.throwDices();
		field.play();
		assertEquals("Tommaso",field.leaderBoard().get(0).getName());
	}
	
	@org.junit.Test
	public void testWin(){
		field.goToPlay();
		field.setCurrentPlayer();
		field.setDiceTot(54, 45);
		field.play();
		field.setCurrentPlayer();
		field.setDiceTot(2, 1);
		field.play();
		field.goToPlay();
		field.setCurrentPlayer();
		assertEquals(99,field.getCurrentPlayer().getPosition());
		assertTrue(field.isWin());
	}
	
	@org.junit.Test
	public void testReset(){
		field.reset();
		assertTrue(field.getPieces().isEmpty());
	}
	
	@org.junit.Test
	public void testTheBigDuckStart(){
		field.goToPlay();
		assertFalse(field.getBigDuck().isAlive());
		field.setCurrentPlayer();
		field.setDiceTot(33, 22);
		field.play();
		field.setCurrentPlayer();
		field.setDiceTot(2, 1);
		field.play();
		field.goToPlay();
		field.setCurrentPlayer();
		assertEquals(55,field.getCurrentPlayer().getPosition());
		assertTrue(field.getBigDuck().isAlive());
	}
	
	@org.junit.Test
	public void testTheBigDuckRestart(){
		field.goToPlay();
		field.setCurrentPlayer();
		field.setDiceTot(33, 22);
		field.play();
		field.setCurrentPlayer();
		field.setDiceTot(2, 1);
		field.play();
		field.goToPlay();
		field.setCurrentPlayer();
		field.setDiceTot(2, 1);
		field.play();
		field.setCurrentPlayer();
		field.setDiceTot(2, 1);
		field.play();
		field.getBigDuck().forJunitTest(1);
		field.goToPlay();
		assertEquals(2,field.getPieces().stream().filter(p -> p.getPosition()==0).count());
	}
	
	@org.junit.Test
	public void testTheBigDuckInvincible(){
		field.goToPlay();
		field.setCurrentPlayer();
		field.setDiceTot(33, 22);
		field.play();
		field.setCurrentPlayer();
		field.setDiceTot(2, 1);
		field.play();
		field.goToPlay();
		field.setCurrentPlayer();
		field.setDiceTot(2, 1);
		field.play();
		field.setCurrentPlayer();
		field.setDiceTot(2, 1);
		field.play();
		field.getBigDuck().forJunitTest(2);
		field.goToPlay();
		assertEquals(1,field.getPieces().stream().filter(p -> p.getPrivilege()>0).count());
	}
	
	@org.junit.Test
	public void testTheBigDuckRestartForOne(){
		field.goToPlay();
		field.setCurrentPlayer();
		field.setDiceTot(33, 22);
		field.play();
		field.setCurrentPlayer();
		field.setDiceTot(2, 1);
		field.play();
		field.goToPlay();
		field.setCurrentPlayer();
		field.setDiceTot(2, 1);
		field.play();
		field.setCurrentPlayer();
		field.setDiceTot(2, 1);
		field.play();
		field.getBigDuck().forJunitTest(3);
		field.goToPlay();
		assertEquals(0,field.getPieces().stream().filter(p -> p.getPrivilege()>0).count());
		assertEquals(1,field.getPieces().stream().filter(p -> p.getPosition()==0).count());
	}
	
	@org.junit.Test
	public void testTheBigDuckPlusTen(){
		field.goToPlay();
		field.setCurrentPlayer();
		field.setDiceTot(33, 22);
		field.play();
		field.setCurrentPlayer();
		field.setDiceTot(2, 1);
		field.play();
		field.goToPlay();
		field.setCurrentPlayer();
		field.setDiceTot(2, 1);
		field.play();
		field.setCurrentPlayer();
		field.setDiceTot(2, 1);
		field.play();
		field.getBigDuck().forJunitTest(4);
		field.goToPlay();
		field.setCurrentPlayer();
		assertEquals(68,field.getCurrentPlayer().getPosition());
		field.setDiceTot(2, 1);
		field.play();
		field.setCurrentPlayer();
		assertEquals(16,field.getCurrentPlayer().getPosition());
	}
	
	@org.junit.Test
	public void testTheBigDuckMinusTen(){
		field.goToPlay();
		field.setCurrentPlayer();
		field.setDiceTot(33, 22);
		field.play();
		field.setCurrentPlayer();
		field.setDiceTot(2, 1);
		field.play();
		field.goToPlay();
		field.setCurrentPlayer();
		field.setDiceTot(2, 1);
		field.play();
		field.setCurrentPlayer();
		field.setDiceTot(2, 1);
		field.play();
		field.getBigDuck().forJunitTest(5);
		field.goToPlay();
		field.setCurrentPlayer();
		assertEquals(48,field.getCurrentPlayer().getPosition());
		field.setDiceTot(2, 1);
		field.play();
		field.setCurrentPlayer();
		assertEquals(0,field.getCurrentPlayer().getPosition());
	}
	
	@org.junit.Test
	public void testTheBigDuckGoToEnd(){
		field.goToPlay();
		field.setCurrentPlayer();
		field.setDiceTot(33, 22);
		field.play();
		field.setCurrentPlayer();
		field.setDiceTot(2, 1);
		field.play();
		field.goToPlay();
		field.setCurrentPlayer();
		field.setDiceTot(2, 1);
		field.play();
		field.setCurrentPlayer();
		field.setDiceTot(2, 1);
		field.play();
		field.getBigDuck().forJunitTest(6);
		field.goToPlay();
		assertEquals(1,field.getPieces().stream().filter(p -> p.getPosition()==97).count());
	}
	
	@org.junit.Test
	public void testTheBigDuckMultiStop(){
		field.goToPlay();
		field.setCurrentPlayer();
		field.setDiceTot(33, 22);
		field.play();
		field.setCurrentPlayer();
		field.setDiceTot(2, 1);
		field.play();
		field.goToPlay();
		field.setCurrentPlayer();
		field.setDiceTot(2, 1);
		field.play();
		field.setCurrentPlayer();
		field.setDiceTot(2, 1);
		field.play();
		field.getBigDuck().forJunitTest(7);
		field.goToPlay();
		assertEquals(1,field.getPieces().stream().filter(p -> p.getCanThrow()<=-2 && p.getCanThrow()>=-4).count());
	}
	
	@org.junit.Test
	public void testTheBigDuckBoostThrow(){
		field.goToPlay();
		field.setCurrentPlayer();
		field.setDiceTot(33, 22);
		field.play();
		field.setCurrentPlayer();
		field.setDiceTot(2, 1);
		field.play();
		field.goToPlay();
		field.setCurrentPlayer();
		field.setDiceTot(2, 1);
		field.play();
		field.setCurrentPlayer();
		field.setDiceTot(2, 1);
		field.play();
		field.getBigDuck().forJunitTest(9);
		field.goToPlay();
		assertEquals(1,field.getPieces().stream().filter(p -> p.getBoostThrow() == 3).count());
	}
	
	@org.junit.Test
	public void testTheBigDuckOddStop(){
		field.goToPlay();
		field.setCurrentPlayer();
		field.setDiceTot(33, 22);
		field.play();
		field.setCurrentPlayer();
		field.setDiceTot(2, 1);
		field.play();
		field.goToPlay();
		field.setCurrentPlayer();
		field.setDiceTot(2, 2);
		field.play();
		field.setCurrentPlayer();
		field.setDiceTot(2, 1);
		field.play();
		field.getBigDuck().forJunitTest(10);
		field.goToPlay();
		assertEquals(1,field.getPieces().stream().filter(p -> p.getCanThrow()== 0).count());
	}
	
	@org.junit.Test
	public void testTheBigDuckEvenReroll(){
		field.goToPlay();
		field.setCurrentPlayer();
		field.setDiceTot(33, 22);
		field.play();
		field.setCurrentPlayer();
		field.setDiceTot(2, 1);
		field.play();
		field.goToPlay();
		field.setCurrentPlayer();
		field.setDiceTot(2, 1);
		field.play();
		field.setCurrentPlayer();
		field.setDiceTot(2, 1);
		field.play();
		field.getBigDuck().forJunitTest(11);
		field.goToPlay();
		assertEquals(2,field.getPieces().stream().filter(p -> p.getCanThrow()==1).count());
	}
	
	@org.junit.Test
	public void testTheBigDuckMalusThrow(){
		field.goToPlay();
		field.setCurrentPlayer();
		field.setDiceTot(33, 22);
		field.play();
		field.setCurrentPlayer();
		field.setDiceTot(2, 1);
		field.play();
		field.goToPlay();
		field.setCurrentPlayer();
		field.setDiceTot(2, 1);
		field.play();
		field.setCurrentPlayer();
		field.setDiceTot(2, 1);
		field.play();
		field.getBigDuck().forJunitTest(12);
		field.goToPlay();
		assertEquals(1,field.getPieces().stream().filter(p -> p.getBoostMalus() == 4).count());
	}
	
}
