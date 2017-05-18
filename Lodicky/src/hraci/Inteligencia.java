/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hraci;

import java.util.Random;
import lode.Lod;
import lode.NemozneUlozenieLodeException;
import lode.Orientacia;
import lode.TypLode;
import lodicky.More;



/**
 * Abstraktná tieda inteligencií - počítačových úrovní. 
 * 
 * @version 2017.05.18
 * @author Katarína Pilarčíková
 */
public abstract class Inteligencia extends Hrac {
    private final Random rand;
    
    /**
     * Konštruktor. 
     * @param meno názov inteligencie
     * @param hraciaPlocha hracia plocha na ktorej sa nachádza
     */
    public Inteligencia(String meno, More hraciaPlocha) {
        super(meno, hraciaPlocha);
        this.rand = new Random();
    }
  
    /**
     * Inicializuje malé počty lodí.
     */
    @Override
    public void initPoctyLodi() {
        for (TypLode typLode : TypLode.values()) {
            this.nastavPoctyLodi(typLode, this.rand.nextInt(3)); 
        }
    }
    
    /**
     * Naplnenie hracieho poľa počítača lodičkami.
     */
    @Override
    public void naplnHraciePole() throws NemozneUlozenieLodeException {
        this.nastavLode(TypLode.LIETADLOVA_LOD);
        this.nastavLode(TypLode.BOJOVA_LOD);
        this.nastavLode(TypLode.PONORKA);
        this.nastavLode(TypLode.CLN);
    }
    
    /**
     * Naplní more loďami.
     */
    @Override
    public void naplnPole() {
    }
    
    /**
     * Vráti náhodné číslo.
     * @return číslo
     */
    protected int dajNahodneCislo() {
        return this.rand.nextInt(10);
    } 
    
    /**
     * Vráti náhodné číslo.
     * @param max maximálne číslo
     * @return číslo
     */
    protected int dajNahodneCislo(int max) {
        return this.rand.nextInt(max);
    } 
    
    /**
     * Nastavenie lodí a ich orientácie. 
     * @param typLode typ lodí, ktoré nastavujeme
     */
    private void nastavLode(TypLode typLode) throws NemozneUlozenieLodeException {
        for (int i = 0; i < this.dajPocetLodi(typLode); i++) {
            Orientacia orientacia = Orientacia.dajNahodnuOrientaciuLode();
            Lod lod = TypLode.vytvorNovuInstanciu(typLode);
            lod.ulozLodPc(orientacia, this.dajHraciaPlocha());
        }
    }
    
}
