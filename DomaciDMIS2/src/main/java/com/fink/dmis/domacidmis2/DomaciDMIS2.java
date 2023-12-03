/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.fink.dmis.domacidmis2;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author danil
 */
public class DomaciDMIS2 {

    public static void main(String[] args) throws InterruptedException {
        Skladiste skladiste = new Skladiste(5);
        Proizvodjac[] threadsProizvodjac = new Proizvodjac[10];
        Potrosac[] threadsPotrosac = new Potrosac[10];
        Izvestac izvestac = new Izvestac(skladiste);
        
        izvestac.start();
        //razlog zbog kojeg su prosledjene zadate vrednosti za minimalno i maksimalno vreme
        //je objasnjen u Izvestac.java
        for (int i = 0; i < 10; i++) {
            threadsProizvodjac[i] = new Proizvodjac(skladiste,30,50);
            threadsProizvodjac[i].start();
        }
        for (int i = 0; i < 10; i++) {
            threadsPotrosac[i] = new Potrosac(skladiste,30,50);
            threadsPotrosac[i].start();
        }
       
        for (int i = 0; i < 10; i++) {
               threadsProizvodjac[i].join();          
        }
        for (int i = 0; i < 10; i++) {
                threadsPotrosac[i].join();
        }
        izvestac.join(); 
    }
}
