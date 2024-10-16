package Customer;

public class Customer {
    public int customerid;
    public String name;
    public Double balance;
    public String password;
    public long acid;
    public Customer(int customerid, String name, Double balance, String password, long acid) {
        this.customerid = customerid;
        this.name = name;
        this.balance = balance;
        this.password = password;
        this.acid = acid;
    }
    @Override
    public String toString() {
        return customerid +" " + acid+
                " " + name + " "+ balance+
                " " + password;
    }
    public long getAccountNumber() {
        return acid;
    }
    public int getCustidr() {
        return customerid;
    }
}
