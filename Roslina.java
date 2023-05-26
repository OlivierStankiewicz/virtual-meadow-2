package Projekt;

public abstract class Roslina extends Organizm {
    public Roslina(int sila, int pozX, int pozY, Swiat swiat, char reprezentacja) {
        super(sila, 0, pozX, pozY, swiat, reprezentacja, Constants.GREEN);
    }

    @Override
    public void akcja() {
        if (wiek > 0) {
            float random = (float) Math.random();
            if (random < Constants.RNG_ROZSIANIA) {
                rozmnazanie(this);
            }
        }
        starzejSie();
    }

    @Override
    public int kolizja(Organizm other, boolean[] istnieje) {
        return 0;
    }
}
