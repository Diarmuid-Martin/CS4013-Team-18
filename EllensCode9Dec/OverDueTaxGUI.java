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

public class OverDueTaxGUI 
{ 
    private static TextField Year = new TextField();
    private static TextField RouteKey = new TextField();
    private static CheckBox chBox=new CheckBox("");
    
     public static int getYear() {
         String year=Year.getText();  
        return Integer. parseInt(year);
    }
     
     public static String getRoutingKey(){
         return RouteKey.getText();
     }
     
     public static Boolean isChecked(){
         return chBox.isSelected();
     }

    public static void start(Stage primaryStage)
    {
        GridPane UI = new GridPane(); 
        UI.setHgap(5);
        UI.setVgap(5);
        UI.setPadding(new Insets(10, 15, 20, 25));
        UI.setAlignment(Pos.CENTER);
        
        
        
        UI.add(new Label("Enter Year:"), 0, 0);
        UI.add(Year,1,0);
        
        UI.add(new Label("Enable Area Search"), 0, 1);
        UI.add(chBox, 1, 1);
        
        UI.add(new Label("Enter Eircode:"), 0, 2);
        UI.add(RouteKey,1,2);
        
        Button Search=new Button("Search");
        UI.add(Search, 0, 4);
        
         Search.setOnAction((ActionEvent e) -> {
             primaryStage.hide();
             DisplayOverdueTax.start(primaryStage);
        }); 

        Button Cancel = new Button("Cancel");
        UI.add(Cancel,1,4);
        
        Cancel.setOnAction((ActionEvent e) -> {
            primaryStage.hide();
            AdminUser.start(primaryStage);
        }); 
        
        
        
        
 
        Scene scene = new Scene(UI);
        primaryStage.setTitle("Property Charge Management System"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.setWidth(800);
        primaryStage.setHeight(500);
        primaryStage.show(); // Display the stage
        

    }
}