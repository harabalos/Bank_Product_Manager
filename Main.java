import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        FileLoader bankStore = new FileLoader();
        FileLoader sellersStore = new FileLoader();
        FileLoader salesStore = new FileLoader();
        FileLoader creditCardMovementStore = new FileLoader();



        ArrayList<Double> creditCardTotalWorthMovements = new ArrayList<Double>(); 
        ArrayList<Double> totalAmountOfLoans_perSeller = new ArrayList<Double>();
        ArrayList<Double> comisionsOfSellers = new ArrayList<Double>();
        double sumComision = 0;

        System.out.println( // MENU
                "Choose:\n\t1.new seller\n\t2.new bank product\n\t3.new sale\n\t4.new credit card movement\n\t5.show loans\n\t6.calculating a seller's commission\n\t7.Display of credit card transactions related to a seller\n\t8.Calculation of the commission of all the sellers of the bank\n\t9.Display the final commission amount of all sellers\n\n\tOr 0 to end the programm!");
        int choice = scan.nextInt();
        while (choice < 0 || choice > 9) { 
            System.out.println("Please type a number between 1 - 9 or 0 to end the programm!");
            choice = scan.nextInt();
        }
        while (choice != 0) { 
            if (choice == 1) { 
            } else if (choice == 2) { 
                }
             else if (choice == 3) {

                    }
             else if (choice == 4) {


  
                }

             else if (choice == 5) {

            } else if (choice == 6) {
  
            } else if (choice == 7) {

            } else if (choice == 8) { 


             
            }

            System.out.println( // ksanarwta sto telos tis loopas
                    "Choose:\n\t1.new seller\n\t2.new bank product\n\t3.new sale\n\t4.new credit card movement\n\t5.show loans\n\t6.calculating a seller's commission\n\t7.Display of credit card transactions related to a seller\n\t8.Calculation of the commission of all the sellers of the bank\n\t9.Display the final commission amount of all sellers\n\n\tOr 0 to end the programm!");
            choice = scan.nextInt();
            while (choice < 0 || choice > 9) { // elegxos gia to an einai anamesa apo 0-9
                System.out.println("Please type a number between 1 - 9 or 0 to end the programm!");
                choice = scan.nextInt();
            }

        }
        scan.close();
        System.out.println("Thank you for visiting Java Bank!");
    }
}

