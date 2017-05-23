/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package excel;

/**
 *
 * @author Mino
 */
public class Zakaznik {
    private String meno;
    private String priezvisko;
    private int vklad;

    public Zakaznik(String meno, String priezvisko, int vklad) {
        this.meno = meno;
        this.priezvisko = priezvisko;
        this.vklad = vklad;
    }

    public void setVklad(int vklad) {
        this.vklad = vklad;
    }

    public int getVklad() {
        return vklad;
    }

    @Override
    public String toString() {
        return "Zakaznik{" + "meno=" + meno + ", priezvisko=" + priezvisko + ", vklad=" + vklad + '}';
    }

    public String getMeno() {
        return meno;
    }

    public String getPriezvisko() {
        return priezvisko;
    }    
    
    public void zvacsiSumu(int suma){
        vklad+=suma;
    }
}
