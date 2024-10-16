import Customer.Customerhandler;
import Customer.Customerhandler2;
import bank.Atmhandle;
import bank.Bank;

import java.io.IOException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //Customerhandler.getInstance().initialize();
        Customerhandler handler = new Customerhandler();
        try {
            handler.initialize();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


       // System.out.println(Bank.customers.get(Bank.customers.size() - 1));

        menu();
        handler.finalizefile();
    }
    static void menu(){
        System.out.println("Enter choice"+"\n1.add user"+"\n2.authenticate"+"\n3.withdraw"+"\n4.deposit"+"\n5.transfer");
        try{
            Scanner sc = new Scanner(System.in);
            int option = sc.nextInt();
            Customerhandler2 handle = new Customerhandler2();
            Atmhandle handler = new Atmhandle();
            switch(option){
                case 1:
                    handle.addCustomer();
                    break;
                case 2:
                    System.out.println("enter customerid");
                    int customerid = sc.nextInt();
                    System.out.println("enter password");
                    String password = sc.next();
                    handle.authenticate(customerid,password);
                    break;
                case 3:
                    withdraw();
                    break;
                case 4:
                    deposit();
                    break;
                case 5:
                    transfer();
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
        catch(Exception e){
            System.out.println("Invalid input");
        }

    }

    private static void transfer() {
        Scanner sc = new Scanner(System.in);

        Customerhandler2 handle = new Customerhandler2();
        Atmhandle handler = new Atmhandle();

        System.out.println("enter customerid");
        int customerid = sc.nextInt();
        System.out.println("enter password");
        String password = sc.next();
        if(handle.authenticate(customerid,password)) {
            System.out.println("enter to customer id");
            int tocustomerid = sc.nextInt();
            System.out.println("enter transfer amount");
            double transferamount = sc.nextInt();
            handler.transfer(customerid,tocustomerid,transferamount);
        }
    }

    private static void withdraw() {
        Scanner sc = new Scanner(System.in);

        Customerhandler2 handle = new Customerhandler2();
        Atmhandle handler = new Atmhandle();

        System.out.println("enter customerid");
        int customerid = sc.nextInt();
        System.out.println("enter password");
        String password = sc.next();
        if(handle.authenticate(customerid,password)) {
            System.out.println("enter withdraw amount");
            double withdrawamount = sc.nextInt();
            handler.withdraw(customerid,withdrawamount);
        }
    }
    private static void deposit() {
        Scanner sc = new Scanner(System.in);

        Customerhandler2 handle = new Customerhandler2();
        Atmhandle handler = new Atmhandle();

        System.out.println("enter customerid");
        int customerid = sc.nextInt();
        System.out.println("enter password");
        String password = sc.next();
        if(handle.authenticate(customerid,password)) {
            System.out.println("enter deposit amount");
            double depositamount = sc.nextInt();
            handler.deposit(customerid,depositamount);
        }
    }

}

