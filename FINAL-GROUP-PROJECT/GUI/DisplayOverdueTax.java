
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
  * A class that creates a GUI which displays overdue tax in a table for a particular
  * year that the user enters.
  * There is also the option to select an area based on the routing key of the
  * eircode
 * @author Ellen McWey
 * @version 9/12/2020
 */

public class DisplayOverdueTax {
    private static TableView table = new TableView();
    private static String path = FindFile.FindPath("SystemData.csv");
    private static int year=OverDueTaxGUI.getYear();
    private static Boolean isChecked= OverDueTaxGUI.isChecked();
    private static String routingKey= OverDueTaxGUI.getRoutingKey();
    
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
        }

        return propString;

 }
     /**
       * A method which converts the Eircode to a routing key.
       * @param eircode
       * @return the routing key
       */
         public static String convertToRoutingKey(String eircode){
        String result =  eircode.substring(0,3); 
        return result;
    }

         
        /**
     * A method to make an ArrayList of type property of all properties in the file
     * with the same routing key as the routing key the user entered.
     * I did this by using an if statement which compares the routing key the
     * user entered to the Property object's routing key, which I got using my 
     * convertToRoutingKey method and if these are equal,
     * it adds the Property to the ArrayList.
     * @param path, the path of the file that I want to create the ArrayList of
     * @return an ObservableList of Property objects with this routing key
     */
    public static ObservableList<Property> allProperties(String path) {
        final ObservableList<Property> obsProperties = FXCollections.observableArrayList();
        ArrayList<String> s = propString(path);
        s.remove(0);
        
        for(int i=0;i<s.size();i++){
                    String[] v=s.get(i).split(","); 
                    double v12=Double.parseDouble(v[12]);
                    int year1 = Integer.parseInt(v[7]);
                   
                    if(year==year1&&v12>0){
                    int propNum=Integer.parseInt(v[0]);
                    double value = Double.parseDouble(v[4]);
                    double v8=Double.parseDouble(v[8]);
                    double v9=Double.parseDouble(v[9]);
                    double v10=Double.parseDouble(v[10]);
                    double v11=Double.parseDouble(v[11]);
                    
                   if(isChecked){
                       if(routingKey.equals(convertToRoutingKey(v[3]))){
                           Property prop = new Property(propNum, v[1], v[2], v[3],value,v[5],v[6], year1, v8, v9,v10,v11,v12);
                           System.out.println(prop.toString());
                            obsProperties.add(prop);
                       }
                   }else{
                    Property prop = new Property(propNum, v[1], v[2], v[3],value,v[5],v[6], year, v8, v9,v10,v11,v12);
                  
                   obsProperties.add(prop);
                   }
                }
        }
        return obsProperties;
    }
    
    /**
     * A start method which creates an ObservableList of objects of type Property,
     * creates a table with the relevant headings and populates it with the 
     * relevant data.
     * It adds this table to the Scene, then adds the Scene to the Stage
     * and displays the contents of the Stage
     * @param primaryStage, the stage that is created by the platform itself
     */
     public static void start(Stage primaryStage)
    {
        //Creates an observableList of properties with this eircode
        ObservableList<Property> propertiesWithOverdueTax=allProperties(path); 
        Scene scene;
        
        
        //Label above the table
        Label pData=new Label("Overdue tax data for year " +year);
        
        Button btCancel = new Button("Cancel");
        
        if(!propertiesWithOverdueTax.isEmpty()){

        TableColumn yearHeading = new TableColumn("Year");
        yearHeading.setMinWidth(100);
        yearHeading.setCellValueFactory(new PropertyValueFactory<Property,String>("Year"));
        
        TableColumn ownerHeading = new TableColumn("Owner");
        ownerHeading.setMinWidth(100);
       ownerHeading.setCellValueFactory(new PropertyValueFactory<Property,String>("name"));
        
        
        TableColumn addressHeading = new TableColumn("Address");
        addressHeading.setMinWidth(100);
        addressHeading.setCellValueFactory(new PropertyValueFactory<Property,String>("address"));
        
         TableColumn eircodeHeading = new TableColumn("Eircode");
        eircodeHeading.setMinWidth(100);
        eircodeHeading.setCellValueFactory(new PropertyValueFactory<Property,String>("eircode"));
        
        TableColumn princHeading = new TableColumn("Principal");
        princHeading.setMinWidth(100);
        princHeading.setCellValueFactory(new PropertyValueFactory<Property,String>("principal"));
        
        TableColumn balanceHeading = new TableColumn("Overdue tax at the end of this year");
        balanceHeading.setMinWidth(250);
        balanceHeading.setCellValueFactory(new PropertyValueFactory<Property,String>("balance"));
        
        table.setItems(propertiesWithOverdueTax);
        table.getColumns().addAll(yearHeading,ownerHeading,addressHeading,eircodeHeading,princHeading, balanceHeading);
        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(pData,btCancel,table);
 
        scene = new Scene(new Group());
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
        }else{
            
            Label oOT=new Label("There is no overdue tax for this year.");
            VBox vbox2 = new VBox(pData,oOT);
            scene = new Scene(vbox2, 200, 100);
        }
         
        btCancel.setOnAction((ActionEvent e) -> {
            primaryStage.hide();
            AdminUser.start(primaryStage);
        }); 
 
        primaryStage.setTitle("Property Charge Management System"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.setWidth(1000);
        primaryStage.setHeight(500);
        primaryStage.show(); // Display the stage
        
      
        
    }
     
}
