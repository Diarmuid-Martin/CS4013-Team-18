
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.Group; 
import javafx.scene.control.*;
import javafx.event.ActionEvent; 
import javafx.event.EventHandler; 
import javafx.beans.*;

public class HowManyOwners  {
    
    
    public static void start(Stage primaryStage){

        GridPane OpeningUI = new GridPane(); 
        OpeningUI.setHgap(10);
        OpeningUI.setVgap(5);
        OpeningUI.setPadding(new Insets(10, 15, 20, 25));
        OpeningUI.setAlignment(Pos.CENTER);

        TextField NumOfOwners = new TextField();        
        OpeningUI.add(new Label("Enter The Number of Owners for this Property (Including Self):"),0,0);
        OpeningUI.add(NumOfOwners,0,1);
        
        Button Enter = new Button("Enter");
        OpeningUI.add(Enter,1,1); 
    
        Button Cancel = new Button("Cancel");
        OpeningUI.add(Cancel,1,3);
        
        Scene Opening = new Scene(OpeningUI);
        primaryStage.setTitle("Property Charge Management System"); // Set the stage title
        primaryStage.setScene(Opening); // Place the scene in the stage
        primaryStage.show();
        primaryStage.setWidth(450);
        primaryStage.setHeight(200);

        Enter.setOnAction(new EventHandler<ActionEvent>() 
            {
                @Override public void handle(ActionEvent e) {
                    try{
                        String text = NumOfOwners.getText();
                        int input = Integer.parseInt(text);
                        
                        if(input>0){
                        primaryStage.hide();
                        AddPropForNewOwner.start(primaryStage, input);
                         }else{
                         NumOfOwners.setText("");
                         NumOfOwners.setPromptText("Please use positive whole numbers.");
                        }
                    }catch (NumberFormatException er) {
                        NumOfOwners.setText("");
                        NumOfOwners.setPromptText("Your input was invalid. Please use numbers.");

                    } 
                }
            });
            
            
            Cancel.setOnAction(new EventHandler<ActionEvent>() 
            {
                @Override public void handle(ActionEvent e) {
                primaryStage.hide();
                ExistingOwner.start(primaryStage);
                }

    }); 
            
        }
    }
