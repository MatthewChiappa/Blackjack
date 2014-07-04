// aceDecision (Player)

package Blackjack_;

import java.util.*;

public class Game {
 
 Deck deck;
 Player player;
 Dealer dealer;
 int bet;
 boolean split = false;
 boolean betTime = true;
 boolean firstTime = true;

 public static void main(String[] args) {
   
   System.out.println("\t\t\tWelcome To Blackjack!");
   System.out.println("Creating deck...");
   System.out.println("Shuffling deck...\n");
   
   Game newGame = new Game();
   newGame.newPlayer();
   newGame.initDrawCards();

 }

public void newPlayer() {
   player = new Player();
}
 
 public void initDrawCards() {
   if(firstTime)
     deck = new Deck();
   if (deck.getCount() > 40)
     deck = new Deck();
   else 
     deck.shuffleDeck(deck.getCards());
   firstTime = false;
   player.newGame();
   dealer = new Dealer();
   betTime = true;
  
   for (int i = 0; i < 2; i++) {
     player.hand[i] = deck.drawCard();
     player.incCount();
     deck.incCardCount();
     dealer.hand[i] = deck.drawCard();
     dealer.incCount();
     deck.incCardCount();
   }
   printHands();
   whatMove();
   
 }
 
 public void printHands() {
   
   bet();

   System.out.println("\nDealer:");
   for(int i = 0; i < dealer.getCount(); i++){
     if(dealer.cardIsHidden() && i == 1)
       System.out.println("*HIDDEN*");
     else {
       if(dealer.hand[i] == null)
       break;
       if(i == dealer.getCount()-1)
         System.out.print(dealer.hand[i].getFace() + " of " + dealer.hand[i].getSuit());
       else
         System.out.print(dealer.hand[i].getFace() + " of " + dealer.hand[i].getSuit() + ", ");
     }
   }
  System.out.println("\nPlayer:");
  for(int i = 0; i < player.getCount(); i++) {
    if(player.hand[i] == null)
       break;
    
    if(i == player.getCount()-1)
         System.out.println(player.hand[i].getFace() + " of " + player.hand[i].getSuit());
       else
         System.out.print(player.hand[i].getFace() + " of " + player.hand[i].getSuit() + ", ");
  }
 }
 
 public void bet() {
   
   Scanner scan = new Scanner(System.in);
  
   System.out.println("Bank: $" + player.getMoney());
   
   if(betTime) {
     System.out.println("\nHow much would you like to bet?");

     try {
       bet = scan.nextInt();
     }
     catch(InputMismatchException e) {
       System.out.println("Invalid Bet.\n");
       bet();
     }
     if(bet > player.getMoney()) {
       System.out.println("Invalid Bet.\n");
       bet();  
     }
     betTime = false;
   }
   
 }
 
 public void whatMove() {
   
   Scanner scan2 = new Scanner(System.in);
   
   System.out.println("\nWhat is your move? (Hit, Stay, Split*)");
   String play = scan2.nextLine();
   System.out.println();
   
   if(play.equals("Hit"))
     hit();
   else if(play.equals("Stay"))
     stay();
   else if (play.equals("Split"))
     split();
   else {
     System.out.println("Invalid entry.");
     whatMove();
   }
   
 }
 
 public void hit() {
   player.hitCard(deck);
   if(!player.checkForBust() && !player.checkBlackjack()){
     printHands();
     whatMove();
   }
   else if(player.checkBlackjack()) {
     System.out.println();
      printHands();
      System.out.println("\n\n\nBLACKJACK-YOU WIN!!\n\n\n");
      player.addMoney(bet);
      initDrawCards();
   }
   else {
     player.printHand();
     System.out.println("\n\n*BUST*\n\n\n");
     player.subMoney(bet);
     initDrawCards();
   }
 }
 
 public void stay() {
   dealer.dealerMove(deck);
   if(!dealer.checkBlackjack() && !dealer.checkForBust()) {
     if(player.checkBlackjack()) {
      System.out.println();
      printHands();
      System.out.println("\n\n\nBLACKJACK-YOU WIN!!\n\n\n");
      player.addMoney(bet);
      initDrawCards();
     }
     else if(player.checkForWin(dealer)) {
      System.out.println();
      printHands();
      System.out.println("\n\n\nYOU WIN!!\n\n\n");
      player.addMoney(bet);
      initDrawCards();
     }
     else if(checkForPush()) {
      printHands();
      System.out.println("\n\n\n\nIt's a tie-PUSH\n\n\n");
      initDrawCards();
    }
     else {
      printHands();
      System.out.println("\n\n\nDealer Wins-YOU LOSE\n\n\n");
      player.subMoney(bet);
      initDrawCards();
     }
   }
   else if(dealer.checkForBust()) {
     System.out.println("\n\n\nDealer Bust-YOU WIN!!\n\n\n");
     player.addMoney(bet);
     initDrawCards();
   }
   else {
     if(checkForPush()) {
      printHands();
      System.out.println("\n\n\n\nIt's a tie-PUSH\n\n\n");
      initDrawCards();
    }
     else {
       printHands();
      System.out.println("\n\n\nDealer Blackjack-YOU LOSE\n\n\n");
      player.subMoney(bet);
      initDrawCards();
     }
   }
 }
 
 public void split() {
   int num1 = convToInt(player.hand[0].getFace());
   int num2 = convToInt(player.hand[1].getFace());

   if(num1 != num2) {
     System.out.println("Split Invalid.\n");
     whatMove();
   }
   else {
     player.split();
   }
 }
 
 public boolean checkForPush() {
   int sumPlayer = 0, sumDealer = 0;
   int temp;
   
   for(int i = 0; i < player.getCount(); i++){
     if(player.hand[i] == null)
       break;
     
     temp = convToInt(player.hand[i].getFace());
     sumPlayer += temp;
   }
   
   for(int i = 0; i < dealer.getCount(); i++){
     if(dealer.hand[i] == null)
       break;
     
     temp = convToInt(dealer.hand[i].getFace());
     sumDealer += temp;
   }
   
   if (sumPlayer == sumDealer)
     return true;
   else 
     return false;
 }
 
 public int convToInt(String card) {
   int num;
   Scanner scan = new Scanner(System.in);
  
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
   
   for(int i = 0; i < player.getCount(); i++){
     if(player.hand[i] == null)
       break;
     else if(player.hand[i].getFace().equals("Ace"))
       temp = 11;
     else
       temp = convToInt(player.hand[i].getFace());
     sum += temp;
   }
   
   if(sum > 11)
     return true;
   else
     return false;
   
 }

}