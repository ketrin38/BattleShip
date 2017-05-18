package hraci;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import lode.Orientacia;
import lode.Lod;
import lode.NemozneUlozenieLodeException;
import lode.TypLode;
import lodicky.More;

/**
 * Riadi správanie sa užívatela, pri nastavovaní hry,lodičiek
 * aj samotnom strieľaní na protihráčovu hraciu plochu.
 * 
 * @version 2017.05.18
 * @author Katarína Pilarčíková
 */
public class Uzivatel extends Hrac {
    
    /**
     * Konštruktor užívateľa. Tu priradíme hraciu plochu užívateľovi.
     * @param plocha hracia plocha užívateľa
     */
    public Uzivatel(More plocha) {
        super("Užívateľ", plocha);
        
    }
    
    /**
     * Strieľanie lodí užívateľa na protihráča. 
     * @param cielovy protihráč
     */
    @Override
    public void vystrel(Hrac cielovy) {
        int pocitadlo = 0;
        Suradnice pom = null;
        Suradnice suradnice = cielovy.dajHraciaPlocha().vystrelNaSupera();
        if (pocitadlo == 0){
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(Uzivatel.class.getName()).log(Level.SEVERE, null, ex);
            }  
            
        } else {
            
            while(true) {
                if(!pom.equals(suradnice)) 
                {
                    break; 
                }
            }
            this.vystrelNaPoziciu(cielovy, suradnice);
        
        }
        pom = suradnice;      
        this.vystrelNaPoziciu(cielovy, suradnice);
    }
    
    /**
     * Užívateľ zadá počty lodí pre jednotlivé typy lodí.
     */
    @Override
    public void initPoctyLodi() {
        Random rand = new Random();
        for (TypLode typLode : TypLode.values()) {
             this.nastavPoctyLodi(typLode, rand.nextInt(3)); 
         }
    }
   
    /**
     * Naplí hracie pole užívateľa lodičkami.
     * @throws lode.NemozneUlozenieLodeException
     */
    @Override
    public void naplnHraciePole() throws NemozneUlozenieLodeException {
        this.nastavLode(TypLode.LIETADLOVA_LOD);
        this.nastavLode(TypLode.BOJOVA_LOD);
        this.nastavLode(TypLode.PONORKA);
        this.nastavLode(TypLode.CLN);
    }
    
    /**
     * Naplí hracie pole užívateľa lodičkami.
     * @throws lode.NemozneUlozenieLodeException
     */
    @Override
    public void naplnPole() throws NemozneUlozenieLodeException {
        this.nastavLodeNaModro(TypLode.LIETADLOVA_LOD);
        this.nastavLodeNaModro(TypLode.BOJOVA_LOD);
        this.nastavLodeNaModro(TypLode.PONORKA);
        this.nastavLodeNaModro(TypLode.CLN);
    }
    
    
    /**
     * Nastavenie orientácie lodí užíveteľom.
     * @param typLode typ lode
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
     * @param typLode typ lode
     */
    private void nastavLodeNaModro(TypLode typLode)
        throws NemozneUlozenieLodeException {
        for (int i = 0; i < this.dajPocetLodi(typLode); i++) {
            Orientacia orientacia = Orientacia.dajNahodnuOrientaciuLode();
            Lod lod = TypLode.vytvorNovuInstanciu(typLode);
            lod.ulozLodPc(orientacia, this.dajHraciaPlocha());
        }   
    }
    
}