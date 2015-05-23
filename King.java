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
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.*;

public class King extends ChessPiece
{
	private static final long serialVersionUID = -3292941590288943851L;
	private boolean isChecked;
	private boolean hasMoved;
	
	public King(boolean isWhite, Location loc)
	{
		super(isWhite, loc);
		
		hasMoved=false;
		
		isChecked=false;		
	}
	
	public void draw(Graphics g)
	{
		final int x = 20;
		final int y = 40;
		final int width = 40;
		if(getColor())
		{
			g.setColor(Color.WHITE);
			g.setFont(new Font("Courier", g.getFont().getStyle(), width));
			g.fillOval(10, 10, width, width);
			g.setColor(Color.BLACK);
		}
		else
		{
			g.setColor(Color.BLACK);
			g.setFont(new Font("Courier", g.getFont().getStyle(), width));
			g.fillOval(10, 10, width, width);
			g.setColor(Color.WHITE);
		}
		g.drawString("+", x, y);
	}
	
	public boolean getHasMoved()
	{
		return hasMoved;
	}
	
	public void setHasMoved(boolean input)
	{
		hasMoved=input;
	}
	
	public boolean isChecked()
	{
		return isChecked;
	}
	
	public void setIsChecked(boolean check)
	{
		isChecked=check;
	}
	
	public void updateIsChecked(BoardState board)
	{
		ArrayList<Location> possibleChecks = getPossibleChecks(board);
		
		for(int a=0; a<possibleChecks.size(); a++)
			if(possibleChecks.get(a).equals(getLocation()))
			{
				isChecked = true;
				break;
			}
			else
				isChecked=false;
	}
	
	public ArrayList<Location> getPossibleChecks(BoardState board)
	{
		ArrayList<Location> checks = new ArrayList<Location>();
		for(int y=0; y<board.getState().length; y++)
			for(int x=0; x<board.getState().length; x++)
				if(board.getState()[y][x]!=null && board.getState()[y][x].getColor()!=getColor())
				{
					ArrayList<Location> tempChecks = board.getState()[y][x].getMoves(board);
					if(board.getState()[y][x] instanceof King)
					{
						for(int a=0; a<tempChecks.size(); a++)
							if(Math.abs((board.getState()[y][x].getLocation().getCol())-(tempChecks.get(a).getCol()))==2)
							{
								tempChecks.remove(a);
								a--;
							}
					}
					for(int a=0; a<tempChecks.size(); a++)
						checks.add(tempChecks.get(a));
				}
		return checks;
	}
	
	public ArrayList<Location> getMoves(BoardState board)
	{
		ArrayList<Location> possibleMoves = new ArrayList<Location>();
		int y = getLocation().getRow();
		int x = getLocation().getCol();
		
		Location[] locs = new Location[10];
		
		locs[0] = new Location(y-1,x-1);
		locs[1] = new Location(y-1,x);
		locs[2] = new Location(y-1,x+1);
		locs[3] = new Location(y,x-1);
		locs[4] = new Location(y,x+1);
		locs[5] = new Location(y+1,x+1);
		locs[6] = new Location(y+1,x);
		locs[7] = new Location(y+1,x-1);
		
		locs[8] = new Location(y,x-2);
		locs[9] = new Location(y,x+2);
		
		for(Location z: locs)
			if(board.isValid(z) && (board.isEmpty(z) || board.isPieceWhite(z)!=getColor()))
				possibleMoves.add(z);
		
		return possibleMoves;
	}

	public void moveTo(Location moveLoc)
	{
		setLocation(moveLoc);
		hasMoved=true;
	}
	
	public String toString()
	{
		return super.toString()+" King";
	}
}