
import java.util.ArrayList;

/**
 *
 * @author Brad Kivell
 */
public class Person {

    private final ArrayList<Card> hand;
    private int handValue;

    // DEFAULT CONSTRUCTOR
    public Person() {
        this.hand = new ArrayList<>();
        this.handValue = 0;
    }

    // ADDS card PARAM  TO PLAYER hand
    public void giveCard(Card card) {
        this.getHand().add(card);
        this.setHandValue(this.getHandValue() + card.getValue());
    }

    // CLEARS PLAYERS hand
    public void clearCards() {
        this.getHand().clear();
        this.setHandValue(0);
    }

    // RETURNS hand AS ArrayList<Card>;
    public ArrayList<Card> getHand() {
        return hand;
    }

    // RETURNS handValue AS int
    public int getHandValue() {
        return handValue;
    }

    // SETS handValue TO PARAM
    public void setHandValue(int i) {
        this.handValue = i;
    }

    // Checks player hand for Ace RETURNS Boolean
    public boolean checkForAce() {
        for (Card c : this.getHand()) {
            if (c.getFace().equals("Ace")) {
                return true;
            }
        }
        return false;
    }

}
