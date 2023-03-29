import java.util.Random;

public class Simulation {
    private Cell[][] cells = new Cell[20][20];

    public Simulation(){
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                this.cells[i][j] = new Cell(i,j);
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
            if(this.cells[x][y].getCellState()){
                i--;
            }
            else{
                if(beingSet.equalsIgnoreCase("treasure")){
                    this.cells[x][y].setTreasure(true);
                }
                else if(beingSet.equalsIgnoreCase("hole")){
                    this.cells[x][y].setHole(true);
                }
                else if(beingSet.equalsIgnoreCase("exit")){
                    this.cells[x][y].setExit(true);
                }
                else if(beingSet.equalsIgnoreCase("bats")){
                    this.cells[x][y].setBat(true);
                }
                else if(beingSet.equalsIgnoreCase("wumpus")){
                    this.cells[x][y].setWumpus(true);
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

    public void displayBoard(){
        for(int i = 0; i < 20; i++){
            for(int j = 0; j < 20; j++){
                if(this.cells[i][j].getBat()){
                    System.out.print("B");
                }
                else if(this.cells[i][j].getExit()){
                    System.out.print("E");
                }
                else if(this.cells[i][j].getTreasure()){
                    System.out.print("T");
                }
                else if(this.cells[i][j].getHole()){
                    System.out.print("H");
                }
                else if(this.cells[i][j].getWumpus()){
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
