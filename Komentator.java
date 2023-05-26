package Projekt;

public class Komentator {
    private String[] komentarze;
    private String oUmiejetnosci;

    public Komentator() {
        komentarze = new String[Constants.LIMIT_KOMENTARZY];

        for (int i = 0; i < Constants.LIMIT_KOMENTARZY; i++) {
            komentarze[i] = "";
        }
    }

    public String getKomentarz(int ktory) {
        if (ktory < Constants.LIMIT_KOMENTARZY) {
            return komentarze[ktory];
        }
        return "";
    }

    public void addKomentarz(String komentarz) {
        for (int i = 0; i < Constants.LIMIT_KOMENTARZY; i++) {
            if (komentarze[i].equals("")) {
                komentarze[i] = komentarz;
                break;
            }
        }
    }

    public void setOUmiejetnosci(String komentarz) {
        oUmiejetnosci = komentarz;
    }

    public void oglosZabicie(Organizm kto, Organizm kogo) {
        String text;
        if (kogo.getInicjatywa()!=0) {
            text = kto.organizmToString();
            text += " zabil " + kogo.organizmToString();
            addKomentarz(text);
            return;
        }

        text = kto.organizmToString();
        text += " zjadl " + kogo.organizmToString();
        addKomentarz(text);
    }

    public void oglosZwiekszenieSily(Organizm kto, int oIle) {
        String text = "Zwiekszono sile " + kto.organizmToString();
        text += " z " + (kto.getSila() - oIle);
        text += " na " + kto.getSila();
        addKomentarz(text);
    }

    public void oglosZatrucie(Organizm kto, Organizm kim) {
        String text = kto.organizmToString() + " zatrul sie ";
        text += kim.organizmToString() + " i umarl";
        addKomentarz(text);
    }

    public void oglosRozmnozenie(Organizm a, Organizm b) {
        String text;
        if (a != b) {
            text = a.organizmToString() + " i ";
            text += b.organizmToString() + " sie rozmnozyly ";
            addKomentarz(text);
            return;
        }

        text = a.organizmToString() + " rozsial sie ";
        addKomentarz(text);
    }

    public void oglosOdbicieAtaku(Organizm kto, Organizm czyj) {
        String text = kto.organizmToString() + " odbil atak ";
        text += czyj.organizmToString();
        addKomentarz(text);
    }

    public void oglosUcieczke(Organizm kto, Organizm przedKim) {
        String text = kto.organizmToString() + " uciekl przed walka z ";
        text += przedKim.organizmToString();
        addKomentarz(text);
    }

    public void oglosUmiejetnoscCzlowieka(Czlowiek uzytkownik) {
        String text = "Umiejetnosc specjalna " + uzytkownik.organizmToString();
        if (uzytkownik.getUmiejWlacz()) {
            text += " aktywna, poziom naladowania wynosi: ";
        } else {
            text += " nieaktywna, poziom naladowania wynosi: ";
        }

        text += Integer.toString(uzytkownik.getUmiejLicz()) + "/5";
        oUmiejetnosci = text;
    }

    public void oglosSpalenie(Organizm kto, Organizm kogo) {
        String text = kto.organizmToString() + " spalil " + kogo.organizmToString() + " uzywajac specjalnej umiejetnosci";
        addKomentarz(text);
    }

    public void skomentujChecZapisu() {
        System.out.println("Wykonaj ruch, aby dokonczyc ture i wtedy gra zostanie zapisana");
    }

    public void skomentujStanUmiejetnosci() {
        System.out.println(oUmiejetnosci);
    }

    public void skomentujTure() {
        int i = 0;
        while (i < Constants.LIMIT_KOMENTARZY && !komentarze[i].equals("")) {
            System.out.println(komentarze[i]);
            komentarze[i] = "";
            i++;
        }
    }

    public String getOUmiejetnosci()
    {
       return oUmiejetnosci;
    }

}



