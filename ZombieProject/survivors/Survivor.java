/*
* Survivor.java
* Survivor class that extends from Character
* Initializes variables of health and attackPower as int type
* Parent class of Child, Teacher, Soldier
*/

package survivors;

import characters.Character;

public class Survivor extends Character {

    //initializing variables
    public Survivor(int health, int attackPower) {
        this.health = health;
        this.attackPower = attackPower;
    }
}
