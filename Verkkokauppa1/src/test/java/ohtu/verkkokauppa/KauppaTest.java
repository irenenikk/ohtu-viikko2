package ohtu.verkkokauppa;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class KauppaTest {
    private Pankki pankki;
    private Viitegeneraattori viite;
    private Varasto varasto;
    private Kauppa k;

    @Before
    public void setUp() {
        pankki = mock(Pankki.class);

        viite = mock(Viitegeneraattori.class);
        when(viite.uusi()).thenReturn(42);

        varasto = mock(Varasto.class);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.saldo(2)).thenReturn(5);
        when(varasto.saldo(3)).thenReturn(0);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "leipä", 2));
        when(varasto.haeTuote(3)).thenReturn(new Tuote(3, "chia-siemen", 100));

        k = new Kauppa(varasto, pankki, viite);
    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto("pekka", 42, "12345", "3 3333-44455" , 5);
    }

    @Test
    public void ostostenPaatyttyaPankinMetodiaTilisiirtoKutstuaanOikeinKahdellaEriTuotteella() {
        k.aloitaAsiointi();
        k.lisaaKoriin(2);
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto("pekka", 42, "12345", "3 3333-44455" , 7);
    }

    @Test
    public void ostostenPaatyttyaPankinMetodiaTilisiirtoKutstuaanOikeinKahdellaSamallaTuotteella() {
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto("pekka", 42, "12345", "3 3333-44455" , 10);
    }

    @Test
    public void ostostenPaatyttyaPankinMetodiaTilisiirtoKutstuaanOikeinKahdellaTuotteellaJoistToistaEiTarpeeksiVarastossa() {
        k.aloitaAsiointi();
        k.lisaaKoriin(2);
        k.lisaaKoriin(3);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto("pekka", 42, "12345", "3 3333-44455" , 2);

    }

    @Test
    public void aloitaAsiointiNollaaOstostenTiedot() {
        k.aloitaAsiointi();
        k.lisaaKoriin(2);
        k.tilimaksu("pekka", "12345");
        verify(pankki).tilisiirto("pekka", 42, "12345", "3 3333-44455" , 2);

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);

        k.tilimaksu("pekka", "12345");
        verify(pankki).tilisiirto("pekka", 42, "12345", "3 3333-44455" , 7);
    }

    @Test
    public void uusiViiteNumeroJokaiselleMaksuTapahtumalle() {
        k.aloitaAsiointi();
        k.lisaaKoriin(2);
        k.tilimaksu("pekka", "12345");

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);

        k.tilimaksu("aino", "67890");
        verify(viite, times(2)).uusi();
    }

    @Test
    public void poistaKoristaPoistaaTuotteenOstosKoristaJaVieVarastoon() {
        k.aloitaAsiointi();
        k.lisaaKoriin(2);

        k.poistaKorista(2);

        verify(varasto).palautaVarastoon(any(Tuote.class));
    }
}
