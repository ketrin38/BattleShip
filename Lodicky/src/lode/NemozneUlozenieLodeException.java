/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lode;

/**
 * Výnimka.
 * 
 * @version 2015.06.22
 * @author Katarína Pilarčíková
 */
public class NemozneUlozenieLodeException extends Exception {
    public NemozneUlozenieLodeException() {
    }
    
    public NemozneUlozenieLodeException(String msg) {
        super(msg);
    }
}
