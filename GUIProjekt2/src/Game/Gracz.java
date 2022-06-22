package Game;

public class Gracz {
    int hp;
    int wynik;
    private static Gracz to;
    int sila = 1;
    int monety = 0;


    public Gracz(){
        this.hp = 10;
        this.wynik = 0;
        to = this;
    }


    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getWynik() {
        return wynik;
    }

    public void setWynik() {
        this.wynik++;
    }

    public static Gracz getTo() {
        return to;
    }

    public int getSila() {
        return sila;
    }

    public void dodajSila() {
        sila++;
    }


    public int getMonety() {
        return monety;
    }

    public void setMonety(int monety){
        this.monety = monety;
    }

    public void setMonety() {
        monety += (int)(Math.random() * 30 + 1);
    }

    public void utrataHp(){
        this.setHp(getHp()-1);
        if(getHp() == 0){
            OknoGry.getTo().setVisible(false);
         new OknoKoncowe();
        }
    }


}
