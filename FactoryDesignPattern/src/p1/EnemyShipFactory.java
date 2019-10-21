package p1;

public class EnemyShipFactory {

    public EnemyShip makeEnemyShip(String newShipType) {
        if(newShipType.equals("U")) {
            return new UFOEnemyShip();
        } else if (newShipType.equals("R")) {
            return new RocketEnemyShip();
        } else if (newShipType.equals("H")) {
            return new HugeUFOEnemyShip();
        } else {
            return null;
        }
    }

}
