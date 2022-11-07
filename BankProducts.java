
public class BankProducts {
    private int code;
    private int number;
    private int afm;



    public BankProducts(int code, int number, int afm, String type, String describe) {
        this.code = code;
        this.number = number;
        this.afm = afm;


    }

    public BankProducts() {

    }

    public BankProducts(BankProducts source) {
        this.code = source.code;
        this.number = source.number;
        this.afm = source.afm;

    }

    public int getAfm() {
        return this.afm;
    }

    public int getCode() {
        return this.code;
    }

    public int getNumber() {
        return this.number;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setAfm(int afm) {
        this.afm = afm;
    }

    public void setNumber(int number) {
        this.number = number;
    }


}
