/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.dmis.domacidmis2;

import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author danil
 */
public class Proizvodjac extends Thread {
    private static int statId = 0;
    private int id=++statId;
    private Skladiste skladiste;
    private int brojac = 0;
    private int minTime ;
    private int maxTime ;
    private int trajanje = minTime + (int)Math.random()*(maxTime-minTime);
    
    public Proizvodjac(Skladiste skladiste, int minTime, int maxTime){
        this.skladiste = skladiste;
        this.minTime = minTime;
        this.maxTime = maxTime;
        this.setPriority(Thread.NORM_PRIORITY);
    }
    
    @Override
    public void run(){
        System.out.println("Proizvodjac "+id+" je krenuo sa proizvodnjom");
        try{
            while(!interrupted()){
                int trajanje1 = minTime + (int)Math.random()*(maxTime-minTime);
                Thread.sleep(trajanje1);
                
                int proizvod = id*1000 + brojac++;
                skladiste.Stavi(proizvod,id);
            }
        } catch (InterruptedException ex) {
            System.out.println("Prozivodjac "+id+ " je zavrsio sa radom");
        }
    }
}
