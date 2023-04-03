import java.util.Random;
import java.util.ArrayList;

public class Player {

    private int x;
    private int y;
    private int arrows;
    private boolean alive;

    public Player() {
        //assign random start postition (edit so don't spawn on a hazard)
        this.moveToRandom();

        this.alive = true;
        this.arrows = 5;
    }

    //move
    public void move(String direction) {
        if (direction.equals("s")) {
            if (this.y == 19) {
                this.y = 0;
            } else {
                this.y ++;
            }
        } else if (direction.equals("n")) {
            if (this.y == 0) {
                this.y = 19;
            } else {
                this.y --;
            }
        } else if (direction.equals("w")) {
            if (this.x == 0) {
                this.x = 19;
            } else {
                this.x --;
            }
        } else if (direction.equals("e")) {
            if (this.x == 19) {
                this.x = 0;
            }
            this.x ++;
        }
    }

    //shoot
    public void shoot() {
        //EDIT: add code to check for things in neighbouring caves?? or do it in cave??
        this.arrows--;
    }

    //move to random position
    public void moveToRandom() {
        Random rd = new Random();
        this.x = rd.nextInt(0,19);
        this.y = rd.nextInt(0,19);
    }

    //get the neigbouring caves
    public ArrayList<Cave> getNeighbours(Cave[][] caveSystem) {
        ArrayList<Cave> neighbourCaves = new ArrayList<Cave>();

        int currentX, currentY;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                currentX = this.x + i;
                currentY = this.y + j;

                //compensate for wrapping around in x
                if (currentX < 0) {
                    currentX += caveSystem.length;
                } else if (currentX >= caveSystem.length) {
                    currentX -= caveSystem.length;
                }
                
                //compensate for wrapping around in y
                if (currentY < 0) {
                    currentY += caveSystem.length;
                } else if (currentY >= caveSystem.length) {
                    currentY -= caveSystem.length;
                }

                if (i == j) {
                    continue;
                }

                neighbourCaves.add(caveSystem[currentX][currentY]);
            }
        } 

        return neighbourCaves;
    }

    //search through neighbouring caves and get percepts - finish once cave class written
    public void checkNeighbours(Cave[][] caveSystem) {
        ArrayList<Cave> neighbours = this.getNeighbours(caveSystem);

        for (Cave cave: neighbours) {
            if (cave.getHole()) {
                System.out.println("You feel a breeze");
            } else if (cave.getWumpus()) {
                System.out.println("You smell a Wumpus");
            } else if (cave.getTreasure()) {
                System.out.println("You see glittering from a neighbouring cave");
            }
        }
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