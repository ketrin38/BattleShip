package lode;

/**
 * Trieda uchováva typy lodí, teda aj ich názvy a počty políčok,
 * ktoré zaberajú.
 * 
 * @version 2015.06.22
 * @author Katarína Pilarčíková
 */
public enum TypLode {
    CLN("čln", 2, "yellow"),
    PONORKA("ponorka", 3, "brown"),
    BOJOVA_LOD("bojová loď", 4, "magenta"),
    LIETADLOVA_LOD("lietadlová loď", 5, "white");
    
    private final String nazov;
    private final int zabratePolicka;
    private final String farba;
    
    
    /**
     * Vytvorí novú inštanciu lode. 
     * @param typLode pozadovaný typ lode
     * @return typ lode
     */
    public static Lod vytvorNovuInstanciu(TypLode typLode) {
        switch (typLode) {
            case CLN:
                return new Lod(CLN.dajZabratePolicka(), CLN.dajFarbu());
            case PONORKA:
                return new Lod(PONORKA.dajZabratePolicka(), PONORKA.dajFarbu());
            case BOJOVA_LOD:
                return new Lod(BOJOVA_LOD.dajZabratePolicka(),
                        BOJOVA_LOD.dajFarbu());
            case LIETADLOVA_LOD:
                return new Lod(LIETADLOVA_LOD.dajZabratePolicka(),
                        LIETADLOVA_LOD.dajFarbu());
            default:
                return null;
        }
    }
    
    /**
     * Vracia hodnotu atribútu udaávajúcu počet zabratých políčok.
     * @return zabraté políčka
     */
    public int dajZabratePolicka() {
        return this.zabratePolicka;
    }
    
    /**
     * Vrati farbu lode
     * @return farba lode
     */
    public String dajFarbu() {
        return this.farba;
    } 
    
    /**
     * Metóda vracia popisy jednotlivých lodí v strigovej hodnote.
     * @return popis lode v stringovej hodte
     */
    @Override
    public String toString() {
        return this.nazov + " (" + this.zabratePolicka + "-polickova)";
    }
    
    /**
     * Konstruktor.
     * @param nazov názov lode
     * @param zabratePolicka počet políčok,ktoré zaberá
     * @param farbaLode farba danej lode
     */
    private TypLode(String nazov, int zabranePolicka, String farbaLode) {
        this.nazov = nazov;
        this.zabratePolicka = zabranePolicka;
        this.farba = farbaLode;
    }
    
    
}
