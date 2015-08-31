package com.mahmud.card.core.parser;

import java.io.File;
import java.util.ArrayList;

import com.mahmud.card.core.model.Card;


public interface IParser {

	void setDir(String dir);

	ArrayList<Card> parse();
	
	void setFile(File file);

}
