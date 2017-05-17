package hraci;

import java.util.ArrayList;
import lodicky.More;

/**
 * Počítačová úroveň číslo 2. 
 * 
 * @version 2015.06.11
 * @author Katarína Pilarčíková
 */
public class Inteligencia2 extends Inteligencia {
    private final ArrayList<Suradnice> netrafenePolicka;
    
    /**
     * Konštruktor.
     * @param plocha inteligencie2
     */
    public Inteligencia2(More plocha) {
        super("PočítačÚroveň2", plocha);

        this.netrafenePolicka = new ArrayList<>();
        this.naplnPolicka();
    }
   
    /**
     * Počítač strieľa na pole protihráča, tam kde este nestrelil.
     * @param cielovy  protihráč
     */
    @Override
    public void vystrel(Hrac cielovy) {
        int cislo = this.dajNahodneCislo(this.netrafenePolicka.size());
        Suradnice strela;
        strela = this.netrafenePolicka.get(cislo);
        this.vystrelNaPoziciu(cielovy, strela);
        this.netrafenePolicka.remove(cislo);
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
}
