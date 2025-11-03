import java.util.*;

public class TextAdventure 
{
    FancyConsole console;
    Scanner inScanner;
    Player ourHero;

    public TextAdventure()
    {
        console = new FancyConsole("My Alien Game!", 600, 600);
        inScanner = new Scanner(System.in);
        ourHero = new Player("Explorer", 100, 0);
    }

    public void play()
    {
        String input;
        showImage("bg1.png");

        System.out.println("What is your name:\n");
        input = inScanner.nextLine();
        ourHero.changeName(input);

        System.out.println("You wake up in a daze unfamiliar with your surroundings.\nLasers flying across the sky, screams coming from all directions, and a large, seemingly endless ocean behind you.\nIn the distance, you see an alien coming toward you with their gun raised.\nOcean: Swim away from the alien\nSword: Grab the sword sitting on a rock and try to fight\n");
        input = inScanner.nextLine().toLowerCase();

        if (input.equals("ocean"))
            enterZone1();
        else if (input.equals("sword"))
            enterZone2();
        else
        {
            System.out.println("While trying to make a decision, the alien shoots you from afar and leaves you to bleed out.\n");
            gameEnd();
        }
    }

    // Ocean  
    private void enterZone1()
    {
        showImage("ocean.png");
        System.out.println("You jump into the ocean and escape a fight that would most likely end with you dead.\nAfter swimming for a while, you come across a raft with some sort of talisman on it with a squid-like creature carved on the front.\nTouch: Interact with the artifact and see what it does\nLeave: Ignore the talisman\n");
        String choice = inScanner.nextLine().toLowerCase();

        if (choice.equals("touch"))
            enterZone3();
        else if (choice.equals("leave"))
            enterZone4();
        else
        {
            System.out.println("You took too long to decide, you run out of strength and slowly drown as you sink into the ocean.\n");
            gameEnd();
        }
    }

    // Sword
    private void enterZone2()
    {
        showImage("alienCharge.png");
        System.out.println("You grab the sword and charge at the alien!\nThe alien fires its weapon as you swing...\n");

        delayFiveSeconds();

        double chance = Math.random(); 
        boolean win = chance < 0.5;

        if (win)
        {
            ourHero.defeatMonster();
            showImage("alienChargeWin.png");
            System.out.println("You stab the alien through his heart and it falls to the ground.\nYou find 50 gold in its pocket!\n");
            ourHero.setGold(ourHero.getGold() + 50);
            enterZone5();
        }
        else
        {
            showImage("alienKilledYou.png");
            System.out.println("The alien fires first. You are hit in the chest and fall to the ground.\n");
            ourHero.setHealth(0);
            gameEnd();
        }
    }

    // Cthulhu
    private void enterZone3()
    {
        showImage("boss.png");
        System.out.println("The moment you touch the artifact, the sea begins to boil.\nA monstrous shadow rises, tentacles the size of mountains.\nCthulhu awakens, and your mind shatters as darkness surrounds you.\nFight: Try to fight the beast in front of you.\nFlee: Run away.\n");
        String choice = inScanner.nextLine().toLowerCase();

        if (choice.equals("fight")){

            enterZoneWorstEnding();
            System.out.print("You died trying to fight Cthulhu...\n=== Worst Ending ===\n");
        }
        else if (choice.equals("flee")){

            enterZoneWorstEnding();
            System.out.print("You tried to run away from Cthulhu, but it was no use...\n=== Worst Ending ===\n");
        }
        else{

            enterZoneWorstEnding();
            System.out.print("You hesitated for too long...\n=== Worst Ending ===\n");

        }
        gameEnd();
    }

    // Chulhu thingamabob
    private void enterZoneWorstEnding()
    {
        showImage("bossDied.png");
        gameEnd();
    }

    // Swim Further
    private void enterZone4()
    {
        showImage("thingIDK.png");
        System.out.println("You leave the artifact alone and continue swimming until you find an island.\nOn the island, you see a temple in the distance.\nEnter: Go to the temple\nExplore: Walk around the island\n");
        String choice = inScanner.nextLine().toLowerCase();

        if (choice.equals("enter"))
            enterZone6();
        else if (choice.equals("explore"))
            enterZone5();
        else
        {
            System.out.println("You sit on the beach until night falls and eventually fall asleep. Aliens find you sleeping and kill you.\n");
            gameEnd();
        }
    }

    // Alien Army
    private void enterZone5()
    {
        showImage("alienArmy.png");
        System.out.println("You hear tons of footsteps from behind a hill, many soldiers appear in front of you.\nFight: Take your sword and face them\nSurrender: Drop your weapon and give up\n");
        String choice = inScanner.nextLine().toLowerCase();

        if (choice.equals("fight"))
        {
            double chance = Math.random(); 
            boolean survive = chance < 0.25;

            if (survive)
            {
                showImage("alienArmyWin.png");
                ourHero.defeatMonster();
                ourHero.setGold(ourHero.getGold() + 50);
                System.out.println("Against all odds, you fight through the alien army and find a hidden passage underground.\n");
                enterZone6();
            }
            else
            {
                showImage("alienArmyLoss.png");
                System.out.println("You fight but there are just too many. You get shot, everything around you turns red and you fall over.\n");
                gameEnd();
            }
        }
        else if (!choice.equals("surrender"))
        {
            showImage("alienArmyLoss.png");
            System.out.println("You freeze unsure of what to do and the aliens take your silence as a threat and fire their weapons.\n");
            gameEnd();
        }
        else
        {
            showImage("alienArmySurrender.png");
            System.out.println("You surrender and the aliens capture you. \n They bring you onto a huge ship heading toward a alien temple.\n");
            enterZone6();
        }
    }

    // Endings
    private void enterZone6()
    {
        showImage("houseEnding.png");
        System.out.println("You arrive at a massive alien temple glowing with a strange light.\n A guardian emerges from the light and demands to see your worth.\n 'Only those with both 50 gold && at least one monster defeated may pass.' \n");
        delayFiveSeconds();
        
        if (ourHero.getGold() >= 50 && ourHero.getMonstersDefeated() > 0)
        {
            showImage("bestEnding.png");
            System.out.println("The guardian nods and steps aside.\nYou enter the temple and see a blinding light that seems to speak to you... \n === BEST ENDING === \n ");
        }
        else if (ourHero.getGold() < 50 || ourHero.getMonstersDefeated() == 0)
        {
            showImage("badEnding.png");
            System.out.println("The guardian shakes its head. You are deemed unworthy and vanish into a flash of white light. \n === BAD ENDING === \n ");
        }
        else
        {
            showImage("neutralEnding.png");
            System.out.println("The guardian fades away, leaving you standing alone as the temple disappears into thin air. \n === NEUTRAL ENDING === \n ");
        }

        gameEnd();
    }

    private void gameEnd()
    {
        System.out.println("Game Over\nFinal Stats:\nName: " + ourHero.getName() + "\nHealth: " + ourHero.getHealth() + "\nGold: " + ourHero.getGold() + "\nMonsters Defeated: " + ourHero.getMonstersDefeated() + "\n");
        inScanner.close();
    }

    private void showImage(String thingName){

        console.setImage(thingName);
        delayFiveSeconds();

    }

    private void delayFiveSeconds(){

        long start = System.currentTimeMillis();
        long end = start + 1000; 

        while (System.currentTimeMillis() < end){

        }

    }
}
