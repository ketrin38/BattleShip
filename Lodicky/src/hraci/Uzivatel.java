package hraci;

import grafika.HraciaPlocha;
import lode.Orientacia;
import lodicky.Parser;
import lode.Lod;
import lode.NemozneUlozenieLodeException;
import lode.TypLode;

/**
 * Riadi správanie sa užívatela, pri nastavovaní hry,lodičiek
 * aj samotnom strieľaní na protihráčovu hraciu plochu.
 * 
 * @version 2015.06.11
 * @author Katarína Pilarčíková
 */
public class Uzivatel extends Hrac {
    private transient Parser parser;
    
    /**
     * Konštruktor užívateľa. Tu priradíme hraciu plochu užívateľovi.
     * @param plocha hracia plocha užívateľa
     */
    public Uzivatel(HraciaPlocha plocha) {
        super("Užívateľ", plocha);  
        this.parser = Parser.dajInstanciu();
    }
    
    /**
     * Strieľanie lodí užívateľa na protihráča. 
     * @param cielovy protihráč
     */
    @Override
    public void vystrel(Hrac cielovy) {
        if (this.parser == null) { 
            this.parser = Parser.dajInstanciu();
        }
        
        System.out.println("Zadajte súradnice výstrelu:");
        System.out.println("Riadok:");
        int riadok = this.parser.nacitajSuradnice();
        System.out.println("Stĺpec:");
        int stlpec = this.parser.nacitajSuradnice();
        
        Suradnice suradnice = new Suradnice(riadok - 1, stlpec - 1);
        System.out.print("Zadali ste súradnice: " + suradnice);
        
        this.vystrelNaPoziciu(cielovy, suradnice);
    }
    
    /**
     * Naplí hracie pole užívateľa lodičkami.
     * @throws lode.NemozneUlozenieLodeException
     */
    @Override
    public void naplnPole() throws NemozneUlozenieLodeException {
        this.nastavLodeNaModro(TypLode.CLN);
        this.nastavLodeNaModro(TypLode.PONORKA);
        this.nastavLodeNaModro(TypLode.BOJOVA_LOD);
        this.nastavLodeNaModro(TypLode.LIETADLOVA_LOD);
    }
    
    
    /**
     * Nastavenie orientácie lodí užívateľom.
     * @param typ typ lode
     */
    private void nastavLode(TypLode typLode) throws NemozneUlozenieLodeException {
    for (int i = 0; i < this.dajPocetLodi(typLode); i++) {
            Orientacia orientacia = Orientacia.dajNahodnuOrientaciuLode();
            Lod lod = TypLode.vytvorNovuInstanciu(typLode);
            lod.ulozLod(orientacia, this.dajHraciaPlocha());
        }
    }
    
    /**
     * Nastavenie orientácie lodí užíveteľom.
     * @param typ typ lode
     */
    private void nastavLodeNaModro(TypLode typ)
        throws NemozneUlozenieLodeException {
        int pocet = this.dajPocetLodi(typ);
        
        System.out.println("Pocet lodi typu " + typ + " je " + pocet);
        
        if (pocet > 0) {
            System.out.println("Postupne zadajte ich orientáciu:");
            
            for (int i = 0; i < pocet; i++) {
                String text = this.parser.nacitajOrientaciu();
                
                Orientacia orientacia = Orientacia.prelozOrientaciuLode(text);
                Lod lod = TypLode.vytvorNovuInstanciu(typ);
                lod.ulozLodPc(orientacia, this.dajHraciaPlocha());
            }
        }
    }  

    @Override
    public void naplnHraciePole() throws NemozneUlozenieLodeException {
        this.nastavLodeNaModro(TypLode.CLN);
        this.nastavLodeNaModro(TypLode.PONORKA);
        this.nastavLodeNaModro(TypLode.BOJOVA_LOD);
        this.nastavLodeNaModro(TypLode.LIETADLOVA_LOD);
    
    }

    @Override
    public void initPoctyLodi() {
          for (TypLode typLode : TypLode.values()) {
            this.nastavPoctyLodi(typLode, 2); 
        }
    }
}