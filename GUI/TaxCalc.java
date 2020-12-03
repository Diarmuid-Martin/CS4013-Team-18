
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

public class TaxCalc
{

    public static void start(Stage primaryStage)
    {

        GridPane UI = new GridPane(); 
        UI.setHgap(100);
        UI.setVgap(50);
        UI.setPadding(new Insets(10, 15, 20, 25));
        UI.setAlignment(Pos.CENTER);

       
        UI.add(new Label(""),0,0); 
        TextField FixedCharge = new TextField("");
        UI.add(FixedCharge,1,0);

        UI.add(new Label(""),0,1); 
        TextField PrincipalResidCharge = new TextField("");
        UI.add(PrincipalResidCharge,1,1);

        UI.add(new Label(""),0,2); 
        TextField UnpaidCharge = new TextField("");
        UI.add(UnpaidCharge,1,2);

        ComboBox ValueCat = new ComboBox(); 
        ValueCat.getItems().addAll(
            "<150,000",
            "150,000-400,000",
            "400,001-650,000",
            ">650,000" 
        );    
        UI.add(ValueCat,0,3);    
        TextField ValueCharge = new TextField("");
        UI.add(ValueCharge,1,3);

        ComboBox LocCat = new ComboBox(); 
        LocCat.getItems().addAll(
            "City",
            "Large Town",
            "Small Town",
            "Village",
            "Countryside"  
        );        
        UI.add(LocCat,0,4); 
        TextField LocationCharge = new TextField("");
        UI.add(LocCat,1,4);

        Button Cancel = new Button("Cancel");
        UI.add(Cancel,1,6);
        
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

