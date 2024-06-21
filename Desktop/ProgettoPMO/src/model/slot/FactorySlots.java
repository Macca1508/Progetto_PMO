package model.slot;

public class FactorySlots {
	public static ActionSlot createActionSlots(ActionSlotsType type,int slotNumber) {
		switch(type){
			case REROLL:
				return new RerollActionSlot(slotNumber);
			case DOUBLE_RESULT:
				return new DoubleResultActionSlot(slotNumber);
			case SWAP:
				return new SwapActionSlot(slotNumber);
			default:
				System.out.println("errore 1");
				break;
		}
		return null;
	}
	
	public static ActionSlot createActionSlots(ActionSlotsType type,int slotNumber,int extra) {
		switch(type){
			case GO_TO:
				return new GoToActionSlot(slotNumber,extra);
			case STOP:
				return new StopActionSlot(slotNumber,extra);
			default:
				System.out.println("errore 2");
				break;
		}
		return null;
	}
}
