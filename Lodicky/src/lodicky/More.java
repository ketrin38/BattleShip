package lodicky;

import hraci.Suradnice;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import lode.Lod;
import lode.Orientacia;

/**
 * Objekt predstavuje hraciu plochu hráča. 
 * 
 * @version 2017.05.18
 * @author Katarína Pilarčíková
 */
public class More extends JFrame implements ActionListener {
    public static final int ROZMER = 10;
 
    private JButton[][] tlacidla;
    private Cast[][] vlastnosti_cast;
    private String nazov;
    public Suradnice surad;
    
    /**
     * Konštruktor mora.
     * @param nazov názov mora
     */
    public More(String nazov) {
        this.surad = new Suradnice(4, 4);
        this.tlacidla = new JButton[ROZMER][ROZMER];
        this.vlastnosti_cast = new Cast[ROZMER][ROZMER];
        this.nazov = nazov;      
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                    this.tlacidla[i][j] = new JButton(" ");   
                    this.vlastnosti_cast[i][j] = new Cast();     
            }   
        }
    } 
    
    /**
     * Vykresleslí grafiku mora.
     * @return JPanel panel mora
     */
    public JPanel vykresli() 
    {
        JPanel grid1 = new JPanel(new GridLayout(ROZMER,ROZMER));
        javax.swing.border.Border empty = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        javax.swing.border.Border blackLine = BorderFactory.createLineBorder(Color.black);
        CompoundBorder line = new CompoundBorder(empty, blackLine);
        TitledBorder grid1Border = BorderFactory.createTitledBorder(line, this.nazov);
        grid1.setBorder(grid1Border); 
          for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                   this.tlacidla[i][j].setBackground(this.nastavFarbu(i, j));
                   this.tlacidla[i][j].addMouseListener(new ButtonMouseListener(this, i, j));
                   grid1.add(this.tlacidla[i][j]);     
            }   
        }

        grid1.setBorder(grid1Border); 
        return grid1;
    } 
    
    /**
     * Nastaví farbu tlačidlu.
     * @param i súradnica tlačidla riadok
     * @param j súradnica tlačidla stĺpec
     * @return Color farba
     */
    public Color nastavFarbu(int i, int j) {
        String farba = this.vlastnosti_cast[i][j].dajFarbu();
       switch (farba)
    	{
            case "blue": return Color.blue;
            case "red": return Color.red;
            case "green":  return Color.green;
            case "yellow": return Color.yellow;
            case "brown": return Color.BLACK;
            case "magenta": return Color.MAGENTA;
            case "white": return Color.WHITE;
            default: return Color.orange;
    	}
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
        
        return this.vlastnosti_cast[riadok][stlpec].jeToLodicka();
    }
    
    /**
     * Skontroluje či na danej pozící už leží potopena loď.
     * @param suradnice súradnice 
     * @return či je potopena
     */
    public boolean jePotopenaNa(Suradnice suradnice) {
        int riadok = suradnice.dajRiadok();
        int stlpec = suradnice.dajStlpec();
        
        return this.vlastnosti_cast[riadok][stlpec].jePotopena();
    }
    
    /**
     * Ak je trafená loď na hracej plochce tak ju označí.
     * @param suradnice súradnice výstrelu
     */
    public void oznacTrafenuLodicku(Suradnice suradnice) {
        int riadok = suradnice.dajRiadok();
        int stlpec = suradnice.dajStlpec();
    
        this.vlastnosti_cast[riadok][stlpec].potopLodicku();
        this.tlacidla[riadok][stlpec].setBackground(this.nastavFarbu(riadok, stlpec));
    }
    
    /**
     * Označenie netrafenej lode.
     * @param suradnice  súradnice výstrelu 
     */
    public void oznacNetrafenuLodicku(Suradnice suradnice) {
        int riadok = suradnice.dajRiadok();
        int stlpec = suradnice.dajStlpec();
        
        this.vlastnosti_cast[riadok][stlpec].nezasiaholLodicku();
        this.tlacidla[riadok][stlpec].setBackground(this.nastavFarbu(riadok, stlpec));
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
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                    this.tlacidla[i][j] = new JButton(" ");                   
                    this.tlacidla[i][j].setBackground(this.nastavFarbu(i, j));   
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
            if (this.vlastnosti_cast[riadok][stlpec].jeToLodicka()) {
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
            this.vlastnosti_cast[riadok][stlpec].ocnacLodicku(farba);
            this.tlacidla[riadok][stlpec].setBackground(this.nastavFarbu(riadok, stlpec));
            riadok += riadokModif;
            stlpec += stlpecModif;
        }
     }   
    
    /**
     * Umiestni lodičku na hraciu plochu.
     */
    public Suradnice vystrelNaSupera() {
        return this.surad;
    }
    
    /**
     * Vystrelí na zadané súradnice.
     * @param x súradnica x
     * @param y súradnica y
     * @return Suradnice 
     */
    public Suradnice vystrel(int x, int y) {
        return this.surad = new Suradnice(x,y);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       
    }

    /**
     * Objekt, ktorý zabezpečuje správu udalostí na Mori.
     * @version 2017.05.18
     * @author Katarína Pilarčíková
     */   
    public class ButtonMouseListener extends MouseAdapter {
        private final More more;
        private final int x;
	private final int y;
        
        /**
         * Konštruktor.
         * @param more more, kde patrí tlačidlo
         * @param x súradnica tlačidla x
         * @param y súradnica tlačidla y
         */
	public ButtonMouseListener(More more, int x, int y){
	    this.more = more;
	    this.x = x;
	    this.y = y;
	}
        
        /**
         * Sledovanie udalosti stlačenie tlačidla.
         * Nastaví súradnicu ďalšieho výstrelu.
         * @param e udalosť
         */
	public void mousePressed(MouseEvent e)
	{
            more.tlacidla[x][y].setBackground(Color.LIGHT_GRAY);
            more.surad = new Suradnice(x,y); 
	}
         
    }
}


