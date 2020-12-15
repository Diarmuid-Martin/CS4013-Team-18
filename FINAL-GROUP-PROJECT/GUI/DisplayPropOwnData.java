import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent; 

 /**
  * A class which displays property tax payment data for any property owner.
 * @author Ellen McWey
 * @version 9/12/2020
 */

public class DisplayPropOwnData {
    private static TableView table = new TableView();
    private static String path = FindFile.FindPath("SystemData.csv");
    private static String name=PropOwnData.getOwnersName();
    
    /**
     * A method to read in all the properties in my csv file as a String, which 
     * is specified by passing in the path as a String 
     * and make it into a String array.
     * @param path, where SystemData.csv is located
     * @return an ArrayList of type String of the contents of SystemData.csv
     */
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
     /**
     * A method to make an ArrayList of type Property of all the properties in 
     * the file with the same Owner's name.
     * Uses an if statement which compares the name the
     * user entered to the Property object's owner's name and if these are equal 
     * it adds the Property to the ArrayList.
     * @param path, the path of the file that I want to create the ArrayList of
     * @return an ObservableList of Property objects with this owner
     */
    public static ObservableList<Property> allProperties(String path) {
        final ObservableList<Property> obsProperties = FXCollections.observableArrayList();
        ArrayList<String> s = propString(path);
        s.remove(0);
        
        for(int i=0;i<s.size();i++){
                    String[] v=s.get(i).split(","); 
                   
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
    
    /**
     * A start method which creates an ObservableList of objects of type Property
     * with this owner,
     * creates a table with the relevant headings and populates it with the 
     * relevant data.
     * It adds this table to the Scene, then adds the Scene to the Stage
     * and displays the contents of the Stage.
     * @param primaryStage, the stage that is created by the platform itself
     */
     public static void start(Stage primaryStage)
    {
        //Creates an observableList of properties with this eircode
        ObservableList<Property> propertiesWithThisOwner=allProperties(path); 
        
        //Label above the table
        Label pData=new Label("Payment data for property owner: " +name);
        
        //Button for going back to the AdminUserScreen
        Button btCancel= new Button("Cancel");
        
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
        vbox.getChildren().addAll(pData,btCancel,table);
 
        Scene scene = new Scene(new Group());
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
        
 
        primaryStage.setTitle("Property Charge Management System"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.setWidth(1000);
        primaryStage.setHeight(500);
        primaryStage.show(); // Display the stage
        
      
       btCancel.setOnAction((ActionEvent e) -> {
            primaryStage.hide();
            AdminUser.start(primaryStage);
        }); 
    }
     
}
