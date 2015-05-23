/*
G Chess version 1.0
Copyright (c) 2010 Gary Menezes

Copyright Notice
  You may not use this code for any commercial purpose.
*/
import java.awt.*;
import javax.swing.*;

public class PromotionPopup extends PopupWindow
{
	private static final long serialVersionUID = 5337411370661157823L;

	public PromotionPopup(GUI listener)
	{
		super(listener, "Promote Pawn To:");
		
		setLayout(new GridLayout(2,2));
		
		JButton[] pieces = new JButton[4];
		pieces[0] = new JButton("Queen");
		pieces[1] = new JButton("Rook");
		pieces[2] = new JButton("Bishop");
		pieces[3] = new JButton("Knight");
		
		for(int x=0; x<pieces.length; x++)
		{
			pieces[x].addActionListener(listener);
			pieces[x].setActionCommand("-"+(x+1));
			add(pieces[x]);
		}
		
		pack();
		resetLocation();
	}
}
