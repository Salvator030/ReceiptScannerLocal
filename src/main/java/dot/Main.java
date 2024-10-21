package dot;

import dot.business.ocrScanner.Tess;
import dot.business.receipt.Receipt;
import dot.business.receipt.ReceiptScanner;
import javafx.application.Application;
import dot.business.excel.FastexcelHelper;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage; 

public class Main extends Application {


  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("javaFX/fxml/Main.fxml")); 
    primaryStage.setTitle("FXML-Beispiel");
    primaryStage.setScene(new Scene(root, 1024 , 1024));
    primaryStage.show(); 
  }
  public static void main(String[] args) {
    
    launch(args); 
//    ReceiptScanner scanner = new ReceiptScanner();
//    String inputImgPath = "src/main/resources/testBon5.jpg";
//   scanner.setReceiptImage(inputImgPath);
//    Receipt receipt = scanner.scannReceipt();

//   FastexcelHelper eHelper = new FastexcelHelper();
//  try {eHelper.writeExcel("test",receipt);}catch(Exception e){};

   
  }



  

}