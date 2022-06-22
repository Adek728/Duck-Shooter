package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class OknoGry extends JFrame implements KeyListener {

    static OknoGry to;
    int czas = 0;
    Gracz gracz;
    int speed;
    int kwota = 500;

    ArrayList<Integer> listaX = new ArrayList<>();
    ArrayList<Integer> listaY = new ArrayList<>();
    ArrayList<Chmura> chmury = new ArrayList<>();

    int trudnoscSekudnowa = 1000;
    int liczbaChmur;
    int liczbaDodanych = 0;

    public OknoGry(int trudnosc){
        to = this;
        gracz = new Gracz();

        switch (trudnosc) {
            case 1 -> {

                for(int i = 0; i < 3; i++){
                    int x = (int)(Math.random() * 700+100);
                    int y = (int)(Math.random() * 700+100);
                    addTree(x,y);
                    listaX.add(x);
                    listaY.add(y);
                }


                liczbaChmur = 5;


                speed = 50;
            }
            case 2 -> {
                for(int i = 0; i < 5; i++){
                    int x = (int)(Math.random() * 700+100);
                    int y = (int)(Math.random() * 700+100);
                    addTree(x,y);
                    listaX.add(x);
                    listaY.add(y);
                }

                liczbaChmur = 10;
                speed = 25;
            }
            default -> {
                for(int i = 0; i < 7; i++){
                    int x = (int)(Math.random() * 700+100);
                    int y = (int)(Math.random() * 700+100);
                    addTree(x,y);
                    listaX.add(x);
                    listaY.add(y);
                }


                liczbaChmur = 15;
                speed = 10;
            }
        }

        Thread utrudnienie = new Thread(() -> {
           speed = speed - 2;
            trudnoscSekudnowa = trudnoscSekudnowa - 15;
           try{
               Thread.sleep(5000);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
        });
        utrudnienie.start();

        setBounds(800,100,500,500);

        getContentPane().setBackground(new Color(0, 255, 232));

        JLabel czasLabel = new JLabel("Czas: ");
        JLabel monetyLabel = new JLabel("Monety: " + Gracz.getTo().getMonety());
        JLabel wynik = new JLabel("Wynik: " + Gracz.getTo().getWynik());
        JLabel zycia = new JLabel("Życia: " + Gracz.getTo().getHp());
        JButton kupUlepszenie = new JButton("Ulepszenie za " + kwota);

        kupUlepszenie.addActionListener(z -> {
            if(Gracz.getTo().getMonety() >= kwota){
                Gracz.getTo().dodajSila();
                Gracz.getTo().setMonety((Gracz.getTo().getMonety() - kwota));
                kwota += 1000;
                monetyLabel.setText("Monety: " + Gracz.getTo().getMonety());
                kupUlepszenie.setText("Ulepszenie za " + kwota);
            }
        });

        czasLabel.setBounds(190,850,200,200);
        czasLabel.setSize(50,100);
        monetyLabel.setBounds(50,800,200,200);
        monetyLabel.setSize(50,200);
        kupUlepszenie.setBounds(350,800,75,75);
        kupUlepszenie.setSize(75,75);

        JPanel panel = new JPanel();
        panel.add(czasLabel);
        panel.add(monetyLabel);
        panel.add(kupUlepszenie);
        panel.add(wynik);
        panel.add(zycia);
        add(panel);
        setSize(550,80);

        Thread kaczka = new Thread(()->{
            while (!Thread.interrupted() && Gracz.getTo().getHp()>0){
                add(new Kaczka(speed));
                try {
                    Thread.sleep(trudnoscSekudnowa);
                } catch (InterruptedException e) {
                    return;
                }
            }
        });
        kaczka.start();


        Thread uplywanieCzasu = new Thread(()->{
            while (!Thread.interrupted()){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    return;
                }
                czasLabel.setText("Czas: " + ++czas + "s");
            }
        });
        uplywanieCzasu.start();

        Thread informacje = new Thread(() -> {
            while(!Thread.interrupted()){
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                monetyLabel.setText("Monety: " + Gracz.getTo().getMonety());
                zycia.setText("Życia: " + Gracz.getTo().getHp());
                wynik.setText("Wynik: " + Gracz.getTo().getWynik());
            }
        });
        informacje.start();


        Thread dodawanieChmur = new Thread(() -> {
           while (!Thread.interrupted() && liczbaDodanych < liczbaChmur){
               liczbaDodanych++;
               Chmura chmura = new Chmura();
               chmury.add(chmura);
               add(chmura);
               try{
                   Thread.sleep(3000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
        });
        dodawanieChmur.start();

        addKeyListener(this);

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setFocusable(true);

    }


    public void addTree(int x, int y){
        add(new Drzewo(x,y));
    }


    public static OknoGry getTo() {
        return to;
    }


    public ArrayList<Integer> getListaX() {
        return listaX;
    }

    public ArrayList<Integer> getListaY() {
        return listaY;
    }



    public ArrayList<Chmura> getChmury() {
        return chmury;
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.isControlDown() && (e.getKeyCode() == KeyEvent.VK_Q) && e.isShiftDown()){
            Gracz.getTo().setHp(0);
            OknoGry.getTo().setVisible(false);
            OknoStartowe.getTo().setVisible(true);
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
