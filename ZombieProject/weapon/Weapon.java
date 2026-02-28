/*
* Weapon.java
* Parent class of weapon types
* declares variables damageLevel and accuracyLevel
* Includes method for accuracy logic; Generates a random number between 1-100
* If number is within accuracy range, returns true(successful hit),
* If number is out of accuracy range, returns false(miss)
* i.e. Shotgun AccuracyLevel=75% ... if a roll is 42 <= 75 -> true -> HIT
*/

import java.util.Random;

public class Weapon {
    private final int damageLevel;
    private final int accuracyLevel; // store as percent 0..100

    public Weapon(int damageLevel, int accuracyLevel) {
        this.damageLevel = damageLevel;
        this.accuracyLevel = accuracyLevel;
    }

    //getters
    public int getDamageLevel() { return damageLevel; }
    public int getAccuracyLevel() { return accuracyLevel; }

    //returns simple class name as weapon name
    public String getWeaponName() {
        return getClass().getSimpleName();
    }

    //accuracy logic (true = hit, false = miss)
    public boolean hits(Random rng) {
        int roll = rng.nextInt(100) + 1; //1-100
        return roll <= accuracyLevel; //
    }

    //helper method for dealDamage
    public int getDamage() {
        return damageLevel;
    }

    //formatting
    @Override
    public String toString() {
        return getWeaponName() + " (DMG= " + damageLevel + ", Accuracy= " + accuracyLevel + "%)";
    }
} //end Weapon.java
