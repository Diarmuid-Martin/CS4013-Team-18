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

 /**
 * @author Ellen McWey
 * @version 9/12/2020
 */

public class AdminUser
{
    public static void start(Stage primaryStage){  
        GridPane UI = new GridPane(); 
        UI.setHgap(100);
        UI.setVgap(50);
        UI.setPadding(new Insets(10, 15, 20, 25));
        UI.setAlignment(Pos.CENTER);
 
        int height=50;
        int width=300;
        
        
        Button Prop = new Button("Particular Property Payment Data");
        Prop.setMinSize(width, height);

        Button PropOwn = new Button("Particular Property Owner Payment Data");
        PropOwn.setMinSize(width, height);
        
        Button DueTax = new Button("Overdue Tax for Particular Year");
        DueTax.setMinSize(width, height);
        
        Button Stats = new Button("Tax Statistics for Area");
        Stats.setMinSize(width, height);

        Button Calc = new Button("Change Tax Calculator");
        Calc.setMinSize(width, height);
        
        UI.add(Prop, 0,0);
        UI.add(PropOwn, 0,1);
        UI.add(DueTax,0,2);
        UI.add(Stats,0,3);
        UI.add(Calc,0,4);
        
        
        Scene scene = new Scene(UI);
        primaryStage.setTitle("Property Charge Management System"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.setWidth(600);
        primaryStage.setHeight(600);
        primaryStage.show(); // Display the stage
        
        
        
       Prop.setOnAction((ActionEvent e) -> {
           primaryStage.hide();
           PropData.start(primaryStage);
        });
        
         PropOwn.setOnAction((ActionEvent e) -> {
             primaryStage.hide();
             PropOwnData.start(primaryStage);
        });
        
            DueTax.setOnAction((ActionEvent e) -> {
                primaryStage.hide();
                OverDueTaxGUI.start(primaryStage);
        });
        
         Stats.setOnAction((ActionEvent e) -> {
             primaryStage.hide();
             TaxStat.start(primaryStage);
        });
            
         Calc.setOnAction((ActionEvent e) -> {
             primaryStage.hide();
             CalcTaxGUI.start(primaryStage);
        });
       
    }
}