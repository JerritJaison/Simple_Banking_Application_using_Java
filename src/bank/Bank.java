package bank;

import Customer.Customer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Bank {
    public static List<Customer>  customers = new ArrayList<Customer>();
    public static HashMap<Integer, Customer> customerMap = new HashMap<Integer, Customer>();

    public static int refCustid;
    public static long refAcid;

    public static final double INITIAL_BALANCE = 10000;

}
