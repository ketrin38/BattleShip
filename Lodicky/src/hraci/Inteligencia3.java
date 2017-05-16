package hraci;


import grafika.HraciaPlocha;
import java.util.ArrayList;
import lode.NemozneUlozenieLodeException;

/**
 * Počítačová úroveň číslo 3. 
 * 
 * @version 2015.06.11
 * @author Katarína Pilarčíková
 */
public class Inteligencia3 extends Inteligencia {
    private final ArrayList<Suradnice> netrafenePolicka;
    private Suradnice poslednaTrafena;
    
    /**
     * Vytvorí počítačovú úroveň číslo 3.
     * @param plocha plocha, kde sa nachádza
     */
    public Inteligencia3(HraciaPlocha plocha) {
        super("PočítačÚroveň3", plocha);
        
        this.netrafenePolicka = new ArrayList<>();
        this.poslednaTrafena = null;
        this.naplnPolicka();
    }
   
    /**
     * Strielanie na protihraca. 
     * @param cielovy protihráč
     */
    @Override
    public void vystrel(Hrac cielovy) {
        Suradnice cielovaSur;
        // este na ziadnu suradnicu nebolo vystrelene, striela sa nahodne
        if (this.poslednaTrafena == null) {
            cielovaSur = this.nahodnaVolnaSuradnica();
        } else {
            cielovaSur = this.volnaSuradnicaOkolieTrafenej();
            if (cielovaSur == null) { // ziadna sa nenasla
                cielovaSur = this.nahodnaVolnaSuradnica();
                this.poslednaTrafena = null;
            }
        }
        
        this.vystrelNaPoziciu(cielovy, cielovaSur);
        this.netrafenePolicka.remove(cielovaSur);
        
        if (cielovy.dajHraciaPlocha().jeLodickaNa(cielovaSur)) {
            this.poslednaTrafena = cielovaSur;
        }
    }
    
    /**
     * Naplnenie zoznamu netrafenych policok.
     */
    private void naplnPolicka() {
        for (int i = 0; i < 10; i++) {
            for ( int j = 0 ; j < 10 ; j++ ) {
                this.netrafenePolicka.add(new Suradnice(i , j));
            }
        }  
    }
    
    /**
     * Najde volnu suradnicu na ktoru este nebolo vystrelene.
     * @return nahodnu volnu suradnicu
     */
    private Suradnice nahodnaVolnaSuradnica() {
        int poz = this.dajNahodneCislo(this.netrafenePolicka.size());
        Suradnice suradnice = this.netrafenePolicka.get(poz);
        
        return suradnice;
    }
    
    /**
     * Nájdi v okolí voľné susedné políčka, kam sa ešte nestrieľalo.
     * @return volnu súradnicu
     */
    private Suradnice volnaSuradnicaOkolieTrafenej() {
        ArrayList<Suradnice> zoznam = new ArrayList<>();
        
        Suradnice nad = new Suradnice (this.poslednaTrafena.dajRiadok(),
                this.poslednaTrafena.dajStlpec() + 1);
        Suradnice pod = new Suradnice (this.poslednaTrafena.dajRiadok(),
                this.poslednaTrafena.dajStlpec() - 1);
        Suradnice napravo = new Suradnice (this.poslednaTrafena.dajRiadok() + 1,
                this.poslednaTrafena.dajStlpec());
        Suradnice nalavo = new Suradnice (this.poslednaTrafena.dajRiadok() - 1,
                this.poslednaTrafena.dajStlpec());
        
        zoznam.add(nad);
        zoznam.add(pod);
        zoznam.add(napravo);
        zoznam.add(nalavo);
        
        for (Suradnice aktualna : zoznam) {
            if (aktualna.existujeTaka() && 
                    this.netrafenePolicka.contains(aktualna)) {
                return aktualna;
            }
        }
        
        return null;
    }      

}

