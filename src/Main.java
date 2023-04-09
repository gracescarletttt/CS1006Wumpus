import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        //Main obj = new Main();
        Simulation s = new Simulation();
        
        
        Player me = new Player();
        Wumpus wumpus = new Wumpus();
        //s.setPlayer(me);
        me.moveToRandom();
        s.setPlayer(me.getY(), me.getX());
        wumpus.moveToRandom(me.getY(), me.getX());
        s.setWumpus(wumpus.getY(), wumpus.getX());
        s.setBats(2, me.getY(), me.getX(), wumpus.getY(), wumpus.getX());//for each of these sets, pass in coordinates of wumpus and player to make sure these aren't placed on their co-ordinates
        s.setExit(1, me.getY(), me.getX(), wumpus.getY(), wumpus.getX());
        s.setHoles(4, me.getY(), me.getX(), wumpus.getY(), wumpus.getX());
        //s.setWumpus(1);
        s.setTreasure(1, me.getY(), me.getX(), wumpus.getY(), wumpus.getX());
        s.displayBoard();
        System.out.println(me.getY() + " Y and X " + me.getX());
        while(me.getStatus()){
            Scanner reader = new Scanner(System.in);
            String command = reader.nextLine();
            int yPosBeforeMove = me.getY();
            int xPosBeforeMove = me.getX();
            me.move(command);
            if(s.checkHole(me.getY(), me.getX())){
                me.kill();
                System.out.println("You fell in a pitt and died. Better luck next time!");
            }
            if(s.checkExit(me.getY(), me.getX())){
                if(me.getTreasure()){
                    System.out.println("Congratulations! You won!");
                    break;
                }
                else{
                    System.out.println("You found the exit, however you have no treasure. Try find the treasure and get back here safely to win.");
                }
            }
            if(s.checkBats(me.getY(), me.getX())){
                me.moveToRandom();
                s.setPlayer(me.getY(), me.getX());
            }

            me.checkNeighbours(s.getCaves());
            if(s.checkTreasure(me.getY(), me.getX())){
                me.setTreasure(true);
                System.out.println("Treasure collected.");
                s.removeTreasure(me.getY(), me.getX());
            }
            s.removePlayer(yPosBeforeMove, xPosBeforeMove);
            s.setRoute(yPosBeforeMove, xPosBeforeMove);
            s.setPlayer(me.getY(), me.getX());
            s.displayBoard();
            System.out.println(me.getY() + " Y and X " + me.getX());
            
        }
        

    }
}
