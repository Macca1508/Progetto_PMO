package model.slot;
import model.piece.PieceImp;

public class SwapActionSlot extends SlotImp implements ActionSlot{
	
	private PieceImp target;
	
	public SwapActionSlot(int slotName) {
		super(slotName);
	}
	public void action(PieceImp p) {
		int a,b;
		System.out.println(p.getPosition());
		a = p.getPosition();
		p.setPosition(target.getPosition());
		System.out.println(p.getPosition());
		target.setPosition(a);
		b = p.getLastPosition();
		p.setLastPosition(a);
		target.setLastPosition(b);
	}
	// Genera la stringa del pop-up
	public String message() {
		return " cambierà la posione con "+ target.getName();
	}
	
	public void setTarget(PieceImp p) {
		this.target = p;
	}

}
