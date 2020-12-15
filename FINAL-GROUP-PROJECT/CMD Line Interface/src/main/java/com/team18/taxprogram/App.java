/**
* @author(Liam Ryan)
**/

package com.team18.taxprogram;

import com.team18.taxprogram.model.Model;
import com.team18.taxprogram.ui.Controller;
import com.team18.taxprogram.ui.cmd.CMDController;

public final class App {
    private App() {
    }

    /**
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        assert(args.length == 4);
        Model model = new Model(args[0], args[1], args[2]);
        Controller ui = new CMDController(model);   
        ui.main();
    }
}
