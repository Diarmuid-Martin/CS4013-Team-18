
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

public class ExistingOwner
{
    
    public static void start(Stage primaryStage){       
        GridPane UI = new GridPane(); 
        UI.setHgap(100);
        UI.setVgap(50);
        UI.setPadding(new Insets(10, 15, 20, 25));
        UI.setAlignment(Pos.CENTER_RIGHT);

        int height=50;
        int width=200;

        Button AddProp= new Button("Register New Property");
        AddProp.setMinSize(width,height);

        Button PayTax= new Button("Pay Due Tax");
        PayTax.setMinSize(width,height);

        Button ViewPayments= new Button("View Previous Payments");
        ViewPayments.setMinSize(width,height);

        UI.add(AddProp, 0,0);
        UI.add(PayTax, 0,1);
        UI.add(ViewPayments, 0,2);       

        ListView<String> properties = new ListView<>();
        properties.setPrefSize(150, 120);
        properties.getItems().addAll(createPropList());
        properties.setMinSize(300,200);
        properties.setMaxSize(300,200);
        
        HBox ListBox = new HBox();
        ListBox.setSpacing(10);
        ListBox.setPadding(new Insets(10,10,10,10));
        ListBox.getChildren().addAll(properties,UI);
        ListBox.setAlignment(Pos.CENTER_LEFT);
 
          

        primaryStage.setTitle("Property Owner"); // Set the stage title
        primaryStage.setWidth(600);
        primaryStage.setHeight(350);

        Scene scene = new Scene(ListBox);
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

        
        AddProp.setOnAction(new EventHandler<ActionEvent>() 
            {
                @Override public void handle(ActionEvent e) {
                    primaryStage.hide(); 
                    HowManyOwners.start(primaryStage);
                    
                }
            });
        
            
        
        
    }

    public static String[] createPropList()
    {
        String[] List = {
                "Owners, Address, Eircode, Value, Location Cat, Y/N, Tax Due"
                };
        return List;
    }
}
