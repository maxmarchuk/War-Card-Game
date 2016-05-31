
public interface Deck {

    /**
     * Creates a deck of cards
     *
     * @param numberOfSuits The number of different suits this deck will have
     * @param numberOfRanks The number of different ranks this deck will have
     */
    public void create(int numberOfSuits, int numberOfRanks);

    /**
     * Shuffles the created deck of cards
     */
    public void shuffle();

    /**
     * Returns a card from the deck
     */
    public Card deal();
}