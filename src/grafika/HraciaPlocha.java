package grafika;

import java.io.Serializable;
import hraci.Suradnice;
import lode.Lod;
import lode.Orientacia;

/**
 * Trieda HraciaPlocha vytvorí hraciu plochu.
 * Stará sa o správne uloženie lodí.
 *  
 * @version 2015.06.11
 * @author Katarína Pilarčíková
 **/
public class HraciaPlocha implements Serializable {
    public static final int ROZMER = 10;
    
    private Stvorec oramovanie;
    private final Policko[][] hraciaPlocha;    
 
    /**
     * Konstruktor. Vytvori hracie pole.
     * @param polohaX poloha X policka
     * @param polohaY poloha Y policka
     */
    public HraciaPlocha(int polohaX, int polohaY) {
        this.oramovanie = null;
        this.hraciaPlocha = new Policko[ROZMER][ROZMER];
        this.nakresliOramovanie(polohaX, polohaY);        
        this.naplnMore(polohaX, polohaY); 
    }
    
    /**
     * Nakreslenie lodí na hraciu plochu.
     * @param lod loď, ktorú vykresľujeme
     * @param suradnice súradnice odkial sa loď nakreslí
     * @param orientacia orientácia lode
     * @param farba farba lode
     */
    public void nakresliLodicku(Lod lod, Suradnice suradnice,
            Orientacia orientacia, String farba) {
        if (orientacia == Orientacia.HORIZONTALNE) {
            this.nakresliUmiestnenuLod(lod, suradnice, 0, 1, farba);
        } else {
            this.nakresliUmiestnenuLod(lod, suradnice, 1, 0, farba);
        }
    }
    
    /**
     * Skontroluje či na danej pozící už leží loď.
     * @param suradnice súradnice kam sa má loď vykresliť
     * @return či je pozícia voľná
     */
    public boolean jeLodickaNa(Suradnice suradnice) {
        int riadok = suradnice.dajRiadok();
        int stlpec = suradnice.dajStlpec();
        
        return this.hraciaPlocha[riadok][stlpec].siLodicka();
    }
    
    /**
     * Skontroluje či na danej pozící už leží potopena loď.
     * @param suradnice súradnice 
     * @return či je potopena
     */
    public boolean jePotopenaNa(Suradnice suradnice) {
        int riadok = suradnice.dajRiadok();
        int stlpec = suradnice.dajStlpec();
        
        return this.hraciaPlocha[riadok][stlpec].siPotopena();
    }
    
    /**
     * Ak je trafená loď na hracej plochce tak ju označí.
     * @param suradnice súradnice výstrelu
     */
    public void oznacTrafenuLodicku(Suradnice suradnice) {
        int riadok = suradnice.dajRiadok();
        int stlpec = suradnice.dajStlpec();
    
        this.hraciaPlocha[riadok][stlpec].trafilLodicku();
    }
    
    /**
     * Označenie netrafenej lode.
     * @param suradnice  súradnice výstrelu 
     */
    public void oznacNetrafenuLodicku(Suradnice suradnice) {
        int riadok = suradnice.dajRiadok();
        int stlpec = suradnice.dajStlpec();
        
        this.hraciaPlocha[riadok][stlpec].netrafilLodicku();
    }
    
    /**
     * Skontroluje, či je možné uložiť loď na hraciu plochu s damou orientáciou. 
     * @param lod loď, ktorú ukladáme
     * @param orientacia orientácia lode
     * @param suradnice počiatočné súradnice
     * @return je  možné
     */
    public boolean jeMozneUlozitLod(Lod lod, Orientacia orientacia,
            Suradnice suradnice) {
        int pocetPolicok = lod.dajPocetPolicok();
        
        if (orientacia == Orientacia.HORIZONTALNE) {
            return this.skontrolujMiestoPreLod(suradnice, 0, 1, pocetPolicok);
        } else {
            return this.skontrolujMiestoPreLod(suradnice, 1, 0, pocetPolicok);
        }
    }
    
    /**
     * Prekresli hraciu plochu.
     */
    public void prekresli() {
        this.oramovanie.zobraz();
        
        for (Policko[] riadok : this.hraciaPlocha) {
            for (Policko policko : riadok) {
                policko.zobraz();
            }
        }
    }
    
    /**
     * Skontroluje, či je možné uložiť loď na hraciu plochu. 
     * Teda či má dostatok voľných políčok.
     * @param suradnice počiatočné súradnice lode
     * @param riadokModif modifikovaný riadok
     * @param stlpecModif modifikovaný stĺpec
     * @param pocetPolicok počet políčok lode
     * @return true ak je možná
     */
    private boolean skontrolujMiestoPreLod(Suradnice suradnice, int riadokModif,
        int stlpecModif, int pocetPolicok) {
        int riadok = suradnice.dajRiadok();
        int stlpec = suradnice.dajStlpec();
        
        for (int i = 0; i < pocetPolicok; i++) {
            if (this.hraciaPlocha[riadok][stlpec].siLodicka()) {
                return false;
            }
            
            riadok += riadokModif;
            stlpec += stlpecModif;
        }
        
        return true;
    }
    
    /**
     * Umiestni lodičku na hraciu plochu.
     * @param lod loď
     * @param suradnice počiatočné súradnice
     * @param riadokModif modifikovaný riadok
     * @param stlpecModif modifikovaný stĺpec
     */
    private void nakresliUmiestnenuLod(Lod lod, Suradnice suradnice,
            int riadokModif, int stlpecModif, String farba) {
        int pocetPolicok = lod.dajPocetPolicok();
        
        int riadok = suradnice.dajRiadok();
        int stlpec = suradnice.dajStlpec();
        
        for (int i = 0; i < pocetPolicok; i++) {
            this.hraciaPlocha[riadok][stlpec].nakresliLodicku(farba);
            
            riadok += riadokModif;
            stlpec += stlpecModif;
        }
    }
    
    /**
     * Vytvori cierny podklad pod hraciu plochu. 
     * @param polohaX poloha X oramovania
     * @param polohaY poloha Y oramovania
     * */
    private void nakresliOramovanie(int polohaX, int polohaY) {
        this.oramovanie = new Stvorec();
        this.oramovanie.zmenFarbu("black");
        this.oramovanie.posunVodorovne(polohaX - 60);
        this.oramovanie.posunZvisle(polohaY - 50);
        this.oramovanie.zmenStranu(2 + ROZMER * 33);
        this.oramovanie.zobraz();
    }
    
    /**
     * Vytvori dvojrozmerne pole policok.
     * @param polohaX   poloha X kde zacinaju policka  
     * @param polohaY   poloha Y kde zacinaju policka
     */
    private void naplnMore(int polohaX, int polohaY) {
        for (int riadok = 0; riadok < ROZMER; riadok++) {     
            for (int stlpec = 0; stlpec < ROZMER; stlpec++) {
                int x = polohaX + 2 + stlpec * 33;
                int y = polohaY + 2 + riadok * 33;
                
                this.hraciaPlocha[riadok][stlpec] = new Policko(x, y, "blue");
            } 
        }
    } 
}