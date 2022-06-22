package Game;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class OknoStartowe extends JFrame{

    static OknoStartowe to;
    ArrayList<String> lista = new ArrayList<>();


    public OknoStartowe() {
        odczyt();
        to = this;
        setBounds(1000, 400, 500, 500);
        JButton start = new JButton("Nowa Gra");
        JButton tabela = new JButton("Tabela");
        JButton wyjscie = new JButton("Wyjscie");

        JPanel panel = new JPanel();

        panel.add(start);
        panel.add(tabela);
        panel.add(wyjscie);


        start.addActionListener(e -> {

            JLabel poziom = new JLabel("Wybierz Poziom Trudnosci");
            JButton latwy = new JButton("Latwy");
            JButton sredni = new JButton("Sredni");
            JButton trudny = new JButton("trudny");
            JPanel poziomPanel = new JPanel();

            poziomPanel.add(poziom);
            poziomPanel.add(latwy);
            poziomPanel.add(sredni);
            poziomPanel.add(trudny);


            latwy.addActionListener(x -> {
                setVisible(false);
                OknoGry oknoGry = new OknoGry(1);
                oknoGry.setSize(1200, 1000);
                remove(poziomPanel);
                add(panel);
                revalidate();
                repaint();
            });

            sredni.addActionListener(x -> {
                setVisible(false);
                OknoGry oknoGry = new OknoGry(2);
                oknoGry.setSize(1200, 1000);
                remove(poziomPanel);
                add(panel);
                revalidate();
                repaint();
            });

            trudny.addActionListener(x -> {
                setVisible(false);
                OknoGry oknoGry = new OknoGry(3);
                oknoGry.setSize(1200, 1000);
                remove(poziomPanel);
                add(panel);
                revalidate();
                repaint();
            });


            remove(panel);
            add(poziomPanel);
            revalidate();
            repaint();

        });

        tabela.addActionListener(e -> {
            sortowanie();
            setVisible(false);
            Tabela tabela1 = new Tabela();
            tabela1.setBounds(1000, 400, 500, 500);
        });

        wyjscie.addActionListener(e -> {
            this.dispose();
            zapis();
        });

        add(panel);
        setSize(500, 100);
        setVisible(true);

    }

    public static OknoStartowe getTo() {
        return to;
    }

    public ArrayList<String> getLista() {
        return lista;
    }

    public void dodaj(String text) {
        lista.add(text);
        sortowanie();
    }


    public void sortowanie() {
        if (lista.size() > 1) {
            for (int i = 0; i < lista.size() - 1; i++) {
                String[] tab1 = lista.get(i).split(" ");
                String[] tab2 = lista.get(i + 1).split(" ");
                System.out.println(tab1[5]);
                System.out.println(tab2[5]);
                if (Integer.parseInt(tab1[5]) < Integer.parseInt(tab2[5])) {
                    System.out.println("XD");
                    Collections.swap(lista, i, i + 1);
                    i = 0;
                } else if (Integer.parseInt(tab1[5]) < Integer.parseInt(tab2[5])) {
                }

            }
        }
    }
    public void odczyt(){
        BufferedReader bufferedReader = null;
        try {
            File file = new File("C:\\Users\\user\\Desktop\\GUIProjekt2\\out\\production\\GUIProjekt2\\dane.txt");
            bufferedReader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                lista.add(line);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void zapis(){
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileOutputStream("C:\\Users\\user\\Desktop\\GUIProjekt2\\out\\production\\GUIProjekt2\\dane.txt"));
            for(String text : lista){
                pw.write(text);
                pw.write("\n");
            }
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}
