    /**
	* @author(Liam Ryan)
	**/
package com.team18.taxprogram.model;

import java.util.ArrayList;
import java.util.stream.Collectors;

import com.team18.taxprogram.io.IO;

public class Property extends Saveable {
    public ArrayList<Owner> owners;
    public String address;
    public String eircode;
    public Location location;
    public Boolean principle;
    public Double value;
    public int registeredYear;
    
    /**
    * Constructor
    * @param owners
    * @param address
    * @param eircode
    * @param location
    * @param principle
    * @param registeredYear
    * @param value
    **/
    public Property(ArrayList<Owner> owners, String address, String eircode, Location location, Boolean principle,
            int registeredYear, Double value) {
        this.owners = owners;
        this.address = address;
        this.eircode = eircode;
        this.location = location;
        this.principle = principle;
        this.registeredYear = registeredYear;
        this.value = value;
    }
    
    /**
    * @return int
    **/
    public int getRegisteredYear() {
        return this.registeredYear;
    }

    /**
    * @param registeredYear
    * @return void
    **/
    public void setRegisteredYear(int registeredYear) {
        this.registeredYear = registeredYear;
    }

    /**
    * @return ArrayList
    **/
    public ArrayList<Owner> getOwners() {
        return this.owners;
    }

    /**
    * @param owners
    * @return void
    **/
    public void setOwners(ArrayList<Owner> owners) {
        this.owners = owners;
    }
    
    /**
    * @return String
    **/
    public String getAddress() {
        return this.address;
    }

    /**
    * @param address
    * @return void
    **/    
    public void setAddress(String address) {
        this.address = address;
    }
    
    /**
    * @return String
    **/
    public String getEircode() {
        return this.eircode;
    }

    /**
    * @param eircode
    * @return void
    **/     
    public void setEircode(String eircode) {
        this.eircode = eircode;
    }

    /**
    * @return Location
    **/
    public Location getLocation() {
        return this.location;
    }

    /**
    * @param location
    * @return void
    **/ 
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
    * @return Boolean
    **/
    public Boolean isPrinciple() {
        return this.principle;
    }

    /**
    * @return Boolean
    **/
    public Boolean getPrinciple() {
        return this.principle;
    }

    /**
    * @param principle
    * @return void
    **/
    public void setPrinciple(Boolean principle) {
        this.principle = principle;
    }

    /**
    * @return Double
    **/
    public Double getValue() {
        return this.value;
    }

    /**
    * @param value
    * @return void
    **/
    public void setValue(Double value) {
        this.value = value;
    }
    
    /**
    * @return String
    **/
    @Override
    public String toString() {
        String ownersString = String.join(",", getOwners().stream().map(elt -> elt.toString()).collect(Collectors.toList()));
        return ownersString + "," + getAddress() + "," + getEircode()
                + "," + getLocation().toString() + "," + isPrinciple() + ","  + getValue() + "," +  getRegisteredYear();
    }

    /**
    * @return String
    **/
    @Override
    public String toCSVString() {
        String ownersString = String.join(",",
                getOwners().stream().map(elt -> elt.toCSVString()).collect(Collectors.toList()));
        return IO.encodeString(ownersString) + "," + IO.encodeString(getAddress()) + "," + IO.encodeString(getEircode())
                + "," + getLocation().toString() + "," + isPrinciple() + "," + getValue() + ","
                + getRegisteredYear();
    }
}
