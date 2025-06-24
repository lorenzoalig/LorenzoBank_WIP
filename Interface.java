import java.util.Scanner;

/**
 * This class defines an interactive user interface that runs in the terminal. The user has the option to create an account or log in
 * to an existing one.
 * 
 *      Note: Account creation employs validations to check if username is already taken or available. 
 * 
 * After logging in, the user can access all account information and execute multiple procedures, all of which are detailed in
 * the BankAccount and DataBase classes.
 * 
 * Disclaimer: This is not the final version. Its purpose is for testing only. Further documentation to be added.
 * 
 * @lorenzoalig
 * @18.06.25
 */

public class Interface
{
    Scanner input = new Scanner(System.in);
    private DataBase data;
    private String currentUsername;
    private String currentPassword;
    
    
    public Interface(){
        
        this.data = null;
        
    }
    
    public DataBase getDataBase(){
        return this.data;
    }
    public void setDataBase(DataBase newData){
        this.data = newData;
    }
    
    public String getCurrentUsername(){
        return this.currentUsername;
    }
    public void setCurrentUsername(String username){
        this.currentUsername = username;
    }
    
    public String getCurrentPassword(){
        return this.currentPassword;
    }
    public void setCurrentPassword(String password){
        this.currentPassword = password;
    }
    
    public void boot(int size){
        
        this.data = new DataBase();
        
        this.data.initializeVector(5);
        
    }
    
    public void printWelcome(){
        System.out.println("Welcome to Lorenzo's Bank!\n");
    }
    
    public void printWelcomeUser(){
        System.out.println("Welcome back " + this.currentUsername + "! This is your home page." + "\n\n" +
                           "Your account code is: [" + data.locateAccount(this.currentUsername).getCode() + "]\n");
    }
    
    public void printGoodbye(){
        System.out.println("\nApplication shutting off...");
        System.out.println("\n\nGood Bye!!");
    }
    
    public void printLoadingScreen(){
        
    }
    
    public void printOptions1(){
        System.out.println("Please select the desired operation:");
            
        System.out.println("[1] Create an Account." + "\n" +
                           "[2] Log in to my Account." + "\n" +
                           "[9] Quit.\n");
    }
    
    public void printOptions2(){
        System.out.println("Please select the desired operation:");
            
        System.out.println("[1] Check balance." + "\n" +
                           "[2] Deposit." + "\n" +
                           "[3] Withdrawal." + "\n" +
                           "[4] Transfer." + "\n" +
                           "[5] Delete account." + "\n" +
                           "[8] Logoff." + "\n" +
                           "[9] Quit.\n");
    }
    
    /**
     * Starts 1st menu screen routine and RETURNS:
     * 
     *      [1] If 1st menu should repeat;
     *      [2] If program should proceed to 2nd menu;
     *      [3] Quits program.
     */
    
    public int executeMenu1(){
        
        int option = input.nextInt();
        
        switch(option){
            
            case 1: //CREATE ACCOUNT
                
                if(BankAccount.createAccount(data, input))
                    System.out.println("\nAccount was created succesfully.\n");
                else
                    System.out.println("\nError: account could not be created.\n");
                
                return 1;
                
            case 2: //LOGIN TO ACCOUNT
                
                System.out.println("\fWelcome to account login!");
                
                input.nextLine();
                String username = promptUsername();
                String password = promptPassword();
                
                if(this.data.loginToAccount(username, password)){
                    
                    System.out.println("\nLogin successful.\n");
                    
                    this.currentUsername = username;
                    this.currentPassword = password;
                    
                    return 2;
                    
                } else {
                    
                    System.out.println("\nIncorret username or password.\n");
                    return 1;
                    
                }
                
            case 9:
                
                printGoodbye();
                return 3;
                
            default:
                    
                    System.out.println("\nInvalid option. Please try again.");
                    return 1;
        }
    }
    
    /**
     * Starts 2nd menu screen routine and RETURNS:
     * 
     *      [1] If 2nd menu should repeat;
     *      [2] If program should return to 1st menu;
     *      [4] Quits program.
     */
    
    public int executeMenu2(){
        
        double value;
        int option = input.nextInt();
        
        BankAccount account = data.locateAccount(this.currentUsername);
        
        switch(option){
            
            case 1:     //CHECK BALANCE
                
                double balance;
                balance = account.getBalance();
                
                System.out.println("\nAccount balance: $ " + balance + "\n");
                return 1;

            case 2:     //DEPOSIT
                
                System.out.println("\nPlease enter the deposit amount:");
                
                value = input.nextDouble();
                account.deposit(value);
                
                return 1;
                
            case 3:     //WITHDRAWAL
                
                System.out.println("\nPlease enter the withdrawal amount:");
                
                value = input.nextDouble();
                account.withdraw(value);
                
                return 1;
                
            case 4:     //TRANSFER
                
                System.out.println("\nPlease inform the receiving account's code:");
                
                int receiverCode = input.nextInt();
                BankAccount receiverAccount = data.locateAccount(receiverCode);
                
                if(receiverAccount != null){
                    
                    System.out.println("\nPlease enter the transfer amount:");
                    
                    value = input.nextDouble();
                    
                    account.transfer(receiverAccount, value);
                    
                }
                
                return 1;
            
            case 5:     //DELETE ACCOUNT
                
                System.out.println("\nAre you sure you want to delete this account?" + "\n" +
                                   "[1] Yes" + "\n" +
                                   "[2] No");
                
                option = input.nextInt();
                int accountCode;
                
                if(option == 1){
                    
                    accountCode = account.getCode();
                    
                    if(data.deleteAccount(accountCode)){
                        System.out.println("\nAccount was deleted successfully.\n");
                        return 2;
                    } else{
                        return 1;
                    }
                } else {
                    clearScreen();
                    return 1;
                }
            
            case 8:     //LOGOFF
                
                clearScreen();
                return 2;
            
            case 9:     //QUIT
                
                printGoodbye();
                return 3;
                
            default:
                
                System.out.println("Invalid option. Please Try again.");
                return 1;
        }
    }
    
    public String promptUsername(){
        
        System.out.println("\nPlease enter username:");
        
        return this.input.nextLine();
    }
    
    public String promptPassword(){
        
        System.out.println("\nPlease enter password:");
        
        return this.input.nextLine();
    }
    
    public void clearScreen(){
        System.out.print("\f");
    }
}
