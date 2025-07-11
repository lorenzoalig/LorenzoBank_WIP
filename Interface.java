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
 * @10.07.25
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
        this.database.initializeDatabase(size);

    }
    
    public void printWelcome(){

        System.out.println("Welcome to Lorenzo's Bank!\n");

    }
    
    public void printWelcomeUser(){

        System.out.println("Welcome back, " + this.currentUsername + "! This is your home page." + "\n\n" +
                           "\tYour account code is: [" + database.locateAccount(this.currentUsername).getCode() + "]\n");

    }

    public void printWelcomeAdmin(){

        System.out.println("Welcome back, Administrator!" + "\n\n" +
                           "\tCurrently logged in to: [admin]\n");

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
                           "[8] Admin login." + "\n" +
                           "[9] Quit.\n");     

    }
    
    public void printOptions2(){

        System.out.println("Please select the desired operation:");
            
        System.out.println("[1] Check balance." + "\n" +
                           "[2] Deposit." + "\n" +
                           "[3] Withdrawal." + "\n" +
                           "[4] Transfer." + "\n" +
                           "[5] Change password." + "\n" +
                           "[6] Change username." + "\n" +
                           "[7] Delete account." + "\n" +
                           "[8] Logoff." + "\n" +
                           "[9] Quit.\n");   

    }

    public void printOptionsAdmin(){

        System.out.println("Please select the desired operation:");
            
        System.out.println("[1] Show account." + "\n" +
                           "[2] Show database." + "\n" +
                           "[3] Delete account." + "\n" +
                           "[4] Deposit to an account [ILLEGAL]." + "\n" +
                           "[5] Transfer between accounts [ILLEGAL]." + "\n" +
                           "[6] Change account username." + "\n" +
                           "[7] Change account password." + "\n" +
                           "[8] Logoff." + "\n" +
                           "[9] Quit.\n");   

    }
    
    /**
     * Starts 1st menu screen routine and RETURNS:
     * 
     *      [1] If program should proceed to 2nd menu;
     *      [0] If 1st menu should repeat;
     *      [-1] Quits program immediately.
     *      [2] If program should access admin menu;
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
                
            case 8: // ADMIN LOGIN

                if(loginToAdmin())

                    return 2;  // Proceed to admin menu.
                else
                    return 0;   // Repeats current menu.
                
            case 9: // QUIT

                return -1;
                
            default: 

                clearScreen();
                System.out.println("Error: invalid option.\n");
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
            
            case 1: // CHECK BALANCE

                checkBalance(account);
                return 0;

            case 2: // DEPOSIT

                depositToAccount(account);
                return 0;
                
            case 3: // WITHDRAWAL

                withdrawFromAccount(account);
                return 0;
                
            case 4: // TRANSFER

                transferFromAccount(account);
                return 0;
            
            case 5: // CHANGE PASSWORD

                if(changePassword(account))
                    return 1;
                else
                    return 0;
                
            case 6: // CHANGE USERNAME

                if(changeUsername(account))
                    return 1;
                else
                    return 0;
                
            case 7: // DELETE ACCOUNT

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

                clearScreen();
                System.out.println("Error: invalid option.\n");
                return 0;
        }
    }

    /**
     * Starts admin menu screen routine and RETURNS:
     * 
     *      [1] If program should return to 1st menu;
     *      [0] If 2nd menu should repeat;
     *      [-1] Quits program.
     */
    public int executeAdminMenu(){

        int option = input.nextInt();
        
        switch(option){
            
            case 1: // ADMIN - SHOW ACCOUNT
                
                adminShowAccount();
                return 0;

            case 2: // ADMIN - SHOW DATABASE
                
                adminShowDatabase();
                return 0;
                
            case 3: // ADMIN - DELETE ACCOUNT
                
                adminDeleteAccount();
                return 0;
                
            case 4: // ADMIN - DEPOSIT
                
                adminDeposit();
                return 0;
            
            case 5: // ADMIN - TRANSFER
                
                adminTransfer();
                return 0;
                
            case 6: // CHANGE USERNAME
                ;
                return 0;
                
            case 7: // CHANGE PASSWORD
                ;
                return 0;
            
            case 8: //LOGOFF
                logoffThisAccount();
                return 1;
            
            case 9: //QUIT
                return -1;
                
            default:

                clearScreen();
                System.out.println("Error: invalid option.\n");
                return 0;
        }
    }
    
    public void createAccount(){
        
        BankAccount account;
        String username = "";
        String password;
        boolean invalid = true;
        
        clearScreen();
        System.out.println("Welcome to account creation!\n");
        
        while(invalid == true){
            input.nextLine();       // Cleans buffer.
            
            System.out.println("Please, enter a username, or [8] to return:");
            username = input.nextLine();
            
            if(this.database.checkUsername(username))
                invalid = false; 
            else{
                System.out.println("\nUsername taken. Please try again.");    
                invalid = true;
            }
        }
        
        if(username.equals("8")){

            clearScreen();
            System.out.println("Returning to home screen...\n");

        } else{

            System.out.println("\nPlease, enter a password:");
            password = input.nextLine();
            
            account = new BankAccount(username, password);
            
            if(this.database.insertAccount(account)){

                clearScreen();
                System.out.println("Account was created succesfully.\n");

            } else{

                clearScreen();
                System.out.println("Error: account could not be created due to insufficient storage.\n");

            }
        }
    }
    
    public boolean loginToAccount(){

        input.nextLine();
        
        clearScreen();
        System.out.println("Welcome to account login!");
        
        String username = promptString("username");
        String password = promptString("password");
        
        if(this.database.validateLogin(username, password)){
            
            clearScreen();
            System.out.println("Login successful!\n");
            
            this.currentUsername = username;
            this.currentPassword = password;
            
            return true;
            
        } else {
            
            clearScreen();
            System.out.println("Error: incorret username or password.\n");
            return false;
            
        }
    }
    
    public boolean loginToAdmin(){
        
        input.nextLine();

        clearScreen();
        System.out.println("Welcome to admin login!" + "\n" + "\n" +
                           "Note: Admin username and password are both 'admin' ;)");

        String username = promptString("admin username");
        String password = promptString("admin password");

        if(username.equals("admin") && password.equals("admin")){

            clearScreen();
            System.out.println("Admin login succesful!\n");
            return true;  // Admin login successful

        } else{

            clearScreen();
            System.out.println("Error: incorrect admin username or password.\n");
            return false;  // Admin login failed
        }
    }

    public void checkBalance(BankAccount account){

        double balance;
        balance = account.getBalance();
        
        clearScreen();
        System.out.println(this.currentUsername + ", your account balance is:" + "\n\n" +
                           "\t" + "$ " + balance + "\n");

    }
    
    public void depositToAccount(BankAccount account){

        clearScreen();
        System.out.println("Deposit selected.");
        
        promptString("the deposit amount");
        double value = input.nextDouble();
        
        if(account.deposit(value)){
            
            clearScreen();
            System.out.println("Deposit was successful." + "\n\n" +
                               "\t$" + value + " has been deposited into your account, " + account.getUsername() + ".\n");
        
        } else{
            
            clearScreen();
            System.out.println("Error: invalid deposit ammount. Value must be greater than zero.\n");

        }
    }
    
    public void withdrawFromAccount(BankAccount account){

        clearScreen();
        System.out.println("Withdrawal selected.");
        
        promptString("the withdrawal amount");       
        double value = input.nextDouble();
        
        if(value <= 0){

            clearScreen();
            System.out.println("Error: invalid deposit ammount. Value must be greater than zero.\n"); 

        } else if(account.withdraw(value)){
            
            clearScreen();
            System.out.println("Withdrawal was successful." + "\n\n" +
                               "\t$" + value + " has been withdrawn from your account, " + account.getUsername() + ".\n");

            } else{

                clearScreen();
                System.out.println("Error: withdrawal could not be completed due to insufficient funds.\n");

            }

    }
    
    public void transferFromAccount(BankAccount account){

        double value;

        clearScreen();
        System.out.println("Transfer selected.");
        
        int receiverCode = promptInt("the receiving account's code");

        BankAccount receiverAccount = this.database.locateAccount(receiverCode);
        
        if(receiverAccount == null){

            clearScreen();
            System.out.println("Error: Account does not exist.\n");

        } else{

            promptString("the transfer amount");;
            value = input.nextDouble();
            
            if(value <= 0){

                clearScreen();
                System.out.println("Invalid transfer ammount. Value must be greater than zero.\n");

            } else if(account.transfer(receiverAccount, value)){

                clearScreen();
                System.out.println("Transfer was successful." + "\n\n" +
                                   "$" + value + " has been transferred from your account, " + account.getUsername() + ".\n\n" +
                                   "\tSender account: [" + account.getCode() + "]" + "\n" +
                                   "\tReceiver account: [" + receiverAccount.getCode() + "]\n");
                } else{

                    clearScreen();
                    System.out.println("Error: transfer could not be completed due to insufficient funds.\n");

                }
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

                clearScreen();
                System.out.println("Account was deleted successfully. Redirecting to home screen...\n");
                
                this.database.rearrangeFiles();
                
                return true;

            } else{

                clearScreen();
                System.out.println("Error: could not delete account.\n");
                
                return false;
            }

        } else{

            clearScreen();
            System.out.println("Account deletion cancelled.\n");

            return false;
        }
    }
    
    public boolean changePassword(BankAccount account){

        input.nextLine();
        
        clearScreen();
        System.out.println("Password change selected.");
        
        String password = promptString("current password");
        String passwordAux = account.getPassword();
        
        if(!password.equals(passwordAux)){

            clearScreen();
            System.out.println("Error: incorrect password.\n");

            return false;

        } else{

            password = promptString("new password");
            
            if(password.equals(passwordAux)){

                clearScreen();
                System.out.println("Error: new password must be different from current password.\n");

                return false;

            } else{

                System.out.println("\nPlease repeat new password:");
                passwordAux = input.nextLine();
                
                if(!password.equals(passwordAux)){

                    clearScreen();
                    System.out.println("Error: passwords did not match.\n");

                    return false;

                } else{

                    account.setPassword(password);

                    clearScreen();
                    System.out.println("Password updated successfully. Redirecting to home screen...\n");

                    return true;
                }
            }
        }
    }
    
    public boolean changeUsername(BankAccount account){

        input.nextLine();
        
        clearScreen();
        System.out.println("Username change.");
        
        String newUsername = promptString("new username");
        
        if(newUsername.equals(account.getUsername())){

            clearScreen();
            System.out.println("Error: new username must be different from current username.\n");

            return false;

        } else if(!this.database.checkUsername(newUsername)){

            clearScreen();
            System.out.println("Error: username is not available.\n");

            return false;

        } else{

            System.out.println("\nAre you sure you want to change account username?" + "\n" +
                                   "[1] Yes" + "\n" +
                                   "[2] No");
            int option = input.nextInt();
            
            if(option == 1){

                account.setUsername(newUsername);
                
                clearScreen();
                System.out.println("Username updated successfully. Redirecting to home screen...\n");

                return true;

            } else{

                clearScreen();
                System.out.println("Username change cancelled.\n");

                return false;
            }
        }
    }
    
    public void logoffThisAccount(){

        this.currentUsername = null;
        this.currentPassword = null;
        
        clearScreen();
        System.out.println("Loggin off. Redirecting to home screen...\n");
        
    }

    public void adminShowDatabase(){

        clearScreen();
        System.out.println("Printing database...\n");

        database.printDatabase();

        System.out.println();

    }

    public void adminShowAccount(){

        BankAccount account;
        
        System.out.println("\nWould you like to search account by [1]Username or [2]Number?");

        int option = input.nextInt();
        
        if(option == 1){

            input.nextLine();   // Clears buffer.

            String username = promptString("the account's username");

            account = database.locateAccount(username);

            if(account == null){

                clearScreen();
                System.out.println("Error: account does not exist.\n");

            } else{

                clearScreen();
                System.out.println("Printing account...");
                System.out.println("\n" + account.toString() + "\n");

            }

        } else if(option == 2){

            int code = promptInt("the account's code");

            account = database.locateAccount(code);

            if(account == null){

                clearScreen();
                System.out.println("Error: account does not exist.\n");

            } else{

                clearScreen();
                System.out.println("Printing account...");
                System.out.println("\n" + account.toString() + "\n");

            }

        } else{

            clearScreen();
            System.out.println("Error: invalid option.\n");
        }
    }
    
    public void adminDeleteAccount(){

        BankAccount account;

        clearScreen();
        System.out.println("[ADMIN] Account deletion." + "\n" +
                           "\nWould you like to delete an account by [1]Username or [2]Number:");

        int option = input.nextInt();
        
        if(option == 1){

            input.nextLine();   // Clears buffer.

            String username = promptString("the account's username");
            account = database.locateAccount(username);

            if(account == null){

                clearScreen();
                System.out.println("Error: account does not exist.\n");

            } else if(database.deleteAccount(account.getCode())){

                    clearScreen();
                    System.out.println("Account deleted succesfully.\n");

                } else{

                    clearScreen();
                    System.out.println("Error: account could not be deleted.\n");
                }

        } else if(option == 2){

            int code = promptInt("the account's code");
            account = database.locateAccount(code);

            if(account == null){

                clearScreen();
                System.out.println("Error: account does not exist.\n");

            } else if(database.deleteAccount(account.getCode())){

                    database.rearrangeFiles();
                    
                    clearScreen();
                    System.out.println("Account deleted succesfully.\n");

                } else{

                    clearScreen();
                    System.out.println("Error: account could not be deleted.\n");
                }
        
        } else{

            clearScreen();
            System.out.println("Error: invalid option.\n");
        }
    }

    public void adminDeposit(){

        clearScreen();
        System.out.println("[ADMIN] Deposit selected.");
        
        BankAccount account;
        double value;
        int code = promptInt("the account code to deposit to");

        account = database.locateAccount(code);

        if(account == null){
            
            clearScreen();
            System.out.println("Error: account does not exist.\n");

        } else{

            value = promptDouble("the amount to deposit");
            
            if(account.deposit(value)){

                clearScreen();
                System.out.println("Deposit was successful." + "\n\n" +
                                   "\t$" + value + " has been deposited into " + account.getUsername() + "'s account [" + account.getCode() + "].\n");

            } else{

                clearScreen();
                System.out.println("Error: invalid deposit ammount. Value must be greater than zero.\n");
            }
        }

    }

    public void adminTransfer(){

        clearScreen();
        System.out.println("[ADMIN] Transfer selected.");
        
        BankAccount accountSender, accountReceiver;
        double value;
        int code = promptInt("the account code to transfer from");

        accountSender = database.locateAccount(code);

        if(accountSender == null){
            
            clearScreen();
            System.out.println("Error: sender account does not exist.\n");

        } else{
        
            code = promptInt("the account code to transfer to");
            accountReceiver = database.locateAccount(code);

            if(accountReceiver == null){

                clearScreen();
                System.out.println("Error: receiver account does not exist.\n");
                
            } else{

                value = promptDouble("the amount to deposit");

                if(value <= 0){

                    clearScreen();
                    System.out.println("Error: transfer amount must be greater than zero.\n");
                
                } else if(accountSender.transfer(accountReceiver, value)){

                    clearScreen();
                    System.out.println("Transfer was successful." + "\n\n" +
                                       "$" + value + " has been transferred from " + accountSender.getUsername() + "'s account into " + accountReceiver.getUsername() + "'s account." + "\n\n" +
                                       "\tSender account: [" + accountSender.getCode() + "]" + "\n" +
                                       "\tReceiver account: [" + accountReceiver.getCode() + "]\n");

                    } else{

                        clearScreen();
                        System.out.println("Error: transfer could not be completed due to insufficient funds.\n");
                    }
            }
        }
    }

    public String promptString(String prompt){

        System.out.println("\nPlease enter " + prompt + ":");
        
        return this.input.nextLine();

    }

    public int promptInt(String prompt){

        System.out.println("\nPlease enter " + prompt + ":");
        
        return this.input.nextInt();

    }

    public double promptDouble(String prompt){

        System.out.println("\nPlease enter " + prompt + ":");
        
        return this.input.nextDouble();

    }
    
    public void clearScreen(){

        // System.out.print("\f");                  // Clears terminal in BlueJ.
        
        try {                                       // Clears terminal in CMD - kinda works for VSCode terminal aswell =D
            ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls"); // Windows 
            pb.inheritIO().start().waitFor();
        } catch (Exception e) {
            System.out.println("\n[could not clear console]\n");
            e.printStackTrace();
        }

        // System.out.print("\033[H\033[2J");       // Clears terminal with ANSI Escape code (in consoles that support it, e.g. VSCode)
        // System.out.flush();
    }
}
