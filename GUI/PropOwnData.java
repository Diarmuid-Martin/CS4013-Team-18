
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.Group; 
import javafx.scene.control.*; 
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent; 
import javafx.event.EventHandler; 


public class PropOwnData 
{

    public static void start(Stage primaryStage)
    {

         GridPane UI = new GridPane(); 
        UI.setHgap(100);
        UI.setVgap(50);
        UI.setPadding(new Insets(10, 15, 20, 25));
        UI.setAlignment(Pos.CENTER);
 
        UI.add(new Label("Enter Property Owner Name:"),0,0);
        TextField Owner = new TextField();
        UI.add(Owner,0,1);
        
        Button Search = new Button("Search");
        UI.add(Search,0,3);
        
        Button Cancel = new Button("Cancel");
        UI.add(Cancel,0,5);
        
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
        
    }
}