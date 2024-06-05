package view;

import javax.swing.*;

import controller.ViewController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Board extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int delay = 600;
	private List<JLabel> lblListField = new ArrayList<>();
	private ViewController viewController;
	
	public Board(ViewController viewController) {
		this.viewController = viewController;
		this.setLayout(new GridLayout(8,8));
		for(int i=0;i<64;i++)
			lblListField.add(new JLabel());
		for(int i=0;i<64;i++)
			lblListField.get(i).setIcon(new ImageIcon("immagini/"+convertion1(i)+".png"));
		lblListField.forEach(txt -> txt.setPreferredSize(new Dimension(126,126)));
		lblListField.forEach(txt -> txt.setBorder(BorderFactory.createLineBorder(Color.BLACK,2)));
		for(JLabel  lbl:lblListField)
			this.add(lbl);
	}
	
	public List<JLabel> getLblField() {
		return lblListField;
	}
	
	// crea una il riferimneto per muoversi a spirale
	private int[][] spiralReferenceMove() {
		int [][] result = new int [8][8];	
		int i=0;
		int lMax=7;
		int lMin=0;
		int row=0;
		int col=0;
		do {
			for(;col<=lMax;i++,col++) 
				result[row][col]=i ;
			for(col--,row++;row<=lMax;row++,i++) 
				result[row][col]=i;
			for(row--,col--;col>=lMin;col--,i++) 
				result[row][col]=i;
			lMin++;
			for(col++,row--;row>=lMin;row--,i++) 
				result[row][col]=i;
			col++;
			row++;
			lMax--;
		}while(i<=63);
		return result;
	}
	// dato un numero restituisce il numero effettivo della casella  Es. 63 == 14
	private int convertion1(int val) {
		int b[][] = spiralReferenceMove();
		int a= val/8; 
		int c= val%8;
		return b[a][c];
	}
	// dato un numero restituisce il numero della casella corrispondete  Es. 14 == 63
	private int convertion2(int val) {
		int b[][] = spiralReferenceMove();
		for (int h = 0; h < b.length; h++) 
            for (int j = 0; j < b[h].length; j++) 
                if(b[h][j]==val)
                	return 8*h+j;
		return 0;
	}
	// Imposta le immagini nelle varie caselle
	public void imagesManagement(int currentPosition,int lastPosition) {
		lblListField.get(convertion2(currentPosition)).setIcon(new ImageIcon("immagini/personaggi/"+controlSlot(currentPosition,1)+".png"));
		if(viewController.getPositionByCurrentPlayer() == lastPosition && !viewController.getDirection())
			lblListField.get(convertion2(lastPosition)).setIcon(new ImageIcon("immagini/"+lastPosition+".png"));
		else if(viewController.thereArePlayer(lastPosition)) 
			lblListField.get(convertion2(lastPosition)).setIcon(new ImageIcon("immagini/personaggi/"+controlSlot(lastPosition,0)+".png"));
		else
			lblListField.get(convertion2(lastPosition)).setIcon(new ImageIcon("immagini/"+lastPosition+".png"));
		
	}
	// Getsisce le immagini nel intero movimento di una pedina 
	public void updateImages(Container c) {
		for(int i=1,h=0;i<=viewController.valueDiceTot();i++) {
			if(viewController.getLastPositionByCurrentPlayer()+i-1<63) {
				imagesManagement(viewController.getLastPositionByCurrentPlayer()+i,viewController.getLastPositionByCurrentPlayer()+i-1);
				this.animation(viewController.getLastPositionByCurrentPlayer()+i,viewController.getLastPositionByCurrentPlayer()+i-1);
			}else {
				h+=2;
				imagesManagement(viewController.getLastPositionByCurrentPlayer()+i-h,viewController.getLastPositionByCurrentPlayer()+1+i-h);
				this.animation(viewController.getLastPositionByCurrentPlayer()+1+i-h,viewController.getLastPositionByCurrentPlayer()+i-h);
			}
		}
	}
	// Restituisce una stringa con le presenze delle pedine sulla casella in esame 
	public String controlSlot(int position,int cause) {
		String result="";
		Set<Integer> col = new HashSet<>();
		col = viewController.pieceInTheSlots(position);
		for(int i=0; i<6;i++) {
			if(i == viewController.getCurrentPlayer().getC().getNumber() && cause==1 && viewController.getCurrentPlayer().getPosition()!= position)
				result += "1";
			else {
				if(col.contains(i))
					result += "1";
				else
					result += "0";
			}
		}
		return result;
	} 
	// Serve per realizzare il delay tra uno spostamento e l'altro 
	public void animation(int pFinale,int pIniziale) {
		JOptionPane pane = new JOptionPane("Spostamento da "+pIniziale+" a "+ pFinale, JOptionPane.INFORMATION_MESSAGE);
        JDialog dialog = pane.createDialog("Spostamento");
        dialog.setLocation(1560, 530);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        Timer timer = new Timer(Board.delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
        timer.setRepeats(false);
        timer.start();
        dialog.setVisible(true);
	}
}
