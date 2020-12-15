/**
* @author(Liam Ryan)
*
**/
package com.team18.taxprogram.accounting;

import com.team18.taxprogram.model.Property;
import com.team18.taxprogram.model.Location;

public class TaxCalculatorImpl implements TaxCalculator {
    // Balance sheets
    private static double[] PropValue = { 0, 150000, 400000, 650000 };
    private static double[] TaxRate = { 0, .01, .02, .04 };
/**
* Calculates overdue tax amount
* @param amount
* @return double
**/
    public double latePenalty(double amount) {
        return 1.07 * amount;
    }
/**
* Calculates the property tax
* @param property
* @return double
**/
    public double getTaxForOneYear(Property property) {
        double fixed = 100;
        int principleAmount = 0;
        double EstValue = property.getValue();
        double calcTaxRate = 0;
        double locationTax = 0;

        if (property.getPrinciple() == false) {
            principleAmount = 100;
        }

        if (property.getLocation() == Location.CITY) {
            locationTax = 100;
        }

        if (property.getLocation() == Location.LARGE_TOWN) {
            locationTax = 80;
        }

        if (property.getLocation() == Location.SMALL_TOWN) {
            locationTax = 60;
        }

        if (property.getLocation() == Location.VILLAGE) {
            locationTax = 50;
        }

        if (property.getLocation() == Location.COUNTRYSIDE) {
            locationTax = 25;
        }

        for (int j = 1; j < PropValue.length; j++) {
            if (EstValue >= PropValue[j - 1] && EstValue < PropValue[j]) {
                calcTaxRate = TaxRate[j - 1];
            }
        }

        return fixed + locationTax + principleAmount + property.getValue() * calcTaxRate;
    }
}
