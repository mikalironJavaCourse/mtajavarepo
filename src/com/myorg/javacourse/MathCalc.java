package com.myorg.javacourse;

public class MathCalc{
	
	private static int radius,angleB,Hypotenuse,base,exp;
	
	public MathCalc(int radiusValue,int angleValue,int HypotenuseValue,int baseValue,int expValue)
	{
		radius=radiusValue;
		angleB=angleValue;
		Hypotenuse=HypotenuseValue;
		base=baseValue;
		exp=expValue;
	}

	public double areaCircle()
	{
		return Math.PI*Math.pow(radius,2);
	}
	
	public double findOpposite()
	{
		return Math.sin(Math.toRadians(angleB)) * Hypotenuse;
	}

	public double power()
	{
		return Math.pow(base, exp);
	}
	
	public String getResult() {
	        String line1 = new String("calculation 1: Area of circle with radius:  "+ radius +"is " + areaCircle());
			String line2 = new String("calculation 2: Length of opposite where angle B is 30 degrees and Hypotenuse length is 50 cm is: " + findOpposite());
			String line3 = new String("calculation 3: Power of "  + base +  " with exp of " +exp+ " is " + (int)power());
			String resultStr = (line1 +"<br>" + line2 +"<br>" + line3);
			return resultStr;
	}

}