package lodicky;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.Serializable;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Rozhranie hry lodičky.
 * 
 * @version 2017.05.18
 * @author Katarína Pilarčíková
 */
public class Gui implements Serializable {
    
    private More prveMore;
    private More druheMore;
    private JLabel prvyMusiTrafit;
    private JLabel druhyMusiTrafit;
    private JLabel prvyHrac;
    
    /**
     * Konštruktor.
     * @param prve more prvého hráča
     * @param druhe more druhého hráča
     */
    public Gui(More prve, More druhe) 
    {
        this.prveMore = prve;
        this.druheMore = druhe;
        this.prvyMusiTrafit = new JLabel();
        this.druhyMusiTrafit = new JLabel();
        this.prvyHrac = new JLabel();
    }
    
    /**
     * Vykreslí rozhranie. 
     */
    public void vykresli() {
        JFrame frame = new JFrame("Hra lodičky");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new FlowLayout());
        
        JPanel grid2 = new JPanel(new GridLayout(4,4));
        grid2.add( new JLabel("POTREBNÝ POČET ZÁSAHOV"));
        grid2.add( new JLabel(""));
        grid2.add( new JLabel("Používateľ :"));
        this.prvyMusiTrafit.setText("0");
        grid2.add(this.prvyMusiTrafit);
        grid2.add( new JLabel("Protiháč :"));
        this.druhyMusiTrafit.setText("0");
        grid2.add( this.druhyMusiTrafit);
        grid2.add( new JLabel("Hru začína:"));
        this.prvyHrac.setText("-------");
        grid2.add(this.prvyHrac); 
        frame.getContentPane().add(grid2);
        frame.getContentPane().add(this.prveMore.vykresli());
        frame.getContentPane().add(this.druheMore.vykresli());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    /**
     * Nastaví počty políčok, ktoré musia hráči trafiť.
     * @param prvy počet prvého
     * @param druhy počet druhého
     */
    public void nastavPocty(int prvy, int druhy){
        this.prvyMusiTrafit.setText(Integer.toString(prvy));
        this.druhyMusiTrafit.setText(Integer.toString(druhy));
    } 
    
    /**
     * Nastaví meno hráča, ktorý je prvý na rade.
     * @param meno meno prveho hraca
     */
    public void nastavPrveho(String meno){
        this.prvyHrac.setText(meno);
    } 
   
 }
