import java.lang.reflect.Array;
import java.util.*;

public class War {
    public void play(int numberOfSuits, int numberOfRanks, int numberOfPlayers) {
        if(numberOfPlayers < 2) {
            System.out.println("Must have at least 2 players to play War");
            return;
        }
        if(numberOfSuits < 1) {
            System.out.println("Must have one or more suits to play War");
            return;
        }
        if(numberOfRanks < 1) {
            System.out.println("Must have one or more ranks to play War");
            return;
        }

        // Setup the game
        WarDeck deck = new WarDeck();
        deck.create(numberOfSuits, numberOfRanks);
        deck.shuffle();

        ArrayList<Player> players = new ArrayList<>(numberOfPlayers);
        for(int i = 0; i < numberOfPlayers; i++){
            players.add(i, new Player(i));
        }
        int currentPlayer = 0;
        int deckSize = deck.deck.size();

        // Deal a card from the deck to every player
        for (int i = 0; i <= deckSize - 1; i++) {
           players.get(currentPlayer).addToHand(deck.deal());

            if (currentPlayer == numberOfPlayers - 1) {
                currentPlayer = 0;
            } else {
                currentPlayer++;
            }
        }

        int winner = playWarGame(players);

        System.out.println("Player " + winner + " wins!");
    }

    public int playWarGame(ArrayList<Player> players){
        boolean gameOver = false;
        boolean warring = false;
        Stack<Card> potDeck = new Stack();
        HashMap<Integer, Card> cardsBeingPlayed = new HashMap<>();

        while (!gameOver) {
            // reset the variables needed
            potDeck.clear();
            cardsBeingPlayed.clear();
            players.forEach(player -> player.startPlayingRound());

            boolean roundOver = false;
            // Play the round
            while(!roundOver) {
                // Get the current cards in play
                for (Player player : players) {
                    if (player.isPlayingGame() && player.isPlayingRound()) {
                        Card tmpCard = player.getCard();
                        if(tmpCard != null){
                            cardsBeingPlayed.put(player.getNumber(), tmpCard);
                        } else {
                            // Player is out of cards. mark as inactive
                            player.setDonePlayingGame();
                            int winner = getWinner(players);
                            if(winner != -1) {
                                gameOver = true;
                                System.out.println("WINNING DECK: " + players.get(winner).hand+ "");
                                return winner;
                            }
                        }
                        if(warring){
                            // If there's a war, add another card to the pot
                            tmpCard = player.getCard();
                            if(tmpCard != null){
                                potDeck.push(tmpCard);
                            } else {
                                // Player is out of cards. mark as inactive
                                player.setDonePlayingGame();
                                int winner = getWinner(players);
                                if(winner != -1) {
                                    gameOver = true;
                                    return winner;
                                }
                            }
                        }
                    }
                }

                cardsBeingPlayed = filterPlayingDeck(cardsBeingPlayed, potDeck, players);
                int roundWinner = getRoundWinner(players);
                if(roundWinner != -1) {
                    roundOver = true;
                    warring = false;
                    System.out.println("Player " + roundWinner+ " wins the round and wins " + potDeck);
                    players.get(roundWinner).addToHand(potDeck);
                } else{
                    warring = true;
                }
            }
        }

        // determine who the winner is based on the index of the player hand with cards
        return -1;
    }

    public int getWinner(ArrayList<Player> players) {
        int stillInPlay = 0;
        int winner = -1;
        for (Player player : players) {
            if (player.isPlayingGame()){
                stillInPlay++;
                winner = player.getNumber();
            }
        }
        if(stillInPlay > 1){
            return -1;
        } else {
            return winner;
        }
    }

    public int getRoundWinner(ArrayList<Player> players) {
        int stillInPlay = 0;
        int roundWinner = -1;
        for(int c = 0; c < players.size(); c++) {
            boolean playing = players.get(c).isPlayingRound();
            if(playing){
                stillInPlay++;
                roundWinner = c;
            }
        }
        if(stillInPlay > 1){
            return -1;
        } else {
            return roundWinner;
        }
    }

    // Function to split the deck into two decks based on highest and lowest values
    //
    // Returns a new deck
    public HashMap<Integer, Card> filterPlayingDeck(HashMap<Integer, Card> deck, Stack<Card> potDeck, ArrayList<Player> players) {
        int highestValue = 0;
        HashMap<Integer, Card> highCards = new HashMap<>();

        // get the highest value of a card in the deck
        for (int c:deck.keySet()) {
            int value = deck.get(c).getValue();
            if(value > highestValue) {
                highestValue = value;
            }
        }

        // do the filtering based on the highest value
        for (int c:deck.keySet()) {
            int value= deck.get(c).getValue();
            if(value < highestValue) {
                // set that player's round to done
                players.get(c).setDonePlayingRound();
                potDeck.push(deck.get(c));
            } else {
                potDeck.push(deck.get(c));
                highCards.put(c, deck.get(c));
            }
        }

        if(highCards.size() == 1){
            highCards.clear();
        }
        return highCards;
    }
}