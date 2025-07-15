/**
 * This class serves as a database for all bank accounts created. It works
 * with as an object vector and includes the following methods:
 * 
 *      - Initializing the vector
 *      - Creating and inserting accounts
 *      - Locating an account with a code (returns BankAccount object)
 *      - Deleting an account
 *      - Rearranging files in the database (repositioning the vector to fill null elements)
 *      - Sorting the vector
 *      - Printing the vector (to see all database)
 *      
 * Disclaimer: This is not the final version. Its purpose is for testing only. Further documentation to be added.
 * 
 * @lorenzoalig
 * @01.07.25
 */

public class DataBase
{
    private BankAccount[] accountFiles;
    private int index;
    
    // Constructor
    public DataBase(){
        
        this.index = 0;
        this.accountFiles = null;
    
    }
    
    // Getter for a copy of the database vector for iteration
    public BankAccount[] getAccountFiles(){
        BankAccount[] copyOfAccountFiles = new BankAccount[this.index];
        
        for(int i = 0; i < this.index; i++){
            
            copyOfAccountFiles[i] = this.accountFiles[i];

        }
        
        return copyOfAccountFiles;
    }
    
    // Getter for the index
    public int getIndex(){
        return this.index;
    }
    
    // Intialize the database vector
    public void initializeDatabase(int size){
        
        this.accountFiles = new BankAccount[size];
    
    }
    
    // Insert an account on the database
    public boolean insertAccount(BankAccount account){

        if(this.index >= accountFiles.length){
            
            return false;
        
        } else{
            
            this.accountFiles[index] = account;
            this.index++;
            
            return true;
        }        
    }
    
    // System.out.println("\nError: Account could not be saved. Max number of accounts reached.");
    
    // Locate an account on the database via code
    public BankAccount locateAccount(int code){
        
        int accountCode;
        
        for(int i = 0; i < this.accountFiles.length; i++){
            
            if(this.accountFiles[i] != null){
            
                accountCode = this.accountFiles[i].getCode();
                
                if(accountCode == code){
                
                    return this.accountFiles[i];
                
                }
            }
        }
        
        return null;
    }
    
    // System.out.println("\nError: Account does not exist.");

    // Locate an account on the database via code
    public BankAccount locateAccount(String username){
        
        String accountName;
        
        for(int i = 0; i < this.accountFiles.length; i++){
            
            if(this.accountFiles[i] != null){
                
                accountName = this.accountFiles[i].getUsername();
                
                if(username.equals(accountName)){
                
                    return this.accountFiles[i];
                
                }
            }
        }
        
        return null;
    }
    
    // System.out.println("\nError: Account does not exist.");
    
    // Delete an account from the database via code
    public boolean deleteAccount(int code){
        
        BankAccount aux = locateAccount(code);
        
        for(int i = 0; i < this.index; i++){
            
            if(this.accountFiles[i] == aux){
                    
                    this.accountFiles[i] = null;
                    return true;
                    
            }
        }
        
        return false;
    }
    
    // Rearrange database to fill null elements
    public void rearrangeFiles(){
        
        for(int i = 0; i < this.index; i++){
            
            if(this.accountFiles[i] == null){
                
                    for(int j = i; j < this.index; j++){
                        
                        this.accountFiles[j] = this.accountFiles[j + 1];
                        
                    }
                    
                    this.index--;
            }
        }
    }
    
    // Sort accounts in database by code
    public void sortFilesByCode(){
        
        for(int i = 0; i < this.accountFiles.length; i++){
            
            for(int j = i + 1; j < this.accountFiles.length; j++){
                
                BankAccount temp;
                
                if(accountFiles[j].getCode() < accountFiles[i].getCode()){
                    temp = accountFiles[i];
                    accountFiles[i] = accountFiles[j];
                    accountFiles[j] = temp;
                }
                
            }
        }
    }
    
    // Print entire database
    public void printDatabase(){
        
        for(int i = 0; i < this.accountFiles.length; i++){
            
            if(this.accountFiles[i] == null){
                
                System.out.println("Account{NULL, Position:" + (i + 1) + "}");
                
            }else {
            
                System.out.println(this.accountFiles[i].toString());
            
            }
        }
    }
    
    // Check if username is available
    public boolean checkUsername(String username){          // Checks the account vector to see if username is already taken. If so, returns false.
        
        String accountName;
        
        for(int i = 0; i < this.index; i++){
                       
            accountName = this.accountFiles[i].getUsername();
            
            if(username.equals(accountName))
                
                return false;
        
        }
        
        return true;
    }
    
    // Validate account login attempt
    public boolean validateLogin(String username, String password){
        
        String accountPassword;
        
        if(checkUsername(username)){
            
            return false;
            
        } else {
            
            accountPassword = locateAccount(username).getPassword();
            
            if(password.equals(accountPassword)){
                                
                return true;
                
            } else {
                                
                return false;
                
            }
        }
    }
}
