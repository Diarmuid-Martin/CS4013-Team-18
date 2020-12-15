/**
* @author(Liam Ryan)
*
**/
package com.team18.taxprogram.accounting;

public interface Statement {
    public String toText();
    public void addLine(LineItem line);
    public LineItem lastLine();
}
