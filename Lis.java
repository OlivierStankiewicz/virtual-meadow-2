package Projekt;

public class Lis extends Zwierze{
    Lis(int pozX, int pozY, Swiat swiat)
    {
        super(3, 7, pozX, pozY, swiat, Constants.REPREZENTACJA_LISA);
    }

    @Override
    public String typToString() {
        return "Lis";
    }

    @Override
    public Organizm stworzOrganizm(int pozX, int pozY)
    {
        return new Lis(pozX, pozY, swiat);
    }

    @Override
    public void akcja()
    {
        boolean[] czyIstnieje = new boolean[1];
        czyIstnieje[0]= true;
        if (wiek!=0)
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
            if ( (other!=null && other.getSila()<sila && kolizja(other, czyIstnieje)!=0) || other==null)
            {
                pozX = newX;
                pozY = newY;
            }
        }

        if(czyIstnieje[0])
            starzejSie();
    }
}
