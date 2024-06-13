package model.slot;
import model.piece.PieceImp;

public interface ActionSlot{
	void action(PieceImp piece);
	int getSlotName();
	String message();
}
