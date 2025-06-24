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
 * @18.06.25
 */

import java.util.Scanner;

public class BankAccount
{
    private static int codeAux = 1;
    private int code;
    private double balance;
    private String username;
    private String password;
    
    public BankAccount(String username, String password){
        
        this.code = codeAux + 100;
        this.balance = 0;
        this.username = username;
        this.password = password;
        
        codeAux++;
        
    }
    
    /**
     * Returns the class variable "codeAux".
     * Intentionally, there is no setter methor for this variable, as
     * modifying it would interfere with the assignment of new account codes.
     * This getter can be used to check how many accounts have been created
     * using this class, even if they haven't been added to a database.
     */
    public int getcodeAux(){
        return codeAux;
    }
    public int getCode(){
        return this.code;
    }
    public void setCode(int code){
        this.code = code;
    }
    
    public double getBalance(){
        return this.balance;
    }
    public void setBalance(double balance){
        this.balance = balance;
    }
    
    public String getUsername(){
        return this.username;
    }
    public void setUsername(String username){
        this.username = username;
    }
    
    public String getPassword(){
        return this.password;
    }
    public void setPassword(String password){
        this.password = password;
    }
    
    public String toString(){
        return "\nUsername: " + this.username + "\n" +
               "Account code: " + this.code + "\n" +
               "Account balance: $ " + this.balance;
    }
    
    public static boolean createAccount(DataBase accountFiles, Scanner input){
        
        BankAccount temp;
        String username = "";
        String password;
        boolean invalid = true;
        
        while(invalid == true){
            
            input.nextLine();       // Cleans buffer.
            
            System.out.println("\fWelcome to account creation!" + "\n\n" +
                               "Plase, enter a username, or [9] to quit:");
            username = input.nextLine();
            
            if(username.equals("9"))
                return false;
            
            else if(accountFiles.checkUsername(username) == true)
                invalid = false;
                
                else{
                    System.out.println("\nUsername taken. Please try again.\n");    
                    invalid = true;
                }
        }
        
        System.out.println("\nPlease, enter a password:");
        password = input.nextLine();
        
        temp = new BankAccount(username, password);
        
        if(accountFiles.insertAccount(temp))
            return true;
        
        else
            return false;
    }
    
    public void withdraw(double amount){
        if(this.balance < amount){
            this.balance = this.balance;
            System.out.println("\nWithdrawal could not be completed due to insufficient funds.");
        } else{
            this.balance = this.balance - amount;
            System.out.println("\nWithdrawal was successful. $" + amount + " has been withdrawn from account code: " + this.code + ".");
        }
    }
    
    public void deposit(double amount){
        if(amount < 0){
            this.balance = this.balance;
            System.out.println("\nInvalid deposit ammount. Value must be greater than zero.\n");
        } else{
            this.balance = this.balance + amount;
            System.out.println("\nDeposit was successful. $" + amount + " has been deposited into account code: " + this.code + ".\n");
        }
    }
    
    public void transfer(BankAccount receivingAccount, double amount){
        if(amount < 0)
            System.out.println("\nInvalid transfer ammount. Value must be greater than zero.");
        else if(this.balance < amount)
            System.out.println("\nTransfer could not be completed due to insufficient funds.");
            else{
                this.balance = this.balance - amount;
                receivingAccount.setBalance((receivingAccount.getBalance() + amount));
                
                System.out.println("\nTransfer was successful. $" + amount + " has been transferred from account code: " + this.code + 
                                   ", into account code: " + receivingAccount.getCode() + ".");
            }
    } 
}
