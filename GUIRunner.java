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
import java.util.ArrayList;
import java.awt.Desktop;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

public class GUIRunner implements Serializable
{
	private static final long serialVersionUID = -5502586734068739286L;
	//	private Sound sounds;
	private Location selectedPiece, promotedPiece;
	private BoardState board;
	private GUI gui;
	private boolean isWhiteTurn;
	private boolean highlight;
	private boolean perpetualFlip;
	private ArrayList<Integer> undoMoves;
	private ArrayList<Integer> wasPieceTaken;
	private ArrayList<Location> moves;
	
	public GUIRunner()
	{
		board = new BoardState();
		gui = new GUI(board, this);
		highlight = true;
		perpetualFlip=false;
		moves = new ArrayList<Location>();
	}
	
	public void setVisible()
	{
		gui.setVisible(true);
		newGame();
//		sounds.newgame();
	}
	
	public void newGame()
	{
		board.resetBoardState();
		selectedPiece = null;
		isWhiteTurn = true;
		board.setTurn(isWhiteTurn);
		gui.updateBoard(board);
		gui.enableSide(isWhiteTurn);
		undoMoves = new ArrayList<Integer>();
		wasPieceTaken = new ArrayList<Integer>();
		gui.newGame();
	}
	
	public boolean getTurn()
	{
		return isWhiteTurn;
	}
	
	public ArrayList<Location> getMoves(Location piece)
	{
		ArrayList<Location> allMoves = board.getState()[piece.getRow()][piece.getCol()].getMoves(board);
		for(int a=0; a<allMoves.size(); a++)
			if(!isLegal(piece, allMoves.get(a), 0))
			{
				allMoves.remove(a);
				a--;
			}
		return allMoves;
	}
	
	public void checkGameOver()
	{
		ArrayList<Location> moves = new ArrayList<Location>();
		ArrayList<Location> temp = new ArrayList<Location>();
		for(int y=0; y<board.getState().length; y++)
			for(int x=0; x<board.getState().length; x++)
			{
				if(board.getState()[y][x]!=null && board.getState()[y][x].getColor()==isWhiteTurn)
				{
					temp = getMoves(new Location(y,x));
					for(Location z : temp)
						moves.add(z);
					if(moves.size()>0)
						break;
				}
				if(moves.size()>0)
					break;
			}
		if(moves.size()==0)
		{
			boolean isCheck = true;
			for(int y=0; y<board.getState().length; y++)
				for(int x=0; x<board.getState().length; x++)
					if(board.getState()[y][x]!=null && board.getState()[y][x] instanceof King && board.getState()[y][x].getColor()==isWhiteTurn)
					{
						isCheck = ((King)board.getState()[y][x]).isChecked();
						break;
					}
			if(isCheck)
			{
				gui.updateStatusBar("Game Over!", true);
				if(isWhiteTurn)
					gui.gameOver(-1);
				else
					gui.gameOver(1);
//				sounds.victory();
			}
			else
			{
				gui.updateStatusBar("Stalemate!", true);
				gui.gameOver(0);
//				sounds.draw();
			}
		}

		boolean isWhiteKnight = false;
		boolean isWhiteBishop = false;
		boolean isBlackKnight = false;
		boolean isBlackBishop = false;
		boolean isDraw = true;
		for(int y=0; y<board.getState().length; y++)
			for(int x=0; x<board.getState().length; x++)
			{
				if(board.getState()[y][x]!=null && (board.getState()[y][x] instanceof Queen || board.getState()[y][x] instanceof Rook || board.getState()[y][x] instanceof Pawn))
				{
					isDraw=false;
					break;
				}
				else if(board.getState()[y][x]!=null && board.getState()[y][x] instanceof Bishop && board.getState()[y][x].getColor()==isWhiteTurn)
					isWhiteBishop=true;
				else if(board.getState()[y][x]!=null && board.getState()[y][x] instanceof Knight && board.getState()[y][x].getColor()==isWhiteTurn)
					isWhiteKnight = true;
				else if(board.getState()[y][x]!=null && board.getState()[y][x] instanceof Bishop && board.getState()[y][x].getColor()!=isWhiteTurn)
					isBlackBishop=true;
				else if(board.getState()[y][x]!=null && board.getState()[y][x] instanceof Knight && board.getState()[y][x].getColor()!=isWhiteTurn)
					isBlackKnight = true;
			}
		if(isDraw)
			if(isWhiteBishop && isWhiteKnight || isBlackBishop && isBlackKnight || isBlackBishop && isWhiteBishop || isBlackKnight && isWhiteKnight)
				isDraw = false;
		if(isDraw)
		{
			gui.updateStatusBar("Draw!", true);
			gui.gameOver(0);
		}
	}
	
