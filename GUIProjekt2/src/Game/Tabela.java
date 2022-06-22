package Game;
import Game.OknoStartowe;
import Game.Wynik;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class Tabela extends JFrame implements Serializable {
    BorderLayout borderLayout1 = new BorderLayout();
    JList list1 = new JList();
    public Tabela(){
        this.getContentPane().setLayout(borderLayout1);
        this.setSize(new Dimension(400, 300));

        Wynik ld = new Wynik(OknoStartowe.getTo().getLista());
        list1.setModel(ld);
        list1.repaint();

        JButton fillButton = new JButton();
        fillButton.setText("Wyjscie");

        fillButton.addActionListener(e -> {
            setVisible(false);
            OknoStartowe.getTo().setVisible(true);
        });

        this.getContentPane().add(new JScrollPane(list1), BorderLayout.CENTER);
        this.getContentPane().add(fillButton, BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);
    }


}
