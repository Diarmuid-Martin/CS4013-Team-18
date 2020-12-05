
import java.util.ArrayList;

/**
 * THINGS I NEED TO SORT IN THIS CLASS:
 * How does it relate to owner?
 * @author Ellen
 */
public class PropertyOwner {
    private String name;
    private ArrayList<Property> ownedProperties;
    private ArrayList<PropertyOwner> allPropertyOwners=new ArrayList<>();
    
    

    public PropertyOwner(String name) {
        this.name = name;
        ownedProperties=new ArrayList<Property>();//creates an ArrayList of 
        //ownedProperties for this particular owner
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

    public void setOwnedProperties(ArrayList<Property> ownedProperties) {
        this.ownedProperties = ownedProperties;
    }
    
 public void addProperty(double marketValue,String address,
         String eircode,int year, char locationCategory){
       ownedProperties.add(new Property(marketValue,address,eircode,year,locationCategory));
   }
    
//      public void addProperty(Property p){
//        ownedProperties.add(p);
//    }
    
    /**
     * Method which checks if the property is the owners principleProperty
     * which I am letting be the first property in the ArrayList
     * I am only using the index of the Property of the Array to check,
     * I may need to make it more complex
     * Pseuodocode:
     * 1 I want it to be a no-arg method
     * 2 I am going to search the OwnersProperty array for the property
     * store the index number and then if the index number is 0 isPrin
     * 
     * @param i
     * @return true if the owner only has one property, false if more than one
     */
//    public boolean isPrincipleProperty(){
//        boolean onlyOneProperty=false;
//        if(i!=0){
//           onlyOneProperty=false; 
//        }
        
        //If ownedProperties.size()==0 then the property you are adding must be
        //the principle property
        //Once ownedProperties.size()>0 it already has a principle property and 
        //any properties added after this will not be principle properties
//        if(ownedProperties.size()==0){
//            onlyOneProperty=true;
//        }
//        return onlyOneProperty;
//    }
    
 
    /**
     * Use the property tax calculator, return amount of property tax due
     * NEED TO GET THE PROPERTYTAX CALC WORKING FIRST
     */
    public double getPropertyTaxDue(){
        double sumOfTaxDueForThisYear=0;
        for (Property ownedPropertie : ownedProperties) {
            // sumOfTaxDueForThisYear+=ownedProperties.get(i).calculateTax;
        }
        return 4;
    }
    
    /**
     * A method that pays the propertyTax and calls the Transaction account
     * and returns the proper
     * @param amounttyTaxDue
     */
    public double payPropertyTax(double amount){
        double sumOfTaxDueForThisYear=getPropertyTaxDue();
        return 4;
    }

    @Override
    public String toString() {
        return "PropertyOwner{" + "name=" + name + ", ownedProperties=" + ownedProperties + '}';
    }
    
}
