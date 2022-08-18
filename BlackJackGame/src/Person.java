
import java.util.ArrayList;

/**
 *
 * @author Brad Kivell
 */
public class Person {

    private ArrayList<Card> hand;
    private int handValue;

    // DEFAULT CONSTRUCTOR
    public Person() {
        this.hand = new ArrayList<>();
        this.handValue = 0;
    }

    // ADDS card PARAM  TO PLAYER hand
    public void giveCard(Card card) {
        this.getHand().add(card);
    }

    // CLEARS PLAYERS hand
    public void clearCards() {
        this.getHand().clear();
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
    public void setHandValue(int score) {
        this.handValue = score;
    }
}
