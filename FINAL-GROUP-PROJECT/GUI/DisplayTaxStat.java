import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.geometry.*;


 /**
  * A class which displays property tax statistics for a particular area
  * based on the routing key of the eircode.
 * @author Ellen McWey
 * @version 9/12/2020
 */

public class DisplayTaxStat {
    private static TableView table = new TableView();
    private static String path = FindFile.FindPath("SystemData.csv");
    private static String routingKey= TaxStat.getRoutingKey();

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
      * Returns the average tax paid.
      * @param propertiesWithThisRoutingK, the ObservableList of Property objects
      * with this routing key
      * @return the average tax paid
      */
     public static double getRoutingKeyAvg(ObservableList<Property> propertiesWithThisRoutingK)
    {
        double sum = 0;
        for(Property prop:propertiesWithThisRoutingK){
            sum+=prop.getAmountPaid();
        }
        return sum/propertiesWithThisRoutingK.size();
    }
     
      /**
      * Returns the total tax paid
      * @param propertiesWithThisRoutingK, the ObservableList of Property objects
      * with this routing key
      * @return the total tax paid
      */
      public static double getTotalTaxPaidRK(ObservableList<Property> propertiesWithThisRoutingK)
    {
        double sum = 0;
        for(Property prop:propertiesWithThisRoutingK){
            sum+=prop.getAmountPaid();
        }
        return sum;
    }
      
      /**
       * A method which returns the number of properties which have paid their 
       * property tax
       * @param propertiesWithThisRoutingK, the ObservableList of Property objects
       * @return number of properties where balance is 0
       */
       public static int numberOfPropTaxPaid(ObservableList<Property> propertiesWithThisRoutingK){
            int i=0;
           for(Property prop:propertiesWithThisRoutingK){
               if(prop.getBalance()==0){
                   i++;
               }
             
           }
           return i;
       }
       
       /**
        * A method which returns the percentage of properties which have paid their 
       * property tax
        * @param propertiesWithThisRoutingK the ObservableList of Property objects
       * @return percentage of properties where balance is 0
        */
       public static double percentOfPropTaxPaid(ObservableList<Property> propertiesWithThisRoutingK){
            double i=0.0;
           for(Property prop:propertiesWithThisRoutingK){
               if(prop.getBalance()==0){
                   i++;
               }
             
           }
           double percent=i/propertiesWithThisRoutingK.size()*100;
           return percent;
       }
      
       
       /**
        * A method which prints out the properties with this routing key,
        * I made it just to check if my GUI's output was correct.
        * @param propertiesWithThisRoutingK,the ObservableList of Property objects
        */
      public static void printOutObs(ObservableList<Property> propertiesWithThisRoutingK){
          for(Property prop:propertiesWithThisRoutingK){
            prop.toString();
        }
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
     * convertToRoutingKey method and if these are equal 
     * it adds the Property to the ArrayList.
     * @param path, the path of the file that I want to create the ArrayList of
     * @return an ObservableList of Property objects with this routing key
     */
    public static ObservableList<Property> allProperties(String path) {
        final ObservableList<Property> obsProperties = FXCollections.observableArrayList();
        ArrayList<String> s = propString(path);
        s.remove(0);
        for (String item : s) {
            String[] v = item.split(",");
            
            if(routingKey.equals(convertToRoutingKey(v[3]))){
                double v12=Double.parseDouble(v[12]);
            int propNum=Integer.parseInt(v[0]);
            double value = Double.parseDouble(v[4]);
            int year = Integer.parseInt(v[7]);
            double v8=Double.parseDouble(v[8]);
            double v9=Double.parseDouble(v[9]);
            double v10=Double.parseDouble(v[10]);
            double v11=Double.parseDouble(v[11]);
            Property prop = new Property(propNum, v[1], v[2], v[3],value,v[5],v[6], year, v8, v9,v10,v11,v12);;
            obsProperties.add(prop);
            }
        }
        
        return obsProperties;
    }
    
    /**
     * A start method which creates an ObservableList of objects of type Property,
     * creates a table with the relevant headings and populates it with the 
     * relevant data.
     * It adds this table to the Scene, then adds the Scene to the Stage
     * and displays the contents of the Stage.
     * @param primaryStage, the stage that is created by the platform itself
     */
     public static void start(Stage primaryStage)
    {
        //Creates an observableList of properties with this eircode
        ObservableList<Property> propertiesWithThisRoutingKey=allProperties(path); 
        
        TextArea b=new TextArea();
        b.setEditable(false);
        Button btCancel = new Button("Cancel");
        
        StackPane r = new StackPane();
        r.getChildren().add(b); 
        VBox vbox = new VBox(r,btCancel);
        vbox.setAlignment(Pos.CENTER);
        
        Scene scene=new Scene(vbox, 200, 200);
        
        b.appendText("Property tax statistics for the routing key "+routingKey+":\n");
        
        if(!propertiesWithThisRoutingKey.isEmpty()){

        double totalTaxPaid=getTotalTaxPaidRK(propertiesWithThisRoutingKey);
        String taxPS="The total tax paid for the routing key "+routingKey+" is €"+totalTaxPaid+".\n";
        b.appendText(taxPS);
        
        double averTaxPaid=getRoutingKeyAvg(propertiesWithThisRoutingKey);
        String AvtaxPS="The average tax paid for the routing key "+routingKey+" is €"+averTaxPaid+".\n";
        b.appendText(AvtaxPS);
        
        double numberOfPropTaxPaidRK=numberOfPropTaxPaid(propertiesWithThisRoutingKey);
        String numberOfPropTaxPaidS="The number of properties that have paid their property tax is "+numberOfPropTaxPaidRK+".\n";
        b.appendText(numberOfPropTaxPaidS);
        
        double percentOfPropTaxPaidRK=percentOfPropTaxPaid(propertiesWithThisRoutingKey);
        String percentOfPropTaxPaidRKS="The percent of properties that have paid their property tax is "+percentOfPropTaxPaidRK+"%.\n";
        b.appendText(percentOfPropTaxPaidRKS);
        
        }else{
            Label pData=new Label("Property tax statistics for the routing key "+routingKey);
            Label oOT=new Label("There are no properties with this routing key.");
            VBox vbox2 = new VBox(pData,oOT,btCancel);
            scene = new Scene(vbox2, 200, 100);
        }
            
 
        primaryStage.setTitle("Property Charge Management System"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.setWidth(1000);
        primaryStage.setHeight(300);
        primaryStage.show(); // Display the stage
        
      
        btCancel.setOnAction((ActionEvent e) -> {
            primaryStage.hide();
            AdminUser.start(primaryStage);
        }); 
    }
     
}

