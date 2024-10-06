package dot.handler;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class FileHelper {

    static File inputFile;
    static String tessDataPath = "src/main/resources/tessdata";

    public static void setInputFile(String filePath){
        inputFile = new File(filePath);
    }
    
        public static void writeToFile(String name, String res) {
        int counter = 1;
        String pathString = ("src/main/resources/out" + name + ".txt");
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(new FileWriter(pathString));
            printWriter.write(res);
        } catch (Exception e) {
        } finally {
            if (printWriter != null) {
                printWriter.flush();
                printWriter.close();
            }
        }
    }
    
}