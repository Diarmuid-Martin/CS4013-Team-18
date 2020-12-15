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
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import java.time.LocalDateTime;


/**
 * A class which displays properties for the owner which was detrimined in the OwnerWho class.
 * It does this by comparing the name of the owner the user entered to the list of data in SystemData.csv
 * It also gives the option to add properties and to pay tax
 * @author Hannah O'Hea
 * @version 9/12/2020
 */
public class OwnerFunctions {
    private static Button btAdd;
    private static Button btPay;
    private static Label lbSelectYear ;

    //making array of years for drop down list
    private static int[] years;
    private static String[] yearsString;
    private static int year;
    private static ComboBox<String> cbYear;
    private static GridPane gpData;
    private static String path = FindFile.FindPath("SystemData.csv");

    public static void start(Stage primaryStage) {
        btAdd = new Button("Add New Property");
        
        btPay= new Button("Pay Property Tax");
        lbSelectYear= new Label("Select Year");
        years = new int[50];
        year= LocalDateTime.now().getYear();
        yearsString =new String[50];
        cbYear = new ComboBox<>();
        for(int i=0;i<years.length;i++)
        {
            years[i]=year-i;
            yearsString[i]= String.valueOf(years[i]);
        }

        ObservableList<String> items= FXCollections.observableArrayList(yearsString);
        cbYear.getItems().addAll(items);
        gpData = new GridPane();
        gpData.getChildren().clear();

        ArrayList<PropertyOb> allProps=dataCVS.allProperties(path);

        BorderPane UI = new BorderPane();

        //HBox at top of GUI:
        HBox hBox= new HBox();
        hBox.setPadding(new Insets(15,12,15,12));
        hBox.setSpacing(10);
        hBox.setStyle("-fx-background-color: #336699;");

        hBox.getChildren().addAll(btAdd, btPay,lbSelectYear,cbYear);
        //cbYear:

        gpData.setGridLinesVisible(true);

        cbYear.setOnAction(event -> {

                gpData.getChildren().clear();
                gpData.setGridLinesVisible(true);
                ArrayList<String> sArray = new ArrayList<>();

                for(int i=0;i<allProps.size();i++) {                
                    String arrayName=allProps.get(i).getName();
                    int arrayYear=allProps.get(i).getYear();
                    int selectedYear=Integer.parseInt(cbYear.getValue());

                    if (OwnerWho.getName().equals(arrayName) && arrayYear==selectedYear) {
                        String sProp = allProps.get(i).toStringCsv();
                        sArray.add(sProp);
                    }
                }

                String line;
                // gpData.getChildren().clear();

                for(int i=0;i<sArray.size();i++) {
                    line=sArray.get(i);
                    String[] values=line.split(",");
                    for(int j=0;j<values.length;j++) {
                        Text row = new Text(values[j]);
                        row.setFont(Font.font("Arial", FontWeight.LIGHT, 15));
                        gpData.add(row, j, i+1);
                    }

                }
                //grid pane for tax data
                gpData.setHgap(10);
                gpData.setVgap(0);
                gpData.setPadding(new Insets(0,10,0,10));
                gpData.setGridLinesVisible(true);

            
                String[] firstRow=getFirstRow(path);
                for(int i=0;i<firstRow.length;i++) {
                    Text cols = new Text(firstRow[i]);
                    cols.setFont(Font.font("Arial", FontWeight.BOLD,15));
                    gpData.add(cols,i,0);

                }


            });


        int height=20;
        int width=100;

        btAdd.setMinSize(width,height);
        btPay.setMinSize(width, height);
        lbSelectYear.setMinSize(width-40,height);
        cbYear.setMinSize(width,height);

        UI.setTop(hBox);
        UI.setCenter(gpData);

        Scene scene = new Scene(UI);
        primaryStage.setTitle("Welcome "+OwnerWho.getName());
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

                    AddProperty.start(primaryStage);
                }
            });
        //
        btPay.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent e){

                    primaryStage.hide();

                    ChooseProp.start(primaryStage);
                }
            });
       

    }

    //is used to display the data for each property
    public static String[] getFirstRow(String path) {
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
    //    public static ArrayList<Text> getPropNameYear( String path,String name, int year){
    //        ArrayList<PropertyOb> allP=dataCVS.allProperties(path);
    //        // ArrayList<String> allPString = new ArrayList<>();
    //        // for(int i=0;i<allP.size();i++){
    //        //     allPString.add(allP.get(i).toString());
    //        // }
    //        ArrayList<Text> tArray = new ArrayList<>();
    //        for(int i=0;i<allP.size();i++) {
    //            String arrayName=allProps.get(i).getName();
    //            int arrayYear=allProps.get(i).getYear();
    //            if (name.equals(arrayName) && arrayYear==year) {
    //                Text tProp = new Text(allProps.get(i).toStringCsv());
    //
    //                tArray.add(tProp);
    //            }
    //        }
    //        return tArray;
    //    }

    //    public static void main(String[] args) {
    //        String path = "C:\\Users\\Hannah\\IdeaProjects\\TaxManagment\\src\\com\\company\\SystemData.csv";
    //        ArrayList<PropertyOb> allP=dataCVS.allProperties(path);
    //
    //        System.out.print(allP.get(1).getName());
    //        ArrayList<Text> t=getPropNameYear(path,"Hannah",2020);
    //        System.out.println(t);
    //    }
}
