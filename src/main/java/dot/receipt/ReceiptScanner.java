package dot.receipt;

import dot.ocrScanner.Tess;
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

    public Receipt scannReceipt() {
        String result = tess.tess4jNormal(image);
        Receipt receipt = result != null
                ? new Receipt(itemScanner.getDate(result), itemScanner.getStoreName(result),
                        itemScanner.getTotalSumm(result))
                : null;

        return receipt;
    }

}
