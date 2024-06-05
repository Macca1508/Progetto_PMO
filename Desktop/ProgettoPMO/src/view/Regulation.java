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
		JLabel lblRegulation = new JLabel();
		JButton btnExit = new JButton("Esci");
		JButton btnGoToRegistation = new JButton("Registrazione utenti");
		
		this.setBackground(Color.WHITE);
		this.setLayout(layout);

		btnGoToRegistation.setFont(new Font("SansSerif", Font.PLAIN, 40));
		btnExit.setFont(new Font("SansSerif", Font.PLAIN, 40));
		btnExit.setPreferredSize(new Dimension(400, 90));
		btnGoToRegistation.setPreferredSize(new Dimension(400, 90));
		lblRegulation.setIcon( new ImageIcon("Immagini/regolamento.png"));
		
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnGoToRegistation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserRegistation userR = new UserRegistation(c,viewController);
				c.removeAll();
				c.add(userR);
				c.repaint();
				c.revalidate();
			}
		});
		this.add(lblRegulation);
		this.add(btnGoToRegistation);
		this.add(btnExit);

		layout.putConstraint(SpringLayout.WEST, lblRegulation, 660, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, lblRegulation, 20, SpringLayout.NORTH, this); 
		layout.putConstraint(SpringLayout.WEST, btnGoToRegistation, 540, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, btnGoToRegistation, 840, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, btnExit, 980, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, btnExit, 840, SpringLayout.NORTH, this);
	}

}
