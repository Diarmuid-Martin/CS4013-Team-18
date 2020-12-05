import java.util.ArrayList;
/**
 * Write a description of class AllProperties here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class AllProperties
{
    private ArrayList<Property> allProperties=new ArrayList<>();

    public ArrayList<Property> getAllProperties(){
        return allProperties;   
    }

    public void add(Property p){
        allProperties.add(p);   
    }

    public String displayProperties(){
        Property[] pArray = allProperties.toArray(new Property[0]);   
        return pArray.toString(); 
    }

}
