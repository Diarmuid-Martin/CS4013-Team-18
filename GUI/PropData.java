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

public class PropData
{

    public static void start(Stage primaryStage)
    {

        GridPane UI = new GridPane(); 
        UI.setHgap(10);
        UI.setVgap(15);
        UI.setPadding(new Insets(10, 15, 20, 25));
        UI.setAlignment(Pos.CENTER);

        UI.add(new Label("Enter Property Address:"),0,0);

        UI.add(new Label("Address Line 1:"),0,1);
        UI.add(new TextField(),1,1);

        UI.add(new Label("Address Line 2:"),0,2);
        UI.add(new TextField(),1,2);

        UI.add(new Label("City:"),0,3);
        UI.add(new TextField(),1,3);
        
        UI.add(new Label("County:"),0,4);
        UI.add(new TextField(),1,4);
        
        UI.add(new Label("Country:"),0,5);
        UI.add(new TextField(),1,5);
        
        
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

