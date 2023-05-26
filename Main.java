package Projekt;

public class Main {
    public static void main(String[] args) {

        Swiat swiat = new Swiat();

        while (!swiat.getKoniecSwiata()) {
            swiat.wykonajTure();
            swiat.rysujSwiat();
        }

        System.out.println("KONIEC GRY");
    }
}