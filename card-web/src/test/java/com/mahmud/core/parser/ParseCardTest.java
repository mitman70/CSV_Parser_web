package com.mahmud.core.parser;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mahmud.card.core.model.Card;
import com.mahmud.card.core.parser.CardCSVParser;
import com.mahmud.card.core.parser.IParser;

public class ParseCardTest {
	
	
	private ArrayList<Card> expectedCards = new ArrayList<Card>();
	
	private String fileName = "test.csv";
	
	
	@Before
	public void setup()
	{
		setupCards();
		createTestCSV(fileName);
	}
	
	
	
	private void setupCards()
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
	
	
	
	private void createTestCSV ( String fileName )
	{
	    try
	    {
	        FileWriter writer = new FileWriter( fileName );
	        writer.append( "HSBC Canada" );
	        writer.append( ',' );
	        writer.append( "5601-2345-3446-5678" );
	        writer.append( ',' );
	        writer.append( "Nov-2017" );
	        writer.append( '\n' );
	        
	        writer.append( "Royal Bank of  Canada" );
	        writer.append( ',' );
	        writer.append( "4519-4532-4524-2456" );
	        writer.append( ',' );
	        writer.append( "Oct-2017" );
	        writer.append( '\n' );
	        
	        writer.append( "American Express" );
	        writer.append( ',' );
	        writer.append( "3786-7334-8965-345" );
	        writer.append( ',' );
	        writer.append( "Dec-2018" );
	        writer.append( '\n' );
	        writer.flush();
	        writer.close();
	    }
	    catch ( IOException e )
	    {
	        e.printStackTrace();
	    }
	}
	

	@Test
	public void testParseCSVFromDirectory() {
		IParser csvParser = new CardCSVParser();
		csvParser.setDir("./");
		
		ArrayList<Card> actualCards = csvParser.parse();
		
		assertEquals(actualCards.get(0).getBankName(),expectedCards.get(0).getBankName());
		assertEquals(actualCards.get(0).getCardNo(),expectedCards.get(0).getCardNo());
		assertEquals(actualCards.get(0).getExpiryDate(),expectedCards.get(0).getExpiryDate());
	}
	
	@Test
	public void testParseCSVFromFile() {
		
		File file = new File(fileName);
		IParser csvParser = new CardCSVParser();
		csvParser.setFile(file);
		
		ArrayList<Card> actualCards = csvParser.parse();
		
		assertEquals(actualCards.get(0).getBankName(),expectedCards.get(0).getBankName());
		assertEquals(actualCards.get(0).getCardNo(),expectedCards.get(0).getCardNo());
		assertEquals(actualCards.get(0).getExpiryDate(),expectedCards.get(0).getExpiryDate());
	}
	
	@After
	public void tearDown()
	{
		try{
    		
    		File file = new File(fileName);
        	
    		if(file.delete()){
    			System.out.println(file.getName() + " is deleted!");
    		}else{
    			System.out.println("Delete operation is failed.");
    		}
    	   
    	}catch(Exception e){
    		
    		e.printStackTrace();
    		
    	}
	}

}
