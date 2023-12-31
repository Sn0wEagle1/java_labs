import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WordFilter {

    @DataProcessor
    public static void process(String data) {
        List<String> words = Arrays.asList(data.split(" "));
        List<String> filteredWords = words.stream()
                .sorted()
                .collect(Collectors.toList());
        System.out.println("Filtered Words: " + filteredWords);
        DataManager.processedData.addAll(filteredWords);
    }
}