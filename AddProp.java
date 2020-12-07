package com.company;


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

public class AddProp {
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
    public static String getSelection(){return cbLocation.getValue();}
    //  public static SingleSelectionModel<String> getSelection() {return cbLocation.getSelectionModel();}
    public static ComboBox<String> getCbLocation() {
        return cbLocation;
    }



    public static void start(Stage primaryStage) {

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
        UI.add(btAddProp,0,5);


        int height=50;
        int width=200;

        UI.setAlignment(Pos.CENTER);
        tfAddress.setAlignment(Pos.BOTTOM_RIGHT);
        tfEircode.setAlignment(Pos.BOTTOM_RIGHT);
        tfValue.setAlignment(Pos.BOTTOM_RIGHT);
        UI.setHalignment(btAddProp,HPos.RIGHT);

        //buttons


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
        primaryStage.setTitle("Add Property for "+Owner.getName());
        primaryStage.setScene(scene);
        primaryStage.show();

        //add
        btAddProp.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e){

                primaryStage.hide();
                try {
                    addProperty();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                OwnerPage.start(primaryStage);
                primaryStage.show();


            }
        });



    }

    //methods
    //add property to excel sheet, then hide this window and open the OwnerPage again.
    private static void addProperty() throws IOException{
        //make object of Property
        date= LocalDateTime.now();
        Property p = new Property(getLastPropNumb()+1, Owner.getName(), getTfAddress(),
                getTfEircode(), getTfValue(), yn, getSelection(), date.getYear(),
                getCurrentTax(), 0, getTotalTax(), 0, getBalance() );
        //add property.toStringCsv() to the file SystemData
        //System.out.println(p.toString());
        String path="C:\\Users\\Hannah\\Documents\\IdeaProjects\\SystemData.csv";
        String s=p.toStringCsv();
        BufferedWriter writer = new BufferedWriter(new FileWriter(path,true));
        PrintWriter pw = new PrintWriter(writer);
        pw.println(s);
        pw.flush();
        writer.close();
        pw.close();



    }

    //method to get last prop number
    public static int getLastPropNumb(){
        String path = "C:\\Users\\Hannah\\Documents\\IdeaProjects\\SystemData.csv";
        String line;
        ArrayList<String> a =new ArrayList<>();
        // ArrayList<Double> h = new ArrayList<>();
        try {
            BufferedReader br=new BufferedReader(new FileReader(path));

            while((line=br.readLine())!=null) {
                String[] values = line.split(",");
                a.add(values[0]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        double[] d = new double[a.size()-1];
        double max=0;
        for(int i=0;i<d.length;i++) {
            d[i]=Double.parseDouble(a.get(i+1));
        }
        for(int i=0;i<d.length;i++) {

            if(d[i]>max)
                max=d[i];

        }
        int iMax=(int) max;
        return iMax;

    }

    public static double getCurrentTax() {
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

    //method to get overdue tax from previous years.
    //but this class is for new properties and so cant have overdue tax.
    //this method should go somewhere else maybe in the property class??
    // public static void getOverdueTax()


    //again, no need for this now cause there is no overdues tex, but it is in constructor so..
    public static double getTotalTax() {
        return getCurrentTax();
    }
    public static double getBalance(){
        return getTotalTax();

    }

}



