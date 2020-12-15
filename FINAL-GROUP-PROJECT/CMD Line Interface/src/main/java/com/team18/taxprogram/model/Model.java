/**
* @author(Liam Ryan)
**/
package com.team18.taxprogram.model;

import java.io.IOException;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import com.team18.taxprogram.accounting.Statement;
import com.team18.taxprogram.accounting.StatsLineItem;
import com.team18.taxprogram.accounting.TaxCalculator;
import com.team18.taxprogram.accounting.TaxCalculatorImpl;
import com.team18.taxprogram.accounting.BalancingStatement;
import com.team18.taxprogram.accounting.LineItem;
import com.team18.taxprogram.accounting.PropertyLineItem;
import com.team18.taxprogram.io.Database;
import com.team18.taxprogram.io.IO;
import static java.util.stream.Collectors.toList;

public class Model {
    Database data;
    TaxCalculator calculator;
    String propFileName;
    String ownerFileName;
    String transactionFileName;
    int currentYear;
	
	/**
	*  Class Constructor
	* @param propFileName
	* @param ownerFileName
	* @param transactionFileName
	**/
    public Model(String propFileName, String ownerFileName, String transactionFileName) {
        this.propFileName = propFileName;
        this.ownerFileName = ownerFileName;
        this.transactionFileName = transactionFileName;
        currentYear = Year.now().getValue();
        data = IO.fromCSVs(propFileName, ownerFileName, transactionFileName);
        calculator = new TaxCalculatorImpl();
    }

	/**
	* Saves data to CSV
	*
	* @return void
	**/
    public void save() throws IOException {
        IO.saveToCSVs(propFileName, ownerFileName, transactionFileName, data);
    }

	/**
	* Gets the owners name
	* @param name
	* @return Owner
	**/
    public Owner getOwner(String name) {
        for(int i = 0; i < data.owners.size(); i++) {
            if(name.equals(data.owners.get(i).name)) {
                return data.owners.get(i);
            }
        }
        Owner newOwner = new Owner(name);
        addOwner(newOwner);
        return newOwner;
    }

    /**
     * adds a transaction to database
     * 
     * @param owner
     * @param prop
     * @param amount
     * @param year
     * @return void
     **/
    public void addTransaction(Owner owner, Property prop, double amount, int year) {
        data.addTransaction(new Transaction(owner, prop, amount, year));
    }

    /**
     * Adds property to Database
     * 
     * @param prop
     **/
    public void addProperty(Property prop) {
        data.addProperty(prop);
    }

    /**
     * Adds owner to Database
     * 
     * @param owner
     **/
    public void addOwner(Owner owner) {
        data.addOwner(owner);
    }

	/**
	* Gets an owners properties
	* @param owner
	* @return List
	**/
    public List<Property> getOwnersProperties(Owner owner) {
        ArrayList<Property> results = new ArrayList<>();
        for(int i = 0; i < data.properties.size(); i++){
            if( (data.properties.get(i)).owners.contains(owner)) {
                results.add(data.properties.get(i));
            }
        }
        return results;
    }

	/**
    *  generate the tax liability for a propety and a range of years
    * @param finalYear
    * @param owner
    * @param prop
    * @return BalancingStatement
    **/
    private BalancingStatement calcBalancingStatement(int finalTaxYear, int finalPaymentYear, Property prop) {
        BalancingStatement statement = new BalancingStatement("|year|dueTax|paidTax|remainingTax|");
        double debt = 0.0;
        for(int taxYear = prop.getRegisteredYear(); taxYear <= Math.max(finalTaxYear, finalPaymentYear); taxYear++) {
            debt = (debt < 0) ? debt : calculator.latePenalty(debt);
            double debtForYear = calculator.getTaxForOneYear(prop);
            if(taxYear <= finalTaxYear) debt += debtForYear;
            double paidTax = getPaymentTotalPerYear(taxYear, prop);
            if(taxYear <= finalPaymentYear) debt -= paidTax;
            statement.addLine(new LineItem(taxYear, debtForYear, paidTax, debt));
        }
        return statement;
    }
	/**
    *  gets Total Payment for a property
    * @param taxYear
    * @param prop
    * @return double
    **/
	private double getPaymentTotalPerYear(int taxYear, Property prop) {
        double total = 0.0;
        for(int i = 0; i < data.transactions.size(); i++) {
            if(data.transactions.get(i).property == prop && data.transactions.get(i).year == taxYear) {
                total += data.transactions.get(i).amount;
            }
        }
        return total;
    }

