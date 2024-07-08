package model.slot;
import model.piece.PieceImp;

public class StopActionSlot extends SlotImp implements ActionSlot {
	private int stopTurns;
	
	public StopActionSlot(int slotName,int StopTurns) {
		super(slotName);
		this.stopTurns=StopTurns;
	}
	// Blocca la pedina diminuendo i suoi permssi in base a tanto dovra stare ferma
	public void action(PieceImp piece) {
		if(piece.getPrivilege() == 0)
			if(piece.getBoostMalus() > 0 )
				for(int i=0;i<stopTurns*2;i++)
					piece.canThrowDec(); 
			else
				for(int i=0;i<stopTurns;i++)
					piece.canThrowDec(); 
	}
	// Genera la stringa del pop-up
	public String message(PieceImp piece) {
		if(piece.getPrivilege() == 0)
			return (this.stopTurns == 1)? piece.getName()+" starà fermo per "+ this.stopTurns+" turno": piece.getName()+" starà fermo per "+ this.stopTurns+" turni";		
		else 
			return piece.getName()+" al momento è invincibile";	
		
	}
}
