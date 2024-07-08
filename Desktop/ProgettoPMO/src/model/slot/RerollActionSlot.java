package model.slot;
import model.piece.PieceImp;

public class RerollActionSlot extends SlotImp implements ActionSlot {
	
	public RerollActionSlot(int slotName) {
		super(slotName);
	}
	// Aumenta i permessi della pedina cosi ritirerà
	public void action(PieceImp piece) {
		piece.canThrowInc();
		
	}
	// Genera la stringa del pop-up
	public String message(PieceImp piece) {
		return piece.getName()+" ritirerà";
	}
}
