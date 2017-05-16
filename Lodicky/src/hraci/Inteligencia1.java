package hraci;

import grafika.HraciaPlocha;

/**
 * Počítačová úroveň číslo 1. 
 * 
 * @version 2015.06.11
 * @author Katarína Pilarčíková
 */
public class Inteligencia1 extends Inteligencia {
    
    /**
     * Vytvorí počítačovú úroveň číslo 1.
     * @param plocha plocha, kde sa nachádza
     */
    public Inteligencia1(HraciaPlocha plocha) {
        super("PočítačÚroveň1", plocha);
    }
   
    /**
     * Random stieľanie na protihráča.
     * @param cielovy protihráč
     */
    @Override
    public void vystrel(Hrac cielovy) {
        int riadok = this.dajNahodneCislo();
        int stlpec = this.dajNahodneCislo();
        
        this.vystrelNaPoziciu(cielovy, new Suradnice(riadok, stlpec));
    }

    
    
}
