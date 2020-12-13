/**
* @author(Liam Ryan)
*
**/
package com.team18.taxprogram.accounting;

import com.team18.taxprogram.model.Property;

public interface TaxCalculator {
    public double latePenalty(double amount);
    public double getTaxForOneYear(Property property);
}
