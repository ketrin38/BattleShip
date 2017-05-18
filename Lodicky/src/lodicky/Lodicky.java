package lodicky;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Spúšťacia trieda hry.
 * @version 2017.05.18
 * @author Katarína Pilarčíková
 */
public class Lodicky {
    private static final String ULOZENA_HRA_CESTA = "save.bin";
    
    private static Lodicky instancia = null;
    private LogikaHry hra;
    private boolean novaHra;
    
    public static void main(String[] args) {
        Lodicky lodicky = Lodicky.dajInstanciu();
        lodicky.spusti();
 
    }
    
    /**
     * Vytvorí jedináčika.
     * @return jedináčika
     */
    public static Lodicky dajInstanciu() {
        if (instancia == null) {
            instancia = new Lodicky();
        }  
        return instancia;
    }
    
   
    
    /**
     * Spustenie hry.
     */
    public void spusti() {
        this.inicializujHru();
        this.hra.hra(this.novaHra);
    }
    
    /**
     * Ukladanie priebehu hry. 
     */
    public void ulozHru() {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        
        try {
            fos = new FileOutputStream(new File(ULOZENA_HRA_CESTA));
            oos = new ObjectOutputStream(fos);
            
            oos.writeObject(this.hra);
        } catch (FileNotFoundException ex) {
            this.vypisChybuUlozenia(ex);
        } catch (IOException ex) {
            this.vypisChybuUlozenia(ex);
        } finally {
       
            try {
                if (oos != null) {
                    oos.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException ex) {
            }
        }
    }
    
    /**
     * Konštruktor.
     */
    private Lodicky() {
        this.hra = null;
        this.novaHra = true;
    }

    /**
     * Zisti, či užívateľ chce pokračovať v hre alebo začať novú.
     */
    private void inicializujHru() {
        String[] typy = { "Začiatočník", "Pokročilý", 
            "Expert", "Hra pre dvoch", "Posledná hra"};
            JFrame frame = new JFrame("Hra lodicky");
        String zvolenaHra = (String) JOptionPane.showInputDialog(frame, 
        "Zvolte si typ hry",
        "Hra lodičky",
        JOptionPane.QUESTION_MESSAGE, null, typy, typy[0]);
        
        if (zvolenaHra.equals("Posledná hra")) {
            this.hra = this.nacitajUlozenuHru();
        }       
        if (this.hra == null) {
            if (zvolenaHra.equals("Posledná hra")) {
                this.hra = this.nacitajUlozenuHru();
            } else if(zvolenaHra.equals("Začiatočník")) {
                this.hra = TypNovejHry.vybratieTypu(1);
            }
            else if(zvolenaHra.equals("Pokročilý")) {
                this.hra = TypNovejHry.vybratieTypu(2);
            }
            else if(zvolenaHra.equals("Expert")) {
                this.hra = TypNovejHry.vybratieTypu(3);
            }
            else if(zvolenaHra.equals("Hra pre dvoch")) {
                this.hra = TypNovejHry.vybratieTypu(4);
            }
        } else {
            this.novaHra = false;
        }
    }

    /**
     * Načíta uloženú hru.
     * @return uloženú hru
     */
    private LogikaHry nacitajUlozenuHru() {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        LogikaHry nacitanaHra = null;
        try {
            fis = new FileInputStream(new File(ULOZENA_HRA_CESTA));
            ois = new ObjectInputStream(fis);
            nacitanaHra = (LogikaHry) ois.readObject();
        } catch (FileNotFoundException ex) {
            this.vypisChybuNacitania(ex);
        } catch (IOException ex) {
            this.vypisChybuNacitania(ex);
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException ex) {
            }
            
            return nacitanaHra;
        }
    }
    
    /**
     * Vypise chybu načítania. 
     * @param ex 
     */
    private void vypisChybuNacitania(Exception ex) {
        System.err.println("Nepodarilo sa nacitat hru: " + ex.getMessage());
    }
    
    /**
     * Vypíše chybu uloženia hry.
     * @param ex  
     */
    private void vypisChybuUlozenia(Exception ex) {
        System.err.println("Nepodarilo sa ulozit hru: " + ex.getMessage());
    }
    
}
    