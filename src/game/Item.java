package game;
import java.util.*;

class Item {
    protected String name;
    protected String event;

    public Item(String name, String event){
        this.name = name;
        this.event = event;
    }
    public void use(Character character){
        if(this.event.equals("HP")){
            character.setHP(character.getHP() + 5);
        }
        else if (this.event.equals("mana")){
            character.setMana(character.getMana() + 5);
        }
        else if (this.event.equals("stamina")){
            character.setStamina(character.getStamina() + 5);
        }
    }
}

class Sword extends Item{
    public Sword() {
        super("sword", "stamina");
    }
}
class Wand extends Item{
    public Wand(){
        super("wand", "mana");
    }
}
class FirstAid extends Item{
    public FirstAid(){
        super("firstAid", "HP");
    }
}

