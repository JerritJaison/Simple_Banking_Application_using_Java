package transaction;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Transactionhandler {
    public void writetransaction(int customerid,Transaction transaction) {
        String filename = customerid + ".txt";
        try{
            File file = new File(filename);

            Boolean isnewfile = false;

            if(!file.exists()) {
                file.createNewFile();
                isnewfile = true;
            }

            FileWriter fw = new FileWriter(file,true);
            if(!isnewfile) {
                fw.write("\n");
            }

            fw.write(transaction.toString());

            fw.close();

        }
        catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }
    public int gettransactionid(int customerid){
        String filename = customerid + ".txt";
        try{
            File file = new File(filename);
            if(!file.exists()) {
                return 0;
            }

            Scanner sc = new Scanner(file);
            String trans="";
            while(sc.hasNext()) {
                trans = sc.nextLine();
            }
            sc.close();

            Transaction lasttr = trans(trans);
            int lastid = lasttr.transactionID;
            return lastid;

        }
        catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return 0;

    }

    public Transaction trans(String trans){
        String arr[] = trans.split(" ");
        return new Transaction(
                Integer.parseInt(arr[0]),
                arr[1],
                Double.parseDouble(arr[2]),
                Double.parseDouble(arr[3])
        );
    }

}
