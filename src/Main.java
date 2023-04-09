import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        //Main obj = new Main();
        Simulation s = new Simulation();
        s.setBats(2);
        s.setExit(1);
        s.setHoles(3);
        s.setWumpus(1);
        s.setTreasure(4);
        
        Player me = new Player();
        //s.setPlayer(me);
        me.moveToRandom();
        s.setPlayer(me.getY(), me.getX());
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
                System.out.println("You fell in a hole and died. Better luck next time!");
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
            }
            s.removePlayer(yPosBeforeMove, xPosBeforeMove);
            s.setRoute(yPosBeforeMove, xPosBeforeMove);
            s.setPlayer(me.getY(), me.getX());
            s.displayBoard();
            System.out.println(me.getY() + " Y and X " + me.getX());
            
        }
        

    }
}
