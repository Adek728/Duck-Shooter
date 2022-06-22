package Game;

import Game.Gracz;

import javax.swing.*;
public class OknoKoncowe extends JFrame{

    public OknoKoncowe(){

        JPanel panel = new JPanel();
        JLabel przegrana = new JLabel("PRZEGRALES :(,");
        JLabel label = new JLabel("Podaj nick:");
        JTextArea text = new JTextArea();
        JButton button = new JButton("OK");

        panel.add(przegrana);
        panel.add(label);
        panel.add(text);
        panel.add(button);
        add(panel);
        setBounds(1000,400,500,500);
        setSize(400,80);
        setVisible(true);

        button.addActionListener(e -> {
            String nick = text.getText();
            OknoStartowe.getTo().dodaj(nick+", a Jego wynik to: " + Gracz.getTo().getWynik());
            setVisible(false);
            OknoStartowe.getTo().setVisible(true);
        });
    }


}
