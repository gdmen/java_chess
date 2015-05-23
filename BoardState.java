/*
G Chess version 1.0
Copyright (c) 2010 Gary Menezes

Copyright Notice
  You may not use this code for any commercial purpose.
*/
import java.util.ArrayList;
import java.io.*;

@SuppressWarnings("serial")
public class BoardState implements Serializable
{
	private ChessPiece[][] boardState;
	private ChessPiece takenPiece;
	private boolean isWhiteTurn;
	private ArrayList<ChessPiece[][]> savedStates;
	private int savedStateQueue;
	
	public BoardState()
	{
		boardState = new ChessPiece[8][8];
		savedStates = new ArrayList<ChessPiece[][]>();
		savedStateQueue = 0;
		resetBoardState();
	}
	
	public ChessPiece[][] getState()
	{
		return boardState;
	}
	
	public boolean isPieceWhite(Location loc)
	{
		return boardState[loc.getRow()][loc.getCol()].getColor();
	}
	
	public void setTurn(boolean isWhite)
	{
		isWhiteTurn = isWhite;
	}
	
	public boolean getTurn()
	{
		return isWhiteTurn;
	}
	
	public void resetOtherPawns(Location currentPiece)
	{
		for(int y=0; y<boardState.length; y++)
			for(int x=0; x<boardState.length; x++)
			{
				Location loc = new Location(y,x);
				if(isValid(loc) && !isEmpty(loc) && boardState[y][x] instanceof Pawn)
					if(!currentPiece.equals(loc))
						((Pawn)boardState[y][x]).setDoubleMove(false);
			}
	}
	
	public int saveState()
	{
		savedStates.add(new ChessPiece[8][8]);
		for(int y=0; y<boardState.length; y++)
			for(int x=0; x<boardState.length; x++)
				savedStates.get(savedStateQueue)[y][x]=ChessPiece.clone(boardState[y][x]);
		savedStateQueue++;
		return savedStateQueue-1;
	}
	
	public void undoMove(int queueNumber)
	{
		for(int y=0; y<boardState.length; y++)
			for(int x=0; x<boardState.length; x++)
					boardState[y][x]=savedStates.get(queueNumber)[y][x];
	}
	
	public void moveFrom_To(Location start, Location end)
	{
		boardState[start.getRow()][start.getCol()].moveTo(end);
		ChessPiece temp = boardState[start.getRow()][start.getCol()];
		takenPiece = boardState[end.getRow()][end.getCol()];
		boardState[start.getRow()][start.getCol()] = null;
		boardState[end.getRow()][end.getCol()] = temp;
		
		if(takenPiece == null && start.getCol()!=end.getCol() && temp instanceof Pawn)
			if(temp.getColor())
			{
				Location EP = new Location(end.getRow()+1, end.getCol());
				if(!isEmpty(EP) && !boardState[EP.getRow()][EP.getCol()].getColor() && boardState[EP.getRow()][EP.getCol()] instanceof Pawn)
					boardState[EP.getRow()][EP.getCol()] = null;
			}
			else
			{
				Location EP = new Location(end.getRow()-1, end.getCol());
				if(!isEmpty(EP) && boardState[EP.getRow()][EP.getCol()].getColor() && boardState[EP.getRow()][EP.getCol()] instanceof Pawn)
					boardState[EP.getRow()][EP.getCol()] = null;
			}
		if(takenPiece == null && temp instanceof King)
			if(start.getCol()-end.getCol() == 2)
			{
				boardState[end.getRow()][end.getCol()-2].moveTo(new Location(end.getRow(), end.getCol()+1));
				moveFrom_To(new Location(end.getRow(), end.getCol()-2), new Location(end.getRow(), end.getCol()+1));
			}
			else if(start.getCol()-end.getCol() == -2)
			{
				boardState[end.getRow()][end.getCol()+1].moveTo(new Location(end.getRow(), end.getCol()-1));
				moveFrom_To(new Location(end.getRow(), end.getCol()+1), new Location(end.getRow(), end.getCol()-1));	
			}
	}
		
	public boolean isValid(Location loc)
	{
		if( (loc.getRow() < boardState.length && loc.getRow() >= 0) && (loc.getCol() < boardState.length && loc.getCol() >=0) )
			return true;
		return false;
	}
	
	public boolean isEmpty(Location loc)
	{
		if(!isValid(loc))
			return false;
		if(boardState[loc.getRow()][loc.getCol()]==null)
			return true;
		return false;
	}
	
	public void addQueen(Location loc)
	{
		boardState[loc.getRow()][loc.getCol()] = new Queen(boardState[loc.getRow()][loc.getCol()].getColor(), new Location(loc.getRow(),loc.getCol()));
	}
	
	public void addRook(Location loc)
	{
		boardState[loc.getRow()][loc.getCol()] = new Rook(boardState[loc.getRow()][loc.getCol()].getColor(), new Location(loc.getRow(),loc.getCol()));
	}
	
	public void addBishop(Location loc)
	{
		boardState[loc.getRow()][loc.getCol()] = new Bishop(boardState[loc.getRow()][loc.getCol()].getColor(), new Location(loc.getRow(),loc.getCol()));
	}
	
	public void addKnight(Location loc)
	{
		boardState[loc.getRow()][loc.getCol()] = new Knight(boardState[loc.getRow()][loc.getCol()].getColor(), new Location(loc.getRow(),loc.getCol()));
	}
	
	public void resetBoardState()
	{
		takenPiece=null;
		savedStates = new ArrayList<ChessPiece[][]>();
		savedStateQueue = 0;
		for(int y=0; y<boardState.length; y++)
			for(int x=0; x<boardState.length; x++)
			{
				if(y==0)
					if(x==0 || x==7)
						boardState[y][x] = new Rook(false, new Location(y,x));
					else if(x==1 || x==6)
						boardState[y][x] = new Knight(false, new Location(y,x));
					else if(x==2 || x==5)
						boardState[y][x] = new Bishop(false, new Location(y,x));
					else if(x==3)
						boardState[y][x] = new Queen(false, new Location(y,x));
					else
						boardState[y][x] = new King(false, new Location(y,x));
				else if(y==1)
					boardState[y][x] = new Pawn(false, new Location(y,x));
				else if(y==6)
					boardState[y][x] = new Pawn(true, new Location(y,x));
				else if(y==7)
					if(x==0 || x==7)
						boardState[y][x] = new Rook(true, new Location(y,x));
					else if(x==1 || x==6)
						boardState[y][x] = new Knight(true, new Location(y,x));
					else if(x==2 || x==5)
						boardState[y][x] = new Bishop(true, new Location(y,x));
					else if(x==3)
						boardState[y][x] = new Queen(true, new Location(y,x));
					else
						boardState[y][x] = new King(true, new Location(y,x));
				else
					boardState[y][x]=null;
			}
	}
}
