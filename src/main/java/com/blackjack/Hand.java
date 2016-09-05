package com.blackjack;

import java.util.LinkedList;

public class Hand {

	LinkedList<Card> handOfCards;

	boolean isBust = false;

	Hand(){
		
		//Deal two initial cards from the deck
		this.handOfCards = new LinkedList<Card>();
	}

	public void addCard(Card newCard) {

		if (this.handOfCards.size() >= Game.MAX_CARDS_IN_HAND) {
			//return Game.MAX_CARDS_IN_HAND;   //5 cards is the max you can have, don't add another
		} else {
			handOfCards.add(newCard);
			//return handOfCards.size();
		}
	}

//	//Return the number of cards in the hand //TODO be given new card,
//	public int takeNewCard(Deck deckOfCards){
//		if (this.handOfCards.size() >= MAX_CARDS_IN_HAND) {
//			return MAX_CARDS_IN_HAND;   //5 cards is the max you can have, don't add another
//		} else {
//		handOfCards.add(deckOfCards.deal());
//		return handOfCards.size();
//		}
//
//	}


	public int size() {
		return handOfCards.size();

	}

	public int getMinimumScore() {
		
		//Assume ace is low.
		int s = 0;
		
		for (Card c : this.handOfCards) {
			
			int val = c.getMinValue();
			s += val;
		}
		
		return s;
		
	}
	
	public int getScoreClosestTo21(){
		
		//Calculate highest score that doesn't go bust
		//If player has gone bust, return the minimum score

		int maxValid = 0;
		int acesInHand = 0;
		
		//First, be optimistic. Calculate max val assuming ace will be counted as 11
		for (Card c : this.handOfCards){
				maxValid = maxValid + c.getMaxValue();
				if (c.specialTypeOfCard.equals(Card.ACE)) {
					acesInHand++ ;
				}
				
		}

		//Is this max score a valid one? 
		if (maxValid <= Game.TARGET_SCORE) {
			return maxValid;
		}
		
		//If hand is over 21, check to see if any aces are present, if so, count them as 1 and check if hand is now valid

		for (int a = acesInHand ; a > 0 ; a--){
			maxValid = maxValid - 10;
			if (maxValid <= Game.TARGET_SCORE) {
				return maxValid;
			}
		}
		
		//Still too high? Return maxValid and let Round figure it out
		return maxValid;
		
		
	}
	
	
	public boolean isBust(){
		
		//does hand equal over 21?

		if (isBust) {
			return true;
		}

		int score = this.getMinimumScore();

		if (score > Game.TARGET_SCORE) {
			setBust();

			return true;
		}

		return false;
		
	}



	public void setBust(){
		//make hand empty. Then score (which iterates over the list) will still work.
		isBust = true;
		this.handOfCards.clear();

	}

	
	
	public String toString(){
		String cards = "";
		for (Card c : handOfCards ) {
			cards = cards + c.toString() + " ";
		}
		return cards;
	}
	
}
