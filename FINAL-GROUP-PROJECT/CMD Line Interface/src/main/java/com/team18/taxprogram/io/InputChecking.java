/**
* @author(Liam Ryan)
**/
package com.team18.taxprogram.io;

import java.util.Scanner;

import com.team18.taxprogram.model.Location;

public class InputChecking {

	/**
	* checker is an lambda/anonymous function 
	* input -> output
	* @param in
	* @return parser()
	**/
    static public Location getLocation(Scanner in) {
        InputCheck<Location> checker = (s) -> Location.valueOf(s);
        return parser(in, checker);
    }
	/**
 	* checker is an lambda/anonymous function 
	* input -> output
	* @param in
	* @return parser()
	**/   
    static public boolean getBool(Scanner in) {
        InputCheck<Boolean> checker = (s) -> Boolean.parseBoolean(s);
        return parser(in, checker);
    }

		/**
	* checker is an lambda/anonymous function 
	* input -> output
	* @param in
	* @return parser()
	**/
    static public int getInt(Scanner in) {
        InputCheck<Integer> checker = (s) -> Integer.parseInt(s);
        return parser(in, checker);
    }

		/**
	* checker is an lambda/anonymous function 
	* input -> output
	* @param in
	* @return parser()
	**/
    static public double getDouble(Scanner in) {
        InputCheck<Double> checker = (s) -> Double.parseDouble(s);
        return parser(in, checker);
    }

		/**
	* checker is an lambda/anonymous function 
	* input -> output
	* @param in
	* @return parser()
	**/
    static public String getString(Scanner in) {
        InputCheck<String> checker = (s) -> s;
        return parser(in, checker);
    }

		/**
	* checker is an lambda/anonymous function 
	* input -> output
	* @param in
	* @return parser()
	**/
    static public String getEircode(Scanner in) {
        InputCheck<String> checker = (s) -> s.toUpperCase();
        return parser(in, checker);
    }

		/**
	* checker is an lambda/anonymous function 
	* input -> output
	* @param in
	* @return parser()
	**/
    static public String getOption(Scanner in) {
        InputCheck<String> checker = (s) -> s.toUpperCase();
        return parser(in, checker);
    }

		/**
	* checker is an lambda/anonymous function 
	* input -> output
	* @param in
	* @return parser()
	**/
    static public String getOwnerName(Scanner in) {
        InputCheck<String> checker = (s) -> s.toUpperCase();
        return parser(in, checker);
    }

		/**
	* checker is an lambda/anonymous function 
	* input -> output
	* @param in
	* @return parser()
	**/
	public static String getRoutingKey(Scanner in) {
		InputCheck<String> checker = (s) -> s.toUpperCase();
        return parser(in, checker);
    }
    /** 
    *takes in checker object
	* tries to read input of checker
	* @param in
	* @param checker
	* @return T
	*
	**/
    public static <T>  T parser(Scanner in, InputCheck<T> checker){
        while(true){
            String input = in.nextLine();
            try {
                T res = checker.readInput(input);
                return res;
            } catch (Exception e) {
                System.out.println("Please enter a valid input");
            }
        }

    }
}
