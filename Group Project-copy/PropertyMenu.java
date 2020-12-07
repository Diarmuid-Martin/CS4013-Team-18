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
    private String name;
    private String eircode;
    Property property = new Property(name,100000,"pAddress","pPostcode", 2000,"C", "Y");

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

    public void registerProperty()
    {
        System.out.println("Please Enter your Address:");
        String address = in.nextLine().toUpperCase();

        System.out.println("Please Enter your Eircode:");
        String eircode = in.nextLine().toUpperCase();

        System.out.println("Please Enter the value of your House:");
        double value = in.nextDouble();

        System.out.println("Please Enter your Location: ");
        System.out.println("(C)ity, (L)arge Town, (S)mall Town, (V)illage, Count(R)yside ");
        String location = in.nextLine().toUpperCase();

        System.out.println("Please Enter year Created:");
        int yearCreated = in.nextInt();

        System.out.println("Principal Private Residence");
        String ppr = in.nextLine();

        Property newProperty = new Property(name,value,address,eircode,yearCreated,location,ppr);
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
                property.getSpecificProperty(eircode);  
            }

            else if (input.equals("O"))
            {
                property.getOwnersProperties(name);
            }
            else if (input.equals("Y"))
            {
                /**
                 * get a list of all overdue property tax for a particular year
                 * 
                 */
                // property.getAllProperties().calcOverDueTax();
            }
            else if (input.equals("E"))
            {
                /**
                 * calc overdue tax by eircode routing key (E41)
                 */
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
                property.getOwnersProperties(name);

            }
            else if (input.equals("T"))
            {
                /**
                 * view tax due per property
                 */
                property.getOwnersProperties(name);

            }
            else if (input.equals("O"))
            {
                /**
                 * overdue tax
                 */
                property.getOwnersProperties(name);

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
                property.getRoutingKey(routingKey);
            }
            else if (input.equals("A"))
            {
                System.out.println("Please Enter Routing Key :");
                String routingKey = in.nextLine().toUpperCase();
                property.getRoutingKeyAvg(routingKey);
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

}