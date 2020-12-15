
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

import java.io.File; 
import java.io.*;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Allows the user to choose the property they want to pay tax for
 * @author Diarmuid Martin
 * @version 8/12/2020
 */
public class ChooseProp 
{
    private static String path = FindFile.FindPath("SystemData.csv");
    public static void start(Stage primaryStage) {
        GridPane UI = new GridPane(); 
        UI.setHgap(10);
        UI.setVgap(5);
        UI.setPadding(new Insets(10, 15, 20, 25));
        UI.setAlignment(Pos.CENTER);

        TextField Eircode = new TextField();        
        UI.add(new Label("Enter Eircode Of Property:"),0,0);
        UI.add(Eircode,0,1);

        Button Enter = new Button("Enter");
        UI.add(Enter,1,1); 

        Scene Opening = new Scene(UI);
        primaryStage.setTitle("Choose Property"); // Set the stage title
        primaryStage.setScene(Opening); // Place the scene in the stage
        primaryStage.show();
        primaryStage.setWidth(450);
        primaryStage.setHeight(200);

        Enter.setOnAction(new EventHandler<ActionEvent>() 
            {
                @Override public void handle(ActionEvent e) {
                    String Eir= Eircode.getText();
                    try{             
                        if(Check(Eir)){
                            primaryStage.hide();
                            PayMyTax.start(primaryStage, Eircode.getText());
                        }else{
                            Eircode.setText("");
                            Eircode.setPromptText("Property Doesn't Exist");
                        }
                    }catch(FileNotFoundException file){
                        Eircode.setText("");
                        Eircode.setPromptText("Can't Find File");

                    } catch (IOException a) {
                        a.printStackTrace();
                    } catch (Exception er){
                        er.printStackTrace();
                    }

                }
            });
    }

    /** 
     * Checks to make sure property exists inside the SystemData.csv file
     */
    public static boolean Check(String Eircode) throws Exception{

        String line;            

        boolean doesntExist=true;

        BufferedReader br = new BufferedReader(new FileReader(path));
        while ((line = br.readLine()) != null) {
            String[] Cat = line.split(",");

            if(Cat[3].equals(Eircode))
            {           
                doesntExist = false;
            }

            return doesntExist;
        }

        return false;
    }
}
