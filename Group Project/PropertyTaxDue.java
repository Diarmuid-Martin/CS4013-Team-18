    import java.util.ArrayList;
/**
* This class extends PropertyTaxCalculator to handle overdue tax but is this
* really necessary or should we have all this in PropertyTaxCalculator  
* 
 * @author (liam + ellen)
* @version (a version number or a date)
*/
public class PropertyTaxDue
{
    private double overdueTax;
    private Property property;
    private double currentYearTax=0; 
    
       /**
     * A method that updates the balance once the user withdraws money from 
     * their account
     * @param withdrawalAmount
     */
    public void withdraw(double withdrawalAmount){
        balance=balance-withdrawalAmount;
        transactions.add(new Transaction('W',withdrawalAmount,balance,"Withdraw" ));
    } 
    
   /**
     * Returns the total tax due for 1 property
     */
    public double calculateTaxDueOfOneProperty(Property property)
    {
        return property.getTotalTax();
    }
    
    /**
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
                currentYearTax+=property.getTotalTax();        
            }           
        return currentYearTax;
    }  

}



