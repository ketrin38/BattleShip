package hraci;

import lodicky.More;

/**
 * Počítačová úroveň číslo 1. 
 * 
 * @version 2017.05.18
 * @author Katarína Pilarčíková
 */
public class Inteligencia1 extends Inteligencia {
    
    /**
     * Vytvorí počítačovú úroveň číslo 1.
     * @param plocha plocha, kde sa nachádza
     */
    public Inteligencia1(More plocha) {
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
