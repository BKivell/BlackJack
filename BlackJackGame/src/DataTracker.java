
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Brad Kivell (20115449)
 */
public class DataTracker {

    //------------------------------------[VARIABLES]------------------------------------
    private String filePath = "./resources/DataTracker.txt";
    private String userName = "";
    private int balance = 0;

    HashMap<String, Integer> scoreMap = new HashMap<>();
    boolean pastUser = false;

    FileReader reader;
    BufferedReader buffReader;

    //------------------------------------[CONSTRUCTOR]------------------------------------
    public DataTracker(String userName) {
        try {
            this.reader = new FileReader(filePath);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DataTracker.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.buffReader = new BufferedReader(reader);
        this.userName = userName;
    }

    //------------------------------------[LOAD DATA FROM FILE]------------------------------------
    public void loadSequence() {
        // Load from file
        String line;
        String name;
        Integer scoreRead;
        try {
            while ((line = buffReader.readLine()) != null) {
                // Split line into score & name
                String[] splitStr = line.split("\\s+");
                if (splitStr.length >= 1) {
                    name = splitStr[0];
                    scoreRead = Integer.parseInt(splitStr[1]);
                    if (getUserName().trim().equalsIgnoreCase(name.trim())) {
                        setBalance((int) scoreRead);
                        pastUser = true;
                    }
                    scoreMap.put(name, scoreRead);
                }
            }
        } catch (IOException ex) {
            System.out.println("Error: IOException while loading file");
        }
        System.out.println("Welcome! Your current balance is: " + getBalance());
    }

    //------------------------------------[SAVE TO FILE]------------------------------------
    public void saveSequence() throws FileNotFoundException {
        try (
                 PrintWriter writer = new PrintWriter(filePath) // Write Updated Values
                ) {
            if (!pastUser) {
                writer.print(getUserName() + " " + getBalance() + "\n");
            } else {
                scoreMap.put(getUserName(), getBalance());
            }
            for (Map.Entry keyValuePair : scoreMap.entrySet()) {
                writer.print(keyValuePair.getKey() + " " + keyValuePair.getValue() + "\n");
            }
            // Close Writer
        }
    }

    // SCORE UPDATE
    public void scoreUpdate(int change) {
        this.setBalance(this.getBalance() + change);
        System.out.println("Current Balance: " + this.getBalance());
    }

    
    //------------------------------------[GET & SET]------------------------------------
    // RETURNS userName AS String
    public String getUserName() {
        return userName;
    }

    // SETS userName TO PARAM
    public void setUserName(String userName) {
        this.userName = userName;
    }

    // RETURNS balance AS int
    public int getBalance() {
        return balance;
    }

    // SETS balance TO PARAM
    public void setBalance(int balance) {
        this.balance = balance;
    }
}
