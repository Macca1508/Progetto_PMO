package view;

import javax.swing.*;

import controller.ViewController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Regulation extends JPanel{
	
	private static final long serialVersionUID = 1L;

	public Regulation(Container c,ViewController viewController) {
		SpringLayout layout = new SpringLayout();
		JLabel lblRegulation = new JLabel("REGOLAMENTO");
		JLabel lblRules = new JLabel("<html> "
				+ "i giocatori a turno<br/>"
				+ "lanciano due dadi, poi spostano la propria pedina di un numero di caselle pari alla somma<br/>"
				+ "del lancio. Vince chi arriva per primo all’ultima casella con un lancio esatto, terminando<br/>"
				+ "il suo movimento sulla casella 63; se un giocatore ottiene un numero più alto di quello<br/>"
				+ "necessario per raggiungere l’ultima casella, dopo aver raggiunto la casella 63 dovrà tornare<br/>"
				+ "indietro. Esistono inoltre alcune caselle speciali, elencate di seguito.<br/>"
				+ "	STOP PER UN TURNO(18 ,19 ,26 ,36);<br/>"
				+ "	STOP PER DUE TURNI (31 ,52);<br/>"
				+ "	RITIRA I DADI (5 ,41 ,50 ,53 ,59);<br/>"
				+ "	TIRO 2X (14 ,32);<br/>"
				+ "	VAI DA .. A .. (6 ,9 ,23 ,42 ,45 ,54 ,58);</html>");
		JButton btnExit = new JButton("Esci");
		JButton btnBackToMenu = new JButton("Torna al Menù");
		
		this.setBackground(Color.WHITE);
		this.setLayout(layout);
		lblRegulation.setFont(new Font("SansSerif", Font.BOLD, 45));
		lblRules.setFont(new Font("SansSerif", Font.PLAIN, 25));
		btnBackToMenu.setFont(new Font("SansSerif", Font.PLAIN, 40));
		btnExit.setFont(new Font("SansSerif", Font.PLAIN, 40));
		btnExit.setPreferredSize(new Dimension(400, 90));
		btnBackToMenu.setPreferredSize(new Dimension(400, 90));
		
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnBackToMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu menu = new Menu(c,viewController);
				c.removeAll();
				c.add(menu);
				c.repaint();
				c.revalidate();
			}
		});
		this.add(lblRegulation);
		this.add(lblRules);
		this.add(btnBackToMenu);
		this.add(btnExit);

		layout.putConstraint(SpringLayout.WEST, lblRegulation, 836, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, lblRegulation, 110, SpringLayout.NORTH, this); 
		layout.putConstraint(SpringLayout.WEST, lblRules, 720, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, lblRules, 300, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, btnBackToMenu, 760, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, btnBackToMenu, 800, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, btnExit, 760, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, btnExit, 900, SpringLayout.NORTH, this);
	}

}
