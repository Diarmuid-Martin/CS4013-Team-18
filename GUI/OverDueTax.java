
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Group; 
import javafx.scene.control.*;
import javafx.event.ActionEvent; 
import javafx.event.EventHandler; 
import javafx.beans.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.paint.Color;


public class OverDueTax 
{

    public static void start(Stage primaryStage)
    {
         GridPane UI = new GridPane(); 
        UI.setHgap(100);
        UI.setVgap(50);
        UI.setPadding(new Insets(10, 15, 20, 25));
        UI.setAlignment(Pos.CENTER);
        
        
        
        UI.add(new Label("Enter Year:"), 0, 0);
        TextField Year = new TextField();
        UI.add(Year,1,0);
        
        UI.add(new Label("Enable Area Search"), 0, 1);
        UI.add(new CheckBox(""), 1, 1);
        
        UI.add(new Label("Enter Eircode Routing Key:"), 0, 2);
        TextField RouteKey = new TextField();
        UI.add(RouteKey,1,2);
        

        Button Cancel = new Button("Cancel");
        UI.add(Cancel,1,4);
        
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
