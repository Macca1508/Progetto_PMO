package view;

import javax.swing.*;

import controller.ViewController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Menu(Container c,ViewController viewController) {
		SpringLayout layout = new SpringLayout();
		JLabel lblWelcome = new JLabel("Benvenuto al gioco dell'oca");
		JButton btnGoToRegistation = new JButton("Registrazione utenti");
		JButton btnRegulation = new JButton("Regolamento");
		JButton btnExit = new JButton("Esci");
		
		this.setBackground(Color.WHITE);
		this.setLayout(layout);
		
		lblWelcome.setFont(new Font("SansSerif", Font.BOLD, 45));
		lblWelcome.setHorizontalAlignment(JLabel.CENTER);
		
		btnGoToRegistation.setPreferredSize(new Dimension(500,120));
		btnRegulation.setPreferredSize(new Dimension(500,120));
		btnExit.setPreferredSize(new Dimension(500,120));
		btnGoToRegistation.setFont(new Font("SansSerif", Font.PLAIN, 40));
		btnRegulation.setFont(new Font("SansSerif", Font.PLAIN, 40));
		btnExit.setFont(new Font("SansSerif", Font.PLAIN, 40));
		
        btnGoToRegistation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserRegistation r = new UserRegistation(c,viewController);
				c.removeAll();
				c.add(r);
				c.repaint();
				c.revalidate();
			}
		});
        btnRegulation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Regulation p = new Regulation(c,viewController);
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
		
		this.add(lblWelcome);
		this.add(btnGoToRegistation);
		this.add(btnRegulation);
		this.add(btnExit);
		
		layout.putConstraint(SpringLayout.WEST, lblWelcome, 660, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, lblWelcome, 100, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, btnGoToRegistation, 710, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, btnGoToRegistation, 200, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, btnRegulation, 710, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, btnRegulation, 360, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, btnExit, 710, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, btnExit, 750, SpringLayout.NORTH, this);
	}
}
