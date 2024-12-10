package dot.javaFX.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Collections;

import dot.asserts.EPurpose;
import dot.business.excel.FastexcelHelper;
import dot.business.handler.FileHandler;
import dot.business.receipt.Receipt;
import dot.business.receipt.ReceiptScanner;
import dot.javaFX.models.MainViewModel;
import dot.javaFX.objects.ReceiptsValuesTableRow;
import javafx.beans.binding.Bindings;
import javafx.stage.Stage;

public class MainInteractor {

    MainViewModel mainViewModel;
    Stage stage;
    ReceiptScanner receiptScanner;
    FileHandler fileHandler;
    FastexcelHelper excelHelper;
    Stage dialogStage;

    public MainInteractor(MainViewModel mainViewModel, Stage stage) {
        this.mainViewModel = mainViewModel;
        this.stage = stage;
        this.receiptScanner = new ReceiptScanner();
        fileHandler = new FileHandler();
        this.excelHelper = new FastexcelHelper(fileHandler);

        mainViewModel.inputFileSetProperty()
                .bind(Bindings.createBooleanBinding(() -> checkIfFileSet(), mainViewModel.inputFileProperty()));
        mainViewModel.filePathStringProperty()
                .bind(Bindings.createStringBinding(() -> setNewPath(), mainViewModel.inputFileProperty()));
        mainViewModel.tableRowListEmptyProperty().bind(
                Bindings.createBooleanBinding(() -> checkIsTableRowListEmpty(), mainViewModel.tableRowsProperty()));
        mainViewModel.receiptsListEmptyProperty().bind(
                Bindings.createBooleanBinding(() -> checkIsreceiptsListEmpty(), mainViewModel.receiptsListProperty()));

    }

    public void setMainViewModel(MainViewModel mainViewModel) {
        this.mainViewModel = mainViewModel;
    }

    public MainViewModel getMainViewModel() {
        return this.mainViewModel;
    }

    // Binding Mehtoden

    private boolean checkIfFileSet() {
        if (mainViewModel.getInputFile() != null) {
            return true;
        }
        return false;
    }

    private String setNewPath() {
        if (mainViewModel.getInputFile() != null) {
            return mainViewModel.getInputFile().getAbsolutePath();
        }
        return "";
    }

    private boolean checkIsTableRowListEmpty() {
        return mainViewModel.getTableRows().isEmpty();
    }

    private boolean checkIsreceiptsListEmpty() {
        return mainViewModel.getReceiptsList().isEmpty();

    // hilfs mehtoden

    } private void setDefaulReceipttValues(Receipt receipt) {
        if (receipt.getDate() == null) {
            receipt.setDate("nicht lesbar");
        }
        if (receipt.getShopName() == null) {
            receipt.setShopName("nicht lesbar");
        }
        if (receipt.getSumm() == null) {
            receipt.setSumm("nicht lesbar");
        }

        if (receipt.getPurpose() == null) {
            receipt.setPurpose(EPurpose.SONSTIGES);
        }
    }

    // Mehtoden um mit dem Model zu interargieren

    public void setInputFileInModel( File file) {
        if (file != null) {
            mainViewModel.setInputFile(file);
        }
    }

    // Tested
    public Receipt scannReceipt() {
        receiptScanner.setReceiptImage(mainViewModel.getInputFile());
        mainViewModel.setInputFile(null);
       return receiptScanner.scannReceipt(receiptScanner.scanImage());

    }

      public void toggleScanning() {
        mainViewModel.setScanning(!mainViewModel.isScanning());
    }

      // Tested
    protected void addScannenReciptTotableRows() {
        setDefaulReceipttValues(mainViewModel.getScannedReceipt());
        mainViewModel.addReceiptsList(mainViewModel.getScannedReceipt());
        ReceiptsValuesTableRow row = new ReceiptsValuesTableRow(mainViewModel.getTableRows().size(),
                mainViewModel.getScannedReceipt(), null);
        mainViewModel.addTablesRows(row);
        Collections.sort(mainViewModel.getTableRows());
        mainViewModel.setScannendReceipt(null);
    }

    public void setReceiptValues(Receipt receipt) {
        mainViewModel.setScannendReceipt(receipt);
    }

    public Receipt getReceiptValues(){
        return mainViewModel.getScannedReceipt();
    }

    public void clearReceiptValues(){
        mainViewModel.setScannendReceipt(null);
    }

    public void saveExcelInDirectory(File directory) {
       mainViewModel.setOutputDirectory(directory);
        fileHandler.setOutputFolder(directory);
        try {

            excelHelper.writeReceiptsToExcelFiles(mainViewModel.getTableRows());
            mainViewModel.tableRowsProperty().clear();
        } catch (NumberFormatException | IOException | ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }



    

}
