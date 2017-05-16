package lode;

import java.util.Random;
/**
 * Narába s orientáciou lodí. Horizontálnou alebo vertinállnou polohou.
 * 
 * @version 2015.06.11
 * @author Katarína Pilarčíková
 */
public enum Orientacia {
    HORIZONTALNE,
    VERTIKALNE;
    
    /**
     * Vráti náhodnú orientáciu lode pre inteligencie.
     * @return nahodna poloha lode
     **/
    public static Orientacia dajNahodnuOrientaciuLode() {
        Random r = new Random(); // pseudonahodny generator cisel
        int poc = Orientacia.values().length;
        
        return Orientacia.values()[r.nextInt(poc)];
    }
   
    /**
     * Nastaví orientáciu lode podľa toho, ako ju užívateľ zadal.
     * @param zadane zdaná orientácia
     * @return zadanu orientaciu lode
     */
    public static Orientacia prelozOrientaciuLode(String zadane) {
        if (zadane.equalsIgnoreCase("h")) {
            return Orientacia.valueOf("HORIZONTALNE");
        } else if (zadane.equalsIgnoreCase("v")) {
            return Orientacia.valueOf("VERTIKALNE");
        } else {
            return null;
        }
    }
}