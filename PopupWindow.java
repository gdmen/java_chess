/*
G Chess version 1.0
Copyright (c) 2010 Gary Menezes

Copyright Notice
  You may not use this code for any commercial purpose.
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
