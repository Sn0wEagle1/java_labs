package lab3;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopy {
    public static void main(String[] args) {
        String sourceFilePath = "C:\\Users\\Slavik\\Desktop\\lab\\Tasks\\src\\lab3\\source.txt";
        String destinationFilePath = "C:\\Users\\Slavik\\Desktop\\lab\\Tasks\\src\\lab3\\destination.txt";

        try (FileInputStream sourceStream = new FileInputStream(sourceFilePath);
             FileOutputStream destinationStream = new FileOutputStream(destinationFilePath)) {

            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = sourceStream.read(buffer)) != -1) {
                destinationStream.write(buffer, 0, bytesRead);
            }

            System.out.println("Файл успешно скопирован.");
        } catch (IOException e) {
            System.err.println("Ошибка при копировании файла: " + e.getMessage());
        }
    }
}
