package view;

import javax.swing.*;

import controller.ViewController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class UserRegistation extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private List<JTextField> txtList= new ArrayList<>();
	private List<JButton> btnList= new ArrayList<>();
	private List<String> players = new ArrayList<>();
	private List<Choice> chcList = new ArrayList<>();
	private SpringLayout layout;
	private JLabel lblTitle;
	private JButton btnGoToPlay;
	private JButton btnGoToMenu;
	private JButton btnExit;

	public UserRegistation(Container c,ViewController viewController){
		layout = new SpringLayout();
		lblTitle = new JLabel("Creazione giocatori");
		btnGoToPlay = new JButton("Inizia a giocare");
		btnGoToMenu = new JButton("Torna al Menu");
		btnExit = new JButton("Esci");
		
   	 	
		for(int i=0;i<6;i++) {
			txtList.add(new JTextField());
			btnList.add(new JButton("+"));
			chcList.add(new Choice());
		}
		for(int i=0;i<6;i++) {
			chcList.get(i).add("ORANGE");
			chcList.get(i).add("BLUE");
			chcList.get(i).add("YELLOW");
			chcList.get(i).add("RED");
			chcList.get(i).add("GREEN");
			chcList.get(i).add("PURPLE");
		}
		
		this.setBackground(Color.WHITE);
		this.setLayout(layout);
		
		btnGoToPlay.setPreferredSize(new Dimension(500,120));
		btnGoToMenu.setPreferredSize(new Dimension(500,120));
		btnExit.setPreferredSize(new Dimension(500,120));
		txtList.forEach(txt -> txt.setPreferredSize(new Dimension(400,100)));
		btnList.forEach(btn -> btn.setPreferredSize(new Dimension(100,100)));
		chcList.forEach(chc -> chc.setPreferredSize(new Dimension(150,100)));
		
		txtList.forEach(txt -> txt.setHorizontalAlignment(JLabel.CENTER));
		lblTitle.setHorizontalAlignment(JLabel.CENTER);
        
		lblTitle.setFont(new Font("SansSerif", Font.BOLD, 45));
		btnGoToPlay.setFont(new Font("SansSerif", Font.PLAIN, 40));
		btnGoToMenu.setFont(new Font("SansSerif", Font.PLAIN, 40));
		btnExit.setFont(new Font("SansSerif", Font.PLAIN, 40));
        btnList.forEach(btn -> btn.setFont(new Font("SansSerif", Font.PLAIN, 40)));
        txtList.forEach(txt -> txt.setFont(new Font("SansSerif", Font.BOLD, 30)));
        chcList.forEach(chc -> chc.setFont(new Font("SansSerif", Font.BOLD, 30)));
       
        
        btnGoToPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(players.size()>= 2){
					Game r = new Game(c,players,viewController);
					c.removeAll();
					c.add(r);
					c.repaint();
					c.revalidate();
				}
			}
		});
        btnGoToMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		
		btnList.forEach(btn -> btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final JButton jb = (JButton)e.getSource();
				int p = btnList.indexOf(jb);
				if((!players.contains(txtList.get(p).getText())) && (!txtList.get(p).getText().isEmpty()) && (players.stream().filter(n-> n.equals(chcList.get(p).getSelectedItem())).findAny().isEmpty()))                                               
				{
					players.add(txtList.get(p).getText());
					players.add(chcList.get(p).getSelectedItem());
					jb.setEnabled(false);
					chcList.get(p).setEnabled(false);
					txtList.get(p).setEditable(false);
				}else {
					JOptionPane.showMessageDialog(c,"<html>il nome o il colore non sono corretti</html>");
				}
			}
		}));
		
		this.add(btnGoToPlay);
		this.add(btnGoToMenu);
		this.add(btnExit);
		this.add(lblTitle);
		for(JTextField  txt:txtList)
			this.add(txt);
		
		for(Choice  chc:chcList)
			this.add(chc);
		
		for(JButton  btn:btnList)
			this.add(btn);
	
		layout.putConstraint(SpringLayout.WEST, btnGoToPlay, 1210, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, btnGoToPlay, 200, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, btnGoToMenu, 1210, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, btnGoToMenu, 360, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, btnExit, 1210, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, btnExit, 750, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, lblTitle, 800, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, lblTitle, 30, SpringLayout.NORTH, this);
        int agg=0;
        for(JTextField  txt:txtList) {
        	layout.putConstraint(SpringLayout.WEST, txt, 410, SpringLayout.WEST, this);
        	layout.putConstraint(SpringLayout.NORTH, txt, 140+agg, SpringLayout.NORTH, this);
        	agg+=150;
        }
        agg=0;
        for(JButton  btn:btnList) {
        	layout.putConstraint(SpringLayout.WEST, btn, 1060, SpringLayout.WEST, this);
        	layout.putConstraint(SpringLayout.NORTH, btn, 140+agg, SpringLayout.NORTH, this);
        	agg+=150;
        }
        agg=0;
        for(Choice  chc:chcList) {
        	layout.putConstraint(SpringLayout.WEST, chc, 860, SpringLayout.WEST, this);
        	layout.putConstraint(SpringLayout.NORTH, chc, 140+agg, SpringLayout.NORTH, this);
        	agg+=150;
        }
	}

}
