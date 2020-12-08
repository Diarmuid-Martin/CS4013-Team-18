import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
/**
 * Write a description of class PropertyMenu here.
 *
 * @author (liam)
 * @version (a version number or a date)
 */
public class PropertyMenu
{
    private Scanner in;
    private String name;// = null;
    private String eircode;//=null;
    // private String address=null;
    // private int yearCreated=0;
    // private String ppr=null;
    // private double value=0;
    // private String location=null;
    Property property = new Property();
    PropertyTaxCalculator calc = new PropertyTaxCalculator();
    // private ArrayList<Property> allProperties=new ArrayList<>();    

    public PropertyMenu()
    {
        in = new Scanner(System.in)  ;
    }

    public void run()
    throws IOException
    {
        boolean more = true;
        while(more)
        {
            System.out.println("A)dmin  O)wner  Q)uit");
            String input = in.nextLine().toUpperCase();

            if (input.equals("A"))
            {
                adminOptions();   
            }
            else if (input.equals("O"))
            {
                ownerName();   
            }
            else if (input.equals("Q"))
            {
                more = false;
            }
        }
    }

    public void ownerName()
    {
        System.out.println("Please Enter your name: ");
        name = in.nextLine().toUpperCase();//uppercase just so all names are the same for comparing and getting owners properties
        ownerOptions();
    }

    public void ownerOptions()
    {
        boolean more = true; 
        while(more)
        {
            System.out.println("(R)-Register Property");
            System.out.println("(P)-Pay Tax");
            System.out.println("(V)-View List of your Properties");
            System.out.println("(T)-Tax due per Property");
            System.out.println("(O)-Overdue Property Tax");
            System.out.println("(B)-Balancing Statement");
            System.out.println("(Q)-Quit");
            // investigate.....???
            String input = in.nextLine().toUpperCase();
            if (input.equals("R"))
            {
                registerProperty();
            }
            else if (input.equals("P"))
            {
                /**
                 * payTax
                 */
                System.out.println("Enter how much are you paying:");
                double amount = in.nextInt();
                property.payTaxOnThisProperty(amount);
            }
            else if (input.equals("V"))
            {
                /**
                 * View your Properties
                 */
                System.out.print(property.getOwnersProperties(name));
                //System.out.print(property.getAllProperties().toString());
            }
            else if (input.equals("T"))
            {
                /**
                 * view tax due per property
                 */ 
                property.getOwnersProperties(name);
                //for each property in this array list, get tax due
            }
            else if (input.equals("O"))
            {
                /**
                 * overdue tax
                 */
                property.getOwnersProperties(name);//.calc.calcOverdueTax();;

            }
            else if (input.equals("B"))
            {
                balancingStatement();
            }
            else if (input.equals("Q"))
            {
                more= false;
            }
            //else throw exception
        }
    }

    public void registerProperty()
    {
        //boolean more = true;
        int i = 0;

        System.out.println("Please Enter your Eircode:");
        String postcode = in.nextLine().toUpperCase();     

        System.out.println("Please Enter year Created:");
        String sYearCreated = in.nextLine();
        int yearCreated = Integer.parseInt(sYearCreated);

        System.out.println("Please Enter the value of your House:");
        String sValue = in.nextLine();
        double value = Double.valueOf(sValue);

        System.out.println("Please Enter your Location : C)ity, (L)arge Town, (S)mall Town, (V)illage,(R)Countryside");
        //System.out.println("(C)ity, (L)arge Town, (S)mall Town, (V)illage,(R)Countryside ");
        String location = in.nextLine().toUpperCase();

        System.out.println("Please Enter your Address:");
        String address = in.nextLine();//.toUpperCase();

        System.out.println("Please Enter Principal Private Residence (Y/N)");
        String ppr = in.nextLine().toUpperCase();

        Property newProperty = new Property(name,value,address,postcode,yearCreated,location,ppr);
        property.getAllProperties().add(new Property(name,value,address,postcode,yearCreated,location,ppr));
        //System.out.print(property.toString());
    }

