import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.FileWriter;

import java.io.*;
import java.nio.Buffer;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

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
 * Pays the tax due on property selected in ChooseProp and Updates the information on the SystemData.csv file
 * @author Diarmuid Martin and Hannah O'Hea
 * @version 12/12/2020
 */
public class PayMyTax {
    private static LocalDateTime date;
    private static Label lbPay;
    private static TextField tfAmount;
    private static Button btPay;
    private static String path = FindFile.FindPath("SystemData.csv");
    private static ArrayList<PropertyOb> p;
    private static ArrayList<PropertyOb> pName;

    public static void start(Stage primaryStage, String Eircode) {
        GridPane UI = new GridPane();
        UI.setHgap(5);
        UI.setVgap(5);
        UI.setPadding(new Insets(10, 15, 20, 25));
        UI.setAlignment(Pos.CENTER);

        lbPay=new Label("Pay Amount total tax due for : "+OwnerWho.getName());
        tfAmount=new TextField();
        btPay= new Button("Pay");
        date=LocalDateTime.now();
        p= dataCVS.allProperties(path);

        //hBox
        HBox hBox= new HBox();
        hBox.setPadding(new Insets(15,12,15,12));
        hBox.setSpacing(10);
        //hBox.setStyle("-fx-background-color: #336699;");
        tfAmount.setEditable(true);
        int height=20;
        int width=100;

        lbPay.setMinSize(width+30,height);
        tfAmount.setMinSize(width, height);
        btPay.setMinSize(width,height);

        hBox.getChildren().addAll(lbPay, tfAmount,btPay);
        UI.add(hBox,0,0);

        /**
         * Makes sure it is a valid input and updates tax due and tax paid by this input
         * @author Diarmuid Martin 
         * @version 12/12/2020
         */

        btPay.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent e){
                    try{
                        String text = tfAmount.getText(); //converts input into double 
                        double input = Double.parseDouble(text);

                        if(input>0){  //makes sure a positive amount of tax is paid for
                            try{
                                PayTax(input, Eircode);
                                primaryStage.hide(); 
                                OwnerFunctions.start(primaryStage);
                            }catch (Exception er){
                                tfAmount.setText("");
                                tfAmount.setPromptText("Try Closing All CSV Files.");
                            }

                        }else{
                            tfAmount.setText("");
                            tfAmount.setPromptText("Use positive numbers.");
                        }                       

                    }catch (NumberFormatException er) {
                        tfAmount.setText("");
                        tfAmount.setPromptText("Your input was invalid");

                    } 

                }
            });

        Scene scene = new Scene(UI);
        primaryStage.setTitle("Pay Tax for "+OwnerWho.getName());
        primaryStage.setScene(scene);
        primaryStage.setWidth(600);
        primaryStage.setHeight(150);
        primaryStage.show();
    }

    /**
     * Writes the new information by first creating DataUpdate.csv file which is the SystemData.csv with the new information
     * Then it rewrites SystemData to include the update
     * @author Diarmuid Martin 
     * @version 12/12/2020
     */
    public static void PayTax(double payment, String Eircode) throws Exception 
    {
        String newFile= "DataUpdate.csv";
        String newPath = FindFile.FindPath(newFile);
        BufferedReader br = null;

        BufferedWriter Overwrite = new BufferedWriter(new FileWriter(newPath));
        String line;

        //this variables are used to convert input into changes in the csv files
        double tax;
        double taxPaid;
        String replaceTax;
        String replacePaid;

        try {
            br = new BufferedReader(new FileReader(path));
            while ((line = br.readLine()) != null) {
                String[] Cat = line.split(",");

                if(Cat[3].equals(Eircode) && Cat[7].equals("2020"))
                {           
                    // Updates what has been paid 
                    taxPaid =Double.parseDouble(Cat[11]);
                    taxPaid = taxPaid + payment;
                    replacePaid = Double.toString(taxPaid);
                    Cat[11] = replacePaid;

                    //Updates what is due to be paid
                    tax= Double.parseDouble(Cat[12]);
                    tax= tax - payment;
                    replaceTax= Double.toString(tax);
                    Cat[12] = replaceTax;

                }
                //writes the updated data into the UpdateData file 
                for(int i=0; i<Cat.length;i++){ 
                    Overwrite.append(Cat[i] + ",");
                }
                Overwrite.append("\n");

            }
            Overwrite.flush();
            Overwrite.close();

            try{
                Rewrite(newPath,path); //rewrites the System Data file including the new update
            }catch(Exception er){
                tfAmount.setText("");
                tfAmount.setPromptText("Try Closing All CSV Files.");
            }

        } catch (FileNotFoundException a) {
            a.printStackTrace();
        } catch (IOException a) {
            a.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException a) {
                }
            }
        }

    }

    /**
     * Updates the SystemData file
     */
    public static void Rewrite(String newData, String oldData) throws Exception
    {

        BufferedReader br = null;

        BufferedWriter Overwrite = new BufferedWriter(new FileWriter(oldData));
        String line;

        try {
            br = new BufferedReader(new FileReader(newData));
            while ((line = br.readLine()) != null) {
                String[] Cat = line.split(",");

                for(int i=0; i<Cat.length;i++){
                    Overwrite.append(Cat[i] + ",");
                }
                Overwrite.append("\n");

            }
            Overwrite.flush();
            Overwrite.close();

        } catch (FileNotFoundException a) {
            a.printStackTrace();
        } catch (IOException a) {
            a.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException a) {
                }
            }
        }
    }
}
