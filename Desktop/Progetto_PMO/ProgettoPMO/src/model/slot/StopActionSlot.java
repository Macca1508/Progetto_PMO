package model.slot;
import model.piece.PieceImp;

public class StopActionSlot extends SlotImp implements ActionSlot {
	private int stopTurns;
	
	public StopActionSlot(int slotName,int StopTurns) {
		super(slotName);
		this.stopTurns=StopTurns;
	}
	// Blocca la pedina diminuendo i suoi permssi in base a tanto dovra stare ferma
	public void action(PieceImp p) {
		for(int i=0;i<stopTurns;i++)
			p.canThrowDec(); 
	}
	// Genera la stringa del pop-up
	public String message() {
		return "starà fermo per "+ this.stopTurns+" turni";
	}
}
