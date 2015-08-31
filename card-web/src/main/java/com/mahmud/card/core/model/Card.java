package com.mahmud.card.core.model;


import java.util.Date;



public class Card  implements Comparable<Card>{
	
	private String bankName;
	private String cardNo;
	private String maskedCardNo;
	private Date expiryDate;
	
	
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getMaskedCardNo() {
		return maskedCardNo;
	}
	public void setMaskedCardNo(String maskedCardNo) {
		this.maskedCardNo = maskedCardNo;
	}
	
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	
	@Override
	public int compareTo(Card card) {
		
		return getExpiryDate().compareTo(card.getExpiryDate());
		
	}
	
	@Override
	public String toString()
	{
		return getBankName()+" "+getCardNo()+" "+getMaskedCardNo();
	}
	

}
