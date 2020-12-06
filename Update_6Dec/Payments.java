/**
 * Working off the basis that we are dealing with one owner at a time
 * When tax is paid, writes a history, have a toString/print to file
 * Needs work with the tax calculator class and the taxDue class or 
 * When the owner pays a certain amount of money towards the amount of tax due
 * on that property we reduce the tax due on that property by that amount and
 * reduce the overall amount of taxDue from total tax due from the owner
 * @author (Ellen)
 * @version 6/12/2020
 */
import java.time.*;
public class Payments {
    private String ownerName;
    private String propertyAddress;//use a string tokeniser/divider to only use
    //1st line
    private LocalDateTime date =LocalDateTime.now();
    private double paymentAmount;//respresents the amount the owner is paying
     private double totalTaxDueOnOneProperty; 
   private double totalTaxDueOnAllProperties; 
    
    /**
     * A no arg constructor that initialises Transaction's instance variables to 
     * their default values
    */
     public Payments() {
        this.ownerName=null;
        this.propertyAddress=null;
        this.paymentAmount = 0;
        this.totalTaxDueOnOneProperty = 0;
        this.totalTaxDueOnAllProperties=0;
        this.date=LocalDateTime.now();
    }
//I'm not calling any methods to get values instead in my PropertyTaxCalculator
     //I am passing in all the values I need in the pay method
        public Payments(PropertyOwner owner,Property property,double paymentAmount,
                double totalTaxDueOnOneProperty, double totalTaxDueOnAllProperties) {
        this.ownerName=owner.getName();
        this.propertyAddress=property.getAddress();
        this.paymentAmount = paymentAmount;
        this.totalTaxDueOnOneProperty = totalTaxDueOnOneProperty;
        this.totalTaxDueOnAllProperties=totalTaxDueOnAllProperties;
        this.date=LocalDateTime.now();
    }  

    @Override
    public String toString() {
        return "Payments{" + "Owner=" + ownerName + ", Property Address=" + 
                propertyAddress + ", Date=" + date + ", Payment Amount=" 
                + paymentAmount + ", Total Tax Due On This Property=" 
                + totalTaxDueOnOneProperty
                + ", Total Tax Due On All Properties=" + totalTaxDueOnAllProperties + '}';
    }
        
     
    
    
    
}