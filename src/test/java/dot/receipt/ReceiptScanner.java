package dot.receipt;

import dot.ocrScanner.Tess;
import dot.receipt.ItemScanner;

import java.io.File;

public class ReceiptScanner {

    Tess tess;
    ItemScanner itemScanner;
    File image;

    public ReceiptScanner() {
        tess = new Tess();
        itemScanner = new ItemScanner();
        image = null;
    }

    public void setReceiptImage(String path) {
        image = new File(path);
    }

    public String scannReceipt() {
        String result = tess.tess4jNormal(image);
        String output;
        output = result != null
                ? "name: " + itemScanner.getStoreName(result) + "\nSumm: " + itemScanner.getTotalSumm(result)
                        + "\nDate: " + itemScanner.getDate(result)
                : "null";
        return output;
    }

}
