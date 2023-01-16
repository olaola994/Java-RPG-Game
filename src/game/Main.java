package game;

public class Main {
    public static void main(String[] args) {
        Character ja = new Character("Ola", 15, 15, 15);
        Character potwor = new Character("Zombie", 20, 20, 20);
        Character przyjaciel = new Character("Zosia", 10, 10, 10);
        Sword sword = new Sword();
        Wand wand = new Wand();
        FirstAid firstAid = new FirstAid();
        potwor.getStatistics();
        ja.attack(potwor, "magic");
        potwor.getStatistics();
    }
}