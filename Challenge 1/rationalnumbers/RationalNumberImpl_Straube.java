package rationalnumbers;

public class RationalNumberImpl_Straube implements RationalNumber
{

	private int numerator;
	private int denominator;
	
	public RationalNumberImpl_Straube(int numerator, int denominator)
	{
		//Denominator cannot be 0
		assert denominator != 0;

		if(numerator == 0)
		{
			this.numerator = 0;
			this.denominator = 1;
		}
		if(denominator == 1)
		{
			this.numerator = numerator;
			this.denominator = 1;
		}
		
		int gcf = getGCF(numerator,denominator);
		this.denominator = denominator / gcf;
		this.numerator = numerator / gcf;
		
	}

	public int getGCF(int numerator,int denominator)
	{
        if (denominator != 0)
        {
            return getGCF(denominator, numerator % denominator);
        }
        else
        {
            return numerator;
        }
    }
	
	public int getNumerator() 
	{
		return numerator;
	}
	
	public int getDenominator() 
	{
		return denominator;
	}
	
	public double getValue() 
	{
		double num = numerator;
		double denum = denominator;
		double retVal = num/denum;
		return retVal;
	}

}
