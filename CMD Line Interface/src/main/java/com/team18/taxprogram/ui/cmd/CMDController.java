
/**
* @author(Liam Ryan)
**/
package com.team18.taxprogram.ui.cmd;

import com.team18.taxprogram.model.Location;
import com.team18.taxprogram.model.Model;
import com.team18.taxprogram.model.Owner;
import com.team18.taxprogram.model.Property;
import com.team18.taxprogram.ui.Controller;
import com.team18.taxprogram.accounting.Statement;
import com.team18.taxprogram.io.InputChecking;

import java.io.IOException;
import java.util.*;

public class CMDController implements Controller {
    Model model;
    private Scanner in;

    /**
    * Constructor
    * @param model
    **/
    public CMDController(Model model) {
        this.model = model;
        in = new Scanner(System.in);
    }

    /**
    * Beginning of Command Line Interface
    * Allows you to Be an Owner / Aministrator
    * @return void
    **/
    @Override
    public void main() {
        boolean more = true;
        while (more) {
            System.out.println("A)dmin  O)wner  Q)uit");
            String input = InputChecking.getOption(in);

            switch (input) {
                case "A":
                    adminOptions();
                    break;
                case "O":
                    ownerLoginPage();
                    break;
                case "Q":
                    more = false;
                    try {
                        model.save();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    System.out.println("Invalid inputs");
            }
        }
    }

    /**
    * Administrator Menu
    * @return void
    **/
    @Override
    public void adminOptions() {
        boolean more = true;
        while (more) {
            System.out.println("(P)-Property Tax data : Property");
            System.out.println("(O)-Property Tax data : Owner");
            System.out.println("(Y)-Overdue Property Tax : Year");
            System.out.println("(E)-Overdue Property Tax : Routing Key");
            System.out.println("(S)-Property Tax Statistics : Routing Key");
            System.out.println("(Q)-Quit");

            String input = InputChecking.getOption(in);
            Statement s = null;
            switch (input) {
                case "P":
                    System.out.println("Please Enter Eircode");
                    String eircode = InputChecking.getEircode(in);
                    Property prop = model.getSpecificProperty(eircode);
                    if(prop == null){
                        System.out.println("Property doesn't exist");
                        break;
                    }
                    s = model.balancingStatementProperty(prop);
                    displayStmt(s);
                    break;

                case "O":
                    System.out.println("Please Enter Owners Name");
                    String ownerName = InputChecking.getOwnerName(in);
                    Owner owner = model.getOwner(ownerName);
                    List<Property> props = model.getOwnersProperties(owner);
                    s = model.taxDue(props);
                    displayStmt(s);
                    break;                

                case "Y":
                    int year = selectYear();
                    s = model.getTotalDueTax(year);
                    displayStmt(s);
                    break;
                    
                case "E":
                    String key1 = selectRoutingKey();
                    s = model.getRoutingKeyStatement(key1);
                    displayStmt(s);
                    break;

                case "S":
                    String key2 = selectRoutingKey();
                    s =  model.statsOnRoutingKey(key2);
                    displayStmt(s);
                    break;
                case "Q":
                    more = false;
                    break;
            }
        }
    }

    /**
    * The owner inserts their name and runs the owners page
    * @return void
    **/
    @Override
    public void ownerLoginPage() {
        System.out.println("Please Enter your name: ");
        String name = InputChecking.getOwnerName(in);
        Owner owner = model.getOwner(name);
        ownerPage(owner);
    }

    
    /**
    * The Owners Page, Allows you to undertake
    * a variety of tasks
    * @param owner
    * @return void
    **/    
    @Override
    public void ownerPage(Owner owner) {
        boolean more = true;
        while (more) {
            System.out.println("(R)-Register Property");
            System.out.println("(P)-Pay Tax");
            System.out.println("(V)-View List of your Properties");
            System.out.println("(T)-View Tax due per Property this Year");
            System.out.println("(O)-View Total Overdue Property Tax");
            System.out.println("(B)-Balancing Statement");
            System.out.println("(Q)-Quit");

            String input = InputChecking.getOption(in);
            int year = 0;
            List<Property> props = model.getOwnersProperties(owner);
            switch (input) {
                case "R":
                    registerNewProperty(owner);
                    break;
                case "P":
                    Property prop = selectProperty(props);
                    System.out.println("Enter how much are you paying:");
                    double amount = InputChecking.getDouble(in);
                    year = selectYear();
                    model.addTransaction(owner, prop, amount, year);
                    break;
                case "V":
                    displayProperties(props);
                    break;
                case "T":
                    Statement stmtTax = model.taxDue(props);
                    displayStmt(stmtTax);
                    break;

                case "O":
                    Statement stmtOverdueTax = model.taxOverdue(props);
                    displayStmt(stmtOverdueTax);
                    break;

                case "B":
                    System.out.println("(Y)-Year");
                    System.out.println("(P)-Property");
                    String type = InputChecking.getOption(in);
                    switch (type) {
                        case "Y":
                            year = selectYear();
                            Statement stmtYear = model.balancingStatementYear(owner, year);
                            displayStmt(stmtYear);
                            break;
                        case "P":
                            Property p = selectProperty(props);
                            Statement stmtProp = model.balancingStatementProperty(p);
                            displayStmt(stmtProp);
                            break;
                        default:
                            System.out.println("Invalid option");
                    }
                    break;
                case "Q":
                    more = false;
                    break;
                default:
                    System.out.println("Not a valid command");
            }
        }
    }

    /**
    * Method to register a property
    * @param owner
    * @return void
    **/
    @Override
    public void registerNewProperty(Owner owner) {
        ArrayList<Owner> owners = new ArrayList<>();
        owners.add(owner);
        System.out.println("How many additional owners?");

        int num_owners = InputChecking.getInt(in);
        for(int i = 0; i < num_owners; i++){
            String name = InputChecking.getOwnerName(in);
            Owner additionalOwner = model.getOwner(name);
            owners.add(additionalOwner);
        }
        System.out.println("Address");
        String address = InputChecking.getString(in);

        System.out.println("Eircode");
        String eircode = InputChecking.getEircode(in);

        System.out.println("Location: CITY, LARGE_TOWN, SMALL_TOWN, VILLAGE, COUNTRYSIDE");
        Location location = InputChecking.getLocation(in);
            
        System.out.println("Principle Property: True/False");
        Boolean principle = InputChecking.getBool(in);

        System.out.println("Year");
        int year = InputChecking.getInt(in);

        System.out.println("Value");
        Double value = InputChecking.getDouble(in);

        model.addProperty(new Property(owners, address, eircode, location, principle, year, value));
    }

    /**
    * Selects a property
    * @param props
    * @return Property
    **/
    private Property selectProperty(List<Property> props) {
        int selection = 1;
        while (true) {
            System.out.println("Select a property number");
            System.out.println(props.size());

            selection = InputChecking.getInt(in);
            if (1 <= selection && selection <= props.size()) {
                break;
            }
        }
        return props.get(selection - 1);
    }

    /**
    * Selects a Year for getting data
    * @return int
    **/
    private int selectYear() {
        System.out.println("Select a year");
        int year = InputChecking.getInt(in);
        return year;
    }
    
    /**
    * gets Routing Key
    * @return String
    **/
    private String selectRoutingKey() {
        System.out.println("Please Enter Routing Key");
        String key = InputChecking.getRoutingKey(in);
        return key;
    }
    
    /**
    * Displays the Properties
    * @param props
    * @return void
    **/
    @Override
    public void displayProperties(List<Property> props) {
        for(int i = 0; i < props.size(); i++) {
            System.out.println(props.get(i).toString());
        }
    }

    /**
    * Prints Statement
    * @param stmt
    * @return void
    **/
    @Override
    public void displayStmt(Statement stmt) {
        if(stmt != null){
            System.out.println(stmt.toText());
        }
    }
}
