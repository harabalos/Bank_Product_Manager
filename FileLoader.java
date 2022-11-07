import java.io.*;
import java.util.ArrayList;

public class FileLoader {

    private ArrayList<CreditCards> creditCards = new ArrayList<CreditCards>(); // Dilwsi arraylist gia ta creditcards
    private ArrayList<Loan> loans = new ArrayList<Loan>();// Dilwsi arraylist gia ta loans
    private ArrayList<Sellers> sellers = new ArrayList<Sellers>();// Dilwsi arraylist gia tous sellers
    private ArrayList<Sales> sales = new ArrayList<Sales>();// Dilwsi arraylist gia ta sales
    private ArrayList<CreditCardMovement> creditCardMovements = new ArrayList<CreditCardMovement>();// Dilwsi arraylist
                                                                                                    // gia ta
                                                                                                    // creditcards
                                                                                                    // movements

    public void loadFileBankProducts(String data) {
        BufferedReader reader = null; // ta thetoume null
        CreditCards creditCard = null;
        Loan loan = null;
        try {
            reader = new BufferedReader(new FileReader(data));
            String line;
            while ((line = reader.readLine()) != null) { // diavazei kathe line tou text
                if (line.toUpperCase().contains("BANKITEM_LIST".toUpperCase()) || (line.contains("{"))
                        || (line.toUpperCase().contains("BANKITEM".toUpperCase()))
                        || (line.contains("}"))) { // an to line periexei ena apo afta tote pigainei sto epomeno
                    continue;

                } else {
                    if (line.toUpperCase().contains("Card".toUpperCase())) { // an line periexei to Card tote ftiaxnei
                                                                             // object
                        creditCard = new CreditCards(); // credit card kai topothetei
                        line = reader.readLine(); // ta stoixeia pou diavazei an line
                        ((CreditCards) creditCard).setCode(Integer.parseInt(line.trim().split(" ", 2)[1]));
                        line = reader.readLine();
                        ((CreditCards) creditCard).setDescribe(line.trim().split(" ", 2)[1]);
                        line = reader.readLine();
                        ((CreditCards) creditCard).setAfm(Integer.parseInt(line.trim().split(" ", 2)[1]));
                        line = reader.readLine();
                        ((CreditCards) creditCard).setNumber(Integer.parseInt(line.trim().split(" ", 2)[1]));
                        line = reader.readLine();
                        ((CreditCards) creditCard)
                                .setCommissionRate(Double.parseDouble(line.trim().split(" ", 2)[1]));
                        line = reader.readLine();
                        ((CreditCards) creditCard)
                                .setMaxAmountOfMovement(Double.parseDouble(line.trim().split(" ", 2)[1]));
                        line = reader.readLine();
                        ((CreditCards) creditCard)
                                .setMaxAnnualAmmount(Double.parseDouble(line.trim().split(" ", 2)[1]));
                    } else if (line.toUpperCase().contains("Loan".toUpperCase())) { // an line periexei to Loan tote
                                                                                    // ftiaxnei object
                        loan = new Loan(); // loan kai topothetei
                        line = reader.readLine(); // ta stoixeia pou diavazei an line
                        ((Loan) loan).setCode(Integer.parseInt((line.trim().split(" ", 2)[1])));
                        line = reader.readLine();
                        ((Loan) loan).setDescribe(((line.trim().split(" ", 2)[1])));
                        line = reader.readLine();
                        ((Loan) loan).setAfm(Integer.parseInt(((line.trim().split(" ", 2)[1]))));
                        line = reader.readLine();
                        ((Loan) loan).setNumber(Integer.parseInt(((line.trim().split(" ", 2)[1]))));
                        line = reader.readLine();
                        ((Loan) loan).setAmountOfLoan(Integer.parseInt(((line.trim().split(" ", 2)[1]))));
                        line = reader.readLine();
                        ((Loan) loan).setAnnualInterestRate(Integer.parseInt(((line.trim().split(" ", 2)[1]))));
                    }
                    if (creditCards.size() < 4) {
                        creditCards.add(creditCard);
                    }
                    if (loan != null) {
                        loans.add(loan);
                    }
                    line = reader.readLine();
                }
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());

        }
    }
}