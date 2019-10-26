package rationalnumbers;

public class RationalNumberUtils_Straube 
{
	public static boolean equal(RationalNumber r1, RationalNumber r2)
	{
		double valueOfr1 = r1.getValue();
		double valueOfr2 = r2.getValue();
		if(valueOfr1 == valueOfr2)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public static RationalNumber add(RationalNumber r1, RationalNumber r2)
	{
		int a = r1.getNumerator();
		int b = r1.getDenominator();
		int c = r2.getNumerator();
		int d = r2.getDenominator();

		RationalNumber retVal =  new RationalNumberImpl_Straube(((a*d)+(b*c)), (b*d));
		return retVal;
	}
	public static RationalNumber subtract(RationalNumber r1, RationalNumber r2)
	{
		int a = r1.getNumerator();
		int b = r1.getDenominator();
		int c = r2.getNumerator();
		int d = r2.getDenominator();
		RationalNumber retVal =  new RationalNumberImpl_Straube(((a*d)-(b*c)),(b*d));
		return retVal;
	}
	public static RationalNumber multiply(RationalNumber r1, RationalNumber r2)
	{
		int a = r1.getNumerator();
		int b = r1.getDenominator();
		int c = r2.getNumerator();
		int d = r2.getDenominator();
		RationalNumber retVal =  new RationalNumberImpl_Straube((a*c),(b*d));
		return retVal;
	}
	public static RationalNumber divide(RationalNumber r1, RationalNumber r2)
	{
		int a = r1.getNumerator();
		int b = r1.getDenominator();
		int c = r2.getNumerator();
		int d = r2.getDenominator();
		RationalNumber retVal =  new RationalNumberImpl_Straube((a*d),(b*c));
		return retVal;
	}
	public static RationalNumber negate(RationalNumber r1)
	{
		int a = r1.getNumerator();
		int b = r1.getDenominator();
		RationalNumber retVal =  new RationalNumberImpl_Straube(-a,b);
		return retVal;
	}
}
