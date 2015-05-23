/*
G Chess version 1.0
Copyright (c) 2010 Gary Menezes

Copyright Notice
  You may not use this code for any commercial purpose.
*/
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.*;

public class Rook extends ChessPiece
{
	private static final long serialVersionUID = 3819757220079709631L;
	private boolean hasMoved;
	
	public Rook(boolean isWhite, Location loc)
	{
		super(isWhite, loc);
		
		hasMoved=false;
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
			g.fillRect(10, 10, width, width);
			g.setColor(Color.BLACK);
		}
		else
		{
			g.setColor(Color.BLACK);
			g.setFont(new Font("Courier", g.getFont().getStyle(), width));
			g.fillRect(10, 10, width, width);
			g.setColor(Color.WHITE);
		}
		g.drawString("R", x, y);
	}
	
	public boolean getHasMoved()
	{
		return hasMoved;
	}
	
	public void setHasMoved(boolean input)
	{
		hasMoved=input;
	}
	
	public ArrayList<Location> getMoves(BoardState board)
	{
		ArrayList<Location> possibleMoves = new ArrayList<Location>();
		int y = getLocation().getRow();
		int x = getLocation().getCol();
		
		boolean[] isRowBlocked = new boolean[4];
		
		Location[] locs = new Location[32];
		
		for(int z=1; z<=32; z++)
		{
			if(z<=8)
				locs[z-1] = new Location(y-z%8, x);
			else if(z<=16)
				locs[z-1] = new Location(y, x+z%8);
			else if(z<=24)
				locs[z-1] = new Location(y+z%8, x);
			else
				locs[z-1] = new Location(y, x-z%8);
		}

		if(getColor())
		{
			for(int i=0; i<locs.length; i++)
				if(board.isValid(locs[i]) && !isRowBlocked[i/8] && (board.isEmpty(locs[i]) || !board.isPieceWhite(locs[i])))
				{
					possibleMoves.add(locs[i]);
					if(!board.isEmpty(locs[i]) && !board.isPieceWhite(locs[i]))
						isRowBlocked[i/8]=true;
				}
				else
					isRowBlocked[i/8]=true;
		}
		else
		{
			for(int i=0; i<locs.length; i++)
				if(board.isValid(locs[i]) && !isRowBlocked[i/8] && (board.isEmpty(locs[i]) || board.isPieceWhite(locs[i])))
				{
					possibleMoves.add(locs[i]);
					if(!board.isEmpty(locs[i]) && board.isPieceWhite(locs[i]))
						isRowBlocked[i/8]=true;
				}
				else
					isRowBlocked[i/8]=true;
		}
		return possibleMoves;
	}
	
	public void moveTo(Location moveLoc)
	{
		setLocation(moveLoc);
		hasMoved=true;
	}
	
	public String toString()
	{
		return super.toString()+" Rook";
	}
}
