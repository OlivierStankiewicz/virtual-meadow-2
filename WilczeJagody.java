package Projekt;

public class WilczeJagody extends Roslina{
    public WilczeJagody(int pozX, int pozY, Swiat swiat) {
        super(99, pozX, pozY, swiat, Constants.REPREZENTACJA_WILCZYCH_JAGOD);
    }

    @Override
    public Organizm stworzOrganizm(int pozX, int pozY) {
        return new WilczeJagody(pozX, pozY, swiat);
    }

    @Override
    public String typToString() {
        return "Wilcze Jagody";
    }

    @Override
    public int specjalnaUmiejetnosc(Organizm atakujacy)
    {
        swiat.komentator.oglosZabicie(atakujacy, this);
        swiat.komentator.oglosZatrucie(atakujacy, this);
        swiat.removeOrganizm(atakujacy);
        swiat.removeOrganizm(this);
        return 3;
    }
}
