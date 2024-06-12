package test;

import view.Menu;

import controller.Controller;
import view.MyFrame;

public class ViewTest {

	public static void main(String[] args) {
		// Commentgare riga 30 e 35 nella classe Game per escudere completamente il model 
		// per poter visualizzare l'ultima schemata della view 
		Controller c = new Controller();
		MyFrame f = new MyFrame();
		Menu m = new Menu(f.getContentPane(), c);
		f.getContentPane().add(m);
		f.setVisible(true);
	}

}
