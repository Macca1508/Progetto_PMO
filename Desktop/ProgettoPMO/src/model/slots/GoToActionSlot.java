package model.slots;
import model.pieces.PieceImp;

public class GoToActionSlot extends Slot implements ActionSlot {
	private int pieceTo;
	
	public GoToActionSlot(int slotName,int pieceTo) {
		super(slotName);
		this.pieceTo=pieceTo;
	}
	// Sposta la posizione della pedine che vi è andata sopra nella casella designata 
	public void action(PieceImp piece) {
		System.out.println(piece.getName()+" andrà alla casella "+ this.pieceTo);
		piece.setLastPosition(piece.getPosition());
		piece.setPosition(this.pieceTo);
	}
	// Genera la stringa del pop-up
	public String message() {
		return " andrà dalla casella "+super.getSlotName()+" alla casella "+ this.pieceTo;
	}
}
