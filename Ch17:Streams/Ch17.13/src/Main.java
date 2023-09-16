import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    static int MAX = 26;

    public static void main(String[] args) {
        List<Character> chars = new ArrayList<>() ;

            char []alphabet = { 'a', 'b', 'c', 'd', 'e', 'f', 'g',
                    'h', 'i', 'j', 'k', 'l', 'm', 'n',
                    'o', 'p', 'q', 'r', 's', 't', 'u',
                    'v', 'w', 'x', 'y', 'z' };

            for (int i = 0; i < 30; i++)
               chars.add(alphabet[(int) (Math.random() * 100 % MAX)]);
        System.out.println("the List in ascending order.");
            chars.stream()
                    .sorted()
                    .forEach((e) -> System.out.printf("%c ",e));
        System.out.println("\n\nthe List in Descending order.");
        chars.stream()
                .sorted(Comparator.reverseOrder())
                .forEach((e) -> System.out.printf("%c ",e));
        System.out.println("\n\nthe List in ascending order with duplicates removed");
        chars.stream()
                .distinct()
                .sorted()
                .forEach((e) -> System.out.printf("%c ",e));
    }


}