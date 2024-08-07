package model.slot;
import model.piece.PieceImp;

public class DoubleResultActionSlot extends SlotImp implements ActionSlot{

	public DoubleResultActionSlot(int slotName) {
		super(slotName);
	}
	// Muove la pedina di quanto aveva fatto prima  
	public void action(PieceImp piece) {
		int valueOfThrow = piece.getPosition()-piece.getLastPosition();
		piece.setLastPosition(piece.getPosition());
		piece.setPosition(piece.getPosition()+valueOfThrow);
	}
	// Genera la stringa del pop-up
	public String message(PieceImp piece) {
		return piece.getName()+ " si muoverà di quanto ha fatto prima";
	}
	
}
