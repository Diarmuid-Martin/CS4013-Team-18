/**
* @author(Liam Ryan)
*
**/

package com.team18.taxprogram.accounting;

public class LineItem {
    int year;
    double dueTax;
    double paidTax;
    double remainingTax;
 
    /**
    * Constructor for the class
    * @param year

    * @param dueTax
    * @param paidTax
    * @param remainingTax
    **/
    
    public LineItem(int year, double dueTax, double paidTax, double remainingTax) {
        this.year = year;
        this.dueTax = dueTax;
        this.paidTax = paidTax;
        this.remainingTax = remainingTax;
    }
/**
* To String Method
* @return String
**/
    @Override
    public String toString() {
        return "|" + year + "|" + dueTax +"|" + paidTax + "|" + remainingTax+ "|";
    }

    public int getYear() {
        return this.year;
    }

    public double getDueTax() {
        return this.dueTax;
    }

    public double getPaidTax() {
        return this.paidTax;
    }

    public double getRemainingTax() {
        return this.remainingTax;
    }
}
