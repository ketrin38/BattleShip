/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package skuskapenka;

/**
 *
 * @author Mino
 */
public class SkuskaPenka {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       GUI guiko = new GUI();
        guiko.setVisible(true);
        Skusanie slovnik = new Skusanie();
        slovnik.setVisible(true);
    }
}
