import java.time.LocalDate;
import java.util.ArrayList;

/**
* This is just what ive done, will prons have to be broken up into diff clases
* but i just threw everything together.
*
* @author (liam)
* @version (a version number or a date)
*/
public class Property
{
    
    private double Value;
    private String Address;
    private String Postcode;
    // private ArrayList<ProperyOwner> Owners;
    //should we have owner as an instance variable
   private int YearCreated;
    private boolean principle;
    //Have an overdueTax instance variable? that maintains the amount of 
    //overdue tax from one year to another?
    private char location; //  enum/char, probs should be enum tbh, will change it
    // have to remember how to use it
    // city     100
    // l town   80  
    // s town   60
    // village  50
    // country  25
    private int[]PropValue={0,150000,400000,650000}; 
    // note ranges are 0-149999, 150 - 400, 400001-650, 650+ in q
    private double[]TaxRate={0,.01,.02,.04};
    private ArrayList<Property> allProperties=new ArrayList<>();
    
    /**
     * Constructor for objects of class Property
     */
    // have to add the ArrayList of owners to the constructor
    //ELLEN: I've adjusted the yearCreated's initialisation and I have removed
    //the boolean principle field as that will be done in Property owner i think
    public Property(double pValue, String pAddress,String pPostcode, int pYearCreated,char pLocation)
    {
        Value = pValue;
        Address = pAddress;
        Postcode = pPostcode;
        YearCreated = pYearCreated;//LocalDate.now().getYear();
        location = pLocation;
        allProperties.add(new Property(Value,Address,Postcode,YearCreated,location));
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

    public void setLocation(char pLocation)
    {
        location =pLocation;
    }

    public char getLocation()
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
    
    /**
     * 
     */
   public double getTotalTax()
    { 
        return 5;
    }
   
    public void PayTax()
    {
        
    }   
}
