package ohtu.kivipaperisakset;

import java.util.Scanner;

public class KPSPelaajaVsPelaaja extends KPSPeli {

    private static final Scanner scanner = new Scanner(System.in);

    @Override
    public String pyydaToinenSiirto() {
        System.out.print("Toisen pelaajan siirto: ");
        String tokanSiirto = scanner.nextLine();
        return tokanSiirto;
    }

    @Override
    public String pelaaToinenSiirto(String ensimmainenSiirto) {
        return pyydaToinenSiirto();
    }
}