import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Main method that launches the GUI
 * @author Diarmuid Martin
 */

public class OpeningScreen extends Application {

    @Override
    public void start(Stage primaryStage) {
        GridPane UI = new GridPane();
        UI.setHgap(100);
        UI.setVgap(50);
        UI.setPadding(new Insets(10, 15, 20, 25));
        UI.setAlignment(Pos.CENTER);

        int height=50;
        int width=200;

        Button Admin = new Button("Admin User");
        Admin.setMinSize(width,height);

        Button PropOwn = new Button("Property Owner");
        PropOwn.setMinSize(width,height);

        UI.add(Admin, 0,0);
        UI.add(PropOwn, 0,1);

        Scene scene = new Scene(UI);
        primaryStage.setTitle("Property Charge Management System");
        primaryStage.setScene(scene);
        primaryStage.setWidth(400);
        primaryStage.setHeight(300);
        primaryStage.show();

        Admin.setOnAction(new EventHandler<ActionEvent >() {
                @Override
                public void handle(ActionEvent e){

                    primaryStage.hide();
                    //starts the next GUI owner
                    AdminUser.start(primaryStage);
                }
            });
        //This EventHandler is how we go into the Owner GUI
        PropOwn.setOnAction(new EventHandler<ActionEvent >() {

                @Override
                public void handle(ActionEvent e){

                    primaryStage.hide();
                    //starts the next GUI owner
                    OwnerWho.start(primaryStage);
                }
            });

    }

    //NOT NEEDED FOR RUNNING FROM THE COMMAND LINE/IDE SUCH AS NETBEANS

    public static void main(String[] args) {
        launch(args);
    }

}

