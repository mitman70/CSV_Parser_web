package com.mahmud.card.core.exeception;

public class CardException extends Exception
{
      //Parameterless Constructor
      /**
	 * 
	 */
	private static final long serialVersionUID = -885937774630173338L;

	public CardException() {}

      //Constructor that accepts a message
      public CardException(String message)
      {
         super(message);
      }
 }
