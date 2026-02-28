/*
* Survivor.java
* Survivor class that extends from Character
* Initializes variables of health and attackPower as int type
* Parent class of Child, Teacher, Soldier
*/

package survivors;

import characters.Character;
import java.util.Random;
import weapon.Weapon;

public class Survivor extends Character {

    //initializing variables
    private Weapon weapon;

    public Survivor(int health, int attackPower) {
        this.health = health;
        this.attackPower = attackPower;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public String getWeaponName() {
        if (weapon == null) return "Fists";
        return weapon.getWeaponName();
    }

    // Release 3 attack that uses weapon accuracy and damage
    public void attack(Character target, Random rng) {
        if (target == null || !this.isAlive() || !target.isAlive()) return;

        if (weapon == null) {
            target.takeDamage(this.attackPower);
            return;
        }

        if (weapon.hits(rng)) {
            target.takeDamage(weapon.getDamageLevel());
        }
        // else miss and do nothing
    }
}