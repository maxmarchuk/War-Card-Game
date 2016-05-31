import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import static junit.framework.Assert.*;

/**
 * Created by mmarchuk on 4/18/16.
 */
public class WarDeckTest {

    @org.junit.Test
    public void testCreate() throws Exception {
        WarDeck warDeck = new WarDeck();
        warDeck.create(4, 13);
        assertEquals(true, (warDeck.deck.size() == (4 * 13)));
    }

    @org.junit.Test
    public void testRoundWinning() throws Exception {
        War war = new War();
        HashMap<Integer, Card> deck = new HashMap<>();
        ArrayList<Player> players = new ArrayList<>();
        Stack<Card> potDeck = new Stack<>();
        players.add(0, new Player(0));
        players.add(1, new Player(1));
        players.add(2, new Player(2));

        deck.put(0, new Card("clubs", "1", 1));
        deck.put(1, new Card("diamonds", "2", 2));
        deck.put(2, new Card("spades", "3", 3));

        war.filterPlayingDeck(deck, potDeck, players);
        assertEquals(true, (war.getRoundWinner(players) == 2));
        assertEquals(false, (players.get(0).isPlayingRound()));
        assertEquals(false, (players.get(1).isPlayingRound()));
        assertEquals(true, (potDeck.size() == 3));
    }

    @org.junit.Test
    public void testRoundContinuing() throws Exception {
        War war = new War();
        HashMap<Integer, Card> deck = new HashMap<>();
        ArrayList<Player> players = new ArrayList<>();
        Stack<Card> potDeck = new Stack<>();

        players.add(0, new Player(0));
        players.add(1, new Player(1));
        players.add(2, new Player(2));


        deck.put(0, new Card("clubs", "1", 1));
        deck.put(1, new Card("diamonds", "2", 2));
        deck.put(2, new Card("spades", "2", 2));

        war.filterPlayingDeck(deck, potDeck, players);
        assertEquals(true, (war.getRoundWinner(players) == -1));

        System.out.println(potDeck.size());
        assertEquals(true, (potDeck.size() == 3));
    }

}