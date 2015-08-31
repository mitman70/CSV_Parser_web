package com.mahmud.card.core.service;

import java.io.File;
import java.util.List;

import com.mahmud.card.core.model.Card;
import com.mahmud.card.core.parser.IParser;

public interface ICardService {
	
	public List<Card> processCards(boolean ascending);
	public IParser getParser();
	public String printCardDetails(List<Card> cards);
}
