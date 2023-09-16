import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    private static Formatter output;
    private static Scanner input;

    public static void main(String[] args) throws IOException {
        System.out.printf("%nEnter request%n%s%n%s%n%s%n%s%n%s%n",
                " 1 - Add Records To oldmast.txt",
                " 2 - List accounts From oldmast.txt",
                " 3 - Add Records To trans.txt",
                " 4 - List accounts From trans.txt",
                " 5 - Create Newest.txt By cpmbining trans.txt and oldmast.txt",
                " 6 - Terminate program");

        System.out.printf("%n? ");
        Scanner in = new Scanner(System.in);
        int choice;
        choice=in.nextInt();
        while (choice != 6) {
            switch (choice) {
                case 1:
                    processWritingToFile(1);
                    break;
                case 2:
                    processReadingFile(1);
                    break;
                case 3:
                    processWritingToFile(2);
                    break;
                case 4:
                    processReadingFile(2);
                    break;
                case 5:
                    FileMatch fileMatch = new FileMatch();
                    fileMatch.matchFiles();

            }
            System.out.printf("%nEnter request%n%s%n%s%n%s%n%s%n%s%n",
                    " 1 - Add Records To oldmast.txt",
                    " 2 - List accounts From oldmast.txt",
                    " 3 - Add Records To trans.txt",
                    " 4 - List accounts From trans.txt",
                    " 5 - Create Newest.txt By cpmbining trans.txt and oldmast.txt",
                    " 6 - Terminate program");
            System.out.printf("%n? ");
            choice = in.nextInt();
            System.out.println(choice);
        }
        System.out.println("Exit Successfully");
    }
    public static void processReadingFile(int fileNumber)  {
        if(fileNumber == 1){
            openFileForReading("oldmast.txt");

        } else if (fileNumber == 2) {
            openFileForReading("trans.txt");

        }
        readRecords(fileNumber);
        closeFileInput();
    }
    public static void processWritingToFile(int fileNumber)  {
        if(fileNumber == 1){
            openFileForWriting("oldmast.txt");

        } else if (fileNumber == 2) {
            openFileForWriting("trans.txt");
        }
        addRecordsToOldFile(fileNumber);
        closeFileOutput();
    }
    public static void openFileForWriting(String fileName)  {
        try
        {
            FileOutputStream file =new FileOutputStream(fileName,true);
            output = new Formatter(file);
        }
        catch (SecurityException securityException)
        {
            System.err.println("Write permission denied. Terminating.");
            System.exit(1);
        }
        catch (FileNotFoundException fileNotFoundException)
        {
            System.err.println("Error opening file. Terminating.");
            System.exit(1);
        }
    }
    public static void addRecordsToOldFile(int fileNumber)
    {
        Scanner input = new Scanner(System.in);
        if(fileNumber == 1){
            System.out.printf("%s%n%s%n? ",
                    "Enter account number, first name, last name and balance.",
                    "Enter end-of-file indicator to end input.");
        } else if (fileNumber == 2) {
            System.out.printf("%s%n%s%n? ",
                    "Enter account number and balance.",
                    "Enter end-of-file indicator to end input.");
        }
        while (input.hasNext())
        {
            try
            {
                if(fileNumber == 1){
                    output.format("%d %s %s %.2f%n", input.nextInt(),
                            input.next(), input.next(), input.nextDouble());
                } else if (fileNumber == 2) {
                    output.format("%d %.2f%n", input.nextInt(),
                             input.nextDouble());
                }

            }
            catch (FormatterClosedException formatterClosedException)
            {
                System.err.println("Error writing to file. Terminating.");
                break;
            }
            catch (NoSuchElementException elementException)
            {
                System.err.println("Invalid input. Please try again.");
                input.nextLine();

            }
            System.out.print("? ");
        }
    }
    public static void closeFileOutput()
    {
        if (output != null)
            output.close();
    }

    public static void openFileForReading(String fileName)
    {
        try
        {
            input = new Scanner(Paths.get(fileName));
        }
        catch (IOException ioException)
        {
            System.err.println("Error opening file. Terminating.");
            System.exit(1);
        }
    }

    public static void readRecords(int choice)
    {
        display(choice);
        try
        {
            while (input.hasNext()) // while there is more to read
            {
                getData(choice);
            }
        }
        catch (NoSuchElementException elementException)
        {
            System.err.println("File improperly formed. Terminating.");
        }
        catch (IllegalStateException stateException)
        {
            System.err.println("Error reading from file. Terminating.");
        }
    }
    public static void display(int fileNumber){
        if(fileNumber == 1){
            System.out.printf("%-10s%-12s%-12s%10s%n", "Account",
                    "First Name", "Last Name", "Balance");
        } else if (fileNumber == 2) {
            System.out.printf("%-10s%10s%n", "Account", "Balance");
        }
    }
    public static void getData(int choice){
        if(choice == 1){
            System.out.printf("%-10d%-12s%-12s%10.2f%n", input.nextInt(),
                    input.next(), input.next(), input.nextDouble());
        } else if (choice == 2) {
            System.out.printf("%-10d%10.2f%n",  input.nextInt(), input.nextDouble());
        }
    }
    public static void closeFileInput()
    {
        if (input != null)
            input.close();
    }
}