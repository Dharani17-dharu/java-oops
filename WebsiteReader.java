import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class WebsiteReader {
    public static void main(String[] args) throws IOException {
        String websiteURL = "https://www.kamarajengg.edu.in/";
        URL url = new URL(websiteURL);
        Scanner scanner = new Scanner(url.openStream());

        while (scanner.hasNextLine()) {
            System.out.println(scanner.nextLine());
        }
        scanner.close();
    }
}
