package p1;

public class Demo {

    public static void main(String[] args) {
        EnemyShipFactory factory = new EnemyShipFactory();
        EnemyShip enemyShip1 = factory.makeEnemyShip("U");
        doStuff(enemyShip1);
    }

    public static void doStuff(EnemyShip anEnemyShip) {
        // ...
    }
}
