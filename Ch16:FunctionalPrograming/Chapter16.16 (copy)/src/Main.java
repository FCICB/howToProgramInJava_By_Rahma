import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Map<String, Integer> map = new TreeMap<>();
        getwords(map);
        displayWords(map);
        }

    private static void displayWords(Map<String, Integer> map) {
        System.out.printf(" The number Of duplicate words in the Sentence \n");
        System.out.printf("%-10s%10s\n","WORD","DUPLICATE");
        for (String key : map.keySet()){
            if(map.get(key) != 1){
                System.out.printf("%-10s%10s%n", key, map.get(key));
            }
        }
    }

    private static void getwords(Map<String, Integer> map) {
        System.out.println("Enter Sentence please : ");
        Scanner input = new Scanner(System.in);
        String  lineOfString = input.nextLine();
        String[] words = lineOfString.split(" ");
        for (String word:words) {
            if(map.containsKey(word.toLowerCase())){
                map.put(word,(map.get(word))+1);
            }else {
                map.put(word,1);
            }

        }
    }
}
