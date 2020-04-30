package com.calculator;

import java.util.Scanner;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Scanner;
import java.math.RoundingMode;

public class FinalCalc {

	static String OnEquPress(String str) 
	{

		String origStr = str;
		int lastSogerPoteah;
		int firstSogerSoger;
		int i;
		BigDecimal result;
		String num1 = "";
		String num2 = "";
		String sub;
		String leftSub;
		String rightSub;
		String leftStr;
		String rightStr;

		while(str.contains("("))
		{
			lastSogerPoteah = str.lastIndexOf('(');
			firstSogerSoger = str.indexOf(')', lastSogerPoteah + 1);
			sub = str.substring(lastSogerPoteah + 1 , firstSogerSoger);
			leftStr = str.substring(0, lastSogerPoteah);
			rightStr = str.substring(firstSogerSoger + 1);
			// * AND /
			while(sub.contains("*") || sub.contains("/"))
			{
				if(sub.indexOf('/') != -1)
				{
					i = sub.indexOf('/');
					sub = calc(sub, i);
				}
				else 
				{
					if(sub.indexOf('*') != -1)
					{
						i = sub.indexOf('*');
						sub = calc(sub, i);
					}
				}
			}

			//+ AND -
			while(sub.contains("+") || sub.contains("-"))
			{
				while(sub.charAt(0) == '-' && sub.charAt(1) == '-')
					sub = sub.replaceFirst("--", "");

				if(sub.indexOf('-') == 0 && sub.indexOf('-', 1) == -1 && sub.indexOf('+', 1) == -1)
					break;

				if(sub.indexOf('+') != -1)
				{
					i = sub.indexOf('+');
					sub = calc(sub, i);
				}
				else 
				{
					if(sub.indexOf('-', 1) != -1)
					{
						i = sub.indexOf('-', 1);
						sub = calc(sub, i);
					}
				}
			}
			// Delete ()
			str = leftStr + sub + rightStr;
		}


		while(str.contains("*") || str.contains("/"))
		{
			if(str.indexOf('/') != -1)
			{
				i = str.indexOf('/');
				str = calc(str, i);
			}
			else 
			{
				if(str.indexOf('*') != -1)
				{
					i = str.indexOf('*');
					str = calc(str, i);
				}
			}
		}

		//+ AND -
		while(str.contains("+") || str.contains("-"))
		{	
			while(str.charAt(0) == '-' && str.charAt(1) == '-')
				str = str.replaceFirst("--", "");

			if(str.indexOf('-') == 0 && str.indexOf('-', 1) == -1 && str.indexOf('+', 1) == -1)
				break;

			else 
			{
				if((str.indexOf('+') != -1))
				{
					i = str.indexOf('+');
					str = calc(str, i);
				}
				else 
				{
					if(str.indexOf('-', 1) != -1) 
					{
						i = str.indexOf('-', 1);
						str = calc(str, i);
					}
				}
			}
		}

		result = new BigDecimal(str).setScale(5,RoundingMode.DOWN);
		return result.toString();
	}

	static String calc(String str, int i)
	{
		String num1 = "";
		String num2 = "";
		String rightSub = "";
		String leftSub = "";
		BigDecimal result = new BigDecimal(0);
		String sub;
		int flag = 0;

		int k = i + 1;

		if(str.charAt(i+1)=='-')
		{
			if(str.charAt(i)=='+')
				str = str.replace("+-", "-");

			if(str.charAt(i)=='-')
				str = str.replace("--", "+");	

			if(str.charAt(i)=='/' || str.charAt(i)=='*')
			{
				flag = 1;
				str = str.substring(0,i+1) + str.substring(i+2);
			}
		}
		while(k<str.length() && ((str.charAt(k) >= '0' && str.charAt(k) <= '9') || str.charAt(k) == '.'))
		{
			num1 = num1 + str.charAt(k);
			k++;
		}

		if(k < str.length())
			rightSub = str.substring(k);

		k=i-1;

		while(k>=0 && ((str.charAt(k) >= '0' && str.charAt(k) <= '9') || str.charAt(k) == '.'))
		{
			num2 = str.charAt(k) + num2;
			k--;
		}

		if(k > 0 && str.charAt(k) == '-')
		{
			leftSub = str.substring(0, k);
			if(str.charAt(k-1) >= '0' && str.charAt(k-1) <= '9')
				leftSub = leftSub + "+";
		}
		else 
		{
			if(k >= 0 && str.charAt(k) != '-')
				leftSub = str.substring(0, k+1);	
		}

		if(k >= 0 && str.charAt(k) == '-')
		{
			if(str.charAt(i) == '+')
				result = new BigDecimal(num1).subtract(new BigDecimal(num2), MathContext.DECIMAL128);
			if(str.charAt(i) == '-')
			{
				result = new BigDecimal(num1).add(new BigDecimal(num2), MathContext.DECIMAL128);
				result = result.negate();
			}
			if(str.charAt(i) == '*')
			{
				result = new BigDecimal(num2).multiply(new BigDecimal(num1), MathContext.DECIMAL128);
				result = result.negate();
			}
			if(str.charAt(i) == '/')
			{
				result = new BigDecimal(num2).divide(new BigDecimal(num1), MathContext.DECIMAL128);
				result = result.negate();
			}
		}

		else 
		{
			if(str.charAt(i)=='*')
				result = new BigDecimal(num2).multiply(new BigDecimal(num1), MathContext.DECIMAL128);
			if(str.charAt(i)=='/')
				result = new BigDecimal(num2).divide(new BigDecimal(num1), MathContext.DECIMAL128);
			if(str.charAt(i)=='+')
				result = new BigDecimal(num2).add(new BigDecimal(num1), MathContext.DECIMAL128);
			if(str.charAt(i)=='-')
				result = new BigDecimal(num2).subtract(new BigDecimal(num1), MathContext.DECIMAL128);
		}

		if(flag == 1)
			result = result.negate();

		sub = leftSub + String.valueOf(result) + rightSub;
		return sub;
	}
}

