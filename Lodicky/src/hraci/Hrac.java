package hraci;

import java.io.Serializable;
import java.util.HashMap;
import lode.NemozneUlozenieLodeException;
import lode.TypLode;
import lodicky.More;

/**
 * Trieda pre hráčov uchováva informácie o jeho mene, o počte jeho lodí i
 * o tom koľko políčok ešte nemá potopených.
 * 
 * @version 2017.05.18
 * @author Katarína Pilarčíková
 */
public abstract class Hrac implements Serializable {
    private final More hraciaPlocha;
    private final HashMap<TypLode, Integer> poctyLodi;  
    private String meno;
    private int nepotopene;
    
    /**
     * Konštruktor hráča.
     * @param meno meno hráča
     * @param hraciaPlocha hracia plocha, kde hrá hrč
     * */
    public Hrac(String meno, More hraciaPlocha) {
        this.meno = meno;
        this.nepotopene = 0;
        this.hraciaPlocha = hraciaPlocha;
        this.poctyLodi = new HashMap<>();
    }
    
    /**
     * Inicializuje počet lodí.
     */
    public abstract void initPoctyLodi();
    
    /**
     * Vystrelenie na protihráča.
     * @param cielovy protihráč
     */
    public abstract void vystrel(Hrac cielovy);
    
    /**
     * Naplenie hracej plochy.
     * @throws lode.NemozneUlozenieLodeException
     */
    public abstract void naplnHraciePole() throws NemozneUlozenieLodeException;
    
    
    /**
     * Naplní hracie pole loďami.
     * @throws lode.NemozneUlozenieLodeException
     */
    public abstract void naplnPole() throws NemozneUlozenieLodeException;
   
    /**
     * Nastaví počty lodí pre daný typ.
     * @param typLode typ lode
     * @param pocet  počet lodí
     */
    public void nastavPoctyLodi(TypLode typLode, int pocet) {
        this.poctyLodi.put(typLode, pocet);
        
    }
    
    /**
     * Posúva počty lodí dalšiemu hráčovi.
     * @param dalsiHrac další hráč
     */
    public void duplikujPoctyLodi(Hrac dalsiHrac) {
        for (TypLode typLode : TypLode.values()) {
            this.nastavPoctyLodi(typLode, dalsiHrac.dajPocetLodi(typLode));
        }
    }
    
    /**
     * Nastaví počet nepotopených lodí.
     * @param pocetNepotopenych počet nepotopených lodí
     */
    public void nastavNepotopene(int pocetNepotopenych) {
        this.nepotopene = pocetNepotopenych;
    }
    
    /**
    * Zmaže počty lodí.
    */
    public void resetPoctyLodi() {
        this.poctyLodi.clear();
    }
    
    /**
     * Vráti počet obsadených políčok.
     * @return počet obsadených políčok
     */
    public int dajPocetObsadenychPolicok() {
        int pocet = 0;
        
        for (TypLode typLode : TypLode.values()) {
            pocet += (this.dajPocetLodi(typLode) * typLode.dajZabratePolicka());
        }
        
        return pocet;
    }
    
    /**
     * Vráti meno ako string.
     * @return meno hráča
     */ 
    @Override
    public String toString() {
        return this.meno;
    }
    
     /**
     * Vráti počet lodí daného typu, ktorý má hráč. 
     * @param typLode typ lode
     * @return počet dan
     */
    public int dajPocetLodi(TypLode typLode) {
        return this.poctyLodi.get(typLode);
    }
    
    /**
     * Vráti hraciu plochuhráča.
     * @return hraciu plochu hráča
     */
    public More dajHraciaPlocha() {
        return this.hraciaPlocha;
    }
    
     /**
     * Vráti počet políčok, ktoré ešte hráč nemá potopené.
     * @return nepotopené
     */
    public int dajNepotopene() {
        return this.nepotopene;
    }
    
     /**
     * Zmení meno hráča. 
     * @param noveMeno  nové meno hráča
     */
    public void zmenMeno(String noveMeno) {
        this.meno = noveMeno;
    }
    
     /**
     * Vreti meno hráča. 
     * @return meno hráča
     */
    public String dajMeno() {
        return this.meno;
    }
    
    /**
     * Strieľanie na protihráčovu plochu a oznámenie hráčovi, či trafil.
     * @param cielovy hráč, na ktorého plochu sa strieľa
     * @param suradnice súradnice kam stieľa
     */
    protected void vystrelNaPoziciu(Hrac cielovy, Suradnice suradnice) {
        More cielovaHraciaPlocha = cielovy.dajHraciaPlocha();
        
        String sprava;
        if (cielovaHraciaPlocha.jeLodickaNa(suradnice)) {
            if ( !cielovaHraciaPlocha.jePotopenaNa(suradnice) ) {
                cielovy.nepotopene--;
            } 
            
            cielovaHraciaPlocha.oznacTrafenuLodicku(suradnice);
            sprava = "BOLA";
        } else {
            cielovaHraciaPlocha.oznacNetrafenuLodicku(suradnice);
            sprava = "NEBOLA";
        }
        
        System.out.println("Lod na pozicii: " + suradnice + " " + sprava +
                " zasiahnuta");
    }
    

}