	public void checkPromotion(Location loc)
	{
		if(board.getState()[loc.getRow()][loc.getCol()] instanceof Pawn && (loc.getRow()==0 || loc.getRow()==7))
		{
			gui.promotion();
			promotedPiece = loc;
		}
	}
	
	public boolean isLegal(Location start, Location end, int restraint)
	{
		boolean isLegal = true;
		boolean complete = true;
		int queue;
		
		if(restraint!=1 && board.getState()[start.getRow()][start.getCol()] instanceof King)
		{
			if(start.getRow()==end.getRow() && Math.abs(start.getCol()-end.getCol())==2)
			{
				((King)board.getState()[start.getRow()][start.getCol()]).updateIsChecked(board);
				if(((King)board.getState()[start.getRow()][start.getCol()]).getHasMoved() || ((King)board.getState()[start.getRow()][start.getCol()]).isChecked())
				{
					isLegal = false;
					complete = false;
				}
				else if(end.getCol()==2 && board.isEmpty(new Location(start.getRow(),start.getCol()-1)) && board.isEmpty(new Location(start.getRow(),start.getCol()-2)) && board.isEmpty(new Location(start.getRow(),start.getCol()-3)) && !board.isEmpty(new Location(start.getRow(),start.getCol()-4)) && board.getState()[start.getRow()][start.getCol()-4] instanceof Rook && !((Rook)board.getState()[start.getRow()][start.getCol()-4]).getHasMoved())
				{
					if(isLegal(start, new Location(start.getRow(), start.getCol()-1), 1) && isLegal(start, end, 1))
					{
						isLegal = true;
						complete = false;
					}
					else
					{
						isLegal = false;
						complete = false;
					}
				}
				else if(end.getCol()==6 && board.isEmpty(new Location(start.getRow(),start.getCol()+1)) && board.isEmpty(new Location(start.getRow(),start.getCol()+2)) && !board.isEmpty(new Location(start.getRow(),start.getCol()+3)) && board.getState()[start.getRow()][start.getCol()+3] instanceof Rook && !((Rook)board.getState()[start.getRow()][start.getCol()+3]).getHasMoved())
				{
					if(isLegal(start, new Location(start.getRow(), start.getCol()+1), 1) && isLegal(start, end, 1))
					{
						isLegal = true;
						complete = false;
					}
					else
					{
						isLegal = false;
						complete = false;
					}
				}
				else
				{
					isLegal = false;
					complete = false;
				}
			}
		}
		
		if(complete)
		{
			queue = board.saveState();
			board.moveFrom_To(start, end);
			for(int y=0; y<board.getState().length; y++)
				for(int x=0; x<board.getState().length; x++)
					if(board.getState()[y][x]!=null && board.getState()[y][x] instanceof King && board.getState()[y][x].getColor()==isWhiteTurn)
					{
						((King)board.getState()[y][x]).updateIsChecked(board);
						isLegal = !((King)board.getState()[y][x]).isChecked();
						break;
					}
			board.undoMove(queue);
		}
		
		return isLegal;
	}
	
	public void movePiece(Location loc)
	{
		undoMoves.add(board.saveState());
		if(board.getState()[loc.getRow()][loc.getCol()]!=null)
		{
			wasPieceTaken.add(1);
//			sounds.taken();
		}
		else
		{
			wasPieceTaken.add(0);
//			sounds.move();
		}
		board.moveFrom_To(selectedPiece, loc);
		gui.updatePGN(selectedPiece, loc);
		isWhiteTurn = !isWhiteTurn;
		board.setTurn(isWhiteTurn);
		selectedPiece=null;
		gui.updateBoard(board);
		gui.enableSide(isWhiteTurn);
		for(int y=0; y<board.getState().length; y++)
			for(int x=0; x<board.getState().length; x++)
				if(board.getState()[y][x]!=null && board.getState()[y][x] instanceof King && board.getState()[y][x].getColor()==isWhiteTurn)
				{
					((King)board.getState()[y][x]).updateIsChecked(board);
					if(((King)board.getState()[y][x]).isChecked())
//						sounds.check();
					break;
				}
		if(perpetualFlip)
		{
			gui.flipBoard();
			if(gui.getFlipped()==isWhiteTurn)
				gui.flipBoard();
			gui.enableSide(isWhiteTurn);
			if(selectedPiece!=null)
			{
				gui.selected(selectedPiece);
				processMoves();
			}
		}
	}
	
