/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.dmis.domacidmis2;

/**
 *
 * @author danil
 */
public class Skladiste {
    private static int statId = 0;
    private int id=++statId;
    private int [] niz;
    private int ulaz;
    private int izlaz;
    private int stanje = 0;
    private final int kapacitet;
    
    public int getStanje() {
        return stanje;
    }

    public int getId() {
        return id;
    }
    public Skladiste( int kapacitet ) {
        this.kapacitet = kapacitet;
        niz = new int[kapacitet];
    }
    
    public synchronized void IzvestajSkladista(int idIzvestaca) throws InterruptedException{
        if (stanje==0){
            System.out.println("Izvestac "+idIzvestaca+" kaze da je skladiste prazno");
        }else {
            StringBuilder string= new StringBuilder();
            string.append("izvestac "+idIzvestaca+" kaze da je sadrzaj skladista sledeci: ");
            for(int i:niz){
                string.append(i).append(" ");
            }
            notifyAll();
            System.out.println(string.toString());
        }
    }

    public synchronized void Stavi (int element,int idProizvodjaca) throws InterruptedException{
        while(stanje == kapacitet) wait();
        niz[ulaz++] = element;
        stanje++;
        if(ulaz == kapacitet)ulaz = 0;
        System.out.println("Proizvodjac "+idProizvodjaca+" je dodao proizvod "+ element);
        notifyAll();
    }

    public synchronized int Uzmi(int idPotrosaca) throws InterruptedException{
        while(stanje==0)wait();
        int element = niz[izlaz];
        niz[izlaz++]=0;
        stanje--;
        if(izlaz == kapacitet) izlaz=0;
        System.out.println("Potrosac "+idPotrosaca+" je uzeo proizvod "+ element);
        notifyAll();

        return element;
    }

}
