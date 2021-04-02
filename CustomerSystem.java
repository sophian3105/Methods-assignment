// Throughout this project, the use of data structures are not permitted such as methods like .split and .toCharArray




import java.util.Scanner;
// More packages may be imported in the space below

class CustomerSystem{
    // Declaring global variables to later store in customer data file
    public static String first;
    public static String last;
    public static String city;
    public static String postal;
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
    public static String enterCustomerInfo(){
        Scanner reader = new Scanner(System.in);
        // Storing customer information
        System.out.println("What is your first name?");
        CustomerSystem.first = reader.nextLine();
        System.out.println("What is your last name?");
        CustomerSystem.last = reader.nextLine();
        System.out.println("Where are you from?");
        CustomerSystem.city = reader.nextLine();
        String info = ("Hello " + first + " " + last + " from " + city);
        return info;
    }
    /*
    * This method may be edited to achieve the task however you like.
    * The method may not nesessarily be a void return type
    * This method may also be broken down further depending on your algorithm
    */
    public static void validatePostalCode(){
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