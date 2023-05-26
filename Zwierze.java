package Projekt;
import java.awt.*;

public abstract class Zwierze extends Organizm {
    public Zwierze(int sila, int inicjatywa, int pozX, int pozY, Swiat swiat, char reprezentacja) {
        super(sila, inicjatywa, pozX, pozY, swiat, reprezentacja, Constants.YELLOW);
    }

    public Zwierze(int sila, int inicjatywa, int pozX, int pozY, Swiat swiat, char reprezentacja, Color kolor) {
        super(sila, inicjatywa, pozX, pozY, swiat, reprezentacja, kolor);
    }
    @Override
    public void akcja() {
        boolean[] czyIstnieje = new boolean[1];
        czyIstnieje[0]= true;
        if (wiek > 0) {
            int newX = pozX, newY = pozY;
            int ruch = wylosujRuch(false);
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

            Organizm other = swiat.ktoNaPolu(newX, newY);
            if ((other != null && kolizja(other, czyIstnieje)!=0) || other == null) {
                pozX = newX;
                pozY = newY;
            }
        }

        if (czyIstnieje[0]) {
            starzejSie();
        }
    }

    @Override
    public int kolizja(Organizm other, boolean[] istnieje) {
        if (other.getReprezentacja() == reprezentacja) {
            rozmnazanie(other);
            return 0;
        }

        int specjalna = specjalnaWalka(other);

        if (specjalna == 0 && walkaWygrana(other)) {
            return 1;
        }

        if (specjalna != 3 && specjalna != 0) {
            return specjalna - 1;
        }

        istnieje[0] = false;
        return 0;
    }

    protected int specjalnaWalka(Organizm atakowany) {
        int specjal = atakowany.specjalnaUmiejetnosc(this);
        if (specjal == 0) {
            return 0;
        }

        return specjal;
    }

    protected boolean walkaWygrana(Organizm atakowany) {
        if (atakowany.getSila() > sila) {
            swiat.komentator.oglosZabicie(atakowany, this);
            swiat.removeOrganizm(this);
            return false;
        }

        swiat.komentator.oglosZabicie(this, atakowany);
        swiat.removeOrganizm(atakowany);
        return true;
    }
}
