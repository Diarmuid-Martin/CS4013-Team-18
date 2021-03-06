
/**
 * Write a description of class PropertyTaxCaculator here.
 * PropertyTacCalculator
 * Doesnt have Penalty for unpaid tax yet
 *
 * @author (liam)
 * @version (a version number or a date)
 */
public class PropertyTaxCalculator
{
   private double[]PropValue={0,150000,400000,650000}; 
   private double[]TaxRate={0,.01,.02,.04};

   public double getTotalTaxThisYear(Property property)
    {
        // need to add penalty for unpaid tax 7% compounded

        double fixed = 100;
        int principleAmount;
        double EstValue = property.getValue();
        double calcTaxRate = 0;
        double locationTax = 0;
        if(property.getPrinciple() == false)
        {
            principleAmount = 100;
        }
        else
        {
           principleAmount = 0; 
        }
        
        if(property.getLocation() == 'C')
        {
            locationTax = 100;
        }
        
        if(property.getLocation() == 'L')
        {
            locationTax = 80;
        }
        
        if(property.getLocation() == 'S')
        {
            locationTax = 60;
        }
        
        if(property.getLocation() == 'V')
        {
            locationTax = 50;
        }
        
        if(property.getLocation() == 'R')
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
}
