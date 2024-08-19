package dot.test.handler;

import java.io.File;

class FileHandeler {

    static File inputFile;
    static String tessDataPath = "src/main/resources/tessdata";

    public void setInputFile(String filePath){
        inputFile = new File(filePath);
    }
}