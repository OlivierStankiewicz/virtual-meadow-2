package Projekt;

public class Owca extends Zwierze{
    Owca(int pozX, int pozY, Swiat swiat)
    {
        super(4, 4, pozX, pozY, swiat, Constants.REPREZENTACJA_OWCY);
    }

    @Override
    public String typToString() {
        return "Owca";
    }

    @Override
    public Organizm stworzOrganizm(int pozX, int pozY)
    {
        return new Owca(pozX, pozY, swiat);
    }
}
