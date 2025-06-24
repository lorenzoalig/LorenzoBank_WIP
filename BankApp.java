/**
 * This is the Bank application executable. It boots the program interface and starts the menu routines.
 * 
 * Disclaimer: This is not the final version. Its purpose is for testing only. Further documentation to be added.
 * 
 * @lorenzoalig
 * @18.06.25
 */

public class BankApp
{
    public static void main(String args[]){
        
        Interface program = new Interface();
        
        program.boot(5);
        
        startRoutine1(program);
        
    }
    
    public static void startRoutine1(Interface program){
        
        boolean quit = false;
        int option;
        
        program.clearScreen();
        program.printWelcome();
        
        while(quit == false){
            
            program.printOptions1();
            
            option = program.executeMenu1();
            
            if(option == 3){
                
                quit = true;
                
            } else if(option == 1){
                
                quit = false;
                
                } else if(option == 2){
                    
                    option = startRoutine2(program);
                    
                    if(option == 2)
                        quit = true;
                    
                }
        }
    }
    
    public static int startRoutine2(Interface program){         //Returns 1 if 1st menu should repeat and 2 if application should shut down.
        
        boolean quit = false;
        int option;
        
        while(quit == false){
            
            // program.clearScreen();
            program.printWelcomeUser();
            program.printOptions2();
            
            option = program.executeMenu2();
            
            if(option == 3){
                
                quit = true;
                return 2;
                
            } else if(option == 2){
                
                program.setCurrentUsername(null);
                program.setCurrentPassword(null);
                
                quit = true;
                return 1;
                
                } else if(option == 1){
                    
                    quit = false;
                    
                }
        }
        return 1;
    }
}
