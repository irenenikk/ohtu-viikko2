package ohtu.kivipaperisakset;

/**
 * Created by nikkaire on 17.12.2017.
 */
public class KPSTehdas {

    public static KPSPeli luoHelppoYksinPeli() {
        return new KPSTekoaly();
    }

    public static KPSPeli luoVaikeaYksinPeli() {
        return new KPSParempiTekoaly();
    }

    public static KPSPeli luoKaksinpeli() {
        return new KPSPelaajaVsPelaaja();
    }
}
