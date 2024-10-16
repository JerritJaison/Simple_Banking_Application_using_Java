package Customer;

import bank.Bank;
import transaction.Transaction;
import transaction.Transactionhandler;

import java.util.Scanner;

public class Customerhandler2 {

    public void addCustomer(){
        System.out.println("Add customer name");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        System.out.println("password");
        String password = sc.nextLine();

        System.out.println("retype password");
        String retypepassword = sc.nextLine();

        if(!password.equals(retypepassword)){
            System.out.println("Passwords do not match");
            return;

        }
        Bank.refCustid++;
        Bank.refAcid++;

        if(!isvalid(password)){
            System.out.println("failed");
            return;
        }

        password = encryptpass(password);

        Customer c =new Customer(
                Bank.refCustid,
                name,
                Bank.INITIAL_BALANCE,
                password,
                Bank.refAcid
        );
        Bank.customers.add(c);

        Customerhandler.getInstance().addtoFile(c);
        logtransaction(Bank.refCustid);


    }

    private void logtransaction(int customerid) {
        Transaction tr = new Transaction(1,"opening",10000,10000);

        Transactionhandler handler = new Transactionhandler();
        handler.writetransaction(customerid,tr);

    }

    public boolean isvalid(String password){
        char passchar[] = password.toCharArray();
        for(char pa : passchar){
            if((pa >=97 && pa <= 122) || (pa >=65 && pa <= 90) || (pa >=48 && pa <= 57) ){
                continue;
            }
            else return false;
        }
        return true;
    }

    public String encryptpass(String password){
        char passchar[] = password.toCharArray();
        for(int i=0;i<passchar.length;i++){
            if(passchar[i] == 'Z' || passchar[i] == 'z' || passchar[i]=='9') {

                switch (passchar[i]) {
                    case 'z':
                        passchar[i] = 'a';
                        break;
                    case 'Z':
                        passchar[i] = 'A';
                        break;
                    case 9:
                        passchar[i] = 0;
                        break;
                }
            }
            else{
                passchar[i] = (char) (passchar[i]+1);
            }

    }
        String Encryptedpass = String.valueOf(passchar);
        //System.out.println(Encryptedpass);
        return Encryptedpass;
}
public boolean authenticate(int customerid, String password){
        String encrypted = encryptpass(password);


        Customer c = Bank.customerMap.get(customerid);
    System.out.println(c);
        if(c == null){
            System.out.println("user not found");
            return false;
        }
        if(encrypted.equals(c.password)){
            System.out.println("Authenticated");
            return true;
        }
        else {
            System.out.println("invalid password");
        }
        return false;
}

}
