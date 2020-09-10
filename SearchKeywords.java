import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.File;
import java.util.HashMap;
import java.util.Arrays;

public class SearchKeywords {
    public static void main(String[] args) {

        if (args.length < 3 || args.length > 5) {
            System.out.println(
                    "Usage: SearchWord <folderName> <fileExtension> <keyword1> (optional): <keyword2> <keyword3>");
            System.out.println(Arrays.toString(args));
            return;
        }

        File file = new File(args[0]);
        String extension = args[1];
        String[] keywords = getKeywords(args);

        String[] files = getAllFiles(file, extension);

        searchThroughFiles(files, keywords);

    }

    /**
     * Handles calling the countWordsInFile function on each file with the given
     * keywords. fileResults map is a HashMap consisting of files as the keys and a
     * array of the number of times each keyword appears as the values.
     * 
     * @param files    A list of the files to search.
     * @param keywords A list of the keywords to match.
     */

    private static void searchThroughFiles(String[] files, String[] keywords) {
        int noOfFiles = files.length;
        HashMap<String, int[]> fileResults = new HashMap<String, int[]>();
        for (int i = 0; i < noOfFiles; i++) {
            try {
                fileResults.put(files[i], countWordsInFile(files[i], keywords));
            } catch (Exception e) {
                System.out.println("File not found: " + files[i]);
            }
        }

        for (int i = 0; i < files.length; i++) {
            System.out.println(files[i]);
            int[] test = fileResults.get(files[i]);
            for (int j = 0; j < test.length; j++) {
                System.out.println(keywords[j] + ": " + test[j]);
            }
        }
    }

    /**
     * Gets the amount of times the given search word appears in the given file to
     * parse.
     * 
     * @param filetoParse The file to parse.
     * @param searchWords The keyword(s) that should be searched for.
     * @return An array with one index per keyword, each index containing the amount
     *         of times the keyword appears.
     */

    private static int[] countWordsInFile(String fileToParse, String[] searchWords) throws FileNotFoundException {

        int[] wordCounter = new int[searchWords.length];

        FileReader searchFile = new FileReader(fileToParse);
        Scanner sc = new Scanner(searchFile);
        while (sc.hasNextLine()) {
            Scanner check = new Scanner(sc.nextLine());
            while (check.hasNext()) {
                String word = check.next();
                for (int i = 0; i < searchWords.length; i++) {
                    if (matchSearchWord(word, searchWords[i]))
                        wordCounter[i]++;
                }

            }
        }
        sc.close();
        return wordCounter;

    }

    /**
     * A function that matches two words up against eachother, with some handling.
     * 
     * @param word      The single word to match.
     * @param matchWord The list of keywords to match.
     * @return Returns true if the matchWord was the same as word. Returns false
     *         otherwise
     */
    private static boolean matchSearchWord(String word, String matchWord) {
        // The regex is to match words even though they contain special characters.
        // If this wasn't done the function would not match e.g. Java to "Java"
        return word.toLowerCase().replaceAll("[^a-zA-Z0-9]", "").equals(matchWord.toLowerCase());
    }

    /**
     * Finds the keywords from the args and returns them in a list. This function is
     * very program-specific and assumes that the program is used as handled in the
     * main funcion: Usage: SearchWord <folderName> <fileExtension> <keyword1>
     * <keyword2> <keyword3>
     * 
     * @param args The arguments that should be searched through.
     * @return The list of the arguments, with the correct size.
     */
    private static String[] getKeywords(String[] args) {
        String[] keywords = new String[args.length - 2];
        for (int i = 2; i < args.length; i++) {
            keywords[i - 2] = args[i].toLowerCase();
        }
        return keywords;
    }

    /**
     * Gets all files with the specified extension in the directory specified. Does
     * not return any directories or files in sub-directories.
     * 
     * @param directory The directory to search in.
     * @param extension The extension to match for.
     * @return A string array of all the paths of all files in the directory
     *         specified.
     */
    private static String[] getAllFiles(File directory, String extension) {
        // TODO: extension has to be .txt, .doc or .docx
        File[] files = directory.listFiles(File::isFile);
        String[] filepaths = new String[files.length];
        String regex = "^.*" + extension + "$";
        for (int i = 0; i < filepaths.length; i++) {
            if (files[i].getPath().matches(regex)) {
                filepaths[i] = files[i].getPath();
            }
        }
        System.out.println(
                "The folder " + directory + " contains " + filepaths.length + " number of " + extension + " files.");
        return filepaths;
    }

}