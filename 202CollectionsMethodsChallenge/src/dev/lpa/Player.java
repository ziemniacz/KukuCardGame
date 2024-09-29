package dev.lpa;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private String name;
    private Card previousCard;
    private List<Card> playersDeck = new ArrayList<>();

    public Player(String name) {
        this.name = name;
    }

    public Player() {
    }

    public List<Card> getPlayersDeck() {
        return playersDeck;
    }

    public String getName() {
        return name;
    }

    public Card getPreviousCard() {
        return previousCard;
    }

    public void setPreviousCard(Card previousCard) {
        this.previousCard = previousCard;
    }

    public void addCardsToDeck(Card card){
        playersDeck.add(card);
    }

    public Card leftSearch(boolean isSuit){
        if(isSuit) {
            if (playersDeck.get(0).getSuit() != playersDeck.get(playersDeck.size() - 1).getSuit() && playersDeck.get(0).getSuit().equals(playersDeck.get(1).getSuit())) {
                return playersDeck.get(playersDeck.size() - 1);
            }
            return playersDeck.get(0);
        }

        else{
            if (playersDeck.get(0).getFace() != playersDeck.get(playersDeck.size() - 1).getFace() && playersDeck.get(0).getFace().equals(playersDeck.get(1).getFace())) {
                return playersDeck.get(playersDeck.size() - 1);
            }
            return playersDeck.get(0);
        }
    }

    @Override
    public String toString() {
        return name + " " + playersDeck;
    }
}
