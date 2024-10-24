package dot;

import dot.business.ocrScanner.Tess;
import dot.business.receipt.Receipt;
import dot.business.receipt.ReceiptScanner;
import dot.javaFX.controller.KassenbonMainController;
import javafx.application.Application;

import java.lang.ModuleLayer.Controller;

import dot.business.excel.FastexcelHelper;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage; 

public class Main extends Application {


  @Override
  public void start(Stage primaryStage) throws Exception {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("javaFX/fxml/Kassenbon-Main.fxml"));
    Parent root = loader.load(); 
    KassenbonMainController  controller = loader.getController();
    primaryStage.setTitle("FXML-Beispiel");
    primaryStage.setScene(new Scene(root, 640 , 400));
    controller.setStage(primaryStage);
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