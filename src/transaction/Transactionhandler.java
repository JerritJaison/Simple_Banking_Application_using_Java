package transaction;

import java.io.File;
import java.io.FileWriter;

public class Transactionhandler {
    public void writetransaction(int customerid,Transaction transaction) {
        String filename = customerid + ".txt";
        try{
            File file = new File(filename);
            if(!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file,true);
            fw.write(transaction.toString());

            fw.close();

        }
        catch (Exception e) {

        }



    }
}
