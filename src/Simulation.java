import java.util.Random;

public class Simulation {
    private Cave[][] caves = new Cave[20][20];
    private Player me = new Player();

    public Simulation(){
        for (int y = 0; y < 20; y++) {
            for (int x = 0; x < 20; x++) {
                this.caves[y][x] = new Cave(y,x);
            }
        }
        
    }


    // public void setPlayerObj(Player me){
    //      this.me = me;
    // }

    public int getRandomNo(){
        Random rand = new Random();
        return rand.nextInt(20);
    }

    public void setting(String beingSet, int thisMany){
        for(int i = 0; i<thisMany; i++){
            int x = getRandomNo();
            int y = getRandomNo();
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

    public void setTreasure(int thisMany){
        setting("treasure", thisMany);
        
    }

    public void setHoles(int thisMany){
        setting("hole", thisMany);
    }

    public void setExit(int thisMany){
       setting("exit", thisMany);
    }

    public void setBats(int thisMany){
        setting("bats", thisMany);
    }

    public void setWumpus(int thisMany){
        setting("wumpus", thisMany);
    }
    public void setPlayer(int y, int x){
        this.caves[y][x].setPlayer(true);
    }
    public void removePlayer(int y, int x){
        this.caves[y][x].setPlayer(false);
    }
    public void setRoute(int y, int x){
        this.caves[y][x].setRoute(true);
    }


    public Cave[][] getCaves() {
        return this.caves;
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
                    System.out.print("o");
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

