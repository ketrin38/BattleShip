package lodicky;

import java.io.Serializable;
import hraci.Hrac;
import hraci.Uzivatel;
import java.util.Random;
import javax.swing.JOptionPane;
import lode.NemozneUlozenieLodeException;

/**
 * Spravuje chod hry, striedanie, kto začína hru, kontroluje celý priebeh.
 *  
 * @version 2015.06.11
 * @author Katarína Pilarčíková
 */
public class LogikaHry implements Serializable {
    private Hrac prvyHrac;
    private Hrac druhyHrac;
    private transient Parser parser;
    private Gui hraOkno;

    /**
     * Konštruktor logiky hry.
     * @param prvyHrac prvý hráč v hre 
     * @param druhyHrac druhý hráč v hre 
     */
    public LogikaHry(Hrac prvyHrac, Hrac druhyHrac) {
        this.prvyHrac = prvyHrac;
        this.druhyHrac = druhyHrac;
        this.parser = Parser.dajInstanciu();
        this.hraOkno = null;
        
    }
    
    /**
     * Priebeh celej hry. 
     * @param novaHra indikuje ci je dana hra nova alebo nacitana zo suboru
     */
    public void hra(boolean novaHra) {
        this.hraOkno = new Gui(this.prvyHrac.dajHraciaPlocha(),this.druhyHrac.dajHraciaPlocha());    
        if (novaHra) {         
            this.hraOkno.vykresli();
            this.inicializujPoradieHracov();
            this.inicializujHru();
            this.hraOkno.nastavPocty(this.prvyHrac.dajNepotopene(), this.druhyHrac.dajNepotopene());
        } else {
            this.prvyHrac.dajHraciaPlocha().prekresli();
            this.druhyHrac.dajHraciaPlocha().prekresli();
            this.hraOkno.nastavPocty(this.prvyHrac.dajNepotopene(), this.druhyHrac.dajNepotopene());
            this.hraOkno.vykresli(); 
        }
              
        Hrac vitaz = null;
        
        while (true) {                 
            this.vypisOramovanie();
           
            this.prvyHrac.vystrel(this.druhyHrac);
            if (this.jeKoniecHry()) {
                vitaz = this.prvyHrac;
                break;
            }
            this.druhyHrac.vystrel(this.prvyHrac);
            if (this.jeKoniecHry()) {
                vitaz = this.druhyHrac;
                break;
            }
            
            this.vypisInfoOPolickach(this.prvyHrac, this.druhyHrac);
            this.vypisOramovanie();
            // Automaticke ukladanie po kazdrom kroku
            Lodicky.dajInstanciu().ulozHru();
        }
        
        if ((JOptionPane.showConfirmDialog(null, "Hra bola úspešne ukončená." +
                "Víťazom sa stal hráč " + vitaz + "!!!"
                + "\nPáčila sa vám hra ?", "", JOptionPane.YES_NO_OPTION)) == JOptionPane.YES_OPTION)
            
	    System.exit(0);
	else
	    System.exit(0);
    }
    
    /**
     * Inicializácia hry.
     */
    private void inicializujHru() {
        this.nastaveniePoctovLodi();
        
        if ( ( this.prvyHrac  instanceof Uzivatel )  && 
                ( this.druhyHrac  instanceof Uzivatel )) {
      
            this.prvyHrac.zmenMeno(" - 1. hráč");
            this.druhyHrac.zmenMeno(" - 2. hráč");
            if (!this.naplnPoliaNaModro()) {
                System.out.println("Koniec hry, neplatna konfiguracia");
                System.exit(0);
            }
        } else {
            if (!this.naplnPoliaStandardne()) {
                System.out.println("Koniec hry, neplatna konfiguracia");
                System.exit(0);
            }
        } 
        
        int nepotopene = this.prvyHrac.dajPocetObsadenychPolicok();
        this.prvyHrac.nastavNepotopene(nepotopene);
        this.druhyHrac.nastavNepotopene(nepotopene);
    }
    
    /**
     * Náhodne vyberie poradie hráčov.
     */
    private void inicializujPoradieHracov() {
        Random rand = new Random(); 
        boolean hodnota = rand.nextBoolean();
        
        if (hodnota) { // false - ponechame povodne, true - vymena poradia
            this.vymenPoradieHracov();
        }
        this.hraOkno.nastavPrveho(this.prvyHrac.dajMeno());
        System.out.println("Hru začína hráč '" + this.prvyHrac );
    }
    
    /**
     * Vymení poradie hráčov.
     */
    private void vymenPoradieHracov() {
        Hrac docastny = this.prvyHrac;
        this.prvyHrac = this.druhyHrac;
        this.druhyHrac = docastny;
    }
    
    /**
     * Má na starosti ukončenie hry ak jeden z hráčov trafí všetky
     * súperove lode.
     * @return true ak hra skončila
     */
    private boolean jeKoniecHry() {
        boolean prvySkoncil = this.prvyHrac.dajNepotopene() == 0;
        boolean druhySkoncil = this.druhyHrac.dajNepotopene() == 0;
        
        return prvySkoncil || druhySkoncil;
    }
    
    private boolean naplnPoliaStandardne() {
        while (true) {
            try {
                this.prvyHrac.naplnHraciePole();
                this.druhyHrac.naplnHraciePole();
                return true;
            } catch (NemozneUlozenieLodeException ex) {
                this.kontrolaDostupnostiParseru();
                boolean volba = this.parser.nacitajVolbuOpatovnyVyberLodi();
                if (!volba) {
                    return false;
                }
                this.prvyHrac.resetPoctyLodi();
                this.druhyHrac.resetPoctyLodi();
                this.nastaveniePoctovLodi();
            }
        }
    }
    
    private boolean naplnPoliaNaModro() {
        while (true) {
            try {
                this.prvyHrac.naplnPole();
                this.druhyHrac.naplnPole();
                return true;
            } catch (NemozneUlozenieLodeException ex) {
                this.kontrolaDostupnostiParseru();
                boolean volba = this.parser.nacitajVolbuOpatovnyVyberLodi();
                if (!volba) {
                    return false;
                }
                this.prvyHrac.resetPoctyLodi();
                this.druhyHrac.resetPoctyLodi();
                this.nastaveniePoctovLodi();
            }
        }
    }
    
    // Nutne pri opatovnom spusteni hry zo suboru, parser je nutne vytvor znovu
    private void kontrolaDostupnostiParseru() {
        if (this.parser == null) {
            this.parser = Parser.dajInstanciu();
        }
    }
    /**
     * Vypíše orámovanie medzi dvojicami strieľaní.
     */
    private void vypisOramovanie() {
        String txt = "********************************************************";
        System.out.println(txt);
    }
    /**
     * Vypíše aktuálny stav. Stav koľko nepotopených má
     * utočiaci a cieľový hráč.
     * @param utociaci
     * @param cielovy 
     */
    private void vypisInfoOPolickach(Hrac utociaci, Hrac cielovy) {
        this.hraOkno.nastavPocty(utociaci.dajNepotopene(), cielovy.dajNepotopene());
    }
    
    private void nastaveniePoctovLodi() {
        this.prvyHrac.initPoctyLodi();
        this.druhyHrac.duplikujPoctyLodi(this.prvyHrac);
    }
    
}