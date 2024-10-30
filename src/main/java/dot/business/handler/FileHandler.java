package dot.business.handler;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class FileHandler {

    private File inputFile;
    private File outputFolder;
    private String outputFileName;
    private String outputFileDate;
    private final String outputFileType = ".xlsx";
    private final String tessDataPath = "src/main/resources/tessdata";
    private final File documentsDirectory = new File(
            System.getProperty("user.home") + File.separator + "Documents" + File.separator);

    public void setInputFile(String filePath) {
        this.inputFile = new File(filePath);
    }

    public void setInputFile(File inputFile) {
        this.inputFile = inputFile;
    }

    public void setOutputFolder(File outputFolder) {
        this.outputFolder = outputFolder;
    }

    public File getInputFile() {
        return inputFile;
    }

    public File getOutputFolder() {
        return outputFolder;
    }

    public String getTessDataPath() {
        return tessDataPath;
    }

    public File getDocumentsDirectory() {
        return documentsDirectory;
    }

        public String getOutputFileName() {
        return outputFileName;
    }

    public void setOutputFileNamePrefix(String outputFileName) {
        this.outputFileName = outputFileName;
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