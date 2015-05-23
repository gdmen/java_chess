/*
G Chess version 1.0
Copyright (c) 2010 Gary Menezes

Copyright Notice
  You may not use this code for any commercial purpose.
*/
import javax.swing.*;

public class Menu extends JMenuBar
{
	private static final long serialVersionUID = -2707635993178862413L;

	public Menu(GUI parent)
	{
		//File Menu
		JMenu file = new JMenu("File");
		JMenuItem exit = new JMenuItem("Exit");
		exit.setActionCommand("-11");
		exit.addActionListener(parent);
		file.add(exit);

		//Game Menu
		JMenu game = new JMenu("Game");
		JMenuItem newGame = new JMenuItem("New Game");
		newGame.setActionCommand("-10");
		newGame.addActionListener(parent);
		game.add(newGame);

		//Options Menu
		JMenu options = new JMenu("Options");
		JCheckBoxMenuItem flip = new JCheckBoxMenuItem("flip board every turn");
		flip.setActionCommand("-13");
		flip.addActionListener(parent);
		options.add(flip);
		
		//Help Menu
		JMenu help = new JMenu("Help");
		JMenuItem rules = new JMenuItem("Rules!");
		rules.setActionCommand("-14");
		rules.addActionListener(parent);
		help.add(rules);
		
		add(file);
		add(game);
		add(options);
		add(help);
	}
}
