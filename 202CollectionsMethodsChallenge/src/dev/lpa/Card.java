package dev.lpa;

import java.util.LinkedList;
import java.util.List;

public class Card {

    public enum Suit{
        CLUB, DIAMOND, HEART, SPADE

    }

    private Suit suit;
    private String face;
    private int rank;

    public Card(String face, Suit suit){
        this.suit = suit;
        this.face = face;
        this.rank = setRank();
    }

    public Card() {
    }

    public String getFace() {
        return face;
    }

    public Suit getSuit() {
        return suit;
    }

    public int getRank() {
        return rank;
    }

    private int setRank(){
        Integer rank = 0;
        switch (face){
            case "2" -> rank = 0;
            case "3" -> rank = 1;
            case "4" -> rank = 2;
            case "5" -> rank = 3;
            case "6" -> rank = 4;
            case "7" -> rank = 5;
            case "8" -> rank = 6;
            case "9" -> rank = 7;
            case "10" -> rank = 8;
            case "J" -> rank = 9;
            case "Q" -> rank = 10;
            case "K" -> rank = 11;
            case "A" -> rank = 12;
        }

        return rank;
    }

    public static LinkedList<Card> getStandardDeck(){
        String[] faces = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        LinkedList<Card> deck = new LinkedList<>();

        for(int suitNum = 0; suitNum < 4; suitNum++) {

            for (int i = 0; i < 13; i++) {

                if (suitNum == 0) {
                    deck.add(new Card(faces[i], Suit.CLUB));
                }
                else if(suitNum == 1){
                    deck.add(new Card(faces[i], Suit.DIAMOND));
                }
                else if (suitNum == 2) {
                    deck.add(new Card(faces[i], Suit.HEART));
                }
                else {
                    deck.add(new Card(faces[i], Suit.SPADE));
                }
            }
        }

        return deck;
    }

    public static void printDeck(String description, List<Card> cards, int rowCount){
        System.out.println(description);
        for (int j = 0; j < cards.size(); j++){
            if(j % (cards.size() / rowCount) == 0){
                System.out.println();
            }
            System.out.print(cards.get(j) + " ");
        }
        System.out.println();
    }

    public static void printDeck(List<Card> cards){
        System.out.println("Current Deck");
//        for(int i = 0; i < 52; i++){
//            System.out.println(cards.get(i) + " ");
//        }
        for (int j = 0; j < 52; j++){
            if(j % 13 == 0){
                System.out.println();
            }
            System.out.print(cards.get(j) + " ");
        }
        System.out.println();

    }

    public static Card getNumericCard(Suit suit, int cardNumber){
        if(cardNumber > 1 && cardNumber < 11){
            return new Card(String.valueOf(cardNumber), suit);
        }
        return null;
    }

    public static Card getFaceCard(Suit suit, char cardFace){
        if(cardFace == 'J' || cardFace == 'Q' || cardFace == 'K' || cardFace == 'A'){
            return new Card(Character.toString(cardFace), suit);
        }
        return null;
    }

    @Override
    public String toString() {
        char suit = ' ';
        switch (this.suit){
            case CLUB -> suit = 9827;
            case DIAMOND -> suit = 9830;
            case HEART -> suit = 9829;
            case SPADE -> suit = 9824;
//            default -> suit = 0;
        }
        return face + suit;
    }
}
