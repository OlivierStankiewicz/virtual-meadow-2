package Projekt;
import java.io.*;
import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Swiat implements ActionListener{

    public Komentator komentator;
    public JFrame frame = new JFrame();
    protected int sizeX;
    protected int sizeY;
    protected Organizm[] organizmy;
    protected boolean koniecSwiata;
    protected boolean czyZapis;
    private JPanel title_panel = new JPanel();
    private JPanel button_panel = new JPanel();
    private JLabel textfield = new JLabel();

    private JLabel menufield = new JLabel();

    private JButton[][] buttons;

    public Swiat()
    {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1300,1400);
        frame.getContentPane().setBackground(new Color(25, 25, 25));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        textfield.setBackground(new Color(25, 25, 25));
        textfield.setForeground(new Color(120, 150, 245));
        textfield.setFont(new Font("Calibri", Font.BOLD, 45));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("| Wirtualna Laka        193454 |");
        textfield.setOpaque(true);

        menufield.setBackground(new Color(25, 25, 25));
        menufield.setForeground(new Color(120, 150, 245));
        menufield.setFont(new Font("Calibri", Font.PLAIN, 30));
        menufield.setHorizontalAlignment(JLabel.CENTER);
        menufield.setText("Ruch - Strzalki    Specjalna umiejetnosc - Spacja    Zakonczenie gry - Q    Zapisz gre - Z");
        menufield.setOpaque(true);

        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0,0,1300,100);

        title_panel.add(textfield, BorderLayout.NORTH);
        title_panel.add(menufield, BorderLayout.SOUTH);
        frame.add(title_panel, BorderLayout.NORTH);

        String[] options = {"Nowa Gra", "Wczytaj Gre"};


        int wybor = JOptionPane.showOptionDialog(frame,"Wybierz co chcesz zrobic", "Wirtualna Laka 193454", 0, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

        if(wybor == 0)
        {
            int usersizeX = Integer.parseInt( JOptionPane.showInputDialog(frame, "Podaj szerokosc mapy:"));
            int usersizeY= Integer.parseInt( JOptionPane.showInputDialog(frame, "Podaj wysokosc mapy:"));
            float userFill;
            while (true) {
                userFill = Float.parseFloat(JOptionPane.showInputDialog(frame, "Podaj wspolczynnik zapelnienia mapy:"));
                if (userFill >= 0 && userFill < 1) {
                    break;
                }
            }
            zbudujSwiat(usersizeX, usersizeY, userFill);
        }

        else if(wybor == 1)
        {
            try {
                wczytajSwiat();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(frame, "Wystąpił błąd w trakcie próby wczytania stanu gry", "Błąd wczytywania", JOptionPane.ERROR_MESSAGE);
            }
        }


        else {
        zakonczSwiat();
        }

        button_panel.setLayout(new GridLayout(sizeY, sizeX));
        button_panel.setBackground(new Color(50, 50, 50));

        for (int i = 0; i < sizeX; i++)
        {
            for (int j = 0; j < sizeY;	j++)
            {
                buttons[i][j] = new JButton();
                button_panel.add(buttons[i][j]);
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 50));
                buttons[i][j].setFocusable(false);
                buttons[i][j].addActionListener(this);
            }
        }

            frame.add(button_panel);
            SwingUtilities.updateComponentTreeUI(frame);
            rysujSwiat();
        }

    public void zbudujSwiat(int sizeX, int sizeY, float zapelnienie)
    {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        komentator = new Komentator();
        koniecSwiata = false;
        czyZapis=false;

        buttons = new JButton[sizeX][sizeY];

        for (int i = 0; i < sizeX; i++)
        {
            for (int j = 0; j < sizeY;	j++)
            {
                buttons[i][j] = new JButton();
                buttons[i][j].setBackground(new Color(50, 50, 50));
                buttons[i][j].setText("");
            }
        }

        organizmy = new Organizm[sizeX*sizeY];
        for (int i = 0; i < sizeX*sizeY; i++)
            organizmy[i] = null;

        generujOrganizmy(zapelnienie);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                if(e.getSource()==buttons[i][j] && buttons[i][j].getText()=="") {
                    String name = JOptionPane.showInputDialog(frame, "Podaj nazwe zwierzecia ktore chcesz tutaj dodac:");
                    if(name == null)
                        return;

                    switch (name)
                    {
                        case "Wilk":
                            new Wilk(j, i, this);
                            break;
                        case "Owca":
                            new Owca(j, i, this);
                            break;
                        case "Lis":
                            new Lis(j, i, this);
                            break;
                        case "Zolw":
                            new Zolw(j, i, this);
                            break;
                        case "Antylopa":
                            new Antylopa(j, i, this);
                            break;
                        case "Trawa":
                            new Trawa(j, i, this);
                            break;
                        case "Mlecz":
                            new Mlecz(j, i, this);
                            break;
                        case "Guarana":
                            new Guarana(j, i, this);
                            break;
                        case "Wilcze Jagody":
                            new WilczeJagody(j, i, this);
                            break;
                        case "Barszcz Sosnowskiego":
                            new BarszczSosnowskiego(j, i, this);
                            break;
                    }

                    aktualizujMape();
                }
            }
        }
    }

    public int getSizeX()
    {
        return sizeX;
    }

    public int getSizeY()
    {
        return sizeY;
    }

    public boolean getKoniecSwiata()
    {
        return koniecSwiata;
    }

    public void zakonczSwiat()
    {
        koniecSwiata=true;
    }

    public void chceZapisac()
    {
        czyZapis=true;
    }

    private void aktualizujMape()
    {
        for (int i = 0; i < sizeX; i++)
        {
            for (int j = 0; j < sizeY; j++)
            {
                buttons[i][j].setBackground(new Color(80, 80, 80));
                buttons[i][j].setText("");
            }
        }

        for (int i = 0; i < sizeX * sizeY; i++)
        {
            if (organizmy[i] != null)
            {
                int x = organizmy[i].getX();
                int y = organizmy[i].getY();
                buttons[y][x].setBackground(organizmy[i].getKolor());
                buttons[y][x].setForeground(Color.white);
                buttons[y][x].setText(String.valueOf(organizmy[i].getReprezentacja()));
            }
        }

        SwingUtilities.updateComponentTreeUI(frame);
    }

    public void rysujSwiat()
    {
        rysujNaglowek();
        komentator.skomentujStanUmiejetnosci();
        komentator.skomentujTure();
        System.out.println();
        aktualizujMape();
    }

    public void addOrganizm(Organizm organizm)
    {
        for (int i = 0; i < sizeX * sizeY; i++)
        {
            if (organizmy[i] == null)
            {
                organizmy[i] = organizm;
                return;
            }
        }
    }

    public void removeOrganizm(Organizm organizm)
    {
        if(organizm.getReprezentacja() == Constants.REPREZENTACJA_CZLOWIEKA)
            zakonczSwiat();

        for (int i = 0; i < sizeX * sizeY; i++)
        {
            if (organizmy[i] == organizm)
            {
                organizmy[i] = null;
                return;
            }
        }
    }

    public Organizm ktoNaPolu(int x, int y)
    {
        for (int i = 0; i < sizeX * sizeY; i++)
        {
            if (organizmy[i] != null && organizmy[i].getX() == x && organizmy[i].getY() == y)
            {
                return organizmy[i];
            }
        }
        return null;
    }

    public void wykonajTure()
    {
        sortujOrganizmy();

        for (int i = 0; i < sizeX * sizeY; i++)
        {
            if (organizmy[i] != null)
            {
                organizmy[i].akcja();
            }
        }

        if (czyZapis)
        {
            try {
                zapiszStanGry();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(frame, "Wystąpił błąd w trakcie próby zapisu", "Błąd zapisu", JOptionPane.ERROR_MESSAGE);
            }
            czyZapis = false;
        }
    }

    private void sortujOrganizmy()
    {
        int gap = 1;
        while (gap <= sizeX*sizeY) {
            gap <<= 1;
        }
        gap -= gap>>1;

        while (gap >= 1)
        {
            for (int i = gap; i < sizeX * sizeY; i++)
            {
                Organizm x = organizmy[i];
                int j = i;
                while (j >= gap && czyAWazniejszyOdB(x, organizmy[j - gap]))
                {
                    organizmy[j] = organizmy[j - gap];
                    j -= gap;

                }
                organizmy[j] = x;

            }
            gap /= 2;
        }
    }

    private boolean czyAWazniejszyOdB(Organizm a, Organizm b)
    {
        if (a == null)
            return false;

        if (b == null)
            return true;

        int iniA = a.getInicjatywa();
        int iniB = b.getInicjatywa();

        if (iniA > iniB)
            return true;

        if (iniA == iniB && a.getWiek() > b.getWiek())
            return true;

        return false;
    }
    private void rysujNaglowek()
    {
       System.out.println("Wirtualna Laka - gra autorstwa Olivier Stankiewicz 193454");
       System.out.println("Kolejna tura --------------------------------------------");
    }

    private void generujOrganizmy(float zapelnienie)
    {
        int[] xy = new int[2];
        losujWolnePole(xy);
        new Czlowiek(xy[0], xy[1], this, frame);
        for (int i = 0; i < sizeX * sizeY-1; i++)
        {
            Random rand = new Random();
            float randomFloat = rand.nextFloat();

            if (randomFloat < zapelnienie)
            {
                int jaki = rand.nextInt(Constants.LICZBA_ORGANIZMOW);
                losujWolnePole(xy);

                switch (jaki)
                {
                    case 0:
                        new Wilk(xy[0], xy[1], this);
                        break;
                    case 1:
                        new Owca(xy[0], xy[1], this);
                        break;
                    case 2:
                        new Lis(xy[0], xy[1], this);
                        break;
                    case 3:
                        new Zolw(xy[0], xy[1], this);
                        break;
                    case 4:
                        new Antylopa(xy[0], xy[1], this);
                        break;
                    case 5:
                        new Trawa(xy[0], xy[1], this);
                        break;
                    case 6:
                        new Mlecz(xy[0], xy[1], this);
                        break;
                    case 7:
                        new Guarana(xy[0], xy[1], this);
                        break;
                    case 8:
                        new WilczeJagody(xy[0], xy[1], this);
                        break;
                    case 9:
                        new BarszczSosnowskiego(xy[0], xy[1], this);
                        break;

                }
            }
        }
    }

    private void losujWolnePole(int[] xy)
    {
        Random rand = new Random();
        while (true)
        {
            xy[0] = rand.nextInt(sizeX);
            xy[1] = rand.nextInt(sizeY);

            if (ktoNaPolu(xy[0], xy[1]) == null)
                return;
        }
    }

    private void zapiszStanGry() throws IOException {
        String fileName = JOptionPane.showInputDialog(frame, "Podaj nazwe pliku w ktorym chcesz zapisac:");
        FileWriter writer = new FileWriter(fileName);
        writer.write("Swiat\n");
        writer.write(sizeX + " " + sizeY + " " + koniecSwiata + "\n");
        writer.write("\n");
        writer.write("Komentarze\n");
        writer.write(komentator.getOUmiejetnosci() + "\n");
        for (int i = 0; i < Constants.LIMIT_KOMENTARZY; i++)
        {
            String komentarz = komentator.getKomentarz(i);
            if (komentarz != "")
                writer.write(komentarz + "\n");
        }
        writer.write("\n");
        writer.write("Organizmy\n");
        for (int i = 0; i < sizeX * sizeY; i++)
        {
            if (organizmy[i] != null)
                writer.write(organizmy[i].zapisOrganizmu() + "\n");
        }
        writer.write("0");
        writer.close();
        JOptionPane.showMessageDialog(frame,"Zapisano pomyslnie!", "Zapis", JOptionPane.INFORMATION_MESSAGE);
    }

    private void wczytajSwiat() throws IOException {
        String fileName = JOptionPane.showInputDialog(frame, "Podaj nazwe pliku z którego chcesz wczytać:");
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line = reader.readLine();
        line = reader.readLine();
        String[] dimensions = line.split(" ");
        sizeX = Integer.parseInt(dimensions[0]);
        sizeY = Integer.parseInt(dimensions[1]);
        koniecSwiata = Boolean.parseBoolean(dimensions[2]);
        if(koniecSwiata)
            zakonczSwiat();

        buttons = new JButton[sizeX][sizeY];

        for (int i = 0; i < sizeX; i++)
        {
            for (int j = 0; j < sizeY;	j++)
            {
                buttons[i][j] = new JButton();
                buttons[i][j].setBackground(new Color(50, 50, 50));
                buttons[i][j].setText("");

            }
        }

        organizmy = new Organizm[sizeX*sizeY];
        for (int i = 0; i < sizeX*sizeY; i++)
            organizmy[i] = null;

        line = reader.readLine();
        line = reader.readLine();

        line = reader.readLine();
        komentator = new Komentator();
        komentator.setOUmiejetnosci(line);

        while (true)
        {
            line = reader.readLine();
            if (line.equals(""))
                break;
            komentator.addKomentarz(line);
        }

        line = reader.readLine();

        while (true) {
            line = reader.readLine();
            String[] tokens = line.split(" ");

            char reprezentacja = tokens[0].charAt(0);
            if (reprezentacja == '0')
                break;
            int pozX = Integer.parseInt(tokens[1]);
            int pozY = Integer.parseInt(tokens[2]);
            int sila = Integer.parseInt(tokens[3]);
            int wiek = Integer.parseInt(tokens[4]);

            Organizm o;
            Czlowiek c;
            switch (reprezentacja) {
                case Constants.REPREZENTACJA_CZLOWIEKA:
                    c = new Czlowiek(pozX, pozY, this, frame);
                    o = c;
                    o.setSila(sila);
                    o.setWiek(wiek);
                    boolean wlaczona = Boolean.parseBoolean(tokens[5]);
                    if (wlaczona)
                        c.setUmiejWlacz(true);
                    int licznik = Integer.parseInt(tokens[6]);
                    c.setUmiejLicz(licznik);
                    break;

                case Constants.REPREZENTACJA_WILKA:
                    o = new Wilk(pozX, pozY, this);
                    o.setSila(sila);
                    o.setWiek(wiek);
                    break;

                case Constants.REPREZENTACJA_OWCY:
                    o = new Owca(pozX, pozY, this);
                    o.setSila(sila);
                    o.setWiek(wiek);
                    break;

                case Constants.REPREZENTACJA_LISA:
                    o = new Lis(pozX, pozY, this);
                    o.setSila(sila);
                    o.setWiek(wiek);
                    break;

                case Constants.REPREZENTACJA_ZOLWIA:
                    o = new Zolw(pozX, pozY, this);
                    o.setSila(sila);
                    o.setWiek(wiek);
                    break;

                case Constants.REPREZENTACJA_ANTYLOPY:
                    o = new Antylopa(pozX, pozY, this);
                    o.setSila(sila);
                    o.setWiek(wiek);
                    break;

                case Constants.REPREZENTACJA_TRAWY:
                    o = new Trawa(pozX, pozY, this);
                    o.setSila(sila);
                    o.setWiek(wiek);
                    break;

                case Constants.REPREZENTACJA_MLECZA:
                    o = new Mlecz(pozX, pozY, this);
                    o.setSila(sila);
                    o.setWiek(wiek);
                    break;

                case Constants.REPREZENTACJA_GUARANY:
                    o = new Guarana(pozX, pozY, this);
                    o.setSila(sila);
                    o.setWiek(wiek);
                    break;

                case Constants.REPREZENTACJA_WILCZYCH_JAGOD:
                    o = new WilczeJagody(pozX, pozY, this);
                    o.setSila(sila);
                    o.setWiek(wiek);
                    break;

                case Constants.REPREZENTACJA_BARSZCZU_SOSNOWSKIEGO:
                    o = new BarszczSosnowskiego(pozX, pozY, this);
                    o.setSila(sila);
                    o.setWiek(wiek);
                    break;
            }
        }
        JOptionPane.showMessageDialog(frame,"Wczytano pomyslnie!", "Odczyt", JOptionPane.INFORMATION_MESSAGE);
    }
}