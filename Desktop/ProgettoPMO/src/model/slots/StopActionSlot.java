package model.slots;
import model.pieces.PieceImp;

public class StopActionSlot extends Slot implements ActionSlot {
	private int StopTurns;
	
	public StopActionSlot(int slotName,int StopTurns) {
		super(slotName);
		this.StopTurns=StopTurns;
	}
	// Blocca la pedina diminuendo i suoi permssi in base a tanto dovra stare ferma
	public void action(PieceImp p) {
		System.out.println(p.getName()+" starà fermo per "+ this.StopTurns);
		for(int i=0;i<StopTurns;i++)
			p.canThrowDec(); 
	}
	// Genera la stringa del pop-up
	public String message() {
		return "starà fermo per "+ this.StopTurns+" turni";
	}
}
