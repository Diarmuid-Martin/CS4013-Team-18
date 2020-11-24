import java.time.*;
public class Transaction {

    private LocalDateTime date =LocalDateTime.now();
    private double amount;//respresents the amount the owner has paid
    private double taxDue;
    
    /**
     * A no arg constructor that inialises Transaction's instance variables to 
     * their default values
    */
     public Transaction() {
        this.amount = 0;
        this.taxDue = 0;
        this.date=LocalDateTime.now();
    }

        public Transaction(double amount, double tax, String description) {
        this.amount = amount;
        this.taxDue = tax;
        this.date=LocalDateTime.now();
    }  
        
        @Override
    public String toString() {
        return "Transaction{" + "Date=" + date + ", Tax due=" + taxDue + ", Amount paid=€" 
                + amount + ", Balance=€" + balance + ", Description=" + description + '}';
    }
    
    
}

