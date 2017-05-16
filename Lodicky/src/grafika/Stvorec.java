package grafika;
import java.awt.Rectangle;
import java.io.Serializable;

/**
 * Štvorec, s ktorým možno pohybovať a nakreslí sa na plátno.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0  (15 July 2000)
 */

public class Stvorec implements Serializable {
    private int velkostStrany;
    private int xLavyHorny;
    private int yLavyHorny;
    private String farba;
    private boolean jeViditelny;
    
    /**
     * Vytvor nový štvorec preddefinovanej farby na preddefinovanej pozícii.
     */
    public Stvorec() {
        this.velkostStrany = 30;
        this.xLavyHorny = 60;
        this.yLavyHorny = 50;
        this.farba = "red";
        this.jeViditelny = false;         
    }

    /**
     * (Štvorec) Zobraz sa.
     */
    public void zobraz() {
        this.jeViditelny = true;
        this.nakresli();
    }
    
    /**
     * (Štvorec) Skry sa.
     */
    public void skry() {
        this.zmaz();
        this.jeViditelny = false;
    }
    
    /**
     * (Štvorec) Posuň sa vpravo o pevnú dĺžku.
     */
    public void posunVpravo() {
        this.posunVodorovne(20);
    }

    /**
     * (Štvorec) Posuň sa vľavo o pevnú dĺžku.
     */
    public void posunVlavo() {
        this.posunVodorovne(-20);
    }

    /**
     * (Štvorec) Posuň sa hore o pevnú dĺžku.
     */
    public void posunHore() {
        this.posunZvisle(-20);
    }

    /**
     * (Štvorec) Posuň sa dole o pevnú dĺžku.
     */
    public void posunDole() {
        this.posunZvisle(20);
    }

    /**
     * (Štvorec) Posuň sa vodorovne o dĺžku danú parametrom.
     * @param vzdialenost velkost posunu
     */
    public void posunVodorovne(int vzdialenost) {
        this.zmaz();
        this.xLavyHorny += vzdialenost;
        this.nakresli();
    }

    /**
     * (Štvorec) Posuň sa zvisle o dĺžku danú parametrom.
     * @param vzdialenost velkost posunu
     */
    public void posunZvisle(int vzdialenost) {
        this.zmaz();
        this.yLavyHorny += vzdialenost;
        this.nakresli();
    }

    /**
     * (Štvorec) Posuň sa pomaly vodorovne o dĺžku danú parametrom.
     * @param vzdialenost velkost posunu
     */
    public void pomalyPosunVodorovne(int vzdialenost) {
        int delta;

        if (vzdialenost < 0) {
            delta = -1;
            vzdialenost = -vzdialenost;
        } else  {
            delta = 1;
        }

        for (int i = 0; i < vzdialenost; i++) {
            this.xLavyHorny += delta;
            this.nakresli();
        }
    }

    /**
     * (Štvorec) Posuň sa pomaly vodorovne o dĺžku danú parametrom.
     * @param vzdialenost
     */
    public void pomalyPosunZvisle(int vzdialenost) {
        int delta;

        if (vzdialenost < 0) {
            delta = -1;
            vzdialenost = -vzdialenost;
        } else {
            delta = 1;
        }

        for (int i = 0; i < vzdialenost; i++) {
            this.yLavyHorny += delta;
            this.nakresli();
        }
    }

    /**
     * (Štvorec) Zmeň dĺžku strany na hodnotu danú parametrom.
     * Dĺžka strany musí byť nezáporné celé číslo. 
     * @param dlzka nova dlzka
     */
    public void zmenStranu(int dlzka) {
        this.zmaz();
        this.velkostStrany = dlzka;
        this.nakresli();
    }

    /**
     * (Štvorec) Zmeň farbu na hodnotu danú parametrom.
     * Nazov farby musí byť po anglicky.
     * Možné farby sú tieto:
     * červená - "red"
     * žltá    - "yellow"
     * modrá   - "blue"
     * zelená  - "green"
     * fialová - "magenta"
     * čierna  - "black"
     * biela   - "white"
     * hnedá   - "brown"
     * @param novaFarba farba
     */
    public void zmenFarbu(String novaFarba) {
        this.farba = novaFarba;
        this.nakresli();
    }

    /*
     * Draw the square with current specifications on screen.
     */
    private void nakresli() {
        if (this.jeViditelny) {
            Platno canvas = Platno.dajPlatno();
            canvas.draw(this, this.farba,
                        new Rectangle(this.xLavyHorny, this.yLavyHorny, 
                                this.velkostStrany, this.velkostStrany));
            canvas.wait(3);
        }
    }

    /*
     * Erase the square on screen.
     */
    private void zmaz() {
        if (this.jeViditelny) {
            Platno canvas = Platno.dajPlatno();
            canvas.erase(this);
        }
    }
}

