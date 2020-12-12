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
* 
* 
* I think I should read in the 4th value in each comma separated row(the eircode)
* and compare it to the eircode i have 
* If it is equal to the eircode read in the values for year, current year tax, overdue tax,total tax, amount paid and balance
* And display these values in a table
 * @author Ellen McWey
 * @version 9/12/2020
 */
public class DisplayPropData
{
    private static TableView table = new TableView();
    private static String path="C:\\Users\\User\\Documents\\NetBeansProjects\\HannahsCode\\src\\SystemData.csv";
    private static String eircode=PropData.getTfEircode();
 //getPropertyPaymentsByEircode
    
    /**
     * I am going to read in all the properties in my csv file as a string 
     * and make it into a String array
     * @param path
     * @return 
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
     //method to make an array list type property of all properties in file,
    public static ObservableList<Property> allProperties(String path) {
        final ObservableList<Property> obsProperties = FXCollections.observableArrayList();;
        ArrayList<Property> p = new ArrayList<>();
        ArrayList<String> s = propString(path);
        s.remove(0);//removes the property number bc we dont need it to make a 
        //property object OR IS THIS WHERE WE REMOVE THE LINE OF HEADINGS AND 
        //NOT PROPERTY OBJECTS
        for(int i=0;i<s.size();i++){
                    String[] v=s.get(i).split(",");//Note how i increases, 
                   
                    if(eircode.equals(v[3])){
                    int propNum=Integer.parseInt(v[0]);
                    double value = Double.parseDouble(v[4]);
                    int year = Integer.parseInt(v[7]);
                    double v8=Double.parseDouble(v[8]);
                    double v9=Double.parseDouble(v[9]);
                    double v10=Double.parseDouble(v[10]);
                    double v11=Double.parseDouble(v[11]);
                    double v12=Double.parseDouble(v[12]);
                     
                    
                    Property prop = new Property(propNum, v[1], v[2], v[3],value,v[5],v[6], year, v8, v9,v10,v11,v12);
                    //System.out.println(prop.toString());
                   obsProperties.add(prop);
                    
                    }
        }
        return obsProperties;
    }
    
    public static void start(Stage primaryStage)
    {
        //Creates an observableList of properties with this eircode
        ObservableList<Property> propertiesWithThisEircode=allProperties(path); 
        
        //Label above the table
        Label pData=new Label("Payment data for property located at " +eircode);
        
        TableColumn yearHeading = new TableColumn("Year");
        yearHeading.setMinWidth(100);
        yearHeading.setCellValueFactory(new PropertyValueFactory<Property,String>("Year"));
        
        TableColumn ownerHeading = new TableColumn("Owner");
        ownerHeading.setMinWidth(100);
       ownerHeading.setCellValueFactory(new PropertyValueFactory<Property,String>("name"));
        
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
        
        table.setItems(propertiesWithThisEircode);
        table.getColumns().addAll(yearHeading,ownerHeading, currentYearHeading, overdueTaxHeading, totalTaxHeading,amountPaidHeading,balanceHeading);
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
