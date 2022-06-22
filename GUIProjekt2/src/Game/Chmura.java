package Game;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class Chmura extends JLabel  implements MouseListener {

    int speed = 50;
    int x;
    int y;

    public Chmura(){

        setIcon(new ImageIcon("src/cloud-svgrepo-com.png"));


        Thread poruszanie = new Thread(() -> {
            x = OknoGry.getTo().getWidth();

            this.y = (int)(Math.random()* OknoGry.getTo().getHeight() - 80);

            while(!Thread.interrupted() && Gracz.getTo().getHp() > 0){
                setBounds(x,y,150,150);
                x--;
                if(x < 0){
                    x = 700;
                    y = (int)(Math.random()* OknoGry.getTo().getHeight() - 80);
                }
                try{
                    Thread.sleep(speed);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        poruszanie.start();

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

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

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
