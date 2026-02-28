/*
* Survivor.java
* Survivor class that extends from Character
* Initializes variables of health and attackPower as int type
* Parent class of Child, Teacher, Soldier
*/

package survivors;

import characters.Character;

private Weapon weapon;

public void setWeapon(Weapon weapon) {
    this.weapon = weapon;
}

public Weapon getWeapon() {
    return weapon;
}

public void attack(Character other, Random rng) {
    if (other == null) return;

    int damage = attackPower;

    if (weapon != null) {
        if (weapon.hits(rng)) {
            damage += weapon.getDamageLevel();
        } else {
            damage = 0; // for any missed shot
        }
    }

    other.takeDamage(damage);
}

public class Survivor extends Character {

    //initializing variables
    public Survivor(int health, int attackPower) {
        this.health = health;
        this.attackPower = attackPower;
    }
}
