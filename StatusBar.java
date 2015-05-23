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
import java.io.Serializable;

public class StatusBar extends JLabel implements Serializable
{
	private static final long serialVersionUID = -7333825650716423683L;
	public StatusBar(Dimension size)
	{
		super(" ", JLabel.CENTER);
		setToolTipText("Dynamic Status Bar");
		setPreferredSize(size);
		setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.BLACK));
		setOpaque(true);
		setBackground(Color.WHITE);
		setFont(new Font("Courier", getFont().getStyle(), 20));
	}
	public void update(String text, boolean alert)
	{
		if(alert)
		{
			setBackground(Color.YELLOW);
			setForeground(Color.RED);
		}
		else
		{
			setBackground(Color.WHITE);
			setForeground(Color.BLACK);
		}
		setText(text);
	}
}
