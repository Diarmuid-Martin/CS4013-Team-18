/**
* @author(Liam Ryan)
*
**/
package com.team18.taxprogram.io;

import java.util.List;
import java.util.ArrayList;


import com.team18.taxprogram.model.Property;
import com.team18.taxprogram.model.Owner;
import com.team18.taxprogram.model.Transaction;

public class Database {
    public List<Property> properties;
    public List<Owner> owners;
    public List<Transaction> transactions;
/**
* Constructor for the class
* @param properties ArrayList of properties
* @param owners ArrayList of owners
* @param transactions ArrayList of transactions
* 
**/
    public Database(List<Property> properties, List<Owner> owners, List<Transaction> transactions) {
        this.properties = properties;
        this.owners = owners;
        this.transactions = transactions;
    }
/**
* Creates empty Database
* @return Database
**/
    public static Database empty() {
        return new Database(new ArrayList<Property>(), new ArrayList<Owner>(), new ArrayList<Transaction>());
    }
/**
* Adds Transaction
* @param t 
* @return void
**/
    public void addTransaction(Transaction t) {
        transactions.add(t);
    }

    /**
    * Adds Owner
    * @param o
    * @return void
    **/
    public void addOwner(Owner o) {
        owners.add(o);
    }
 
    /**
    * Adds Property
    * @param p
    * @return void
    **/
    public void addProperty(Property p) {
        properties.add(p);
    }
}
