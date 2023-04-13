import javax.swing.*;

public class Cave extends JButton {
    private int x;
    private int y;
    private boolean treasure;
    private boolean bat;
    private boolean hole;
    private boolean wumpus;
    private boolean exit;
    private boolean player;
    private boolean route;

    public Cave(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setRoute(boolean state){
        this.route = state;
    }
    public void setTreasure(boolean state){
        this.treasure = state;
    }
    public void setBat(boolean state){
        this.bat = state;
    }
    public void setHole(boolean state){
        this.hole = state;
    }
    public void setWumpus(boolean state){
        this.wumpus = state;
    }
    public void setExit(boolean state){
        this.exit = state;
    }
     public void setPlayer(boolean state){
        this.player = state;
    }

    public boolean getCellState(){
        if(this.treasure || this.bat || this.exit || this.wumpus || this.hole){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean getTreasure(){
        return this.treasure;
    }

    public boolean getBat(){
        return this.bat;
    }

    public boolean getHole(){
        return this.hole;
    }

    public boolean getWumpus(){
        return this.wumpus;
    }

    public boolean getExit(){
        return this.exit;
    }

    public boolean getPlayer(){
        return this.player;
    }

    public boolean getRoute(){
        return this.route;
    }

    public boolean checkWumpus(int x, int y) {
        if (x == this.x && y == this.y) {
            return true;
        } else {
            return false;
        }
    }
}
