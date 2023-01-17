package game;

import java.util.ArrayList;
import java.util.Random;

public class Character{
    String name;
    protected int HP;
    protected int mana;
    protected int stamina;
    protected ArrayList<Item> items;
    protected boolean friendly;
    protected Character friend;

    public Character(String name, int HP, int mana, int stamina) {
        this.name = name;
        this.HP = HP;
        this.mana = mana;
        this.stamina = stamina;
        items = new ArrayList<Item>();
    }

    public String getName(){return name;}
    public int getHP(){return HP;}
    public int getMana() {return mana;}
    public int getStamina() {return stamina;}

    public boolean isFriendly() {return friendly;}
    public ArrayList<Item> getItems(){
        return items;
    }

    public void setHP(int HP) {this.HP = HP;}
    public void setMana(int mana) {this.mana = mana;}
    public void setStamina(int stamina) {this.stamina = stamina;}

    public void setFriendly(boolean friendly) {this.friendly = friendly;}
    public void setFriend(Character friend){
        this.friend = friend;
    }
    public boolean hasFriend(){
        return this.friend != null;
    }
    public void getStatistics(){
        System.out.println("Name: "+ name +" HP: " + HP + " Stamina: " + stamina + " Mana: " + mana);
    }

    public void attack(Character enemy, String kindOfAttack){
        if(kindOfAttack.equals("physical")){
            if(this.stamina >= 5){
                enemy.setHP(enemy.getHP() - 10);
                this.stamina -= 5;
            }
            else {
                System.out.println("You have not enought stamina for phisical attack.");
            }
        }
        else if (kindOfAttack.equals("magic")){
            if(this.stamina >= 5){
                enemy.setHP(enemy.getHP() - 10);
                this.stamina -= 5;
            }
            else {
                System.out.println("You have not enought mana for magic attack.");
            }
        }
    }
    public void useItem(Item item){
        if(items.contains(item)){
            item.use(this);
            items.remove(item);
        }
        else{
            System.out.println("Character doen't have this item.");
        }

    }
    public void addItem(Item item){
        items.add(item);
    }
    public Character callFriend(){
        if(hasFriend()){
            return this.friend;
        }
        else return null;
    }

}

class Monster extends Character{
    Random liczba = new Random();
    protected int EXPtoGive = liczba.nextInt(15);
    Monster(String name, int HP, int mana, int stamina){
        super(name, HP, mana, stamina);
        friendly = false;
    }

    @Override
    public void getStatistics() {
        super.getStatistics();
    }

    public int getEXPtoGive() {
        return EXPtoGive;
    }
}

class Monster1 extends Monster{
    Monster1(String name, int HP, int mana, int stamina){
        super(name, HP, mana, stamina);
        friendly = false;
    }
    public void attack(Monster enemy, String kindOfAttack){
        if(kindOfAttack.equals("physical")){
            if(this.stamina >= 1){
                enemy.setHP(enemy.getHP() - 1);
                this.stamina -= 1;
            }
            else {
                System.out.println("You have not enought stamina for phisical attack.");
            }
        }
        else if (kindOfAttack.equals("magic")){
            if(this.stamina >= 1){
                enemy.setHP(enemy.getHP() - 1);
                this.stamina -= 1;
            }
            else {
                System.out.println("You have not enought mana for magic attack.");
            }
        }
    }
}
class Monster2 extends Monster{
    Monster2(String name, int HP, int mana, int stamina){
        super(name, HP, mana, stamina);
        friendly = false;
    }
    public void attack(Monster enemy, String kindOfAttack){
        if(kindOfAttack.equals("physical")){
            if(this.stamina >= 2){
                enemy.setHP(enemy.getHP() - 2);
                this.stamina -= 2;
            }
            else {
                System.out.println("You have not enought stamina for phisical attack.");
            }
        }
        else if (kindOfAttack.equals("magic")){
            if(this.stamina >= 2){
                enemy.setHP(enemy.getHP() - 2);
                this.stamina -= 2;
            }
            else {
                System.out.println("You have not enought mana for magic attack.");
            }
        }
    }
}
class Monster3 extends Monster{
    Monster3(String name, int HP, int mana, int stamina){
        super(name, HP, mana, stamina);
        friendly = false;
    }
    public void attack(Monster enemy, String kindOfAttack){
        if(kindOfAttack.equals("physical")){
            if(this.stamina >= 3){
                enemy.setHP(enemy.getHP() - 3);
                this.stamina -= 3;
            }
            else {
                System.out.println("You have not enought stamina for phisical attack.");
            }
        }
        else if (kindOfAttack.equals("magic")){
            if(this.stamina >= 3){
                enemy.setHP(enemy.getHP() - 3);
                this.stamina -= 3;
            }
            else {
                System.out.println("You have not enought mana for magic attack.");
            }
        }
    }
}
