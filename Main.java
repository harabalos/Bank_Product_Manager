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
            } else if (choice == 3) {
                System.out.print("Choose the seller by his code: \n\n");
                for (int i = 0; i < sellersStore.getSellers().size(); i++) { // kanei print tou sellers gia na tous dei
                                                                             // o xristis kai na mporei na epileksei to
                                                                             // code tou
                    System.out.println(sellersStore.getSellers().get(i));
                }
                int mparigkasCode = scan.nextInt();
                int j = 0;
                while (true) { // loopa pou elegxei an exei dwthei swsti timi
                    for (int i = 0; i < sellersStore.getSellers().size(); i++) {
                        if (mparigkasCode == sellersStore.getSellers().get(i).getCode()) {
                            j = 1;
                            break;
                        }
                    }
                    if (j == 1) {
                        break;
                    } else {
                        System.out.print("Doesnt exist! Write again: ");
                        mparigkasCode = scan.nextInt();
                    }
                }
                System.out.print("What's the sale code: ");
                int saleCode = scan.nextInt();
                System.out.print("Whats the Bank Product you want to choose(1 for credit card and 2 for loan)? ");
                int bankProduct = scan.nextInt();

                System.out.print("Tell us the reasoning: ");
                scan.nextLine();
                String reason = scan.nextLine();
                Sales salere = new Sales(saleCode, bankProduct, reason, null); // ftiaxnei kainourio object sales kai
                                                                               // vazei tis times tou xristi

                salesStore.getSales().add(salere);

                String bankItemType;
                if (bankProduct == 1) { // elegxei poia timi exei dwthei etsi wste na to metafrasi san card i loan
                    bankItemType = "Card";
                } else {
                    bankItemType = "Loan";
                }

                System.out.print("Do you want to store this sale?");
                String storeSale = scan.nextLine();

                if (storeSale.equalsIgnoreCase("yes")) { // kalei tin WriteFilesSales kai grafei ta stoixeia sto
                                                         // Sales.txt
                    salesStore.WriteFilesSales("Sales.txt", saleCode, bankItemType, bankProduct, reason);
                }

                System.out.println("SALE ADDED");
            } else if (choice == 4) {

                System.out.println("Write the code of the credit card you want to choose to do this movement: ");

                for (int i = 0; i < bankStore.getCreditCards().size(); i++) { // emfanizei tis credit cards
                    System.out.println(bankStore.getCreditCards().get(i));
                }
                int CCcode = scan.nextInt();
                int j = 0;
                int position = 0;
                while (true) { // loop gia ton elegxo egkirotitas timwn
                    for (int i = 0; i < bankStore.getCreditCards().size(); i++) {
                        if (CCcode == bankStore.getCreditCards().get(i).getCode()) {
                            j = 1;
                            position = i; // apothikevei to i
                            break;
                        }
                    }
                    if (j == 1) {
                        break;
                    } else {
                        System.out.print("Doesnt exist! Write again: ");
                        CCcode = scan.nextInt();
                    }
                }
                double s2 = creditCardTotalWorthMovements.get(position);
                if (s2 >= bankStore.getCreditCards().get(position).getmaxAnnualAmmount()) { // elegxei an to
                                                                                            // sigkekrimeno movement
                                                                                            // einai megalitero apo to
                                                                                            // maxannoual kai emfanizei
                                                                                            // to katalilo minima
                    System.out.println("YOU HAVE REACHED THE LIMIT!");

                } else {
                    System.out.print("Write the worth: ");
                    double worth = scan.nextDouble();

                    boolean ok1 = false;
                    boolean ok2 = false;
                    double s = creditCardTotalWorthMovements.get(position) + worth;
                    while (true) { // elegxos gia to an eixe kseperasei to max amount of transactions
                        if (worth > bankStore.getCreditCards().get(position).getmaxAmountOfMovement()) {
                            System.out.println(
                                    "You have surpassed the max amount of transaction.Type another amount of money: ");
                            s = creditCardTotalWorthMovements.get(position) - worth;
                            worth = scan.nextDouble();
                        } else {
                            ok1 = true;
                            s = worth + creditCardTotalWorthMovements.get(position);
                        }

                        if (s > bankStore.getCreditCards().get(position).getmaxAnnualAmmount()) {
                            System.out.println( // elegxos gia to an exei kseperasei to max annual transaction amount
                                    "You have surpassed the max annual transaction amount.Type another amount of money: ");
                            worth = scan.nextDouble();
                        } else {
                            ok2 = true;
                        }
                        if (ok1 && ok2) {
                            break;
                        }
                    }

                    creditCardTotalWorthMovements.set(position, s); // apothikevei stri lista
                    if (ok1 && ok2) {
                        scan.nextLine();
                        System.out.print("Write the reasoning: ");
                        String reason = scan.nextLine();
                        creditCardMovementStore.getCreditCardMovements()
                                .add(new CreditCardMovement(CCcode, worth, reason, CCcode));
                        s2 = s;

                        System.out.print("Do you want to store this sale?");
                        String storeSale = scan.nextLine();

                        if (storeSale.equalsIgnoreCase("yes")) { // kalei tin WriteFilesCreditCardMovements pou grafei
                                                                 // ta stoixeia pou edwse o xristis sto
                                                                 // CreditCardMovements.txt
                            creditCardMovementStore.WriteFilesCreditCardMovements("CreditCardMovement.txt", CCcode,
                                    CCcode, worth, reason);
                        }
                    }
                    System.out.println("CREDIT CARD MOVEMENT ADDED..");
                }

            } else if (choice == 5) {
                System.out.println(bankStore.getLoans()); // emfanizei ta loans olwn
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

