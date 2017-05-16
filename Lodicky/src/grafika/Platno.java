package grafika;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Image;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Shape;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * Canvas is a class to allow for simple graphical drawing on a canvas.
 * This is a modification of the general purpose Canvas, specially made for
 * the BlueJ "shapes" example. 
 *
 * @author: Bruce Quig
 * @author: Michael Kolling (mik)
 *
 * @version: 1.6.1 (shapes)
 */
public class Platno implements Serializable {
    // Note: The implementation of this class (specifically the handling of
    // shape identity and colors) is slightly more complex than necessary. This
    // is done on purpose to keep the interface and instance fields of the
    // shape objects in this project clean and simple for educational purposes.

    private static final Color BROWN = new Color(160, 96, 16);
    
    private static Platno platnoSingleton;
  
    /**
     * Factory method to get the canvas singleton object.
     * @return platno
     */
    public  static Platno dajPlatno() {
        if (platnoSingleton == null) {
            platnoSingleton = new Platno( "Lodičky" , 850, 400, Color.white);
        }
        platnoSingleton.setVisible(true);
        return platnoSingleton;
    }

    //  ----- instance part -----

    private JFrame frame;
    private CanvasPane canvas;
    private Graphics2D graphic;
    private Color pozadie;
    private Image canvasImage;
    private List<Object> objekty;
    private HashMap<Object, PopisTvaru> tvary;
    private Timer timer;
    
    /**
     * Create a Canvas.
     * @param title  title to appear in Canvas Frame
     * @param width  the desired width for the canvas
     * @param height  the desired height for the canvas
     * @param bgClour  the desired background colour of the canvas
     */
    private Platno( String paTitulok , int paSirka ,  int paVyska , Color paPozadie) {
        this.frame = new JFrame();
        this.canvas = new CanvasPane();
        this.frame.setContentPane(this.canvas);
        this.frame.setTitle(paTitulok);
        this.canvas.setPreferredSize(new Dimension(paSirka, paVyska));
        this.pozadie = paPozadie;
        this.frame.pack();
        
        this.objekty = new ArrayList<>();
        this.tvary = new HashMap<>();
        this.timer = new javax.swing.Timer(25, null);
        this.timer.start();
        
    }
     
    
    /**
     * Set the canvas visibility and brings canvas to the front of screen
     * when made visible. This method can also be used to bring an already
     * visible canvas to the front of other windows.
     * @param visible  boolean value representing the desired visibility of
     * the canvas (true or false) 
     */
    public void setVisible(boolean visible) {
        if (this.graphic == null) {
            // first time: instantiate the offscreen image and fill it with
            // the background colour
            Dimension size = this.canvas.getSize();
            this.canvasImage = this.canvas.createImage(size.width, size.height);
            this.graphic = (Graphics2D) this.canvasImage.getGraphics();
            this.graphic.setColor(this.pozadie);
            this.graphic.fillRect(0, 0, size.width, size.height);
            this.graphic.setColor(Color.black);
        }
        this.frame.setVisible(visible);
    }

    /**
     * Draw a given shape onto the canvas.
     * @param  objekt  an object to define identity for this shape
     * @param  farba           the color of the shape
     * @param  tvar            the shape object to be drawn on the canvas
     */
     // Note: this is a slightly backwards way of maintaining the shape
     // objects. It is carefully designed to keep the visible shape interfaces
     // in this project clean and simple for educational purposes.
    public void draw(Object objekt, String farba, Shape tvar) {
        this.objekty.remove(objekt);   // just in case it was already there
        this.objekty.add(objekt);      // add at the end
        this.tvary.put(objekt, new PopisTvaru(tvar, farba));
        this.redraw();
    }
 
    /**
     * Erase a given shape's from the screen.
     * @param objekt  the shape object to be erased 
     */
    public void erase(Object objekt) {
        this.objekty.remove(objekt);   // just in case it was already there
        this.tvary.remove(objekt);
        this.redraw();
    }

