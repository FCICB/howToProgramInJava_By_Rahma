import java.util.*;
import java.util.stream.Collectors;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        System.out.println("Enter Sentence please : ");
        Scanner input = new Scanner(System.in);
        String  lineOfString = input.nextLine();
        String[] words=lineOfString.split(" ");
        Arrays.stream(words)
                .map(String::toLowerCase)
                .distinct()
                .sorted()
                .forEach(e -> System.out.printf("%s ",e));

    }
}