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
    protected ArrayList<Friend> friends;
    protected int EXP;

    public Character(String name, int HP, int mana, int stamina) {
        this.name = name;
        this.HP = HP;
        this.mana = mana;
        this.stamina = stamina;
        items = new ArrayList<Item>();
        friends = new ArrayList<Friend>();
        this.EXP = 0;
    }

    public String getName(){return name;}
    public int getHP(){return HP;}
    public int getMana() {return mana;}
    public int getStamina() {return stamina;}

    public int getEXP() {
        return EXP;
    }

    public boolean isFriendly() {return friendly;}
    public ArrayList<Item> getItems(){
        return items;
    }
    public Item getItem(){
        return items.get(0);
    }

    public void setHP(int HP) {this.HP = HP;}
    public void setMana(int mana) {this.mana = mana;}
    public void setStamina(int stamina) {this.stamina = stamina;}
    public void setEXP(int EXP){this.EXP = EXP;}

    public void setFriendly(boolean friendly) {this.friendly = friendly;}
    public void addFriend(Friend friend){
        this.friends.add(friend);
    }
    public boolean hasFriend(){
        return this.friends != null;
    }
    public Character getFriend(Character friend){
        return friend;
    }
    public void getStatistics(){
        System.out.println("-----------------------------------------------------------");
        System.out.println("| "+ name + " |EXP: " + EXP +" | HP: " + HP + "| Stamina: " + stamina + "| Mana: " + mana +"|");
        System.out.println("-----------------------------------------------------------");
    }

    public void attack(Character enemy, String kindOfAttack){
        if(this.stamina >= 2 || this.mana >=2) {
            if (kindOfAttack.equals("physical")) {
                    enemy.setHP(enemy.getHP() - 4);
                    this.stamina -= 2;
            } else if (kindOfAttack.equals("magic")) {
                    enemy.setHP(enemy.getHP() - 4);
                    this.mana -= 2;
            }
        }

    }
    public void useItem(Item item){
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getName().equals(item.getName())) {
                item.use(this);
                items.remove(i);
                return;
            }
        }
        System.out.println("Character doesn't have this item.");

    }
    public void addItem(Item item){
        items.add(item);
    }
    public Friend callFriend(){
        if(hasFriend()){
            return this.friends.get(0);
        }
        else return null;
    }

}

class Monster extends Character{
    Random liczba = new Random();
    protected int EXPtoGive = liczba.nextInt(6);
    Monster(String name, int HP, int mana, int stamina){
        super(name, HP, mana, stamina);
        this.friendly = false;
    }
    public void setName(String name){
        this.name = name;
    }

    public void attack(Character enemy, String kindOfAttack, int level){

            if (kindOfAttack.equals("physical")) {
                    enemy.setHP(enemy.getHP() - level);
                    this.stamina -= level;
                    System.out.println("Monster made physical attack.");
                try{
                    Thread.sleep(3000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            else if (kindOfAttack.equals("magic")) {
                    enemy.setHP(enemy.getHP() - level);
                    this.mana -= level;
                    System.out.println("Monster made magical attack.");
                try{
                    Thread.sleep(3000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }

    }

    public void getStatistics(){
        System.out.println("----------------------------------------------------------");
        System.out.println("|"+ name + " |EXP to give: "+ EXPtoGive + " | HP: " + HP + "| Stamina: " + stamina + "| Mana: " + mana +"|");
        System.out.println("----------------------------------------------------------");
    }
    public int getEXPtoGive() {
        return EXPtoGive;
    }
}

class Friend extends Character{
    public Friend(){
        super("friend", 2,2,2);
    }
    public void setName(String name){
        this.name = name;
    }
    public void attack(Character enemy){
            enemy.setHP(enemy.getHP() - 2);
            this.stamina -= 2;
            System.out.println("Your friend made physical attack.");

    }
}
