// Homework 4: Assassin
//
// Instructor-provided testing program.

import java.io.*;
import java.util.*;

/** Class AssassinMain is the main client program for assassin game management.
    It reads names from a file names.txt, shuffles them, and uses them to
    start the game.  The user is asked for the name of the next victim until
    the game is over. */
public class AssassinMain {
    /** input file name from which to read data */
    public static final String INPUT_FILENAME = "names.txt";
    
    /** true for different results every run; false for predictable results */
    public static final boolean RANDOM = false;
    
    /** If not random, use this value to guide the sequence of numbers
        that will be generated by the Random object. */
    public static final int SEED = 42;


    public static void main(String[] args) throws FileNotFoundException {
        // read names into an ArrayList 
        File inputFile = new File(INPUT_FILENAME);
        if (!inputFile.canRead()) {
            System.out.println("Required input file not found; exiting.\n" + inputFile.getAbsolutePath());
            System.exit(1);
        }
        Scanner input = new Scanner(inputFile);
        
        ArrayList<String> nameList = new ArrayList<String>();
        
        while (input.hasNextLine()) {
            nameList.add(0, input.nextLine().trim());
        }

        // shuffle and build an AssassinManager
          Random rand = (RANDOM) ? new Random() : new Random(SEED);
        Collections.shuffle(nameList, rand);
        
        AssassinManager manager = new AssassinManager(nameList);

        // prompt the user for victims until the game is over
        Scanner console = new Scanner(System.in);
        while (!manager.isGameOver()) {
            oneKill(console, manager);
        }

        // report who won
        System.out.println("Game was won by " + manager.winner());
        System.out.println("Final graveyard is as follows:");
        manager.printGraveyard();

    }

    /** Handles the details of recording one victim.  Shows the current kill
        ring and graveyard to the user, prompts for a name and records the
        kill if the name is legal. */
    public static void oneKill(Scanner console, AssassinManager manager) {
        // print both linked lists
        System.out.println("Current kill ring:");
        manager.printKillRing();
        System.out.println("Current graveyard:");
        manager.printGraveyard();
        
        // prompt for next victim to kill
        System.out.println();
        System.out.print("next victim? ");
        String name = console.nextLine().trim();
        
        // kill the victim, if possible
        if (manager.graveyardContains(name)) {
            System.out.println(name + " is already dead.");
        } else if (!manager.killRingContains(name)) {
            System.out.println("Unknown person.");
        } else {
            manager.kill(name);
        }
        System.out.println();
    }
}
