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
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class LineCounter extends Application implements Initializable {

//<editor-fold defaultstate="collapsed" desc="Setting the scene.">
    @Override
    public void start(Stage stage) throws Exception {
        ImageView imgView = new ImageView("harold.png");
        imgView.setFitWidth(20);
        imgView.setFitHeight(20);
        Menu file = new Menu("File");
        MenuItem item = new MenuItem("Open Multiple Files", imgView);
        file.getItems().addAll(item);
        //Creating a File chooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Multiple Files");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("All Files", "*.*"));
        
        //Creating a menu bar and adding menu to it.
        MenuBar menuBar = new MenuBar(file);
        TextArea textArea = new TextArea();
        VBox vbox = new VBox(textArea);
        vbox.setLayoutX(60);
        vbox.setLayoutY(60);
        Group root = new Group(menuBar, vbox);
        Scene scene = new Scene(root, 595, 355, Color.LIGHTGREY);
        
        //Adding action on the menu item
        item.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                //Opening a dialog box and put the output in a list
                List<File> output = fileChooser.showOpenMultipleDialog(stage);
                for (int i = 0; i < output.size(); i++) {
                    try {
                        textArea.appendText(countLines(output.get(i), i));
                        
                    } catch (IOException ex) {
                        Logger.getLogger(LineCounter.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            
        });
        
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
        
        String res = i + 1 + " = " + lineCount + "\n";
        return res;
        
    }
//</editor-fold>

    public static void main(String[] args) throws IOException {
        launch(args);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
