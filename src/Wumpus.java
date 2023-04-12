import java.util.ArrayList;
import java.util.Random;

public class Wumpus {
    private int x;
    private int y;
    private boolean alive;

    public Wumpus() {
        this.moveToRandom();

        this.alive = true;
    }

    public void move2(int playerY, int playerX){
        Random rand = new Random();
        int num = rand.nextInt(3);
        if(playerX - this.x > 0){//Player is to right
            if(num == 0){
                this.x--;
            }
            else if(num == 1){
                this.y--;
            }
            else{
                this.y++;
            }
        }
        else if(playerY - this.y > 0){//Player is below
            if(num == 0){
                this.y--;
            }
            else if(num == 1){
                this.x--;
            }
            else{
                this.x++;
            }
        }
        else if(playerY - this.y < 0){//Player is above
            if(num == 0){
                this.y++;
            }
            else if(num == 1){
                this.x--;
            }
            else{
                this.x++;
            }
        }
        else if(playerX - this.x < 0){//Player is to left
            if(num == 0){
                this.x++;
            }
            else if(num == 1){
                this.y--;
            }
            else{
                this.y++;
            }
        }
    }

    //move to random position
    public void moveToRandom() {
        Random rd = new Random();
        this.x = rd.nextInt(0,19);
        this.x = rd.nextInt(0,19);
    }

    //get the neigbouring caves
    public ArrayList<Cave> getNeighbours(Cave[][] caveSystem) {
        ArrayList<Cave> neighbourCaves = new ArrayList<Cave>();

        //int currentX = this.x;
        //int currentY = this.y;
        for (int y = -1; y <= 1; y++) {
            for (int x = -1; x <= 1; x++) {
                //currentX = this.x + i;
                //currentY = this.y + j;

                int currentY = this.y + y;
                int currentX = this.x + x;

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

                 if (y == x) {
                     continue;
                }

                neighbourCaves.add(caveSystem[currentY][currentX]);
            }
        } 

        return neighbourCaves;
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


