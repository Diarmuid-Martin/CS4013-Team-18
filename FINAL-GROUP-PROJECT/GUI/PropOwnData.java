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

public class PropOwnData 
{
    private static TextField Owner = new TextField();
    
     public static String getOwnersName() {
        return Owner.getText();
    }

    public static void start(Stage primaryStage)
    {

        GridPane UI = new GridPane(); 
        UI.setHgap(5);
        UI.setVgap(1);
        UI.setPadding(new Insets(10, 10, 10, 10)); 
        UI.setAlignment(Pos.CENTER);
 
        UI.add(new Label("Enter Property Owner Name:"),0,0);
        UI.add(Owner,1,0);
        
        Button Search = new Button("Search");
        UI.add(Search,0,1);
        Search.setOnAction(new EventHandler<ActionEvent>() 
            {
                @Override public void handle(ActionEvent e) {
                primaryStage.hide();
                DisplayPropOwnData.start(primaryStage);
                
               }

    }); 
        
        Button Cancel = new Button("Cancel");
        UI.add(Cancel,1,1);
        
        Cancel.setOnAction(new EventHandler<ActionEvent>() 
            {
                @Override public void handle(ActionEvent e) {
                primaryStage.hide();
                AdminUser.start(primaryStage);
                }

    }); 
        
        
        Scene scene = new Scene(UI);
        primaryStage.setTitle("Property Charge Management System: to get data for any property owner"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.setWidth(400);
        primaryStage.setHeight(300);
        primaryStage.show(); // Display the stage
        
    }
}