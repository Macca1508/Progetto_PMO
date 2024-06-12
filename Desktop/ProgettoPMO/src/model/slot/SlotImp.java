package model.slot;


public class SlotImp implements Slot{
	private final int slotName;
	
	 public SlotImp(int slotName) {
		 this.slotName=slotName;
	 }
	public int getSlotName() {
		return slotName;
	}
}
