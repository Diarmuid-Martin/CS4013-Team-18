package com.company;

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

public class Owner  {

    private static TextField tfName = new TextField();
    private static Button btOK=new Button("OK");
    private static String name;

    public static void setName(String pName){
        name=pName;
    }
    public static String getName(){

        return name;
    }

    public static void start(Stage primaryStage) {



        TextField tfName = new TextField();
        Button btOK=new Button("OK");
        Label lbEnterName=new Label("Enter Your First Name: (Press OK)");

        GridPane UI = new GridPane();
        UI.setHgap(100);
        UI.setVgap(50);
        UI.setPadding(new Insets(10,15,20,25));
        UI.setAlignment(Pos.CENTER);

        int height = 50;
        int width = 200;

        UI.add(lbEnterName,0,0);
        UI.add (tfName,1,0);
        UI.add(btOK,1,5);


        tfName.setAlignment(Pos.CENTER);

        primaryStage.setTitle("Owner Properties");
        primaryStage.setWidth(600);
        primaryStage.setHeight(350);


        Scene scene = new Scene(UI,400,300);
        primaryStage.setScene(scene);
        primaryStage.show();

        btOK.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e){

                primaryStage.hide();
                setName(tfName.getText());
                //reads through SystemData.csv file, finds properties with name same as tfName above, and  puts them in an
                //   ArrayList of type PropertyList..(Make another class which is an array list in its constructor and its attributes are
                //   the colunms in the excel sheet.).
                //   Then in the next GUI, OwnerPage, It displays some details and tax due ect.
                OwnerPage.start(primaryStage);
            }
        });

    }
}
