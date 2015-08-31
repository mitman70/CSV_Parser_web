package com.mahmud.core.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;  

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.mahmud.card.core.model.Card;
import com.mahmud.card.core.parser.IParser;
import com.mahmud.card.core.service.CardService;


public class CardServiceTest {
	
	@Mock
	private IParser parser;
	
	private ArrayList<Card> mockedCardsFromParser = new ArrayList<Card>();
	
	private ArrayList<Card> expectedCards = new ArrayList<Card>();
	
	private Map<String,String> cardMapping = new HashMap<String,String>();
	
	private CardService cardService = new CardService();
	
	
	
	
	@Before
	public void setup()
	{
		
		MockitoAnnotations.initMocks(this);
		
		
		setupMockedCardsFromParser();
		setupExpectedCards();
		setupCardMapping();
		
		
		parser.setDir(".");
		cardService.setParser(parser);
		cardService.setCardMapping(cardMapping);
	}
	
	private void setupCardMapping()
	{
		cardMapping.put("HSBC Canada", "##xx-xxxx-xxxx-xxxx");
		cardMapping.put("Royal Bank of  Canada", "####-xxxx-xxxx-xxxx");
		cardMapping.put("American Express", "xxxx-xxxx-xxxx-###");
	}
	
	private void setupMockedCardsFromParser()
	{
		Card card1 = new Card();
		card1.setBankName("HSBC Canada");
		card1.setCardNo("5601-2345-3446-5678");
		String expDateStr1 = "Nov-2017";
		SimpleDateFormat dt = new SimpleDateFormat("MMM-yyyy"); 
		
		Date expDate1 = createExpiryDate(dt, expDateStr1);		
		card1.setExpiryDate(expDate1);
		
		Card card2 = new Card();
		card2.setBankName("Royal Bank of  Canada");
		card2.setCardNo("4519-4532-4524-2456");
		String expDateStr2 = "Oct-2017";
		
		
		Date expDate2 = createExpiryDate(dt, expDateStr2);		
		card2.setExpiryDate(expDate2);
		
		
		Card card3 = new Card();
		card3.setBankName("American Express");
		card3.setCardNo("3786-7334-8965-345");
		String expDateStr3 = "Dec-2018";
		
		
		Date expDate3 = createExpiryDate(dt, expDateStr3);		
		card3.setExpiryDate(expDate3);
		
		mockedCardsFromParser.add(card1);
		mockedCardsFromParser.add(card2);
		mockedCardsFromParser.add(card3);
		
		
		
	}
	
	private void setupExpectedCards()
	{
		SimpleDateFormat dt = new SimpleDateFormat("MMM-yyyy"); 
		
		
		Card card1 = new Card();
		card1.setBankName("American Express");
		card1.setCardNo("3786-7334-8965-345");
		card1.setMaskedCardNo("xxxx-xxxx-xxxx-345");
		String expDateStr1 = "Dec-2018";
		
		
		Date expDate1 = createExpiryDate(dt, expDateStr1);		
		card1.setExpiryDate(expDate1);
		
		Card card2 = new Card();
		card2.setBankName("HSBC Canada");
		card2.setCardNo("5601-2345-3446-5678");
		card2.setMaskedCardNo("56xx-xxxx-xxxx-xxxx");
		String expDateStr2 = "Nov-2017";
		
		
		Date expDate2 = createExpiryDate(dt, expDateStr2);		
		card2.setExpiryDate(expDate2);
		
		Card card3 = new Card();
		card3.setBankName("Royal Bank of  Canada");
		card3.setCardNo("4519-4532-4524-2456");
		card3.setMaskedCardNo("4519-xxxx-xxxx-xxxx");
		String expDateStr3 = "Oct-2017";
		
		
		Date expDate3 = createExpiryDate(dt, expDateStr3);		
		card3.setExpiryDate(expDate3);
		
		
		
		
		expectedCards.add(card1);
		expectedCards.add(card2);
		expectedCards.add(card3);
		
		
		
	}
	
	
	
	
	
	private Date createExpiryDate(SimpleDateFormat dt, String expDateStr) {
		
		Date expDate = null;
		try {
			expDate = dt.parse(expDateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return expDate;
	}

	@Test
	public void testRetrievingCardListInDescendingOrder() {
		
		when(parser.parse()).thenReturn(mockedCardsFromParser);
		
		List<Card> actualCards = cardService.processCards(true);
		
		assertEquals(actualCards.get(0).getBankName(),expectedCards.get(0).getBankName());
		assertEquals(actualCards.get(0).getExpiryDate(),expectedCards.get(0).getExpiryDate());
		
		Mockito.verify(parser).parse();
	}
	
	
	@Test
	public void testRetrievingCardListInAscendingOrder() {
		
		when(parser.parse()).thenReturn(mockedCardsFromParser);
		
		
		List<Card> actualCards = cardService.processCards(false);
		Collections.reverse(expectedCards);
		assertEquals(actualCards.get(0).getBankName(),expectedCards.get(0).getBankName());
		assertEquals(actualCards.get(0).getExpiryDate(),expectedCards.get(0).getExpiryDate());
		assertEquals(actualCards.get(0).getCardNo(),expectedCards.get(0).getCardNo());
		
		Mockito.verify(parser).parse();
	}
	
	@Test
	public void testPrintingCardDetails()
	{
		
		String actualCardOutput = cardService.printCardDetails(expectedCards);
		String expectedOutput = "American Express 3786-7334-8965-345 xxxx-xxxx-xxxx-345\n"+
				"HSBC Canada 5601-2345-3446-5678 56xx-xxxx-xxxx-xxxx\n"+
				"Royal Bank of  Canada 4519-4532-4524-2456 4519-xxxx-xxxx-xxxx\n";
		
		assertEquals(expectedOutput, actualCardOutput);
		
	}
	
	
	
	@Test
	public void testRetrievingEmptyCardListWhenPassingInFileIsNull() {
		
		when(parser.parse()).thenReturn(new ArrayList<Card>());
		
		
		parser.setFile(null);
		
		List<Card> actualCards = cardService.processCards(true);
		
		assertEquals(actualCards.size(),0);
		
		
		Mockito.verify(parser).parse();
	}

}
