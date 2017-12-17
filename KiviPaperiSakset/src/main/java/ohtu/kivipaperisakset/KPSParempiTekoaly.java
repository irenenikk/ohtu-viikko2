package ohtu.kivipaperisakset;

import java.util.Scanner;

import java.util.Scanner;

// Kivi-Paperi-Sakset, jossa voidaan valita pelataanko vastustajaa
// vastaan vai ei
public class KPSParempiTekoaly extends KPSPeli {

    private TekoalyParannettu tekoaly = new TekoalyParannettu(20);

    @Override
    public String pyydaToinenSiirto() {
        String siirto = tekoaly.annaSiirto();
        System.out.println("Tietokone valitsi: " + siirto);
        return siirto;
    }

    @Override
    public String pelaaToinenSiirto(String ekanSiirto) {
        String siirto = pyydaToinenSiirto();
        tekoaly.asetaSiirto(ekanSiirto);
        return siirto;
    }
}
