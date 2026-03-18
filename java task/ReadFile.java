import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.stream.Stream;

public class ReadFile {
    public static void main(String[] args) {
        String fileName = "user.txt";

        // Files.lines returns a Stream<String>
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stream.forEach(System.out::println);
        } catch (IOException e) {
            System.err.println("Could not read file: " + e.getMessage());
        }
    }
}