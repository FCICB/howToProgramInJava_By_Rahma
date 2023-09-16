import java.io.IOException;
import java.nio.file.Paths;
import java.util.Formatter;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class FileMatch {


     Formatter outNewMaster = new Formatter("newmast.txt");
    Formatter outLog= new Formatter("log.txt");

    public FileMatch() throws IOException {
    }
    public void matchFiles(){
        try( Scanner inOldMaster = new Scanner(Paths.get("oldmast.txt"));)
        {

            while (inOldMaster.hasNext()) // while there is more to read
            {
                Account account = new Account();
                account.setAccount(inOldMaster.nextInt());
                account.setFirstName(inOldMaster.next());
                account.setLastName(inOldMaster.next());
                account.setBalance(inOldMaster.nextDouble());
                try(Scanner inTransaction = new Scanner(Paths.get("trans.txt"));)
                {
                    while (inTransaction.hasNext()) // while there is more to read
                    {
                        TransactionRecord transaction = new TransactionRecord();
                        transaction.setAccountNumber(inTransaction.nextInt());
                        transaction.setAmount(inTransaction.nextDouble());
                        if(account.getAccount() == transaction.getAccountNumber()){
                            account.combine(transaction);
                            break;
                        }

                    }
                }
                outNewMaster.format("%d %s %s %.2f%n",
                        account.getAccount(), account.getFirstName(),
                        account.getLastName(), account.getBalance());
                outNewMaster.flush();
            }
        }
        catch (NoSuchElementException elementException)
        {
            System.err.println("File improperly formed. Terminating.");
        }
        catch (IllegalStateException stateException)
        {
            System.err.println("Error reading from file. Terminating.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try( Scanner inTransaction = new Scanner(Paths.get("trans.txt"));)
        {
            while (inTransaction.hasNext()) // while there is more to read
            {
                TransactionRecord transaction = new TransactionRecord();
                transaction.setAccountNumber(inTransaction.nextInt());
                transaction.setAmount(inTransaction.nextDouble());
               boolean flag=false;
                try(Scanner inOldMaster = new Scanner(Paths.get("oldmast.txt"));)
                {
                    while (inOldMaster.hasNext()) // while there is more to read
                    {
                        Account account = new Account();
                        account.setAccount(inOldMaster.nextInt());
                        account.setFirstName(inOldMaster.next());
                        account.setLastName(inOldMaster.next());
                        account.setBalance(inOldMaster.nextDouble());
                        if(account.getAccount() == transaction.getAccountNumber()){
                            flag=true;
                            break;
                        }

                    }
                    if (flag == false){
                        outLog.format("%d %.2f%n",
                                transaction.getAccountNumber(), transaction.getAmount());
                        outLog.flush();
                    }
                }
            }
        }
        catch (NoSuchElementException elementException)
        {
            System.err.println("File improperly formed. Terminating.");
        }
        catch (IllegalStateException stateException)
        {
            System.err.println("Error reading from file. Terminating.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }



}
