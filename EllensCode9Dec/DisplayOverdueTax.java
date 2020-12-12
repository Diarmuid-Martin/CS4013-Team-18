
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

public class DisplayOverdueTax {
    private static TableView table = new TableView();
    private static String path="C:\\Users\\User\\Documents\\NetBeansProjects\\HannahsCode\\src\\SystemData.csv";
    private static int year=OverDueTaxGUI.getYear();
    private static Boolean isChecked= OverDueTaxGUI.isChecked();
    private static String routingKey= OverDueTaxGUI.getRoutingKey();
    
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
     
         public static String convertToRoutingKey(String eircode){
        String result =  eircode.substring(0,3); 
        return result;
    }

         
     //method to make an array list type property of all properties in file,
    public static ObservableList<Property> allProperties(String path) {
        final ObservableList<Property> obsProperties = FXCollections.observableArrayList();
        ArrayList<String> s = propString(path);
        s.remove(0);// REMOVE THE LINE OF HEADINGS AND NOT PROPERTY OBJECTS
        
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
                    //System.out.println(prop.toString());
                   obsProperties.add(prop);
                   }
                }
        }
        return obsProperties;
    }
    
     public static void start(Stage primaryStage)
    {
        //Creates an observableList of properties with this eircode
        ObservableList<Property> propertiesWithOverdueTax=allProperties(path); 
        Scene scene;
        
        
        //Label above the table
        Label pData=new Label("Overdue tax data for year " +year);
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
        vbox.getChildren().addAll(pData,table);
 
        scene = new Scene(new Group());
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
        }else{
            
            Label oOT=new Label("There is no overdue tax for this year.");
            VBox vbox2 = new VBox(pData,oOT);
            scene = new Scene(vbox2, 200, 100);
        }
            
 
        primaryStage.setTitle("Property Charge Management System"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.setWidth(1000);
        primaryStage.setHeight(500);
       // primaryStage.setWidth(screenWidth);
        //.setHeight(screenHeight);
        primaryStage.show(); // Display the stage
        
      
        
    }
     
}
