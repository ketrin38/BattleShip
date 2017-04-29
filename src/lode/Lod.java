package lode;

import java.util.Random;
import grafika.HraciaPlocha;
import hraci.Suradnice;

/**
 * Trieda lode
 * 
 * @version 2015.06.22
 * @author Katarína Pilarčíková
 */
public class Lod {
    private static final int MAX_POCET_POKUSOV_ULOZENIE = 1;
    
    private final int pocetPolicok;
    private final String farbaLode;
    private final int generatorUlozenia;
    
    /**
     * Konštruktor lode. 
     * @param pocetPolicok počet políčok lode
     * @param farbaLode   farba lode
     */
    public Lod(int pocetPolicok, String farbaLode) {
        this.pocetPolicok = pocetPolicok;
        this.farbaLode = farbaLode;
        this.generatorUlozenia = 11 - this.pocetPolicok;
    }
    
   /**
    * Uloží loď podľa súradníc, na danú hraciu plochu.
    * @param orientacia orienácie lode
    * @param hraciaPlocha hracia polcha, kde sa loď uloží
    * @throws lode.NemozneUlozenieLodeException
    */
    public void ulozLod(Orientacia orientacia, HraciaPlocha hraciaPlocha)
        throws NemozneUlozenieLodeException {
        this.pokusUlozenieLode(orientacia, hraciaPlocha, this.farbaLode);
    }
    
    /**
    * Uloží loď podľa súradníc, na danú hraciu plochu.
    * @param orientacia orienácie lode
    * @param hraciaPlocha hracia polcha, kde sa loď uloží
    * @throws lode.NemozneUlozenieLodeException
    */
    public void ulozLodPc(Orientacia orientacia, HraciaPlocha hraciaPlocha)
        throws NemozneUlozenieLodeException {
        this.pokusUlozenieLode(orientacia, hraciaPlocha, "blue");
    }
    
    /**
     * Vráti počet políčok, ktoré loď zaberá.
     * @return počet políčok lode
     */
    public int dajPocetPolicok() {
        return this.pocetPolicok;
    }
    
    /**
     * Metóda náhodne generuje umiestnenie lode.
     * @param orientacia orientácie lode
     * @return súradnice lode
     */
    private Suradnice genNahodneUmiestnenie(Orientacia orientacia) {
        Random rand = new Random();
        
        int hodnotaA = rand.nextInt(10);
        int hodnotaB = rand.nextInt(this.generatorUlozenia);
        
        if (orientacia == Orientacia.HORIZONTALNE) {
            return new Suradnice(hodnotaA, hodnotaB);
        } else {
            return new Suradnice(hodnotaB, hodnotaA);
        }
    }
    
    
    /**
     * Pokus o uloženie lode
     * @param orientacia orientacia lode
     * @param hraciaPlocha hracia plocha, kam sa ma vykreslit
     * @param farba farba lode
     * @throws NemozneUlozenieLodeException 
     */
    private void pokusUlozenieLode(Orientacia orientacia,
            HraciaPlocha hraciaPlocha, String farba)
        throws NemozneUlozenieLodeException {
        Suradnice suradnice = null;
        
        for (int i = 0; i < MAX_POCET_POKUSOV_ULOZENIE; i++) {
            Suradnice nahodneSuradnice = this.genNahodneUmiestnenie(orientacia);
            
            if (hraciaPlocha.jeMozneUlozitLod(this, orientacia,
                    nahodneSuradnice)) {
                suradnice = nahodneSuradnice;
                break;
            }
        }
        
        if (suradnice == null) { // Nepodarilo sa ulozit
            throw new NemozneUlozenieLodeException("Nemozno ulozit danu lod "
                    + "na hracie pole!");
        }
        
        hraciaPlocha.nakresliLodicku(this, suradnice, orientacia, farba);
    }
}

