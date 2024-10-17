package Customer;

import bank.Bank;

import java.io.*;
import java.util.*;

public class Customerhandler {

    //singleton method
    static Customerhandler handler; // instead of creating new object in every class we can use getInstance
    public static Customerhandler getInstance() {
        if (handler == null) {
            handler = new Customerhandler();
        }
        return handler;
    }


    private static final String filename = "bank.txt";

    public void initialize() throws IOException {
        //List<Customer> customerslist = new ArrayList<Customer>();
        File file = new File(filename); //open file to reading from file

        BufferedReader read = new BufferedReader(new FileReader(file));
        String customerinfo = read.readLine(); //storing each line in customerinfo

        do {  //we use parseinfo method to get each customeinfo
            Customer singlecustomer = parseinfo(customerinfo);

            Bank.customers.add(singlecustomer);
            Bank.customerMap.put(singlecustomer.customerid, singlecustomer);//adding to hashmap

            customerinfo = read.readLine();
        } while (customerinfo != null);

        read.close(); //must step

        //Bank.customers=customerslist; //since it is static no need for creating object


        Bank.refCustid = Bank.customers.size();

        Customer lastCustomer = Bank.customers.get(Bank.customers.size() - 1);
        long lastAccountNumber = lastCustomer.getAccountNumber();

        Bank.refAcid=lastAccountNumber;
    }

        private Customer parseinfo(String customerinfo){
            String[] info = customerinfo.split(" ");
            Customer customer =new Customer(
                    Integer.parseInt(info[0]),
                    info[2],
                    Double.parseDouble(info[3]),
                    info[4],
                    Long.parseLong(info[1])

            );
            return customer;
        }

        public void addtoFile(Customer customer){
            File file = new File(filename);
            BufferedWriter writer = null;
            try {
                writer = new BufferedWriter(new FileWriter(file,true));
                writer.write("\n"+customer.toString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            finally{
                if(writer != null){
                    try {
                        writer.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

        }
    public void finalizefile(){ //this method is used to write to file after deposit,withdraw,transfer
        File file = new File(filename);
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file));

            Set keyset =  Bank.customerMap.keySet();
            Iterator iterator = keyset.iterator();
            Boolean isfirstline = true;

            while(iterator.hasNext()){
                int customerid = (Integer)iterator.next();
                Customer customer = Bank.customerMap.get(customerid);
                if(isfirstline){
                    writer.write(customer.toString());
                    isfirstline = false;
                }else {
                    writer.write("\n" + customer.toString());
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally{
            if(writer != null){
                try {
                    writer.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

    }

