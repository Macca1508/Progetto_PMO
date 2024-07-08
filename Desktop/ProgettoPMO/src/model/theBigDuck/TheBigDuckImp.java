package model.theBigDuck;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import model.piece.PieceImp;

public class TheBigDuckImp {
	private int turn;
	private boolean isAlive;
	private List<PieceImp> players;
	private Random rnd;
	private boolean restart;
	private String message;
	private static TheBigDuckImp bigDuck;
	private int test;
	
	private TheBigDuckImp() {
		this.turn = 0;
		this.isAlive = false;
		this.rnd = new Random();
		this.restart =true;
		this.message = "";
		this.test=0;
	}
	// Solo per test Junit
	public void forJunitTest (int n) { 
		test=n;
	}
	// Solo per test Junit
	public void kill() {
		this.isAlive =false;
	}
	public int getTurn() {
		return turn;
	}
	public boolean isAlive() {
		return isAlive;
	}
	public boolean isRestart() {
		return restart;
	}
	// Riporta the big duck allo stato iniziale
	public void reset() {
		this.turn=0;
		this.restart= true;
		this.message = "";
		this.isAlive =false;
	}
	public static TheBigDuckImp createTheBigDuck () {
		if(bigDuck == null)
			bigDuck = new TheBigDuckImp();
		return bigDuck;
	}
	// Attiva the big duck 
	public void start() {
		if((!this.isAlive) && this.players.stream().filter(player -> player.getPosition() >= 55 ).findFirst().isPresent())
			this.isAlive = true;
	}
	// Restituisce i mesaggio fatto dai vari metodi
	public String getMessage() {
		String tmp;
		tmp = this.message;
		this.message = "";
		return tmp;
	}
	private void invincibleDec() {
		this.players.forEach(player -> player.privilegeDec());
	}
	private void boostMalusDec() {
		this.players.forEach(player -> player.boostMalusDec());
	}
	public void setPlayer(Set<PieceImp> player) {
		this.players = player.stream().collect(Collectors.toList());
	}
	// Incrementa il turno 
	public void increseTurn() {
		if(this.isAlive)
			this.turn++;
	}
	// Metodo che ogni due turni esegue una funzione casuele tra le 12 
	public void doTheMagic() {
		int action;
		this.invincibleDec();
		this.boostMalusDec();
		if(isAlive && (this.turn)%2 == 0) {
			if (test != 0) // controllo solo per fare test junit
				action = test;
			else if(this.restart) // controllo per fare eseguere la funzione restart dall'inizio
				action=this.rnd.nextInt(1, 13);
			else 
				action=this.rnd.nextInt(2, 13);
			switch(action) {
				case 1:
					this.restart();
					this.restart=false;
					break;
				case 2:
					this.invincible();
					break;
				case 3:
					this.restartForOne();
					break;
				case 4:
					this.plusTen();
					break;
				case 5:
					this.minusTen();
					break;
				case 6:
					this.goToEnd();
					break;
				case 7:
					this.multiStop();
					break;
				case 8:
					this.casualGoTo();
					break;
				case 9:
					this.boostThrow();
					break;
				case 10:
					this.oddStop();
					break;
				case 11:
					this.evenReroll();
					break;
				case 12:
					this.boostMalus();
					break;
			}
			test=0;
		}
	}
	// Tutti i giocatori torneranno nella casella iniziale
	private void restart() {
		this.message ="Evento n.1 - Ritorno al principio totale<br>"; 
		this.players.forEach(player -> player.setLastPosition(player.getPosition()));	
		this.players.forEach(player -> player.setPosition(0));
		this.players.forEach(player -> player.resetBoostThrow());
		this.players.forEach(player -> player.resetPrivilege());
		this.players.forEach(player -> player.resetPrivilege());
		this.message += "ogni giocatore tornera alla casella iniziale";
	}
	// Un giocatore scelto casualmente tornerà alla casella iniziale 
	private void restartForOne() {
		this.message ="Evento n.3 - Ritorno al principio<br>"; 
		int n = this.rnd.nextInt(this.players.size());
		if(this.players.get(n).getPrivilege() == 0) {
			this.players.get(n).setLastPosition(this.players.get(n).getPosition());
			this.players.get(n).setPosition(0);
			this.message += this.players.get(n).getName()+" tornerà alla casella iniziale";
		}else
			this.message += this.players.get(n).getName()+" non tornerà alla casella iniziale grazie alla sua invincibilità";
	}
	// Un giocatore sarà invincibile(tutti i malus sono nulli) per 3 turni
	private void invincible() {
		this.message ="Evento n.2 - God mode on\n"; 
		PieceImp tmp = this.players.get(this.rnd.nextInt(this.players.size()));
		tmp.setPrivilege(3);
		this.message += tmp.getName()+ " sarà invincibile per 3 turni";
	}
	// Tutti i giocatori andranno avanti di 10 posizioni
	private void plusTen() {
		this.message ="Evento n.4 - Più 10<br>"; 
		this.players.forEach(player -> player.setLastPosition(player.getPosition()));	
		for(PieceImp player:players) {
			if(player.getPosition()+10 > 99)
				player.setPosition(99 - player.getPosition()-89);
			else
				player.setPosition(player.getPosition()+10);
		}
		this.message += "ogni giocatore andrà avanti di 10";
	}
	// Tutti i giocatori andranno indietro di 10 posizioni, se invincinili di 0, se con malus di 20
	private void minusTen() {
		this.message = "Evento n.5 - Meno 10 se va bene<br>"; 
		for(PieceImp player:players) {
			if(player.getPrivilege() == 0) {
				player.setLastPosition(player.getPosition());
				if(player.getBoostMalus() >0) {
					if(player.getPosition()-20 < 0) 
						player.setPosition(0);
					else
						player.setPosition(player.getPosition()-20);
					this.message += player.getName()+" va in dietro di massimo 20 caselle,";
				}else {
					if(player.getPosition()-10 < 0) 
						player.setPosition(0);
					else
						player.setPosition(player.getPosition()-10);
					this.message += player.getName()+" va in dietro di 10 caselle,";
				}
			}else
				this.message += player.getName()+" è invincibile,";
		}
	}
	// Sposta un giocatore a caso alla casella 97
	private void goToEnd() {
		this.message = "Evento n.6 - Verso la fine<br>"; 
		int n = this.rnd.nextInt(this.players.size());
		this.players.get(n).setLastPosition(this.players.get(n).getPosition());
		this.players.get(n).setPosition(97);
		this.message += this.players.get(n).getName()+" andrà alla casella 97";
	}
	// Ferma per 3-5 turni un giocatore a caso
	private void multiStop() {
		this.message = "Evento n.7 - Ancora Stop<br>"; 
		int n = this.rnd.nextInt(this.players.size());
		int stopTurn = this.rnd.nextInt(3,6);
		if(this.players.get(n).getPrivilege() == 0)
			if(this.players.get(n).getBoostMalus() ==0) {
				for(int i=0;i<stopTurn;i++)  
					this.players.get(n).canThrowDec();
				this.message += this.players.get(n).getName()+ " starà fermo per "+stopTurn+" turni";
			}else {
				for(int i=0;i<stopTurn*2;i++)  
					this.players.get(n).canThrowDec();
				this.message += this.players.get(n).getName()+ " starà fermo per "+stopTurn*2+" turni";
			}
		else
			this.message += this.players.get(n).getName()+ " è invincibile e può essere fermato";
	}
	// Sposta un giocatore a caso in una qualsiasi casella nella mappa
	private void casualGoTo() {
		this.message = "Evento n.8 - Viaggio nella mappa<br>"; 
		int position = 0;
		int n = this.rnd.nextInt(this.players.size());
		this.players.get(n).setLastPosition(this.players.get(n).getPosition());
		do {
			position = this.rnd.nextInt(1,99);
		}while(this.players.get(n).getPosition() == position);
		this.players.get(n).setPosition(position);
		this.message += this.players.get(n).getName()+" andrà nella casella "+position;
		
	}
	// Sposta un giocatore a caso avrà un bonus nel lancio per i prossimi 3
	private void boostThrow() {
		this.message = "Evento n.9 - Tiro di potenza<br>"; 
		PieceImp tmp = this.players.get(this.rnd.nextInt(this.players.size()));
		tmp.setBoostThrow(3);
		this.message += tmp.getName()+ " avrà un potenziamento nel tiro per 3 turni";
	}
	// Tutti i giocatori nelle caselle dispari saranno fermati per un turno
	private void oddStop() {
		this.message = "Evento n.10 - Dispari sfortunato<br>"; 
		if(players.stream().filter(player->player.getPosition()%2 == 1).count() != 0) {
			for(PieceImp player: players.stream().filter(player->player.getPosition()%2 == 1).toList()) {
				if(player.getPrivilege() ==0) {
					if(player.getBoostMalus() >0) {
						player.canThrowDec();
						player.canThrowDec();
						this.message +=	player.getName()+" starà fermo per 2 turni";
					}else {
						player.canThrowDec();
						this.message +=	player.getName()+" starà fermo per 1 turno,";
					}
				}else 
					this.message +=	player.getName()+" è invincibile e può essere fermato,";
			}
		}else
			this.message +="Non ci sono giocatori in posizione dispari";
	}
	// Tutti i giocatori nelle caselle pari tireranno una volta in più
	private void evenReroll() {
		this.message = "Evento n.11 - Pari fortunato<br>"; 
		if(players.stream().filter(player->player.getPosition()%2 == 0).count() != 0) {
			this.players.stream()
						.filter(player->player.getPosition()%2 == 0)
						.forEach(player -> player.canThrowInc());	
			for(PieceImp player: players.stream().filter(player->player.getPosition()%2 == 0).toList()) {
				this.message +=	player.getName()+", ";
			}
			if(players.stream().filter(player->player.getPosition()%2 == 0).count() > 1)
				this.message += "tireranno di nuovo";
			else
				this.message += "tirerà di nuovo";
		}else 
			this.message +="Non ci sono giocatori in posizione pari";
	}
	// Per 4 turni un giocatore scelto casualmente avrà tutti i malus radoppiati
	private void boostMalus() {
		this.message = "Evento n.12 - La sfortuna si abbatte<br>";
		int n = this.rnd.nextInt(this.players.size());
		if(this.players.get(n).getPrivilege() == 0) {
			this.players.get(n).addBoostMalus(4);
			this.message += "i malus di "+this.players.get(n).getName()+" saranno raddoppiati per 4 turni";
		}else
			this.message += this.players.get(n).getName()+" non tornerà alla casella iniziale grazie alla sua invincibilità";
	}
	
}
