import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class SearchKeywords {
    public static void main(String[] args) {

        if (args.length < 3 || args.length > 5) {
            System.out.println("Usage: SearchWord <folderName> <fileExtension> <keyword1> <keyword2> <keyword3>");
            return;
        }

        String filename = args[0];
        String searchw = args[1];

        SearchFileForWords(filename, searchw);

    }

    public static void SearchFileForWords(String fileToParse, String searchWord) {

        int wordCounter = 0;

        try {
            FileReader searchFile = new FileReader(fileToParse);
            Scanner sc = new Scanner(searchFile);
            while (sc.hasNextLine()) {
                Scanner check = new Scanner(sc.nextLine());
                while (check.hasNext()) {
                    if (check.next().toLowerCase().replaceAll("[^a-zA-Z0-9]", "").equals(searchWord.toLowerCase())) {
                        wordCounter++;
                    }
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
            return;
        }

        System.out.println(
                "The keyword '" + searchWord + "' appeared " + wordCounter + " times in the " + fileToParse + " file.");

    }

}