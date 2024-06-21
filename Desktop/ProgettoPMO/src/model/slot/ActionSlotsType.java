package model.slot;

public enum ActionSlotsType {
	REROLL,GO_TO,STOP,DOUBLE_RESULT,SWAP;
	
	public static ActionSlotsType parseToActionSlotType(String s) {
		if(s.equals("REROLL"))
			return ActionSlotsType.REROLL;
		else if(s.equals("GOTO"))
			return ActionSlotsType.GO_TO;
		else if(s.equals("STOP"))
			return ActionSlotsType.STOP;
		else if(s.equals("DOUBLERESULT"))
			return ActionSlotsType.DOUBLE_RESULT;
		else if(s.equals("SWAP"))
			return ActionSlotsType.SWAP;
		else
			return null;
	}
}
