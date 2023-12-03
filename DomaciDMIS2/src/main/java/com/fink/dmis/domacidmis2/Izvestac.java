/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.dmis.domacidmis2;

/**
 *
 * @author danil
 */
public class Izvestac extends Thread{
    private static int ID = -1;
    private int izvestacId = ++ID;
    private Skladiste skladiste;
    
    public Izvestac(Skladiste skladiste){
        this.skladiste = skladiste;
        this.setPriority(Thread.MAX_PRIORITY);
    }

    @Override
    public void run() {
        System.out.println("Izvestac "+izvestacId+" je krenuo sa izvestavanjem");
        try{
            while(!interrupted()){
                /*
                Iako je u zadatku zadato da izvestavac izvestava na svakih 10 sekundi,
                to dovodi do problema prilikom potvrde rada gladanjem izlaza u konzoli.
                Ako se stavi vrednost od 10 sec i ako se prosledi proizvodjacu i potrosacu
                minimalno i maksimalno vreme reda nekoliko stotina milisekunci i vise,
                prilikom svakog pokretanja koje je radjeno izvestavac nam govori da je skadiste prazno.
                ako se minimalno i maksimano vreme smanje na red velicina nekoliko desetina mislisekundi i manje,
                izvestavac mnogo redje u odnosu na proizvodjaca i potrosaca dobija svoje vreme tako da je iz izlaza 
                vrlo tesko uociti kada je izvestavac dobio svoje vreme.
                Iz tih razloga odlucio sam da vreme spavanja izvestaca smanjim na 0.5 sekundi, dok su minimalno i maksimalno vreme 
                proizvodjaca i potrosaca postavljeni na 30 i 50 milisekundi respektivno.
                To dovodi do toga da je izlaz mnogo pregledniji i da imamo kombinaciju praznog i popunjenog skladista.
                (Zakljucak donet nakon pokretanja programa vise od 50 puta u razlicitim konfiguracijama)
                */
                int trajanje = 500;
                Thread.sleep(trajanje);
                skladiste.IzvestajSkladista(izvestacId);
                
            }
        } catch (InterruptedException ex) {
            System.out.println("Izvestac "+izvestacId+ " je zavrsio sa radom");
        }
    }
    
    
    
    
}
