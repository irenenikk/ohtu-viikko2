package ohtu.Komennot;

import ohtu.Sovelluslogiikka;

import javax.swing.*;
import java.util.Stack;

/**
 * Created by nikkaire on 10.12.2017.
 */
public class Nollaa implements Komento {

    private Sovelluslogiikka sovellus;
    private JTextField tuloskentta;
    private JTextField syotekentta;
    private Stack<Integer> historia;



    public Nollaa(Sovelluslogiikka sovellus, JTextField syotekentta, JTextField tuloskentta) {
        this.sovellus = sovellus;
        this.syotekentta = syotekentta;
        this.tuloskentta = tuloskentta;
        this.historia = new Stack<>();
    }

    @Override
    public void suorita() {
        this.historia.add(sovellus.tulos());
        this.sovellus.nollaa();
        syotekentta.setText("");
        tuloskentta.setText("" + sovellus.tulos());
    }

    @Override
    public void peru() {
        int edellinen = this.historia.pop();
        tuloskentta.setText("" + edellinen);
        this.sovellus.setTulos(edellinen);
    }
}
