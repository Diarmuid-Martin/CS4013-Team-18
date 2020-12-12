import java.util.Objects;

public class Property {


    private int propNumb;
    private String name;
    private String address;
    private String eircode;
    private double value;
    private String principal;
    private String location;
    private int year;
    private double currentTax;
    private double overdueTax;
    private double totalTax;
    private double amountPaid;
    private double balance;

    public Property(int propNumb,String name, String address, String eircode, double value, String principal, String location,
                       int year, double currentTax, double overdueTax, double totalTax, double amountPaid, double balance){

        this.propNumb=propNumb;
        this.name=name;
        this.address=address;
        this.eircode=eircode;
        this.value=value;
        this.principal=principal;
        this.location=location;
        this.year=year;
        this.currentTax=currentTax;
        this.overdueTax=overdueTax;
        this.totalTax=totalTax;
        this.amountPaid=amountPaid;
        this.balance=balance;

    }

    //set methods


    public void setPropNumb(int propNumb) {
        this.propNumb = propNumb;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEircode(String eircode) {
        this.eircode = eircode;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setPrincipal(String principal) {
       this.principal=principal;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setCurrentTax(double currentTax) {
        this.currentTax = currentTax;
    }

    public void setOverdueTax(double overdueTax) {
        this.overdueTax = overdueTax;
    }

    public void setTotalTax(double totalTax) {
        this.totalTax = totalTax;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }



    //get mehtods
    public int getPropNumb() {
        return propNumb;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getEircode() {
        return eircode;
    }

    public double getValue() {
        return value;
    }

    public String getPrincipal() {
        return principal;
    }

    public String getLocation() {
        return location;
    }

    public int getYear() {
        return year;
    }

    public double getCurrentTax() {
        return currentTax;
    }

    public double getOverdueTax() {
        return overdueTax;
    }

    public double getTotalTax() {
        return totalTax;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public double getBalance() {
        return balance;
    }




    //toStringArray
    public String[] toStringArray() {
        String[] s=new String[13];
        s[0]=Double.toString(propNumb);
        s[1]=name;
        s[2]=address;
        s[3]=eircode;
        s[4]=Double.toString(value);
        s[5]=principal;
        s[6]=location;
        s[7]= Integer.toString(year);
        s[8]=Double.toString(currentTax);
        s[9]=Double.toString(overdueTax);
        s[10]=Double.toString(totalTax);
        s[11]=Double.toString(amountPaid);
        s[12]=Double.toString(balance);
        return s;
    }

    //toCsvString
    public String toStringCsv(){
        String[] s = this.toStringArray();
        String c =s[0]+","+s[1]+","+s[2]+","+s[3]+","+s[4]+","+s[5]+"," +s[6]+","+s[7]+","+s[8]+","+s[9]+","+s[10]+","+s[11]+","+s[12];
        return c;

    }

    //toString
    @Override
    public String toString() {
        return "Property{" +
                "propNumb=" + propNumb +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", eircode='" + eircode + '\'' +
                ", value=" + value +
                ", principal=" + principal +
                ", location=" + location +
                ", year=" + year +
                ", currentTax=" + currentTax +
                ", overdueTax=" + overdueTax +
                ", totalTax=" + totalTax +
                ", amountPaid=" + amountPaid +
                ", balance=" + balance +
                '}';
    }

    //equels


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Property property = (Property) o;
        return propNumb == property.propNumb && Double.compare(property.value, value) == 0 && principal == property.principal && location == property.location && year == property.year && Double.compare(property.currentTax, currentTax) == 0 && Double.compare(property.overdueTax, overdueTax) == 0 && Double.compare(property.totalTax, totalTax) == 0 && Double.compare(property.amountPaid, amountPaid) == 0 && Double.compare(property.balance, balance) == 0 && Objects.equals(name, property.name) && Objects.equals(address, property.address) && Objects.equals(eircode, property.eircode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(propNumb, name, address, eircode, value, principal, location, year, currentTax, overdueTax, totalTax, amountPaid, balance);
    }
}