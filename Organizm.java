package Projekt;

import java.util.Random;
import java.awt.*;
public abstract class Organizm {
    protected int sila;
    protected int inicjatywa;
    protected int pozX;
    protected int pozY;
    protected int wiek;
    protected Swiat swiat;
    protected char reprezentacja;
    protected Color kolor;

    public Organizm(int sila, int inicjatywa, int pozX, int pozY, Swiat swiat, char reprezentacja, Color kolor) {
        this.sila = sila;
        this.inicjatywa = inicjatywa;
        this.pozX = pozX;
        this.pozY = pozY;
        this.wiek = 0;
        this.swiat = swiat;
        this.reprezentacja = reprezentacja;
        this.kolor = kolor;
        swiat.addOrganizm(this);
    }
    public int getX() {
        return pozX;
    }

    public int getY() {
        return pozY;
    }

    public char getReprezentacja() {
        return reprezentacja;
    }

    public int getInicjatywa() {
        return inicjatywa;
    }

    public int getWiek() {
        return wiek;
    }

    public int getSila() {
        return sila;
    }

    public Color getKolor() {
        return kolor;
    }

    public void setSila(int nowaSila) {
        sila = nowaSila;
    }

    public void setWiek(int nowyWiek) {
        wiek = nowyWiek;
    }

    public void zwiekszSile(int oIle) {
        sila += oIle;
    }

    public String organizmToString() {
        return typToString() + "[" + pozX + "," + pozY + "] s" + sila;
    }

    public String zapisOrganizmu() {
        return reprezentacja + " " + pozX + " " + pozY + " " + sila + " " + wiek;
    }

    public int specjalnaUmiejetnosc(Organizm atakujacy){
        return 0;
    }

    public abstract void akcja();

    public abstract int kolizja(Organizm other, boolean[] istnieje);

    public abstract String typToString();

    public abstract Organizm stworzOrganizm(int pozX, int pozY);

    protected void starzejSie() {
        wiek++;
    }

    protected void rozmnazanie(Organizm other) {
        if (!czyOtoczony()) {
            int newX = pozX;
            int newY = pozY;
            int ruch = wylosujRuch(true);
            switch (ruch) {
                case 0:
                    newX++;
                    break;
                case 1:
                    newX--;
                    break;
                case 2:
                    newY++;
                    break;
                case 3:
                    newY--;
                    break;
            }

            Organizm tmp = stworzOrganizm(newX, newY);
            swiat.komentator.oglosRozmnozenie(this, other);
        }
    }

    protected int wylosujRuch(boolean musiBycWolne)
    {
        int width = swiat.getSizeX();
        int height = swiat.getSizeY();
        Random rand = new Random();
        while (true)
        {
            int random = rand.nextInt(4);
            int newX = pozX;
            int newY = pozY;

            switch (random)
            {
                case 0:
                    newX++;
                    break;
                case 1:
                    newX--;
                    break;
                case 2:
                    newY++;
                    break;
                case 3:
                    newY--;
                    break;
            }

            if (newX >= 0 && newX < width && newY >= 0 && newY < height && (!musiBycWolne || swiat.ktoNaPolu(newX, newY)==null))
            {
                return random;
            }
        }
    }

    protected boolean czyOtoczony()
    {
        int width = swiat.getSizeX();
        int height = swiat.getSizeY();

        if (pozX - 1 >= 0 && swiat.ktoNaPolu(pozX - 1, pozY)==null)
            return false;

        if (pozX + 1 < width && swiat.ktoNaPolu(pozX + 1, pozY)==null)
            return false;

        if (pozY - 1 >= 0 && swiat.ktoNaPolu(pozX, pozY - 1)==null)
            return false;

        if (pozY + 1 < width && swiat.ktoNaPolu(pozX, pozY + 1)==null)
            return false;

        return true;
    }
}