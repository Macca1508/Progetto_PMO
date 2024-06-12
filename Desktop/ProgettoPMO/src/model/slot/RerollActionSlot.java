package model.slot;
import model.piece.PieceImp;

public class RerollActionSlot extends SlotImp implements ActionSlot {
	
	public RerollActionSlot(int slotName) {
		super(slotName);
	}
	// Aumenta i permessi della pedina cosi ritirerà
	public void action(PieceImp p) {
		p.canThrowInc();
	}
	// Genera la stringa del pop-up
	public String message() {
		return "ritirerà";
	}
}
