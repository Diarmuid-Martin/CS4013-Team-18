
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

public class AddPropForNewOwner 
{
    public static void start(Stage primaryStage, int numberOfOwners){

        GridPane UI = new GridPane();
        UI.setAlignment(Pos.CENTER); // Set center alignment
        UI.setPadding( new Insets(11, 12, 13, 14));
        UI.setHgap(10);
        UI.setVgap(7.5); 

        int i =0;
        while(i<numberOfOwners){
            UI.add(new Label("Owner Name:"), 0, i);
            UI.add(new TextField(), 1, i);
            i++;
        }

        String[] Labels = {"Address:","Eircode:","Market Value:"};

        while(i-numberOfOwners<3){
            UI.add(new Label(Labels[i-numberOfOwners]), 0, i); 
            UI.add(new 
                TextField(), 1, i);   
            i++;
        }

        UI.add(new Label("Principal Residence:"), 0, i); 
        UI.add(new 
            CheckBox(""), 1, i);

        i++;

        UI.add(new Label("Location:"), 0, i);

        ComboBox LocCat = new ComboBox(); 
        LocCat.getItems().addAll(
            "City",
            "Large Town",
            "Small Town",
            "Village",
            "Countryside"  
        );        
        UI.add(LocCat, 1, i);
        i++;
        UI.add(new Label("Click to Add New Property:"), 0, i+1);
        UI.add(new 
            Button("Add"), 1, i+1);
        
         Button Cancel = new Button("Cancel");
         UI.add(Cancel, 1, i+2);

        ScrollBar scrollBar = new ScrollBar();
        scrollBar.setMin(0);  
        scrollBar.setMax(UI.getHeight()+(numberOfOwners-1)*60);  
        scrollBar.setValue(0);  
        scrollBar.setOrientation(Orientation.VERTICAL);  
        scrollBar.setUnitIncrement(12);  
        scrollBar.setBlockIncrement(10);

        AnchorPane root = new AnchorPane(scrollBar);
        AnchorPane.setTopAnchor(scrollBar, 0d);
        AnchorPane.setRightAnchor(scrollBar, 0d);
        AnchorPane.setBottomAnchor(scrollBar, 0d);

        HBox ListBox = new HBox();
        ListBox.setSpacing(10);
        ListBox.setPadding(new Insets(10,10,10,10));
        ListBox.getChildren().addAll(UI,root);
        ListBox.setAlignment(Pos.TOP_CENTER); 

        scrollBar.valueProperty().addListener(new ChangeListener<Number>() {
                public void changed(ObservableValue<? extends Number> ov,
                Number old_val, Number new_val) {
                    ListBox.setLayoutY(-new_val.doubleValue());
                }
            });

        
        Scene myScene = new Scene( ListBox);  
        Color c = new Color(.95,.95,.95,1.0);
        myScene.setFill(c);
        primaryStage.setWidth(450);
        primaryStage.setHeight(300);
        primaryStage.setTitle("Add Property"); // Set the stage title
        primaryStage.setScene(myScene);
        primaryStage.show();

        Cancel.setOnAction(new EventHandler<ActionEvent>() 
            {
                @Override public void handle(ActionEvent e) {
                primaryStage.hide();
                ExistingOwner.start(primaryStage);
                }

    }); 
}


}

