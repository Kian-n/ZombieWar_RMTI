/*
 * Taite Krogfus / Ibrahim Ali / Rachel Hanslik / Moua Yang
 * Main.java
 * Main class that simulates survivors trying to get to safety.
 * Uses Character, Survivor, Zombie, Child, Teacher, Soldier, Tank,
 * and CommonInfected classes.
 
 */

import characters.Character;
import java.util.Random;
import survivors.*;
import weapon.*;
import zombies.*;

public class ZombieWar {

    public static void main(String[] args) {

        // ===== Added Welcome Banner =====
        System.out.println("\n**********************************");
        System.out.println("   !!WARNING!! ZOMBIE OUTBREAK");
        System.out.println("**********************************\n");

        System.out.println("Running Release 1.0");
        release1();

        System.out.println(); 
        System.out.println("Running Release 2.0"); 
        run2();
    
        System.out.println();
        System.out.println("Running Release 3.0");
        run3();
    }

    public static void release1() {

        //Number of zombies and survivors in the simulation
        int numSurvivors = 17;
        int numZombies = 8;

        // Fixed seed so sample run ALWAYS produces same result
        Random rand = new Random(8);

        //Arrays to store zombies and survivors
        Survivor[] survivors = new Survivor[numSurvivors];
        Zombie[] zombies = new Zombie[numZombies];

        String[] survivorNames = new String[numSurvivors];
        String[] zombieNames = new String[numZombies];

        //Initialzing the characters
        int childNum = 0;
        int teacherNum = 0;
        int soldierNum = 0;

        // Create survivors
        for (int i = 0; i < survivors.length; i++) {

            int choice = rand.nextInt(3); 
            // 0 = child, 1 = teacher, 2 = soldier

            if (choice == 0) {
                survivors[i] = new Child();
                survivorNames[i] = "Child" + childNum;
                childNum++;
            }
            else if (choice == 1) {
                survivors[i] = new Teacher();
                survivorNames[i] = "Teacher" + teacherNum;
                teacherNum++;
            }
            else {
                survivors[i] = new Soldier();
                survivorNames[i] = "Soldier" + soldierNum;
                soldierNum++;
            }
        }

        int commonNum = 0;
        int tankNum = 0;

        // Create zombies
        for (int i = 0; i < zombies.length; i++) {

            int choice = rand.nextInt(2); 
            // 0 = common infected, 1 = tank

            if (choice == 0) {
                zombies[i] = new CommonInfected();
                zombieNames[i] = "CommonInfected" + commonNum;
                commonNum++;
            }
            else {
                zombies[i] = new Tank();
                zombieNames[i] = "Tank" + tankNum;
                tankNum++;
            }
        }

        System.out.println("Zombie War 1.0 sample results:");

        //Run one round of combat
        runBattle(survivors, zombies);

        //Report of the survivors
        printReport1(survivors, zombies);
    }

    // Simple battle method
    //The function performs one round of combat
    //Each character hets to attack until they either die or win
    public static void runBattle(Survivor[] survivors, Zombie[] zombies) {

        // Survivors attack all zombies
        for (Survivor survivor : survivors) {
            if (survivor != null && survivor.isAlive()) {

                //Survivors attack all zombies that are still alive
                for (Zombie zombie : zombies) {
                    if (zombie != null && zombie.isAlive()) {
                        survivor.dealDamage(zombie);
                    }
                }
            }
        }

        // Zombies attack all survivors
        for (Zombie zombie : zombies) {
            if (zombie != null && zombie.isAlive()) {

                //Zombies attack all survivors that are still alive
                for (Survivor survivor : survivors) {
                    if (survivor != null && survivor.isAlive()) {
                        zombie.dealDamage(survivor);
                    }
                }
            }
        }
    }

    // Prints the Release 1.0 
    public static void printReport1(Survivor[] survivors, Zombie[] zombies) {

        int totalSurvivors = survivors.length;
        int totalZombies = zombies.length;
        int survivorsAlive = countAliveSurvivors(survivors);

        System.out.println("We have " + totalSurvivors +
                " survivors trying to make it to safety.");

        System.out.println("But there are " + totalZombies +
                " zombies waiting for them.");

        System.out.println("It seems " + survivorsAlive +
                " have made it to safety.");
    }

