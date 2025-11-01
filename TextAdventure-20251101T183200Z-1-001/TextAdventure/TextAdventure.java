import java.util.*;

public class TextAdventure 
{
  FancyConsole console;
  Scanner inScanner;
  Player ourHero;
  Random rand = new Random();

  public TextAdventure()
  {
    console = new FancyConsole("My Alien Game!", 600, 600);
    inScanner = new Scanner(System.in);
    ourHero = new Player("Explorer", 100, 0);
  }

  public void play()
  {
    String input;
    console.setImage("bg1.png");

    System.out.println("What is your name:\n");
    input = inScanner.nextLine();
    ourHero.changeName(input);

    System.out.println("You wake up in a daze unfamiliar with your surroundings. \n Lasers flying across the sky, screams coming from all directions, and a large, seemingly endless ocean behind you.\nIn the distance, you see an alien coming toward you with their gun raised.\nOcean: Swim away from the alien\nSword: Grab the sword sitting on a rock and try to fight\n");
    input = inScanner.nextLine().toLowerCase();

    if (input.equals("ocean"))
      enterZone1();
    else if (input.equals("sword"))
      enterZone2();
    else
    {
      System.out.println("While trying to make a decision, the alien shoots you from afar and leaves you to bleed out");
      gameEnd();
    }
  }

  // Ocean Path 
  private void enterZone1()
  {
    console.setImage("ocean.png");
    System.out.println("You jump into the ocean and escape a fight that would most likely end with you dead.\nAfter swimming for a while, you come across a raft with some sort of talisman on it with a squid-like creature carved on the front.\nTouch: Interact with the artifact and see what it does\nLeave: Ignore the talisman\n");
    String choice = inScanner.nextLine().toLowerCase();

    if (choice.equals("touch"))
      enterZone3();
    else if (choice.equals("leave"))
      enterZone4();
    else
    {
      System.out.println("You took to long to decide, you run out of strength and slowly drown as you sink into the ocean.");
      gameEnd();
    }
  }

  // Sword Path
  private void enterZone2()
  {
    console.setImage("alienCharge.png");
    System.out.println("You grab the sword and charge at the alien!\nThe alien fires its weapon as you swing...\n");

    boolean win = rand.nextBoolean(); // 50/50 chance
    if (win)
    {
      ourHero.defeatMonster();
      console.setImage("alienChargeWin.png");
      System.out.println("You stab the alien through his heart and it falls to the ground.\nYou find 50 gold in its pocket! \n");
      ourHero.setGold(ourHero.getGold() + 50);
      enterZone5();
    }
    else
    {
      console.setImage("alienKilledYou");
      System.out.println("The alien fires first. You are hit in the chest and fall to the ground.\n");
      ourHero.setHealth(0);
      gameEnd();
    }
  }

  // Cthulhu Ending
  private void enterZone3()
  {
    console.setImage("boss.png");
    System.out.println("The moment you touch the artifact, the sea begins to boil.\nA monstrous shadow rises, tentacles the size of mountains.\nCthulhu awakens, and your mind shatters as darkness surrounds you.\n=== WORST ENDING ===\n");
    gameEnd();
  }

  // Swim Further
  private void enterZone4()
  {
    console.setImage("thingIDK.png");
    System.out.println("You leave the artifact alone and continue swimming until you find an island.\nOn the island, you see a temple in the distance. \nEnter: Go to the temple\nExplore: Walk around the island\n");
    String choice = inScanner.nextLine().toLowerCase();

    if (choice.equals("enter"))
      enterZone6();
    else if (choice.equals("explore"))
      enterZone5();
    else
    {
      System.out.println("You sit on the beach until night falls and eventually fall asleep. Aliens find you sleeping and kill you");
      gameEnd();
    }
  }
                                
  //  Alien Army
  private void enterZone5()
  {
    console.setImage("alienArmy.png");
    System.out.println("You hear tons of footsteps from behind a hill, many soldiers apear in front of you. \nFight: Take your sword and face them\nSurrender: Drop your weapon and give up\n");
    String choice = inScanner.nextLine().toLowerCase();

    if (choice.equals("fight"))
    {
      boolean survive = rand.nextInt(100) < 25; // 25% chance to survive
      if (survive)
      {
        console.setImage("alienArmyWin.png");
        ourHero.defeatMonster();
        ourHero.setGold(ourHero.getGold() + 50);
        System.out.println("Against all odds, you fight through the alien army and find a hidden passage underground.\n");
        enterZone6();
      }
      else
      {
        console.setImage("alienArmyLoss.png");
        System.out.println("You fight but theres just to many. You keep getting shot and everything around you turns red.\n");
        gameEnd();
      }
    }
    else if (!choice.equals("surrender"))
    {
      console.setImage("alienArmyLoss.png");
      System.out.println("You freeze, unsure of what to do. The aliens take your silence as a threat and fire their weapons.\n");
      gameEnd();
    }
    else
    {
      console.setImage("alienArmySurrender");
      System.out.println("You surrender, and the aliens capture you. They bring you aboard a massive ship heading toward an alien temple.\n");
      enterZone6();
    }
  }

  // Final Outcome
  private void enterZone6()
  {
    console.setImage("houseEnding.png");
    System.out.println("You arrive at a massive alien temple glowing with strange energy.\nA guardian emerges from the light and demands to see your worth.\nOnly those with both 50 gold && at least one monster defeated may pass.\n");

    if (ourHero.getGold() >= 50 && ourHero.getMonstersDefeated() > 0)
    {
      console.setImage("zone6_good.jpg");
      System.out.println("The guardian nods and steps aside.\nYou enter the temple and see a blinding light that seems to speak to you... \n ===BEST ENDING=== \n");
    }

    gameEnd();
  }

  private void gameEnd()
  {
    System.out.println("Game Over.\nFinal Stats:\nName: " + ourHero.getName() + "\nHealth: " + ourHero.getHealth() + "\nGold: " + ourHero.getGold() + "\nMonsters Defeated: " + ourHero.getMonstersDefeated() + "\n");
    inScanner.close();
  }
}