import java.time.LocalDate;
import java.util.ArrayList;

/**
 * A property has a property owner so should property extend PropertyOwner
 * This might make it easier for us to add ownedProperties to the Arraylist
 * of Properties in the PropertyOwner class
 *
 * @author (liam+ellen)
 * @version 6/12/2020
 */
public class Property
{

    private double Value;
    private String Address;
    private String Postcode;
    private String Name;
    private String Ppr;
    private int YearCreated;
    private boolean principle;
    private String location; 

    private int[]PropValue={0,150000,400000,650000}; 
    // note ranges are 0-149999, 150 - 400, 400001-650, 650+ in q
    private double[]TaxRate={0,.01,.02,.04};
    private ArrayList<Property> allProperties=new ArrayList<>();

    //I'm creating an object of PropertyTaxCalculator here so that
    //we can use it to carry out calculations throughout this class
    PropertyTaxCalculator c1=new PropertyTaxCalculator();

    /**
     * Constructor for objects of class Property
     */
    // have to add the ArrayList of owners to the constructor, that is if a 
    //property has more than one owner

    public Property(String pName,double pValue, String pAddress,String pPostcode, int pYearCreated,String pLocation, String Ppr)
    {
        Name = pName;
        Value = pValue;
        Address = pAddress;
        Postcode = pPostcode;
        YearCreated = pYearCreated;//LocalDate.now().getYear();
        location = pLocation;
        allProperties.add(new Property(Name,Value,Address,Postcode,YearCreated,location,Ppr));

    }

    public ArrayList<Property> getAllProperties() {
        return allProperties;
    }

    public void setName(String pName)
    {
        Name = pName;
    }

    public String getName()
    {
        return Name;
    }

    public void setPpr(String pPpr)
    {
        Ppr = pPpr;
    }

    public String getPpr()
    {
        return Ppr;
    }

    public void setValue(double pValue)
    {
        Value = pValue;
    }

    public double getValue()
    {
        return Value;
    }

    public void setYearCreated(int pYearCreated)
    {
        YearCreated = pYearCreated;
    }

    public int getYearCreated()
    {
        return YearCreated;
    }

    public void setAddress(String pAddress)
    {
        Address = pAddress;
    }

    public String getAddress()
    {
        return Address;
    }

    public void setPostcode(String pPostcode)
    {
        Postcode = pPostcode;
    }

    public String getPostcode()
    {
        return Postcode;
    }

    public void setLocation(String pLocation)
    {
        location =pLocation;
    }

    public String getLocation()
    {
        return location;
    }

    public String cutEir()
    {
     String result =  Postcode.substring(0,3); 
     return result;
    }

    //I HOPE THAT this PASSES IN THE CURRENT PROPERTY OBJECT INTO THE METHOD
    /**
     * Calculates the total tax(current year+overdue)  on the current property object
     * Do I want to maintain this value as an instance variable in this class
     * to use the payProperty tax method
     * @return 
     */
    public double getTotalTax()
    { 

        return c1.totalTaxOnOneProperty(this);
    }

    /**
     * A payTax method that calls the PropertyTaxCalculator to carry out 
     * the payment of tax and recalculation of overdue tax.
     * Note how we need the owner of the property to carry this out in a way
     * that reduces the total amount of propertyTax
     * that the owner owes on all properties. This implies that the property
     * class will need to maintain an instance of an owner object or extend the
     * owner object
     * 
     * @param paymentAmount 
     */
    public void payTaxOnThisProperty(double paymentAmount)
    {
        // c1.payTax(owner,this,paymentAmount)
    }  

    public String getSpecificProperty(String postcode)
    {
        for(int i = 0; i < allProperties.size(); i++){
            if(!allProperties.get(i).getPostcode().equals(postcode))
            {
                allProperties.remove(i);
            }
        }
        return allProperties.toString();
    } 

    public String getOwnersProperties(String name)
    {
        for(int i = 0; i < allProperties.size(); i++){
            if(!allProperties.get(i).getName().equals(name))
            {
                allProperties.remove(i);
            }
        }
        return allProperties.toString();
    } 
    
        public double getRoutingKey(String routingKey)
        {
        double sum = 0;
        for(int i = 0; i < allProperties.size(); i++)
        {
            if(!allProperties.get(i).cutEir().equals(routingKey))
            {
                allProperties.remove(i);
            }
        }
        
        for(int i = 0; i < allProperties.size(); i++)
        {
            sum = sum + allProperties.get(i).getTotalTax();
        }
        return sum;
    } 
    
            public double getRoutingKeyAvg(String routingKey)
        {
        double sum = 0;
        for(int i = 0; i < allProperties.size(); i++)
        {
            if(!allProperties.get(i).cutEir().equals(routingKey))
            {
                allProperties.remove(i);
            }
        }
        
        for(int i = 0; i < allProperties.size(); i++)
        {
            sum = sum + allProperties.get(i).getTotalTax();
        }
        return sum/allProperties.size();
    } 
    
                public double getRoutingKeyOverdueTax(String routingKey)
        {
        double sum = 0;
        for(int i = 0; i < allProperties.size(); i++)
        {
            if(!allProperties.get(i).cutEir().equals(routingKey))
            {
                allProperties.remove(i);
            }
        }
        
        for(int i = 0; i < allProperties.size(); i++)
        {
            //sum = sum + allProperties.get(i).calcOverdueTax();
        }
        return sum;
    } 
}

    // public String getPropertiesOfParticularYear(int year){
        // for(int i = 0; i < allProperties.size(); i++){
            // if(allProperties.get(i).getYearCreated() != yearCreated)
            // {
                // allProperties.remove(i);
            // }
        // }
        // return allProperties.toString();
    // } 
    
        // public double calcOverdueTax(Property property, int year, double amountPaid)
    // {
        // int yearOne = property.getYearCreated();
        // double overdueTax = 0;
        // /**
         // * swap 2020 for method that gets CurrentYear();
         // */
        // for(int i = yearOne;i < year ; i++)
        // {
            // overdueTax = overdueTax-TaxPaidOnThisProperty(amountPaid);
        // }
        // return overdueTax;
    // }



