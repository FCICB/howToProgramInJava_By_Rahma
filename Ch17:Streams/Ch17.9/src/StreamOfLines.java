import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
public class StreamOfLines
{
    public static void main(String[] args) throws IOException
    {
        Map<Character, Long> wordCounts =
                Files.lines(Paths.get("yarab"))
                        .flatMapToInt(CharSequence::chars)
                        .mapToObj(c -> (char) c)
                        .collect(Collectors.groupingBy(c -> c, Collectors.counting()));
        wordCounts.forEach((character, count) -> System.out.printf("%10s : %d%n", character, count));
    }
}