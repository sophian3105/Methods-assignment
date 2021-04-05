// Throughout this project, the use of data structures are not permitted such as methods like .split and .toCharArray



import java.io.IOException;
import java.util.Scanner;
import java.io.*;
import java.io.File;
// More packages may be imported in the space below

class CustomerSystem{
    public static void main(String[] args){
        // Please do not edit any of these variables
        Scanner reader = new Scanner(System.in);
        String userInput, enterCustomerOption, generateCustomerOption, exitCondition;
        enterCustomerOption = "1";
        generateCustomerOption = "2";
        exitCondition = "9";

        // More variables for the main may be declared in the space below


        do{
            printMenu();                                    // Printing out the main menu
            userInput = reader.nextLine();                  // User selection from the menu

            if (userInput.equals(enterCustomerOption)){
                // Only the line below may be editted based on the parameter list and how you design the method return
		        // Any necessary variables may be added to this if section, but nowhere else in the code
                enterCustomerInfo();
            }
            else if (userInput.equals(generateCustomerOption)) {
                // Only the line below may be editted based on the parameter list and how you design the method return
                generateCustomerDataFile();
            }
            else{
                System.out.println("Please type in a valid option (A number from 1-9)");
            }

        } while (!userInput.equals(exitCondition));         // Exits once the user types 
        
        reader.close();
        System.out.println("Program Terminated");
    }
    public static void printMenu(){
        System.out.println("Customer and Sales System\n"
        .concat("1. Enter Customer Information\n")
        .concat("2. Generate Customer data file\n")
        .concat("3. Report on total Sales (Not done in this part)\n")
        .concat("4. Check for fraud in sales data (Not done in this part)\n")
        .concat("9. Quit\n")
        .concat("Enter menu option (1-9)\n")
        );
    }
    /* @author Sophia Nguyen
     * Introduces user to program and also stores their information
     * @param none
     * @return The user inputs for the questions to be later used in a different method
     */
    public static void enterCustomerInfo(){
        String code = "";
        String name = "";
        Scanner reader = new Scanner(System.in);
        // Storing customer information
        System.out.println("What is your first name?");
        String first = reader.nextLine();
        System.out.println("What is your last name?");
        String last = reader.nextLine();
        System.out.println("Where are you from?");
        String city = reader.nextLine();
        //Calling postal code validation method
        validatePostalCode(code,name);
    }
    /* @author Sophia Nguyen
     * Validates the postal code by scanning the first 3 characters that is inputted
     * This is a procedural method because there is no return
     * @param none
     * @return none
     */
    public static String validatePostalCode(String postal, String user){
        Scanner reader = new Scanner(System.in);
        try{ 
            // Finding the postal code file for comparison
            System.out.println("Please input your username on your local machine");
            user = reader.nextLine();
            System.out.println("Please input where you placed the Methods-assignment folder");
            String loc = reader.nextLine();
            // User must type in a correct postal code or program will loop
            boolean valid = false;
            do{
                // Reading the postal code file
                File file = new File("/Users/" + user + "/" + loc + "/Methods-assignment/PostalCode.csv");
                BufferedReader br = new BufferedReader(new FileReader(file));
                // User must type in at least 3 characters
                boolean enough = false;
                do{
                    System.out.println("What is your postal code? Please input at least 3 characters and make sure it is correct or program will loop.");
                    postal = reader.nextLine();
                        if (calculateCharacters(postal)>=3){
                            String line;
                            while ((line = br.readLine()) != null && valid == false){
                                // Takes 3 characters from both user input and postal codes to compare
                                String newPostal = postal.substring(0,3);
                                String compare = line.substring(0, 3);
                                // If there are no differences then that means the postal codes are the same so it is valid
                                if(compare.compareTo(newPostal) == 0){
                                    System.out.println("Valid");
                                    valid = true;
                                }
                                else{
                                    valid = false;
                                }
                            }
                            enough = true;
                        }
                    else{
                        System.out.println("Invalid, please input at least 3 characters");
                        enough = false;
                    }
                }while(enough == false);
            }while(valid==false);
        }
        // Program cannot find file
        catch (IOException e){ 
            System.out.println("Invalid");
        }
        return postal;
    }
    /* @author Sophia Nguyen
     * Calculates the amount of characters in a string to be used in a different method
     * @param A string that the method counts the characters of
     * @return The amount of characters in a string
     */
    public static int calculateCharacters(String x){
        int len = x.length();
        return len;
    }
    /*
    * This method may be edited to achieve the task however you like.
    * The method may not nesessarily be a void return type
    * This method may also be broken down further depending on your algorithm
    */
    public static void validateCreditCard(){
    }
    /*
    * This method may be edited to achieve the task however you like.
    * The method may not nesessarily be a void return type
    * This method may also be broken down further depending on your algorithm
    */
    public static void generateCustomerDataFile(){
    }
    /*******************************************************************
    *       ADDITIONAL METHODS MAY BE ADDED BELOW IF NECESSARY         *
    *******************************************************************/
}