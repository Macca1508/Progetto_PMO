package model;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import model.piece.PieceImp;

public class TheBigDuck {
	private int turn;
	private boolean isAlive;
	private List<PieceImp> players;
	
	public TheBigDuck() {
		this.turn = 0;
		this.isAlive = false;
	}
	
	public void start() {
		if(!this.isAlive && this.turn>= 4)
			this.isAlive = true;
	}
	
	private void restart() {
			this.players.forEach(player -> player.setLastPosition(player.getPosition()));	
			this.players.forEach(player -> player.setPosition(0));
	}
	public void setPlayer(Set<PieceImp> player) {
		this.players = player.stream().collect(Collectors.toList());
	}
	public void increseTurn() {
		this.turn++;
	}
	public void doTheMagic(int magicNumber) {
		if(isAlive) {
			switch(magicNumber) {
				case 1:
					System.out.println("magia");
					this.restart();
					break;
				case 2:
					break;
				case 3:
					break;
				case 4:
					break;
				case 5:
					break;
				case 6:
					break;
				case 7:
					break;
				case 8:
					break;
				case 9:
					break;
				case 10:
					break;
				case 11:
					break;
				case 12:
					break;
			}
		}
	}
}
