
import java.util.ArrayList;

/**
 *
 * Overdue tax and overdue tax interest methods aren't yet correct
 *
 * @author (liam+ellen)
 * @version 6/12/2020
 */
public class PropertyTaxCalculator
{
    private double[]PropValue={0,150000,400000,650000}; 
    private double[]TaxRate={0,.01,.02,.04};
    private double overdueTax;
    private double totalTaxDueOnOneProperty; 
    private double totalTaxDueOnAllProperties;
    private double year;
    protected ArrayList<Payments> payments=new ArrayList<Payments>();
    Property property = new Property("pName",100000,"pAddress","pPostcode", 2000,"C", "Y");

    /**
     * Gets property tax for this year only on one property only
     * @param property
     * @return prop tax for this year only
     * @author (Liam)
     */
    public double getPropertyTaxThisYear(Property property)
    {
        double fixed = 100;
        int principleAmount;
        double EstValue = property.getValue();
        double calcTaxRate = 0;
        double locationTax = 0;
        if(property.getPpr() .equals("N"))
        {
            principleAmount = 100;
        }
        else
        {
            principleAmount = 0; 
        }

        if(property.getLocation() .equals ("C"))
        {
            locationTax = 100;
        }

        if(property.getLocation() .equals ("L"))
        {
            locationTax = 80;
        }

        if(property.getLocation() .equals ("S"))
        {
            locationTax = 60;
        }

        if(property.getLocation() .equals ("V"))
        {
            locationTax = 50;
        }

        if(property.getLocation() .equals ("R"))
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

        return fixed + locationTax + principleAmount + property.getValue()*calcTaxRate;
    } 
    //new method getOverdueTax
    /**
     * NOT SURE IF BEST WAY TO DO YET
     * A method that updates the amount of tax due once the owner pays some of
     * the tax and adds this payment to the payments arrayList
     * totalTaxDueOnOneProperty and totalTaxDueOnAllProperties are reduced by the 
     * paymentAmount and it should stay at this new lower amount from one method call
     * to the next once we are in the same year
     * Obviously once we go into the next year it should increase by the amount 
     * of tax due for that year
     * @param paymentAmount
     */
    public double payTax(Property property,double paymentAmount ){
        totalTaxDueOnOneProperty=totalTaxDueOnOneProperty-paymentAmount;
        totalTaxDueOnAllProperties=totalTaxDueOnAllProperties-paymentAmount;
        // payments.add(new Payments(owner,property,paymentAmount,
        //        totalTaxDueOnOneProperty,totalTaxDueOnAllProperties));
        return calcOverdueTax(property, paymentAmount);

    } 

    public void payTaxtwo(PropertyOwner owner,Property property,double paymentAmount ,int year){
        totalTaxDueOnOneProperty=totalTaxDueOnOneProperty-paymentAmount;
        totalTaxDueOnAllProperties=totalTaxDueOnAllProperties-paymentAmount;
        payments.add(new Payments(owner,property,paymentAmount,
                totalTaxDueOnOneProperty,totalTaxDueOnAllProperties));
        calcOverdueTax(property, paymentAmount);

    } 

    /**
     * NOT CORRECT
     * Call this method from payTax method so that each time a payment is made we
     * reduce the amount of tax that is overdue
     * For this reason I think we should have a separate method that applies the interest
     * rate that takes into account the year end otherwise we will apply the interest 
     * rate each time a payment is made, although that would be fine if we assume
     * the owner only makes a payment once a year
     * Calculates overdue tax on a property and applies a 7% interest rate-do this in another method?
     * I actually don't want to do it the following way as if i used this method
     * it would add this years tax due to it each time we make a payment which wouldn't
     * be correct
     * thisYearsTaxDue would come from getPropertyTaxThisYear
     * thisYearsTaxPaid
     * Problems: 
     * @param property
     * @return the amount of overdue tax
     */
    public double calcOverdueTax(Property property, double paymentAmount)
    {
        int yearOne = property.getYearCreated();
        
        /**
         * swap 2020 for method that gets CurrentYear();
         */
        for(int i = yearOne;i < year;i++)
        {
            overdueTax = overdueTax-paymentAmount;
        }
        return overdueTax;
    }

    /**
     * A method that returns the total tax (current year+overdue) on one property
     * @param property
     * @return 
     */
    public double totalTaxOnOneProperty(Property property){
        totalTaxDueOnOneProperty=calcOverdueTax(property,0)+getPropertyTaxThisYear(property);
        return totalTaxDueOnOneProperty;
    }

    /** 
     * Calculates the total tax (current year+overdue) that an owner owes on all
     * their properties
     * Search through an ArrayList of PropertyOwners 
     * calculates the sum of all the tax due
     * 
     * Returns how much tax an owner owes
     */
    public double calculateOwnersTotalTaxDue(PropertyOwner owner)
    {
        owner.getOwnedProperties();
        for(Property property:owner.getOwnedProperties()) 
        {
            property.getTotalTax();
            totalTaxDueOnAllProperties+=property.getTotalTax();        
        }           
        return totalTaxDueOnAllProperties;
    }  

    public double getPropByYear(int year)
    {
        double overDueTax = 0;
        for(Property property : property.getAllProperties())
        {
            int yearOne = property.getYearCreated(); 
            for (int i = yearOne; i < year ; i++)
            {
                overDueTax = overDueTax - (getPropertyTaxThisYear(property) - payTax(property,0));   
            }
        }
        return overDueTax;
    }

    public double getRoutingKeyOverdueTax(String routingKey)
    {
        double sum = 0;
        for(int i = 0; i < property.getAllProperties().size(); i++)
        {
            if(property.getAllProperties().get(i).cutEir().equals(routingKey))
            {
                property.getAllProperties().remove(i);
            }
        }

        for(int i = 0; i < property.getAllProperties().size(); i++)
        {
            //sum = sum + property.getAllProperties().get(i).calcOverdueTax();
        }
        return sum;
    } 
}
