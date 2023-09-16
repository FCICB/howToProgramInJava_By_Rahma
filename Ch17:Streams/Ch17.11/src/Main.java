import java.util.Arrays;
import java.util.Comparator;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Invoice[] invoices = {
                new Invoice(83, "Electric sander", 7, 57.98),
                new Invoice(24, "Power saw", 18, 99.99),
                new Invoice(7, "Sledge hammer", 11, 21.50),
                new Invoice(77, "Hammer", 76, 11.99),
                new Invoice(39, "Lawn mower", 3, 79.50),
                new Invoice(68, "Screwdriver", 106, 6.99),
                new Invoice(56, "Jig saw", 21, 11.00),
                new Invoice(3, "Wrench", 34, 7.50)
        };
        display(1);
        Arrays.stream(invoices)
                .sorted(Comparator.comparing(Invoice::getPartDescription))
                .forEach(e -> displayInvoice(e));
        display(2);
        Arrays.stream(invoices)
                .sorted(Comparator.comparing(Invoice::getPrice))
                .forEach(e -> displayInvoice(e));
        display(3);
        Arrays.stream(invoices)
                .sorted(Comparator.comparing(Invoice::getQuantity))
                .map(inv -> String.format("%-20s %-10s", inv.getPartDescription(), inv.getQuantity()))
                .forEach(System.out::println);
        display(4);
        Arrays.stream(invoices)
                .sorted(Comparator.comparing(e->e.getPrice()*e.getQuantity()))
                .map(inv -> String.format("%-20s %-10s", inv.getPartDescription(), inv.getQuantity()*inv.getPrice()))
                .forEach(System.out::println);
        display(5);
        Arrays.stream(invoices)
                .filter(e -> e.getPrice()*e.getQuantity() > 200 && e.getPrice()*e.getQuantity() < 500)
                .sorted(Comparator.comparing(e->e.getPrice()*e.getQuantity()))
                .map(inv -> String.format("%-20s %-10s", inv.getPartDescription(), inv.getQuantity()*inv.getPrice()))
                .forEach(System.out::println);

    }
    public static void display(int num){
        System.out.println("\n=========================================================");
        switch (num){
            case 1:
                System.out.println("\nsorting the Invoice objects by PartDescription :\n");
                System.out.printf("%-15s%-20s%-10s%-10s\n","partNumber", "partDescription","quantity","price" );
                break;
            case 2:
                System.out.println("\nsorting the Invoice objects by Price :\n");
                System.out.printf("%-15s%-20s%-10s%-10s\n","partNumber", "partDescription","quantity","price" );
                break;
            case 3:
                System.out.println("\nmaping each Invoice to its PartDescription and Quantity,sort the results by Quantity, :\n");
                System.out.printf("%-20s%-10s\n", "partDescription","quantity");
                break;
            case 4:
                System.out.println("\nmap each Invoice to its PartDescription and the value of\n" +
                        "the Invoice (i.e., Quantity * Price). Order the results by Invoice value.:\n");
                System.out.printf("%-20s%-10s\n", "partDescription","Quantity * Price");
                break;
            case 5:
                System.out.println("\nselect the Invoice values in the range $200 to $500 :\n");
                System.out.printf("%-20s%-10s\n", "partDescription","Quantity * Price");
                break;

        }


    }
    public static void displayInvoice(Invoice invoice){
        System.out.printf("%-15d%-20s%-10d%-10f\n",invoice.getPartNumber(), invoice.getPartDescription(),invoice.getQuantity(),invoice.getPrice());
    }

}