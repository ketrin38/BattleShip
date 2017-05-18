package lodicky;

import hraci.Inteligencia1;
import hraci.Inteligencia2;
import hraci.Inteligencia3;
import hraci.Uzivatel;


/**
 * Enum typov novej hry.
 * 
 * @version 2017.05.18
 * @author Katarína Pilarčíková
 */
public enum TypNovejHry {
    INTELIGENCIA_1,
    INTELIGENCIA_2,
    INTELIGENCIA_3,
    UZIVATEL_VS_UZIVATEL;
    /**
     * Vytvotenie typu novej hry.
     * @param cislo cislo prisluchajuce k typu hry.
     * @return hra daneho typu
     */
    public static LogikaHry vybratieTypu(int cislo) {
        More poleUzivatel = new More("More používateľa");  // uzivatel
        More pole2 = new More("More protihráč");
        Uzivatel uzivatel = new Uzivatel(poleUzivatel);
        LogikaHry hra = null;
        switch( cislo ) {
            case 1 :
                Inteligencia1 pc1 = new Inteligencia1 (pole2);
                hra = new LogikaHry(uzivatel, pc1);
                return hra;
            case 2 : 
                Inteligencia2 pc2 = new Inteligencia2 (pole2);
                hra = new LogikaHry(uzivatel, pc2);
                return hra;
            case 3 : 
                Inteligencia3 pc3 = new Inteligencia3 (pole2);
                hra = new LogikaHry(uzivatel, pc3);
                return hra;
            case 4 :
                Uzivatel uzivatel2 = new Uzivatel(pole2);
                hra = new LogikaHry(uzivatel, uzivatel2);
                return hra;
            default :
                return hra;
        } 
    }
}
