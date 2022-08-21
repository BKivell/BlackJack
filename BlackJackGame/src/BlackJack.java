
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

    // Called when no more moves are to be made
    public void endGame() {
        System.out.println("\nGame Over");
        System.out.println("Players Hand Value: " + player.getHandValue());
        System.out.println("Dealers Hand Value: " + dealer.getHandValue());

        //Check winner & display results------------------------------------------------------------------------------------------**
        //Also update balances
        if (player.getHandValue() <= 21) {
            if (player.getHandValue() == 21) {
                System.out.println("BLACKJACK - Player Wins");
                player.increaseBalance();
            } else if (player.getHandValue() > dealer.getHandValue()) {
                System.out.println("Player Wins");
                player.increaseBalance();
            } else if (player.getHandValue() < dealer.getHandValue() && dealer.getHandValue() <= 21) {
                System.out.println("Dealer Wins");
                player.decreaseBalance();
            } else if (player.getHandValue() < dealer.getHandValue() && dealer.getHandValue() > 21) {
                System.out.println("Player Wins - Dealer Bust");
                player.increaseBalance();
            } else {
                System.out.println("No Winner");
            }
        } else {
            System.out.println("BUST - Dealer Wins");
            player.decreaseBalance();
        }

        // Return cards to the dealers deck
        dealer.returnCards(player);
        dealer.returnCards(dealer);
        // Save Data
        dataTracker.setBalance(player.getBalance());
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

    //-----------------------------------------[SHOW GAME OPTIONS]-----------------------------------------
    public void showGameOptions() {
        // Variables
        boolean showingOptions = true;
        int optionNum;
        // Print hand values
        System.out.println("\nPlayers hand value: " + player.getHandValue());
        System.out.println("Dealers hand value: " + dealer.getHandValue() + " + Unknown Card");
        // Loop while in users turn
        System.out.println("\n1. Hit - Deal more cards");
        System.out.println("2. Stand - End turn with current cards ");
        // Only display option if player holds ace
        if (player.checkForAce()) {
            System.out.println("3. Change value of Ace Cards");
        }
        System.out.println("\nPlease enter your option number (E.g '1'): ");
        while (showingOptions) {
            optionNum = getIntInput();
            switch (optionNum) {
                case 1:
                    dealToPerson(player, 1);
                    // Check if player wants to hit again------------------------------------------------------------------------------------**
                    // Keep hitting until player wants to end turn or goes bust
                    showingOptions = false;
                    break;
                case 2:
                    showingOptions = false;
                    break;
                case 3:
                    if (player.checkForAce()) {
                        // Change ace value here------------------------------------------------------------------------------------**
                        // Check for multiple ace cards
                        showingOptions = false;
                    } else {
                        System.out.println("Invalid Option: No aces are in hand");
                    }
                    break;
                default:
                    System.out.println("Invalid Option");
            }
        }
    }

    //-----------------------------------------[MAIN LOOP]-----------------------------------------
    public void gameLoop() {
        // Start Game Loop
        System.out.println("New Game Starting");
        System.out.println("Current Balance: " + player.getBalance());
        System.out.println("Enter an amount to bet (Must be a whole number)");
        boolean setUp = true;
        while (setUp) {// Get bet amount and set players money in game
            if (player.setMoneyInGame(getIntInput())) {
                setUp = false;
            }
        }

        // Deal card to player & check hand value
        System.out.println("\nPlayers hand:");
        dealToPerson(player, 2);
        System.out.println("\nDealers hand:");
        dealToPerson(dealer, 1);

        // Show options (Deal another card, Sit at current cards, Change ace value)
        showGameOptions();

        // Deal final cards to dealer until they have at least 17
        System.out.println("\nDealers Last Cards:");
        while (dealer.getHandValue() < 17) {
            dealToPerson(dealer, 1);
        }

        //if player picks "stand", show dealer card. if dealer card is 21 then print "BUST" and remove bet amount from player. 
        //if dealer card isnt 21 and is less then player add 1 more card to dealer if  dealer card value still less then player then player wins.
        //if dealer card more then 21(e.g 25) then player wins and add 2 X bet amount.
        //if dealer card less then 21 but more then player card value then dealer wins and print "Dealer wins" and remove bet amount from player.
        //if Player chooses hit then add one card to player and calculate the value of the card and print the card value if the card value is 21 print player wins and return 2 X bet amount.
        //if player card less then 21(e.g 17) ask player if they want to "HIT or STAND"
        //if player card value more then 21, then print "BUST", take the bet amount.
        //NOTE DEALER option only happens if player picks "STAND".
        endGame();

        // Check for replay, if replay is wanted, call startGame();
        System.out.println("Type 1 to restart game or 2 to exit");
        if (getIntInput() == 1) {
            startGame();
        }
    }
}
