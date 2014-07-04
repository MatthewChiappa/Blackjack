package Blackjack_;

import java.util.*;

public class Dealer extends Game {
 
 Card[] hand = new Card[10]; 
 int handCount;
 boolean cardIsHidden;
 boolean ace;
 
 public Dealer() {
  handCount = 0;
  cardIsHidden = true;
 }
 
 public int getCount() {
  return handCount;
 }
 
 public void incCount() {
  handCount++;
 }
 
 public boolean cardIsHidden() {
   return cardIsHidden;
 }
 
 public void changeCard(boolean card) {
   cardIsHidden = card;
 }
 
 public boolean checkForBust() {
   int sum = 0;
   int temp;
   
   for(int i = 0; i < handCount; i++){
     temp = convToInt(hand[i].getFace());
     sum += temp;
   }
   
   if(sum > 21) 
     return true;
   
   else
     return false;
 }
 
 public void dealerMove(Deck deck) {
   Scanner scan = new Scanner(System.in);
   changeCard(false);
   
   int sum = 0;
   int temp;
   
   for(int i = 0; i < handCount; i++){
     if(hand[i] == null)
       break;
     
     temp = convToInt(hand[i].getFace());
     sum += temp;
   }
   
   if(sum <= 17) {
     printHand();
     hitCard(deck);
     printHand();
     System.out.println("\nContinue? (Y)");
     String input = scan.nextLine();
     System.out.println();
     if(input.equals("Y"))
       dealerMove(deck);
     else 
       dealerMove(deck);
   }
  
 }
  
  public void hitCard(Deck deck) {
   hand[handCount] = deck.drawCard();
   incCount();
   deck.incCardCount();
 }
  
 public boolean checkBlackjack() {
   int sum = 0;
   int temp;
   
   for(int i = 0; i < handCount; i++){
     temp = convToInt(hand[i].getFace());
     sum += temp;
   }
   
   if (sum == 21)
     return true;
   else 
     return false;
  }
 
 public int convToInt(String card) {
   int num;
  
   if(card.equals("Two"))
     num = 2;
   else if(card.equals("Three"))
     num = 3;
   else if(card.equals("Four"))
     num = 4;
   else if(card.equals("Five"))
     num = 5;
   else if(card.equals("Six"))
     num = 6;
   else if(card.equals("Seven"))
    num = 7;
   else if(card.equals("Eight"))
    num = 8;
   else if(card.equals("Nine"))
    num = 9;
   else if(card.equals("Ace")){
    if(aceDecision())
      num = 1;
    else
      num = 11;
   }
   else
    num = 10;

    return num;  
  }
 
 public boolean aceDecision() {
   int sum = 0;
   int temp;
   
   for(int i = 0; i < handCount; i++){
     if(hand[i] == null)
       break;
     else if(hand[i].getFace().equals("Ace"))
       temp = 11;
     else
       temp = convToInt(hand[i].getFace());
     sum += temp;
   }
   
   if(sum > 11)
     return true;
   else
     return false;
   
 }
 
 public void printHand() {
   
   System.out.println("\nDealer:");
   for(int i = 0; i < handCount; i++){
     if(hand[i] == null)
       break;
     if(cardIsHidden() && i == 1)
       System.out.println("*HIDDEN*");
     else {
       if(i == handCount-1)
         System.out.print(hand[i].getFace() + " of " + hand[i].getSuit() + "\n");
       else
         System.out.print(hand[i].getFace() + " of " + hand[i].getSuit() + ", ");
     }
   }
 }

}