    // Release 2
    public static void run2() {

        Survivor[] survivors = new Survivor[5];
        Zombie[] zombies = new Zombie[9];

        String[] survivorNames = new String[survivors.length];
        String[] zombieNames = new String[zombies.length];

        int index = 0;

        // 3 teachers
        survivors[index] = new Teacher();
        survivorNames[index] = "Teacher0"; index++;

        survivors[index] = new Teacher();
        survivorNames[index] = "Teacher1"; index++;

        survivors[index] = new Teacher();
        survivorNames[index] = "Teacher2"; index++;

        // 2 soldiers
        survivors[index] = new Soldier();
        survivorNames[index] = "Soldier0"; index++;

        survivors[index] = new Soldier();
        survivorNames[index] = "Soldier1"; index++;

        int j = 0;

        // 2 common infected
        zombies[j] = new CommonInfected();
        zombieNames[j] = "CommonInfected0"; j++;

        zombies[j] = new CommonInfected();
        zombieNames[j] = "CommonInfected1"; j++;

        // 7 tanks
        for (int k = 0; k < 7; k++) {
            zombies[j] = new Tank();
            zombieNames[j] = "Tank" + k;
            j++;
        }

        System.out.println("Zombie War 2.0 sample results:");
        runBattle2(survivors, zombies, survivorNames, zombieNames);
        printReport2(survivors, zombies);
    }

    // Battle for Release 2.0 (prints kill messages)
    public static void runBattle2(Survivor[] survivors, Zombie[] zombies,
                                  String[] survivorNames, String[] zombieNames) {

        while (anyAlive(survivors) && anyAlive(zombies)) {

            // Survivors attack zombies
            for (int i = 0; i < survivors.length; i++) {
                if (survivors[i] == null || !survivors[i].isAlive()) continue;

                for (int z = 0; z < zombies.length; z++) {
                    if (zombies[z] == null || !zombies[z].isAlive()) continue;

                    boolean wasAlive = zombies[z].isAlive();
                    survivors[i].dealDamage(zombies[z]);

                    if (wasAlive && !zombies[z].isAlive()) {
                        System.out.println("   " + survivorNames[i] + " killed " + zombieNames[z]);
                    }
                }
            }

            if (!anyAlive(zombies)) break;

            // Zombies attack survivors
            //Each zombie kill is tracked for the report
            for (int z = 0; z < zombies.length; z++) {
                if (zombies[z] == null || !zombies[z].isAlive()) continue;

                for (int s = 0; s < survivors.length; s++) {
                    if (survivors[s] == null || !survivors[s].isAlive()) continue;

                    boolean wasAlive = survivors[s].isAlive();
                    zombies[z].dealDamage(survivors[s]);

                    if (wasAlive && !survivors[s].isAlive()) {
                        System.out.println("   " + zombieNames[z] + " killed " + survivorNames[s]);
                    }
                }
            }
        }
    }

    public static void printReport2(Survivor[] survivors, Zombie[] zombies) {

        //Counts how many survivors are left after the combat
        int survivorsAlive = countAlive(survivors);

        System.out.println();
        System.out.println("We have 5 survivors trying to make it to safety (0 children, 3 teachers, 2 soldiers)");
        System.out.println("But there are 9 zombies waiting for them (2 common infected, 7 tanks)");

        //If there are no survivors
        if (survivorsAlive == 0) {
            System.out.println("None of the survivors made it.");
        
        //If survivors made it to safety
        } else {
            System.out.println("It seems " + survivorsAlive + " made it to safety.");
        }
    }

    private static boolean anyAlive(Character[] arr) {
        for (Character c : arr) {
            if (c != null && c.isAlive()) return true;
        }
        return false;
    }

    private static int countAlive(Character[] arr) {
        int count = 0;
        for (Character c : arr) {
            if (c != null && c.isAlive()) count++;
        }
        return count;
    }

