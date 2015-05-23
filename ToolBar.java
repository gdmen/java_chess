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

public class ToolBar extends JPanel
{
	private static final long serialVersionUID = 956862821780246300L;

	public ToolBar(GUI listener, Dimension size)
	{
		setPreferredSize(size);
		setOpaque(true);
		setBackground(new Color(51,102,255));
		
		JPanel title = new JPanel();
		title.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		title.setLayout(new BorderLayout());
		title.setPreferredSize(new Dimension(size.width-5, 60));
		title.setOpaque(true);
		title.setBackground(new Color(46,184,0));
		
		JLabel title1 = new JLabel("TOOL", JLabel.CENTER);
		title1.setPreferredSize(new Dimension(size.width-5, 30));
		title1.setFont(new Font("Courier", title1.getFont().getStyle(), 20));
		title1.setOpaque(true);
		title1.setBackground(new Color(46,184,0));
		JLabel title2 = new JLabel("BAR", JLabel.CENTER);
		title2.setFont(new Font("Courier", title2.getFont().getStyle(), 20));
		title2.setPreferredSize(new Dimension(size.width-5, 30));
		title.add(title1, BorderLayout.NORTH);
		title.add(title2, BorderLayout.SOUTH);
		
		JButton flip = new JButton("FLIP");
		flip.setPreferredSize(new Dimension(size.width-5, 60));
		flip.setBorder(BorderFactory.createRaisedBevelBorder());
		flip.setBackground(Color.WHITE);
		flip.setToolTipText("Turn Board 180 Degrees");
		
		JButton undo = new JButton("UNDO");
		undo.setPreferredSize(new Dimension(size.width-5, 60));
		undo.setBorder(BorderFactory.createRaisedBevelBorder());
		undo.setBackground(Color.LIGHT_GRAY);
		undo.setToolTipText("Undo Move");
		
		JButton highlight = new JButton("HIGHLIGHT");
		highlight.setPreferredSize(new Dimension(size.width-5, 60));
		highlight.setBorder(BorderFactory.createRaisedBevelBorder());
		highlight.setBackground(Color.WHITE);
		highlight.setToolTipText("Toggle Move Highlighting");
		
		JButton newGameButton = new JButton("NEW");
		newGameButton.setPreferredSize(new Dimension(size.width-5, 60));
		newGameButton.setBorder(BorderFactory.createRaisedBevelBorder());
		newGameButton.setBackground(Color.LIGHT_GRAY);
		newGameButton.setToolTipText("New Game");
		
		JButton exitButton = new JButton("EXIT");
		exitButton.setPreferredSize(new Dimension(size.width-5, 60));
		exitButton.setBorder(BorderFactory.createRaisedBevelBorder());
		exitButton.setBackground(Color.WHITE);
		exitButton.setToolTipText("Exit G Chess");
		
		flip.setActionCommand("-20");
		undo.setActionCommand("-21");
		highlight.setActionCommand("-22");
		newGameButton.setActionCommand("-10");
		exitButton.setActionCommand("-11");
		
		flip.addActionListener(listener);
		undo.addActionListener(listener);
		highlight.addActionListener(listener);
		newGameButton.addActionListener(listener);
		exitButton.addActionListener(listener);
		
		add(title);
		
		/*JPanel one = new JPanel();
		one.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		one.setLayout(new BorderLayout());
		one.setPreferredSize(new Dimension(size.width-5, 70));
		JLabel flipLabel = new JLabel("FLIP SIDE", JLabel.CENTER);
		flipLabel.setFont(new Font("Monospaced", flipLabel.getFont().getStyle(), 11));
		one.add(flipLabel, BorderLayout.NORTH);
		one.add(flip, BorderLayout.SOUTH);
		
		JPanel two = new JPanel();
		two.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		two.setLayout(new BorderLayout());
		two.setPreferredSize(new Dimension(size.width-5, 70));
		JLabel undoLabel = new JLabel("UNDO MOVE", JLabel.CENTER);
		undoLabel.setFont(new Font("Monospaced", undoLabel.getFont().getStyle(), 11));
		two.add(undoLabel, BorderLayout.NORTH);
		two.add(undo, BorderLayout.SOUTH);
		
		JPanel three = new JPanel();
		three.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		three.setLayout(new BorderLayout());
		three.setPreferredSize(new Dimension(size.width-5, 70));
		JLabel highlightLabel = new JLabel("HIGHLIGHT", JLabel.CENTER);
		highlightLabel.setFont(new Font("Monospaced", highlightLabel.getFont().getStyle(), 11));
		three.add(highlightLabel, BorderLayout.NORTH);
		three.add(highlight, BorderLayout.SOUTH);
		
		JPanel four = new JPanel();
		four.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		four.setLayout(new BorderLayout());
		four.setPreferredSize(new Dimension(size.width-5, 70));
		JLabel newGameLabel = new JLabel("NEW GAME", JLabel.CENTER);
		newGameLabel.setFont(new Font("Monospaced", newGameLabel.getFont().getStyle(), 11));
		four.add(newGameLabel, BorderLayout.NORTH);
		four.add(newGameButton, BorderLayout.SOUTH);
		
		JPanel five = new JPanel();
		five.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		five.setLayout(new BorderLayout());
		five.setPreferredSize(new Dimension(size.width-5, 70));
		JLabel exitLabel = new JLabel("EXIT", JLabel.CENTER);
		exitLabel.setFont(new Font("Monospaced", exitLabel.getFont().getStyle(), 11));
		five.add(exitLabel, BorderLayout.NORTH);
		five.add(exitButton, BorderLayout.SOUTH);*/
				
		add(flip);
		add(undo);
		add(highlight);
		add(newGameButton);
		add(exitButton);
	}
}
