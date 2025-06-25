/**
 * This class creates instances of bank accounts with basic attributes and basic operations,such as deposit,
 * withdrawal, and transfers between accounts.
 * 
 * It includes the following attributes:
 * 
 *  - Username (must be available)
 *  - Password
 *  - Account code
 *  - Balance
 * 
 * During account creation, the class also checks if the username is available (in the database), automatically assigns
 * an account code, and adds the account to the database.
 * 
 * Disclaimer: This is not the final version. Its purpose is for testing only. Further documentation to be added.
 * 
 * @lorenzoalig
 * @24.06.25
 */

public class BankAccount
{
    private static int tracker = 1;
    private int code;
    private double balance;
    private String username;
    private String password;
    
    // Constructor
    public BankAccount(String username, String password){
        
        this.code = tracker + 100;
        this.balance = 0;
        this.username = username;
        this.password = password;
        
        tracker++;
        
    }
    
    /**
     * Returns the class variable "tracker".
     * Intentionally, there is no setter methor for this variable, as
     * modifying it would interfere with the assignment of new account codes.
     * This getter can be used to check how many accounts have been created using this class, even if they haven't been added to a database.
     */
    public int getTracker(){
        return tracker;
    }
    
    // Getter and setter for account code
    public int getCode(){
        return this.code;
    }
    public void setCode(int code){
        this.code = code;
    }
    
    // Getter and setter for account balance
    public double getBalance(){
        return this.balance;
    }
    public void setBalance(double balance){
        this.balance = balance;
    }
    
    // Getter and setter for account username
    public String getUsername(){
        return this.username;
    }
    public void setUsername(String username){
        this.username = username;
    }
    
    // Getter and setter for account password
    public String getPassword(){
        return this.password;
    }
    public void setPassword(String password){
        this.password = password;
    }
    
    // toString method
    public String toString(){
        return "Account{username: " + this.username + ", code: " + this.code + ", balance: " + this.balance + "}";
    }
    
    public boolean withdraw(double amount){
        if(this.balance < amount){
            return false;
        } else{
            this.balance = this.balance - amount;
            return true;
        }
    }
    
    public boolean deposit(double amount){
        if(amount <= 0){
            return false;
        } else{
            this.balance = this.balance + amount;
            return true;
        }
    }
    
    public boolean transfer(BankAccount receivingAccount, double amount){
        if(amount <= 0)
            return false;
        else if(this.balance < amount)
            return false;
            else{
                this.balance = this.balance - amount;
                receivingAccount.setBalance((receivingAccount.getBalance() + amount));
                
                return true;
            }
    } 
}





