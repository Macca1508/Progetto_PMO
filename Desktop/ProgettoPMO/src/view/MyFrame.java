package view;

import javax.swing.JFrame;

public class MyFrame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MyFrame(){
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}


}
