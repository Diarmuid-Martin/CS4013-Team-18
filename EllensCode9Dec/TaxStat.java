import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.event.ActionEvent; 

 /**
 * @author Ellen McWey
 * @version 9/12/2020
 */

public class TaxStat
{
    private static TextField RouteKey = new TextField();
    
     public static String getRoutingKey(){
         return RouteKey.getText();
     }

    public static void start(Stage primaryStage)
    {

        GridPane UI = new GridPane(); 
        UI.setHgap(100);
        UI.setVgap(50);
        UI.setPadding(new Insets(10, 15, 20, 25));
        UI.setAlignment(Pos.CENTER);
        
       
        
        UI.add(new Label("Enter Eircode Routing Key:"), 0, 0);
        UI.add(RouteKey,1,0);
        
        Button Cancel = new Button("Cancel");
        UI.add(Cancel,1,3);
        
        Cancel.setOnAction((ActionEvent e) -> {
            primaryStage.hide();
            AdminUser.start(primaryStage);
        }); 
        
        Button Search = new Button("Search");
        UI.add(Search, 0, 3);
        
        Search.setOnAction((ActionEvent e) -> {
            primaryStage.hide();
            DisplayTaxStat.start(primaryStage);
        }); 
        
 
        Scene scene = new Scene(UI);
        primaryStage.setTitle("Property Charge Management System"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.setWidth(400);
        primaryStage.setHeight(300);
        primaryStage.show(); // Display the stage
        
    }
}