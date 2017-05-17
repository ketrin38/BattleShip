/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lodicky;

import hraci.Suradnice;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

/**
 *
 * @author Katka
 */
public class Gui implements Serializable {
    
    private More prveMore;
    private More druheMore;
    private JLabel prvyMusiTrafit;
    private JLabel druhyMusiTrafit;
    private JLabel prvyHrac;
    
    public Gui(More prve, More druhe) 
    {
        this.prveMore = prve;
        this.druheMore = druhe;
        this.prvyMusiTrafit = new JLabel();
        this.druhyMusiTrafit = new JLabel();
        this.prvyHrac = new JLabel();
    }
    
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
    
    public void nastavPocty(int prvy, int druhy) 
    {
        this.prvyMusiTrafit.setText(Integer.toString(prvy));
        this.druhyMusiTrafit.setText(Integer.toString(druhy));
    } 
    
    public void nastavPrveho(String meno) 
    {
        this.prvyHrac.setText(meno);
    } 
    
    
}
