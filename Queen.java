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

public class Queen extends ChessPiece
{
	private static final long serialVersionUID = 716804568379608862L;
	
	public Queen(boolean isWhite, Location loc)
	{
		super(isWhite, loc);
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
		g.drawString("Q", x, y);
	}
	
	public ArrayList<Location> getMoves(BoardState board)
	{
		ArrayList<Location> possibleMoves = new ArrayList<Location>();
		int y = getLocation().getRow();
		int x = getLocation().getCol();
		
		boolean[] isRowBlocked = new boolean[8];
		
		Location[] locs = new Location[64];
		
		for(int z=1; z<=64; z++)
		{
			if(z<=8)
				locs[z-1] = new Location(y-z%8, x-z%8);
			else if(z<=16)
				locs[z-1] = new Location(y-z%8, x);
			else if(z<=24)
				locs[z-1] = new Location(y-z%8, x+z%8);
			else if(z<=32)
				locs[z-1] = new Location(y, x+z%8);
			else if(z<=40)
				locs[z-1] = new Location(y+z%8, x+z%8);
			else if(z<=48)
				locs[z-1] = new Location(y+z%8, x);
			else if(z<=56)
				locs[z-1] = new Location(y+z%8, x-z%8);
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
	}
	
	public String toString()
	{
		return super.toString()+" Queen";
	}
}