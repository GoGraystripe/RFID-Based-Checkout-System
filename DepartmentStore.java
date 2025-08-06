/** This is the DepartmentStore class, which enables the user to make use of the ItemList class
 * and gives true functionality to the program
 * @author Otto Halbhuber 
 * ID: 116150792
 * Recitation: R30*/

import java.util.*;
import java.text.NumberFormat;

public class DepartmentStore{
    public static void main(String[] args) {

        ItemList itemList = new ItemList();
        
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello!");

        while(true){

            introPrompt();

            String userInput = scanner.nextLine();

            if(userInput.equals("C") || userInput.equals("c")){
                itemList.cleanStore();
            }

            if(userInput.equals("I")){
                System.out.print("Enter the name: ");
                String name = scanner.nextLine().trim();

                System.out.print("Enter the RFID: ");
                String RFID = scanner.nextLine().trim();

                System.out.print("Enter the original location: ");
                String originalLocation = scanner.nextLine().trim();

                System.out.print("Enter the price: ");
                double price = scanner.nextDouble();
                scanner.nextLine();

                itemList.insertInfo(name, RFID, price, originalLocation);

            }
            
            if(userInput.equals("L") || userInput.equals("l")){
                System.out.println("Enter the location: ");
                String location = scanner.nextLine().trim();

                itemList.printByLocation(location);

            }

            if(userInput.equals("M") || userInput.equals("m")){
                System.out.println("Enter the RFID: ");
                String RFID = scanner.nextLine().trim();

                System.out.println("Enter the current location: ");
                String originalLocation = scanner.nextLine().trim();

                System.out.println("Enter the new location: ");
                String newLocation = scanner.nextLine().trim();

                itemList.moveItem(RFID, originalLocation, newLocation);
            }

            if(userInput.equals("O") || userInput.equals("o")){
                System.out.println("Enter the cart number: ");
                String cartNumber = scanner.nextLine().trim();

                double cost = itemList.checkOut(cartNumber);
                NumberFormat Format = NumberFormat.getCurrencyInstance();

                System.out.println("The total cost for all merchandise in cart 105 was " + "$" + Format.format(cost));
            }

            if(userInput.equals("P") || userInput.equals("p")){
                itemList.printAll();
            }

            if(userInput.equals("R") || userInput.equals("r")){
                System.out.println("Enter the RFID tag number: ");
                String rfidTagNumber = scanner.nextLine().trim();

                itemList.printByRfidTagNumber(rfidTagNumber);
            }

            if(userInput.equals("U") || userInput.equals("u")){
                itemList.removeAllPurchased();

            }

            if(userInput.equals("Q") || userInput.equals("q")){
                System.out.println("Goodbye!");
                break;
            }
        }
    }

    public static void introPrompt(){
        System.out.println("C - Clean store ");
        System.out.println("I - Insert an item into the list ");
        System.out.println("L - List by location ");
        System.out.println("M - Move an item in the store ");
        System.out.println("O - Checkout  ");
        System.out.println("P - Print all items in store ");
        System.out.println("R - Print by RFID tag number ");
        System.out.println("U - Update inventory system   ");
        System.out.println("Q - Exit the program. ");
        System.out.println();

        System.out.print("Please select an option: ");
    }
}