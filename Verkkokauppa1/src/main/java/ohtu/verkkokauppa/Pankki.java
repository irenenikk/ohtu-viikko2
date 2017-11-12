package ohtu.verkkokauppa;

/**
 * Created by nikkaire on 12.11.2017.
 */
public interface Pankki {

    boolean tilisiirto(String nimi, int viitenumero, String tililta, String tilille, int summa);
}
