package dot.business.receipt;

import dot.business.ocrScanner.Tess;
import java.io.File;
import java.util.HashMap;

public class ReceiptScanner {

    Tess tess;
    ItemScanner itemScanner;
    File image;

    public ReceiptScanner() {
        tess = new Tess();
        itemScanner = new ItemScanner();
        image = null;
    }

    public void setReceiptImage(File file) {
        image = file;
    }

    public String scanImage(){
        return tess.tess4jNormal(image);
    }

    public Receipt scannReceipt( String result) {
      
        String date = null;
        String name = null;
        Double summ = null;

        String[] resultArray = result.split("\n");
        for (String resultString : resultArray) {

            if (date == null) {
                date = itemScanner.getDate(resultString);
            }
            if (name == null) {
                name = itemScanner.getStoreName(resultString);
            }
            if (summ == null) {
                summ = itemScanner.getTotalSumm(resultString);
            }

        }
        Receipt receipt = new Receipt(date != null ? date : "", name != null ? name : "",
                summ != null ? summ : 0);

        return receipt;
    }

}
