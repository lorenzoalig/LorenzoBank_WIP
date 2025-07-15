/**
 * This is the Bank application executable. It boots the program interface and starts the menu routines.
 * 
 * Disclaimer: This is not the final version. Its purpose is for testing only. Further documentation to be added.
 * 
 * @lorenzoalig
 * @10.07.25
 */

public class Application
{
    public static void main(String args[]){
        
        Interface program = new Interface();
        
        program.boot(10);
        
        startRoutine(program);
        
    }
    
    public static void startRoutine(Interface program){
        
        boolean quit = false;
        boolean back = false;
        program.clearScreen();
        
        do{
            program.printWelcome();
            program.printOptions1();
            
            switch(program.executeWelcomeMenu()){
                
                case -1:

                    quit = true;
                    break;
                    
                case 0:

                    quit = false;
                    break;
                    
                case 1:

                    back = false;
                    program.printWelcomeUser();
                    
                    while(!back && !quit){
                        
                        program.printOptions2();
                        
                        switch(program.executeUserMenu()){
                            case -1:
                                quit = true;
                                break;
                                
                            case 0:
                                back = false;
                                break;
                                
                            case 1:
                                back = true;
                                break;
                        }
                    }
                    break;

                case 2:

                    back = false;
                    program.printWelcomeAdmin();

                    while(!quit && !back){
                        
                        program.printOptionsAdmin();

                        switch(program.executeAdminMenu()){

                            case -1:
                                quit = true;
                                break;

                            case 0:
                                quit = false;
                                break;

                            case 1:
                                back = true;
                                break;
                        }
                    }
                    break;

            }
        }while(!quit);
        
        program.printGoodbye();
        
    }
}