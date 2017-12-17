package ohtu.kivipaperisakset;

import java.util.Scanner;

public class KPSTekoaly extends KPSPeli{

    private Tekoaly tekoaly = new Tekoaly();

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