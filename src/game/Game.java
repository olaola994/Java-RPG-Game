package game;
import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class Game {

    public void game(){

        System.out.print("Welcome to the game in which your goal is to kill all the\n" +
                "monsters and be the best fighter in the galaxy. On your way you will be\n" +
                "In your backpack you will store the items you have acquired which you can later use to help you.");
        sleepGame(6000);
        System.out.println("\n\nChoose the degree of difficulty of the game\n1 - easy\n2 - medium\n3 - hard");
        ArrayList<Monster> monsters = new ArrayList<>();
        Scanner scan = new Scanner(System.in);
        int level = scan.nextInt();
        Random number = new Random();
        if(level == 1){
            int counter = 1;
            for(int i = 0; i < 2; i++){
                Monster monster = new Monster("Zombie", counter, counter,counter);
                monsters.add(monster);
                int whichItem = number.nextInt(3) + 1;
                counter++;
                Item item;
                if(whichItem == 1) {
                    item = new Sword();
                }
                else if(whichItem == 2){
                    item = new Wand();
                }
                else{
                    item = new FirstAid();
                }
                monster.addItem(item);
            }
        }
        else if(level == 2){
            int counter = 2;
            for(int i = 0; i < 3; i++){
                Monster monster = new Monster("Ogre", counter, counter,counter);
                monsters.add(monster);
                counter += 2;
                Item item;
                int whichItem = number.nextInt(3) + 1;
                if(whichItem == 1) {
                    item = new Sword();
                }
                else if(whichItem == 2){
                    item = new Wand();
                }
                else{
                    item = new FirstAid();
                }
                monster.addItem(item);
            }
        }
        else if(level == 3) {
            int counter = 3;
            for (int i = 0; i < 4; i++) {
                Monster monster = new Monster("Sceleton", counter, counter, counter);
                monsters.add(monster);
                counter += 2;
                Item item;
                int whichItem = number.nextInt(3) + 1;
                if (whichItem == 1) {
                    item = new Sword();
                } else if (whichItem == 2) {
                    item = new Wand();
                } else {
                    item = new FirstAid();
                }
                monster.addItem(item);
            }
        }
        sleepGame(3000);
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nHi, what is your name?");
        String name = scanner.nextLine();
        System.out.println("Hello " + name + ". You are starting game now.\n");

        sleepGame(2000);
        System.out.println("Specify the attribute points your character should have (Pool of points to distribute - 15):\n ");
        int HP = 0;
        int stamina = 0;
        int mana = 0;
        do{
            System.out.println("HP: ");
            HP = scan.nextInt();
            if(HP >= 15){
                System.out.println("You don't have that many points. HP has to be lower than 15. Please try again.");
                continue;
            }
            System.out.println("Stamina: ");
            stamina = scan.nextInt();
            if(stamina >= 15-HP){
                System.out.println("You don't have that many points. Stamina has to be lower than " + (15-HP) + ". Please try again.");
                continue;
            }
            System.out.println("Mana: ");
            mana = scan.nextInt();
            if(mana > 15 - HP - stamina) {
                System.out.println("You don't have that many points. Mana has to be lower than " + (15 - HP - stamina) + ". Please try again.");
                continue;
            }
        } while(HP >= 15 || stamina >= 15-HP || mana > 15 - HP - stamina);

        Character me = new Character(name, HP, mana, stamina);
        me.setEXP(0);

        // losowanie dla mnie przedmiotu
        int whichItem = losowanie(3);
        if(whichItem == 1){
            Item item = new Sword();
            me.addItem(item);
        }
        else if(whichItem == 2){
            Item item = new Wand();
            me.addItem(item);
        }
        else{
            Item item = new FirstAid();
            me.addItem(item);
        }

        //gra
        int friendNo = 0;
        int monsterCounter = 1;
        while(monsters.size() != 0) {
            sleepGame(2000);
            System.out.println("Now You are facing " + monsterCounter + " Monster");
            Monster m = monsters.get(0);
            sleepGame(2000);
            System.out.println("\nYour statistics");
            me.getStatistics();
            System.out.println("\nMonster's statistics");
            m.getStatistics();
            sleepGame(1000);
            System.out.println("\nYour turn: \n1 - Physical attack(-5 stamina)\n2 - Magic attack(-5 mana)\n3 " +
                    "- Your items\n4 - call friend(" + me.friends.size() + ")");
            System.out.println("---------------------------");
            Scanner s = new Scanner(System.in);
            int myMove = s.nextInt();
            if (myMove == 1) {
                if(me.stamina < 2) {
                    System.out.println("You have not enought stamina to attack.");
                    sleepGame(3000);
                    continue;
                }
                me.attack(m, "physical");
            } else if (myMove == 2) {
                if(me.mana < 2) {
                    System.out.println("You have not enought mana to attack.");
                    sleepGame(3000);
                    continue;
                }
                me.attack(m, "magic");
            }
            else if(myMove == 3){
                if(me.items.size() > 0) {
                    int counter = 1;
                    System.out.println("\nYour items");
                    for (Item item : me.items) {
                        System.out.println(counter + ". " + item.name);
                        System.out.println();
                        counter++;
                    }
                    System.out.println("Do you want to use any of them? (press yes or no)");
                    Scanner scanItem = new Scanner(System.in);
                    String chooseItem = scanItem.nextLine();
                    if (chooseItem.equals("yes")){
                        System.out.println("Choose number of item you want to use");
                        Scanner numberOfItem = new Scanner(System.in);
                        int itemNumber = numberOfItem.nextInt();
                        me.items.get(itemNumber-1).use(me);
                        me.items.remove(itemNumber-1);
                        continue;
                    }
                    else {
                        continue;
                    }
                }
                else {
                    System.out.println("You don't have any items\n");
                    sleepGame(2000);
                    continue;
                }
            }
            else if(myMove == 4){
                if (me.friends.size() > 0) {
                    Friend friendToHelp = me.callFriend();
                    friendToHelp.attack(m);
                    me.addItem(friendToHelp.getItem());
                    me.friends.remove(0);
                    if(m.getHP() > 0) continue;
                }
                else {
                    System.out.println("You don't have any friends.");
                    sleepGame(2000);
                    continue;
                }
            }

            if(m.getHP() <= 0) {
                sleepGame(2000);
                System.out.println("\nCongratulation. You killed " + monsterCounter + " Monster\n");
                sleepGame(3000);
                monsterCounter++;
                me.addItem(m.getItem());
                me.setEXP(me.getEXP() + m.getEXPtoGive());
                if(m.getEXPtoGive() >=5){
                    System.out.println("You got " + m.getEXPtoGive() + "EXP from " + m.name +". Now you can increase +5 chosen attribute.");
                    System.out.println("1 - HP\n2 - stamina\n3 - Mana");
                    Scanner scanAttributeToIncrease = new Scanner(System.in);
                    int attributeToIncrease = scanAttributeToIncrease.nextInt();
                    if(attributeToIncrease == 1){
                        me.setHP(me.getHP() + 5);
                    }
                    else if(attributeToIncrease == 2){
                        me.setStamina(me.getStamina() + 5);
                    }
                    else if(attributeToIncrease == 3){
                        me.setMana(me.getMana() + 5);
                    }
                }
                monsters.remove(0);
                sleepGame(3000);
                if (friendNo > 3) continue;
                int numberForFriend = losowanie(3);
                if(numberForFriend == 1){
                    friendNo++;
                    String friendName ="";
                    if(friendNo == 1) name = "Jenna";
                    else if(friendNo == 2) name = "Leila";
                    else if(friendNo == 3) name = "Katia";
                    sleepGame(3000);
                    System.out.println("\nYou meet a friend on your path - " + name + ". You can call on her when you need help\n");
                    sleepGame(5000);
                    Friend friend = new Friend();
                    friend.setFriendly(true);
                    friend.setName(name);
                    int itemForFriend = losowanie(3);
                    if(itemForFriend == 1){
                        Item friendItem = new Sword();
                        friend.addItem(friendItem);
                    }
                    else if(itemForFriend == 2){
                        Item friendItem = new Wand();
                        friend.addItem(friendItem);
                    }
                    else{
                        Item friendItem = new FirstAid();
                        friend.addItem(friendItem);
                    }
                    me.addFriend(friend);
                }
                continue;
            }
            System.out.println("\nMonster is making move...\n");
            sleepGame(6000);
            int monsterAttack = losowanie(2);
            if(monsterAttack == 1) {
                if (m.getStamina() >= 2) {
                    m.attack(me, "physical", level);
                } else if (m.getMana() >= 2) {
                    m.attack(me, "magic", level);
                }
                else {
                    System.out.println("Monster has not enough mana and stamina to attack.");
                    sleepGame(2000);
                    continue;
                }
            }
            else if(monsterAttack == 2){
                if (m.getMana() >= 2) {
                    m.attack(me, "magic", level);
                } else if (m.getStamina() >= 2) {
                    m.attack(me, "physical", level);
                }
                else {
                    System.out.println("Monster has not enough mana and stamina to attack.");
                    sleepGame(2000);
                    continue;
                }
            }
        }
        if(monsters.size() == 0) {
            sleepGame(2000);
            System.out.println("Congratulation! You killed all Monsters. You are the winner.");
        }
        if(me.getHP() <= 0) {
            sleepGame(2000);
            System.out.println("I'm sorry. You lose");
        }
        }

        public static int losowanie(int bound){
            Random number = new Random();
            int whichItem = number.nextInt(bound) + 1;
            return whichItem;
        }
        public static void sleepGame(int seconds){
            try{
                Thread.sleep(seconds);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

    }

