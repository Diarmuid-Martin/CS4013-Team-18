
import java.time.LocalDate;

/**
 * This is just what ive done, will prons have to be broken up into diff clases
 * but i just threw everything together. (calculator is in this class)
 *
 * @author (liam)
 * @version (a version number or a date)
 */
public class Property
{
    // instance variables - replace the example below with your own
    private double Value;
    private String Address;
    private String Postcode;
    // private ArrayList<ProperyOwner> Owners;
    private int YearCreated;
    private boolean principle;
    private String location; //  enum/char, probs should be enum tbh, will change it
    // have to remember how to use it
    // city     100
    // l town   80  
    // s town   60
    // village  50
    // country  25
    private int[]PropValue={0,150000,400000,650000}; 
    // note ranges are 0-149999, 150 - 400, 400001-650, 650+ in q
    private double[]TaxRate={0,.01,.02,.04};
    /**
     * Constructor for objects of class Property
     */
    // have to add the ArrayList of owners to the constructor
    //ELLEN: I've adjusted the yearCreated's initialisation and I have removed
    //the boolean principle field as that will be done in Property owner i think
    public Property(double pValue, String pAddress,String pPostcode, int pYearCreated,String pLocation)
    {
        Value = pValue;
        Address = pAddress;
        Postcode = pPostcode;
        YearCreated = pYearCreated;//LocalDate.now().getYear();
        location = pLocation;
        //PropertyOwner.addProperty(Value,Address,Postcode,YearCreated,location);
        principle=isPrincipleProperty();
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

    public void setPrinciple(boolean pPrinciple)
    {
        principle = pPrinciple;
    }

    public boolean getPrinciple()
    {
        return principle;
    }
    
    public double getTotalTax()
    {
        // need to add location category tax
        // and penalty for unpaid tax

        double fixed = 100;
        int principleAmount;
        double EstValue = getValue();
        double calcTaxRate = 0;
        double locationTax = 0;
        if(principle == true)
        {
            principleAmount = 100;
        }
        else
        {
           principleAmount = 0; 
        }
        
        //could also do 2 arrays and get index for these methods.
        // or enum
        if(getLocation() == "CITY")
        {
            locationTax = 100;
        }
        
        if(getLocation() == "LARGE TOWN")
        {
            locationTax = 80;
        }
        
        if(getLocation() == "SMALL TOWN")
        {
            locationTax = 60;
        }
        
        if(getLocation() == "VILLAGE")
        {
            locationTax = 50;
        }
        
        if(getLocation() == "COUNTRYSIDE")
        {
            locationTax = 25;
        }
        
        for(int j = 1; j<PropValue.length;j++)
        {
            if(EstValue >= PropValue[j-1] && EstValue < PropValue[j])
            {
                calcTaxRate = TaxRate[j-1];
            }
        }
        
        return fixed + locationTax + principleAmount + getValue()*calcTaxRate;
    }
}