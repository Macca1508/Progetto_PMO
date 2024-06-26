package model.slot;
import model.piece.PieceImp;

public class GoToActionSlot extends SlotImp implements ActionSlot {
	private int pieceTo;
	
	public GoToActionSlot(int slotName,int pieceTo) {
		super(slotName);
		this.pieceTo=pieceTo;
	}
	// Sposta la posizione della pedine che vi è andata sopra nella casella designata 
	public void action(PieceImp piece) {
		piece.setLastPosition(piece.getPosition());
		piece.setPosition(this.pieceTo);
	}
	// Genera la stringa del pop-up
	public String message() {
		return " andrà dalla casella "+super.getSlotName()+" alla casella "+ this.pieceTo;
	}
}
