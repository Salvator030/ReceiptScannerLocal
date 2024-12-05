
package dot.business.handler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Set;

public class FileHandler {

    private final String defaultFileName = "Kassenbons-Abrechnung";
    private final String outputFileType = ".xlsx";
    private final String tessDataPath = "src/main/resources/tessdata";
    private final File documentsDirectory = new File(
            System.getProperty("user.home") + File.separator + "Documents" + File.separator);
   
            private File inputFile;
    private String outputFileName;
    private String outputFileDate;

    private File inputFolder;
    private File outputFolder;

    public FileHandler() {
        inputFile = null;
        inputFolder = documentsDirectory;
        outputFileName = defaultFileName;
        outputFolder = null;
        outputFileDate = null;

    }

    public void setInputFile(String filePath) {
        this.inputFile = new File(filePath);
    }

    public void setInputFile(File inputFile) {
        this.inputFile = inputFile;
    }

    public void setOutputFolder(File folder) {
        if (folder != null) {
            this.outputFolder = folder;
            System.out.println(outputFolder);
        } else {
            this.outputFolder = documentsDirectory;
        }
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

    public String getOutputFileDate() {
        return outputFileDate;
    }

    public void setOutputFileName(String outputFileName) {
        this.outputFileName = outputFileName;
    }

    public void setOutputFileDate(String outputFileDate) {
        this.outputFileDate = outputFileDate;
    }

    public Path getFullOuputFilePath(String date) {
        Path path = Path.of( this.outputFolder.toString(), outputFileName + "-" + date.replace(".", "") + outputFileType);
        return path;
    }

    public Path getFullInputFilePath(String date) {
        Path path = Path.of(inputFolder.toString(), outputFileName + "-" + date.replace(".", "") + outputFileType);
        return path;
    }

    public boolean checkExistFile(Path path) {
        if (!Files.exists(path)) {
            File file = new File(path.toString());
            try {
                file.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return false;
        }
        return true;
    }

    public String fileLocationToString(File folder, String fileName) {
        String path = folder.getAbsolutePath();
        return path.substring(0, path.length() - 1) + File.separator + fileName;
    }

    public HashMap<String, Path> getExcelFilesPathesToReadIn(Set<String> datesKeys) {
        HashMap<String, Path> datePathMap = new HashMap<String, Path>();
        for (String date : datesKeys) {
            datePathMap.put(date, getFullInputFilePath(date));
        }
        return datePathMap;
    }

    public HashMap<String, Path> getExcelFilesPathesToWriteOut(Set<String> datesKeys) {
        HashMap<String, Path> datePathMap = new HashMap<String, Path>();
        for (String date : datesKeys) {
            datePathMap.put(date, getFullOuputFilePath(date));
        }
        return datePathMap;
    }

}
