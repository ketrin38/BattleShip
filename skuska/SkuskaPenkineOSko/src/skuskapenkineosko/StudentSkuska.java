/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skuskapenkineosko;

/**
 *
 * @author Katka
 */
public class StudentSkuska {
    private String menoPriezvisko;
    private String datum;
    private String znamka;

    public StudentSkuska(String menoPriezvisko, String datum, String znamka) {
        this.menoPriezvisko = menoPriezvisko;
        this.datum = datum;
        this.znamka = znamka;
    }

    public String getMenoPriezvisko() {
        return menoPriezvisko;
    }

    public void setMenoPriezvisko(String menoPriezvisko) {
        this.menoPriezvisko = menoPriezvisko;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getZnamka() {
        return znamka;
    }

    public void setZnamka(String znamka) {
        this.znamka = znamka;
    }
    
    
    
}
