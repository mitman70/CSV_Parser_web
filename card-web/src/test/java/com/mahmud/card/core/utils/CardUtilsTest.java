package com.mahmud.card.core.utils;

import static org.junit.Assert.*;

import org.junit.Test;

import com.mahmud.card.core.exeception.CardException;
import com.mahmud.card.core.utils.CardUtils;

public class CardUtilsTest {

	@Test
	public void testHSBCCardMask()  throws CardException{
		String expectedMaskedCardNo = "56xx-xxxx-xxxx-xxxx";
		
		String actualMaskedCardNo = CardUtils.maskCardNo("5601-2345-3446-5678","##xx-xxxx-xxxx-xxxx");
		
		assertEquals(expectedMaskedCardNo, actualMaskedCardNo);
		
	}
	
	@Test
	public void testRBCCardMask() throws CardException {
		String expectedMaskedCardNo = "4519-xxxx-xxxx-xxxx";
		
		String actualMaskedCardNo = CardUtils.maskCardNo("4519-4532-4524-2456","####-xxxx-xxxx-xxxx");
		
		assertEquals(expectedMaskedCardNo, actualMaskedCardNo);
		
	}
	
	@Test
	public void testAMEXCardMask() throws CardException {
		String expectedMaskedCardNo = "xxxx-xxxx-xxxx-345";
		
		String actualMaskedCardNo = CardUtils.maskCardNo("3786-7334-8965-345","xxxx-xxxx-xxxx-###");
		
		assertEquals(expectedMaskedCardNo, actualMaskedCardNo);
		
	}
	
	
	@Test(expected=CardException.class)
	public void testAMEXCardNotValid() throws CardException {
		String expectedMaskedCardNo = "xxxx-xxxx-xxxx-345";
		
		String actualMaskedCardNo = CardUtils.maskCardNo("3786-7334-8965-3","xxxx-xxxx-xxxx-###");
		
		
		
	}
	

}
