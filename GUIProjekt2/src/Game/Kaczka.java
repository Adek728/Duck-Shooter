package Game;

import Game.Chmura;
import Game.Gracz;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;


public class Kaczka extends JLabel implements MouseListener {

    int hp;
    int kaczkaX;
    int kaczkaY;
    boolean czyZyje = true;

    public Kaczka(int speed){

        setIcon(losowaKaczka());


        Thread poruszanieKaczki = new Thread(() -> {
           int x = 0;
           int y = (int)(Math.random()*700+100);
           kaczkaY = y;
           while(!Thread.interrupted() && Gracz.getTo().getHp() > 0 && czyZyje){
               setBounds(x,y,50,50);
               x++;
               kaczkaX = x;
               if(x > OknoGry.getTo().getWidth()){
                   Gracz.getTo().utrataHp();
                   OknoGry.getTo().remove(this);
                   break;
               }
               try{
                   Thread.sleep(speed);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
        });

        poruszanieKaczki.start();
        addMouseListener(this);


        Thread sprawdzanie = new Thread(() -> {
           while(!Thread.interrupted()){
               try{
                   Thread.sleep(1);
               } catch (Exception exception) {
                   exception.printStackTrace();
               }
               if(Gracz.getTo().getHp() <= 0){
                   OknoGry.getTo().remove(this);
               }
           }
        });
        sprawdzanie.start();

    }

    public ImageIcon losowaKaczka(){
        int x = (int)(Math.random()*4);
        switch (x) {
            case 0 -> {
                this.hp = 1;
                return PobierzZdj.pobierz("src/duck-blue.png");
            }
            case 1 -> {
                this.hp = 2;
                return PobierzZdj.pobierz("src/duck-red.png");
            }
            case 2 -> {
                this.hp = 3;
                return PobierzZdj.pobierz("src/duck-green.png");
            }
            default -> {
                this.hp = 5;
                return PobierzZdj.pobierz("src/duck-yellow.png");
            }
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        ArrayList<Integer> listaX = OknoGry.getTo().getListaX();
        ArrayList<Integer> listaY = OknoGry.getTo().getListaY();
        int ile = listaX.size() - 1;
        boolean czy = true;
        for(int i = 0; i < ile; i++){
            if(kaczkaX >= (listaX.get(i) - 50) && kaczkaX <= (listaX.get(i) + 50)
                    && kaczkaY >= (listaY.get(i) - 75) && kaczkaY <= (listaY.get(i) +75)){
             czy = false;
            }
        }
        ArrayList<Chmura> chmury = OknoGry.getTo().getChmury();
        for(int i = 0; i < chmury.size(); i++){
            if(kaczkaX == chmury.get(i).getX() && kaczkaY == chmury.get(i).getY()){
                czy = false;
            }
        }

        if(czy){
            hp = hp - Gracz.getTo().getSila();
            if(hp <= 0){
                czyZyje=false;
                OknoGry.getTo().remove(this);
                OknoGry.getTo().revalidate();
                OknoGry.getTo().repaint();
                Gracz.getTo().setMonety();
                Gracz.getTo().setWynik();
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


}
