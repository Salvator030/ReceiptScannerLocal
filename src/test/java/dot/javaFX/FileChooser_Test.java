package dot.javaFX;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import org.testfx.matcher.control.LabeledMatchers;

import dot.Main;
import javafx.stage.Stage;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isNotNull;

public class FileChooser_Test extends ApplicationTest {

   @Override
   public void start(Stage stage) throws Exception {
      new Main().start(stage);
   }

   @Test
   void containFileChooserViewElements() {
      verifyThat("#filePathText", isNotNull());
      verifyThat("#selectReceiptBtn", LabeledMatchers.hasText("Kassenbon wählen"));
      

   }

}
