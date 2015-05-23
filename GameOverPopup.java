/*
G Chess version 1.0
Copyright (c) 2010 Gary Menezes

Copyright Notice
  You may use the accompanying code under the following conditions:
  You may:
    1. Use this code for non-commercial, educational, and personal purposes.
    2. Redistribute this code *as is* along with included copyright notices.
  You may not:
    1. Use this code for any commercial purpose.
    2. Create any derivative works for redistribution.
*/
import java.awt.*;
import javax.swing.*;

public class GameOverPopup extends PopupWindow
{
	private static final long serialVersionUID = 1400666790706351389L;
	private JLabel text;
	private JButton newGame, exit;
	
	public GameOverPopup(GUI parent, String title)
	{
		super(parent, title);
		
		text = new JLabel("The game ended in a "+title+"!");
		newGame = new JButton("New Game");
		exit = new JButton("Exit");
		newGame.setActionCommand("-10");
		exit.setActionCommand("-11");
		newGame.addActionListener(parent);
		exit.addActionListener(parent);
		
		JPanel top = new JPanel();
		top.setBackground(new Color(0,0,0,0));
		top.add(text);
		JPanel bottom = new JPanel();
		bottom.setBackground(new Color(0,0,0,0));
		bottom.add(newGame);
		bottom.add(exit);
		add(top);
		add(bottom);
		
		pack();
		resetLocation();
	}
}