    public static int countAliveSurvivors(Survivor[] survivors) {
        int count = 0;
        for (Survivor survivor : survivors) {
            if (survivor != null && survivor.isAlive()) {
                count++;
            }
        }
        return count;
    }

    public static void run3() {

        Survivor[] survivors = new Survivor[5];
        Zombie[] zombies = new Zombie[5];
        String[] survivorNames = new String[5];
        String[] zombieNames = new String[5];

        Random rand = new Random(8);

        int childNum = 0, teacherNum = 0, soldierNum = 0;

        for (int i = 0; i < survivors.length; i++) {
            survivors[i] = new Soldier();  
            survivorNames[i] = "Soldier" + i;
        }

        // Create survivors with random types
        for (int i = 0; i < survivors.length; i++) {

            int typeChoice = rand.nextInt(3); // 0 = Child, 1 = Teacher, 2 = Soldier

            if (typeChoice == 0) {
                survivors[i] = new Child();
                survivorNames[i] = "Child" + childNum;
                childNum++;
            } else if (typeChoice == 1) {
                survivors[i] = new Teacher();
                survivorNames[i] = "Teacher" + teacherNum;
                teacherNum++;
            } else {
                survivors[i] = new Soldier();
                survivorNames[i] = "Soldier" + soldierNum;
                soldierNum++;
            }

            int weaponChoice = rand.nextInt(7);

            switch (weaponChoice) {
                case 0: survivors[i].setWeapon(new AssaultRifle()); break;
                case 1: survivors[i].setWeapon(new Shotgun()); break;
                case 2: survivors[i].setWeapon(new Pistol()); break;
                case 3: survivors[i].setWeapon(new Axe()); break;
                case 4: survivors[i].setWeapon(new Crowbar()); break;
                case 5: survivors[i].setWeapon(new FryingPan()); break;
                case 6: survivors[i].setWeapon(new SubmachineGun()); break;
            }

            System.out.println(survivorNames[i] + 
                " was given a " + survivors[i].getWeaponName());
        }

        for (int i = 0; i < zombies.length; i++) {
            zombies[i] = new CommonInfected();
            zombieNames[i] = "CommonInfected" + i;
        }

        System.out.println("\nZombie War 3.0 sample results:");
        runBattle3(survivors, zombies, survivorNames, zombieNames, rand);

        // ===== Added Battle Summary =====
        System.out.println("\n**********************************");
        System.out.println("         BATTLE SUMMARY");
        System.out.println("**********************************");

        System.out.println("Survivors remaining: " + countAlive(survivors));
        System.out.println("Zombies remaining: " + countAlive(zombies));

        System.out.println("\n===== Simulation Complete =====");
    }

    public static void runBattle3(Survivor[] survivors, Zombie[] zombies,
                              String[] survivorNames, String[] zombieNames,
                              Random rand) {

        while (anyAlive(survivors) && anyAlive(zombies)) {

            // Survivors attack using weapons
            for (int i = 0; i < survivors.length; i++) {
                if (survivors[i] == null || !survivors[i].isAlive()) continue;

                for (int z = 0; z < zombies.length; z++) {
                    if (zombies[z] == null || !zombies[z].isAlive()) continue;

                    boolean wasAlive = zombies[z].isAlive();

                    survivors[i].attack(zombies[z], rand);

                    if (wasAlive && !zombies[z].isAlive()) {
                        System.out.println("   " + survivorNames[i] +
                                " killed " + zombieNames[z] +
                                " using " + survivors[i].getWeaponName());
                    }
                }
            }

            if (!anyAlive(zombies)) break;

            // Zombies attack normally
            for (int z = 0; z < zombies.length; z++) {
                if (zombies[z] == null || !zombies[z].isAlive()) continue;

                for (int s = 0; s < survivors.length; s++) {
                    if (survivors[s] == null || !survivors[s].isAlive()) continue;

                    boolean wasAlive = survivors[s].isAlive();

                    zombies[z].dealDamage(survivors[s]);

                    if (wasAlive && !survivors[s].isAlive()) {
                        System.out.println("   " + zombieNames[z] +
                                " killed " + survivorNames[s]);
                    }
                }
            }
        }
    }
}