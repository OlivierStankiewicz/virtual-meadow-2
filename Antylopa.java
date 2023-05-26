package Projekt;

public class Antylopa extends Zwierze {

    public Antylopa(int pozX, int pozY, Swiat swiat) {
        super(4, 4, pozX, pozY, swiat, (char) Constants.REPREZENTACJA_ANTYLOPY);
    }

    @Override
    public Organizm stworzOrganizm(int pozX, int pozY) {
        return new Antylopa(pozX, pozY, swiat);
    }

    @Override
    public String typToString() {
        return "Antylopa";
    }

    @Override
    public void akcja() {
        boolean[] czyIstnieje = new boolean[1];
        czyIstnieje[0]= true;
        int i = 0;
        while (czyIstnieje[0] && wiek!=0 && i < 2) {
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
            i++;
        }
        if (czyIstnieje[0]) {
            starzejSie();
        }
    }

    @Override
    public int specjalnaUmiejetnosc(Organizm atakujacy) {
        double random = Math.random();
        if (random < Constants.RNG_UCIECZKI_ANTYLOPY && !czyOtoczony()) {
            swiat.komentator.oglosUcieczke(this, atakujacy);
            int ruch = wylosujRuch(true);
            switch (ruch) {
                case 0:
                    pozX++;
                    break;
                case 1:
                    pozX--;
                    break;
                case 2:
                    pozY++;
                    break;
                case 3:
                    pozY--;
                    break;
            }
            return 2;
        }
        return 0;
    }
}