import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class SearchWord {
  public static void main(String[] args) {

    if (args.length != 2) {
        System.out.println("Usage: SearchWord <filename> <keyword>");
        return;
    }

    String filename = args[0];
    String searchw = args[1];
    int wordCounter = 0;

    try {
        FileReader file = new FileReader(filename);
        Scanner sc = new Scanner(file);
        while(sc.hasNextLine()) {
            Scanner check = new Scanner(sc.nextLine());
            while(check.hasNext()) {
                if(check.next().toLowerCase().replaceAll("[^a-zA-Z0-9]", "").equals(searchw.toLowerCase())) {
                    wordCounter++;
                }
            }
        }
        sc.close();
    } 
    catch (FileNotFoundException e) {
        System.out.println(e);
        return;
    }
    
    System.out.println("The keyword '" + searchw + "' appeared " + wordCounter + " times in the " + filename + " file.");

  }
}