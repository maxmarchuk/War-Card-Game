import java.util.*;

public class Player {
    private int number;
    private boolean playingGame;
    private boolean playingRound;

    public void setHand(Stack<Card> hand) {
        this.hand = hand;
    }

    public Stack<Card> hand;

    public Player(int number) {
        this.number = number;
        this.playingGame = true;
        this.playingRound = true;
        this.hand = new Stack();
    }

    public int getNumber() {
        return number;
    }

    public boolean isPlayingGame() {
        return this.playingGame;
    }

    // Called when a player has no more cards in their deck and
    // can no longer play the game
    public void setDonePlayingGame() {
        this.playingGame = false;
    }

    // Called when the player's card is lower than another player's card
    // therefore dropping them out of the current round
    public boolean isPlayingRound () {
        return this.playingRound;
    }

    // Called at the start of each round
    public void startPlayingRound() {
        this.playingRound = true;
    }

    public void setDonePlayingRound() {
        this.playingRound = false;
    }

    public void addToHand(Card newCard) {
        this.hand.push(newCard);
    }

    public void addToHand(Stack<Card> newCards) {
        newCards.addAll(this.hand);
        this.hand.clear();
        for (Card card : newCards) {
            this.hand.push(card);
        }
    }

    public Card getCard() {
        if(this.hand.isEmpty()){
            this.playingGame = false;
            this.playingRound= false;
            return null;
        }
        return this.hand.pop();
    }

    public boolean hasEmptyHand () {
        return this.hand.isEmpty();
    }
}
