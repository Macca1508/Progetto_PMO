package model.slot;
import model.piece.PieceImp;

public class SwichActionSlot extends SlotImp implements ActionSlot{
	
	PieceImp target;
	
	public SwichActionSlot(int slotName) {
		super(slotName);
	}
	public void action(PieceImp p) {
		int a,b;
		if(p.equals(target)) {
			p.setPosition(0);
			p.setLastPosition(0);
		}else{
			a = p.getPosition();
			p.setPosition(target.getPosition());
			target.setPosition(a);
			b = p.getLastPosition();
			p.setLastPosition(a);
			target.setLastPosition(b);
		}
	}
	// Genera la stringa del pop-up
	public String message() {
		return " cambierà la posione con "+ target.getName();
	}
	
	public void set(PieceImp p) {
		this.target = p;
	}

}
