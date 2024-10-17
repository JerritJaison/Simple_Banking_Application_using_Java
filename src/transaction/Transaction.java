package transaction;

public class Transaction {
    public int transactionID;
    public String type;
    public double amount;
    public double balance;

    public Transaction(int transactionID, String type, double amount, double balance) {
        this.transactionID = transactionID;
        this.type = type;
        this.amount = amount;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return  transactionID +" "+
                 type + " " +
                 amount +" "+
                balance ;
    }
}
