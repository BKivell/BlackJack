
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Brad Kivell
 */
public class BlackJack {

    // INSTANCE VARIABLES
    private final Scanner scan = new Scanner(System.in);
    public String stringInput;
    public int intInput;
    public Dealer dealer;
    DataTracker dataTracker;
    Player player;
    boolean playing;

    // CONSTRUCTOR
    public BlackJack() {
        System.out.println("Welcome to BlackJack!\nPlease enter your username: ");
        stringInput = scan.nextLine();
        dataTracker = new DataTracker(stringInput);
        dataTracker.loadSequence();
        dealer = new Dealer();
        player = new Player(dataTracker.getUserName(), dataTracker.getBalance());
    }

    // Deal card to person
    public void dealToPerson(Person p, int num) {
        for (int i = 0; i < num; i++) {
            Card c = dealer.dealCard();
            p.giveCard(c);
            System.out.println("Dealt: " + c.toString());
        }
    }

    public void startGame() {
        // Clears current cards held
        player.clearCards();
        dealer.clearCards();
        dealer.shuffleDeck();
        gameLoop();
    }

    public void endGame() {
        // Return cards to the dealers deck
        dealer.returnCards(player);
        dealer.returnCards(dealer);
        // Save Data
        try {
            dataTracker.saveSequence();
        } catch (FileNotFoundException ex) {
            System.out.println("File not found: Data has been lost for this session");
        }
    }

    // Shows user options after being delt card
    public void showGameOptions() {
        boolean showingOptions = true;
        System.out.println("\nPlayers hand value: " + player.getHandValue());
        System.out.println("Dealers hand value: " + dealer.getHandValue() + " + Unknown Card");
        while (showingOptions) {
            
            
            showingOptions = false;
        }
        // Deal another card - will end turn
        // Sit at current cards?
        // Change ace value if player holds ace 
        // If player holds ace or dealer, give option to make value 1 or 11 but actually just add and remove 10 off their handValue
    }

    // Main Game Loop
    public void gameLoop() //-------------------------------------------------THIS IS BASICALLY ALL THAT NEEDS TO BE EDITIED
    {
        System.out.println("New Game Starting\nEnter an amount to bet (Must be a whole number)");
        boolean setUp = true;
        while (setUp) {// Get bet amount and set players money in game

            try {
                intInput = scan.nextInt();
                player.setMoneyInGame(intInput);
                setUp = false;
                playing = true;
            } catch (InputMismatchException e) {
                scan.next();
                System.out.println("Invalid Input: Please enter a valid number");
            }
        }

        while (playing) {
            // Deal card to player & check hand value
            System.out.println("\nDealing Cards to Player:");
            dealToPerson(player, 2);
            System.out.println("\nDealing Card to Dealer:");
            dealToPerson(dealer, 1);
            // Show options (Deal another card, Sit at current cards, Change ace value)
            showGameOptions();
            playing = false;
        }
        endGame();
        // Check for replay, if replay is wanted, call startGame();
    }
}

// Deal 2 cards to player & dealer, show 1 dealer card
// If cards first 2 cards dealt = 21, you win 1.5x your money back 
// Player can take more cards or stay
// Then dealer shows cards after player has made decisions
// Dealer must hit until reaches a value of 17
