package linecounter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class LineCounter extends Application implements Initializable {

    private final int BG_WIDTH = 595;
    private final int BG_HEIGHT = 355;
    public int sum = 0;

//<editor-fold defaultstate="collapsed" desc="Setting the scene.">
    @Override
    public void start(Stage stage) throws Exception {
        ImageView imgView = new ImageView("open.png");
        imgView.setFitWidth(20);
        imgView.setFitHeight(20);
        ImageView background = new ImageView("2.jpg");
        background.setFitWidth(BG_WIDTH);
        background.setFitHeight(BG_HEIGHT);
        Menu file = new Menu("File");
        MenuItem item = new MenuItem("Open Files", imgView);
        file.getItems().addAll(item);
        //Creating a File chooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Multiple Files");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("All Files", "*.*"));
        //Creating a menu bar and adding menu to it.
        MenuBar menuBar = new MenuBar(file);
        AnchorPane pane = new AnchorPane(background);
        TextArea textArea = new TextArea("\n\n\n\n\t\t\t\t\t\t\t(c) Balazs Szanka\n\t\t\t\t\t\thttps://github.com/bszanka");
        textArea.setEditable(false);
        VBox vbox = new VBox(textArea);
        vbox.setLayoutX(60);
        vbox.setLayoutY(60);
        Group root = new Group(pane, menuBar, vbox);
        Scene scene = new Scene(root, BG_WIDTH, BG_HEIGHT, Color.LIGHTGREY);
        //Adding action on the menu item
        item.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                sum = 0;
                textArea.clear();
                //Opening a dialog box and put the output in a list
                List<File> output = fileChooser.showOpenMultipleDialog(stage);
                for (int i = 0; i < output.size(); i++) {
                    try {
                        textArea.appendText(countLines(output.get(i), i));

                    } catch (IOException ex) {
                        Logger.getLogger(LineCounter.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                textArea.appendText("\u03A3 = " + sum);

            }

        });
        stage.setResizable(false);
        stage.getIcons().add(new Image("harold.png"));
        stage.setTitle("PalletManager");
        stage.setScene(scene);
        stage.show();
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="The magic happens here.">
    public String countLines(File file, int i) throws FileNotFoundException, IOException {

        FileInputStream fileStream = new FileInputStream(file);
        InputStreamReader input = new InputStreamReader(fileStream);
        BufferedReader reader = new BufferedReader(input);

        String line;

        // Initializing counters
        int lineCount = 0;

        // Reading line by line from the
        // file until a null is returned
        while ((line = reader.readLine()) != null) {
            lineCount++;
        }

        lineCount -= 2;
        String name = file.getName();
        name = name.substring(0, 15);
        sum += lineCount;
        String res = name + " = " + lineCount + "\n";
        return res;

    }
//</editor-fold>

    public static void main(String[] args) throws IOException {
        launch(args);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
