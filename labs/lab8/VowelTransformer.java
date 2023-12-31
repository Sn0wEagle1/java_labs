import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class VowelTransformer {

    @DataProcessor
    public static void transform(String data) {
        List<String> words = Arrays.asList(data.split(" "));
        List<String> transformedWords = words.stream()
                .map(word -> {
                    if (isVowel(word.charAt(0))) {
                        int originalLength = word.length();
                        char vowel = word.charAt(0);
                        return String.valueOf(vowel).repeat(originalLength);
                    } else {
                        return word;
                    }
                })
                .collect(Collectors.toList());
        System.out.println("Transformed Words: " + transformedWords);
        DataManager.processedData.addAll(transformedWords);
    }

    @DataProcessor
    public static void anotherTransformation(String data) {
        List<String> words = Arrays.asList(data.split(" "));
        List<String> transformedWords = words.stream()
                .map(word -> word + "_processed")
                .collect(Collectors.toList());
        System.out.println("Another Transformed Words: " + transformedWords);
        DataManager.processedData.addAll(transformedWords);
    }

    private static boolean isVowel(char c) {
        return "AEIOUaeiou".indexOf(c) != -1;
    }
}
