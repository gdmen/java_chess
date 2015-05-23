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
import java.util.ArrayList;
import javax.swing.*;
import java.io.Serializable;

public class PGNDisplay extends JPanel implements Serializable
{
	private static final long serialVersionUID = -454150266407155639L;
	private JPanel pgnPanel;
	private ArrayList<JLabel> pgn;
	private JLabel pgnLabel;
	
	public PGNDisplay(Dimension size)
	{
		pgn = new ArrayList<JLabel>();
		pgnLabel = new JLabel();
		setPreferredSize(size);
		setOpaque(true);
		setBackground(new Color(51,102,255));
		
		JLabel pgnTitle = new JLabel("G Notation", JLabel.CENTER);
		pgnTitle.setPreferredSize(new Dimension(size.width-5,38));
		pgnTitle.setFont(new Font("Courier", pgnTitle.getFont().getStyle(), 20));
		pgnTitle.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		pgnTitle.setOpaque(true);
		pgnTitle.setBackground(new Color(46,184,0));
		
		JLabel whiteBlack = new JLabel("  White     :     Black  ", JLabel.CENTER);
		whiteBlack.setFont(new Font(whiteBlack.getFont().getFontName(), whiteBlack.getFont().getStyle(), 12));
		whiteBlack.setPreferredSize(new Dimension(160,12));
		
		pgnPanel = new JPanel();
		pgnPanel.setLayout(new BoxLayout(pgnPanel, BoxLayout.PAGE_AXIS));

		JScrollPane pgnScroll = new JScrollPane(pgnPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		pgnScroll.setPreferredSize(new Dimension(size.width, size.height-50));
		
		add(pgnTitle);
		add(whiteBlack);
		add(pgnScroll);
	}
	
	
	public void updatePGN(Location start, Location end, boolean turn)
	{
		unfillPGN();
		String temp = pgnLabel.getText();
		if(turn)
		{
			pgnLabel = new JLabel(" ", JLabel.LEFT);
			pgnLabel.setOpaque(true);
			pgnLabel.setFont(new Font("Monospaced", pgnLabel.getFont().getStyle(), pgnLabel.getFont().getSize()));
			pgnLabel.setForeground(Color.BLACK);
			if(pgn.size()%2==0)
				pgnLabel.setBackground(Color.WHITE);
			else
				pgnLabel.setBackground(Color.LIGHT_GRAY);
			pgn.add(pgnLabel);
			if(pgn.size()>99)
				pgnLabel.setText(pgn.size()+"."+start+" "+end+" :          ");
			else if(pgn.size()>9)
				pgnLabel.setText(pgn.size()+". "+start+" "+end+"  :         ");
			else
				pgnLabel.setText(pgn.size()+".  "+start+" "+end+"  :         ");
		}
		else
		{
			if(pgn.size()>99)
				pgnLabel.setText(temp.trim()+"  "+start+" "+end+"  ");
			else if(pgn.size()>9)
				pgnLabel.setText(temp.trim()+"  "+start+" "+end+"  ");
			else
				pgnLabel.setText(temp.trim()+"  "+start+" "+end+"  ");
		}
		fillPGN();
		redisplayPGN();
	}
	
	public void redisplayPGN()
	{
		pgnPanel.removeAll();
		for(JLabel l : pgn)
			pgnPanel.add(l);
	}
	
	public void backPGN(boolean turn)
	{
		unfillPGN();
		if(!turn && !pgn.isEmpty())
		{
			pgnLabel = pgn.get(pgn.size()-1);
			pgnLabel.setText(pgnLabel.getText().substring(0,pgnLabel.getText().indexOf(":")+1)+"         ");
		}
		else
			pgn.remove(pgn.size()-1);
		fillPGN();
		redisplayPGN();
	}
	
	public void fillPGN()
	{
		while(pgn.size()<24)
		{
			JLabel fillLabel = new JLabel(" ", JLabel.LEFT);
			pgn.add(fillLabel);
			if(pgn.size()>9)
				fillLabel.setText(pgn.size()+".                  ");
			else
				fillLabel.setText(pgn.size()+".                   ");
			fillLabel.setOpaque(true);
			fillLabel.setFont(new Font("Monospaced", fillLabel.getFont().getStyle(), fillLabel.getFont().getSize()));
			fillLabel.setForeground(Color.BLACK);
			if((pgn.size()-1)%2==0)
			{
				fillLabel.setBackground(Color.WHITE);
			}
			else
			{
				fillLabel.setBackground(Color.LIGHT_GRAY);
			}
		}
	}
	
	public void unfillPGN()
	{
		for(int x=0; x<pgn.size(); x++)
		{
			String temp = pgn.get(x).getText().substring(pgn.get(x).getText().indexOf(".")+1).trim();
			if(temp.length()==0)
			{
				pgn.remove(x);
				x--;
			}
		}
	}
	
	public void clearPGN()
	{
		pgn = new ArrayList<JLabel>();
		fillPGN();
		redisplayPGN();
	}
}