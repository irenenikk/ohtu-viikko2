package ohtu.verkkokauppa;

import java.util.ArrayList;

/**
 * Created by nikkaire on 12.11.2017.
 */
public interface Kirjanpito {
    public void lisaaTapahtuma(String tapahtuma);
    public ArrayList<String> getTapahtumat();
}
