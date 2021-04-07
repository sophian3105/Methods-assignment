/*
 * Date: April 7,2021
 * Name: Sophia and Daniel
 * Teacher: Mr.Ho
 * Description: Methods Assignment
 */
import java.io.IOException;
import java.util.Random;
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
        // Asks the user for their username and location so that program can determine the pathway it needs to take
        System.out.println("What is the pathway you need to take to get to the Method-assignment folder?");
        String pathway = reader.nextLine();


        do{
            printMenu();                                    // Printing out the main menu
            userInput = reader.nextLine();                  // User selection from the menu

            if (userInput.equals(enterCustomerOption)){
                // Only the line below may be editted based on the parameter list and how you design the method return
		        // Any necessary variables may be added to this if section, but nowhere else in the code
                enterCustomerInfo(reader,pathway);
            }
            else if (userInput.equals(generateCustomerOption)) {
                // Only the line below may be editted based on the parameter list and how you design the method return
                generateCustomerDataFile(reader,pathway);
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
     * Procedural method with no returns as it is just storing info into a seperate database file
     * @param Scanner reader so that it would read lines and a string that holds the pathway to reach the database to print the info onto it
     * @return None
     */
    public static void enterCustomerInfo(Scanner reader, String path){ 
        // Storing customer information
        System.out.println("What is your first name?");
        String first = reader.nextLine();
        System.out.println("What is your last name?");
        String last = reader.nextLine();
        System.out.println("Where are you from?");
        String city = reader.nextLine();
        //Calling postal code validation method
        String code = "";
        String postal = validatePostalCode(code, reader, path);
        // Calling credit card validation method
        String card = "";
        String cardNum = validateCreditCard(reader, card);
        // Generates a custom ID
        int num = 0;
        int id = customerId(num, path);
        String customer = "Please write this number down. Customer ID: " + id;
        System.out.println(customer);
        try{
            // Opening file in append form so that previous info is not deleted
            // Append boolean = true
            BufferedWriter bw = new BufferedWriter(new FileWriter(path + "/Methods-assignment/Database.csv",true));  
            PrintWriter pw = new PrintWriter(bw);
            // Adding info into the database file
            String info = "\n" + id + "|" + first + "|" + last + "|" + city + "|" + postal + "|" + cardNum;
            // Appending the file will allow more lines to be added to the database without deleting past data
            bw.append(info);
            bw.close();
            pw.close();
        }
        catch(Exception e){
        }
    }
    /* @author Sophia Nguyen
     * Validates the postal code by scanning the first 3 characters that is inputted
     * This method is used to validate the postal code and then return it 
     * @param A string that holds the postal code, Scanner reader to read files, Path to find the PostalCode.csv
     * @return The postal code
     */
    public static String validatePostalCode(String postal, Scanner reader, String path){
        try{ 
            // User must type in a correct postal code or program will loop
            boolean valid = false;
            do{
                // Reading the postal code file
                File file = new File(path + "/Methods-assignment/PostalCode.csv");
                BufferedReader br = new BufferedReader(new FileReader(file));
                // User must type in at least 3 characters
                boolean enough = false;
                do{
                    System.out.println("What is your postal code? Please input at least 3 characters and make sure it is correct or program will loop.");
                    postal = reader.nextLine();
                        // If there are at least 3 characters
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
                                // Not valid since there are differences
                                else{
                                    valid = false;
                                }
                            }
                            enough = true;
                        }
                        //User inputted less than 3 characters
                        else{
                            System.out.println("Invalid, please input at least 3 characters");
                            enough = false;
                        }
                }while(enough == false);
                //Closing buffer reader
                br.close();
            }while(valid==false);
        }
        // Program cannot find file
        catch (IOException e){ 
            System.out.println("Invalid");
        }
        // Returns the postal code to store it in database
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
    //@Author Daniel Yermashev
    //Using the luhn algorythm to validate user inputed credit card numbers
    //@param a scanner to read the user input, a string that holds the credit card number that user enters
    //return the users card number
    public static String validateCreditCard(Scanner reader, String card){
        boolean valid = false;
        do{
            String space = "";
            boolean enough = false;
            do{
                System.out.println("Enter credit card number. Don't add spaces");
                // Getting the users card number
                card = reader.nextLine();
                // Getting the length of the card
                int len = card.length();
                if (len >= 9){
                    // Loop that reverses the card number
                    for(int i = (card.length()-1); i >= 0; i--){
                        space = space + card.charAt(i);
                    }
                    // the sums of the odd and even numbers
                    int sum1 = 0;
                    int sum2 = 0;
                    // Getting Every odd digit
                    for(int j = 0; j < len; j++){
                        if(j % 2 == 0){
                            String num = Character.toString(space.charAt(j));
                            // Converting the string to and integer
                            sum1 += Integer.parseInt(num);
                        }
                        else{
                            // Getting every even number
                            int num = Integer.parseInt(Character.toString(space.charAt(j)));
                            int doubleNum = num * 2;
                            if (doubleNum > 9){
                                int numSum = 0;
                                do{
                                    // Sum of the numbers
                                    int remainder = doubleNum % 10; // Getting the remainder
                                    numSum = numSum + remainder;
                                    doubleNum = doubleNum /10;
                                    // Creating the sum
                                } while(doubleNum > 0);
                                sum2 += numSum;
                            }
                            else{
                                sum2 += doubleNum;
                            }
                        }
                    }
                    // The total of all sums
                    int total = sum1 + sum2;
                    // If the credit card is valid
                    if(total%10 == 0){
                        System.out.println("Valid");
                        valid = true;
                    }
                    // If the credit card is not valid
                    else{
                        System.out.println("Invalid");
                        valid = false;
                    }
                    enough = true;
                }
                else{
                    // If the user enters less than 9 digits
                    System.out.println("Invalid, please input at least 9 characters");
                    enough = false;
                }
            }while(enough == false);
        }while (valid == false);
        // Returning the card
        return card;
    }
    //@Author Daniel Yermashev
    //Creating a rng that creates a 9 digit id for a customer between the ranges of 100000000 and 199999999 that will always start with 1 
    //@param none
    //return customer string with random customer id
    public static int customerId(int randNum, String path){
        Random id = new Random();
        try{
            // Making sure that ID is always unique by comparing with database
            File database = new File(path + "/Methods-assignment/Database.csv");
            BufferedReader br = new BufferedReader(new FileReader(database));
            String line;
            // Generates a 9 digit random integer
            randNum = id.nextInt(199999999-100000000)+100000000;
            while ((line = br.readLine()) != null){
                String newLine = line.substring(0,9);
                int compare = Integer.parseInt(newLine);
                int diff = (Integer.compare(compare,randNum));
                // Just in case there is already an ID that matches the generator
                // If that is the case generator will create another number
                if (diff == 0){
                    randNum = id.nextInt(199999999-100000000)+100000000;
                }
                else{
                }
            }
            br.close();
        }
        catch(Exception e){
        }
        return randNum;
    } 
    /* @author Sophia Nguyen
     * Creates a csv file that stores all of the information that was inputted from before by collecting information from the database
     * Checks the unique ID and if it matches, user's info will be printed
     * This is a procedural method because there is no return as it is to generate a data file 
     * @param Scanner reader so that it can carry scanner through methods, A string for the path to reach out to the database
     * @return none
     */
    public static void generateCustomerDataFile(Scanner reader, String path){
        try{
            System.out.println("Would you like to store your information on a csv file?");
            String store = reader.nextLine();
            // User wants to store their info on a csv file
            if(store.equals("Yes"))
            {
                // Allows user to name and decide the location of the file
                // Must ask for path to get to the common folders because user might not want to store it in the same place as the Methods-assignment folder
                // We cannot just ask for the user because the different operating systems have different pathways to reach their common folders
                System.out.println("What is the pathway to reach the common folders (e.g Desktop)?");
                String folderPath = reader.nextLine();
                System.out.println("In what common folder would you like to place this in?");
                String commonFolder = reader.nextLine();
                System.out.println("What folder would you like to place this file? Please make sure this folder exists.");
                String folder = reader.nextLine();
                System.out.println("What would you like to name your file?");
                String name = reader.nextLine();
                // Creating the new csv file
                File file = new File(folderPath + "/" + commonFolder + "/" + folder + "/" + name + ".csv");
                // Reading the database
                File data = new File(path + "/Methods-assignment/Database.csv");
                BufferedReader br = new BufferedReader(new FileReader(data));
                boolean print = false;
                // Prompts user for their unique ID
                System.out.println("Please reinput your ID. If file is not created, ID was wrong");
                String id = reader.nextLine();
                // If the document is not blank and ID matches
                // If ID is false program will loop
                String line;
                while ((line = br.readLine()) != null && print == false){
                    String newLine = line.substring(0,9);
                    // If ID is found then program will print out customers info
                    // == 0 means no difference
                    if(id.compareTo(newLine) == 0){
                        System.out.println("Valid");
                        FileWriter cw = new FileWriter(file);
                        // Append allows for text to be added without deleting past info
                        cw.append (line);
                        // Closing csv writer or else new csv file will be blank
                        cw.close();
                        System.out.println("Thank you and have a nice day");
                        print = true;
                    }
                    else{
                        print = false;
                    }
                }
                br.close(); 
            }
            // User does not want to store their info on a csv file
            else if(store.equals("No"))
            {
                System.out.println("Thank you and have a nice day");
            } 
        }
        // If the IO operation fails
        catch(IOException e){
            System.out.println("Fail");
        }
    }
}