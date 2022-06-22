package Game;

import javax.swing.*;


public class Drzewo extends JLabel{

    public Drzewo(int x, int y){
        setIcon(PobierzZdj.pobierz("src/tree.png"));
        setBounds(x,y,150,150);
    }

}
