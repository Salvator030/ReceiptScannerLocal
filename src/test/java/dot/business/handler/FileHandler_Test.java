package dot.business.handler;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FileHandler_Test {
    
    public final FileHandler fileHandler = new FileHandler();

    @Test
    public void isDocumentsDirectory_WinDocumentDirectory(){
        assertEquals("C:\\Users\\Admin\\Documents", fileHandler.getDocumentsDirectory().getAbsolutePath());
    } 
}
