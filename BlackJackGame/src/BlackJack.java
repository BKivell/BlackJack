
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Brad Kivell
 * @author Shelvin Kumar
 */
public class BlackJack {

    // INSTANCE VARIABLES
    private final Scanner scan = new Scanner(System.in);
    public String stringInput;
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

    // Returns input as integer w/ exception handling
    public int getIntInput() {
        int i = 0;
        boolean askingInput = true;
        while (askingInput) {
            try {
                i = scan.nextInt();
                askingInput = false;
            } catch (InputMismatchException e) {
                scan.next();
                System.out.println("Invalid Input: Please enter a valid number");
            }
        }
        return i;
    }

    // Shows user options after being delt card
    public void showGameOptions() {
        // Variables
        boolean showingOptions = true;
        int optionNum;
        // Print hand values
        System.out.println("\nPlayers hand value: " + player.getHandValue());
        System.out.println("Dealers hand value: " + dealer.getHandValue() + " + Unknown Card");
        // Loop while in users turn
        System.out.println(""); // DISPLAY OPTIONS
        System.out.println("\nPlease enter your option number: ");
        while (showingOptions) {
            optionNum = getIntInput();
            switch (optionNum) {
                case 1 -> System.out.println("");
                // Do option 1
                case 2 -> System.out.println("");
                // Do option 2
                case 3 -> System.out.println("");
                // Do option 3
                default -> System.out.println("Invalid Option");
            }

            // Exit loop
            showingOptions = false;
        }
        // Deal another card - will end turn
        // Sit at current cards?
        // Change ace value if player holds ace 
        // If player holds ace or dealer, give option to make value 1 or 11 but actually just add and remove 10 off their handValue
        // Follow rules
    }

    // Main Game Loop
    public void gameLoop() {
        System.out.println("New Game Starting\nEnter an amount to bet (Must be a whole number)");
        boolean setUp = true;
        while (setUp) {// Get bet amount and set players money in game
            player.setMoneyInGame(getIntInput());
            setUp = false;
            playing = true;
        }

        while (playing) {
            // Deal card to player & check hand value
            System.out.println("\nDealing Cards to Player:");
            dealToPerson(player, 2);
            System.out.println("\nDealing Card to Dealer:");
            dealToPerson(dealer, 1);
            // Show options (Deal another card, Sit at current cards, Change ace value)
            //if the vlaue is less them 21 ask player if they want to "hit" or "stand".
            //if player picks "stand", show dealer card. if dealer card is 21 then print "BUST" and remove bet amount from player. 
            //if dealer card isnt 21 and is less then player add 1 more card to dealer if  dealer card value still less then player then player wins.
            //if dealer card more then 21(e.g 25) then player wins and add 2 X bet amount.
            //if dealer card less then 21 but more then player card value then dealer wins and print "Dealer wins" and remove bet amount from player.
            //if Player chooses hit then add one card to player and calculate the value of the card and print the card value if the card value is 21 print player wins and return 2 X bet amount.
            //if player card less then 21(e.g 17) ask player if they want to "HIT or STAND"
            //if player card value more then 21, then print "BUST", take the bet amount.
              
            //NOTE DEALER option only happens if player picks "STAND".
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
