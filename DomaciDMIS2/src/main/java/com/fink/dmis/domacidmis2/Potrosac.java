/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.dmis.domacidmis2;

/**
 *
 * @author danil
 */
public class Potrosac extends Thread{
    private static int statId = 0;
    private int id=++statId;

    private Skladiste skladiste;
    private int brojac = 0;
    private int minTime ;
    private int maxTime ;

    private int trajanje = minTime + (int)Math.random()*(maxTime-minTime);
    
    public Potrosac(Skladiste skladiste, int minTime, int maxTime) {
        this.skladiste = skladiste;
        this.minTime = minTime;
        this.maxTime = maxTime;
        this.setPriority(Thread.NORM_PRIORITY);
    }
    
    @Override
    public void run(){
        System.out.println("Potrosac "+id+" je krenuo sa radom");
        try{
            while(!interrupted()){
                int trajanje1 = minTime + (int)Math.random()*(maxTime-minTime);
                Thread.sleep(trajanje1);
                
                int proizvod = skladiste.Uzmi(id);
            }
        } catch (InterruptedException ex) {
            System.out.println("Potrosac "+id+ " je zavrsio sa radom");
        }
    }
}
