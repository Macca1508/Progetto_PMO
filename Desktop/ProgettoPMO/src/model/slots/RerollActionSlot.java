package model.slots;
import model.pieces.PieceImp;

public class RerollActionSlot extends Slot implements ActionSlot {
	
	public RerollActionSlot(int slotName) {
		super(slotName);
	}
	// Aumenta i permessi della pedina cosi ritirerà
	public void action(PieceImp p) {
		System.out.println(p.getName()+" ritirerà");
		p.canThrowInc();
	}
	// Genera la stringa del pop-up
	public String message() {
		return "ritirerà";
	}
}
