package ohtu.verkkokauppa;

/**
 * Created by nikkaire on 12.11.2017.
 */
public interface Varasto {
    Tuote haeTuote(int id);

    int saldo(int id);

    void otaVarastosta(Tuote t);

    void palautaVarastoon(Tuote t);
}
