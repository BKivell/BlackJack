
import java.util.Scanner;

/**
 *
 * @author Brad Kivell
 */
public class BlackJack {

    // INSTANCE VARIABLES
    private final Scanner scan = new Scanner(System.in);
    public String input;

    // CONSTRUCTOR
    public BlackJack() {
        System.out.println("Welcome to BlackJack\nPlease enter your username: ");
        input = scan.nextLine();
        BlackJack game = new BlackJack();
        DataTracker dataTracker = new DataTracker(game.input);
        dataTracker.loadSequence();
        Player player = new Player(dataTracker.getUserName(), dataTracker.getBalance());
        Dealer dealer = new Dealer();
    }
    
    public void dealToPlayer()
    {
        
    }
    
    public void dealToDealer()
    {
        
    }
    
    public void calculateHandValue()
    {
        
    }
    
    public void startGame()
    {
        
    }
    

}
