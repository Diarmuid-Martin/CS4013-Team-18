
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
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

/**
 * A class which asks the user to enter in details of the property they want to enter
 * Once the details are entered a property is created and written in the SystemData.csv file
 * Tax due is automatically caclulated
 * @author Hannah O'Hea
 * @version 11/12/2020
 */
public class AddProperty {
    private static LocalDateTime date;
    private static TextField tfAddress = new TextField();
    private static TextField tfEircode = new TextField();
    private static TextField tfValue = new TextField();
    private static RadioButton rbYes=new RadioButton("Yes");
    private static RadioButton rbNo=new RadioButton("No");
    private static String[] locations={"CITY","LARGETOWN","SMALLTOWN","VILLAGE","COUNTRYSIDE"};
    private static ComboBox<String> cbLocation = new ComboBox<>();
    private static Button btAddProp=new Button("Add Property");
    private static String yn;

    public static String getTfAddress() {
        return tfAddress.getText();
    }

    public static String getTfEircode() {
        return tfEircode.getText();
    }

    public static double getTfValue() { return Double.parseDouble(tfValue.getText()); }

    public static RadioButton getRbYes() {
        return rbYes;
    }

    public static RadioButton getRbNo() {
        return rbNo;
    }

    
    public static String getSelection(){
        return cbLocation.getValue();
    }
   
    public static ComboBox<String> getCbLocation() {
        return cbLocation;
    }
    
    
    private static ArrayList<PropertyOb> props;
    private static int max;

    public static void start(Stage primaryStage) {
        String file= "SystemData.csv";
        String path = FindFile.FindPath(file);
        props= dataCVS.allProperties(path);
        int max=getMaxNumb(path);
        //location drop down box, CheckBox
        ObservableList<String> items= FXCollections.observableArrayList(locations);
        cbLocation.getItems().addAll(items);

        GridPane UI = new GridPane();
        UI.setHgap(5);
        UI.setVgap(5);
        UI.setPadding(new Insets(10, 15, 20, 25));
        UI.setAlignment(Pos.CENTER);
        UI.add(new Label("Address:"),0,0);
        UI.add(tfAddress,1,0);
        UI.add(new Label("Eircode"),0,1);
        UI.add(tfEircode,1,1);
        UI.add(new Label("Value:"),0,2);
        UI.add(tfValue,1,2);
        UI.add(new Label("Principal Residence: "),0,3);
        UI.add(rbYes,1,3);
        UI.add(rbNo,2,3);
        UI.add(new Label("Location"),0,4);
        UI.add(cbLocation,1,4);
        Button Cancel = new Button("Cancel");
        UI.add(Cancel,0,5);
        UI.add(btAddProp,1,5);


        int height=50;
        int width=200;

        UI.setAlignment(Pos.CENTER);
        tfAddress.setAlignment(Pos.BOTTOM_RIGHT);
        tfEircode.setAlignment(Pos.BOTTOM_RIGHT);
        tfValue.setAlignment(Pos.BOTTOM_RIGHT);

        //buttons

        final ToggleGroup group = new ToggleGroup();
        rbYes.setToggleGroup(group);
        rbNo.setToggleGroup(group);
        rbYes.setOnAction(event -> {
                if(rbYes.isSelected()){
                    yn="Y";
                    rbYes.setTextFill(Color.AQUA);
                }
            });
        rbNo.setOnAction(event -> {
                if(rbNo.isSelected()){
                    yn="N";
                    rbNo.setTextFill(Color.AQUA);
                }
            });

        Scene scene = new Scene(UI,1500,700);
        primaryStage.setTitle("Add Property for "+OwnerWho.getName());
        primaryStage.setScene(scene);
        primaryStage.show();

        Cancel.setOnAction((ActionEvent e) -> {
                primaryStage.hide();
                AdminUser.start(primaryStage);
            }); 

        //add
        btAddProp.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent e){

                    primaryStage.hide();
                    try {                       
                        //adds property to array then writes to file the new array list
                        date= LocalDateTime.now(); //is used to get the year of creation
                        PropertyOb p = new PropertyOb(max+1, OwnerWho.getName(), getTfAddress(),
                                getTfEircode(), getTfValue(), yn, getSelection(), date.getYear(),
                                calcCurrentTax(), 0, calcTotalTax(), 0, calcBalance() );

                        String s=p.toStringCsv();
                        dataCVS.addPropCSV(props,p,path);


                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    OwnerWho.start(primaryStage);
                    primaryStage.show();

                }
            });


    }

    public static int getMaxNumb(String path){
        ArrayList<PropertyOb> d = dataCVS.allProperties(path);
        double max=0;
        double[] j =new double[d.size()];
        for(int i =0;i<d.size();i++){
            j[i]=d.get(i).getPropNumb();
            if(j[i]>max)
                max=j[i];
        }

        int iMax=(int) max;
        return iMax;
    }

    
    /* 
     * calcCurrenTax is used when a property is entered into the system
     * It uses details such as value of property, location and if its a principal residence
     * this information is written in the SystemData.csv file
     * @author Hannah O'Hea
     */
    public static double calcCurrentTax() {
        double fixed=100;
        double v=getTfValue();
        double rate=0.0;
        String isP=yn;
        double locationTax=0;
        double princTax=0;

        if(v<150000)
            rate=0.0;
        else if (v>=150000&&v<=400000)
            rate=0.01;
        else if(v>=400001&&v<=650000)
            rate=0.02;
        else if(v>650000)
            rate=0.04;
        double valueTax=(rate/100.00)*v;

        if(getSelection().equals("CITY"))
            locationTax=100;
        else if (getSelection().equals("LARGETOWN"))
            locationTax=80;
        else if (getSelection().equals("SMALLTOWN"))
            locationTax=60;
        else if (getSelection().equals("VILLAGE"))
            locationTax=50;
        else if (getSelection().equals("COUUNTRYSIDE"))
            locationTax=25;

        if(yn.equals("Y"))
            princTax=0;
        else if(yn.equals("N"))
            princTax=100;

        return fixed+valueTax+locationTax+princTax;
    }

    public static double calcTotalTax() {
        return calcCurrentTax();
    }//+overduetax
    public static double calcBalance(){
        return calcTotalTax()-PropertyOb.getAmountPaid(); 
    }

}
