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
public class Skuska {
    private String nazov;
    private ArrayList<StudentSkuska> zoznam;

    public Skuska(String nazov) {
        this.nazov = nazov;
        this.zoznam = new ArrayList<>();       
    }

    public String getNazov() {
        return nazov;
    }

    public ArrayList<StudentSkuska> getZoznam() {
        return zoznam;
    }
    public void pridaj(StudentSkuska student) {
      
        this.zoznam.add(student);
    }
    
    
    
    
}
