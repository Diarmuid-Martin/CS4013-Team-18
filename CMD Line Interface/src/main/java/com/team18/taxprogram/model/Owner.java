/**
* @author(Liam Ryan)
**/
package com.team18.taxprogram.model;

import java.util.Objects;

import com.team18.taxprogram.io.IO;

public class Owner extends Saveable {
    public String name;

    /**
    * Constructor
	* @param name
	**/
    public Owner(String name){
        this.name = name;
    }

    /**
	* toString method
	* @return String
	**/
    @Override
    public String toString() {
        return name;
    }

    /**
	* @return String
	**/    
    @Override
    public String toCSVString() {
        return IO.encodeString(name);
    }

    /**
    * equals method
    * @return boolean
    **/
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Owner)) {
            return false;
        }
        Owner owner = (Owner) o;
        return Objects.equals(name, owner.name);
    }

	/**
	* hashCode method
	* @return int
	**/
    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
