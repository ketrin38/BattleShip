/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lodicky;

import java.io.Serializable;

/**
 *
 * @author Katka
 */
public class Cast implements Serializable {
    private boolean jeLodicka;
    private boolean jePotopena;
    private String farba;
    
    public Cast() 
    {
        this.jeLodicka = false;
        this.jePotopena = false;
        this.farba = "blue";
    }
    
    public void ocnacLodicku(String  farba) {
        this.jeLodicka = true;
        this.farba = farba; 
    }
    
    public void potopLodicku() {
        this.jePotopena = true;
        this.farba = "green"; 
    }
    
    public void nezasiaholLodicku() {
        this.farba = "red"; 
    }
    
    public boolean jeToLodicka() {
        return this.jeLodicka;
    }
    
    
    public boolean jePotopena() {
        return this.jePotopena;
    }
    
    public String dajFarbu() {
        return this.farba;
    }
    
    
    
    
}
