/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skuskapenkineosko;

import java.util.ArrayList;

/**
 *
 * @author Katka
 */
public class SkuskaPenkineOSko {

    private ArrayList<Skuska> skusky;
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        Aplikacia app = new Aplikacia();
        app.vypisSkusky();
        app.vypisSkuskuMeno("1 0 . 1 . 2 0 1 7");
    }
    
    
 
    
}
