package grafika;


import java.io.Serializable;
import hraci.Suradnice;

/**
 * Trieda policko vytvara jedno policko a uchovava v 
 * nom vsetky potrebne informacie.
 * 
 * @version 2015.01.11
 * @author Katarina Pilarcikova
 */
public class Policko implements Serializable {
    private final Stvorec jednoPolicko;
    private final Suradnice suradnicePolicka;
    private final String farba;
    private boolean siLodicka;
    private boolean siPotopena;    
    
    /**
     * Toto je jedno hracie policko, ktore nesie v sebe vsetky informacie.
     * @param polohaX poloha X
     * @param polohaY poloha Y
     * @param farba   farba policka
     */
    public Policko(int polohaX, int polohaY, String farba) {
        this.suradnicePolicka = new Suradnice (polohaX, polohaY);
        // Vykreslenie
        this.jednoPolicko = new Stvorec();
        this.jednoPolicko.zmenFarbu(farba);
        this.jednoPolicko.posunVodorovne(this.suradnicePolicka.dajRiadok() - 60);
        this.jednoPolicko.posunZvisle(this.suradnicePolicka.dajStlpec() - 50);
        this.jednoPolicko.zmenStranu(31);
        this.jednoPolicko.zobraz();
        // Uchovanie pre Hru
        this.farba = farba;
        this.siLodicka = false;
        this.siPotopena = false;
    }    
    
    /**
     * Vykresli lodicku. Policku, kde sa nachadza lodicka zmeni farbu,
     * cim oznaci, ze tam je lod.
     * @param jejFarba farba lode
     */
    public void nakresliLodicku(String jejFarba) {
        this.jednoPolicko.zmenFarbu(jejFarba);
        this.siLodicka = true;
        this.jednoPolicko.zobraz();
    }
    
    /**
     * Pri trafeni lode, prefarbi dane policko na zeleno.
     */
    public void trafilLodicku() {
        this.siPotopena = true;
        this.jednoPolicko.zmenFarbu("green");
    } 
    
    /**
     * Pri netrafeni lode, vykona prefarbenie policka na cervenu.
     */
    public void netrafilLodicku() {
        this.jednoPolicko.zmenFarbu("red");
    }
    
    /**
     * Vrati farbu policka.
     * @return farbu policka
     */
    public String dajFarbu() {
        return this.farba;
    }
    
    /**
     * Vrati polohu X.
     * @return poloha X
     */
    public int dajPolohuX() {
        return this.suradnicePolicka.dajRiadok();
    }
    
    /**
     * Vrati polohu Y.
     * @return poloha Y
     */
    public int dajPolohuY() {
        return this.suradnicePolicka.dajStlpec();
    }
    
    /**
     * Vrati hodnotu atributu siLodicka
     * @return true, ak na danej pozicii je
     */
    public boolean siLodicka() {
        return this.siLodicka;
    }
    
    /**
     * Vrati hodnotu atributu siPotopena
     * @return true, ak uz bola trafena
     */
    public boolean siPotopena() {
        return this.siPotopena;
    }
    
    /**
     * Zobrazí dané políčko.
     */
    public void zobraz() {
        this.jednoPolicko.zobraz();
    }
} 


