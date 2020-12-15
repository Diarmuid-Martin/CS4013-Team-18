import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.event.ActionEvent; 
import javafx.event.EventHandler; 

 /**
 * @author Ellen McWey, Diarmuid Martin
 * @version 9/12/2020
 */

public class PropData
{
    private static TextField tfEircode = new TextField();
    
     public static String getTfEircode() {
        return tfEircode.getText();
    }
    public static void start(Stage primaryStage)
    {

        GridPane UI = new GridPane(); 
        UI.setHgap(10);
        UI.setVgap(15);
        UI.setPadding(new Insets(10, 15, 20, 25));
        UI.setAlignment(Pos.CENTER);

      
        UI.add(new Label("Enter eircode:"),0,1);
        UI.add(tfEircode,1,1);
         
        
        UI.add(new Label("Search For Property"),0,7);
        Button Search = new Button("Search:");
        UI.add(Search,1,7);
        
        Button Cancel = new Button("Cancel");
        UI.add(Cancel,1,8);
        
        Cancel.setOnAction(new EventHandler<ActionEvent>() 
            {
                @Override public void handle(ActionEvent e) {
                primaryStage.hide();
                AdminUser.start(primaryStage);
                }

    }); 
        
        
        Scene scene = new Scene(UI);
        primaryStage.setTitle("Property Charge Management System"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.setWidth(400);
        primaryStage.setHeight(300);
        primaryStage.show(); // Display the stage

        
        
        Search.setOnAction(new EventHandler<ActionEvent>() 
            {
                
                @Override public void handle(ActionEvent e) {
                    primaryStage.hide(); 
                    DisplayPropData.start(primaryStage);
                    
                }
            });
        
        
        
    }
}
