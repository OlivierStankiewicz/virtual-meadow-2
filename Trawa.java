package Projekt;

public class Trawa extends Roslina{
    public Trawa(int pozX, int pozY, Swiat swiat) {
        super(0, pozX, pozY, swiat, Constants.REPREZENTACJA_TRAWY);
    }

    @Override
    public Organizm stworzOrganizm(int pozX, int pozY) {
        return new Trawa(pozX, pozY, swiat);
    }

    @Override
    public String typToString() {
        return "Trawa";
    }
}
