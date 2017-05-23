/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skuskapenkineosko;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Katka
 */
public class Aplikacia {
       private ArrayList<Skuska> skusky;
    
    
    public Aplikacia() {
        this.skusky = new ArrayList<Skuska>();
        Citac citac = new Citac();
        citac.nacitajSubor("");
        Scanner scanner = new Scanner(citac.nacitajSubor(""));
        
            while (scanner.hasNextLine()) {
                // zoberie liniu
                
               String line = scanner.nextLine();
         
               String[] arr = line.split("\\s+");
               // ROBENIE :D 
                ArrayList<String> pomocny = new ArrayList<>();
                for ( String ss : arr) 
                {
                     pomocny.add(ss.trim());
                }
                
                if(pomocny.size() > 4  && !pomocny.get(3).equals("") && pomocny.get(3) != null )
                {
                    String menoPriez = pomocny.get(0) +  " " + pomocny.get(1);
               
             
                   StudentSkuska pomStudent = new StudentSkuska(menoPriez, pomocny.get(2), pomocny.get(3));
                   pridajSkuskuDoZoznamu(pomocny.get(2).trim(), pomStudent);
                }
                   
               }
              
            scanner.close();  

    }      
    
    
    public boolean existujeSkuska(String nazov) 
    {
        return this.skusky.stream().anyMatch(m->m.getNazov().equals(nazov));  
    }
    
    public  void pridajSkuskuDoZoznamu(String nazov, StudentSkuska studentSkuska) 
    {
        if(this.existujeSkuska(nazov)) 
        {
            Skuska pom = this.skusky.stream().filter(m -> m.getNazov().equals(nazov)).findAny().get();
            pom.pridaj(studentSkuska);
        }
        else
        {
            Skuska nova = new Skuska(nazov);
            
            nova.pridaj(studentSkuska);
            this.skusky.add(nova);
        }
    
    } 

    public ArrayList<Skuska> getSkusky() {
        return skusky;
    }
    
    public void vypisSkuskuMeno(String nazov) 
    {
        Skuska pom = null;
        
        for (Skuska pomm : this.skusky) 
        {
            if(pomm.getNazov().equals(nazov)) 
            {
               pom = pomm; 
             
            }
        }

        
        if ( pom != null) 
        {
            for(StudentSkuska s : pom.getZoznam()) 
            {
                System.out.println( s.getMenoPriezvisko() + " " + s.getDatum() + " " + s.getZnamka());
            }
        
        } else {
        
            System.out.println("nenasla som ");
        }
        
    
    }
    
    public void vypisSkusky() 
    {
        System.out.println("Vypisujem skusky");
     for (Skuska pomm : this.skusky) 
        {
            System.out.println("        >>> SKUSKA" + pomm.getNazov());
        }
    }
    
    
}
