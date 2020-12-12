
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

 /**
 * @author Ellen McWey
 * @version 9/12/2020
 */

public class DisplayPropOwnData {
     private static TableView table = new TableView();
    private static String path="C:\\Users\\User\\Documents\\NetBeansProjects\\HannahsCode\\src\\SystemData.csv";
    private static String name=PropOwnData.getOwnersName();
    
     public static ArrayList<String> propString(String path) {
        ArrayList<String> propString =new ArrayList<>();
        String line;
        try {
            BufferedReader br=new BufferedReader(new FileReader(path));
            while((line=br.readLine())!=null){

                propString.add(line+"\n");

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return propString;

 }
     //method to make an array list type property of all properties in file,
    public static ObservableList<Property> allProperties(String path) {
        final ObservableList<Property> obsProperties = FXCollections.observableArrayList();
        ArrayList<String> s = propString(path);
        s.remove(0);// REMOVE THE LINE OF HEADINGS AND NOT PROPERTY OBJECTS
        
        for(int i=0;i<s.size();i++){
                    String[] v=s.get(i).split(",");//Note how i increases, 
                   
                    if(name.equals(v[1])){
                    int propNum=Integer.parseInt(v[0]);
                    double value = Double.parseDouble(v[4]);
                    int year = Integer.parseInt(v[7]);
                    double v8=Double.parseDouble(v[8]);
                    double v9=Double.parseDouble(v[9]);
                    double v10=Double.parseDouble(v[10]);
                    double v11=Double.parseDouble(v[11]);
                    double v12=Double.parseDouble(v[12]);
                   
                    Property prop = new Property(propNum, v[1], v[2], v[3],value,v[5],v[6], year, v8, v9,v10,v11,v12);
                    System.out.println(prop.toString());
                   obsProperties.add(prop);
                    
                    }
        }
        return obsProperties;
    }
    //int propNumb,String name, String address, String eircode, double value, String principal, String location,
                      // int year, double currentTax, double overdueTax, double totalTax, double amountPaid, double balance)
     public static void start(Stage primaryStage)
    {
        //Creates an observableList of properties with this eircode
        ObservableList<Property> propertiesWithThisOwner=allProperties(path); 
        
        //Label above the table
        Label pData=new Label("Payment data for property owner: " +name);
        
        TableColumn yearHeading = new TableColumn("Year");
        yearHeading.setMinWidth(100);
        yearHeading.setCellValueFactory(new PropertyValueFactory<Property,String>("year"));
        
         TableColumn addressHeading = new TableColumn("Address");
        addressHeading.setMinWidth(100);
        addressHeading.setCellValueFactory(new PropertyValueFactory<Property,String>("address"));
        
         TableColumn eircodeHeading = new TableColumn("Eircode");
        eircodeHeading.setMinWidth(100);
        eircodeHeading.setCellValueFactory(new PropertyValueFactory<Property,String>("eircode"));
        
        TableColumn princHeading = new TableColumn("Principal");
        princHeading.setMinWidth(100);
        princHeading.setCellValueFactory(new PropertyValueFactory<Property,String>("principal"));
        
        TableColumn currentYearHeading = new TableColumn("Current Year Tax");
        currentYearHeading.setMinWidth(100);
        currentYearHeading.setCellValueFactory(new PropertyValueFactory<Property,String>("currentTax"));
        
        TableColumn overdueTaxHeading = new TableColumn("Overdue Tax");
        overdueTaxHeading.setMinWidth(100);
        overdueTaxHeading.setCellValueFactory(new PropertyValueFactory<Property,String>("overdueTax"));
        
        TableColumn totalTaxHeading = new TableColumn("Total Tax");
        totalTaxHeading.setMinWidth(100);
        totalTaxHeading.setCellValueFactory(new PropertyValueFactory<Property,String>("totalTax"));
        
        TableColumn amountPaidHeading = new TableColumn("Amount Paid");
        amountPaidHeading.setMinWidth(100);
        amountPaidHeading.setCellValueFactory(new PropertyValueFactory<Property,String>("amountPaid"));
        
        TableColumn balanceHeading = new TableColumn("Balance");
        balanceHeading.setMinWidth(100);
        balanceHeading.setCellValueFactory(new PropertyValueFactory<Property,String>("balance"));
        
        table.setItems(propertiesWithThisOwner);
        table.getColumns().addAll(yearHeading, addressHeading,eircodeHeading,princHeading,currentYearHeading, overdueTaxHeading, totalTaxHeading,amountPaidHeading,balanceHeading);
           final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(pData,table);
 
        Scene scene = new Scene(new Group());
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
        
 
        primaryStage.setTitle("Property Charge Management System"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.setWidth(1000);
        primaryStage.setHeight(500);
       // primaryStage.setWidth(screenWidth);
        //.setHeight(screenHeight);
        primaryStage.show(); // Display the stage
        
      
        
    }
     
}
