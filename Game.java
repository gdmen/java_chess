/*
G Chess version 1.0
Copyright (c) 2010 Gary Menezes

Copyright Notice
  You may not use this code for any commercial purpose.
*/
import javax.swing.SwingUtilities;


public class Game {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				GUIRunner run = new GUIRunner();
				run.setVisible();
			}
		});
	}
}
