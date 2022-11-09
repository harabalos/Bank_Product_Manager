import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        FileLoader bankStore = new FileLoader();
        FileLoader sellersStore = new FileLoader();
        FileLoader salesStore = new FileLoader();
        FileLoader creditCardMovementStore = new FileLoader();

        if (bankStore.isFileExists(new File("BankProducts.txt")) && // elegxos an ta files einai kala grammena
                sellersStore.isFileExists(new File("Sellers.txt"))
                && salesStore.isFileExists(new File("Sales.txt"))
                && creditCardMovementStore.isFileExists(new File("CreditCardMovement.txt"))) {
            bankStore.loadFileBankProducts("BankProducts.txt");
            sellersStore.loadFileSellers("Sellers.txt");
            salesStore.loadFileSales("Sales.txt");
            creditCardMovementStore.loadFileCreditCardMovements("CreditCardMovement.txt");
        } else {
            System.out.println("File doesnt exist..");
            System.exit(0);
        }

        ArrayList<Double> creditCardTotalWorthMovements = new ArrayList<Double>(); // dilwsi arraylists pou
                                                                                   // xrisimopoiountai gia ton
                                                                                   // upologismo tou commision kialla..
        ArrayList<Double> totalAmountOfLoans_perSeller = new ArrayList<Double>();
        ArrayList<Double> comisionsOfSellers = new ArrayList<Double>();
        double sumComision = 0;

        System.out.println( // MENU
                "Choose:\n\t1.new seller\n\t2.new bank product\n\t3.new sale\n\t4.new credit card movement\n\t5.show loans\n\t6.calculating a seller's commission\n\t7.Display of credit card transactions related to a seller\n\t8.Calculation of the commission of all the sellers of the bank\n\t9.Display the final commission amount of all sellers\n\n\tOr 0 to end the programm!");
        int choice = scan.nextInt();
        while (choice < 0 || choice > 9) { // an den einai anamesa apo 0 kai 9 ksanarwtaei
            System.out.println("Please type a number between 1 - 9 or 0 to end the programm!");
            choice = scan.nextInt();
        }
        while (choice != 0) { // upologizei ta sinolika loans tou kathena
            for (int i = 0; i < sellersStore.getSellers().size(); i++) {
                double sum = 0;
                for (int j = 0; j < bankStore.getLoans().size(); j++) {
                    if (sellersStore.getSellers().get(i).getCode() == bankStore.getLoans().get(j).getCode()) {
                        sum += bankStore.getLoans().get(j).getAmountOfLoan();
                    }
                }
                totalAmountOfLoans_perSeller.add(sum); // ta vazei sti lista
            }

            for (int z = 0; z < bankStore.getCreditCards().size(); z++) { // upologizei tis sinolikes kiniseis tou
                                                                          // kathena
                double sum = 0.0;
                for (int j = 0; j < creditCardMovementStore.getCreditCardMovements().size(); j++) {
                    if (bankStore.getCreditCards().get(z).getCode() == creditCardMovementStore.getCreditCardMovements()
                            .get(j)
                            .getCode()) {
                        sum += creditCardMovementStore.getCreditCardMovements().get(j).getMovementWorth();
                    }
                }
                creditCardTotalWorthMovements.add(sum);
            }

            for (int v = 0; v < sellersStore.getSellers().size(); v++) { // upologizei ta commision tou kathena
                if (totalAmountOfLoans_perSeller.get(v) <= 500000) {
                    comisionsOfSellers
                            .add((totalAmountOfLoans_perSeller.get(v) * 0.1) + (creditCardTotalWorthMovements.get(v)
                                    * bankStore.getCreditCards().get(v).getCommisionRate()));
                } else if (totalAmountOfLoans_perSeller.get(v) <= 2000000) {
                    comisionsOfSellers.add((totalAmountOfLoans_perSeller.get(v) * 0.2)
                            + (creditCardTotalWorthMovements.get(v)
                                    * bankStore.getCreditCards().get(v).getCommisionRate()));
                } else {
                    comisionsOfSellers.add((totalAmountOfLoans_perSeller.get(v) * 0.25)
                            + (creditCardTotalWorthMovements.get(v)
                                    * bankStore.getCreditCards().get(v).getCommisionRate()));
                }
            }

            if (choice == 1) { // input apo xristi
                scan.nextLine();
                System.out.print("First name: ");
                String firstName = scan.nextLine();

                System.out.print("Last name: ");
                String lastName = scan.nextLine();

                System.out.print("Code: ");
                int code = scan.nextInt();

                System.out.print("AFM: ");
                int afm = scan.nextInt();

                Sellers seller = new Sellers(firstName, lastName, afm, code); // ftiaxnei kainourio seller object me ta
                                                                              // input tou xristi
                sellersStore.getSellers().add(seller);

                System.out.println("SELLER ADDED");

            } else if (choice == 2) { // input apo xristi
                System.out.print("Select 1 for credit cards,\nOr 2 for loans:");
                int answer = scan.nextInt();
                if (answer == 1) {
                    System.out.print("Write the code: ");
                    int codeP = scan.nextInt();

                    System.out.print("Write the number: ");
                    int number = scan.nextInt();

                    System.out.print("Write the AFM: ");
                    int afm = scan.nextInt();

                    System.out.print("Write the commision rate: ");
                    double commissionRate = scan.nextDouble();

                    System.out.print("Write the max amount of movement: ");
                    double maxAmountOfMovement = scan.nextDouble();

                    System.out.print("Write the max annual amount: ");
                    double maxAnnualAmmount = scan.nextDouble();

                    CreditCards creditCardx = new CreditCards(codeP, number, afm, null, null, commissionRate,
                            maxAmountOfMovement,
                            maxAnnualAmmount); // ftiaxnei object credit cards kai vazei ta input tou xristi

                    bankStore.getCreditCards().add(creditCardx);

                    System.out.println("CREDIT CARD ADDED");
                } else if (answer == 2) {
                    System.out.print("Write the code: ");
                    int codeP = scan.nextInt();

                    System.out.print("Write the number: ");
                    int number = scan.nextInt();

                    System.out.print("Write the AFM: ");
                    int afm = scan.nextInt();

                    System.out.println("Write the amount of Loan: ");
                    int amountOfLoan = scan.nextInt();

                    System.out.println("Write the Max annual interest rate: ");
                    int annualInterestRate = scan.nextInt();

                    Loan loanx = new Loan(codeP, number, afm, null, null, amountOfLoan, annualInterestRate);

                    bankStore.getLoans().add(loanx); // ftiaxnei object credit cards kai vazei ta input tou xristi

                    System.out.println("LOAN ADDED");

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
}

