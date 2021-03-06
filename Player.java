package Blackjack_;

import java.util.*;

public class Player extends Game {

 Card[] hand = new Card[10];
 Card[] splitHand = new Card[10];
 int handCount;
 int money = 0;
 
 public Player() {
  handCount = 0;
  if (money < 5)
    money = 100;
 }

 public void newGame() {
  handCount = 0;
  hand = new Card[10];
  if (money < 5){
    System.out.println("No Money Left In The Bank!\nReplenishing Money...\n\n");
    money = 100;
  }
}
 
 public int getCount() {
  return handCount;
 }
 
 public void incCount() {
  handCount++;
 }

 public int getMoney() {
  return money;
}

 public void addMoney(int inc) {
  money += inc;
}

 public void subMoney(int dec) {
  money -= dec;
}
 
 public void hitCard(Deck deck) {
   hand[handCount] = deck.drawCard();
   incCount();
   deck.incCardCount();
 }

 public void split() {
   //splithand[0] = hand[1];
   hand[1] = new Card();
}
 
 public void printHand() {
   
   System.out.println("\nPlayer:");
   for(int i = 0; i < handCount; i++){
     if(i == handCount-1)
       System.out.print(hand[i].getFace() + " of " + hand[i].getSuit() + "\n");
     else
       System.out.print(hand[i].getFace() + " of " + hand[i].getSuit() + ", ");
   }
 }
 
 public boolean checkForBust() {
   int sum = 0;
   int temp;
   
   for(int i = 0; i < handCount; i++){
     if(hand[i] == null)
       break;
     
     temp = convToInt(hand[i].getFace());
     sum += temp;
   }
   
   if(sum > 21) 
     return true;
   
   else
     return false;
 }
 
 public boolean checkForWin(Dealer dealer) {
   int num, sumPlayer = 0, sumDealer = 0;

   for(int i = 0; i < handCount; i++) {
     if(hand[i] == null)
       break;
     
     num = convToInt(hand[i].getFace());
     sumPlayer += num;
   }
  
  for(int i = 0; i < dealer.getCount(); i++) {
    if(dealer.hand[i] == null)
      break;
    
    num = convToInt(dealer.hand[i].getFace());
    sumDealer += num;
  }

  if(sumPlayer > sumDealer)
   return true;
  else 
   return false;
   
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
 
 public boolean aceDecision() {
   int sum = 0;
   int temp;
   
   for(int i = 0; i < handCount; i++){
     if(hand[i] == null)
       break; 
     
     temp = convToInt(hand[i].getFace());
     sum += temp;
   }
   
   if(sum > 11)
     return true;
   else
     return false;
   
 }
 
}
