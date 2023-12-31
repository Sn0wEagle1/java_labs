import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class DataManager {

    private final List<Object> dataProcessors = new ArrayList<>();
    static List<String> processedData = new ArrayList<>();

    public void registerDataProcessor(Object processor) {
        dataProcessors.add(processor);
    }

    public List<String> loadData(String source) throws IOException {
        Path path = Path.of(source);
        return Files.readAllLines(path);
    }

    public void processData() {
        ExecutorService executorService = Executors.newFixedThreadPool(dataProcessors.size());

        for (Object dataProcessor : dataProcessors) {
            Arrays.stream(dataProcessor.getClass().getDeclaredMethods())
                    .filter(method -> method.isAnnotationPresent(DataProcessor.class))
                    .forEach(method -> {
                        try {
                            List<String> data = loadData("C:\\Users\\Slavik\\Desktop\\lab\\Tasks\\text.txt");
                            data.parallelStream()  // Используем parallelStream для параллельной обработки
                                    .forEach(line -> {
                                        try {
                                            method.invoke(dataProcessor, line);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
        }

        executorService.shutdown();
    }

    public void saveData(String destination) throws IOException {
        Path path = Path.of(destination);
        Files.write(path, processedData);
        System.out.println("Saved processed data to " + destination);
    }

    public static void main(String[] args) throws IOException {
        DataManager dataManager = new DataManager();
        dataManager.registerDataProcessor(new WordFilter());
        dataManager.registerDataProcessor(new VowelTransformer());
        dataManager.processData();
        dataManager.saveData("processed_data.txt");
    }
}
