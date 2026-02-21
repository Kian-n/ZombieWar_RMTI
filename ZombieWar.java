/*
 * Taite Krogfus / Ibrahim Ali / Rachel Hanslik / Moua Yang
 * Main.java
 * Main class that simulates survivors trying to get to safety.
 * Uses Character, Survivor, Zombie, Child, Teacher, Soldier, Tank,
 * and CommonInfected classes.
 
 * 1.0 sample run details: 17 survivors, 8 zombies, 6 made to safety.
 * release1() uses a randomizer to select survivor and zombie types.
 * It uses a fixed seed so the output is always the same.
 */

import java.util.Random;

public class ZombieWar {

    public static void main(String[] args) {
        release1();
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
        printReport(survivors, zombies);
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
    public static void printReport(Survivor[] survivors, Zombie[] zombies) {

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

    //Loops through the characters after one round of combat to see who is still alive
    public static int countAliveSurvivors(Survivor[] survivors) {

        int count = 0;

        for (Survivor survivor : survivors) {
            if (survivor != null && survivor.isAlive()) {
                count++;
            }
        }

        return count;
    }
}
