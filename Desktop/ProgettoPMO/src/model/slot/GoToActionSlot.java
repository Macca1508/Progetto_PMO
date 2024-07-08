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
	public String message(PieceImp piece) {
		if(piece.getPrivilege() > 0 && (this.pieceTo-super.getSlotName())<0) 
			return piece.getName() +" al momento è invincibile";
		else
			return piece.getName()+" andrà dalla casella "+super.getSlotName()+" alla casella "+ this.pieceTo;
	}
}
