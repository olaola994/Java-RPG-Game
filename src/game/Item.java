package game;
import java.util.*;

class Item {
    protected String name;
    protected String event;

    public Item(String name){
        this.name = name;
    }
    public void use(Character character){
        if(this.event.equals("HP")){
            System.out.println("You got +5 HP");
            character.setHP(character.getHP() + 5);
        }
        else if (this.event.equals("mana")){
            System.out.println("You got +5 mana");
            character.setMana(character.getMana() + 5);
        }
        else if (this.event.equals("stamina")){
            System.out.println("You got +5 stamina");
            character.setStamina(character.getStamina() + 5);
        }
    }

    public String getName() {
        return name;
    }
}

class Sword extends Item{
    public Sword() {
        super("sword");
        this.event = "stamina";
    }
}
class Wand extends Item{
    public Wand(){
        super("wand");
        this.event = "mana";
    }
}
class FirstAid extends Item{
    public FirstAid(){
        super("First Aid");
        this.event = "HP";
    }
}