    public void adminOptions()
    {
        boolean more = true; 
        while(more)
        {
            System.out.println("(P)-Property Tax data : Property");
            System.out.println("(O)-Property Tax data : Owner");
            System.out.println("(Y)-Overdue Property Tax : Year");
            System.out.println("(E)-Overdue Property Tax : Eircode");
            System.out.println("(S)-Property Tax Statistics : Eircode");
            System.out.println("(Q)-Quit");

            //Property property = new Property("pName",100000,"pAddress","pPostcode", 2000,"C", "Y");

            // investigate.....???
            String input = in.nextLine().toUpperCase();
            if (input.equals("P"))
            {
                System.out.println("Please Enter Eircode");
                eircode = in.nextLine().toUpperCase();
                System.out.println(property.getSpecificProperty(eircode));  
            }

            else if (input.equals("O"))
            {
                System.out.println("Please Enter Owners Name");
                String ownerName = in.nextLine().toUpperCase();
                property.getOwnersProperties(ownerName);
            }
            else if (input.equals("Y"))
            {
                /**
                 * get a list of all overdue property tax for a particular year
                 * 
                 */
                System.out.println("Please Enter a year you wish to get the tax for: ");
                String sYear = in.nextLine();
                int yr;
                try
                {
                    yr = Integer.parseInt(sYear);
                }
                catch(PropertyException e)
                {
                    System.out.println("Invalid Input");
                }
                /**
                 * fix this
                 */
                //calc.getPropByYear(property,yr);
            }
            else if (input.equals("E"))
            {
                /**
                 * calc overdue tax by eircode routing key (E41)
                 */                
                System.out.println("Please Enter Routing Key");
                String key = in.nextLine().toUpperCase();
                property.getRoutingKeyTotalTax(key);
                System.out.println(property.getRoutingKeyTotalTax(key));
            }
            else if (input.equals("S"))
            {
                statsOnRoutingKey();
            }
            else if (input.equals("Q"))
            {
                more= false;
            }
        }
    }

    public void balancingStatement()
    {
        boolean more = true;
        while(more)
        {
            System.out.println("(Y) Balancing Statement for year");
            System.out.println("(P) Balancing Statement for property");
            String input = in.nextLine().toUpperCase();
            if (input.equals("Y"))
            {
                System.out.println("Balancing Statement for year, to be completed");
            }
            else if (input.equals("P"))
            {
                System.out.println("Balancing Statement for property, to be completed");

            }
        }
    }

    public void statsOnRoutingKey()
    {
        boolean more = true; 
        while(more)
        {
            System.out.println("Stats based on routing key");
            System.out.println("T)otal Tax Paid");
            System.out.println("A)verage Tax Paid");
            System.out.println("N)umber of Property Taxes Paid");
            System.out.println("P)ercent of Property Taxes Paid");
            System.out.println("Q)uit");
            String input = in.nextLine().toUpperCase();
            if (input.equals("T"))
            {
                System.out.println("Please Enter Routing Key :");
                String routingKey = in.nextLine().toUpperCase();
                property.getRoutingKeyTotalTax(routingKey);
                System.out.println(property.getRoutingKeyTotalTax(routingKey));
            }
            else if (input.equals("A"))
            {
                System.out.println("Please Enter Routing Key :");
                String routingKey = in.nextLine().toUpperCase();
                property.getRoutingKeyAvg(routingKey);
                 System.out.println(property.getRoutingKeyAvg(routingKey));
            }
            else if (input.equals("N"))
            {

            }
            else if (input.equals("P"))
            {

            }
            else if (input.equals("Q"))
            {
                more= false;
            }
            //else throw exception

        }
    }

    //  @Override
    // public String toString()
    // {
    // return   name + value + address + postcode + yearCreated + location + ppr; 
    // }
}