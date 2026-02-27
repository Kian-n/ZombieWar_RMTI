# Zombie War Group Project CSC 422
Group Assignment by: Rachel, Taite, Moua, and Ibrahim. 

This Project simulates a war between zombies and survivors.
Each character has their own health and attack.
The program makes the zombies and survivors fight and reports how many survivors made it to safety.
Survivors use weapons against the zombies to aid in the fight.
Each weapon has a corresponding damage level and accuracy level.

# Files Used:
The Parent class Character.java was the main class that had the variables for Zombies and Survivors.
For the Zombies, we had two classes - CommonInfected.java and Tank.java
The survivor had three classes - Child.java, Teacher.java, and Soldier.java
Another parent class Weapon.java was implemented and it's the class that each weapon inherited from.
The individual weapon classes include: SubmachineGun.java, Shotgun.java, AssaultRifle.java, Pistol.java, Axe.java, Crowbar.java and FryingPan.java

# Program Logic
The program generates survivors and zombies. Each survivor receives a weapon type. Make each survivor attack every zombie, and have a zombie attack every survivor. Repeats until all zombies are dead or all survivors are dead. The program prints out who killed who and what weapon type was used by the survivor to eliminate a zombie. Once this is done, it prints the number of survivors who made it to safety.
