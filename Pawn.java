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
import java.util.*;

@SuppressWarnings("serial")
public class Pawn extends ChessPiece
{
	private boolean doubleMove;
	
	public Pawn(boolean isWhite, Location loc)
	{
		super(isWhite, loc);
	}
	
	public void draw(Graphics g)
	{
		final int x = 20;
		final int y = 40;
		final int width = 40;
		Polygon p = new Polygon();
		p.addPoint(30,0);
		p.addPoint(5,50);
		p.addPoint(55,50);
		if(getColor())
		{
			g.setColor(Color.WHITE);
			g.setFont(new Font("Courier", g.getFont().getStyle(), width));
			g.fillPolygon(p);
			g.setColor(Color.BLACK);
		}
		else
		{
			g.setColor(Color.BLACK);
			g.setFont(new Font("Courier", g.getFont().getStyle(), width));
			g.fillPolygon(p);
			g.setColor(Color.WHITE);
		}
		g.drawString("P", x, y);
	}
	
	public boolean getDoubleMove()
	{
		return doubleMove;
	}
	
	public void setDoubleMove(boolean input)
	{
		doubleMove=input;
	}
	
	public ArrayList<Location> getMoves(BoardState board)
	{
		ArrayList<Location> possibleMoves = new ArrayList<Location>();
		int y = getLocation().getRow();
		int x = getLocation().getCol();
		
		Location whiteOne = new Location(y-1,x);
		Location whiteTwo = new Location(y-2,x);
		Location whiteLeft = new Location(y-1,x-1);
		Location whiteRight = new Location(y-1,x+1);
		
		Location enPassantLeft = new Location(y,x-1);
		Location enPassantRight = new Location(y,x+1);
				
		Location blackOne = new Location(y+1,x);
		Location blackTwo = new Location(y+2,x);
		Location blackLeft = new Location(y+1,x-1);
		Location blackRight = new Location(y+1,x+1);
		
		if(getColor())
		{
			if(y!=0)
			{
				if(board.isValid(whiteOne) && board.isEmpty(whiteOne))
					possibleMoves.add(whiteOne);
				if(board.isValid(whiteTwo) && getLocation().getRow()==6 && board.isEmpty(whiteTwo) && board.isEmpty(whiteOne))
					possibleMoves.add(whiteTwo);
				if(board.isValid(whiteLeft) && !board.isEmpty(whiteLeft) && !board.isPieceWhite(whiteLeft))
					possibleMoves.add(whiteLeft);
				if(board.isValid(whiteRight) && !board.isEmpty(whiteRight) && !board.isPieceWhite(whiteRight))
					possibleMoves.add(whiteRight);
				if(board.isValid(whiteRight) && board.isEmpty(whiteRight) && board.isValid(enPassantRight) && !board.isEmpty(enPassantRight) && !board.isPieceWhite(enPassantRight) && board.getState()[enPassantRight.getRow()][enPassantRight.getCol()] instanceof Pawn)
					if(enPassantRight.getRow()==3 && ((Pawn)board.getState()[enPassantRight.getRow()][enPassantRight.getCol()]).getDoubleMove())
						possibleMoves.add(whiteRight);
				if(board.isValid(whiteLeft) && board.isEmpty(whiteLeft) && board.isValid(enPassantLeft) && !board.isEmpty(enPassantLeft) && !board.isPieceWhite(enPassantLeft) && board.getState()[enPassantLeft.getRow()][enPassantLeft.getCol()] instanceof Pawn)
					if(enPassantLeft.getRow()==3 && ((Pawn)board.getState()[enPassantLeft.getRow()][enPassantLeft.getCol()]).getDoubleMove())
						possibleMoves.add(whiteLeft);
			}
		}
		else
		{
			if(y!=7)
			{
				if(board.isValid(blackOne) && board.isEmpty(blackOne))
					possibleMoves.add(blackOne);
				if(board.isValid(blackTwo) && getLocation().getRow()==1 && board.isEmpty(blackTwo) && board.isEmpty(blackOne))
					possibleMoves.add(blackTwo);
				if(board.isValid(blackLeft) && !board.isEmpty(blackLeft) && board.isPieceWhite(blackLeft))
					possibleMoves.add(blackLeft);
				if(board.isValid(blackRight) && !board.isEmpty(blackRight) && board.isPieceWhite(blackRight))
					possibleMoves.add(blackRight);
				if(board.isValid(blackRight) && board.isEmpty(blackRight) && board.isValid(enPassantRight) && !board.isEmpty(enPassantRight) && board.isPieceWhite(enPassantRight) && board.getState()[enPassantRight.getRow()][enPassantRight.getCol()] instanceof Pawn)
					if(enPassantRight.getRow()==4 && ((Pawn)board.getState()[enPassantRight.getRow()][enPassantRight.getCol()]).getDoubleMove())
						possibleMoves.add(blackRight);
				if(board.isValid(blackLeft) && board.isEmpty(blackLeft) && board.isValid(enPassantLeft) && !board.isEmpty(enPassantLeft) && board.isPieceWhite(enPassantLeft) && board.getState()[enPassantLeft.getRow()][enPassantLeft.getCol()] instanceof Pawn)
					if(enPassantLeft.getRow()==4 && ((Pawn)board.getState()[enPassantLeft.getRow()][enPassantLeft.getCol()]).getDoubleMove())
						possibleMoves.add(blackLeft);
			}
		}
		return possibleMoves;
	}
	
	public void moveTo(Location moveLoc)
	{
		if((int)Math.abs(getLocation().getRow()-moveLoc.getRow()) > 1)
			doubleMove = true;
		else
			doubleMove = false;
		setLocation(moveLoc);
	}
	
	public String toString()
	{
		return super.toString()+" Pawn";
	}
}