package view;

import javax.swing.*;

import controller.ViewController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Regulation extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private SpringLayout layout;
	private JLabel lblRegulation;
	private JLabel lblRegulation2;
	private JButton btnExit;
	private JButton btnGoToRegistation;

	public Regulation(Container c,ViewController viewController) {
		layout = new SpringLayout();
		lblRegulation = new JLabel();
		lblRegulation2 = new JLabel();
		btnExit = new JButton("Esci");
		btnGoToRegistation = new JButton("Registrazione utenti");
		
		this.setBackground(Color.WHITE);
		this.setLayout(layout);

		btnGoToRegistation.setFont(new Font("SansSerif", Font.PLAIN, 40));
		btnExit.setFont(new Font("SansSerif", Font.PLAIN, 40));
		btnExit.setPreferredSize(new Dimension(400, 90));
		btnGoToRegistation.setPreferredSize(new Dimension(400, 90));
		lblRegulation.setIcon( new ImageIcon("Immagini/Regolamento.png"));
		lblRegulation2.setIcon( new ImageIcon("Immagini/RegolamentoP2.png"));
		
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
		this.add(lblRegulation2);
		this.add(btnGoToRegistation);
		this.add(btnExit);

		layout.putConstraint(SpringLayout.WEST, lblRegulation, 375, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, lblRegulation, 20, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, lblRegulation2, 1005, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, lblRegulation2, 20, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, btnGoToRegistation, 540, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, btnGoToRegistation, 840, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, btnExit, 980, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, btnExit, 840, SpringLayout.NORTH, this);
	}

}
