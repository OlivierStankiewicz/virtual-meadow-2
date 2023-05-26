package Projekt;
import java.util.Random;

public class Mlecz extends Roslina{

    public Mlecz(int pozX, int pozY, Swiat swiat) {
        super(0, pozX, pozY, swiat, Constants.REPREZENTACJA_MLECZA);
    }

    @Override
    public Organizm stworzOrganizm(int pozX, int pozY) {
        return new Mlecz(pozX, pozY, swiat);
    }

    @Override
    public String typToString() {
        return "Mlecz";
    }

    @Override
    public void akcja()
    {
        if (wiek!=0)
        {
            for (int i = 0; i < 3; i++)
            {
                Random random = new Random();
                float randomFloat = random.nextFloat();

                if (randomFloat < Constants.RNG_ROZSIANIA)
                    rozmnazanie(this);
            }
        }

        starzejSie();
    }
}
