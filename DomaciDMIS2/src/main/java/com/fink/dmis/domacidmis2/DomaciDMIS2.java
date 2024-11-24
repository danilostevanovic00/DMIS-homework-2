/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.fink.dmis.domacidmis2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;



/**
 *
 * @author danil
 */
public class DomaciDMIS2 {
    
    private static Map<Integer, String> loadRaspored(String fileName) throws IOException {
        Map<Integer, String> raspored = new TreeMap<>();
        File file = new File(fileName);

        if (!file.exists()) {
            throw new FileNotFoundException("File " + fileName + " not found in the current directory.");
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length != 2) {
                    System.out.println("Invalid line format: " + line);
                    continue; // Skip invalid lines
                }
                int time = Integer.parseInt(parts[0]);
                String izvestacName = parts[1];
                raspored.put(time, izvestacName);
            }
        }
        return raspored;
    }


    public static void main(String[] args) throws InterruptedException, IOException {
        Skladiste skladiste = new Skladiste(10);
        Proizvodjac[] threadsProizvodjac = new Proizvodjac[20];
        Potrosac[] threadsPotrosac = new Potrosac[30];
        Izvestac izvestac1 = new Izvestac(skladiste);
        Izvestac izvestac2 = new Izvestac(skladiste);
        Izvestac izvestac3 = new Izvestac(skladiste);
        
        izvestac1.start();
        izvestac2.start();
        izvestac3.start();
        //razlog zbog kojeg su prosledjene zadate vrednosti za minimalno i maksimalno vreme
        //je objasnjen u Izvestac.java
        for (int i = 0; i < 20; i++) {
            threadsProizvodjac[i] = new Proizvodjac(skladiste,30,50);
            threadsProizvodjac[i].start();
        }
        for (int i = 0; i < 30; i++) {
            threadsPotrosac[i] = new Potrosac(skladiste,30,50);
            threadsPotrosac[i].start();
        }
        
        Map<Integer, String> raspored = loadRaspored("../../../../textFile/Raspored.txt");

        for (Map.Entry<Integer, String> entry : raspored.entrySet()) {
            String izvestacName = entry.getValue();

            // Simulate time intervals between events
            Thread.sleep(10000);

            // Notify the appropriate `Izvestac` thread
            switch (izvestacName) {
                case "Izvestac1" -> izvestac1.setRadi(true);
                case "Izvestac2" -> izvestac2.setRadi(true);
                case "Izvestac3" -> izvestac3.setRadi(true);
                default -> System.out.println("Nepoznat izvestac: " + izvestacName);
            }
        }
       
        for (int i = 0; i < 20; i++) {
               threadsProizvodjac[i].join();          
        }
        for (int i = 0; i < 30; i++) {
                threadsPotrosac[i].join();
        }
        izvestac1.join(); 
        izvestac2.join();
        izvestac3.join();
    }
}
