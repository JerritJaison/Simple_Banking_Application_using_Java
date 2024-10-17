package bank;

import Customer.Customer;
import transaction.Transaction;
import transaction.Transactionhandler;

public class Atmhandle {

    public void deposit(int customerid, double amount){
        if(amount<0){
            return;
        }
        Customer c = Bank.customerMap.get(customerid);

        c.balance += amount;
        Bank.customerMap.put(customerid,c);

        Transactionhandler handler = new Transactionhandler();
        int lastid = handler.gettransactionid(customerid);

        Transaction trans = new Transaction(lastid++,"Deposit",amount,c.balance);
        handler.writetransaction(customerid,trans);


    }
    public boolean withdraw(int customerid, double amount){
        if(amount<0){
            return false;
        }
        Customer c = Bank.customerMap.get(customerid);
        double check = c.balance - amount;
        if(check>=1000){
            c.balance = check;
            Bank.customerMap.put(customerid,c);

            Transactionhandler handler = new Transactionhandler();
            int lastid = handler.gettransactionid(customerid);
            lastid++;

            Transaction trans = new Transaction(lastid,"Withdraw",amount,c.balance);
            handler.writetransaction(customerid,trans);

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
