package Blackjack_;

public class Card {
 
 private String face;
 private String suit;
 
 public Card() {
  
  this.face = null;
  this.suit = null;
  
 }
 
 public String getFace() {
  return face;
 }
 
 public String getSuit() {
  return suit;
 }
 
 public void setFace(String face) {
  this.face = face;
 }
 
 public void setSuit(String suit) {
  this.suit = suit;
 }

}