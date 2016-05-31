import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by mmarchuk on 4/18/16.
 */
public class CardTest {

    @Test
    public void testGettersAndSetters() throws Exception {
        Card card = new Card("Clubs", "7", 7);
        assertEquals(card.getRank(), "7");
        assertEquals(card.getSuit(), "Clubs");
    }
}