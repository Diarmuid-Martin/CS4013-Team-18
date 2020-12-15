

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


/**
 * @author Hannah O'Hea
 * @version 10/12/2020
 * This class asks who is logging in to detrimine what data to display
 */
public class OwnerWho  {

    private static TextField tfName;
    private static Button btOK;
    private static String name;

    public static void setName(String pName){
        name=pName;
    }
    public static String getName(){

        return name;
    }

    public static void start(Stage primaryStage) {
        tfName=new TextField();
        btOK=new Button("OK");


        TextField tfName = new TextField();
        Button btOK=new Button("OK");
        Label lbEnterName=new Label("Enter Your First Name: (Press OK)");

        GridPane UI = new GridPane();
        UI.setHgap(50);
        UI.setVgap(25);
        UI.setPadding(new Insets(10,15,20,25));
        UI.setAlignment(Pos.CENTER);

       

        UI.add(lbEnterName,0,0);
        UI.add (tfName,1,0);
        UI.add(btOK,1,1);


        tfName.setAlignment(Pos.CENTER);

        primaryStage.setTitle("Owner Properties");
        primaryStage.setWidth(500);
        primaryStage.setHeight(150);


        Scene scene = new Scene(UI,200,150);
        primaryStage.setScene(scene);
        primaryStage.show();

        btOK.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e){

                primaryStage.hide();
                setName(tfName.getText());
                //reads through SystemData.csv file, finds properties with name same as tfName above, and  puts them in an
                OwnerFunctions.start(primaryStage);
                //The next GUI, OwnerPage, It displays some details such as tax due, tax paid etc.
            }
        });

    }
}