    private double getPaymentTotal(Property prop) {
        double total = 0.0;
        int finalYear = Year.now().getValue();
        for(int i = prop.getRegisteredYear(); i <= finalYear; i++){
            total += getPaymentTotalPerYear(i, prop);
        }
        return total;
    }

    /**
    *  gets Total Payment for a property
    * @param prop
    * @return Staement from taxCalc()
    **/
    public Statement balancingStatementProperty(Property prop) {
        return calcBalancingStatement(currentYear, currentYear, prop);
    }
    
	/**
    *  gets balancingStatement for year
    * @param owner
    * @param year
    * @return Statement
    **/
    public Statement balancingStatementYear(Owner owner, int year) {
        // We will get the properties of owner which were registered before the year
        List<Property> allProperties = getOwnersProperties(owner);
        List<Property> atTheTime = new ArrayList<Property>();
        for(int i = 0; i < allProperties.size(); i++){
            if(allProperties.get(i).registeredYear <= year) {
                atTheTime.add(allProperties.get(i));
            }
        }
        return taxDueYear(year, year, atTheTime);
    }

	/**
    * 
    * @param props
    * @return Statement
    **/
	public Statement taxDueYear(int taxYear, int paymentYear, List<Property> props) {
        Statement stmt = new BalancingStatement("taxDue");
        for(int i = 0; i < props.size(); i++) {
            Statement tmpStmt = calcBalancingStatement(taxYear, paymentYear, props.get(i));
            LineItem lastLine = tmpStmt.lastLine();
            if(lastLine != null){
                LineItem newLine = new PropertyLineItem(props.get(i), lastLine.getYear(), lastLine.getDueTax(), lastLine.getPaidTax(), lastLine.getRemainingTax());
                stmt.addLine(newLine);
            }
        }
		return stmt;
    }
    
    public Statement taxDue(List<Property> props) {
        return taxDueYear(currentYear, currentYear, props);
    }
	
	/**
    * 
    * @param props
    * @return Statement
    **/
	public Statement taxOverdue(List<Property> props) {
		return taxDueYear(currentYear -1, currentYear, props);
	}

	/**
    * Gets a property based on eircode
    * @param eircode
    * @return Property
    **/
	public Property getSpecificProperty(String eircode) {
		for (int i = 0; i < data.properties.size(); i++) {
            if ((data.properties.get(i)).eircode.equals(eircode)) {
                return data.properties.get(i);
            }
        }
        return null;
	}
	
	/**
    * Gets the total tax of an area based on routing key
    * @param key
    * @return int
    **/

	private List<Property> getRoutingKeyProps(String key) {
        List<Property> inRoutingKey = new ArrayList<Property>();
        for(int i = 0; i < data.properties.size(); i++){
            if(data.properties.get(i).eircode.startsWith(key)) {
                inRoutingKey.add(data.properties.get(i));
            }
        }
		return inRoutingKey;
	}

		/**
    * Method for getting stats on a Routing key area
    * @param key
    *  
    **/
	public Statement statsOnRoutingKey(String key) {
        // gets stats on routing key
        //
        // 1) calc total tax paid by routing key and display this as double/int
        // search through all props for routing key and add up
        // 2) calc average tax paid by key, display double
        // 3) no. of properties which have paid their taxes. int
        // 4) % of props that have taxes paid. double/int

        List<Property> props = getRoutingKeyProps(key);
        List<Double> paymentAmounts = props.stream().map(x -> getPaymentTotal(x)).collect(toList());
        Double sumOfTaxPaid = paymentAmounts.stream().mapToDouble(x -> x.doubleValue()).sum();
        int numPaidTax = (int)paymentAmounts.stream().filter((x) -> x != 0.0).count();
        int numOfProps = props.size();
        Statement stmt = new BalancingStatement("Key | Tax Paid | Average Paid | Num Paid | Percentage Paid |");
        LineItem results = new StatsLineItem(key, sumOfTaxPaid, sumOfTaxPaid/numOfProps, numPaidTax, ((double)numPaidTax)*100 / numOfProps);
        stmt.addLine(results);
        return stmt;
	}

    public Statement getTotalDueTax(int year) {
        return taxDueYear(year, year, data.properties);
    }

	public Statement getRoutingKeyStatement(String key1) {
		return taxDue(getRoutingKeyProps(key1));
	}
}