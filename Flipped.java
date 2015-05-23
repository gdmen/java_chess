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
