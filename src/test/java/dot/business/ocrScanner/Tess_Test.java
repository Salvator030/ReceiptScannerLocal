package dot.business.ocrScanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.jupiter.api.Test;

import dot.business.receipt.ExampleReceiptStrings;

public class Tess_Test {
    private Tess tess = new Tess();

    private File testFile = new File("src/test/resources/ocrScannerTestFile.jpg");

    @Test
    public void test_TessNormal_withExampleReceiptString() {
         String expected = "*  * Kundenbeleg * *\n" +
                "Blume2000 SE\n" +
                "\n" +
                "Filiale 1140\r\n" +
                "\n" +
                "Marzahner Promenade 1 A\n" +
                "12679 Berlin\n" +
                "\n" +
                "Danke für Ihren Einkauf!\n";

                String actual = tess.tess4jNormal(testFile);

                assertTrue(actual != null);
                assertTrue(actual.contains("Blume2000"));
                assertTrue(actual.contains("Filiale 1140"));
                assertTrue(actual.contains("Marzahner"));
                assertTrue(actual.contains("Danke für Ihren Einkauf!"));
    }
}
