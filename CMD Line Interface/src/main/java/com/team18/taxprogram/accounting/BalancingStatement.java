/**
* @author(Liam Ryan)
*
**/
package com.team18.taxprogram.accounting;

import java.util.ArrayList;

public class BalancingStatement implements Statement{
    ArrayList<LineItem> lines;
    String headerString;
/**
* Constructor for the class
**/
    public BalancingStatement(String headerString) {
        this.headerString = headerString;
        lines = new ArrayList<LineItem>();
    }
/**
* Adds a line to the arrayList
* @param line 
* @return void
**/
    @Override
    public void addLine(LineItem line) {
        lines.add(line);
    }

    @Override
    public LineItem lastLine(){
        if(lines.size() > 0){
            return lines.get(lines.size() - 1);
        }
        else {
            return null;
        }
    }
/**
* Prints the details of the Statement
* @return String
**/
    @Override
    public String toText() {
        String text = headerString + "\n";
        for(int i = 0; i < lines.size(); i++) {
            text += lines.get(i).toString() + "\n";
        }
        return text;
    }
    
}
