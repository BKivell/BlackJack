
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
        this.updateHandValue();
    }

    // CLEARS PLAYERS hand
    public void clearCards() {
        this.getHand().clear();
        this.updateHandValue();
    }

    // RETURNS hand AS ArrayList<Card>;
    public ArrayList<Card> getHand() {
        return hand;
    }

    public void displayHand() {
        for (Card c : getHand()) {
            System.out.println(c.toString());
        }
    }

    // RETURNS handValue AS int
    public int getHandValue() {
        return handValue;
    }

    // SETS handValue TO PARAM
    public void updateHandValue() {
        int i = 0;
        for(Card c : hand){
            i += c.getValue();
        }
        this.handValue = i;
    }

    // Checks player hand for Ace RETURNS number of aces
    public int checkForAce() {
        int i = 0;
        for (Card c : this.getHand()) {
            if (c.getFace().equals("Ace")) {
                i++;
            }
        }
        return i;
    }

    // Returns the ith ace card
    public Card getAceCard(int i) {
        Card ace = null;
        int count = 1;
        for (Card c : this.hand) {
            if (c.getFace().equals("Ace")) {
                if(count == i)
                {
                    ace = c;
                }
                count++;
            }
        }
        return ace;
    }
    
//    // Changes value of the ith ace card in hand
//    public void setAceCardValue(int index, int value) {
//        int count = 1;
//        for (Card c : hand) {
//            if (c.getFace().equals("Ace")) {
//                if(count == index)
//                {
//                    c.setValue(value);
//                }
//                count++;
//            }
//        }
//    }

}
