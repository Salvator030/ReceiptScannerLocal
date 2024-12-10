package dot.business.handler;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;

public class FileHandler_Test {
    
    public final FileHandler fileHandler = new FileHandler();

    /*
    @Test
    public void isDocumentsDirectory_WinDocumentDirectory(){
        assertEquals("C:\\Users\\Admin\\Documents", fileHandler.getDocumentsDirectory().getAbsolutePath());
    } 
 */
    @Test
    public void fileLocationToString_createFilePathString(){
        assertEquals("C:\\Users\\Admin\\Document\\test.txt", fileHandler.fileLocationToString(new File("C:\\Users\\Admin\\Documents\\"),"test.txt"));
    }

    @Test
    public void getFullOutputFilePath_withefaultValues(){
        fileHandler.setOutputFolder(new File("C:\\Users\\Admin\\Documents\\"));
        assertEquals(Path.of("C:\\Users\\Admin\\Documents\\Kassenbons-Abrechnung-012000.xlsx"), fileHandler.getFullOuputFilePath("01.2000"));
    }
}
