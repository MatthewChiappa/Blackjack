package Blackjack_;

import java.util.*;

public class Deck {
 
 int cardCount;
 final public String[] faces = {"Two", "Three", "Four", "Five", "Six",
         "Seven", "Eight", "Nine", "Jack", "Queen", "King", "Ace"};
 final public String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
 
 Card[] cards = new Card[52];
 
 public Deck() {
  cardCount = 0;

  int count = 0;
  for(int x = 0; x < suits.length; x++) {
   
   for(int i = 0; i < faces.length; i++) {
    cards[count] = new Card();
    cards[count].setFace(faces[i]);
    cards[count].setSuit(suits[x]);
    if(cards[count] == null)
      throw new NullPointerException();
    count++;
   }
  }

  cards = shuffleDeck(cards);
 }
 
 public Card[] shuffleDeck(Card[] deck) {
  
  Random rand = new Random();
  Card temp = new Card();
  int randomNum;
  for(int i = 0; i < cards.length; i++) {
   randomNum = rand.nextInt(cards.length)-1;
   while(randomNum < 0 || randomNum > 52)
    randomNum = rand.nextInt(cards.length)-1;
   temp = deck[i];
   deck[i] = deck[randomNum];
   deck[randomNum] = temp;
  }
  
  return deck;
 }
 
 public Card[] getCards() {
   return cards;
 }
 
 public Card drawCard() {
  // remember to increment the card count
  while(cards[cardCount] == null)
    cardCount++;
  return cards[cardCount];
 }
 
 public void incCardCount() {
  cardCount++;
 }
 
 public int getCount() {
   return cardCount;
 }
 
 // test method
 public void printDeck() {
  for(int i = 0; i < cards.length; i ++)
   System.out.println(cards[i].getFace() + " of " + cards[i].getSuit());
  System.out.println();
 }
 
}