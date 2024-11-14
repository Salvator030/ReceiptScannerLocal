package dot.javaFX.models;

import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableListValue;

import java.io.File;

import dot.business.receipt.Receipt;
import dot.javaFX.objects.ReceiptsValuesTableRow;
import javafx.beans.Observable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class MainViewModel {
    

    private final StringProperty filePathSting = new SimpleStringProperty(null);
    private final ObjectProperty<File> ecxelFile = new SimpleObjectProperty<File>(null);
    private final BooleanProperty isEcxelFileSet = new SimpleBooleanProperty(false);
    private final ObservableListValue<ReceiptsValuesTableRow> tableRows = new SimpleListProperty<ReceiptsValuesTableRow>(null);
    private final BooleanProperty isTableRowsListEmty = new SimpleBooleanProperty();
  private final ObjectProperty<Receipt> scannedReceipt = new SimpleObjectProperty<Receipt>();} 