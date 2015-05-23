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

public class PopupWindow extends JDialog
{
	private static final long serialVersionUID = -6382879648233899539L;
	private Point parentLoc;
	private GUI parent;
	public PopupWindow(GUI parent, String title)
	{
		super(parent,title);
		this.parent = parent;
		setSize(300,100);
		if(parent.isVisible())
		{
			parentLoc = parent.getLocationOnScreen();
			setLocation((int)parentLoc.getX()+(parent.getWidth()/2-getSize().width/2), (int)parentLoc.getY()+(parent.getHeight()/2-getSize().height/2));
		}
		setResizable(false);
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}
	public void resetLocation()
	{
		if(parent.isVisible())
			setLocation((int)parentLoc.getX()+(parent.getWidth()/2-getSize().width/2), (int)parentLoc.getY()+(parent.getHeight()/2-getSize().height/2));
	}
}
