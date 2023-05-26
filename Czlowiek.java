package Projekt;
import javax.swing.*;
import java.awt.event.*;
public class Czlowiek extends Zwierze implements KeyListener{
    private int umiejetnoscLicznik;
    private boolean umiejetnoscWlaczona;
    private JFrame mainFrame;

    private int action = 0;

    public Czlowiek(int pozX, int pozY, Swiat swiat,JFrame mainFrame) {
        super(5, 4, pozX, pozY, swiat, Constants.REPREZENTACJA_CZLOWIEKA, Constants.LIGHT_AQUA);
        this.umiejetnoscLicznik = 5;
        this.umiejetnoscWlaczona = false;
        this.mainFrame = mainFrame;
        mainFrame.addKeyListener(this);
    }

    @Override
    public Organizm stworzOrganizm(int pozX, int pozY) {
        return null;
    }

    @Override
    public String typToString() {
        return "Czlowiek";
    }

    public void setUmiejLicz(int licznik) {
        umiejetnoscLicznik = licznik;
    }

    public void setUmiejWlacz(boolean wlaczona) {
        umiejetnoscWlaczona = wlaczona;
    }

    @Override
    public void akcja() {
        boolean[] czyIstnieje = new boolean[1];
        czyIstnieje[0]= true;
        action = 0;
        int input = 0;
        if (wiek != 0) {
            while(input==action)
            {
                try{Thread.sleep(50);}catch(InterruptedException e){}
            }
            input = action;
            int newX = pozX, newY = pozY;

            while (true) {
                if (input == 32 && !umiejetnoscWlaczona && umiejetnoscLicznik == 5) {
                    umiejetnoscWlaczona = true;
                    while(input==action)
                    {
                        try{Thread.sleep(50);}catch(InterruptedException e){}
                    }
                    input = action;
                } else if (input == 90) {
                    swiat.chceZapisac();
                    swiat.komentator.skomentujChecZapisu();
                    JOptionPane.showMessageDialog(mainFrame,"Wykonaj ruch, aby dokonczyc ture i wtedy gra zostanie zapisana", "Zapis", JOptionPane.INFORMATION_MESSAGE);
                    while(input==action)
                    {
                        try{Thread.sleep(50);}catch(InterruptedException e){}
                    }
                    input = action;
                } else if (input == 39) {
                    newX++;
                    break;
                } else if(input == 37) {
                    newX--;
                    break;
                } else if(input==40) {
                    newY++;
                    break;
                } else if(input == 38) {
                    newY--;
                    break;
                } else if (input == 81) {
                    swiat.zakonczSwiat();
                    break;
                } else {
                    while(input==action)
                    {
                        try{Thread.sleep(50);}catch(InterruptedException e){}
                    }
                    input = action;
                }
            }

            if (umiejetnoscWlaczona) {
                Organizm o1 = swiat.ktoNaPolu(pozX + 1, pozY);
                Organizm o2 = swiat.ktoNaPolu(pozX - 1, pozY);
                Organizm o3 = swiat.ktoNaPolu(pozX, pozY + 1);
                Organizm o4 = swiat.ktoNaPolu(pozX, pozY - 1);
                Organizm[] tab = {o1, o2, o3, o4};
                for (int i = 0; i < 4; i++) {
                    if (tab[i] != null) {
                        swiat.komentator.oglosSpalenie(this, tab[i]);
                        swiat.removeOrganizm(tab[i]);
                    }
                }
                umiejetnoscLicznik--;
                if (umiejetnoscLicznik == 0)
                    umiejetnoscWlaczona = false;
            } else if (umiejetnoscLicznik < 5)
                umiejetnoscLicznik++;

            if (input != 81 && newX >= 0 && newX < swiat.getSizeX() && newY >= 0 && newY < swiat.getSizeY()) {
                Organizm other = swiat.ktoNaPolu(newX, newY);
                if ((other != null && kolizja(other, czyIstnieje) != 0) || other == null) {
                    pozX = newX;
                    pozY = newY;
                }
            }
        }
        if (czyIstnieje[0]) {
            starzejSie();
            swiat.komentator.oglosUmiejetnoscCzlowieka(this);
        }

    }

    public int getUmiejLicz() {
        return umiejetnoscLicznik;
    }

    public boolean getUmiejWlacz() {
        return umiejetnoscWlaczona;
    }

    @Override
    public String zapisOrganizmu() {
        return reprezentacja + " " + pozX + " " + pozY + " " + sila + " " + wiek + " " + umiejetnoscWlaczona + " " + umiejetnoscLicznik;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        action = e.getKeyCode();
    }
}