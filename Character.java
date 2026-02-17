public class Character {

    public int health;
    public int attackPower;

    // if character is alive- return true
    public boolean isAlive() {
        return health > 0;
    }

    // damage to other characters
    public void dealDamage(Character other) {
        if (other != null) {
            other.takeDamage(this.attackPower);
        }
    }

    // reduce health by given amount
    public void takeDamage(int damage) {
        health -= damage;
    }
}
