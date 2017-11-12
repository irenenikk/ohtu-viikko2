package ohtu.verkkokauppa;

public class PankkiImpl implements Pankki {

    private static PankkiImpl instanssi;

    private Kirjanpito kirjanpito;

    public PankkiImpl() {
        kirjanpito = Kirjanpito.getInstance();
    }

    public static Pankki getInstance() {
        if (PankkiImpl.instanssi == null) {
            PankkiImpl.instanssi = new PankkiImpl();
        }

        return PankkiImpl.instanssi;
    }

    @Override
    public boolean tilisiirto(String nimi, int viitenumero, String tililta, String tilille, int summa) {
        kirjanpito.lisaaTapahtuma("tilisiirto: tililtä " + tilille + " tilille " + tilille
                + " viite " + viitenumero + " summa " + summa + "e");

        // täällä olisi koodi joka ottaa yhteyden pankin verkkorajapintaan
        return true;
    }
}
