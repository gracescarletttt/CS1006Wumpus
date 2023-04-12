import java.util.Random;

public class Simulation {
    private Cave[][] caves = new Cave[20][20];
    private boolean running;

    public Simulation(){
        for (int y = 0; y < 20; y++) {
            for (int x = 0; x < 20; x++) {
                this.caves[y][x] = new Cave(y,x);
            }
        }

        this.running = true;    
    }

    public int getRandomNo(){
        Random rand = new Random();
        return rand.nextInt(20);
    }

    public void setting(String beingSet, int thisMany, int playerY, int playerX, int wumpusY, int wumpusX){
        for(int i = 0; i<thisMany; i++){
            int x = getRandomNo();
            int y = getRandomNo();
            int x2 = getRandomNo();
            int y2 = getRandomNo();
            while((x == playerX && y == playerY) || (x2 == wumpusX && y2 == wumpusY)){
                if(x == playerX && y == playerX){
                    x = getRandomNo();
                    y = getRandomNo();
                }
                else if(x2 == wumpusX && y2 == wumpusY){
                    x2 = getRandomNo();
                    y2 = getRandomNo();
                }
            }
            if(this.caves[y][x].getCellState()){
                i--;
            }
            else{
                if(beingSet.equalsIgnoreCase("treasure")){
                    this.caves[y][x].setTreasure(true);
                }
                else if(beingSet.equalsIgnoreCase("hole")){
                    this.caves[y][x].setHole(true);
                }
                else if(beingSet.equalsIgnoreCase("exit")){
                    this.caves[y][x].setExit(true);
                }
                else if(beingSet.equalsIgnoreCase("bats")){
                    this.caves[y][x].setBat(true);
                }
                else if(beingSet.equalsIgnoreCase("wumpus")){
                    this.caves[y][x].setWumpus(true);
                }
            }
        }
        
        
    }

    public void setTreasure(int thisMany, int playerY, int playerX, int wumpusY, int wumpusX){
        setting("treasure", thisMany, playerY, playerX, wumpusY, wumpusX);
        
    }

    public void setHoles(int thisMany, int playerY, int playerX, int wumpusY, int wumpusX){
        setting("hole", thisMany, playerY, playerX, wumpusY, wumpusX);
    }

    public void setExit(int thisMany, int playerY, int playerX, int wumpusY, int wumpusX){
       setting("exit", thisMany, playerY, playerX, wumpusY, wumpusX);
    }

    public void setBats(int thisMany, int playerY, int playerX, int wumpusY, int wumpusX){
        setting("bats", thisMany, playerY, playerX, wumpusY, wumpusX);
    }

    public void setPlayer(int y, int x){
        this.caves[y][x].setPlayer(true);
    }
    
    public void setWumpus(int y, int x){
        this.caves[y][x].setWumpus(true);
    }
    
    public void setRoute(int y, int x){
        this.caves[y][x].setRoute(true);
    }


    public void removePlayer(int y, int x){
        this.caves[y][x].setPlayer(false);
    }

    public void removeTreasure(int y, int x){
        this.caves[y][x].setTreasure(false);
    }

    public void removeWumpus(int y, int x) {
        this.caves[y][x].setWumpus(false);
    }

    public void stopRunning() {
        this.running = false;
    }

    public Cave[][] getCaves() {
        return this.caves;
    }

    public boolean getRunning() {
        return this.running;
    }

    public boolean checkHole(int y, int x){
        if(this.caves[y][x].getHole() == true){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean checkTreasure(int y, int x){
        if(this.caves[y][x].getTreasure() == true){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean checkBats(int y, int x){
        if(this.caves[y][x].getBat() == true){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean checkExit(int y, int x){
        if(this.caves[y][x].getExit() == true){
            return true;
        }
        else{
            return false;
        }
    }

    public void displayBoard(){
        for(int y = 0; y < 20; y++){
            for(int x = 0; x < 20; x++){
                if(this.caves[y][x].getPlayer()){
                    System.out.print("P");
                }
                else if(this.caves[y][x].getBat()){
                    System.out.print("B");
                }
                else if(this.caves[y][x].getExit()){
                    System.out.print("E");
                }
                else if(this.caves[y][x].getTreasure()){
                    System.out.print("T");
                }
                else if(this.caves[y][x].getHole()){
                    System.out.print("H");
                }
                else if(this.caves[y][x].getWumpus()){
                    System.out.print("W");

                }
                else if(this.caves[y][x].getRoute()){
                    System.out.print("*");
                }
                else{
                    System.out.print(".");
                }
                System.out.print(" ");
            }
            System.out.println("");
        }
    }
}

