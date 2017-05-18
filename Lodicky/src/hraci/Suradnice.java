package hraci;

import java.io.Serializable;

/**
 * Súradnice v hracej ploche pouzívané pri nastavovaní a strielaní. 
 * 
 * @version 2017.05.18
 * @author Katarína Pilarčíková
 */
public class Suradnice implements Serializable {
    private final int riadok;
    private final int stlpec;
    
    /**
     * Konštruktor súradníc udáva polohu.
     * @param riadok riadok
     * @param stlpec stĺpec
     */
    public Suradnice(int riadok, int stlpec) {
        this.riadok = riadok;
        this.stlpec = stlpec;
    }
    
    /**
     * Porovná rovnosť dvoch inštancií.
     * @param objekt objekt pre porovnanie
     * @return true ak su zhodne, false ak nie
     */
    @Override
    public boolean equals(Object objekt) {
        if (objekt == null) {
            return false;
        } else if (this == objekt) {
            return true;
        } else if (!(objekt instanceof Suradnice)) {
            return false;
        } else {
            Suradnice suradnice = (Suradnice) objekt;
            
            boolean riadkyEq = this.riadok == suradnice.riadok;
            boolean stlpceEq = this.stlpec == suradnice.stlpec;
            
            return riadkyEq && stlpceEq;
        }
    }

    /**
     * Vygeneruje hash kod pre danu instanciu.
     * @return hash kod
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + this.riadok;
        hash = 59 * hash + this.stlpec;
        
        return hash;
    }
    
    /**
     * Vráti súradnice ako stingovú hodnotu. 
     * @return súradnice stringovo
     */
    @Override
    public String toString() {
        return "riadok " + (this.riadok + 1) + " stlpec " + (this.stlpec + 1);
    }
    
    /**
     * Vráti riadok súradnice.
     * @return riadok
     */
    public int dajRiadok() {
        return this.riadok;
    }
    
    /**
     * Vráti stĺpec súradnice
     * @return stĺpec
     */
    public int dajStlpec() {
        return this.stlpec;
    }
    
    /**
     * Overi existenciu novej suradnice.
     * @return true ak existuje v poli 
     */
    public boolean existujeTaka() {
        return ((this.riadok < 10 ) && (this.riadok >= 0)) &&
                ((this.stlpec < 10 ) && (this.stlpec >= 0)); 
    } 
}
