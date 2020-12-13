/**
* @author(Liam Ryan)
**/
package com.team18.taxprogram.model;

import com.team18.taxprogram.io.IO;

/**
* Constructor
**/
public class Transaction extends Saveable{
    public Owner owner;
    public Property property;
    public double amount;
    public int year;

    /**
    * Constructor
    * @param owner
    * @param property
    * @param amount
    * @param year
    **/
    public Transaction(Owner owner, Property property, double amount, int year){
        this.owner = owner;
        this.property = property;
        this.amount = amount;
        this.year = year;
    }

    /**
    * @return Owner
    **/
    public Owner getOwner() {
        return this.owner;
    }

    /**
    * @param owner
    * @return void
    **/
    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    /**
    * @return Property
    **/
    public Property getProperty() {
        return this.property;
    }

    /**
    * @param property
    * @return void
    **/
    public void setProperty(Property property) {
        this.property = property;
    }

    /**
    * @return double
    **/
    public double getAmount() {
        return this.amount;
    }

    /**
    * @param amount
    * @return void
    **/
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
    * @return int
    **/
    public int getYear() {
        return this.year;
    }

    /**
    * @param year
    * @return void
    **/
    public void setYear(int year) {
        this.year = year;
    }

    /**
    * @return String
    **/
    @Override
    public String toString() {
        return  getOwner().toString() + "," + getProperty().toString() + "," + getAmount() + "," + getYear();
    }

    /**
    * @return String
    **/
    @Override
    public String toCSVString() {
        return IO.encodeString(getOwner().toCSVString()) + "," + IO.encodeString(getProperty().toCSVString()) + "," + getAmount() + "," + getYear();
    }
}
