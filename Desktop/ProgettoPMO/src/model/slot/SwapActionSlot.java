package model.slot;
import model.piece.PieceImp;

public class SwapActionSlot extends SlotImp implements ActionSlot{
	
	private PieceImp target;
	
	public SwapActionSlot(int slotName) {
		super(slotName);
	}
	public void action(PieceImp piece) {
		int a,b;
		a = piece.getPosition();
		piece.setPosition(target.getPosition());
		target.setPosition(a);
		b = piece.getLastPosition();
		piece.setLastPosition(a);
		target.setLastPosition(b);
		
	}
	// Genera la stringa del pop-up
	public String message(PieceImp piece) {
		return piece.getName()+" cambierà la posione con "+ target.getName();
	}
	
	public void setTarget(PieceImp p) {
		this.target = p;
	}

}
