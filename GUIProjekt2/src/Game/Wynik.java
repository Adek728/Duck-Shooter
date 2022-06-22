package Game;

import java.util.ArrayList;


import javax.swing.AbstractListModel;


class Wynik extends AbstractListModel{

    private ArrayList anArrayList;

    public Wynik(ArrayList arrayList) {
        anArrayList = arrayList;
    }


    public int getSize() {
        return anArrayList.size();
    }

    public Object getElementAt(int i) {
        return anArrayList.get(i);
    }


}