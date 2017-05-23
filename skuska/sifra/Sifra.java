/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skuskacesarovasifra;

/**
 *
 * @author Katka
 */
public class Sifra 
{
    private String vstup;
    private String vystup;
    private int posun;
    
    public Sifra() 
    {
        this.vystup = "";
        this.vstup = "";
        this.posun = 0;
    }
    
    public void zasifruj() 
    {
        for (char ch: this.vstup.toCharArray())
        {
            int cislo = (int) ch;
            cislo = cislo + this.posun;
            char pomm = (char) cislo;
            this.vystup += pomm;
        }
    }
    
    public void odsifruj() 
    {
        for (char ch: this.vstup.toCharArray())
        {
            char pomm = ' ';
            if (!Character.isSpaceChar(ch) && !Character.isWhitespace(ch) ) 
            {
                int cislo = (int) ch;
                cislo = cislo - this.posun;
                pomm = (char) cislo;
            } 
            else 
            {
                pomm = ch;
                
            }
           
            
            this.vystup += pomm;
        }
    }
    
    public void setVstup(String paVstup) 
    {
        this.vstup = paVstup;
    }
    
    public String getVystup()
    {
        return this.vystup;
    }
    
     public void setPosun(int posun) 
    {
        this.posun = posun;
    }
    
    public int getPosun()
    {
        return this.posun;
    }
}
