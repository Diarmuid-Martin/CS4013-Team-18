
/**
 * Write a description of class OverDueTax here.
 *
 * @author (liam + )
 * @version (a version number or a date)
 */
public class OverDueTax
{
    public double calcOverdueTax(Property property)
    {
        int yearOne = property.getYearCreated();
        double overdueTax = 0;
        double TaxDue = 0;  // from propertyTaxDue
        double TaxPaid = 0; // inputted by user
        /**
         * swap 2020 for method that gets CurrentYear();
         */
        for(int i = yearOne;i < 2020;i++)
        {
            overdueTax = (overdueTax +(TaxDue - TaxPaid))*1.07;
        }
        return overdueTax;
    }
}
