/**
* @author(Liam Ryan)
*
**/
package com.team18.taxprogram.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Base64;

import com.team18.taxprogram.model.Owner;
import com.team18.taxprogram.model.Property;
import com.team18.taxprogram.model.Saveable;
import com.team18.taxprogram.model.Transaction;
import com.team18.taxprogram.model.Factory;

public class IO {
    /**
    * Gets data from CSV
    * @param propFileName
    * @param ownerFileName
    * @param transactionFileName
    * @return Database
    **/
    public static Database fromCSVs(String propFileName, String ownerFileName, String transactionFileName) {
        List<Owner> owners = loadObjectsFromCSV(ownerFileName, Owner.class);
        List<Property> properties = loadObjectsFromCSV(propFileName, Property.class);
        List<Transaction> transactions = loadObjectsFromCSV(transactionFileName, Transaction.class);
        return new Database(properties, owners, transactions);
    }

    /**
    * @param T
    * @param fileName
    * @return List
    **/
    // pass in param of class t, prints out list of this type
    private static <T extends Saveable> List<T> loadObjectsFromCSV(String fileName, Class<T> cls) {
        if (checkIfExists(fileName)) {
            List<String> lines;
            try {
                lines = getLines(fileName);
            } catch (IOException e) {
                lines = new ArrayList<String>();
                e.printStackTrace();
            }
            List<T> props = lines.stream().map(elt -> Factory.parseLine(elt, cls)).collect(Collectors.toList());
            return props;
        } else {
            return new ArrayList<T>();
        }
    }
/**
* @param propFileName
* @param owwnerFileName
* @param transactionFileName
* @return void
* 
**/
    public static void saveToCSVs(String propFileName, String ownerFileName, String transactionFileName,
            Database data) throws IOException {
        saveObjectsToCSV(propFileName, data.properties);
        saveObjectsToCSV(ownerFileName, data.owners);
        saveObjectsToCSV(transactionFileName, data.transactions);
    }

    /**
    * writes objects data to the CSV
    * @param filename
    * @param objects
    * @return void
    **/
    private static void saveObjectsToCSV(String fileName, List<? extends Saveable> objects) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

        writer.write(ownerHeaders());
        writer.newLine();
        for (int i = 0; i < objects.size(); i++) {
            writer.write(objects.get(i).toCSVString());
            writer.newLine();
        }
        writer.close();
    }

    /**
    * Checks if the file exists
    * @param path
    * @return boolean
    * 
    **/
    private static boolean checkIfExists(String path) {
        File tempFile = new File(path);
        return tempFile.exists();
    }
    /**
    * Checks if the file exists
    * @param path
    * @return List
    * 
    **/
    private static List<String> getLines(String path) throws IOException {
        ArrayList<String> lines = new ArrayList<String>();
        String row;
        int lineNo = 0;
        BufferedReader csvReader = new BufferedReader(new FileReader(path));
        while ((row = csvReader.readLine()) != null) {
            if(lineNo != 0){ // Dont pass header
                lines.add(row);
            }
            lineNo = lineNo + 1;
        }
        csvReader.close();
        return lines;
    }

    /**
    * Headers of properties
    * @return String
    **/
    public static String propHeaders() {
        return "owners,address,eircode,location,principle,value,year";
    }
    
    /**
    * headings for owner
    * @return String
    **/ 
    public static String ownerHeaders() {
        return "name";
    }
    
    /**
    * headers for transactions
    * @return String
    **/
    public static String transactionsHeaders() {
        return "owner,property,amount,year";
    }
    /**
    * encodes strings to dal with commas
    * @return String
    **/
    public static String encodeString(String s) {
        return new String(Base64.getEncoder().encode(s.getBytes()));
    }
    /**
    * decodes strings
    * @return String
    **/
    public static String decodeString(String s) {
        return new String(Base64.getDecoder().decode(s.getBytes()));
    }
}
