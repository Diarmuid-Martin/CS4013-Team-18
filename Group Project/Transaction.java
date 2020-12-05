
/**
 * Working off the basis that we are dealing with on owner at a time
 * When tax is paid, writes a history, have a toString/print to file
 * Needs work with the tax calculator class and the taxDue class or 
 * When the owner pays a certain amount of money towards the amount of tax due
 * on that property we reduce the tax due on that property by that amount and
 * reduce the overall amount of taxDue from total tax due from the owner
 */
import java.time.*;
public class Transaction {

    private LocalDateTime date =LocalDateTime.now();
    private double amount;//respresents the amount the owner is paying
    private double totalTaxDue;//get from calculateOwnersTotalTaxDue method
    //PropertyTaxDue
    
    /**
     * A no arg constructor that initialises Transaction's instance variables to 
     * their default values
    */
     public Transaction() {
        this.amount = 0;
        this.totalTaxDue = 0;
        this.date=LocalDateTime.now();
    }

        public Transaction(PropertyOwner owner,double amount, double tax, String description) {
        this.amount = amount;
        this.totalTaxDue = calculateOwnersTotalTaxDue(owner);
        this.date=LocalDateTime.now();
    }  
        
        @Override
    public String toString() {
        return "Transaction{" + "Date=" + date + ", Tax due=" + taxDue + ", Amount paid=€" 
                + amount + ", Balance=€" + balance + ", Description=" + description + '}';
    }
    
    
}

