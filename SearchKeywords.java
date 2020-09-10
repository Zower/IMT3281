import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;

public class SearchKeywords {
    public static void main(String[] args) {

        // if (args.length < 3 || args.length > 5) {
        // System.out.println("Usage: SearchWord <folderName> <fileExtension> <keyword1>
        // <keyword2> <keyword3>");
        // return;
        // }

        File file = new File(args[0]);

        String[] files = getAllFiles(file);

        try {
            int count = countWordsInFile(files[1], "Java");
            System.out.println(count);
        } catch (Exception e) {
            System.out.println("File not found");
        }

        // System.out.println(
        // "The keyword '" + searchWord + "' appeared " + wordCounter + " times in the "
        // + fileToParse + " file.");

    }

    /**
     * Gets the amount of times the given search word appears in the given file to
     * parse.
     * 
     * @param filetoParse The file to parse.
     * @param searchWord  The word that should be searched for.
     * @return An int denoting the amount of times the word appeared in the given
     *         file.
     */

    private static int countWordsInFile(String fileToParse, String searchWord) throws FileNotFoundException {

        int wordCounter = 0;

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

        return wordCounter;

    }

    /**
     * Prints relative, absolute and cononical path of the file given, whether the
     * path exists, and if it is a directory.
     * 
     * @param file The file to look for.
     */
    private static void printPaths(File file) throws IOException {
        System.out.println("Exists: " + file.exists());
        System.out.println("Is directory: " + file.isDirectory());
        System.out.println("Absolute Path: " + file.getAbsolutePath());
        System.out.println("Canonical Path: " + file.getCanonicalPath());
        System.out.println("Path: " + file.getPath());
    }

    /**
     * Gets all files in the directory specified. Does not return any directories or
     * files in sub-directories.
     * 
     * @param directory The directory to search in.
     * @return A string array of all the relative paths of all files in the
     *         directory specified.
     */
    private static String[] getAllFiles(File directory) {
        File[] files = directory.listFiles(File::isFile);
        String[] filepaths = new String[files.length];

        for (int i = 0; i < filepaths.length; i++) {
            filepaths[i] = files[i].getPath();
        }

        return filepaths;
    }

}