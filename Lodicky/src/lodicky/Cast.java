/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lodicky;

import java.io.Serializable;

/**
 * Objekt predstavuje jednu cast hracej plochy,
 * ktorý zároveň uchováva aj svoj stav.
 * 
 * @version 2017.05.18
 * @author Katarína Pilarčíková
 */
public class Cast implements Serializable {
    private boolean jeLodicka;
    private boolean jePotopena;
    private String farba;
    
    /**
     * Konštruktor časti hracje plochy.
     */
    public Cast() 
    {
        this.jeLodicka = false;
        this.jePotopena = false;
        this.farba = "blue";
    }
    
    /**
     * Metóda zmení farbu časti na požadovanú.
     * @param farba názov farby políčka
     */
    public void ocnacLodicku(String  farba) {
        this.jeLodicka = true;
        this.farba = farba; 
    }
    
    /**
     * Potopí lodičku, ktorá sa nachádzala na tejto časti.
     */
    public void potopLodicku() {
        this.jePotopena = true;
        this.farba = "green"; 
    }
    
    /**
     * Metóda označí časť ako nezasiahnutú loď.
     */
    public void nezasiaholLodicku() {
        this.farba = "red"; 
    }
    
    /**
     * Metóda vráti stav časti, či je  časť lodičkou, alebo nie.
     * @return jeLodicka
     */
    public boolean jeToLodicka() {
        return this.jeLodicka;
    }
    
    /**
     * Metóda vráti stav časti, či je potopenou, alebo nie.
     * @return jePotopena
     */
    public boolean jePotopena() {
        return this.jePotopena;
    }
    
    /**
     * Metóda vráti aktuálnu farbu časti.
     * @return jeLodicka
     */
    public String dajFarbu() {
        return this.farba;
    }   
}
