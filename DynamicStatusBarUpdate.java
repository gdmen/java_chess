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
import java.awt.event.*;
import javax.swing.*;

public class DynamicStatusBarUpdate extends MouseAdapter
{
	private GUI gui;
	
	public DynamicStatusBarUpdate(GUI gui)
	{
		this.gui = gui;
	}
	
	public void mouseEntered(MouseEvent e)
	{
		JButton temp = (JButton)e.getComponent();
		gui.dynamicUpdateStatusBar(temp);
	}
	public void mouseExited(MouseEvent e)
	{
		gui.updateStatusBar(" ", false);
	}
}
