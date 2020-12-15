/**
* @author(Liam Ryan)
*
**/
package com.team18.taxprogram.accounting;

import com.team18.taxprogram.model.Owner;

public class StatsLineItem extends LineItem {
    Owner owner;
    String key;
    double totalTax;
    double averageTax;
    int numPaidTax;
    double percentagePaid;
    
    /**
     * // 1) calc total tax paid by routing key and display this as double/int //
     * search through all props for routing key and add up // 2) calc average tax
     * paid by key, display double // 3) no. of properties which have paid their
     * taxes. int // 4) % of props that have taxes paid. double/int
     **/
    public StatsLineItem(String key, double totalTax, double averageTax, int numPaidTax, double percentagePaid) {
        super(0, 0, 0, 0);
        this.key = key;
        this.totalTax = totalTax;
        this.averageTax = averageTax;
        this.numPaidTax = numPaidTax;
        this.percentagePaid = percentagePaid;
    }

    @Override
    public String toString() {
        return "|" + key + "|" + totalTax + "|" + averageTax + "|" + numPaidTax + "|" + percentagePaid + "|";
    }
    
}
