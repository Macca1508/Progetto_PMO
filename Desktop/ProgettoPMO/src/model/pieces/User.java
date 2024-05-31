package model.pieces;

public abstract class User {
	private final String name;
	
	public User(final String name) {
		this.name=name;
	}
	public String getName() {
		return name;
	}
}
