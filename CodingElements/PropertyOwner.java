
import java.util.ArrayList;

/**
 *
 * @author Ellen
 */
public class PropertyOwner {
    private String name;
    private ArrayList<Property> ownedProperties;

    public PropertyOwner(String name) {
        this.name = name;
        ownedProperties=new ArrayList<Property>();
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
            String eircode,int year, String locationCategory){
        ownedProperties.add(new Property(marketValue,address,eircode,year,locationCategory));
    }
    
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
    public boolean isPrincipleProperty(){
        boolean onlyOneProperty=false;
//        if(i!=0){
//           onlyOneProperty=false; 
//        }
        
        //If ownedProperties.size()==0 then the property you are adding must be
        //the principle property
        //Once ownedProperties.size()>0 it already has a principle property and 
        //any properties added after this will not be principle properties
        if(ownedProperties.size()==0){
            onlyOneProperty=true;
        }
        return onlyOneProperty;
    }
    
 
    /**
     * Use the property tax calculator, return amount of property tax due
     * NEED TO GET THE PROPERTYTAX CALC WORKING FIRST
     */
    public double getPropertyTaxDue(){
        double sumOfTaxDueForThisYear=0;
        for(int i=0;i<ownedProperties.size();i++){
           // sumOfTaxDueForThisYear+=ownedProperties.get(i).calculateTax;
        }
        return 4;
    }
    
    /**
     * A method that pays the propertyTax and calls the Transaction account
     * and returns the propertyTaxDue
     */
    public double payPropertyTax(double amount){
        double sumOfTaxDueForThisYear=getPropertyTaxDue();
        
    }
}