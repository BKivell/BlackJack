
/**
 *
 * @author Brad Kivell
 */
public final class Player extends Person {

    private int balance;
    private String name;
    private int moneyInGame;

    public Player(String name, int balance) {
        super();
        this.setBalance(balance);
        this.setName(name);
        this.setMoneyInGame(0);
    }

    // RETURN balance AS int
    public int getBalance() {
        return balance;
    }

    // SETS balance TO PARAM
    public void setBalance(int balance) {
        this.balance = balance;
    }

    // RETURNS name AS String
    public String getName() {
        return name;
    }

    // SETS name TO PARAM
    public void setName(String name) {
        this.name = name;
    }

    // RETURNS moneyInGame AS String
    public int getMoneyInGame() {
        return moneyInGame;
    }

    // SET moneyInGame TO PARAM
    public void setMoneyInGame(int moneyInGame) {
        this.moneyInGame = moneyInGame;
    }

}
