package view;

import javax.swing.*;

import controller.ViewController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Game extends JPanel{ 
	
	private static final long serialVersionUID = 1L;
	
	private List<JLabel> lblListLeaderBoard = new ArrayList<>();
	private ViewController viewController;

	public Game(Container c,List<String> players,ViewController viewController){
		this.viewController = viewController;
		viewController.createPiece(players);
		Board field1 = new Board(viewController);
		SpringLayout layout = new SpringLayout();
		for(int i=0,j=1;i<players.size();i+=2,j++)
			lblListLeaderBoard.add(new JLabel(""+(j)+" "+players.get(i)));
		this.leaderBoard();
		JLabel dice1 = new JLabel();
		JLabel dice2 = new JLabel();
		JLabel result = new JLabel();
		JLabel equals = new JLabel("=");
		JButton btnBackToMenu = new JButton("Torna al Menu");
		JButton btnPlay = new JButton("Gioca");
		JButton btnExit = new JButton("Esci");
		
		this.setBackground(Color.WHITE);
		this.setPreferredSize(new Dimension(800,950));
		this.setLayout(layout);
		
		dice1.setPreferredSize(new Dimension(150,150));
		dice1.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
		dice2.setPreferredSize(new Dimension(150,150));
		dice2.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
		equals.setPreferredSize(new Dimension(120,120));
		result.setPreferredSize(new Dimension(150,150));
		btnPlay.setPreferredSize(new Dimension(400,100));
		btnBackToMenu.setPreferredSize(new Dimension(300,80));
		btnExit.setPreferredSize(new Dimension(300,80));
		lblListLeaderBoard.forEach(txt -> txt.setPreferredSize(new Dimension(350,150)));
		lblListLeaderBoard.forEach(txt -> txt.setFont(new Font("SansSerif", Font.PLAIN, 45)));
		
		result.setFont(new Font("SansSerif", Font.PLAIN, 105));
		equals.setFont(new Font("SansSerif", Font.PLAIN, 105));
		btnBackToMenu.setFont(new Font("SansSerif", Font.PLAIN, 40));
		btnExit.setFont(new Font("SansSerif", Font.PLAIN, 40));
		btnPlay.setFont(new Font("SansSerif", Font.PLAIN, 40));
        
		btnBackToMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewController.reset();
				Menu p = new Menu(c,viewController);
				c.removeAll();
				c.add(p);
				c.repaint();
				c.revalidate();
			}
		});
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)  {
				final JButton jb = (JButton)e.getSource();
				viewController.startTurn();
				viewController.play();
				dice1.setIcon(new ImageIcon("immagini/facceDadi/"+viewController.valueDice1()+".png"));
				dice2.setIcon(new ImageIcon("immagini/facceDadi/"+viewController.valueDice2()+".png"));
				result.setText("" +(viewController.valueDice1()+viewController.valueDice2()));
				field1.updateImages(c);
				if(viewController.ifIsAction()) {
					printActions(c);
					viewController.doActions();
					field1.imagesManagement(viewController.getPositionByCurrentPlayer(),viewController.getLastPositionByCurrentPlayer());
				}
				leaderBoard();
				if(viewController.isWin()) {
					jb.setEnabled(false);
					JOptionPane.showMessageDialog(c,"<html><h1>"+viewController.getCurrentPlayer().getName()+" ha vinto !!</h1></html>");
				}
			
			}
		});
		
		this.add(btnPlay);
		this.add(btnBackToMenu);
		this.add(btnExit);
		this.add(dice1);
		this.add(dice2);
		this.add(equals);
		this.add(result);
		for(JLabel  lbl:lblListLeaderBoard)
			this.add(lbl);
		this.add(field1);
		layout.putConstraint(SpringLayout.WEST, btnPlay, 1060, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, btnPlay, 530, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, dice1, 1210, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, dice1, 680, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, dice2, 1400, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, dice2, 680, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, equals, 1580, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, equals, 700, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, result, 1650, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, result, 680, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, btnBackToMenu, 1060, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, btnBackToMenu, 920, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, btnExit, 1560, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, btnExit, 920, SpringLayout.NORTH, this);
        int y=0;
        int x=0;
        for(JLabel  lbl:lblListLeaderBoard) {
        	layout.putConstraint(SpringLayout.WEST, lbl, 1110+x, SpringLayout.WEST, this);
        	layout.putConstraint(SpringLayout.NORTH, lbl, 40+y, SpringLayout.NORTH, this);
        	if(y == 300)
        	{
        		x=400;
        		y=0;
        	}
        	else	
        		y+=150;
        	
        }
	}
	
	// Mantiene aggiornata la classifica durante la partita
	public void leaderBoard() {
		for(int i=0;i<viewController.numberOfPlayers();i++) {
			lblListLeaderBoard.get(i).setText(""+(i+1)+" "+viewController.getPlayerForRank(i).getName()+" "+viewController.getPlayerForRank(i).getPosition());
			lblListLeaderBoard.get(i).setForeground(viewController.convertiColore(i));
		}
	}
	// Creo una finestra pop-up dove stanpo che azione fare il giocatore
	public void printActions(Container c) {
		String message = viewController.setMessages();
		JOptionPane.showMessageDialog(c,"<html><h1>"+viewController.getCurrentPlayer().getName()+" "+message+"</h1></html>");
	}
	
	
		
	
}
