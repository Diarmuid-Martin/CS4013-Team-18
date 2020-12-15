/**
* @author(Liam Ryan)
**/
package com.team18.taxprogram.ui;

import java.util.List;

import com.team18.taxprogram.accounting.Statement;
import com.team18.taxprogram.model.Owner;
import com.team18.taxprogram.model.Property;

public interface Controller {
    public void main();
    public void adminOptions();
    public void ownerLoginPage();
    public void ownerPage(Owner owner);
    public void registerNewProperty(Owner owner);
    public void displayProperties(List<Property> props);
    public void displayStmt(Statement stmt);
}
