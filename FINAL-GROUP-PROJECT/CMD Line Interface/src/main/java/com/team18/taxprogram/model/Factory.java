/**
* @author(Liam Ryan)
**/

package com.team18.taxprogram.model;

import java.util.ArrayList;

import com.team18.taxprogram.io.IO;

public class Factory {

    /**
    *takes in lines of csv and gives you back the objects
    *string class = params
    *returns output of string casted to type T
    * @param S
    * @param T
    * @return T
    **/
    public static <T extends Saveable> T parseLine(String s, Class<T> cls) {
        if(cls == Owner.class){
            return (T) parseOwner(s);
        }
        if (cls == Transaction.class) {
            return (T) parseTransactions(s);
        }
        if (cls == Property.class) {
            return (T) parseProperty(s);
        }
        return null;
    }

    /**
    *  returns details of Property
    *   @param String
    *   @return property
    **/
    public static Property parseProperty(String s) {
        String[] entries = s.split(",");
        ArrayList<Owner> owners = new ArrayList<Owner>();
        String[] ownersNames = IO.decodeString(entries[0]).split(",");
        for (int i = 0; i < ownersNames.length; i++) {
            owners.add(parseOwner(ownersNames[i]));
        }
        String address = IO.decodeString(entries[1]);
        String eircode = IO.decodeString(entries[2]);
        Location location = Location.valueOf(entries[3]);
        boolean principle = Boolean.valueOf(entries[4]);
        double value = Double.parseDouble(entries[5]);
        int year = Integer.parseInt(entries[6]);
        return new Property(owners, address, eircode, location, principle, year, value);
    }

     /**
    *  decodes and returns details of transactions
    *   @param String
    *   @return Transaction
    **/
    public static Transaction parseTransactions(String s) {
        String[] entries = s.split(",");
        Owner owner = parseOwner(entries[0]);
        Property prop = parseProperty(IO.decodeString(entries[1]));
        double value = Double.parseDouble(entries[2]);
        int year = Integer.parseInt(entries[3]);
        return new Transaction(owner, prop, value, year);
    }

    /**
    * decodes the owner
    * @param s
    * @return Owner
    **/
    public static Owner parseOwner(String s) {
        return new Owner(IO.decodeString(s));
    }

}
