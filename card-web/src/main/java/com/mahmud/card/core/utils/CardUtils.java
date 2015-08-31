package com.mahmud.card.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import com.mahmud.card.core.exeception.CardException;



public class CardUtils {

	public static String maskCardNo(String cardNo, String pattern) throws CardException {

		char[] cardArray = cardNo.toCharArray();
		char[] cardTypeArray = pattern.toCharArray();
		if(cardArray.length!=cardTypeArray.length)
		{
			throw new CardException("Card no "+cardNo+" not valid");
		}
		char[] maskedCardArray = new char[cardNo.length()];
		Arrays.fill(maskedCardArray, '-');
		
		int index = 0;
		for(char c: cardTypeArray)
		{
			if(c == '#')
			{
				maskedCardArray[index] = cardArray[index];
			}else if(c == 'x')
			{
				maskedCardArray[index] = 'x';
			}
			index++;
		}
		
		return new String(maskedCardArray);
	}
	
	public static Date createExpiryDate(SimpleDateFormat dt, String expDateStr) {
		Date expDate = new Date();
		try {
			expDate = dt.parse(expDateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return expDate;
	}

}
