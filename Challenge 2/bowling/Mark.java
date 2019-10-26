package bowling;

import java.util.HashMap;
import java.util.Map;

public enum Mark {
	ZERO("-"),
	ONE("1"),
	TWO("2"),
	THREE("3"),
	FOUR("4"),
	FIVE("5"),
	SIX("6"),
	SEVEN("7"),
	EIGHT("8"),
	NINE("9"),
	STRIKE("X"),
	SPARE("/"),
	EMPTY(" ");
	
	private final String stringRepresentation;   // in kilograms
    Mark(String stringRepresentation) {
        this.stringRepresentation = stringRepresentation;
    }
    
    private static Map<Integer, Mark> integerToMarkMap;
    static
    {
    	integerToMarkMap = new HashMap<Integer, Mark>();
    	integerToMarkMap.put(0, ZERO);
    	integerToMarkMap.put(1, ONE);
    	integerToMarkMap.put(2, TWO);
    	integerToMarkMap.put(3, THREE);
    	integerToMarkMap.put(4, FOUR);
    	integerToMarkMap.put(5, FIVE);
    	integerToMarkMap.put(6, SIX);
    	integerToMarkMap.put(7, SEVEN);
    	integerToMarkMap.put(8, EIGHT);
    	integerToMarkMap.put(9, NINE);
    }
    
	public static Mark translate(int pinCount)
	{
		if(pinCount == 0)
		{
			return Mark.ZERO;
		}
		else if(pinCount == 1)
		{
			return Mark.ONE;
		}
		else if(pinCount == 2)
		{
			return Mark.TWO;
		}
		else if(pinCount == 3)
		{
			return Mark.THREE;
		}
		else if(pinCount == 4)
		{
			return Mark.FOUR;
		}
		else if(pinCount == 5)
		{
			return Mark.FIVE;
		}
		else if(pinCount == 6)
		{
			return Mark.SIX;
		}
		else if(pinCount == 7)
		{
			return Mark.SEVEN;
		}
		else if(pinCount == 8)
		{
			return Mark.EIGHT;
		}
		else if(pinCount == 9)
		{
			return Mark.NINE;
		}
		else
		{
			return null;
		}
	}
	
	public int getValue()
	{
		if(this == ZERO) return 0;
		if(this == ONE) return 1;
		if(this == TWO) return 2;
		if(this == THREE) return 3;
		if(this == FOUR) return 4;
		if(this == FIVE) return 5;
		if(this == SIX) return 6;
		if(this == SEVEN) return 7;
		if(this == EIGHT) return 8;
		if(this == NINE) return 9;
		else
			throw new RuntimeException("DO NOT CHANGE Mark.java!");
	}
    
    public String toString()
    {
    	return stringRepresentation;
    }
}
