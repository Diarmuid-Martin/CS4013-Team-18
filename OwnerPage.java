package com.company;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import java.time.LocalDateTime;


public class OwnerPage {


    private static Button btAdd = new Button("Add New Property");
    private static Button btPay = new Button("Pay Property Tax");
    private static TextField tfSelectYear = new TextField("Select Year");

    //making array of years for drop down list
    private static int[] years = new int[50];
    private static String[] yearsString=new String[50];
    private static int year= LocalDateTime.now().getYear();
    private static ComboBox<String> cbYear = new ComboBox<>();

    private static GridPane gpData = new GridPane();


    public static void start(Stage primaryStage) {

        for(int i=0;i<years.length;i++)
        {
            years[i]=year-i;
            yearsString[i]= String.valueOf(years[i]);
        }


        BorderPane UI = new BorderPane();

        //HBox at top of GUI:
        HBox hBox= new HBox();
        hBox.setPadding(new Insets(15,12,15,12));
        hBox.setSpacing(10);
        hBox.setStyle("-fx-background-color: #336699;");

        int height=20;
        int width=100;

        btAdd.setMinSize(width,height);
        btPay.setMinSize(width, height);
        tfSelectYear.setMinSize(width-40,height);
        tfSelectYear.setEditable(false);
        cbYear.setMinSize(width,height);

        hBox.getChildren().addAll(btAdd, btPay, tfSelectYear,cbYear);

        //cbYear:

        ObservableList<String> items= FXCollections.observableArrayList(yearsString);
        cbYear.getItems().addAll(items);

        cbYear.setOnAction(event -> {
            gpData.getChildren().clear();

            ArrayList<String[]> d = ownerPropertiesYear(Owner.getName(), getYearSelection());
            for(int i=0;i<d.size();i++) {
                String[] s=d.get(i);
                for(int j=0;j<s.length;j++) {
                    Text cols = new Text(s[j]);
                    cols.setFont(Font.font("Arial", FontWeight.LIGHT,15));
                    gpData.add(cols,j+1,i+2);

                }
            }
            //grid pane for tax data
            gpData.setHgap(10);
            gpData.setVgap(0);
            gpData.setPadding(new Insets(0,10,0,10));
            gpData.setGridLinesVisible(true);

            //column headings is first row in excel file
            String[] firstRow2=getFirstRow();
            for(int i=0;i<firstRow2.length;i++) {
                Text cols = new Text(firstRow2[i]);
                cols.setFont(Font.font("Arial", FontWeight.BOLD,15));
                gpData.add(cols,i+1,0);

            }

        });






        UI.setTop(hBox);
        UI.setCenter(gpData);

        Scene scene = new Scene(UI);
        primaryStage.setTitle("Welcome "+Owner.getName());
        primaryStage.setScene(scene);
        primaryStage.setWidth(1300);
        primaryStage.setHeight(700);
        primaryStage.show();


        //Buttons:

        //Add Property
        btAdd.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e){

                primaryStage.hide();

               AddProp.start(primaryStage);
            }
        });

        //SelectYear



    }









    //methods
    public static String[] getFirstRow() {
        String path = "C:\\Users\\Hannah\\Documents\\IdeaProjects\\SystemData.csv";
        String line;
        ArrayList<String> a=new ArrayList<>();
        try {
            BufferedReader br=new BufferedReader(new FileReader(path));

            line=br.readLine();
            String[] values=line.split(",");

            for(int i=0;i<values.length;i++) {
                // System.out.print(values[i]+" ");
                a.add(values[i]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] b = a.toArray(new String[0]);
        return b;


    }

    public static String getYearSelection(){return cbYear.getValue();}
//method to get properties of specified owner:
    public static ArrayList<String[]> ownerProperties(String name){
        // ArrayList<Property> properties=new ArrayList<>();
        ArrayList<String[]> s =new ArrayList<>();
        String path ="C:\\Users\\Hannah\\Documents\\IdeaProjects\\SystemData.csv";
        String line;
        try {
            BufferedReader br=new BufferedReader(new FileReader(path));
            while((line=br.readLine())!=null){
                String[] v=line.split(",");
                //  System.out.println(v[1]);
                if(name.equals(v[1])){
                    double v01=Double.parseDouble(v[0]);
                    int v0=(int)v01;
                    double v4 = Double.parseDouble(v[4]);
                    int v7 = Integer.parseInt(v[7]);
                    double v8=Double.parseDouble(v[8]);
                    double v9=Double.parseDouble(v[9]);
                    double v10=Double.parseDouble(v[10]);
                    double v11=Double.parseDouble(v[11]);
                    double v12=Double.parseDouble(v[12]);

                    Property prop = new Property(v0, v[1], v[2], v[3],v4,v[5], v[6], v7, v8, v9,v10,v11,v12);
                    // System.out.println(prop.toString());
                    // properties.add(prop);
                    //new code
                    String[] h = prop.toStringArray();
                    s.add(h);

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return s;

    }


    //method to get properties from a specific year and before it:
public static ArrayList<String[]> ownerPropertiesYear(String name, String year) {
    // ArrayList<Property> properties=new ArrayList<>();
    ArrayList<String[]> s =new ArrayList<>();
    String path ="C:\\Users\\Hannah\\Documents\\IdeaProjects\\SystemData.csv";
    String line;
    try {
        BufferedReader br=new BufferedReader(new FileReader(path));
        while((line=br.readLine())!=null){
            String[] v=line.split(",");
            //  System.out.println(v[1]);


            if(name.equals(v[1])){

                double v01=Double.parseDouble(v[0]);
                int v0=(int)v01;
                double v4 = Double.parseDouble(v[4]);
                int v7 = Integer.parseInt(v[7]);
                double v8=Double.parseDouble(v[8]);
                double v9=Double.parseDouble(v[9]);
                double v10=Double.parseDouble(v[10]);
                double v11=Double.parseDouble(v[11]);
                double v12=Double.parseDouble(v[12]);

                Property prop = new Property(v0, v[1], v[2], v[3],v4,v[5], v[6], v7, v8, v9,v10,v11,v12);
                // System.out.println(prop.toString());
                // properties.add(prop);
                //new code
                int intYear=Integer.parseInt(year);
                int dataYear=Integer.parseInt(v[7]);
                if(dataYear<=intYear) {
                    String[] h = prop.toStringArray();
                    s.add(h);
                }
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }


    return s;

}

    }




