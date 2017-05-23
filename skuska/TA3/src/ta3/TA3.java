/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ta3;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author Katka
 */
public class TA3 {
    public static int POCET_PISMEN = 30;
    private static String zaznamy;
    private static boolean pokracuj;
    
    public static void main(String[] args) throws InterruptedException {
        TA3.pokracuj = false;
        TA3.zaznamy = "Lorem Ipsum"
               + "je fiktívny text"
                + "používaný pri návrhu tlačovín a typografie. Lorem Ipsum je štandardným výplňovým textom"
                + " už od 16. storočia, keď neznámy tlačiar zobral sadzobnicu plnú tlačových znakov "
                + "a pomiešal ich, aby tak vytvoril vzorkovú knihu. Prežil nielen päť storočí, ale aj "
                + "skok do elektronickej sadzby, a pritom zostal v podstate nezmenený. Spopularizovaný bol v"
                + " 60-tych rokoch 20.storočia, vydaním hárkov Letraset, ktoré obsahovali pasáže Lorem Ipsum,"
                + " a neskôr aj publikačným softvérom ako Aldus PageMaker, ktorý obsahoval verzie Lorem Ipsum."; 
        
        
    
        OknoTA3 okno = new OknoTA3();
        okno.setVisible(true);
        System.out.print(">>>> " + TA3.zaznamy.length());
   
        char[] pom =  TA3.zaznamy.toCharArray();
       
        for(int j = 0; j < TA3.zaznamy.length(); j++) 
        {  
            TimeUnit.SECONDS.sleep(1);
            String  naVyspis = "";
            for(int i = 0; i < POCET_PISMEN ; i++) 
            {
                naVyspis += pom[j + i];
            }
            okno.nastavText(naVyspis);
            System.out.print(naVyspis + "\n");
            if (j == (TA3.zaznamy.length() - POCET_PISMEN)) { j = 0;}
    
        }
    }   
}
