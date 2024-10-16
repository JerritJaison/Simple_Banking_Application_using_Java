package bank;

import Customer.Customer;

public class Atmhandle {

    public void deposit(int customerid, double amount){
        if(amount<0){
            return;
        }
        Customer c = Bank.customerMap.get(customerid);

        c.balance += amount;
        Bank.customerMap.put(customerid,c);


    }
    public boolean withdraw(int customerid, double amount){
        if(amount<0){
            return false;
        }
        Customer c = Bank.customerMap.get(customerid);
        double check = c.balance - amount;
        if(check>=1000){
            Bank.customerMap.put(customerid,c);
            return true;
        }
        return false;
    }
    public void transfer(int fromcustomerid, int tocustomerid, double amount){
        Customer c = Bank.customerMap.get(tocustomerid);

        if(c == null){
            System.out.println("Customer not found");
            return;
        }
        boolean issucess = withdraw(fromcustomerid, amount);
        if(!issucess){
            deposit(tocustomerid, amount);
            System.out.println("Transfer successful");
        }
    }
}
