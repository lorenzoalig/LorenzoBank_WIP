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
 * @24.06.25
 */

public class Interface
{
    Scanner input = new Scanner(System.in);
    // Database
    private DataBase database;
    
    //Username and password curently logged in
    private String currentUsername;
    private String currentPassword;
    
    
    public Interface(){
        
        this.database = null;
        this.currentUsername = null;
        this.currentPassword = null;
        
    }
    
    // Getter and setter for DataBase
    public DataBase getDataBase(){
        return this.database;
    }
    public void setDataBase(DataBase newData){
        this.database = newData;
    }
    
    // Getter and setter for currentUsername (username logged in)
    public String getCurrentUsername(){
        return this.currentUsername;
    }
    public void setCurrentUsername(String username){
        this.currentUsername = username;
    }
    
    // Getter and setter for currentPassword (password logged in)
    public String getCurrentPassword(){
        return this.currentPassword;
    }
    public void setCurrentPassword(String password){
        this.currentPassword = password;
    }
    
    // Boot the program
    public void boot(int size){
        
        this.database = new DataBase();
        
        this.database.initializeDatabase(5);
        
    }
    
    public void printWelcome(){
        System.out.println("Welcome to Lorenzo's Bank!\n");
    }
    
    public void printWelcomeUser(){
        System.out.println("Welcome back, " + this.currentUsername + "! This is your home page." + "\n\n" +
                           "\tYour account code is: [" + database.locateAccount(this.currentUsername).getCode() + "]\n");
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
                           "[8] Admin login [NOT FUNCTIONAL]." + "\n" +
                           "[9] Quit.\n");
    }
    
    public void printOptions2(){
        System.out.println("Please select the desired operation:");
            
        System.out.println("[1] Check balance." + "\n" +
                           "[2] Deposit." + "\n" +
                           "[3] Withdrawal." + "\n" +
                           "[4] Transfer." + "\n" +
                           "[5] Change password [NOT FUNCTIONAL]." + "\n" +
                           "[6] Change username [NOT FUNCTIONAL]." + "\n" +
                           "[7] Delete account." + "\n" +
                           "[8] Logoff." + "\n" +
                           "[9] Quit.\n");
    }
    
    /**
     * Starts 1st menu screen routine and RETURNS:
     * 
     *      [1] If program should proceed to 2nd menu;
     *      [0] If 1st menu should repeat;
     *      [-1] Quits program immediately.
     */
    
    public int executeWelcomeMenu(){
        
        int option = input.nextInt();
        
        switch(option){
            
            case 1: // CREATE ACCOUNT
                createAccount();
                return 0;
                
            case 2: // LOGIN TO ACCOUNT
                if(loginToAccount())
                    return 1;
                else
                    return 0;
                
            case 8: // ADMIN LOGIN [NOT FUNCTIONAL]
                return 0;
                
            case 9: // QUIT
                return -1;
                
            default:   
                System.out.println("\fError: invalid option.\n");
                return 0;
        }
    }
    
    /**
     * Starts 2nd menu screen routine and RETURNS:
     * 
     *      [1] If program should return to 1st menu;
     *      [0] If 2nd menu should repeat;
     *      [-1] Quits program.
     */
    
    public int executeUserMenu(){
        int option = input.nextInt();
        BankAccount account = this.database.locateAccount(this.currentUsername);
        
        switch(option){
            
            case 1: //CHECK BALANCE
                checkBalance(account);
                return 0;

            case 2: //DEPOSIT
                depositToAccount(account);
                return 0;
                
            case 3: //WITHDRAWAL
                withdrawFromAccount(account);
                return 0;
                
            case 4: //TRANSFER
                transferFromAccount(account);
                return 0;
            
            case 5: //DELETE ACCOUNT
                if(deleteThisAccount(account))
                    return 1;
                else
                    return 0;
            
            case 8: //LOGOFF
                logoffThisAccount();
                return 1;
            
            case 9: //QUIT
                return -1;
                
            default:
                System.out.println("\fError: invalid option.\n");
                return 0;
        }
    }
    
    public void createAccount(){
        
        BankAccount account;
        String username = "";
        String password;
        boolean invalid = true;
        
        System.out.println("\fWelcome to account creation!\n");
        
        while(invalid == true){
            input.nextLine();       // Cleans buffer.
            
            System.out.println("Plase, enter a username, or [8] to return:");
            username = input.nextLine();
            
            if(this.database.checkUsername(username))
                invalid = false; 
            else{
                System.out.println("\nUsername taken. Please try again.");    
                invalid = true;
            }
        }
        
        if(username.equals("8")){
            System.out.println("\fReturning to home screen...\n");
        } else{
            System.out.println("\nPlease, enter a password:");
            password = input.nextLine();
            
            account = new BankAccount(username, password);
            
            if(this.database.insertAccount(account)){
                System.out.println("\fAccount was created succesfully.\n");
            } else{
                System.out.println("\fError: account could not be created.\n");
            }
        }
    }
    
    public boolean loginToAccount(){
            input.nextLine();
            
            System.out.println("\fWelcome to account login!");
            
            String username = promptString("username");
            String password = promptString("password");
            
            if(this.database.validateLogin(username, password)){
                
                System.out.println("\fLogin successful!\n");
                
                this.currentUsername = username;
                this.currentPassword = password;
                
                return true;
                
            } else {
                
                System.out.println("\fIncorret username or password.\n");
                return false;
                
            }
    }
    
    public void checkBalance(BankAccount account){
        double balance;
        balance = account.getBalance();
        
        System.out.println("\f" + this.currentUsername + ", your account balance is:" + "\n\n" +
                           "\t" + "$ " + balance + "\n");
    }
    
    public void depositToAccount(BankAccount account){
        System.out.println("\fDeposit selected.");
        
        promptString("the deposit amount");
        double value = input.nextDouble();
        
        if(account.deposit(value))
            System.out.println("\fDeposit was successful." + "\n\n" +
                               "\t$" + value + " has been deposited into your account, " + account.getUsername() + ".\n"); 
        else
            System.out.println("\fInvalid deposit ammount. Value must be greater than zero.\n");      
    }
    
    public void withdrawFromAccount(BankAccount account){
        System.out.println("\fWithdrawal selected.");
        
        promptString("the withdrawal amount");       
        double value = input.nextDouble();
        
        if(value <= 0)
            System.out.println("\fInvalid deposit ammount. Value must be greater than zero.\n"); 
        else if(account.withdraw(value))
            System.out.println("\fWithdrawal was successful." + "\n\n" +
                               "\t$" + value + " has been withdrawn from your account, " + account.getUsername() + ".\n");
            else
                System.out.println("\fWithdrawal could not be completed due to insufficient funds.\n");
    }
    
    public void transferFromAccount(BankAccount account){
        System.out.println("\fTransfer selected.");
        
        promptString("the receiving account's code");
        int receiverCode = input.nextInt();
        double value;
        
        BankAccount receiverAccount = this.database.locateAccount(receiverCode);
        
        if(receiverAccount == null)
            System.out.println("\fError: Account does not exist.\n"); 
        else{
            promptString("the transfer amount");;
            value = input.nextDouble();
            
            if(value <= 0)
                System.out.println("\fInvalid transfer ammount. Value must be greater than zero.\n");
            else if(account.transfer(receiverAccount, value))
                System.out.println("\fTransfer was successful." + "\n\n" +
                                   "$" + value + " has been transferred from your account, " + account.getUsername() + ".\n\n" +
                                   "\tSender account: [" + account.getCode() + "]" + "\n" +
                                   "\tReceiver account: [" + receiverAccount.getCode() + "]\n");
                else
                    System.out.println("\fTransfer could not be completed due to insufficient funds.\n");
        }
    }
    
    public boolean deleteThisAccount(BankAccount account){
        System.out.println("\nAre you sure you want to delete this account?" + "\n" +
                                   "[1] Yes" + "\n" +
                                   "[2] No");
        int option = input.nextInt();
        int accountCode; 
        
        if(option == 1){
            accountCode = account.getCode();
            
            if(database.deleteAccount(accountCode)){
                System.out.println("\fAccount was deleted successfully. Redirecting to home screen...\n");
                return true;
            } else{
                System.out.println("\fError: Could not delete account.\n");
                return false;
            }
        } else {
            clearScreen();
            return false;
        }
    }
    
    public void logoffThisAccount(){
        this.currentUsername = null;
        this.currentPassword = null;
        
        System.out.println("\fLoggin off. Redirecting to home screen...\n");
    }
    
    public String promptString(String name){
        System.out.println("\nPlease enter " + name + ":");
        
        return this.input.nextLine();
    }
    
    public void clearScreen(){
        System.out.print("\f");
    }
}
