package Projekt;

import java.util.Random;

public class Zolw extends Zwierze{
    Zolw(int pozX, int pozY, Swiat swiat)
    {
        super(2, 1, pozX, pozY, swiat, Constants.REPREZENTACJA_ZOLWIA);
    }

    @Override
    public String typToString() {
        return "Zolw";
    }

    @Override
    public Organizm stworzOrganizm(int pozX, int pozY)
    {
        return new Zolw(pozX, pozY, swiat);
    }

    @Override
    public void akcja()
    {
        boolean[] czyIstnieje = new boolean[1];
        czyIstnieje[0]= true;

        Random random = new Random();
        float randomFloat = random.nextFloat();

        if (randomFloat < Constants.RNG_RUCHU_ZOLWIA && wiek!=0)
        {
            int newX = pozX, newY = pozY;
            int ruch = wylosujRuch(false);
            switch (ruch)
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

            Organizm other = swiat.ktoNaPolu(newX, newY);
            if ((other!=null  && kolizja(other, czyIstnieje)!=0) || other==null)
            {
                pozX = newX;
                pozY = newY;
            }
        }

        if (czyIstnieje[0])
            starzejSie();
    }

    @Override
    public int specjalnaUmiejetnosc(Organizm atakujacy)
    {
        if (atakujacy.getSila() < 5)
        {
            swiat.komentator.oglosOdbicieAtaku(this, atakujacy);
            return 1;
        }

        return 0;
    }
}
