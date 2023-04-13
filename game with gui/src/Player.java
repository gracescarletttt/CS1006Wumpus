import java.util.Random;
import java.util.ArrayList;

public class Player {

    private int x;
    private int y;
    private int arrows;
    private boolean alive;
    private boolean hasTreasure;

    public Player() {
        //assign random start postition 
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
                this.y++;
            }
        } else if (direction.equals("w")) {
            if (this.y == 0) {
                this.y = 19;
            } else {
                this.y--;
            }
        } else if (direction.equals("a")) {
            if (this.x == 0) {
                this.x = 19;
            } else {
                this.x--;
            }
        } else if (direction.equals("d")) {
            if (this.x == 19) {
                this.x = 0;
            }
            this.x++;
        }
    }

    //shoot
    public void shoot(Cave[][] caveSystem, Wumpus wumpus, String direction, Simulation simulation) {
        Cave adjacentCave = null;

        if (direction.equals("w")) {
            int y;
            if (this.getY()== 0) {
                y = 19;
            } else {
                y = this.getY() - 1;
            }
            adjacentCave = caveSystem[this.getX()][y];
        } else if (direction.equals("s")) {
            int y;
            if (this.getY() == 19) {
                y = 0;
            } else {
                y = this.getY() + 1;
            }
            adjacentCave = caveSystem[this.getX()][y];
        } else if (direction.equals("d")) {
            int x;
            if (this.getX() == 19) {
                x = 0;
            } else {
                x = this.getX() + 1;
            } 
            adjacentCave = caveSystem[x][this.getY()];
        } else if (direction.equals("a")) {
            int x;
            if (this.getX() == 0) {
                x = 19;
            } else {
                x = this.getX() - 1;
            }
            adjacentCave = caveSystem[x][this.getY()];
        }
        else {
            System.out.println("improper direction");
            return;
        }

        if (this.arrows > 0) {
            if (adjacentCave.checkWumpus(wumpus.getX(), wumpus.getY())) {
                System.out.println("You killed the Wumpus!");
                wumpus.kill();
                simulation.removeWumpus(wumpus.getY(), wumpus.getX());
            } else {
                System.out.println("You missed.");
                simulation.removeWumpus(wumpus.getY(), wumpus.getX());
                wumpus.moveToRandom();
                simulation.setWumpus(wumpus.getY(),wumpus.getX());
            }
    
            this.arrows--;
        } else {
            System.out.println("You're out of arrows!");
        }


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

        int currentX = this.x;
        int currentY = this.y;
        for (int y = -1; y <= 1; y++) {
            for (int x = -1; x <= 1; x++) {
                currentX = this.x + x;
                currentY = this.y + y;

                //compensate for wrapping around in x
                if (currentY < 0) {
                    currentY += caveSystem.length;
                } else if (currentY >= caveSystem.length) {
                    currentY -= caveSystem.length;
                }
                
                //compensate for wrapping around in y
                if (currentX < 0) {
                    currentX += caveSystem.length;
                } else if (currentX >= caveSystem.length) {
                    currentX -= caveSystem.length;
                }

                //if the modulus of x and y are equal (anything other than directly adjacent squares) ignore
                 if (Math.abs(y) == Math.abs(x)) {
                     continue;
                }

                neighbourCaves.add(caveSystem[currentY][currentX]);
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

    //check the player's current position for hazards
    public void checkPosition(Cave[][] caveSystem, Simulation simulation) {
        Cave currentCave = caveSystem[this.getY()][this.getX()];

        if (currentCave.getBat()) {
            System.out.println("You got picked up and moved by the Superbats!");
            this.moveToRandom();
        } else if (currentCave.getWumpus()) {
            System.out.println("The Wumpus got you. You lose.");
            this.kill();
        } else if (currentCave.getHole()) {
            System.out.println("You fell in a pit and died. Better luck next time!");
            this.kill();
        } else if (currentCave.getTreasure()) {
            System.out.println("You found the treasure! Now you need to get to the exit!");
            this.setTreasure(true);
            simulation.removeTreasure(this.getY(), this.getX());
        } else if (currentCave.getExit()) {
            if (this.getTreasure()) {
                System.out.println("Congratulations! You escaped with the treasure!");
                simulation.stopRunning();
            } else {
                System.out.println("You've found the exit, however you have no treasure. Try to find the treasure and get back here safely to win.");
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

    public boolean getTreasure(){
        return this.hasTreasure;
    }

    public int getArrows() {
        return this.arrows;
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

    public void setTreasure(boolean hasTreasure){
        this.hasTreasure = hasTreasure;
    }
}
