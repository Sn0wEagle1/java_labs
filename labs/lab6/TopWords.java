package lab6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class TopWords {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\Slavik\\Desktop\\lab\\Tasks\\text.txt";
        File file = new File(filePath);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // создаем объект Map для хранения слов и их количества
        Map<String, Integer> wordCountMap = new HashMap<>();

        // читаем файл по словам и добавляем их в Map
        while (scanner.hasNext()) {
            String word = scanner.next().toLowerCase();
            word = word.replaceAll("[^a-zA-Zа-яА-Я]", "");
            wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
        }

        scanner.close();

        // создаем список из элементов Map
        List<Map.Entry<String, Integer>> list = new ArrayList<>(wordCountMap.entrySet());

        // сортируем список по убыванию количества повторений
        Collections.sort(list, (o1, o2) -> o2.getValue().compareTo(o1.getValue()));

        System.out.println("Top 10 words:");
        int count = 0;
        Iterator<Map.Entry<String, Integer>> iterator = list.iterator();
        while (iterator.hasNext() && count < 10) {
            Map.Entry<String, Integer> entry = iterator.next();
            System.out.println(entry.getKey() + ": " + entry.getValue());
            count++;
        }
    }
}
