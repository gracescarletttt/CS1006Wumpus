import java.util.Random;

public class Simulation {
    private Cave[][] caves = new Cave[20][20];

    public Simulation(){
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                this.caves[i][j] = new Cave(i,j);
            }
        }
    }

    public int getRandomNo(){
        Random rand = new Random();
        return rand.nextInt(20);
    }

    public void setting(String beingSet, int thisMany){
        for(int i = 0; i<thisMany; i++){
            int x = getRandomNo();
            int y = getRandomNo();
            if(this.caves[x][y].getCellState()){
                i--;
            }
            else{
                if(beingSet.equalsIgnoreCase("treasure")){
                    this.caves[x][y].setTreasure(true);
                }
                else if(beingSet.equalsIgnoreCase("hole")){
                    this.caves[x][y].setHole(true);
                }
                else if(beingSet.equalsIgnoreCase("exit")){
                    this.caves[x][y].setExit(true);
                }
                else if(beingSet.equalsIgnoreCase("bats")){
                    this.caves[x][y].setBat(true);
                }
                else if(beingSet.equalsIgnoreCase("wumpus")){
                    this.caves[x][y].setWumpus(true);
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
     public void setPlayer(int x, int y){
          this.caves[x][y].setPlayer(true);
    }

    public Cave[][] getCaves() {
        return this.caves;
    }

    public void displayBoard(){
        for(int i = 0; i < 20; i++){
            for(int j = 0; j < 20; j++){
                if(this.caves[i][j].getPlayer()){
                    System.out.print("P");
                }
                else if(this.caves[i][j].getBat()){
                    System.out.print("B");
                }
                else if(this.caves[i][j].getExit()){
                    System.out.print("E");
                }
                else if(this.caves[i][j].getTreasure()){
                    System.out.print("T");
                }
                else if(this.caves[i][j].getHole()){
                    System.out.print("H");
                }
                else if(this.caves[i][j].getWumpus()){
                    System.out.print("W");
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
