/*
G Chess version 1.0
Copyright (c) 2010 Gary Menezes

Copyright Notice
  You may not use this code for any commercial purpose.
*/
import java.io.Serializable;

public class Flipped implements Serializable
{
	private static final long serialVersionUID = -5489058992136269079L;
	boolean isFlipped;
	public Flipped()
	{
		isFlipped = false;
	}
	public void flip()
	{
		isFlipped = !isFlipped;
	}
	public boolean getFlip()
	{
		return isFlipped;
	}
	public boolean equals(Flipped other)
	{
		if(other.getFlip()==getFlip())
			return true;
		return false;
	}
}
