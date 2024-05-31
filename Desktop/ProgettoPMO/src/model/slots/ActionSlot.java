package model.slots;
import model.pieces.PieceImp;

public interface ActionSlot{
	void action(PieceImp piece);
	int getSlotName();
	String message();
}
