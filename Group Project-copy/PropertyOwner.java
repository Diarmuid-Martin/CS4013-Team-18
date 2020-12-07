
import java.util.ArrayList;

/**
 * THINGS WE NEED TO SORT IN THIS CLASS: 
 * Nothing is being added to the arraylist of ownedProperties-must fix
 * @author Ellen
 * @version 6/12/2020
 */
public class PropertyOwner {

    private String name;
    private ArrayList<Property> ownedProperties;
    private ArrayList<PropertyOwner> allPropertyOwners = new ArrayList<>();

    public PropertyOwner(String name) {
        this.name = name;
        ownedProperties = new ArrayList<Property>();//creates an ArrayList of 
        //ownedProperties for this particular owner BUT WE MUST IMPLEMENT
        //CODE THAT ADDS PROPERTIES TO IT
        allPropertyOwners.add(new PropertyOwner(name));//creates an ArrayList 
        //of all propertyOwners in the system
    }

    public ArrayList<PropertyOwner> getAllPropertyOwners() {
        return allPropertyOwners;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Property> getOwnedProperties() {
        return ownedProperties;
    }
/**
 * Sets the 
 * @param ownedProperties 
 */
    public void setOwnedProperties(ArrayList<Property> ownedProperties) {
        this.ownedProperties = ownedProperties;
    }

    public void addProperty(String name,double marketValue, String address,
            String eircode, int year, String locationCategory, String ppr) {
        ownedProperties.add(new Property(name,marketValue, address, eircode, year, locationCategory,ppr));
    }
    
     /**
     * Calculates the total tax(current year+overdue) on the current owner object
     * Do I want to maintain this value as an instance variable in this class
     * to use the payProperty tax method
     * @return 
     */
    public double getOwnersPropertyTaxDue() {
        PropertyTaxCalculator c1=new PropertyTaxCalculator();
        return c1.calculateOwnersTotalTaxDue(this);
    }

    /**
     * We won't have a payPropertyTax method in PropertyOwner as that would
     * imply that we are making a payment against the total amount of propertyTax
     * that the owner owes on all properties but that is not the case. Instead
     * we will be making payments against however much property tax is due on 
     * a particular property and in whatever method we are making the payment against 
     * we will the property we will also reduce the total amount of propertyTax
     * that the owner owes on all properties by the same amount
     * Should I have a method and an instance variable in this class that 
     * maintains total amount of propertyTax
     * that the owner owes on all properties ?
     *
     * @param amounttyTaxDue
     */
//    public double payPropertyTax(double amount) {
//        double sumOfTaxDueForThisYear = getPropertyTaxDue();
//        return;
//    }

    @Override
    public String toString() {
        return "PropertyOwner{" + "name=" + name + ", ownedProperties=" + ownedProperties + '}';
    }

}
