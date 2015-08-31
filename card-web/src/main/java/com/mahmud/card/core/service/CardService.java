package com.mahmud.card.core.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.mahmud.card.core.exeception.CardException;
import com.mahmud.card.core.model.Card;
import com.mahmud.card.core.parser.IParser;
import com.mahmud.card.core.utils.CardUtils;

public class CardService implements ICardService {
	
	@Autowired
	private IParser parser;
	
	
	private Map<String,String> cardMapping;
	
	

	@Override
	public List<Card> processCards(boolean descending) {
		
		ArrayList<Card> processdCardsList = new ArrayList<Card>();
		
		ArrayList<Card> cardsList = parser.parse();
		for(Card card:cardsList)
		{
			if(cardMapping.containsKey(card.getBankName()))
			{
				try {
					String maskedCardNo = CardUtils.maskCardNo(card.getCardNo(), cardMapping.get(card.getBankName()));
					card.setMaskedCardNo(maskedCardNo);
					processdCardsList.add(card);
				} catch (CardException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return sortResults(descending, processdCardsList);
	}

	private List<Card> sortResults(boolean descending,
			ArrayList<Card> processdCardsList) {
		Collections.sort(processdCardsList);
		if(descending==true)
		{
			Collections.reverse(processdCardsList);
		}
		return processdCardsList;
	}

	@Override
	public IParser getParser() {
		// TODO Auto-generated method stub
		return parser;
	}

	@Override
	public String printCardDetails(List<Card> cards) {
		String output = "";
		for(Card card:cards)
		{
			output +=card.toString()+"\n";
		}
		return output;
	}

	public Map<String, String> getCardMapping() {
		return cardMapping;
	}

	public void setCardMapping(Map<String, String> cardMapping) {
		this.cardMapping = cardMapping;
	}

	public void setParser(IParser parser) {
		this.parser = parser;
	}



}
