/**
 * Console based BlackJack game for PDC Assignment 1
 * 
 * @author Brad Kivell - 20115449
 * @author Shelvin Kumar - 17985924
 */
public class Main {

    public static void main(String[] args) {
        String Rule = """
                                    RULES:
        OBJECT OF THE GAME: Each player attempts to beat the dealer by getting 21.
        CARD VALUES:Each number card is the value of the card, Ace Cards value can 
        be 1 or 11, Face cards value equal 10.
        BETTING:
        Start of the game the player places a bet, betting can range from $1 to $100 depanding on buget.
        THE DEALER:
        Once the player has placed their bet, the dealer then deal 2 card to the player facing up and
        1 card facing up and one card hidden from player to themself. The Dealer then asks the player if they
        Want to HIT or STAND, If Player picks HIT then dealer gives then one 1 and asks again until player either
        says STAND or if player gets BUST(Hits till past 21).
        THE GAME:
        The point of the BlackJack is the get your card value as close to 21 with out getting BUST to win and get the bet value doubled.        
        """;

        //Info for brad.                     
        /*
        
        */
        System.out.println(Rule);
        BlackJack game = new BlackJack();
        game.startGame();
    }
}

