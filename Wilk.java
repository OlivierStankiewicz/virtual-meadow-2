package Projekt;

public class Wilk extends Zwierze{
    Wilk(int pozX, int pozY, Swiat swiat)
    {
        super(9, 5, pozX, pozY, swiat, Constants.REPREZENTACJA_WILKA);
    }

    @Override
    public String typToString() {
        return "Wilk";
    }

    @Override
    public Organizm stworzOrganizm(int pozX, int pozY)
    {
        return new Wilk(pozX, pozY, swiat);
    }
}
