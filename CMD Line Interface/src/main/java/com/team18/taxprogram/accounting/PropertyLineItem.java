/**
* @author(Liam Ryan)
*
**/
package com.team18.taxprogram.accounting;

import com.team18.taxprogram.model.Property;

public class PropertyLineItem extends LineItem {
    Property property;
    /**
    * Constructor for the class
    * @param property
    * @param year
    * @param dueTax
    * @param paidTax
    * @param remainingTax 
    **/
    public PropertyLineItem(Property property, int year, double dueTax, double paidTax, double remainingTax) {
        super(year, dueTax, paidTax, remainingTax);
        this.property = property;
    }

    @Override
    public String toString() {
        return "|" + property.eircode + "|" + year + "|" + dueTax + "|" + paidTax + "|" + remainingTax + "|";
    }
    
}
