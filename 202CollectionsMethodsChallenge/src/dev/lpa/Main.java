package dev.lpa;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        LinkedList<Card> standardDeck = Card.getStandardDeck();
        Collections.shuffle(standardDeck);
        List<Player> table = new ArrayList<>();

        List<Player> players = new ArrayList<>();

        Player gruby = new Player("Gruby");
        players.add(gruby);
        Player matfiz = new Player("Matfiz");
        players.add(matfiz);
        Player deam = new Player("Deam");
        players.add(deam);
        Player pilkarzyk = new Player("Piłkarzyk");
        players.add(pilkarzyk);

        for(int i = 0; i < 4; i++){

            for(int j = 0; j < 3; j++){
                players.get(i).addCardsToDeck(standardDeck.get(standardDeck.size() - 1 - i));
                standardDeck.remove(standardDeck.size() - 1 - i);
            }
        }

        gruby.addCardsToDeck(standardDeck.get(standardDeck.size() - 1));
        standardDeck.remove(standardDeck.get(standardDeck.size() - 1));

        var suitSorter = Comparator.comparing(Card :: getSuit)
                        .thenComparing(Card :: getFace);

        var faceSorter = Comparator.comparing(Card :: getFace)
                .thenComparing(Card :: getSuit);

//        players.forEach(s -> System.out.println(s.getPlayersDeck()));
        int i = 0;
        Player nextPlayer = new Player();

        players.forEach(System.out :: println);
        System.out.println();
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        while(table.size() < 3){

            if(i == players.size()){
                i = 0;
            }
            if(i == players.size() - 1){
                nextPlayer = players.get(0);
            }
            else{
                nextPlayer = players.get(i + 1);
            }


            if(isSuitKuku(players.get(i).getPlayersDeck())){
                table.add(players.get(i));
                nextPlayer.getPlayersDeck().add(players.get(i).leftSearch(true));
                players.get(i).getPlayersDeck().remove(players.get(i).leftSearch(true));
                System.out.println(players.get(i).getName() + " Kuku suit");
                players.remove(players.get(i));
            }
            else if(isFaceKuku(players.get(i).getPlayersDeck())){
                table.add(players.get(i));
                nextPlayer.getPlayersDeck().add(players.get(i).leftSearch(false));
                players.get(i).getPlayersDeck().remove(players.get(i).leftSearch(false));
                System.out.println(players.get(i).getName() + " Kuku face");
                players.remove(players.get(i));
            }
            else{
                if(players.get(i).getPlayersDeck().contains(players.get(i).getPreviousCard())){
                    standardDeck.push(players.get(i).getPreviousCard());
                    players.get(i).getPlayersDeck().remove(players.get(i).getPreviousCard());
                    players.get(i).addCardsToDeck(standardDeck.get(standardDeck.size() - 1));

                    standardDeck.remove(standardDeck.size() - 1);
                }
                if(mostSuits(players.get(i).getPlayersDeck()) != null){
                    Card.Suit matchingSuit = mostSuits(players.get(i).getPlayersDeck());
                    Card unneededCard = new Card();
                    for (ListIterator<Card> it = players.get(i).getPlayersDeck().listIterator(); it.hasNext();) {
                        if (it.next().getSuit() != matchingSuit) {
                            unneededCard = it.previous();
                            it.remove();
                            break;
                        }
                    }
                    nextPlayer.getPlayersDeck().add(unneededCard);
                    players.get(i).setPreviousCard(unneededCard);
                    System.out.println(players.get(i).getName() + " " + players.get(i).getPreviousCard());
                }
                else if(mostFaces(players.get(i).getPlayersDeck()) != null){
                    String matchingFace = mostFaces(players.get(i).getPlayersDeck());
                    Card unneededCard = new Card();
                    for(Iterator<Card> it  = players.get(i).getPlayersDeck().iterator(); it.hasNext();){

                        if(! it.next().getFace().equals(matchingFace)){
                            unneededCard = it.next();
                            it.remove();
                            break;
                        }
                    }
                    nextPlayer.getPlayersDeck().add(unneededCard);
                    players.get(i).setPreviousCard(unneededCard);
                    System.out.println(players.get(i).getName() + " " + players.get(i).getPreviousCard());
                }
                else{
                    nextPlayer.getPlayersDeck().add(players.get(i).getPlayersDeck().get(players.get(i).getPlayersDeck().size() - 1));
                    players.get(i).getPlayersDeck().remove(players.get(i).getPlayersDeck().get(players.get(i).getPlayersDeck().size() - 1));
                }
                i++;

            }

            players.forEach(System.out :: println);
            System.out.println();
        }

        table.add(players.get(0));
        System.out.println("\n" + table.get(0) + "\n" + table.get(1) + "\n" + table.get(2) + "\n" + table.get(3) );
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    }



    public static Card.Suit mostSuits(List<Card> hand){
        var suitSorter = Comparator.comparing(Card :: getSuit)
                .thenComparing(Card :: getFace);
        hand.sort(suitSorter);

        for(int i = 0; i < hand.size(); i++){
            try {
                if (hand.get(i).getSuit().equals(hand.get(i + 1).getSuit())) {
                    return hand.get(i).getSuit();
                }
            }catch (IndexOutOfBoundsException ioobe){
                break;
            }
        }
        return null;
    }

    public static String mostFaces(List<Card> hand){
        var faceSorter = Comparator.comparing(Card :: getFace)
                .thenComparing(Card :: getSuit);
        hand.sort(faceSorter);

        for(int i = 0; i < hand.size(); i++){
            try {
                if (hand.get(i).getFace().equals(hand.get(i + 1).getFace())) {
                    return hand.get(i).getFace();
                }
            }catch (IndexOutOfBoundsException ioobe){
                break;
            }
        }
        return null;
    }

    public static boolean isSuitKuku(List<Card> hand){
        var suitSorter = Comparator.comparing(Card :: getSuit)
                .thenComparing(Card :: getFace);
        hand.sort(suitSorter);
        int counter = 1;
        for(int i = 0; i < hand.size(); i++){
            if(counter == 3){
                return true;
            }
            try {
                if (hand.get(i).getSuit().equals(hand.get(i + 1).getSuit())) {
                    counter++;
                }
                else counter = 1;
            }catch (IndexOutOfBoundsException ioobe){
                break;
            }
        }

        return counter >= 3;
    }

    public static boolean isFaceKuku(List<Card> hand){
        var faceSorter = Comparator.comparing(Card :: getFace)
                .thenComparing(Card :: getSuit);
        hand.sort(faceSorter);
        int counter = 1;
        for(int i = 0; i < hand.size(); i++){
            if(counter == 3){
                return true;
            }
            try {
                if (hand.get(i).getFace().equals(hand.get(i + 1).getFace())) {
                    counter++;
                }
                else counter = 1;
            }catch (IndexOutOfBoundsException ioobe){
                break;
            }
        }

        return counter >= 3;
    }

}