    /**
     * Set the foreground colour of the Canvas.
     * @param  farba   the new colour for the foreground of the Canvas 
     */
    public void setForegroundColor(String farba) {
        if (farba.equals("red")) {
            this.graphic.setColor(Color.red);
        } else if (farba.equals("black")) {
            this.graphic.setColor(Color.black);
        } else if (farba.equals("blue")) {
            this.graphic.setColor(Color.blue);
        } else if (farba.equals("yellow")) {
            this.graphic.setColor(Color.yellow);
        } else if (farba.equals("green")) {
            this.graphic.setColor(Color.green);
        } else if (farba.equals("magenta")) {
            this.graphic.setColor(Color.magenta);
        } else if (farba.equals("white")) {
            this.graphic.setColor(Color.white);
        } else if (farba.equals("white")) {
            this.graphic.setColor(Color.white);
        } else if (farba.equals("brown")) {
            this.graphic.setColor(BROWN);
        } else {
            this.graphic.setColor(Color.black);
        }
    }

    /**
     * Wait for a specified number of milliseconds before finishing.
     * This provides an easy way to specify a small delay which can be
     * used when producing animations.
     * @param  milisekundy  the number 
     */
    public void wait(int milisekundy) {
        try {
            Thread.sleep(milisekundy);
        } catch (Exception e) {
            System.out.println("Cakanie sa nepodarilo");
        }
    }

    /**
     * * Redraw all shapes currently on the Canvas.
     */
    private void redraw() {
        this.erase();
        for (Object tvar : this.objekty ) {
            this.tvary.get(tvar).draw(this.graphic);
        }
        
        this.vykreslenieSuradnic(30 , 10);
        this.vykreslenieSuradnic(430 , 10);
        this.pis(160, -10, "More hráča");
        this.pis(560, -10, "More počítača");
        this.canvas.repaint();
    }
       
    /**
     * Erase the whole canvas. (Does not repaint.)
     */
    private void erase() {
        Color original = this.graphic.getColor();
        this.graphic.setColor(this.pozadie);
        Dimension size = this.canvas.getSize();
        this.graphic.fill(new Rectangle(0, 0, size.width, size.height));
        this.graphic.setColor(original);
    }
    
    public void addKeyListener(KeyListener listener) {
        this.frame.addKeyListener(listener);
    }
    
    public void addMouseListener(MouseListener listener) {
        this.canvas.addMouseListener(listener);
    }
    
    public void addTimerListener(ActionListener listener) {
        this.timer.addActionListener(listener);
    }


    /************************************************************************
     * Inner class CanvasPane - the actual canvas component contained in the
     * Canvas frame. This is essentially a JPanel with added capability to
     * refresh the image drawn on it.
     */
    private class CanvasPane extends JPanel {
        public void paint(Graphics paGraphic) {
            paGraphic.drawImage(canvasImage, 0, 0, null);
        }
    }
    
    /************************************************************************
     * Inner class CanvasPane - the actual canvas component contained in the
     * Canvas frame. This is essentially a JPanel with added capability to
     * refresh the image drawn on it.
     */
    private class PopisTvaru {
        private Shape tvar;
        private String farba;

        public PopisTvaru(Shape zadanyTvar, String zadanaFarba) {
            this.tvar = zadanyTvar;
            this.farba = zadanaFarba;
        }

        public void draw(Graphics2D grafika) {
            Platno.this.setForegroundColor(this.farba);
            grafika.fill(this.tvar);
        }
    }
    
    
    /**
     * Vykresli jeden String
     * @param x
     * @param y
     * @param sprava text alebo pismeno na vypis
     */
    public void pis(int x, int y, String sprava) {
        Font f = new Font("SansSerif", Font.BOLD, 14);
        FontMetrics fm = this.graphic.getFontMetrics(f);
        String str = sprava;
        int width1 = fm.stringWidth(str);
        int cy = fm.getHeight() + fm.getAscent() + y;
        this.graphic.setColor(Color.black);
        this.graphic.setFont(f);
        this.graphic.drawString(str, x, cy);
    }
    
    /**
     * Vykresli suradnice na platno
     * @param x zaciatocna suradnica X na vykreslenie
     * @param y zaciatocna suradnica Y na vykreslenie
     */
    public void vykreslenieSuradnic(int x, int y) {
        for (int i = 1; i < 11; i++) {
            this.pis(x + 33 * i, y, this.toString(i));
            this.pis( x, y + 33 * i, this.toString(i));
        }
    } 
    
    /**
     * Pretypuje cislo z int do String
     * @param hodnota vstupne cislo, kt. chceme premenit na String
     * @return cislo v type stringu
     */
    public String toString (int hodnota) {
        String cislo = "";
        cislo += hodnota;
        return cislo;
    }
}


