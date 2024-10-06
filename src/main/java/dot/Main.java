package dot;

import dot.ocrScanner.Tess;
import dot.receipt.ReceiptScanner;

public class Main {

  public static void main(String[] args) {
   ReceiptScanner scanner = new ReceiptScanner();
   String inputImgPath = "src/main/resources/testBon5.jpg";
  scanner.setReceiptImage(inputImgPath);
   System.out.println(scanner.scannReceipt());

   
  }
}