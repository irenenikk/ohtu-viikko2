package ohtu.Komennot;

import ohtu.Komennot.Komento;
import ohtu.Sovelluslogiikka;

import javax.swing.*;
import java.util.ArrayDeque;
import java.util.Stack;

/**
 * Created by nikkaire on 10.12.2017.
 */
public class Summa implements Komento {
    private Sovelluslogiikka sovellus;
    private JTextField tuloskentta;
    private JTextField syotekentta;
    private Stack<Integer> historia;


    public Summa(Sovelluslogiikka sovellus, JTextField syotekentta, JTextField tuloskentta) {
        this.sovellus = sovellus;
        this.syotekentta = syotekentta;
        this.tuloskentta = tuloskentta;
        this.historia = new Stack<>();
    }

    @Override
    public void suorita() {
        this.historia.add(sovellus.tulos());
        int arvo = 0;
        try {
            arvo = Integer.parseInt(syotekentta.getText());
        } catch (Exception e) {
        }
        this.sovellus.plus(arvo);
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
