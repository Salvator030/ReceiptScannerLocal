package dot;

import dot.ocrScanner.Tess;
import dot.receipt.Receipt;
import dot.receipt.ReceiptScanner;
import dot.excel.FastexcelHelper;

public class Main {

  public static void main(String[] args) {
   ReceiptScanner scanner = new ReceiptScanner();
   String inputImgPath = "src/main/resources/testBon5.jpg";
  scanner.setReceiptImage(inputImgPath);
   Receipt receipt = scanner.scannReceipt();

  FastexcelHelper eHelper = new FastexcelHelper();
 try {eHelper.writeExcel("test",receipt);}catch(Exception e){};

   
  }
}