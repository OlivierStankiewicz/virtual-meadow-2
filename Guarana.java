package Projekt;

public class Guarana extends Roslina{
    Guarana(int pozX, int pozY, Swiat swiat){
        super(0, pozX, pozY, swiat, Constants.REPREZENTACJA_GUARANY);
    }

    @Override
    public String typToString() {
        return "Guarana";
    }

    @Override
    public int specjalnaUmiejetnosc(Organizm atakujacy)
    {
        swiat.komentator.oglosZabicie(atakujacy, this);
        atakujacy.zwiekszSile(3);
        swiat.komentator.oglosZwiekszenieSily(atakujacy, 3);
        swiat.removeOrganizm(this);
        return 2;
    }

    @Override
    public Organizm stworzOrganizm(int pozX, int pozY)
    {
        return new Guarana(pozX, pozY, swiat);
    }
}
