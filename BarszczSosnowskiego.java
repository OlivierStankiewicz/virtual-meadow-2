package Projekt;

public class BarszczSosnowskiego extends Roslina {
    public BarszczSosnowskiego(int pozX, int pozY, Swiat swiat) {
        super(10, pozX, pozY, swiat, Constants.REPREZENTACJA_BARSZCZU_SOSNOWSKIEGO);
    }

    @Override
    public Organizm stworzOrganizm(int pozX, int pozY) {
        return new BarszczSosnowskiego(pozX, pozY, swiat);
    }

    @Override
    public String typToString() {
        return "Barszcz Sosnowskiego";
    }

    @Override
    public int specjalnaUmiejetnosc(Organizm atakujacy) {
        swiat.komentator.oglosZabicie(atakujacy, this);
        swiat.komentator.oglosZatrucie(atakujacy, this);
        swiat.removeOrganizm(atakujacy);
        swiat.removeOrganizm(this);
        return 3;
    }

    @Override
    public void akcja() {
        if (wiek > 0) {
            float random = (float) Math.random();

            if (random < Constants.RNG_ROZSIANIA) {
                rozmnazanie(this);
            }

            Organizm o1 = swiat.ktoNaPolu(pozX + 1, pozY);
            Organizm o2 = swiat.ktoNaPolu(pozX - 1, pozY);
            Organizm o3 = swiat.ktoNaPolu(pozX, pozY + 1);
            Organizm o4 = swiat.ktoNaPolu(pozX, pozY - 1);
            Organizm[] tab = {o1, o2, o3, o4};

            for (int i = 0; i < 4; i++) {
                if (tab[i] != null && tab[i].getInicjatywa() > 0) {
                    swiat.komentator.oglosZabicie(this, tab[i]);
                    swiat.removeOrganizm(tab[i]);
                }
            }
        }

        starzejSie();
    }
}