import java.util.*;

class WarDeck implements Deck {
    String[] suits = null;
    HashMap<String, Integer> rankToValueMap = null;
    List<Card> deck = null;
    String[] ranks = null;
    Integer[] values = null;

    public WarDeck() {
        this.deck = new Stack();
        this.rankToValueMap = new HashMap<>();
        this.suits = new String[]{"clubs", "diamonds", "hearts", "spades"};
        this.ranks = new String[]{"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
        this.values = new Integer[]{2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};

        for (int i = 0; i < this.ranks.length; i++) {
            this.rankToValueMap.put(this.ranks[i], this.values[i]);
        }
    }

    @Override
    public void create(int numberOfSuits, int numberOfRanks) {
        for(int s = 0; s < numberOfSuits; s++){
            for(int r = 0; r < numberOfRanks; r++){
                this.deck.add(new Card(this.suits[s], this.ranks[r], this.values[r]));
            }
        }
    }

    @Override
    public void shuffle() {
        Collections.shuffle(this.deck, new Random(System.nanoTime()));

    }

    @Override
    public Card deal() {
        Card c = this.deck.get(deck.size()-1);
        this.deck.remove(deck.size()-1);
        return c;
    }


    @Override
    public String toString() {
        String deckString = "";
        deckString += "Deck Size: " + this.deck.size() + "\n";
        for (Object card: this.deck) {
            deckString += card.toString() + "\n";
        }

        return deckString;
    }
}