package model.pieces;

public enum Colors {
	 ORANGE(0)
	,BLUE(1)
	,YELLOW(2)
	,RED(3)
	,GREEN(4)
	,PURPLE(5);
	private int number;
	
	Colors(int number) {
		this.number=number;
	}

	public int getNumber() {
		return number;
	}
}
