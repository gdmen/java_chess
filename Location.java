/*
G Chess version 1.0
Copyright (c) 2010 Gary Menezes

Copyright Notice
  You may not use this code for any commercial purpose.
*/
import java.io.Serializable;

public class Location implements Serializable
{
	private static final long serialVersionUID = 5087471276680520399L;
	int Row;
	int Column;
	
	public Location()
	{
		Row = 0;
		Column = 0;
	}
	
	public Location(int Row, int Column)
	{
		this.Row = Row;
		this.Column = Column;
	}
	
	public int getRow()
	{
		return Row;
	}
	
	public int getCol()
	{
		return Column;
	}
	
    public boolean equals(Location loc)
    {
        return loc.getRow() == this.getRow() && loc.getCol() == this.getCol();
    }
    
    public String toString()
    {
    	int col = getCol();
    	int row = getRow();
    	char pngCol;
    	int pngRow;
    	switch (col)
    	{
    		case 0:
    			pngCol = 'a'; break;
    		case 1:
    			pngCol = 'b'; break;
    		case 2:
    			pngCol = 'c'; break;
    		case 3:
    			pngCol = 'd'; break;
    		case 4:
    			pngCol = 'e'; break;
    		case 5:
    			pngCol = 'f'; break;
    		case 6:
    			pngCol = 'g'; break;
    		case 7:
    			pngCol = 'h'; break;
    		default:
    			pngCol = ' '; break;
    	}
    	
    	switch (row)
    	{
    		case 0:
    			pngRow = 8; break;
    		case 1:
    			pngRow = 7; break;
    		case 2:
    			pngRow = 6; break;
    		case 3:
    			pngRow = 5; break;
    		case 4:
    			pngRow = 4; break;
    		case 5:
    			pngRow = 3; break;
    		case 6:
    			pngRow = 2; break;
    		case 7:
    			pngRow = 1; break;
    		default:
    			pngRow = 0; break;
    	}
    	
    	return ""+pngCol+""+pngRow;
    }
}