	public void processMoves()
	{
		boolean isCheck = true;
		moves = getMoves(selectedPiece);
		if(moves.size()==0)
		{
			for(int y=0; y<board.getState().length; y++)
				for(int x=0; x<board.getState().length; x++)
					if(board.getState()[y][x]!=null && board.getState()[y][x] instanceof King && board.getState()[y][x].getColor()==isWhiteTurn)
					{
						isCheck = ((King)board.getState()[y][x]).isChecked();
						break;
					}
			if(isCheck)
				gui.updateStatusBar("You're in check!", true);
			else
				gui.updateStatusBar("You can't move that piece!", true);
		}
		else
			for(Location z : moves)
				gui.enable(z, highlight);
	}
	
	public void processOne(Location loc)
	{
		if(board.getState()[loc.getRow()][loc.getCol()]!=null)
		{
			if((board.getState()[loc.getRow()][loc.getCol()].getColor() && isWhiteTurn) || (!board.getState()[loc.getRow()][loc.getCol()].getColor() && !isWhiteTurn))
			{
				gui.enableSide(isWhiteTurn);
				selectedPiece = loc;
				gui.resetBackground();
				gui.resetBorders();
				gui.selected(selectedPiece);
				processMoves();
			}
			else
			{
				movePiece(loc);
				board.resetOtherPawns(loc);
				checkPromotion(loc);
				checkGameOver();
			}
		}
		else
		{
			movePiece(loc);
			board.resetOtherPawns(loc);
			checkPromotion(loc);
			checkGameOver();
		}
	}
	
	public void processTwo(int command)
	{
		if(command>=-4)
		{
			if(command==-1)
				board.addQueen(promotedPiece);
			else if(command==-2)
				board.addRook(promotedPiece);
			else if(command==-3)
				board.addBishop(promotedPiece);
			else if(command==-4)
				board.addKnight(promotedPiece);
			for(int y=0; y<board.getState().length; y++)
				for(int x=0; x<board.getState().length; x++)
					if(board.getState()[y][x]!=null && board.getState()[y][x] instanceof King && board.getState()[y][x].getColor()==isWhiteTurn)
					{
						((King)board.getState()[y][x]).updateIsChecked(board);
						if(((King)board.getState()[y][x]).isChecked())
//							sounds.check();
						break;
					}
			gui.endPromotion();
			gui.updateBoard(board);
		}
		else if(command==-10)
		{
			newGame();
//			sounds.newgame();
		}
		else if(command==-11)
		{
			gui.verifyExit();
		}
		else if(command==-12)
		{
//			sounds.exit();
			try
	        {
				Thread.sleep(1000);
	        }
			catch(Exception e)
	        {
	            e.printStackTrace();
	        }
			gui.dispose();
		}
		else if(command==-13)
		{
			perpetualFlip=!perpetualFlip;
		}
		else if(command==-14)
		{
			try {
				Desktop.getDesktop().browse(new URI("http://games.yahoo.com/help/rules/ch&ss=1"));
			} catch (IOException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
				
		}
		else if(command==-20)
		{
			gui.flipBoard();
			gui.enableSide(isWhiteTurn);
			if(selectedPiece!=null)
			{
				gui.selected(selectedPiece);
				processMoves();
			}
		}
		else if(command==-21)
		{
//			gui.repaint();
			if(undoMoves.size() > 0)
			{
				board.undoMove(undoMoves.get(undoMoves.size()-1));
				undoMoves.remove(undoMoves.size()-1);
				isWhiteTurn = !isWhiteTurn;
				board.setTurn(isWhiteTurn);
				selectedPiece=null;
				gui.updateBoard(board);
				gui.enableSide(isWhiteTurn);
				wasPieceTaken.remove(wasPieceTaken.size()-1);
				gui.backPGN();
				if(perpetualFlip)
					gui.flipBoard();
//				sounds.undo();
			}
			else
				gui.updateStatusBar("You can't undo any more moves!", true);
		}
		else if(command==-22)
		{
			highlight = !highlight;
			if(!highlight)
				gui.resetBackground();
			else if(selectedPiece!=null)
				processMoves();
			gui.updateStatusBar("Highlighting of possible moves toggled!", true);
		}
	}
}