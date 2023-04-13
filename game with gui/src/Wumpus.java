import java.util.Random;

public class Wumpus {
    private int x;
    private int y;
    private boolean alive;

    public Wumpus() {
        this.moveToRandom();

        this.alive = true;
    }

    //move to random position
    public void moveToRandom() {
        Random rd = new Random();
        this.x = rd.nextInt(0,19);
        this.y = rd.nextInt(0,19);
    }

    //----------------getters-------------------------
    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public boolean getStatus() {
        return this.alive;
    }

    //----------------setters-------------------------
    public void kill() {
        this.alive = false;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}


