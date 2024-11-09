package dot.business.ocrScanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import org.junit.jupiter.api.Test;

import dot.business.receipt.ExampleReceiptStrings;

public class Tess_Test {
    private Tess tess = new Tess();

    @Test
    public void test_TessNormal_withExampleReceiptString(){
        assertEquals(ExampleReceiptStrings.getExampleReceiptString1(), tess.tess4jNormal(new File("D:/Develop/Repositorys/java_ocr_test/src/main/resources/testBon5.jpg")));
    }
}
