import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileReadUpdate {
    public static void main(String[] args) {
        String filepath = "example.txt";
        String content = "hello welcome";

        File file = new File(filepath);

        // Using try-with-resources to automatically close Scanner and FileWriter
        try (Scanner scanner = new Scanner(file)) {
            System.out.println("File content:");
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return;  // Exit if file can't be read
        }

        try (FileWriter writer = new FileWriter(file, true)) {
            writer.write("\n" + content);
            System.out.println("File updated successfully!");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}

