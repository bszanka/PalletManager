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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;


public class LineCounter extends Application implements Initializable {
    
    @FXML
    private ScrollPane pane1;
    @FXML
    private Label label1;
    
    @Override
    public void start(Stage stage) throws Exception {  
      Parent root2 = FXMLLoader.load(getClass().getResource("FXML.fxml"));
      ScrollPane pane = pane1;
      Label label = label1;
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
      //Adding action on the menu item
      item.setOnAction(new EventHandler<ActionEvent>() {
         public void handle(ActionEvent event) {
            //Opening a dialog box
             List<File> output = fileChooser.showOpenMultipleDialog(stage);
             for(int i = 0; i < 30;i++){
             try {
                 countLines(output.get(i), i);
             } catch (IOException ex) {
                 Logger.getLogger(LineCounter.class.getName()).log(Level.SEVERE, null, ex);
             }
             }
      }

          private void countLines(File file, int i) throws FileNotFoundException, IOException{
        
		FileInputStream fileStream = new FileInputStream(file); 
		InputStreamReader input = new InputStreamReader(fileStream); 
		BufferedReader reader = new BufferedReader(input); 
		
		String line; 
		
		// Initializing counters 
                int lineCount = 0;
		
		// Reading line by line from the 
		// file until a null is returned 
		while((line = reader.readLine()) != null) 
		{ 
                    lineCount++;
		} 
                
                lineCount -= 2;
                
		System.out.println(i+1 + " = " + lineCount);
        
    }
      });
      //Creating a menu bar and adding menu to it.
      MenuBar menuBar = new MenuBar(file);
      Group root = new Group(menuBar);
      Scene scene = new Scene(root, 595, 355, Color.LIGHTGREY);
      stage.setTitle("File Chooser Example");
      stage.setScene(scene);
      stage.show();
    }

    public static void main(String[] args) throws IOException {
                launch(args);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
