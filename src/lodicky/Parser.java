package lodicky;

import java.util.Scanner;

/**
 * Trieda parsera a zároveň má aj na starosti správne načítanie z klávesnice.
 * 
 * @version 2015.06.11
 * @author Katarína Pilarčíková
 */
public class Parser {
    private static Parser instancia = null;
    private final Scanner nacitavac;
    
    /**
     * Dá inštaciu parseru.
     * @return parser
     */
    public static Parser dajInstanciu() {
        if (instancia == null) {
            instancia = new Parser();
        }
        
        return instancia;
    }
    
    /**
     * Načíta, či užívateľ chce chrať uloženú hru alebo nie.
     * @return true ak ano, chce zacat s ulozenou hrou, false ak nie
     */
    public boolean nacitajVolbuZacatUlozenuHru() {
        System.out.println("Chceš spustiť naposledy rozohranú hru?");
        System.out.println("V prípade neúspešného načítania alebo voľby nie");
        System.out.println("bude spustená nová hra.");
        System.out.println("Y/y pre ÁNO ");
        System.out.println("N/n pre NIE");
        
        String volba = null;
        if (this.nacitavac.hasNext()) {
            volba = this.nacitavac.next();
        }
        return volba.equalsIgnoreCase("y");
    }
    
    /**
    * Kontrola vkladania počtu lodí.
    * @return pocet lodi
    */
    public int nacitajPocetLodi() {
        String pocetLodi = "";
        System.out.println("Zadajte počet lodí. Od 0 po 4");
        
        if (this.nacitavac.hasNext()) {
            pocetLodi = this.nacitavac.next();
        }
        
        while (true) {                
            if ( pocetLodi.equals("1") || pocetLodi.equals("2") ||
                 pocetLodi.equals("3") || pocetLodi.equals("4") || 
                 pocetLodi.equals("0")) {  
                int pocet = Integer.parseInt(pocetLodi);  
                return pocet;
            } else {
                System.out.println("Zadali ste zle. "
                        + "Počet lodí môže byť od 0 po 4. ");
                
                if (this.nacitavac.hasNext()) {
                    pocetLodi = this.nacitavac.next();
                }
            }
        }    
    }
    
    /**
    * Voľba čísla hry.
    * @return číslo hry
    */
    public int nacitajTypNovejHry() {
        String cislo = "";
        System.out.println("Vybete si typ novej hry : ");
        System.out.println("Zadajte príslušné číslo hry : ");
        System.out.println(" Číslo    Hra ");
        System.out.println("   1      začiatočník ");
        System.out.println("   2      pokročilý ");
        System.out.println("   3      expert ");
        System.out.println("   4      užívateľ vs užívateľ" );
        
        if (this.nacitavac.hasNext()) {
            cislo = this.nacitavac.next();
        }
        
        while (true) {                
            if ( cislo.equals("1") || cislo.equals("2") ||
                 cislo.equals("3") || cislo.equals("4") ) {  
                int pocet = Integer.parseInt(cislo);  
                return pocet;
            } else {
                System.out.println("Zadali ste zle. Opakujte voľbu ! ");
                
                if (this.nacitavac.hasNext()) {
                    cislo = this.nacitavac.next();
                }
            }
        }    
    }
    
    /**
     * Načíta voľbu opätovného načítania lodí
     * @return voľba Y alebo N
     */
    public boolean nacitajVolbuOpatovnyVyberLodi() {
        System.out.println("Nemozno ulozit dane lode na hracie pole.");
        System.out.println("Prajete si umoznit vyber lodi znovu? Y/N");
        
        String volba = null;
        
        if (this.nacitavac.hasNext()) {
            volba = this.nacitavac.next();
        }
        
        if (volba != null) {
            return volba.equalsIgnoreCase("y");
        } else {
            return false;
        }
    }
    
    /**
    * Kontrola vkladania suradníc 
    * @return súradnicu výstrelu na políčko
    */
    public int nacitajSuradnice() {
        String vlozene;
        int suradnica;
                
        do {
            System.out.println("Zadajte súradnicu, súradnica musí mať číslo "
                    + "od 1 až po 10");
            
            if (this.nacitavac.hasNext()) {
                vlozene = this.nacitavac.next();
            } else {                
                continue;
            }
            
            if ( vlozene.equals("1") || vlozene.equals("2") ||
                 vlozene.equals("3") || vlozene.equals("4") ||
                 vlozene.equals("5") || vlozene.equals("6") ||
                 vlozene.equals("7") || vlozene.equals("8") ||
                 vlozene.equals("9") || vlozene.equals("10") ) {
                
                suradnica = Integer.parseInt(vlozene);   
                break;
            }
        } while (true);
                
        return suradnica;        
    }
    
    /**
     * Načítanie orientácie lodí.
     * @return orientácia znak
     */
    public String nacitajOrientaciu() {
        String orientacia = "";
        System.out.println("Zadajte orientaciu lode znakom:" );
        System.out.println("H - horizontalna poloha" );
        System.out.println("V - vertikalna poloha" );
                
        if (this.nacitavac.hasNext()) {
            orientacia = this.nacitavac.next();
        }
        
        while (true) { 
            if (orientacia.equalsIgnoreCase("v") ||
                    orientacia.equalsIgnoreCase("h")) {
                return orientacia; 
            } else {
                System.out.println("Zadali ste zlu orientaciu zadajte znova H "
                        + "alebo V");
                
                if (this.nacitavac.hasNext()) {
                    orientacia = this.nacitavac.next();
                }
            } 
        }    
    } 
    
    /**
     * Zatvorenie aplikacie.
     */
    public void nacitajVolbuKoniecApp() {
        String koniec = "";
        System.out.println("Aplikaciu zatvorite stlacenim klavesu C ");
                
        if (this.nacitavac.hasNext()) {
            koniec = this.nacitavac.nextLine();
        }
        
        while (true) {
            if (koniec.equalsIgnoreCase("c")) {  
                System.exit(0);
            } else {
                System.out.println("Zadajte C alebo c");
                
                if (this.nacitavac.hasNext()) {
                    koniec = this.nacitavac.nextLine();
                }
            }
        }
    }
    
    /**
    * Voľba riešenia hry, v prípade, že sa nedajú uložiť všetky lode.
    * @return číslo hry
    */
    public int nacitajRiesenieZlehoUlozeniaLodi() {
        String cislo = "";
        System.out.println("Lode sa nedajú uložiť na hraciu plohu.");
        System.out.println("Máte dve možnosti : ");
        System.out.println(" Zadajte číslo, ak chcete :  ");
        System.out.println("   1      - Začať novú hru ");
        System.out.println("   2      - Opať zadať počty lodí");

        
        if (this.nacitavac.hasNext()) {
            cislo = this.nacitavac.next();
        }
        
        while (true) {                
            if ( cislo.equals("1") || cislo.equals("2") ) {  
                int pocet = Integer.parseInt(cislo);  
                return pocet;
            } else {
                System.out.println("Zadali ste zle. Opakujte voľbu ! ");
                
                if (this.nacitavac.hasNext()) {
                    cislo = this.nacitavac.next();
                }
            }
        }    
    }
    
    /**
     * Konštruktor.
     */
    private Parser() {
        this.nacitavac = new Scanner(System.in);
    }
}
